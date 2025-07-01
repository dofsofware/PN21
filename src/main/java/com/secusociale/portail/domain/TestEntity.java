package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TestEntity.
 */
@Entity
@Table(name = "test_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "texte")
    private String texte;

    @NotNull
    @Column(name = "ninea", nullable = false, unique = true)
    private String ninea;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public TestEntity texte(String texte) {
        this.texte = texte;
        return this;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getNinea() {
        return ninea;
    }

    public TestEntity ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TestEntity dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestEntity)) {
            return false;
        }
        return id != null && id.equals(((TestEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
            "id=" + getId() +
            ", texte='" + getTexte() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            "}";
    }
}
