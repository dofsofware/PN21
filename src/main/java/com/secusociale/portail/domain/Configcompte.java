package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Configcompte.
 */
@Entity
@Table(name = "configcompte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Configcompte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 24)
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "manager_phone_number")
    private String managerPhoneNumber;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "sender_first_name")
    private String senderFirstName;

    @Column(name = "agence_code")
    private String agenceCode;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "signataire_phone_number")
    private String signatairePhoneNumber;

    @Column(name = "signataire_email")
    private String signataireEmail;

    @Column(name = "manager_firstname")
    private String managerFirstname;

    @Column(name = "manager_lastname")
    private String managerLastname;

    @Lob
    @Column(name = "signataires")
    private String signataires;

    @OneToOne
    @JoinColumn(unique = true,nullable = true)
    private Agence agence;

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

    public Configcompte accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public Configcompte accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public Configcompte managerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
        return this;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public Configcompte managerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
        return this;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public Configcompte senderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
        return this;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getAgenceCode() {
        return agenceCode;
    }

    public Configcompte agenceCode(String agenceCode) {
        this.agenceCode = agenceCode;
        return this;
    }

    public void setAgenceCode(String agenceCode) {
        this.agenceCode = agenceCode;
    }

    public Boolean isActive() {
        return active;
    }

    public Configcompte active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Configcompte createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getSignatairePhoneNumber() {
        return signatairePhoneNumber;
    }

    public Configcompte signatairePhoneNumber(String signatairePhoneNumber) {
        this.signatairePhoneNumber = signatairePhoneNumber;
        return this;
    }

    public void setSignatairePhoneNumber(String signatairePhoneNumber) {
        this.signatairePhoneNumber = signatairePhoneNumber;
    }

    public String getSignataireEmail() {
        return signataireEmail;
    }

    public Configcompte signataireEmail(String signataireEmail) {
        this.signataireEmail = signataireEmail;
        return this;
    }

    public void setSignataireEmail(String signataireEmail) {
        this.signataireEmail = signataireEmail;
    }

    public String getManagerFirstname() {
        return managerFirstname;
    }

    public Configcompte managerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
        return this;
    }

    public void setManagerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public Configcompte managerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
        return this;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public String getSignataires() {
        return signataires;
    }

    public Configcompte signataires(String signataires) {
        this.signataires = signataires;
        return this;
    }

    public void setSignataires(String signataires) {
        this.signataires = signataires;
    }

    public Agence getAgence() {
        return agence;
    }

    public Configcompte agence(Agence agence) {
        this.agence = agence;
        return this;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Configcompte)) {
            return false;
        }
        return id != null && id.equals(((Configcompte) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Configcompte{" +
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
            "}";
    }
}
