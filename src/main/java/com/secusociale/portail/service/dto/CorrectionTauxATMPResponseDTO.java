package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.util.List;


public class CorrectionTauxATMPResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> facturesCorrigees;
    private int nombreFacturesCorrigees;
    private int nombreFacturesTotal;
    private List<String> erreurs;

    public List<String> getFacturesCorrigees() {
        return facturesCorrigees;
    }

    public void setFacturesCorrigees(List<String> facturesCorrigees) {
        this.facturesCorrigees = facturesCorrigees;
    }

    public int getNombreFacturesCorrigees() {
        return nombreFacturesCorrigees;
    }

    public void setNombreFacturesCorrigees(int nombreFacturesCorrigees) {
        this.nombreFacturesCorrigees = nombreFacturesCorrigees;
    }

    public int getNombreFacturesTotal() {
        return nombreFacturesTotal;
    }

    public void setNombreFacturesTotal(int nombreFacturesTotal) {
        this.nombreFacturesTotal = nombreFacturesTotal;
    }

    public List<String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(List<String> erreurs) {
        this.erreurs = erreurs;
    }
}
