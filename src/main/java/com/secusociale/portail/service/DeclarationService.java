package com.secusociale.portail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.secusociale.portail.config.CodeErreurSystem;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.domain.enumeration.*;
import com.secusociale.portail.model.DeclarationModel;
import com.secusociale.portail.repository.*;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.declaration.DnsService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.FactureIndividuelleDTO;
import com.secusociale.portail.service.dto.SuiviJobDTO;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.dto.custom.DeclarationModelDTO;
import com.secusociale.portail.service.dto.custom.LastDeclarationDTO;
import com.secusociale.portail.service.soap.succursales.CMGETLISTESECURSALES;
import com.secusociale.portail.service.succursales.SuccursaleService;
import com.secusociale.portail.service.utils.UtilsService;
import com.secusociale.portail.web.rest.DeclarationResource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.zalando.problem.Problem;

import javax.xml.ws.Holder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.*;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;

@Service
public class DeclarationService {
    private final Logger log = LoggerFactory.getLogger(DeclarationService.class);

    @Autowired
    private DeclarationRepository declarationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SuiviJobService suiviJobService;

    @Autowired
    private DnsService dnsService;

    @Autowired
    private ContenuTPRepository contenuTPRepository;

    @Autowired
    private ContenuTPService contenuTPService;

    @Autowired
    private DeclarationResource declarationResource;

    @Autowired
    private FactureDNSRepository factureDNSRepository;

    @Autowired
    private SuccursaleService succursaleService;

    @Autowired
    private NouvelleImmatriculationService nouvelleImmatriculationService ;
    @Autowired
    private OldImmatriculationService oldImmatriculationService ;
    @Autowired
    private UtilsService utilsService ;
    @Autowired
    private TauxATRepository tauxATRepository ;

    @Autowired
    private DeclaredEmployerRepository declaredEmployerRepository;

    @Autowired
    private DnsExceptionHandlerService dnsExceptionHandlerService;

    private final ObjectMapper objectMapper;

    @Autowired
    private JournalJobService journalJobService;

    @Autowired
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;

    private final JournalJobRepository journalJobRepository;

    @Value("${application.ftpaddress: 192.168.125.23}")
    private String host;
    @Value("${application.ftpport: 22}")
    private int port;
    @Value("${application.ftplogin: wango}")
    private String login;
    @Value("${application.ftppass: wango}")
    private String password;
    @Value("${application.ftppath: declaration}")
    private String path;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private TauxATRepository atRepository;

    @Autowired
    private MailService mailService;

    private final Map<TypeJobName, Boolean> isRunningMap = new ConcurrentHashMap<>();
//    @Autowired
//    @Qualifier("oracleDataSource")
//    private DataSource oracleDataSource;

    private static final Float TAUX_RG = 14F;
    private static final Float TAUX_RC = 6F;
    private static final Float TAUX_PF = 7F;

    public DeclarationService(ObjectMapper objectMapper, ImmatriculationRecupereeRepository immatriculationRecupereeRepository, JournalJobRepository journalJobRepository) {
        this.objectMapper = objectMapper;
        this.immatriculationRecupereeRepository = immatriculationRecupereeRepository;
        this.journalJobRepository = journalJobRepository;
    }

