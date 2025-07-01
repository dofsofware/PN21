package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.enumeration.StatusDocP;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.NouvelleImmatriculation} entity.
 */
public class NouvelleImmatriculationDTO implements Serializable {

    private Long id;

    @NotNull
    private String type;

    private String numdoc;

    private String statusdoc;

    private String statusdesc;

    @Lob
    private String payload;

    private String typeIdentifiant;

    private String ninea;

    private String numeroUnique;

    private String numeroCss;

    private String numeroIpres;

    private Long user;

    private Boolean isSubmit;

    private Instant createdAt;

    private String employerRegistrationFormId;

    private String employeeRegistrationFormId;

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

    private String numeroDossier;

    private StatusDocP statusDocP;

    @Lob
    private String raisonSociale;

    private String registreCommerce;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getStatusdoc() {
        return statusdoc;
    }

    public void setStatusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
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

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Boolean isIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmployerRegistrationFormId() {
        return employerRegistrationFormId;
    }

    public void setEmployerRegistrationFormId(String employerRegistrationFormId) {
        this.employerRegistrationFormId = employerRegistrationFormId;
    }

    public String getEmployeeRegistrationFormId() {
        return employeeRegistrationFormId;
    }

    public void setEmployeeRegistrationFormId(String employeeRegistrationFormId) {
        this.employeeRegistrationFormId = employeeRegistrationFormId;
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

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public Boolean getSubmit() {
        return isSubmit;
    }

    public void setSubmit(Boolean submit) {
        isSubmit = submit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NouvelleImmatriculationDTO nouvelleImmatriculationDTO = (NouvelleImmatriculationDTO) o;
        if (nouvelleImmatriculationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nouvelleImmatriculationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NouvelleImmatriculationDTO{" +
            "id=" + id +
            ", type='" + type + '\'' +
            ", numdoc='" + numdoc + '\'' +
            ", statusdoc='" + statusdoc + '\'' +
            ", statusdesc='" + statusdesc + '\'' +
            ", payload='" + payload + '\'' +
            ", typeIdentifiant='" + typeIdentifiant + '\'' +
            ", ninea='" + ninea + '\'' +
            ", numeroUnique='" + numeroUnique + '\'' +
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
            ", numeroDossier='" + numeroDossier + '\'' +
            ", statusDocP=" + statusDocP +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", registreCommerce='" + registreCommerce + '\'' +
            '}';
    }
}
