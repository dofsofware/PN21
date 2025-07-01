package com.secusociale.portail.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.EmployeurCompte} entity.
 */
public class EmployeurCompteDTO implements Serializable {

    private Long id;

    private String accountNumber;

    private String accountHolderName;

    private String managerPhoneNumber;

    private String managerEmail;

    private String senderFirstName;

    private Boolean active;

    private Instant createdDate = Instant.now();

    private String signatairePhoneNumber;

    private String signataireEmail;


    private String numeroUnique;

    private Long userId;

    private String managerFirstname;

    private String managerLastname;

    @Lob
    private String signataires;


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

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeurCompteDTO employeurCompteDTO = (EmployeurCompteDTO) o;
        if (employeurCompteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeurCompteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeurCompteDTO{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountHolderName='" + getAccountHolderName() + "'" +
            ", managerPhoneNumber='" + getManagerPhoneNumber() + "'" +
            ", managerEmail='" + getManagerEmail() + "'" +
            ", senderFirstName='" + getSenderFirstName() + "'" +
            ", active='" + isActive() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", signatairePhoneNumber='" + getSignatairePhoneNumber() + "'" +
            ", signataireEmail='" + getSignataireEmail() + "'" +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", userId=" + getUserId() +
            ", managerFirstname='" + getManagerFirstname() + "'" +
            ", managerLastname='" + getManagerLastname() + "'" +
            ", signataires='" + getSignataires() + "'" +
            "}";
    }
}
