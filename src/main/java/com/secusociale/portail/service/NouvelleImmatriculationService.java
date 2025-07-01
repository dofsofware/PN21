package com.secusociale.portail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.domain.enumeration.*;
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.immatriculation.FindEmployeurByIdService;
import com.secusociale.portail.service.immatriculation.ImmatPortailService;
import com.secusociale.portail.service.immatriculation.StatutDossierImmatriculationService;
import com.secusociale.portail.service.mapper.NouvelleImmatriculationMapper;
import com.secusociale.portail.service.soap.CmFindEmployeurByTypeId.CmFindEmployeurByTypeId;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND;
import com.secusociale.portail.service.soap.domestique.InboundDomFrm;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUND;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAI;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUND;
import com.secusociale.portail.service.soap.statutDossierImmatriculation.CmGetStatusDossierImmatriculation;
import com.fasterxml.jackson.databind.JsonNode;
import com.secusociale.portail.service.utils.UtilsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.zalando.problem.Problem;

import javax.validation.Valid;
import javax.xml.ws.Holder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Service Implementation for managing {@link NouvelleImmatriculation}.
 */
@Service
@Transactional
public class NouvelleImmatriculationService {

    private final Logger log = LoggerFactory.getLogger(NouvelleImmatriculationService.class);
    private static final String ENTITY_NAME = "portailCssIpresV2NouvelleImmatriculation";

    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;
    private final NouvelleImmatriculationMapper nouvelleImmatriculationMapper;
    private final UserRepository userRepository;
    private final ImmatPortailService immatPortailService;
    private final DeclaredEmployerService declaredEmployerService;
    private final UtilsService utilsService;
    private final MailService mailService;
    private final SpringTemplateEngine templateEngine;
    private final JournalImmatriculationService journalImmatriculationService;
    private final UserService userService;
    private final GeoInfoService geoInfoService;
    private final TauxATService tauxATService;

    @Autowired
    private SuiviJobService suiviJobService;

    @Autowired
    private JournalJobService journalJobService;

    private final StatutDossierImmatriculationService statutDossierImmatriculationService;
    private final FindEmployeurByIdService findEmployeurByIdService;

    public NouvelleImmatriculationService(DeclaredEmployerService declaredEmployerService, NouvelleImmatriculationRepository nouvelleImmatriculationRepository, NouvelleImmatriculationMapper nouvelleImmatriculationMapper, UserRepository userRepository, ImmatPortailService immatPortailService, UtilsService utilsService, MailService mailService, SpringTemplateEngine templateEngine, JournalImmatriculationService journalImmatriculationService, @Lazy UserService userService, GeoInfoService geoInfoService, TauxATService tauxATService, StatutDossierImmatriculationService statutDossierImmatriculationService, FindEmployeurByIdService findEmployeurByIdService) {
        this.nouvelleImmatriculationRepository = nouvelleImmatriculationRepository;
        this.nouvelleImmatriculationMapper = nouvelleImmatriculationMapper;
        this.userRepository = userRepository;
        this.immatPortailService = immatPortailService;
        this.utilsService = utilsService;
        this.mailService = mailService;
        this.templateEngine = templateEngine;
        this.journalImmatriculationService = journalImmatriculationService;
        this.userService = userService;
        this.geoInfoService = geoInfoService;
        this.tauxATService = tauxATService;
        this.statutDossierImmatriculationService = statutDossierImmatriculationService;
        this.findEmployeurByIdService = findEmployeurByIdService;
        this.declaredEmployerService = declaredEmployerService;
    }

    /**
     * Save a nouvelleImmatriculation.
     *
     * @param nouvelleImmatriculationDTO the entity to save.
     * @return the persisted entity.
     */
    public NouvelleImmatriculationDTO save(NouvelleImmatriculationDTO nouvelleImmatriculationDTO) {
        log.debug("Request to save NouvelleImmatriculation : {}", nouvelleImmatriculationDTO);
        NouvelleImmatriculation nouvelleImmatriculation = nouvelleImmatriculationMapper.toEntity(nouvelleImmatriculationDTO);
        nouvelleImmatriculation = nouvelleImmatriculationRepository.save(nouvelleImmatriculation);
        return nouvelleImmatriculationMapper.toDto(nouvelleImmatriculation);
    }

    /**
     * Get all the nouvelleImmatriculations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NouvelleImmatriculationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NouvelleImmatriculations");
        return nouvelleImmatriculationRepository.findAll(pageable)
            .map(nouvelleImmatriculationMapper::toDto);
    }

    /**
     * Get one nouvelleImmatriculation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NouvelleImmatriculationDTO> findOne(Long id) {
        log.debug("Request to get NouvelleImmatriculation : {}", id);
        return nouvelleImmatriculationRepository.findById(id)
            .map(nouvelleImmatriculationMapper::toDto);
    }

    /**
     * Delete the nouvelleImmatriculation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NouvelleImmatriculation : {}", id);
        nouvelleImmatriculationRepository.deleteById(id);
    }

    public List<NouvelleImmatriculation> getUserImmatriculations(String username) {
        if (userRepository.findOneByLogin(username).isPresent()) {
            return nouvelleImmatriculationRepository.findAllByUser(userRepository.findByLogin(username).getId());
        }
        return null;
    }

    public Integer countLocalImmatsByUser(String username) {
        if (userRepository.findOneByLogin(username).isPresent()) {
            return nouvelleImmatriculationRepository.findAllByUser(userRepository.findByLogin(username).getId()).size();
        }
        return 0;
    }

    public List<NouvelleImmatriculation> getAllNotNullNumDoc() {
        return nouvelleImmatriculationRepository.findAllByNumdocNotNull();
    }

    public List<NouvelleImmatriculation> getAllByNumdocNotNullAndStatusdocNotIESV() {
        return nouvelleImmatriculationRepository.findAllByNumdocNotNullAndStatusdocNotIESV("IESV");
    }

    public List<NouvelleImmatriculation> getActiveImmats() {
        return nouvelleImmatriculationRepository.findAllByStatusdocAndNumeroUniqueNull("IESV");
    }

    /**
     * Immatriculation status should be automatically check for change.
     * <p>
     * This is scheduled to get fired every day, at 01:00 (am).
     */
    @Scheduled(cron = "#{@changeImmatriculationStatusJobExpression}")
    @Transactional
    public void changeImmatriculationStatus() {
        List<NouvelleImmatriculation> list = getAllByNumdocNotNullAndStatusdocNotIESV();
        for (NouvelleImmatriculation immat : list) {
            checkAndUpdateMyStatus(immat);
        }
    }

