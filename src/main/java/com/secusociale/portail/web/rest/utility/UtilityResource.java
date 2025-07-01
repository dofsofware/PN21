package com.secusociale.portail.web.rest.utility;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.domain.enumeration.TypeJobName;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.DeclarationRepository;
import com.secusociale.portail.repository.JournalJobRepository;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.AsyncJobLauncher;
import com.secusociale.portail.service.dto.DMTDTO;
import com.secusociale.portail.service.immatriculation.AgencesRattachementService;
import com.secusociale.portail.service.soap.agencesRattachement.AGENCESEMPLOYEURSERVICE;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UtilityResource {

    private final Logger log = LoggerFactory.getLogger(UtilityResource.class);

    private final AgencesRattachementService agencesRattachementService;
    private final AgenceRepository agenceRepository;
    private final ImmatriculationRecupereeService oldImmatriculationService;
    private final NouvelleImmatriculationService nouvelleImmatriculationService;
    private final DeclarationRepository declarationRepository;

    private final DMTService dmtService;

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    @Autowired
    private DeclarationService declarationService;

    @Autowired
    private DemandeServiceService demandeServiceService;

    @Autowired
    private JournalJobService journalJobService;

    @Autowired
    private JournalJobRepository journalJobRepository;

    private final AsyncJobLauncher asyncJobLauncher;

    public UtilityResource(AgencesRattachementService agencesRattachementService, AgenceRepository agenceRepository, ImmatriculationRecupereeService oldImmatriculationService, NouvelleImmatriculationService nouvelleImmatriculationService, DeclarationRepository declarationRepository, DMTService dmtService, JournalJobRepository journalJobRepository, AsyncJobLauncher asyncJobLauncher) {
        this.agencesRattachementService = agencesRattachementService;
        this.agenceRepository = agenceRepository;
        this.oldImmatriculationService = oldImmatriculationService;
        this.nouvelleImmatriculationService = nouvelleImmatriculationService;
        this.declarationRepository = declarationRepository;
        this.dmtService = dmtService;
        this.journalJobRepository = journalJobRepository;
        this.asyncJobLauncher = asyncJobLauncher;
    }

    @GetMapping("/sendServices")
    @Transactional
    @Async
    public void sendDemandeServiceToPSRM() {
        this.demandeServiceService.sendDemandeServiceToPSRM();
    }

    @GetMapping("/sendDmts")
    @Transactional
    @Async
    public void sendDMTToPSRM() {
        this.dmtService.sendDmtToPSRMJob();
    }

    @GetMapping("/sendOneDmt/{id}")
    public DMTDTO getDMT(@PathVariable Long id) {
        return this.dmtService.sendOnDmtToPSRM(id);
    }


//    @GetMapping("/sendDeclarations")
//    @Transactional
//    @Async
//    public void validationDesDeclarationsTrimestriellesSoumises() {
//        this.declarationService.validationDesDeclarationsTrimestriellesSoumisesManuelle();
//    }

    @GetMapping("/sendImmatriculations")
    @Transactional
    @Async
    public void sendImmatriculationsToPCRM() {
        this.nouvelleImmatriculationService.sendImmatriculationsToPCRM();
    }

    @GetMapping("/reSendDeclarationFiles")
    @Transactional
    @Async
    public ResponseEntity<HashMap<String, Object>> reSendDeclarationFiles() {
        return ResponseEntity.ok(this.declarationService.sendNonSoumiseDeclarationFilesManuel());
    }

    @PostMapping("/declarations/reSendMe")
    @Transactional
    public ResponseEntity<Declaration> reSendMe(@RequestBody Declaration declaration) {
        return ResponseEntity.ok(this.declarationService.reSendMe(declaration));
    }

    @GetMapping("/populate-agences")
    @Transactional
    //Every 7 days at noon
    @Scheduled(cron = "0 0 12 */7 * ?")
    public void populateAgences() {
        oldImmatriculationService.findAll().forEach(oi -> {
            if (!StringUtils.isEmpty(oi.getNumeroUnique())) {
                try {
                    List<AGENCESEMPLOYEURSERVICE.Output> results = agencesRattachementService.getAgencesRattachement(oi.getNumeroUnique()).value.getOutput();
                    results.forEach(output -> {
                        if (!StringUtils.isEmpty(output.getInstitution())) {
                            if (!StringUtils.isEmpty(output.getCodeAgence())) {
                                Agence agence = new Agence();
                                if (!agenceRepository.findByCodeAndInstitution(output.getCodeAgence(), output.getInstitution()).isPresent()) {
                                    agence.code(output.getCodeAgence())
                                        .institution(output.getInstitution())
                                        .adresse(output.getAdresseAgence())
                                        .nom(output.getNomAgence())
                                        .description(StringUtils.isEmpty(output.getTelephoneAgence()) ? null : "Tel: " + output.getTelephoneAgence());
                                    agenceRepository.save(agence);
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @GetMapping("/set-agences")
    @Scheduled(cron = "0 0 12 */7 * ?")
    void setAgences() {
        oldImmatriculationService.findAll().forEach(oi -> {
            if (!StringUtils.isEmpty(oi.getNumeroUnique())) {
                try {
                    List<AGENCESEMPLOYEURSERVICE.Output> results = agencesRattachementService.getAgencesRattachement(oi.getNumeroUnique()).value.getOutput();
                    results.forEach(output -> {
                        if (output.getInstitution().equalsIgnoreCase("CSS")) {
                            oi.setAgenceCss(output.getCodeAgence());
                        }
                        if (output.getInstitution().equalsIgnoreCase("IPRES")) {
                            oi.setAgenceIpres(output.getCodeAgence());
                        }
                    });
                    oldImmatriculationService.save(oi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @GetMapping("/set-agences-declaration")
    @Scheduled(cron = "0 0 12 */7 * ?")
    void setAgencesDeclaration() {
        declarationRepository.findAll().forEach(oi -> {
            if (!StringUtils.isEmpty(oi.getNumeroUnique())) {
                try {
                    List<AGENCESEMPLOYEURSERVICE.Output> results = agencesRattachementService.getAgencesRattachement(oi.getNumeroUnique()).value.getOutput();
                    results.forEach(output -> {
                        if (output.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!StringUtils.isEmpty(oi.getCodeAgenceCSS()))
                                oi.setCodeAgenceCSS(output.getCodeAgence());
                        }
                        if (output.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!StringUtils.isEmpty(oi.getCodeAgenceIPRES()))
                                oi.setCodeAgenceIPRES(output.getCodeAgence());
                        }
                    });
                    if (oi.getStatus().equalsIgnoreCase("TRAITER")) {
                        if (oi.isIsUploaded()) {
                            String pfid = StringUtils.isEmpty(oi.getProcessFlowId()) ? "" : oi.getProcessFlowId();
                            if (!pfid.equalsIgnoreCase(oi.getDetails()[0].getID_DNS())) {
                                oi.setProcessFlowId(oi.getDetails()[0].getID_DNS());
                            }
                        }
                    }
                    declarationRepository.save(oi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @GetMapping("/check-PSRM")
    public Object checkPSRM() {
        HashMap<String, Object> resultat = new HashMap<>();
        ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
        if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
            resultat.put("etat", "DOWN");
            resultat.put("code", "500");
            resultat.put("message", "Le serveur PSRM semble etre indisponible");
        } else {
            resultat.put("etat", "UP");
            resultat.put("code", "200");
            resultat.put("message", "Le serveur PSRM semble etre disponible");
        }

        return ResponseEntity.ok().body(resultat);
    }

    @GetMapping("/send-job/{type}")
    @Transactional
    public ResponseEntity<String> synchroniseAllDeclaration(@PathVariable String type) {
        try {
            TypeJobName jobType = TypeJobName.valueOf(type);

            if (TypeJobName.SEND_INVOICES_TO_PSRM.equals(jobType)) {
                asyncJobLauncher.runSendAllSynchronisationDelaration(declarationService, jobType);
            } else if (TypeJobName.sendImmatriculations.equals(jobType)) {
                asyncJobLauncher.runSendImmatriculations(nouvelleImmatriculationService);
            } else {
                throw new IllegalArgumentException("Type non reconnu : " + type);
            }

            //Retour immédiat sans attendre la fin du traitement
            return ResponseEntity.ok("Batch lancé avec succès.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/is-job-running/{type}")
    public ResponseEntity<Map<String, Boolean>> isRunning(@PathVariable String type) {
        try {
            TypeJobName jobType = TypeJobName.valueOf(type);
            boolean isRunning = declarationService.isRunning(jobType);

            // Créer une Map pour encapsuler le résultat
            Map<String, Boolean> response = new HashMap<>();
            response.put("running", isRunning);

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Retourner false dans un format JSON
            Map<String, Boolean> response = new HashMap<>();
            response.put("running", false);
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/interrupt/{nom}/{uuid}")
    public ResponseEntity<String> stopJobByTypeAndUuid(@PathVariable String nom, @PathVariable String uuid) {
        try {
            String result = declarationService.stopSynchronisationDelaration(uuid);
            return ResponseEntity.ok("Résultat de l'arrêt : " + result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
}
