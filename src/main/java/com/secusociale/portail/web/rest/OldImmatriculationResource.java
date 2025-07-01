package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.ImmatriculationRecupereeQueryService;
import com.secusociale.portail.service.ImmatriculationRecupereeService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeCriteria;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeDTO;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import com.secusociale.portail.service.dto.custom.ReuploadMandatDTO;
import com.secusociale.portail.service.dto.custom.UpdateOldImmatriculation;
import com.secusociale.portail.service.immatriculation.VerifierExistenceEmployeur;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.web.rest.vm.CheckResponse;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;
import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.OldImmatriculation}.
 */
@RestController
@RequestMapping("/api")
public class OldImmatriculationResource {

    private static final String ENTITY_NAME = "OldImmatriculation";
    private final Logger log = LoggerFactory.getLogger(OldImmatriculationResource.class);
    private final ImmatriculationRecupereeService oldImmatriculationService;
    private final ImmatriculationRecupereeQueryService oldImmatriculationQueryService;
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerifierExistenceEmployeur verifierExistenceEmployeur;
    @Autowired
    private DocumentUrlService documentUrlService;
    @Autowired
    private AgenceRepository agenceRepository;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public OldImmatriculationResource(ImmatriculationRecupereeService oldImmatriculationService, ImmatriculationRecupereeQueryService oldImmatriculationQueryService, UserService userService) {
        this.oldImmatriculationService = oldImmatriculationService;
        this.oldImmatriculationQueryService = oldImmatriculationQueryService;
        this.userService = userService;
    }

