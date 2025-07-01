package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.JournalImmatriculationDTO;
import com.secusociale.portail.service.JournalImmatriculationService;
import com.secusociale.portail.service.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JournalImmatriculationResource {

    private final Logger log = LoggerFactory.getLogger(JournalImmatriculationResource.class);
    private final JournalImmatriculationService journalImmatriculationService;

    public JournalImmatriculationResource(JournalImmatriculationService journalImmatriculationService) {
        this.journalImmatriculationService = journalImmatriculationService;
    }

    @GetMapping("/journal-immat/historique/{id}")
    public Object getHistoriqueImmatriculation(@PathVariable Long id) {
        log.debug("REST request to get historique immatriculation : {}", id);
        try {
            List<JournalImmatriculationDTO> historique = journalImmatriculationService.getHistoriqueImmatriculation(id);
            return ApiResponse.success(historique);
        } catch (Exception e) {
            return ApiResponse.error400("Erreur lors de la récupération de l'historique: " + e.getMessage());
        }
    }
}
