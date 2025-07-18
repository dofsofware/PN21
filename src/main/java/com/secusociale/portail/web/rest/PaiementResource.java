package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.Paiement;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.PaiementService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.*;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Paiement}.
 */
@RestController
@RequestMapping("/api")
public class PaiementResource {

    private final Logger log = LoggerFactory.getLogger(PaiementResource.class);
    @Autowired
    private UserRepository userRepository;
    private static final String ENTITY_NAME = "paiement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaiementService paiementService;

    public PaiementResource(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    /**
     * {@code POST  /paiements} : Create a new paiement.
     *
     * @param paiement the paiement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paiement, or with status {@code 400 (Bad Request)} if the paiement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paiements")
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to save Paiement : {}", paiement);
        if (paiement.getId() != null) {
            throw new BadRequestAlertException("A new paiement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paiement.etat("EN_ATTENTE");
        paiement.dateCreation(LocalDate.now());
        Optional<User> user = getCurrentUserLogin().flatMap(userRepository::findOneByLogin);
        user.map(User::getId).ifPresent(paiement::userId);

        Paiement result = paiementService.save(paiement);
        return ResponseEntity.created(new URI("/api/paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/paiments/validate/{id}")
    public ResponseEntity<Paiement> validatePaiement(@PathVariable Long id) {
        log.debug("REST request to get Paiement : {}", id);
        Optional<Paiement> paiement = paiementService.findOne(id);
        paiement.ifPresent(p -> {
            p.setEtat("VALIDE");
            paiementService.save(p);
        });
        return ResponseUtil.wrapOrNotFound(paiement);
    }

    /**
     * {@code PUT  /paiements} : Updates an existing paiement.
     *
     * @param paiement the paiement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paiement,
     * or with status {@code 400 (Bad Request)} if the paiement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paiement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paiements")
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to update Paiement : {}", paiement);
        if (paiement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Paiement result = paiementService.save(paiement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paiement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paiements} : get all the paiements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paiements in body.
     */
    @GetMapping("/paiements")
    public ResponseEntity<List<Paiement>> getAllPaiements(Pageable pageable) {
        log.debug("REST request to get a page of Paiements");
        Page<Paiement> page = paiementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paiements/:id} : get the "id" paiement.
     *
     * @param id the id of the paiement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paiement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paiements/{id}")
    public ResponseEntity<Paiement> getPaiement(@PathVariable Long id) {
        log.debug("REST request to get Paiement : {}", id);
        Optional<Paiement> paiement = paiementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paiement);
    }

    /**
     * {@code DELETE  /paiements/:id} : delete the "id" paiement.
     *
     * @param id the id of the paiement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paiements/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        log.debug("REST request to delete Paiement : {}", id);
        paiementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    /**
     * {@code GET  /paiements/:idUser} : get the "id" user.
     *
     * @param idUser the id of the paiement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paiement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paiements/user/{idUser}")
    public List<Paiement> getPaiementByUser(@PathVariable Long idUser) {
        log.debug("REST request to get Paiement : {}", idUser);
        return paiementService.findAllByUserId(idUser);
    }
}
