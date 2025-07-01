package com.secusociale.portail.service.dto.dialogue;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.Configcompte} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.ConfigcompteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /configcomptes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ConversationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;
    private LongFilter userInit;
    private StringFilter status;

    private StringFilter title;

    private StringFilter agence;

    private InstantFilter createdDate;

    public ConversationCriteria() {
    }

    public ConversationCriteria(ConversationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userInit = other.userInit == null ? null : other.userInit.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.agence = other.agence == null ? null : other.agence.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
    }

    @Override
    public ConversationCriteria copy() {
        return new ConversationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }


    public LongFilter getUserInit() {
        return userInit;
    }

    public void setUserInit(LongFilter userInit) {
        this.userInit = userInit;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getAgence() {
        return agence;
    }

    public void setAgence(StringFilter agence) {
        this.agence = agence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ConversationCriteria that = (ConversationCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(userInit, that.userInit) &&
                Objects.equals(title, that.title) &&
                Objects.equals(status, that.status) &&
                Objects.equals(agence, that.agence) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            userInit,
            title,
            status,
            agence,
            createdDate
        );
    }

    @Override
    public String toString() {
        return "ConfigcompteCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (userInit != null ? "accountNumber=" + userInit + ", " : "") +
            (agence != null ? "accountHolderName=" + agence + ", " : "") +
            (title != null ? "managerPhoneNumber=" + title + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            "}";
    }

}
