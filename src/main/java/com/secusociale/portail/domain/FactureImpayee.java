package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A FactureImpayee.
 */
@Entity
@Table(name = "facture_impayee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FactureImpayee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_facture")
    private String numeroFacture;

    @Column(name = "type_facture")
    private String typeFacture;

    @Column(name = "date_echeance")
    private String dateEcheance;

    @Column(name = "date_debut")
    private String dateDebut;

    @Column(name = "date_fin")
    private String dateFin;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "status")
    private String status;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "detteinput", precision = 21, scale = 2)
    private BigDecimal detteinput;

    @Column(name = "montant_verse", precision = 21, scale = 2)
    private BigDecimal montantVerse;

    @Column(name = "penalite", precision = 21, scale = 2)
    private BigDecimal penalite;

    @Column(name = "montant_paye", precision = 21, scale = 2)
    private BigDecimal montantPaye;

    @Column(name = "dette", precision = 21, scale = 2)
    private BigDecimal dette;

    @Column(name = "montant_total", precision = 21, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "majorations", precision = 21, scale = 2)
    private BigDecimal majorations;

    @Column(name = "montant_principal", precision = 21, scale = 2)
    private BigDecimal montantPrincipal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public FactureImpayee numeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
        return this;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public String getTypeFacture() {
        return typeFacture;
    }

    public FactureImpayee typeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
        return this;
    }

    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }

    public String getDateEcheance() {
        return dateEcheance;
    }

    public FactureImpayee dateEcheance(String dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(String dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public FactureImpayee dateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public FactureImpayee dateFin(String dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public FactureImpayee raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getStatus() {
        return status;
    }

    public FactureImpayee status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public FactureImpayee numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public BigDecimal getDetteinput() {
        return detteinput;
    }

    public FactureImpayee detteinput(BigDecimal detteinput) {
        this.detteinput = detteinput;
        return this;
    }

    public void setDetteinput(BigDecimal detteinput) {
        this.detteinput = detteinput;
    }

    public BigDecimal getMontantVerse() {
        return montantVerse;
    }

    public FactureImpayee montantVerse(BigDecimal montantVerse) {
        this.montantVerse = montantVerse;
        return this;
    }

    public void setMontantVerse(BigDecimal montantVerse) {
        this.montantVerse = montantVerse;
    }

    public BigDecimal getPenalite() {
        return penalite;
    }

    public FactureImpayee penalite(BigDecimal penalite) {
        this.penalite = penalite;
        return this;
    }

    public void setPenalite(BigDecimal penalite) {
        this.penalite = penalite;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public FactureImpayee montantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimal getDette() {
        return dette;
    }

    public FactureImpayee dette(BigDecimal dette) {
        this.dette = dette;
        return this;
    }

    public void setDette(BigDecimal dette) {
        this.dette = dette;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public FactureImpayee montantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getMajorations() {
        return majorations;
    }

    public FactureImpayee majorations(BigDecimal majorations) {
        this.majorations = majorations;
        return this;
    }

    public void setMajorations(BigDecimal majorations) {
        this.majorations = majorations;
    }

    public BigDecimal getMontantPrincipal() {
        return montantPrincipal;
    }

    public FactureImpayee montantPrincipal(BigDecimal montantPrincipal) {
        this.montantPrincipal = montantPrincipal;
        return this;
    }

    public void setMontantPrincipal(BigDecimal montantPrincipal) {
        this.montantPrincipal = montantPrincipal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureImpayee)) {
            return false;
        }
        return id != null && id.equals(((FactureImpayee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FactureImpayee{" +
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