    public boolean putToFTPServer(InputStream stream, String name) {
        JSch jSch = new JSch();
        Session jschSession = null;
        try {
            InputStream resource = new ClassPathResource(
                "config/known_host").getInputStream();
            jSch.setKnownHosts(resource);
            jschSession = jSch.getSession(login, host, port);
            jschSession.setPassword(password);
            jschSession.connect();
            Channel sftp = jschSession.openChannel("sftp");
            sftp.connect();
            ChannelSftp channelSftp = (ChannelSftp) sftp;
            channelSftp.put(stream, path + "/" + name);
            stream.close();
            log.info(String.format("adress: %s, port: %s, path: %s", host, port, path));
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            try {
                if (jschSession != null && jschSession.isConnected()) {
                    jschSession.disconnect();
                }
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public boolean putToFTPServer(String fileUrl, String name) {
        JSch jSch = new JSch();
        Session jschSession = null;
        try {
            InputStream stream = new URL(fileUrl).openStream();
            InputStream resource = new ClassPathResource(
                "config/known_host").getInputStream();
            jSch.setKnownHosts(resource);
            jschSession = jSch.getSession(login, host, port);
            jschSession.setPassword(password);
            jschSession.connect();
            Channel sftp = jschSession.openChannel("sftp");
            sftp.connect();
            ChannelSftp channelSftp = (ChannelSftp) sftp;
            channelSftp.put(stream, path + "/" + name);
            stream.close();
            log.info(String.format("adress: %s, port: %s, path: %s", host, port, path));
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            try {
                if (jschSession != null && jschSession.isConnected()) {
                    jschSession.disconnect();
                }
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        }
    }

   // @Scheduled(cron = "#{@sendDeclarationJobExpression}")
    public void validationDesDeclarationsTrimestriellesSoumises() {
        List<Declaration> list = declarationRepository.findAllByTypeDeclarationAndStatus("TRIMESTRIEL", "SOUMISE");
        for (Declaration declaration : list) {
            try {
                DeclarationModel model = new ObjectMapper().readValue(declaration.getRequeteBrute(), DeclarationModel.class);
                Holder<DeclarationModel> resultat = dnsService.createDns(model);
                if (resultat != null) {
                    String brute = (new ObjectMapper()).writeValueAsString(resultat);
                    declaration.reponseBrute(brute).status("TRAITER").processFlowId(resultat.value.getFormId());
                    declarationRepository.save(declaration);
                }
            } catch (Exception e) {
                declaration.setReponseBrute(e.getMessage());
                declaration.setStatus("INVALIDE");
                long nbErrors = declaration.getNombreErreurs() + 1;
                declaration.setNombreErreurs(nbErrors);
                if (declarationRepository.findAllByNumeroUniqueAndStatus(declaration.getNumeroUnique(), "INVALIDE").isEmpty()) {
                    declarationRepository.save(declaration);
                } else {
                    List<Declaration> founds = declarationRepository.findAllByNumeroUniqueAndStatus(declaration.getNumeroUnique(), "INVALIDE");
                    int foundSize = founds.size();
                    declaration.setId(founds.get(foundSize - 1).getId());
                    declarationRepository.save(declaration);
                }
                e.printStackTrace();
            }
        }
    }

//    public void validationDesDeclarationsTrimestriellesSoumisesManuelle() {
//        String jobName = "sendDeclarations";
//        Optional<SuiviJobDTO> jobOptional = suiviJobService.findJob(jobName);
//        if (!jobOptional.isPresent()) {
//            throw Problem.builder().withTitle("Batch introuvable").withDetail("Aucun batch trouvé avec ce nom").build();
//        }
//        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
//        if (!loggedusername.isPresent()) {
//            loggedusername = Optional.of("plateforme");
//        }
//        String username = loggedusername.get();
//        String userEmail = userRepository.findByLogin(username).getEmail();
//        SuiviJobDTO job = jobOptional.get();
//        job.setStatut("STARTED");
//        job.setDemarreLe(Instant.now());
//        job.setDemarrePar(userEmail);
//        suiviJobService.save(job);
//
//        List<Declaration> list = declarationRepository.findAllByTypeDeclarationAndStatus("TRIMESTRIEL", "SOUMISE");
//        try {
//            for (Declaration declaration : list) {
//                try {
//                    DeclarationModel model = new ObjectMapper().readValue(declaration.getRequeteBrute(), DeclarationModel.class);
//                    Holder<DeclarationModel> resultat = dnsService.createDns(model);
//                    if (resultat != null) {
//                        String brute = (new ObjectMapper()).writeValueAsString(resultat);
//                        declaration.reponseBrute(brute).status("TRAITER").processFlowId(resultat.value.getFormId());
//                        declarationRepository.save(declaration);
//                    }
//                } catch (Exception e) {
//                    declaration.setReponseBrute(e.getMessage());
//                    declaration.setStatus("INVALIDE");
//                    long nbErrors = declaration.getNombreErreurs() + 1;
//                    declaration.setNombreErreurs(nbErrors);
//                    if (declarationRepository.findAllByNumeroUniqueAndStatus(declaration.getNumeroUnique(), "INVALIDE").isEmpty()) {
//                        declarationRepository.save(declaration);
//                    } else {
//                        List<Declaration> founds = declarationRepository.findAllByNumeroUniqueAndStatus(declaration.getNumeroUnique(), "INVALIDE");
//                        int foundSize = founds.size();
//                        declaration.setId(founds.get(foundSize - 1).getId());
//                        declarationRepository.save(declaration);
//                    }
//                    e.printStackTrace();
//                }
//            }
//            job.setTermineLe(Instant.now());
//            job.setStatut("NOT-STARTED");
//        } catch (Exception e) {
//            job.setTermineLe(Instant.now());
//            job.setStatut("NOT-STARTED");
//            job.setErreurs(e.toString());
//        }
//        suiviJobService.save(job);
//    }

    @Transactional
    public HashMap<String, Object> sendNonSoumiseDeclarationFiles() {
        List<Declaration> list = declarationRepository.findAllByTypeDeclarationAndStatus("MENSUEL", "NON_SOUMISE");
        Integer total = list.size();
        Integer soumises = 0;
        Integer nonSoumises = 0;
        for (Declaration declaration : list) {
            boolean isSendToFTP = putToFTPServer(declaration.getFileURL(), declaration.getFileName());
            if (isSendToFTP) {
                declaration.setStatus("SOUMISE");
                declarationRepository.save(declaration);
                soumises++;
            } else {
                nonSoumises++;
            }
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("soumises", soumises);
        result.put("nonSoumises", nonSoumises);
        return result;
    }

    @Transactional
    public HashMap<String, Object> sendNonSoumiseDeclarationFilesManuel() {
        String jobName = "reSendDeclarationFiles";
        Optional<SuiviJobDTO> jobOptional = suiviJobService.findJob(jobName);
        if (!jobOptional.isPresent()) {
            throw Problem.builder().withTitle("Batch introuvable").withDetail("Aucun batch trouvé avec ce nom").build();
        }
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        if (!loggedusername.isPresent()) {
            loggedusername = Optional.of("plateforme");
        }
        String username = loggedusername.get();
        String userEmail = userRepository.findByLogin(username).getEmail();
        SuiviJobDTO job = jobOptional.get();
        job.setStatut("STARTED");
        job.setDemarreLe(Instant.now());
        job.setDemarrePar(userEmail);
        suiviJobService.save(job);
        List<Declaration> list = declarationRepository.findAllByTypeDeclarationAndStatus("MENSUEL", "NON_SOUMISE");
        Integer total = list.size();
        Integer soumises = 0;
        Integer nonSoumises = 0;
        HashMap<String, Object> result = new HashMap<>();
        try {
            for (Declaration declaration : list) {
                boolean isSendToFTP = putToFTPServer(declaration.getFileURL(), declaration.getFileName());
                if (isSendToFTP) {
                    declaration.setStatus("SOUMISE");
                    declarationRepository.save(declaration);
                    soumises++;
                } else {
                    nonSoumises++;
                }
            }
            result.put("total", total);
            result.put("soumises", soumises);
            result.put("nonSoumises", nonSoumises);
            job.setTermineLe(Instant.now());
            job.setStatut("NOT-STARTED");
        } catch (Exception exception) {
            job.setTermineLe(Instant.now());
            job.setStatut("NOT-STARTED");
            job.setErreurs(exception.toString());
            result.put("erreurs", exception.toString());
        }

        suiviJobService.save(job);

        return result;
    }

    @Scheduled(cron = "#{@sendDeclarationJobExpression}")
    public void sendNonSoumiseDeclarationFilesJob() {
        sendNonSoumiseDeclarationFiles();
    }

    @Transactional
    public Declaration reSendMe(Declaration declaration) {
        if ("NON_SOUMISE".equalsIgnoreCase(declaration.getStatus())) {
            boolean isSendToFTP = putToFTPServer(declaration.getFileURL(), declaration.getFileName());
            if (isSendToFTP) {
                declaration.setStatus("SOUMISE");
                declarationRepository.save(declaration);
            }
        }
        return declaration;
    }

    public ApiResponse<String> validateSalariesFile(DeclarationModelDTO preDnsInput) {
        if (preDnsInput == null || preDnsInput.getSalariesFile() == null || preDnsInput.getSalariesFile().trim().isEmpty()) {
            return null;
        }
        try {
            byte[] excelBytes = Base64.getDecoder().decode(preDnsInput.getSalariesFile().split(",")[1]);
            ApiResponse<String> response;

            // Créer le workbook pour obtenir le nombre de feuilles
            try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
                int numberOfSheets = workbook.getNumberOfSheets();

                for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {

                    // Vérifier si la feuille est cachée
                    if (workbook.isSheetHidden(sheetIndex)) {
                        continue; // Ignorer les feuilles cachées
                    }

                    // Valider la structure, année, mois, etc. pour chaque feuille
                    response = validateStructure(excelBytes);
                    if (response != null) {
                        return response;
                    }

                    response = validateAnnee(excelBytes);
                    if (response != null) {
                        return response;
                    }

                    response = validateMois(excelBytes);
                    if (response != null) {
                        return response;
                    }

                    response = validateAnneeEtMois(excelBytes, preDnsInput.getDateDebutPeriodeCotisation(), sheetIndex);
                    if (response != null) {
                        return response;
                    }

                    response = validateExistingDeclaration(preDnsInput);
                    if (response != null) {
                        return response;
                    }

                    // Appeler validateSalariesList avec l'index de la feuille
                    response = validateSalariesList(excelBytes, sheetIndex);
                    if (response != null) {
                        return response;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            return ApiResponse.error500("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public ApiResponse<String> validateExistingDeclaration(DeclarationModelDTO preDnsInput) {
        LastDeclarationDTO declarationDTO = new LastDeclarationDTO();
        declarationDTO.setNumeroUnique(preDnsInput.getNumeroUnique());
        declarationDTO.setDebut(preDnsInput.getDateDebutPeriodeCotisation().toInstant());
        declarationDTO.setFin(preDnsInput.getDateFinPeriodeCotisation().toInstant());
        declarationDTO.setType(preDnsInput.getTypeDeclaration());

        ResponseEntity<HashMap<String, Object>> response = declarationResource.checkPeriode(declarationDTO);

        boolean existe = (boolean) response.getBody().get("exist");

        if (existe) {
            return ApiResponse.error400("Vous avez déjà une déclaration sur la période choisie.");
        }
        return null;
    }

    private ApiResponse<String> validateAnnee(byte[] excelBytes) {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
            Sheet sheet = workbook.getSheetAt(0);

            Row row3 = sheet.getRow(2);
            if (row3 == null || row3.getCell(1) == null) {
                return ApiResponse.error400("La cellule B3 est vide.");
            }

            // Ensure the cell contains a numeric value
            Cell yearCell = row3.getCell(1);
            int year;

            try {
                if (yearCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    year = (int) yearCell.getNumericCellValue();
                } else if (yearCell.getCellType() == Cell.CELL_TYPE_STRING) {
                    year = Integer.parseInt(yearCell.getStringCellValue().trim());
                } else {
                    return ApiResponse.error400("La cellule B3 doit contenir une année valide.");
                }
            } catch (NumberFormatException e) {
                return ApiResponse.error400("La valeur de la cellule B3 n'est pas une année valide.");
            }

            if (year < 1970 || year > 2050) {
                return ApiResponse.error400(
                    "L'année doit être comprise entre 1970 et 2050. " +
                        "Valeur reçue : " + year
                );
            }

            return null;
        } catch (Exception e) {
            return ApiResponse.error500("Erreur lors de la validation de l'année : " + e.getMessage());
        }
    }

    private ApiResponse<String> validateMois(byte[] excelBytes) {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
            Sheet sheet = workbook.getSheetAt(0);

            Row row3 = sheet.getRow(2);
            if (row3 == null || row3.getCell(2) == null) {
                return ApiResponse.error400("La cellule C3 est vide.");
            }

            Cell monthCell = row3.getCell(2);
            String monthValue;

            if (monthCell.getCellType() == Cell.CELL_TYPE_STRING) {
                monthValue = monthCell.getStringCellValue().trim().toUpperCase();
            } else {
                return ApiResponse.error400("La cellule C3 doit contenir un nom de mois valide.");
            }

            Set<String> validMonths = new HashSet<>(Arrays.asList(
                "JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN",
                "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"
            ));

            if (!validMonths.contains(monthValue)) {
                return ApiResponse.error400(
                    "Le mois doit être l'un des mois suivants : " +
                        String.join(", ", validMonths) +
                        ". Valeur reçue : " + monthValue
                );
            }

            return null;
        } catch (Exception e) {
            return ApiResponse.error500("Erreur lors de la validation du mois : " + e.getMessage());
        }
    }

    private ApiResponse<String> validateNumeroUnique(byte[] excelBytes, String expectedNumeroEmployeur) {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
            Sheet sheet = workbook.getSheetAt(0);

            Row row2 = sheet.getRow(2);
            String NumeroEmployeurInFile =(row2 != null && row2.getCell(0) != null)
                ? row2.getCell(0).getStringCellValue().trim()
                : "";
            if(NumeroEmployeurInFile.length() != 10 || expectedNumeroEmployeur.length() != 10 ){
                return ApiResponse.error400(
                    "La taille du numéro unique doit être égale à 10 caractères."
                );
            }

            if (row2 == null ||
                row2.getCell(0) == null ||
                !row2.getCell(0).getStringCellValue().trim().equals(expectedNumeroEmployeur)) {

                String actualValue = (row2 != null && row2.getCell(0) != null)
                    ? row2.getCell(0).getStringCellValue().trim()
                    : "Cellule vide";

                return ApiResponse.error400(
                    "Le numéro unique (A3) ne correspond pas au numéro employeur. " +
                        "Attendu : '" + expectedNumeroEmployeur + "', " +
                        "Reçu : '" + actualValue + "'"
                );
            }

            return null;
        } catch (Exception e) {
            return ApiResponse.error500("Erreur lors de la validation du numéro unique : " + e.getMessage());
        }
    }

    private ApiResponse<String> validateSalariesList(byte[] excelBytes, int sheetIndex) {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
          //  Sheet sheet = workbook.getSheetAt(0);
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            Set<String> validTypePieceIdentite = new HashSet<>(Arrays.asList(
                "CARTE D'IDENTITE NATIONALE", "CARTE D'IDENTITE CEDEAO",
                "PASSPORT", "CARTE CONSULAIRE", "IDENTIFIANT FICTIF"
            ));

            Set<String> validTypeContrat = new HashSet<>(Arrays.asList(
                "CDI", "CDD", "JOURNALIER", "CONTRAT DE STAGE", "CONTRAT SPECIAL",
                "APPRENTISSAGE", "CONTRAT SAISONNIER", "CONTRAT SAISONNIER AGRICOLE",
                "EXPATRIER", "GERANT MAJORITAIRE SARL", "ASSUJETI CSS", "ASSUJETI IPRES"
            ));

            Set<String> validOuiNon = new HashSet<>(Arrays.asList("OUI", "NON"));

            for (int rowNum = 6; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null || isEntireRowEmpty(row)) {
                    break;
                }

                // A : doit être vide
                if (isNonEmptyCell(row, 0)) {
                    return ApiResponse.error400(
                        "La cellule A" + (rowNum + 1) + " ne doit pas contenir une valeur."
                    );
                }

                // B : doit pas être vide et doit être alphabétique
                if (!isValidAlphabeticCell(row, 1)) {
                    return ApiResponse.error400(
                        "La cellule B" + (rowNum + 1) + " dans la feuille " + (sheetIndex + 1) + " doit une valeur alphabétique."
                    );
                }

                // C : doit pas être vide et doit être alphabétique
                if (!isValidAlphabeticCell(row, 2)) {
                    return ApiResponse.error400(
                        "La cellule C" + (rowNum + 1) +  " dans la feuille " + (sheetIndex + 1) + " doit une valeur alphabétique."
                    );
                }

                // D : doit pas être vide, alphabétique, et dans la liste prédéfinie
                String typePieceIdentite = getStringCellValue(row, 3);
                System.out.println("test test"+typePieceIdentite);
                if (typePieceIdentite == null || !validTypePieceIdentite.contains(typePieceIdentite)) {
                    return ApiResponse.error400(
                        "La cellule D" + (rowNum + 1) + " dans la feuille " + (sheetIndex + 1) + " doit contenir un type de pièce d'identité valide. valeur possible : "+
                            String.join(", ", validTypePieceIdentite)
                    );
                }

                // E : doit pas être vide et doit être numérique avec des contrôles spécifiques selon le type de pièce d'identité
                String numeroPieceIdentite = getStringCellValue(row, 4);

                if (typePieceIdentite.equals("CARTE D'IDENTITE CEDEAO")) {
                    if (!isValidNumericStringWithExactLength(numeroPieceIdentite, 17)) {
                        return ApiResponse.error400(
                            "Le numéro de la carte d'identification CEDEAO de la ligne " + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit composer de 16 ou 17 caractères."
                        );
                    }
                } else if (typePieceIdentite.equals("CARTE D'IDENTITE NATIONALE")) {
                    if (!isValidAlphanumericString(numeroPieceIdentite) ||
                        (numeroPieceIdentite.length() < 13 || numeroPieceIdentite.length() > 14)) {
                        return ApiResponse.error400(
                            "Le numéro de la carte d'identification NATIONALE de la ligne " + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) +
                                " doit être alphanumérique et composer de 13 ou 14 caractères."
                        );
                    }
                } else if (typePieceIdentite.equals("PASSPORT")) {
                    if (!isValidAlphanumericString(numeroPieceIdentite)) {
                        return ApiResponse.error400(
                            "Le numéro de passport de la ligne " + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit contenir un numéro alphanumérique ."
                        );
                    }
                } else if (typePieceIdentite.equals("CARTE CONSULAIRE")) {
                    if (!isValidAlphanumericString(numeroPieceIdentite)) {
                        return ApiResponse.error400(
                            "Le numéro de la carte consulaire de la ligne " + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit contenir un numéro alphanumérique ."
                        );
                    }
                }
                else if (typePieceIdentite.equals("IDENTIFIANT FICTIF")) {
                    if (!isValidAlphanumericString(numeroPieceIdentite)) {
                        return ApiResponse.error400(
                            "Le numéro identifiant fictif de la ligne " + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit contenir un numéro alphanumérique ."
                        );
                    }
                } else {
                 //   if (!isValidNumericStringLength(numeroPieceIdentite, 12, 17)) {
                        return ApiResponse.error400(
                            "Le type de la piéce de la ligne " + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit etre carte d'identification nationale ou passport ou carte consulaire ou identifiant fictif."
                        );
                  //  }
                }

                // F : doit pas être vide, alphabétique, et dans la liste prédéfinie
                String typeContrat = getStringCellValue(row, 5);
                if (typeContrat == null || !validTypeContrat.contains(typeContrat)) {
                    return ApiResponse.error400(
                        "La cellule F" + (rowNum + 1) + " dans la feuille " + (sheetIndex + 1) + " doit contenir un type de contrat valide. valeur possible : "+
                            String.join(", ", validTypeContrat)
                    );
                }

                // G : doit pas être vide, numétrique
                if (!isValidNumericCell(row, 6)) {
                    return ApiResponse.error400(
                        "La cellule G" + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit être numétrique ."
                    );
                }

                // H : doit pas être vide et doit être numérique
                if (!isValidNumericCell(row, 7)) {
                    return ApiResponse.error400(
                        "La cellule H" + (rowNum + 1) + "dans la feuille " + (sheetIndex + 1) + " doit contenir une valeur numérique."
                    );
                }

                // I : doit pas être vide et doit être dans OUI/NON
                String declarationCss = getStringCellValue(row, 8);
                if (declarationCss == null || !validOuiNon.contains(declarationCss)) {
                    return ApiResponse.error400(
                        "La cellule I" + (rowNum + 1) +  "dans la feuille " + (sheetIndex + 1) + " doit contenir OUI ou NON."
                    );
                }
            }

            return null;
        } catch (Exception e) {
            return ApiResponse.error500("Erreur lors de la validation de la liste des salariés : " + e.getMessage());
        }
    }

    private boolean isValidAlphanumericString(String value) {
        return value != null && value.matches("^[a-zA-Z0-9]+$");
    }

    private boolean isNonEmptyCell(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        return cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK;
    }

    private boolean isValidAlphabeticCell(Row row, int columnIndex) {
        String value = getStringCellValue(row, columnIndex);

        if (value != null) {
            // Remplacer tous les types d'espaces (y compris l'espace insécable) par un seul espace
            value = value.replaceAll("[\\s\\u00A0]+", " ").trim();

            boolean isValid = value.matches("^[a-zA-ZÀ-ÿ\\s-]+$");
            return isValid;
        }
        return false;
    }

    private String getStringCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            default:
                return null;
        }
    }

    private boolean isValidNumericStringLength(String value, int minLength, int maxLength) {
        if (value == null) return false;
        return value.matches("^\\d{" + minLength + "," + maxLength + "}$");
    }

    private boolean isValidNumericCell(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null) return false;

        return cell.getCellType() == Cell.CELL_TYPE_NUMERIC ||
            (cell.getCellType() == Cell.CELL_TYPE_STRING &&
                cell.getStringCellValue().trim().matches("^\\d+$"));
    }

