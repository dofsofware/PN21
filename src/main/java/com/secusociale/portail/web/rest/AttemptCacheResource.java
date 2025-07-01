package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.AttemptCache;
import com.secusociale.portail.service.AttemptCacheService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.AttemptCache}.
 */
@RestController
@RequestMapping("/api")
public class AttemptCacheResource {

    private static final String ENTITY_NAME = "portailCssIpresV2AttemptCache";
    private final Logger log = LoggerFactory.getLogger(AttemptCacheResource.class);
    private final AttemptCacheService attemptCacheService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public AttemptCacheResource(AttemptCacheService attemptCacheService) {
        this.attemptCacheService = attemptCacheService;
    }

    /**
     * {@code POST  /attempt-caches} : Create a new attemptCache.
     *
     * @param attemptCache the attemptCache to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attemptCache, or with status {@code 400 (Bad Request)} if the attemptCache has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attempt-caches")
    public ResponseEntity<AttemptCache> createAttemptCache(@Valid @RequestBody AttemptCache attemptCache) throws URISyntaxException {
        log.debug("REST request to save AttemptCache : {}", attemptCache);
        if (attemptCache.getId() != null) {
            throw new BadRequestAlertException("A new attemptCache cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttemptCache result = attemptCacheService.save(attemptCache);
        return ResponseEntity.created(new URI("/api/attempt-caches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attempt-caches} : Updates an existing attemptCache.
     *
     * @param attemptCache the attemptCache to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attemptCache,
     * or with status {@code 400 (Bad Request)} if the attemptCache is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attemptCache couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attempt-caches")
    public ResponseEntity<AttemptCache> updateAttemptCache(@Valid @RequestBody AttemptCache attemptCache) throws URISyntaxException {
        log.debug("REST request to update AttemptCache : {}", attemptCache);
        if (attemptCache.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttemptCache result = attemptCacheService.save(attemptCache);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attemptCache.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attempt-caches} : get all the attemptCaches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attemptCaches in body.
     */
    @GetMapping("/attempt-caches")
    public List<AttemptCache> getAllAttemptCaches() {
        log.debug("REST request to get all AttemptCaches");
        return attemptCacheService.findAll();
    }

    /**
     * {@code GET  /attempt-caches/:id} : get the "id" attemptCache.
     *
     * @param id the id of the attemptCache to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attemptCache, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attempt-caches/{id}")
    public ResponseEntity<AttemptCache> getAttemptCache(@PathVariable Long id) {
        log.debug("REST request to get AttemptCache : {}", id);
        Optional<AttemptCache> attemptCache = attemptCacheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attemptCache);
    }

    /**
     * {@code DELETE  /attempt-caches/:id} : delete the "id" attemptCache.
     *
     * @param id the id of the attemptCache to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attempt-caches/{id}")
    public ResponseEntity<Void> deleteAttemptCache(@PathVariable Long id) {
        log.debug("REST request to delete AttemptCache : {}", id);
        attemptCacheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
