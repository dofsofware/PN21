package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DeclarationManquante.
 */
@Entity
@Table(name = "declaration_manquante")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeclarationManquante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "payload")
    private String payload;

    @Column(name = "date_upload")
    private LocalDate dateUpload;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private Boolean status;

    @Lob
    @Column(name = "file_join")
    private String fileJoin;

    @Column(name = "person_id")
    private String personId;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Column(name = "ancien_numero_ipres")
    private String ancienNumeroIpres;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "regime")
    private String regime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public DeclarationManquante payload(String payload) {
        this.payload = payload;
        return this;
    }

    public LocalDate getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(LocalDate dateUpload) {
        this.dateUpload = dateUpload;
    }

    public DeclarationManquante dateUpload(LocalDate dateUpload) {
        this.dateUpload = dateUpload;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DeclarationManquante userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Boolean isStatus() {
        return status;
    }

    public DeclarationManquante status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFileJoin() {
        return fileJoin;
    }

    public void setFileJoin(String fileJoin) {
        this.fileJoin = fileJoin;
    }

    public DeclarationManquante fileJoin(String fileJoin) {
        this.fileJoin = fileJoin;
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public DeclarationManquante personId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public DeclarationManquante raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public String getAncienNumeroIpres() {
        return ancienNumeroIpres;
    }

    public void setAncienNumeroIpres(String ancienNumeroIpres) {
        this.ancienNumeroIpres = ancienNumeroIpres;
    }

    public DeclarationManquante ancienNumeroIpres(String ancienNumeroIpres) {
        this.ancienNumeroIpres = ancienNumeroIpres;
        return this;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public DeclarationManquante annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public DeclarationManquante regime(String regime) {
        this.regime = regime;
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeclarationManquante)) {
            return false;
        }
        return id != null && id.equals(((DeclarationManquante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeclarationManquante{" +
            "id=" + getId() +
            ", payload='" + getPayload() + "'" +
            ", dateUpload='" + getDateUpload() + "'" +
            ", userId=" + getUserId() +
            ", status='" + isStatus() + "'" +
            ", fileJoin='" + getFileJoin() + "'" +
            ", personId='" + getPersonId() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", ancienNumeroIpres='" + getAncienNumeroIpres() + "'" +
            ", annee=" + getAnnee() +
            ", regime='" + getRegime() + "'" +
            "}";
    }
}
