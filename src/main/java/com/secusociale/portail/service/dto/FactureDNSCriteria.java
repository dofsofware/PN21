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
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.FactureDNS} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.FactureDNSResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /facture-dns?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FactureDNSCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter numeroFacture;

    private LongFilter idDeclaration;

    private StringFilter numeroUnique;

    private StringFilter raisonSociale;

    private StringFilter adresse;

    private StringFilter agenceIpres;

    private StringFilter agenceCss;

    private InstantFilter debutPeriode;

    private InstantFilter finPeriode;

    private InstantFilter dateReception;

    private DoubleFilter vieillesseRG;

    private DoubleFilter vieillesseRC;

    private FloatFilter tauxRG;

    private FloatFilter tauxRC;

    private DoubleFilter mtVieillesseRG;

    private DoubleFilter mtVieillesseRC;

    private DoubleFilter mtMajorationCss;

    private DoubleFilter prestationFam;

    private FloatFilter tauxPrestationFam;

    private DoubleFilter mtPrestationFam;

    private DoubleFilter atmp;

    private FloatFilter tauxATMP;

    private DoubleFilter mtAtmp;

    private DoubleFilter totalIpres;

    private DoubleFilter totalCss;

    private DoubleFilter totalAPayer;

    private DoubleFilter mtMajorationIpres;

    private StringFilter statut;

    private InstantFilter echeance;

    public FactureDNSCriteria() {
    }

    public FactureDNSCriteria(FactureDNSCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.numeroFacture = other.numeroFacture == null ? null : other.numeroFacture.copy();
        this.idDeclaration = other.idDeclaration == null ? null : other.idDeclaration.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.raisonSociale = other.raisonSociale == null ? null : other.raisonSociale.copy();
        this.adresse = other.adresse == null ? null : other.adresse.copy();
        this.agenceIpres = other.agenceIpres == null ? null : other.agenceIpres.copy();
        this.agenceCss = other.agenceCss == null ? null : other.agenceCss.copy();
        this.debutPeriode = other.debutPeriode == null ? null : other.debutPeriode.copy();
        this.finPeriode = other.finPeriode == null ? null : other.finPeriode.copy();
        this.dateReception = other.dateReception == null ? null : other.dateReception.copy();
        this.vieillesseRG = other.vieillesseRG == null ? null : other.vieillesseRG.copy();
        this.vieillesseRC = other.vieillesseRC == null ? null : other.vieillesseRC.copy();
        this.tauxRG = other.tauxRG == null ? null : other.tauxRG.copy();
        this.tauxRC = other.tauxRC == null ? null : other.tauxRC.copy();
        this.mtVieillesseRG = other.mtVieillesseRG == null ? null : other.mtVieillesseRG.copy();
        this.mtVieillesseRC = other.mtVieillesseRC == null ? null : other.mtVieillesseRC.copy();
        this.mtMajorationCss = other.mtMajorationCss == null ? null : other.mtMajorationCss.copy();
        this.prestationFam = other.prestationFam == null ? null : other.prestationFam.copy();
        this.tauxPrestationFam = other.tauxPrestationFam == null ? null : other.tauxPrestationFam.copy();
        this.mtPrestationFam = other.mtPrestationFam == null ? null : other.mtPrestationFam.copy();
        this.atmp = other.atmp == null ? null : other.atmp.copy();
        this.tauxATMP = other.tauxATMP == null ? null : other.tauxATMP.copy();
        this.mtAtmp = other.mtAtmp == null ? null : other.mtAtmp.copy();
        this.totalIpres = other.totalIpres == null ? null : other.totalIpres.copy();
        this.totalCss = other.totalCss == null ? null : other.totalCss.copy();
        this.totalAPayer = other.totalAPayer == null ? null : other.totalAPayer.copy();
        this.mtMajorationIpres = other.mtMajorationIpres == null ? null : other.mtMajorationIpres.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.echeance = other.echeance == null ? null : other.echeance.copy();
    }

    @Override
    public FactureDNSCriteria copy() {
        return new FactureDNSCriteria(this);
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

    public LongFilter getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(LongFilter idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(StringFilter raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public StringFilter getAdresse() {
        return adresse;
    }

    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }

    public StringFilter getAgenceIpres() {
        return agenceIpres;
    }

    public void setAgenceIpres(StringFilter agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public StringFilter getAgenceCss() {
        return agenceCss;
    }

    public void setAgenceCss(StringFilter agenceCss) {
        this.agenceCss = agenceCss;
    }

    public InstantFilter getDebutPeriode() {
        return debutPeriode;
    }

    public void setDebutPeriode(InstantFilter debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public InstantFilter getFinPeriode() {
        return finPeriode;
    }

    public void setFinPeriode(InstantFilter finPeriode) {
        this.finPeriode = finPeriode;
    }

    public InstantFilter getDateReception() {
        return dateReception;
    }

    public void setDateReception(InstantFilter dateReception) {
        this.dateReception = dateReception;
    }

    public DoubleFilter getVieillesseRG() {
        return vieillesseRG;
    }

    public void setVieillesseRG(DoubleFilter vieillesseRG) {
        this.vieillesseRG = vieillesseRG;
    }

    public DoubleFilter getVieillesseRC() {
        return vieillesseRC;
    }

    public void setVieillesseRC(DoubleFilter vieillesseRC) {
        this.vieillesseRC = vieillesseRC;
    }

    public FloatFilter getTauxRG() {
        return tauxRG;
    }

    public void setTauxRG(FloatFilter tauxRG) {
        this.tauxRG = tauxRG;
    }

    public FloatFilter getTauxRC() {
        return tauxRC;
    }

    public void setTauxRC(FloatFilter tauxRC) {
        this.tauxRC = tauxRC;
    }

    public DoubleFilter getMtVieillesseRG() {
        return mtVieillesseRG;
    }

    public void setMtVieillesseRG(DoubleFilter mtVieillesseRG) {
        this.mtVieillesseRG = mtVieillesseRG;
    }

    public DoubleFilter getMtVieillesseRC() {
        return mtVieillesseRC;
    }

    public void setMtVieillesseRC(DoubleFilter mtVieillesseRC) {
        this.mtVieillesseRC = mtVieillesseRC;
    }

    public DoubleFilter getMtMajorationCss() {
        return mtMajorationCss;
    }

    public void setMtMajorationCss(DoubleFilter mtMajorationCss) {
        this.mtMajorationCss = mtMajorationCss;
    }

    public DoubleFilter getPrestationFam() {
        return prestationFam;
    }

    public void setPrestationFam(DoubleFilter prestationFam) {
        this.prestationFam = prestationFam;
    }

    public FloatFilter getTauxPrestationFam() {
        return tauxPrestationFam;
    }

    public void setTauxPrestationFam(FloatFilter tauxPrestationFam) {
        this.tauxPrestationFam = tauxPrestationFam;
    }

    public DoubleFilter getMtPrestationFam() {
        return mtPrestationFam;
    }

    public void setMtPrestationFam(DoubleFilter mtPrestationFam) {
        this.mtPrestationFam = mtPrestationFam;
    }

    public DoubleFilter getAtmp() {
        return atmp;
    }

    public void setAtmp(DoubleFilter atmp) {
        this.atmp = atmp;
    }

    public FloatFilter getTauxATMP() {
        return tauxATMP;
    }

    public void setTauxATMP(FloatFilter tauxATMP) {
        this.tauxATMP = tauxATMP;
    }

    public DoubleFilter getMtAtmp() {
        return mtAtmp;
    }

    public void setMtAtmp(DoubleFilter mtAtmp) {
        this.mtAtmp = mtAtmp;
    }

    public DoubleFilter getTotalIpres() {
        return totalIpres;
    }

    public void setTotalIpres(DoubleFilter totalIpres) {
        this.totalIpres = totalIpres;
    }

    public DoubleFilter getTotalCss() {
        return totalCss;
    }

    public void setTotalCss(DoubleFilter totalCss) {
        this.totalCss = totalCss;
    }

    public DoubleFilter getTotalAPayer() {
        return totalAPayer;
    }

    public void setTotalAPayer(DoubleFilter totalAPayer) {
        this.totalAPayer = totalAPayer;
    }

    public DoubleFilter getMtMajorationIpres() {
        return mtMajorationIpres;
    }

    public void setMtMajorationIpres(DoubleFilter mtMajorationIpres) {
        this.mtMajorationIpres = mtMajorationIpres;
    }

    public StringFilter getStatut() {
        return statut;
    }

    public void setStatut(StringFilter statut) {
        this.statut = statut;
    }

    public InstantFilter getEcheance() {
        return echeance;
    }

    public void setEcheance(InstantFilter echeance) {
        this.echeance = echeance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FactureDNSCriteria that = (FactureDNSCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numeroFacture, that.numeroFacture) &&
            Objects.equals(idDeclaration, that.idDeclaration) &&
            Objects.equals(numeroUnique, that.numeroUnique) &&
            Objects.equals(raisonSociale, that.raisonSociale) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(agenceIpres, that.agenceIpres) &&
            Objects.equals(agenceCss, that.agenceCss) &&
            Objects.equals(debutPeriode, that.debutPeriode) &&
            Objects.equals(finPeriode, that.finPeriode) &&
            Objects.equals(dateReception, that.dateReception) &&
            Objects.equals(vieillesseRG, that.vieillesseRG) &&
            Objects.equals(vieillesseRC, that.vieillesseRC) &&
            Objects.equals(tauxRG, that.tauxRG) &&
            Objects.equals(tauxRC, that.tauxRC) &&
            Objects.equals(mtVieillesseRG, that.mtVieillesseRG) &&
            Objects.equals(mtVieillesseRC, that.mtVieillesseRC) &&
            Objects.equals(mtMajorationCss, that.mtMajorationCss) &&
            Objects.equals(prestationFam, that.prestationFam) &&
            Objects.equals(tauxPrestationFam, that.tauxPrestationFam) &&
            Objects.equals(mtPrestationFam, that.mtPrestationFam) &&
            Objects.equals(atmp, that.atmp) &&
            Objects.equals(tauxATMP, that.tauxATMP) &&
            Objects.equals(mtAtmp, that.mtAtmp) &&
            Objects.equals(totalIpres, that.totalIpres) &&
            Objects.equals(totalCss, that.totalCss) &&
            Objects.equals(totalAPayer, that.totalAPayer) &&
            Objects.equals(mtMajorationIpres, that.mtMajorationIpres) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(echeance, that.echeance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numeroFacture,
        idDeclaration,
        numeroUnique,
        raisonSociale,
        adresse,
        agenceIpres,
        agenceCss,
        debutPeriode,
        finPeriode,
        dateReception,
        vieillesseRG,
        vieillesseRC,
        tauxRG,
        tauxRC,
        mtVieillesseRG,
        mtVieillesseRC,
        mtMajorationCss,
        prestationFam,
        tauxPrestationFam,
        mtPrestationFam,
        atmp,
        tauxATMP,
        mtAtmp,
        totalIpres,
        totalCss,
        totalAPayer,
        mtMajorationIpres,
        statut,
        echeance
        );
    }

    @Override
    public String toString() {
        return "FactureDNSCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numeroFacture != null ? "numeroFacture=" + numeroFacture + ", " : "") +
                (idDeclaration != null ? "idDeclaration=" + idDeclaration + ", " : "") +
                (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
                (raisonSociale != null ? "raisonSociale=" + raisonSociale + ", " : "") +
                (adresse != null ? "adresse=" + adresse + ", " : "") +
                (agenceIpres != null ? "agenceIpres=" + agenceIpres + ", " : "") +
                (agenceCss != null ? "agenceCss=" + agenceCss + ", " : "") +
                (debutPeriode != null ? "debutPeriode=" + debutPeriode + ", " : "") +
                (finPeriode != null ? "finPeriode=" + finPeriode + ", " : "") +
                (dateReception != null ? "dateReception=" + dateReception + ", " : "") +
                (vieillesseRG != null ? "vieillesseRG=" + vieillesseRG + ", " : "") +
                (vieillesseRC != null ? "vieillesseRC=" + vieillesseRC + ", " : "") +
                (tauxRG != null ? "tauxRG=" + tauxRG + ", " : "") +
                (tauxRC != null ? "tauxRC=" + tauxRC + ", " : "") +
                (mtVieillesseRG != null ? "mtVieillesseRG=" + mtVieillesseRG + ", " : "") +
                (mtVieillesseRC != null ? "mtVieillesseRC=" + mtVieillesseRC + ", " : "") +
                (mtMajorationCss != null ? "mtMajorationCss=" + mtMajorationCss + ", " : "") +
                (prestationFam != null ? "prestationFam=" + prestationFam + ", " : "") +
                (tauxPrestationFam != null ? "tauxPrestationFam=" + tauxPrestationFam + ", " : "") +
                (mtPrestationFam != null ? "mtPrestationFam=" + mtPrestationFam + ", " : "") +
                (atmp != null ? "atmp=" + atmp + ", " : "") +
                (tauxATMP != null ? "tauxATMP=" + tauxATMP + ", " : "") +
                (mtAtmp != null ? "mtAtmp=" + mtAtmp + ", " : "") +
                (totalIpres != null ? "totalIpres=" + totalIpres + ", " : "") +
                (totalCss != null ? "totalCss=" + totalCss + ", " : "") +
                (totalAPayer != null ? "totalAPayer=" + totalAPayer + ", " : "") +
                (mtMajorationIpres != null ? "mtMajorationIpres=" + mtMajorationIpres + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (echeance != null ? "echeance=" + echeance + ", " : "") +
            "}";
    }

}
