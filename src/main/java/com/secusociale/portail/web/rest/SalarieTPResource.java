package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.SalarieTP;
import com.secusociale.portail.repository.SalarieTPRepository;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/salarie-tps")
@Transactional
public class SalarieTPResource {

    private static final Logger LOG = LoggerFactory.getLogger(SalarieTPResource.class);

    private static final String ENTITY_NAME = "jhipsterSalarieTp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalarieTPRepository salarieTPRepository;

    public SalarieTPResource(SalarieTPRepository salarieTPRepository) {
        this.salarieTPRepository = salarieTPRepository;
    }

    /**
     * {@code POST  /salarie-tps} : Create a new salarieTP.
     *
     * @param salarieTP the salarieTP to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salarieTP, or with status {@code 400 (Bad Request)} if the salarieTP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SalarieTP> createSalarieTP(@Valid @RequestBody SalarieTP salarieTP) throws URISyntaxException {
        LOG.debug("REST request to save SalarieTP : {}", salarieTP);
        if (salarieTP.getId() != null) {
            throw new BadRequestAlertException("A new salarieTP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        salarieTP = salarieTPRepository.save(salarieTP);
        return ResponseEntity.created(new URI("/api/salarie-tps/" + salarieTP.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, salarieTP.getId().toString()))
            .body(salarieTP);
    }

    /**
     * {@code PUT  /salarie-tps/:id} : Updates an existing salarieTP.
     *
     * @param id the id of the salarieTP to save.
     * @param salarieTP the salarieTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salarieTP,
     * or with status {@code 400 (Bad Request)} if the salarieTP is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salarieTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SalarieTP> updateSalarieTP(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SalarieTP salarieTP
    ) throws URISyntaxException {
        LOG.debug("REST request to update SalarieTP : {}, {}", id, salarieTP);
        if (salarieTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salarieTP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salarieTPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        salarieTP = salarieTPRepository.save(salarieTP);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salarieTP.getId().toString()))
            .body(salarieTP);
    }

    /**
     * {@code PATCH  /salarie-tps/:id} : Partial updates given fields of an existing salarieTP, field will ignore if it is null
     *
     * @param id the id of the salarieTP to save.
     * @param salarieTP the salarieTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salarieTP,
     * or with status {@code 400 (Bad Request)} if the salarieTP is not valid,
     * or with status {@code 404 (Not Found)} if the salarieTP is not found,
     * or with status {@code 500 (Internal Server Error)} if the salarieTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalarieTP> partialUpdateSalarieTP(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SalarieTP salarieTP
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SalarieTP partially : {}, {}", id, salarieTP);
        if (salarieTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salarieTP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salarieTPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalarieTP> result = salarieTPRepository
            .findById(salarieTP.getId())
            .map(existingSalarieTP -> {
                if (salarieTP.getNumeroAssureSocial() != null) {
                    existingSalarieTP.setNumeroAssureSocial(salarieTP.getNumeroAssureSocial());
                }
                if (salarieTP.getNom() != null) {
                    existingSalarieTP.setNom(salarieTP.getNom());
                }
                if (salarieTP.getPrenom() != null) {
                    existingSalarieTP.setPrenom(salarieTP.getPrenom());
                }
                if (salarieTP.getNumeroPiece() != null) {
                    existingSalarieTP.setNumeroPiece(salarieTP.getNumeroPiece());
                }
                if (salarieTP.getAge() != null) {
                    existingSalarieTP.setAge(salarieTP.getAge());
                }
                if (salarieTP.getNombreEnfantEligibre() != null) {
                    existingSalarieTP.setNombreEnfantEligibre(salarieTP.getNombreEnfantEligibre());
                }
                if (salarieTP.getTempsDePresenceHeureMois1() != null) {
                    existingSalarieTP.setTempsDePresenceHeureMois1(salarieTP.getTempsDePresenceHeureMois1());
                }
                if (salarieTP.getTempsDePresenceHeureMois2() != null) {
                    existingSalarieTP.setTempsDePresenceHeureMois2(salarieTP.getTempsDePresenceHeureMois2());
                }
                if (salarieTP.getTempsDePresenceHeureMois3() != null) {
                    existingSalarieTP.setTempsDePresenceHeureMois3(salarieTP.getTempsDePresenceHeureMois3());
                }
                if (salarieTP.getObservation() != null) {
                    existingSalarieTP.setObservation(salarieTP.getObservation());
                }

                return existingSalarieTP;
            })
            .map(salarieTPRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salarieTP.getId().toString())
        );
    }

    /**
     * {@code GET  /salarie-tps} : get all the salarieTPS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salarieTPS in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SalarieTP>> getAllSalarieTPS(Pageable pageable) {
        LOG.debug("REST request to get a page of SalarieTPS");
        Page<SalarieTP> page = salarieTPRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /salarie-tps/:id} : get the "id" salarieTP.
     *
     * @param id the id of the salarieTP to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salarieTP, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SalarieTP> getSalarieTP(@PathVariable("id") Long id) {
        LOG.debug("REST request to get SalarieTP : {}", id);
        Optional<SalarieTP> salarieTP = salarieTPRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(salarieTP);
    }

    /**
     * {@code DELETE  /salarie-tps/:id} : delete the "id" salarieTP.
     *
     * @param id the id of the salarieTP to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalarieTP(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete SalarieTP : {}", id);
        salarieTPRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

