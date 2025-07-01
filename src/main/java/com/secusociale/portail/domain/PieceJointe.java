package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PieceJointe.
 */
@Entity
@Table(name = "piece_jointe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PieceJointe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "entity_type")
    private String entityType;

    @Lob
    @Column(name = "file")
    private String file;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "user_created")
    private String userCreated;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "child_id")
    private Long childId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PieceJointe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public PieceJointe extension(String extension) {
        this.extension = extension;
        return this;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEntityType() {
        return entityType;
    }

    public PieceJointe entityType(String entityType) {
        this.entityType = entityType;
        return this;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getFile() {
        return file;
    }

    public PieceJointe file(String file) {
        this.file = file;
        return this;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getEntityId() {
        return entityId;
    }

    public PieceJointe entityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PieceJointe createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public PieceJointe userCreated(String userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getDescription() {
        return description;
    }

    public PieceJointe description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChildId() {
        return childId;
    }

    public PieceJointe childId(Long childId) {
        this.childId = childId;
        return this;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceJointe)) {
            return false;
        }
        return id != null && id.equals(((PieceJointe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PieceJointe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", extension='" + getExtension() + "'" +
            ", entityType='" + getEntityType() + "'" +
            ", file='" + getFile() + "'" +
            ", entityId=" + getEntityId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", userCreated='" + getUserCreated() + "'" +
            ", description='" + getDescription() + "'" +
            ", childId=" + getChildId() +
            "}";
    }
}
