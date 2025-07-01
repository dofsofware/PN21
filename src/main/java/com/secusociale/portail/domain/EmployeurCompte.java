package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A EmployeurCompte.
 */
@Entity
@Table(name = "employeur_compte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployeurCompte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "manager_phone_number")
    private String managerPhoneNumber;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "sender_first_name")
    private String senderFirstName;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_date")
    private Instant createdDate = Instant.now();

    @Column(name = "signataire_phone_number")
    private String signatairePhoneNumber;

    @Column(name = "signataire_email")
    private String signataireEmail;


    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "manager_firstname")
    private String managerFirstname;

    @Column(name = "manager_lastname")
    private String managerLastname;

    @Lob
    @Column(name = "signataires")
    private String signataires;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public EmployeurCompte accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public EmployeurCompte accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public EmployeurCompte managerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
        return this;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public EmployeurCompte managerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
        return this;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public EmployeurCompte senderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
        return this;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public Boolean isActive() {
        return active;
    }

    public EmployeurCompte active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public EmployeurCompte createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getSignatairePhoneNumber() {
        return signatairePhoneNumber;
    }

    public EmployeurCompte signatairePhoneNumber(String signatairePhoneNumber) {
        this.signatairePhoneNumber = signatairePhoneNumber;
        return this;
    }

    public void setSignatairePhoneNumber(String signatairePhoneNumber) {
        this.signatairePhoneNumber = signatairePhoneNumber;
    }

    public String getSignataireEmail() {
        return signataireEmail;
    }

    public EmployeurCompte signataireEmail(String signataireEmail) {
        this.signataireEmail = signataireEmail;
        return this;
    }

    public void setSignataireEmail(String signataireEmail) {
        this.signataireEmail = signataireEmail;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public EmployeurCompte numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Long getUserId() {
        return userId;
    }

    public EmployeurCompte userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getManagerFirstname() {
        return managerFirstname;
    }

    public EmployeurCompte managerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
        return this;
    }

    public void setManagerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public EmployeurCompte managerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
        return this;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public String getSignataires() {
        return signataires;
    }

    public EmployeurCompte signataires(String signataires) {
        this.signataires = signataires;
        return this;
    }

    public void setSignataires(String signataires) {
        this.signataires = signataires;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeurCompte)) {
            return false;
        }
        return id != null && id.equals(((EmployeurCompte) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EmployeurCompte{" +
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
