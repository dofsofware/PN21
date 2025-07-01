package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.OldImmatriculation} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.OldImmatriculationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /old-immatriculations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OldImmatriculationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter typeIdentifiant;

    private StringFilter numeroIdentifiant;

    private StringFilter numeroUnique;

    private StringFilter status;

    private StringFilter moyenConfirmation;


    private InstantFilter date;

    private StringFilter numeroRC;

    private LongFilter userId;

    private StringFilter agenceCSS;

    private StringFilter agenceIPRES;

    private LongFilter agentId;

    public OldImmatriculationCriteria() {
    }

    public OldImmatriculationCriteria(OldImmatriculationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeIdentifiant = other.typeIdentifiant == null ? null : other.typeIdentifiant.copy();
        this.numeroIdentifiant = other.numeroIdentifiant == null ? null : other.numeroIdentifiant.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.moyenConfirmation = other.moyenConfirmation == null ? null : other.moyenConfirmation.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.numeroRC = other.numeroRC == null ? null : other.numeroRC.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.agenceCSS = other.agenceCSS == null ? null : other.agenceCSS.copy();
        this.agenceIPRES = other.agenceIPRES == null ? null : other.agenceIPRES.copy();
        this.agentId = other.agentId == null ? null : other.agentId.copy();
    }

    @Override
    public OldImmatriculationCriteria copy() {
        return new OldImmatriculationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(StringFilter typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public StringFilter getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public void setNumeroIdentifiant(StringFilter numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public void setMoyenConfirmation(StringFilter moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public InstantFilter getDate() {
        return date;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public StringFilter getNumeroRC() {
        return numeroRC;
    }

    public void setNumeroRC(StringFilter numeroRC) {
        this.numeroRC = numeroRC;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getAgenceCSS() {
        return agenceCSS;
    }

    public void setAgenceCSS(StringFilter agenceCSS) {
        this.agenceCSS = agenceCSS;
    }

    public StringFilter getAgenceIPRES() {
        return agenceIPRES;
    }

    public void setAgenceIPRES(StringFilter agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
    }

    public LongFilter getAgentId() {
        return agentId;
    }

    public void setAgentId(LongFilter agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OldImmatriculationCriteria)) return false;
        OldImmatriculationCriteria that = (OldImmatriculationCriteria) o;
        return getId().equals(that.getId()) && getTypeIdentifiant().equals(that.getTypeIdentifiant()) && getNumeroIdentifiant().equals(that.getNumeroIdentifiant()) && getNumeroUnique().equals(that.getNumeroUnique()) && getStatus().equals(that.getStatus()) && Objects.equals(getMoyenConfirmation(), that.getMoyenConfirmation()) && getDate().equals(that.getDate()) && Objects.equals(getNumeroRC(), that.getNumeroRC()) && getUserId().equals(that.getUserId()) && Objects.equals(getAgenceCSS(), that.getAgenceCSS()) && Objects.equals(getAgenceIPRES(), that.getAgenceIPRES()) && Objects.equals(getAgentId(), that.getAgentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            typeIdentifiant,
            numeroIdentifiant,
            numeroUnique,
            status,
            moyenConfirmation,
            date,
            numeroRC,
            userId,
            agenceCSS,
            agenceIPRES,
            agentId
        );
    }

    @Override
    public String toString() {
        return "OldImmatriculationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (typeIdentifiant != null ? "typeIdentifiant=" + typeIdentifiant + ", " : "") +
            (numeroIdentifiant != null ? "numeroIdentifiant=" + numeroIdentifiant + ", " : "") +
            (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (moyenConfirmation != null ? "moyenConfirmation=" + moyenConfirmation + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (numeroRC != null ? "numeroRC=" + numeroRC + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (agenceCSS != null ? "agenceCSS=" + agenceCSS + ", " : "") +
            (agenceIPRES != null ? "agenceIPRES=" + agenceIPRES + ", " : "") +
            (agentId != null ? "agentId=" + agentId + ", " : "") +
            "}";
    }

}
