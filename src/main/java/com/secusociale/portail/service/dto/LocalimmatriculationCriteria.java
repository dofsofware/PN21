package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.Localimmatriculation} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.LocalimmatriculationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /old-immatriculations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LocalimmatriculationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter type;

    private StringFilter numdoc;

    private StringFilter statusdoc;

    private StringFilter statusdesc;

    private StringFilter payload;

    private LongFilter user;

    private LongFilter employeur;

    private BooleanFilter isSubmit;

    private StringFilter ninea;

    private StringFilter typeIdentifiant;

    private InstantFilter createdAt;

    public LocalimmatriculationCriteria() {
    }

    public LocalimmatriculationCriteria(LocalimmatriculationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.numdoc = other.numdoc == null ? null : other.numdoc.copy();
        this.statusdoc = other.statusdoc == null ? null : other.statusdoc.copy();
        this.statusdesc = other.statusdesc == null ? null : other.statusdesc.copy();
        this.payload = other.payload == null ? null : other.payload.copy();
        this.user = other.user == null ? null : other.user.copy();
        this.employeur = other.employeur == null ? null : other.employeur.copy();
        this.isSubmit = other.isSubmit == null ? null : other.isSubmit.copy();
        this.ninea = other.ninea == null ? null : other.ninea.copy();
        this.typeIdentifiant = other.typeIdentifiant == null ? null : other.typeIdentifiant.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
    }

    @Override
    public LocalimmatriculationCriteria copy() {
        return new LocalimmatriculationCriteria(this);
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

    public StringFilter getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(StringFilter numdoc) {
        this.numdoc = numdoc;
    }

    public StringFilter getStatusdoc() {
        return statusdoc;
    }

    public void setStatusdoc(StringFilter statusdoc) {
        this.statusdoc = statusdoc;
    }

    public StringFilter getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(StringFilter statusdesc) {
        this.statusdesc = statusdesc;
    }

    public StringFilter getPayload() {
        return payload;
    }

    public void setPayload(StringFilter payload) {
        this.payload = payload;
    }

    public LongFilter getUser() {
        return user;
    }

    public void setUser(LongFilter user) {
        this.user = user;
    }

    public LongFilter getEmployeur() {
        return employeur;
    }

    public void setEmployeur(LongFilter employeur) {
        this.employeur = employeur;
    }

    public BooleanFilter getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(BooleanFilter isSubmit) {
        this.isSubmit = isSubmit;
    }

    public StringFilter getNinea() {
        return ninea;
    }

    public void setNinea(StringFilter ninea) {
        this.ninea = ninea;
    }

    public StringFilter getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(StringFilter typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalimmatriculationCriteria)) return false;
        LocalimmatriculationCriteria that = (LocalimmatriculationCriteria) o;
        return getId().equals(that.getId()) && getTypeIdentifiant().equals(that.getTypeIdentifiant()) && getType().equals(that.getType()) && getStatusdoc().equals(that.getStatusdoc()) && Objects.equals(getStatusdesc(), that.getStatusdesc()) && getNumdoc().equals(that.getNumdoc()) && Objects.equals(getUser(), that.getUser()) && getEmployeur().equals(that.getEmployeur()) && Objects.equals(getIsSubmit(), that.getIsSubmit()) && Objects.equals(getPayload(), that.getPayload()) && Objects.equals(getNinea(), that.getNinea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            type,
            numdoc,
            statusdoc,
            statusdesc,
            payload,
            user,
            employeur,
            isSubmit,
            ninea,
            typeIdentifiant,
            createdAt
        );
    }

    @Override
    public String toString() {
        return "LocalimmatriculationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (numdoc != null ? "numdoc=" + numdoc + ", " : "") +
            (statusdoc != null ? "statusdoc=" + statusdoc + ", " : "") +
            (statusdesc != null ? "statusdesc=" + statusdesc + ", " : "") +
            (payload != null ? "payload=" + payload + ", " : "") +
            (user != null ? "user=" + user + ", " : "") +
            (employeur != null ? "employeur=" + employeur + ", " : "") +
            (isSubmit != null ? "isSubmit=" + isSubmit + ", " : "") +
            (ninea != null ? "ninea=" + ninea + ", " : "") +
            (typeIdentifiant != null ? "typeIdentifiant=" + typeIdentifiant + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            "}";
    }

}
