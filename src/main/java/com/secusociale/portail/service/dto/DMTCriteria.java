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
 * Criteria class for the {@link com.secusociale.portail.domain.DMT} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.DMTResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dmts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DMTCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter idEmployeur;

    private StringFilter status;

    private InstantFilter date;

    private LongFilter userId;

    private StringFilter raisonSocial;

    public DMTCriteria() {
    }

    public DMTCriteria(DMTCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.idEmployeur = other.idEmployeur == null ? null : other.idEmployeur.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.raisonSocial = other.raisonSocial == null ? null : other.raisonSocial.copy();
    }

    @Override
    public DMTCriteria copy() {
        return new DMTCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getIdEmployeur() {
        return idEmployeur;
    }

    public void setIdEmployeur(StringFilter idEmployeur) {
        this.idEmployeur = idEmployeur;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(StringFilter raisonSocial) {
        this.raisonSocial = raisonSocial;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DMTCriteria that = (DMTCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(idEmployeur, that.idEmployeur) &&
            Objects.equals(status, that.status) &&
            Objects.equals(date, that.date) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(raisonSocial, that.raisonSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        idEmployeur,
        status,
        date,
        userId,
        raisonSocial
        );
    }

    @Override
    public String toString() {
        return "DMTCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idEmployeur != null ? "idEmployeur=" + idEmployeur + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (raisonSocial != null ? "raisonSocial=" + raisonSocial + ", " : "") +
            "}";
    }

}
