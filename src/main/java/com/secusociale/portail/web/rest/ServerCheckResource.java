package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
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
 * REST controller for managing {@link com.secusociale.portail.domain.ServerCheck}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ServerCheckResource {

    private final Logger log = LoggerFactory.getLogger(ServerCheckResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2ServerCheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServerCheckRepository serverCheckRepository;

    public ServerCheckResource(ServerCheckRepository serverCheckRepository) {
        this.serverCheckRepository = serverCheckRepository;
    }

    /**
     * {@code POST  /server-checks} : Create a new serverCheck.
     *
     * @param serverCheck the serverCheck to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serverCheck, or with status {@code 400 (Bad Request)} if the serverCheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/server-checks")
    public ResponseEntity<ServerCheck> createServerCheck(@RequestBody ServerCheck serverCheck) throws URISyntaxException {
        log.debug("REST request to save ServerCheck : {}", serverCheck);
        if (serverCheck.getId() != null) {
            throw new BadRequestAlertException("A new serverCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServerCheck result = serverCheckRepository.save(serverCheck);
        return ResponseEntity.created(new URI("/api/server-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /server-checks} : Updates an existing serverCheck.
     *
     * @param serverCheck the serverCheck to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serverCheck,
     * or with status {@code 400 (Bad Request)} if the serverCheck is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serverCheck couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/server-checks")
    public ResponseEntity<ServerCheck> updateServerCheck(@RequestBody ServerCheck serverCheck) throws URISyntaxException {
        log.debug("REST request to update ServerCheck : {}", serverCheck);
        if (serverCheck.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServerCheck result = serverCheckRepository.save(serverCheck);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serverCheck.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /server-checks} : get all the serverChecks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serverChecks in body.
     */
    @GetMapping("/server-checks")
    public ResponseEntity<List<ServerCheck>> getAllServerChecks(Pageable pageable) {
        Page<ServerCheck> page = serverCheckRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /server-checks/:id} : get the "id" serverCheck.
     *
     * @param id the id of the serverCheck to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serverCheck, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/server-checks/{id}")
    public ResponseEntity<ServerCheck> getServerCheck(@PathVariable Long id) {
        log.debug("REST request to get ServerCheck : {}", id);
        Optional<ServerCheck> serverCheck = serverCheckRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(serverCheck);
    }

    /**
     * {@code DELETE  /server-checks/:id} : delete the "id" serverCheck.
     *
     * @param id the id of the serverCheck to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/server-checks/{id}")
    public ResponseEntity<Void> deleteServerCheck(@PathVariable Long id) {
        log.debug("REST request to delete ServerCheck : {}", id);
        serverCheckRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
