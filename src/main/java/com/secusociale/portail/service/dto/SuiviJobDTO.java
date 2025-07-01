package com.secusociale.portail.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.SuiviJob} entity.
 */
public class SuiviJobDTO implements Serializable {

    private Long id;

    private String nom;

    @Lob
    private String description;

    private String statut;

    private Instant demarreLe;

    private Instant termineLe;

    private String demarrePar;

    @Lob
    private String erreurs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getDemarreLe() {
        return demarreLe;
    }

    public void setDemarreLe(Instant demarreLe) {
        this.demarreLe = demarreLe;
    }

    public Instant getTermineLe() {
        return termineLe;
    }

    public void setTermineLe(Instant termineLe) {
        this.termineLe = termineLe;
    }

    public String getDemarrePar() {
        return demarrePar;
    }

    public void setDemarrePar(String demarrePar) {
        this.demarrePar = demarrePar;
    }

    public String getErreurs() {
        return erreurs;
    }

    public void setErreurs(String erreurs) {
        this.erreurs = erreurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SuiviJobDTO suiviJobDTO = (SuiviJobDTO) o;
        if (suiviJobDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suiviJobDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuiviJobDTO{" +
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
