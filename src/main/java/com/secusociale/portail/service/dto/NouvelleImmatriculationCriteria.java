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
 * Criteria class for the {@link com.secusociale.portail.domain.NouvelleImmatriculation} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.NouvelleImmatriculationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nouvelle-immatriculations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NouvelleImmatriculationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter type;

    private StringFilter numdoc;

    private StringFilter statusdoc;

    private StringFilter statusdocp;

    private StringFilter statusdesc;

    private StringFilter typeIdentifiant;

    private StringFilter ninea;

    private StringFilter numeroUnique;

    private StringFilter numeroCss;

    private StringFilter numeroIpres;

    private LongFilter user;

    private BooleanFilter isSubmit;

    private InstantFilter createdAt;

    private StringFilter employerRegistrationFormId;

    private StringFilter employeeRegistrationFormId;

    private StringFilter zoneIpres;

    private StringFilter agenceCss;

    private StringFilter agenceIpres;

    private StringFilter tauxAt;

    private StringFilter registreCommerce;

    private StringFilter globalSearch;

    private LongFilter agenceIpresId;

    private LongFilter agenceCssId;


    public NouvelleImmatriculationCriteria() {
    }

    public NouvelleImmatriculationCriteria(NouvelleImmatriculationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.numdoc = other.numdoc == null ? null : other.numdoc.copy();
        this.statusdoc = other.statusdoc == null ? null : other.statusdoc.copy();
        this.statusdocp = other.statusdocp == null ? null : other.statusdocp.copy();
        this.statusdesc = other.statusdesc == null ? null : other.statusdesc.copy();
        this.typeIdentifiant = other.typeIdentifiant == null ? null : other.typeIdentifiant.copy();
        this.ninea = other.ninea == null ? null : other.ninea.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.numeroCss = other.numeroCss == null ? null : other.numeroCss.copy();
        this.numeroIpres = other.numeroIpres == null ? null : other.numeroIpres.copy();
        this.user = other.user == null ? null : other.user.copy();
        this.isSubmit = other.isSubmit == null ? null : other.isSubmit.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.employerRegistrationFormId = other.employerRegistrationFormId == null ? null : other.employerRegistrationFormId.copy();
        this.employeeRegistrationFormId = other.employeeRegistrationFormId == null ? null : other.employeeRegistrationFormId.copy();
        this.zoneIpres = other.zoneIpres == null ? null : other.zoneIpres.copy();
        this.agenceCss = other.agenceCss == null ? null : other.agenceCss.copy();
        this.agenceIpres = other.agenceIpres == null ? null : other.agenceIpres.copy();
        this.agenceIpresId = other.agenceIpresId == null ? null : other.agenceIpresId.copy();
        this.agenceCssId = other.agenceCssId == null ? null : other.agenceCssId.copy();
        this.tauxAt = other.tauxAt == null ? null : other.tauxAt.copy();
        this.registreCommerce = other.registreCommerce == null ? null : other.registreCommerce.copy();
        this.globalSearch = other.globalSearch == null ? null : other.globalSearch.copy();
    }

    @Override
    public NouvelleImmatriculationCriteria copy() {
        return new NouvelleImmatriculationCriteria(this);
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

    public StringFilter getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(StringFilter numdoc) {
        this.numdoc = numdoc;
    }

    public StringFilter getStatusdoc() {
        return statusdoc;
    }

    public void setStatusdoc(StringFilter statusdoc) {
        this.statusdoc = statusdoc;
    }

    public StringFilter getStatusdocp() {
        return statusdocp;
    }

    public void setStatusdocp(StringFilter statusdocp) {
        this.statusdocp = statusdocp;
    }

    public StringFilter getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(StringFilter statusdesc) {
        this.statusdesc = statusdesc;
    }

    public StringFilter getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(StringFilter typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public StringFilter getNinea() {
        return ninea;
    }

    public void setNinea(StringFilter ninea) {
        this.ninea = ninea;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getNumeroCss() {
        return numeroCss;
    }

    public void setNumeroCss(StringFilter numeroCss) {
        this.numeroCss = numeroCss;
    }

    public StringFilter getNumeroIpres() {
        return numeroIpres;
    }

    public void setNumeroIpres(StringFilter numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public LongFilter getUser() {
        return user;
    }

    public void setUser(LongFilter user) {
        this.user = user;
    }

    public BooleanFilter getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(BooleanFilter isSubmit) {
        this.isSubmit = isSubmit;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public StringFilter getEmployerRegistrationFormId() {
        return employerRegistrationFormId;
    }

    public void setEmployerRegistrationFormId(StringFilter employerRegistrationFormId) {
        this.employerRegistrationFormId = employerRegistrationFormId;
    }

    public StringFilter getEmployeeRegistrationFormId() {
        return employeeRegistrationFormId;
    }

    public void setEmployeeRegistrationFormId(StringFilter employeeRegistrationFormId) {
        this.employeeRegistrationFormId = employeeRegistrationFormId;
    }

    public StringFilter getZoneIpres() {
        return zoneIpres;
    }

    public void setZoneIpres(StringFilter zoneIpres) {
        this.zoneIpres = zoneIpres;
    }

    public StringFilter getAgenceCss() {
        return agenceCss;
    }

    public void setAgenceCss(StringFilter agenceCss) {
        this.agenceCss = agenceCss;
    }

    public StringFilter getAgenceIpres() {
        return agenceIpres;
    }

    public void setAgenceIpres(StringFilter agenceIpres) {
        this.agenceIpres = agenceIpres;
    }

    public LongFilter getAgenceIpresId() {
        return agenceIpresId;
    }

    public void setAgenceIpresId(LongFilter agenceIpresId) {
        this.agenceIpresId = agenceIpresId;
    }

    public LongFilter getAgenceCssId() {
        return agenceCssId;
    }

    public void setAgenceCssId(LongFilter agenceCssId) {
        this.agenceCssId = agenceCssId;
    }

    public StringFilter getTauxAt() {
        return tauxAt;
    }

    public void setTauxAt(StringFilter tauxAt) {
        this.tauxAt = tauxAt;
    }

    public StringFilter getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(StringFilter registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public StringFilter getGlobalSearch() {
        return globalSearch;
    }

    public void setGlobalSearch(StringFilter globalSearch) {
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
        final NouvelleImmatriculationCriteria that = (NouvelleImmatriculationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(type, that.type) &&
            Objects.equals(numdoc, that.numdoc) &&
            Objects.equals(statusdoc, that.statusdoc) &&
            Objects.equals(statusdocp, that.statusdocp) &&
            Objects.equals(statusdesc, that.statusdesc) &&
            Objects.equals(typeIdentifiant, that.typeIdentifiant) &&
            Objects.equals(ninea, that.ninea) &&
            Objects.equals(numeroUnique, that.numeroUnique) &&
            Objects.equals(numeroCss, that.numeroCss) &&
            Objects.equals(numeroIpres, that.numeroIpres) &&
            Objects.equals(user, that.user) &&
            Objects.equals(isSubmit, that.isSubmit) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(employerRegistrationFormId, that.employerRegistrationFormId) &&
            Objects.equals(employeeRegistrationFormId, that.employeeRegistrationFormId) &&
            Objects.equals(zoneIpres, that.zoneIpres) &&
            Objects.equals(agenceCss, that.agenceCss) &&
            Objects.equals(agenceIpres, that.agenceIpres) &&
            Objects.equals(tauxAt, that.tauxAt) &&
            Objects.equals(registreCommerce, that.registreCommerce) &&
            Objects.equals(globalSearch, that.globalSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        type,
        numdoc,
        statusdoc,
        statusdocp,
        statusdesc,
        typeIdentifiant,
        ninea,
        numeroUnique,
        numeroCss,
        numeroIpres,
        user,
        isSubmit,
        createdAt,
        employerRegistrationFormId,
        employeeRegistrationFormId,
        zoneIpres,
        agenceCss,
        agenceIpres,
        tauxAt,
        registreCommerce,
        agenceCssId,
        agenceIpresId,
        globalSearch
        );
    }

    @Override
    public String toString() {
        return "NouvelleImmatriculationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (numdoc != null ? "numdoc=" + numdoc + ", " : "") +
                (statusdoc != null ? "statusdoc=" + statusdoc + ", " : "") +
                (statusdocp != null ? "statusdocp=" + statusdocp + ", " : "") +
                (statusdesc != null ? "statusdesc=" + statusdesc + ", " : "") +
                (typeIdentifiant != null ? "typeIdentifiant=" + typeIdentifiant + ", " : "") +
                (ninea != null ? "ninea=" + ninea + ", " : "") +
                (numeroUnique != null ? "numeroUnique=" + numeroUnique + ", " : "") +
                (numeroCss != null ? "numeroCss=" + numeroCss + ", " : "") +
                (numeroIpres != null ? "numeroIpres=" + numeroIpres + ", " : "") +
                (user != null ? "user=" + user + ", " : "") +
                (isSubmit != null ? "isSubmit=" + isSubmit + ", " : "") +
                (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
                (employerRegistrationFormId != null ? "employerRegistrationFormId=" + employerRegistrationFormId + ", " : "") +
                (employeeRegistrationFormId != null ? "employeeRegistrationFormId=" + employeeRegistrationFormId + ", " : "") +
                (zoneIpres != null ? "zoneIpres=" + zoneIpres + ", " : "") +
                (agenceCss != null ? "agenceCss=" + agenceCss + ", " : "") +
                (agenceIpres != null ? "agenceIpres=" + agenceIpres + ", " : "") +
                (agenceIpresId != null ? "agenceIpresId=" + agenceIpresId + ", " : "") +
                (agenceCssId != null ? "agenceCssId=" + agenceCssId + ", " : "") +
                (tauxAt != null ? "tauxAt=" + tauxAt + ", " : "") +
                (registreCommerce != null ? "registreCommerce=" + registreCommerce + ", " : "") +
                (globalSearch != null ? "globalSearch=" + globalSearch + ", " : "") +

            "}";
    }

}
