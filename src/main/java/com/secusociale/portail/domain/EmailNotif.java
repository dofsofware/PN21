package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A EmailNotif.
 */
@Entity
@Table(name = "email_notif")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmailNotif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "groupe", nullable = false, unique = true)
    private String groupe;

    @Lob
    @Column(name = "emails")
    private String emails;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "last_send_date")
    private Instant lastSendDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupe() {
        return groupe;
    }

    public EmailNotif groupe(String groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getEmails() {
        return emails;
    }

    public EmailNotif emails(String emails) {
        this.emails = emails;
        return this;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Boolean isActive() {
        return active;
    }

    public EmailNotif active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getLastSendDate() {
        return lastSendDate;
    }

    public EmailNotif lastSendDate(Instant lastSendDate) {
        this.lastSendDate = lastSendDate;
        return this;
    }

    public void setLastSendDate(Instant lastSendDate) {
        this.lastSendDate = lastSendDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailNotif)) {
            return false;
        }
        return id != null && id.equals(((EmailNotif) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EmailNotif{" +
            "id=" + getId() +
            ", groupe='" + getGroupe() + "'" +
            ", emails='" + getEmails() + "'" +
            ", active='" + isActive() + "'" +
            ", lastSendDate='" + getLastSendDate() + "'" +
            "}";
    }
}
