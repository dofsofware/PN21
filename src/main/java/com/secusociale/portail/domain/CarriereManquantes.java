package com.secusociale.portail.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A CarriereManquantes.
 */
@Entity
@Table(name = "carriere_manquantes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarriereManquantes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_unique_employeur", nullable = true)
    private String numeroUniqueEmployeur;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @NotNull
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @NotNull
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Min(value = 0, message = "Le salaire brut doit être supérieur à 0")
    private Double salaireBrut;

    @Min(value = 0, message = "Le montant de cotisation CSS doit être supérieur à 0")
    private Double cotisationCss;

    @Min(value = 0, message = "Le montant de cotisation IPRES doit être supérieur à 0")
    private Double cotisationIpres;

    @NotNull
    @Column(name = "justificatifs_carriereManquantes", nullable = false)
    private String JustificatifsCarriereManquantes;

    @ManyToOne
    @JoinColumn(name = "reclamation_id")
    @JsonIgnoreProperties("carriereManquantes")
    private Reclamation reclamation;

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }



    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CarriereManquantes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroUniqueEmployeur() {
        return this.numeroUniqueEmployeur;
    }

    public CarriereManquantes nomEmployeur(String nomEmployeur) {
        this.setNumeroUniqueEmployeur(nomEmployeur);
        return this;
    }

    public void setNumeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }

    public LocalDate getDateDebut() {
        return this.dateDebut;
    }

    public CarriereManquantes dateDebut(LocalDate dateDebut) {
        this.setDateDebut(dateDebut);
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return this.dateFin;
    }

    public CarriereManquantes dateFin(LocalDate dateFin) {
        this.setDateFin(dateFin);
        return this;
    }


    public @NotNull String getJustificatifsCarriereManquantes() {
        return JustificatifsCarriereManquantes;
    }

    public void setJustificatifsCarriereManquantes(@NotNull String justificatifsCarriereManquantes) {
        JustificatifsCarriereManquantes = justificatifsCarriereManquantes;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public @Min(value = 0, message = "Le salaire brut doit être supérieur à 0") Double getSalaireBrut() {
        return salaireBrut;
    }

    public void setSalaireBrut(@Min(value = 0, message = "Le salaire brut doit être supérieur à 0") Double salaireBrut) {
        this.salaireBrut = salaireBrut;
    }

    public @Min(value = 0, message = "Le montant de cotisation CSS doit être supérieur à 0") Double getCotisationCss() {
        return cotisationCss;
    }

    public void setCotisationCss(@Min(value = 0, message = "Le montant de cotisation CSS doit être supérieur à 0") Double cotisationCss) {
        this.cotisationCss = cotisationCss;
    }

    public @Min(value = 0, message = "Le montant de cotisation IPRES doit être supérieur à 0") Double getCotisationIpres() {
        return cotisationIpres;
    }

    public void setCotisationIpres(@Min(value = 0, message = "Le montant de cotisation IPRES doit être supérieur à 0") Double cotisationIpres) {
        this.cotisationIpres = cotisationIpres;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarriereManquantes)) {
            return false;
        }
        return getId() != null && getId().equals(((CarriereManquantes) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CarriereManquantes{" +
            "id=" + id +
            ", numeroUniqueEmployeur='" + numeroUniqueEmployeur + '\'' +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", dateDebut=" + dateDebut +
            ", dateFin=" + dateFin +
            ", salaireBrut=" + salaireBrut +
            ", cotisationCss=" + cotisationCss +
            ", cotisationIpres=" + cotisationIpres +
            ", JustificatifsCarriereManquantes='" + JustificatifsCarriereManquantes + '\'' +
            ", reclamation=" + reclamation +
            '}';
    }
}

