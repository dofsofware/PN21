package com.secusociale.portail.service;

import com.secusociale.portail.domain.ImmatriculationRecuperee;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeDTO;
import com.secusociale.portail.service.dto.OldImmatriculationDTO;
import com.secusociale.portail.service.immatriculation.AgencesRattachementService;
import com.secusociale.portail.service.immatriculation.FindEmployeurByIdService;
import com.secusociale.portail.service.immatriculation.IdentifiantsEmployeurService;
import com.secusociale.portail.service.mapper.ImmatriculationRecupereeMapper;
import com.secusociale.portail.service.soap.CmFindEmployeurByTypeId.CmFindEmployeurByTypeId;
import com.secusociale.portail.service.soap.agencesRattachement.AGENCESEMPLOYEURSERVICE;
import com.secusociale.portail.service.soap.identifiantsEmployeurs.IDsEMPLOYEURSERVICE;
import com.secusociale.portail.web.rest.vm.CheckResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ImmatriculationRecuperee}.
 */
@Service
@Transactional
public class ImmatriculationRecupereeService {

    private final Logger log = LoggerFactory.getLogger(ImmatriculationRecupereeService.class);

    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;

    private final ImmatriculationRecupereeMapper immatriculationRecupereeMapper;

    private final UserRepository userRepository;

    private final DocumentUrlService documentUrlService;

    private final FindEmployeurByIdService findEmployeurByIdService;

    @Autowired
    private IdentifiantsEmployeurService identifiantsEmployeurService;
    @Autowired
    private AgencesRattachementService agencesRattachementService;

    public ImmatriculationRecupereeService(ImmatriculationRecupereeRepository immatriculationRecupereeRepository, ImmatriculationRecupereeMapper immatriculationRecupereeMapper, UserRepository userRepository, DocumentUrlService documentUrlService, FindEmployeurByIdService findEmployeurByIdService) {
        this.immatriculationRecupereeRepository = immatriculationRecupereeRepository;
        this.immatriculationRecupereeMapper = immatriculationRecupereeMapper;
        this.userRepository = userRepository;
        this.documentUrlService = documentUrlService;
        this.findEmployeurByIdService = findEmployeurByIdService;
    }

    /**
     * Save a immatriculationRecuperee.
     *
     * @param immatriculationRecupereeDTO the entity to save.
     * @return the persisted entity.
     */
    public ImmatriculationRecupereeDTO save(ImmatriculationRecupereeDTO immatriculationRecupereeDTO) {
        log.debug("Request to save ImmatriculationRecuperee : {}", immatriculationRecupereeDTO);
        ImmatriculationRecuperee immatriculationRecuperee = immatriculationRecupereeMapper.toEntity(immatriculationRecupereeDTO);
        immatriculationRecuperee = immatriculationRecupereeRepository.save(immatriculationRecuperee);
        return immatriculationRecupereeMapper.toDto(immatriculationRecuperee);
    }

    /**
     * Get all the immatriculationRecuperees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ImmatriculationRecupereeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ImmatriculationRecuperees");
        return immatriculationRecupereeRepository.findAll(pageable)
            .map(immatriculationRecupereeMapper::toDto);
    }

    /**
     * Get all the oldImmatriculations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ImmatriculationRecupereeDTO> findAll() {
        return immatriculationRecupereeRepository.findAll().stream()
            .map(immatriculationRecupereeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one immatriculationRecuperee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ImmatriculationRecupereeDTO> findOne(Long id) {
        log.debug("Request to get ImmatriculationRecuperee : {}", id);
        return immatriculationRecupereeRepository.findById(id)
            .map(immatriculationRecupereeMapper::toDto);
    }

    /**
     * Get one immatriculationRecuperee by userId.
     *
     * @param userId the id of the entity.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ImmatriculationRecupereeDTO> findAllByUser(Long userId) {
        log.debug("Request to get ImmatriculationRecuperee : {}", userId);
        return immatriculationRecupereeRepository.findAllByUserIdOrManagerId(userId)
            .stream().map(immatriculationRecupereeMapper::toDto).collect(Collectors.toList());
    }



    /**
     * Delete the immatriculationRecuperee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ImmatriculationRecuperee : {}", id);
        immatriculationRecupereeRepository.deleteById(id);
    }

    /**
     * Methodes de recuperation d'immatriculation existante
     */

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

