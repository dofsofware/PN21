package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.GrappeMember} entity.
 */
public class GrappeMemberDTO implements Serializable {

    private Long id;

    private String type;

    private LocalDate dateNais;

    private LocalDate dateMariage;

    private String sexe;

    private String origine;

    private String firstName;

    private String lastName;

    private String numeroCni;

    @Lob
    private String pj1;

    @Lob
    private String pj2;

    @Lob
    private String pj3;

    @Lob
    private String pj4;

    @Lob
    private String pj5;

    @Lob
    private String pj6;

    @Lob
    private String pj7;

    @Lob
    private String pj8;

    @Lob
    private String pj9;

    @Lob
    private String pj10;

    @Lob
    private String extraInfos;

    private SalarieDTO salarieObj;

    private StatutGrappeMembre statut;

    public void setSalarieObj(SalarieDTO salarieObj) {
        this.salarieObj = salarieObj;
    }

    public SalarieDTO getSalarieObj() {
        return salarieObj;
    }

    private Long salarieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateNais() {
        return dateNais;
    }

    public void setDateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
    }

    public LocalDate getDateMariage() {
        return dateMariage;
    }

    public void setDateMariage(LocalDate dateMariage) {
        this.dateMariage = dateMariage;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public StatutGrappeMembre getStatut() {
        return statut;
    }

    public void setStatut(StatutGrappeMembre statut) {
        this.statut = statut;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumeroCni() {
        return numeroCni;
    }

    public void setNumeroCni(String numeroCni) {
        this.numeroCni = numeroCni;
    }

    public String getPj1() {
        return pj1;
    }

    public void setPj1(String pj1) {
        this.pj1 = pj1;
    }

    public String getPj2() {
        return pj2;
    }

    public void setPj2(String pj2) {
        this.pj2 = pj2;
    }

    public String getPj3() {
        return pj3;
    }

    public void setPj3(String pj3) {
        this.pj3 = pj3;
    }

    public String getPj4() {
        return pj4;
    }

    public void setPj4(String pj4) {
        this.pj4 = pj4;
    }

    public String getPj5() {
        return pj5;
    }

    public void setPj5(String pj5) {
        this.pj5 = pj5;
    }

    public String getPj6() {
        return pj6;
    }

    public void setPj6(String pj6) {
        this.pj6 = pj6;
    }

    public String getPj7() {
        return pj7;
    }

    public void setPj7(String pj7) {
        this.pj7 = pj7;
    }

    public String getPj8() {
        return pj8;
    }

    public void setPj8(String pj8) {
        this.pj8 = pj8;
    }

    public String getPj9() {
        return pj9;
    }

    public void setPj9(String pj9) {
        this.pj9 = pj9;
    }

    public String getPj10() {
        return pj10;
    }

    public void setPj10(String pj10) {
        this.pj10 = pj10;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Long getSalarieId() {
        return salarieId;
    }

    public void setSalarieId(Long salarieId) {
        this.salarieId = salarieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GrappeMemberDTO grappeMemberDTO = (GrappeMemberDTO) o;
        if (grappeMemberDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grappeMemberDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrappeMemberDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", dateNais='" + getDateNais() + "'" +
            ", dateMariage='" + getDateMariage() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", origine='" + getOrigine() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", numeroCni='" + getNumeroCni() + "'" +
            ", pj1='" + getPj1() + "'" +
            ", pj2='" + getPj2() + "'" +
            ", pj3='" + getPj3() + "'" +
            ", pj4='" + getPj4() + "'" +
            ", pj5='" + getPj5() + "'" +
            ", pj6='" + getPj6() + "'" +
            ", pj7='" + getPj7() + "'" +
            ", pj8='" + getPj8() + "'" +
            ", pj9='" + getPj9() + "'" +
            ", pj10='" + getPj10() + "'" +
            ", extraInfos='" + getExtraInfos() + "'" +
            ", salarieId=" + getSalarieId() +
            "}";
    }
}
