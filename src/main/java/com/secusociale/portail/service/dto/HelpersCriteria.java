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
 * Criteria class for the {@link com.secusociale.portail.domain.Helpers} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.HelpersResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /helpers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HelpersCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter user;

    private InstantFilter date;

    public HelpersCriteria() {
    }

    public HelpersCriteria(HelpersCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.user = other.user == null ? null : other.user.copy();
        this.date = other.date == null ? null : other.date.copy();
    }

    @Override
    public HelpersCriteria copy() {
        return new HelpersCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUser() {
        return user;
    }

    public void setUser(StringFilter user) {
        this.user = user;
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
        final HelpersCriteria that = (HelpersCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(user, that.user) &&
            Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        user,
        date
        );
    }

    @Override
    public String toString() {
        return "HelpersCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (user != null ? "user=" + user + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
            "}";
    }

}
