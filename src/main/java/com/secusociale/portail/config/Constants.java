package com.secusociale.portail.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    //public static final String PHONE_REGEX = "^[_.@A-Za-z0-9-]*$";
    //public static final String EMAIL_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "fr";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String TYPE_AGENT = "AGENT";
    public static final String EMPLOYEUR = "EMPLOYEUR";
    public static final String CONSULTATION = "CONSULTATION";
    public static final String ADMINISTRATEUR = "ADMINISTRATEUR";
    public static final String CABINET = "CABINET";
    public static final String SALARIE = "SALARIE";
    public static final String GESTIONNAIRE_SALARIE = "GESTIONNAIRE_SALARIE";
    public static final String GESTIONNAIRE_EMPLOYEUR = "GESTIONNAIRE_EMPLOYEUR";
    public static final String CHEF_AGENCE = "CHEF_AGENCE";
    public static final String SUPER_ADMIN= "SUPER_ADMIN";


    public static final String GRAPPE_TYPE_ENFANT = "ENFANT";
    public static final String GRAPPE_TYPE_CONJOINT = "CONJOINT";
    public static final String ASCENDANT_PERE = "ASCENDANT_PERE";
    public static final String ASCENDANT_MERE = "ASCENDANT_MERE";


    public static final Integer TEMPS_EXPIRATION_CODE_OTP = 5;
    public static final String INDICATIF_DE_TELEPHONE = "+221";
    public static final Boolean DEV_MODE = true;
    public static final String REJET = "REJET";

    public static  final String CERTIFICAT_DE_NON_INSCRIPTION = "CERTIFICAT_DE_NON_INSCRIPTION";
    public static  final String CERTIFICAT_DE_RADIATION = "CERTIFICAT_DE_RADIATION";
    public static final String VALIDE = "VALIDE";

    public static final String PSRM_URL = "http://192.168.125.23:6500";
    public static final String MS_SYNCHRO_URL = "http://192.168.125.113:30034/batchsync";

    public static final String[] EMAILS_DEV_BACK_END = new String[]{"xamalteam@gmail.com", "elhdamecisse@gmail.com","cfkhouma@gmail.com"};
    public static final String[] EMAILS_DAILY_REPPORTING = new String[]{"xamalteam@gmail.com", "elhdamecisse@gmail.com","gueybabss@gmail.com"};

    private Constants() {
    }
}
