package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.domain.enumeration.TypeCompte;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.sms.SmsService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.web.rest.errors.EmailAlreadyUsedException;
import com.secusociale.portail.web.rest.errors.LoginAlreadyUsedException;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Salarie}.
 */
@RestController
@RequestMapping("/api")
public class SalarieResource {

    @Value("${constantes.nbPoints: 10000}")
    private int nbPoints;

    private final Logger log = LoggerFactory.getLogger(SalarieResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2Salarie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;


    private final SalarieRepository salarieRepository;
    private final SmsService smsService;
    private final SalarieQueryService salarieQueryService;
    private final GrappeMemberService grappeMemberService;

    private final UserService userService;

    private final SalarieService salarieService;
    private final UserRepository userRepository;
    private final AgenceRepository agenceRepository;

    private final MailService mailService;

    public SalarieResource(SmsService smsService,AgenceRepository agenceRepository, SalarieService salarieService, SalarieRepository salarieRepository, SalarieQueryService salarieQueryService, GrappeMemberService grappeMemberService, UserService userService, UserRepository userRepository, MailService mailService) {
        this.salarieService = salarieService;
        this.salarieRepository = salarieRepository;
        this.salarieQueryService = salarieQueryService;
        this.grappeMemberService = grappeMemberService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.agenceRepository = agenceRepository;
        this.smsService = smsService;
    }

    /**
     * {@code POST  /salaries} : Create a new salarie.
     *
     * @param salarieDTO the salarieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salarieDTO, or with status {@code 400 (Bad Request)} if the salarie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/salaries")
    public ResponseEntity<SalarieDTO> createSalarie(@Valid @RequestBody SalarieDTO salarieDTO) throws URISyntaxException {

        UserDTO userDTO = new UserDTO(salarieDTO);

        log.debug("REST request to save Salarie : {}", salarieDTO);
        if (salarieDTO.getId() != null) {
            throw new BadRequestAlertException("A new salarie cannot already have an ID", ENTITY_NAME, "idexists");
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            User newUser = userService.createUser(userDTO);
            mailService.sendCreationEmail(newUser);
            SalarieDTO result = salarieService.save(salarieDTO);
            return ResponseEntity.created(new URI("/api/salaries/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code PUT  /salaries} : Updates an existing salarie.
     *
     * @param salarieDTO the salarieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salarieDTO,
     * or with status {@code 400 (Bad Request)} if the salarieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salarieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/salaries")
    public ResponseEntity<SalarieDTO> updateSalarie(@Valid @RequestBody SalarieDTO salarieDTO) throws URISyntaxException {
        log.debug("REST request to update Salarie : {}", salarieDTO);
        if (salarieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalarieDTO result = salarieService.save(salarieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salarieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /salaries} : get all the salaries.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salaries in body.
     */
    @GetMapping("/salaries")
    public ResponseEntity<HashMap<String, Object>> getAllSalaries(
        @RequestParam(required = false, name = "dateQueries") String dateQueries,
        @RequestParam(required = false, name = "search") String search,
        SalarieCriteria criteria,
        Pageable pageable,
        @RequestParam(value = "statut", defaultValue = "", required = false) String statut)
        throws ParseException {
        log.debug("REST request to get Salaries by criteria: {}", criteria);

        if (!isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatut(stringFilter);
        }

        if (!isEmpty(search)) {
            StringFilter globalFilter = new StringFilter();
            globalFilter.setContains(search.toUpperCase(Locale.ROOT)); // Utilisation de contains pour le filtre global
            criteria.setGlobalSearch(globalFilter); // Assure-toi que tu as un champ approprié dans SalarieCriteria
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
                criteria.setCreatedAt(ifl);
            }
        }

        // Ajout du tri par createdAt
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<SalarieDTO> page = salarieQueryService.findByCriteria(criteria, sortedPageable);
        List<SalarieDTO> list = page.getContent();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
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
     * {@code GET  /salaries/count} : count all the salaries.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/salaries/count")
    public ResponseEntity<Long> countSalaries(SalarieCriteria criteria) {
        log.debug("REST request to count Salaries by criteria: {}", criteria);
        return ResponseEntity.ok().body(salarieQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /salaries/:id} : get the "id" salarie.
     *
     * @param id the id of the salarieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salarieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/{id}")
    public ResponseEntity<SalarieDTO> getSalarie(@PathVariable Long id) {
        log.debug("REST request to get Salarie : {}", id);
        Optional<SalarieDTO> salarieDTO = salarieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salarieDTO);
    }

    /**
     * {@code GET  /salaries/user/:userId} : get the "userId" salarie.
     *
     * @param userId the id of the attached user of the salarieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salarieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/user/{userId}")
    public ResponseEntity<SalarieDTO> getSalarieByUserId(@PathVariable Long userId) {
        log.debug("REST request to get Salarie by userId : {}", userId);
        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        return ResponseUtil.wrapOrNotFound(salarieDTO);
    }

    /**
     * {@code GET  /salaries/my-self} : get the "userId" salarie.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salarieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/my-self")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<SalarieDTO> getMySelf() {
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Long userId = userRepository.findByLogin(username).getId();
        log.debug("REST request to get Salarie by userId : {}", userId);
        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        return ResponseUtil.wrapOrNotFound(salarieDTO);
    }

    /**
     * {@code GET  /salaries/change-state/:salarieId} : get the "salarieId" salarie.
     *
     * @param salarieId the id of the attached user of the salarieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salarieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/change-state/{salarieId}")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")
    public ResponseEntity<HashMap<String, Object>> activateSalarie(@PathVariable Long salarieId, @PathParam("flag") String flag, @PathParam("numeroUnique") String numeroUnique) {
        HashMap<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(flag)) {
            throw Problem.builder().withDetail("Veuillez renseigner l'opération à effectuer ('activation' ou 'desactivation')").withTitle("Erreur").build();
        }
        List<String> flags = new ArrayList<>();
        flags.add("activation");
        flags.add("desactivation");
        if (!flags.contains(flag)) {
            throw Problem.builder().withDetail("L'opération à effectuer doit etre 'activation' ou 'desactivation'").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Long userId = userRepository.findByLogin(username).getId();
        Optional<SalarieDTO> salarieDTO = salarieService.findOne(salarieId);

        if (!salarieDTO.isPresent()) {
            throw Problem.builder().withDetail("Aucun salarie avec l'id " + salarieId + " n'a été trouvé").withTitle("Erreur").build();
        }
        SalarieDTO newSalDto = salarieDTO.get();
        String message = "";
        if (flag.equalsIgnoreCase("activation")) {
            newSalDto.setActive(true);
            newSalDto.setAgentId(userId);
            newSalDto.setStatut("ACTIVER");
            newSalDto.setLastUpdateAt(Instant.now());
            newSalDto.setNumeroUnique(numeroUnique);
            newSalDto.setActivatedAt(Instant.now());
            message = "Salarie activé avec succes";
        } else {
            newSalDto.setStatut("DESACTIVER");
            newSalDto.setActive(false);
            newSalDto.setAgentId(userId);
            newSalDto.setLastUpdateAt(Instant.now());
            message = "Salarie desactivé avec succes";
        }
        SalarieDTO rs = salarieService.save(newSalDto);

        result.put("code", "200");
        result.put("message", message);
        result.put("salarie", rs);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /salaries/dashboard} : get the connected salarie dashboard.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Map of data, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/dashboard")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<HashMap<String, Object>> myDash() {
        HashMap<String, Object> result = new HashMap<>();
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Long userId = userRepository.findByLogin(username).getId();
        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        if (!salarieDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        try {
            Long nbConjoints = grappeMemberService.countByType(Constants.GRAPPE_TYPE_CONJOINT, salarieDTO.get().getId());
            Long nbEnfants = grappeMemberService.countByType(Constants.GRAPPE_TYPE_ENFANT, salarieDTO.get().getId());
            Long nbAscendantMere = grappeMemberService.countByType(Constants.ASCENDANT_MERE, salarieDTO.get().getId());
            Long nbAscendantPere = grappeMemberService.countByType(Constants.ASCENDANT_PERE, salarieDTO.get().getId());
            result.put("nombre_conjoints", nbConjoints);
            result.put("nombre_enfants", nbEnfants);
            result.put("nombre_points", nbPoints);
            result.put("nbAscendantMere", nbAscendantMere);
            result.put("nbAscendantPere", nbAscendantPere);
            result.put("nombre_ascendants", nbAscendantMere+nbAscendantPere);
            result.put("code", 200);
        } catch (Exception e) {
            result.put("code", 400);
            result.put("message", "Impossible de recuperer les donnees");
        }

        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/salaries/agent-id/{agentId}")
    public ApiResponse<HashMap<String, Object>> getAgenceByAgentId(@PathVariable Long agentId) {
        HashMap<String, Object> result = new HashMap<>();

        Optional<User> userOptional = userRepository.findById(agentId);
        User user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }else{
            result.put("message","Aucun agent trouvé avec l'id : "+agentId);
            return ApiResponse.successResponse(ApiResponse.ResponseCode.INTERNAL_ERROR, result);
        }

        assert user != null;
        if (!TypeCompte.AGENT.equals(TypeCompte.valueOf(user.getTypeCompte()))) {
            result.put("message","L'utilisateur n'est pas un agent");
            return ApiResponse.successResponse(ApiResponse.ResponseCode.INTERNAL_ERROR, result);
        }

        String codeAgence = user.getAgence();
        if (codeAgence == null) {
            result.put("message","Aucune agence associée à cet agent");
            return ApiResponse.successResponse(ApiResponse.ResponseCode.INTERNAL_ERROR, result);
        }

        Agence agence = agenceRepository.findByCode(codeAgence);
        if(agence == null){
            result.put("message","Agence non trouvée avec le code : " + codeAgence);
            return ApiResponse.successResponse(ApiResponse.ResponseCode.INTERNAL_ERROR, result);
        }

        result.put("agence",agence);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, result);
    }

    @PutMapping("/salaries/id/{id}")
    public ResponseEntity<HashMap<String, Object>> updateSubmitStatus(
        @PathVariable Long id,
        @RequestParam(required = true) Boolean submitStatus) {

        log.debug("REST request to update Salarie submit status : {}", id);

        HashMap<String, Object> result = new HashMap<>();

        Optional<SalarieDTO> salarieOptional = salarieService.findOne(id);
        if (!salarieOptional.isPresent()) {
            throw Problem.builder()
                .withDetail("Aucun salarié avec l'id " + id + " n'a été trouvé")
                .withTitle("Erreur")
                .build();
        }

        SalarieDTO salarieDTO = salarieOptional.get();

        salarieDTO.setSubmitted(submitStatus);
        salarieDTO.setLastUpdateAt(Instant.now());
        if(submitStatus){
            grappeMemberService.modifyStatusBySalarieId(StatutGrappeMembre.SOUMIS,salarieDTO.getId());
        }

        SalarieDTO updatedSalarie = salarieService.save(salarieDTO);

        result.put("code", "200");
        result.put("message", submitStatus ? "Dossier soumis avec succès" : "Dossier retiré avec succès");
        result.put("salarie", updatedSalarie);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .body(result);
    }

    @PutMapping("/salaries/user-id/{userId}")
    public ResponseEntity<HashMap<String, Object>> updateSubmitStatusByUserId(
        @PathVariable Long userId,
        @RequestParam(required = true) Boolean submitStatus) {

        log.debug("REST request to update Salarie submit status for userId : {}", userId);

        HashMap<String, Object> result = new HashMap<>();

        Optional<SalarieDTO> salarieOptional = salarieService.findByUserId(userId);
        if (!salarieOptional.isPresent()) {
            throw Problem.builder()
                .withDetail("Aucun salarié avec le userId " + userId + " n'a été trouvé")
                .withTitle("Erreur")
                .build();
        }

        SalarieDTO salarieDTO = salarieOptional.get();

        salarieDTO.setSubmitted(submitStatus);
        salarieDTO.setLastUpdateAt(Instant.now());
        if(submitStatus){
            grappeMemberService.modifyStatusBySalarieId(StatutGrappeMembre.SOUMIS,salarieDTO.getId());
        }


        SalarieDTO updatedSalarie = salarieService.save(salarieDTO);

        result.put("code", "200");
        result.put("message", submitStatus ? "Dossier soumis avec succès" : "Dossier retiré avec succès");
        result.put("salarie", updatedSalarie);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salarieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code DELETE  /salaries/:id} : delete the "id" salarie.
     *
     * @param id the id of the salarieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/salaries/{id}")
    public ResponseEntity<Void> deleteSalarie(@PathVariable Long id) {
        salarieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //TODO à améliorer avec le démo mettre le code métier dans le SalarieService

    @PostMapping("/salaries/renvoieCodeOTP/{salarieId}")
    public ResponseEntity<HashMap<String, Object>> renvoieCodeOTP(@PathVariable Long salarieId) {
        HashMap<String, Object> result = new HashMap<>();

        Optional<SalarieDTO> salarieDTO = salarieService.findOne(salarieId);
        if (!salarieDTO.isPresent()) {
            throw new EntityNotFoundException("Aucun salarié trouvé avec l'id " + salarieId);
        }

        User user = userService.getUserWithAuthoritiesByLogin(salarieDTO.get().getUser().getLogin()).orElseThrow(() ->
            new EntityNotFoundException("Aucun utilisateur trouvé pour le salarié " + salarieId));

        String codeOTP = RandomStringUtils.randomNumeric(6);

        user.setOtp(codeOTP);
        user.setExpirationDate(LocalDateTime.now()
            .plusMinutes(Constants.TEMPS_EXPIRATION_CODE_OTP)
            .atZone(ZoneId.systemDefault())
            .toInstant());
        userService.save(user);

        try {
            String phoneNumberFormated = smsService.formatPhoneNumber(salarieDTO.get().getTelephone());
            String content = "Voici votre code d'activation : " + codeOTP + ". Merci de compléter le processus d'activation dans les "+ Constants.TEMPS_EXPIRATION_CODE_OTP +" prochaines minutes.";
            smsService.sendSms(content, phoneNumberFormated);
            result.put("code", "200");
            result.put("message", "Code OTP envoyé par SMS");
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi du code OTP par SMS", e);
            result.put("code", "500");
            result.put("message", "Erreur lors de l'envoi du code OTP par SMS");
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
