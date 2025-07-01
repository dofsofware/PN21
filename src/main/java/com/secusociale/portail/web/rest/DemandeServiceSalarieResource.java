package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.DemandeService;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.DemandeServiceSalarieQueryService;
import com.secusociale.portail.service.DemandeServiceSalarieService;
import com.secusociale.portail.service.SalarieService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.utils.UtilsService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.DemandeServiceSalarie}.
 */
@RestController
@RequestMapping("/api")
public class DemandeServiceSalarieResource {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceSalarieResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2DemandeServiceSalarie";
    private final UtilsService utilsService;
    private final AgenceRepository agenceRepository;
    private final SalarieRepository salarieRepository;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeServiceSalarieService demandeServiceSalarieService;

    private final DemandeServiceSalarieQueryService demandeServiceSalarieQueryService;

    private final UserRepository userRepository;
    private final UserService userService;
    private final SalarieService salarieService;

    public DemandeServiceSalarieResource(DemandeServiceSalarieService demandeServiceSalarieService, DemandeServiceSalarieQueryService demandeServiceSalarieQueryService, UserRepository userRepository, UserService userService, SalarieService salarieService, UtilsService utilsService, AgenceRepository agenceRepository, SalarieRepository salarieRepository) {
        this.demandeServiceSalarieService = demandeServiceSalarieService;
        this.demandeServiceSalarieQueryService = demandeServiceSalarieQueryService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.salarieService = salarieService;
        this.utilsService = utilsService;
        this.agenceRepository = agenceRepository;
        this.salarieRepository = salarieRepository;
    }

