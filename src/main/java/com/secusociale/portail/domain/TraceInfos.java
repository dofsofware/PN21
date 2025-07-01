package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TraceInfos.
 */
@Entity
@Table(name = "trace_infos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TraceInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "request")
    private String request;

    @Lob
    @Column(name = "reponse")
    private String reponse;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    private Instant date;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public TraceInfos request(String request) {
        this.request = request;
        return this;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getReponse() {
        return reponse;
    }

    public TraceInfos reponse(String reponse) {
        this.reponse = reponse;
        return this;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getUsername() {
        return username;
    }

    public TraceInfos username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getDate() {
        return date;
    }

    public TraceInfos date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TraceInfos)) {
            return false;
        }
        return id != null && id.equals(((TraceInfos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TraceInfos{" +
            "id=" + getId() +
            ", request='" + getRequest() + "'" +
            ", reponse='" + getReponse() + "'" +
            ", username='" + getUsername() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
