package com.secusociale.portail.service;

import com.secusociale.portail.domain.DeclaredEmployer;
import com.secusociale.portail.domain.enumeration.SearchType;
import com.secusociale.portail.domain.enumeration.StatutJuridique;
import com.secusociale.portail.repository.DeclaredEmployerRepository;
import com.secusociale.portail.service.dto.DeclaredEmployerDTO;
import com.secusociale.portail.service.mapper.DeclaredEmployerMapper;
import com.secusociale.portail.validators.ConstraintViolationException;
import com.secusociale.portail.validators.DeclaredEmployerValidator;
import com.secusociale.portail.web.rest.SalarieResource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeclaredEmployerService {

    private final Logger log = LoggerFactory.getLogger(SalarieResource.class);
    @Autowired
    private DeclaredEmployerRepository declaredEmployerRepository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Transactional
    public void importDeclaredEmployerFromCSV(MultipartFile file) throws IOException {
        List<DeclaredEmployer> employers = new ArrayList<>();
        BufferedReader reader = null;
        CSVReader csvReader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            csvReader = new CSVReader(reader);

            String[] headers = csvReader.readNext();
            if (headers == null) {
                throw new IOException("Le fichier CSV est vide");
            }

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                try {
                    DeclaredEmployer employer = mapLineToEmployer(line);
                    employers.add(employer);
                } catch (Exception e) {
                    log.error("Erreur à la ligne: " + String.join(",", line), e);
                }
            }

            declaredEmployerRepository.saveAll(employers);

        } catch (CsvValidationException e) {
            log.error("Erreur lors de la lecture du CSV !", e);
            throw new IOException("Erreur lors de la lecture du CSV !", e);
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    log.error("Erreur de la fermeture CSV !", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Erreur de la fermeture CSV !", e);
                }
            }
        }
    }

    private DeclaredEmployer mapLineToEmployer(String[] line) {
        DeclaredEmployer employer = new DeclaredEmployer();

        int i = 0;
        try {
            employer.setNumeroUniqueParent(getValueOrNull(line[i++]));
            employer.setNumeroUnique(getValueOrNull(line[i++]));
            String raisonSociale = getValueOrNull(line[i++]);
            if (raisonSociale != null && raisonSociale.length() > 255) {
                raisonSociale = raisonSociale.substring(0, 255);
            }
            employer.setRaisonSociale(raisonSociale);
            String ninea = getValueOrNull(line[i++]);
            if (ninea == null || ninea.trim().isEmpty()) {
                ninea = "N/A";
            }
            employer.setNinea(ninea);
            employer.setAncienNumIpres(getValueOrNull(line[i++]));
            employer.setAncienNumCss(getValueOrNull(line[i++]));
            i++;
            employer.setTypeEtablissement(getValueOrNull(line[i++]));
            employer.setActivitePrincipale(getValueOrNull(line[i++]));

            String tauxAt = getValueOrNull(line[i++]);
            if (tauxAt != null && !tauxAt.trim().isEmpty()) {
                try {
                    employer.setTauxAt(Double.parseDouble(tauxAt.replace(",", ".")));
                } catch (NumberFormatException e) {
                    log.warn("Valeur du Taux AT invalide : " + tauxAt);
                    employer.setTauxAt(0.0);
                }
            }

            String nombreEmployesRc = getValueOrNull(line[i++]);
            if (nombreEmployesRc != null && !nombreEmployesRc.trim().isEmpty()) {
                try {
                    employer.setNombreEmployesRc(Integer.parseInt(nombreEmployesRc));
                } catch (NumberFormatException e) {
                    log.warn("Valeur du Nombre d'Employés RC invalide : " + nombreEmployesRc);
                    employer.setNombreEmployesRc(0);
                }
            }

            employer.setAgenceCss(getValueOrNull(line[i++]));
            employer.setStatutJuridique(getValueOrNull(line[i++]));
            employer.setCodeSecteurCs(getValueOrNull(line[i++]));
            employer.setCodeZoneGeographiqueCss(getValueOrNull(line[i++]));

            String dateNaissance = getValueOrNull(line[i++]);
            if (dateNaissance != null && !dateNaissance.trim().isEmpty()) {
                try {
                    employer.setDateNaissance(LocalDate.parse(dateNaissance, dateFormatter));
                } catch (Exception e) {
                    log.warn("Date de Naissance invalide : " + dateNaissance);
                }
            }

            employer.setTypeEmployeur(getValueOrNull(line[i++]));

            String dateImmat = getValueOrNull(line[i++]);
            if (dateImmat != null && !dateImmat.trim().isEmpty()) {
                try {
                    employer.setDatePremiereImmatriculation(LocalDate.parse(dateImmat, dateFormatter));
                } catch (Exception e) {
                    log.warn("Date de première immatriculation invalide : " + dateImmat);
                }
            }

            String effectif = getValueOrNull(line[i++]);
            if (effectif != null && !effectif.trim().isEmpty()) {
                try {
                    employer.setEffectif(Long.parseLong(effectif));
                } catch (NumberFormatException e) {
                    log.warn("Valeur du Nombre d'Employés invalide : " + effectif);
                    employer.setEffectif(0L);
                }
            }

            employer.setAgenceIpres(getValueOrNull(line[i++]));
            employer.setCodeSecteurIpres(getValueOrNull(line[i++]));
            employer.setCodeZoneGeographiqueIpres(getValueOrNull(line[i++]));
            employer.setTypeImmatriculation(getValueOrNull(line[i++]));
            employer.setSecteurActivite(getValueOrNull(line[i++]));
            employer.setSigle(getValueOrNull(line[i]));

        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("La ligne ne contient pas assez de colonnes", e);
        }

        return employer;
    }

    private String getValueOrNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

    @Transactional
    public HashMap<String, Object>  importDeclaredEmployer(String base64File) throws IOException  {

        try {
            byte[] fileBytes = Base64.getDecoder().decode(base64File);
            HashMap<String, Object> errors = new HashMap<>();
            Workbook workbook = null;
            try {
                workbook = WorkbookFactory.create(new ByteArrayInputStream(fileBytes));
            } catch (InvalidFormatException e) {
                errors.put("status", "error");
                errors.put("message", "Une contrainte a été violée : " + e.getMessage());
                log.error("Methode : importDeclaredEmployer , Error : {}", errors);
                return errors;
            }
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            String[] expectedHeaders = {
                "NINEA", "RAISON_SOCIALE", "NUMERO_UNIQUE", "ANCIEN_NUM_IPRES",
                "ANCIEN_NUM_CSS", "ADRESSE", "REGION", "TELEPHONE", "EFFECTIF",
                "STATUT_JURIDIQUE", "SECTEUR_ACTIVITE", "SIGLE", "RCCM",
                "AGENCE_IPRES", "AGENCE_CSS"
            };

            if (!validateHeaders(headerRow, expectedHeaders)) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("error", "Le fichier n'est pas conforme au fichier attendu !");
                log.error("Methode : importDeclaredEmployer , Error : {}", result);
                return result;
            }

            List<DeclaredEmployer> employers = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (isRowEmpty(currentRow)) {
                    break;
                }
                try {
                    DeclaredEmployer employer = mapRowToEmployer(sheet.getRow(i));
                    employers.add(employer);

                    Row nextRow = sheet.getRow(i + 1);
                    if (nextRow == null || isRowEmpty(nextRow)) {
                        break;
                    }
                } catch (Exception e) {
                    log.error("Error processing row {}: {}", i, e.getMessage());
                    errors.put("Ligne ", i+1);
                    errors.put("exception", e.getMessage());
                    log.error("Methode : importDeclaredEmployer , Error : {}", errors);
                    return errors;
                }
            }

            try {
                DeclaredEmployerValidator.DeclaredEmployerValidators(employers,declaredEmployerRepository);
                List<DeclaredEmployer> uniqueEmployers = employers.stream()
                    .filter(employer -> !declaredEmployerRepository.existsByNinea(employer.getNinea()) &&
                        !declaredEmployerRepository.existsByNumeroUnique(employer.getNumeroUnique()))
                    .collect(Collectors.toList());
                if(!uniqueEmployers.isEmpty()){
                    declaredEmployerRepository.saveAll(uniqueEmployers);
                }

            } catch (ConstraintViolationException e) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("error", e.getMessage());
                log.error("Methode : importDeclaredEmployer , Error : {}", result);
                return result;
            }catch (DataIntegrityViolationException e) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("error", e.getMessage());
                log.error("Methode : importDeclaredEmployer , Error : {}", result);
                return result;
            }

            return null;
        } catch (IllegalArgumentException e) {
            log.error("Invalid Base64 encoding", e);
            HashMap<String, Object> result = new HashMap<>();
            result.put("error", "Invalid Base64 encoding : "+ e);
            log.error("Methode : importDeclaredEmployer , Error : {}", result);
            return result;
        }
    }