    /**
     * {@code POST  /old-immatriculations} : Create a new oldImmatriculation.
     *
     * @param oldImmatriculationDTO the oldImmatriculationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oldImmatriculationDTO, or with status {@code 400 (Bad Request)} if the oldImmatriculation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/old-immatriculations")
    public ResponseEntity<ImmatriculationRecupereeDTO> createOldImmatriculation(@RequestBody ImmatriculationRecupereeDTO oldImmatriculationDTO) throws URISyntaxException {
        log.debug("REST request to save OldImmatriculation : {}", oldImmatriculationDTO);
        oldImmatriculationDTO.setDate(Instant.now());
        if (oldImmatriculationDTO.getStatus() != null && !oldImmatriculationDTO.getStatus().equalsIgnoreCase("INEXISTANT"))
            oldImmatriculationDTO.setStatus("EN_COURS_DE_VERIFICATION");
        String numUnique = null;
        if (oldImmatriculationDTO.getId() != null) {
            throw new BadRequestAlertException("A new oldImmatriculation cannot already have an ID", ENTITY_NAME, "idexists");
        }

        try {
            String path = documentUrlService.transformBase64ToURL(oldImmatriculationDTO.getMandatFile(), "mandat");
            oldImmatriculationDTO.setMandatFile(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw Problem.builder().withDetail("Erreur lors de l'enregistrement du mandat").withTitle("Erreur lors de l'enregistrement du mandat").build();
        }
        if(StringUtils.isNotEmpty(oldImmatriculationDTO.getStatus()))
            if (oldImmatriculationDTO.getStatus().equalsIgnoreCase("EN_COURS_DE_VERIFICATION") || oldImmatriculationDTO.getStatus().equalsIgnoreCase("ACTIVEE")) {
                CheckResponse test = oldImmatriculationService.checkOldImmatExist(oldImmatriculationDTO.getNumeroUnique());
                numUnique = test.getNumeroUnique();
                oldImmatriculationDTO.setNumeroUnique(numUnique);
            }


        Long userId = getCurrentUserLogin().map(userRepository::findByLogin).map(User::getId).orElse(null);
        oldImmatriculationDTO.setUserId(userId);

        ImmatriculationRecupereeDTO result = oldImmatriculationService.save(oldImmatriculationDTO);
        result.setExtrasInfo(oldImmatriculationDTO.getExtrasInfo());
        result = oldImmatriculationService.setAgenceRattachement(result);

        if (StringUtils.isNotEmpty(result.getAgenceIpres()))
            result.setAgenceIPRESObject(agenceRepository.findByCode(result.getAgenceIpres()));

        if (StringUtils.isNotEmpty(result.getAgenceCss()))
            result.setAgenceCSSObject(agenceRepository.findByCode(result.getAgenceCss()));



        result.setUser(userRepository.findById(result.getUserId()).orElse(null));
        if (result.getAgentId() != null) {
            result.setAgent(userRepository.findById(result.getAgentId()).map(AgentDTO::new).orElse(null));
        }
        return ResponseEntity.created(new URI("/api/old-immatriculations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /old-immatriculations} : Updates an existing oldImmatriculation.
     *
     * @param oldImmatriculationDTO the oldImmatriculationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oldImmatriculationDTO,
     * or with status {@code 400 (Bad Request)} if the oldImmatriculationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oldImmatriculationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/old-immatriculations")
    public ResponseEntity<ImmatriculationRecupereeDTO> updateOldImmatriculation(@RequestBody ImmatriculationRecupereeDTO oldImmatriculationDTO) {
        log.debug("REST request to update OldImmatriculation : {}", oldImmatriculationDTO);
        if (oldImmatriculationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImmatriculationRecupereeDTO result = oldImmatriculationService.save(oldImmatriculationDTO);
        result.setAgenceIPRESObject(agenceRepository.findByCode(result.getAgenceIpres()));
        result.setAgenceCSSObject(agenceRepository.findByCode(result.getAgenceCss()));
        result.setUser(userRepository.findById(result.getUserId()).orElse(null));
        if (result.getAgentId() != null) {
            result.setAgent(userRepository.findById(result.getAgentId()).map(AgentDTO::new).orElse(null));
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, oldImmatriculationDTO.getId().toString()))
            .body(result);
    }


    @PostMapping("/old-immatriculations-traitement")
   // @PreAuthorize("hasRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")
    public ResponseEntity<HashMap<String, Object>> traiterDemande(@RequestBody UpdateOldImmatriculation immatriculation) {
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            User agent = userRepository.findByLogin(loggedusername.get());
            if (oldImmatriculationService.findOne(immatriculation.getId()).isPresent()) {
                ImmatriculationRecupereeDTO imm = oldImmatriculationService.findOne(immatriculation.getId()).get();
                String message = "";
                if (immatriculation.getValid()) {
                    imm.setStatus("ACTIVEE");
                    message = "Immatriculation acceptée avec succès";
                } else {
                    imm.setStatus("REJETEE");
                    message = "Immatriculation rejetée avec succès";
                    imm.setMotif(immatriculation.getMotif());
                    result.put("motif", immatriculation.getMotif());
                }
                imm.setAgentId(agent.getId());
                imm.setDateTraitement(Instant.now());
                ImmatriculationRecupereeDTO obj = oldImmatriculationService.save(imm);
                obj.setAgenceIPRESObject(agenceRepository.findByCode(obj.getAgenceIpres()));
                obj.setAgenceCSSObject(agenceRepository.findByCode(obj.getAgenceCss()));
                obj.setUser(userRepository.findById(obj.getUserId()).orElse(null));
                if (obj.getAgentId() != null) {
                    obj.setAgent(userRepository.findById(obj.getAgentId()).map(AgentDTO::new).orElse(null));
                }
                result.put("code", "200");
                result.put("message", message);
                result.put("immatriculation", obj);
            } else {
                result.put("code", "400");
                result.put("error", "Aucune demande trouvée!");
            }
        } else {
            result.put("code", "400");
            result.put("error", "Veuillez vous connecter");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /old-immatriculations} : get all the oldImmatriculations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oldImmatriculations in body.
     */
    @GetMapping("/old-immatriculations")
    public ResponseEntity<List<ImmatriculationRecupereeDTO>> getAllOldImmatriculations(ImmatriculationRecupereeCriteria criteria, @RequestParam(value = "statut", defaultValue = "", required = false) String statut) {
        log.debug("REST request to get OldImmatriculations by criteria: {}", criteria);
        if (!StringUtils.isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatus(stringFilter);
        }
        List<ImmatriculationRecupereeDTO> entityList = oldImmatriculationQueryService.findByCriteria(criteria);
        entityList.forEach(el -> {
            el.setAgenceCSSObject(agenceRepository.findByCode(el.getAgenceCss()));
            el.setAgenceIPRESObject(agenceRepository.findByCode(el.getAgenceIpres()));
            el.setUser(userRepository.findById(el.getUserId()).orElse(null));
            if (el.getAgentId() != null) {
                AgentDTO agentDTO = userRepository.findById(el.getAgentId()).map(AgentDTO::new).orElse(null);
                if (agentDTO != null) {
                    agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                    el.setAgent(agentDTO);
                }
            }
        });
        return ResponseEntity.ok().body(entityList);
    }


