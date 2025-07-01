package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SuiviJob.
 */
@Entity
@Table(name = "suivi_job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SuiviJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "statut")
    private String statut;

    @Column(name = "demarre_le")
    private Instant demarreLe;

    @Column(name = "termine_le")
    private Instant termineLe;

    @Column(name = "demarre_par")
    private String demarrePar;

    @Lob
    @Column(name = "erreurs")
    private String erreurs;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public SuiviJob nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public SuiviJob description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public SuiviJob statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getDemarreLe() {
        return demarreLe;
    }

    public SuiviJob demarreLe(Instant demarreLe) {
        this.demarreLe = demarreLe;
        return this;
    }

    public void setDemarreLe(Instant demarreLe) {
        this.demarreLe = demarreLe;
    }

    public Instant getTermineLe() {
        return termineLe;
    }

    public SuiviJob termineLe(Instant termineLe) {
        this.termineLe = termineLe;
        return this;
    }

    public void setTermineLe(Instant termineLe) {
        this.termineLe = termineLe;
    }

    public String getDemarrePar() {
        return demarrePar;
    }

    public SuiviJob demarrePar(String demarrePar) {
        this.demarrePar = demarrePar;
        return this;
    }

    public void setDemarrePar(String demarrePar) {
        this.demarrePar = demarrePar;
    }

    public String getErreurs() {
        return erreurs;
    }

    public SuiviJob erreurs(String erreurs) {
        this.erreurs = erreurs;
        return this;
    }

    public void setErreurs(String erreurs) {
        this.erreurs = erreurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuiviJob)) {
            return false;
        }
        return id != null && id.equals(((SuiviJob) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SuiviJob{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", statut='" + getStatut() + "'" +
            ", demarreLe='" + getDemarreLe() + "'" +
            ", termineLe='" + getTermineLe() + "'" +
            ", demarrePar='" + getDemarrePar() + "'" +
            ", erreurs='" + getErreurs() + "'" +
            "}";
    }
}
