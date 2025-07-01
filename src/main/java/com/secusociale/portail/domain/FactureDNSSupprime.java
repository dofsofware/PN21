package com.secusociale.portail.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Entity
public class FactureDNSSupprime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Column(name = "numero_facture", nullable = false, unique = true)
    private String numeroFacture;

    @NotNull
    @Column(name = "id_declaration", nullable = false)
    private Long idDeclaration;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "agence_ipres")
    private String agenceIpres;

    @Column(name = "agence_css")
    private String agenceCss;

    @Column(name = "debut_periode")
    private Instant debutPeriode;

    @Column(name = "fin_periode")
    private Instant finPeriode;

    @Column(name = "date_reception")
    private Instant dateReception;

    @Column(name = "vieillesse_rg")
    private Double vieillesseRG;

    @Column(name = "vieillesse_rc")
    private Double vieillesseRC;

    @Column(name = "taux_rg")
    private Float tauxRG;

    @Column(name = "taux_rc")
    private Float tauxRC;

    @Column(name = "mt_vieillesse_rg")
    private Double mtVieillesseRG;

    @Column(name = "mt_vieillesse_rc")
    private Double mtVieillesseRC;

    @Column(name = "mt_majoration_css")
    private Double mtMajorationCss;

    @Column(name = "prestation_fam")
    private Double prestationFam;

    @Column(name = "taux_prestation_fam")
    private Float tauxPrestationFam;

    @Column(name = "mt_prestation_fam")
    private Double mtPrestationFam;

    @Column(name = "atmp")
    private Double atmp;

    @Column(name = "taux_atmp")
    private Float tauxATMP;

    @Column(name = "mt_atmp")
    private Double mtAtmp;

    @Column(name = "total_ipres")
    private Double totalIpres;

    @Column(name = "total_css")
    private Double totalCss;

    @Column(name = "total_a_payer")
    private Double totalAPayer;

    @Lob
    @Column(name = "extra_infos")
    private String extraInfos;

    @Column(name = "mt_majoration_ipres")
    private Double mtMajorationIpres;

    @Column(name = "statut")
    private String statut;

    @Column(name = "echeance")
    private Instant echeance;

    @Column(name = "salaire_declare")
    private BigDecimal salaireDeclare;

    // Constructeur qui prend un objet FactureDNS
    public FactureDNSSupprime(FactureDNS factureDNS, Long idFactureSupprimee) {
        this.id = idFactureSupprimee; // Assignation de l'ID de la facture supprimée
        this.idDeclaration = factureDNS.getIdDeclaration();
        this.numeroFacture = factureDNS.getNumeroFacture();
        this.numeroUnique = factureDNS.getNumeroUnique();
        this.statut = factureDNS.getStatut();
        this.raisonSociale = factureDNS.getRaisonSociale();
        this.adresse = factureDNS.getAdresse();
        this.agenceIpres = factureDNS.getAgenceIpres();
        this.agenceCss = factureDNS.getAgenceCss();
        this.debutPeriode = factureDNS.getDebutPeriode();
        this.finPeriode = factureDNS.getFinPeriode();
        this.dateReception = factureDNS.getDateReception();
        this.vieillesseRG = factureDNS.getVieillesseRG();
        this.vieillesseRC = factureDNS.getVieillesseRC();
        this.tauxRG = factureDNS.getTauxRG();
        this.tauxRC = factureDNS.getTauxRC();
        this.mtVieillesseRG = factureDNS.getMtVieillesseRG();
        this.mtVieillesseRC = factureDNS.getMtVieillesseRC();
        this.mtMajorationCss = factureDNS.getMtMajorationCss();
        this.prestationFam = factureDNS.getPrestationFam();
        this.tauxPrestationFam = factureDNS.getTauxPrestationFam();
        this.mtPrestationFam = factureDNS.getMtPrestationFam();
        this.atmp = factureDNS.getAtmp();
        this.tauxATMP = factureDNS.getTauxATMP();
        this.mtAtmp = factureDNS.getMtAtmp();
        this.totalIpres = factureDNS.getTotalIpres();
        this.totalCss = factureDNS.getTotalCss();
        this.totalAPayer = factureDNS.getTotalAPayer();
        this.mtMajorationIpres = factureDNS.getMtMajorationIpres();
        this.echeance = factureDNS.getEcheance();
        this.salaireDeclare = factureDNS.getSalaireDeclare();
    }
    public FactureDNSSupprime() {
        // Constructeur par défaut
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(Long idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public Instant getDebutPeriode() {
        return debutPeriode;
    }

    public void setDebutPeriode(Instant debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public String getDebutPeriodeAsString() {
        return debutPeriode != null ? DateTimeFormatter.ISO_INSTANT.format(debutPeriode) : null;
    }

    public void setDebutPeriodeFromString(String debutPeriode) {
        this.debutPeriode = debutPeriode != null ? Instant.parse(debutPeriode) : null;
    }

    public Instant getFinPeriode() {
        return finPeriode;
    }

    public void setFinPeriode(Instant finPeriode) {
        this.finPeriode = finPeriode;
    }

    public String getFinPeriodeAsString() {
        return finPeriode != null ? DateTimeFormatter.ISO_INSTANT.format(finPeriode) : null;
    }

    public void setFinPeriodeFromString(String finPeriode) {
        this.finPeriode = finPeriode != null ? Instant.parse(finPeriode) : null;
    }

    public Instant getDateReception() {
        return dateReception;
    }

    public void setDateReception(Instant dateReception) {
        this.dateReception = dateReception;
    }

    public String getDateReceptionAsString() {
        return dateReception != null ? DateTimeFormatter.ISO_INSTANT.format(dateReception) : null;
    }

    public void setDateReceptionFromString(String dateReception) {
        this.dateReception = dateReception != null ? Instant.parse(dateReception) : null;
    }

    public Double getVieillesseRG() {
        return vieillesseRG;
    }

    public void setVieillesseRG(Double vieillesseRG) {
        this.vieillesseRG = vieillesseRG;
    }

    public Double getVieillesseRC() {
        return vieillesseRC;
    }

    public void setVieillesseRC(Double vieillesseRC) {
        this.vieillesseRC = vieillesseRC;
    }

    public Float getTauxRG() {
        return tauxRG;
    }

    public void setTauxRG(Float tauxRG) {
        this.tauxRG = tauxRG;
    }

    public Float getTauxRC() {
        return tauxRC;
    }

    public void setTauxRC(Float tauxRC) {
        this.tauxRC = tauxRC;
    }

    public Double getMtVieillesseRG() {
        return mtVieillesseRG;
    }

    public void setMtVieillesseRG(Double mtVieillesseRG) {
        this.mtVieillesseRG = mtVieillesseRG;
    }

    public Double getMtVieillesseRC() {
        return mtVieillesseRC;
    }

    public void setMtVieillesseRC(Double mtVieillesseRC) {
        this.mtVieillesseRC = mtVieillesseRC;
    }

    public Double getMtMajorationCss() {
        return mtMajorationCss;
    }

    public void setMtMajorationCss(Double mtMajorationCss) {
        this.mtMajorationCss = mtMajorationCss;
    }

    public Double getPrestationFam() {
        return prestationFam;
    }

    public void setPrestationFam(Double prestationFam) {
        this.prestationFam = prestationFam;
    }

    public Float getTauxPrestationFam() {
        return tauxPrestationFam;
    }

    public void setTauxPrestationFam(Float tauxPrestationFam) {
        this.tauxPrestationFam = tauxPrestationFam;
    }

    public Double getMtPrestationFam() {
        return mtPrestationFam;
    }

    public void setMtPrestationFam(Double mtPrestationFam) {
        this.mtPrestationFam = mtPrestationFam;
    }

    public Double getAtmp() {
        return atmp;
    }

    public void setAtmp(Double atmp) {
        this.atmp = atmp;
    }

    public Float getTauxATMP() {
        return tauxATMP;
    }

    public void setTauxATMP(Float tauxATMP) {
        this.tauxATMP = tauxATMP;
    }

    public Double getMtAtmp() {
        return mtAtmp;
    }

    public void setMtAtmp(Double mtAtmp) {
        this.mtAtmp = mtAtmp;
    }

    public Double getTotalIpres() {
        return totalIpres;
    }

    public void setTotalIpres(Double totalIpres) {
        this.totalIpres = totalIpres;
    }

    public Double getTotalCss() {
        return totalCss;
    }

    public void setTotalCss(Double totalCss) {
        this.totalCss = totalCss;
    }

    public Double getTotalAPayer() {
        return totalAPayer;
    }

    public void setTotalAPayer(Double totalAPayer) {
        this.totalAPayer = totalAPayer;
    }


    public Double getMtMajorationIpres() {
        return mtMajorationIpres;
    }

    public void setMtMajorationIpres(Double mtMajorationIpres) {
        this.mtMajorationIpres = mtMajorationIpres;
    }

    public Instant getEcheance() {
        return echeance;
    }

    public void setEcheance(Instant echeance) {
        this.echeance = echeance;
    }

    public String getEcheanceAsString() {
        return echeance != null ? DateTimeFormatter.ISO_INSTANT.format(echeance) : null;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public FactureDNSSupprime extraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
        return this;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }


    public void setEcheanceFromString(String echeance) {
        this.echeance = echeance != null ? Instant.parse(echeance) : null;
    }

    public BigDecimal getSalaireDeclare() {
        return salaireDeclare;
    }

    public void setSalaireDeclare(BigDecimal salaireDeclare) {
        this.salaireDeclare = salaireDeclare;
    }
}
