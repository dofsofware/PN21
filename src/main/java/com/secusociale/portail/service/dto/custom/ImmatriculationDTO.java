package com.secusociale.portail.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND;

import java.io.Serializable;

public class ImmatriculationDTO extends IMMATRICULATIONINBOUND {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isSubmit;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    public boolean getIsSubmit() {
        return this.isSubmit;
    }

    public void setIsSubmit(boolean submit) {
        isSubmit = submit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
