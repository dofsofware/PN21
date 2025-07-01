package com.secusociale.portail.beans;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

public class SessionAuthenticationStrategy extends ConcurrentSessionControlAuthenticationStrategy {

    public SessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
        super(sessionRegistry);
    }

    @Override
    protected int getMaximumSessionsForThisUser(Authentication authentication) {
        return 1;
    }
}
