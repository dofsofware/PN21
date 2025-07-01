package com.secusociale.portail.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Activities} entity.
 */
public class ActivitiesDTO implements Serializable {

    private Long id;

    private Long userId;

    @Lob
    private String oldValue;

    @Lob
    private String newValue;

    private String operation;

    private Instant dateOperation;

    private String typeObjet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActivitiesDTO activitiesDTO = (ActivitiesDTO) o;
        if (activitiesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activitiesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActivitiesDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", oldValue='" + getOldValue() + "'" +
            ", newValue='" + getNewValue() + "'" +
            ", operation='" + getOperation() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", typeObjet='" + getTypeObjet() + "'" +
            "}";
    }
}
