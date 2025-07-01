package com.secusociale.portail.web.rest.utility;

import com.secusociale.portail.domain.*;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.LocalimmatriculationRepository;
import com.secusociale.portail.repository.OldImmatriculationRepository;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.NouvelleImmatriculationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/extractions")
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class InfosExtractionResource {

    private final LocalimmatriculationRepository localimmatriculationRepository;
    private final OldImmatriculationRepository oldImmatriculationRepository;
    private final NouvelleImmatriculationService nouvelleImmatriculationService;
    private final ServerCheckRepository serverCheckRepository;
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;
    private static final Logger log = LoggerFactory.getLogger(InfosExtractionResource.class);

    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping("/migrate-all-in-one/{batchSize}")
    @Async
    public void migrateAllInOne(@PathVariable int batchSize) {
        log.info("=================================================================");
        log.info("Demarrage de la migration complete avec une taille de lot de {}", batchSize);
        log.info("=================================================================");
        // Migration des nouvelles immatriculations
        try {
            processMigrationNewImmat(batchSize);
        } catch (Exception e) {
            log.info("=================================================================");
            log.error("Erreur lors de la migration des nouvelles immatriculations", e);
            log.info("=================================================================");
        }

        // Migration des anciennes immatriculations
        try {
            processMigrationOldImmat(batchSize);
        } catch (Exception e) {
            log.info("=================================================================");
            log.error("Erreur lors de la migration des anciennes immatriculations", e);
            log.info("=================================================================");
        }

        // Migration des immatriculations recuperees
        try {
            processMigrationRecuperee(batchSize);
        } catch (Exception e) {
            log.info("=================================================================");
            log.error("Erreur lors de la migration des immatriculations recuperees", e);
            log.info("=================================================================");
        }
    }

    private void processMigrationNewImmat(int batchSize) {
        String processName = "Nouvelles immatriculations";
        long totalRecords = localimmatriculationRepository.count();
        int totalPages = (int) Math.ceil((double) totalRecords / batchSize);
        log.info("=================================================================");
        log.info("Demarrage de la migration : {} - Total: {}", processName, totalRecords);
        log.info("=================================================================");
        for (int page = 0; page < totalPages; page++) {
            try {
                Pageable pageRequest = PageRequest.of(page, batchSize);
                Page<Localimmatriculation> pageData = localimmatriculationRepository.findAll(pageRequest);
                processNewImmatBatch(pageData.getContent(), processName, page + 1, totalPages);

                if (page % 10 == 0) {
                    System.gc();
                }
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("Erreur lors du traitement du lot {} pour {}", page, processName, e);
                log.info("=================================================================");
            }
        }
    }

    private void processMigrationOldImmat(int batchSize) {
        String processName = "Anciennes immatriculations";
        long totalRecords = oldImmatriculationRepository.count();
        int totalPages = (int) Math.ceil((double) totalRecords / batchSize);
        log.info("=================================================================");
        log.info("Demarrage de la migration : {} - Total: {}", processName, totalRecords);
        log.info("=================================================================");
        for (int page = 0; page < totalPages; page++) {
            try {
                Pageable pageRequest = PageRequest.of(page, batchSize);
                Page<OldImmatriculation> pageData = oldImmatriculationRepository.findAll(pageRequest);
                processOldImmatBatch(pageData.getContent(), processName, page + 1, totalPages);

                if (page % 10 == 0) {
                    System.gc();
                }
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("Erreur lors du traitement du lot {} pour {}", page, processName, e);
                log.info("=================================================================");
            }
        }
    }

    private void processMigrationRecuperee(int batchSize) {
        String processName = "Immatriculations recuperees";
        long totalRecords = immatriculationRecupereeRepository.count();
        int totalPages = (int) Math.ceil((double) totalRecords / batchSize);
        log.info("=================================================================");
        log.info("Demarrage de la migration : {} - Total: {}", processName, totalRecords);
        log.info("=================================================================");
        for (int page = 0; page < totalPages; page++) {
            try {
                Pageable pageRequest = PageRequest.of(page, batchSize);
                Page<ImmatriculationRecuperee> pageData = immatriculationRecupereeRepository.findAll(pageRequest);
                processRecupereeBatch(pageData.getContent(), processName, page + 1, totalPages);

                if (page % 10 == 0) {
                    System.gc();
                }
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("Erreur lors du traitement du lot {} pour {}", page, processName, e);
                log.info("=================================================================");
            }
        }
    }

    private void processNewImmatBatch(List<Localimmatriculation> records, String processName,
                                      int currentPage, int totalPages) {
        log.info("=================================================================");
        log.info("{} - Traitement du lot {}/{}", processName, currentPage, totalPages);
        log.info("=================================================================");
        int successCount = 0;
        int errorCount = 0;
        for (Localimmatriculation record : records) {
            try {
                processNewImmatWithTransaction(record);
                successCount++;
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("{} - Erreur lors du traitement de l'immatriculation {} : {}",
                    processName, record.getId(), e.getMessage(), e);
                log.info("=================================================================");
                errorCount++;
            }
        }
        log.info("=================================================================");
        log.info("{} - Lot {}/{} termine. Succes: {}, Erreurs: {}",
            processName, currentPage, totalPages, successCount, errorCount);
        log.info("=================================================================");
    }

    private void processOldImmatBatch(List<OldImmatriculation> records, String processName,
                                      int currentPage, int totalPages) {
        log.info("=================================================================");
        log.info("{} - Traitement du lot {}/{}", processName, currentPage, totalPages);
        log.info("=================================================================");
        int successCount = 0;
        int errorCount = 0;
        for (OldImmatriculation record : records) {
            try {
                processOldImmatWithTransaction(record);
                successCount++;
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("{} - Erreur lors du traitement de l'immatriculation {} : {}",
                    processName, record.getNumeroUnique(), e.getMessage(), e);
                log.info("=================================================================");
                errorCount++;
            }
        }
        log.info("=================================================================");
        log.info("{} - Lot {}/{} termine. Succes: {}, Erreurs: {}",
            processName, currentPage, totalPages, successCount, errorCount);
        log.info("=================================================================");
    }

    private void processRecupereeBatch(List<ImmatriculationRecuperee> records, String processName,
                                       int currentPage, int totalPages) {
        log.info("=================================================================");
        log.info("{} - Traitement du lot {}/{}", processName, currentPage, totalPages);
        log.info("=================================================================");
        int successCount = 0;
        int errorCount = 0;
        for (ImmatriculationRecuperee record : records) {
            try {
                processRecupereeWithTransaction(record);
                successCount++;
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("{} - Erreur lors du traitement de l'immatriculation {} : {}",
                    processName, record.getNumeroUnique(), e.getMessage(), e);
                log.info("=================================================================");
                errorCount++;
            }
        }
        log.info("=================================================================");
        log.info("{} - Lot {}/{} termine. Succes: {}, Erreurs: {}",
            processName, currentPage, totalPages, successCount, errorCount);
        log.info("=================================================================");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processNewImmatWithTransaction(Localimmatriculation record) {
        if (record != null) {
            try {
                NouvelleImmatriculation ni = new NouvelleImmatriculation(record);
                insertINouvelleImmatriculation(ni);
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("Erreur lors du traitement de l'immatriculation {}: {}",
                    record.getId(), e.getMessage(), e);
                log.info("=================================================================");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processOldImmatWithTransaction(OldImmatriculation record) {
        if (record != null) {
            try {
                ImmatriculationRecuperee ir = new ImmatriculationRecuperee(record);
                insertImmatriculationRecuperee(ir);
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("Erreur lors du traitement de l'immatriculation {}: {}",
                    record.getNumeroUnique(), e.getMessage(), e);
                log.info("=================================================================");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processRecupereeWithTransaction(ImmatriculationRecuperee record) {
        if (record != null) {
            try {
                NouvelleImmatriculation ni = mapOldToNewImmatriculation(record);
                if (ni != null) {
                    insertINouvelleImmatriculation(ni);
                }
            } catch (Exception e) {
                log.info("=================================================================");
                log.error("Erreur lors du traitement de l'immatriculation {}: {}",
                    record.getNumeroUnique(), e.getMessage(), e);
                log.info("=================================================================");
            }
        }
    }


    @GetMapping("/migrate-new-immats")
    @Async
    public void migrateNewImmats(Pageable pageable) {
        try {
            localimmatriculationRepository.findAll(pageable).stream()
                .map(NouvelleImmatriculation::new)
                .collect(Collectors.toList())
                .forEach(this::insertINouvelleImmatriculation);
        } catch (Exception e) {
            System.err.println("Exception lors de la migration local vers nouvelle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/migrate-old-immats")
    @Async
    public void migrateOldImmats(Pageable pageable) {
        try {
            oldImmatriculationRepository.findAll(pageable).stream()
                .map(ImmatriculationRecuperee::new)
                .collect(Collectors.toList())
                .forEach(this::insertImmatriculationRecuperee);
        } catch (Exception e) {
            System.err.println("Exception lors de la migration old vers recuperee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<String> getKeys(JSONObject jsonObject, List<String> list) {
        try {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.toLowerCase().contains("date")) {
                    list.add(key);
                } else {
                    if (jsonObject.get(key) instanceof JSONObject) {
                        list.addAll(getKeys((JSONObject) jsonObject.get(key), new ArrayList<>()));
                    } else if (jsonObject.get(key) instanceof JSONArray) {
                        list.addAll(getKeys(((JSONArray) jsonObject.get(key)).getJSONObject(0), new ArrayList<>()));
                    } else {
                        list.add(key);
                    }
                }

            }
        } catch (JSONException e) {
            System.err.println("getKeys Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

   @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertImmatriculationRecuperee(ImmatriculationRecuperee ir) {
//        System.out.println("ir ==> " + ir);
        String sql = "INSERT INTO immatriculation_recuperee (agence_css, agence_ipres, agent_id, created_at, date, date_traitement, extras_info, mandat_file, motif, moyen_confirmation, ninea, ninea_file, numero_css, numero_identifiant, numero_ipres, numero_unique, payload, raison_sociale, rc_file, registre_commerce, sector_css, sector_ipres, status, statusdesc, taux_at, type_identifiant, user_id, zone_css, zone_ipres) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        entityManager.createNativeQuery(sql).setParameter(1, ir.getAgenceCss()).setParameter(2, ir.getAgenceIpres()).setParameter(3, ir.getAgentId()).setParameter(4, ir.getCreatedAt()).setParameter(5, ir.getDate()).setParameter(6, ir.getDateTraitement()).setParameter(7, ir.getExtrasInfo()).setParameter(8, ir.getMandatFile()).setParameter(9, ir.getMotif()).setParameter(10, ir.getMoyenConfirmation()).setParameter(11, ir.getNinea()).setParameter(12, ir.getNineaFile()).setParameter(13, ir.getNumeroCss()).setParameter(14, ir.getNumeroIdentifiant()).setParameter(15, ir.getNumeroIpres()).setParameter(16, ir.getNumeroUnique()).setParameter(17, ir.getPayload()).setParameter(18, ir.getRaisonSociale()).setParameter(19, ir.getRcFile()).setParameter(20, ir.getRegistreCommerce()).setParameter(21, ir.getSectorCss()).setParameter(22, ir.getSectorIpres()).setParameter(23, ir.getStatus()).setParameter(24, ir.getStatusdesc()).setParameter(25, ir.getTauxAt()).setParameter(26, ir.getTypeIdentifiant()).setParameter(27, ir.getUserId()).setParameter(28, ir.getZoneCss()).setParameter(29, ir.getZoneIpres()).executeUpdate();
    }

   @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertINouvelleImmatriculation(NouvelleImmatriculation ni) {
//        System.out.println("ni ==> " + ni);
        String sql = "insert into nouvelle_immatriculation (agence_css, agence_ipres, created_at, employee_registration_form_id, employer_registration_form_id, is_submit, ninea, numdoc, numero_css, numero_ipres, numero_unique, payload, raison_sociale, registre_commerce, sector_css, sector_ipres, statusdesc, statusdoc, taux_at, type, type_identifiant, user, zone_css, zone_ipres) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        entityManager.createNativeQuery(sql)
            .setParameter(1, ni.getAgenceCss())
            .setParameter(2, ni.getAgenceIpres())
            .setParameter(3, ni.getCreatedAt())
            .setParameter(4, ni.getEmployeeRegistrationFormId())
            .setParameter(5, ni.getEmployerRegistrationFormId())
            .setParameter(6, ni.isIsSubmit())
            .setParameter(7, ni.getNinea())
            .setParameter(8, ni.getNumdoc())
            .setParameter(9, ni.getNumeroCss())
            .setParameter(10, ni.getNumeroIpres())
            .setParameter(11, ni.getNumeroUnique())
            .setParameter(12, ni.getPayload())
            .setParameter(13, ni.getRaisonSociale())
            .setParameter(14, ni.getRegistreCommerce())
            .setParameter(15, ni.getNumeroIpres())
            .setParameter(16, ni.getSectorCss())
            .setParameter(17, ni.getSectorIpres())
            .setParameter(18, ni.getStatusdoc())
            .setParameter(19, ni.getTauxAt())
            .setParameter(20, ni.getType())
            .setParameter(21, ni.getTypeIdentifiant())
            .setParameter(22, ni.getUser())
            .setParameter(23, ni.getZoneCss())
            .setParameter(24, ni.getZoneIpres())
            .executeUpdate();
        if (getServerCheck() != null && getServerCheck().getEtat().equalsIgnoreCase("UP")) {
            if ("IESV".equals(ni.getStatusdoc()) && StringUtils.isEmpty(ni.getNumeroUnique())) {
                nouvelleImmatriculationService.getMyUniqueNumber(ni);
            }
        }

    }

    ServerCheck getServerCheck() {
        return serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
    }

    @GetMapping("/migrate-from-recuperee-to-new-immats")
    @Async
    public void migrateFromOldToNewImmats(Pageable pageable) {
        immatriculationRecupereeRepository.findAll(pageable).stream()
            .map(this::mapOldToNewImmatriculation)
            .forEach(this::insertINouvelleImmatriculation);
    }

    private NouvelleImmatriculation mapOldToNewImmatriculation(ImmatriculationRecuperee oldImmat) {
        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();

        try{
            nouvelleImmatriculation.setAgenceCss(oldImmat.getAgenceCss());
            nouvelleImmatriculation.setAgenceIpres(oldImmat.getAgenceIpres());
            nouvelleImmatriculation.setCreatedAt(oldImmat.getCreatedAt());
            nouvelleImmatriculation.setNinea(oldImmat.getNinea());
            nouvelleImmatriculation.setNumeroUnique(oldImmat.getNumeroUnique());
            nouvelleImmatriculation.setPayload(oldImmat.getPayload());
            nouvelleImmatriculation.setRaisonSociale(oldImmat.getRaisonSociale());
            nouvelleImmatriculation.setRegistreCommerce(oldImmat.getRegistreCommerce());
            nouvelleImmatriculation.setSectorCss(oldImmat.getSectorCss());
            nouvelleImmatriculation.setSectorIpres(oldImmat.getSectorIpres());
            nouvelleImmatriculation.setStatusdesc(oldImmat.getStatusdesc());
            nouvelleImmatriculation.setStatusdoc(Objects.equals(oldImmat.getStatus(), "ACTIVEE") ?"IESV":oldImmat.getStatus());
            nouvelleImmatriculation.setTauxAt(oldImmat.getTauxAt());
            nouvelleImmatriculation.setTypeIdentifiant(oldImmat.getTypeIdentifiant());
            nouvelleImmatriculation.setUser(oldImmat.getUserId());
            nouvelleImmatriculation.setZoneCss(oldImmat.getZoneCss());
            nouvelleImmatriculation.setZoneIpres(oldImmat.getZoneIpres());
            nouvelleImmatriculation.setType("IMMATPORTAIL");

            return nouvelleImmatriculation;
        } catch (Exception e) {
            System.err.println("Exception lors de la migration recuperee vers nouvelle: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
