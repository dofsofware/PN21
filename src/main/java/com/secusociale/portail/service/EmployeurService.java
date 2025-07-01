package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Employeur;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.EmployeurRepository;
import com.secusociale.portail.service.dto.AgenceEmployeurLinkDTO;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.OTPCode;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.service.sms.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Service Implementation for managing {@link Employeur}.
 */
@Service
@Transactional
public class EmployeurService {

    private final Logger log = LoggerFactory.getLogger(EmployeurService.class);

    private final EmployeurRepository employeurRepository;
    private final UserService userService;
    private final MailService mailService;
    private final SmsService smsService;
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;
    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;
    private final AgenceRepository agenceRepository;

    public EmployeurService(EmployeurRepository employeurRepository,
                            UserService userService, MailService mailService,
                            ImmatriculationRecupereeRepository immatriculationRecupereeRepository,
                            NouvelleImmatriculationRepository nouvelleImmatriculationRepository,
                            SmsService smsService, AgenceRepository agenceRepository
    ) {
        this.employeurRepository = employeurRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.smsService = smsService;
        this.immatriculationRecupereeRepository = immatriculationRecupereeRepository;
        this.nouvelleImmatriculationRepository = nouvelleImmatriculationRepository;
        this.agenceRepository = agenceRepository;
    }

    /**
     * Save a employeur.
     *
     * @param employeur the entity to save.
     * @return the persisted entity.
     */
    public Employeur save(Employeur employeur) {
        log.debug("Request to save Employeur : {}", employeur);
        return employeurRepository.save(employeur);
    }

    /**
     * Get all the employeurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Employeur> findAll(Pageable pageable) {
        log.debug("Request to get all Employeurs");
        return employeurRepository.findAll(pageable);
    }

    /**
     * Get one employeur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Employeur> findOne(Long id) {
        log.debug("Request to get Employeur : {}", id);
        return employeurRepository.findById(id);
    }


    /**
     * Get all employeurs by User.
     *
     * @return the list of employeurs by currentUser.
     */
    @Transactional(readOnly = true)
    public List<Employeur> findEmployeurByUser() {
        log.debug("Request to get Employeur : {}");
        // String username = SecurityUtils.getCurrentUserLogin().get();
        return employeurRepository.findByUserIsCurrentUser();
    }

    @Transactional(readOnly = true)
    public Optional<Employeur> findEmployeurByTypeIdentifiantAndNumeroIdentifiant(String typeIdentifiant, String numeroIdentifiant) {
        log.debug("Request to get Employeur : {}, {}", typeIdentifiant, numeroIdentifiant);
        // String username = SecurityUtils.getCurrentUserLogin().get();
        return employeurRepository.findByTypeOfIdentityAndNumeroIdentifiant(typeIdentifiant, numeroIdentifiant);
    }

    public Optional<Employeur> checkEmployeur(String typeIdentifiant, String numeroIdentifiant) {
        Optional<Employeur> mayExist = findEmployeurByTypeIdentifiantAndNumeroIdentifiant(typeIdentifiant, numeroIdentifiant);
        return mayExist;
    }