        Optional<ImmatriculationRecuperee> byTypeAndNum = immatriculationRecupereeRepository.findByTypeIdentifiantAndNumeroIdentifiant(type, value);
        Optional<ImmatriculationRecuperee> byUniqueNum = immatriculationRecupereeRepository.findByNumeroUnique(numUnique);
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
        Optional<ImmatriculationRecuperee> byTypeAndNum = null;
        Optional<ImmatriculationRecuperee> byUniqueNum = null;

        try {
            if (StringUtils.isNotEmpty(numIpres)) {
                response = this.checkEmployerId("ANI", numIpres);
                response.setTypeIdentifiant("ANI");
                response.setNumeroIdentifiant(numIpres);
                byTypeAndNum = immatriculationRecupereeRepository.findByTypeIdentifiantAndNumeroIdentifiant("ANI", numIpres);
                if (response.getNumeroUnique() != null) {
                    byUniqueNum = immatriculationRecupereeRepository.findByNumeroUnique(response.getNumeroUnique());
                    response.setExists(byTypeAndNum.isPresent() || byUniqueNum.isPresent());
                } else
                    response.setExists(false);

            }
            if (StringUtils.isNotEmpty(numCss)) {
                response = this.checkEmployerId("ANC", numCss);
                response.setTypeIdentifiant("ANC");
                response.setNumeroIdentifiant(numCss);
                byTypeAndNum = immatriculationRecupereeRepository.findByTypeIdentifiantAndNumeroIdentifiant("ANC", numCss);
                if (response.getNumeroUnique() != null) {
                    byUniqueNum = immatriculationRecupereeRepository.findByNumeroUnique(response.getNumeroUnique());
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
                Optional<ImmatriculationRecuperee> oldRepo = immatriculationRecupereeRepository.findById(id);
                if (oldRepo.isPresent()) {
                    ImmatriculationRecuperee oldim = oldRepo.get();
                    if (response.getStatus().equalsIgnoreCase("EXISTANT")) {
                        oldim.setStatus("EN_COURS_DE_VERIFICATION");
                    } else {
                        oldim.setStatus(response.getStatus());
                    }

                    if (response.getEmployeur() != null) {
                        oldim.setAgenceCss(response.getEmployeur().getAgenceCodeCSS());
                        oldim.setAgenceIpres(response.getEmployeur().getAgenceCodeIPRES());
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
                    oldim = immatriculationRecupereeRepository.save(oldim);

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

    public ImmatriculationRecupereeDTO setAgenceRattachement(ImmatriculationRecupereeDTO dto){
        try {
            Holder<AGENCESEMPLOYEURSERVICE> agencesRattachement = agencesRattachementService.getAgencesRattachement(dto.getNumeroUnique());
            if(agencesRattachement.value != null) {
                List<AGENCESEMPLOYEURSERVICE.Output> agences = agencesRattachement.value.getOutput();
                if (agences !=null) {
                    agences.forEach(a -> {
                    if (a.getInstitution().equalsIgnoreCase("IPRES")) {
                        dto.setAgenceIpres(a.getCodeAgence());
                    } else if (a.getInstitution().equalsIgnoreCase("CSS")) {
                        dto.setAgenceCss(a.getCodeAgence());
                    }

              });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
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

        Optional<ImmatriculationRecuperee> byUniqueNum = immatriculationRecupereeRepository.findAllByNumeroUnique(numeroUnique).stream().findFirst();
        response.setCode("200");
        response.setTypeIdentifiant("personId");
        response.setExtras(byUniqueNum.map(ImmatriculationRecuperee::getExtrasInfo).orElse(null));
        response.setNumeroIdentifiant(numeroUnique);
        response.setNumeroUnique(numeroUnique);
        response.setExists(byUniqueNum.isPresent());
        return response;
    }

    public Integer countOldImmatsByUser(String username) {
        if (userRepository.findOneByLogin(username).isPresent()) {
            return immatriculationRecupereeRepository.findAllByUserId((userRepository.findOneByLogin(username).get().getId())).size();
        }
        return 0;
    }

}
