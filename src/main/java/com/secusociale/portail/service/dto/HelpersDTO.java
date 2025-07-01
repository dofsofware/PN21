package com.secusociale.portail.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Helpers} entity.
 */
public class HelpersDTO implements Serializable {

    private Long id;

    private String user;

    private Instant date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HelpersDTO helpersDTO = (HelpersDTO) o;
        if (helpersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), helpersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HelpersDTO{" +
            "id=" + getId() +
            ", user='" + getUser() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
