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
 * Criteria class for the {@link com.secusociale.portail.domain.SuiviJob} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.SuiviJobResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /suivi-jobs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SuiviJobCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nom;

    private StringFilter statut;

    private InstantFilter demarreLe;

    private InstantFilter termineLe;

    private StringFilter demarrePar;

    public SuiviJobCriteria() {
    }

    public SuiviJobCriteria(SuiviJobCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.demarreLe = other.demarreLe == null ? null : other.demarreLe.copy();
        this.termineLe = other.termineLe == null ? null : other.termineLe.copy();
        this.demarrePar = other.demarrePar == null ? null : other.demarrePar.copy();
    }

    @Override
    public SuiviJobCriteria copy() {
        return new SuiviJobCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getStatut() {
        return statut;
    }

    public void setStatut(StringFilter statut) {
        this.statut = statut;
    }

    public InstantFilter getDemarreLe() {
        return demarreLe;
    }

    public void setDemarreLe(InstantFilter demarreLe) {
        this.demarreLe = demarreLe;
    }

    public InstantFilter getTermineLe() {
        return termineLe;
    }

    public void setTermineLe(InstantFilter termineLe) {
        this.termineLe = termineLe;
    }

    public StringFilter getDemarrePar() {
        return demarrePar;
    }

    public void setDemarrePar(StringFilter demarrePar) {
        this.demarrePar = demarrePar;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SuiviJobCriteria that = (SuiviJobCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(demarreLe, that.demarreLe) &&
            Objects.equals(termineLe, that.termineLe) &&
            Objects.equals(demarrePar, that.demarrePar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nom,
        statut,
        demarreLe,
        termineLe,
        demarrePar
        );
    }

    @Override
    public String toString() {
        return "SuiviJobCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (demarreLe != null ? "demarreLe=" + demarreLe + ", " : "") +
                (termineLe != null ? "termineLe=" + termineLe + ", " : "") +
                (demarrePar != null ? "demarrePar=" + demarrePar + ", " : "") +
            "}";
    }

}
