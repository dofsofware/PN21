package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.ImmatriculationRecuperee} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.ImmatriculationRecupereeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /immatriculation-recuperees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImmatriculationRecupereeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter moyenConfirmation;

    private StringFilter status;

    private StringFilter statusdesc;

    private StringFilter typeIdentifiant;

    private StringFilter numeroIdentifiant;

    private StringFilter ninea;

    private StringFilter numeroUnique;

    private StringFilter numeroCss;

    private StringFilter numeroIpres;

    private StringFilter raisonSociale;

    private LongFilter userId;

    private LongFilter agentId;

    private InstantFilter createdAt;

    private InstantFilter dateTraitement;

    private InstantFilter date;

    private StringFilter zoneIpres;

    private StringFilter agenceCss;

    private StringFilter agenceIpres;

    private StringFilter tauxAt;

    private StringFilter registreCommerce;

    public ImmatriculationRecupereeCriteria() {
    }

    public ImmatriculationRecupereeCriteria(ImmatriculationRecupereeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.moyenConfirmation = other.moyenConfirmation == null ? null : other.moyenConfirmation.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.statusdesc = other.statusdesc == null ? null : other.statusdesc.copy();
        this.typeIdentifiant = other.typeIdentifiant == null ? null : other.typeIdentifiant.copy();
        this.numeroIdentifiant = other.numeroIdentifiant == null ? null : other.numeroIdentifiant.copy();
        this.ninea = other.ninea == null ? null : other.ninea.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.numeroCss = other.numeroCss == null ? null : other.numeroCss.copy();
        this.numeroIpres = other.numeroIpres == null ? null : other.numeroIpres.copy();
        this.raisonSociale = other.raisonSociale == null ? null : other.raisonSociale.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.agentId = other.agentId == null ? null : other.agentId.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.dateTraitement = other.dateTraitement == null ? null : other.dateTraitement.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.zoneIpres = other.zoneIpres == null ? null : other.zoneIpres.copy();
        this.agenceCss = other.agenceCss == null ? null : other.agenceCss.copy();
        this.agenceIpres = other.agenceIpres == null ? null : other.agenceIpres.copy();
        this.tauxAt = other.tauxAt == null ? null : other.tauxAt.copy();
        this.registreCommerce = other.registreCommerce == null ? null : other.registreCommerce.copy();
    }

    @Override
    public ImmatriculationRecupereeCriteria copy() {
        return new ImmatriculationRecupereeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public void setMoyenConfirmation(StringFilter moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(StringFilter statusdesc) {
        this.statusdesc = statusdesc;
    }

    public StringFilter getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(StringFilter typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public StringFilter getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public void setNumeroIdentifiant(StringFilter numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public StringFilter getNinea() {
        return ninea;
    }

    public void setNinea(StringFilter ninea) {
        this.ninea = ninea;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getNumeroCss() {
        return numeroCss;
    }

    public void setNumeroCss(StringFilter numeroCss) {
        this.numeroCss = numeroCss;
    }

    public StringFilter getNumeroIpres() {
        return numeroIpres;
    }

    public void setNumeroIpres(StringFilter numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public StringFilter getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(StringFilter raisonSociale) {
        this.raisonSociale = raisonSociale;
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

    public InstantFilter getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(InstantFilter dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public StringFilter getZoneIpres() {
        return zoneIpres;
    }

    public void setZoneIpres(StringFilter zoneIpres) {
        this.zoneIpres = zoneIpres;
    }

    public StringFilter getAgenceCss() {
        return agenceCss;
    }

    public void setAgenceCss(StringFilter agenceCss) {
        this.agenceCss = agenceCss;
    }

    public StringFilter getAgenceIpres() {
        return agenceIpres;
    }

    public void setAgenceIpres(StringFilter agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public StringFilter getTauxAt() {
        return tauxAt;
    }

    public void setTauxAt(StringFilter tauxAt) {
        this.tauxAt = tauxAt;
    }

    public StringFilter getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(StringFilter registreCommerce) {
        this.registreCommerce = registreCommerce;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImmatriculationRecupereeCriteria that = (ImmatriculationRecupereeCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(moyenConfirmation, that.moyenConfirmation) &&
                Objects.equals(status, that.status) &&
                Objects.equals(statusdesc, that.statusdesc) &&
                Objects.equals(typeIdentifiant, that.typeIdentifiant) &&
                Objects.equals(numeroIdentifiant, that.numeroIdentifiant) &&
                Objects.equals(ninea, that.ninea) &&
                Objects.equals(numeroUnique, that.numeroUnique) &&
                Objects.equals(numeroCss, that.numeroCss) &&
                Objects.equals(raisonSociale, that.raisonSociale) &&
                Objects.equals(numeroIpres, that.numeroIpres) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(dateTraitement, that.dateTraitement) &&
                Objects.equals(date, that.date) &&
                Objects.equals(zoneIpres, that.zoneIpres) &&
                Objects.equals(agenceCss, that.agenceCss) &&
                Objects.equals(agenceIpres, that.agenceIpres) &&
                Objects.equals(tauxAt, that.tauxAt) &&
                Objects.equals(registreCommerce, that.registreCommerce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            moyenConfirmation,
            status,
            statusdesc,
            typeIdentifiant,
            numeroIdentifiant,
            ninea,
            numeroUnique,
            raisonSociale,
            numeroCss,
            numeroIpres,
            userId,
            agentId,
            createdAt,
            dateTraitement,
            date,
            zoneIpres,
            agenceCss,
            agenceIpres,
            tauxAt,
            registreCommerce
        );
    }

    @Override
    public String toString() {
        return "ImmatriculationRecupereeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (moyenConfirmation != null ? "moyenConfirmation=" + moyenConfirmation + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (statusdesc != null ? "statusdesc=" + statusdesc + ", " : "") +
            (typeIdentifiant != null ? "typeIdentifiant=" + typeIdentifiant + ", " : "") +
            (numeroIdentifiant != null ? "numeroIdentifiant=" + numeroIdentifiant + ", " : "") +
            (ninea != null ? "ninea=" + ninea + ", " : "") +
            (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
            (numeroCss != null ? "numeroCss=" + numeroCss + ", " : "") +
            (numeroIpres != null ? "numeroIpres=" + numeroIpres + ", " : "") +
            (raisonSociale != null ? "raisonSociale=" + raisonSociale + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (agentId != null ? "agentId=" + agentId + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (dateTraitement != null ? "dateTraitement=" + dateTraitement + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (zoneIpres != null ? "zoneIpres=" + zoneIpres + ", " : "") +
            (agenceCss != null ? "agenceCss=" + agenceCss + ", " : "") +
            (agenceIpres != null ? "agenceIpres=" + agenceIpres + ", " : "") +
            (tauxAt != null ? "tauxAt=" + tauxAt + ", " : "") +
            (registreCommerce != null ? "registreCommerce=" + registreCommerce + ", " : "") +
            "}";
    }

}
