package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Authority;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.service.dto.OTPCode;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.AuthorityRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import io.github.jhipster.security.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    private AgenceRepository agenceRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final CacheManager cacheManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, CacheManager cacheManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.cacheManager = cacheManager;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
//                if (user.getTypeCompte().equals(Constants.SALARIE)) {
//                    user.setActivated(false);
//                }
                user.setActivationKey(null);
                this.clearUserCaches(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }


    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                this.clearUserCaches(user);
                return user;
            });
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public List<User> getUsersByEmailLike(String email) {
        return userRepository.findAllByEmailLike(email);
    }

    public Optional<User> requestPasswordReset(String mail,String codeOtp) {
        return userRepository.findOneByEmailIgnoreCase(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(codeOtp);
                user.setResetDate(Instant.now());
                this.clearUserCaches(user);
                return user;
            });
    }

    public Optional<User> findOneByPhone(String phone) {
        return userRepository.findOneByPhoneIgnoreCase(phone)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                this.clearUserCaches(user);
                return user;
            });
    }

    public Long countByPhoneIgnoreCase(String phone){
        return userRepository.countByPhoneIgnoreCase(phone);
    }



    public User registerUser(UserDTO userDTO, String password) {
        userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        userRepository.findFirstByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed && existingUser.getEmail() != null) {
                throw new EmailAlreadyUsedException();
            }
        });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        //for agents
        newUser.setInstitution(userDTO.getInstitution());
        newUser.setAgence(userDTO.getAgence());
        newUser.setOtp(userDTO.getOtp());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setTypeCompte(userDTO.getTypeCompte());
        newUser.setLangKey(userDTO.getLangKey());
        newUser.setPhone(userDTO.getPhone());
        Set<Authority> authorities = new HashSet<>();
        if (userDTO.getAuthorities() != null)
            for (String role : userDTO.getAuthorities()) {
                authorityRepository.findById(role).ifPresent(authorities::add);
            }
        // new user is active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
