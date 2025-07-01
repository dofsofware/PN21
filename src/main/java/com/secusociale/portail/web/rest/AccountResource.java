package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.consultation.ConsultationService;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.dto.consultation.EmployeurConsultationDTO;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import com.secusociale.portail.service.dto.custom.RegisterUserDTO;
import com.secusociale.portail.service.dto.custom.ResendActivationEmailDTO;
import com.secusociale.portail.service.sms.SmsService;
import com.secusociale.portail.service.utils.UtilsService;
import com.secusociale.portail.web.rest.errors.EmailAlreadyUsedException;
import com.secusociale.portail.web.rest.errors.InvalidPasswordException;
import com.secusociale.portail.web.rest.errors.*;
import com.secusociale.portail.web.rest.vm.CheckVM;
import com.secusociale.portail.web.rest.vm.ForgetPasswordVM;
import com.secusociale.portail.web.rest.vm.KeyAndPasswordVM;
import com.secusociale.portail.web.rest.vm.ManagedUserVM;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;

    private final UserService userService;

    private final UserQueryService newUserQueryService;
    private final NouvelleImmatriculationService nouvelleImmatriculationService;
    private final ImmatriculationRecupereeService immatriculationRecupereeService;

    private final MailService mailService;
    private final SalarieService salarieService;
    private final ConsultationService consultationService;

    private final AgenceRepository agenceRepository;

    private final UtilsService utilsService;

    private final SmsService smsService;

    private final SalarieRepository salarieRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * {@code POST  /register} : register the user.
     *
     * @param userDTO the managed user View Model.
     * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, String>> registerAccount(@Valid @RequestBody RegisterUserDTO userDTO) {
        String activationFrontUrl = userDTO.getActivationFrontUrl();
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, String> error = new HashMap<>();
        if (StringUtils.isEmpty(activationFrontUrl)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'url d'activation");
            return ResponseEntity.ok().body(result);
        }
        if (activationFrontUrl.endsWith("/"))
            activationFrontUrl = StringUtils.substring(activationFrontUrl, 0, activationFrontUrl.length() - 1);

        if (userDTO.getLangKey() == null) {
            userDTO.setLangKey("fr");
        }
        try {
            String defaultPassword = RandomStringUtils.randomAlphabetic(8);
            User user = userService.registerUser(userDTO, defaultPassword);
            mailService.sendActivationEmail(user, defaultPassword, activationFrontUrl);
        } catch (Exception ex) {
            error.put("code", "400");
            error.put("message", ex.getMessage());
            return ResponseEntity.ok().body(error);
        }
        result.put("code", "200");
        result.put("message", "Veuillez consulter votre boite mail pour activer");
        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/resend-mail-activation")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResponseEntity<HashMap<String, String>> resendActivationMail(@Valid @RequestBody ResendActivationEmailDTO resendActivationEmailDTO) {

        HashMap<String, String> result = new HashMap<>();
        HashMap<String, String> error = new HashMap<>();

        if (StringUtils.isEmpty(resendActivationEmailDTO.getUserEmail())) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'email de l'utilisateur");
            return ResponseEntity.ok().body(result);
        }

        if (StringUtils.isEmpty(resendActivationEmailDTO.getActivationFrontUrl())) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'url d'activation");
            return ResponseEntity.ok().body(result);
        }

        Optional<UserDTO> foundUser = Optional.of(userRepository
                .findOneByEmailIgnoreCase(resendActivationEmailDTO.getUserEmail()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(UserDTO::new);

        if (!foundUser.isPresent()) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'url d'activation");
            return ResponseEntity.ok().body(result);
        }

        UserDTO userDTO = foundUser.get();

        String activationFrontUrl = resendActivationEmailDTO.getActivationFrontUrl();

        if (StringUtils.isEmpty(activationFrontUrl)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'url d'activation");
            return ResponseEntity.ok().body(result);
        }
        if (activationFrontUrl.endsWith("/"))
            activationFrontUrl = StringUtils.substring(activationFrontUrl, 0, activationFrontUrl.length() - 1);

        if (userDTO.getLangKey() == null) {
            userDTO.setLangKey("fr");
        }
        try {
            String defaultPassword = RandomStringUtils.randomAlphabetic(8);
            User user = userService.recoverRegisteredUser(userDTO, defaultPassword);
            mailService.sendActivationEmail(user, defaultPassword, activationFrontUrl);
        } catch (Exception ex) {
            error.put("code", "400");
            error.put("message", ex.getMessage());
            return ResponseEntity.ok().body(error);
        }
        result.put("code", "200");
        result.put("message", "Veuillez consulter votre boite mail pour activer");
        return ResponseEntity.ok().body(result);

    }


    /**
     * {@code POST  /register} : register the user.
     *
     * @param salarieDTO the managed user View Model.
     * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register-salarie")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, Object>> registerSalarieAccount(@Valid @RequestBody SalarieDTO salarieDTO) {
        UserDTO userDTO = new UserDTO(salarieDTO);
        String activationFrontUrl = salarieDTO.getActivationFrontUrl();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        log.debug("REST request to save Salarie : {}", salarieDTO);

        if (StringUtils.isEmpty(activationFrontUrl)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'url d'activation");
            return ResponseEntity.ok().body(result);
        }
        if (activationFrontUrl.endsWith("/"))
            activationFrontUrl = StringUtils.substring(activationFrontUrl, 0, activationFrontUrl.length() - 1);

        if (userDTO.getLangKey() == null) {
            userDTO.setLangKey("fr");
        }
        if (salarieDTO.getId() != null) {
            throw new BadRequestAlertException("A new salarie cannot already have an ID", "Salarie", "idexists");
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findFirstByEmailIgnoreCase(userDTO.getEmail()).isPresent() && userDTO.getEmail() != null) {
            throw new EmailAlreadyUsedException();
        } else if (salarieRepository.findFirstByNumeroUnique(salarieDTO.getNumeroUnique()).isPresent() && salarieDTO.getNumeroUnique() != null) {
            throw new NumeroUniqueAlreadyUsedException();
        } else if (salarieService.findByNumeroCni(salarieDTO.getNumeroCni()).isPresent()) {
            throw new NumeroCniAlreadyUsedException();
        } else {
            User user;
            SalarieDTO sdto;
            try {
                String defaultPassword = RandomStringUtils.randomAlphabetic(8);
                String otp = utilsService.generateOtp(6);
                userDTO.setOtp(otp);
                user = userService.registerUser(userDTO, defaultPassword);
                salarieDTO.setPrenom(salarieDTO.getFirstName());
                salarieDTO.setNom(salarieDTO.getLastName());
                salarieDTO.setTelephone(salarieDTO.getPhone());
                salarieDTO.setUserId(user.getId());
                salarieDTO.setCreatedAt(Instant.now());
                salarieDTO.setLastUpdateAt(Instant.now());
                salarieDTO.setStatut("DESACTIVER");
                sdto = salarieService.save(salarieDTO);
                String message = "Bonjour " + salarieDTO.getNom() + " " + salarieDTO.getPrenom() + "\n" + "Merci de vous être inscrit sur notre plateforme ! Pour finaliser la création de votre compte, veuillez entrer le code de vérification ci-dessous :"+"\n"+"code de vérification : " + otp;
                smsService.sendSms(message, salarieDTO.getPhone());
                //mailService.sendActivationEmail(user, defaultPassword, activationFrontUrl);
            } catch (Exception ex) {
                error.put("code", "400");
                error.put("message", ex.getMessage());
                return ResponseEntity.ok().body(error);
            }
            result.put("code", "200");
            result.put("userId", user.getId());
            result.put("salarieId", sdto.getId());
            result.put("message", "Veuillez consulter votre boite mail pour activer");
            return ResponseEntity.ok().body(result);
        }

    }

    @PostMapping("/verification")
    ResponseEntity<?> verifyOtp(@RequestBody VerifDTO verifDTO){
        if(!verifDTO.getConfirmPassword().equals(verifDTO.getPassword()))
            throw new BadRequestAlertException("les deux mots de passent doivent etre identiques","User","400");
        Optional<Salarie> salarie = Optional.ofNullable(salarieRepository.findById(verifDTO.getId()).orElseThrow(() -> new BadRequestAlertException("il existe aucun salarie avec l'id", "Salarie", "400")));
        Optional<User> user = userRepository.findById(salarie.get().getUserId());
        if (!user.get().getOtp().equals(verifDTO.getOtp()))
            throw new BadRequestAlertException("le code de vérification est incorect", "User","400");

        user.get().setPassword(passwordEncoder.encode(verifDTO.getPassword()));
        user.get().setOtp(null);
        user.get().setActivated(true);
        user.get().setHasPasswordUpdated(true);
        userRepository.save(user.get());
        Map<String,Object> reponse = new HashMap<>();
        reponse.put("code", HttpStatus.OK.value());
        reponse.put("message", "Veification réussie");
        return ResponseEntity.ok().body(reponse);
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param employeurConsultationDTO the managed user View Model.
     * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register-employeur")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, Object>> registerEmployeurAccount(@Valid @RequestBody EmployeurConsultationDTO employeurConsultationDTO) throws URISyntaxException {
        HashMap<String, Object> result = new HashMap<>();
        UserDTO userDTO = new UserDTO(employeurConsultationDTO);
        log.debug("REST request to save Salarie : {}", employeurConsultationDTO);
        if (employeurConsultationDTO.getId() != null) {
            throw new BadRequestAlertException("Un nouveau employeur ne peut pas avoir un ID", "Employeur", "idexists");
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            String defaultPassword = RandomStringUtils.randomAlphabetic(8);
            User newUser = userService.registerUser(userDTO, defaultPassword);
            mailService.sendActivationEmail(newUser, defaultPassword, employeurConsultationDTO.getActivationFrontUrl());
            employeurConsultationDTO.setUserId(newUser.getId());
            //EmployeurConsultation obj = consultationService.save(employeurConsultationDTO);
            result.put("code", "200");
            result.put("userId", newUser.getId());
            //result.put("employeurId", obj.getId());
            result.put("message", "Veuillez consulter votre boite mail pour activer");
            return ResponseEntity.created(new URI("/api/register-employeur"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, "Employeur", newUser.getId().toString()))
                .body(result);
        }

    }

    /**
     * {@code GET  /activate} : activate the registered user.
     *
     * @param key the activation key.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
     */
    @CrossOrigin("*")
    @GetMapping("/activate")
    public ResponseEntity<Map<String, String>> activateAccount(@RequestParam(value = "key") String key) {
        Optional<User> user = userService.activateRegistration(key);
        Map<String, String> result = new HashMap<>();
        if (!user.isPresent()) {
            result.put("code", "400");
            result.put("message", "Aucun utilisateur n'a été trouvé pour cette clé d'activation");
            return ResponseEntity.ok(result);
        }
        result.put("code", "200");
        result.put("message", "Votre compte a été activé avec succès");
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/check-authenticate")
    public User isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        String username = request.getRemoteUser();
        return username != null ? userRepository.findByLogin(username) : null;
    }

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public UserDTO getAccount() {
        return userService.getUserWithAuthorities()
            .map(UserDTO::new)
            .orElseThrow(() -> Problem.builder()
                .withStatus(Status.UNAUTHORIZED)
                .withDetail("Compte introuvable")
                .withTitle("Erreur")
                .build()
            );
    }

    /**
     * {@code POST  /account} : update the current user information.
     *
     * @param userDTO the current user information.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws RuntimeException          {@code 500 (Internal Server Error)} if the user login wasn't found.
     */
    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new RuntimeException("Current user login not found"));
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<User> user = userRepository.findOneByLogin(userLogin);
        if (!user.isPresent()) {
            throw new RuntimeException("Impossible de trouvé l'utilisateur");
        }
        userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
            userDTO.getLangKey(), userDTO.getImageUrl());
    }

    /**
     * {@code POST  /account/change-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
     */
    @PostMapping(path = "/account/change-password")
    public ResponseEntity<HashMap<String, String>> changePassword(@Valid @RequestBody PasswordChangeDTO passwordChangeDto, BindingResult bindingResult) {
        HashMap<String, String> result = new HashMap<>();
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            result.put("code", "400");
            result.put("message", "Veuillez verifier le nombre de caractères du mot de passe! (minimum: " + ManagedUserVM.PASSWORD_MIN_LENGTH + ", maximum: " + ManagedUserVM.PASSWORD_MAX_LENGTH + ")");
            return ResponseEntity.ok(result);
        }
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                result.put("code", "400");
                result.put("message", String.format("%s", objectError.getDefaultMessage()));
            });
            return ResponseEntity.ok(result);
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
        result.put("code", "200");
        result.put("message", "Mot de passe changé avec succès");
        return ResponseEntity.ok(result);
    }

    /**
     * {@code POST   /account/reset-password/init} : Send an email or SMS to reset the password of the user.
     *
     * @param forgetPasswordVM the forgetPasswordVM of the user.
     */
    @PostMapping(path = "/account/reset-password/init")
    public ResponseEntity<HashMap<String, String>> requestPasswordReset(@RequestBody ForgetPasswordVM forgetPasswordVM) {
        HashMap<String, String> result = new HashMap<>();

        if (StringUtils.isBlank(forgetPasswordVM.getEmail()) && StringUtils.isBlank(forgetPasswordVM.getPhoneNumber())) {
            result.put("code", "400");
            result.put("message", "Veuillez fournir soit un email soit un numéro de téléphone");
            return ResponseEntity.ok(result);
        }

        String Otp = utilsService.generateOtp(6);

        Optional<User> user = Optional.empty();
        if (StringUtils.isNotBlank(forgetPasswordVM.getEmail())) {
            user = userService.requestPasswordReset(forgetPasswordVM.getEmail(),Otp);
        }

        if (StringUtils.isNotBlank(forgetPasswordVM.getPhoneNumber()) && !user.isPresent()) {
            if(userService.countByPhoneIgnoreCase(forgetPasswordVM.getPhoneNumber())>1){
                result.put("code", "400");
                result.put("message", "Nous avons trouver plusieurs utilisateurs avec le même numéro de téléphone, merci de fournir votre Email.");
                return ResponseEntity.ok(result);
            }
            user = userService.findOneByPhone(forgetPasswordVM.getPhoneNumber());
        }

        if (forgetPasswordVM.getUrl().endsWith("/")) {
            forgetPasswordVM.setUrl(StringUtils.substring(forgetPasswordVM.getUrl(), 0, forgetPasswordVM.getUrl().length() - 1));
        }

        if (user.isPresent()) {

            if (StringUtils.isNotBlank(forgetPasswordVM.getEmail())) {
                mailService.sendPasswordResetMail(user.get(), forgetPasswordVM.getUrl());
                result.put("code", "200");
                result.put("message", "Un lien de réinitialisation vous a été envoyé");
            }

            if (StringUtils.isNotBlank(forgetPasswordVM.getPhoneNumber())) {
                try {
                    String phoneNumberFormatted = smsService.formatPhoneNumber(forgetPasswordVM.getPhoneNumber());
                    String content = "Votre code de réinitialisation est "+Otp+". Ce code est valable pendant "+Constants.TEMPS_EXPIRATION_CODE_OTP+" minutes. Si vous n'avez pas fait cette demande, veuillez ignorer ce message.";
                    smsService.sendSms(content, phoneNumberFormatted);
                    result.put("code", "200");
                    result.put("message", "Un lien de réinitialisation vous a été envoyé");
                } catch (Exception e) {
                    log.error("Erreur lors de l'envoi du code OTP par SMS", e);
                    result.put("code", "500");
                    result.put("message", "Erreur lors de l'envoi du code OTP par SMS");
                }
            }
            return ResponseEntity.ok(result);
        } else {
            log.warn("Password reset requested for non-existing mail '{}' or phone number '{}'", forgetPasswordVM.getEmail(), forgetPasswordVM.getPhoneNumber());
            result.put("code", "400");
            result.put("message", "Nous n'avons pas trouvé l'email ou le N° téléphone saisi dans notre base !");
            return ResponseEntity.ok(result);
        }
    }

    /**
     * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
     *
     * @param keyAndPassword the generated key and the new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws RuntimeException         {@code 500 (Internal Server Error)} if the password could not be reset.
     */
    @PostMapping(path = "/account/reset-password/finish")
    public ResponseEntity<HashMap<String, String>> finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        HashMap<String, String> result = new HashMap<>();
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            result.put("code", "400");
            result.put("message", "Veuillez verifier le nombre de caractères du mot de passe! (minimum: " + ManagedUserVM.PASSWORD_MIN_LENGTH + ", maximum: " + ManagedUserVM.PASSWORD_MAX_LENGTH + ")");
            return ResponseEntity.ok(result);
        }

        Optional<User> user =
            userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            result.put("code", "400");
            result.put("message", "Aucun utilisateur n'a été trouvé pour cette clé de reinitialisation!");
            return ResponseEntity.ok(result);
        }
        result.put("code", "200");
        result.put("message", "Mot de passe réinitialisé avec succès!");
        return ResponseEntity.ok(result);
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

    @PostMapping(path = "/account/check")
    public ResponseEntity<HashMap<String, Object>> checkUserExists(@RequestBody CheckVM checkVM) {
        boolean exists = false;
        String message = "";
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        if (StringUtils.isEmpty(checkVM.getType()) || StringUtils.isEmpty(checkVM.getValue())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", "400");
            map.put("message", "Veuillez renseigner le type et la valeur!");
            return ResponseEntity.ok(map);
        }
        ArrayList<String> types = new ArrayList<>();
        types.add("login");
        types.add("email");
        types.add("numerounique");
        types.add("numerocni");

        if (!types.contains(checkVM.getType().toLowerCase())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", "400");
            map.put("message", "Le type est soit 'login' ou 'email'");
            return ResponseEntity.ok(map);
        }
        switch (checkVM.getType().toLowerCase()) {
            case "login":
                if (userRepository.findOneByLogin(checkVM.getValue().toLowerCase()).isPresent()) {
                    message = "Le nom d'utilisateur: " + checkVM.getValue() + " est déjà utilisé";
                    exists = true;
                } else {
                    message = "Le nom d'utilisateur: " + checkVM.getValue() + " est disponible";
                }
                result.put("exists", exists);
                result.put("message", message);
                return ResponseEntity.ok(result);
            case "email":
                if (userRepository.findOneByEmailIgnoreCase(checkVM.getValue()).isPresent()) {
                    message = "L'email: " + checkVM.getValue() + " est déjà utilisé";
                    exists = true;
                } else {
                    message = "L'email: " + checkVM.getValue() + " est disponible";
                }
                result.put("exists", exists);
                result.put("message", message);
                return ResponseEntity.ok(result);
            case "numerounique":
                if (salarieService.findByNumeroUnique(checkVM.getValue()).isPresent()) {
                    message = "Le numéro unique: " + checkVM.getValue() + " est déjà utilisé";
                    exists = true;
                } else {
                    message = "Le numéro unique: " + checkVM.getValue() + " est disponible";
                }
                result.put("exists", exists);
                result.put("message", message);
                return ResponseEntity.ok(result);
            case "numerocni":
                if (salarieService.findByNumeroCni(checkVM.getValue()).isPresent()) {
                    message = "Le NIN: " + checkVM.getValue() + " est déjà utilisé";
                    exists = true;
                } else {
                    message = "Le NIN: " + checkVM.getValue() + " est disponible";
                }
                result.put("exists", exists);
                result.put("message", message);
                return ResponseEntity.ok(result);
            default:
                break;
        }
        return ResponseEntity.ok(result);

    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param userDTO the managed user View Model.
     * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register-agent")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> registerAgentAccount(@Valid @RequestBody RegisterUserDTO userDTO) throws JsonProcessingException {
        String activationFrontUrl = userDTO.getActivationFrontUrl();
        String institution = userDTO.getInstitution();
        String agence = userDTO.getAgence();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        if (userDTO.getProfil() == null) {
            error.put("code", "400");
            error.put("error", "Le profil de l'agent est obligatoire");
            return ResponseEntity.badRequest().body(error);
        }

        switch (userDTO.getProfil()) {
            case Constants.GESTIONNAIRE_EMPLOYEUR:
                userDTO.setTypeCompte(Constants.GESTIONNAIRE_EMPLOYEUR);
                break;
            case Constants.GESTIONNAIRE_SALARIE:
                userDTO.setTypeCompte(Constants.GESTIONNAIRE_SALARIE);
                break;
            case Constants.CHEF_AGENCE:
                userDTO.setTypeCompte(Constants.CHEF_AGENCE);
                break;
            default:
                error.put("code", "400");
                error.put("error", "Le profil doit être compris dans ['GESTIONNAIRE_EMPLOYEUR','GESTIONNAIRE_SALARIE','CHEF_AGENCE']");
                return ResponseEntity.badRequest().body(error);
        }

        if (StringUtils.isEmpty(activationFrontUrl)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'url d'activation");
            return ResponseEntity.ok().body(result);
        }
        if (StringUtils.isEmpty(institution) && StringUtils.isEmpty(agence)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'institution et l'agence de l'Agent!");
            return ResponseEntity.ok().body(result);
        }
        if (StringUtils.isEmpty(institution)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'institution de l'Agent!");
            return ResponseEntity.ok().body(result);
        }
        if (StringUtils.isEmpty(agence)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'agence de l'Agent!");
            return ResponseEntity.ok().body(result);
        }
        if (activationFrontUrl.endsWith("/"))
            activationFrontUrl = StringUtils.substring(activationFrontUrl, 0, activationFrontUrl.length() - 1);

        if (userDTO.getLangKey() == null) {
            userDTO.setLangKey("fr");
        }
        try {
            String defaultPassword = RandomStringUtils.randomAlphabetic(8);
            AgentDTO agentDTO = userService.registerAgent(userDTO, defaultPassword,userDTO.getProfil());
            User user = agentDTO.getUser();
            mailService.sendActivationEmail(user, defaultPassword, activationFrontUrl);
            result.put("code", "200");
            result.put("message", "Veuillez consulter votre boite mail pour activer");
            result.put("data", agentDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            error.put("code", "400");
            error.put("message", ex.getMessage());
            return ResponseEntity.ok().body(error);
        }

        return ResponseEntity.ok().body(result);

    }


    @GetMapping("/list-agent")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> listAgentAccounts(UserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Agent Accounts by pageable: {}", pageable);
        HashMap<String, Object> error = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        try {
            StringFilter typeFilter = new StringFilter();

            // Filtrage par type de compte
            List<String> types = Arrays.asList("AGENT", "GESTIONNAIRE_SALARIE", "GESTIONNAIRE_EMPLOYEUR", "CHEF_AGENCE");
            typeFilter.setIn(types);
            criteria.setTypeCompte(typeFilter);

            if (loggedusername.isPresent()) {
                String username = loggedusername.get();
                User user = userRepository.findByLogin(username);

                StringFilter agencyFilter = new StringFilter();
                if (!isEmpty(user.getAgence())) {
                    if (!isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            agencyFilter.setContains(user.getAgence());
                            criteria.setAgence(agencyFilter);
                        } else if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                            agencyFilter.setEquals(user.getAgence());
                            criteria.setAgence(agencyFilter);
                        }
                    } else {
                        criteria.setAgence(agencyFilter);
                    }
                } else {
                    criteria.setAgence(agencyFilter);
                }
            }
            // Récupération des agents
            Page<AgentDTO> page = newUserQueryService.findByCriteria(criteria, pageable).map(userDTO -> {
                return userRepository.findById(userDTO.getId()).orElse(null);
            }).map(AgentDTO::new);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<AgentDTO> ads = page.getContent();

            // Remplissage des informations liées aux agents
            ads.forEach(agentDTO -> {
                Agence ag = agenceRepository.findByCode(agentDTO.getAgence());
                agentDTO.setAgenceObject(ag);
            });
            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            result.put("code", "200");
            result.put("list", ads);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        } catch (Exception e) {
            error.put("code", "400");
            error.put("error", e.getMessage());
            return ResponseEntity.ok().body(error);
        }
    }

    @GetMapping("/list-employeurs")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> listEmployeursAccounts(UserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OldImmatriculations by pageable: {}", pageable);
        HashMap<String, Object> error = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        try {
            if (criteria.getActivated() == null) {
                criteria.setActivated(new BooleanFilter());
            }
            String activatedParam = ServletUriComponentsBuilder.fromCurrentRequest().build().getQueryParams().getFirst("activated.contains");
            if (activatedParam != null) {
                boolean activatedValue = Boolean.parseBoolean(activatedParam);
                criteria.getActivated().setEquals(activatedValue);
            }

            Page<UserDTO> page = newUserQueryService.findByCriteria(criteria, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);

            List<UserDTO> ads = page.getContent();
            ads.forEach(agentDTO -> {
                Integer nbLocals = nouvelleImmatriculationService.countLocalImmatsByUser(agentDTO.getLogin());
                Integer nbOlds = immatriculationRecupereeService.countOldImmatsByUser(agentDTO.getLogin());
                agentDTO.setNbLocalImmat(nbLocals);
                agentDTO.setNbOldImmat(nbOlds);
                agentDTO.setNbImmats(nbOlds + nbLocals);
            });

            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            result.put("code", "200");
            result.put("list", ads);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        } catch (Exception e) {
            error.put("code", "400");
            error.put("error", e.getMessage());
            return ResponseEntity.ok().body(error);
        }
    }

    @GetMapping("/list-salaries")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\")")
    public ResponseEntity<HashMap<String, Object>> listSalariesAccounts(UserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get listSalariesAccounts by pageable: {}", pageable);
        HashMap<String, Object> error = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        try {
//            Page<UserDTO> page = userService.getAllSalaries(pageable);
            StringFilter typeFilter = new StringFilter();
            StringFilter roleFilter = new StringFilter();
            typeFilter.setEquals(Constants.SALARIE);
            roleFilter.setEquals(AuthoritiesConstants.SALARIE);
            criteria.setTypeCompte(typeFilter);

            Page<UserDTO> page = newUserQueryService.findByCriteria(criteria, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);

            List<UserDTO> ads = page.getContent();

            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            result.put("code", "200");
            result.put("list", ads);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        } catch (Exception e) {
            error.put("code", "400");
            error.put("error", e.getMessage());
            return ResponseEntity.ok().body(error);
        }
    }

    @PutMapping("/update-agent/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> updateAgent(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setTypeCompte(Constants.TYPE_AGENT);
        ArrayList<String> roles = new ArrayList<>();
        roles.add(AuthoritiesConstants.AGENT);
        userDTO.setAuthorities(new HashSet<>(roles));
        String institution = userDTO.getInstitution();
        String agence = userDTO.getAgence();
        //set id
        userDTO.setId(id);
        //response
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        if (StringUtils.isEmpty(institution) || StringUtils.isEmpty(agence)) {
            result.put("code", "400");
            result.put("error", "Veuillez renseigner l'institution et l'agence de l'Agent!");
            return ResponseEntity.ok().body(result);
        }
        if (userDTO.getLangKey() == null) {
            userDTO.setLangKey("fr");
        }
        try {
            Optional<AgentDTO> user = userService.updateAgent(userDTO);
            AgentDTO rep = user.map(agentDTO -> {
                agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                return agentDTO;
            }).orElse(null);
            if (rep != null) {
                result.put("code", "200");
                result.put("message", "Agent modifié avec succès");
                result.put("data", rep);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            error.put("code", "400");
            error.put("message", ex.getMessage());
            return ResponseEntity.ok().body(error);
        }

        return ResponseEntity.ok().body(result);

    }

    @GetMapping(value = "/activate-agent/{id}", params = {"mode"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> activateAgent(@PathVariable Long id, @RequestParam(value = "mode", defaultValue = "desactivation", required = true) String mode) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        try {
            Optional<AgentDTO> user = userService.activateAgent(id, mode);
            AgentDTO rep = user.map(agentDTO -> {
                agentDTO.setAgenceObject(agenceRepository.findByCode(agentDTO.getAgence()));
                return agentDTO;
            }).orElse(null);
            if (rep != null) {
                if (mode.equalsIgnoreCase("activation")) {
                    result.put("code", "200");
                    result.put("message", "Agent activé avec succès");
                    result.put("data", rep);
                } else if (mode.equalsIgnoreCase("desactivation")) {
                    result.put("code", "200");
                    result.put("message", "Agent désactivé avec succès");
                    result.put("data", rep);
                }
            }
        } catch (Exception ex) {
            error.put("code", "400");
            error.put("message", ex.getMessage());
            return ResponseEntity.ok().body(error);
        }

        return ResponseEntity.ok().body(result);

    }

}
