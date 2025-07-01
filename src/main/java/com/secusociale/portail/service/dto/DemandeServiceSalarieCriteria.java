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
 * Criteria class for the {@link com.secusociale.portail.domain.DemandeServiceSalarie} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.DemandeServiceSalarieResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /demande-service-salaries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DemandeServiceSalarieCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter type;

    private StringFilter statut;

    private InstantFilter date;

    private InstantFilter dateTraitement;

    private StringFilter numeroUniqueEmployeur;

    private LongFilter gestionnaireId;

    private LongFilter salarieId;

   private StringFilter globalSearch;

    public DemandeServiceSalarieCriteria() {
    }

    public DemandeServiceSalarieCriteria(DemandeServiceSalarieCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.dateTraitement = other.dateTraitement == null ? null : other.dateTraitement.copy();
        this.numeroUniqueEmployeur = other.numeroUniqueEmployeur == null ? null : other.numeroUniqueEmployeur.copy();
        this.gestionnaireId = other.gestionnaireId == null ? null : other.gestionnaireId.copy();
        this.salarieId = other.salarieId == null ? null : other.salarieId.copy();
        this.globalSearch = other.globalSearch == null ? null : other.globalSearch.copy();
    }

    @Override
    public DemandeServiceSalarieCriteria copy() {
        return new DemandeServiceSalarieCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getStatut() {
        return statut;
    }

    public void setStatut(StringFilter statut) {
        this.statut = statut;
    }

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public InstantFilter getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(InstantFilter dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public StringFilter getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public void setNumeroUniqueEmployeur(StringFilter numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }

    public LongFilter getGestionnaireId() {
        return gestionnaireId;
    }

    public void setGestionnaireId(LongFilter gestionnaireId) {
        this.gestionnaireId = gestionnaireId;
    }

    public LongFilter getSalarieId() {
        return salarieId;
    }

    public void setSalarieId(LongFilter salarieId) {
        this.salarieId = salarieId;
    }

    public StringFilter getGlobalSearch() { // Getter pour globalSearch
        return globalSearch;
    }

    public void setGlobalSearch(StringFilter globalSearch) { // Setter pour globalSearch
        this.globalSearch = globalSearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DemandeServiceSalarieCriteria that = (DemandeServiceSalarieCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(type, that.type) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(date, that.date) &&
            Objects.equals(dateTraitement, that.dateTraitement) &&
            Objects.equals(numeroUniqueEmployeur, that.numeroUniqueEmployeur) &&
            Objects.equals(gestionnaireId, that.gestionnaireId) &&
            Objects.equals(salarieId, that.salarieId) &&
            Objects.equals(globalSearch, that.globalSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        type,
        statut,
        date,
        dateTraitement,
            numeroUniqueEmployeur,
        gestionnaireId,
        salarieId,
        globalSearch
        );
    }

    @Override
    public String toString() {
        return "DemandeServiceSalarieCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (dateTraitement != null ? "dateTraitement=" + dateTraitement + ", " : "") +
                (numeroUniqueEmployeur != null ? "numeroUniqueEmployeur=" + numeroUniqueEmployeur + ", " : "") +
                (gestionnaireId != null ? "gestionnaireId=" + gestionnaireId + ", " : "") +
                (salarieId != null ? "salarieId=" + salarieId + ", " : "") +
                (globalSearch != null ? "globalSearch=" + globalSearch + ", " : "") +
            "}";
    }

}
