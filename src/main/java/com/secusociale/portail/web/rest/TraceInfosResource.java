package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.TraceInfos;
import com.secusociale.portail.repository.TraceInfosRepository;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.TraceInfos}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TraceInfosResource {

    private final Logger log = LoggerFactory.getLogger(TraceInfosResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2TraceInfos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TraceInfosRepository traceInfosRepository;

    public TraceInfosResource(TraceInfosRepository traceInfosRepository) {
        this.traceInfosRepository = traceInfosRepository;
    }

    /**
     * {@code POST  /trace-infos} : Create a new traceInfos.
     *
     * @param traceInfos the traceInfos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new traceInfos, or with status {@code 400 (Bad Request)} if the traceInfos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trace-infos")
    public ResponseEntity<TraceInfos> createTraceInfos(@RequestBody TraceInfos traceInfos) throws URISyntaxException {
        log.debug("REST request to save TraceInfos : {}", traceInfos);
        if (traceInfos.getId() != null) {
            throw new BadRequestAlertException("A new traceInfos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TraceInfos result = traceInfosRepository.save(traceInfos);
        return ResponseEntity.created(new URI("/api/trace-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trace-infos} : Updates an existing traceInfos.
     *
     * @param traceInfos the traceInfos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated traceInfos,
     * or with status {@code 400 (Bad Request)} if the traceInfos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the traceInfos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trace-infos")
    public ResponseEntity<TraceInfos> updateTraceInfos(@RequestBody TraceInfos traceInfos) throws URISyntaxException {
        log.debug("REST request to update TraceInfos : {}", traceInfos);
        if (traceInfos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TraceInfos result = traceInfosRepository.save(traceInfos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, traceInfos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trace-infos} : get all the traceInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of traceInfos in body.
     */
    @GetMapping("/trace-infos")
    public ResponseEntity<List<TraceInfos>> getAllTraceInfos(Pageable pageable) {
        log.debug("REST request to get a page of TraceInfos");
        Page<TraceInfos> page = traceInfosRepository.findAll(pageable);
        System.out.println(ServletUriComponentsBuilder.fromCurrentRequest());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trace-infos/:id} : get the "id" traceInfos.
     *
     * @param id the id of the traceInfos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the traceInfos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trace-infos/{id}")
    public ResponseEntity<TraceInfos> getTraceInfos(@PathVariable Long id) {
        log.debug("REST request to get TraceInfos : {}", id);
        Optional<TraceInfos> traceInfos = traceInfosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(traceInfos);
    }

    /**
     * {@code DELETE  /trace-infos/:id} : delete the "id" traceInfos.
     *
     * @param id the id of the traceInfos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trace-infos/{id}")
    public ResponseEntity<Void> deleteTraceInfos(@PathVariable Long id) {
        log.debug("REST request to delete TraceInfos : {}", id);
        traceInfosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
