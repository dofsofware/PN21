package com.secusociale.portail.service;

import com.secusociale.portail.domain.OldImmatriculation;
import com.secusociale.portail.repository.OldImmatriculationRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.dto.OldImmatriculationDTO;
import com.secusociale.portail.service.dto.custom.AncienneImmatriculationDTO;
import com.secusociale.portail.service.immatriculation.FindEmployeurByIdService;
import com.secusociale.portail.service.immatriculation.IdentifiantsEmployeurService;
import com.secusociale.portail.service.immatriculation.ImmatPortailService;
import com.secusociale.portail.service.mapper.AncienneImmatriculationMapper;
import com.secusociale.portail.service.mapper.OldImmatriculationMapper;
import com.secusociale.portail.service.soap.CmFindEmployeurByTypeId.CmFindEmployeurByTypeId;
import com.secusociale.portail.service.soap.identifiantsEmployeurs.IDsEMPLOYEURSERVICE;
import com.secusociale.portail.web.rest.vm.CheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;

/**
 * Service Implementation for managing {@link OldImmatriculation}.
 */
@Service
@Transactional
public class OldImmatriculationService {

    private final Logger log = LoggerFactory.getLogger(OldImmatriculationService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentUrlService documentUrlService;
//
//    @Autowired
//    private VerifierExistenceEmployeur verifierExistenceEmployeur;

    @Autowired
    private FindEmployeurByIdService findEmployeurByIdService;

    @Autowired
    private IdentifiantsEmployeurService identifiantsEmployeurService;

    @Autowired
    private ImmatPortailService immatPortailService;

    private final OldImmatriculationRepository oldImmatriculationRepository;

    private final OldImmatriculationMapper oldImmatriculationMapper;
    private final AncienneImmatriculationMapper ancienneImmatriculationMapper;

    public OldImmatriculationService(OldImmatriculationRepository oldImmatriculationRepository, OldImmatriculationMapper oldImmatriculationMapper, AncienneImmatriculationMapper ancienneImmatriculationMapper) {
        this.oldImmatriculationRepository = oldImmatriculationRepository;
        this.oldImmatriculationMapper = oldImmatriculationMapper;
        this.ancienneImmatriculationMapper = ancienneImmatriculationMapper;
    }

    /**
     * Save a oldImmatriculation.
     *
     * @param oldImmatriculationDTO the entity to save.
     * @return the persisted entity.
     */
    public AncienneImmatriculationDTO save(OldImmatriculationDTO oldImmatriculationDTO) {
        log.debug("Request to save OldImmatriculation : {}", oldImmatriculationDTO);
        OldImmatriculation oldImmatriculation = oldImmatriculationMapper.toEntity(oldImmatriculationDTO);
        oldImmatriculation = oldImmatriculationRepository.save(oldImmatriculation);
        return ancienneImmatriculationMapper.toDto(oldImmatriculation);
    }

    /**
     * Get all the oldImmatriculations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OldImmatriculationDTO> findAll() {
        return oldImmatriculationRepository.findAll().stream()
            .map(oldImmatriculationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the oldImmatriculations.
     *
     * @return the list of entities.
     */
    @Transactional
    public OldImmatriculationDTO findByNumeroUnique(String numeroUnique) {
        return oldImmatriculationRepository.findByNumeroUnique(numeroUnique)
            .map(oldImmatriculationMapper::toDto).orElse(null);
    }

    /**
     * Get one oldImmatriculation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OldImmatriculationDTO> findOne(Long id) {
        return oldImmatriculationRepository.findById(id)
            .map(oldImmatriculationMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<OldImmatriculation> findById(Long id) {
        return oldImmatriculationRepository.findById(id);
    }

    /**
     * Delete the oldImmatriculation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OldImmatriculation : {}", id);
        oldImmatriculationRepository.deleteById(id);
    }

    public List<AncienneImmatriculationDTO> findAllByAndUser(Long userId) {
        return oldImmatriculationRepository.findAllByUserId(userId).stream()
            .map(ancienneImmatriculationMapper::toDto)
            .collect(Collectors.toList());
    }

    public Integer countOldImmatsByUser(String username) {
        if (userRepository.findOneByLogin(username).isPresent()) {
            return oldImmatriculationRepository.findAllByUserId((userRepository.findOneByLogin(username).get().getId())).size();
        }
        return 0;
    }

    public CheckResponse checkOldImmatExist(String type, String value) {
        CheckResponse response = new CheckResponse();
        Holder<CmFindEmployeurByTypeId> cmCheckExistenceEmployeur = new Holder<CmFindEmployeurByTypeId>();
        String numUnique = null;
        try {
            cmCheckExistenceEmployeur = findEmployeurByIdService.findEmployeurByTypeId(type, value);
            int taille = cmCheckExistenceEmployeur.value.getOutput().getEmployeurs().size();
            numUnique = taille != 0 ? cmCheckExistenceEmployeur.value.getOutput().getEmployeurs().get(0).getNumeroEmployeur() : null;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (numUnique == null)
            throw Problem.builder().withDetail("Aucun employeur trouvé avec la combinaison utilisée").withStatus(Status.BAD_REQUEST).build();

        Optional<OldImmatriculation> byTypeAndNum = oldImmatriculationRepository.findByTypeIdentifiantAndNumeroIdentifiant(type, value);
        Optional<OldImmatriculation> byUniqueNum = oldImmatriculationRepository.findByNumeroUnique(numUnique);
        response.setCode("200");
        response.setTypeIdentifiant(type);
        response.setNumeroIdentifiant(value);
        response.setNumeroUnique(numUnique);
        response.setExists(byTypeAndNum.isPresent() || byUniqueNum.isPresent());
        return response;
    }

    public CheckResponse checkOldImmatExistNew(String numIpres, String numCss, Long id, String currentStatus) {
        CheckResponse response = new CheckResponse();
        response.setExists(false);
        // Holder<CmFindEmployeurByTypeId> cmCheckExistenceEmployeur = new Holder<CmFindEmployeurByTypeId>();
        Optional<OldImmatriculation> byTypeAndNum = null;
        Optional<OldImmatriculation> byUniqueNum = null;
        // CmFindEmployeurByTypeId.Output.Employeurs employeur=null;
        //   List<CmFindEmployeurByTypeId.Output.Employeurs> listEmployeurs=null;

        try {
            if (numIpres != null && !numIpres.equalsIgnoreCase("")) {
                response = this.checkEmployerId("ANI", numIpres);
                response.setTypeIdentifiant("ANI");
                response.setNumeroIdentifiant(numIpres);
                byTypeAndNum = oldImmatriculationRepository.findByTypeIdentifiantAndNumeroIdentifiant("ANI", numIpres);
                if (response.getNumeroUnique() != null) {
                    byUniqueNum = oldImmatriculationRepository.findByNumeroUnique(response.getNumeroUnique());
                    response.setExists(byTypeAndNum.isPresent() || byUniqueNum.isPresent());
                } else
                    response.setExists(false);

            }
            if (numCss != null && !numCss.equalsIgnoreCase("") && response.getNumeroUnique() == null) {
                response = this.checkEmployerId("ANC", numCss);
                response.setTypeIdentifiant("ANC");
                response.setNumeroIdentifiant(numCss);
                byTypeAndNum = oldImmatriculationRepository.findByTypeIdentifiantAndNumeroIdentifiant("ANC", numCss);
                if (response.getNumeroUnique() != null) {
                    byUniqueNum = oldImmatriculationRepository.findByNumeroUnique(response.getNumeroUnique());
                    response.setExists(byTypeAndNum.isPresent() || byUniqueNum.isPresent());
                }
            }


            if (response.getNumeroUnique() == null) {
                response.setStatus("INEXISTANT");
                response.setTypeIdentifiant("all");
                response.setExists(false);
            }
            if (response.getStatus() != null && response.getStatus().equalsIgnoreCase("DOUBLON")) {
                response.setStatus("DOUBLON");
                response.setTypeIdentifiant("all");
                response.setExists(false);
            }
            if (response.getStatus() != null && currentStatus != null && !currentStatus.equalsIgnoreCase("") && !response.getStatus().equalsIgnoreCase(currentStatus) && !currentStatus.equalsIgnoreCase("") && currentStatus != null) {
                Optional<OldImmatriculation> oldRepo = oldImmatriculationRepository.findById(id);
                if (oldRepo.isPresent()) {
                    OldImmatriculation oldim = oldRepo.get();
                    if (response.getStatus().equalsIgnoreCase("EXISTANT")) {
                        oldim.setStatus("EN_COURS_DE_VERIFICATION");
                    } else {
                        oldim.setStatus(response.getStatus());
                    }

                    if (response.getEmployeur() != null) {
                        oldim.setAgenceCSS(response.getEmployeur().getAgenceCodeCSS());
                        oldim.setAgenceIPRES(response.getEmployeur().getAgenceCodeIPRES());
                        String numeroIdentifiant = "";
                        if (response.getEmployeur().getAncienNumeroIPRES() != null) {
                            numeroIdentifiant = response.getEmployeur().getAncienNumeroIPRES();
                        } else if (response.getEmployeur().getAncienNumeroCSS() != null) {
                            numeroIdentifiant = numeroIdentifiant + "@@##@@" + response.getEmployeur().getAncienNumeroCSS();
                        }
                        oldim.setNumeroIdentifiant(numeroIdentifiant);
                        // oldim.set
                    }
                    if (response.getNumeroUnique() != null) {
                        oldim.setNumeroUnique(response.getNumeroUnique());
                    }
                    oldim = oldImmatriculationRepository.save(oldim);

                    response.setStatus(oldim.getStatus());
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }


        return response;
    }

    public CheckResponse checkEmployerId(String type, String numero) throws JAXBException {
        Holder<CmFindEmployeurByTypeId> cmCheckExistenceEmployeur = null;
        List<CmFindEmployeurByTypeId.Output.Employeurs> listEmployeurs = null;
        CheckResponse response = new CheckResponse();
        // on recherche sur le webservice qui recupere toutes les informations
        cmCheckExistenceEmployeur = findEmployeurByIdService.findEmployeurByTypeId(type, numero);
        if (cmCheckExistenceEmployeur != null) {

            if (cmCheckExistenceEmployeur.value != null && cmCheckExistenceEmployeur.value.getOutput() != null)
                listEmployeurs = cmCheckExistenceEmployeur.value.getOutput().getEmployeurs();

            if (listEmployeurs == null || listEmployeurs.size() == 0) {
                // l'employeur n'existe pas donc le matching ne passe pas avec le numero
                response.setNumeroUnique(null);
            } else if (listEmployeurs.size() == 1) {
                // matching effectif avec le numero de Ipres
                response.setEmployeur(listEmployeurs.get(0));
                response.setNumeroUnique(response.getEmployeur().getNumeroEmployeur());
                response.setStatus("EXISTANT");
                response.setCode("200");
            } else {
                // cas de doublons
                response.setStatus("DOUBLON");
            }
        }
        return response;
    }

    public CheckResponse checkOldImmatExist(String numeroUnique) {
        CheckResponse response = new CheckResponse();
        Holder<IDsEMPLOYEURSERVICE> cmCheckExistenceEmployeur = new Holder<IDsEMPLOYEURSERVICE>();
        List<IDsEMPLOYEURSERVICE.Output> results = new ArrayList<>();
        try {

            cmCheckExistenceEmployeur = identifiantsEmployeurService.getIdentifiantsEmployeurs(numeroUnique);
            results = cmCheckExistenceEmployeur.value.getOutput();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (results == null || results.isEmpty())
            throw Problem.builder().withDetail("Aucun employeur trouvé avec cet numéro unique").withStatus(Status.BAD_REQUEST).build();

        Optional<OldImmatriculation> byUniqueNum = oldImmatriculationRepository.findByNumeroUnique(numeroUnique);
        response.setCode("200");
        response.setTypeIdentifiant("personId");
        response.setNumeroIdentifiant(numeroUnique);
        response.setNumeroUnique(numeroUnique);
        response.setExists(byUniqueNum.isPresent());
        return response;
    }

    @Scheduled(cron = "0 0 * ? * *")
    @Transactional
    public void changeBase64ToUrl() {
        oldImmatriculationRepository.findAll().forEach(oldImmatriculation -> {
            if (!oldImmatriculation.getMandatFile().startsWith("http")) {
                try {
                    String path = documentUrlService.uploadMandat(base64ToMultipart(oldImmatriculation.getMandatFile(), "mandat"));
                    oldImmatriculation.setMandatFile(path);
                    oldImmatriculationRepository.save(oldImmatriculation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
