package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.enumeration.TypeOperation;

public class ValidationImmatriculationDTO {
    private String typeOperation;

    private String motif;

    private String immatriculationId;

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getImmatriculationId() {
        return immatriculationId;
    }

    public void setImmatriculationId(String immatriculationId) {
        this.immatriculationId = immatriculationId;
    }
}
