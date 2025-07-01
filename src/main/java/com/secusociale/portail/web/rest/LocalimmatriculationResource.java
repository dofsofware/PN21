package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.Localimmatriculation;
import com.secusociale.portail.domain.NouvelleImmatriculation;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.NouvelleImmatriculationQueryService;
import com.secusociale.portail.service.NouvelleImmatriculationService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.dto.NouvelleImmatriculationCriteria;
import com.secusociale.portail.service.dto.NouvelleImmatriculationDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.NouvelleImmatriculation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LocalimmatriculationResource {

    private static final String ENTITY_NAME = "portailCssIpresV2NouvelleImmatriculation";
    private final Logger log = LoggerFactory.getLogger(LocalimmatriculationResource.class);
    private final NouvelleImmatriculationRepository localimmatriculationRepository;
    private final NouvelleImmatriculationService localImmatriculationService;
    private final NouvelleImmatriculationService nouvelleImmatriculationService;
    private final UserService userService;
    private final NouvelleImmatriculationQueryService localimmatriculationQueryService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public LocalimmatriculationResource(NouvelleImmatriculationRepository localimmatriculationRepository, NouvelleImmatriculationService localImmatriculationService, NouvelleImmatriculationService nouvelleImmatriculationService, UserService userService, NouvelleImmatriculationQueryService localimmatriculationQueryService) {
        this.localimmatriculationRepository = localimmatriculationRepository;
        this.localImmatriculationService = localImmatriculationService;
        this.nouvelleImmatriculationService = nouvelleImmatriculationService;
        this.userService = userService;
        this.localimmatriculationQueryService = localimmatriculationQueryService;
    }

    /**
     * {@code POST  /localimmatriculations} : Create a new localimmatriculation.
     *
     * @param localimmatriculation the localimmatriculation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new localimmatriculation, or with status {@code 400 (Bad Request)} if the localimmatriculation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/localimmatriculations")
    public ResponseEntity<NouvelleImmatriculation> createNouvelleImmatriculation(@Valid @RequestBody NouvelleImmatriculation localimmatriculation) throws URISyntaxException {
        log.debug("REST request to save NouvelleImmatriculation : {}", localimmatriculation);
        if (localimmatriculation.getId() != null) {
            throw new BadRequestAlertException("A new localimmatriculation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        localimmatriculation.setCreatedAt(Instant.now());
        NouvelleImmatriculation result = localimmatriculationRepository.save(localimmatriculation);
        return ResponseEntity.created(new URI("/api/localimmatriculations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /localimmatriculations} : Updates an existing localimmatriculation.
     *
     * @param localimmatriculation the localimmatriculation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localimmatriculation,
     * or with status {@code 400 (Bad Request)} if the localimmatriculation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the localimmatriculation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/localimmatriculations")
    public ResponseEntity<NouvelleImmatriculation> updateNouvelleImmatriculation(@Valid @RequestBody NouvelleImmatriculation localimmatriculation) throws URISyntaxException {
        log.debug("REST request to update NouvelleImmatriculation : {}", localimmatriculation);
        if (localimmatriculation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (localimmatriculation.getCreatedAt() == null) {
            localimmatriculation.setCreatedAt(Instant.now());
        }
        NouvelleImmatriculation result = localimmatriculationRepository.save(localimmatriculation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localimmatriculation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /localimmatriculations} : get all the localimmatriculations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localimmatriculations in body.
     */
    @GetMapping("/localimmatriculations")
    public ResponseEntity<List<NouvelleImmatriculationDTO>> getAllNouvelleImmatriculations(NouvelleImmatriculationCriteria criteria, @RequestParam(defaultValue = "0") int page,
                                                                                           @RequestParam(defaultValue = "10") int size) {
        log.debug("REST request to get a page of NouvelleImmatriculations");
        Pageable pageable = PageRequest.of(page, size);
        Page<NouvelleImmatriculationDTO> pages = localimmatriculationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), pages);
        return ResponseEntity.ok().headers(headers).body(pages.getContent());
    }

    /**
     * {@code GET  /localimmatriculations-admin} : get all the localimmatriculations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localimmatriculations in body.
     */
    @GetMapping("/localimmatriculations-admin")
    public ResponseEntity<HashMap<String, Object>> getAllNouvelleImmatriculationsAdmin(NouvelleImmatriculationCriteria criteria, Pageable pageable, @RequestParam(value = "statut", defaultValue = "", required = false) String statut, @RequestParam(value = "userEmail", defaultValue = "", required = false) String userEmail, @RequestParam(value = "raisonSociale", defaultValue = "", required = false) String raisonSociale) {
        log.debug("REST request to get a page of NouvelleImmatriculations");
        HashMap<String, Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatusdoc(stringFilter);
        }

        if (!StringUtils.isEmpty(userEmail)) {
            if (userService.getUserByEmail(userEmail).map(User::getId).isPresent()) {
                LongFilter longFilter = new LongFilter();
                longFilter.setEquals(userService.getUserByEmail(userEmail).map(User::getId).get());
                criteria.setUser(longFilter);
            }
        }

        if (!StringUtils.isEmpty(raisonSociale)) {
            List<Long> idFounds = localimmatriculationRepository.findAll(pageable).filter(li -> StringUtils.containsIgnoreCase(li.getPayload(), raisonSociale)).map(NouvelleImmatriculation::getId).toList();
            LongFilter longFilter = new LongFilter();
            longFilter.setIn(idFounds);
            criteria.setId(longFilter);
        }

        Page<NouvelleImmatriculationDTO> page = localimmatriculationQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    /**
     * {@code GET  /localimmatriculations} : get all the localimmatriculations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localimmatriculations in body.
     * ***************************************************************
     Changer le 30/10/2024 pour prendre en compte la colonne manager
     * ***************************************************************
    @GetMapping("/immatriculations/currentUser")
    public ResponseEntity<List<NouvelleImmatriculation>> getAllNouvelleImmatriculationsByUser() {
        log.debug("REST request to get a page of NouvelleImmatriculations");
        String username = SecurityUtils.getCurrentUserLogin().orElse(null);
        List<NouvelleImmatriculation> list = nouvelleImmatriculationService.getUserImmatriculations(username);
        System.out.println("username = " + username);
        return ResponseEntity.ok().body(list);
    }*/


    /**
     * {@code GET  /localimmatriculations} : get all the localimmatriculations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localimmatriculations in body.
     */
    @GetMapping("/immatriculations/currentUser")
    public ResponseEntity<List<NouvelleImmatriculation>> getAllNouvelleImmatriculationsByUser() {
        log.debug("REST request to get a page of NouvelleImmatriculations");
        final Optional<User> isUser = userService.getUserWithAuthorities();
        if(!isUser.isPresent()) {
            return null;
        }

        final User user = isUser.get();
        List<NouvelleImmatriculation> list = localimmatriculationRepository.findAllByUserIdOrManagerId(user.getId());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/nouvelle-immatriculations/numero-unique")
    public ResponseEntity<?> getNouvelleImatByNumeroUnique(@RequestParam String numeroUnique) {

        NouvelleImmatriculation immat = localimmatriculationRepository.findByNumeroUnique(numeroUnique).orElseThrow(() -> new BadRequestAlertException("Aucun immatriculation trouvé avec le numero unique " + numeroUnique + ".", "immatriculation", "immatriculationNotFound"));
        Map<String, Object> reponse = new HashMap<>();
        reponse.put("code", HttpStatus.OK.value());
        reponse.put("data", immat);
        return ResponseEntity.ok().body(reponse);
    }

    /**
     * {@code GET  /localimmatriculations/:id} : get the "id" localimmatriculation.
     *
     * @param id the id of the localimmatriculation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localimmatriculation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/localimmatriculations/{id}")
    public ResponseEntity<NouvelleImmatriculation> getNouvelleImmatriculation(@PathVariable Long id) {
        log.debug("REST request to get NouvelleImmatriculation : {}", id);
        Optional<NouvelleImmatriculation> localimmatriculation = localimmatriculationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(localimmatriculation);
    }

    /**
     * {@code DELETE  /localimmatriculations/:id} : delete the "id" localimmatriculation.
     *
     * @param id the id of the localimmatriculation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/localimmatriculations/{id}")
    public ResponseEntity<Void> deleteNouvelleImmatriculation(@PathVariable Long id) {
        log.debug("REST request to delete NouvelleImmatriculation : {}", id);
        localimmatriculationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}
