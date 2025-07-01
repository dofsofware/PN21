package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GedUpdate.
 */
@Entity
@Table(name = "ged_update")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GedUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_path")
    private String oldPath;

    @Column(name = "new_path")
    private String newPath;

    @Column(name = "id_old")
    private Long idOld;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "date")
    private Instant date;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPath() {
        return oldPath;
    }

    public GedUpdate oldPath(String oldPath) {
        this.oldPath = oldPath;
        return this;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getNewPath() {
        return newPath;
    }

    public GedUpdate newPath(String newPath) {
        this.newPath = newPath;
        return this;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public Long getIdOld() {
        return idOld;
    }

    public GedUpdate idOld(Long idOld) {
        this.idOld = idOld;
        return this;
    }

    public void setIdOld(Long idOld) {
        this.idOld = idOld;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public GedUpdate numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Instant getDate() {
        return date;
    }

    public GedUpdate date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GedUpdate)) {
            return false;
        }
        return id != null && id.equals(((GedUpdate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GedUpdate{" +
            "id=" + getId() +
            ", oldPath='" + getOldPath() + "'" +
            ", newPath='" + getNewPath() + "'" +
            ", idOld=" + getIdOld() +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
