package com.secusociale.portail.web.rest.immatriculation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Employeur;
import com.secusociale.portail.domain.NouvelleImmatriculation;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import com.secusociale.portail.domain.enumeration.TypeDocument;
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.EmployeurService;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.NouvelleImmatriculationService;
import com.secusociale.portail.service.consultation.ConsultationService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.NouvelleImmatriculationDTO;
import com.secusociale.portail.service.dto.custom.*;
import com.secusociale.portail.service.immatriculation.*;
import com.secusociale.portail.service.mapper.NouvelleImmatriculationMapper;
import com.secusociale.portail.service.soap.CmFindEmployeurByTypeId.CmFindEmployeurByTypeId;
import com.secusociale.portail.service.soap.agencesRattachement.AGENCESEMPLOYEURSERVICE;
import com.secusociale.portail.service.soap.agencesRattachement.AGENCESEMPLOYEURSERVICEFault;
import com.secusociale.portail.service.soap.certificatImmatriculation.CmGetCertificatImmatriculation;
import com.secusociale.portail.service.soap.certificatImmatriculation.CmGetCertificatImmatriculationFault;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUNDFault;
import com.secusociale.portail.service.soap.domestique.InboundDomFrm;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILS;
import com.secusociale.portail.service.soap.ficheEmployeur.CMFICHEPORTXAI;
import com.secusociale.portail.service.soap.identifiantsEmployeurs.IDsEMPLOYEURSERVICE;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUND;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAI;
import com.secusociale.portail.service.soap.infosEmployeur.CMINFOSEMPLOYEUR;
import com.secusociale.portail.service.soap.infosSalaries.CMGETPERSONSLINKTOEMPLOYER;
import com.secusociale.portail.service.soap.listeEmployes.EMPLOYESLISTSERVICE;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUND;
import com.secusociale.portail.service.soap.numeroCompteEmployeur.CMPerAccountById;
import com.secusociale.portail.service.soap.recepisseDepot.GETRECEPISSEDEPOTURL;
import com.secusociale.portail.service.soap.soldeEmployeur.XAIGETSOLDE;
import com.secusociale.portail.service.soap.statutDossierImmatriculation.CmGetStatusDossierImmatriculation;
import com.secusociale.portail.service.soap.statutDossierImmatriculation.CmGetStatusDossierImmatriculationFault;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;