    @Scheduled(cron = "#{@getLocalImmatriculationUniqueNumberJobExpression}")
    public void getUniqueNumber() {
        List<NouvelleImmatriculation> list = getActiveImmats();
        for (NouvelleImmatriculation immat : list) {
            if (!StringUtils.isEmpty(immat.getNinea())) {
                try {
                    Holder<CmFindEmployeurByTypeId> ele = new Holder<>();
                    ele = findEmployeurByIdService.findEmployeurByTypeId("SCI", immat.getNinea());
                    if (ele.value.getOutput() != null)
                        if (!ele.value.getOutput().getEmployeurs().isEmpty()) {
                            CmFindEmployeurByTypeId.Output.Employeurs emp = ele.value.getOutput().getEmployeurs().get(0);
                            immat.setNinea(emp.getNumeroEmployeur());
                            immat.setTypeIdentifiant("personId");
                            nouvelleImmatriculationRepository.saveAndFlush(immat);
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.warn("Erreur lors de la recuperation de numeroUnique du dossier {}", immat.getNinea());
                }
            }
        }
    }

    public NouvelleImmatriculation checkAndUpdateMyStatus(NouvelleImmatriculation immat) {

        try {
            Holder<CmGetStatusDossierImmatriculation> statuDossier = statutDossierImmatriculationService.getStatutDossierImmatriculation(immat.getNumdoc());
            if (statuDossier.value.getOutput() != null) {
                String codeStatut = statuDossier.value.getOutput().getCodeStatus();
                if (!StringUtils.isEmpty(codeStatut)) {
                    immat.setStatusdoc(codeStatut);
                    immat.setStatusdesc(statuDossier.value.getOutput().getDescription());
                    if (codeStatut.equalsIgnoreCase("IESV")) {
                        System.out.println("fini la dougg");
                        NouvelleImmatriculation savedImmat = nouvelleImmatriculationRepository.saveAndFlush(immat);
                        immat = getMyUniqueNumber(savedImmat);
                    }
                }
            }
        } catch (Exception ex) {
            throw Problem.builder().withDetail(ex.getMessage()).withTitle("Erreur").build();
        }

        return nouvelleImmatriculationRepository.saveAndFlush(immat);
    }

    public List<NouvelleImmatriculation> checkAndUpdateAllStatus(List<NouvelleImmatriculation> immats) {
        for (NouvelleImmatriculation immat : immats) {
            if(!"IESV".equalsIgnoreCase(immat.getStatusdoc()) && StringUtils.isEmpty(immat.getNumeroUnique()) ){
                try {
                    Holder<CmGetStatusDossierImmatriculation> statuDossier = statutDossierImmatriculationService.getStatutDossierImmatriculation(immat.getNumdoc());
                    if (statuDossier.value.getOutput() != null) {
                        String codeStatut = statuDossier.value.getOutput().getCodeStatus();
                        if (!StringUtils.isEmpty(codeStatut)) {
                            immat.setStatusdoc(codeStatut);
                            immat.setStatusdesc(statuDossier.value.getOutput().getDescription());
                            if (codeStatut.equalsIgnoreCase("IESV")) {
                                System.out.println("fini la dougg");
                                NouvelleImmatriculation savedImmat = nouvelleImmatriculationRepository.saveAndFlush(immat);
                                immat = getMyUniqueNumber(savedImmat);
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw Problem.builder().withDetail(ex.getMessage()).withTitle("Erreur").build();
                }
                nouvelleImmatriculationRepository.saveAndFlush(immat);
            }

        }
        return immats;
    }

    public NouvelleImmatriculation getMyUniqueNumber(NouvelleImmatriculation immat) {
        if (!StringUtils.isEmpty(immat.getNinea())) {
            if (StringUtils.isEmpty(immat.getNumeroUnique())) {
                try {
                    String ninea = immat.getNinea();
                    Holder<CmFindEmployeurByTypeId> ele = findEmployeurByIdService.findEmployeurByTypeId("SCI", ninea);
                    if (ele.value.getOutput() != null)
                        if (!ele.value.getOutput().getEmployeurs().isEmpty()) {
                            CmFindEmployeurByTypeId.Output.Employeurs emp = ele.value.getOutput().getEmployeurs().get(0);
                            if (StringUtils.isNotEmpty(emp.getNumeroEmployeur())) {
                                immat.setNumeroUnique(emp.getNumeroEmployeur());
                                immat.setTypeIdentifiant("personId");
                            }
                            if (StringUtils.isNotEmpty(emp.getAgenceCodeIPRES())) {
                                immat.setAgenceIpres(emp.getAgenceCodeIPRES());
                            }
                            if (StringUtils.isNotEmpty(emp.getAgenceCodeCSS())) {
                                immat.setAgenceCss(emp.getAgenceCodeCSS());
                            }
                            nouvelleImmatriculationRepository.save(immat);
                        }
                } catch (Exception ex) {
                    throw Problem.builder().withDetail(ex.getMessage()).withTitle("Erreur").build();
                }
            }
        } else {
            System.out.println("NINEA NULL");
        }
        return immat;
    }

    public void sendImmatriculationsToPCRM() {
        List<NouvelleImmatriculation> submitFalse = nouvelleImmatriculationRepository.findAllByIsSubmitIsTrueAndNumdocIsNullAndStatusdocEquals("EN_ATTENTE");
        if (submitFalse.size() > 0) {
            System.out.println("Sending immats");
            String jobName = "sendImmatriculations";
            Optional<SuiviJobDTO> jobOptional = suiviJobService.findJob(jobName);
//            if (!jobOptional.isPresent()) {
//                throw Problem.builder().withTitle("Batch introuvable").withDetail("Aucun batch trouvé avec ce nom").build();
//            }
            Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
            if (loggedusername == null || !loggedusername.isPresent()) {
                loggedusername = Optional.of("plateforme");
            }
            String username = loggedusername.get();
            User foundUser = userRepository.findByLogin(username);
            String userEmail = foundUser !=null ? userRepository.findByLogin(username).getEmail() : "system";
            SuiviJobDTO job = jobOptional.orElseGet(SuiviJobDTO::new);
            job.setNom("jobName");
            job.setStatut("STARTED");
            job.setDemarreLe(Instant.now());
            job.setDemarrePar(userEmail);
            suiviJobService.save(job);

            ObjectMapper mapper = new ObjectMapper();
            submitFalse.forEach(immatriculation -> {
                switch (immatriculation.getType()) {
                    case "IMMATPORTAIL":
                        try {
                            IMMATRICULATIONINBOUND immatriculationinbound = mapper.readValue(immatriculation.getPayload(), IMMATRICULATIONINBOUND.class);
                            createImmatriculationPortail(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "MAINTIENT-AFFILIATION":
                        try {
                            MAINTAFFINBOUND immatriculationinbound = mapper.readValue(immatriculation.getPayload(), MAINTAFFINBOUND.class);
                            createMaintientAffiliation(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "REPRESENTATION-DIPLOMATIQUE":
                        try {
                            IMMATREPDIPLO immatriculationinbound = mapper.readValue(immatriculation.getPayload(), IMMATREPDIPLO.class);
                            createRepresentationDiplomatique(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "PUBLIQUE-PARAPUBLIQUE":
                        try {
                            IMMAT2INBOUND immatriculationinbound = mapper.readValue(immatriculation.getPayload(), IMMAT2INBOUND.class);
                            createPubliqueParapublique(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DOMESTIQUE":
                        try {
                            InboundDomFrm immatriculationinbound = mapper.readValue(immatriculation.getPayload(), InboundDomFrm.class);
                            createImmatriculationDomestique(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "INDEPENDANT":
                        try {
                            CMCrtIndForXAI immatriculationinbound = mapper.readValue(immatriculation.getPayload(), CMCrtIndForXAI.class);
                            createIndependant(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            });
            job.setTermineLe(Instant.now());
            job.setStatut("NOT-STARTED");
            suiviJobService.save(job);
        } else {
            System.out.println("vide");
        }
    }


    @Scheduled(cron = "#{@changeImmatriculationStatusJobExpression}")
    public void sendImmatriculationsToPCRMJob() {
        sendImmatriculationsToPCRM();
    }

    public void createImmatriculationPortail(NouvelleImmatriculation nv, IMMATRICULATIONINBOUND immatriculation) {

        try {
            Holder<IMMATRICULATIONINBOUND> immatriculationInbound = immatPortailService.createImmatriculationPortail(immatriculation);
            IMMATRICULATIONINBOUND.Output output = immatriculationInbound.value.getOutput();
            if (output != null) {
                nv.setAgenceCss(output.getAgenceCss());
                nv.setAgenceIpres(output.getAgenceIpres());
                nv.setTauxAt(output.getTauxAt());
                nv.setSectorCss(output.getSectorCss());
                nv.setSectorIpres(output.getSectorIpres());
                nv.setZoneCss(output.getZoneCss());
                nv.setZoneIpres(output.getZoneIpres());
                nv.setNumdoc(output.getProcessFlowId());
                nv.setEmployerRegistrationFormId(output.getEmployerRegistrationFormId());
                nv.setEmployeeRegistrationFormId(output.getEmployeeRegistrationFormId());
            }
        } catch (Exception e) {
            nv.setStatusdesc(e.getMessage());
            nv.setStatusdoc(Constants.REJET);
            e.printStackTrace();
        } finally {
            nouvelleImmatriculationRepository.saveAndFlush(nv);
        }


    }

    public void createMaintientAffiliation(NouvelleImmatriculation nv, MAINTAFFINBOUND immatriculation) {
        try {
            Holder<MAINTAFFINBOUND> immatriculationInbound = immatPortailService.createImmatriculationMaintienAffiliation(immatriculation);
            MAINTAFFINBOUND.Output output = immatriculationInbound.value.getOutput();
            if (output != null) {
                nv.setNumdoc(output.getProcessFlowId());
                nv.setEmployerRegistrationFormId(output.getRegistrationFormId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            nv.setStatusdesc(e.getMessage());
            nv.setStatusdoc(Constants.REJET);
        } finally {
            nouvelleImmatriculationRepository.saveAndFlush(nv);
        }

    }

    public void createRepresentationDiplomatique(NouvelleImmatriculation nv, IMMATREPDIPLO immatriculation) {
        try {
            Holder<IMMATREPDIPLO> immatriculationInbound = immatPortailService.createImmatriculationRepresentatnt(immatriculation);
            IMMATREPDIPLO.Output output = immatriculationInbound.value.getOutput();

            if (output != null) {
                nv.setNumdoc(output.getProcessFlowId());
                nv.setAgenceCss(output.getAgenceCss());
                nv.setAgenceIpres(output.getAgenceIpres());
                nv.setTauxAt(output.getTauxAt());
                nv.setSectorCss(output.getSectorCss());
                nv.setSectorIpres(output.getSectorIpres());
                nv.setZoneCss(output.getZoneCss());
                nv.setZoneIpres(output.getZoneIpres());
                nv.setEmployerRegistrationFormId(output.getEmployerRegistrationFormId());
                nv.setEmployeeRegistrationFormId(output.getEmployeeRegistrationFormId());
            }

        } catch (Exception e) {
            nv.setStatusdesc(e.getMessage());
            nv.setStatusdoc(Constants.REJET);
            e.printStackTrace();
        } finally {
            nouvelleImmatriculationRepository.saveAndFlush(nv);
        }
    }

    public void createPubliqueParapublique(NouvelleImmatriculation nv, IMMAT2INBOUND immatriculation) {
        try {
            Holder<IMMAT2INBOUND> immatriculationInbound = immatPortailService.createImmatPublicParapublique(immatriculation);
            IMMAT2INBOUND.Output output = immatriculationInbound.value.getOutput();
            if (output != null) {
                nv.setNumdoc(output.getProcessFlowId());
                nv.setAgenceCss(output.getAgenceCss());
                nv.setAgenceIpres(output.getAgenceIpres());
                nv.setTauxAt(output.getTauxAt());
                nv.setSectorCss(output.getSectorCss());
                nv.setSectorIpres(output.getSectorIpres());
                nv.setZoneCss(output.getZoneCss());
                nv.setZoneIpres(output.getZoneIpres());
                nv.setEmployerRegistrationFormId(output.getEmployerRegistrationFormId());
                nv.setEmployeeRegistrationFormId(output.getEmployeeRegistrationFormId());
            }
        } catch (Exception e) {
            nv.setStatusdesc(e.getMessage());
            nv.setStatusdoc(Constants.REJET);
            e.printStackTrace();
        } finally {
            nouvelleImmatriculationRepository.saveAndFlush(nv);
        }


    }

    public void createImmatriculationDomestique(NouvelleImmatriculation nv, InboundDomFrm immatriculation) {
        try {
            Holder<InboundDomFrm> immatriculationInbound = immatPortailService.createImmatDomestique(immatriculation);
            InboundDomFrm.Output output = immatriculationInbound.value.getOutput();
            if (output != null) {
                nv.setNumdoc(output.getFolderId());
                nv.setAgenceIpres(output.getAgenceIpres());
                nv.setAgenceCss(output.getAgenceCss());
                nv.setTauxAt(output.getTauxAt());
                nv.setSectorCss(output.getSectorCss());
                nv.setSectorIpres(output.getSectorIpres());
                nv.setZoneCss(output.getZoneCss());
                nv.setZoneIpres(output.getZoneIpres());
                nv.setEmployerRegistrationFormId(output.getRegistrationFormId());
                nv.setEmployeeRegistrationFormId(output.getRegistrationFormId());
            }
        } catch (Exception e) {
            nv.setStatusdesc(e.getMessage());
            nv.setStatusdoc(Constants.REJET);
            e.printStackTrace();
        } finally {
            nouvelleImmatriculationRepository.saveAndFlush(nv);
        }


    }

    public void createIndependant(NouvelleImmatriculation nv, CMCrtIndForXAI independant) {
        try {
            Holder<CMCrtIndForXAI> immatIndependant = immatPortailService.createImmatIndependant(independant);
            CMCrtIndForXAI.Output output = immatIndependant.value.getOutput();
            if (output != null) {
                nv.setNumdoc(output.getDossierId());
                nv.setAgenceCss(output.getAgenceCss());
                nv.setAgenceIpres(output.getAgenceIpres());
                nv.setTauxAt(output.getTauxAt());
                nv.setSectorCss(output.getSectorCss());
                nv.setSectorIpres(output.getSectorIpres());
                nv.setZoneCss(output.getZoneCss());
                nv.setZoneIpres(output.getZoneIpres());
                nv.setEmployerRegistrationFormId(output.getFormulaireId());
                nv.setEmployeeRegistrationFormId(output.getFormulaireId());
            }

        } catch (Exception e) {
            nv.setStatusdesc(e.getMessage());
            nv.setStatusdoc(Constants.REJET);
            log.error(e.getMessage());
        } finally {
            nouvelleImmatriculationRepository.saveAndFlush(nv);
        }

    }


    public ApiResponse<String> validateImmatriculationDTO(@Valid NouvelleImmatriculationDTO nouvelleImmatriculationDTO,Boolean fromValidation) {
        ApiResponse<String> response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode payloadNode;
        try {
            payloadNode = objectMapper.readTree(nouvelleImmatriculationDTO.getPayload());
        } catch (IOException e) {
            log.error("Method : validateImmatriculationDTO , Message : Impossible de parser le champ 'payload' JSON");
            return ApiResponse.error500("Impossible de parser le champ 'payload' JSON");
        }

        JsonNode inputNode = payloadNode.path("input");
        JsonNode employerQueryNode = inputNode.path("employerQuery");

        response = validatePayloadStructure(payloadNode,nouvelleImmatriculationDTO.getType());
        if(response != null){
            return response;
        }

        if(Objects.equals(nouvelleImmatriculationDTO.getType(), "IMMATPORTAIL")
        ){
            response = validateLegalStatus(employerQueryNode.path("legalStatus").asText());
            if(response != null){
                return response;
            }

            response = validateTradeRegisterNumber(
                employerQueryNode.path("tradeRegisterNumber").asText(),
                nouvelleImmatriculationDTO.getRegistreCommerce()
            );
            if(response != null){
                return response;
            }
        }

        if(Objects.equals(nouvelleImmatriculationDTO.getType(), "IMMATPORTAIL") ||
            Objects.equals(nouvelleImmatriculationDTO.getType(), "PUBLIQUE-PARAPUBLIQUE")
        ){
            response = validateNineaNumber(
                employerQueryNode.path("nineaNumber").asText(),
                nouvelleImmatriculationDTO.getNinea()
            );
            if(response != null){
                return response;
            }


            response = validateRaisonSociale(nouvelleImmatriculationDTO.getRaisonSociale());
            if(response != null){
                return response;
            }
        }


       if(!Objects.equals(nouvelleImmatriculationDTO.getType(), "INDEPENDANT") &&
        !Objects.equals(nouvelleImmatriculationDTO.getType(), "MAINTIENT-AFFILIATION")
        ){
            response = validateEmployeeList(nouvelleImmatriculationDTO);
            if(response != null){
                return response;
            }
        }

        if(fromValidation ==  false){
            response = validateNullFields(nouvelleImmatriculationDTO);
            if(response != null){
                return response;
            }
        }

        response = validateDocuments(nouvelleImmatriculationDTO);
        if(response != null){
            return response;
        }

        if (fromValidation == false) {
            if (!Objects.equals(nouvelleImmatriculationDTO.getType(), "INDEPENDANT") &&
                !Objects.equals(nouvelleImmatriculationDTO.getType(), "REPRESENTATION-DIPLOMATIQUE")) {
                response = validateExistence(nouvelleImmatriculationDTO);
                if (response != null) {
                    return response;
                }
            }
        }


        return null;
    }

    private void saveNewDeclaredEmployer(@Valid NouvelleImmatriculationDTO nouvelleImmatriculationDTO) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(nouvelleImmatriculationDTO.getPayload());
            JsonNode employerQuery = rootNode.path("input").path("employerQuery");
            JsonNode mainRegistrationForm = rootNode.path("input").path("mainRegistrationForm");

            DeclaredEmployer newDeclaredEmployer = new DeclaredEmployer();

            String nineaNumber = employerQuery.path("nineaNumber").asText(null);
            String legalStatus = employerQuery.path("legalStatus").asText(null);
            String shortName = mainRegistrationForm.path("shortName").asText(null);
            String businessSector = mainRegistrationForm.path("businessSector").asText(null);
            String noOfWorkersInBasicScheme = mainRegistrationForm.path("noOfWorkersInBasicScheme").asText(null);
            String region = mainRegistrationForm.path("region").asText(null);
            String address = mainRegistrationForm.path("address").asText(null);
            String telephone = mainRegistrationForm.path("telephone").asText(null);

            newDeclaredEmployer.setAgenceCss(nouvelleImmatriculationDTO.getAgenceCss());
            newDeclaredEmployer.setAgenceIpres(nouvelleImmatriculationDTO.getAgenceIpres());
            newDeclaredEmployer.setRaisonSociale(nouvelleImmatriculationDTO.getRaisonSociale());
            newDeclaredEmployer.setRccm(nouvelleImmatriculationDTO.getRegistreCommerce());
            newDeclaredEmployer.setSigle(shortName);
            newDeclaredEmployer.setAncienNumCss(nouvelleImmatriculationDTO.getNumeroCss());
            newDeclaredEmployer.setAncienNumIpres(nouvelleImmatriculationDTO.getNumeroIpres());
            if (noOfWorkersInBasicScheme == null || noOfWorkersInBasicScheme.isEmpty()) {
                newDeclaredEmployer.setEffectif(null);
            }else {
                newDeclaredEmployer.setEffectif(Long.valueOf(noOfWorkersInBasicScheme));
            }
            if(legalStatus != null){
                newDeclaredEmployer.setStatutJuridique(legalStatus);
            }
            newDeclaredEmployer.setNinea(nouvelleImmatriculationDTO.getNinea());
            newDeclaredEmployer.setNumeroUnique(nouvelleImmatriculationDTO.getNumeroUnique());
            newDeclaredEmployer.setSecteurActivite(businessSector);
            newDeclaredEmployer.setRegion(region);
            newDeclaredEmployer.setAdresse(address);
            newDeclaredEmployer.setTelephone(telephone);

            declaredEmployerService.save(newDeclaredEmployer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors du traitement du JSON", e);
        }
    }

    private ApiResponse<String> validateExistence(NouvelleImmatriculationDTO nouvelleImmatriculationDTO) {
        String ninea = nouvelleImmatriculationDTO.getNinea();
        if (!isValidNineaFormat(ninea)) {
            return ApiResponse.error400("Format NINEA invalide");
        }
        try {
            boolean response = declaredEmployerService.doesEmployerExist(ninea, SearchType.NINEA);
            if (response == true) {
                return ApiResponse.error400("Un employeur avec le NINEA : " + ninea + " est déjà immatriculé");
            }
            return null;
        } catch (NumberFormatException e) {
            return ApiResponse.error400("NINEA doit être un nombre valide");
        }
    }

    private boolean isValidNineaFormat(String ninea) {
        return ninea != null && ninea.matches("\\d+");
    }
    private ApiResponse<String> validateLegalStatus(String legalStatus) {
        if (legalStatus == null || legalStatus.trim().isEmpty()) {
            log.error("Method : validateLegalStatus , Message : Le champ 'legalStatus' dans 'payload' ne peut pas être null ou vide");
            return ApiResponse.error400("Le champ 'legalStatus' dans 'payload' ne peut pas être null ou vide");
        }

        try {
            StatutJuridique.valueOf(legalStatus);
        } catch (IllegalArgumentException e) {
            log.error("Method : validateLegalStatus , Message : Le champ 'legalStatus' dans 'payload'  doit être compris dans " + Arrays.toString(StatutJuridique.values()));
            return ApiResponse.error400("Le champ 'legalStatus' dans 'payload'  doit être compris dans " + Arrays.toString(StatutJuridique.values()));
        }
        return null;
    }

    private ApiResponse<String> validateNineaNumber(String nineaNumber, String ninea) {
        if (nineaNumber == null || nineaNumber.trim().isEmpty()) {
            log.error("Method : validateNineaNumber , Message : Le champ 'nineaNumber' dans 'payload' ne peut pas être null ou vide");
            return ApiResponse.error400("Le champ 'nineaNumber' dans 'payload' ne peut pas être null ou vide");
        }

        if (!nineaNumber.equals(ninea)) {
            log.error("Method : validateNineaNumber , Message : Le champ 'nineaNumber' dans 'payload' doit correspondre au champ ninea");
            return ApiResponse.error400("Le champ 'nineaNumber' dans 'payload' doit correspondre au champ ninea");
        }
        return null;
    }

    private ApiResponse<String> validateTradeRegisterNumber(String tradeRegisterNumber, String registreCommerce) {
        if (tradeRegisterNumber == null || tradeRegisterNumber.trim().isEmpty()) {
            log.error("Method : validateTradeRegisterNumber , Message : Le champ 'tradeRegisterNumber' dans 'payload' ne peut pas être null ou vide");
            return ApiResponse.error400("Le champ 'tradeRegisterNumber' dans 'payload' ne peut pas être null ou vide");
        }

        if (!tradeRegisterNumber.equals(registreCommerce)) {
            log.error("Method : validateTradeRegisterNumber , Message : Le champ 'tradeRegisterNumber' dans 'payload' doit correspondre au champ registre de commerce");
            return ApiResponse.error400("Le champ 'tradeRegisterNumber' dans 'payload' doit correspondre au champ registre de commerce");
        }
        return null;
    }

    private ApiResponse<String> validateRaisonSociale(String raisonSociale) {
        if (raisonSociale == null || raisonSociale.trim().isEmpty()) {
            log.error("Method : validateRaisonSociale , Message : Le champ 'raisonSociale' ne peut pas être null ou vide");
            return ApiResponse.error400("Le champ 'raisonSociale' ne peut pas être null ou vide");
        }
        return null;
    }

    private ApiResponse<String> validateNullFields(NouvelleImmatriculationDTO dto) {

        Object[][] nullCheckFields = {
            {"employerRegistrationFormId", dto.getEmployerRegistrationFormId()},
            {"employeeRegistrationFormId", dto.getEmployeeRegistrationFormId()},
            {"zoneCss", dto.getZoneCss()},
            {"sectorCss", dto.getSectorCss()},
            {"zoneIpres", dto.getZoneIpres()},
            {"sectorIpres", dto.getSectorIpres()},
            {"agenceCss", dto.getAgenceCss()},
            {"agenceIpres", dto.getAgenceIpres()},
            {"tauxAt", dto.getTauxAt()},
            {"numeroUnique", dto.getNumeroUnique()},
            {"typeIdentifiant", dto.getTypeIdentifiant()},
            {"statusdesc", dto.getStatusdesc()},
            {"numdoc", dto.getNumdoc()}
        };

        for (Object[] fieldInfo : nullCheckFields) {
            String fieldName = (String) fieldInfo[0];
            Object fieldValue = fieldInfo[1];

            if (fieldValue != null) {
                log.error(String.format("Method : validateNullFields , Message : Le champ '%s' devrait être null mais contient la valeur : '%s'", fieldName, fieldValue));
                return ApiResponse.error400("Le champ '" + fieldName + "' devrait être null mais contient la valeur : '" + fieldValue+"'");
            }
        }
        return null;
    }

    private ApiResponse<String> validatePayloadStructure(JsonNode payloadNode,String typeImmat) {
        String message = "Method : validatePayloadStructure , Message : La structure du champ 'payload' est invalide";
        if(Objects.equals(typeImmat, "IMMATPORTAIL")) {
            if (!payloadNode.has("input") ||
                !payloadNode.path("input").has("employerQuery") ||
                !payloadNode.path("input").has("mainRegistrationForm") ||
                !payloadNode.path("input").has("legalRepresentativeForm") ||
                !payloadNode.path("input").has("documents") ||
                !payloadNode.path("input").has("employeList")
            ) {
                log.error(message);
                return ApiResponse.error400("La structure du champ 'payload' est invalide");
            }
        }

        if(Objects.equals(typeImmat, "PUBLIQUE-PARAPUBLIQUE") || Objects.equals(typeImmat, "REPRESENTATION-DIPLOMATIQUE")) {
            if (!payloadNode.has("input") ||
                !payloadNode.path("input").has("employerQuery") ||
                !payloadNode.path("input").has("mainRegistrationForm") ||
                !payloadNode.path("input").has("personneContact") ||
                !payloadNode.path("input").has("documents") ||
                !payloadNode.path("input").has("employeList")
            ) {
                log.error(message);
                return ApiResponse.error400("La structure du champ 'payload' est invalide");
            }
        }

        if(Objects.equals(typeImmat, "MAINTIENT-AFFILIATION")) {
            if (!payloadNode.has("input") ||
                !payloadNode.path("input").has("registrationFormInfos") ||
                !payloadNode.path("input").has("informationsGenerales") ||
                !payloadNode.path("input").has("infosSupplementaires") ||
                !payloadNode.path("input").has("documents")
            ) {
                log.error(message);
                return ApiResponse.error400("La structure du champ 'payload' est invalide");
            }
        }

        if(Objects.equals(typeImmat, "DOMESTIQUE")) {
            if (!payloadNode.has("input") ||
                !payloadNode.path("input").has("registrationFormInfos") ||
                !payloadNode.path("input").has("domesticRegistrationForm") ||
                !payloadNode.path("input").has("documents") ||
                !payloadNode.path("input").has("employeList")
            ) {
                log.error(message);
                return ApiResponse.error400("La structure du champ 'payload' est invalide"+payloadNode);
            }
        }
        return null;
    }

    private ApiResponse<String> validateEmployeeList(NouvelleImmatriculationDTO dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode payloadNode = objectMapper.readTree(dto.getPayload());
            JsonNode employeListNode = payloadNode.path("input").path("employeList");

            if (!employeListNode.isArray() || employeListNode.size() == 0) {
                log.error("Method : validateEmployeeList , Message : La 'employeList' doit contenir au moins un élément");
                return ApiResponse.error400("La 'employeList' doit contenir au moins un élément");
            }
        } catch (IOException e) {
            log.error("Method : validateEmployeeList , Message : Erreur lors de la lecture du payload JSON");
            return ApiResponse.error500("Erreur lors de la lecture du payload JSON");
        }
        return null;
    }

    private ApiResponse<String> validateDocuments(NouvelleImmatriculationDTO dto ) {
        String typeImmat = dto.getType();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode payloadNode = objectMapper.readTree(dto.getPayload());
            JsonNode documentsNode = payloadNode.path("input").path("documents");

            String[] docImmatPortail = {
                "demandeEcrit", "formDemande", "contratsTravail",
                "cni", "dmt", "registreCommerce", "declarationEtablissement"
            };

            String[] docPubliqueParapublique = {
                "demandeEcrit", "formDemande", "contratsTravail",
                "cni", "decretMinisteriel"
            };

            String[] docs = {
                "demandeEcrit", "formDemande", "contratsTravail",
                "cni"
            };

            String[] docIndependant = {
                "demandeEcrit", "formDemande", "cni"
            };

            if(Objects.equals(typeImmat, "PUBLIQUE-PARAPUBLIQUE")) {
                for (String docKey : docPubliqueParapublique) {
                    JsonNode documentNode = documentsNode.path(docKey);
                    if (documentNode.isNull()){
                        log.error("Method : validateDocuments , Message : Le document '" + docKey + "' ne peut pas être null ou vide");
                        return ApiResponse.error400("Le document '" + docKey + "' ne peut pas être null ou vide");
                    }

                    if (documentNode.isObject()) {
                        JsonNode urlNode = documentNode.path("url");

                        if (urlNode.isNull() || !urlNode.isTextual() || urlNode.asText().trim().isEmpty()) {
                            log.error("Method : validateDocuments , Message : L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                            return ApiResponse.error400("L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                        }
                    }
                }
            }

            if(Objects.equals(typeImmat, "IMMATPORTAIL")) {
                for (String docKey : docImmatPortail) {
                    JsonNode documentNode = documentsNode.path(docKey);
                    if (documentNode.isNull()){
                        log.error("Method : validateDocuments , Message : Le document '" + docKey + "' ne peut pas être null ou vide");
                        return ApiResponse.error400("Le document '" + docKey + "' ne peut pas être null ou vide");
                    }

                    if (documentNode.isObject()) {
                        JsonNode urlNode = documentNode.path("url");

                        if (urlNode.isNull() || !urlNode.isTextual() || urlNode.asText().trim().isEmpty()) {
                            log.error("Method : validateDocuments , Message : L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                            return ApiResponse.error400("L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                        }
                    }
                }
            }

            if(Objects.equals(typeImmat, "DOMESTIQUE") ||
                Objects.equals(typeImmat, "MAINTIENT-AFFILIATION") ||
                Objects.equals(typeImmat, "REPRESENTATION-DIPLOMATIQUE")) {
                for (String docKey : docs) {
                    JsonNode documentNode = documentsNode.path(docKey);
                    if (documentNode.isNull()){
                        log.error("Method : validateDocuments , Message : Le document '" + docKey + "' ne peut pas être null ou vide");
                        return ApiResponse.error400("Le document '" + docKey + "' ne peut pas être null ou vide");
                    }

                    if (documentNode.isObject()) {
                        JsonNode urlNode = documentNode.path("url");

                        if (urlNode.isNull() || !urlNode.isTextual() || urlNode.asText().trim().isEmpty()) {
                            log.error("Method : validateDocuments , Message : L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                            return ApiResponse.error400("L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                        }
                    }
                }
            }

            if(Objects.equals(typeImmat, "INDEPENDANT")) {
                for (String docKey : docIndependant) {
                    JsonNode documentNode = documentsNode.path(docKey);
                    if (documentNode.isNull()){
                        log.error("Method : validateDocuments , Message : Le document '" + docKey + "' ne peut pas être null ou vide");
                        return ApiResponse.error400("Le document '" + docKey + "' ne peut pas être null ou vide");
                    }

                    if (documentNode.isObject()) {
                        JsonNode urlNode = documentNode.path("url");

                        if (urlNode.isNull() || !urlNode.isTextual() || urlNode.asText().trim().isEmpty()) {
                            log.error("Method : validateDocuments , Message : L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                            return ApiResponse.error400("L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                        }
                    }
                }
            }

            if(Objects.equals(typeImmat, "MAINTIENT-AFFILIATION")) {
                for (String docKey : docIndependant) {
                    JsonNode documentNode = documentsNode.path(docKey);
                    if (documentNode.isNull()){
                        log.error("Method : validateDocuments , Message : Le document '" + docKey + "' ne peut pas être null ou vide");
                        return ApiResponse.error400("Le document '" + docKey + "' ne peut pas être null ou vide");
                    }

                    if (documentNode.isObject()) {
                        JsonNode urlNode = documentNode.path("url");

                        if (urlNode.isNull() || !urlNode.isTextual() || urlNode.asText().trim().isEmpty()) {
                            log.error("Method : validateDocuments , Message : L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                            return ApiResponse.error400("L'URL du document '" + docKey + "' ne peut pas être null ou vide");
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("Method : validateDocuments , Message : Erreur lors de la lecture du payload JSON");
            return ApiResponse.error500("Erreur lors de la lecture du payload JSON");
        }
        return null;
    }

    public ApiResponse<Object> checkSalarieDuplicates(FileUploaded fileUploaded) {
        try {
            byte[] bytes = Base64.getDecoder().decode(fileUploaded.getFile().split(",")[1]);
            Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(bytes));
            Sheet sheet = workbook.getSheetAt(0);

            Map<String, Integer> seenCombinations = new HashMap<>();
            List<SheetDuplicateDTO> duplicates = new ArrayList<>();
            String sheetName = sheet.getSheetName();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || isEntireRowEmpty(row)) {
                    continue;
                }
                if (row != null) {
                    Cell typePiece = row.getCell(7); // typePiece
                    Cell numeroPiece = row.getCell(8); // numeroPiece
                    Cell nom = row.getCell(1); // nom
                    Cell prenom = row.getCell(2); // prenom

                    String key = (typePiece != null ? typePiece.toString() : "") + "_" + (numeroPiece != null ? numeroPiece.toString() : "");

                    if (seenCombinations.containsKey(key)) {
                        SheetDuplicateDTO duplicate = new SheetDuplicateDTO();
                        duplicate.setFeuille(sheetName);
                        duplicate.setOccurence1("Ligne " + (seenCombinations.get(key) + 1));
                        duplicate.setOccurence2("Ligne " + (i + 1));
                        Map<String, String> valeursDupliquees = new HashMap<>();
                        valeursDupliquees.put("typePiece", typePiece != null ? typePiece.toString() : "");
                        valeursDupliquees.put("numeroPiece", numeroPiece != null ? numeroPiece.toString() : "");
                        valeursDupliquees.put("nom", nom != null ? nom.toString() : "");
                        valeursDupliquees.put("prenom", prenom != null ? prenom.toString() : "");
                        duplicate.setValeursDupliquees(valeursDupliquees);
                        duplicates.add(duplicate);
                    } else {
                        seenCombinations.put(key, i);
                    }
                }
            }

            if (!duplicates.isEmpty()) {
                return ApiResponse.error400(duplicates);
            } else {
                return ApiResponse.success("Aucune duplication trouvée.");
            }

        } catch (IOException e) {
            log.error("Erreur lors de la lecture du fichier Excel", e);
            return ApiResponse.error500("Erreur lors de la lecture du fichier Excel : " + e.getMessage());
        } catch (InvalidFormatException e) {
            return ApiResponse.error500("Erreur lors du formatage du fichier Excel : " + e.getMessage());
        }
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

    public @Valid NouvelleImmatriculationDTO getNumeroDossier(@Valid NouvelleImmatriculationDTO nouvelleImmatriculationDTO) {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String numeroDossier = "P" + nouvelleImmatriculationDTO.getId() + timestamp;
        nouvelleImmatriculationDTO.setNumeroDossier(numeroDossier);
        String adresse ="";
        String localite ="";
        String activitePrincipale ="";
        try {
            ObjectMapper mapper = new ObjectMapper();
            String payload = nouvelleImmatriculationDTO.getPayload();
            if (payload != null) {
                JsonNode rootNode = mapper.readTree(payload);
                TypeImmatriculation type = TypeImmatriculation.fromCode(nouvelleImmatriculationDTO.getType());
                JsonNode registrationNode;

                if (type.getRegistrationNodeName().isEmpty()) {
                    registrationNode = rootNode.path("input");
                } else {
                    registrationNode = rootNode.path("input").path(type.getNodeNameForRegistration());
                }

                adresse = type == TypeImmatriculation.MAINTIENT_AFFILIATION ?
                    registrationNode.path("addresse").asText() :
                    registrationNode.path("address").asText();

                localite = registrationNode.path("region").asText();
                activitePrincipale = type.hasMainLineOfBusiness() ? registrationNode.path("mainLineOfBusiness").asText() : "";
            }
        } catch (Exception ignored){}
        GeoInfo geoInfo = geoInfoService.searchMostRelevantGeoInfo(adresse);
        if (geoInfo == null) {
            geoInfo = geoInfoService.searchMostRelevantGeoInfo(localite);
        }
        Taux_AT tauxATObj = tauxATService.searchMostRelevantTauxAT(activitePrincipale);
        if (tauxATObj == null) {
            tauxATObj = tauxATService.searchMostRelevantTauxAT(activitePrincipale.split(" ")[0]);
        }
        if (geoInfo != null) {
            if (geoInfo.getAgenceIpres() != null) {
                nouvelleImmatriculationDTO.setAgenceIpres(geoInfo.getAgenceIpres());
            }
            if (geoInfo.getAgenceCss() != null) {
                nouvelleImmatriculationDTO.setAgenceCss(geoInfo.getAgenceCss());
            }
            if (geoInfo.getCodeSectorCss() != null) {
                nouvelleImmatriculationDTO.setSectorCss(geoInfo.getCodeSectorCss());
            }
            if (geoInfo.getCodeSectorIpres() != null) {
                nouvelleImmatriculationDTO.setSectorIpres(geoInfo.getCodeSectorIpres());
            }
            if (geoInfo.getCodeZoneCss() != null) {
                nouvelleImmatriculationDTO.setZoneCss(geoInfo.getCodeZoneCss());
            }
            if (geoInfo.getCodeZoneIpres() != null) {
                nouvelleImmatriculationDTO.setZoneIpres(geoInfo.getCodeZoneIpres());
            }
        }

        if (tauxATObj != null && tauxATObj.getTauxAT() != null) {
            nouvelleImmatriculationDTO.setTauxAt(tauxATObj.getTauxAT());
        }
        return save(nouvelleImmatriculationDTO);
    }

    public Object validateImmatriculation(ValidationImmatriculationDTO validationDTO) {
        if (validationDTO.getImmatriculationId() == null || validationDTO.getImmatriculationId().isEmpty()) {
            return ApiResponse.error400("L'immatriculationId est obligatoire");
        }
        if (validationDTO.getTypeOperation() == null) {
            return ApiResponse.error400("Le typeOperation est obligatoire");
        }

        try {
            TypeOperation.valueOf(validationDTO.getTypeOperation().toString());
        } catch (IllegalArgumentException e) {
            return ApiResponse.error400("Type d'opération invalide. Les valeurs acceptées sont: VALIDATION, REJETE");
        }

        Long id = Long.parseLong(validationDTO.getImmatriculationId());
        log.debug("REST request to validateImmatriculation : {}", id);

        Optional<String> loggedUsername = getCurrentUserLogin();
        if (!loggedUsername.isPresent()) {
            return ApiResponse.error400("Utilisateur non connecté");
        }

        String username = loggedUsername.get();
        User user = userRepository.findByLogin(username);

        Optional<NouvelleImmatriculationDTO> nouvelleImmatriculationOpt = findOne(id);
        if (!nouvelleImmatriculationOpt.isPresent()) {
            return ApiResponse.error400("Immatriculation non trouvée");
        }

        NouvelleImmatriculationDTO nouvelleImmatriculation = nouvelleImmatriculationOpt.get();
        ApiResponse<String> response = validateImmatriculationDTO(nouvelleImmatriculation,true);
        if(response != null){
            return response;
        }
        if (Objects.equals(nouvelleImmatriculation.getStatusdoc(), "IESV")) {
            return ApiResponse.error400("Cette Immatrication est déjà validée, elle n'est plus modifiable");
        }

        Object statusResult = determineNewStatus(user, TypeOperation.valueOf(validationDTO.getTypeOperation()), nouvelleImmatriculation);
        if (!(statusResult instanceof StatusDocP)) {
            return statusResult;
        }
        StatusDocP newStatus = (StatusDocP) statusResult;

        Object result = processValidation(validationDTO, nouvelleImmatriculation, newStatus);


        if (result instanceof NouvelleImmatriculationDTO) {
            NouvelleImmatriculation retourImmat = nouvelleImmatriculationMapper.toEntity((NouvelleImmatriculationDTO) result);
            String detail = TypeOperation.REJETE == TypeOperation.valueOf(validationDTO.getTypeOperation()) ?
                "Rejet de l'immatriculation: " + validationDTO.getMotif() :
                "Validation de l'immatriculation - Nouveau statut: " + newStatus;

            journalImmatriculationService.journaliserAction(
                (NouvelleImmatriculation) retourImmat,
                detail,
                "VALIDATION_IMMATRICULATION"
            );
        }

        return result;
    }

    private Object determineNewStatus(User user, TypeOperation typeOperation, NouvelleImmatriculationDTO immatriculation) {
        if (typeOperation == TypeOperation.REJETE) {
            return StatusDocP.REJETE;
        }

        StatusDocP currentStatus = immatriculation.getStatusDocP();
        switch (user.getTypeCompte()) {
            case Constants.GESTIONNAIRE_EMPLOYEUR:
                if (currentStatus == null ||
                    (!currentStatus.equals(StatusDocP.VALIDATION_CHEF_AGENCE_CSS) &&
                        !currentStatus.equals(StatusDocP.VALIDATION_CHEF_AGENCE_IPRES))) {
                    return StatusDocP.VALIDATION_CHEF_AGENCE_CSS;
                }
                return ApiResponse.error400("Cette immatriculation est en attente de validation pour les prochaines étapes.");

            case Constants.CHEF_AGENCE:
                if ("CSS".equals(user.getInstitution())) {
                    if (currentStatus != null &&
                        (currentStatus.equals(StatusDocP.VALIDATION_GESTIONNAIRE_DE_COMPTE) ||
                            currentStatus.equals(StatusDocP.VALIDATION_CHEF_AGENCE_CSS))) {
                        return StatusDocP.VALIDATION_CHEF_AGENCE_IPRES;
                    }
                    return ApiResponse.error400("Cette immatriculation doit être validée par le gestionnaire de compte au préalable.");
                } else if ("IPRES".equals(user.getInstitution())) {
                    if (currentStatus != null &&
                        currentStatus.equals(StatusDocP.VALIDATION_CHEF_AGENCE_IPRES)) {
                        return StatusDocP.VALIDE;
                    }
                    return ApiResponse.error400("Cette immatriculation doit être validée par le Chef agence CSS au préalable.");
                }
        }

        return ApiResponse.error400("Opération non autorisée pour votre type de compte");
    }

    private Object processValidation(ValidationImmatriculationDTO validationDTO,
                                     NouvelleImmatriculationDTO nouvelleImmatriculation,
                                     StatusDocP newStatus) {

        if (TypeOperation.REJETE == TypeOperation.valueOf(validationDTO.getTypeOperation())) {
            if (isEmpty(validationDTO.getMotif())) {
                return ApiResponse.error400("Le motif de rejet est obligatoire");
            }
            nouvelleImmatriculation.setStatusDocP(StatusDocP.REJETE);
            nouvelleImmatriculation.setStatusdoc("REJET");
            nouvelleImmatriculation.setStatusdesc(validationDTO.getMotif());
        } else {
            nouvelleImmatriculation.setStatusDocP(newStatus);
            nouvelleImmatriculation.setStatusdesc("Votre demande est en cours de traitement");
            nouvelleImmatriculation.setStatusdoc("ACFIE");
            User currentUser = userService.getUserWithAuthorities()
                .orElseThrow(() -> new RuntimeException("Utilisateur non connecté"));
            String cachet = currentUser.getCachet();
            if (StatusDocP.VALIDATION_CHEF_AGENCE_IPRES.equals(newStatus)) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddHHmmss"));
                String numeroUnique = "P" + nouvelleImmatriculation.getId() + timestamp;
                nouvelleImmatriculation.setNumeroUnique(numeroUnique);
                nouvelleImmatriculation.setStatusdesc("Votre demande a été traité avec succès");
                nouvelleImmatriculation.setStatusdoc("IESV");
                saveNewDeclaredEmployer(nouvelleImmatriculation);
                sendCertificatDimmatriculation(nouvelleImmatriculation, TypeDocument.CERTIFICATION_D_IMMATRICULATION,cachet);
            }
        }

        return nouvelleImmatriculation;
    }

    public boolean isHeadquarters(String numeroUnique) {
        return nouvelleImmatriculationRepository.findByStatusIESVAndNumeroUnique(numeroUnique)
            .map(immat -> {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = immat.getPayload();
                    if (payload != null) {
                        JsonNode rootNode = mapper.readTree(payload);
                        JsonNode employerQuery = rootNode.path("input").path("employerQuery");

                        String typeEtablissement = employerQuery.path("typeEtablissement").asText();
                        return "HDQT".equals(typeEtablissement);
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            })
            .orElse(false);
    }

    public List<NouvelleImmatriculation> findBranchOffices(String numeroUnique) {
        ObjectMapper mapper = new ObjectMapper();

        return nouvelleImmatriculationRepository.findByStatusIESV().stream()
            .filter(immat -> {
                try {
                    String payload = immat.getPayload();
                    if (payload != null) {
                        JsonNode rootNode = mapper.readTree(payload);
                        JsonNode employerQuery = rootNode.path("input").path("employerQuery");

                        String typeEtablissement = employerQuery.path("typeEtablissement").asText();
                        String hqId = employerQuery.path("hqId").asText();
                        boolean response = "BRNC".equals(typeEtablissement) && numeroUnique.equals(hqId);

                        return response;
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            })
            .collect(Collectors.toList());
    }

    public Optional<NouvelleImmatriculation> findByNumeroUnique(String numeroUnique) {
        return nouvelleImmatriculationRepository.findByNumeroUnique(numeroUnique);
    }

    public void sendCertificatDimmatriculation(NouvelleImmatriculationDTO nouvelleImmatriculation, TypeDocument typeDocument, String cachet)  {
        try {
            String sujet ="";
            String contenu ="";
            String prenom ="";
            String nom ="";
            String email ="";
            String url = utilsService.generateCertificat(nouvelleImmatriculation, typeDocument,cachet);
            ObjectMapper mapper = new ObjectMapper();
            String payload = nouvelleImmatriculation.getPayload();
            if (payload != null) {
                JsonNode rootNode = mapper.readTree(payload);
                TypeImmatriculation type = TypeImmatriculation.fromCode(nouvelleImmatriculation.getType());

                JsonNode contactNode;
                if (type.isDirectInInput()) {
                    contactNode = rootNode.path("input");
                } else {
                    contactNode = rootNode.path("input").path(type.getNodeNameForContact());
                }

                prenom = contactNode.path(type.isUseEnglishNames() ? "firstName" : "prenom").asText();
                nom = contactNode.path(type.isUseEnglishNames() ? "lastName" : "nom").asText();
                email = contactNode.path("email").asText();
            }

            Locale locale = Locale.forLanguageTag("FR");
            Context context = new Context(locale);
            context.setVariable("url", url);
            context.setVariable("nom",nom);
            context.setVariable("prenom",prenom);
            if(typeDocument == TypeDocument.CERTIFICATION_D_IMMATRICULATION){
                contenu = templateEngine.process("mail/certificatImmatriculationEmail", context);
                mailService.sendEmail(email,"Certificat d'immatriculation",contenu,false,true);
            }else {
                contenu = templateEngine.process("mail/certificatDeRecepisseDeDepotImmatriculationEmail", context);
                mailService.sendEmail(email,"Récépissé de dépôt de votre demande d'immatriculation",contenu,false,true);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendImmatriculationsToPCRMByPortail() {
        List<NouvelleImmatriculation> submitFalse = nouvelleImmatriculationRepository.findAllByIsSubmitIsTrueAndNumdocIsNullAndStatusdocEquals("EN_ATTENTE");
        if (submitFalse.size() > 0) {
            System.out.println("Sending immats");
            String jobName = "sendImmatriculations";
            Optional<JournalJobDTO> jobOptional = journalJobService.findJob(jobName);
//            if (!jobOptional.isPresent()) {
//                throw Problem.builder().withTitle("Batch introuvable").withDetail("Aucun batch trouvé avec ce nom").build();
//            }
            Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
            if (loggedusername == null || !loggedusername.isPresent()) {
                loggedusername = Optional.of("plateforme");
            }
            String username = loggedusername.get();
            User foundUser = userRepository.findByLogin(username);
            String userEmail = foundUser !=null ? userRepository.findByLogin(username).getEmail() : "system";
            JournalJobDTO job = jobOptional.orElseGet(JournalJobDTO::new);
            job.setNom("jobName");
            job.setStatut("STARTED");
            job.setDemarreLe(Instant.now());
            job.setDemarrePar(userEmail);
            journalJobService.save(job);

            ObjectMapper mapper = new ObjectMapper();
            submitFalse.forEach(immatriculation -> {
                switch (immatriculation.getType()) {
                    case "IMMATPORTAIL":
                        try {
                            IMMATRICULATIONINBOUND immatriculationinbound = mapper.readValue(immatriculation.getPayload(), IMMATRICULATIONINBOUND.class);
                            createImmatriculationPortail(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "MAINTIENT-AFFILIATION":
                        try {
                            MAINTAFFINBOUND immatriculationinbound = mapper.readValue(immatriculation.getPayload(), MAINTAFFINBOUND.class);
                            createMaintientAffiliation(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "REPRESENTATION-DIPLOMATIQUE":
                        try {
                            IMMATREPDIPLO immatriculationinbound = mapper.readValue(immatriculation.getPayload(), IMMATREPDIPLO.class);
                            createRepresentationDiplomatique(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "PUBLIQUE-PARAPUBLIQUE":
                        try {
                            IMMAT2INBOUND immatriculationinbound = mapper.readValue(immatriculation.getPayload(), IMMAT2INBOUND.class);
                            createPubliqueParapublique(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DOMESTIQUE":
                        try {
                            InboundDomFrm immatriculationinbound = mapper.readValue(immatriculation.getPayload(), InboundDomFrm.class);
                            createImmatriculationDomestique(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "INDEPENDANT":
                        try {
                            CMCrtIndForXAI immatriculationinbound = mapper.readValue(immatriculation.getPayload(), CMCrtIndForXAI.class);
                            createIndependant(immatriculation, immatriculationinbound);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            });
            job.setTermineLe(Instant.now());
            job.setStatut("NOT-STARTED");
            journalJobService.save(job);
        } else {
            System.out.println("vide");
        }
    }
}
