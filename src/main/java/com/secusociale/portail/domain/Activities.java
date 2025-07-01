package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Activities.
 */
@Entity
@Table(name = "activities")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activities implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Lob
    @Column(name = "old_value")
    private String oldValue;

    @Lob
    @Column(name = "new_value")
    private String newValue;

    @Column(name = "operation")
    private String operation;

    @Column(name = "date_operation")
    private Instant dateOperation;

    @Column(name = "type_objet")
    private String typeObjet;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Activities userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public Activities oldValue(String oldValue) {
        this.oldValue = oldValue;
        return this;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public Activities newValue(String newValue) {
        this.newValue = newValue;
        return this;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOperation() {
        return operation;
    }

    public Activities operation(String operation) {
        this.operation = operation;
        return this;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Instant getDateOperation() {
        return dateOperation;
    }

    public Activities dateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(Instant dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getTypeObjet() {
        return typeObjet;
    }

    public Activities typeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
        return this;
    }

    public void setTypeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activities)) {
            return false;
        }
        return id != null && id.equals(((Activities) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Activities{" +
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
