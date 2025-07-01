package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.DeclarationService;
import com.secusociale.portail.service.NouvelleImmatriculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pcrm-testeur")
public class PCRMTesterRessource {

    private final Logger log = LoggerFactory.getLogger(PCRMTesterRessource.class);

    private final NouvelleImmatriculationService nouvelleImmatriculationService;
    private final DeclarationService declarationService;

    public PCRMTesterRessource(NouvelleImmatriculationService nouvelleImmatriculationService, DeclarationService declarationService) {
        this.nouvelleImmatriculationService = nouvelleImmatriculationService;
        this.declarationService = declarationService;
    }

    /**
     * Point de terminaison pour déclencher manuellement la méthode de changement de statut d'immatriculation
     */
    @PostMapping("/changer-statut-immatriculation")
    public ResponseEntity<String> testerChangerStatutImmatriculation() {
        log.info("Requête REST pour déclencher manuellement le changement de statut d'immatriculation");
        try {
            nouvelleImmatriculationService.changeImmatriculationStatus();
            return ResponseEntity.ok("Tâche de changement de statut d'immatriculation exécutée avec succès");
        } catch (Exception e) {
            log.error("Erreur lors de l'exécution du changement de statut d'immatriculation", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de l'exécution de la tâche de changement de statut : " + e.getMessage());
        }
    }

    /**
     * Point de terminaison pour déclencher manuellement la méthode d'obtention du numéro unique
     */
    @PostMapping("/obtenir-numero-unique")
    public ResponseEntity<String> testerObtentionNumeroUnique() {
        log.info("Requête REST pour déclencher manuellement l'obtention du numéro unique");
        try {
            nouvelleImmatriculationService.getUniqueNumber();
            return ResponseEntity.ok("Votre demande est en cours de traitement");
        } catch (Exception e) {
            log.error("Erreur lors de l'exécution de l'obtention du numéro unique", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de l'exécution de la tâche d'obtention du numéro unique : " + e.getMessage());
        }
    }

    /**
     * Point de terminaison pour déclencher manuellement l'envoi des immatriculations à PCRM
     */
    @PostMapping("/envoyer-immatriculations")
    public ResponseEntity<String> testerEnvoyerImmatriculationsPCRM() {
        log.info("Requête REST pour déclencher manuellement l'envoi des immatriculations");
        try {
            nouvelleImmatriculationService.sendImmatriculationsToPCRMJob();
            return ResponseEntity.ok("Tâche d'envoi des immatriculations exécutée avec succès");
        } catch (Exception e) {
            log.error("Erreur lors de l'exécution de l'envoi des immatriculations", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de l'exécution de la tâche d'envoi des immatriculations : " + e.getMessage());
        }
    }

    /**
     * Point de terminaison pour déclencher manuellement la validation des déclarations par PCRM
     */
    @PostMapping("/validate-declaration")
    public ResponseEntity<String> validationDesDeclarationsTrimestriellesSoumises() {
        log.info("Requête REST pour déclencher manuellement la validationDesDeclarationsTrimestriellesSoumises");
        try {
            declarationService.validationDesDeclarationsTrimestriellesSoumises();
            return ResponseEntity.ok("validationDesDeclarationsTrimestriellesSoumises envoyer avec succès");
        } catch (Exception e) {
            log.error("Erreur lors de l'exécution de la validationDesDeclarationsTrimestriellesSoumises", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de l'exécution de la validationDesDeclarationsTrimestriellesSoumises : " + e.getMessage());
        }
    }
}
