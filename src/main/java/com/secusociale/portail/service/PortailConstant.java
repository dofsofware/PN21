package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;

public final class PortailConstant {

    public static final String DEFAULTUSEDCRON = "0 0 0 * * ?";

    public static final String USERNAME = "WANGO";
    public static final String PASSWORD = "wango123";

    public static final String IMMATRICULATION_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/IMMATRICULATION_INBOUND?WSDL";
    public static final String SUCCURSALESURL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_LISTE_SECURSALES?WSDL";
    public static final String DMT_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmDmt?WSDL";
    public static final String DECLARATION_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/DNS_INBOUND_SERVICE?WSDL";
    public static final String ATTESTATION_REGU_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmGetAttestationRegularite?wsdl";
    public static final String CERTIFICAT_IMMAT_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmGetCertificatImmatriculation?wsdl";
    public static final String STATUT_DOSSIER_IMMAT_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/Cm-GetStatusDossierImmatriculation?WSDL";
    public static final String PRE_DNS_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmPresDns?WSDL";
    public static final String MAINT_AFF_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/MAINT-AFF_INBOUND?WSDL";
    public static final String PUBLIC_PARAPUBLIC_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/IMMAT2_INBOUND?WSDL";
    public static final String REP_DIP_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/IMMAT_REP_DIPLO?WSDL";
    public static final String GEN_ATTESTATION_REG_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GEN_ATTESTATION?WSDL";
    public static final String CM_GET_LISTE_SECURSALES_REG_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_LISTE_SECURSALES?WSDL";
    public static final String CM_HAS_DNS_REG_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_HAS_DNS?WSDL";
    public static final String CM_PER_EXIST_REG_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_PER_EXIST?WSDL";
    public static final String CHECK_EXISTENCE_EMPLOYEUR_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmCheckExistenceEmployeur?WSDL";
    public static final String FIND_EMPLOYEUR_BY_TYPE_ID_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmFindEmployeurByTypeId?WSDL";
    public static final String EMPLOYEUR_EXISTANT_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_EMPLOYEUR_DETAILS?wsdl";
    public static final String DOMESTIQUE_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/inboundDomFrm?WSDL";
    public static final String RECEPISSE_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/GET_RECEPISSE_DEPOT_URL?wsdl";
    public static final String INFOS_COMPTE_EMPLOYEUR_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_INFOS_EMPLOYEUR?wsdl";
    public static final String INFOS_SALARIES_WSDL = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_PERSONS_LINK_TO_EMPLOYER?wsdl";


    private PortailConstant() {
    }
}
