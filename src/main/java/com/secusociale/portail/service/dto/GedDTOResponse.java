package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class GedDTOResponse implements Serializable {
    //id, raisonSociale sociale, numéro css, numéro ipress, numéro unique, date, agence caisse, agence ipress, ninéa, mandat

    private String code;
    private String message;
    private String erreur;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GedDTOResponse that = (GedDTOResponse) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(erreur, that.erreur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, erreur);
    }

    @Override
    public String toString() {
        return "GedDTOResponse{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", erreur='" + erreur + '\'' +
            '}';
    }

    public GedDTOResponse(HashMap<String, String> source) {
        setCode(source.get("code"));
        setMessage(source.get("message"));
        setErreur(source.get("erreur"));
    }

    public GedDTOResponse() {

    }
}
