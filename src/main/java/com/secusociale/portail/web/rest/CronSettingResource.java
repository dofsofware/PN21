package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.CronSettingService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.CronSettingDTO;
import com.secusociale.portail.service.dto.CronSettingCriteria;
import com.secusociale.portail.service.CronSettingQueryService;

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
 * REST controller for managing {@link com.secusociale.portail.domain.CronSetting}.
 */
@RestController
@RequestMapping("/api")
public class CronSettingResource {

    private final Logger log = LoggerFactory.getLogger(CronSettingResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2CronSetting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CronSettingService cronSettingService;

    private final CronSettingQueryService cronSettingQueryService;

    public CronSettingResource(CronSettingService cronSettingService, CronSettingQueryService cronSettingQueryService) {
        this.cronSettingService = cronSettingService;
        this.cronSettingQueryService = cronSettingQueryService;
    }

    /**
     * {@code POST  /cron-settings} : Create a new cronSetting.
     *
     * @param cronSettingDTO the cronSettingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cronSettingDTO, or with status {@code 400 (Bad Request)} if the cronSetting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cron-settings")
    public ResponseEntity<CronSettingDTO> createCronSetting(@RequestBody CronSettingDTO cronSettingDTO) throws URISyntaxException {
        log.debug("REST request to save CronSetting : {}", cronSettingDTO);
        if (cronSettingDTO.getId() != null) {
            throw new BadRequestAlertException("A new cronSetting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CronSettingDTO result = cronSettingService.save(cronSettingDTO);
        return ResponseEntity.created(new URI("/api/cron-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cron-settings} : Updates an existing cronSetting.
     *
     * @param cronSettingDTO the cronSettingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cronSettingDTO,
     * or with status {@code 400 (Bad Request)} if the cronSettingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cronSettingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cron-settings")
    public ResponseEntity<CronSettingDTO> updateCronSetting(@RequestBody CronSettingDTO cronSettingDTO) throws URISyntaxException {
        log.debug("REST request to update CronSetting : {}", cronSettingDTO);
        if (cronSettingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CronSettingDTO result = cronSettingService.save(cronSettingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cronSettingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cron-settings} : get all the cronSettings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cronSettings in body.
     */
    @GetMapping("/cron-settings")
    public ResponseEntity<List<CronSettingDTO>> getAllCronSettings(CronSettingCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CronSettings by criteria: {}", criteria);
        Page<CronSettingDTO> page = cronSettingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cron-settings/count} : count all the cronSettings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cron-settings/count")
    public ResponseEntity<Long> countCronSettings(CronSettingCriteria criteria) {
        log.debug("REST request to count CronSettings by criteria: {}", criteria);
        return ResponseEntity.ok().body(cronSettingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cron-settings/:id} : get the "id" cronSetting.
     *
     * @param id the id of the cronSettingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cronSettingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cron-settings/{id}")
    public ResponseEntity<CronSettingDTO> getCronSetting(@PathVariable Long id) {
        log.debug("REST request to get CronSetting : {}", id);
        Optional<CronSettingDTO> cronSettingDTO = cronSettingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cronSettingDTO);
    }

    /**
     * {@code DELETE  /cron-settings/:id} : delete the "id" cronSetting.
     *
     * @param id the id of the cronSettingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cron-settings/{id}")
    public ResponseEntity<Void> deleteCronSetting(@PathVariable Long id) {
        log.debug("REST request to delete CronSetting : {}", id);
        cronSettingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
