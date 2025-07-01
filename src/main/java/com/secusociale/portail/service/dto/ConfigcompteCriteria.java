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
 * Criteria class for the {@link com.secusociale.portail.domain.Configcompte} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.ConfigcompteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /configcomptes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ConfigcompteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter accountNumber;

    private StringFilter accountHolderName;

    private StringFilter managerPhoneNumber;

    private StringFilter managerEmail;

    private StringFilter senderFirstName;

    private StringFilter agenceCode;

    private BooleanFilter active;

    private InstantFilter createdDate;

    private StringFilter signatairePhoneNumber;

    private StringFilter signataireEmail;

    private StringFilter managerFirstname;

    private StringFilter managerLastname;

    private LongFilter agenceId;

    public ConfigcompteCriteria() {
    }

    public ConfigcompteCriteria(ConfigcompteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.accountNumber = other.accountNumber == null ? null : other.accountNumber.copy();
        this.accountHolderName = other.accountHolderName == null ? null : other.accountHolderName.copy();
        this.managerPhoneNumber = other.managerPhoneNumber == null ? null : other.managerPhoneNumber.copy();
        this.managerEmail = other.managerEmail == null ? null : other.managerEmail.copy();
        this.senderFirstName = other.senderFirstName == null ? null : other.senderFirstName.copy();
        this.agenceCode = other.agenceCode == null ? null : other.agenceCode.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.signatairePhoneNumber = other.signatairePhoneNumber == null ? null : other.signatairePhoneNumber.copy();
        this.signataireEmail = other.signataireEmail == null ? null : other.signataireEmail.copy();
        this.managerFirstname = other.managerFirstname == null ? null : other.managerFirstname.copy();
        this.managerLastname = other.managerLastname == null ? null : other.managerLastname.copy();
        this.agenceId = other.agenceId == null ? null : other.agenceId.copy();
    }

    @Override
    public ConfigcompteCriteria copy() {
        return new ConfigcompteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public StringFilter getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(StringFilter accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public StringFilter getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(StringFilter managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public StringFilter getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(StringFilter managerEmail) {
        this.managerEmail = managerEmail;
    }

    public StringFilter getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(StringFilter senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public StringFilter getAgenceCode() {
        return agenceCode;
    }

    public void setAgenceCode(StringFilter agenceCode) {
        this.agenceCode = agenceCode;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getSignatairePhoneNumber() {
        return signatairePhoneNumber;
    }

    public void setSignatairePhoneNumber(StringFilter signatairePhoneNumber) {
        this.signatairePhoneNumber = signatairePhoneNumber;
    }

    public StringFilter getSignataireEmail() {
        return signataireEmail;
    }

    public void setSignataireEmail(StringFilter signataireEmail) {
        this.signataireEmail = signataireEmail;
    }

    public StringFilter getManagerFirstname() {
        return managerFirstname;
    }

    public void setManagerFirstname(StringFilter managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public StringFilter getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(StringFilter managerLastname) {
        this.managerLastname = managerLastname;
    }

    public LongFilter getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(LongFilter agenceId) {
        this.agenceId = agenceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ConfigcompteCriteria that = (ConfigcompteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(accountHolderName, that.accountHolderName) &&
            Objects.equals(managerPhoneNumber, that.managerPhoneNumber) &&
            Objects.equals(managerEmail, that.managerEmail) &&
            Objects.equals(senderFirstName, that.senderFirstName) &&
            Objects.equals(agenceCode, that.agenceCode) &&
            Objects.equals(active, that.active) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(signatairePhoneNumber, that.signatairePhoneNumber) &&
            Objects.equals(signataireEmail, that.signataireEmail) &&
            Objects.equals(managerFirstname, that.managerFirstname) &&
            Objects.equals(managerLastname, that.managerLastname) &&
            Objects.equals(agenceId, that.agenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        accountNumber,
        accountHolderName,
        managerPhoneNumber,
        managerEmail,
        senderFirstName,
        agenceCode,
        active,
        createdDate,
        signatairePhoneNumber,
        signataireEmail,
        managerFirstname,
        managerLastname,
        agenceId
        );
    }

    @Override
    public String toString() {
        return "ConfigcompteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
                (accountHolderName != null ? "accountHolderName=" + accountHolderName + ", " : "") +
                (managerPhoneNumber != null ? "managerPhoneNumber=" + managerPhoneNumber + ", " : "") +
                (managerEmail != null ? "managerEmail=" + managerEmail + ", " : "") +
                (senderFirstName != null ? "senderFirstName=" + senderFirstName + ", " : "") +
                (agenceCode != null ? "agenceCode=" + agenceCode + ", " : "") +
                (active != null ? "active=" + active + ", " : "") +
                (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
                (signatairePhoneNumber != null ? "signatairePhoneNumber=" + signatairePhoneNumber + ", " : "") +
                (signataireEmail != null ? "signataireEmail=" + signataireEmail + ", " : "") +
                (managerFirstname != null ? "managerFirstname=" + managerFirstname + ", " : "") +
                (managerLastname != null ? "managerLastname=" + managerLastname + ", " : "") +
                (agenceId != null ? "agenceId=" + agenceId + ", " : "") +
            "}";
    }

}
