package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_ordre_virement")
    private String numeroOrdreVirement; //ok

    @Column(name = "banque_source")
    private String banqueSource; // ok

    @Column(name = "numero_compte_source")
    private String numeroCompteSource; //ok

    @Column(name = "banque_destination")
    private String banqueDestination; //ok

    @Column(name = "numero_compte_destination")
    private String numeroCompteDestination; // ok

    @Column(name = "reference_facture")
    private String referenceFacture; //ok

    @Column(name = "montant_total")
    private Integer montantTotal;

    @Column(name = "montant_account")
    private Integer montantAccount;

    @Column(name = "etat")
    private String etat;

    @Lob
    @Column(name = "file_join")
    private String fileJoin;


    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOrdreVirement() {
        return numeroOrdreVirement;
    }

    public Paiement numeroOrdreVirement(String numeroOrdreVirement) {
        this.numeroOrdreVirement = numeroOrdreVirement;
        return this;
    }

    public void setNumeroOrdreVirement(String numeroOrdreVirement) {
        this.numeroOrdreVirement = numeroOrdreVirement;
    }

    public String getBanqueSource() {
        return banqueSource;
    }

    public Paiement banqueSource(String banqueSource) {
        this.banqueSource = banqueSource;
        return this;
    }

    public void setBanqueSource(String banqueSource) {
        this.banqueSource = banqueSource;
    }

    public String getNumeroCompteSource() {
        return numeroCompteSource;
    }

    public Paiement numeroCompteSource(String numeroCompteSource) {
        this.numeroCompteSource = numeroCompteSource;
        return this;
    }

    public void setNumeroCompteSource(String numeroCompteSource) {
        this.numeroCompteSource = numeroCompteSource;
    }

    public String getBanqueDestination() {
        return banqueDestination;
    }

    public Paiement banqueDestination(String banqueDestination) {
        this.banqueDestination = banqueDestination;
        return this;
    }

    public void setBanqueDestination(String banqueDestination) {
        this.banqueDestination = banqueDestination;
    }

    public String getNumeroCompteDestination() {
        return numeroCompteDestination;
    }

    public Paiement numeroCompteDestination(String numeroCompteDestination) {
        this.numeroCompteDestination = numeroCompteDestination;
        return this;
    }

    public void setNumeroCompteDestination(String numeroCompteDestination) {
        this.numeroCompteDestination = numeroCompteDestination;
    }

    public String getReferenceFacture() {
        return referenceFacture;
    }

    public Paiement referenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
        return this;
    }

    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }

    public Integer getMontantTotal() {
        return montantTotal;
    }

    public Paiement montantTotal(Integer montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(Integer montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Integer getMontantAccount() {
        return montantAccount;
    }

    public Paiement montantAccount(Integer montantAccount) {
        this.montantAccount = montantAccount;
        return this;
    }

    public void setMontantAccount(Integer montantAccount) {
        this.montantAccount = montantAccount;
    }

    public String getEtat() {
        return etat;
    }

    public Paiement etat(String etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getFileJoin() {
        return fileJoin;
    }

    public Paiement fileJoin(String fileJoin) {
        this.fileJoin = fileJoin;
        return this;
    }

    public void setFileJoin(String fileJoin) {
        this.fileJoin = fileJoin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Paiement dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public Paiement userId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paiement)) {
            return false;
        }
        return id != null && id.equals(((Paiement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paiement{" +
            "id=" + getId() +
            ", numeroOrdreVirement='" + getNumeroOrdreVirement() + "'" +
            ", banqueSource='" + getBanqueSource() + "'" +
            ", numeroCompteSource='" + getNumeroCompteSource() + "'" +
            ", banqueDestination='" + getBanqueDestination() + "'" +
            ", numeroCompteDestination='" + getNumeroCompteDestination() + "'" +
            ", referenceFacture='" + getReferenceFacture() + "'" +
            ", montantTotal=" + getMontantTotal() +
            ", montantAccount=" + getMontantAccount() +
            ", etat='" + getEtat() + "'" +
            ", fileJoin='" + getFileJoin() + "'" +
            ", userId='" + getUserId() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            "}";
    }
}
