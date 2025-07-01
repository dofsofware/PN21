package com.secusociale.portail.service.dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Configcompte} entity.
 */
public class ConfigcompteDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 24)
    private String accountNumber;

    private String accountHolderName;

    private String managerPhoneNumber;

    private String managerEmail;

    private String senderFirstName;

    private String agenceCode;

    private Boolean active;

    private Instant createdDate;

    private String signatairePhoneNumber;

    private String signataireEmail;

    private String managerFirstname;

    private String managerLastname;

    @Lob
    private String signataires;


    private Long agenceId;

    private String agenceNom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getAgenceCode() {
        return agenceCode;
    }

    public void setAgenceCode(String agenceCode) {
        this.agenceCode = agenceCode;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getSignatairePhoneNumber() {
        return signatairePhoneNumber;
    }

    public void setSignatairePhoneNumber(String signatairePhoneNumber) {
        this.signatairePhoneNumber = signatairePhoneNumber;
    }

    public String getSignataireEmail() {
        return signataireEmail;
    }

    public void setSignataireEmail(String signataireEmail) {
        this.signataireEmail = signataireEmail;
    }

    public String getManagerFirstname() {
        return managerFirstname;
    }

    public void setManagerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public String getSignataires() {
        return signataires;
    }

    public void setSignataires(String signataires) {
        this.signataires = signataires;
    }

    public Long getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }

    public String getAgenceNom() {
        return agenceNom;
    }

    public void setAgenceNom(String agenceNom) {
        this.agenceNom = agenceNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfigcompteDTO configcompteDTO = (ConfigcompteDTO) o;
        if (configcompteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configcompteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfigcompteDTO{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountHolderName='" + getAccountHolderName() + "'" +
            ", managerPhoneNumber='" + getManagerPhoneNumber() + "'" +
            ", managerEmail='" + getManagerEmail() + "'" +
            ", senderFirstName='" + getSenderFirstName() + "'" +
            ", agenceCode='" + getAgenceCode() + "'" +
            ", active='" + isActive() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", signatairePhoneNumber='" + getSignatairePhoneNumber() + "'" +
            ", signataireEmail='" + getSignataireEmail() + "'" +
            ", managerFirstname='" + getManagerFirstname() + "'" +
            ", managerLastname='" + getManagerLastname() + "'" +
            ", signataires='" + getSignataires() + "'" +
            ", agenceId=" + getAgenceId() +
            ", agenceNom='" + getAgenceNom() + "'" +
            "}";
    }
}
