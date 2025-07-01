package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.EmployeurCompteService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.dto.EmployeurCompteDTO;
import com.secusociale.portail.service.dto.EmployeurCompteCriteria;
import com.secusociale.portail.service.EmployeurCompteQueryService;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.EmployeurCompte}.
 */
@RestController
@RequestMapping("/api")
public class EmployeurCompteResource {

    private final Logger log = LoggerFactory.getLogger(EmployeurCompteResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2EmployeurCompte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private UserRepository userRepository;

    private final EmployeurCompteService employeurCompteService;

    private final EmployeurCompteQueryService employeurCompteQueryService;

    public EmployeurCompteResource(EmployeurCompteService employeurCompteService, EmployeurCompteQueryService employeurCompteQueryService) {
        this.employeurCompteService = employeurCompteService;
        this.employeurCompteQueryService = employeurCompteQueryService;
    }

    /**
     * {@code POST  /employeur-comptes} : Create a new employeurCompte.
     *
     * @param employeurCompteDTO the employeurCompteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeurCompteDTO, or with status {@code 400 (Bad Request)} if the employeurCompte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employeur-comptes")
    public ResponseEntity<EmployeurCompteDTO> createEmployeurCompte(@Valid @RequestBody EmployeurCompteDTO employeurCompteDTO) throws URISyntaxException {
        log.debug("REST request to save EmployeurCompte : {}", employeurCompteDTO);
        if (employeurCompteDTO.getId() != null) {
            throw new BadRequestAlertException("A new employeurCompte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            User user = userRepository.findByLogin(username);
            employeurCompteDTO.setUserId(user.getId());
        }
        EmployeurCompteDTO result = employeurCompteService.save(employeurCompteDTO);
        return ResponseEntity.created(new URI("/api/employeur-comptes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employeur-comptes} : Updates an existing employeurCompte.
     *
     * @param employeurCompteDTO the employeurCompteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeurCompteDTO,
     * or with status {@code 400 (Bad Request)} if the employeurCompteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeurCompteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employeur-comptes")
    public ResponseEntity<EmployeurCompteDTO> updateEmployeurCompte(@Valid @RequestBody EmployeurCompteDTO employeurCompteDTO) throws URISyntaxException {
        log.debug("REST request to update EmployeurCompte : {}", employeurCompteDTO);
        if (employeurCompteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            User user = userRepository.findByLogin(username);
            employeurCompteDTO.setUserId(user.getId());
        }
        EmployeurCompteDTO result = employeurCompteService.save(employeurCompteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeurCompteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /employeur-comptes} : get all the employeurComptes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employeurComptes in body.
     */
    @GetMapping("/employeur-comptes")
    public ResponseEntity<HashMap<String, Object>> getAllEmployeurComptes(EmployeurCompteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EmployeurComptes by criteria: {}", criteria);
        Page<EmployeurCompteDTO> page = employeurCompteQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        List<EmployeurCompteDTO> list = page.getContent();
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
     * {@code GET  /employeur-comptes/count} : count all the employeurComptes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/employeur-comptes/count")
    public ResponseEntity<Long> countEmployeurComptes(EmployeurCompteCriteria criteria) {
        log.debug("REST request to count EmployeurComptes by criteria: {}", criteria);
        return ResponseEntity.ok().body(employeurCompteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /employeur-comptes/:id} : get the "id" employeurCompte.
     *
     * @param id the id of the employeurCompteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeurCompteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employeur-comptes/{id}")
    public ResponseEntity<EmployeurCompteDTO> getEmployeurCompte(@PathVariable Long id) {
        log.debug("REST request to get EmployeurCompte : {}", id);
        Optional<EmployeurCompteDTO> employeurCompteDTO = employeurCompteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeurCompteDTO);
    }

    /**
     * {@code GET  /employeur-comptes/mine/:numeroUnique} : get the "numeroUnique" employeurCompte.
     *
     * @param numeroUnique the numeroUnique of the employeurCompteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeurCompteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employeur-comptes/mine/{numeroUnique}")
    public ResponseEntity<HashMap<String, Object>> getAllEmployeurCompteByNumeroUnique(@PathVariable String numeroUnique, Pageable pageable) {
        log.debug("REST request to get EmployeurCompte : {}", numeroUnique);
        Page<EmployeurCompteDTO> page = employeurCompteService.finAllByNumeroUnique(numeroUnique, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        List<EmployeurCompteDTO> list = page.getContent();
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
     * {@code DELETE  /employeur-comptes/:id} : delete the "id" employeurCompte.
     *
     * @param id the id of the employeurCompteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employeur-comptes/{id}")
    public ResponseEntity<Void> deleteEmployeurCompte(@PathVariable Long id) {
        log.debug("REST request to delete EmployeurCompte : {}", id);
        employeurCompteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
