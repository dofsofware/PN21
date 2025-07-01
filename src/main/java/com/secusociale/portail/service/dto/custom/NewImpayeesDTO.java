package com.secusociale.portail.service.dto.custom;

import com.secusociale.portail.service.soap.newfactureimpayees.CMPAYDNSXAI;

import java.math.BigDecimal;

public class NewImpayeesDTO {
    private Boolean chkBox;
    private BigDecimal nbrLigne;
    private String numeroFacture;
    private String typeFacture;
    private String dateEcheance;
    private BigDecimal montantPrincipal;
    private BigDecimal majorations;
    private BigDecimal montantTotal;
    private String dateDebut;
    private String dateFin;
    private BigDecimal dette;
    private BigDecimal montantPaye;
    private BigDecimal penalite;
    private BigDecimal montantVerse;
    private String dateDebut2;
    private BigDecimal detteinput;
    private String raisonSociale;
    private String status;

    public Boolean getChkBox() {
        return chkBox;
    }

    public void setChkBox(Boolean chkBox) {
        this.chkBox = chkBox;
    }

    public BigDecimal getNbrLigne() {
        return nbrLigne;
    }

    public void setNbrLigne(BigDecimal nbrLigne) {
        this.nbrLigne = nbrLigne;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public String getTypeFacture() {
        return typeFacture;
    }

    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }

    public String getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(String dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public BigDecimal getMontantPrincipal() {
        return montantPrincipal;
    }

    public void setMontantPrincipal(BigDecimal montantPrincipal) {
        this.montantPrincipal = montantPrincipal;
    }

    public BigDecimal getMajorations() {
        return majorations;
    }

    public void setMajorations(BigDecimal majorations) {
        this.majorations = majorations;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getDette() {
        return dette;
    }

    public void setDette(BigDecimal dette) {
        this.dette = dette;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimal getPenalite() {
        return penalite;
    }

    public void setPenalite(BigDecimal penalite) {
        this.penalite = penalite;
    }

    public BigDecimal getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(BigDecimal montantVerse) {
        this.montantVerse = montantVerse;
    }

    public String getDateDebut2() {
        return dateDebut2;
    }

    public void setDateDebut2(String dateDebut2) {
        this.dateDebut2 = dateDebut2;
    }

    public BigDecimal getDetteinput() {
        return detteinput;
    }

    public void setDetteinput(BigDecimal detteinput) {
        this.detteinput = detteinput;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NewImpayeesDTO() {
    }

    public NewImpayeesDTO(CMPAYDNSXAI.Results impayee, String status) {
        this.chkBox = impayee.getChkBox().getValue();
        this.nbrLigne = impayee.getNbrLigne();
        this.numeroFacture = impayee.getNumeroFacture();
        this.typeFacture = impayee.getTypeFacture();
        this.dateEcheance = impayee.getDateEcheance();
        this.montantPrincipal = impayee.getMontantPrincipal();
        this.majorations = impayee.getMajorations();
        this.montantTotal = impayee.getMontantTotal();
        this.dateDebut = impayee.getDateDebut();
        this.dateFin = impayee.getDateFin();
        this.dette = impayee.getDette();
        this.montantPaye = impayee.getMontantPaye();
        this.penalite = impayee.getPenalite();
        this.montantVerse = impayee.getMontantVerse();
        this.dateDebut2 = impayee.getDateDebut2();
        this.detteinput = impayee.getDetteinput();
        this.raisonSociale = impayee.getRaisonSociale();
        this.status = status;
    }
}
