package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.*;
import com.secusociale.portail.repository.DeclarationRepository;
import com.secusociale.portail.service.JournalDeclarationService;
import com.secusociale.portail.service.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JournalDeclarationResource {

    private final Logger log = LoggerFactory.getLogger(JournalDeclarationResource.class);
    private final JournalDeclarationService journalDeclarationService;
    private final DeclarationRepository declarationRepository;

    public JournalDeclarationResource(JournalDeclarationService journalDeclarationService, DeclarationRepository declarationRepository) {
        this.journalDeclarationService = journalDeclarationService;
        this.declarationRepository = declarationRepository;
    }

    @GetMapping("/journal-declaration")
    public Object getAllHistoriqueDeclaration() {
        log.debug("REST request to get historique declaration : {}");
        try {
            List<JournalDeclaration> historique = journalDeclarationService.getAllHistoriqueDeclaration();
            return ApiResponse.success(historique);
        } catch (Exception e) {
            return ApiResponse.error400("Erreur lors de la récupération de l'historique: " + e.getMessage());
        }
    }

    @GetMapping("/journal-declaration/historique/{id}")
    public Object getHistoriqueDeclaration(@PathVariable Long id) {
        log.debug("REST request to get historique immatriculation : {}", id);
        try {
            List<JournalDeclarationDTO> historique = journalDeclarationService.getHistoriqueDeclaration(id);
            return ApiResponse.success(historique);
        } catch (Exception e) {
            return ApiResponse.error400("Erreur lors de la récupération de l'historique: " + e.getMessage());
        }
    }

        @GetMapping("/journal-new-declaration/{id}")
    public Object getHistoriqueNewDeclaration(@PathVariable Long id) {
        log.debug("REST request to get historique immatriculation : {}", id);
        try {
           // List<JournalDeclarationDTO> historique = journalDeclarationService.getHistoriqueDeclaration(id);
            Optional<Declaration> historique = declarationRepository.findById(id);
            return ApiResponse.success(historique);
        } catch (Exception e) {
            return ApiResponse.error400("Erreur lors de la récupération de l'historique: " + e.getMessage());
        }
    }
}
