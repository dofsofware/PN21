package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.SuiviJobService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.SuiviJobDTO;
import com.secusociale.portail.service.dto.SuiviJobCriteria;
import com.secusociale.portail.service.SuiviJobQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.SuiviJob}.
 */
@RestController
@RequestMapping("/api")
public class SuiviJobResource {

    private final Logger log = LoggerFactory.getLogger(SuiviJobResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2SuiviJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuiviJobService suiviJobService;

    private final SuiviJobQueryService suiviJobQueryService;

    public SuiviJobResource(SuiviJobService suiviJobService, SuiviJobQueryService suiviJobQueryService) {
        this.suiviJobService = suiviJobService;
        this.suiviJobQueryService = suiviJobQueryService;
    }

    /**
     * {@code POST  /suivi-jobs} : Create a new suiviJob.
     *
     * @param suiviJobDTO the suiviJobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suiviJobDTO, or with status {@code 400 (Bad Request)} if the suiviJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suivi-jobs")
    public ResponseEntity<SuiviJobDTO> createSuiviJob(@RequestBody SuiviJobDTO suiviJobDTO) throws URISyntaxException {
        log.debug("REST request to save SuiviJob : {}", suiviJobDTO);
        if (suiviJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new suiviJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuiviJobDTO result = suiviJobService.save(suiviJobDTO);
        return ResponseEntity.created(new URI("/api/suivi-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /suivi-jobs} : Updates an existing suiviJob.
     *
     * @param suiviJobDTO the suiviJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suiviJobDTO,
     * or with status {@code 400 (Bad Request)} if the suiviJobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suiviJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/suivi-jobs")
    public ResponseEntity<SuiviJobDTO> updateSuiviJob(@RequestBody SuiviJobDTO suiviJobDTO) throws URISyntaxException {
        log.debug("REST request to update SuiviJob : {}", suiviJobDTO);
        if (suiviJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuiviJobDTO result = suiviJobService.save(suiviJobDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, suiviJobDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /suivi-jobs} : get all the suiviJobs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suiviJobs in body.
     */
    @GetMapping("/suivi-jobs")
    public ResponseEntity<List<SuiviJobDTO>> getAllSuiviJobs(SuiviJobCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SuiviJobs by criteria: {}", criteria);
        Page<SuiviJobDTO> page = suiviJobQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /suivi-jobs/count} : count all the suiviJobs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/suivi-jobs/count")
    public ResponseEntity<Long> countSuiviJobs(SuiviJobCriteria criteria) {
        log.debug("REST request to count SuiviJobs by criteria: {}", criteria);
        return ResponseEntity.ok().body(suiviJobQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /suivi-jobs/:id} : get the "id" suiviJob.
     *
     * @param id the id of the suiviJobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suiviJobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suivi-jobs/{id}")
    public ResponseEntity<SuiviJobDTO> getSuiviJob(@PathVariable Long id) {
        log.debug("REST request to get SuiviJob : {}", id);
        Optional<SuiviJobDTO> suiviJobDTO = suiviJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suiviJobDTO);
    }

    /**
     * {@code DELETE  /suivi-jobs/:id} : delete the "id" suiviJob.
     *
     * @param id the id of the suiviJobDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/suivi-jobs/{id}")
    public ResponseEntity<Void> deleteSuiviJob(@PathVariable Long id) {
        log.debug("REST request to delete SuiviJob : {}", id);
        suiviJobService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
