package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.ContenuTP;
import com.secusociale.portail.domain.SalarieTP;
import com.secusociale.portail.domain.TempsDePresence;
import com.secusociale.portail.domain.enumeration.StatutTP;
import com.secusociale.portail.model.CheckFileModel;
import com.secusociale.portail.repository.ContenuTPRepository;
import com.secusociale.portail.repository.SalarieTPRepository;
import com.secusociale.portail.repository.TempsDePresenceRepository;
import com.secusociale.portail.service.ContenuTPService;
import com.secusociale.portail.service.TempsDePresenceService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.StatusUpdateRequest;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api/temps-de-presences")
@Transactional
public class TempsDePresenceResource {

    private static final Logger LOG = LoggerFactory.getLogger(TempsDePresenceResource.class);

    private static final String ENTITY_NAME = "jhipsterTempsDePresence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TempsDePresenceRepository tempsDePresenceRepository;
    private final SalarieTPRepository salarieTPRepository;
    private final ContenuTPRepository contenuTPRepository;
    private final ContenuTPService contenuTPService;
    private final TempsDePresenceService tempsDePresenceservice;

    public TempsDePresenceResource(ContenuTPService contenuTPService,ContenuTPRepository contenuTPRepository, TempsDePresenceService tempsDePresenceservice ,TempsDePresenceRepository tempsDePresenceRepository, SalarieTPRepository salarieTPRepository) {
        this.tempsDePresenceRepository = tempsDePresenceRepository;
        this.salarieTPRepository = salarieTPRepository;
        this.tempsDePresenceservice = tempsDePresenceservice;
        this.contenuTPRepository = contenuTPRepository;
        this.contenuTPService = contenuTPService;
    }


