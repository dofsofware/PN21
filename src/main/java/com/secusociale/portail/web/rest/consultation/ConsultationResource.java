package com.secusociale.portail.web.rest.consultation;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.DeclarationRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.consultation.ConsultationQueryService;
import com.secusociale.portail.service.consultation.ConsultationService;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.dto.consultation.EmployeurConsultationCriteria;
import com.secusociale.portail.service.dto.consultation.EmployeurConsultationDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.web.rest.errors.EmailAlreadyUsedException;
import com.secusociale.portail.web.rest.errors.LoginAlreadyUsedException;
import io.github.jhipster.web.util.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;

@RestController
@RequestMapping("/api/consultation")
@RequiredArgsConstructor
@Transactional
public class ConsultationResource {
    private final DeclarationRepository declarationRepository;
    private final ConsultationService consultationService;
    private final ConsultationQueryService consultationQueryService;
    private final UserService userService;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final AgenceRepository agenceRepository;
    private final Logger log = LoggerFactory.getLogger(ConsultationResource.class);

    private static final String ENTITY_NAME = "EmployeurConsultation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * {@code POST  /creer-employeur} : Create a new Employeur.
     *
     * @param employeurConsultationDTO the employeurConsultationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeurConsultationDTO, or with status {@code 400 (Bad Request)} if the employeurConsultation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creer-employeur")
    public ResponseEntity<Object> createEmployeur(@Valid @RequestBody EmployeurConsultationDTO employeurConsultationDTO) throws URISyntaxException {

        UserDTO userDTO = new UserDTO(employeurConsultationDTO);

        log.debug("REST request to save Salarie : {}", employeurConsultationDTO);
        if (employeurConsultationDTO.getId() != null) {
            throw new BadRequestAlertException("Un nouveau employeur ne peut pas avoir un ID", ENTITY_NAME, "idexists");
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            String defaultPassword = RandomStringUtils.randomAlphabetic(8);
            User newUser = userService.registerUser(userDTO, defaultPassword);
            mailService.sendActivationEmail(newUser, defaultPassword, employeurConsultationDTO.getActivationFrontUrl());
            employeurConsultationDTO.setUserId(newUser.getId());
            EmployeurConsultation result = consultationService.save(employeurConsultationDTO);
            return ResponseEntity.created(new URI("/api/consultation/create-employeur" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
        }
    }

    @GetMapping("/mes-declarations")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.CONSULTATION + "\")")
    public ResponseEntity<HashMap<String, Object>> getMyDeclarations() {
        log.debug("REST request to get all Declarations");

        Optional<String> loggedusername = getCurrentUserLogin();
        if (!loggedusername.isPresent()) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }

        if (!isCurrentUserInRole(AuthoritiesConstants.CONSULTATION)) {
            throw Problem.builder().withStatus(Status.FORBIDDEN).withDetail("Vous n'avez pas les droits pour acceder a cette ressource").withTitle("Erreur").build();
        }

        User user = userRepository.findByLogin(loggedusername.get());

        HashMap<String, Object> result = new HashMap<>();
        List<Declaration> list = declarationRepository.findAllByNumeroUnique(consultationService.findByUserId(user.getId()).map(EmployeurConsultation::getNumeroUnique).orElse(null));
        list.forEach(el -> {
            el.setAgenceCSS(agenceRepository.findByCode(el.getCodeAgenceCSS()));
            el.setAgenceIPRES(agenceRepository.findByCode(el.getCodeAgenceIPRES()));
            el.setUser(userRepository.findById(el.getOwnerID()).map(UserDTO::new).orElse(null));
        });
        result.put("code", "200");
        result.put("list", list);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code POST  /activer-employeur} : active a new Employeur.
     *
     * @param id the id of the employeur to activate.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeurConsultationDTO, or with status {@code 400 (Bad Request)} if the employeurConsultation has already an ID.
     */

    @GetMapping(value = "/activate-employeur/{id}", params = {"mode"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\",\"" + AuthoritiesConstants.AGENT  + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR +  "\")")
    public ResponseEntity<HashMap<String, Object>> activateEmployeur(@PathVariable Long id, @RequestParam(value = "mode", defaultValue = "desactivation", required = true) String mode) {
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        if (!loggedusername.isPresent()) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        User user = userRepository.findByLogin(loggedusername.get());
        EmployeurConsultation rep = consultationService.activate(id, mode, user.getId());

        if (mode.equalsIgnoreCase("activation")) {
            result.put("code", "200");
            result.put("message", "Employeur activé avec succès");
            result.put("data", rep);
        } else if (mode.equalsIgnoreCase("desactivation")) {
            result.put("code", "200");
            result.put("message", "Employeur désactivé avec succès");
            result.put("data", rep);
        }

        return ResponseEntity.ok().body(result);

    }


    @GetMapping("/employeurs")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL  + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR +  "\")")
    public ResponseEntity<HashMap<String, Object>> getEmployeurs(EmployeurConsultationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get all Employeurs");

        Optional<String> loggedusername = getCurrentUserLogin();
        if (!loggedusername.isPresent()) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }

        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
        Page<EmployeurConsultation> page = consultationQueryService.findByCriteria(criteria, pageable);
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get-infos")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.CONSULTATION + "\")")
    public ResponseEntity<HashMap<String, Object>> getConsultationInfos() {
        Optional<String> loggedusername = getCurrentUserLogin();
        if (!loggedusername.isPresent()) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }

        if (!isCurrentUserInRole(AuthoritiesConstants.CONSULTATION)) {
            throw Problem.builder().withStatus(Status.FORBIDDEN).withDetail("Vous n'avez pas les droits pour acceder a cette ressource").withTitle("Erreur").build();
        }

        User user = userRepository.findByLogin(loggedusername.get());
        if (user == null) {
            throw Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail("Utilisateur inconnu").withTitle("Erreur").build();
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "404");
        result.put("data", null);
        consultationService.findByUserId(user.getId()).ifPresent(employeurConsultation -> {
            result.put("data", employeurConsultation);
            result.put("code", "200");
        });
        return ResponseEntity.ok(result);
    }

}
