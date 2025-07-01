package com.secusociale.portail.service.dto.custom;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.User;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;

public class AncienneImmatriculationDTO implements Serializable {

    private Long id;

    private String typeIdentifiant;

    private String numeroIdentifiant;

    @Lob
    private String nineaFile;

    @Lob
    private String rcFile;

    @Lob
    private String motif;

    @Lob
    private String mandatFile;

    @Lob
    private String extrasInfo;

    private String status;

    private String moyenConfirmation;

    private Instant date;

    private String numeroRC;

    private Long userId;

    private String numeroUnique;

    private String agenceCSS;

    private String agenceIPRES;

    private Long agentId;

    private Instant dateTraitement;

    private User user;
    private Agence agenceCSSObject;
    private Agence agenceIPRESObject;
    private AgentDTO agent;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public String getNineaFile() {
        return nineaFile;
    }

    public void setNineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
    }

    public String getRcFile() {
        return rcFile;
    }

    public void setRcFile(String rcFile) {
        this.rcFile = rcFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public void setMoyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getNumeroRC() {
        return numeroRC;
    }

    public void setNumeroRC(String numeroRC) {
        this.numeroRC = numeroRC;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getAgenceIPRES() {
        return agenceIPRES;
    }

    public void setAgenceIPRES(String agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
    }

    public String getAgenceCSS() {
        return agenceCSS;
    }

    public void setAgenceCSS(String agenceCSS) {
        this.agenceCSS = agenceCSS;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public Agence getAgenceCSSObject() {
        return agenceCSSObject;
    }

    public void setAgenceCSSObject(Agence agenceCSSObject) {
        this.agenceCSSObject = agenceCSSObject;
    }

    public Agence getAgenceIPRESObject() {
        return agenceIPRESObject;
    }

    public void setAgenceIPRESObject(Agence agenceIPRESObject) {
        this.agenceIPRESObject = agenceIPRESObject;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getExtrasInfo() {
        return extrasInfo;
    }

    public void setExtrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
    }
}
