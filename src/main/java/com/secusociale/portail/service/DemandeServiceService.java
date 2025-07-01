package com.secusociale.portail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.DemandeService;
import com.secusociale.portail.repository.DemandeServiceRepository;
import com.secusociale.portail.service.attestation.DemandeAttestationService;
import com.secusociale.portail.service.attestation.StatutDossierAttestationService;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegularite;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Holder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DemandeService}.
 */
@Service
@Transactional
public class DemandeServiceService {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceService.class);

    private final DemandeServiceRepository demandeServiceRepository;

    @Autowired
    private StatutDossierAttestationService statutDossierAttestationService;

    @Autowired
    private DemandeAttestationService demandeAttestationService;

    public DemandeServiceService(DemandeServiceRepository demandeServiceRepository) {
        this.demandeServiceRepository = demandeServiceRepository;
    }

    /**
     * Save a demandeService.
     *
     * @param demandeService the entity to save.
     * @return the persisted entity.
     */
    public DemandeService save(DemandeService demandeService) {
        log.debug("Request to save DemandeService : {}", demandeService);
        return demandeServiceRepository.save(demandeService);
    }

    public Optional<DemandeService> findByIdDocAndTypeDemandeAndStatut(String idDossier, String typeDemande, List<String> statuses) {
        List<DemandeService> demandeServices = demandeServiceRepository.findAllByIdDossierAndTypeDemandeAndStatutIn(idDossier, typeDemande, statuses);
        return demandeServices.stream().findFirst();
    }

    public Boolean hasAtLeastAPendingOne(String numeroUnique, String typeDemande, List<String> statuses) {
        List<DemandeService> demandeServices = demandeServiceRepository.findAllByNumeroUniqueAndTypeDemandeAndStatutIn(numeroUnique, typeDemande, statuses);
        return demandeServices.stream().findFirst().isPresent();
    }

    /**
     * Get all the demandeServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandeService> findAll(Pageable pageable) {
        log.debug("Request to get all DemandeServices");
        if (pageable.getSort().isEmpty()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        }
        return demandeServiceRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<DemandeService> findAllByUniqueNumber(String numeroUnique, Pageable pageable) {
        log.debug("Request to get all DemandeServices by unique number");
        return demandeServiceRepository.findAllByNumeroUnique(numeroUnique, pageable);
    }

    /**
     * Get one demandeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DemandeService> findOne(Long id) {
        log.debug("Request to get DemandeService : {}", id);
        return demandeServiceRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<DemandeService> findDemandesByEmployeur(Long iDemployeur) {
        log.debug("Request to get DemandeService : {}", iDemployeur);
        return demandeServiceRepository.findAllByEmployeur(iDemployeur);
    }


    /**
     * Delete the demandeService by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DemandeService : {}", id);
        demandeServiceRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void ChangeAttestationStatus() {
        List<DemandeService> list = demandeServiceRepository.findAllByIdDossierNotNullAndStatutNot("IMPRESSION");
        for (DemandeService immat : list.stream().filter(demandeService -> demandeService.getIdDossier() != null).collect(Collectors.toList())) {
            switch (immat.getTypeDemande()) {
                case "attestation régularité":
                    try {
                        Holder<CMGetStatusDemandeAttestation> statuDossier = new Holder<CMGetStatusDemandeAttestation>();
                        statuDossier = statutDossierAttestationService.getStatutDemandeAttestation(immat.getIdDossier());
                        if (statuDossier.value.getOutput() != null)
                            if (!StringUtils.isEmpty(statuDossier.value.getOutput().getStatus())) {
                                immat.setStatut(statuDossier.value.getOutput().getStatus());
                                demandeServiceRepository.saveAndFlush(immat);
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.warn("Erreur lors de la recuperation de status du dossier {}", immat.getIdDossier());
                    }
                    break;
                case "Reprise Activité":
                    break;
            }
        }
    }

    public void checkAttestationStatusAndUpdate(DemandeService demandeService) {
        if (StringUtils.isNotEmpty(demandeService.getIdDossier())) {
            switch (demandeService.getTypeDemande()) {
                case "attestation régularité":
                    try {
                        Holder<CMGetStatusDemandeAttestation> statuDossier = new Holder<CMGetStatusDemandeAttestation>();
                        statuDossier = statutDossierAttestationService.getStatutDemandeAttestation(demandeService.getIdDossier());
                        if (statuDossier.value.getOutput() != null)
                            if (!StringUtils.isEmpty(statuDossier.value.getOutput().getStatus())) {
                                demandeService.setStatut(statuDossier.value.getOutput().getStatus());
                                demandeServiceRepository.saveAndFlush(demandeService);
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.warn("Erreur lors de la recuperation de status du dossier {}", demandeService.getIdDossier());
                    }
                    break;
                case "Reprise Activité":
                    break;
            }
        }

    }

    public void sendDemandeServiceToPSRM() {
        List<DemandeService> dossierIsNull = demandeServiceRepository.findAllByIdDossierIsNull();

        dossierIsNull.forEach(demandeService -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String numeroEmployeur = demandeService.getNumeroUnique();
            JSONObject requete = null;

            if (demandeService.getStatut()==Constants.VALIDE) {
            switch (demandeService.getTypeDemande()) {
                case "attestation régularité":
                    try {
                        if (StringUtils.isEmpty(demandeService.getNumeroUnique())) {
                            requete = new JSONArray(demandeService.getPayload()).getJSONObject(0);
                            numeroEmployeur = requete.getString("numeroUnique");
                        }
                        Holder<CmGetAttestationRegularite> attestation = demandeAttestationService.createDossierAttestation(numeroEmployeur);
                        if (attestation.value.getOutput() != null) {
                            String jsonString = objectMapper.writeValueAsString(attestation.value);
                            demandeService.setReponsebrute(jsonString);
                            demandeService.setIdDossier(attestation.value.getOutput().getIdDossier());
                            demandeServiceRepository.save(demandeService);
                        }
                    } catch (Exception e) {
                        demandeService.setReponsebrute(e.toString());
                        demandeServiceRepository.save(demandeService);
                        e.printStackTrace();
                    }
                    break;
                case "Reprise Activité":
                    break;
            }
            }
        });
    }

    public  Optional<DemandeService> sendOrUpdateDemandeService(Long id) {
        Optional<DemandeService> demande = demandeServiceRepository.findById(id);

        if (demande.isPresent()) {
            DemandeService demandeService = demande.get();

            if (StringUtils.isEmpty(demandeService.getIdDossier())) {
                String numeroEmployeur = demandeService.getNumeroUnique();
                ObjectMapper objectMapper = new ObjectMapper();
                switch (demandeService.getTypeDemande()) {
                    case "attestation régularité":
                        JSONObject requete = null;
                        try {
                            if (StringUtils.isEmpty(demandeService.getNumeroUnique())) {
                                requete = new JSONArray(demandeService.getPayload()).getJSONObject(0);
                                numeroEmployeur = requete.getString("numeroUnique");
                            }

                            Holder<CmGetAttestationRegularite> attestation = demandeAttestationService.createDossierAttestation(numeroEmployeur);
                            if (attestation.value.getOutput() != null) {
                                String jsonString = objectMapper.writeValueAsString((CmGetAttestationRegularite) attestation.value);
                                demandeService.setReponsebrute(jsonString);
                                demandeService.setIdDossier(attestation.value.getOutput().getIdDossier());
//                            Holder<CMGetStatusDemandeAttestation> statutDemandeAttestation = statutDossierAttestationService.getStatutDemandeAttestation(demandeService.getIdDossier());
//                            if(statutDemandeAttestation.value != null){
//                                if(statutDemandeAttestation.value.getOutput() != null){
//                                    String status = statutDemandeAttestation.value.getOutput().getStatus();
//                                    demandeService.setStatut(status);
//                                }
//                            }
                                demandeServiceRepository.save(demandeService);
                            }
                        } catch (Exception e) {
                            demandeService.setReponsebrute(e.getMessage());
                            demandeService.setStatut(Constants.REJET);
                            demandeServiceRepository.save(demandeService);
                            e.printStackTrace();
                        }
                        break;
                    case "Reprise Activité":
                        break;
                }
            } else {
                if (!"IMPRESSION".equalsIgnoreCase(demandeService.getStatut())) {
                    checkAttestationStatusAndUpdate(demandeService);
                }
            }

            return Optional.of(demandeService);
        }

        return Optional.empty();
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void sendDemandeServiceToPCRMJob() {
        this.sendDemandeServiceToPSRM();
    }


}
