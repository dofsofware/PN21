package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.FactureImpayeeService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.FactureImpayeeDTO;
import com.secusociale.portail.service.dto.FactureImpayeeCriteria;
import com.secusociale.portail.service.FactureImpayeeQueryService;

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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.FactureImpayee}.
 */
@RestController
@RequestMapping("/api")
public class FactureImpayeeResource {

    private final Logger log = LoggerFactory.getLogger(FactureImpayeeResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2FactureImpayee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactureImpayeeService factureImpayeeService;

    private final FactureImpayeeQueryService factureImpayeeQueryService;

    public FactureImpayeeResource(FactureImpayeeService factureImpayeeService, FactureImpayeeQueryService factureImpayeeQueryService) {
        this.factureImpayeeService = factureImpayeeService;
        this.factureImpayeeQueryService = factureImpayeeQueryService;
    }

    /**
     * {@code POST  /facture-impayees} : Create a new factureImpayee.
     *
     * @param factureImpayeeDTO the factureImpayeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new factureImpayeeDTO, or with status {@code 400 (Bad Request)} if the factureImpayee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facture-impayees")
    public ResponseEntity<FactureImpayeeDTO> createFactureImpayee(@RequestBody FactureImpayeeDTO factureImpayeeDTO) throws URISyntaxException {
        log.debug("REST request to save FactureImpayee : {}", factureImpayeeDTO);
        if (factureImpayeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new factureImpayee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactureImpayeeDTO result = factureImpayeeService.save(factureImpayeeDTO);
        return ResponseEntity.created(new URI("/api/facture-impayees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facture-impayees} : Updates an existing factureImpayee.
     *
     * @param factureImpayeeDTO the factureImpayeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated factureImpayeeDTO,
     * or with status {@code 400 (Bad Request)} if the factureImpayeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the factureImpayeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facture-impayees")
    public ResponseEntity<FactureImpayeeDTO> updateFactureImpayee(@RequestBody FactureImpayeeDTO factureImpayeeDTO) throws URISyntaxException {
        log.debug("REST request to update FactureImpayee : {}", factureImpayeeDTO);
        if (factureImpayeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactureImpayeeDTO result = factureImpayeeService.save(factureImpayeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, factureImpayeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facture-impayees} : get all the factureImpayees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factureImpayees in body.
     */
    @GetMapping("/facture-impayees")
    public ResponseEntity<HashMap<String, Object>> getAllFactureImpayees(FactureImpayeeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FactureImpayees by criteria: {}", criteria);
        Page<FactureImpayeeDTO> page = factureImpayeeQueryService.findByCriteria(criteria, pageable);
        List<FactureImpayeeDTO> list = page.getContent();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
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
     * {@code GET  /facture-impayees/count} : count all the factureImpayees.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/facture-impayees/count")
    public ResponseEntity<Long> countFactureImpayees(FactureImpayeeCriteria criteria) {
        log.debug("REST request to count FactureImpayees by criteria: {}", criteria);
        return ResponseEntity.ok().body(factureImpayeeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /facture-impayees/:id} : get the "id" factureImpayee.
     *
     * @param id the id of the factureImpayeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factureImpayeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facture-impayees/{id}")
    public ResponseEntity<FactureImpayeeDTO> getFactureImpayee(@PathVariable Long id) {
        log.debug("REST request to get FactureImpayee : {}", id);
        Optional<FactureImpayeeDTO> factureImpayeeDTO = factureImpayeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(factureImpayeeDTO);
    }

    /**
     * {@code DELETE  /facture-impayees/:id} : delete the "id" factureImpayee.
     *
     * @param id the id of the factureImpayeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facture-impayees/{id}")
    public ResponseEntity<Void> deleteFactureImpayee(@PathVariable Long id) {
        log.debug("REST request to delete FactureImpayee : {}", id);
        factureImpayeeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
