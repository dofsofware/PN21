package com.secusociale.portail.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;

public class RepresentationDiplomatiqueDTO extends IMMATREPDIPLO {
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
