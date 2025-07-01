package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Carriere.
 */
@Entity
@Table(name = "carriere")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Carriere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_unique_salarie")
    private String numeroUniqueSalarie;

    @Column(name = "numero_unique_employeur")
    private String numeroUniqueEmployeur;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "type_regime")
    private String typeRegime;

    @Column(name = "type_declaration")
    private String typeDeclaration;

    @Column(name = "debut_cotisation")
    private LocalDate debutCotisation;

    @Column(name = "fin_cotisation")
    private LocalDate finCotisation;

    @Column(name = "exercice")
    private Integer exercice;

    @Column(name = "salaire_brute")
    private Double salaireBrute;

    @Column(name = "salaire_assujettie")
    private Double salaireAssujettie;

    @Column(name = "cotisation_ipres")
    private Double cotisationIpres;

    @Column(name = "cotisation_css")
    private Double cotisationCss;

    @Lob
    @Column(name = "extra_infos")
    private String extraInfos;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JsonIgnoreProperties("carrieres")
    private Salarie salarie;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroUniqueSalarie() {
        return numeroUniqueSalarie;
    }

    public Carriere numeroUniqueSalarie(String numeroUniqueSalarie) {
        this.numeroUniqueSalarie = numeroUniqueSalarie;
        return this;
    }

    public void setNumeroUniqueSalarie(String numeroUniqueSalarie) {
        this.numeroUniqueSalarie = numeroUniqueSalarie;
    }

    public String getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public Carriere numeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
        return this;
    }

    public void setNumeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public Carriere raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getTypeRegime() {
        return typeRegime;
    }

    public Carriere typeRegime(String typeRegime) {
        this.typeRegime = typeRegime;
        return this;
    }

    public void setTypeRegime(String typeRegime) {
        this.typeRegime = typeRegime;
    }

    public String getTypeDeclaration() {
        return typeDeclaration;
    }

    public Carriere typeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
        return this;
    }

    public void setTypeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public LocalDate getDebutCotisation() {
        return debutCotisation;
    }

    public Carriere debutCotisation(LocalDate debutCotisation) {
        this.debutCotisation = debutCotisation;
        return this;
    }

    public void setDebutCotisation(LocalDate debutCotisation) {
        this.debutCotisation = debutCotisation;
    }

    public LocalDate getFinCotisation() {
        return finCotisation;
    }

    public Carriere finCotisation(LocalDate finCotisation) {
        this.finCotisation = finCotisation;
        return this;
    }

    public void setFinCotisation(LocalDate finCotisation) {
        this.finCotisation = finCotisation;
    }

    public Integer getExercice() {
        return exercice;
    }

    public Carriere exercice(Integer exercice) {
        this.exercice = exercice;
        return this;
    }

    public void setExercice(Integer exercice) {
        this.exercice = exercice;
    }

    public Double getSalaireBrute() {
        return salaireBrute;
    }

    public Carriere salaireBrute(Double salaireBrute) {
        this.salaireBrute = salaireBrute;
        return this;
    }

    public void setSalaireBrute(Double salaireBrute) {
        this.salaireBrute = salaireBrute;
    }

    public Double getSalaireAssujettie() {
        return salaireAssujettie;
    }

    public Carriere salaireAssujettie(Double salaireAssujettie) {
        this.salaireAssujettie = salaireAssujettie;
        return this;
    }

    public void setSalaireAssujettie(Double salaireAssujettie) {
        this.salaireAssujettie = salaireAssujettie;
    }

    public Double getCotisationIpres() {
        return cotisationIpres;
    }

    public Carriere cotisationIpres(Double cotisationIpres) {
        this.cotisationIpres = cotisationIpres;
        return this;
    }

    public void setCotisationIpres(Double cotisationIpres) {
        this.cotisationIpres = cotisationIpres;
    }

    public Double getCotisationCss() {
        return cotisationCss;
    }

    public Carriere cotisationCss(Double cotisationCss) {
        this.cotisationCss = cotisationCss;
        return this;
    }

    public void setCotisationCss(Double cotisationCss) {
        this.cotisationCss = cotisationCss;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public Carriere extraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
        return this;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Carriere createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Salarie getSalarie() {
        return salarie;
    }

    public Carriere salarie(Salarie salarie) {
        this.salarie = salarie;
        return this;
    }

    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carriere)) {
            return false;
        }
        return id != null && id.equals(((Carriere) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Carriere{" +
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
            "}";
    }
}
