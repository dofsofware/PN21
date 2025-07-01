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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.Carriere} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.CarriereResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /carrieres?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CarriereCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter numeroUniqueSalarie;

    private StringFilter numeroUniqueEmployeur;

    private StringFilter raisonSociale;

    private StringFilter typeRegime;

    private StringFilter typeDeclaration;

    private LocalDateFilter debutCotisation;

    private LocalDateFilter finCotisation;

    private IntegerFilter exercice;

    private DoubleFilter salaireBrute;

    private DoubleFilter salaireAssujettie;

    private DoubleFilter cotisationIpres;

    private DoubleFilter cotisationCss;

    private InstantFilter createdAt;

    private LongFilter salarieId;

    public CarriereCriteria() {
    }

    public CarriereCriteria(CarriereCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.numeroUniqueSalarie = other.numeroUniqueSalarie == null ? null : other.numeroUniqueSalarie.copy();
        this.numeroUniqueEmployeur = other.numeroUniqueEmployeur == null ? null : other.numeroUniqueEmployeur.copy();
        this.raisonSociale = other.raisonSociale == null ? null : other.raisonSociale.copy();
        this.typeRegime = other.typeRegime == null ? null : other.typeRegime.copy();
        this.typeDeclaration = other.typeDeclaration == null ? null : other.typeDeclaration.copy();
        this.debutCotisation = other.debutCotisation == null ? null : other.debutCotisation.copy();
        this.finCotisation = other.finCotisation == null ? null : other.finCotisation.copy();
        this.exercice = other.exercice == null ? null : other.exercice.copy();
        this.salaireBrute = other.salaireBrute == null ? null : other.salaireBrute.copy();
        this.salaireAssujettie = other.salaireAssujettie == null ? null : other.salaireAssujettie.copy();
        this.cotisationIpres = other.cotisationIpres == null ? null : other.cotisationIpres.copy();
        this.cotisationCss = other.cotisationCss == null ? null : other.cotisationCss.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.salarieId = other.salarieId == null ? null : other.salarieId.copy();
    }

    @Override
    public CarriereCriteria copy() {
        return new CarriereCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNumeroUniqueSalarie() {
        return numeroUniqueSalarie;
    }

    public void setNumeroUniqueSalarie(StringFilter numeroUniqueSalarie) {
        this.numeroUniqueSalarie = numeroUniqueSalarie;
    }

    public StringFilter getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public void setNumeroUniqueEmployeur(StringFilter numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }

    public StringFilter getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(StringFilter raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public StringFilter getTypeRegime() {
        return typeRegime;
    }

    public void setTypeRegime(StringFilter typeRegime) {
        this.typeRegime = typeRegime;
    }

    public StringFilter getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(StringFilter typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public LocalDateFilter getDebutCotisation() {
        return debutCotisation;
    }

    public void setDebutCotisation(LocalDateFilter debutCotisation) {
        this.debutCotisation = debutCotisation;
    }

    public LocalDateFilter getFinCotisation() {
        return finCotisation;
    }

    public void setFinCotisation(LocalDateFilter finCotisation) {
        this.finCotisation = finCotisation;
    }

    public IntegerFilter getExercice() {
        return exercice;
    }

    public void setExercice(IntegerFilter exercice) {
        this.exercice = exercice;
    }

    public DoubleFilter getSalaireBrute() {
        return salaireBrute;
    }

    public void setSalaireBrute(DoubleFilter salaireBrute) {
        this.salaireBrute = salaireBrute;
    }

    public DoubleFilter getSalaireAssujettie() {
        return salaireAssujettie;
    }

    public void setSalaireAssujettie(DoubleFilter salaireAssujettie) {
        this.salaireAssujettie = salaireAssujettie;
    }

    public DoubleFilter getCotisationIpres() {
        return cotisationIpres;
    }

    public void setCotisationIpres(DoubleFilter cotisationIpres) {
        this.cotisationIpres = cotisationIpres;
    }

    public DoubleFilter getCotisationCss() {
        return cotisationCss;
    }

    public void setCotisationCss(DoubleFilter cotisationCss) {
        this.cotisationCss = cotisationCss;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public LongFilter getSalarieId() {
        return salarieId;
    }

    public void setSalarieId(LongFilter salarieId) {
        this.salarieId = salarieId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarriereCriteria that = (CarriereCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numeroUniqueSalarie, that.numeroUniqueSalarie) &&
            Objects.equals(numeroUniqueEmployeur, that.numeroUniqueEmployeur) &&
            Objects.equals(raisonSociale, that.raisonSociale) &&
            Objects.equals(typeRegime, that.typeRegime) &&
            Objects.equals(typeDeclaration, that.typeDeclaration) &&
            Objects.equals(debutCotisation, that.debutCotisation) &&
            Objects.equals(finCotisation, that.finCotisation) &&
            Objects.equals(exercice, that.exercice) &&
            Objects.equals(salaireBrute, that.salaireBrute) &&
            Objects.equals(salaireAssujettie, that.salaireAssujettie) &&
            Objects.equals(cotisationIpres, that.cotisationIpres) &&
            Objects.equals(cotisationCss, that.cotisationCss) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(salarieId, that.salarieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numeroUniqueSalarie,
        numeroUniqueEmployeur,
        raisonSociale,
        typeRegime,
        typeDeclaration,
        debutCotisation,
        finCotisation,
        exercice,
        salaireBrute,
        salaireAssujettie,
        cotisationIpres,
        cotisationCss,
        createdAt,
        salarieId
        );
    }

    @Override
    public String toString() {
        return "CarriereCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numeroUniqueSalarie != null ? "numeroUniqueSalarie=" + numeroUniqueSalarie + ", " : "") +
                (numeroUniqueEmployeur != null ? "numeroUniqueEmployeur=" + numeroUniqueEmployeur + ", " : "") +
                (raisonSociale != null ? "raisonSociale=" + raisonSociale + ", " : "") +
                (typeRegime != null ? "typeRegime=" + typeRegime + ", " : "") +
                (typeDeclaration != null ? "typeDeclaration=" + typeDeclaration + ", " : "") +
                (debutCotisation != null ? "debutCotisation=" + debutCotisation + ", " : "") +
                (finCotisation != null ? "finCotisation=" + finCotisation + ", " : "") +
                (exercice != null ? "exercice=" + exercice + ", " : "") +
                (salaireBrute != null ? "salaireBrute=" + salaireBrute + ", " : "") +
                (salaireAssujettie != null ? "salaireAssujettie=" + salaireAssujettie + ", " : "") +
                (cotisationIpres != null ? "cotisationIpres=" + cotisationIpres + ", " : "") +
                (cotisationCss != null ? "cotisationCss=" + cotisationCss + ", " : "") +
                (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
                (salarieId != null ? "salarieId=" + salarieId + ", " : "") +
            "}";
    }

}