//    public Page<DeclaredEmployerDTO> getEmployersByNumeroUnique(String numeroUnique, Pageable pageable) {
//        return declaredEmployerRepository.findByNumeroUniqueParent(numeroUnique, pageable);
//    }

    public Page<DeclaredEmployerDTO> getEmployersByNumeroUnique(String numeroUnique, Pageable pageable) {
        Page<DeclaredEmployer> employersPage = declaredEmployerRepository.findByNumeroUniqueParent(numeroUnique, pageable);
        List<DeclaredEmployerDTO> employerDTOs = employersPage.getContent()
            .stream()
            .map(DeclaredEmployerMapper.INSTANCE::toDto)
            .collect(Collectors.toList());

        return new PageImpl<>(employerDTOs, pageable, employersPage.getTotalElements());
    }


    private boolean validateHeaders(Row headerRow, String[] expectedHeaders) {
        if (headerRow == null || headerRow.getLastCellNum() < expectedHeaders.length) {
            return false;
        }

        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null || !cell.getStringCellValue().trim().equals(expectedHeaders[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }

    private DeclaredEmployer mapRowToEmployer(Row row) {
        DeclaredEmployer employer = new DeclaredEmployer();

        employer.setNinea(getCellValueAsString(row.getCell(0)));
        employer.setRaisonSociale(getCellValueAsString(row.getCell(1)));
        employer.setNumeroUnique(getCellValueAsString(row.getCell(2)));
        employer.setAncienNumIpres(getCellValueAsString(row.getCell(3)));
        employer.setAncienNumCss(getCellValueAsString(row.getCell(4)));
        employer.setAdresse(getCellValueAsString(row.getCell(5)));
        employer.setRegion(getCellValueAsString(row.getCell(6)));
        employer.setTelephone(getCellValueAsString(row.getCell(7)));
        employer.setEffectif(getCellValueAsLong(row.getCell(8)));
        employer.setStatutJuridique(String.valueOf(row.getCell(9)));
        employer.setSecteurActivite(getCellValueAsString(row.getCell(10)));
        employer.setSigle(getCellValueAsString(row.getCell(11)));
        employer.setRccm(getCellValueAsString(row.getCell(12)));
        employer.setAgenceIpres(getCellValueAsString(row.getCell(13)));
        employer.setAgenceCss(getCellValueAsString(row.getCell(14)));

        return employer;
    }

    private Long getCellValueAsLong(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return (long) cell.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
                return Long.parseLong(cell.getStringCellValue().trim());
            default:
                return null;
        }
    }

    private String getCellValueAsString(Cell cell) {
        return cell != null ? cell.getStringCellValue().trim() : null;
    }

    private StatutJuridique getCellValueAsStatutJuridique(Cell cell) {
        if (cell == null) return null;
        try {
            return StatutJuridique.valueOf(cell.getStringCellValue().trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean doesEmployerExist(String keyWanted, SearchType searchType) {
        return declaredEmployerRepository.existsBySearchCriteria(searchType.name(),keyWanted);
    }

    public Optional<DeclaredEmployer> findEmployerByCriteria(String keyWanted, SearchType searchType) {
        return declaredEmployerRepository.findBySearchCriteria(searchType.name(), keyWanted);
    }

    public List<DeclaredEmployer> findByRaisonSociale(String keyWanted) {
        return declaredEmployerRepository.findByRaisonSocialeLike(keyWanted);
    }

    public DeclaredEmployer save(DeclaredEmployer newDeclaredEmployer) {
        return declaredEmployerRepository.save(newDeclaredEmployer);
    }
}