//        Set<Authority> authorities = new HashSet<>();
        if (authorities.isEmpty()) {
            authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        }
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User recoverRegisteredUser(UserDTO userDTO, String password) {
        User newUser = userRepository.findByLogin(userDTO.getLogin());
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encryptedPassword);
        // new user is active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        newUser.setHasPasswordUpdated(false);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }


    public AgentDTO registerAgent(UserDTO userDTO, String password,String profil) {
        userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        //for agents
        newUser.setInstitution(userDTO.getInstitution());
        newUser.setAgence(userDTO.getAgence());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setTypeCompte(userDTO.getTypeCompte());
        newUser.setLangKey(userDTO.getLangKey());
        newUser.setPhone(userDTO.getPhone());
        // new user is active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        switch (profil) {
            case Constants.GESTIONNAIRE_EMPLOYEUR:
                authorityRepository.findById(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR).ifPresent(authorities::add);
                break;
            case Constants.GESTIONNAIRE_SALARIE:
                authorityRepository.findById(AuthoritiesConstants.GESTIONNAIRE_SALARIE).ifPresent(authorities::add);
                break;
            case Constants.CHEF_AGENCE:
                authorityRepository.findById(AuthoritiesConstants.CHEF_AGENCE).ifPresent(authorities::add);
                break;
            case Constants.SUPER_ADMIN:
                authorityRepository.findById(AuthoritiesConstants.SUPER_ADMIN).ifPresent(authorities::add);
                break;
        }
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        AgentDTO agentDTO = new AgentDTO(newUser);
        agentDTO.setAgenceObject(agenceRepository.findByCode(newUser.getAgence()));
        return agentDTO;
    }

    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.getActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        this.clearUserCaches(existingUser);
        return true;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail().toLowerCase());
        }
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        ArrayList<String> defaultDesactiveProfiles = new ArrayList<>();
        defaultDesactiveProfiles.add(Constants.SALARIE);
        defaultDesactiveProfiles.add(Constants.EMPLOYEUR);
        if (defaultDesactiveProfiles.contains(userDTO.getTypeCompte())) {
            user.setActivated(false);
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        userRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if (email != null) {
                    user.setEmail(email.toLowerCase());
                }
                user.setLangKey(langKey);
                user.setImageUrl(imageUrl);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                if (userDTO.getLogin() != null) {
                    user.setLogin(userDTO.getLogin().toLowerCase());
                }
                if (userDTO.getTypeCompte() != null) {
                    user.setTypeCompte(userDTO.getTypeCompte().toUpperCase(Locale.ROOT));
                }
                if (userDTO.getFirstName() != null) {
                    user.setFirstName(userDTO.getFirstName());
                }
                if (userDTO.getLastName() != null) {
                    user.setLastName(userDTO.getLastName());
                }
                if (userDTO.getEmail() != null) {
                    user.setEmail(userDTO.getEmail().toLowerCase());
                }
                if (!StringUtils.isEmpty(userDTO.getPhone())) {
                    user.setPhone(userDTO.getPhone());
                }
                if (!StringUtils.isEmpty(userDTO.getImageUrl())) {
                    user.setImageUrl(userDTO.getImageUrl());
                }
                if (!StringUtils.isEmpty(userDTO.getLangKey())) {
                    user.setLangKey(userDTO.getLangKey());
                }
                user.setActivated(userDTO.isActivated());
                user.setLocked(userDTO.isLocked());


                Set<Authority> managedAuthorities = user.getAuthorities();

                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public Optional<AgentDTO> updateAgent(UserDTO userDTO) {
        System.out.println(userDTO.toString());
        return Optional.of(userRepository
                .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                if (!StringUtils.isEmpty(userDTO.getFirstName())) {
                    user.setFirstName(userDTO.getFirstName());
                }
                if (!StringUtils.isEmpty(userDTO.getLastName())) {
                    user.setLastName(userDTO.getLastName());
                }
                if (!StringUtils.isEmpty(userDTO.getEmail())) {
                    user.setEmail(userDTO.getEmail().toLowerCase());
                }
                if (!StringUtils.isEmpty(userDTO.getInstitution())) {
                    user.setInstitution(userDTO.getInstitution());
                }
                if (!StringUtils.isEmpty(userDTO.getAgence())) {
                    user.setAgence(userDTO.getAgence());
                }
                if (!StringUtils.isEmpty(userDTO.getPhone())) {
                    user.setPhone(userDTO.getPhone());
                }
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                if (userDTO.getAuthorities() != null) {
                    userDTO.getAuthorities().stream()
                        .map(authorityRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .forEach(managedAuthorities::add);
                }
                this.clearUserCaches(user);
//                user = userRepository.save(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(AgentDTO::new);
    }

    public Optional<AgentDTO> activateAgent(Long id, String mode) {
        return Optional.of(userRepository
                .findById(id))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setActivated(mode.equalsIgnoreCase("activation"));
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(AgentDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            this.clearUserCaches(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                user.setHasPasswordUpdated(true);
                this.clearUserCaches(user);
                log.debug("Changed password for User: {}", user);
            });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<AgentDTO> getAllAgents(Pageable pageable) {
        Authority a = authorityRepository.findById(AuthoritiesConstants.AGENT).orElse(null);
        return userRepository.findAllByTypeCompteAndAuthoritiesContains(pageable, Constants.TYPE_AGENT, a).map(AgentDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<AgentDTO> getAllEmployeurs(Pageable pageable) {
        Authority a = authorityRepository.findById(AuthoritiesConstants.USER).orElse(null);
        ArrayList<String> types = new ArrayList<>();
        types.add(Constants.EMPLOYEUR);
        types.add(Constants.CABINET);
        return userRepository.findAllByTypeCompteInAndAuthoritiesContains(pageable, types, a).map(AgentDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllSalaries(Pageable pageable) {
        Authority a = authorityRepository.findById(AuthoritiesConstants.SALARIE).orElse(null);
        ArrayList<String> types = new ArrayList<>();
        types.add(Constants.SALARIE);
        return userRepository.findAllByTypeCompteInAndAuthoritiesContains(pageable, types, a).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserDTOWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesById(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired every day, at 01:00 (am).
     */
    //@Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
            .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
            .forEach(user -> {
                log.debug("Deleting not activated user {}", user.getLogin());
                userRepository.delete(user);
                this.clearUserCaches(user);
            });
    }

    /**
     * Gets a list of all the authorities.
     *
     * @return a list of all the authorities.
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }


    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        if (user.getEmail() != null) {
            Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
        }
    }

    public Optional<User> findOne(Long id) { return userRepository.findById(id);  }

    public User getLoggerUser() {
        if (SecurityUtils.getCurrentUserJWT().isPresent() && SecurityUtils.getCurrentUserLogin().isPresent()) {
            String login = SecurityUtils.getCurrentUserLogin().get();
            return userRepository.findByLogin(login);
        }
        return null;
    }

    public boolean existsById(Long userMandataireId) { return userRepository.existsById(userMandataireId); }

    public User save(User user) { return userRepository.save(user); }

    public boolean existsByIdAndCodeOTP(Long userMandataireId, String codeOTP) {
        return userRepository.existsByIdAndValidCodeOTP(userMandataireId, codeOTP, Instant.now());
    }

    public void removeOTP(Long userMandataireId, String codeOTP) {
        Optional<User> user = userRepository.findById(userMandataireId);
        user.ifPresent(u -> {
            List<OTPCode> listCodeOTP = u.getOtpCodes();
            listCodeOTP.removeIf(otpCode -> otpCode.getCode().equals(codeOTP));
            u.setOtpCodes(listCodeOTP);
            userRepository.save(u);
        });
    }


    public Optional<User> findByUserNameOrPhoneNumber(String userName, String phoneNumber, String typeCompte) {
        return userRepository.findByUserNameOrPhoneNumber(userName,phoneNumber,typeCompte);
    }

    public boolean existsByUserNameOrTelephoneAndUserTypeCompte(String userName, String phoneNumber, String typeCompte) {
        return userRepository.existsByUserNameOrTelephoneAndUserTypeCompte(userName,phoneNumber,typeCompte);
    }

    public User findByLogin(String userName){
        return userRepository.findByLogin(userName);
    }


    public boolean checkLogin(String username, String password) {
        User user = userRepository.findByLogin(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }


    public Optional<UserDTO> updateCachet(Long id, String cachet) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setCachet(cachet);

            userRepository.save(user);
            UserDTO userDTO = new UserDTO(user);
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }
}
