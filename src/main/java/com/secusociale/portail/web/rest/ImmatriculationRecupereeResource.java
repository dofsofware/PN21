package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.ImmatriculationRecupereeQueryService;
import com.secusociale.portail.service.ImmatriculationRecupereeService;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeCriteria;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeDTO;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import com.secusociale.portail.service.immatriculation.VerifierExistenceEmployeur;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.web.rest.vm.CheckResponse;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.ImmatriculationRecuperee}.
 */
@RestController
@RequestMapping("/api")
public class ImmatriculationRecupereeResource {

    private final Logger log = LoggerFactory.getLogger(ImmatriculationRecupereeResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2ImmatriculationRecuperee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImmatriculationRecupereeService immatriculationRecupereeService;

    private final ImmatriculationRecupereeQueryService immatriculationRecupereeQueryService;

    private final UserRepository userRepository;

    private final VerifierExistenceEmployeur verifierExistenceEmployeur;

    private final DocumentUrlService documentUrlService;

    private final AgenceRepository agenceRepository;

    public ImmatriculationRecupereeResource(ImmatriculationRecupereeService immatriculationRecupereeService, ImmatriculationRecupereeQueryService immatriculationRecupereeQueryService, UserRepository userRepository, VerifierExistenceEmployeur verifierExistenceEmployeur, DocumentUrlService documentUrlService, AgenceRepository agenceRepository) {
        this.immatriculationRecupereeService = immatriculationRecupereeService;
        this.immatriculationRecupereeQueryService = immatriculationRecupereeQueryService;
        this.userRepository = userRepository;
        this.verifierExistenceEmployeur = verifierExistenceEmployeur;
        this.documentUrlService = documentUrlService;
        this.agenceRepository = agenceRepository;
    }

    /**
     * {@code POST  /immatriculation-recuperees} : Create a new immatriculationRecuperee.
     *
     * @param immatriculationRecupereeDTO the immatriculationRecupereeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new immatriculationRecupereeDTO, or with status {@code 400 (Bad Request)} if the immatriculationRecuperee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/immatriculation-recuperees")
    public ResponseEntity<ImmatriculationRecupereeDTO> createImmatriculationRecuperee(@RequestBody ImmatriculationRecupereeDTO immatriculationRecupereeDTO) throws URISyntaxException {
        log.debug("REST request to save ImmatriculationRecuperee : {}", immatriculationRecupereeDTO);
        if (immatriculationRecupereeDTO.getId() != null) {
            throw new BadRequestAlertException("A new immatriculationRecuperee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        immatriculationRecupereeDTO.setDate(Instant.now());
        immatriculationRecupereeDTO.setCreatedAt(Instant.now());
        String statutInexistant = "INEXISTANT";
        if(!statutInexistant.equalsIgnoreCase(immatriculationRecupereeDTO.getStatus()))
            immatriculationRecupereeDTO.setStatus("EN_COURS_DE_VERIFICATION");
        String numUnique = null;
        try {
            String path = documentUrlService.transformBase64ToURL(immatriculationRecupereeDTO.getMandatFile(), "mandat");
            immatriculationRecupereeDTO.setMandatFile(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw Problem.builder().withDetail("Erreur lors de l'enregistrement du mandat").withTitle("Erreur lors de l'enregistrement du mandat").build();
        }
        if (immatriculationRecupereeDTO.getStatus().equalsIgnoreCase("EN_COURS_DE_VERIFICATION") || immatriculationRecupereeDTO.getStatus().equalsIgnoreCase("ACTIVEE")) {
            CheckResponse test = immatriculationRecupereeService.checkOldImmatExist(immatriculationRecupereeDTO.getNumeroUnique());
            numUnique = test.getNumeroUnique();
            immatriculationRecupereeDTO.setNumeroUnique(numUnique);
        }

        Long userId = getCurrentUserLogin().map(userRepository::findByLogin).map(User::getId).orElse(null);
        immatriculationRecupereeDTO.setUserId(userId);

        ImmatriculationRecupereeDTO result = immatriculationRecupereeService.save(immatriculationRecupereeDTO);
        result.setExtrasInfo(immatriculationRecupereeDTO.getExtrasInfo());
        if (!StringUtils.isEmpty(result.getAgenceIpres()))
            result.setAgenceIPRESObject(agenceRepository.findByCode(result.getAgenceIpres()));

        if (!StringUtils.isEmpty(result.getAgenceCss()))
            result.setAgenceCSSObject(agenceRepository.findByCode(result.getAgenceCss()));

        result.setUser(userRepository.findById(result.getUserId()).orElse(null));
        if (result.getAgentId() != null) {
            result.setAgent(userRepository.findById(result.getAgentId()).map(AgentDTO::new).orElse(null));
        }

        return ResponseEntity.created(new URI("/api/immatriculation-recuperees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /immatriculation-recuperees} : Updates an existing immatriculationRecuperee.
     *
     * @param immatriculationRecupereeDTO the immatriculationRecupereeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated immatriculationRecupereeDTO,
     * or with status {@code 400 (Bad Request)} if the immatriculationRecupereeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the immatriculationRecupereeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/immatriculation-recuperees")
    public ResponseEntity<ImmatriculationRecupereeDTO> updateImmatriculationRecuperee(@RequestBody ImmatriculationRecupereeDTO immatriculationRecupereeDTO) throws URISyntaxException {
        log.debug("REST request to update ImmatriculationRecuperee : {}", immatriculationRecupereeDTO);
        if (immatriculationRecupereeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImmatriculationRecupereeDTO result = immatriculationRecupereeService.save(immatriculationRecupereeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, immatriculationRecupereeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /immatriculation-recuperees} : get all the immatriculationRecuperees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of immatriculationRecuperees in body.
     */
    @GetMapping("/immatriculation-recuperees")
    public ResponseEntity<List<ImmatriculationRecupereeDTO>> getAllImmatriculationRecuperees(ImmatriculationRecupereeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ImmatriculationRecuperees by criteria: {}", criteria);
        Page<ImmatriculationRecupereeDTO> page = immatriculationRecupereeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /immatriculation-recuperees/count} : count all the immatriculationRecuperees.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/immatriculation-recuperees/count")
    public ResponseEntity<Long> countImmatriculationRecuperees(ImmatriculationRecupereeCriteria criteria) {
        log.debug("REST request to count ImmatriculationRecuperees by criteria: {}", criteria);
        return ResponseEntity.ok().body(immatriculationRecupereeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /immatriculation-recuperees/:id} : get the "id" immatriculationRecuperee.
     *
     * @param id the id of the immatriculationRecupereeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the immatriculationRecupereeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/immatriculation-recuperees/{id}")
    public ResponseEntity<ImmatriculationRecupereeDTO> getImmatriculationRecuperee(@PathVariable Long id) {
        log.debug("REST request to get ImmatriculationRecuperee : {}", id);
        Optional<ImmatriculationRecupereeDTO> immatriculationRecupereeDTO = immatriculationRecupereeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(immatriculationRecupereeDTO);
    }

    /**
     * {@code DELETE  /immatriculation-recuperees/:id} : delete the "id" immatriculationRecuperee.
     *
     * @param id the id of the immatriculationRecupereeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/immatriculation-recuperees/{id}")
    public ResponseEntity<Void> deleteImmatriculationRecuperee(@PathVariable Long id) {
        log.debug("REST request to delete ImmatriculationRecuperee : {}", id);
        immatriculationRecupereeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
