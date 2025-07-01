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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.GrappeMember} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.GrappeMemberResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /grappe-members?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 */
public class GrappeMemberCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter type;

    private LocalDateFilter dateNais;

    private LocalDateFilter dateMariage;

    private StringFilter sexe;

    private StringFilter origine;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter numeroCni;

    private LongFilter salarieId;
    private StringFilter globalSearch;

    public GrappeMemberCriteria() {
    }

    public GrappeMemberCriteria(GrappeMemberCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.dateNais = other.dateNais == null ? null : other.dateNais.copy();
        this.dateMariage = other.dateMariage == null ? null : other.dateMariage.copy();
        this.sexe = other.sexe == null ? null : other.sexe.copy();
        this.origine = other.origine == null ? null : other.origine.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.numeroCni = other.numeroCni == null ? null : other.numeroCni.copy();
        this.salarieId = other.salarieId == null ? null : other.salarieId.copy();
        this.globalSearch = other.globalSearch == null ? null : other.globalSearch.copy(); // Copie pour le champ globalSearch
    }

    @Override
    public GrappeMemberCriteria copy() {
        return new GrappeMemberCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public LocalDateFilter getDateNais() {
        return dateNais;
    }

    public void setDateNais(LocalDateFilter dateNais) {
        this.dateNais = dateNais;
    }

    public LocalDateFilter getDateMariage() {
        return dateMariage;
    }

    public void setDateMariage(LocalDateFilter dateMariage) {
        this.dateMariage = dateMariage;
    }

    public StringFilter getSexe() {
        return sexe;
    }

    public void setSexe(StringFilter sexe) {
        this.sexe = sexe;
    }

    public StringFilter getOrigine() {
        return origine;
    }

    public void setOrigine(StringFilter origine) {
        this.origine = origine;
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

    public StringFilter getNumeroCni() {
        return numeroCni;
    }

    public void setNumeroCni(StringFilter numeroCni) {
        this.numeroCni = numeroCni;
    }

    public LongFilter getSalarieId() {
        return salarieId;
    }

    public void setSalarieId(LongFilter salarieId) {
        this.salarieId = salarieId;
    }

    public StringFilter getGlobalSearch() { // Getter pour globalSearch
        return globalSearch;
    }

    public void setGlobalSearch(StringFilter globalSearch) { // Setter pour globalSearch
        this.globalSearch = globalSearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GrappeMemberCriteria that = (GrappeMemberCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(type, that.type) &&
            Objects.equals(dateNais, that.dateNais) &&
            Objects.equals(dateMariage, that.dateMariage) &&
            Objects.equals(sexe, that.sexe) &&
            Objects.equals(origine, that.origine) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(numeroCni, that.numeroCni) &&
            Objects.equals(salarieId, that.salarieId) &&
            Objects.equals(globalSearch, that.globalSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        type,
        dateNais,
        dateMariage,
        sexe,
        origine,
        firstName,
        lastName,
        numeroCni,
        salarieId,
        globalSearch
        );
    }

    @Override
    public String toString() {
        return "GrappeMemberCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (dateNais != null ? "dateNais=" + dateNais + ", " : "") +
            (dateMariage != null ? "dateMariage=" + dateMariage + ", " : "") +
            (sexe != null ? "sexe=" + sexe + ", " : "") +
            (origine != null ? "origine=" + origine + ", " : "") +
            (firstName != null ? "firstName=" + firstName + ", " : "") +
            (lastName != null ? "lastName=" + lastName + ", " : "") +
            (numeroCni != null ? "numeroCni=" + numeroCni + ", " : "") +
            (salarieId != null ? "salarieId=" + salarieId + ", " : "") +
            (globalSearch != null ? "globalSearch=" + globalSearch + ", " : "") + // Ajout de globalSearch
            "}";
    }

}
