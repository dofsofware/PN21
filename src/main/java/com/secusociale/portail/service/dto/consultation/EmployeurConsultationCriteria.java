package com.secusociale.portail.service.dto.consultation;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.consultation.EmployeurConsultation} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.consultation.ConsultationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /activities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeurConsultationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter userId;
    private LongFilter agentId;

    private StringFilter numeroUnique;
    private StringFilter numeroCni;
    private StringFilter prenom;
    private StringFilter nom;
    private StringFilter telephone;
    private StringFilter email;

    private InstantFilter createdAt;
    private InstantFilter validateAt;


    public EmployeurConsultationCriteria() {
    }

    public EmployeurConsultationCriteria(EmployeurConsultationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.agentId = other.agentId == null ? null : other.agentId.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.numeroCni = other.numeroCni == null ? null : other.numeroCni.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.validateAt = other.validateAt == null ? null : other.validateAt.copy();
    }

    @Override
    public EmployeurConsultationCriteria copy() {
        return new EmployeurConsultationCriteria(this);
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

    public LongFilter getAgentId() {
        return agentId;
    }

    public void setAgentId(LongFilter agentId) {
        this.agentId = agentId;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getNumeroCni() {
        return numeroCni;
    }

    public void setNumeroCni(StringFilter numeroCni) {
        this.numeroCni = numeroCni;
    }

    public StringFilter getPrenom() {
        return prenom;
    }

    public void setPrenom(StringFilter prenom) {
        this.prenom = prenom;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public InstantFilter getValidateAt() {
        return validateAt;
    }

    public void setValidateAt(InstantFilter validateAt) {
        this.validateAt = validateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeurConsultationCriteria that = (EmployeurConsultationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(agentId, that.agentId) &&
            Objects.equals(numeroUnique, that.numeroUnique) &&
            Objects.equals(numeroCni, that.numeroCni) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(validateAt, that.validateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        userId,
        agentId,
        numeroUnique,
        numeroCni,
        prenom,
        nom,
        telephone,
        email,
        createdAt,
        validateAt
        );
    }

    @Override
    public String toString() {
        return "EmployeurConsultationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (agentId != null ? "agentId=" + agentId + ", " : "") +
                (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
                (numeroCni != null ? "numeroCni=" + numeroCni + ", " : "") +
                (prenom != null ? "prenom=" + prenom + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
                (validateAt != null ? "validateAt=" + validateAt + ", " : "") +
            "}";
    }

}
