package com.secusociale.portail.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String CABINET = "ROLE_CABINET";

    public static final String AGENT = "ROLE_AGENT";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String EMPLOYEUR = "ROLE_EMPLOYEUR";
    public static final String EMPLOYE = "ROLE_EMPLOYE";
    public static final String CONSULTATION = "ROLE_CONSULTATION";

    public static final String SALARIE = "ROLE_SALARIE";
    public static final String PORTAIL = "ROLE_PORTAIL";
    public static final String GESTIONNAIRE_SALARIE = "ROLE_GESTIONNAIRE_SALARIE";
    public static final String GESTIONNAIRE_EMPLOYEUR = "ROLE_GESTIONNAIRE_EMPLOYEUR";
    public static final String CHEF_AGENCE = "ROLE_CHEF_AGENCE";
    public static final String CHEF_AGENCE_IPRES = "ROLE_CHEF_AGENCE_IPRES";
    public static final String CHEF_AGENCE_CSS = "ROLE_CHEF_AGENCE_CSS";

    public static final String ALLOCATAIRE = "ROLE_ALLOCATAIRE";

    private AuthoritiesConstants() {
    }
}
