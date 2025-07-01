package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.service.AgenceQueryService;
import com.secusociale.portail.service.AgenceService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.dto.AgenceCriteria;
import com.secusociale.portail.service.dto.AgenceDTO;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Agence}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AgenceResource {

    private final Logger log = LoggerFactory.getLogger(AgenceResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2Agence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgenceRepository agenceRepository;
    private final UserService userService;
    private final AgenceService agenceService;

    @Autowired
    private AgenceQueryService agenceQueryService;

    public AgenceResource(AgenceRepository agenceRepository, UserService userService, AgenceService agenceService) {
        this.agenceRepository = agenceRepository;
        this.userService = userService;
        this.agenceService = agenceService;
    }

    /**
     * {@code POST  /agences} : Create a new agence.
     *
     * @param agence the agence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agence, or with status {@code 400 (Bad Request)} if the agence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agences")
    public ResponseEntity<Agence> createAgence(@RequestBody Agence agence) throws URISyntaxException {
        log.debug("REST request to save Agence : {}", agence);
        if (agence.getId() != null) {
            throw new BadRequestAlertException("A new agence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Agence result = agenceRepository.save(agence);
        return ResponseEntity.created(new URI("/api/agences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /agences} : Updates an existing agence.
     *
     * @param agence the agence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agence,
     * or with status {@code 400 (Bad Request)} if the agence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agences")
    public ResponseEntity<Agence> updateAgence(@RequestBody Agence agence) throws URISyntaxException {
        log.debug("REST request to update Agence : {}", agence);
        if (agence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Agence result = agenceRepository.save(agence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agences} : get all the agences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agences in body.
     */
    @GetMapping("/allAgences")
    public List<Agence> getAllAgences() {
        log.debug("REST request to get all Agences");
        return agenceRepository.findAll();
    }

    /**
     * {@code GET  /agences} : get all the agences.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agence in body.
     */
    @GetMapping(value = "/agences")
    public ResponseEntity<List<Agence>> getAllAgence(@RequestParam(value = "type", defaultValue = "", required = false) String type, AgenceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Agence by criteria: {}", criteria);
        if (!StringUtils.isEmpty(type)) {
            StringFilter filter = new StringFilter();
            filter.setEquals(type);
             criteria.setInstitution(filter);
        }
        Page<Agence> page = agenceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /agences/:id} : get the "id" agence.
     *
     * @param id the id of the agence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agences/{id}")
    public ResponseEntity<Agence> getAgence(@PathVariable Long id) {
        log.debug("REST request to get Agence : {}", id);
        Optional<Agence> agence = agenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(agence);
    }

    /**
     * {@code DELETE  /agences/:id} : delete the "id" agence.
     *
     * @param id the id of the agence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agences/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        log.debug("REST request to delete Agence : {}", id);
        agenceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/agences/user-agence")
    public ApiResponse<Agence> getUserAgence() {
        log.debug("Requête REST pour obtenir l'agence de l'utilisateur connecté");

        Optional<User> userOptional = userService.getUserWithAuthorities();

        if (!userOptional.isPresent()) {
            throw new BadRequestAlertException("Utilisateur non trouvé", ENTITY_NAME, "utilisateur.non.trouve");
        }

        User user = userOptional.get();

        if (!"AGENT".equals(user.getTypeCompte())) {
            throw new BadRequestAlertException("L'utilisateur n'est pas un agent", ENTITY_NAME, "utilisateur.non.agent");
        }

        String agenceCode = user.getAgence();
        if (StringUtils.isEmpty(agenceCode)) {
            throw new BadRequestAlertException("Aucune agence n'est associée à cet agent", ENTITY_NAME, "agent.sans.agence");
        }

        Optional<Agence> agence = Optional.ofNullable(agenceRepository.findByCode(agenceCode));

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, agence.get());
    }

    @GetMapping("/agences/search/{term}")
    public ApiResponse<Object> searchAgences(@PathVariable String term) {
        log.debug("REST request to search Agences with term : {}", term);
        List<Agence> result = agenceService.searchAgences(term);
        return ApiResponse.success(result);
    }
}
