package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DMT.
 */
@Entity
@Table(name = "dmt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DMT implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_employeur")
    private String idEmployeur;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Instant date;

    @Column(name = "user_id")
    private Long userId;

    @Lob
    @Column(name = "file")
    private String file;

    @Lob
    @Column(name = "reponsebrute")
    private String reponsebrute;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Lob
    @Column(name = "autre_infos")
    private String autreInfos;

    @Lob
    @Column(name = "employes_list")
    private String employesList;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEmployeur() {
        return idEmployeur;
    }

    public DMT idEmployeur(String idEmployeur) {
        this.idEmployeur = idEmployeur;
        return this;
    }

    public void setIdEmployeur(String idEmployeur) {
        this.idEmployeur = idEmployeur;
    }

    public String getStatus() {
        return status;
    }

    public DMT status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getDate() {
        return date;
    }

    public DMT date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public DMT userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFile() {
        return file;
    }

    public DMT file(String file) {
        this.file = file;
        return this;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getReponsebrute() {
        return reponsebrute;
    }

    public DMT reponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
        return this;
    }

    public void setReponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public DMT raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getAutreInfos() {
        return autreInfos;
    }

    public DMT autreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
        return this;
    }

    public void setAutreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
    }

    public String getEmployesList() {
        return employesList;
    }

    public DMT employesList(String employesList) {
        this.employesList = employesList;
        return this;
    }

    public void setEmployesList(String employesList) {
        this.employesList = employesList;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DMT)) {
            return false;
        }
        return id != null && id.equals(((DMT) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DMT{" +
            "id=" + getId() +
            ", idEmployeur='" + getIdEmployeur() + "'" +
            ", status='" + getStatus() + "'" +
            ", date='" + getDate() + "'" +
            ", userId=" + getUserId() +
            ", file='" + getFile() + "'" +
            ", reponsebrute='" + getReponsebrute() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", autreInfos='" + getAutreInfos() + "'" +
            ", employesList='" + getEmployesList() + "'" +
            "}";
    }
}
