package com.secusociale.portail.domain.enumeration;

public enum TypeImmatriculation {
    IMMATPORTAIL(
            "IMMATPORTAIL",
            "legalRepresentativeForm",
            "mainRegistrationForm",
            true,
            true,
            true
    ),
    DOMESTIQUE(
            "DOMESTIQUE",
            "domesticRegistrationForm",
            "domesticRegistrationForm",
            true,
            true,
            true
    ),
    PUBLIQUE_PARAPUBLIQUE(
            "PUBLIQUE-PARAPUBLIQUE",
            "personneContact",
            "mainRegistrationForm",
            false,
            true,
            true
    ),
    INDEPENDANT(
            "INDEPENDANT",
            "input",
            "",
            false,
            false,
            true
    ),
    REPRESENTATION_DIPLOMATIQUE(
            "REPRESENTATION-DIPLOMATIQUE",
            "personneContact",
            "mainRegistrationForm",
            false,
            true,
            true
    ),
    MAINTIENT_AFFILIATION(
            "MAINTIENT-AFFILIATION",
            "informationsGenerales",
            "infosSupplementaires",
            false,
            false,
            false
    );

    private final String code;
    private final String contactNodeName;
    private final String registrationNodeName;
    private final boolean useEnglishNames;
    private final boolean hasShortName;
    private final boolean hasMainLineOfBusiness;

    TypeImmatriculation(
            String code,
            String contactNodeName,
            String registrationNodeName,
            boolean useEnglishNames,
            boolean hasShortName,
            boolean hasMainLineOfBusiness
    ) {
        this.code = code;
        this.contactNodeName = contactNodeName;
        this.registrationNodeName = registrationNodeName;
        this.useEnglishNames = useEnglishNames;
        this.hasShortName = hasShortName;
        this.hasMainLineOfBusiness = hasMainLineOfBusiness;
    }

    public static TypeImmatriculation fromCode(String code) {
        for (TypeImmatriculation type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Type d'immatriculation inconnu: " + code);
    }

    public String getCode() {
        return code;
    }

    public String getContactNodeName() {
        return contactNodeName;
    }

    public String getRegistrationNodeName() {
        return registrationNodeName;
    }

    public boolean isUseEnglishNames() {
        return useEnglishNames;
    }

    public boolean hasShortName() {
        return hasShortName;
    }

    public boolean hasMainLineOfBusiness() {
        return hasMainLineOfBusiness;
    }

    public String getNodeNameForContact() {
        return contactNodeName;
    }

    public String getNodeNameForRegistration() {
        return registrationNodeName.isEmpty() ? "input" : registrationNodeName;
    }

    public boolean isDirectInInput() {
        return "input".equals(contactNodeName);
    }
}
