package com.secusociale.portail.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.service.SecurityService;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND;
import com.secusociale.portail.service.soap.domestique.InboundDomFrm;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUND;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAI;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUND;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Localimmatriculation} entity.
 */
public class LocalimmatriculationDTO implements Serializable {

    private Long id;

    @JsonProperty("typeImmatriculation")
    private String type;

    private String numdoc;

    private String statusdoc;

    private String statusdesc;

    @Lob
    private String payload;

    private Long user;

    private Long employeur;

    @NotNull
    private Boolean isSubmit;

    @JsonProperty("numeroUnique")
    private String ninea;

    @JsonProperty("typeIdentifiant")
    private String typeIdentifiant;

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public LocalimmatriculationDTO typeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
        return this;
    }

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

    public void setType(String type) {
        this.type = type;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public LocalimmatriculationDTO numdoc(String numdoc) {
        this.numdoc = numdoc;
        return this;
    }

    public String getStatusdoc() {
        return statusdoc;
    }

    public void setStatusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
    }

    public LocalimmatriculationDTO statusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
        return this;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public void setPayload(String payload) {
        try {
            this.payload = SecurityService.encrypText(payload);
        } catch (Exception e) {
            e.printStackTrace();
            this.payload = payload;
        }
    }

    public LocalimmatriculationDTO payload(String payload) {
        this.payload = payload;
        return this;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public LocalimmatriculationDTO user(Long user) {
        this.user = user;
        return this;
    }

    public Long getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Long employeur) {
        this.employeur = employeur;
    }

    public LocalimmatriculationDTO employeur(Long employeur) {
        this.employeur = employeur;
        return this;
    }

    public Boolean isIsSubmited() {
        return isSubmit;
    }

    public LocalimmatriculationDTO isSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
        return this;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public LocalimmatriculationDTO ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    @Override
    public String toString() {
        return "LocalimmatriculationDTO{" +
            "id=" + getId() +
            ", numdoc='" + getNumdoc() + "'" +
            ", statusdoc='" + getStatusdoc() + "'" +
            ", statusdesc='" + getStatusdesc() + "'" +
            ", user=" + getUser() +
            ", employeur=" + getEmployeur() +
            ", isSubmited='" + isIsSubmited() + "'" +
            ", ninea='" + getNinea() + "'" +
            "}";
    }
}
