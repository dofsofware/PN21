package com.secusociale.portail.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.DemandeServiceSalarie} entity.
 */
public class UpdateDemandeServiceSalarieDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String statut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UpdateDemandeServiceSalarieDTO demandeServiceSalarieDTO = (UpdateDemandeServiceSalarieDTO) o;
        if (demandeServiceSalarieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeServiceSalarieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandeServiceSalarieDTO{" +
            "id=" + getId() +
            ", statut='" + getStatut() + "'" +
            "}";
    }
}
