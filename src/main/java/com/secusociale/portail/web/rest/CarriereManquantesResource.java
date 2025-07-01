package com.secusociale.portail.web.rest;


import com.secusociale.portail.domain.CarriereManquantes;
import com.secusociale.portail.repository.CarriereManquantesRepository;
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


@RestController
@RequestMapping("/api/carriere-manquantes")
@Transactional
public class CarriereManquantesResource {

    private static final Logger LOG = LoggerFactory.getLogger(CarriereManquantesResource.class);

    private static final String ENTITY_NAME = "jhipsterCarriereManquantes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarriereManquantesRepository carriereManquantesRepository;

    public CarriereManquantesResource(CarriereManquantesRepository carriereManquantesRepository) {
        this.carriereManquantesRepository = carriereManquantesRepository;
    }

    /**
     * {@code POST  /carriere-manquantes} : Create a new carriereManquantes.
     *
     * @param carriereManquantes the carriereManquantes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carriereManquantes, or with status {@code 400 (Bad Request)} if the carriereManquantes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CarriereManquantes> createCarriereManquantes(@Valid @RequestBody CarriereManquantes carriereManquantes)
        throws URISyntaxException {
        LOG.debug("REST request to save CarriereManquantes : {}", carriereManquantes);
        if (carriereManquantes.getId() != null) {
            throw new BadRequestAlertException("A new carriereManquantes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        carriereManquantes = carriereManquantesRepository.save(carriereManquantes);
        return ResponseEntity.created(new URI("/api/carriere-manquantes/" + carriereManquantes.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, carriereManquantes.getId().toString()))
            .body(carriereManquantes);
    }

    /**
     * {@code PUT  /carriere-manquantes/:id} : Updates an existing carriereManquantes.
     *
     * @param id the id of the carriereManquantes to save.
     * @param carriereManquantes the carriereManquantes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carriereManquantes,
     * or with status {@code 400 (Bad Request)} if the carriereManquantes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carriereManquantes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarriereManquantes> updateCarriereManquantes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CarriereManquantes carriereManquantes
    ) throws URISyntaxException {
        LOG.debug("REST request to update CarriereManquantes : {}, {}", id, carriereManquantes);
        if (carriereManquantes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carriereManquantes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carriereManquantesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        carriereManquantes = carriereManquantesRepository.save(carriereManquantes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carriereManquantes.getId().toString()))
            .body(carriereManquantes);
    }

    /**
     * {@code PATCH  /carriere-manquantes/:id} : Partial updates given fields of an existing carriereManquantes, field will ignore if it is null
     *
     * @param id the id of the carriereManquantes to save.
     * @param carriereManquantes the carriereManquantes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carriereManquantes,
     * or with status {@code 400 (Bad Request)} if the carriereManquantes is not valid,
     * or with status {@code 404 (Not Found)} if the carriereManquantes is not found,
     * or with status {@code 500 (Internal Server Error)} if the carriereManquantes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarriereManquantes> partialUpdateCarriereManquantes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CarriereManquantes carriereManquantes
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CarriereManquantes partially : {}, {}", id, carriereManquantes);
        if (carriereManquantes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carriereManquantes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carriereManquantesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarriereManquantes> result = carriereManquantesRepository
            .findById(carriereManquantes.getId())
            .map(existingCarriereManquantes -> {
                if (carriereManquantes.getNumeroUniqueEmployeur() != null) {
                    existingCarriereManquantes.setNumeroUniqueEmployeur(carriereManquantes.getNumeroUniqueEmployeur());
                }
                if (carriereManquantes.getDateDebut() != null) {
                    existingCarriereManquantes.setDateDebut(carriereManquantes.getDateDebut());
                }
                if (carriereManquantes.getDateFin() != null) {
                    existingCarriereManquantes.setDateFin(carriereManquantes.getDateFin());
                }

                return existingCarriereManquantes;
            })
            .map(carriereManquantesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carriereManquantes.getId().toString())
        );
    }

    /**
     * {@code GET  /carriere-manquantes} : get all the carriereManquantes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carriereManquantes in body.
     */
    @GetMapping("")
    public List<CarriereManquantes> getAllCarriereManquantes() {
        LOG.debug("REST request to get all CarriereManquantes");
        return carriereManquantesRepository.findAll();
    }

    /**
     * {@code GET  /carriere-manquantes/:id} : get the "id" carriereManquantes.
     *
     * @param id the id of the carriereManquantes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carriereManquantes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarriereManquantes> getCarriereManquantes(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CarriereManquantes : {}", id);
        Optional<CarriereManquantes> carriereManquantes = carriereManquantesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(carriereManquantes);
    }

    /**
     * {@code DELETE  /carriere-manquantes/:id} : delete the "id" carriereManquantes.
     *
     * @param id the id of the carriereManquantes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarriereManquantes(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CarriereManquantes : {}", id);
        carriereManquantesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

