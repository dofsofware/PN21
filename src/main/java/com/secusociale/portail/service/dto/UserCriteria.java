package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class UserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter firstName;
    private StringFilter lastName;
    private StringFilter email;
    private StringFilter login;
    private StringFilter phone;
    private StringFilter typeCompte;
    private StringFilter institution;
    private StringFilter agence;
    private StringFilter role;
    private InstantFilter createdDate;
    private BooleanFilter activated;

    public UserCriteria() {
    }

    public UserCriteria(UserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.typeCompte = other.typeCompte == null ? null : other.typeCompte.copy();
        this.institution = other.institution == null ? null : other.institution.copy();
        this.agence = other.agence == null ? null : other.agence.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
    }

    @Override
    public UserCriteria copy() {
        return new UserCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public void setLogin(StringFilter login) {
        this.login = login;
    }

    public StringFilter getLogin() {
        return login;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public StringFilter getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(StringFilter typeCompte) {
        this.typeCompte = typeCompte;
    }

    public StringFilter getInstitution() {
        return institution;
    }

    public void setInstitution(StringFilter institution) {
        this.institution = institution;
    }

    public StringFilter getAgence() {
        return agence;
    }

    public void setAgence(StringFilter agence) {
        this.agence = agence;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public StringFilter getRole() {
        return role;
    }

    public void setRole(StringFilter role) {
        this.role = role;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }
    public BooleanFilter getActivated() {
        return activated;
    }

    public void setActivated(BooleanFilter activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserCriteria that = (UserCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(login, that.login) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(typeCompte, that.typeCompte) &&
                Objects.equals(institution, that.institution) &&
                Objects.equals(agence, that.agence) &&
                Objects.equals(role, that.role) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            firstName,
            lastName,
            email,
            login,
            phone,
            typeCompte,
            phone,
            institution,
            agence,
            role,
            createdDate
        );
    }

    @Override
    public String toString() {
        return "UserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (firstName != null ? "code=" + firstName + ", " : "") +
            (lastName != null ? "nom=" + lastName + ", " : "") +
            (email != null ? "description=" + email + ", " : "") +
            (login != null ? "description=" + login + ", " : "") +
            (phone != null ? "adresse=" + phone + ", " : "") +
            (typeCompte != null ? "typeCompte=" + typeCompte + ", " : "") +
            (institution != null ? "institution=" + institution + ", " : "") +
            (agence != null ? "agence=" + agence + ", " : "") +
            (role != null ? "role=" + role + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            "}";
    }

}
