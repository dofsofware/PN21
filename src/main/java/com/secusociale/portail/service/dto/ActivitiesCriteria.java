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
 * Criteria class for the {@link com.secusociale.portail.domain.Activities} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.ActivitiesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /activities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ActivitiesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter userId;

    private StringFilter operation;

    private InstantFilter dateOperation;

    private StringFilter typeObjet;

    public ActivitiesCriteria() {
    }

    public ActivitiesCriteria(ActivitiesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.operation = other.operation == null ? null : other.operation.copy();
        this.dateOperation = other.dateOperation == null ? null : other.dateOperation.copy();
        this.typeObjet = other.typeObjet == null ? null : other.typeObjet.copy();
    }

    @Override
    public ActivitiesCriteria copy() {
        return new ActivitiesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getOperation() {
        return operation;
    }

    public void setOperation(StringFilter operation) {
        this.operation = operation;
    }

    public InstantFilter getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(InstantFilter dateOperation) {
        this.dateOperation = dateOperation;
    }

    public StringFilter getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(StringFilter typeObjet) {
        this.typeObjet = typeObjet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ActivitiesCriteria that = (ActivitiesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(operation, that.operation) &&
            Objects.equals(dateOperation, that.dateOperation) &&
            Objects.equals(typeObjet, that.typeObjet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        userId,
        operation,
        dateOperation,
        typeObjet
        );
    }

    @Override
    public String toString() {
        return "ActivitiesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (operation != null ? "operation=" + operation + ", " : "") +
                (dateOperation != null ? "dateOperation=" + dateOperation + ", " : "") +
                (typeObjet != null ? "typeObjet=" + typeObjet + ", " : "") +
            "}";
    }

}
