package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.CronSetting} entity.
 */
public class CronSettingDTO implements Serializable {

    private Long id;

    private String code;

    private String expression;

    @Lob
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CronSettingDTO cronSettingDTO = (CronSettingDTO) o;
        if (cronSettingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cronSettingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CronSettingDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", expression='" + getExpression() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
