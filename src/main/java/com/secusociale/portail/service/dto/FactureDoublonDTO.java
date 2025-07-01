package com.secusociale.portail.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class FactureDoublonDTO implements Serializable {

    @JsonProperty("TAX_FORM_ID")
    private String numeroFacture;

    @JsonProperty("PER_ID")
    private String numeroUnique;

    @JsonProperty("START_DT")
    private Date debutPeriode;

    @JsonProperty("END_DT")
    private Date finPeriode;

    @JsonProperty("MONTANT_DETTE_TOTAL")
    private Long montantTotal;

    @JsonProperty("PAYED")
    private String paye;

    @JsonProperty("C1_FORM_SRCE_CD")
    private String sourceFormulaire;

    @JsonProperty("IPRES_AGENCE_CD")
    private String agenceIpres;

    @JsonProperty("CSS_AGENCE_CD")
    private String agenceCss;

    @JsonProperty("FORM_TYPE_CD")
    private String typeFormulaire;

    @JsonProperty("MONTANT_MAJORATION")
    private Long montantMajoration;

    @JsonProperty("MONTANT_PENALITE")
    private Long montantPenalite;

    @JsonProperty("MONTANT_DETTE_PRINCIPAL")
    private Long montantPrincipal;

    @JsonProperty("ACCT_ID")
    private String numeroCompte;

    @JsonProperty("LAST_LOG_CRE_DTTM")
    private Date dateDernierLog;

    @JsonProperty("MONTANT_DETTE_TOTAL_RC")
    private Long montantRC;

    @JsonProperty("MONTANT_DETTE_TOTAL_CSS")
    private Long montantCSS;

    @JsonProperty("LAST_TFC_ID_LOG")
    private Long dernierIdLog;

    @JsonProperty("GRACE_DT")
    private Date dateEcheance;

    @JsonProperty("MONTANT_PAYER")
    private Long montantPaye;

    @JsonProperty("PERIODE_DESCR")
    private String periodeDescription;

    @JsonProperty("MONTANT_DETTE_TOTAL_IPRES")
    private Long montantIpres;

    @JsonProperty("MONTANT_DETTE_TOTAL_PF")
    private Long montantPF;

    @JsonProperty("MONTANT_DETTE_TOTAL_RG")
    private Long montantRG;

    @JsonProperty("MONTANT_DETTE_TOTAL_AT")
    private Long montantAT;

    private String source;

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Date getDebutPeriode() {
        return debutPeriode;
    }

    public void setDebutPeriode(Date debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public Date getFinPeriode() {
        return finPeriode;
    }

    public void setFinPeriode(Date finPeriode) {
        this.finPeriode = finPeriode;
    }

    public Long getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Long montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getPaye() {
        return paye;
    }

    public void setPaye(String paye) {
        this.paye = paye;
    }

    public String getSourceFormulaire() {
        return sourceFormulaire;
    }

    public void setSourceFormulaire(String sourceFormulaire) {
        this.sourceFormulaire = sourceFormulaire;
    }

    public String getAgenceIpres() {
        return agenceIpres;
    }

    public void setAgenceIpres(String agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public String getAgenceCss() {
        return agenceCss;
    }

    public void setAgenceCss(String agenceCss) {
        this.agenceCss = agenceCss;
    }

    public String getTypeFormulaire() {
        return typeFormulaire;
    }

    public void setTypeFormulaire(String typeFormulaire) {
        this.typeFormulaire = typeFormulaire;
    }

    public Long getMontantMajoration() {
        return montantMajoration;
    }

    public void setMontantMajoration(Long montantMajoration) {
        this.montantMajoration = montantMajoration;
    }

    public Long getMontantPenalite() {
        return montantPenalite;
    }

    public void setMontantPenalite(Long montantPenalite) {
        this.montantPenalite = montantPenalite;
    }

    public Long getMontantPrincipal() {
        return montantPrincipal;
    }

    public void setMontantPrincipal(Long montantPrincipal) {
        this.montantPrincipal = montantPrincipal;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Date getDateDernierLog() {
        return dateDernierLog;
    }

    public void setDateDernierLog(Date dateDernierLog) {
        this.dateDernierLog = dateDernierLog;
    }

    public Long getMontantRC() {
        return montantRC;
    }

    public void setMontantRC(Long montantRC) {
        this.montantRC = montantRC;
    }

    public Long getMontantCSS() {
        return montantCSS;
    }

    public void setMontantCSS(Long montantCSS) {
        this.montantCSS = montantCSS;
    }

    public Long getDernierIdLog() {
        return dernierIdLog;
    }

    public void setDernierIdLog(Long dernierIdLog) {
        this.dernierIdLog = dernierIdLog;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Long getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Long montantPaye) {
        this.montantPaye = montantPaye;
    }

    public String getPeriodeDescription() {
        return periodeDescription;
    }

    public void setPeriodeDescription(String periodeDescription) {
        this.periodeDescription = periodeDescription;
    }

    public Long getMontantIpres() {
        return montantIpres;
    }

    public void setMontantIpres(Long montantIpres) {
        this.montantIpres = montantIpres;
    }

    public Long getMontantPF() {
        return montantPF;
    }

    public void setMontantPF(Long montantPF) {
        this.montantPF = montantPF;
    }

    public Long getMontantRG() {
        return montantRG;
    }

    public void setMontantRG(Long montantRG) {
        this.montantRG = montantRG;
    }

    public Long getMontantAT() {
        return montantAT;
    }

    public void setMontantAT(Long montantAT) {
        this.montantAT = montantAT;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
