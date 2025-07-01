package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.domain.enumeration.StatutSynchronisation;
import com.secusociale.portail.domain.enumeration.TypeDePiece;
import com.secusociale.portail.repository.*;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.dto.custom.CallBackDTO;
import com.secusociale.portail.service.dto.custom.LastDeclarationDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * REST controller for managing {@link com.secusociale.portail.domain.Declaration}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DeclarationResource {

    private static final String ENTITY_NAME = "portailCssIpresV2Declaration";
    private final Logger log = LoggerFactory.getLogger(DeclarationResource.class);
    private final DeclarationRepository declarationRepository;
    private final DeclarationJournalRepository declarationJournalRepository;
    private final DeclarationQueryService declarationQueryService;
    private final DeclarationSupprimeeQueryService declarationSupprimeeQueryService;
    private final DeclarationService declarationService;
    private final JournalDeclarationService journalDeclarationService;
    @Autowired
    private FactureDNSRepository factureDNSRepository;
    @Autowired
    private FactureDNSSupprimeRepository factureDNSSupprimeRepository;
    @Autowired
    private DeclarationSupprimeRepository declarationSupprimeRepository;

    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeclarationJournalJobRepository declarationJournalJobRepository;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public DeclarationResource(DeclarationRepository declarationRepository, DeclarationJournalRepository declarationJournalRepository, DeclarationQueryService declarationQueryService, DeclarationSupprimeeQueryService declarationSupprimeeQueryService, DeclarationService declarationService, JournalDeclarationService journalDeclarationService, FactureDNSRepository factureDNSRepository, FactureDNSSupprimeRepository factureDNSSupprimeRepository, DeclarationSupprimeRepository declarationSupprimeRepository) {
        this.declarationJournalRepository = declarationJournalRepository;
        this.declarationRepository = declarationRepository;
        this.declarationQueryService = declarationQueryService;
        this.declarationSupprimeeQueryService = declarationSupprimeeQueryService;
        this.declarationService = declarationService;
        this.journalDeclarationService = journalDeclarationService;
        this.factureDNSRepository = factureDNSRepository;
        this.factureDNSSupprimeRepository = factureDNSSupprimeRepository;
        this.declarationSupprimeRepository = declarationSupprimeRepository;
    }

    /**
     * {@code POST  /declarations} : Create a new declaration.
     *
     * @param declaration the declaration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new declaration, or with status {@code 400 (Bad Request)} if the declaration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/declarations")
    public ResponseEntity<Declaration> createDeclaration(@RequestBody Declaration declaration) throws URISyntaxException {
        log.debug("REST request to save Declaration : {}", declaration);
        if (declaration.getId() != null) {
            throw new BadRequestAlertException("A new declaration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Declaration result = declarationRepository.save(declaration);
        return ResponseEntity.created(new URI("/api/declarations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /declarations} : Updates an existing declaration.
     *
     * @param declaration the declaration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated declaration,
     * or with status {@code 400 (Bad Request)} if the declaration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the declaration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/declarations")
    public ResponseEntity<Declaration> updateDeclaration(@RequestBody Declaration declaration) throws URISyntaxException {
        log.debug("REST request to update Declaration : {}", declaration);
        if (declaration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Declaration result = declarationRepository.save(declaration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, declaration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /declarations} : get all the declarations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of declarations in body.
     */
    //OldImmatriculationCriteria criteria, Pageable pageable
    @GetMapping("/declarations")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarations(DeclarationCriteria criteria, Pageable pageable, @RequestParam(value = "statut", defaultValue = "", required = false) String statut) {
        log.debug("REST request to get all Declarations");
        if (!isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatus(stringFilter);
        }
        HashMap<String, Object> result = new HashMap<>();
        Page<Declaration> page = declarationQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<Declaration> list = page.getContent();
        list.forEach(el -> {
            el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
            el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
            el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
        });
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    /**
     * {@code GET  /declarations} : get all the declarations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of declarations in body.
     */
    //OldImmatriculationCriteria criteria, Pageable pageable
    @GetMapping("/declarations-agent")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarationsAgence(@RequestParam(required = false, name = "dateQueries") String dateQueries, DeclarationCriteria criteria, Pageable pageable, @RequestParam(value = "statut", defaultValue = "", required = false) String statut) throws ParseException, JsonProcessingException {
        log.debug("REST request to get all Declarations");
        if (!isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatus(stringFilter);
        }
        HashMap<String, String> parsed = null;
        if (dateQueries != null && !dateQueries.isEmpty()) {
            parsed = (HashMap<String, String>) new ObjectMapper().readValue(dateQueries, Object.class);
        }

        if (parsed != null && !parsed.isEmpty()) {
            if (!isEmpty(parsed.get("date"))) {
                InstantFilter ifl = new InstantFilter();
                ifl.setGreaterThanOrEqual(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("date")).toInstant());
                ifl.setLessThan(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("date")).toInstant().plus(1, ChronoUnit.DAYS));
                criteria.setCreateAt(ifl);
            }
            if (!isEmpty(parsed.get("debut"))) {
                InstantFilter ifl = new InstantFilter();
                ifl.setGreaterThanOrEqual(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("debut")).toInstant());
                ifl.setLessThan(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("debut")).toInstant().plus(1,ChronoUnit.DAYS));
                criteria.setDebutCotisation(ifl);
            }
            if (!isEmpty(parsed.get("fin"))) {
                InstantFilter ifl = new InstantFilter();
                ifl.setGreaterThanOrEqual(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("fin")).toInstant());
                ifl.setLessThan(new SimpleDateFormat("yyyy-MM-dd").parse(parsed.get("fin")).toInstant().plus(1,ChronoUnit.DAYS));
                criteria.setFinCotisation(ifl);
            }
        }
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                User user = userRepository.findByLogin(username);
                StringFilter sf = new StringFilter();
                LongFilter lf = new LongFilter();
                /*if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                    sf.setEquals("");
                }*/
                if (!isEmpty(user.getAgence())) {
                    if (user.getInstitution() != null && !isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setContains(user.getAgence());
                                criteria.setCodeAgenceCSS(sf);
                            }
                        }
                        if (user.getInstitution() != null && user.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setEquals(user.getAgence());
                                criteria.setCodeAgenceIPRES(sf);
                            }
                        }
                    } else {
                        criteria.setCodeAgenceIPRES(sf);
                    }
                } else {
                    criteria.setCodeAgenceCSS(sf);
                }
            }
            HashMap<String, Object> pagination = new HashMap<>();
            HashMap<String, Object> result = new HashMap<>();
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createAt"));
            Page<Declaration> page = declarationQueryService.findByCriteria(criteria, pageable);
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<Declaration> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
                el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
                el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        }
        return null;
    }

    @GetMapping("/declarations-admin")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarationsAgenceAdmin(DeclarationCriteria criteria, Pageable pageable, @RequestParam(value = "statut", defaultValue = "", required = false) String statut) {
        log.debug("REST request to get all Declarations");
        if (!isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatus(stringFilter);
        }
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            HashMap<String, Object> pagination = new HashMap<>();
            HashMap<String, Object> result = new HashMap<>();
            Page<Declaration> page = declarationQueryService.findByCriteria(criteria, pageable);
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<Declaration> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
                el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
                el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        }
        return null;
    }


    @GetMapping("/declarations-agent-errors")
    //@PreAuthorize("hasRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasRole('" + AuthoritiesConstants.AGENT + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "') or hasRole('" + AuthoritiesConstants.SUPER_ADMIN + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "') or hasRole('" + AuthoritiesConstants.CHEF_AGENCE + "')")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarationsAgenceErrors(DeclarationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get all Declarations");
        StringFilter stringFilter = new StringFilter();
        stringFilter.setEquals("INVALIDE");
        criteria.setStatus(stringFilter);
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                User user = userRepository.findByLogin(username);
                StringFilter sf = new StringFilter();
                LongFilter lf = new LongFilter();
              /*  if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                    sf.setEquals("NO_AGENCE_FILTER");
                }*/
                if (!isEmpty(user.getAgence())) {
                    if (!isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setContains(user.getAgence());
                                criteria.setCodeAgenceCSS(sf);
                            }
                        }
                        if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setEquals(user.getAgence());
                                criteria.setCodeAgenceIPRES(sf);
                            }
                        }
                    } else {
                        criteria.setCodeAgenceIPRES(sf);
                    }
                } else {
                    criteria.setCodeAgenceCSS(sf);
                }
            }
            HashMap<String, Object> pagination = new HashMap<>();
            HashMap<String, Object> result = new HashMap<>();
            Page<Declaration> page = declarationQueryService.findByCriteria(criteria, pageable);
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<Declaration> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
                el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
                el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        }
        return null;
    }

    @PostMapping("/last-declaration")
    public ResponseEntity<Object> getLastDeclarations(@RequestBody LastDeclarationDTO declarationDTO) {
        String numeroUnique = declarationDTO.getNumeroUnique();
        Instant debut = declarationDTO.getDebut();
        String type = declarationDTO.getType();
        List<Declaration> list = declarationRepository.findAllByNumeroUniqueAndTypeDeclarationAndDebutCotisationBeforeOrderByDebutCotisationDesc(numeroUnique, type, debut);
        if (!list.isEmpty())
            return ResponseEntity.ok(list.get(0));
        return ResponseEntity.ok(new Declaration());
    }

    @PostMapping("/declarations/check-periode")
    public ResponseEntity<HashMap<String, Object>> checkPeriode(@RequestBody LastDeclarationDTO declarationDTO) {
        HashMap<String, Object> result = new HashMap<>();
        String numeroUnique = declarationDTO.getNumeroUnique();
        Instant debut = declarationDTO.getDebut();
        Instant fin = declarationDTO.getFin();
        System.out.println("debut - " + debut.toString());
        System.out.println("fin - " + Objects.requireNonNull(fin).toString());
        List<Declaration> founds = declarationRepository.findAllByNumeroUnique(numeroUnique);
        founds = founds.stream().filter(d -> {
            Date deb = Date.from(d.getDebutCotisation());
            Date fn = Date.from(d.getFinCotisation());
            Date debdec = Date.from(debut);
            Date findec = Date.from(fin);
            boolean testd = isDateInBetweenIncludingEndPoints(deb,fn,debdec);
            boolean testf = isDateInBetweenIncludingEndPoints(deb,fn,findec);

            return testd || testf;
        }).collect(Collectors.toList());
        founds.forEach(declaration -> {
            System.out.println("el " + declaration.getId() + " - " + declaration.getDebutCotisation().toString() + " - " + declaration.getFinCotisation().toString());
        });

        if (founds.isEmpty()) {
            result.put("message", "Vous pouvez déclarer sur la période choisie!");
            result.put("code", "200");
            result.put("exist", false);
        } else {
            result.put("message", "Vous avez déjà une déclaration sur la période choisie!");
            result.put("code", "400");
            result.put("exist", true);
        }
        return ResponseEntity.ok().body(result);
    }


    public  boolean isDateInBetweenIncludingEndPoints(Date min, Date max, Date date){
        return !(date.before(min) || date.after(max));
    }

    @GetMapping("/declarations-agent-valides")
   // @PreAuthorize("hasRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasRole('" + AuthoritiesConstants.AGENT + "') or hasRole('" + AuthoritiesConstants.SUPER_ADMIN + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "') or hasRole('" + AuthoritiesConstants.CHEF_AGENCE + "')")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarationsAgenceValides(DeclarationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get all Declarations");
        StringFilter stringFilter = new StringFilter();
        stringFilter.setEquals("TRAITER");
        criteria.setStatus(stringFilter);
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                User user = userRepository.findByLogin(username);
                StringFilter sf = new StringFilter();
                LongFilter lf = new LongFilter();
               /* if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                    sf.setEquals("NO_AGENCE_FILTER");
                }*/
                if (!isEmpty(user.getAgence())) {
                    if (!isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setContains(user.getAgence());
                                criteria.setCodeAgenceCSS(sf);
                            }
                        }
                        if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setEquals(user.getAgence());
                                criteria.setCodeAgenceIPRES(sf);
                            }
                        }
                    } else {
                        criteria.setCodeAgenceIPRES(sf);
                    }
                } else {
                    criteria.setCodeAgenceCSS(sf);
                }
            }
            HashMap<String, Object> pagination = new HashMap<>();
            HashMap<String, Object> result = new HashMap<>();
            Page<Declaration> page = declarationQueryService.findByCriteria(criteria, pageable);
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<Declaration> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
                el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
                el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        }
        return null;
    }

    @GetMapping("/declarations-agent-soumises")
   // @PreAuthorize("hasRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasRole('" + AuthoritiesConstants.AGENT + "') or hasRole('" + AuthoritiesConstants.SUPER_ADMIN + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "') or hasRole('" + AuthoritiesConstants.CHEF_AGENCE + "')")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarationsAgenceSoumises(DeclarationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get all Declarations");
        StringFilter stringFilter = new StringFilter();
        stringFilter.setEquals("SOUMISE");
        criteria.setStatus(stringFilter);
        Optional<String> loggedusername = getCurrentUserLogin();
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                User user = userRepository.findByLogin(username);
                StringFilter sf = new StringFilter();
                LongFilter lf = new LongFilter();
               /* if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                    sf.setEquals("NO_AGENCE_FILTER");
                }*/
                if (!isEmpty(user.getAgence())) {
                    if (!isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setContains(user.getAgence());
                                criteria.setCodeAgenceCSS(sf);
                            }
                        }
                        if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setEquals(user.getAgence());
                                criteria.setCodeAgenceIPRES(sf);
                            }
                        }
                    } else {
                        criteria.setCodeAgenceIPRES(sf);
                    }
                } else {
                    criteria.setCodeAgenceCSS(sf);
                }
            }
            HashMap<String, Object> pagination = new HashMap<>();
            HashMap<String, Object> result = new HashMap<>();
            Page<Declaration> page = declarationQueryService.findByCriteria(criteria, pageable);
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<Declaration> list = page.getContent();
            list.forEach(el -> {
                el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
                el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
                el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
            });
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        }
        return null;
    }


    /**
     * {@code GET  /declarations/:id} : get the "id" declaration.
     *
     * @param id the id of the declaration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the declaration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/declarations/{id}")
    public ResponseEntity<Declaration> getDeclaration(@PathVariable Long id) {
        log.debug("REST request to get Declaration : {}", id);
        Optional<Declaration> declaration = declarationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(declaration);
    }

    /**
     * {@code DELETE  /declarations/:id} : delete the "id" declaration.
     *
     * @param id the id of the declaration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/declarations/{id}")
    public ResponseEntity<Void> deleteDeclaration(@PathVariable Long id) {
        log.debug("REST request to delete Declaration : {}", id);
        declarationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/callback")
    public CallBackDTO callBackForPSRM(@RequestBody CallBackDTO callBackDTO) {
        Optional<Declaration> declaration = declarationRepository.findByFileName(callBackDTO.getNOM_FICHIER());
        if (declaration.isPresent()) {
            Declaration found = declaration.get();
            found.setStatus(callBackDTO.getSTATUS());
            String details = "";
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                log.info("callBackFromPSRM >> " + objectMapper.writeValueAsString(callBackDTO));
                details = objectMapper.writeValueAsString(callBackDTO.getDETAILS());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            found.setDetails(details);
            declarationRepository.save(found);
        }
        return callBackDTO;
    }

    /**
     * {@code GET  /declarations/unique/:id} : get the "id" declaration.
     *
     * @param numeroUnique of the declaration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the declaration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/declarations/unique/{numeroUnique}")
    public ResponseEntity<List<Declaration>> getDeclarationByNumeroUnique(@PathVariable String numeroUnique) {
        log.debug("REST request to get Declarations: {}", numeroUnique);
        List<String> statuses = new ArrayList<>();
        statuses.add("TRAITER");
        statuses.add("INVALIDE");
        List<Declaration> declarations = declarationRepository.findAllByNumeroUniqueAndStatusIn(numeroUnique, statuses);
        return ResponseEntity.ok(declarations);
    }

    /**
     * {@code GET  /declarations/unique/:id} : get the "id" declaration.
     *
     * @param numeroUnique of the declaration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the declaration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/declarations/list/{numeroUnique}")
    public ResponseEntity<List<Declaration>> getDeclarationsListByNumeroUnique(@PathVariable String numeroUnique) {
        log.debug("REST request to get Declarations: {}", numeroUnique);
        List<Declaration> declarations = declarationRepository.findAllByNumeroUnique(numeroUnique);
        return ResponseEntity.ok(declarations);
    }

    /**
     * {@code GET  /declarations/owner/:id} : get the "id" declaration.
     *
     * @param ownerID of the declaration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the declaration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/declarations/owner/{ownerID}")
    public ResponseEntity<List<Declaration>> getNoneReadDeclarationsByOwner(@PathVariable Long ownerID) {
        log.debug("REST request to get Declarations: {}", ownerID);
        List<Declaration> declarations = declarationRepository.findAllByOwnerIDAndIsReadIsFalse(ownerID);
        return ResponseEntity.ok(declarations);
    }

    @GetMapping("/declarations/read/{id}")
    public ResponseEntity<Declaration> readMe(@PathVariable Long id) {
        log.debug("REST request to read declaration: {}", id);
        Optional<Declaration> declaration = declarationRepository.findById(id);
        if (declaration.isPresent()) {
            Declaration dec = declaration.get();
            dec.setIsRead(true);
            declarationRepository.save(dec);
            return ResponseUtil.wrapOrNotFound(Optional.of(dec));
        }
        return ResponseUtil.wrapOrNotFound(declaration);
    }

    @GetMapping("/declarations/salarie/search")
    public ApiResponse<Page<DeclarationRequetBruteDto>> searchDeclarations(
        @RequestParam TypeDePiece typePieceIdentite,
        @RequestParam String numPieceIdentite,
        @PageableDefault(size = 20) Pageable pageable) {

        Page<DeclarationRequetBruteDto> declarations = declarationService.findDeclarationsByIdentityDocument(
            typePieceIdentite,
            numPieceIdentite,
            pageable
        );

        return ApiResponse.success(declarations);
    }

/*    @DeleteMapping("/declarations/annuler/{id}")
    public ResponseEntity<String> checkAndDeleteDeclaration(@PathVariable Long id) {
        log.debug("REST request to check and delete Declaration : {}", id);

        Optional<Declaration> declarationOpt = declarationRepository.findById(id);
        if (!declarationOpt.isPresent()) {
            throw new BadRequestAlertException("Déclaration non trouvée", "declaration", "idnotfound");
        }

        // Vérifier le statut de synchronisation
        Declaration declaration = declarationOpt.get();

        List<FactureDNS> facturesDNS = factureDNSRepository.findAllByIdDeclaration(id);
        if (facturesDNS.isEmpty()) {
            throw new BadRequestAlertException("Aucune facture associée trouvée.","facture","idnotfound");
        }

        for (FactureDNS factureDNS : facturesDNS) {
            if ("PAYER".equalsIgnoreCase(factureDNS.getStatut())) {
                throw new BadRequestAlertException("Une ou plusieurs factures sont déjà payées. Aucune suppression effectuée.", "facture", "idnotfound");
            }
        }

        // Enregistrer les factures dans la table des factures supprimées
        List<FactureDNSSupprime> facturesSupprimees = new ArrayList<>();
        boolean allDeleted = true;

        for (FactureDNS factureDNS : facturesDNS) {
            if (StatutSynchronisation.SYNCHRONISE.equals(declaration.getStatutSynchronisation()) ||
                StatutSynchronisation.NON_SYNCHRONISE2.equals(declaration.getStatutSynchronisation())) {
                String numeroFactureStr = factureDNS.getNumeroFacture();
                // Appeler l'API externe
                String apiUrl = Constants.MS_SYNCHRO_URL+"/api/go-back/" + numeroFactureStr;
                RestTemplate restTemplate = new RestTemplate();

                try {
                    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.DELETE, null, String.class);
                    log.info("API response: {}", response.getBody());
                } catch (HttpClientErrorException e) {
                    log.error("Erreur lors de l'appel à l'API : {}", e.getStatusCode());
                    throw new BadRequestAlertException("Erreur lors de l'appel à l'API de synchronisation.", "declaration", "apierror");
                } catch (Exception e) {
                    log.error("Erreur inattendue : {}", e.getMessage());
                    throw new RuntimeException("Erreur inattendue lors de l'appel à l'API.", e);
                }
            }
            FactureDNSSupprime factureDNSSupprime = new FactureDNSSupprime(factureDNS, factureDNS.getId());
            factureDNSSupprimeRepository.save(factureDNSSupprime);

            // Supprimer la facture
            try {
                facturesSupprimees.add(factureDNSSupprime);
                factureDNSRepository.delete(factureDNS);
            } catch (Exception e) {
                allDeleted = false; // Si une facture n'a pas pu être supprimée
                log.error("Erreur lors de la suppression de la facture : {}", e.getMessage());
            }
        }

        // Si toutes les factures n'ont pas été supprimées, ne pas supprimer la déclaration
        if (!allDeleted) {
            throw new BadRequestAlertException("Toutes les factures n'ont pas pu être supprimées. Aucune suppression de la déclaration effectuée.", "declaration", "facturesnotdeleted");
        }

        // Enregistrer la déclaration dans la table des déclarations supprimées

        DeclarationSupprime declarationDNSSupprime = new DeclarationSupprime(declaration);
        declarationDNSSupprime.setId(declaration.getId());
        declarationDNSSupprime.setReponseBrute(declaration.getReponseBrute());
        declarationDNSSupprime.setRequeteBrute(declaration.getRequeteBrute());
        declarationSupprimeRepository.save(declarationDNSSupprime);
        // Enregistrer l'historique de la déclaration
        String detail = "Annulation de déclaration synchronisee- statut: " + declarationDNSSupprime.getStatutSynchronisation();
        journalDeclarationService.journaliserAction(
            declaration.getId(),
            detail,
            "ANNULATION_DECLARATION_SYCHRONISEE"
        );
        // Supprimer la déclaration d'origine
        declarationRepository.delete(declaration);

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }*/

    @DeleteMapping("/declarations/annuler/{id}")
    public ResponseEntity<String> checkAndDeleteDeclaration(@PathVariable Long id) {
        log.debug("REST request to check and delete Declaration : {}", id);

        Optional<Declaration> declarationOpt = declarationRepository.findById(id);
        if (!declarationOpt.isPresent()) {
            throw new BadRequestAlertException("Déclaration non trouvée", "declaration", "idnotfound");
        }

        // Vérifier le statut de synchronisation
        Declaration declaration = declarationOpt.get();

        List<FactureDNS> facturesDNS = factureDNSRepository.findAllByIdDeclaration(id);
        if (facturesDNS.isEmpty()) {
            throw new BadRequestAlertException("Aucune facture associée trouvée.","facture","idnotfound");
        }

        for (FactureDNS factureDNS : facturesDNS) {
            if ("PAYER".equalsIgnoreCase(factureDNS.getStatut())) {
                throw new BadRequestAlertException("Une ou plusieurs factures sont déjà payées. Aucune suppression effectuée.", "facture", "idnotfound");
            }
        }

        // Enregistrer les factures dans la table des factures supprimées
        List<FactureDNSSupprime> facturesSupprimees = new ArrayList<>();
        boolean allDeleted = true;

        if (StatutSynchronisation.SYNCHRONISE.equals(declaration.getStatutSynchronisation()) ||
            StatutSynchronisation.NON_SYNCHRONISE2.equals(declaration.getStatutSynchronisation())) {
            // Appeler l'API externe
            String apiUrl = Constants.MS_SYNCHRO_URL+"/api/go-back/declaration/" + id;
            RestTemplate restTemplate = new RestTemplate();

            try {
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.DELETE, null, String.class);
                log.info("API response: {}", response.getBody());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                String bodyMessage = rootNode.path("body").asText();

                int code = rootNode.path("code").asInt();
                if (code != 200) {
                    throw new BadRequestAlertException(bodyMessage, "declaration", "apierror");
                }

                // vérifier si le corps contient un message indiquant des factures déjà payées

                if (bodyMessage != null && bodyMessage.contains("déjà payées")) {
                    throw new BadRequestAlertException(
                        bodyMessage,
                        "facture",
                        "facturepayee"
                    );
                }

            } catch (HttpClientErrorException e) {
                log.error("Erreur lors de l'appel à l'API : {}", e.getStatusCode());
                throw new BadRequestAlertException("Erreur lors de l'appel à l'API de synchronisation.", "declaration", "apierror");
            } catch (Exception e) {
                log.error("Erreur inattendue : {}", e.getMessage());
                throw new RuntimeException("Erreur inattendue lors de l'appel à l'API.", e);
            }
        }

        for (FactureDNS factureDNS : facturesDNS) {
            FactureDNSSupprime factureDNSSupprime = new FactureDNSSupprime(factureDNS, factureDNS.getId());
            factureDNSSupprimeRepository.save(factureDNSSupprime);

            // Supprimer la facture
            try {
                facturesSupprimees.add(factureDNSSupprime);
                factureDNSRepository.delete(factureDNS);
            } catch (Exception e) {
                allDeleted = false; // Si une facture n'a pas pu être supprimée
                log.error("Erreur lors de la suppression de la facture : {}", e.getMessage());
            }
        }

        // Si toutes les factures n'ont pas été supprimées, ne pas supprimer la déclaration
        if (!allDeleted) {
            throw new BadRequestAlertException("Toutes les factures n'ont pas pu être supprimées. Aucune suppression de la déclaration effectuée.", "declaration", "facturesnotdeleted");
        }else{

            // Enregistrer la déclaration dans la table des déclarations supprimées
            try {
                DeclarationSupprime declarationDNSSupprime = new DeclarationSupprime(declaration);
                declarationDNSSupprime.setId(declaration.getId());
                declarationDNSSupprime.setReponseBrute(declaration.getReponseBrute());
                declarationDNSSupprime.setRequeteBrute(declaration.getRequeteBrute());

                declarationSupprimeRepository.save(declarationDNSSupprime);

                // Supprimer les journaux existants liés à cette déclaration
                declarationJournalJobRepository.deleteByDeclarationId(declaration.getId());
                log.debug("Journaux liés à la déclaration supprimés : {}", declaration.getId());

                // Journaliser l'action d'annulation
                String detail = "Annulation de déclaration synchronisée - statut: " + declarationDNSSupprime.getStatutSynchronisation();
                journalDeclarationService.journaliserAction(
                    declaration.getId(),
                    detail,
                    "ANNULATION_DECLARATION_SYCHRONISEE"
                );
                log.debug("Journalisation de l'annulation effectuée pour la déclaration : {}", declaration.getId());

                // Supprimer la déclaration d'origine
                declarationRepository.delete(declaration);
                log.info("Déclaration supprimée définitivement : {}", declaration.getId());

            } catch (Exception e) {
                log.error("Erreur lors de la sauvegarde de la déclaration supprimée : {}", e.getMessage(), e);
                throw new BadRequestAlertException("Échec de la sauvegarde de la déclaration annulée. Suppression annulée.", "declaration", "savesupprimefailed");
            }

        }
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/declarations/synchroniser/{id}")
    public ResponseEntity<Declaration> reload(@PathVariable Long id) {
        log.debug("REST request to read declaration: {}", id);
        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/send-single-declaration/" + id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

            if (!loggedusername.isPresent()) {
                loggedusername = Optional.of("plateforme");
            }
            String username = loggedusername.get();
            String userEmail = Optional.ofNullable(userRepository.findByLogin(username))
                .map(User::getEmail)
                .orElse(null);

            ResponseEntity<Map<String, Object>>  response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {});
            log.info("API response send-single-declaration: {}", response.getBody());

            Object codeObj = response.getBody().get("code");
            Integer code = 0;
            if (codeObj != null) {
                code = (Integer) codeObj;
            }
            if (code !=200) {
                throw new BadRequestAlertException("Erreur: déclaration non synchronisée dans PSRM.", "declaration", "apierror");
            } else {
                Map<String, Object> responseBody = response.getBody();
                // Deuxième appel API : correction d'attestation
                String correctionApiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/correction-attestation/" + id + "/" + id;
                ResponseEntity<Declaration> correctionResponse = restTemplate.exchange(
                    correctionApiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Declaration>() {}
                );
                log.info("API response correction attestationn: {}", response.getBody());
                if (correctionResponse.getStatusCode().is2xxSuccessful()) {
                    Declaration updatedDeclaration = correctionResponse.getBody();

                    // Journaliser
                    String detail = "Synchronisation de la déclaration - appel send-single-declaration et correction attestation";
                    journalDeclarationService.journaliserAction(
                        id,
                        detail,
                        "SYNCHRONISATION_DECLARATION"
                    );

                    declarationService.updateJournalJob(responseBody, userEmail);
                    return ResponseEntity.ok(updatedDeclaration);
                } else {
                    log.error("Erreur lors de l'appel à l'API de correction d'attestation : {}", correctionResponse.getStatusCode());
                    return ResponseEntity.status(correctionResponse.getStatusCode()).build();
                }
            }
        } catch (HttpClientErrorException e) {
            log.error("Erreur HTTP lors de l'appel à l'API : {}", e.getStatusCode());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            log.error("Erreur inattendue lors de l'appel à l'API : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @DeleteMapping("/annuler-synchronisation/{id}")
//    public ResponseEntity<String> checkAndDeleteSynchronisation(@PathVariable Long id) {
//        log.debug("REST request to check and delete Declaration : {}", id);
//
//        Optional<Declaration> declarationOpt = declarationRepository.findById(id);
//        if (!declarationOpt.isPresent()) {
//            throw new BadRequestAlertException("Déclaration non trouvée", "declaration", "idnotfound");
//        }
//        Declaration declaration = declarationOpt.get();
//        List<FactureDNS> facturesDNS = factureDNSRepository.findAllByIdDeclaration(id);
//        RestTemplate restTemplate = new RestTemplate();
//
//        for (FactureDNS factureDNS : facturesDNS) {
//                String numeroFactureStr = factureDNS.getNumeroFacture();
//                // Appeler l'API externe
//                String apiUrl = "http://192.168.125.113:30034/batchsync/api/go-back/" + numeroFactureStr;
//
//                try {
//                    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.DELETE, null, String.class);
//                    log.info("API response: {}", response.getBody());
//                    declaration.setStatutSynchronisation(null);
//                    // Enregistrer l'historique de la déclaration
//                    String detail = "Annulation de la synchronisation de la déclaration - appel go-back: ";
//                    journalDeclarationService.journaliserAction(
//                        id,
//                        detail,
//                        "ANNULATION_SYNCHRONISATION_DECLARATION"
//                    );
//                } catch (HttpClientErrorException e) {
//                    log.error("Erreur lors de l'appel à l'API : {}", e.getStatusCode());
//                    throw new BadRequestAlertException("Erreur lors de l'appel à l'API de synchronisation.", "declaration", "apierror");
//                } catch (Exception e) {
//                    log.error("Erreur inattendue : {}", e.getMessage());
//                    throw new RuntimeException("Erreur inattendue lors de l'appel à l'API.", e);
//                }
//        }
//        declarationRepository.save(declaration);
//
//        return ResponseEntity.noContent()
//            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
//            .build();
//    }

    @DeleteMapping("/annuler-synchronisation/{id}")
    public Object checkAndDeleteSynchronisation(@PathVariable Long id) {
        log.debug("REST request to check and delete Declaration : {}", id);
        Map<String,Object> reponse = new HashMap<>();

        Optional<Declaration> declarationOpt = declarationRepository.findById(id);
        if (!declarationOpt.isPresent()) {
            throw new BadRequestAlertException("Déclaration non trouvée", "declaration", "idnotfound");
        }
        Declaration declaration = declarationOpt.get();
        RestTemplate restTemplate = new RestTemplate();

        try {
           // String apiUrl = "http://192.168.125.113:30034/batchsync/api/go-back/declaration/" + id;
            String apiUrl = Constants.MS_SYNCHRO_URL+"/api/go-back/declaration/" + id;
            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.DELETE,
                null,
                Object.class);
            log.info("API response pour go-back: {}", response.getBody());

            Integer code = 200;
            Object responseBody = response.getBody();

            if (responseBody instanceof Map) {
                Map<String, Object> bodyMap = (Map<String, Object>) responseBody;
                Object codeObj = bodyMap.get("code");
                if (codeObj != null) {
                    code = (Integer) codeObj;
                }
            }
            if (code != 200) {
                return ResponseEntity.status(code).body(response.getBody());
            }
           // declaration.setStatutSynchronisation(null);
            // Enregistrer l'historique de la déclaration
            String detail = "Annulation de la synchronisation de la déclaration - appel go-back: ";
            JournalDeclaration jt = journalDeclarationService.journaliserAction(
                id,
                detail,
                "ANNULATION_SYNCHRONISATION_DECLARATION"
            );
            reponse.put("code",200);
            reponse.put("journalJob",jt);
        } catch (HttpClientErrorException e) {
            log.error("Erreur lors de l'appel à l'API : {}", e.getStatusCode());
            throw new BadRequestAlertException("Erreur lors de l'appel à l'API de synchronisation.", "declaration", "apierror");
        } catch (Exception e) {
            log.error("Erreur inattendue : {}", e.getMessage());
            throw new RuntimeException("Erreur inattendue lors de l'appel à l'API.", e);
        }
       // declarationRepository.save(declaration);

        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping("/declarations-supprimees")
    public ResponseEntity<HashMap<String, Object>> getAllDeclarationsSupprimee(DeclarationCriteria criteria, Pageable pageable, @RequestParam(value = "statut", defaultValue = "", required = false) String statut) {
        log.debug("REST request to get all Declarations");
        if (!isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatus(stringFilter);
        }
        HashMap<String, Object> result = new HashMap<>();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createAt"));
        Page<DeclarationSupprime> page = declarationSupprimeeQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<DeclarationSupprime> list = page.getContent();
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @PutMapping("/declarations-supprimees/restaurer/{id}")
    public ResponseEntity<String> restoreDeclaration(@PathVariable Long id) {
        log.debug("REST request to restore DeclarationSupprime : {}", id);

        // Récupérer la déclaration supprimée
        Optional<DeclarationSupprime> declarationSupprimeOpt = declarationSupprimeRepository.findById(id);
        if (!declarationSupprimeOpt.isPresent()) {
            throw new BadRequestAlertException("Déclaration supprimée non trouvée", "declarationSupprime", "idnotfound");
        }

        DeclarationSupprime declarationSupprime = declarationSupprimeOpt.get();

        // Créer une nouvelle déclaration et utiliser la méthode DeclarationRes
        DeclarationJournal declaration = new DeclarationJournal();
        // System.out.println("declarationSupprime.getId()"+declarationSupprime.getId());
        declaration.DeclarationRes(declarationSupprime);
        declaration.setId(declarationSupprime.getId());
        declaration.setReponseBrute(declarationSupprime.getReponseBrute());
        declaration.setRequeteBrute(declarationSupprime.getRequeteBrute());

        // Enregistrer la nouvelle déclaration
        declarationJournalRepository.save(declaration);
        // Enregistrer l'historique de la déclaration
        String detail = "Restauration de la déclaration supprimee : ";
        journalDeclarationService.journaliserAction(
            id,
            detail,
            "RESTAURATION_DECLARATION"
        );


        // Restaurer les factures associées
        List<FactureDNSSupprime> facturesSupprimees = factureDNSSupprimeRepository.findAllByIdDeclaration(id);
        for (FactureDNSSupprime factureSupprimee : facturesSupprimees) {
            FactureDNS factureDNS = new FactureDNS();
            factureDNS.FactureDNSRes(factureSupprimee);
            factureDNSRepository.save(factureDNS);
            factureDNSSupprimeRepository.delete(factureSupprimee);
        }

        // Supprimer la déclaration supprimée de la table des déclarations supprimées
        declarationSupprimeRepository.delete(declarationSupprime);

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/declarations/all-synchroniser")
    public ResponseEntity<Declaration> allSynchronise() {
        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/send-invoices";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Declaration> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Declaration.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Declaration updatedDeclaration = response.getBody();
                log.info("API response de all-synchroniser: {}", updatedDeclaration);
                return ResponseEntity.ok(updatedDeclaration);
            } else {
                log.error("Erreur lors de l'appel à l'API  all-synchroniser : {}", response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).build();
            }
        } catch (HttpClientErrorException e) {
            log.error("Erreur de client lors de l'appel à l'API : {}", e.getStatusCode());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            log.error("Erreur inattendue lors de l'appel à l'API : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/declarations/correction-attestation/{id}")
    public ResponseEntity<Declaration> correctionAttestation(@PathVariable Long id) {
        log.debug("REST request to read declaration: {}", id);
        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/correction-attestation/" + id+"/"+id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Declaration> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Declaration.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Declaration updatedDeclaration = response.getBody();
                log.info("API response de correction attestation: {}", updatedDeclaration);
                return ResponseEntity.ok(updatedDeclaration);
            } else {
                log.error("Erreur lors de l'appel à l'API correction attestation : {}", response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).build();
            }
        } catch (HttpClientErrorException e) {
            log.error("Erreur de client lors de l'appel à l'API : {}", e.getStatusCode());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            log.error("Erreur inattendue lors de l'appel à l'API : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/factures/portail-doublons/{numeroFacture}")
    public ResponseEntity<List<FactureDoublonDTO>> getFacturesDoublons(@PathVariable String numeroFacture) {
        Optional<FactureDNS> factureRefOpt = factureDNSRepository.findByNumeroFacture(numeroFacture);

        if (!factureRefOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        FactureDNS factureRef = factureRefOpt.get();

        List<FactureDNS> factures = factureDNSRepository.findByNumeroUniqueAndPeriodeChevauchante(
            factureRef.getNumeroUnique(),
            factureRef.getDebutPeriode(),
            factureRef.getFinPeriode()
        );

        // Exclure la facture qui a le même numéro que celle passée en paramètre
        List<FactureDNS> doublons = factures.stream()
            .filter(f -> !f.getNumeroFacture().equals(factureRef.getNumeroFacture()))
            .collect(Collectors.toList());

        if (doublons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FactureDoublonDTO> facturesDTO = doublons.stream().map(facture -> {
            FactureDoublonDTO dto = new FactureDoublonDTO();
            dto.setPaye("PAYER".equals(facture.getStatut()) ? "Y" : "N");
            dto.setNumeroUnique(facture.getNumeroUnique());
            dto.setNumeroFacture(facture.getNumeroFacture());
            dto.setMontantPaye(facture.getTotalAPayer().longValue());
            dto.setDebutPeriode(Date.from(facture.getDebutPeriode()));
            dto.setFinPeriode(Date.from(facture.getFinPeriode()));
            dto.setAgenceIpres(facture.getAgenceIpres());
            dto.setAgenceCss(facture.getAgenceCss());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(facturesDTO);
    }


    @GetMapping("/declarations/synchroniser-facture/{numeroFacture}")
    public ResponseEntity<String> synchroniseFacture(@PathVariable String numeroFacture) {
        log.debug("REST request to read numero facture: {}", numeroFacture);
        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/send-single-invoice/"+ numeroFacture;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);

            log.info("API response: {}", response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String bodyMessage = rootNode.path("body").asText();

            int code = rootNode.path("code").asInt();
            if (code != 200) {
                throw new BadRequestAlertException(bodyMessage, "facture", "apierror");
            }
        } catch (HttpClientErrorException e) {
            log.error("Erreur de client lors de l'appel à l'API send-single-invoice : {}", e.getStatusCode());
           // return ResponseEntity.status(e.getStatusCode()).build();
            throw new BadRequestAlertException("Erreur lors de l'appel à l'API go-back invoice.", "declaration", "apierror");
        } catch (Exception e) {
            log.error("Erreur inattendue lors de l'appel à l'API send-single-invoice : {}", e.getMessage());
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            throw new RuntimeException("Erreur inattendue lors de l'appel à l'API go-back invoice.", e);
        }

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, numeroFacture))
            .build();
    }

    @DeleteMapping("/declarations/supprimer/synchroniser-facture/{numeroFacture}")
    public ResponseEntity<String> supprimerSynchroFacture(@PathVariable String numeroFacture) {
        log.debug("REST request to read numero facture: {}", numeroFacture);
        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/go-back/invoice/"+ numeroFacture;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.DELETE, null, String.class);

            log.info("API response: {}", response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String bodyMessage = rootNode.path("body").asText();

            int code = rootNode.path("code").asInt();
            if (code != 200) {
                throw new BadRequestAlertException("Erreur: Facture non supprimée dans psrm", "facture", "apierror");
            }
        } catch (HttpClientErrorException e) {
            log.error("Erreur de client lors de l'appel à l'API go-back invoice : {}", e.getStatusCode());
          //  return ResponseEntity.status(e.getStatusCode()).build();
            throw new BadRequestAlertException("Erreur lors de l'appel à l'API go-back invoice.", "declaration", "apierror");
        } catch (Exception e) {
            log.error("Erreur inattendue lors de l'appel à l'API go-back invoice : {}", e.getMessage());
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            throw new RuntimeException("Erreur inattendue lors de l'appel à l'API go-back invoice.", e);
        }

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, numeroFacture))
            .build();
    }

//    @GetMapping("/declarations/check-doublon/{numeroFacture}")
//    public ResponseEntity<List<FactureDNS>> checkDoublonFacture(@PathVariable String numeroFacture) {
//        log.debug("REST request to read numero facture: {}", numeroFacture);
//        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/doublons/" + numeroFacture;
//        RestTemplate restTemplate = new RestTemplate();
//
//        try {
//            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);
//            log.info("API response: {}", response.getBody());
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode rootNode = objectMapper.readTree(response.getBody());
//
//            int code = rootNode.path("code").asInt();
//            if (code != 200) {
//                throw new BadRequestAlertException("Erreur dans la réponse de l'API check doublon.", "facture", "apierror");
//            }
//
//            JsonNode listeNode = rootNode.path("body").path("listeDuplication");
//            log.info("API listeNode: {}", listeNode);
//            List<FactureDNS> listeFactures = objectMapper.readValue(
//                listeNode.traverse(),
//                new TypeReference<List<FactureDNS>>() {}
//            );
//
//            return ResponseEntity.ok(listeFactures);
//
//        } catch (HttpClientErrorException e) {
//            log.error("Erreur de client lors de l'appel à l'API check doublon : {}", e.getStatusCode());
//            throw new BadRequestAlertException("Erreur lors de l'appel à l'API check doublon.", "facture", "apierror");
//        } catch (Exception e) {
//            log.error("Erreur inattendue lors de l'appel à l'API check doublon : {}", e.getMessage());
//            throw new RuntimeException("Erreur inattendue lors de l'appel à l'API check doublon.", e);
//        }
//    }


    @GetMapping("/factures/doublons/{numeroFacture}")
    public ResponseEntity<List<FactureDoublonDTO>> getFacturesCheckDoublons(@PathVariable String numeroFacture) {
        log.debug("Appel distant pour doublons facture {}", numeroFacture);

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/doublons/" + numeroFacture;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            int code = rootNode.path("code").asInt();
            if (code != 200) {
                throw new BadRequestAlertException("Erreur dans la réponse de l'API check doublon.", "facture", "apierror");
            }

            JsonNode listeNode = rootNode.path("body").path("listeDuplication");
            log.debug("liste listeNode {}", listeNode);

            if (!listeNode.isArray() || listeNode.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            // Conversion JSON → Liste d'objets Java
            List<FactureDoublonDTO> factures = objectMapper.readValue(
                listeNode.traverse(),
                new TypeReference<List<FactureDoublonDTO>>() {}
            );

            return factures.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(factures);

        } catch (HttpClientErrorException e) {
            log.error("Erreur HTTP lors de l'appel API : {}", e.getStatusCode());
            throw new BadRequestAlertException("Erreur HTTP de l'API check doublon.", "facture", "apierror");
        } catch (Exception e) {
            log.error("Erreur inattendue appel API doublon : {}", e.getMessage(), e);
            throw new RuntimeException("Erreur inattendue appel API doublon", e);
        }
    }

    @DeleteMapping("/desync-resync/{idDeclaration}")
    public Object deleteDeclarationDesyncResync(@PathVariable String idDeclaration) {
        log.info("Requête de suppression pour déclaration ID: {}", idDeclaration);

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/go-back/declaration-desync-resync/" + idDeclaration;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                org.springframework.http.HttpMethod.DELETE,
                null,
                Object.class
            );

            log.info("Réponse brute reçue: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception inattendue: {}", e.getMessage(), e);
            return ApiResponse.error500("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }


    @GetMapping("/vue-auxiliaire-employeur/{perId}/{dateDebut}/{dateFin}")
    public Object getVempFtDataByPath(
        @PathVariable("perId") String perId,
        @PathVariable("dateDebut") String dateDebut,
        @PathVariable("dateFin") String dateFin,
        @RequestParam(value = "groupByYear", defaultValue = "false") boolean groupByYear,
        @RequestParam(value = "institutions", required = false) List<String> institutions) {

        String baseUrl = Constants.MS_SYNCHRO_URL + "/api/synch/vue-auxiliaire-employeur/" + perId + "/" + dateDebut + "/" + dateFin;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUrl);

        if (groupByYear) {
            uriBuilder.queryParam("groupByYear", groupByYear);
        }

        if (institutions != null && !institutions.isEmpty()) {
            uriBuilder.queryParam("institutions", institutions.toArray());
        }

        String apiUrl = uriBuilder.build().toUriString();

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                Object.class
            );

            log.info("Réponse brute reçue: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception inattendue: {}", e.getMessage(), e);
            return ApiResponse.error500("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }


    @GetMapping("/factures/doublons-unifiee/{numeroFacture}")
    public ResponseEntity<?> getFacturesDoublonsUnifiees(@PathVariable String numeroFacture) {
        List<FactureDoublonDTO> results = new ArrayList<>();
        boolean hasLocalResults = false;
        boolean hasApiResults = false;

        try {
            ResponseEntity<List<FactureDoublonDTO>> localResponse = getFacturesDoublons(numeroFacture);
            List<FactureDoublonDTO> localResults = localResponse.getBody();

            if (localResults != null && !localResults.isEmpty()) {
                localResults.forEach(dto -> dto.setSource("portail"));
                results.addAll(localResults);
                hasLocalResults = true;
            }
        } catch (Exception e) {
            log.warn("Erreur lors de la récupération des doublons locaux : {}", e.getMessage());
        }

        try {
            ResponseEntity<List<FactureDoublonDTO>> responseApi = getFacturesCheckDoublons(numeroFacture);
            List<FactureDoublonDTO> apiResults = responseApi.getBody();

            if (apiResults != null && !apiResults.isEmpty()) {
                apiResults.forEach(dto -> dto.setSource("psrm"));

                // Supprimer les doublons exacts
                Set<String> existingKeys = results.stream()
                    .map(dto -> dto.getNumeroFacture() + "-" + dto.getNumeroUnique() + "-" + dto.getDebutPeriode() + "-" + dto.getFinPeriode())
                    .collect(Collectors.toSet());

                List<FactureDoublonDTO> filteredApiResults = apiResults.stream()
                    .filter(dto -> {
                        String key = dto.getNumeroFacture() + "-" + dto.getNumeroUnique() + "-" + dto.getDebutPeriode() + "-" + dto.getFinPeriode();
                        return !existingKeys.contains(key);
                    })
                    .collect(Collectors.toList());

                if (!filteredApiResults.isEmpty()) {
                    hasApiResults = true;
                    results.addAll(filteredApiResults);
                }
            }
        } catch (Exception e) {
            log.warn("Erreur lors de la récupération des doublons via API : {}", e.getMessage());
        }

        if (!hasLocalResults && !hasApiResults) {
           // return ResponseEntity.ok(Map.of("message", "Aucun doublon trouvé dans le portail et l'API."));
            throw new BadRequestAlertException("Aucun doublon trouvé dans le portail et psrm.", "facture", "nodoublon");
        }

        return ResponseEntity.ok(results);
    }

    @PostMapping("/vue-auxiliaire-employe")
    public Object searchCmDmtHistoriques(@RequestBody Object request) {

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/vue-auxiliaire-employe";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpEntity<Object> httpEntity = new HttpEntity<>(request);

            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                httpEntity,
                Object.class
            );

            log.info("Réponse brute reçue: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception inattendue: {}", e.getMessage(), e);
            return ApiResponse.error500("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    @GetMapping("/cotisations/{perId}")
    public Object getCotisationsByPerId(@PathVariable String perId) {

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/cotisations/"+perId;
        RestTemplate restTemplate = new RestTemplate();

        try {

            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                Object.class
            );

            log.info("Réponse brute reçue: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception inattendue: {}", e.getMessage(), e);
            return ApiResponse.error500("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    @GetMapping("/factures")
    public Object getFactures(
        @RequestParam(required = false) String perId ,
        @RequestParam(defaultValue = "N") String payed) {

        if (perId == null || perId.trim().isEmpty()) {
            return ApiResponse.error400(Collections.singletonMap("error", "Le paramètre 'perId' est obligatoire"));
        }
        payed = payed.toUpperCase();
        if (payed != null && !payed.equalsIgnoreCase("N") && !payed.equalsIgnoreCase("Y")
            && !payed.equalsIgnoreCase("BOTH") && !payed.equalsIgnoreCase("ALL")) {
            return ApiResponse.error400(Collections.singletonMap("error", "Le paramètre 'payed' doit être 'N', 'Y', 'BOTH' ou 'ALL'"));
        }

        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/factures?perId="+perId+"&payed="+payed;
        RestTemplate restTemplate = new RestTemplate();

        try {

            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                Object.class
            );

            return response.getBody();
        } catch (Exception e) {
            log.error("Exception inattendue: {}", e.getMessage(), e);
            return ApiResponse.error500("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    @GetMapping("/gateway/get-salaries/{numeroEmployeur}")
    public Object getSalariesByNumeroUnique(@PathVariable String numeroEmployeur) {
        String apiUrl = Constants.MS_SYNCHRO_URL + "/api/synch/get-salaries-by-numero-unique/"+numeroEmployeur;
        RestTemplate restTemplate = new RestTemplate();

        try {

            ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                Object.class
            );

            return response.getBody();
        } catch (Exception e) {
            log.error("Exception inattendue: {}", e.getMessage(), e);
            return ApiResponse.error500("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

}