    /**
     * {@code GET  /old-immatriculations} : get all the oldImmatriculations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the pageable which the requested entities should filter.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oldImmatriculations in body.
     */
    @GetMapping("/old-immatriculations-agent")
    //@PreAuthorize("hasRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllOldImmatriculations(@PathParam("query") String query, ImmatriculationRecupereeCriteria criteria, Pageable pageable) throws ParseException {
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        InstantFilter ifl = new InstantFilter();
        if (!isEmpty(query)) {
            ifl.setGreaterThanOrEqual(new SimpleDateFormat("yyyy-MM-dd").parse(query).toInstant());
            ifl.setLessThan(new SimpleDateFormat("yyyy-MM-dd").parse(query).toInstant().plus(1, ChronoUnit.DAYS));
            criteria.setDate(ifl);
        }
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            if ( isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                User user = userRepository.findByLogin(username);
                StringFilter sf = new StringFilter();
                StringFilter queryFilter = new StringFilter();
                LongFilter lf = new LongFilter();
               /* if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                    sf.setEquals("NO_AGENCE_FILTER");
                }
*/
                if (!isEmpty(user.getAgence())) {
                    if (!isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setContains(user.getAgence());
                                criteria.setAgenceCss(sf);
                            }
                        }
                        if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setEquals(user.getAgence());
                                criteria.setAgenceIpres(sf);
                            }
                        }
                    } else {
                        criteria.setAgenceIpres(sf);
                    }
                } else {
                    criteria.setAgenceIpres(sf);
                }
