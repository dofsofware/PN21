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
 * Criteria class for the {@link com.secusociale.portail.domain.GedUpdate} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.GedUpdateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ged-updates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GedUpdateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter oldPath;

    private StringFilter newPath;

    private LongFilter idOld;

    private StringFilter numeroUnique;

    private InstantFilter date;

    public GedUpdateCriteria() {
    }

    public GedUpdateCriteria(GedUpdateCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.oldPath = other.oldPath == null ? null : other.oldPath.copy();
        this.newPath = other.newPath == null ? null : other.newPath.copy();
        this.idOld = other.idOld == null ? null : other.idOld.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.date = other.date == null ? null : other.date.copy();
    }

    @Override
    public GedUpdateCriteria copy() {
        return new GedUpdateCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOldPath() {
        return oldPath;
    }

    public void setOldPath(StringFilter oldPath) {
        this.oldPath = oldPath;
    }

    public StringFilter getNewPath() {
        return newPath;
    }

    public void setNewPath(StringFilter newPath) {
        this.newPath = newPath;
    }

    public LongFilter getIdOld() {
        return idOld;
    }

    public void setIdOld(LongFilter idOld) {
        this.idOld = idOld;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GedUpdateCriteria that = (GedUpdateCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(oldPath, that.oldPath) &&
            Objects.equals(newPath, that.newPath) &&
            Objects.equals(idOld, that.idOld) &&
            Objects.equals(numeroUnique, that.numeroUnique) &&
            Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        oldPath,
        newPath,
        idOld,
        numeroUnique,
        date
        );
    }

    @Override
    public String toString() {
        return "GedUpdateCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (oldPath != null ? "oldPath=" + oldPath + ", " : "") +
                (newPath != null ? "newPath=" + newPath + ", " : "") +
                (idOld != null ? "idOld=" + idOld + ", " : "") +
                (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
            "}";
    }

}
