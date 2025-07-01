package com.secusociale.portail.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Carriere} entity.
 */
public class CarriereDTO implements Serializable {

    private Long id;

    private String numeroUniqueSalarie;

    private String numeroUniqueEmployeur;

    private String raisonSociale;

    private String typeRegime;

    private String typeDeclaration;

    private LocalDate debutCotisation;

    private LocalDate finCotisation;

    private Integer exercice;

    private Double salaireBrute;

    private Double salaireAssujettie;

    private Double cotisationIpres;

    private Double cotisationCss;

    @Lob
    private String extraInfos;

    private Instant createdAt;


    private Long salarieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroUniqueSalarie() {
        return numeroUniqueSalarie;
    }

    public void setNumeroUniqueSalarie(String numeroUniqueSalarie) {
        this.numeroUniqueSalarie = numeroUniqueSalarie;
    }

    public String getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public void setNumeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getTypeRegime() {
        return typeRegime;
    }

    public void setTypeRegime(String typeRegime) {
        this.typeRegime = typeRegime;
    }

    public String getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public LocalDate getDebutCotisation() {
        return debutCotisation;
    }

    public void setDebutCotisation(LocalDate debutCotisation) {
        this.debutCotisation = debutCotisation;
    }

    public LocalDate getFinCotisation() {
        return finCotisation;
    }

    public void setFinCotisation(LocalDate finCotisation) {
        this.finCotisation = finCotisation;
    }

    public Integer getExercice() {
        return exercice;
    }

    public void setExercice(Integer exercice) {
        this.exercice = exercice;
    }

    public Double getSalaireBrute() {
        return salaireBrute;
    }

    public void setSalaireBrute(Double salaireBrute) {
        this.salaireBrute = salaireBrute;
    }

    public Double getSalaireAssujettie() {
        return salaireAssujettie;
    }

    public void setSalaireAssujettie(Double salaireAssujettie) {
        this.salaireAssujettie = salaireAssujettie;
    }

    public Double getCotisationIpres() {
        return cotisationIpres;
    }

    public void setCotisationIpres(Double cotisationIpres) {
        this.cotisationIpres = cotisationIpres;
    }

    public Double getCotisationCss() {
        return cotisationCss;
    }

    public void setCotisationCss(Double cotisationCss) {
        this.cotisationCss = cotisationCss;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

        CarriereDTO carriereDTO = (CarriereDTO) o;
        if (carriereDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carriereDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarriereDTO{" +
            "id=" + getId() +
            ", numeroUniqueSalarie='" + getNumeroUniqueSalarie() + "'" +
            ", numeroUniqueEmployeur='" + getNumeroUniqueEmployeur() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", typeRegime='" + getTypeRegime() + "'" +
            ", typeDeclaration='" + getTypeDeclaration() + "'" +
            ", debutCotisation='" + getDebutCotisation() + "'" +
            ", finCotisation='" + getFinCotisation() + "'" +
            ", exercice=" + getExercice() +
            ", salaireBrute=" + getSalaireBrute() +
            ", salaireAssujettie=" + getSalaireAssujettie() +
            ", cotisationIpres=" + getCotisationIpres() +
            ", cotisationCss=" + getCotisationCss() +
            ", extraInfos='" + getExtraInfos() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", salarieId=" + getSalarieId() +
            "}";
    }
}
