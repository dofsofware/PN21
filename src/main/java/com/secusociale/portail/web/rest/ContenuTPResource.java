package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.ContenuTP;
import com.secusociale.portail.repository.ContenuTPRepository;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/temps-presence-individuel")
@Transactional
public class ContenuTPResource {

    private static final Logger LOG = LoggerFactory.getLogger(ContenuTPResource.class);

    private static final String ENTITY_NAME = "jhipsterContenuTp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContenuTPRepository contenuTPRepository;

    public ContenuTPResource(ContenuTPRepository contenuTPRepository) {
        this.contenuTPRepository = contenuTPRepository;
    }

    /**
     * {@code POST  /contenu-tps} : Create a new contenuTP.
     *
     * @param contenuTP the contenuTP to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contenuTP, or with status {@code 400 (Bad Request)} if the contenuTP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContenuTP> createContenuTP(@Valid @RequestBody ContenuTP contenuTP) throws URISyntaxException {
        LOG.debug("REST request to save ContenuTP : {}", contenuTP);
        if (contenuTP.getId() != null) {
            throw new BadRequestAlertException("A new contenuTP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (contenuTP.getSalarieTPs() != null) {
            contenuTP.getSalarieTPs().forEach(salarieTP -> {
                if (salarieTP.getTempsDePresenceHeureMois1() == null) {
                    salarieTP.setTempsDePresenceHeureMois1(0);
                }
                if (salarieTP.getTempsDePresenceHeureMois2() == null) {
                    salarieTP.setTempsDePresenceHeureMois2(0);
                }
                if (salarieTP.getTempsDePresenceHeureMois3() == null) {
                    salarieTP.setTempsDePresenceHeureMois3(0);
                }
            });
        }
        contenuTP = contenuTPRepository.save(contenuTP);
        return ResponseEntity.created(new URI("/api/contenu-tps/" + contenuTP.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, contenuTP.getId().toString()))
            .body(contenuTP);
    }

    /**
     * {@code PUT  /contenu-tps/:id} : Updates an existing contenuTP.
     *
     * @param id        the id of the contenuTP to save.
     * @param contenuTP the contenuTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contenuTP,
     * or with status {@code 400 (Bad Request)} if the contenuTP is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contenuTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContenuTP> updateContenuTP(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContenuTP contenuTP
    ) throws URISyntaxException {
        LOG.debug("REST request to update ContenuTP : {}, {}", id, contenuTP);
        if (contenuTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contenuTP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contenuTPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        if (contenuTP.getSalarieTPs() != null) {
            contenuTP.getSalarieTPs().forEach(salarieTP -> {
                if (salarieTP.getTempsDePresenceHeureMois1() == null) {
                    salarieTP.setTempsDePresenceHeureMois1(0);
                }
                if (salarieTP.getTempsDePresenceHeureMois2() == null) {
                    salarieTP.setTempsDePresenceHeureMois2(0);
                }
                if (salarieTP.getTempsDePresenceHeureMois3() == null) {
                    salarieTP.setTempsDePresenceHeureMois3(0);
                }
            });
        }

        contenuTP = contenuTPRepository.save(contenuTP);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contenuTP.getId().toString()))
            .body(contenuTP);
    }

    /**
     * {@code PATCH  /contenu-tps/:id} : Partial updates given fields of an existing contenuTP, field will ignore if it is null
     *
     * @param id        the id of the contenuTP to save.
     * @param contenuTP the contenuTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contenuTP,
     * or with status {@code 400 (Bad Request)} if the contenuTP is not valid,
     * or with status {@code 404 (Not Found)} if the contenuTP is not found,
     * or with status {@code 500 (Internal Server Error)} if the contenuTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ContenuTP> partialUpdateContenuTP(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContenuTP contenuTP
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ContenuTP partially : {}, {}", id, contenuTP);
        if (contenuTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contenuTP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contenuTPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        if (contenuTP.getSalarieTPs() != null) {
            contenuTP.getSalarieTPs().forEach(salarieTP -> {
                if (salarieTP.getTempsDePresenceHeureMois1() == null) {
                    salarieTP.setTempsDePresenceHeureMois1(0);
                }
                if (salarieTP.getTempsDePresenceHeureMois2() == null) {
                    salarieTP.setTempsDePresenceHeureMois2(0);
                }
                if (salarieTP.getTempsDePresenceHeureMois3() == null) {
                    salarieTP.setTempsDePresenceHeureMois3(0);
                }
            });
        }

        Optional<ContenuTP> result = contenuTPRepository
            .findById(contenuTP.getId())
            .map(existingContenuTP -> {
                if (contenuTP.getNumeroUnique() != null) {
                    existingContenuTP.setNumeroUnique(contenuTP.getNumeroUnique());
                }
                if (contenuTP.getAnnee() != null) {
                    existingContenuTP.setAnnee(contenuTP.getAnnee());
                }
                if (contenuTP.getTrimestre() != null) {
                    existingContenuTP.setTrimestre(contenuTP.getTrimestre());
                }
                if (contenuTP.getAncienNumeroCss() != null) {
                    existingContenuTP.setAncienNumeroCss(contenuTP.getAncienNumeroCss());
                }
                if (contenuTP.getAncienNumeroIpres() != null) {
                    existingContenuTP.setAncienNumeroIpres(contenuTP.getAncienNumeroIpres());
                }

                return existingContenuTP;
            })
            .map(contenuTPRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contenuTP.getId().toString())
        );
    }

    /**
     * {@code GET  /contenu-tps} : get all the contenuTPS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contenuTPS in body.
     */
    @GetMapping("")
    public ApiResponse<Object> getAllContenuTPS(@RequestParam(name = "filter", required = false) String filter) {
        if ("filetp-is-null".equals(filter)) {
            LOG.debug("REST request to get all ContenuTPs where fileTP is null");
            return ApiResponse.success(StreamSupport.stream(contenuTPRepository.findAll().spliterator(), false)
                .filter(contenuTP -> contenuTP.getFileTP() == null)
                .collect(Collectors.toList()));
        }
        LOG.debug("REST request to get all ContenuTPS");
        List<ContenuTP> reponse = contenuTPRepository.findAll();
        return ApiResponse.success(reponse);
    }

    /**
     * {@code GET  /contenu-tps/:id} : get the "id" contenuTP.
     *
     * @param id the id of the contenuTP to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contenuTP, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContenuTP> getContenuTP(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ContenuTP : {}", id);
        Optional<ContenuTP> contenuTP = contenuTPRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contenuTP);
    }

    /**
     * {@code DELETE  /contenu-tps/:id} : delete the "id" contenuTP.
     *
     * @param id the id of the contenuTP to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContenuTP(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ContenuTP : {}", id);
        contenuTPRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
