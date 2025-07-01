package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ServerCheck.
 */
@Entity
@Table(name = "server_check")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ServerCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "etat")
    private String etat;

    @Column(name = "date")
    private Instant date;

    @Column(name = "toper")
    private Boolean toper;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ServerCheck code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEtat() {
        return etat;
    }

    public ServerCheck etat(String etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Instant getDate() {
        return date;
    }

    public ServerCheck date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Boolean isToper() {
        return toper;
    }

    public ServerCheck toper(Boolean toper) {
        this.toper = toper;
        return this;
    }

    public void setToper(Boolean toper) {
        this.toper = toper;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServerCheck)) {
            return false;
        }
        return id != null && id.equals(((ServerCheck) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ServerCheck{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", etat='" + getEtat() + "'" +
            ", date='" + getDate() + "'" +
            ", toper='" + isToper() + "'" +
            "}";
    }
}
