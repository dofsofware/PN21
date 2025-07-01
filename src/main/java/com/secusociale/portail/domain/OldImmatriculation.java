package com.secusociale.portail.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.service.soap.employeurInfos.CMEMPLOYEURINFOS;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.*;
import javax.xml.ws.Holder;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OldImmatriculation.
 */
@Entity
@Table(name = "old_immatriculation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OldImmatriculation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_identifiant")
    private String typeIdentifiant;

    @Column(name = "numero_identifiant")
    private String numeroIdentifiant;

    @Lob
    @Column(name = "ninea_file")
    private String nineaFile;

    @Lob
    @Column(name = "rc_file")
    private String rcFile;

    @Column(name = "status")
    private String status;

    @Column(name = "moyen_confirmation")
    private String moyenConfirmation;

    @Column(name = "date")
    private Instant date;

    @Column(name = "numero_rc")
    private String numeroRC;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "agence_css")
    private String agenceCSS;

    @Column(name = "agence_ipres")
    private String agenceIPRES;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "date_traitement")
    private Instant dateTraitement;

    @Lob
    @Column(name = "motif_rejet")
    private String motif;

    @Lob
    @Column(name = "extra_infos")
    private String extrasInfo;

    @Lob
    @Column(name = "mandat_file")
    private String mandatFile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public OldImmatriculation typeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
        return this;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public OldImmatriculation numeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
        return this;
    }

    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public String getNineaFile() {
        return nineaFile;
    }

    public OldImmatriculation nineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
        return this;
    }

    public void setNineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
    }

    public String getRcFile() {
        return rcFile;
    }

    public OldImmatriculation rcFile(String rcFile) {
        this.rcFile = rcFile;
        return this;
    }

    public void setRcFile(String rcFile) {
        this.rcFile = rcFile;
    }

    public String getStatus() {
        return status;
    }

    public OldImmatriculation status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public OldImmatriculation moyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
        return this;
    }

    public void setMoyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public Instant getDate() {
        return date;
    }

    public OldImmatriculation date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getNumeroRC() {
        return numeroRC;
    }

    public OldImmatriculation numeroRC(String numeroRC) {
        this.numeroRC = numeroRC;
        return this;
    }

    public void setNumeroRC(String numeroRC) {
        this.numeroRC = numeroRC;
    }

    public Long getUserId() {
        return userId;
    }

    public OldImmatriculation userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public OldImmatriculation numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getAgenceCSS() {
        return agenceCSS;
    }

    public OldImmatriculation agenceCSS(String agenceCSS) {
        this.agenceCSS = agenceCSS;
        return this;
    }

    public void setAgenceCSS(String agenceCSS) {
        this.agenceCSS = agenceCSS;
    }

    public String getAgenceIPRES() {
        return agenceIPRES;
    }

    public OldImmatriculation agenceIPRES(String agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
        return this;
    }

    public void setAgenceIPRES(String agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
    }

    public Long getAgentId() {
        return agentId;
    }

    public OldImmatriculation agentId(Long agentId) {
        this.agentId = agentId;
        return this;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public OldImmatriculation dateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
        return this;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getMotif() {
        return motif;
    }

    public OldImmatriculation motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public JSONObject getExtrasInfoPayload() {
        JSONObject imm = new JSONObject();
        try {
            String clearText = getExtrasInfo();
            imm = new JSONObject(clearText).getJSONObject("value");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            imm = null;
        }
        return imm;
    }

    public String getExtrasInfo() {
        return extrasInfo;
    }

    public OldImmatriculation extrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
        return this;
    }

    public void setExtrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public OldImmatriculation mandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
        return this;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OldImmatriculation)) {
            return false;
        }
        return id != null && id.equals(((OldImmatriculation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OldImmatriculation{" +
            "id=" + getId() +
            ", typeIdentifiant='" + getTypeIdentifiant() + "'" +
            ", numeroIdentifiant='" + getNumeroIdentifiant() + "'" +
            ", nineaFile='" + getNineaFile() + "'" +
            ", rcFile='" + getRcFile() + "'" +
            ", status='" + getStatus() + "'" +
            ", moyenConfirmation='" + getMoyenConfirmation() + "'" +
            ", date='" + getDate() + "'" +
            ", numeroRC='" + getNumeroRC() + "'" +
            ", userId=" + getUserId() +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", agenceCSS='" + getAgenceCSS() + "'" +
            ", agenceIPRES='" + getAgenceIPRES() + "'" +
            ", agentId=" + getAgentId() +
            ", dateTraitement='" + getDateTraitement() + "'" +
            ", motif='" + getMotif() + "'" +
            ", extraInfos='" + getExtrasInfo() + "'" +
            ", mandatFile='" + getMandatFile() + "'" +
            "}";
    }
}
