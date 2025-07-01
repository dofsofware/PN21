package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A APGPaiment.
 */
@Entity
@Table(name = "apgpaiment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class APGPaiment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_paiment")
    private String referencePaiment;

    @Lob
    @Column(name = "factures_concernees")
    private String facturesConcernees;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "methode_de_paiement")
    private String methodeDePaiement;

    @Column(name = "date")
    private Instant date;

    @Column(name = "statut")
    private String statut;

    @Lob
    @Column(name = "details_paiement")
    private String detailsPaiement;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "person_id")
    private String personId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "payment_url")
    private String paymentUrl;

    @Column(name = "get_status_url")
    private String getStatusUrl;

    @Lob
    @Column(name = "details_requete")
    private String detailsRequete;

    @Column(name = "terminal_number")
    private String terminalNumber;

    @Column(name = "secret_key")
    private String secretKey;

    @Lob
    @Column(name = "brute_response")
    private String bruteResponse;

    @Lob
    @Column(name = "documents")
    private String documents;

    @Column(name = "signed")
    private Boolean signed;

    @Lob
    @Column(name = "signed_docs")
    private String signedDocs;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "timestamp")
    private Long timestamp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferencePaiment() {
        return referencePaiment;
    }

    public APGPaiment referencePaiment(String referencePaiment) {
        this.referencePaiment = referencePaiment;
        return this;
    }

    public void setReferencePaiment(String referencePaiment) {
        this.referencePaiment = referencePaiment;
    }

    public String getFacturesConcernees() {
        return facturesConcernees;
    }

    public APGPaiment facturesConcernees(String facturesConcernees) {
        this.facturesConcernees = facturesConcernees;
        return this;
    }

    public void setFacturesConcernees(String facturesConcernees) {
        this.facturesConcernees = facturesConcernees;
    }

    public Double getMontant() {
        return montant;
    }

    public APGPaiment montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getMethodeDePaiement() {
        return methodeDePaiement;
    }

    public APGPaiment methodeDePaiement(String methodeDePaiement) {
        this.methodeDePaiement = methodeDePaiement;
        return this;
    }

    public void setMethodeDePaiement(String methodeDePaiement) {
        this.methodeDePaiement = methodeDePaiement;
    }

    public Instant getDate() {
        return date;
    }

    public APGPaiment date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public APGPaiment statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDetailsPaiement() {
        return detailsPaiement;
    }

    public APGPaiment detailsPaiement(String detailsPaiement) {
        this.detailsPaiement = detailsPaiement;
        return this;
    }

    public void setDetailsPaiement(String detailsPaiement) {
        this.detailsPaiement = detailsPaiement;
    }

    public Long getUserId() {
        return userId;
    }

    public APGPaiment userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPersonId() {
        return personId;
    }

    public APGPaiment personId(String personId) {
        this.personId = personId;
        return this;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCurrency() {
        return currency;
    }

    public APGPaiment currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public APGPaiment paymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getGetStatusUrl() {
        return getStatusUrl;
    }

    public APGPaiment getStatusUrl(String getStatusUrl) {
        this.getStatusUrl = getStatusUrl;
        return this;
    }

    public void setGetStatusUrl(String getStatusUrl) {
        this.getStatusUrl = getStatusUrl;
    }

    public String getDetailsRequete() {
        return detailsRequete;
    }

    public APGPaiment detailsRequete(String detailsRequete) {
        this.detailsRequete = detailsRequete;
        return this;
    }

    public void setDetailsRequete(String detailsRequete) {
        this.detailsRequete = detailsRequete;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public APGPaiment terminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
        return this;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public APGPaiment secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBruteResponse() {
        return bruteResponse;
    }

    public APGPaiment bruteResponse(String bruteResponse) {
        this.bruteResponse = bruteResponse;
        return this;
    }

    public void setBruteResponse(String bruteResponse) {
        this.bruteResponse = bruteResponse;
    }

    public String getDocuments() {
        return documents;
    }

    public APGPaiment documents(String documents) {
        this.documents = documents;
        return this;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public Boolean isSigned() {
        return signed;
    }

    public APGPaiment signed(Boolean signed) {
        this.signed = signed;
        return this;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public String getSignedDocs() {
        return signedDocs;
    }

    public APGPaiment signedDocs(String signedDocs) {
        this.signedDocs = signedDocs;
        return this;
    }

    public void setSignedDocs(String signedDocs) {
        this.signedDocs = signedDocs;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public APGPaiment transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public APGPaiment timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof APGPaiment)) {
            return false;
        }
        return id != null && id.equals(((APGPaiment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "APGPaiment{" +
            "id=" + getId() +
            ", referencePaiment='" + getReferencePaiment() + "'" +
            ", facturesConcernees='" + getFacturesConcernees() + "'" +
            ", montant=" + getMontant() +
            ", methodeDePaiement='" + getMethodeDePaiement() + "'" +
            ", date='" + getDate() + "'" +
            ", statut='" + getStatut() + "'" +
            ", detailsPaiement='" + getDetailsPaiement() + "'" +
            ", userId=" + getUserId() +
            ", personId='" + getPersonId() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", paymentUrl='" + getPaymentUrl() + "'" +
            ", getStatusUrl='" + getGetStatusUrl() + "'" +
            ", detailsRequete='" + getDetailsRequete() + "'" +
            ", terminalNumber='" + getTerminalNumber() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", bruteResponse='" + getBruteResponse() + "'" +
            ", documents='" + getDocuments() + "'" +
            ", signed='" + isSigned() + "'" +
            ", signedDocs='" + getSignedDocs() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", timestamp=" + getTimestamp() +
            "}";
    }
}
