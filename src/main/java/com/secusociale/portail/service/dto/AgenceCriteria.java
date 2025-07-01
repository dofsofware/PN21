package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class AgenceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;
    private StringFilter nom;
    private StringFilter description;
    private StringFilter adresse;
    private StringFilter institution;

    public AgenceCriteria() {
    }

    public AgenceCriteria(AgenceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.adresse = other.adresse == null ? null : other.adresse.copy();
        this.institution = other.institution == null ? null : other.institution.copy();
    }

    @Override
    public AgenceCriteria copy() {
        return new AgenceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getAdresse() {
        return adresse;
    }

    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }

    public StringFilter getInstitution() {
        return institution;
    }

    public void setInstitution(StringFilter institution) {
        this.institution = institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AgenceCriteria that = (AgenceCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(description, that.description) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(institution, that.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            code,
            nom,
            description,
            adresse,
            institution
        );
    }

    @Override
    public String toString() {
        return "AgenceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (nom != null ? "nom=" + nom + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (adresse != null ? "adresse=" + adresse + ", " : "") +
            (institution != null ? "institution=" + institution + ", " : "") +
            "}";
    }

}
