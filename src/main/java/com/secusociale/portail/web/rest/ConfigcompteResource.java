package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.service.AgenceQueryService;
import com.secusociale.portail.service.ConfigcompteService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.ConfigcompteDTO;
import com.secusociale.portail.service.dto.ConfigcompteCriteria;
import com.secusociale.portail.service.ConfigcompteQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Configcompte}.
 */
@RestController
@RequestMapping("/api")
public class ConfigcompteResource {

    private final Logger log = LoggerFactory.getLogger(ConfigcompteResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2Configcompte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConfigcompteService configcompteService;

    private final ConfigcompteQueryService configcompteQueryService;
    private final AgenceRepository agenceRepository;

    public ConfigcompteResource(ConfigcompteService configcompteService, ConfigcompteQueryService configcompteQueryService, AgenceRepository agenceRepository) {
        this.configcompteService = configcompteService;
        this.configcompteQueryService = configcompteQueryService;
        this.agenceRepository = agenceRepository;
    }

    /**
     * {@code POST  /config-comptes} : Create a new configcompte.
     *
     * @param configcompteDTO the configcompteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new configcompteDTO, or with status {@code 400 (Bad Request)} if the configcompte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/config-comptes")
    public ResponseEntity<ConfigcompteDTO> createConfigcompte(@Valid @RequestBody ConfigcompteDTO configcompteDTO) throws URISyntaxException {
        log.debug("REST request to save Configcompte : {}", configcompteDTO);
        if (configcompteDTO.getId() != null) {
            throw new BadRequestAlertException("A new configcompte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Agence agence = agenceRepository.findByCode(configcompteDTO.getAgenceCode());
        if (agence != null) {
            configcompteDTO.setAgenceId(agence.getId());
            configcompteDTO.setAgenceNom(agence.getNom());
        }
        ConfigcompteDTO result = configcompteService.save(configcompteDTO);
        return ResponseEntity.created(new URI("/api/config-comptes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /config-comptes} : Updates an existing configcompte.
     *
     * @param configcompteDTO the configcompteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated configcompteDTO,
     * or with status {@code 400 (Bad Request)} if the configcompteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the configcompteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/config-comptes")
    public ResponseEntity<ConfigcompteDTO> updateConfigcompte(@Valid @RequestBody ConfigcompteDTO configcompteDTO) throws URISyntaxException {
        log.debug("REST request to update Configcompte : {}", configcompteDTO);
        if (configcompteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Agence agence = agenceRepository.findByCode(configcompteDTO.getAgenceCode());
        if (agence != null) {
            configcompteDTO.setAgenceId(agence.getId());
            configcompteDTO.setAgenceNom(agence.getNom());
        }
        ConfigcompteDTO result = configcompteService.save(configcompteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, configcompteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /config-comptes} : get all the configcomptes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of configcomptes in body.
     */
    @GetMapping("/config-comptes")
    public ResponseEntity<HashMap<String, Object>> getAllConfigcomptes(ConfigcompteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Configcomptes by criteria: {}", criteria);
        HashMap<String, Object> pagination = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        Page<ConfigcompteDTO> page = configcompteQueryService.findByCriteria(criteria, pageable);
        List<ConfigcompteDTO> list = page.getContent();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    /**
     * {@code GET  /config-comptes/count} : count all the configcomptes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/config-comptes/count")
    public ResponseEntity<Long> countConfigcomptes(ConfigcompteCriteria criteria) {
        log.debug("REST request to count Configcomptes by criteria: {}", criteria);
        return ResponseEntity.ok().body(configcompteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /config-comptes/:id} : get the "id" configcompte.
     *
     * @param id the id of the configcompteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the configcompteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/config-comptes/{id}")
    public ResponseEntity<ConfigcompteDTO> getConfigcompte(@PathVariable Long id) {
        log.debug("REST request to get Configcompte : {}", id);
        Optional<ConfigcompteDTO> configcompteDTO = configcompteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(configcompteDTO);
    }

    /**
     * {@code GET  /config-comptes/agence/:agenceCode} : get the "agenceCode" configcompte.
     *
     * @param agenceCode the id of the configcompteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the configcompteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/config-comptes/agence/{agenceCode}")
    public ResponseEntity<ConfigcompteDTO> getConfigcompteByAgenceCode(@PathVariable String agenceCode) {
        log.debug("REST request to get Configcompte : {}", agenceCode);
        Optional<ConfigcompteDTO> configcompteDTO = configcompteService.findOneByAgenceCode(agenceCode);
        return ResponseUtil.wrapOrNotFound(configcompteDTO);
    }

    /**
     * {@code DELETE  /config-comptes/:id} : delete the "id" configcompte.
     *
     * @param id the id of the configcompteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/config-comptes/{id}")
    public ResponseEntity<Void> deleteConfigcompte(@PathVariable Long id) {
        log.debug("REST request to delete Configcompte : {}", id);
        configcompteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
