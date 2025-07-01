package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.FactureImpayee} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.FactureImpayeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /facture-impayees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FactureImpayeeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter numeroFacture;

    private StringFilter typeFacture;

    private StringFilter dateEcheance;

    private StringFilter dateDebut;

    private StringFilter dateFin;

    private StringFilter raisonSociale;

    private StringFilter status;

    private StringFilter numeroUnique;

    private BigDecimalFilter detteinput;

    private BigDecimalFilter montantVerse;

    private BigDecimalFilter penalite;

    private BigDecimalFilter montantPaye;

    private BigDecimalFilter dette;

    private BigDecimalFilter montantTotal;

    private BigDecimalFilter majorations;

    private BigDecimalFilter montantPrincipal;

    public FactureImpayeeCriteria() {
    }

    public FactureImpayeeCriteria(FactureImpayeeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.numeroFacture = other.numeroFacture == null ? null : other.numeroFacture.copy();
        this.typeFacture = other.typeFacture == null ? null : other.typeFacture.copy();
        this.dateEcheance = other.dateEcheance == null ? null : other.dateEcheance.copy();
        this.dateDebut = other.dateDebut == null ? null : other.dateDebut.copy();
        this.dateFin = other.dateFin == null ? null : other.dateFin.copy();
        this.raisonSociale = other.raisonSociale == null ? null : other.raisonSociale.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.detteinput = other.detteinput == null ? null : other.detteinput.copy();
        this.montantVerse = other.montantVerse == null ? null : other.montantVerse.copy();
        this.penalite = other.penalite == null ? null : other.penalite.copy();
        this.montantPaye = other.montantPaye == null ? null : other.montantPaye.copy();
        this.dette = other.dette == null ? null : other.dette.copy();
        this.montantTotal = other.montantTotal == null ? null : other.montantTotal.copy();
        this.majorations = other.majorations == null ? null : other.majorations.copy();
        this.montantPrincipal = other.montantPrincipal == null ? null : other.montantPrincipal.copy();
    }

    @Override
    public FactureImpayeeCriteria copy() {
        return new FactureImpayeeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(StringFilter numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public StringFilter getTypeFacture() {
        return typeFacture;
    }

    public void setTypeFacture(StringFilter typeFacture) {
        this.typeFacture = typeFacture;
    }

    public StringFilter getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(StringFilter dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public StringFilter getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(StringFilter dateDebut) {
        this.dateDebut = dateDebut;
    }

    public StringFilter getDateFin() {
        return dateFin;
    }

    public void setDateFin(StringFilter dateFin) {
        this.dateFin = dateFin;
    }

    public StringFilter getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(StringFilter raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public BigDecimalFilter getDetteinput() {
        return detteinput;
    }

    public void setDetteinput(BigDecimalFilter detteinput) {
        this.detteinput = detteinput;
    }

    public BigDecimalFilter getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(BigDecimalFilter montantVerse) {
        this.montantVerse = montantVerse;
    }

    public BigDecimalFilter getPenalite() {
        return penalite;
    }

    public void setPenalite(BigDecimalFilter penalite) {
        this.penalite = penalite;
    }

    public BigDecimalFilter getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimalFilter montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimalFilter getDette() {
        return dette;
    }

    public void setDette(BigDecimalFilter dette) {
        this.dette = dette;
    }

    public BigDecimalFilter getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimalFilter montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimalFilter getMajorations() {
        return majorations;
    }

    public void setMajorations(BigDecimalFilter majorations) {
        this.majorations = majorations;
    }

    public BigDecimalFilter getMontantPrincipal() {
        return montantPrincipal;
    }

    public void setMontantPrincipal(BigDecimalFilter montantPrincipal) {
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
        final FactureImpayeeCriteria that = (FactureImpayeeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numeroFacture, that.numeroFacture) &&
            Objects.equals(typeFacture, that.typeFacture) &&
            Objects.equals(dateEcheance, that.dateEcheance) &&
            Objects.equals(dateDebut, that.dateDebut) &&
            Objects.equals(dateFin, that.dateFin) &&
            Objects.equals(raisonSociale, that.raisonSociale) &&
            Objects.equals(status, that.status) &&
            Objects.equals(numeroUnique, that.numeroUnique) &&
            Objects.equals(detteinput, that.detteinput) &&
            Objects.equals(montantVerse, that.montantVerse) &&
            Objects.equals(penalite, that.penalite) &&
            Objects.equals(montantPaye, that.montantPaye) &&
            Objects.equals(dette, that.dette) &&
            Objects.equals(montantTotal, that.montantTotal) &&
            Objects.equals(majorations, that.majorations) &&
            Objects.equals(montantPrincipal, that.montantPrincipal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numeroFacture,
        typeFacture,
        dateEcheance,
        dateDebut,
        dateFin,
        raisonSociale,
        status,
        numeroUnique,
        detteinput,
        montantVerse,
        penalite,
        montantPaye,
        dette,
        montantTotal,
        majorations,
        montantPrincipal
        );
    }

    @Override
    public String toString() {
        return "FactureImpayeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numeroFacture != null ? "numeroFacture=" + numeroFacture + ", " : "") +
                (typeFacture != null ? "typeFacture=" + typeFacture + ", " : "") +
                (dateEcheance != null ? "dateEcheance=" + dateEcheance + ", " : "") +
                (dateDebut != null ? "dateDebut=" + dateDebut + ", " : "") +
                (dateFin != null ? "dateFin=" + dateFin + ", " : "") +
                (raisonSociale != null ? "raisonSociale=" + raisonSociale + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
                (detteinput != null ? "detteinput=" + detteinput + ", " : "") +
                (montantVerse != null ? "montantVerse=" + montantVerse + ", " : "") +
                (penalite != null ? "penalite=" + penalite + ", " : "") +
                (montantPaye != null ? "montantPaye=" + montantPaye + ", " : "") +
                (dette != null ? "dette=" + dette + ", " : "") +
                (montantTotal != null ? "montantTotal=" + montantTotal + ", " : "") +
                (majorations != null ? "majorations=" + majorations + ", " : "") +
                (montantPrincipal != null ? "montantPrincipal=" + montantPrincipal + ", " : "") +
            "}";
    }

}
