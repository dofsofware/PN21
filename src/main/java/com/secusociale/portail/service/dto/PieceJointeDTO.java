package com.secusociale.portail.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.secusociale.portail.domain.PieceJointe} entity.
 */
public class PieceJointeDTO implements Serializable {

    private Long id;

    @NotBlank
    @NotNull
    private String name;

    private String extension;

    @NotBlank
    @NotNull
    private String entityType;

    @Lob
    @NotBlank
    @NotNull
    private String file;

    @NotBlank
    @NotNull
    private Long entityId;

    private Instant createdAt;

    private String userCreated;

    @Lob
    private String description;

    private Long childId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PieceJointeDTO pieceJointeDTO = (PieceJointeDTO) o;
        if (pieceJointeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pieceJointeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PieceJointeDTO{" +
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