    /**
     * {@code POST  /demande-service-salaries} : Create a new demandeServiceSalarie.
     *
     * @param demandeServiceSalarieDTO the demandeServiceSalarieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandeServiceSalarieDTO, or with status {@code 400 (Bad Request)} if the demandeServiceSalarie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demande-service-salaries")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<DemandeServiceSalarieDTO> createDemandeServiceSalarie(@Valid @RequestBody DemandeServiceSalarieDTO demandeServiceSalarieDTO) throws URISyntaxException {
        log.debug("REST request to save DemandeServiceSalarie : {}", demandeServiceSalarieDTO);
        if (demandeServiceSalarieDTO.getId() != null) {
            throw new BadRequestAlertException("A new demandeServiceSalarie cannot already have an ID", ENTITY_NAME, "idexists");
        }

        demandeServiceSalarieDTO.setDate(Instant.now());

        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findByLogin(username);

        Long userId = user.getId();

        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        if (!salarieDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas salarie, vous ne pouvez pas acceder a cette ressource").withTitle("Erreur").build();
        }
        SalarieDTO sdto = salarieDTO.get();
        Long salarieId = sdto.getId();

        demandeServiceSalarieDTO.setSalarieId(salarieId);

        DemandeServiceSalarieDTO result = demandeServiceSalarieService.save(demandeServiceSalarieDTO);
        result.setSalarieDTO(sdto);
        return ResponseEntity.created(new URI("/api/demande-service-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demande-service-salaries} : Updates an existing demandeServiceSalarie.
     *
     * @param demandeServiceSalarieDTO the demandeServiceSalarieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandeServiceSalarieDTO,
     * or with status {@code 400 (Bad Request)} if the demandeServiceSalarieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandeServiceSalarieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demande-service-salaries")
    public ResponseEntity<DemandeServiceSalarieDTO> updateDemandeServiceSalarie(@Valid @RequestBody DemandeServiceSalarieDTO demandeServiceSalarieDTO) throws URISyntaxException {
        log.debug("REST request to update DemandeServiceSalarie : {}", demandeServiceSalarieDTO);
        if (demandeServiceSalarieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        demandeServiceSalarieDTO.setDate(Instant.now());
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(username);
        if (!user.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        if (isCurrentUserInRole(AuthoritiesConstants.SALARIE)) {
            demandeServiceSalarieDTO.setStatut("EN_COURS");
            demandeServiceSalarieDTO.setGestionnaireId(null);
            Optional<SalarieDTO> salarie = salarieService.findByUserId(user.get().getId());
            salarie.ifPresent(salarieDTO -> demandeServiceSalarieDTO.setSalarieId(salarieDTO.getId()));
        } else if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
            demandeServiceSalarieDTO.setDateTraitement(Instant.now());
            demandeServiceSalarieDTO.setGestionnaireId(user.get().getId());
            Optional<UserDTO> userDTO = user.map(UserDTO::new);
            demandeServiceSalarieDTO.setGestionnaireDTO(userDTO.orElse(null));

            if (demandeServiceSalarieDTO.getStatut().equals(Constants.VALIDE)) {
                if (demandeServiceSalarieDTO.getType().equals(Constants.CERTIFICAT_DE_NON_INSCRIPTION)) {
                    Salarie salarie = salarieService.findById(demandeServiceSalarieDTO.getSalarieId()).orElseThrow(() -> new BadRequestAlertException("Salarie introuvable", ENTITY_NAME, "Salarie introuvable"));
                    Agence agence = agenceRepository.findById(demandeServiceSalarieDTO.getAgenceId()).orElseThrow(() -> new BadRequestAlertException("Agence introuvable", ENTITY_NAME, "Agence introuvable"));
                    try {
                        demandeServiceSalarieDTO.setCertificat(utilsService.generateCertificatDeNonInscription(salarie, agence,demandeServiceSalarieDTO.getMatriculeSolde(),demandeServiceSalarieDTO.getFonction(),demandeServiceSalarieDTO.getId(),user.get().getCachet()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (demandeServiceSalarieDTO.getType().equals(Constants.CERTIFICAT_DE_RADIATION)) {
                    Salarie salarie = salarieService.findById(demandeServiceSalarieDTO.getSalarieId()).orElseThrow(() -> new BadRequestAlertException("Salarie introuvable", ENTITY_NAME, "Salarie introuvable"));
                    Agence agence = agenceRepository.findById(demandeServiceSalarieDTO.getAgenceId()).orElseThrow(() -> new BadRequestAlertException("Agence introuvable", ENTITY_NAME, "Agence introuvable"));
                    try {
                        demandeServiceSalarieDTO.setCertificat(utilsService.generateCertificatDeRadiation(salarie, agence,user.get().getCachet()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    salarie.setStatut("RADIE");
                    salarieRepository.save(salarie);

                }
            }
        }
        DemandeServiceSalarieDTO result = demandeServiceSalarieService.save(demandeServiceSalarieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandeServiceSalarieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demande-service-salaries} : Updates an existing demandeServiceSalarie.
     *
     * @param updateDemandeServiceSalarieDTO the demandeServiceSalarieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandeServiceSalarieDTO,
     * or with status {@code 400 (Bad Request)} if the demandeServiceSalarieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandeServiceSalarieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demande-service-salaries/traitement")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\")")
    public ResponseEntity<DemandeServiceSalarieDTO> traiterDemandeServiceSalarie(@Valid @RequestBody UpdateDemandeServiceSalarieDTO updateDemandeServiceSalarieDTO) throws URISyntaxException {
        log.debug("REST request to update DemandeServiceSalarie : {}", updateDemandeServiceSalarieDTO);
        if (updateDemandeServiceSalarieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemandeServiceSalarieDTO demandeServiceSalarieDTO = demandeServiceSalarieService.findOne(updateDemandeServiceSalarieDTO.getId()).orElse(null);
        if (demandeServiceSalarieDTO == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        demandeServiceSalarieDTO.setStatut(updateDemandeServiceSalarieDTO.getStatut());
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(username);
        if (!user.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE)) {
            demandeServiceSalarieDTO.setDateTraitement(Instant.now());
            demandeServiceSalarieDTO.setGestionnaireId(user.get().getId());
            Optional<UserDTO> userDTO = user.map(UserDTO::new);
            demandeServiceSalarieDTO.setGestionnaireDTO(userDTO.orElse(null));
        }
        DemandeServiceSalarieDTO result = demandeServiceSalarieService.save(demandeServiceSalarieDTO);
        result.setGestionnaireDTO(demandeServiceSalarieDTO.getGestionnaireDTO());
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandeServiceSalarieDTO.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /demande-service-salaries} : get all the demandeServiceSalaries.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeServiceSalaries in body.
     */
    @GetMapping("/demande-service-salaries")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllDemandeServiceSalaries(DemandeServiceSalarieCriteria criteria, Pageable pageable) {
        HashMap<String, Object> result = new HashMap<>();
        log.debug("REST request to get DemandeServiceSalaries by criteria: {}", criteria);
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(username);
        if (!user.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }

        LongFilter lf = new LongFilter();

        System.out.println("userId=" + user.get().getId());
        if (isCurrentUserInRole(AuthoritiesConstants.SALARIE)) {
            Optional<SalarieDTO> salarie = salarieService.findByUserId(user.get().getId());
            salarie.ifPresent(salarieDTO -> lf.setEquals(salarieDTO.getId()));
            criteria.setSalarieId(lf);
        } else if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE)) {
            System.out.println("gest kay");
            lf.setEquals(user.get().getId());
//            criteria.setGestionnaireId(lf);
        }
        Page<DemandeServiceSalarieDTO> page = demandeServiceSalarieQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }


    /**
     * {@code GET  /demande-service-salaries} : get all the demandeServiceSalaries.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeServiceSalaries in body.
     */
    @GetMapping("/demande-service-salaries-traitees")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllDemandeServiceSalariesTraitees(DemandeServiceSalarieCriteria criteria, Pageable pageable) {
        HashMap<String, Object> result = new HashMap<>();
        log.debug("REST request to get DemandeServiceSalaries by criteria: {}", criteria);
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(username);
        if (!user.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }

        LongFilter lf = new LongFilter();

        if (isCurrentUserInRole(AuthoritiesConstants.SALARIE)) {
            Optional<SalarieDTO> salarie = salarieService.findByUserId(user.get().getId());
            salarie.ifPresent(salarieDTO -> lf.setEquals(salarieDTO.getId()));
            criteria.setSalarieId(lf);
            LongFilter longFilter = new LongFilter();
            longFilter.setNotEquals(null);
            criteria.setGestionnaireId(longFilter);
            StringFilter sf = new StringFilter();
            sf.setNotEquals("EN_COURS");
            criteria.setStatut(sf);
        } else if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE)) {
            lf.setEquals(user.get().getId());
            criteria.setGestionnaireId(lf);
            StringFilter sf = new StringFilter();
            sf.setNotEquals("EN_COURS");
            criteria.setStatut(sf);
        }
        Page<DemandeServiceSalarieDTO> page = demandeServiceSalarieQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }


    /**
     * {@code GET  /demande-service-salaries/count} : count all the demandeServiceSalaries.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/demande-service-salaries/count")
    public ResponseEntity<Long> countDemandeServiceSalaries(DemandeServiceSalarieCriteria criteria) {
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(username);
        if (!user.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        LongFilter lf = new LongFilter();
        lf.setEquals(user.get().getId());
        if (isCurrentUserInRole(AuthoritiesConstants.SALARIE)) {
            criteria.setSalarieId(lf);
        } else if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE)) {
            criteria.setGestionnaireId(lf);
        }
        log.debug("REST request to count DemandeServiceSalaries by criteria: {}", criteria);
        return ResponseEntity.ok().body(demandeServiceSalarieQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /demande-service-salaries/:id} : get the "id" demandeServiceSalarie.
     *
     * @param id the id of the demandeServiceSalarieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandeServiceSalarieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demande-service-salaries/{id}")
    public ResponseEntity<DemandeServiceSalarieDTO> getDemandeServiceSalarie(@PathVariable Long id) {
        log.debug("REST request to get DemandeServiceSalarie : {}", id);
        Optional<DemandeServiceSalarieDTO> demandeServiceSalarieDTO = demandeServiceSalarieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeServiceSalarieDTO);
    }

    /**
     * {@code DELETE  /demande-service-salaries/:id} : delete the "id" demandeServiceSalarie.
     *
     * @param id the id of the demandeServiceSalarieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demande-service-salaries/{id}")
    public ResponseEntity<Void> deleteDemandeServiceSalarie(@PathVariable Long id) {
        log.debug("REST request to delete DemandeServiceSalarie : {}", id);
        demandeServiceSalarieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /demande-service-agents} : get all the demandeServiceAgents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeServiceSalaries in body.
     */
    @GetMapping("/demande-service-agent")
   // @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasRole('" + AuthoritiesConstants.AGENT + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "') or hasRole('" + AuthoritiesConstants.CHEF_AGENCE + "')")
    public ResponseEntity<HashMap<String, Object>> getAllDemandeServiceAgents(
        @RequestParam(required = false, name = "dateQueries") String dateQueries,
        DemandeServiceSalarieCriteria criteria, Pageable pageable) throws ParseException {
        HashMap<String, Object> result = new HashMap<>();
        log.debug("REST request to get DemandeServiceAgents by criteria: {}", criteria);

        // Vérifiez si l'utilisateur est connecté
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour accéder à cette ressource").withTitle("Erreur").build();
        }

        String username = getCurrentUserLogin().get();
        Optional<User> user = userRepository.findOneWithAuthoritiesByLogin(username);

        if (!user.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour accéder à cette ressource").withTitle("Erreur").build();
        }
        HashMap<String, String> parsed = new HashMap<>();
        if (dateQueries != null && !dateQueries.trim().isEmpty()) {
            try {
                parsed = (HashMap<String, String>) new ObjectMapper().readValue(dateQueries, Object.class);
            } catch (JsonProcessingException e) {
                log.error("Error parsing dateQueries: {}", e.getMessage());
                HashMap<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("code", "400");
                errorResponse.put("message", "Invalid dateQueries format");
                return ResponseEntity.badRequest().body(errorResponse);
            }
        }

        if (parsed != null && !parsed.isEmpty()) {
            if (!isEmpty(parsed.get("date"))) {
                InstantFilter ifl = new InstantFilter();
                ifl.setGreaterThanOrEqual(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("date")).toInstant());
                ifl.setLessThan(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("date")).toInstant().plus(1, ChronoUnit.DAYS));
                criteria.setDate(ifl);
            }
        }
        // Ajout du tri par createdAt
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<DemandeServiceSalarieDTO> page = demandeServiceSalarieQueryService.findByCriteria(criteria, sortedPageable);

        // Préparation de la réponse
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());

        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @GetMapping("/demande-service-salaries-by")
    public ResponseEntity<HashMap<String, Object>> getDemandeServiceByIdSalarie(DemandeServiceSalarieCriteria criteria, Pageable pageable) {
        HashMap<String, Object> result = new HashMap<>();
        Page<DemandeServiceSalarieDTO> page = demandeServiceSalarieQueryService.findByCriteria(criteria, pageable);

        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());

        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }
}