//                String currentStatus = criteria.getStatus().getEquals();
//                if ((currentStatus != null && !currentStatus.equalsIgnoreCase("INEXISTANT"))) {
//                    if (!currentStatus.equalsIgnoreCase("DOUBLON"))
//
//                }
            }
            log.debug("REST request to get OldImmatriculations by criteria: {}", criteria);
            Page<ImmatriculationRecupereeDTO> page = oldImmatriculationQueryService.findByCriteria(criteria, pageable);
            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<ImmatriculationRecupereeDTO> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSSObject(agenceRepository.findByCode(el.getAgenceCss()));
                el.setAgenceIPRESObject(agenceRepository.findByCode(el.getAgenceIpres()));
                el.setUser(userRepository.findById(el.getUserId()).orElse(null));
                if (el.getAgentId() != null) {
                    AgentDTO agentDTO = userRepository.findById(el.getAgentId()).map(AgentDTO::new).orElse(null);
                    if (agentDTO != null) {
                        agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                        el.setAgent(agentDTO);
                    }
                }
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        } else {
            return null;
        }
    }

    /**
     * {@code GET  /old-immatriculations} : get all the oldImmatriculations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the pageable which the requested entities should filter.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oldImmatriculations in body.
     */
    @GetMapping("/old-immatriculations-admin")
    //@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllOldImmatriculationsAdmin(ImmatriculationRecupereeCriteria criteria, Pageable pageable, @RequestParam(value = "statut", defaultValue = "", required = false) String statut, @RequestParam(value = "userEmail", defaultValue = "", required = false) String userEmail) {
        HashMap<String, Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatus(stringFilter);
        }

        if (!StringUtils.isEmpty(userEmail)) {
            List<Long> ids = userService.getUsersByEmailLike("%"+userEmail+"%").stream().map(User::getId).collect(Collectors.toList());
            LongFilter longFilter = new LongFilter();
            longFilter.setIn(ids);
            criteria.setUserId(longFilter);
        }

        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            Page<ImmatriculationRecupereeDTO> page = oldImmatriculationQueryService.findByCriteria(criteria, pageable);
            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<ImmatriculationRecupereeDTO> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSSObject(agenceRepository.findByCode(el.getAgenceCss()));
                el.setAgenceIPRESObject(agenceRepository.findByCode(el.getAgenceIpres()));
                el.setUser(userRepository.findById(el.getUserId()).orElse(null));
                if (el.getAgentId() != null) {
                    AgentDTO agentDTO = userRepository.findById(el.getAgentId()).map(AgentDTO::new).orElse(null);
                    if (agentDTO != null) {
                        agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                        el.setAgent(agentDTO);
                    }
                }
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        } else {
            return null;
        }
    }


    @GetMapping("/old-immatriculations/mines")
    public ResponseEntity<List<ImmatriculationRecupereeDTO>> getMyOldImmatriculations() {
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        log.debug("REST request to get OldImmatriculations by user: {}", username);
        Long userId = userRepository.findByLogin(username).getId();
        List<ImmatriculationRecupereeDTO> entityList = oldImmatriculationService.findAllByUser(userId);
        entityList.forEach(el -> {
            el.setAgenceCSSObject(agenceRepository.findByCode(el.getAgenceCss()));
            el.setAgenceIPRESObject(agenceRepository.findByCode(el.getAgenceIpres()));
            el.setUser(userRepository.findById(el.getUserId()).orElse(null));
            if (el.getAgentId() != null) {
                AgentDTO agentDTO = userRepository.findById(el.getAgentId()).map(AgentDTO::new).orElse(null);
                if (agentDTO != null) {
                    agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                    el.setAgent(agentDTO);
                }
            }
        });
        return ResponseEntity.ok().body(entityList);
    }

    @GetMapping("/old-immatriculations/mines/{userId}")
    public ResponseEntity<List<ImmatriculationRecupereeDTO>> getMyOldImmatriculations(@PathVariable Long userId) {
        log.debug("REST request to get OldImmatriculations by user: {}", userId);
        List<ImmatriculationRecupereeDTO> entityList = oldImmatriculationService.findAllByUser(userId);
        entityList.forEach(el -> {
            el.setAgenceCSSObject(agenceRepository.findByCode(el.getAgenceCss()));
            el.setAgenceIPRESObject(agenceRepository.findByCode(el.getAgenceIpres()));
            el.setUser(userRepository.findById(el.getUserId()).orElse(null));
            if (el.getAgentId() != null) {
                AgentDTO agentDTO = userRepository.findById(el.getAgentId()).map(AgentDTO::new).orElse(null);
                if (agentDTO != null) {
                    agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                    el.setAgent(agentDTO);
                }
            }
        });
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /old-immatriculations/count} : count all the oldImmatriculations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/old-immatriculations/count")
    public ResponseEntity<Long> countOldImmatriculations(ImmatriculationRecupereeCriteria criteria) {
        log.debug("REST request to count OldImmatriculations by criteria: {}", criteria);
        return ResponseEntity.ok().body(oldImmatriculationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /old-immatriculations/:id} : get the "id" oldImmatriculation.
     *
     * @param id the id of the oldImmatriculationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oldImmatriculationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/old-immatriculations/{id}")
    public ResponseEntity<ImmatriculationRecupereeDTO> getOldImmatriculation(@PathVariable Long id) {
        log.debug("REST request to get OldImmatriculation : {}", id);
        Optional<ImmatriculationRecupereeDTO> oldImmatriculationDTO = oldImmatriculationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oldImmatriculationDTO);
    }

    /**
     * {@code DELETE  /old-immatriculations/:id} : delete the "id" oldImmatriculation.
     *
     * @param id the id of the oldImmatriculationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/old-immatriculations/{id}")
    public ResponseEntity<Void> deleteOldImmatriculation(@PathVariable Long id) {
        log.debug("REST request to delete OldImmatriculation : {}", id);
        oldImmatriculationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /old-immatriculations/:type/:value}
     *
     * @param type  the type of the oldImmatriculation to check.
     * @param value the value of the oldImmatriculation to check.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @GetMapping("/old-immatriculations/check/{type}/{value}")
    public ResponseEntity<CheckResponse> checkOldImmatriculation(@PathVariable String type, @PathVariable String value) {
        log.debug("REST request to check OldImmatriculation with search: type:{}, value:{}", type, value);
        try {
            return ResponseEntity.ok().body(oldImmatriculationService.checkOldImmatExist(type, value));
        } catch (Exception e) {
            throw Problem.builder().withStatus(Status.BAD_REQUEST).withDetail(e.getMessage()).build();
        }
    }

    /**
     * * {@code GET  /old-immatriculations/:type/:value}
     *
     * @param numIpres the old IPres ID
     * @param numCss   the CSS ID
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */


    @GetMapping("/old-immatriculation/check")
    public ResponseEntity<CheckResponse> checkOldImmatriculationNew(@PathParam("numIpres") String numIpres, @PathParam("numCss") String numCss, @PathParam("id") Long id, @PathParam("currentStatus") String currentStatus) {
        log.debug("REST request to check OldImmatriculation with search: numIpres:{}, numCss:{}", numIpres, numCss);
        try {
            CheckResponse cr = oldImmatriculationService.checkOldImmatExistNew(numIpres, numCss, id, currentStatus);
            return ResponseEntity.ok().body(cr);
        } catch (Exception e) {
            throw Problem.builder().withStatus(Status.BAD_REQUEST).withDetail(e.getMessage()).build();
        }
    }

    /**
     * {@code GET  /old-immatriculations/:type/:value}
     *
     * @param numeroUnique the personId of the oldImmatriculation to check.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @GetMapping("/old-immatriculations/check/{numeroUnique}")
    public ResponseEntity<CheckResponse> checkOldImmatriculation(@PathVariable String numeroUnique) {
        log.debug("REST request to check OldImmatriculation with search: numeroUnique:{}", numeroUnique);
        try {
            return ResponseEntity.ok().body(oldImmatriculationService.checkOldImmatExist(numeroUnique));
        } catch (Exception e) {
            throw Problem.builder().withStatus(Status.BAD_REQUEST).withDetail(e.getMessage()).build();
        }
    }

    @PostMapping("/old-immatriculations/reuploadmandat")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HashMap<String, Object>> reuploadmandat(@Valid @RequestBody ReuploadMandatDTO newMandat) {
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            if (oldImmatriculationService.findOne(newMandat.getId()).isPresent()) {
                ImmatriculationRecupereeDTO imm = oldImmatriculationService.findOne(newMandat.getId()).get();

                try {
                    String path = documentUrlService.uploadMandat(base64ToMultipart(newMandat.getMandatFile(), "mandat"));
                    imm.setMandatFile(path);
                    imm.setStatus("EN_COURS_DE_VERIFICATION");
                    ImmatriculationRecupereeDTO obj = oldImmatriculationService.save(imm);
                    result.put("code", "200");
                    result.put("obj", obj);
                } catch (IOException e) {
                    e.printStackTrace();
                    result.put("code", "400");
                    result.put("error", e.getMessage());
                    result.put("message", "Mandat non recharger");
//                    throw Problem.builder().withStatus(Status.BAD_REQUEST).withDetail(e.getMessage()).build();
                }
            }

        } else {
            result.put("code", "400");
            result.put("error", "Veuillez vous connecter");
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/old-immatriculations/update-informations")
    public ResponseEntity<ImmatriculationRecupereeDTO> updateInformations(@RequestBody ImmatriculationRecupereeDTO oldImmatriculationDTO) {
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();

        return ResponseEntity.ok(oldImmatriculationDTO);
    }
}
