package com.secusociale.portail.service;

import com.secusociale.portail.domain.SalarieTP;
import com.secusociale.portail.domain.TempsDePresence;
import com.secusociale.portail.domain.enumeration.StatutTP;
import com.secusociale.portail.model.CheckFileModel;
import com.secusociale.portail.repository.TempsDePresenceRepository;
import io.jsonwebtoken.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class TempsDePresenceService {

    @Autowired
    private TempsDePresenceRepository tempsDePresenceRepository;

    @Autowired
    private ContenuTPService contenuTPService;

    @Value("${application.docdir}")
    private String docDir;

    @Value("${application.docuri}")
    private String docuri;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TempsDePresence saveTempsDePresence(TempsDePresence tp) {
        return tempsDePresenceRepository.save(tp);
    }

        public String extractBase64(String input) {

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("La chaine base64 ne peut pas être null ou vide");
        }

        int semicolonIndex = input.indexOf(";");

        if (semicolonIndex == -1) {
            throw new IllegalArgumentException("Aucun point-virgule trouvé dans la base64");
        }

        String base64Part = input.substring(semicolonIndex + 1);

        if (!base64Part.startsWith("base64,")) {
            throw new IllegalArgumentException("Format base64 incorrect");
        }

        String base64Content = base64Part.substring("base64,".length());

        if (base64Content.isEmpty()) {
            throw new IllegalArgumentException("Contenu base64 vide");
        }
        return base64Content;
    }

    public ResponseEntity<HashMap<String, Object>> validateFile(CheckFileModel checkFileModel, HashMap<String, Object> result) throws java.io.IOException, InvalidFormatException {

        HashMap<String, Object> contenuTP = new HashMap<>();
        HashMap<String, Object> fileTP = new HashMap<>();
        HashMap<String, Object> tempsDePresence = new HashMap<>();
        HashMap<String, Object> rapport = new HashMap<>();
        String base64 = null;
        try {
            base64 = extractBase64(checkFileModel.getFileEncoded());
        } catch (IllegalArgumentException e) {
            result.put("code", "400");
            result.put("erreur", e.getMessage());
            return ResponseEntity.ok(result);
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64);

        try (ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes)) {
            Workbook workbook = WorkbookFactory.create(bis);
            Sheet sheet = workbook.getSheetAt(0);

            // Vérification des cellules spécifiques
            String[][] expectedValues = {
                {"A1", "Informations de L'employeur"},
                {"A2", "Numéro unique employeur"},
                {"B2", "Année"},
                {"C2", "Trimestre"},
                {"D2", "Ancien numero css"},
                {"E2", "Ancien numero ipres"},
                {"A5", "Informations des salariés"},
                {"A6", "Numéro Assuré Social"},
                {"B6", "Nom"},
                {"C6", "Prénom"},
                {"D6", "Type de Piéce"},
                {"E6", "Numéro pièce"},
                {"F6", "Age"},
                {"G6", "Nbre enfant eligible"},
                {"H6", "Temps de présence Heures mois 1"},
                {"I6", "Temps de présence Heures mois 2"},
                {"J6", "Temps de présence Heures mois 3"},
                {"K6", "Observation"}
            };

            for (String[] expected : expectedValues) {
                String cellRef = expected[0];
                String expectedValue = expected[1];
                int rowIdx = Integer.parseInt(cellRef.substring(1)) - 1;
                int colIdx = cellRef.charAt(0) - 'A';
                Row row = sheet.getRow(rowIdx);
                Cell cell = (row != null) ? row.getCell(colIdx) : null;
                if (cell == null || !cell.getStringCellValue().equals(expectedValue)) {
                    result.put("code", "400");
                    result.put("valeur attendue", expectedValue);
                    result.put("valeur presente", (cell != null) ? cell.getStringCellValue() : "null");
                    result.put("erreur", "La cellule " + cellRef + " ne contient pas la valeur attendue.");
                    return ResponseEntity.ok(result);
                }
            }

            // Vérifications spécifiques pour A3, B3, B4, B5, et B6
            int currentYear = LocalDate.now().getYear();

            // Vérification A3
            Cell a3 = sheet.getRow(2).getCell(0);
            Long a3Value = null;
            if (a3 != null) {
                try {
                    a3Value = Long.parseLong("" + a3);
                } catch (Exception e) {
                    result.put("code", "400");
                    result.put("erreur", "La cellule A3 n'est pas de type numérique.");
                    return ResponseEntity.ok(result);
                }
            }
            if (a3Value == null || !a3Value.equals(checkFileModel.getNumeroUnique())) {
                result.put("code", "400");
                result.put("Numéro unique du fichier", a3Value);
                result.put("Numéro unique en paramètre", checkFileModel.getNumeroUnique());
                result.put("erreur", "La cellule Numéro unique du fichier ne contient pas la valeur du numéro unique donné en paramétre");
                return ResponseEntity.ok(result);
            }

            // Vérification B3
            Cell b3 = sheet.getRow(2).getCell(1);

            Double b3Value = null;
            if (b3 != null) {
                try {
                    b3Value = b3.getNumericCellValue();
                } catch (IllegalStateException e) {
                    result.put("code", "400");
                    result.put("erreur", "La cellule B3 n'est pas de type numérique.");
                    return ResponseEntity.ok(result);
                }
            }

            if (b3Value == null || !isNumeric(String.valueOf(b3Value)) || Double.parseDouble(String.valueOf(b3Value)) < currentYear - 2) {
                result.put("code", "400");
                result.put("erreur", "La cellule B3 doit être numérique et supérieure ou égale à " + (currentYear - 2));
                return ResponseEntity.ok(result);
            }

            // Vérification C3
            Cell c3 = sheet.getRow(2).getCell(2);
            Double c3Value = null;
            if (c3 != null) {
                try {
                    c3Value = c3.getNumericCellValue();
                } catch (IllegalStateException e) {
                    result.put("code", "400");
                    result.put("erreur", "La cellule C3 n'est pas de type numérique.");
                    return ResponseEntity.ok(result);
                }
            }

            List<Double> validValues = Arrays.asList(1.0, 2.0, 3.0, 4.0);
            if (c3Value == null || !validValues.contains(c3Value)) {
                result.put("code", "400");
                result.put("erreur", "La cellule C3 doit être numérique et égale à 1, 2, 3 ou 4.");
                return ResponseEntity.ok(result);
            }

            // Vérification D3 ou E3
            Cell d3 = sheet.getRow(2).getCell(3);
            Double d3Value = null;
            if (d3 != null) {
                try {
                    d3Value = d3.getNumericCellValue();
                } catch (IllegalStateException e) {
                    result.put("code", "400");
                    result.put("erreur", "La cellule D3 n'est pas de type numérique.");
                    return ResponseEntity.ok(result);
                }
            }

            Cell e3 = sheet.getRow(2).getCell(4);
            Double e3Value = null;
            if (e3 != null) {
                try {
                    e3Value = e3.getNumericCellValue();
                } catch (IllegalStateException e) {
                    result.put("code", "400");
                    result.put("erreur", "La cellule E3 n'est pas de type numérique.");
                    return ResponseEntity.ok(result);
                }
            }

            // Lecture des données des colonnes A à J à partir de la ligne 7
            List<Map<String, String>> salarieTPs = new ArrayList<>();
            for (int i = 6; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j <= 10; j++) {
                    Cell cell = row.getCell(j);
                    String key = getKey(j);
                    String cellRef = "" + ((char) ('A' + j)) + (i + 1);
                    if (cell == null && j != 10) {
                        result.put("code", "400");
                        result.put("erreur", "La cellule " + cellRef + " ne doit pas être vide.");
                        return ResponseEntity.ok(result);
                    }
                    // test sur l'age
                    if (j == 5) {
                        if (cell.getNumericCellValue() > 65) {
                            result.put("code", "400");
                            result.put("erreur", "La cellule " + cellRef + " doit être numérique et inférieure à 65.");
                            return ResponseEntity.ok(result);
                        }
                    }

                    if (j == 3) {
                        String fullType = cell.toString();
                        rowData.put(key, getTypeCode(fullType));
                    }else {
                        rowData.put(key, cell != null ? cell.toString() : "");
                    }

                }
                rowData.put("statut", String.valueOf(StatutTP.SAISIE));
                boolean exists = contenuTPService.checkTempsPresenceExists(
                    b3Value.intValue(),
                    c3Value.intValue(),
                    rowData.get("nom"),
                    rowData.get("prenom"),
                    rowData.get("typePiece"),
                    rowData.get("numeroPiece")
                );
                int ligne = i+1;
                if (exists) {
                    rapport.put("ligne " + ligne,
                        "Temps de présence de "+ rowData.get("prenom") +" " + rowData.get("nom")+" (N° pièce : "+rowData.get("numeroPiece")+") a déjà été enregistré pour le trimestre "+c3Value.intValue()+" de l'année "+b3Value.intValue()+".");
                } else {
                    salarieTPs.add(rowData);
                    rapport.put("ligne " + ligne, "Temps de présence ajouté pour : " + rowData.get("prenom") + " " + rowData.get("nom"));
                }
            }

            contenuTP.put("numeroUnique", checkFileModel.getNumeroUnique());
            contenuTP.put("annee", b3Value.intValue());
            contenuTP.put("trimestre", c3Value.intValue());
            contenuTP.put("employeur", checkFileModel.getEmployeur());
            contenuTP.put("statut", StatutTP.SAISIE);
            if (d3 != null) {
                contenuTP.put("ancienNumeroCss", d3Value.intValue());
            }
            if (e3 != null) {
                contenuTP.put("ancienNumeroIpres", e3Value.intValue());
            }
            contenuTP.put("salarieTPs", salarieTPs);

            fileTP.put("dateSoumission", LocalDate.now());
            fileTP.put("statut", StatutTP.VALIDE);
            fileTP.put("fileEncoded", base64);
            fileTP.put("contenuTP", contenuTP);

            tempsDePresence.put("userId", checkFileModel.getUserId());
            tempsDePresence.put("numeroUnique", checkFileModel.getNumeroUnique());
            if (d3 != null) {
                tempsDePresence.put("numeroCss", d3Value.intValue());
            }
            if (e3 != null) {
                tempsDePresence.put("numeroIpres", e3Value.intValue());
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String dateTime = LocalDateTime.now().format(formatter);

            String safeDocDir = (docDir != null && !docDir.trim().isEmpty()) ? docDir : "temps_de_présences_files";
            String fileName = "TP_" + checkFileModel.getNumeroUnique() + "_" + dateTime + ".xlsx";
            Path filePath = Paths.get(safeDocDir, fileName);

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, decodedBytes);

            String url = (docuri != null && !docuri.trim().isEmpty()) ? docuri+"/"+fileName : "temps_de_présences_files"+"/"+fileName;
            fileTP.put("docLink", url);
            tempsDePresence.put("fileTP", fileTP);

            TempsDePresence tp = TempsDePresence.mapToTempsDePresence(tempsDePresence, fileTP, contenuTP, salarieTPs);

            try {
                if(!salarieTPs.isEmpty()){
                    saveTempsDePresence(tp);
                }
            } catch (Exception e) {
                result.put("code", "500");
                result.put("erreur", "Erreur lors de la création du temps de présence: " + e.getMessage());
                return ResponseEntity.ok(result);
            }


            result.put("code", "200");
            result.put("rapport ", rapport);
            if(!salarieTPs.isEmpty()){
                result.put("data", tp);
            }

            workbook.close();

        } catch (IOException e) {
            result.put("code", "400");
            result.put("erreur", "Erreur lors de la lecture du fichier Excel: " + e.getMessage());
            return ResponseEntity.ok(result);
        }
        return null;
    }

    private String getKey(int index) {
        switch (index) {
            case 0:
                return "numeroAssureSocial";
            case 1:
                return "nom";
            case 2:
                return "prenom";
            case 3:
                return "typePiece";
            case 4:
                return "numeroPiece";
            case 5:
                return "age";
            case 6:
                return "nombreEnfantEligibre";
            case 7:
                return "tempsDePresenceHeureMois1";
            case 8:
                return "tempsDePresenceHeureMois2";
            case 9:
                return "tempsDePresenceHeureMois3";
            case 10:
                return "observation";
            default:
                throw new IllegalArgumentException("Index doit être entre 0 et 9.");
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getTypeCode(String fullType) {
        switch(fullType.toLowerCase().trim()) {
            case "carte d'identité nationale (nin)":
                return "NIN";
            case "carte consulaire":
                return "CONC";
            case "cedeao":
                return "CDAO";
            case "passport":
                return "PASS";
            case "identifiant fictif":
                return "IDF";
            default:
                throw new IllegalArgumentException("Le type de fichier renseigné est méconnu.");
        }
    }

}