    private static ApiResponse<String> validateStructure(byte[] excelBytes) throws IOException, InvalidFormatException {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
            Sheet sheet = workbook.getSheetAt(0);

            // Vérification des en-têtes
            Row row0 = sheet.getRow(0);
            Row row1 = sheet.getRow(1);
            Row row4 = sheet.getRow(4);
            Row row5 = sheet.getRow(5);

            // Vérification de A1
            if (row0 == null ||
                row0.getCell(0) == null ||
                !row0.getCell(0).getStringCellValue().trim().equals("Informations de L'employeur")) {
                return ApiResponse.error400("Attendu en A1 : 'Informations de L'employeur', reçu : '" +
                    (row0 != null && row0.getCell(0) != null ? row0.getCell(0).getStringCellValue().trim() : "Cellule vide") + "'");
            }

            // Vérification de A2
            if (row1 == null ||
                row1.getCell(0) == null ||
                !row1.getCell(0).getStringCellValue().trim().equals("NUMERO UNIQUE EMPLOYEUR")) {
                return ApiResponse.error400("Attendu en A2 : 'NUMERO UNIQUE EMPLOYEUR', reçu : '" +
                    (row1 != null && row1.getCell(0) != null ? row1.getCell(0).getStringCellValue().trim() : "Cellule vide") + "'");
            }

            // Vérification de B2 (Année)
            if (row1 == null ||
                row1.getCell(1) == null ||
                !row1.getCell(1).getStringCellValue().trim().equals("Année")) {
                return ApiResponse.error400("Attendu en B2 : 'Année', reçu : '" +
                    (row1 != null && row1.getCell(1) != null ? row1.getCell(1).getStringCellValue().trim() : "Cellule vide") + "'");
            }

            // Vérification de C2 (Mois)
            if (row1 == null ||
                row1.getCell(2) == null ||
                !row1.getCell(2).getStringCellValue().trim().equals("Mois")) {
                return ApiResponse.error400("Attendu en C2 : 'Mois', reçu : '" +
                    (row1 != null && row1.getCell(2) != null ? row1.getCell(2).getStringCellValue().trim() : "Cellule vide") + "'");
            }

            // Vérification de A5
            if (row4 == null ||
                row4.getCell(0) == null ||
                !row4.getCell(0).getStringCellValue().trim().equals("Informations des salariés")) {
                return ApiResponse.error400("Attendu en A5 : 'Informations des salariés', reçu : '" +
                    (row4 != null && row4.getCell(0) != null ? row4.getCell(0).getStringCellValue().trim() : "Cellule vide") + "'");
            }

            // Vérification des en-têtes des colonnes de salariés (ligne 6)
            String[] expectedHeaders = {
                "Numéro Assuré Social",
                "Nom",
                "Prénom",
                "Type piéce",
                "Numéro piéce",
                "Type de contrat",
                "Salaire brut assujetti",
                "Temps de présence Heures",
                "Cadre (oui/non)"
            };

            if (row5 == null) {
                return ApiResponse.error400("Ligne des en-têtes de salariés (ligne 6) manquante");
            }

            for (int i = 0; i < expectedHeaders.length; i++) {
                if (row5.getCell(i) == null ||
                    !row5.getCell(i).getStringCellValue().trim().equals(expectedHeaders[i])) {
                    return ApiResponse.error400("Attendu en " +
                        (char) ('A' + i) + "6 : '" + expectedHeaders[i] +
                        "', reçu : '" +
                        (row5.getCell(i) != null ? row5.getCell(i).getStringCellValue().trim() : "Cellule vide") + "'");
                }
            }

            return null;
        }
    }

    private boolean isEntireRowEmpty(Row row) {
        if (row == null) return true;

        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null) {
                String cellValue = getStringCellValue(row, cellNum);
                if (cellValue != null && !cellValue.trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private ApiResponse<String> validateAnneeEtMois(byte[] excelBytes, Date dateDebutPeriodeCotisation, int sheetIndex) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {

           // Sheet sheet = workbook.getSheetAt(0);
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // Convertir Date en Calendar pour extraire l'année et le mois
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDebutPeriodeCotisation);

            int anneeInput = cal.get(Calendar.YEAR);
            int moisInput = cal.get(Calendar.MONTH) + 1;

            // Récupérer l'année de la cellule B3
            String anneeExcel = getStringCellValue(sheet.getRow(2), 1);
            int anneeExcelInt;

            try {
                anneeExcelInt = Integer.parseInt(anneeExcel);
            } catch (NumberFormatException e) {
                return ApiResponse.error400("L'année en cellule B3 dans la feuille " + (sheetIndex + 1) + "  doit être un nombre valide.");
            }

            // Vérifier que l'année correspond
            if (anneeInput != anneeExcelInt) {
                return ApiResponse.error400("L'année de la période de cotisation (" + anneeInput +
                    ") dans la feuille " + (sheetIndex + 1) + " ne correspond pas à l'année indiquée en cellule B3 (" + anneeExcelInt + ").");
            }

            // Liste des mois valides
            List<String> moisValides = Arrays.asList(
                "JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN",
                "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"
            );

            // Convertir le numéro de mois en lettres
            String moisInputStr = getMoisEnLettre(moisInput);

            // Récupérer le mois de la cellule C3
            String moisExcel = getStringCellValue(sheet.getRow(2), 2); // C3 (indice 0-based)

            // Vérifier que le mois correspond
            if (!moisInputStr.equals(moisExcel)) {
                return ApiResponse.error400("Le mois de la période de cotisation (" + moisInputStr +
                    ") dans la feuille " + (sheetIndex + 1) + " ne correspond pas au mois indiqué en cellule C3 (" + moisExcel + ").");
            }

        } catch (InvalidFormatException e) {
            ApiResponse.error500("Erreur lors du formatage " + e.getMessage());
        }

        return null;
    }

    private String getMoisEnLettre(int mois) {
        switch(mois) {
            case 1: return "JANVIER";
            case 2: return "FEVRIER";
            case 3: return "MARS";
            case 4: return "AVRIL";
            case 5: return "MAI";
            case 6: return "JUIN";
            case 7: return "JUILLET";
            case 8: return "AOUT";
            case 9: return "SEPTEMBRE";
            case 10: return "OCTOBRE";
            case 11: return "NOVEMBRE";
            case 12: return "DECEMBRE";
            default: throw new IllegalArgumentException("Numéro de mois invalide : " + mois);
        }
    }

    private boolean isValidNumericStringWithExactLength(String value, int length) {
        if (value == null) return false;
        return value.matches("^\\d{" + length + "}$");
    }

    private boolean isValidNationalIdNumber(String value) {
        if (value == null) return false;
        return value.matches("^[12]\\d{12,13}$");
    }

    public ApiResponse<Object> checkSuccursale(DeclarationModelDTO declarationModelDTO) {
        List<FactureIndividuelleDTO> factures = declarationModelDTO.getFactures();
        String numeroUnique = declarationModelDTO.getNumeroUnique();
        if (factures != null && !factures.isEmpty()) {
            if(factures.size() >= 2){
                Set<String> numerosUniques = new HashSet<>();
                for (FactureIndividuelleDTO facture : factures) {
                    if (!numerosUniques.add(facture.getNumeroUnique())) {
                        return ApiResponse.error400("Numéro unique en double détecté pour la facture : '" + facture.getNumeroUnique() + "'");
                    }
                }
                boolean isHeadquarters = nouvelleImmatriculationService.isHeadquarters(numeroUnique);
                if (!isHeadquarters){
                    return ApiResponse.error400("L'employeur avec le numéro Unique : '"+numeroUnique+"' n'est pas un siège");
                }
                List<NouvelleImmatriculation> result = nouvelleImmatriculationService.findBranchOffices(numeroUnique);
                Integer nombreDeSuccursales = result.size();
                if(nombreDeSuccursales == 0){
                    return ApiResponse.error400("L'employeur avec le numéro Unique : '"+numeroUnique+"' ne possède pas de succursales");
                }
                if(nombreDeSuccursales != factures.size()){
                    return ApiResponse.error400("L'employeur avec le numéro Unique : '"+numeroUnique+"' possède "+nombreDeSuccursales+" succursales, vous essayez d'en déclarer "+factures.size());
                }
            }
        }
        return null;
    }

    public List<FactureDNS> generateFactures(Declaration declaration, DeclarationModelDTO declarationModelDTO,User user,MailService mailService, SpringTemplateEngine templateEngine) {
        List<FactureIndividuelleDTO> factures = declarationModelDTO.getFactures();
        List<FactureDNS> facturesDNS = new ArrayList<>();
        if (factures == null || factures.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            if (factures == null || factures.isEmpty()) {
                return Collections.emptyList();
            }

            for (FactureIndividuelleDTO facture : factures) {
                FactureDNS factureDNS = createFactureDNS(facture, declaration, declarationModelDTO,user, mailService, templateEngine);
                facturesDNS.add(factureDNS);
            }
            facturesDNS = factureDNSRepository.saveAll(facturesDNS);

            for (FactureDNS factureDNS : facturesDNS) {
                String numeroFacture = "999" + String.format("%09d", factureDNS.getId());
                factureDNS.setNumeroFacture(numeroFacture);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return factureDNSRepository.saveAll(facturesDNS);
    }


    private FactureDNS createFactureDNS(FactureIndividuelleDTO facture, Declaration declaration, DeclarationModelDTO declarationModelDTO,User user,MailService mailService, SpringTemplateEngine templateEngine) {
        Double mtVieillesseRG = calculateAmount(facture.getCumulTotSalAssIpresRg(), TAUX_RG);
        Double mtVieillesseRc = calculateAmount(facture.getCumulTotSalAssIpresRcc(), TAUX_RC);
        Double mtVieillesseFam = calculateAmount(facture.getCumulTotSalAssCssPf(), TAUX_PF);
        Float TAUX_ATMP = 0F;
        String numeroUnique = facture.getNumeroUnique();

        String tauxAT = tauxATRepository.findTauxATByNumeroUnique(numeroUnique);
        if (tauxAT != null) {
            TAUX_ATMP = Float.valueOf(tauxAT);
        }

        if(TAUX_ATMP == 0F){
            TauxAT tauxAtToSave = new TauxAT();
            String apiUrl = Constants.MS_SYNCHRO_URL + "/api/get-taux-at/" + numeroUnique;
            RestTemplate restTemplate = new RestTemplate();

            try {
                ResponseEntity<ApiResponseDTO> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, ApiResponseDTO.class);
                ApiResponseDTO apiResponse = response.getBody();

                if (apiResponse != null) {
                    int code = apiResponse.getCode();
                    Map<String, Object> body = apiResponse.getBody();
                    if (body != null) {
                        if(code == 200){
                            String taux = (String) body.get("taux");
                            TAUX_ATMP = Float.valueOf(taux);
                            tauxAtToSave.setNumeroUnique(numeroUnique);
                            tauxAtToSave.setTauxAT(taux);
                            atRepository.save(tauxAtToSave);
                        }
                    }
                }
            } catch (HttpClientErrorException e) {
                log.error("Erreur lors de l'appel à l'API : {}", e.getStatusCode());
                throw new RuntimeException("Erreur lors de l'appel à l'API de synchronisation.", e);
            } catch (Exception e) {
                log.error("Erreur inattendue : {}", e.getMessage());
                throw new RuntimeException("Erreur inattendue lors de l'appel à l'API.", e);
            }
        }

        if(TAUX_ATMP == 0F){
            CodeErreurSystem codeErreurSystem = new CodeErreurSystem(dnsExceptionHandlerService);
            codeErreurSystem.handleException(CodeErreurSystem.GENERATION_FACTURE, "Taux At introuvable pour le numero unique '"+numeroUnique+"' .",user,declaration, mailService,templateEngine);
        }

        Double mtATMP = calculateAmount(facture.getCumulTotSalAssCssAtmp(), TAUX_ATMP);

        Double totalIpres = mtVieillesseRG + mtVieillesseRc;
        Double totalCss = mtVieillesseFam + mtATMP;
        Double totalApayer = totalCss + totalIpres;

        FactureDNS factureDNS = new FactureDNS();
        String numericString = generateNumeroFacture(declaration, facture);

        // Configuration de la facture
        factureDNS.setNumeroFacture(numericString);
        factureDNS.setIdDeclaration(declaration.getId());
        factureDNS.setNumeroUnique(facture.getNumeroUnique());
        factureDNS.setRaisonSociale(declarationModelDTO.getRaisonSociale());
        factureDNS.setAdresse(declarationModelDTO.getAdresse());
        factureDNS.setAgenceIpres(declaration.getCodeAgenceIPRES());
        factureDNS.setAgenceCss(declaration.getCodeAgenceCSS());
        factureDNS.setSalaireDeclare(BigDecimal.valueOf(facture.getTotalSalVerses()));
        factureDNS.setRaisonSociale(facture.getRaisonSociale());

        // Dates
        factureDNS.setDebutPeriode(declarationModelDTO.getDateDebutPeriodeCotisation().toInstant());
        factureDNS.setFinPeriode(declarationModelDTO.getDateFinPeriodeCotisation().toInstant());
        factureDNS.setDateReception(declaration.getCreateAt());

        // Montants IPRES
        factureDNS.setVieillesseRG(facture.getCumulTotSalAssIpresRg());
        factureDNS.setVieillesseRC(facture.getCumulTotSalAssIpresRcc());
        factureDNS.setTauxRG(TAUX_RG);
        factureDNS.setTauxRC(TAUX_RC);
        factureDNS.setMtVieillesseRG(mtVieillesseRG);
        factureDNS.setMtVieillesseRC(mtVieillesseRc);
        factureDNS.setMtMajorationIpres(0.0);

        // Montants CSS
        factureDNS.setPrestationFam(facture.getCumulTotSalAssCssPf());
        factureDNS.setTauxPrestationFam(TAUX_PF);
        factureDNS.setMtPrestationFam(mtVieillesseFam);
        factureDNS.setAtmp(facture.getCumulTotSalAssCssAtmp());
        factureDNS.setTauxATMP(TAUX_ATMP);
        factureDNS.setMtAtmp(mtATMP);
        factureDNS.setMtMajorationCss(0.0);

        // Totaux
        factureDNS.setTotalIpres(totalIpres);
        factureDNS.setTotalCss(totalCss);
        factureDNS.setTotalAPayer(totalApayer);

        // Informations supplémentaires
        factureDNS.setExtraInfos(declaration.getRequeteBrute());
        factureDNS.setStatut(declaration.getStatus());
        factureDNS.setEcheance(declaration.getFinCotisation().plus(Duration.ofDays(15)));

        return factureDNS;
    }

    private BigDecimal getTotalSalaireBrut(DeclarationModelDTO declaration) {
        BigDecimal total = BigDecimal.ZERO;
        for(DeclarationModel.EmployeModel infoSalarie : declaration.getInformationSalaries()) {
            total = total.add(infoSalarie.getSalaireBrut1());
        }
        return total;
    }

    private Double calculateAmount(Double base, Float taux) {
        return (base * taux) / 100;
    }

    private String generateNumeroFacture(Declaration declaration, FactureIndividuelleDTO facture) {
        return utilsService.generateOtp(12);
    }

    public int getTrimestre(Date dateDebutPeriodeCotisation) {
        return ((dateDebutPeriodeCotisation.toInstant()
            .atZone(ZoneId.systemDefault())
            .getMonthValue() - 1) / 3) + 1;
    }

    public HashMap<String, Object> addToTempsDePresence(Declaration declaration, DeclarationModelDTO preDnsInput) throws JSONException {
        HashMap<String, Object> rapport = new HashMap<>();
        try {
            Integer annee = preDnsInput.getDateDebutPeriodeCotisation().toInstant().atZone(ZoneId.systemDefault()).getYear();
            ContenuTP contenuTP = new ContenuTP();
            contenuTP.setNumeroUnique(Long.valueOf(preDnsInput.getNumeroEmployeur()));
            contenuTP.setAnnee(annee);
            contenuTP.setTrimestre(getTrimestre(preDnsInput.getDateDebutPeriodeCotisation()));
            contenuTP.setStatut(StatutTP.SAISIE);

            Set<SalarieTP> salarieTPs = new HashSet<>();

            if(declaration.getTypeDeclaration().equalsIgnoreCase("MENSUEL")){
                String requeteBrute = declaration.getRequeteBrute();
                JSONObject jsonObject = new JSONObject(requeteBrute);
                JSONArray informationSalaries = jsonObject.getJSONArray("informationSalaries");

                for(int i = 0; i < informationSalaries.length(); i++) {
                    JSONObject salarie = informationSalaries.getJSONObject(i);
                    String nomEmploye = salarie.optString("nomEmploye","");
                    String prenomEmploye = salarie.optString("prenomEmploye","");
                    String typePieceIdentite = salarie.optString("typePieceIdentite","");
                    String numPieceIdentite = salarie.optString("numPieceIdentite","");

                    // Validation de numPieceIdentite
                    if (!isValidNumericString(numPieceIdentite) || numPieceIdentite.length() > 19) {
                        rapport.put("rapport " + rapport.size(),
                            "Numéro de pièce d'identité invalide pour " + prenomEmploye + " " + nomEmploye + ": " + numPieceIdentite);
                        continue; // Passer à l'enregistrement suivant
                    }
                    Integer nombreHeures1 = salarie.optInt("nombreHeures1",0);
                    Integer nombreHeures2 = salarie.optInt("nombreHeures2",0);
                    Integer nombreHeures3 = salarie.optInt("nombreHeures3",0);

                    SalarieTP salarieTP = new SalarieTP();
                    salarieTP.setNom(nomEmploye);
                    salarieTP.setPrenom(prenomEmploye);
                    salarieTP.setTypePiece(typePieceIdentite);
                    salarieTP.setNumeroPiece(numPieceIdentite);
                    salarieTP.setTempsDePresenceHeureMois1(nombreHeures1);
                    salarieTP.setTempsDePresenceHeureMois2(nombreHeures2);
                    salarieTP.setTempsDePresenceHeureMois3(nombreHeures3);
                    boolean exists = contenuTPService.checkTempsPresenceExists(contenuTP.getAnnee(),contenuTP.getTrimestre(),nomEmploye,prenomEmploye,typePieceIdentite,numPieceIdentite);

                    if (exists){
                        rapport.put("rapport " + rapport.size(),
                            "Temps de présence de "+ prenomEmploye +" " + nomEmploye+" (N° pièce : "+numPieceIdentite+") a déjà été enregistré pour le trimestre "+contenuTP.getTrimestre()+" de l'année "+contenuTP.getAnnee()+".");
                    }
                    else {
                        rapport.put("rapport " + rapport.size(), "Temps de présence ajouté pour : " + prenomEmploye + " " + nomEmploye);
                        salarieTPs.add(salarieTP);
                    }
                }

                contenuTP.setSalarieTPs(salarieTPs);
            }

            if(!salarieTPs.isEmpty()){
                contenuTPRepository.save(contenuTP);
            }
        }catch (Throwable e){
            throw new RuntimeException(e);
        }

        return rapport;
    }

    // Méthode utilitaire pour vérifier si la chaîne est numérique
    private boolean isValidNumericString(String value) {
        return value != null && value.matches("\\d+");
    }


    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    public ApiResponse<Object> checkUnicity(DeclarationModelDTO preDnsInput) {
        try {
            byte[] bytes = Base64.getDecoder().decode(preDnsInput.getSalariesFile().split(",")[1]);
            Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(bytes));
            HashMap<String, Object> result = new HashMap<>();
            List<SheetDuplicateDTO> allDuplicates = new ArrayList<>();
            List<SalarieDns> allSheetEntries = new ArrayList<>();

            boolean lookupExists = false;
            boolean configExists = false;

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName();
              //  System.out.println("workbook is hidden: " + workbook.isSheetHidden(sheetIndex));

                if (workbook.isSheetHidden(sheetIndex)) {
                    if ("LOOKUP".equals(sheetName)) {
                        lookupExists = true;
                    } else if ("CONFIG".equals(sheetName)) {
                        configExists = true;
                    }
                    continue; // Ignorer le traitement des feuilles cachées
                }

               // System.out.println("sheetName: " + sheetName);
                Map<String, Integer> firstOccurrence = new HashMap<>();

                for (int rowIndex = 6; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null || isEntireRowEmpty(row)) {
                        continue;
                    }

                    String typeDocument = getCellValueAsString(row.getCell(3));
                    String numeroDocument = getCellValueAsString(row.getCell(4));
                    String nom = getCellValueAsString(row.getCell(1));
                    String prenom = getCellValueAsString(row.getCell(2));
                    String salaire = getCellValueAsString(row.getCell(6));
                    String typeContrat = getCellValueAsString(row.getCell(5));

                    if (typeDocument.isEmpty() || numeroDocument.isEmpty() ||
                        nom.isEmpty() || prenom.isEmpty() ||
                        nom.equalsIgnoreCase("Nom") || prenom.equalsIgnoreCase("Prénom")) {
                        continue;
                    }

                    String documentKey = typeDocument + "_" + numeroDocument;

                    if (firstOccurrence.containsKey(documentKey)) {
                        Map<String, String> duplicateValues = new HashMap<>();
                        duplicateValues.put("typeDocument", typeDocument);
                        duplicateValues.put("numeroDocument", numeroDocument);
                        duplicateValues.put("nom", nom);
                        duplicateValues.put("prenom", prenom);
                        duplicateValues.put("salaire", salaire);
                        duplicateValues.put("typeContrat", typeContrat);

                        allDuplicates.add(new SheetDuplicateDTO(
                            sheetName,
                            "ligne " + firstOccurrence.get(documentKey),
                            "ligne " + (rowIndex + 1),
                            duplicateValues
                        ));
                    } else {
                        firstOccurrence.put(documentKey, rowIndex + 1);
                    }

                    allSheetEntries.add(new SalarieDns(
                        sheetName, rowIndex + 1, nom, prenom, salaire,
                        typeContrat, typeDocument, numeroDocument
                    ));
                }
            }
           // System.out.println("lookupExists: " + lookupExists);
            // Vérification après la boucle
            if (!lookupExists && !configExists) {
                return ApiResponse.error400("Le format est invalide, veuillez télécharger le template");
            }

            List<CrossSheetDuplicateDTO> crossSheetDuplicates = findCrossSheetDuplicates(allSheetEntries);

            if (!allDuplicates.isEmpty() || !crossSheetDuplicates.isEmpty()) {
                result.put("duplicationSurMemeFeuille", allDuplicates);
                result.put("duplicationInterFeuille", crossSheetDuplicates);
                return ApiResponse.error400(result);
            }

            return null;

        } catch (IOException e) {
            log.error("Erreur lors de la lecture du fichier Excel", e);
            return ApiResponse.error500("Erreur lors de la lecture du fichier Excel : " + e.getMessage());
        } catch (InvalidFormatException e) {
            return ApiResponse.error500("Erreur lors du formatage du fichier Excel : " + e.getMessage());
        }
    }


    private List<CrossSheetDuplicateDTO> findCrossSheetDuplicates(List<SalarieDns> entries) {
        List<CrossSheetDuplicateDTO> duplicates = new ArrayList<>();
        Map<String, SalarieDns> uniqueEntries = new HashMap<>();

        entries.sort((a, b) -> {
            int sheetCompare = a.getNomFeuille().compareTo(b.getNomFeuille());
            if (sheetCompare != 0) return sheetCompare;
            return Integer.compare(a.getNumeroLigne(), b.getNumeroLigne());
        });

        for (SalarieDns entry : entries) {
            String key = String.format("%s_%s_%s",
                entry.getTypeDocument(),
                entry.getNumeroDocument(),
                entry.getNom() + "_" + entry.getPrenom());

            if (uniqueEntries.containsKey(key)) {
                SalarieDns existing = uniqueEntries.get(key);

                if (existing.getNomFeuille().equals(entry.getNomFeuille()) &&
                    existing.getNumeroLigne() == entry.getNumeroLigne()) {
                    continue;
                }

                Map<String, String> duplicateValues = new HashMap<>();
                duplicateValues.put("nom", entry.getNom());
                duplicateValues.put("prenom", entry.getPrenom());
                duplicateValues.put("typeDocument", entry.getTypeDocument());
                duplicateValues.put("numeroDocument", entry.getNumeroDocument());
                duplicateValues.put("salaire", entry.getSalaire());
                duplicateValues.put("typeContrat", entry.getTypeContrat());

                duplicates.add(new CrossSheetDuplicateDTO(
                    existing.getNomFeuille(),
                    "ligne "+existing.getNumeroLigne(),
                    entry.getNomFeuille(),
                    "ligne "+entry.getNumeroLigne(),
                    duplicateValues
                ));
            } else {
                uniqueEntries.put(key, entry);
            }
        }

        return duplicates;
    }

    public List<CMGETLISTESECURSALES.Results> recupererSuccursales(String personId) throws Exception {

        Holder<CMGETLISTESECURSALES> listeSuccursale = succursaleService.getListeSuccursales(personId);

        if (listeSuccursale == null || listeSuccursale.value == null ||
            listeSuccursale.value.getResults() == null) {
            throw new Exception("Aucune donnée de succursale trouvée pour l'employeur: " + personId);
        }

        List<CMGETLISTESECURSALES.Results> resultats = listeSuccursale.value.getResults();

        if (resultats.isEmpty()) {
            throw new Exception("L'employeur avec le numéro unique ('" + personId + "') ne possède pas de succursales");
        }

        log.info("Nombre de succursales trouvées pour l'employeur {}: {}", personId, resultats.size());

        return resultats;
    }

    public Page<DeclarationRequetBruteDto> findDeclarationsByIdentityDocument(
        TypeDePiece typePieceIdentite,
        String numPieceIdentite,
        Pageable pageable) {

        List<Declaration> allDeclarations = declarationRepository.findAll();
        List<Declaration> filteredDeclarations = allDeclarations.stream()
            .filter(declaration -> hasMatchingSalarie(declaration, typePieceIdentite, numPieceIdentite))
            .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredDeclarations.size());

        List<DeclarationRequetBruteDto> pageContent = filteredDeclarations.subList(start, end)
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());

        return new PageImpl<>(
            pageContent,
            pageable,
            filteredDeclarations.size()
        );
    }

    private boolean hasMatchingSalarie(
        Declaration declaration,
        TypeDePiece typePieceIdentite,
        String numPieceIdentite) {
        try {
            JsonNode rootNode = objectMapper.readTree(declaration.getRequeteBrute());
            JsonNode salariesNode = rootNode.get("informationSalaries");

            if (salariesNode.isArray()) {
                for (JsonNode salarie : salariesNode) {
                    String typePiece = salarie.get("typePieceIdentite").asText();
                    String numPiece = salarie.get("numPieceIdentite").asText();

                    if (typePiece.equals(typePieceIdentite.name()) &&
                        numPiece.equals(numPieceIdentite)) {
                        return true;
                    }
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON from requeteBrute", e);
        }
        return false;
    }

    private DeclarationRequetBruteDto convertToDto(Declaration declaration) {
        try {
            return objectMapper.readValue(declaration.getRequeteBrute(), DeclarationRequetBruteDto.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting declaration to DTO", e);
            throw new RuntimeException("Error processing declaration data", e);
        }
    }

    public Long generateUniqueSynthesis() {
        Random random = new Random();
        Long synthese;

        do {
            synthese = 10000000L + random.nextInt(90000000);
        } while (declarationRepository.existsBySynthese(synthese));

        return synthese;
    }

    public Date getDateEndPeriodeCotisation(Date dateDebutPeriodeCotisation, String typeDeclaration) {
        if (dateDebutPeriodeCotisation == null || typeDeclaration == null) {
            throw new IllegalArgumentException("La date de début et le type de déclaration ne peuvent pas être null");
        }

        LocalDate dateDebut = dateDebutPeriodeCotisation.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        LocalDate dateFin;

        switch (typeDeclaration.toUpperCase()) {
            case "MENSUEL":
                dateFin = dateDebut.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "TRIMESTRIEL":
                dateFin = dateDebut.plusMonths(2)
                    .with(TemporalAdjusters.lastDayOfMonth());
                break;
            default:
                throw new IllegalArgumentException("Type de déclaration inconnu: " + typeDeclaration);
        }

        return Date.from(dateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public void sendDeclaration(Declaration declaration) {
        try {
            String sujet = "Déclaration";
            String contenu;
            String prenom = "";
            String nom = "";
            String email = "";

            // Vérifier les rôles de l'utilisateur
            if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) ||
                isCurrentUserInRole(AuthoritiesConstants.AGENT) ||
                isCurrentUserInRole(AuthoritiesConstants.CABINET) ||
                isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) ||
                isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {

                // Récupérer l'immatriculation
                Optional<ImmatriculationRecuperee> optionalImmatriculation = immatriculationRecupereeRepository.findByNumeroUnique(declaration.getNumeroUnique());
                if (optionalImmatriculation.isPresent()) {
                    ImmatriculationRecuperee immatriculationRecuperee = optionalImmatriculation.get();
                    Optional<User> optionalUser1 = userRepository.findById(immatriculationRecuperee.getUserId());
                    if (optionalUser1.isPresent()) {
                        User userR = optionalUser1.get();
                        prenom = userR.getFirstName();
                        nom = userR.getLastName();
                        email = userR.getEmail();
                    }
                }
            }else {

                // Récupérer l'utilisateur à partir de l'ID
                Optional<User> optionalUser = userRepository.findById(declaration.getOwnerID());
                if (!optionalUser.isPresent()) {
                    System.out.println("User not found");
                    return; // Sortir de la méthode si l'utilisateur n'est pas trouvé
                }

                User user = optionalUser.get();
                prenom = user.getFirstName();
                nom = user.getLastName();
                email = user.getEmail();
                System.out.println("user test: " + user);

            }

            // Préparer le contenu de l'email
            Locale locale = Locale.forLanguageTag("FR");
            Context context = new Context(locale);
            context.setVariable("nom", nom);
            context.setVariable("prenom", prenom);
            context.setVariable("du", declaration.getDebutCotisation());
            context.setVariable("au", declaration.getFinCotisation());

            contenu = templateEngine.process("mail/soumissiondeclarationEmail", context);
            mailService.sendEmail(email, sujet, contenu, false, true);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de la déclaration", e);
        }
    }

    public void sendAllSynchronisationDelaration(TypeJobName jobType) {
        if (isRunning(jobType)) {
            log.warn("Le batch {} est déjà en cours.", jobType);
            return;
        }

        isRunningMap.put(jobType, true);
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        if (!loggedusername.isPresent()) {
            loggedusername = Optional.of("plateforme");
        }
        String username = loggedusername.get();
        String userEmail = Optional.ofNullable(userRepository.findByLogin(username))
            .map(User::getEmail)
            .orElse(null);

        try {
            log.debug("Début du batch {}", jobType);

           // String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/send-invoices";
            String apiUrl = Constants.MS_SYNCHRO_URL+ "/api/synch/send-invoices";
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                log.debug("Réponse API du batch {} : {}", jobType, response.getBody());

                Map<String, Object> responseBody = response.getBody();
                updateJournalJob(responseBody, userEmail);

            } else {
                log.error("Erreur API pour le batch {} : {}", jobType, response.getStatusCode());
            }

        } catch (Exception e) {
            log.error("Erreur inattendue dans le batch {} : {}", jobType, e.getMessage(), e);
        } finally {
            isRunningMap.put(jobType, false);
            log.debug("Fin du batch {}", jobType);
        }
    }

    public void updateJournalJob(Map<String, Object> responseBody, String userEmail) {
        if (responseBody == null || !(responseBody.get("body") instanceof Map)) {
            log.info("ResponseBody invalide ou manquant");
            return;
        }

        Map<String, Object> body = (Map<String, Object>) responseBody.get("body");

        if (!body.containsKey("journalJob_id")) {
            log.info("journalJob_id manquant dans le body");
            return;
        }

        try {
            Long journalJobId = Long.valueOf(body.get("journalJob_id").toString());
            log.info("Mise à jour du JournalJob avec ID: {}", journalJobId);

            int updatedRows = journalJobService.updateJobExecution(
                journalJobId,
                userEmail,
                ModeExecution.PORTAIL
            );

            if (updatedRows > 0) {
                log.info("JournalJob {} mis à jour avec succès. DemarrePar: {}, ModeExecution: {}",
                    journalJobId, userEmail, ModeExecution.PORTAIL);
            } else {
                log.info("Aucun JournalJob trouvé avec l'ID {} pour la mise à jour", journalJobId);
            }

        } catch (NumberFormatException e) {
            log.info("Format invalide pour journalJob_id: {}", body.get("journalJob_id"), e);
        } catch (Exception e) {
            log.info("Erreur lors de la mise à jour du JournalJob", e);
        }
    }

    public boolean isRunning(TypeJobName jobType) {
        return isRunningMap.getOrDefault(jobType, false);
    }
    public String stopSynchronisationDelaration(String uuid) {
        log.debug("Arrêt demandé pour la synchronisation SEND_INVOICES_TO_PSRM");

        Optional<JournalJob> jobOpt = journalJobRepository.findByBatchExecutionId(uuid);

        if (!jobOpt.isPresent()) {
            throw new NoSuchElementException("Aucun job trouvé avec l'UUID : " + uuid);
        }


        JournalJob journalJob = jobOpt.get();
        String statut = journalJob.getStatut();
        String nom = journalJob.getNom();
        Instant termineLe = journalJob.getTermineLe();

        if (!"RUNNING".equals(statut)) {
            throw new IllegalStateException("Le job n'est pas en cours d'exécution. Statut actuel : " + statut);
        }

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/interrupt/" + uuid;
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                log.debug("Réponse de l'API interruption : {}", response.getBody());
                if ("sendInvoicesToPSRM".equals(nom)) {
                    isRunningMap.put(TypeJobName.SEND_INVOICES_TO_PSRM, false);
                }else {
                    isRunningMap.put(TypeJobName.sendImmatriculations, false);
                }
                return response.getBody();
            } else {
                String msg = "Erreur lors de l'appel à l'API d'interruption : " + response.getStatusCode();
                log.error(msg);
                throw new RuntimeException(msg);
            }

        } catch (HttpClientErrorException e) {
            String msg = "Erreur de client HTTP : " + e.getStatusCode();
            log.error(msg);
            throw new RuntimeException(msg, e);
        } catch (Exception e) {
            String msg = "Erreur inattendue : " + e.getMessage();
            log.error(msg);
            throw new RuntimeException(msg, e);
        }
    }
    public ApiResponse<String> validateSalariesRegularisation(DeclarationModelDTO preDnsInput) {
        if (preDnsInput == null || preDnsInput.getSalariesFile() == null || preDnsInput.getSalariesFile().trim().isEmpty()) {
            return null;
        }
        try {
            byte[] excelBytes = Base64.getDecoder().decode(preDnsInput.getSalariesFile().split(",")[1]);
            ApiResponse<String> response;

            // Créer le workbook pour obtenir le nombre de feuilles
            try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(excelBytes))) {
                int numberOfSheets = workbook.getNumberOfSheets();

                for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {

                    // Vérifier si la feuille est cachée
                    if (workbook.isSheetHidden(sheetIndex)) {
                        continue; // Ignorer les feuilles cachées
                    }

                    // Valider la structure, année, mois, etc. pour chaque feuille
                    response = validateStructure(excelBytes);
                    if (response != null) {
                        return response;
                    }

                    response = validateAnnee(excelBytes);
                    if (response != null) {
                        return response;
                    }

                    response = validateMois(excelBytes);
                    if (response != null) {
                        return response;
                    }

                    response = validateAnneeEtMois(excelBytes, preDnsInput.getDateDebutPeriodeCotisation(), sheetIndex);
                    if (response != null) {
                        return response;
                    }


                    // Appeler validateSalariesList avec l'index de la feuille
                    response = validateSalariesList(excelBytes, sheetIndex);
                    if (response != null) {
                        return response;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            return ApiResponse.error500("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }


    public Declaration updateDeclaration(Long id, DeclarationModelDTO preDnsInput) throws JsonProcessingException {
        Optional<Declaration> optionalDeclaration = declarationRepository.findById(id);

        if (!optionalDeclaration.isPresent()) {
            throw new IllegalArgumentException("Déclaration introuvable avec ID : " + id);
        }

        Declaration declaration = optionalDeclaration.get();

        if (preDnsInput.getDateDebutPeriodeCotisation() != null) {
            declaration.setDebutCotisation(preDnsInput.getDateDebutPeriodeCotisation().toInstant());
        }
        if (preDnsInput.getDateFinPeriodeCotisation() != null) {
            declaration.setFinCotisation(preDnsInput.getDateFinPeriodeCotisation().toInstant());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        declaration.setRequeteBrute(objectMapper.writeValueAsString(preDnsInput));

        return declarationRepository.save(declaration);
    }

    public List<FactureDNS> updateFactures(
        Declaration declaration,
        DeclarationModelDTO declarationModelDTO,
        User user,
        MailService mailService,
        SpringTemplateEngine templateEngine
    ) {
        List<FactureIndividuelleDTO> nouvellesFactures = declarationModelDTO.getFactures();

        if (nouvellesFactures == null || nouvellesFactures.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.println("facture declarationModelDTO: " + declarationModelDTO);
        System.out.println("facture user: " + user);
        System.out.println("facture mailService: " + mailService);
        System.out.println("facture templateEngine: " + templateEngine);
        // Récupérer les factures existantes pour cette déclaration
        List<FactureDNS> facturesExistantes = factureDNSRepository.findAllByIdDeclaration(declaration.getId());
        System.out.println("facture facturesExistantes: " + facturesExistantes);
        // Supprimer les anciennes si nécessaire (optionnel, selon logique métier)
        factureDNSRepository.deleteAll(facturesExistantes);

        System.out.println("facture: " + facturesExistantes);
        // Créer de nouvelles factures avec les infos à jour
        List<FactureDNS> facturesMisesAJour = new ArrayList<>();
        for (FactureIndividuelleDTO dto : nouvellesFactures) {
            FactureDNS factureDNS = createFactureDNS(dto, declaration, declarationModelDTO, user, mailService, templateEngine);
            facturesMisesAJour.add(factureDNS);
        }

        // Sauvegarder et assigner un nouveau numéro si besoin
        facturesMisesAJour = factureDNSRepository.saveAll(facturesMisesAJour);

        for (FactureDNS facture : facturesMisesAJour) {
            String numeroFacture = "999" + String.format("%09d", facture.getId());
            facture.setNumeroFacture(numeroFacture);
        }

        return factureDNSRepository.saveAll(facturesMisesAJour);
    }
}
