package com.secusociale.portail.web.rest.vm;

import com.secusociale.portail.service.soap.CmFindEmployeurByTypeId.CmFindEmployeurByTypeId.*;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILS;

/**
 * View Model object for storing the CheckUser's type and value.
 */
public class CheckResponse {

    private String code;
    private String numeroUnique;
    private String typeIdentifiant;
    private String numeroIdentifiant;
    private Boolean exists;
    private String status;

    public Output.Employeurs getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Output.Employeurs employeur) {
        this.employeur = employeur;
    }

    private Output.Employeurs employeur;

    private String extras;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public String getStatus() {
        return status;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
