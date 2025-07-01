package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.domain.enumeration.StatusDocP;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.domain.enumeration.StatutReclamation;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND;
import com.secusociale.portail.service.soap.domestique.InboundDomFrm;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUND;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAI;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUND;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A NouvelleImmatriculation.
 */
@Entity
@Table(name = "nouvelle_immatriculation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NouvelleImmatriculation implements Serializable {

    private static final long serialVersionUID = 1L;

    public NouvelleImmatriculation() {
    }

    public NouvelleImmatriculation(Localimmatriculation localimmatriculation) {
        this.setPayload(localimmatriculation.getClearPayload());
        this.setCreatedAt(localimmatriculation.getCreatedAt());
        this.setTypeIdentifiant(localimmatriculation.getTypeIdentifiant());
        this.setType(localimmatriculation.getType());
        this.setStatusdoc(localimmatriculation.getStatusdoc());
        this.setStatusdesc(localimmatriculation.getStatusdesc());
        this.setIsSubmit(localimmatriculation.isIsSubmited());
        this.setUser(localimmatriculation.getUser());
        this.setCreatedAt(localimmatriculation.getCreatedAt());
        if ("IESV".equalsIgnoreCase(localimmatriculation.getStatusdoc())) {
            this.setNinea(localimmatriculation.getNinea());
        } else {
            this.setNumeroUnique(localimmatriculation.getNinea());
        }

        String type = localimmatriculation.getType();
        Object imm = localimmatriculation.getPayload();
        switch (type) {
            case "INDEPENDANT":
                CMCrtIndForXAI.Output output = ((CMCrtIndForXAI) imm).getOutput();
                if (output != null) {
                    this.setAgenceCss(output.getAgenceCss());
                    this.setAgenceIpres(output.getAgenceIpres());
                    this.setTauxAt(output.getTauxAt());
                    this.setSectorCss(output.getSectorCss());
                    this.setSectorIpres(output.getSectorIpres());
                    this.setZoneCss(output.getZoneCss());
                    this.setZoneIpres(output.getZoneIpres());
                    this.setNumdoc(output.getDossierId());
                    this.setEmployerRegistrationFormId(output.getFormulaireId());
                }
                break;
            case "PUBLIQUE-PARAPUBLIQUE":
                IMMAT2INBOUND.Output output2 = ((IMMAT2INBOUND) imm).getOutput();
                if (output2 != null) {
                    this.setAgenceCss(output2.getAgenceCss());
                    this.setAgenceIpres(output2.getAgenceIpres());
                    this.setTauxAt(output2.getTauxAt());
                    this.setSectorCss(output2.getSectorCss());
                    this.setSectorIpres(output2.getSectorIpres());
                    this.setZoneCss(output2.getZoneCss());
                    this.setZoneIpres(output2.getZoneIpres());
                    this.setNumdoc(output2.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output2.getEmployerRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output2.getEmployeeRegistrationFormId());
                }
                IMMAT2INBOUND.Input input2 = ((IMMAT2INBOUND) imm).getInput();
                if (input2 != null) {
                    if (input2.getEmployerQuery() != null) {
                        this.setRaisonSociale(input2.getEmployerQuery().getEmployerName());
                        this.setNinea(input2.getEmployerQuery().getNineaNumber());
                    }
                }
                break;
            case "IMMATPORTAIL":
                IMMATRICULATIONINBOUND.Output output3 = ((IMMATRICULATIONINBOUND) imm).getOutput();
                if (output3 != null) {
                    this.setAgenceCss(output3.getAgenceCss());
                    this.setAgenceIpres(output3.getAgenceIpres());
                    this.setTauxAt(output3.getTauxAt());
                    this.setSectorCss(output3.getSectorCss());
                    this.setSectorIpres(output3.getSectorIpres());
                    this.setZoneCss(output3.getZoneCss());
                    this.setZoneIpres(output3.getZoneIpres());
                    this.setNumdoc(output3.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output3.getEmployerRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output3.getEmployeeRegistrationFormId());
                }
                IMMATRICULATIONINBOUND.Input input3 = ((IMMATRICULATIONINBOUND) imm).getInput();
                if (input3 != null) {
                    if (input3.getEmployerQuery() != null) {
                        this.setRaisonSociale(input3.getEmployerQuery().getEmployerName());
                        this.setNinea(input3.getEmployerQuery().getNineaNumber());
                        this.setRegistreCommerce(input3.getEmployerQuery().getTradeRegisterNumber());
                    }
                }
                break;
            case "MAINTIENT-AFFILIATION":
                MAINTAFFINBOUND.Output output4 = ((MAINTAFFINBOUND) imm).getOutput();
                if (output4 != null) {
                    this.setNumdoc(output4.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output4.getRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output4.getRegistrationFormId());
                }
                break;
            case "REPRESENTATION-DIPLOMATIQUE":
                IMMATREPDIPLO.Output output5 = ((IMMATREPDIPLO) imm).getOutput();
                if (output5 != null) {
                    this.setAgenceCss(output5.getAgenceCss());
                    this.setAgenceIpres(output5.getAgenceIpres());
                    this.setTauxAt(output5.getTauxAt());
                    this.setSectorCss(output5.getSectorCss());
                    this.setSectorIpres(output5.getSectorIpres());
                    this.setZoneCss(output5.getZoneCss());
                    this.setZoneIpres(output5.getZoneIpres());
                    this.setNumdoc(output5.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output5.getEmployerRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output5.getEmployeeRegistrationFormId());
                }
                IMMATREPDIPLO.Input input5 = ((IMMATREPDIPLO) imm).getInput();
                if (input5 != null) {
                    if (input5.getEmployerQuery() != null) {
                        this.setRaisonSociale(input5.getEmployerQuery().getEmployerName());
                        this.setNinea(input5.getEmployerQuery().getNineaNumber());
                    }
                }
                break;
            case "DOMESTIQUE":
                imm = new InboundDomFrm();
                InboundDomFrm.Output output6 = ((InboundDomFrm) imm).getOutput();
                if (output6 != null) {
                    this.setAgenceCss(output6.getAgenceCss());
                    this.setAgenceIpres(output6.getAgenceIpres());
                    this.setTauxAt(output6.getTauxAt());
                    this.setSectorCss(output6.getSectorCss());
                    this.setSectorIpres(output6.getSectorIpres());
                    this.setZoneCss(output6.getZoneCss());
                    this.setZoneIpres(output6.getZoneIpres());
                    this.setNumdoc(output6.getFolderId());
                }
//                InboundDomFrm.Input input6 = ((InboundDomFrm) imm).getInput();
//                if (input6 != null) {
//
//                }
                break;
        }
    }

    public NouvelleImmatriculation fromLocalImmatriculation(Localimmatriculation localimmatriculation) {
        this.setPayload(localimmatriculation.getClearPayload());
        this.setCreatedAt(localimmatriculation.getCreatedAt());
        this.setTypeIdentifiant(localimmatriculation.getTypeIdentifiant());
        this.setType(localimmatriculation.getType());
        this.setStatusdoc(localimmatriculation.getStatusdoc());
        this.setStatusdesc(localimmatriculation.getStatusdesc());
        this.setIsSubmit(localimmatriculation.isIsSubmited());
        this.setUser(localimmatriculation.getUser());
        this.setCreatedAt(localimmatriculation.getCreatedAt());
        if ("IESV".equalsIgnoreCase(localimmatriculation.getStatusdoc())) {
            this.setNinea(localimmatriculation.getNinea());
        } else {
            this.setNumeroUnique(localimmatriculation.getNinea());
        }

        String type = localimmatriculation.getType();
        Object imm = localimmatriculation.getPayload();
        switch (type) {
            case "INDEPENDANT":
                CMCrtIndForXAI.Output output = ((CMCrtIndForXAI) imm).getOutput();
                if (output != null) {
                    this.setAgenceCss(output.getAgenceCss());
                    this.setAgenceIpres(output.getAgenceIpres());
                    this.setTauxAt(output.getTauxAt());
                    this.setSectorCss(output.getSectorCss());
                    this.setSectorIpres(output.getSectorIpres());
                    this.setZoneCss(output.getZoneCss());
                    this.setZoneIpres(output.getZoneIpres());
                    this.setNumdoc(output.getDossierId());
                    this.setEmployerRegistrationFormId(output.getFormulaireId());
                }
                break;
            case "PUBLIQUE-PARAPUBLIQUE":
                IMMAT2INBOUND.Output output2 = ((IMMAT2INBOUND) imm).getOutput();
                if (output2 != null) {
                    this.setAgenceCss(output2.getAgenceCss());
                    this.setAgenceIpres(output2.getAgenceIpres());
                    this.setTauxAt(output2.getTauxAt());
                    this.setSectorCss(output2.getSectorCss());
                    this.setSectorIpres(output2.getSectorIpres());
                    this.setZoneCss(output2.getZoneCss());
                    this.setZoneIpres(output2.getZoneIpres());
                    this.setNumdoc(output2.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output2.getEmployerRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output2.getEmployeeRegistrationFormId());
                }
                break;
            case "IMMATPORTAIL":
                IMMATRICULATIONINBOUND.Output output3 = ((IMMATRICULATIONINBOUND) imm).getOutput();
                if (output3 != null) {
                    this.setAgenceCss(output3.getAgenceCss());
                    this.setAgenceIpres(output3.getAgenceIpres());
                    this.setTauxAt(output3.getTauxAt());
                    this.setSectorCss(output3.getSectorCss());
                    this.setSectorIpres(output3.getSectorIpres());
                    this.setZoneCss(output3.getZoneCss());
                    this.setZoneIpres(output3.getZoneIpres());
                    this.setNumdoc(output3.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output3.getEmployerRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output3.getEmployeeRegistrationFormId());
                }
                break;
            case "MAINTIENT-AFFILIATION":
                MAINTAFFINBOUND.Output output4 = ((MAINTAFFINBOUND) imm).getOutput();
                if (output4 != null) {
                    this.setNumdoc(output4.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output4.getRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output4.getRegistrationFormId());
                }
                break;
            case "REPRESENTATION-DIPLOMATIQUE":
                IMMATREPDIPLO.Output output5 = ((IMMATREPDIPLO) imm).getOutput();
                if (output5 != null) {
                    this.setAgenceCss(output5.getAgenceCss());
                    this.setAgenceIpres(output5.getAgenceIpres());
                    this.setTauxAt(output5.getTauxAt());
                    this.setSectorCss(output5.getSectorCss());
                    this.setSectorIpres(output5.getSectorIpres());
                    this.setZoneCss(output5.getZoneCss());
                    this.setZoneIpres(output5.getZoneIpres());
                    this.setNumdoc(output5.getProcessFlowId());
                    this.setEmployerRegistrationFormId(output5.getEmployerRegistrationFormId());
                    this.setEmployeeRegistrationFormId(output5.getEmployeeRegistrationFormId());
                }
                break;
            case "DOMESTIQUE":
                imm = new InboundDomFrm();
                InboundDomFrm.Output output6 = ((InboundDomFrm) imm).getOutput();
                if (output6 != null) {
                    this.setAgenceCss(output6.getAgenceCss());
                    this.setAgenceIpres(output6.getAgenceIpres());
                    this.setTauxAt(output6.getTauxAt());
                    this.setSectorCss(output6.getSectorCss());
                    this.setSectorIpres(output6.getSectorIpres());
                    this.setZoneCss(output6.getZoneCss());
                    this.setZoneIpres(output6.getZoneIpres());
                    this.setNumdoc(output6.getFolderId());
                }
                break;
        }
        return this;
    }

//    public NouvelleImmatriculation(String type, Object immatriculation) {
//        this.setPayload(localimmatriculation.getClearPayload());
//        this.setCreatedAt(localimmatriculation.getCreatedAt());
//        this.setTypeIdentifiant(localimmatriculation.getTypeIdentifiant());
//        this.setType(localimmatriculation.getType());
//        this.setStatusdoc(localimmatriculation.getStatusdoc());
//        this.setStatusdesc(localimmatriculation.getStatusdesc());
//        this.setIsSubmit(localimmatriculation.isIsSubmited());
//        this.setUser(localimmatriculation.getUser());
//        this.setCreatedAt(localimmatriculation.getCreatedAt());
//        if ("IESV".equalsIgnoreCase(localimmatriculation.getStatusdoc())) {
//            this.setNinea(localimmatriculation.getNinea());
//        } else {
//            this.setNumeroUnique(localimmatriculation.getNinea());
//        }
//
//        String type = localimmatriculation.getType();
//        Object imm = localimmatriculation.getPayload();
//        switch (type) {
//            case "INDEPENDANT":
//                CMCrtIndForXAI.Output output = ((CMCrtIndForXAI) imm).getOutput();
//                if (output != null) {
//                    this.setAgenceCss(output.getAgenceCss());
//                    this.setAgenceIpres(output.getAgenceIpres());
//                    this.setTauxAt(output.getTauxAt());
//                    this.setSectorCss(output.getSectorCss());
//                    this.setSectorIpres(output.getSectorIpres());
//                    this.setZoneCss(output.getZoneCss());
//                    this.setZoneIpres(output.getZoneIpres());
//                    this.setNumdoc(output.getDossierId());
//                    this.setEmployerRegistrationFormId(output.getFormulaireId());
//                }
//                break;
//            case "PUBLIQUE-PARAPUBLIQUE":
//                IMMAT2INBOUND.Output output2 = ((IMMAT2INBOUND) imm).getOutput();
//                if (output2 != null) {
//                    this.setAgenceCss(output2.getAgenceCss());
//                    this.setAgenceIpres(output2.getAgenceIpres());
//                    this.setTauxAt(output2.getTauxAt());
//                    this.setSectorCss(output2.getSectorCss());
//                    this.setSectorIpres(output2.getSectorIpres());
//                    this.setZoneCss(output2.getZoneCss());
//                    this.setZoneIpres(output2.getZoneIpres());
//                    this.setNumdoc(output2.getProcessFlowId());
//                    this.setEmployerRegistrationFormId(output2.getEmployerRegistrationFormId());
//                    this.setEmployeeRegistrationFormId(output2.getEmployeeRegistrationFormId());
//                }
//                break;
//            case "IMMATPORTAIL":
//                IMMATRICULATIONINBOUND.Output output3 = ((IMMATRICULATIONINBOUND) imm).getOutput();
//                if (output3 != null) {
//                    this.setAgenceCss(output3.getAgenceCss());
//                    this.setAgenceIpres(output3.getAgenceIpres());
//                    this.setTauxAt(output3.getTauxAt());
//                    this.setSectorCss(output3.getSectorCss());
//                    this.setSectorIpres(output3.getSectorIpres());
//                    this.setZoneCss(output3.getZoneCss());
//                    this.setZoneIpres(output3.getZoneIpres());
//                    this.setNumdoc(output3.getProcessFlowId());
//                    this.setEmployerRegistrationFormId(output3.getEmployerRegistrationFormId());
//                    this.setEmployeeRegistrationFormId(output3.getEmployeeRegistrationFormId());
//                }
//                break;
//            case "MAINTIENT-AFFILIATION":
//                MAINTAFFINBOUND.Output output4 = ((MAINTAFFINBOUND) imm).getOutput();
//                if (output4 != null) {
//                    this.setNumdoc(output4.getProcessFlowId());
//                    this.setEmployerRegistrationFormId(output4.getRegistrationFormId());
//                    this.setEmployeeRegistrationFormId(output4.getRegistrationFormId());
//                }
//                break;
//            case "REPRESENTATION-DIPLOMATIQUE":
//                IMMATREPDIPLO.Output output5 = ((IMMATREPDIPLO) imm).getOutput();
//                if (output5 != null) {
//                    this.setAgenceCss(output5.getAgenceCss());
//                    this.setAgenceIpres(output5.getAgenceIpres());
//                    this.setTauxAt(output5.getTauxAt());
//                    this.setSectorCss(output5.getSectorCss());
//                    this.setSectorIpres(output5.getSectorIpres());
//                    this.setZoneCss(output5.getZoneCss());
//                    this.setZoneIpres(output5.getZoneIpres());
//                    this.setNumdoc(output5.getProcessFlowId());
//                    this.setEmployerRegistrationFormId(output5.getEmployerRegistrationFormId());
//                    this.setEmployeeRegistrationFormId(output5.getEmployeeRegistrationFormId());
//                }
//                break;
//            case "DOMESTIQUE":
//                imm = new InboundDomFrm();
//                InboundDomFrm.Output output6 = ((InboundDomFrm) imm).getOutput();
//                if (output6 != null) {
//                    this.setAgenceCss(output6.getAgenceCss());
//                    this.setAgenceIpres(output6.getAgenceIpres());
//                    this.setTauxAt(output6.getTauxAt());
//                    this.setSectorCss(output6.getSectorCss());
//                    this.setSectorIpres(output6.getSectorIpres());
//                    this.setZoneCss(output6.getZoneCss());
//                    this.setZoneIpres(output6.getZoneIpres());
//                    this.setNumdoc(output6.getFolderId());
//                }
//                break;
//        }
//    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    @JsonProperty("typeImmatriculation")
    private String type;

    @Column(name = "numdoc")
    private String numdoc;

    @Column(name = "statusdoc")
    private String statusdoc;

    @Lob
    @Column(name = "statusdesc")
    private String statusdesc;

    @Lob
    @Column(name = "payload")
    @JsonIgnore
    private String payload;

    @Column(name = "type_identifiant")
    private String typeIdentifiant;

    @Column(name = "ninea")
    private String ninea;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "numero_dossier")
    private String numeroDossier;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusdocp")
    private StatusDocP statusDocP = StatusDocP.VALIDATION_GESTIONNAIRE_DE_COMPTE;

    @Column(name = "numero_css")
    private String numeroCss;

    @Column(name = "numero_ipres")
    private String numeroIpres;

    @Column(name = "user")
    private Long user;

    @Column(name = "is_submit")
    private Boolean isSubmit;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "employer_registration_form_id")
    private String employerRegistrationFormId;

    @Column(name = "employee_registration_form_id")
    private String employeeRegistrationFormId;

    @Lob
    @Column(name = "zone_css")
    private String zoneCss;

    @Lob
    @Column(name = "sector_css")
    private String sectorCss;

    @Column(name = "zone_ipres")
    private String zoneIpres;

    @Lob
    @Column(name = "sector_ipres")
    private String sectorIpres;

    @Column(name = "agence_css")
    private String agenceCss;

    @Column(name = "agence_ipres")
    private String agenceIpres;

    @Column(name = "taux_at")
    private String tauxAt;

    @Lob
    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "registre_commerce")
    private String registreCommerce;

    @Column(name = "manager_id")
    private Long manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public NouvelleImmatriculation type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public NouvelleImmatriculation numdoc(String numdoc) {
        this.numdoc = numdoc;
        return this;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager_id) {
        this.manager = manager_id;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getStatusdoc() {
        return statusdoc;
    }

    public NouvelleImmatriculation statusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
        return this;
    }

    public void setStatusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public NouvelleImmatriculation statusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
        return this;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getPayload() {
        return payload;
    }

    @JsonProperty("immatriculation")
    public Object getParsedPayload() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object imm = null;
        switch (type) {
            case "INDEPENDANT":
                imm = new CMCrtIndForXAI();
                try {
                    String clearText = this.payload;
                    imm = objectMapper.readValue(clearText, CMCrtIndForXAI.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "PUBLIQUE-PARAPUBLIQUE":
                imm = new IMMAT2INBOUND();
                try {
                    String clearText = this.payload;
                    imm = objectMapper.readValue(clearText, IMMAT2INBOUND.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "IMMATPORTAIL":
                imm = new IMMATRICULATIONINBOUND();
                try {
                    String clearText = this.payload;
                    imm = objectMapper.readValue(clearText, IMMATRICULATIONINBOUND.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "MAINTIENT-AFFILIATION":
                imm = new MAINTAFFINBOUND();
                try {
                    String clearText = this.payload;
                    imm = objectMapper.readValue(clearText, MAINTAFFINBOUND.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "REPRESENTATION-DIPLOMATIQUE":
                imm = new IMMATREPDIPLO();
                try {
                    String clearText = this.payload;
                    imm = objectMapper.readValue(clearText, IMMATREPDIPLO.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "DOMESTIQUE":
                imm = new InboundDomFrm();
                try {
                    String clearText = this.payload;
                    imm = objectMapper.readValue(clearText, InboundDomFrm.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
        }

        return imm;
    }

    public NouvelleImmatriculation payload(String payload) {
        this.payload = payload;
        return this;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public NouvelleImmatriculation typeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
        return this;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getNinea() {
        try {
            if (StringUtils.isNotEmpty(ninea) && ninea.length() > 9) {
                switch (type) {
                    case "PUBLIQUE-PARAPUBLIQUE":
                        return ((IMMAT2INBOUND) getParsedPayload()).getInput().getEmployerQuery().getNineaNumber();
                    case "IMMATPORTAIL":
                        return ((IMMATRICULATIONINBOUND) getParsedPayload()).getInput().getEmployerQuery().getNineaNumber();
                    case "REPRESENTATION-DIPLOMATIQUE":
                        return ((IMMATREPDIPLO) getParsedPayload()).getInput().getEmployerQuery().getNineaNumber();
                }
            }
        } catch (Exception e) {
            return ninea;
        }
        return ninea;
    }

    public NouvelleImmatriculation ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public NouvelleImmatriculation numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getNumeroCss() {
        return numeroCss;
    }

    public NouvelleImmatriculation numeroCss(String numeroCss) {
        this.numeroCss = numeroCss;
        return this;
    }

    public void setNumeroCss(String numeroCss) {
        this.numeroCss = numeroCss;
    }

    public String getNumeroIpres() {
        return numeroIpres;
    }

    public NouvelleImmatriculation numeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
        return this;
    }

    public void setNumeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public Long getUser() {
        return user;
    }

    public NouvelleImmatriculation user(Long user) {
        this.user = user;
        return this;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Boolean isIsSubmit() {
        return isSubmit;
    }

    public NouvelleImmatriculation isSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
        return this;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NouvelleImmatriculation createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmployerRegistrationFormId() {
        return employerRegistrationFormId;
    }

    public NouvelleImmatriculation employerRegistrationFormId(String employerRegistrationFormId) {
        this.employerRegistrationFormId = employerRegistrationFormId;
        return this;
    }

    public void setEmployerRegistrationFormId(String employerRegistrationFormId) {
        this.employerRegistrationFormId = employerRegistrationFormId;
    }

    public String getEmployeeRegistrationFormId() {
        return employeeRegistrationFormId;
    }

    public NouvelleImmatriculation employeeRegistrationFormId(String employeeRegistrationFormId) {
        this.employeeRegistrationFormId = employeeRegistrationFormId;
        return this;
    }

    public void setEmployeeRegistrationFormId(String employeeRegistrationFormId) {
        this.employeeRegistrationFormId = employeeRegistrationFormId;
    }

    public String getZoneCss() {
        return zoneCss;
    }

    public NouvelleImmatriculation zoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
        return this;
    }

    public void setZoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
    }

    public String getSectorCss() {
        return sectorCss;
    }

    public NouvelleImmatriculation sectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
        return this;
    }

    public void setSectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
    }

    public String getZoneIpres() {
        return zoneIpres;
    }

    public NouvelleImmatriculation zoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
        return this;
    }

    public void setZoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
    }

    public String getSectorIpres() {
        return sectorIpres;
    }

    public NouvelleImmatriculation sectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
        return this;
    }

    public void setSectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
    }

    public String getAgenceCss() {
        return agenceCss;
    }

    public NouvelleImmatriculation agenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
        return this;
    }

    public void setAgenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
    }

    public String getAgenceIpres() {
        return agenceIpres;
    }

    public NouvelleImmatriculation agenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
        return this;
    }

    public void setAgenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public String getTauxAt() {
        return tauxAt;
    }

    public NouvelleImmatriculation tauxAt(String tauxAt) {
        this.tauxAt = tauxAt;
        return this;
    }

    public void setTauxAt(String tauxAt) {
        this.tauxAt = tauxAt;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public NouvelleImmatriculation raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public StatusDocP getStatusDocP() {
        return statusDocP;
    }

    public void setStatusDocP(StatusDocP statusDocP) {
        this.statusDocP = statusDocP;
    }

    public Boolean getSubmit() {
        return isSubmit;
    }

    public void setSubmit(Boolean submit) {
        isSubmit = submit;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public NouvelleImmatriculation registreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
        return this;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NouvelleImmatriculation)) {
            return false;
        }
        return id != null && id.equals(((NouvelleImmatriculation) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, numdoc, statusdoc, statusdesc, payload, typeIdentifiant, ninea, numeroUnique, numeroCss, numeroIpres, user, isSubmit, createdAt, employerRegistrationFormId, employeeRegistrationFormId, zoneCss, sectorCss, zoneIpres, sectorIpres, agenceCss, agenceIpres, tauxAt, raisonSociale, registreCommerce);
    }

    @Override
    public String toString() {
        return "NouvelleImmatriculation{" +
            "id=" + id +
            ", type='" + type + '\'' +
            ", numdoc='" + numdoc + '\'' +
            ", statusdoc='" + statusdoc + '\'' +
            ", statusdesc='" + statusdesc + '\'' +
            ", typeIdentifiant='" + typeIdentifiant + '\'' +
            ", ninea='" + ninea + '\'' +
            ", numeroUnique='" + numeroUnique + '\'' +
            ", numeroDossier='" + numeroDossier + '\'' +
            ", statusDocP=" + statusDocP +
            ", numeroCss='" + numeroCss + '\'' +
            ", numeroIpres='" + numeroIpres + '\'' +
            ", user=" + user +
            ", isSubmit=" + isSubmit +
            ", createdAt=" + createdAt +
            ", employerRegistrationFormId='" + employerRegistrationFormId + '\'' +
            ", employeeRegistrationFormId='" + employeeRegistrationFormId + '\'' +
            ", zoneCss='" + zoneCss + '\'' +
            ", sectorCss='" + sectorCss + '\'' +
            ", zoneIpres='" + zoneIpres + '\'' +
            ", sectorIpres='" + sectorIpres + '\'' +
            ", agenceCss='" + agenceCss + '\'' +
            ", agenceIpres='" + agenceIpres + '\'' +
            ", tauxAt='" + tauxAt + '\'' +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", registreCommerce='" + registreCommerce + '\'' +
            ", manager=" + manager +
            '}';
    }
}
