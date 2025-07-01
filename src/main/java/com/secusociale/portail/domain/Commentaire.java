package com.secusociale.portail.domain;

import com.secusociale.portail.domain.enumeration.StatutTP;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "commentaire")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Commentaire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nouvelle_immatriculation_id")
    private Long nouvelleImmatriculationId;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "type_commentaire")
    private String typeCommentaire;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "destinataire_id")
    private Long destinataireId;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    private User user; // Relation avec l'entité Utilisateur

    // Getters and Setters

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDestinataireId() {
        return destinataireId;
    }

    public void setDestinataireId(Long destinataireId) {
        this.destinataireId = destinataireId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
