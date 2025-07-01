package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.GedUpdateService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.GedUpdateDTO;
import com.secusociale.portail.service.dto.GedUpdateCriteria;
import com.secusociale.portail.service.GedUpdateQueryService;

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
 * REST controller for managing {@link com.secusociale.portail.domain.GedUpdate}.
 */
@RestController
@RequestMapping("/api")
public class GedUpdateResource {

    private final Logger log = LoggerFactory.getLogger(GedUpdateResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2GedUpdate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GedUpdateService gedUpdateService;

    private final GedUpdateQueryService gedUpdateQueryService;

    public GedUpdateResource(GedUpdateService gedUpdateService, GedUpdateQueryService gedUpdateQueryService) {
        this.gedUpdateService = gedUpdateService;
        this.gedUpdateQueryService = gedUpdateQueryService;
    }

    /**
     * {@code POST  /ged-updates} : Create a new gedUpdate.
     *
     * @param gedUpdateDTO the gedUpdateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gedUpdateDTO, or with status {@code 400 (Bad Request)} if the gedUpdate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ged-updates")
    public ResponseEntity<GedUpdateDTO> createGedUpdate(@RequestBody GedUpdateDTO gedUpdateDTO) throws URISyntaxException {
        log.debug("REST request to save GedUpdate : {}", gedUpdateDTO);
        if (gedUpdateDTO.getId() != null) {
            throw new BadRequestAlertException("A new gedUpdate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GedUpdateDTO result = gedUpdateService.save(gedUpdateDTO);
        return ResponseEntity.created(new URI("/api/ged-updates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ged-updates} : Updates an existing gedUpdate.
     *
     * @param gedUpdateDTO the gedUpdateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gedUpdateDTO,
     * or with status {@code 400 (Bad Request)} if the gedUpdateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gedUpdateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ged-updates")
    public ResponseEntity<GedUpdateDTO> updateGedUpdate(@RequestBody GedUpdateDTO gedUpdateDTO) throws URISyntaxException {
        log.debug("REST request to update GedUpdate : {}", gedUpdateDTO);
        if (gedUpdateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GedUpdateDTO result = gedUpdateService.save(gedUpdateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gedUpdateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ged-updates} : get all the gedUpdates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gedUpdates in body.
     */
    @GetMapping("/ged-updates")
    public ResponseEntity<List<GedUpdateDTO>> getAllGedUpdates(GedUpdateCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GedUpdates by criteria: {}", criteria);
        Page<GedUpdateDTO> page = gedUpdateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ged-updates/count} : count all the gedUpdates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ged-updates/count")
    public ResponseEntity<Long> countGedUpdates(GedUpdateCriteria criteria) {
        log.debug("REST request to count GedUpdates by criteria: {}", criteria);
        return ResponseEntity.ok().body(gedUpdateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ged-updates/:id} : get the "id" gedUpdate.
     *
     * @param id the id of the gedUpdateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gedUpdateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ged-updates/{id}")
    public ResponseEntity<GedUpdateDTO> getGedUpdate(@PathVariable Long id) {
        log.debug("REST request to get GedUpdate : {}", id);
        Optional<GedUpdateDTO> gedUpdateDTO = gedUpdateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gedUpdateDTO);
    }

    /**
     * {@code DELETE  /ged-updates/:id} : delete the "id" gedUpdate.
     *
     * @param id the id of the gedUpdateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ged-updates/{id}")
    public ResponseEntity<Void> deleteGedUpdate(@PathVariable Long id) {
        log.debug("REST request to delete GedUpdate : {}", id);
        gedUpdateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
