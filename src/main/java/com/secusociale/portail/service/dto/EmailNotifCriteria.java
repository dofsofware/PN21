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
 * Criteria class for the {@link com.secusociale.portail.domain.EmailNotif} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.EmailNotifResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /email-notifs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmailNotifCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter groupe;

    private BooleanFilter active;

    private InstantFilter lastSendDate;

    public EmailNotifCriteria() {
    }

    public EmailNotifCriteria(EmailNotifCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.groupe = other.groupe == null ? null : other.groupe.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.lastSendDate = other.lastSendDate == null ? null : other.lastSendDate.copy();
    }

    @Override
    public EmailNotifCriteria copy() {
        return new EmailNotifCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getGroupe() {
        return groupe;
    }

    public void setGroupe(StringFilter groupe) {
        this.groupe = groupe;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public InstantFilter getLastSendDate() {
        return lastSendDate;
    }

    public void setLastSendDate(InstantFilter lastSendDate) {
        this.lastSendDate = lastSendDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmailNotifCriteria that = (EmailNotifCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupe, that.groupe) &&
            Objects.equals(active, that.active) &&
            Objects.equals(lastSendDate, that.lastSendDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupe,
        active,
        lastSendDate
        );
    }

    @Override
    public String toString() {
        return "EmailNotifCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupe != null ? "groupe=" + groupe + ", " : "") +
                (active != null ? "active=" + active + ", " : "") +
                (lastSendDate != null ? "lastSendDate=" + lastSendDate + ", " : "") +
            "}";
    }

}
