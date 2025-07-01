package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.FactureImpayee} entity.
 */
public class FactureImpayeeDTO implements Serializable {

    private Long id;

    private String numeroFacture;

    private String typeFacture;

    private String dateEcheance;

    private String dateDebut;

    private String dateFin;

    private String raisonSociale;

    private String status;

    private String numeroUnique;

    private BigDecimal detteinput;

    private BigDecimal montantVerse;

    private BigDecimal penalite;

    private BigDecimal montantPaye;

    private BigDecimal dette;

    private BigDecimal montantTotal;

    private BigDecimal majorations;

    private BigDecimal montantPrincipal;


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

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public BigDecimal getDetteinput() {
        return detteinput;
    }

    public void setDetteinput(BigDecimal detteinput) {
        this.detteinput = detteinput;
    }

    public BigDecimal getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(BigDecimal montantVerse) {
        this.montantVerse = montantVerse;
    }

    public BigDecimal getPenalite() {
        return penalite;
    }

    public void setPenalite(BigDecimal penalite) {
        this.penalite = penalite;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimal getDette() {
        return dette;
    }

    public void setDette(BigDecimal dette) {
        this.dette = dette;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getMajorations() {
        return majorations;
    }

    public void setMajorations(BigDecimal majorations) {
        this.majorations = majorations;
    }

    public BigDecimal getMontantPrincipal() {
        return montantPrincipal;
    }

    public void setMontantPrincipal(BigDecimal montantPrincipal) {
        this.montantPrincipal = montantPrincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FactureImpayeeDTO factureImpayeeDTO = (FactureImpayeeDTO) o;
        if (factureImpayeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), factureImpayeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FactureImpayeeDTO{" +
            "id=" + getId() +
            ", numeroFacture='" + getNumeroFacture() + "'" +
            ", typeFacture='" + getTypeFacture() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", status='" + getStatus() + "'" +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", detteinput=" + getDetteinput() +
            ", montantVerse=" + getMontantVerse() +
            ", penalite=" + getPenalite() +
            ", montantPaye=" + getMontantPaye() +
            ", dette=" + getDette() +
            ", montantTotal=" + getMontantTotal() +
            ", majorations=" + getMajorations() +
            ", montantPrincipal=" + getMontantPrincipal() +
            "}";
    }
}