@SuppressWarnings("SpringConfigurationProxyMethods")
@RestController
@RequestMapping("/api")
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class ImmatPortailResource {

    private final Logger log = LoggerFactory.getLogger(ImmatPortailResource.class);

    private final ImmatPortailService immatPortailService;
    private final NouvelleImmatriculationService nouvelleImmatriculationService;
    private final MailService mailService;
    private final ServerCheckRepository serverCheckRepository;
    private final UserRepository userRepository;
    private final StatutDossierImmatriculationService statutDossierImmatriculationService;
    private final CertificatImmatriculationService certificatImmatriculationService;
    private final AgencesRattachementService agencesRattachementService;
    private final IdentifiantsEmployeurService identifiantsEmployeurService;
    private final ListeEmployesService listeEmployesService;
    private final SoldeEmployeurService soldeEmployeurService;
    private final FindEmployeurByIdService findEmployeurByIdService;
    private final RecepisseDepotService recepisseDepotService;
    private final InfosCompteEmployeurService infosCompteEmployeurService;
    private final InfoSalariesService infosSalarieService;
    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;
    private final NouvelleImmatriculationMapper nouvelleImmatriculationMapper;
    private final EmployeurService employeurService;
    private final NumeroCompteEmployeurService numeroCompteService;
    private final FicheEmployeurService ficheEmployeurService;
    private final ConsultationService consultationService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @PostMapping("/immatPortail")
    public ResponseEntity<Map<String, Object>> createImmatriculationPortail(@RequestBody ImmatriculationDTO immatriculation) {
        boolean isSubmited = immatriculation.getIsSubmit();
        Long id = immatriculation.getId() != null ? immatriculation.getId() : 0;
        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> results = new HashMap<>();
        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(1, "EN_ATTENTE");
        statuses.put(2, "PAS_ENCORE_SOUMISE");
        statuses.put(3, "IESV");
        statuses.put(4, "SOUMISE_LOCALE");

        Holder<IMMATRICULATIONINBOUND> immatriculationInbound = new Holder<IMMATRICULATIONINBOUND>();
        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setType("IMMATPORTAIL");
        nouvelleImmatriculation.setCreatedAt(Instant.now());
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        IMMATRICULATIONINBOUND.Input input = immatriculation.getInput();
        if (input != null) {
            if (input.getEmployerQuery() != null) {
                nouvelleImmatriculation.setRaisonSociale(input.getEmployerQuery().getEmployerName());
                nouvelleImmatriculation.setNinea(input.getEmployerQuery().getNineaNumber());
                nouvelleImmatriculation.setRegistreCommerce(input.getEmployerQuery().getTradeRegisterNumber());
            }
        }

        try {
            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/immatPortail"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("User Required")
                    .build();
            }
            String username = loggedusername.get();
            Long userId = userRepository.findByLogin(username).getId();
            nouvelleImmatriculation.setUser(userId);
            nouvelleImmatriculation.setStatusdoc(isSubmited ? statuses.get(1) : statuses.get(2));
            nouvelleImmatriculation.setIsSubmit(isSubmited);
            String ninea = null;
            try {
                ninea = immatriculation.getInput().getEmployerQuery().getNineaNumber();
            } catch (Exception ignored) {
                ninea = RandomStringUtils.randomAlphanumeric(8);
            }
            nouvelleImmatriculation.setNinea(ninea);
            ObjectMapper objectMapper = new ObjectMapper();

            if (isSubmited) {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                }
                List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, true);
                if (list.size() > 0) {
                    if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                        errors.put("code", "400");
                        errors.put("message", "Vous avez déjà une immatriculation soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                        return ResponseEntity.ok().body(errors);
                    }
                }

                String jsonString = objectMapper.writeValueAsString((IMMATRICULATIONINBOUND) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);

//                immatriculationInbound = immatPortailService.createImmatriculationPortail((IMMATRICULATIONINBOUND) immatriculation);
//                String imback = objectMapper.writeValueAsString(immatriculationInbound.value);
//                nouvelleImmatriculation.setPayload(imback);
//
//                IMMATRICULATIONINBOUND.Output output = immatriculationInbound.value.getOutput();
//
//                if (output != null) {
//                    nouvelleImmatriculation.setAgenceCss(output.getAgenceCss());
//                    nouvelleImmatriculation.setAgenceIpres(output.getAgenceIpres());
//                    nouvelleImmatriculation.setTauxAt(output.getTauxAt());
//                    nouvelleImmatriculation.setSectorCss(output.getSectorCss());
//                    nouvelleImmatriculation.setSectorIpres(output.getSectorIpres());
//                    nouvelleImmatriculation.setZoneCss(output.getZoneCss());
//                    nouvelleImmatriculation.setZoneIpres(output.getZoneIpres());
//                    nouvelleImmatriculation.setNumdoc(output.getProcessFlowId());
//                    nouvelleImmatriculation.setEmployerRegistrationFormId(output.getEmployerRegistrationFormId());
//                    nouvelleImmatriculation.setEmployeeRegistrationFormId(output.getEmployeeRegistrationFormId());
//                }
//
//                else {
//                    nouvelleImmatriculation.setStatusdoc(statuses.get(4));
//                }
                NouvelleImmatriculationDTO nouvelleImmatriculationDTO = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
                ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
                if(response != null){
                    errors.put("code", "400");
                    errors.put("message",response.getBody());
                    return ResponseEntity.ok().body(errors);
                }
                NouvelleImmatriculation nouvelleImmatriculationSaved = nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
                if(nouvelleImmatriculationSaved.getId() != null){
                    NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved=nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationSaved);
                    nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
                    nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
                }
            } else {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                } else {
                    List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, false);
                    if (list.size() > 1) {
                        if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                            errors.put("code", "400");
                            errors.put("message", "Vous avez déjà une immatriculation pas encore soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                            return ResponseEntity.ok().body(errors);
                        }
                    }
                }
                String jsonString = objectMapper.writeValueAsString((IMMATRICULATIONINBOUND) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
                nouvelleImmatriculationRepository.findByNineaAndIsSubmit(ninea, false).stream().findFirst().map(NouvelleImmatriculation::getId).ifPresent(nouvelleImmatriculation::setId);
                nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
            }


        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        try {
            results.put("code", "200");
            results.put("id", nouvelleImmatriculation.getId());
            results.put("data", immatriculationInbound.value != null ? immatriculationInbound.value : nouvelleImmatriculation.getParsedPayload());
            results.put("statusdoc", nouvelleImmatriculation.getStatusdoc());
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "une erreur est survenue, réessayez plus tard!");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        return ResponseEntity.ok().body(results);

    }

    @PostMapping("/maintient-affiliation")
    @Transactional
    public ResponseEntity<Map<String, Object>> createMaintientAffiliation(@RequestBody MaintientAffiliationDTO immatriculation) throws URISyntaxException, JAXBException {
        boolean isSubmited = immatriculation.getIsSubmit();
        Long id = immatriculation.getId() != null ? immatriculation.getId() : 0;
        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> results = new HashMap<>();
        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(1, "EN_ATTENTE");
        statuses.put(2, "PAS_ENCORE_SOUMISE");
        statuses.put(3, "IESV");
        statuses.put(4, "SOUMISE_LOCALE");
        Holder<MAINTAFFINBOUND> immatriculationInbound = new Holder<MAINTAFFINBOUND>();

        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setType("MAINTIENT-AFFILIATION");
        nouvelleImmatriculation.setCreatedAt(Instant.now());
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

//        MAINTAFFINBOUND.Input input = immatriculation.getInput();
//        if (input != null) {
//
//        }

        try {
            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/immatPortail"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("User Required")
                    .build();
            }

            String username = loggedusername.get();
            User byLogin = userRepository.findByLogin(username);

//            if(byLogin.getTypeCompte().equalsIgnoreCase(Constants.EMPLOYEUR)){
//                throw Problem.builder().withDetail("Un compte employeur ne peut avoir plus d'une imatriculation")
//                    .withType(URI.create("/api/immatPortail"))
//                    .withStatus(Status.BAD_REQUEST)
//                    .withTitle("User Required")
//                    .build();
//            }

            Long userId = byLogin.getId();

            nouvelleImmatriculation.setUser(userId);
            nouvelleImmatriculation.setStatusdoc(isSubmited ? statuses.get(1) : statuses.get(2));
            nouvelleImmatriculation.setIsSubmit(isSubmited);
            String ninea = "";

            if (immatriculation.getInput() != null && immatriculation.getInput().getInformationsGenerales() != null) {
                if (StringUtils.isNotEmpty(immatriculation.getInput().getInformationsGenerales().getNin())) {
                    ninea = immatriculation.getInput().getInformationsGenerales().getNin();
                } else if (StringUtils.isNotEmpty(immatriculation.getInput().getInformationsGenerales().getNincedeao())) {
                    ninea = immatriculation.getInput().getInformationsGenerales().getNincedeao();
                } else if (StringUtils.isNotEmpty(immatriculation.getInput().getInformationsGenerales().getNumIdNationale())) {
                    ninea = immatriculation.getInput().getInformationsGenerales().getNumIdNationale();
                }
            }
            if (StringUtils.isEmpty(ninea)) {
                ninea = RandomStringUtils.randomAlphanumeric(8);
            }

            nouvelleImmatriculation.setNinea(ninea);
            ObjectMapper objectMapper = new ObjectMapper();

            if (isSubmited) {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                }
                List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, true);
                if (list.size() > 0) {
                    if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                        errors.put("code", "400");
                        errors.put("message", "Vous avez déjà une immatriculation soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                        return ResponseEntity.ok().body(errors);
                    }
                }

                String jsonString = objectMapper.writeValueAsString((MAINTAFFINBOUND) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);