    /**
     * Delete the employeur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Employeur : {}", id);
        employeurRepository.deleteById(id);
    }

    public ResponseEntity<Map<String,Object >> checkRequiredParamsCheckMandataire(String phoneNumber, String userName) {
        String TypeCompte = Constants.CABINET;
        if (phoneNumber == null && userName == null ) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Un numéro de téléphone ou un userName est requis.");
            Response.put("status", "ERROR");
            return ResponseEntity.ok().body(Response);
        }

        boolean userExists = userService.existsByUserNameOrTelephoneAndUserTypeCompte(userName, phoneNumber, TypeCompte);

        if (!userExists) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Aucun utilisateur de type CABINET trouvé avec le User Name : "+userName);
            Response.put("status", "ERROR");
            Response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response);
        }

        return null;
    }

    public ResponseEntity<?> checkRequiredParamsVerifieCodeOTP(String codeOTP,String userNameEmployee, String userNameManager) {

        if (codeOTP == null || codeOTP.isEmpty()) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Le code OTP est requis.");
            Response.put("status", "ERROR");
            Response.put("code", "400");
            return ResponseEntity.ok().body(Response);
        }
        if (userNameEmployee == null || userNameEmployee.isEmpty()) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Le userName employee est requis.");
            Response.put("status", "ERROR");
            Response.put("code", "400");
            return ResponseEntity.ok().body(Response);
        }
        if (userNameManager == null || userNameManager.isEmpty()) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Le userName manager est requis.");
            Response.put("status", "ERROR");
            Response.put("code", "400");
            return ResponseEntity.ok().body(Response);
        }

        if (codeOTP.length() != 6 || !codeOTP.matches("\\d+")) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Le code est invalide : doit contenir exactement 6 chiffres.");
            Response.put("status", "ERROR");
            Response.put("code", "400");
            return ResponseEntity.badRequest().body(Response);
        }

        return null;
    }

    public void saveOTP(User employeur, String otp) {

        OTPCode otpCode = new OTPCode();
        otpCode.setCode(otp);
        otpCode.setExpirationDate(LocalDateTime.now()
            .plusMinutes(Constants.TEMPS_EXPIRATION_CODE_OTP)
            .atZone(ZoneId.systemDefault())
            .toInstant());
        employeur.getOtpCodes().add(otpCode);
        userService.save(employeur);
    }


    public boolean verifierCodeOTP(Long userManagerId , String codeOTP) {
        boolean isValid = userService.existsByIdAndCodeOTP(userManagerId, codeOTP);
        if (isValid) {
            userService.removeOTP(userManagerId,codeOTP);
        }
        return isValid;
    }

    public Optional<User> findByPhoneNumberOrUserName(String phoneNumber, String userName, String typeCompte) {
        return userService.findByUserNameOrPhoneNumber( userName,phoneNumber,typeCompte);
    }

    public ResponseEntity<Map<String, Object>> validateCode(String userName, String codeOTP) {
        User manager = userService.findByLogin(userName);
        if(manager != null){
            Long userManagerId = manager.getId();
            boolean isValid = verifierCodeOTP(userManagerId, codeOTP);

            if (!isValid) {
                Map<String, Object> Response = new HashMap<>();
                Response.put("message", "Code OTP invalide ou expiré");
                Response.put("status", "ERROR");
                Response.put("code", "400");
                return ResponseEntity.ok().body(Response);
            }
        } else {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Utilisateur non trouvé");
            Response.put("status", "ERROR");
            Response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response);
        }
        return null;
    }

    public void sendCode(String phoneNumber, String userName, String otp) {
        String content = "Voici votre code d'activation : " + otp + ". Merci de compléter le processus d'activation dans les "+ Constants.TEMPS_EXPIRATION_CODE_OTP +" prochaines minutes.";

        Optional<User> userOptional = findByPhoneNumberOrUserName(phoneNumber, userName, Constants.CABINET);
        userOptional.ifPresent(user -> {
            if (user.getEmail() != null) {
                mailService.sendEmail(user.getEmail(), "Vérification OTP", content, false, false);
            }

            String phoneNumberFormated = smsService.formatPhoneNumber(user.getPhone());
            if (phoneNumberFormated != null) {
                try {
                    smsService.sendSms(content,phoneNumberFormated);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            saveOTP(user, otp);
        });
    }

    public ResponseEntity<Map<String, Object>> updateManager(String userNameEmployee,String userNameManager) {
        String typeCompte = Constants.CABINET;
        if (userNameEmployee == null || userNameEmployee.isEmpty()) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Le userName employee est requis.");
            Response.put("status", "ERROR");
            Response.put("code", "400");
            return ResponseEntity.ok().body(Response);
        }
        if (userNameManager == null || userNameManager.isEmpty()) {
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Le userName manager est requis.");
            Response.put("status", "ERROR");
            Response.put("code", "400");
            return ResponseEntity.ok().body(Response);
        }

        User employeeUser = userService.findByLogin(userNameEmployee);
        if(employeeUser == null){
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Utilisateur non trouvé avec le User Name : "+userNameEmployee);
            Response.put("status", "ERROR");
            Response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response);
        }

        User managerUser = userService.findByLogin(userNameManager);
        if(managerUser == null){
            Map<String, Object> Response = new HashMap<>();
            Response.put("message", "Utilisateur non trouvé avec le User Name : "+userNameManager);
            Response.put("status", "ERROR");
            Response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response);
        }



        if (!employeeUser.getTypeCompte().equals(typeCompte)) {
            nouvelleImmatriculationRepository.updateManager(managerUser.getId(), employeeUser.getId());
            immatriculationRecupereeRepository.updateManager(managerUser.getId(), employeeUser.getId());
        }

        return null;
    }

    public ApiResponse<Object> linkAgencesToEmployeur(AgenceEmployeurLinkDTO dto) {

        Optional<Employeur> employeurOpt = employeurRepository.findById(dto.getEmployeurId());
        if (!employeurOpt.isPresent()) {
            return ApiResponse.error400("Employeur non trouvé avec l'ID: " + dto.getEmployeurId());
        }

        Optional<Agence> agenceCssOpt = Optional.ofNullable(agenceRepository.findByCode(dto.getAgenceCssCode()));
        if (!agenceCssOpt.isPresent()) {
            return ApiResponse.error400("Agence CSS non trouvée avec le code: " + dto.getAgenceCssCode());
        }
        if (!"CSS".equals(agenceCssOpt.get().getInstitution())) {
            return ApiResponse.error400("L'agence " + dto.getAgenceCssCode() + " n'est pas une agence CSS");
        }

        Optional<Agence> agenceIpresOpt = Optional.ofNullable(agenceRepository.findByCode(dto.getAgenceIpresCode()));
        if (!agenceIpresOpt.isPresent()) {
            return ApiResponse.error400("Agence IPRES non trouvée avec le code: " + dto.getAgenceIpresCode());
        }
        if (!"IPRES".equals(agenceIpresOpt.get().getInstitution())) {
            return ApiResponse.error400("L'agence " + dto.getAgenceIpresCode() + " n'est pas une agence IPRES");
        }

        Employeur employeur = employeurOpt.get();
        employeur.setAgencyCss(dto.getAgenceCssCode());
        employeur.setAgencyIpres(dto.getAgenceIpresCode());
        employeurRepository.save(employeur);

        return ApiResponse.success("Les agences ont été liées avec succès à l'employeur");
    }
}
