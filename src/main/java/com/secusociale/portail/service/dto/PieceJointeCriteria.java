package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.PieceJointe} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.PieceJointeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /piece-jointes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PieceJointeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter extension;

    private StringFilter entityType;

    private LongFilter entityId;

    private InstantFilter createdAt;

    private StringFilter userCreated;

    private LongFilter childId;

    public PieceJointeCriteria() {
    }

    public PieceJointeCriteria(PieceJointeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.extension = other.extension == null ? null : other.extension.copy();
        this.entityType = other.entityType == null ? null : other.entityType.copy();
        this.entityId = other.entityId == null ? null : other.entityId.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.userCreated = other.userCreated == null ? null : other.userCreated.copy();
        this.childId = other.childId == null ? null : other.childId.copy();
    }

    @Override
    public PieceJointeCriteria copy() {
        return new PieceJointeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getExtension() {
        return extension;
    }

    public void setExtension(StringFilter extension) {
        this.extension = extension;
    }

    public StringFilter getEntityType() {
        return entityType;
    }

    public void setEntityType(StringFilter entityType) {
        this.entityType = entityType;
    }

    public LongFilter getEntityId() {
        return entityId;
    }

    public void setEntityId(LongFilter entityId) {
        this.entityId = entityId;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public StringFilter getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(StringFilter userCreated) {
        this.userCreated = userCreated;
    }

    public LongFilter getChildId() {
        return childId;
    }

    public void setChildId(LongFilter childId) {
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
        final PieceJointeCriteria that = (PieceJointeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(extension, that.extension) &&
            Objects.equals(entityType, that.entityType) &&
            Objects.equals(entityId, that.entityId) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(userCreated, that.userCreated) &&
            Objects.equals(childId, that.childId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        extension,
        entityType,
        entityId,
        createdAt,
        userCreated,
        childId
        );
    }

    @Override
    public String toString() {
        return "PieceJointeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (extension != null ? "extension=" + extension + ", " : "") +
                (entityType != null ? "entityType=" + entityType + ", " : "") +
                (entityId != null ? "entityId=" + entityId + ", " : "") +
                (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
                (userCreated != null ? "userCreated=" + userCreated + ", " : "") +
                (childId != null ? "childId=" + childId + ", " : "") +
            "}";
    }

}
