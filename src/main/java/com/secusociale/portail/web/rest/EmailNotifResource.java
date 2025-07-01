package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.EmailNotifService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.EmailNotifDTO;
import com.secusociale.portail.service.dto.EmailNotifCriteria;
import com.secusociale.portail.service.EmailNotifQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.EmailNotif}.
 */
@RestController
@RequestMapping("/api")
public class EmailNotifResource {

    private final Logger log = LoggerFactory.getLogger(EmailNotifResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2EmailNotif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmailNotifService emailNotifService;

    private final EmailNotifQueryService emailNotifQueryService;

    public EmailNotifResource(EmailNotifService emailNotifService, EmailNotifQueryService emailNotifQueryService) {
        this.emailNotifService = emailNotifService;
        this.emailNotifQueryService = emailNotifQueryService;
    }

    /**
     * {@code POST  /email-notifs} : Create a new emailNotif.
     *
     * @param emailNotifDTO the emailNotifDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emailNotifDTO, or with status {@code 400 (Bad Request)} if the emailNotif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/email-notifs")
    public ResponseEntity<EmailNotifDTO> createEmailNotif(@Valid @RequestBody EmailNotifDTO emailNotifDTO) throws URISyntaxException {
        log.debug("REST request to save EmailNotif : {}", emailNotifDTO);
        if (emailNotifDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailNotif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailNotifDTO result = emailNotifService.save(emailNotifDTO);
        return ResponseEntity.created(new URI("/api/email-notifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /email-notifs} : Updates an existing emailNotif.
     *
     * @param emailNotifDTO the emailNotifDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailNotifDTO,
     * or with status {@code 400 (Bad Request)} if the emailNotifDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emailNotifDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/email-notifs")
    public ResponseEntity<EmailNotifDTO> updateEmailNotif(@Valid @RequestBody EmailNotifDTO emailNotifDTO) throws URISyntaxException {
        log.debug("REST request to update EmailNotif : {}", emailNotifDTO);
        if (emailNotifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailNotifDTO result = emailNotifService.save(emailNotifDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emailNotifDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /email-notifs} : get all the emailNotifs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emailNotifs in body.
     */
    @GetMapping("/email-notifs")
    public ResponseEntity<List<EmailNotifDTO>> getAllEmailNotifs(EmailNotifCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EmailNotifs by criteria: {}", criteria);
        Page<EmailNotifDTO> page = emailNotifQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /email-notifs/count} : count all the emailNotifs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/email-notifs/count")
    public ResponseEntity<Long> countEmailNotifs(EmailNotifCriteria criteria) {
        log.debug("REST request to count EmailNotifs by criteria: {}", criteria);
        return ResponseEntity.ok().body(emailNotifQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /email-notifs/:id} : get the "id" emailNotif.
     *
     * @param id the id of the emailNotifDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emailNotifDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/email-notifs/{id}")
    public ResponseEntity<EmailNotifDTO> getEmailNotif(@PathVariable Long id) {
        log.debug("REST request to get EmailNotif : {}", id);
        Optional<EmailNotifDTO> emailNotifDTO = emailNotifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailNotifDTO);
    }

    /**
     * {@code DELETE  /email-notifs/:id} : delete the "id" emailNotif.
     *
     * @param id the id of the emailNotifDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/email-notifs/{id}")
    public ResponseEntity<Void> deleteEmailNotif(@PathVariable Long id) {
        log.debug("REST request to delete EmailNotif : {}", id);
        emailNotifService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