//                try {
//                    immatriculationInbound = immatPortailService.createImmatriculationMaintienAffiliation((MAINTAFFINBOUND) immatriculation);
//                } catch (Exception e) {
//
//                }
//                nouvelleImmatriculation.setNumdoc(immatriculationInbound.value.getOutput() != null ? immatriculationInbound.value.getOutput().getProcessFlowId() : null);
//                String imback = objectMapper.writeValueAsString(immatriculationInbound.value);
//                nouvelleImmatriculation.setPayload(imback);
//
//
//                MAINTAFFINBOUND.Output output = immatriculationInbound.value.getOutput();
//
//                if (output != null) {
//                    nouvelleImmatriculation.setNumdoc(output.getProcessFlowId());
//                    nouvelleImmatriculation.setEmployerRegistrationFormId(output.getRegistrationFormId());
//                }
                NouvelleImmatriculationDTO nouvelleImmatriculationDTO = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
                ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
                if(response != null){
                    errors.put("code", "400");
                    errors.put("message",response.getBody());
                    return ResponseEntity.ok().body(errors);
                }
                NouvelleImmatriculation nouvelleImmatriculationSaved = nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
                if(nouvelleImmatriculationSaved.getId() != null){
                    NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved=nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationSaved);
                    nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
                    nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
                }
            } else {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                } else {
                    List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, false);
                    if (list.size() > 0) {
                        if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                            errors.put("code", "400");
                            errors.put("message", "Vous avez déjà une immatriculation pas encore soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                            return ResponseEntity.ok().body(errors);
                        }
                    }
                }
                String jsonString = objectMapper.writeValueAsString((MAINTAFFINBOUND) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
                nouvelleImmatriculationRepository.findByNineaAndIsSubmit(ninea, false).stream().findFirst().map(NouvelleImmatriculation::getId).ifPresent(nouvelleImmatriculation::setId);
                nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
            }

            if (isSubmited) {
                nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
            }

        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        try {
            results.put("code", "200");
            results.put("id", nouvelleImmatriculation.getId());
            results.put("data", immatriculationInbound.value != null ? immatriculationInbound.value : nouvelleImmatriculation.getParsedPayload());
            results.put("statusdoc", nouvelleImmatriculation.getStatusdoc());
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getLocalizedMessage() + "/ " + e.getMessage());
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        return ResponseEntity.ok().body(results);
    }

    @PostMapping("/representation-diplomatique")
    @Transactional
    public ResponseEntity<Map<String, Object>> createRepresentationDiplomatique
        (@RequestBody RepresentationDiplomatiqueDTO immatriculation) {
        boolean isSubmited = immatriculation.getIsSubmit();
        long id = immatriculation.getId();
        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> results = new HashMap<>();
        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(1, "EN_ATTENTE");
        statuses.put(2, "PAS_ENCORE_SOUMISE");
        statuses.put(3, "IESV");
        statuses.put(4, "SOUMISE_LOCALE");

        Holder<IMMATREPDIPLO> immatriculationInbound = new Holder<IMMATREPDIPLO>();
        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setType("REPRESENTATION-DIPLOMATIQUE");
        nouvelleImmatriculation.setCreatedAt(Instant.now());
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        IMMATREPDIPLO.Input input = immatriculation.getInput();

        if (input != null) {
            if (input.getEmployerQuery() != null) {
                nouvelleImmatriculation.setRaisonSociale(input.getEmployerQuery().getEmployerName());
                nouvelleImmatriculation.setNinea(input.getEmployerQuery().getNineaNumber());
            }
        }

        try {
            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/representation-diplomatique"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("User Required")
                    .build();
            }
            String username = loggedusername.get();
            Long userId = userRepository.findByLogin(username).getId();
            nouvelleImmatriculation.setUser(userId);
            nouvelleImmatriculation.setStatusdoc(isSubmited ? statuses.get(1) : statuses.get(2));
            nouvelleImmatriculation.setIsSubmit(isSubmited);
            String ninea = null;
            try {
                ninea = immatriculation.getInput().getEmployerQuery().getNineaNumber();
            } catch (Exception ignored) {

            }
            nouvelleImmatriculation.setNinea(ninea);
            ObjectMapper objectMapper = new ObjectMapper();

            if (isSubmited) {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                }
                List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, true);
                if (list.size() > 0) {
                    if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                        errors.put("code", "400");
                        errors.put("message", "Vous avez déjà une immatriculation soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                        return ResponseEntity.ok().body(errors);
                    }
                }

                String jsonString = objectMapper.writeValueAsString((IMMATREPDIPLO) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
//                immatriculationInbound = immatPortailService.createImmatriculationRepresentatnt((IMMATREPDIPLO) immatriculation);
//                nouvelleImmatriculation.setNumdoc(immatriculationInbound.value.getOutput() != null ? immatriculationInbound.value.getOutput().getProcessFlowId() : null);
//                String imback = objectMapper.writeValueAsString(immatriculationInbound.value);
//                nouvelleImmatriculation.setPayload(imback);
//
//                IMMATREPDIPLO.Output output = immatriculationInbound.value.getOutput();
//
//                if (output != null) {
//                    nouvelleImmatriculation.setNumdoc(output.getProcessFlowId());
//                    nouvelleImmatriculation.setAgenceCss(output.getAgenceCss());
//                    nouvelleImmatriculation.setAgenceIpres(output.getAgenceIpres());
//                    nouvelleImmatriculation.setTauxAt(output.getTauxAt());
//                    nouvelleImmatriculation.setSectorCss(output.getSectorCss());
//                    nouvelleImmatriculation.setSectorIpres(output.getSectorIpres());
//                    nouvelleImmatriculation.setZoneCss(output.getZoneCss());
//                    nouvelleImmatriculation.setZoneIpres(output.getZoneIpres());
//                    nouvelleImmatriculation.setEmployerRegistrationFormId(output.getEmployerRegistrationFormId());
//                    nouvelleImmatriculation.setEmployeeRegistrationFormId(output.getEmployeeRegistrationFormId());
//                }
                NouvelleImmatriculationDTO nouvelleImmatriculationDTO = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
                ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
                if(response != null){
                    errors.put("code", "400");
                    errors.put("message",response.getBody());
                    return ResponseEntity.ok().body(errors);
                }
                NouvelleImmatriculation nouvelleImmatriculationSaved = nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
                if(nouvelleImmatriculationSaved.getId() != null){
                    NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved=nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationSaved);
                    nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
                    nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
                }
            } else {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                } else {
                    List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, false);
                    if (list.size() > 0) {
                        if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                            errors.put("code", "400");
                            errors.put("message", "Vous avez déjà une immatriculation pas encore soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                            return ResponseEntity.ok().body(errors);
                        }
                    }
                }
                String jsonString = objectMapper.writeValueAsString((IMMATREPDIPLO) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
                nouvelleImmatriculationRepository.findByNineaAndIsSubmit(ninea, false).stream().findFirst().map(NouvelleImmatriculation::getId).ifPresent(nouvelleImmatriculation::setId);
                nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
            }


        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getLocalizedMessage() + "/ " + e.getMessage());
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        try {
            results.put("code", "200");
            results.put("id", nouvelleImmatriculation.getId());
            results.put("data", immatriculationInbound.value != null ? immatriculationInbound.value : nouvelleImmatriculation.getParsedPayload());
            results.put("statusdoc", nouvelleImmatriculation.getStatusdoc());
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        return ResponseEntity.ok().body(results);
    }

    @PostMapping("/publique-parapublique")
    @Transactional
    public ResponseEntity<Map<String, Object>> createPubliqueParapublique(@RequestBody PubliqueParapubliqueDTO
                                                                              immatriculation) throws URISyntaxException, JAXBException {
        // log.debug("REST request to save Immatriculation : {}", ENTITY_NAME);
        boolean isSubmited = immatriculation.getIsSubmit();
        long id = immatriculation.getId();
        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> results = new HashMap<>();
        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(1, "EN_ATTENTE");
        statuses.put(2, "PAS_ENCORE_SOUMISE");
        statuses.put(3, "IESV");
        statuses.put(4, "SOUMISE_LOCALE");

        Holder<IMMAT2INBOUND> immatriculationInbound = new Holder<IMMAT2INBOUND>();
        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setType("PUBLIQUE-PARAPUBLIQUE");
        nouvelleImmatriculation.setCreatedAt(Instant.now());
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        IMMAT2INBOUND.Input input = immatriculation.getInput();
        if (input != null) {
            if (input.getEmployerQuery() != null) {
                nouvelleImmatriculation.setRaisonSociale(input.getEmployerQuery().getEmployerName());
                nouvelleImmatriculation.setNinea(input.getEmployerQuery().getNineaNumber());
            }
        }

        try {
            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/publique-parapublique"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("User Required")
                    .build();
            }
            String username = loggedusername.get();
            Long userId = userRepository.findByLogin(username).getId();
            nouvelleImmatriculation.setUser(userId);
            nouvelleImmatriculation.setStatusdoc(isSubmited ? statuses.get(1) : statuses.get(2));
            nouvelleImmatriculation.setIsSubmit(isSubmited);
            String ninea = null;
            try {
                ninea = immatriculation.getInput().getEmployerQuery().getNineaNumber();
            } catch (Exception ignored) {
                ninea = RandomStringUtils.randomAlphanumeric(9);
            }
            nouvelleImmatriculation.setNinea(ninea);
            ObjectMapper objectMapper = new ObjectMapper();
            if (isSubmited) {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                }
                List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, true);
                if (list.size() > 0) {
                    if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                        errors.put("code", "400");
                        errors.put("message", "Vous avez déjà une immatriculation soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                        return ResponseEntity.ok().body(errors);
                    }
                }
                String jsonString = objectMapper.writeValueAsString((IMMAT2INBOUND) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
//                immatriculationInbound = immatPortailService.createImmatPublicParapublique((IMMAT2INBOUND) immatriculation);
//                nouvelleImmatriculation.setNumdoc(immatriculationInbound.value.getOutput() != null ? immatriculationInbound.value.getOutput().getProcessFlowId() : null);
//                String imback = objectMapper.writeValueAsString(immatriculationInbound.value);
//                log.info(imback);
//                nouvelleImmatriculation.setPayload(imback);
//
//                IMMAT2INBOUND.Output output = immatriculationInbound.value.getOutput();
//                if (output != null) {
//                    nouvelleImmatriculation.setNumdoc(output.getProcessFlowId());
//                    nouvelleImmatriculation.setAgenceCss(output.getAgenceCss());
//                    nouvelleImmatriculation.setAgenceIpres(output.getAgenceIpres());
//                    nouvelleImmatriculation.setTauxAt(output.getTauxAt());
//                    nouvelleImmatriculation.setSectorCss(output.getSectorCss());
//                    nouvelleImmatriculation.setSectorIpres(output.getSectorIpres());
//                    nouvelleImmatriculation.setZoneCss(output.getZoneCss());
//                    nouvelleImmatriculation.setZoneIpres(output.getZoneIpres());
//                    nouvelleImmatriculation.setEmployerRegistrationFormId(output.getEmployerRegistrationFormId());
//                    nouvelleImmatriculation.setEmployeeRegistrationFormId(output.getEmployeeRegistrationFormId());
//                }
                NouvelleImmatriculationDTO nouvelleImmatriculationDTO = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
                ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
                if(response != null){
                    errors.put("code", "400");
                    errors.put("message",response.getBody());
                    return ResponseEntity.ok().body(errors);
                }
                NouvelleImmatriculation nouvelleImmatriculationSaved = nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
                if(nouvelleImmatriculationSaved.getId() != null){
                    NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved=nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationSaved);
                    nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
                    nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
                }
            } else {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                } else {
                    List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, false);
                    if (list.size() > 0) {
                        if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                            errors.put("code", "400");
                            errors.put("message", "Vous avez déjà une immatriculation pas encore soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                            return ResponseEntity.ok().body(errors);
                        }
                    }
                }
                String jsonString = objectMapper.writeValueAsString((IMMAT2INBOUND) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
                log.info(jsonString);
                nouvelleImmatriculationRepository.findByNineaAndIsSubmit(ninea, false).stream().findFirst().map(NouvelleImmatriculation::getId).ifPresent(nouvelleImmatriculation::setId);
                nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
            }

        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue lors de l'opération");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        try {
            results.put("code", "200");
            results.put("id", nouvelleImmatriculation.getId());
            results.put("data", immatriculationInbound.value != null ? immatriculationInbound.value : nouvelleImmatriculation.getParsedPayload());
            results.put("statusdoc", nouvelleImmatriculation.getStatusdoc());
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue lors de l'opération");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        return ResponseEntity.ok().body(results);

    }

    @PostMapping("/domestique")
    @Transactional
    public ResponseEntity<Map<String, Object>> createImmatriculationPortail(@RequestBody DomestiqueDTO immatriculation) throws URISyntaxException, IMMATRICULATIONINBOUNDFault, JAXBException {
        boolean isSubmited = immatriculation.getIsSubmit();
        long id = immatriculation.getId();
        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> results = new HashMap<>();
        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(1, "EN_ATTENTE");
        statuses.put(2, "PAS_ENCORE_SOUMISE");
        statuses.put(3, "IESV");
        statuses.put(4, "SOUMISE_LOCALE");
        Holder<InboundDomFrm> immatriculationInbound = new Holder<InboundDomFrm>();

        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setType("DOMESTIQUE");
        nouvelleImmatriculation.setCreatedAt(Instant.now());
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        try {
            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/domestique"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("User Required")
                    .build();
            }
            String username = loggedusername.get();
            Long userId = null;
            userId = userRepository.findByLogin(username).getId();
            nouvelleImmatriculation.setUser(userId);
            nouvelleImmatriculation.setStatusdoc(isSubmited ? statuses.get(1) : statuses.get(2));
            nouvelleImmatriculation.setIsSubmit(isSubmited);
            String ninea = "";
            String immatriculationStr = (new ObjectMapper()).writeValueAsString(immatriculation);
            System.out.println(immatriculationStr);
            if (immatriculation.getInput() != null && immatriculation.getInput().getDomesticRegistrationForm() != null) {
                System.out.println("loggggged");
                if (!StringUtils.isEmpty(immatriculation.getInput().getDomesticRegistrationForm().getIdNumber()))
                    ninea = immatriculation.getInput().getDomesticRegistrationForm().getIdNumber();
                else if (!StringUtils.isEmpty(immatriculation.getInput().getDomesticRegistrationForm().getNin()))
                    ninea = immatriculation.getInput().getDomesticRegistrationForm().getNin();
                else if (!StringUtils.isEmpty(immatriculation.getInput().getDomesticRegistrationForm().getNinCedeao()))
                    ninea = immatriculation.getInput().getDomesticRegistrationForm().getNinCedeao();

                System.out.println("ninea = " + ninea);
            }
            if (StringUtils.isEmpty(ninea)) {
                System.out.println("ninea vide!");
                ninea = RandomStringUtils.randomAlphanumeric(8);
            }

            nouvelleImmatriculation.setNinea(ninea);

            ObjectMapper objectMapper = new ObjectMapper();

            if (isSubmited) {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                }
                List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, true);
                if (list.size() > 0) {
                    if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                        errors.put("code", "400");
                        errors.put("message", "Vous avez déjà une immatriculation soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                        return ResponseEntity.ok().body(errors);
                    }
                }

                String jsonString = objectMapper.writeValueAsString((InboundDomFrm) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
//                immatriculationInbound = immatPortailService.createImmatDomestique((InboundDomFrm) immatriculation);
//                nouvelleImmatriculation.setNumdoc(immatriculationInbound.value.getOutput() != null ? immatriculationInbound.value.getOutput().getFolderId() : null);
//                String imback = objectMapper.writeValueAsString(immatriculationInbound.value);
//                nouvelleImmatriculation.setPayload(imback);
//
//                InboundDomFrm.Output output = immatriculationInbound.value.getOutput();
//                if (output != null) {
//                    nouvelleImmatriculation.setNumdoc(output.getFolderId());
//                    nouvelleImmatriculation.setAgenceCss(output.getAgenceCss());
//                    nouvelleImmatriculation.setAgenceIpres(output.getAgenceIpres());
//                    nouvelleImmatriculation.setTauxAt(output.getTauxAt());
//                    nouvelleImmatriculation.setSectorCss(output.getSectorCss());
//                    nouvelleImmatriculation.setSectorIpres(output.getSectorIpres());
//                    nouvelleImmatriculation.setZoneCss(output.getZoneCss());
//                    nouvelleImmatriculation.setZoneIpres(output.getZoneIpres());
//                    nouvelleImmatriculation.setEmployerRegistrationFormId(output.getRegistrationFormId());
//                    nouvelleImmatriculation.setEmployeeRegistrationFormId(output.getRegistrationFormId());
//                }
                NouvelleImmatriculationDTO nouvelleImmatriculationDTO = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
                ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
                if(response != null){
                    errors.put("code", "400");
                    errors.put("message",response.getBody());
                    return ResponseEntity.ok().body(errors);
                }
                NouvelleImmatriculation nouvelleImmatriculationSaved = nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
                if(nouvelleImmatriculationSaved.getId() != null){
                    NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved=nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationSaved);
                    nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
                    nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
                }
            } else {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                } else {
                    List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, false);
                    //if (StringUtils.isNotEmpty(nouvelleImmatriculation.getNinea())) {
                    if (list.size() > 0) {
                        //if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> nouvelleImmatriculation.getNinea().equals(s))) {
                        errors.put("code", "400");
                        errors.put("message", "Vous avez déjà une immatriculation pas encore soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                        return ResponseEntity.ok().body(errors);
                        //}
                    }
                    // }
                }
                String jsonString = objectMapper.writeValueAsString((InboundDomFrm) immatriculation);
                nouvelleImmatriculation.setPayload(jsonString);
                nouvelleImmatriculation.setNinea(ninea);
                nouvelleImmatriculationRepository.findByNineaAndIsSubmit(ninea, false).stream().findFirst().map(NouvelleImmatriculation::getId).ifPresent(nouvelleImmatriculation::setId);
                nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
            }
            if (isSubmited) {
                nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
            }
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue lors de l'opération");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        try {
            results.put("code", "200");
            results.put("id", nouvelleImmatriculation.getId());
            results.put("data", immatriculationInbound.value != null ? immatriculationInbound.value : nouvelleImmatriculation.getParsedPayload());
            results.put("statusdoc", nouvelleImmatriculation.getStatusdoc());
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue lors de l'opération");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        return ResponseEntity.ok().body(results);

    }

    @PostMapping("/independant")
    @Transactional
    public ResponseEntity<Map<String, Object>> createIndependant(@RequestBody IndependantDTO independant) throws JAXBException, IOException {
        boolean isSubmited = independant.getIsSubmit();
        long id = independant.getId();
        HashMap<String, Object> errors = new HashMap<>();
        HashMap<String, Object> results = new HashMap<>();
        HashMap<Integer, String> statuses = new HashMap<>();
        statuses.put(1, "EN_ATTENTE");
        statuses.put(2, "PAS_ENCORE_SOUMISE");
        statuses.put(3, "IESV");
        statuses.put(4, "SOUMISE_LOCALE");

        Holder<CMCrtIndForXAI> immatIndependant = new Holder<CMCrtIndForXAI>();
        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setType("INDEPENDANT");
        nouvelleImmatriculation.setCreatedAt(Instant.now());
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();


        try {
            if (!loggedusername.isPresent()) {
                throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                    .withType(URI.create("/api/independant"))
                    .withStatus(Status.BAD_REQUEST)
                    .withTitle("User Required")
                    .build();
            }
            String username = loggedusername.get();
            Long userId = userRepository.findByLogin(username).getId();

            nouvelleImmatriculation.setUser(userId);
            nouvelleImmatriculation.setStatusdoc(isSubmited ? statuses.get(1) : statuses.get(2));
            nouvelleImmatriculation.setIsSubmit(isSubmited);
            String ninea = null;
            try {
                ninea = independant.getInput().getCarteConsulaire();
            } catch (Exception ignored) {
                ninea = RandomStringUtils.randomAlphanumeric(9);
            }
            nouvelleImmatriculation.setNinea(ninea);
            ObjectMapper objectMapper = new ObjectMapper();

            if (isSubmited) {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                }
                List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, true);

                String jsonString = objectMapper.writeValueAsString((CMCrtIndForXAI) independant);
                nouvelleImmatriculation.setPayload(jsonString);
                NouvelleImmatriculationDTO nouvelleImmatriculationDTO = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
                ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
                if(response != null){
                    errors.put("code", "400");
                    errors.put("message",response.getBody());
                    return ResponseEntity.ok().body(errors);
                }
                NouvelleImmatriculation nouvelleImmatriculationSaved = nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
                if(nouvelleImmatriculationSaved.getId() != null){
                    NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved=nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationSaved);
                    nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
                    nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
                }
            } else {
                Optional<NouvelleImmatriculation> imm = nouvelleImmatriculationRepository.findById(id);
                if (id != 0) {
                    if (imm.isPresent()) {
                        nouvelleImmatriculation.setId(id);
                    }
                } else {
                    List<NouvelleImmatriculation> list = nouvelleImmatriculationRepository.findAllByUserAndIsSubmit(userId, false);
                    if (list.size() > 0) {
                        if (list.stream().map(NouvelleImmatriculation::getNinea).anyMatch(s -> StringUtils.isNotEmpty(s) && s.equals(nouvelleImmatriculation.getNinea()))) {
                            errors.put("code", "400");
                            errors.put("message", "Vous avez déjà une immatriculation pas encore soumise avec cette ninea: " + nouvelleImmatriculation.getNinea());
                            return ResponseEntity.ok().body(errors);
                        }
                    }
                }
                String jsonString = objectMapper.writeValueAsString((CMCrtIndForXAI) independant);
                nouvelleImmatriculation.setPayload(jsonString);
                nouvelleImmatriculationRepository.findByNineaAndIsSubmit(ninea, false).stream().findFirst().map(NouvelleImmatriculation::getId).ifPresent(nouvelleImmatriculation::setId);
                nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
            }
            if (isSubmited) {
                nouvelleImmatriculationRepository.saveAndFlush(nouvelleImmatriculation);
            }

        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue lors de l'opération");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        try {
            results.put("code", "200");
            results.put("id", nouvelleImmatriculation.getId());
            results.put("data", immatIndependant.value != null ? immatIndependant.value : nouvelleImmatriculation.getParsedPayload());
            results.put("statusdoc", nouvelleImmatriculation.getStatusdoc());
        } catch (Exception e) {
            errors.put("code", "400");
            errors.put("message", "Une erreur est survenue lors de l'opération");
            errors.put("trace", e.toString());
            e.printStackTrace();
            return ResponseEntity.ok().body(errors);
        }
        return ResponseEntity.ok().body(results);
    }

    @PostMapping("/immatPortail/uploadFile")
    public Holder<IMMATRICULATIONINBOUND> createImmatriculationUploadFilePortail(@RequestBody IMMATRICULATIONINBOUND immatriculation) throws IOException {
        return immatPortailService.createImmatriculationUploadFilePortail(immatriculation);
    }

    @GetMapping("/statutDossierImmatriculation/{idDossier}")
    Holder<CmGetStatusDossierImmatriculation> getStatutDossierImmatriculation(@PathVariable String idDossier) throws
        JAXBException {

        return statutDossierImmatriculationService.getStatutDossierImmatriculation(idDossier);
    }

    @GetMapping("/immatriculations/locales/sync-status/{id}")
    ResponseEntity<NouvelleImmatriculation> syncStatus(@PathVariable Long id) {
        Optional<NouvelleImmatriculation> optional = nouvelleImmatriculationRepository.findById(id);
        if (!optional.isPresent()) {
            throw Problem.builder().withDetail("Immatriculation introuvable sur le système").withTitle("Immatriculation").build();
        }
        return ResponseEntity.ok(nouvelleImmatriculationService.checkAndUpdateMyStatus(optional.get()));
    }

    @GetMapping("/immatriculations/locales/sync-all-status")
    ResponseEntity<Object> syncAllStatuses(Pageable pageable) {
        return ResponseEntity.ok(nouvelleImmatriculationService.checkAndUpdateAllStatus(nouvelleImmatriculationRepository.findAll(pageable).toList()));
    }

    @GetMapping("/immatriculations/locales/get-numero-unique/{id}")
    ResponseEntity<NouvelleImmatriculation> getUnikNumber(@PathVariable Long id) {
        Optional<NouvelleImmatriculation> optional = nouvelleImmatriculationRepository.findById(id);
        if (!optional.isPresent()) {
            throw Problem.builder().withDetail("Immatriculation introuvable sur le système").withTitle("Immatriculation").build();
        }
        return ResponseEntity.ok(nouvelleImmatriculationService.getMyUniqueNumber(optional.get()));
    }

    @GetMapping("/checkExistenceEmployeur/{typeIdentifiant}/{numeroIdentifiant}")
    Holder<CmFindEmployeurByTypeId> getExistenceEmployeur(@PathVariable String
                                                              typeIdentifiant, @PathVariable String numeroIdentifiant) throws
        JAXBException, CmGetStatusDossierImmatriculationFault {
        Holder<CmFindEmployeurByTypeId> cmCheckExistenceEmployeur = new Holder<CmFindEmployeurByTypeId>();
        cmCheckExistenceEmployeur = findEmployeurByIdService.findEmployeurByTypeId(typeIdentifiant, numeroIdentifiant);
        return cmCheckExistenceEmployeur;
    }

    @GetMapping("/checkEmployeur/{typeIdentifiant}/{numeroIdentifiant}")
    ResponseEntity<HashMap<String, Object>> checkEmployeur(@PathVariable String
                                                               typeIdentifiant, @PathVariable String numeroIdentifiant) throws
        JAXBException, CmGetStatusDossierImmatriculationFault {
        HashMap<String, Object> result = new HashMap<>();
        Optional<Employeur> mayExist = employeurService.checkEmployeur(typeIdentifiant, numeroIdentifiant);
        result.put("exist", mayExist.isPresent());
        result.put("data", mayExist.orElse(null));
        return ResponseEntity.ok(result);
    }


    @GetMapping("/recepisseDepot/{idDossier}")
    Holder<GETRECEPISSEDEPOTURL> getRecepisseDepot(@PathVariable String idDossier) {

        Holder<GETRECEPISSEDEPOTURL> recepisse = new Holder<GETRECEPISSEDEPOTURL>();
        recepisse = recepisseDepotService.getRecepisseDepotUrl(idDossier);
        return recepisse;

    }

    @GetMapping("/infosCompteEmployeur/{idDossier}")
    Holder<CMINFOSEMPLOYEUR> getinfosCompteEmployeur(@PathVariable String idDossier) throws JAXBException {

        Holder<CMINFOSEMPLOYEUR> infosCompte = new Holder<CMINFOSEMPLOYEUR>();
        infosCompte = infosCompteEmployeurService.getInfosCompteEmployeur(idDossier);
        return infosCompte;
    }


    @GetMapping("/salaries/{numeroEmployeur}")
    Holder<CMGETPERSONSLINKTOEMPLOYER> getinfosSalaries(@PathVariable String numeroEmployeur) throws JAXBException {

        Holder<CMGETPERSONSLINKTOEMPLOYER> infosalaries = new Holder<CMGETPERSONSLINKTOEMPLOYER>();
        infosalaries = infosSalarieService.getInfosalarie(numeroEmployeur);
        return infosalaries;

    }

    @GetMapping("/certificatImmatriculation/{idDossier}")
    Holder<CmGetCertificatImmatriculation> getCertificatImmatriculation(@PathVariable String idDossier) throws
        JAXBException, CmGetCertificatImmatriculationFault {
        Holder<CmGetCertificatImmatriculation> certificatImmatriculation = new Holder<CmGetCertificatImmatriculation>();
        certificatImmatriculation = certificatImmatriculationService.getCertificatImmatriculation(idDossier);
        return certificatImmatriculation;

    }

    @GetMapping("/certifactFile/{idDossier}")
    ResponseEntity<Object> getCertificatFile(@PathVariable String idDossier) throws Exception {

        Holder<CmGetCertificatImmatriculation> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        try {
            result = certificatImmatriculationService.getCertificatImmatriculation(idDossier);
            if (result.value != null) {
                stringUrl = result.value.getOutput().getUrl();
                String[] tab = stringUrl.substring(6).split("/");
                fileName = tab[tab.length - 1];
                if (StringUtils.isEmpty(stringUrl))
                    throw new Exception("Aucun certificat");
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
        } catch (Exception e) {
            throw new Exception(Problem.builder().withTitle("Erreur lors du telechargement du recu").with("stringUrl", stringUrl).withDetail("Verifier si l'url >> " + stringUrl + " existe").build());
        }

        return null;
    }


    @PostMapping("/employeurExistant")
    public Holder<CMGETEMPLOYEURDETAILS> getEmployeurexistant(@RequestBody CMGETEMPLOYEURDETAILS
                                                                  cmgetEmployeurDetails) throws JAXBException {
        // log.debug("REST request to save Immatriculation : {}", ENTITY_NAME);
        Holder<CMGETEMPLOYEURDETAILS> employeurDetails = new Holder<CMGETEMPLOYEURDETAILS>();
        employeurDetails = immatPortailService.getEmployeurExistant(cmgetEmployeurDetails);
        return employeurDetails;

    }

    @GetMapping("/listEmployes/{numeroUnique}")
    Holder<EMPLOYESLISTSERVICE> getListeemployes(@PathVariable String numeroUnique) throws JAXBException {
        Holder<EMPLOYESLISTSERVICE> employes = new Holder<EMPLOYESLISTSERVICE>();
        employes = listeEmployesService.getListeEmployes(numeroUnique);
        return employes;
    }


    @GetMapping("/solde/{numeroUnique}")
    Holder<XAIGETSOLDE> getSolde(@PathVariable String numeroUnique) throws JAXBException {
        Holder<XAIGETSOLDE> solde = new Holder<XAIGETSOLDE>();
        solde = soldeEmployeurService.getSoldeEmployeur(numeroUnique);
        return solde;
    }


    @GetMapping("/idsEmployeur/{numeroUnique}")
    Holder<IDsEMPLOYEURSERVICE> getIdentifiantsEmployeur(@PathVariable String numeroUnique) throws JAXBException {
        Holder<IDsEMPLOYEURSERVICE> ids = new Holder<IDsEMPLOYEURSERVICE>();
        ids = identifiantsEmployeurService.getIdentifiantsEmployeurs(numeroUnique);
        return ids;
    }


    @GetMapping("/employeur-agences/{numeroUnique}")
    Holder<AGENCESEMPLOYEURSERVICE> getAgencesEmployeur(@PathVariable String numeroUnique) {
        Holder<AGENCESEMPLOYEURSERVICE> agences = new Holder<AGENCESEMPLOYEURSERVICE>();
        try {
            agences = agencesRattachementService.getAgencesRattachement(numeroUnique);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            agences = null;
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/AGENCES_EMPLOYEUR_SERVICE?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof AGENCESEMPLOYEURSERVICEFault) {
                throw new RuntimeException(((AGENCESEMPLOYEURSERVICEFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        return agences;

    }

    @GetMapping("/numeroCompte/{numeroUnique}")
    Holder<CMPerAccountById> getNumeroCompteByPersonId(@PathVariable String numeroUnique) throws JAXBException {
        Holder<CMPerAccountById> compte = new Holder<CMPerAccountById>();
        compte = numeroCompteService.getNumeroCompteByPersonId(numeroUnique);
        return compte;
    }

    @PostMapping("/updateEmployeur")
    Holder<CMFICHEPORTXAI> updateEmployeur(@RequestBody CMFICHEPORTXAI employeurToUpdate) throws JAXBException {
        return ficheEmployeurService.updateEmployeur(employeurToUpdate);
    }


    @GetMapping("/searchEmployeurByUniqueNumber/{numeroUnique}")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.CABINET + "\",\"" + AuthoritiesConstants.EMPLOYEUR + "\",\"" + AuthoritiesConstants.PORTAIL + "\", \"" + AuthoritiesConstants.AGENT  + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")
    public ResponseEntity<HashMap<String, Object>> searchEmployeurByUniqueNumber(@PathVariable String numeroUnique) {
        HashMap<String, Object> result = new HashMap<>();
        List<Object> foundImmats = immatPortailService.searchEmployeurByUniqueNumber(numeroUnique);
        result.put("data", foundImmats);
        result.put("found", !foundImmats.isEmpty());
        result.put("code", "200");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/consultation/immatriculation")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.CONSULTATION + "\")")
    public ResponseEntity<HashMap<String, Object>> myImmat() {
        Optional<String> loggedusername = getCurrentUserLogin();
        if (!loggedusername.isPresent()) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }

        if (!isCurrentUserInRole(AuthoritiesConstants.CONSULTATION)) {
            throw Problem.builder().withStatus(Status.FORBIDDEN).withDetail("Vous n'avez pas les droits pour acceder a cette ressource").withTitle("Erreur").build();
        }

        User user = userRepository.findByLogin(loggedusername.get());
        if (user == null) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Utilisateur inconnu").withTitle("Erreur").build();
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("found", false);
        result.put("code", "404");
        result.put("data", null);
        consultationService.findByUserId(user.getId()).map(EmployeurConsultation::getNumeroUnique).ifPresent(numeroUnique -> {
            List<Object> foundImmats = immatPortailService.searchEmployeurByUniqueNumber(numeroUnique);
            result.put("data", foundImmats.isEmpty() ? null : foundImmats.get(0));
            result.put("code", "200");
            result.put("found", true);
        });
        return ResponseEntity.ok(result);
    }

}
