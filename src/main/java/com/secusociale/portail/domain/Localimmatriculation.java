package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.service.SecurityService;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND;
import com.secusociale.portail.service.soap.domestique.InboundDomFrm;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUND;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAI;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUND;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Localimmatriculation.
 */
@Entity
@Table(name = "localimmatriculation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Localimmatriculation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_immatriculation")
    @JsonProperty("typeImmatriculation")
    private String type;

    @Column(name = "numdoc")
    private String numdoc;

    @Column(name = "statusdoc")
    private String statusdoc;

    @Column(name = "statusdoc_desc")
    private String statusdesc;

    @Lob
    @Column(name = "payload")
    @JsonProperty("immatriculation")
    private String payload;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "employeur_id")
    private Long employeur;

    @NotNull
    @Column(name = "is_submit", nullable = false)
    private Boolean isSubmit;

    @Column(name = "ninea", nullable = true)
    @JsonProperty("numeroUnique")
    private String ninea;

    @Column(name = "id_type", nullable = true)
    @JsonProperty("typeIdentifiant")
    private String typeIdentifiant;

    @Column(name = "created_at")
    private Instant createdAt;

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public Localimmatriculation typeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
        return this;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public Localimmatriculation numdoc(String numdoc) {
        this.numdoc = numdoc;
        return this;
    }

    public String getStatusdoc() {
        return statusdoc;
    }

    public void setStatusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
    }

    public Localimmatriculation statusdoc(String statusdoc) {
        this.statusdoc = statusdoc;
        return this;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public Object getPayload() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object imm = null;
        switch (type) {
            case "INDEPENDANT":
                imm = new CMCrtIndForXAI();
                try {
                    String clearText = SecurityService.decrypText(this.payload);
                    imm = objectMapper.readValue(clearText, CMCrtIndForXAI.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "PUBLIQUE-PARAPUBLIQUE":
                imm = new IMMAT2INBOUND();
                try {
                    String clearText = SecurityService.decrypText(this.payload);
                    imm = objectMapper.readValue(clearText, IMMAT2INBOUND.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "IMMATPORTAIL":
                imm = new IMMATRICULATIONINBOUND();
                try {
                    String clearText = SecurityService.decrypText(this.payload);
                    imm = objectMapper.readValue(clearText, IMMATRICULATIONINBOUND.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "MAINTIENT-AFFILIATION":
                imm = new MAINTAFFINBOUND();
                try {
                    String clearText = SecurityService.decrypText(this.payload);
                    imm = objectMapper.readValue(clearText, MAINTAFFINBOUND.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "REPRESENTATION-DIPLOMATIQUE":
                imm = new IMMATREPDIPLO();
                try {
                    String clearText = SecurityService.decrypText(this.payload);
                    imm = objectMapper.readValue(clearText, IMMATREPDIPLO.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
            case "DOMESTIQUE":
                imm = new InboundDomFrm();
                try {
                    String clearText = SecurityService.decrypText(this.payload);
                    imm = objectMapper.readValue(clearText, InboundDomFrm.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    imm = null;
                }
                break;
        }

        return imm;
    }

    public String getClearPayload() {
        try {
            return SecurityService.decrypText(this.payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "";
    }

    public void setPayload(String payload) {
        try {
            this.payload = SecurityService.encrypText(payload);
        } catch (Exception e) {
            e.printStackTrace();
            this.payload = payload;
        }
    }

    public Localimmatriculation payload(String payload) {
        this.payload = payload;
        return this;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Localimmatriculation user(Long user) {
        this.user = user;
        return this;
    }

    public Long getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Long employeur) {
        this.employeur = employeur;
    }

    public Localimmatriculation employeur(Long employeur) {
        this.employeur = employeur;
        return this;
    }

    public Boolean isIsSubmited() {
        return isSubmit;
    }

    public Localimmatriculation isSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
        return this;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }


    public String getNinea() {
        try {
            if (StringUtils.isNotEmpty(ninea) && ninea.length() > 9) {
                switch (type) {
                    case "PUBLIQUE-PARAPUBLIQUE":
                        return ((IMMAT2INBOUND) getPayload()).getInput().getEmployerQuery().getNineaNumber();
                    case "IMMATPORTAIL":
                        return ((IMMATRICULATIONINBOUND) getPayload()).getInput().getEmployerQuery().getNineaNumber();
                    case "REPRESENTATION-DIPLOMATIQUE":
                        return ((IMMATREPDIPLO) getPayload()).getInput().getEmployerQuery().getNineaNumber();
                }
            }
        } catch (Exception e) {
            return ninea;
        }
        return ninea;
    }


    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public Localimmatriculation ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Localimmatriculation createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Localimmatriculation)) {
            return false;
        }
        return id != null && id.equals(((Localimmatriculation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Localimmatriculation{" +
            "id=" + getId() +
            ", numdoc='" + getNumdoc() + "'" +
            ", statusdoc='" + getStatusdoc() + "'" +
            ", statusdesc='" + getStatusdesc() + "'" +
            ", payload='" + getPayload() + "'" +
            ", user=" + getUser() +
            ", employeur=" + getEmployeur() +
            ", isSubmited='" + isIsSubmited() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
