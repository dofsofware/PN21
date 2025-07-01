package com.secusociale.portail.service.dto;

public class FactureIndividuelleDTO {
    private double totalSalVerses;
    private double cumulTotSalAssCssAtmp;
    private double cumulTotSalAssCssPf;
    private double cumulTotSalAssIpresRcc;
    private double cumulTotSalAssIpresRg;
    private String numeroUnique;
    private String raisonSociale;


    public double getTotalSalVerses() {
        return totalSalVerses;
    }

    public void setTotalSalVerses(double totalSalVerses) {
        this.totalSalVerses = totalSalVerses;
    }

    public double getCumulTotSalAssCssAtmp() {
        return cumulTotSalAssCssAtmp;
    }

    public void setCumulTotSalAssCssAtmp(double cumulTotSalAssCssAtmp) {
        this.cumulTotSalAssCssAtmp = cumulTotSalAssCssAtmp;
    }

    public double getCumulTotSalAssCssPf() {
        return cumulTotSalAssCssPf;
    }

    public void setCumulTotSalAssCssPf(double cumulTotSalAssCssPf) {
        this.cumulTotSalAssCssPf = cumulTotSalAssCssPf;
    }

    public double getCumulTotSalAssIpresRcc() {
        return cumulTotSalAssIpresRcc;
    }

    public void setCumulTotSalAssIpresRcc(double cumulTotSalAssIpresRcc) {
        this.cumulTotSalAssIpresRcc = cumulTotSalAssIpresRcc;
    }

    public double getCumulTotSalAssIpresRg() {
        return cumulTotSalAssIpresRg;
    }

    public void setCumulTotSalAssIpresRg(double cumulTotSalAssIpresRg) {
        this.cumulTotSalAssIpresRg = cumulTotSalAssIpresRg;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

}
