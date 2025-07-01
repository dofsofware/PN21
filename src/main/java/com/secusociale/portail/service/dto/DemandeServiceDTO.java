package com.secusociale.portail.service.dto;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.secusociale.portail.domain.DemandeService} entity.
 */
public class DemandeServiceDTO implements Serializable {

    private Long id;

    private String typeDemande;

    private String idDossier;

    @Lob
    private String payload;

    @Lob
    private String reponsebrute;

    private Long employeur;

    private String statut;

    private Instant createdAt;

    @Lob
    private String file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public String getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Long getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Long employeur) {
        this.employeur = employeur;
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

    public void setReponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "DemandeServiceDTO{" +
            "id=" + id +
            ", typeDemande='" + typeDemande + '\'' +
            ", idDossier='" + idDossier + '\'' +
            ", payload='" + payload + '\'' +
            ", reponsebrute='" + reponsebrute + '\'' +
            ", file='" + file + '\'' +
            ", employeur=" + employeur +
            ", statut='" + statut + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}
