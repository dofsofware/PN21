package com.secusociale.portail.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.EmailNotif} entity.
 */
public class EmailNotifDTO implements Serializable {

    private Long id;

    @NotNull
    private String groupe;

    @Lob
    private String emails;

    private Boolean active;

    private Instant lastSendDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getLastSendDate() {
        return lastSendDate;
    }

    public void setLastSendDate(Instant lastSendDate) {
        this.lastSendDate = lastSendDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmailNotifDTO emailNotifDTO = (EmailNotifDTO) o;
        if (emailNotifDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailNotifDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailNotifDTO{" +
            "id=" + getId() +
            ", groupe='" + getGroupe() + "'" +
            ", emails='" + getEmails() + "'" +
            ", active='" + isActive() + "'" +
            ", lastSendDate='" + getLastSendDate() + "'" +
            "}";
    }
}