    @PostMapping("")
    public ApiResponse<TempsDePresence> createTempsDePresence(@Valid @RequestBody TempsDePresence tempsDePresence)
        throws URISyntaxException {
        LOG.debug("REST request to save TempsDePresence : {}", tempsDePresence);
        if (tempsDePresence.getId() != null) {
            throw new BadRequestAlertException("A new tempsDePresence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tempsDePresence = tempsDePresenceRepository.save(tempsDePresence);

        return ApiResponse.successResponse( ApiResponse.ResponseCode.OK, tempsDePresence);
    }

    @PutMapping("/update")
    public ApiResponse<TempsDePresence> updateTempsDePresence(
        @Valid @RequestBody TempsDePresence tempsDePresence
    ) throws URISyntaxException {
        Long id = tempsDePresence.getId();
        if (tempsDePresence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LOG.debug("REST request to update TempsDePresence : {}, {}", id, tempsDePresence);
        if (!Objects.equals(id, tempsDePresence.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tempsDePresenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tempsDePresence = tempsDePresenceRepository.save(tempsDePresence);

        return ApiResponse.successResponse( ApiResponse.ResponseCode.OK, tempsDePresence);
    }


    @PatchMapping(value = "/partial-update", consumes = { "application/json", "application/merge-patch+json" })
    public ApiResponse<TempsDePresence> partialUpdateTempsDePresence(
        @NotNull @RequestBody TempsDePresence tempsDePresence
    ) throws URISyntaxException {
        Long id = tempsDePresence.getId();
        if (tempsDePresence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LOG.debug("REST request to partial update TempsDePresence partially : {}, {}", id, tempsDePresence);
        if (!Objects.equals(id, tempsDePresence.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tempsDePresenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TempsDePresence> result = tempsDePresenceRepository
            .findById(tempsDePresence.getId())
            .map(existingTempsDePresence -> {
                if (tempsDePresence.getUserId() != null) {
                    existingTempsDePresence.setUserId(tempsDePresence.getUserId());
                }
                if (tempsDePresence.getNumeroUnique() != null) {
                    existingTempsDePresence.setNumeroUnique(tempsDePresence.getNumeroUnique());
                }
                if (tempsDePresence.getNumeroCss() != null) {
                    existingTempsDePresence.setNumeroCss(tempsDePresence.getNumeroCss());
                }
                if (tempsDePresence.getNumeroIpres() != null) {
                    existingTempsDePresence.setNumeroIpres(tempsDePresence.getNumeroIpres());
                }

                return existingTempsDePresence;
            })
            .map(tempsDePresenceRepository::save);

        return ApiResponse.successResponse( ApiResponse.ResponseCode.OK, tempsDePresence);
    }

    @GetMapping("")
    public ApiResponse<List<TempsDePresence>> getAllTempsDePresences() {
        LOG.debug("REST request to get all TempsDePresences");
        List<TempsDePresence> body = tempsDePresenceRepository.findAll();

        for (TempsDePresence tempsDePresence : body) {
            if (tempsDePresence != null && tempsDePresence.getFileTP() != null && tempsDePresence.getFileTP().getContenuTP() != null) {
                Set<SalarieTP> salarieTPList = salarieTPRepository.findAllByContenuTP(tempsDePresence.getFileTP().getContenuTP());
                tempsDePresence.getFileTP().getContenuTP().setSalarieTPs(salarieTPList);
            }
        }
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, body);
    }


    @GetMapping("/{id}")
    public ApiResponse<TempsDePresence> getTempsDePresence(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TempsDePresence : {}", id);
        TempsDePresence tempsDePresence = tempsDePresenceRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Aucun Temps de présence trouvé pour l'ID : " + id));

        if (tempsDePresence != null && tempsDePresence.getFileTP() != null && tempsDePresence.getFileTP().getContenuTP() != null) {
            Set<SalarieTP> salarieTPList = salarieTPRepository.findAllByContenuTP(tempsDePresence.getFileTP().getContenuTP());
            tempsDePresence.getFileTP().getContenuTP().setSalarieTPs(salarieTPList);
        }
        return ApiResponse.successResponse( ApiResponse.ResponseCode.OK, tempsDePresence);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTempsDePresence(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TempsDePresence : {}", id);
        tempsDePresenceRepository.deleteById(id);
        ResponseEntity<Void> body = ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
        return ApiResponse.successResponse( ApiResponse.ResponseCode.OK, null);
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/create-tp-from-file")
    public ResponseEntity<HashMap<String, Object>> createTPfromFile(@RequestBody CheckFileModel checkFileModel) {
        HashMap<String, Object> result = new HashMap<>();


        if (checkFileModel.getUserId() == null || checkFileModel.getEmployeur().isEmpty() || checkFileModel.getEmployeur() == null || checkFileModel.getFileEncoded() == null || checkFileModel.getNumeroUnique() == null) {
            result.put("code", "400");
            result.put("erreur", "Les champs requis sont manquants.");
            return ResponseEntity.ok(result);
        }

        try {
            ResponseEntity<HashMap<String, Object>> result1 = tempsDePresenceservice.validateFile(checkFileModel, result);
            if (result1 != null) return result1;

        } catch (IllegalArgumentException e) {
            result.put("code", "400");
            result.put("erreur", "Le fichier base64 est invalide: " + e.getMessage());
            return ResponseEntity.ok(result);
        } catch (IllegalStateException e) {
            result.put("code", "400");
            result.put("erreur", "Erreur de données: " + e.getMessage());
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            result.put("code", "500");
            result.put("erreur", "Erreur interne du serveur: " + e.getMessage());
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/simplified")
    public ApiResponse<Object> getAllContenuTPS(
        @RequestParam(name = "filter", required = false) String filter,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, size);

        if ("filetp-is-null".equals(filter)) {
            LOG.debug("REST request to get all ContenuTPs where fileTP is null with pagination");
            Page<ContenuTP> result = contenuTPRepository.findByFileTPIsNull(pageable);
            return ApiResponse.success(result);
        }

        LOG.debug("REST request to get all ContenuTPS with pagination");
        Page<ContenuTP> reponse = contenuTPRepository.findAll(pageable);
        return ApiResponse.success(reponse);
    }

    @PutMapping("/validate/{id}")
    public ApiResponse<Object> updateContenuTPStatus(
        @PathVariable Long id,
        @Valid @RequestBody StatusUpdateRequest statusUpdate
    ) {
        LOG.debug("REST request to update ContenuTP status : {}, {}", id, statusUpdate);

        if (statusUpdate.getStatut() == null) {
            return  ApiResponse.error400("Status ne doit pas être null");
        }

        if ((statusUpdate.getStatut() == StatutTP.RETOURNE || statusUpdate.getStatut() == StatutTP.REJETE)
            && (statusUpdate.getMotif() == null || statusUpdate.getMotif().trim().isEmpty())) {
            return  ApiResponse.error400("Motif obligatoire pour les statuts RETOURNE ou REJETE");
        }

        ContenuTP updatedContenuTP = contenuTPService.updateStatus(id, statusUpdate.getStatut(), statusUpdate.getMotif());
        return ApiResponse.success(updatedContenuTP);
    }

    @GetMapping({
        "/numero-unique/{numeroUnique}/trimestre/{trimestre}",
        "/numero-unique/{numeroUnique}/trimestre/{trimestre}/annee/{annee}"
    })
    public ApiResponse<Object> getTempsDePrésenceByNumeroUnique(
        @PathVariable("numeroUnique") String numeroUnique,
        @PathVariable("trimestre") Integer trimestre,
        @PathVariable(value = "annee", required = false) Integer annee,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        LOG.info("REST request to get ContenuTP by numeroUnique: {}, trimestre {} and annee: {}", numeroUnique, trimestre, annee);
        Map<String, Object>  pagination = new HashMap<>();
        pagination.put("size", size);
        pagination.put("page", page);

        List<ContenuTP> contenuTPList;

        if (annee != null) {
            contenuTPList = contenuTPRepository.findAllByNumeroUniqueAndAnneeAndTrimestre(
                Long.parseLong(numeroUnique),
                annee,
                trimestre
            );
        } else {
            contenuTPList = contenuTPRepository.findAllByNumeroUniqueAndTrimestre(
                Long.parseLong(numeroUnique),
                trimestre
            );
        }
        Map<String, Object>  reponse = new HashMap<>();
//        TODO faire la pagination sur le nombre de salarié
//        reponse.put("status","SUCCESS");
//        reponse.put("code",200);
//        reponse.put("pagination",pagination);
//        reponse.put("body",contenuTPList);
        return ApiResponse.success(contenuTPList);
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyTempsPresence(
        @RequestParam Integer annee,
        @RequestParam Integer trimestre,
        @RequestParam String nom,
        @RequestParam String prenom,
        @RequestParam String typePiece,
        @RequestParam String numeroPiece
    ) {
        boolean exists = contenuTPService.checkTempsPresenceExists(
            annee, trimestre, nom, prenom, typePiece, numeroPiece
        );
        return ResponseEntity.ok(exists);
    }

}
