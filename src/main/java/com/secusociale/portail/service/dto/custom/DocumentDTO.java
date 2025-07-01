package com.secusociale.portail.service.dto.custom;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class DocumentDTO {
    @NotNull
    private String nom;
    @Lob
    @NotNull
    private String base64;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}

