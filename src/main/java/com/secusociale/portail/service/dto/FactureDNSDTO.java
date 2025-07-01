package com.secusociale.portail.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secusociale.portail.domain.enumeration.EtatSynchronisation;
import com.secusociale.portail.domain.enumeration.StatutSynchronisation;

import java.sql.Timestamp;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.FactureDNS} entity.
 */
public class FactureDNSDTO implements Serializable {

    private Long id;

    @NotNull
    private String numeroFacture;

    @NotNull
    private Long idDeclaration;

    private String numeroUnique;

    private String raisonSociale;

    private String adresse;

    private String agenceIpres;

    private String agenceCss;

    private Instant debutPeriode;

    private Instant finPeriode;

    private Instant dateReception;

    private Double vieillesseRG;

    private Double vieillesseRC;

    private Float tauxRG;

    private Float tauxRC;

    private Double mtVieillesseRG;

    private Double mtVieillesseRC;

    private Double mtMajorationCss;

    private Double prestationFam;

    private Float tauxPrestationFam;

    private Double mtPrestationFam;

    private Double atmp;

    private Float tauxATMP;

    private Double mtAtmp;

    private Double totalIpres;

    private Double totalCss;

    private Double totalAPayer;

    @JsonIgnore
    @Lob
    private String extraInfos;

    private Double mtMajorationIpres;

    private String statut;

    private Instant echeance;

    private Timestamp dateSynchronisation;

    @Lob
    private String rapportSynchronisation;

    private StatutSynchronisation statutSynchronisation;

    private EtatSynchronisation etatSynchronisation;

    private Integer nombreInsertion;

    @Lob
    private String infosSynchronisation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Long getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(Long idDeclaration) {
        this.idDeclaration = idDeclaration;
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

    public Instant getFinPeriode() {
        return finPeriode;
    }

    public void setFinPeriode(Instant finPeriode) {
        this.finPeriode = finPeriode;
    }

    public Instant getDateReception() {
        return dateReception;
    }

    public void setDateReception(Instant dateReception) {
        this.dateReception = dateReception;
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

    public String getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Double getMtMajorationIpres() {
        return mtMajorationIpres;
    }

    public void setMtMajorationIpres(Double mtMajorationIpres) {
        this.mtMajorationIpres = mtMajorationIpres;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getEcheance() {
        return echeance;
    }

    public void setEcheance(Instant echeance) {
        this.echeance = echeance;
    }

    public Timestamp getDateSynchronisation() {
        return dateSynchronisation;
    }

    public void setDateSynchronisation(Timestamp dateSynchronisation) {
        this.dateSynchronisation = dateSynchronisation;
    }

    public String getRapportSynchronisation() {
        return rapportSynchronisation;
    }

    public void setRapportSynchronisation(String rapportSynchronisation) {
        this.rapportSynchronisation = rapportSynchronisation;
    }

    public StatutSynchronisation getStatutSynchronisation() {
        return statutSynchronisation;
    }

    public void setStatutSynchronisation(StatutSynchronisation statutSynchronisation) {
        this.statutSynchronisation = statutSynchronisation;
    }

    public EtatSynchronisation getEtatSynchronisation() {
        return etatSynchronisation;
    }

    public void setEtatSynchronisation(EtatSynchronisation etatSynchronisation) {
        this.etatSynchronisation = etatSynchronisation;
    }

    public Integer getNombreInsertion() {
        return nombreInsertion;
    }

    public void setNombreInsertion(Integer nombreInsertion) {
        this.nombreInsertion = nombreInsertion;
    }

    public String getInfosSynchronisation() {
        return infosSynchronisation;
    }

    public void setInfosSynchronisation(String infosSynchronisation) {
        this.infosSynchronisation = infosSynchronisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FactureDNSDTO factureDNSDTO = (FactureDNSDTO) o;
        if (factureDNSDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), factureDNSDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FactureDNSDTO{" +
            "id=" + id +
            ", numeroFacture='" + numeroFacture + '\'' +
            ", idDeclaration=" + idDeclaration +
            ", numeroUnique='" + numeroUnique + '\'' +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", adresse='" + adresse + '\'' +
            ", agenceIpres='" + agenceIpres + '\'' +
            ", agenceCss='" + agenceCss + '\'' +
            ", debutPeriode=" + debutPeriode +
            ", finPeriode=" + finPeriode +
            ", dateReception=" + dateReception +
            ", vieillesseRG=" + vieillesseRG +
            ", vieillesseRC=" + vieillesseRC +
            ", tauxRG=" + tauxRG +
            ", tauxRC=" + tauxRC +
            ", mtVieillesseRG=" + mtVieillesseRG +
            ", mtVieillesseRC=" + mtVieillesseRC +
            ", mtMajorationCss=" + mtMajorationCss +
            ", prestationFam=" + prestationFam +
            ", tauxPrestationFam=" + tauxPrestationFam +
            ", mtPrestationFam=" + mtPrestationFam +
            ", atmp=" + atmp +
            ", tauxATMP=" + tauxATMP +
            ", mtAtmp=" + mtAtmp +
            ", totalIpres=" + totalIpres +
            ", totalCss=" + totalCss +
            ", totalAPayer=" + totalAPayer +
            ", extraInfos='" + extraInfos + '\'' +
            ", mtMajorationIpres=" + mtMajorationIpres +
            ", statut='" + statut + '\'' +
            ", echeance=" + echeance +
            ", dateSynchronisation=" + dateSynchronisation +
            ", rapportSynchronisation='" + rapportSynchronisation + '\'' +
            ", statutSynchronisation=" + statutSynchronisation +
            ", etatSynchronisation=" + etatSynchronisation +
            ", nombreInsertion=" + nombreInsertion +
            ", infosSynchronisation='" + infosSynchronisation + '\'' +
            '}';
    }
}
