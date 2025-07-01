package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.service.dto.custom.AgentDTO;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.ImmatriculationRecuperee} entity.
 */
public class ImmatriculationRecupereeDTO implements Serializable {

    private Long id;

    private String moyenConfirmation;

    private String status;

    private String statusdesc;

    @Lob
    private String payload;

    @Lob
    private String extrasInfo;

    private String typeIdentifiant;

    private String numeroIdentifiant;

    private String ninea;

    private String numeroUnique;

    private String numeroCss;

    private String numeroIpres;

    private Long userId;

    private Long agentId;

    private Instant createdAt;

    private Instant dateTraitement;

    private Instant date;

    @Lob
    private String zoneCss;

    @Lob
    private String sectorCss;

    private String zoneIpres;

    @Lob
    private String sectorIpres;

    private String agenceCss;

    private String agenceIpres;

    private String tauxAt;

    @Lob
    private String raisonSociale;

    @Lob
    private String mandatFile;

    @Lob
    private String motif;

    @Lob
    private String rcFile;

    @Lob
    private String nineaFile;

    private String registreCommerce;

    /**
     * Extras Fields
     *
     */
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

    public String getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public void setMoyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getExtrasInfo() {
        return extrasInfo;
    }

    public void setExtrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
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

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getNumeroCss() {
        return numeroCss;
    }

    public void setNumeroCss(String numeroCss) {
        this.numeroCss = numeroCss;
    }

    public String getNumeroIpres() {
        return numeroIpres;
    }

    public void setNumeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getZoneCss() {
        return zoneCss;
    }

    public void setZoneCss(String zoneCss) {
        this.zoneCss = zoneCss;
    }

    public String getSectorCss() {
        return sectorCss;
    }

    public void setSectorCss(String sectorCss) {
        this.sectorCss = sectorCss;
    }

    public String getZoneIpres() {
        return zoneIpres;
    }

    public void setZoneIpres(String zoneIpres) {
        this.zoneIpres = zoneIpres;
    }

    public String getSectorIpres() {
        return sectorIpres;
    }

    public void setSectorIpres(String sectorIpres) {
        this.sectorIpres = sectorIpres;
    }

    public String getAgenceCss() {
        return agenceCss;
    }

    public void setAgenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
    }

    public String getAgenceIpres() {
        return agenceIpres;
    }

    public void setAgenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public String getTauxAt() {
        return tauxAt;
    }

    public void setTauxAt(String tauxAt) {
        this.tauxAt = tauxAt;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getRcFile() {
        return rcFile;
    }

    public void setRcFile(String rcFile) {
        this.rcFile = rcFile;
    }

    public String getNineaFile() {
        return nineaFile;
    }

    public void setNineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImmatriculationRecupereeDTO immatriculationRecupereeDTO = (ImmatriculationRecupereeDTO) o;
        if (immatriculationRecupereeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), immatriculationRecupereeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImmatriculationRecupereeDTO{" +
            "id=" + getId() +
            ", moyenConfirmation='" + getMoyenConfirmation() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusdesc='" + getStatusdesc() + "'" +
            ", payload='" + getPayload() + "'" +
            ", extrasInfo='" + getExtrasInfo() + "'" +
            ", typeIdentifiant='" + getTypeIdentifiant() + "'" +
            ", numeroIdentifiant='" + getNumeroIdentifiant() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", numeroCss='" + getNumeroCss() + "'" +
            ", numeroIpres='" + getNumeroIpres() + "'" +
            ", userId=" + getUserId() +
            ", agentId=" + getAgentId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", dateTraitement='" + getDateTraitement() + "'" +
            ", date='" + getDate() + "'" +
            ", zoneCss='" + getZoneCss() + "'" +
            ", sectorCss='" + getSectorCss() + "'" +
            ", zoneIpres='" + getZoneIpres() + "'" +
            ", sectorIpres='" + getSectorIpres() + "'" +
            ", agenceCss='" + getAgenceCss() + "'" +
            ", agenceIpres='" + getAgenceIpres() + "'" +
            ", tauxAt='" + getTauxAt() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", mandatFile='" + getMandatFile() + "'" +
            ", motif='" + getMotif() + "'" +
            ", rcFile='" + getRcFile() + "'" +
            ", nineaFile='" + getNineaFile() + "'" +
            ", registreCommerce='" + getRegistreCommerce() + "'" +
            "}";
    }
}
