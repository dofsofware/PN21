package com.secusociale.portail.web.rest;

import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.ActivitiesQueryService;
import com.secusociale.portail.service.ActivitiesService;
import com.secusociale.portail.service.dto.ActivitiesCriteria;
import com.secusociale.portail.service.dto.ActivitiesDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Activities}.
 */
@RestController
@RequestMapping("/api")
public class ActivitiesResource {

    private final Logger log = LoggerFactory.getLogger(ActivitiesResource.class);

    private final UserRepository userRepository;

    private static final String ENTITY_NAME = "portailCssIpresV2Activities";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivitiesService activitiesService;

    private final ActivitiesQueryService activitiesQueryService;

    public ActivitiesResource(UserRepository userRepository, ActivitiesService activitiesService, ActivitiesQueryService activitiesQueryService) {
        this.userRepository = userRepository;
        this.activitiesService = activitiesService;
        this.activitiesQueryService = activitiesQueryService;
    }

    /**
     * {@code POST  /activities} : Create a new activities.
     *
     * @param activitiesDTO the activitiesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activitiesDTO, or with status {@code 400 (Bad Request)} if the activities has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activities")
    public ResponseEntity<ActivitiesDTO> createActivities(@RequestBody ActivitiesDTO activitiesDTO) throws URISyntaxException {
        log.debug("REST request to save Activities : {}", activitiesDTO);
        if (activitiesDTO.getId() != null) {
            throw new BadRequestAlertException("A new activities cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        if (!loggedusername.isPresent()) {
            throw Problem.builder().withDetail("Veuillez vous connecter pour acceder a cette ressource")
                .withType(URI.create("/api/immatPortail"))
                .withStatus(Status.BAD_REQUEST)
                .withTitle("User Required")
                .build();
        }
        String username = loggedusername.get();
        Long userId = userRepository.findByLogin(username).getId();

        activitiesDTO.setDateOperation(Instant.now());
        activitiesDTO.setUserId(userId);

        ActivitiesDTO result = activitiesService.save(activitiesDTO);
        return ResponseEntity.created(new URI("/api/activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activities} : Updates an existing activities.
     *
     * @param activitiesDTO the activitiesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activitiesDTO,
     * or with status {@code 400 (Bad Request)} if the activitiesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activitiesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activities")
    public ResponseEntity<ActivitiesDTO> updateActivities(@RequestBody ActivitiesDTO activitiesDTO) throws URISyntaxException {
        log.debug("REST request to update Activities : {}", activitiesDTO);
        if (activitiesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivitiesDTO result = activitiesService.save(activitiesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activitiesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /activities} : get all the activities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activities in body.
     */
    @GetMapping("/activities")
    public ResponseEntity<List<ActivitiesDTO>> getAllActivities(ActivitiesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Activities by criteria: {}", criteria);
        Page<ActivitiesDTO> page = activitiesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /activities/count} : count all the activities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/activities/count")
    public ResponseEntity<Long> countActivities(ActivitiesCriteria criteria) {
        log.debug("REST request to count Activities by criteria: {}", criteria);
        return ResponseEntity.ok().body(activitiesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /activities/:id} : get the "id" activities.
     *
     * @param id the id of the activitiesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activitiesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivitiesDTO> getActivities(@PathVariable Long id) {
        log.debug("REST request to get Activities : {}", id);
        Optional<ActivitiesDTO> activitiesDTO = activitiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activitiesDTO);
    }

    /**
     * {@code DELETE  /activities/:id} : delete the "id" activities.
     *
     * @param id the id of the activitiesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Void> deleteActivities(@PathVariable Long id) {
        log.debug("REST request to delete Activities : {}", id);
        activitiesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
