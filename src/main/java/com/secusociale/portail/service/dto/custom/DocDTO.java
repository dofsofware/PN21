package com.secusociale.portail.service.dto.custom;

import javax.persistence.Lob;

public class DocDTO {
    private String nom;
    @Lob
    private String doc;
    @Lob
    private String signe;
    private Boolean signed;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getSigne() {
        return signe;
    }

    public void setSigne(String signe) {
        this.signe = signe;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }
}

