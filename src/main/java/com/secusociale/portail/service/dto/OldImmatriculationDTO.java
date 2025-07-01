package com.secusociale.portail.service.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.service.soap.employeurInfos.CMEMPLOYEURINFOS;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.xml.ws.Holder;

/**
 * A DTO for the {@link com.secusociale.portail.domain.OldImmatriculation} entity.
 */
public class OldImmatriculationDTO implements Serializable {

    private Long id;

    private String typeIdentifiant;

    private String numeroIdentifiant;

    @Lob
    private String nineaFile;

    @Lob
    private String rcFile;

    private String status;

    private String moyenConfirmation;

    private Instant date;

    private String numeroRC;

    private Long userId;

    private String numeroUnique;

    private String agenceCSS;

    private String agenceIPRES;

    private Long agentId;

    private Instant dateTraitement;

    @Lob
    private String motif;

    @Lob
    private String extrasInfo;

    @Lob
    private String mandatFile;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    public String getNineaFile() {
        return nineaFile;
    }

    public void setNineaFile(String nineaFile) {
        this.nineaFile = nineaFile;
    }

    public String getRcFile() {
        return rcFile;
    }

    public void setRcFile(String rcFile) {
        this.rcFile = rcFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoyenConfirmation() {
        return moyenConfirmation;
    }

    public void setMoyenConfirmation(String moyenConfirmation) {
        this.moyenConfirmation = moyenConfirmation;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getNumeroRC() {
        return numeroRC;
    }

    public void setNumeroRC(String numeroRC) {
        this.numeroRC = numeroRC;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getAgenceCSS() {
        return agenceCSS;
    }

    public void setAgenceCSS(String agenceCSS) {
        this.agenceCSS = agenceCSS;
    }

    public String getAgenceIPRES() {
        return agenceIPRES;
    }

    public void setAgenceIPRES(String agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getExtrasInfo() {
        return extrasInfo;
    }

    public void setExtrasInfo(String extrasInfo) {
        this.extrasInfo = extrasInfo;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OldImmatriculationDTO oldImmatriculationDTO = (OldImmatriculationDTO) o;
        if (oldImmatriculationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oldImmatriculationDTO.getId());
    }

    public Holder<CMEMPLOYEURINFOS> getExtrasInfoPayload() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Holder<CMEMPLOYEURINFOS> imm = new Holder<CMEMPLOYEURINFOS>();
        try {
            String clearText = getExtrasInfo();
            imm = objectMapper.readValue(clearText, new TypeReference<Holder<CMEMPLOYEURINFOS>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            imm = null;
        }
        System.out.println(Objects.requireNonNull(imm).value.getInput().getNumeroIdentifiant());
        return imm;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OldImmatriculationDTO{" +
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
