package com.secusociale.portail.service.dto;

import com.secusociale.portail.service.dto.custom.AncienneImmatriculationDTO;

import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

public class GedDTO implements Serializable {
    //id, raisonSociale sociale, numéro css, numéro ipress, numéro unique, date, agence caisse, agence ipress, ninéa, mandat
    private Long id;
    private String numeroUnique;
    @Lob
    private String mandat;

    public GedDTO(AncienneImmatriculationDTO ai) {
        setId(ai.getId());
        setNumeroUnique(ai.getNumeroUnique());
        setMandat(ai.getMandatFile());
    }

    public GedDTO() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getMandat() {
        return mandat;
    }

    public void setMandat(String mandat) {
        this.mandat = mandat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GedDTO gedDTO = (GedDTO) o;
        if (gedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GedDTO{" +
            "id=" + id +
            ", numeroUnique='" + numeroUnique + '\'' +
            ", mandat='" + mandat + '\'' +
            '}';
    }
}
