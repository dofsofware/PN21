package com.secusociale.portail.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.GedUpdate} entity.
 */
public class GedUpdateDTO implements Serializable {

    private Long id;

    private String oldPath;

    private String newPath;

    private Long idOld;

    private String numeroUnique;

    private Instant date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public Long getIdOld() {
        return idOld;
    }

    public void setIdOld(Long idOld) {
        this.idOld = idOld;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GedUpdateDTO gedUpdateDTO = (GedUpdateDTO) o;
        if (gedUpdateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gedUpdateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GedUpdateDTO{" +
            "id=" + getId() +
            ", oldPath='" + getOldPath() + "'" +
            ", newPath='" + getNewPath() + "'" +
            ", idOld=" + getIdOld() +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
