package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DemandeServiceSalarie.
 */
@Entity
@Table(name = "demande_service_salarie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemandeServiceSalarie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "statut")
    private String statut;

    @Column(name = "date")
    private Instant date;

    @Lob
    @Column(name = "contenu")
    private String contenu;

    @Lob
    @Column(name = "motif_ou_description")
    private String motifOuDescription;

    @Column(name = "date_traitement")
    private Instant dateTraitement;

    @Column(name = "numero_unique_employeur")
    private String numeroUniqueEmployeur;

    @Column(name = "employeur")
    private String employeur;

    @Column(name = "gestionnaire_id")
    private Long gestionnaireId;

    @Lob
    @Column(name = "old_eyes")
    private String oldEyes;

    private String acteDeTitularisation;

    private Long agenceId;

    private String certificat;

    private String motif;

    @ManyToOne
    @JsonIgnoreProperties("demandeServiceSalaries")
    private Salarie salarie;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public DemandeServiceSalarie type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public DemandeServiceSalarie statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getDate() {
        return date;
    }

    public DemandeServiceSalarie date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public DemandeServiceSalarie contenu(String contenu) {
        this.contenu = contenu;
        return this;
    }

    public String getEmployeur() {
        return employeur;
    }

    public DemandeServiceSalarie employeur(String employeur) {
        this.employeur = employeur;
        return this;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getMotifOuDescription() {
        return motifOuDescription;
    }

    public DemandeServiceSalarie motifOuDescription(String motifOuDescription) {
        this.motifOuDescription = motifOuDescription;
        return this;
    }

    public void setMotifOuDescription(String motifOuDescription) {
        this.motifOuDescription = motifOuDescription;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public DemandeServiceSalarie dateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
        return this;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public DemandeServiceSalarie numeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
        return this;
    }

    public void setNumeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }



    public Long getGestionnaireId() {
        return gestionnaireId;
    }

    public DemandeServiceSalarie gestionnaireId(Long gestionnaireId) {
        this.gestionnaireId = gestionnaireId;
        return this;
    }

    public void setGestionnaireId(Long gestionnaireId) {
        this.gestionnaireId = gestionnaireId;
    }

    public String getOldEyes() {
        return oldEyes;
    }

    public DemandeServiceSalarie oldEyes(String oldEyes) {
        this.oldEyes = oldEyes;
        return this;
    }

    public void setOldEyes(String oldEyes) {
        this.oldEyes = oldEyes;
    }

    public Salarie getSalarie() {
        return salarie;
    }

    public DemandeServiceSalarie salarie(Salarie salarie) {
        this.salarie = salarie;
        return this;
    }

    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove



    public String getActeDeTitularisation() {
        return acteDeTitularisation;
    }

    public void setActeDeTitularisation(String acteDeTitularisation) {
        this.acteDeTitularisation = acteDeTitularisation;
    }

    public Long getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }

    public String getCertificat() {
        return certificat;
    }

    public void setCertificat(String certificat) {
        this.certificat = certificat;
    }

    public String getMotif() {
        return motif;
    }


    public DemandeServiceSalarie motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotift(String motif) {
        this.motif = motif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeServiceSalarie)) {
            return false;
        }
        return id != null && id.equals(((DemandeServiceSalarie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DemandeServiceSalarie{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", statut='" + getStatut() + "'" +
            ", date='" + getDate() + "'" +
            ", contenu='" + getContenu() + "'" +
            ", motifOuDescription='" + getMotifOuDescription() + "'" +
            ", dateTraitement='" + getDateTraitement() + "'" +
            ", numeroUniqueEmployeur='" + getNumeroUniqueEmployeur() + "'" +
            ", gestionnaireId=" + getGestionnaireId() +
            ", oldEyes='" + getOldEyes() + "'" +
            "}";
    }
}
