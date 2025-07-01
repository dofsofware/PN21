package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.DemandeService} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.DemandeServiceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dmts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DemandeServiceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter typeDemande;

    private StringFilter statut;

    private InstantFilter createdAt;

    private LongFilter employeur;

    private StringFilter idDossier;

    private StringFilter reponseBrute;

    private StringFilter globalSearch;

    //    @Lob
    //    private String payload;

    public DemandeServiceCriteria() {
    }

    public DemandeServiceCriteria(DemandeServiceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeDemande = other.typeDemande == null ? null : other.typeDemande.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.employeur = other.employeur == null ? null : other.employeur.copy();
        this.reponseBrute = other.reponseBrute == null ? null : other.reponseBrute.copy();
        this.idDossier = other.idDossier == null ? null : other.idDossier.copy();
        this.globalSearch = other.globalSearch == null ? null : other.globalSearch.copy();
    }

    @Override
    public DemandeServiceCriteria copy() {
        return new DemandeServiceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(StringFilter typeDemande) {
        this.typeDemande = typeDemande;
    }

    public StringFilter getStatut() {
        return statut;
    }

    public void setStatut(StringFilter statut) {
        this.statut = statut;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public LongFilter getEmployeur() {
        return employeur;
    }

    public void setEmployeur(LongFilter employeur) {
        this.employeur = employeur;
    }

    public StringFilter getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(StringFilter idDossier) {
        this.idDossier = idDossier;
    }

    public StringFilter getReponseBrute() {
        return reponseBrute;
    }

    public void setReponseBrute(StringFilter reponseBrute) {
        this.reponseBrute = reponseBrute;
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
        final DemandeServiceCriteria that = (DemandeServiceCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(typeDemande, that.typeDemande) &&
                Objects.equals(statut, that.statut) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(employeur, that.employeur) &&
                Objects.equals(reponseBrute, that.reponseBrute) &&
                Objects.equals(idDossier, that.idDossier) &&
                Objects.equals(globalSearch, that.globalSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            typeDemande,
            statut,
            createdAt,
            employeur,
            reponseBrute,
            idDossier,
            globalSearch
        );
    }

    @Override
    public String toString() {
        return "DemandeServiceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (typeDemande != null ? "typeDemande=" + typeDemande + ", " : "") +
            (statut != null ? "statut=" + statut + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (employeur != null ? "employeur=" + employeur + ", " : "") +
            (idDossier != null ? "idDossier=" + idDossier + ", " : "") +
            (reponseBrute != null ? "reponseBrute=" + reponseBrute + ", " : "") +
            (globalSearch != null ? "reponseBrute=" + globalSearch + ", " : "") +
            "}";
    }

}
