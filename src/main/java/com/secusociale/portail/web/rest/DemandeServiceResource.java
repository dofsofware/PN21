package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.DemandeService;
import com.secusociale.portail.domain.Employeur;
import com.secusociale.portail.repository.EmployeurRepository;
import com.secusociale.portail.service.DemandeServiceQueryService;
import com.secusociale.portail.service.DemandeServiceService;
import com.secusociale.portail.service.dto.DemandeServiceCriteria;
import com.secusociale.portail.service.dto.DemandeServiceDTO;
import com.secusociale.portail.service.utils.UtilsService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.DemandeService}.
 */
@RestController
@RequestMapping("/api")
public class DemandeServiceResource {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceResource.class);

    private static final String ENTITY_NAME = "demandeService";

    private final UtilsService utilsService;

    private final EmployeurRepository employeurRepository;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeServiceService demandeServiceService;

    private final DemandeServiceQueryService demandeServiceQueryService;


    public DemandeServiceResource(DemandeServiceService demandeServiceService, DemandeServiceQueryService demandeServiceQueryService, UtilsService utilsService, EmployeurRepository employeurRepository) {
        this.demandeServiceService = demandeServiceService;
        this.demandeServiceQueryService = demandeServiceQueryService;
        this.utilsService = utilsService;
        this.employeurRepository = employeurRepository;
    }

    /**
     * {@code POST  /demande-services} : Create a new demandeService.
     *
     * @param demandeService the demandeService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandeService, or with status {@code 400 (Bad Request)} if the demandeService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demande-services")
    public ResponseEntity<DemandeService> createDemandeService(@RequestBody DemandeService demandeService) throws URISyntaxException {
        log.debug("REST request to save DemandeService : {}", demandeService);
        if (demandeService.getId() != null) {
            throw new BadRequestAlertException("A new demandeService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        demandeService.setStatut("EN_ATTENTE");
        demandeService.setCreatedAt(Instant.now());
        List<String> statuses = new ArrayList<>();
        statuses.add("EN_ATTENTE");
//        statuses.add("GCOMPTE");
        if (demandeServiceService.hasAtLeastAPendingOne(demandeService.getIdDossier(), demandeService.getTypeDemande(), statuses)) {
            throw Problem.builder()
                .withTitle("Demande en attente!")
                .withStatus(Status.BAD_REQUEST)
                .withDetail("vous avez déjà une demande de service en attente!")
                .with("message", "vous avez déjà une demande de service en attente!")
                .build();
        }
        DemandeService result = demandeServiceService.save(demandeService);
        return ResponseEntity.created(new URI("/api/demande-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demande-services} : Updates an existing demandeService.
     *
     * @param demandeService the demandeService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandeService,
     * or with status {@code 400 (Bad Request)} if the demandeService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandeService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demande-services")
    public ResponseEntity<DemandeService> updateDemandeService(@RequestBody DemandeService demandeService) throws URISyntaxException {
        log.debug("REST request to update DemandeService : {}", demandeService);
        if (demandeService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (demandeService.getCreatedAt() == null) {
            demandeService.setCreatedAt(Instant.now());
        }
        if(demandeService.getStatut().equals("VALIDE")){
            if (demandeService.getTypeDemande().equalsIgnoreCase("attestation régularité")){
                try {
                    Employeur emp = employeurRepository.findById(demandeService.getEmployeur()).orElse(null);
                    demandeService.setFile(utilsService.generateAttestationDeRegularite(emp));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        DemandeService result = demandeServiceService.save(demandeService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandeService.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demande-services} : get all the demandeServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeServices in body.
     */
    @GetMapping("/demande-services")
    public ResponseEntity<HashMap<String, Object>> getAllDemandeServices(
        @RequestParam(required = false, name = "dateQueries") String dateQueries,
        Pageable pageable,
        DemandeServiceCriteria criteria) {
        log.debug("REST request to get a page of DemandeServices");

        // Tri par date (ici tri décroissant, du plus récent au plus ancien)
        Pageable sortedPageable = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(Sort.Order.desc("createdAt"))
        );

        // Récupérer la page de demandes triées par date avec critères
        Page<DemandeServiceDTO> page = demandeServiceQueryService.findByCriteria(criteria, pageable);

        // Construction des informations de pagination
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());

        // Création de la réponse
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @GetMapping("/demande-services/numero-unique/{numeroUnique}")
    public ResponseEntity<List<DemandeService>> getAllDemandeServicesByUnikNumber(@PathVariable String numeroUnique, Pageable pageable) {
        log.debug("REST request to get a page of DemandeServices by numero Unique");
        Page<DemandeService> page = demandeServiceService.findAllByUniqueNumber(numeroUnique, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/demande-services/mines")
    public ResponseEntity<List<DemandeService>> getAllMyDemandeServices(Pageable pageable) {
        log.debug("REST request to get a page of DemandeServices");
        Page<DemandeService> page = demandeServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /demande-services/:id} : get the "id" demandeService.
     *
     * @param id the id of the demandeService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandeService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demande-services/{id}")
    public ResponseEntity<DemandeService> getDemandeService(@PathVariable Long id) {
        log.debug("REST request to get DemandeService : {}", id);
        Optional<DemandeService> demandeService = demandeServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeService);
    }


    /**
     * {@code GET  /demande-services/:id} : get the "id" demandeService.
     *
     * @param iDemployeur the id of the demandeService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandeService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demande-services/{iDemployeur}/employeur")
    public ResponseEntity<List<DemandeService>> getDemandeServicesByEmployeur(@PathVariable Long iDemployeur) {
        log.debug("REST request to get DemandeService : {}", iDemployeur);
        List<DemandeService> demandeServices = demandeServiceService.findDemandesByEmployeur(iDemployeur);
        return new ResponseEntity<>(demandeServices, HttpStatus.OK);
    }

    @GetMapping("/demande-services/{userId}/user")
    public ResponseEntity<List<DemandeService>> getDemandeServicesByUserId(@PathVariable Long userId) {
        log.debug("REST request to get DemandeService : {}", userId);
        List<DemandeService> demandeServices = demandeServiceService.findDemandesByEmployeur(userId);
        return new ResponseEntity<>(demandeServices, HttpStatus.OK);
    }

    /**
     * {@code DELETE  /demande-services/:id} : delete the "id" demandeService.
     *
     * @param id the id of the demandeService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demande-services/{id}")
    public ResponseEntity<Void> deleteDemandeService(@PathVariable Long id) {
        log.debug("REST request to delete DemandeService : {}", id);
        demandeServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //sendOrUpdateDemandeService

    @GetMapping("/demande-services/send-update/{id}")
    public ResponseEntity<DemandeService> sendOrUpdateDemandeService(@PathVariable Long id) {
        log.debug("REST request to get DemandeService : {}", id);
        Optional<DemandeService> demandeService = demandeServiceService.sendOrUpdateDemandeService(id);
        return ResponseUtil.wrapOrNotFound(demandeService);
    }

}
