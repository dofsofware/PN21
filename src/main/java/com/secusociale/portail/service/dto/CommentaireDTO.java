package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Commentaire} entity.
 */
public class CommentaireDTO implements Serializable {

    private Long id;
    private Long nouvelleImmatriculationId;
    private String description;
    private String typeCommentaire;
    private Long createdBy; // Gardez ce champ pour la persistance
    private LocalDateTime createdAt;
    private Long destinataireId;
    private String firstName; // Ajout du champ firstName
    private String lastName;

    // Constructeur avec paramètres
    public CommentaireDTO(Long id, Long nouvelleImmatriculationId, String description, String typeCommentaire,
                          Long createdBy, LocalDateTime createdAt, Long destinataireId,
                          String firstName, String lastName) {
        this.id = id;
        this.nouvelleImmatriculationId = nouvelleImmatriculationId;
        this.description = description;
        this.typeCommentaire = typeCommentaire;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.destinataireId = destinataireId;
        this.firstName = firstName; // Initialisation du prénom
        this.lastName = lastName; // Initialisation du nom de famille
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNouvelleImmatriculationId() {
        return nouvelleImmatriculationId;
    }

    public void setNouvelleImmatriculationId(Long nouvelleImmatriculationId) {
        this.nouvelleImmatriculationId = nouvelleImmatriculationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCommentaire() {
        return typeCommentaire;
    }

    public void setTypeCommentaire(String typeCommentaire) {
        this.typeCommentaire = typeCommentaire;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDestinataireId() {
        return destinataireId;
    }

    public void setDestinataireId(Long destinataireId) {
        this.destinataireId = destinataireId;
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


    // Méthode toString

    @Override
    public String toString() {
        return "CommentaireDTO{" +
            "id=" + getId() +
            ", nouvelleImmatriculationId=" + getNouvelleImmatriculationId() +
            ", description='" + getDescription() + "'" +
            ", typeCommentaire='" + getTypeCommentaire() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createdAt=" + getCreatedAt() +
            ", destinataireId=" + getDestinataireId() +
            ", firstName='" + getFirstName() + "'" + // Ajout du prénom
            ", lastName='" + getLastName() + "'" +
            "}";
    }

    // Méthode equals et hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentaireDTO)) return false;
        CommentaireDTO that = (CommentaireDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
