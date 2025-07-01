package com.secusociale.portail.web.rest.declaration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.CodeErreurSystem;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.model.DeclarationModel;
import com.secusociale.portail.repository.DeclarationRepository;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.declaration.*;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.custom.DeclarationModelDTO;
import com.secusociale.portail.service.immatriculation.AgencesRattachementServicev2;
import com.secusociale.portail.service.soap.agencesRattachement.AGENCESEMPLOYEURSERVICE;
import com.secusociale.portail.service.soap.declaration.existance.cm_has_dns.CMHASDNS;
import com.secusociale.portail.service.soap.declarations_manquantes.CMINFORMATIONMANQUEDNSIPRES;
import com.secusociale.portail.service.soap.derniersDeclarationsV2.DERNDNSEMPLOYEURSERVICEV2;
import com.secusociale.portail.service.soap.getFacture.CMGETFACTURE;
import com.secusociale.portail.service.soap.getTauxPlafonds.CMGETCONSTANTS;
import com.secusociale.portail.service.soap.preDNS.CmPresDnsFault;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Holder;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeclarationPortailResource {

    private static final String ENTITY_NAME = "CmPresDns";
    private final Logger log = LoggerFactory.getLogger(DeclarationPortailResource.class);

    private final ServerCheckRepository serverCheckRepository;
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;

    private final DeclarationService declarationService;

    private final DeclarationExistanceService declarationExistanceService;

    private final DnsExceptionHandlerService dnsExceptionHandlerService;

    private final PreDNSService prednsService;

    private final MailService mailService;

    private final SpringTemplateEngine templateEngine;

    private final ListeDeclarationsServiceV2 listeDeclarationsServiceV2;

    private final DeclarationsManquantesService declarationManquanteService;

    private final GetTauxEtPlafondsService getTauxPlafondService;

    private final DocumentUrlService documentUrlService;

    private final DeclarationRepository declarationRepository;

    private final UserRepository userRepository;

    private final AgencesRattachementServicev2 agencesRattachementServicev2;

    private final JournalDeclarationService journalDeclarationService;


    @PostMapping("/preDNS")
    public Object getPreDNSEmployeur(@RequestBody DeclarationModel preDnsInput) {
        log.debug("REST request to get PreDNS: {}", ENTITY_NAME);
        System.out.println(preDnsInput.toString());
        HashMap<String, Object> errors = new HashMap<>();
        try {
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                errors.put("code", "8000");
                errors.put("message", "PSRM seems to be not UP");
                errors.put("error", "Aucune données de preDNS!");
                return ResponseEntity.badRequest().body(errors);
            }
            return prednsService.getPreDns(preDnsInput);
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("code", "400");
            errors.put("message", e.getLocalizedMessage() + "/ " + e.getMessage());
            errors.put("error", "Aucune données de preDNS!");
            errors.put("trace", e.toString());
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @PostMapping("/hasdns")
    public Object hasDNS(@RequestBody CMHASDNS cmhasdns) {
        log.debug("REST request to get hasDNS: {}", ENTITY_NAME);
        HashMap<String, Object> errors = new HashMap<>();
        try {
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                errors.put("code", "8000");
                errors.put("message", "Une erreur est survenue, merci de réessayer plus tard");
                errors.put("error", "PSRM seems to be not UP");
                return ResponseEntity.badRequest().body(errors);
            }
            return declarationExistanceService.hasDNS(cmhasdns);
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue, merci de réessayer plus tard");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @PutMapping("/check-dns-file")
    public Object checkUnicity(@RequestBody DeclarationModelDTO preDnsInput) {

        ApiResponse<String> response = declarationService.validateSalariesFile(preDnsInput);
        if(response != null){
            return response;
        }

        ApiResponse<Object> uniqueCheck = declarationService.checkUnicity(preDnsInput);
        if (uniqueCheck != null) {
            return uniqueCheck;
        }

        return ApiResponse.success("Le fichier est conforme !");
    }

    @PostMapping("/dns")
    @Transactional
    public Object createDeclaration(@RequestBody DeclarationModelDTO preDnsInput) {
        log.debug("REST request to send DNS: {}", ENTITY_NAME);

        //TODO à revoir
//        ApiResponse<Object> succursaleCheck = declarationService.checkSuccursale(preDnsInput);
//        if (succursaleCheck != null) {
//            return succursaleCheck;
//        }


        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> corrects = new HashMap<>();
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
        try {
            Date dateFin = declarationService.getDateEndPeriodeCotisation(preDnsInput.getDateDebutPeriodeCotisation(),preDnsInput.getTypeDeclaration());
            preDnsInput.setDateFinPeriodeCotisation(dateFin);

            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/immatPortail"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("Pas d'utilisateur")
                    .build();
            }
            String username = loggedusername.get();
            User user = userRepository.findByLogin(username);
            if (user == null) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/immatPortail"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("Pas d'utilisateur")
                    .build();
            }
            Long userId = user.getId();

            if (StringUtils.isNotEmpty(preDnsInput.getSalariesFile())) {

               ApiResponse<Object> unicityCheckResponse = declarationService.checkUnicity(preDnsInput);
               if (unicityCheckResponse != null) {
                   return unicityCheckResponse;
              }

                String uuid = RandomStringUtils.randomAlphanumeric(10);
                String idEmployer = preDnsInput.getNumeroEmployeur();
                String numeroUnique = preDnsInput.getNumeroUnique();

                // Vérification de l'existance de la déclaration
                ApiResponse<String> response = declarationService.validateExistingDeclaration(preDnsInput);
                if (response != null) {
                    return response;
                }

                // Validation des champs numériques
                if (!isValidNumericString(numeroUnique)) {
                    return ResponseEntity.badRequest().body(createErrorResponse("Le numéro unique doit être numérique."));
                }

                HashMap<String, String> extensions = new HashMap<>();
                extensions.put("vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
                extensions.put("msexcel", ".xls");
                String extension = StringUtils.isEmpty(preDnsInput.getSalariesFile().split(";")[0].split("/")[1]) ? ".xlsx" : extensions.get(preDnsInput.getSalariesFile().split(";")[0].split("/")[1]);
                String fileName = "PW_EMP" + idEmployer + "_" + uuid + "_FID" + numeroUnique + extension;
                MultipartFile multipartFile = Objects.requireNonNull(base64ToMultipart(preDnsInput.getSalariesFile(), "listeSalaries"));
                String path = documentUrlService.uploadedExcel(multipartFile, fileName);
                //boolean isSendToFTP = declarationService.putToFTPServer(Objects.requireNonNull(base64ToInputStream(preDnsInput.getSalariesFile())), fileName);
                boolean isSendToFTP = false;
                if (isSendToFTP) {
                    Declaration declaration = new Declaration();
                    declaration
                        .isRead(false)
                        .ownerID(userId)
                        .uuid(uuid)
                        .raisonSociale(preDnsInput.getRaisonSociale())
                        .status("SOUMISE")
                        .fileName(fileName)
                        .createAt(Instant.now())
                        .numeroUnique(numeroUnique)
                        .idIdentifiant(preDnsInput.getNumeroEmployeur())
                        .address(preDnsInput.getAdresse())
                        .debutCotisation(preDnsInput.getDateDebutPeriodeCotisation().toInstant())
                        .finCotisation(preDnsInput.getDateFinPeriodeCotisation().toInstant())
                        .fileURL(path)
                        .isUploaded(true)
                        .typeDeclaration(preDnsInput.getTypeDeclaration())
                        .typeIdentifiant("numeroUnique")
                        .codeAgenceCSS(getAgence(numeroUnique, "css"))
                        .codeAgenceIPRES(getAgence(numeroUnique, "ipres"))
                        .reponseBrute("")
                        .requeteBrute((new ObjectMapper()).writeValueAsString((DeclarationModel) preDnsInput));
                    declarationRepository.save(declaration);
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("code", 200);
                    result.put("message", "Déclaration soumise, le traitement est en cours");
                    result.put("uuid", uuid);
                    return result;
                } else {
                    Declaration declaration = new Declaration();
                    declaration
                        .isRead(false)
                        .ownerID(userId)
                        .uuid(uuid)
                        .raisonSociale(preDnsInput.getRaisonSociale())
                        .status("SOUMISE")
                        .fileName(fileName)
                        .createAt(Instant.now())
                        .numeroUnique(numeroUnique)
                        .idIdentifiant(preDnsInput.getNumeroEmployeur())
                        .address(preDnsInput.getAdresse())
                        .debutCotisation(preDnsInput.getDateDebutPeriodeCotisation().toInstant())
                        .finCotisation(preDnsInput.getDateFinPeriodeCotisation().toInstant())
                        .fileURL(path)
                        .isUploaded(false)
                        .typeDeclaration(preDnsInput.getTypeDeclaration())
                        .typeIdentifiant("numeroUnique")
                        .codeAgenceCSS(getAgence(numeroUnique, "css"))
                        .codeAgenceIPRES(getAgence(numeroUnique, "ipres"))
                        .reponseBrute("")
                        .requeteBrute((new ObjectMapper()).writeValueAsString((DeclarationModel) preDnsInput));
                    CodeErreurSystem codeErreurSystem = new CodeErreurSystem(dnsExceptionHandlerService);
                    Declaration declarationSaved = null;
                    try {
                        declarationSaved = declarationRepository.save(declaration);
                    } catch (RuntimeException ex) {
                        log.error("Erreur lors de la sauvegarde de la déclaration", ex);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.SAVING_DECLARATION, (RuntimeException) ex,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.SAVING_DECLARATION));
                    } catch (Exception ex) {
                        log.error("Erreur lors de la sauvegarde de la déclaration", ex);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.SAVING_DECLARATION, (Exception) ex,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.SAVING_DECLARATION));
                    }

                    List<FactureDNS> factureDNS = null;
                    try {
                        factureDNS = declarationService.generateFactures(declarationSaved, preDnsInput,user,mailService,templateEngine);
                    } catch (RuntimeException e) {
                        log.error("Erreur lors de la generation de facture", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.GENERATION_FACTURE, (RuntimeException) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.GENERATION_FACTURE));
                    }catch (Exception e) {
                        log.error("Erreur lors de la generation de facture", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.GENERATION_FACTURE, (Exception) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.GENERATION_FACTURE));
                    }

                    HashMap<String, Object> rapport = null;
                    try {
                        rapport = declarationService.addToTempsDePresence(declarationSaved, preDnsInput);
                    } catch (RuntimeException e) {
                        log.error("Erreur lors de l'ajout des temps travail", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE, (RuntimeException) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE));
                    } catch (Exception e) {
                        log.error("Erreur lors de l'ajout des temps travail", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE, (Exception) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE));
                    }

                    if(declarationSaved.getId()!= null){
                        declarationService.sendDeclaration(declarationSaved);
                    }

                    errors.put("rapport temps de présence ", rapport);
                    errors.put("code", "200");
                    errors.put("message", "Déclaration soumise, le traitement est en cours");
                    errors.put("factures", factureDNS);
                    errors.put("declaration", declarationSaved);
                    errors.put("isMultiSite", preDnsInput.getIsMultiSite());
                    errors.put("uuid", uuid);
                    return ResponseEntity.ok().body(errors);
                }
            } else {
                Declaration declaration = new Declaration();
                CodeErreurSystem codeErreurSystem = new CodeErreurSystem(dnsExceptionHandlerService);
                try {
                    String uuid = RandomStringUtils.randomAlphanumeric(10);
                    String idEmployer = preDnsInput.getNumeroEmployeur();
                    String numeroUnique = preDnsInput.getNumeroUnique();
                    declaration
                        .isRead(false)
                        .ownerID(userId)
                        .uuid(uuid)
                        .status("SOUMISE")
                        .fileName(null)
                        .createAt(Instant.now())
                        .numeroUnique(numeroUnique)
                        .idIdentifiant(preDnsInput.getNumeroEmployeur())
                        .raisonSociale(preDnsInput.getRaisonSociale())
                        .address(preDnsInput.getAdresse())
                        .debutCotisation(preDnsInput.getDateDebutPeriodeCotisation().toInstant())
                        .finCotisation(preDnsInput.getDateFinPeriodeCotisation().toInstant())
                        .isUploaded(false)
                        .typeDeclaration(preDnsInput.getTypeDeclaration())
                        .typeIdentifiant("numeroUnique")
                        .codeAgenceCSS(getAgence(numeroUnique, "css"))
                        .codeAgenceIPRES(getAgence(numeroUnique, "ipres"))
                        .reponseBrute("")
                        .synthese(declarationService.generateUniqueSynthesis())
                        .requeteBrute((new ObjectMapper()).writeValueAsString((DeclarationModel) preDnsInput));

                    // Send directly to PCRM
//                    ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
//                    if (serverCheck.getEtat().equalsIgnoreCase("UP")) {
//                        Holder<DeclarationModel> resultat = dnsService.createDns((DeclarationModel) preDnsInput);
//                        if (resultat != null) {
//                            String brute = (new ObjectMapper()).writeValueAsString(resultat);
//                            declaration.reponseBrute(brute).status("TRAITER").processFlowId(resultat.value.getFormId());
//                            declarationRepository.save(declaration);
//                        }
//                    }

                    Declaration declarationSaved = null;
                    try {
                        declarationSaved = declarationRepository.save(declaration);
                    } catch (RuntimeException ex) {
                        log.error("Erreur lors de la sauvegarde de la déclaration", ex);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.SAVING_DECLARATION, (RuntimeException) ex,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.SAVING_DECLARATION));
                    } catch (Exception ex) {
                        log.error("Erreur lors de la sauvegarde de la déclaration", ex);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.SAVING_DECLARATION, (Exception) ex,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.SAVING_DECLARATION));
                    }

                    List<FactureDNS> factureDNS = null;
                    try {
                        factureDNS = declarationService.generateFactures(declarationSaved, preDnsInput,user,mailService,templateEngine);
                    } catch (RuntimeException e) {
                        log.error("Erreur lors de la generation de facture", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.GENERATION_FACTURE, (RuntimeException) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.GENERATION_FACTURE));
                    }catch (Exception e) {
                        log.error("Erreur lors de la generation de facture", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.GENERATION_FACTURE, (Exception) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.GENERATION_FACTURE));
                    }

                    HashMap<String, Object> rapport = null;
                    try {
                        rapport = declarationService.addToTempsDePresence(declarationSaved, preDnsInput);
                    } catch (RuntimeException e) {
                        log.error("Erreur lors de l'ajout des temps travail", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE, (RuntimeException) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE));
                    } catch (Exception e) {
                        log.error("Erreur lors de l'ajout des temps travail", e);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        codeErreurSystem.handleException(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE, (Exception) e,user,declaration, mailService,templateEngine);
                        return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.ADDING_TEMPS_DE_PRESENCE));
                    }

                    if(declarationSaved.getId()!= null){
                        declarationService.sendDeclaration(declarationSaved);
                    }

                    errors.put("rapport temps de présence ", rapport);
                    errors.put("code", "200");
                    errors.put("message", "Déclaration soumise, le traitement est en cours");
                    errors.put("factures", factureDNS);
                    errors.put("declaration", declarationSaved);
                    errors.put("isMultiSite", preDnsInput.getIsMultiSite());
                    errors.put("uuid", uuid);
                    return errors;
                } catch (Exception e) {
//                    declaration.reponseBrute(e.getMessage()).status("INVALIDE");
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
//                    errors.put("code", "400");
//                    errors.put("message", e.getMessage());
//                    errors.put("trace", e.toString());
//                    e.printStackTrace();
//                    return ResponseEntity.badRequest().body(errors);
                    log.error("Erreur lors du traitement de la déclaration", e);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    codeErreurSystem.handleException(CodeErreurSystem.INVALID_DECLARATION, (Exception) e,user,declaration, mailService,templateEngine);
                    return ResponseEntity.badRequest().body(CodeErreurSystem.buildErrorMessage(CodeErreurSystem.INVALID_DECLARATION));

                }
            }

        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getMessage());
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(errors);
        }
    }

    private HashMap<String, Object> createErrorResponse(String message) {
        HashMap<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", "400");
        errorResponse.put("message", message);
        return errorResponse;
    }

    private boolean isValidNumericString(String value) {
        return value != null && value.matches("\\d+");
    }


    @GetMapping("/listDeclarations/{numeroUnique}")
    Holder<DERNDNSEMPLOYEURSERVICEV2> getListeDeclarations(@PathVariable String numeroUnique) throws JAXBException {
        return listeDeclarationsServiceV2.getListeDeclarations(numeroUnique);
    }

    @GetMapping("/listDeclarations/getFactureObject/{numfac}")
    Holder<CMGETFACTURE> getFactureObject(@PathVariable String numfac) throws JAXBException {
        return listeDeclarationsServiceV2.getFacture(numfac);
    }

    @GetMapping("/listDeclarations/getFacture/{numfac}")
    ResponseEntity<Object> getFacture(@PathVariable String numfac) throws Exception {
        Holder<CMGETFACTURE> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        result = listeDeclarationsServiceV2.getFacture(numfac);
        if (result.value != null) {
            stringUrl = result.value.getOutput().getUrl();
            String[] tab = stringUrl.substring(6).split("/");
            fileName = tab[tab.length - 1];
            if (StringUtils.isEmpty(stringUrl))
                throw new Exception("Aucune facture pour cette declaration!");
            URL url = new URL(stringUrl);
            InputStream is = url.openStream();
            byte[] data = IOUtils.toByteArray(is);
            ByteArrayResource resource = new ByteArrayResource(data);
            MediaType mediaType = MediaType.APPLICATION_PDF;
            return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                // Content-Type
                .contentType(mediaType) //
                // Content-Length
                .contentLength(data.length) //
                .body(resource);
        }
        return null;
    }

    @GetMapping("/listDeclarations/{numeroUnique}/{idDNS}")
    DERNDNSEMPLOYEURSERVICEV2.Output getListeDeclarations(@PathVariable String numeroUnique, @PathVariable String idDNS) throws JAXBException {
        Holder<DERNDNSEMPLOYEURSERVICEV2> declarations = new Holder<DERNDNSEMPLOYEURSERVICEV2>();
        declarations = listeDeclarationsServiceV2.getListeDeclarations(numeroUnique);
        return declarations.value.getOutput().stream().filter(output -> output.getNumeroFacture().equalsIgnoreCase(idDNS)).findFirst().orElse(null);
    }

    // For formatting response
    public static List<MyMap> parameters(Object obj) {
        Map<String, Object> map = new HashMap<>();
        List<MyMap> myMaps = new ArrayList<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                myMaps.add(new MyMap(field.getName(), ((JAXBElement<?>) field.get(obj)).getValue()));
            } catch (Exception ignored) {
            }
        }
        return myMaps;
    }

    @GetMapping("/tauxEtPlafonds")
    Object getTauxEtPlafond() throws JAXBException {
        Holder<CMGETCONSTANTS> tauxPlafond = new Holder<CMGETCONSTANTS>();
        tauxPlafond = getTauxPlafondService.getTauxPlafonds();
        List<MyMap> map = new ArrayList<>();
        if (tauxPlafond.value == null) return null;
        CMGETCONSTANTS.Output result = tauxPlafond.value.getOutput();
        try {
            map = parameters(result);
            return map;
        } catch (Exception e) {
            return tauxPlafond;
        }
    }


    String getAgence(String numeroUnique, String institution) {
        if (StringUtils.isEmpty(numeroUnique) || StringUtils.isEmpty(institution)) {
            return null;
        }
        Optional<ImmatriculationRecuperee> old = immatriculationRecupereeRepository.findByNumeroUnique(numeroUnique);
        if (old.isPresent()) {
            switch (institution) {
                case "ipres":
                    return old.get().getAgenceIpres();
                case "css":
                    return old.get().getAgenceCss();
                default:
                    return null;
            }
        } else {
            try {
                Holder<AGENCESEMPLOYEURSERVICE> holder = agencesRattachementServicev2.getAgencesRattachement(numeroUnique);
                if (holder == null) {
                    return null;
                }
                return holder
                    .value.getOutput().stream()
                    .filter(output -> (!StringUtils.isEmpty(output.getInstitution()) && output.getInstitution().equalsIgnoreCase(institution))).findAny().map(AGENCESEMPLOYEURSERVICE.Output::getCodeAgence).orElse(null);
            } catch (Exception e) {
                return null;
            }
        }
    }

    static class MyMap {
        private String name;
        private Object value;

        public MyMap(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public MyMap() {

        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    // end for formatting response

    @PostMapping("/getDeclarationManquante")
    public Holder<CMINFORMATIONMANQUEDNSIPRES> getDeclarationManquante(@RequestBody CMINFORMATIONMANQUEDNSIPRES decmanquante) throws CmPresDnsFault, JAXBException, DatatypeConfigurationException {
        log.debug("REST request to get PreDNS: {}", ENTITY_NAME);
        return declarationManquanteService.getDeclarationManquantes(decmanquante);
    }

    @PutMapping("/declaration/{id}")
    public ResponseEntity<?> updateDeclaration(
        @PathVariable Long id,
        @RequestBody DeclarationModelDTO preDnsInput) {
        HashMap<String, Object> errors = new HashMap<>();
        try {
            Declaration declaration = declarationService.updateDeclaration(id, preDnsInput);

            //  Récupérer l'utilisateur
            Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
            String username = loggedusername.get();
            User user = userRepository.findByLogin(username);

            // Mettre à jour les factures associées
            List<FactureDNS> facturesMaj = null;
            facturesMaj = declarationService.updateFactures(declaration, preDnsInput, user, mailService, templateEngine);
            String detail = "Modification de la déclaration";
            journalDeclarationService.journaliserAction(
                id,
                detail,
                "REGULATION_DECLARATION"
            );

            Map<String, Object> response = new HashMap<>();
            response.put("declaration", declaration);
            response.put("factures", facturesMaj);
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getMessage());
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @PutMapping("/check-dns-regularisation")
    public Object checkUnicityRegularisation(@RequestBody DeclarationModelDTO preDnsInput) {

        ApiResponse<String> response = declarationService.validateSalariesRegularisation(preDnsInput);
        if(response != null){
            return response;
        }

        ApiResponse<Object> uniqueCheck = declarationService.checkUnicity(preDnsInput);
        if (uniqueCheck != null) {
            return uniqueCheck;
        }

        return ApiResponse.success("Le fichier est conforme !");
    }

}
