package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DemandeService.
 */
@Entity
@Table(name = "demande_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemandeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_demande")
    private String typeDemande;

    @Column(name = "id_dossier")
    private String idDossier;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "payload")
    @Lob
    private String payload;

    @Column(name = "employeur_id")
    private Long employeur;

    @Column(name = "statut_demande")
    private String statut;

    @Column(name = "created_at")
    private Instant createdAt;

    @Lob
    @Column(name = "reponsebrute")
    private String reponsebrute;

    private String file;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public DemandeService typeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
        return this;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public String getIdDossier() {
        return idDossier;
    }

    public DemandeService idDossier(String idDossier) {
        this.idDossier = idDossier;
        return this;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }

    public Long getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Long employeur) {
        this.employeur = employeur;
    }

    public String getPayload() {
        return payload;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public DemandeService numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getReponsebrute() {
        return reponsebrute;
    }

    public DemandeService reponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
        return this;
    }

    public void setReponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeService)) {
            return false;
        }
        return id != null && id.equals(((DemandeService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DemandeService{" +
            "id=" + getId() +
            ", typeDemande='" + getTypeDemande() + "'" +
            ", idDossier='" + getIdDossier() + "'" +
            ", employeur_id='" + getEmployeur() + "'" +
            ", numero_unique='" + getNumeroUnique() + "'" +
            ", payload='" + getPayload() + "'" +
            ", reponsebrute='" + getReponsebrute() + "'" +
            "}";
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
