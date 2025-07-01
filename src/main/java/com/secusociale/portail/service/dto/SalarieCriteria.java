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
 * Criteria class for the {@link com.secusociale.portail.domain.Salarie} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.SalarieResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /salaries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SalarieCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter numeroUnique;

    private StringFilter numeroCni;

    private StringFilter prenom;

    private StringFilter nom;

    private StringFilter telephone;

    private StringFilter email;

    private StringFilter sexe;

    private LocalDateFilter dateNais;

    private StringFilter lieuNais;

    private BooleanFilter active;

    private LongFilter userId;

    private LongFilter agentId;

    private InstantFilter createdAt;

    private InstantFilter lastUpdateAt;

    private StringFilter statut;
    private StringFilter globalSearch; // Nouveau champ pour la recherche globale

    public SalarieCriteria() {
    }

    public SalarieCriteria(SalarieCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.numeroCni = other.numeroCni == null ? null : other.numeroCni.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.sexe = other.sexe == null ? null : other.sexe.copy();
        this.dateNais = other.dateNais == null ? null : other.dateNais.copy();
        this.lieuNais = other.lieuNais == null ? null : other.lieuNais.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.agentId = other.agentId == null ? null : other.agentId.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.lastUpdateAt = other.lastUpdateAt == null ? null : other.lastUpdateAt.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.globalSearch = other.globalSearch == null ? null : other.globalSearch.copy(); // Copie du nouveau champ
    }

    @Override
    public SalarieCriteria copy() {
        return new SalarieCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getNumeroCni() {
        return numeroCni;
    }

    public void setNumeroCni(StringFilter numeroCni) {
        this.numeroCni = numeroCni;
    }

    public StringFilter getPrenom() {
        return prenom;
    }

    public void setPrenom(StringFilter prenom) {
        this.prenom = prenom;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getSexe() {
        return sexe;
    }

    public void setSexe(StringFilter sexe) {
        this.sexe = sexe;
    }

    public LocalDateFilter getDateNais() {
        return dateNais;
    }

    public void setDateNais(LocalDateFilter dateNais) {
        this.dateNais = dateNais;
    }

    public StringFilter getLieuNais() {
        return lieuNais;
    }

    public void setLieuNais(StringFilter lieuNais) {
        this.lieuNais = lieuNais;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getAgentId() {
        return agentId;
    }

    public void setAgentId(LongFilter agentId) {
        this.agentId = agentId;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public InstantFilter getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(InstantFilter lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public StringFilter getStatut() {
        return statut;
    }

    public void setStatut(StringFilter statut) {
        this.statut = statut;
    }

    public StringFilter getGlobalSearch() {
        return globalSearch; // Getter pour le nouveau champ
    }

    public void setGlobalSearch(StringFilter globalSearch) {
        this.globalSearch = globalSearch; // Setter pour le nouveau champ
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SalarieCriteria that = (SalarieCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numeroUnique, that.numeroUnique) &&
            Objects.equals(numeroCni, that.numeroCni) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(sexe, that.sexe) &&
            Objects.equals(dateNais, that.dateNais) &&
            Objects.equals(lieuNais, that.lieuNais) &&
            Objects.equals(active, that.active) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(agentId, that.agentId) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(lastUpdateAt, that.lastUpdateAt) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(globalSearch, that.globalSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numeroUnique,
        numeroCni,
        prenom,
        nom,
        telephone,
        email,
        sexe,
        dateNais,
        lieuNais,
        active,
        userId,
        agentId,
        createdAt,
        lastUpdateAt,
        statut,
        globalSearch
        );
    }

    @Override
    public String toString() {
        return "SalarieCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
            (numeroCni != null ? "numeroCni=" + numeroCni + ", " : "") +
            (prenom != null ? "prenom=" + prenom + ", " : "") +
            (nom != null ? "nom=" + nom + ", " : "") +
            (telephone != null ? "telephone=" + telephone + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (sexe != null ? "sexe=" + sexe + ", " : "") +
            (dateNais != null ? "dateNais=" + dateNais + ", " : "") +
            (lieuNais != null ? "lieuNais=" + lieuNais + ", " : "") +
            (active != null ? "active=" + active + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (agentId != null ? "agentId=" + agentId + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (lastUpdateAt != null ? "lastUpdateAt=" + lastUpdateAt + ", " : "") +
            (statut != null ? "statut=" + statut + ", " : "") +
            (globalSearch != null ? "globalSearch=" + globalSearch + ", " : "") +
            "}";
    }

}
