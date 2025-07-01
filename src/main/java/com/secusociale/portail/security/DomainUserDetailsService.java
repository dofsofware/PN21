package com.secusociale.portail.security;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.helpers.LoginAttemptService;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(String.format("%s_%s", login, ip))) {
            throw new RuntimeException("bloqué");
        }
        if (new EmailValidator().isValid(login, null)) {
            return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur avec l'email " + login + " n'existe pas"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur " + lowercaseLogin + " n'existe pas"));

    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.getActivated()) {
            throw new UserNotActivatedException("L'utilisateur " + lowercaseLogin + " n'est pas activé");
        }
        if (user.getLocked()) {
            throw new UserNotActivatedException("L'utilisateur " + lowercaseLogin + " a été bloqué");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            grantedAuthorities);
    }
}
