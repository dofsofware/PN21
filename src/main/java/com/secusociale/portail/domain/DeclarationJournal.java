package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.secusociale.portail.domain.enumeration.StatutSynchronisation;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.dto.custom.DetailDTO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A Declaration.
 */
@Entity
@Table(name = "declaration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeclarationJournal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "type_identifiant")
    private String typeIdentifiant;

    @Column(name = "id_identifiant")
    private String idIdentifiant;

    @Column(name = "type_declaration")
    private String typeDeclaration;

    @Column(name = "debut_cotisation")
    private Instant debutCotisation;

    @Column(name = "fin_cotisation")
    private Instant finCotisation;

    @Column(name = "address")
    private String address;

    @Column(name = "process_flow_id")
    private String processFlowId;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "is_uploaded")
    private Boolean isUploaded;

    @Column(name = "file_url")
    private String fileURL;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "i_pdidtraitement")
    private String iPDIDTRAITEMENT;

    @Lob
    @Column(name = "details")
    private String details;

    @Column(name = "status")
    private String status;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "owner_id")
    private Long ownerID;

    @Transient
    @JsonSerialize
    private UserDTO user;

    @Transient
    @JsonSerialize
    private Agence agenceCSS;

    @Transient
    @JsonSerialize
    private Agence agenceIPRES;

    @Lob
    @Column(name = "reponse_brute")
    private String reponseBrute;

    @Column(name = "code_agence_css")
    private String codeAgenceCSS;

    @Column(name = "code_agence_ipres")
    private String codeAgenceIPRES;

    @Lob
    @Column(name = "requete_brute")
    private String requeteBrute;

    @Column(name = "nombre_erreurs")
    private Long nombreErreurs;

    @Column(name = "numero_facture_synthese")
    private Long synthese;


    @Column(name = "date_synchronisation")
    private Timestamp dateSynchronisation;

    @Lob
    @Column(name = "rapport_synchronisation")
    private String rapportSynchronisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_synchronisation")
    private StatutSynchronisation statutSynchronisation;

    @Column(name = "base_id_synchronisation")
    private Integer baseIdSynchronisation;

    @Lob
    @Column(name = "infos_synchronisation")
    private String infosSynchronisation;


    public void DeclarationRes(DeclarationSupprime declaration) {
        this.id = declaration.getId();
        this.uuid = declaration.getUuid();
        this.typeIdentifiant = declaration.getTypeIdentifiant();
        this.idIdentifiant = declaration.getIdIdentifiant();
        this.typeDeclaration = declaration.getTypeDeclaration();
        this.debutCotisation = declaration.getDebutCotisation();
        this.finCotisation = declaration.getFinCotisation();
        this.address = declaration.getAddress();
        this.processFlowId = declaration.getProcessFlowId();
        this.raisonSociale = declaration.getRaisonSociale();
        this.createAt = declaration.getCreateAt();
        this.fileURL = declaration.getFileURL();
        this.numeroUnique = declaration.getNumeroUnique();
        this.status = declaration.getStatus();
        this.fileName = declaration.getFileName();
        this.ownerID = declaration.getOwnerID();
        this.reponseBrute = declaration.getReponseBrute();
        this.codeAgenceCSS = declaration.getCodeAgenceCSS();
        this.codeAgenceIPRES = declaration.getCodeAgenceIPRES();
        this.rapportSynchronisation = declaration.getRapportSynchronisation();
        this.dateSynchronisation = declaration.getDateSynchronisation();
        this.statutSynchronisation = declaration.getStatutSynchronisation();
        this.baseIdSynchronisation = declaration.getBaseIdSynchronisation();
        this.infosSynchronisation = declaration.getInfosSynchronisation();
    }


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public DeclarationJournal uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public DeclarationJournal synthese(Long synthese) {
        this.synthese = synthese;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public DeclarationJournal typeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
        return this;
    }

    public void setTypeIdentifiant(String typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public String getIdIdentifiant() {
        return idIdentifiant;
    }

    public DeclarationJournal idIdentifiant(String idIdentifiant) {
        this.idIdentifiant = idIdentifiant;
        return this;
    }

    public void setIdIdentifiant(String idIdentifiant) {
        this.idIdentifiant = idIdentifiant;
    }

    public String getTypeDeclaration() {
        return typeDeclaration;
    }

    public DeclarationJournal typeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
        return this;
    }

    public void setTypeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public Instant getDebutCotisation() {
        return debutCotisation;
    }

    public DeclarationJournal debutCotisation(Instant debutCotisation) {
        this.debutCotisation = debutCotisation;
        return this;
    }

    public void setDebutCotisation(Instant debutCotisation) {
        this.debutCotisation = debutCotisation;
    }

    public Instant getFinCotisation() {
        return finCotisation;
    }

    public DeclarationJournal finCotisation(Instant finCotisation) {
        this.finCotisation = finCotisation;
        return this;
    }

    public void setFinCotisation(Instant finCotisation) {
        this.finCotisation = finCotisation;
    }

    public String getAddress() {
        return address;
    }

    public DeclarationJournal address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProcessFlowId() {
        return processFlowId;
    }

    public DeclarationJournal processFlowId(String processFlowId) {
        this.processFlowId = processFlowId;
        return this;
    }

    public void setProcessFlowId(String processFlowId) {
        this.processFlowId = processFlowId;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public DeclarationJournal raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public DeclarationJournal createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Boolean isIsUploaded() {
        return isUploaded != null ? isUploaded : false;
    }

    public DeclarationJournal isUploaded(Boolean isUploaded) {
        this.isUploaded = isUploaded;
        return this;
    }

    public void setIsUploaded(Boolean isUploaded) {
        this.isUploaded = isUploaded;
    }

    public String getFileURL() {
        return fileURL;
    }

    public DeclarationJournal fileURL(String fileURL) {
        this.fileURL = fileURL;
        return this;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public DeclarationJournal numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getiPDIDTRAITEMENT() {
        return iPDIDTRAITEMENT;
    }

    public DeclarationJournal iPDIDTRAITEMENT(String iPDIDTRAITEMENT) {
        this.iPDIDTRAITEMENT = iPDIDTRAITEMENT;
        return this;
    }

    public void setiPDIDTRAITEMENT(String iPDIDTRAITEMENT) {
        this.iPDIDTRAITEMENT = iPDIDTRAITEMENT;
    }

    public DetailDTO[] getDetails() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DetailDTO[] detailDTO;
        if (!StringUtils.isEmpty(details)) {
            try {
                detailDTO = objectMapper.readValue(details, DetailDTO[].class);
                return detailDTO;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new DetailDTO[0];
        }
    }

    public DeclarationJournal details(String details) {
        this.details = details;
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public DeclarationJournal status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public DeclarationJournal fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean isIsRead() {
        return isRead;
    }

    public DeclarationJournal isRead(Boolean isRead) {
        this.isRead = isRead;
        return this;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public DeclarationJournal ownerID(Long ownerID) {
        this.ownerID = ownerID;
        return this;
    }

    public String getReponseBrute() {
        return reponseBrute;
    }

    public DeclarationJournal reponseBrute(String reponseBrute) {
        this.reponseBrute = reponseBrute;
        return this;
    }

    public void setReponseBrute(String reponseBrute) {
        this.reponseBrute = reponseBrute;
    }

    @JsonIgnore
    public String getCodeAgenceCSS() {
        return codeAgenceCSS;
    }

    public DeclarationJournal codeAgenceCSS(String codeAgenceCSS) {
        this.codeAgenceCSS = codeAgenceCSS;
        return this;
    }

    public void setCodeAgenceCSS(String codeAgenceCSS) {
        this.codeAgenceCSS = codeAgenceCSS;
    }

    @JsonIgnore
    public String getCodeAgenceIPRES() {
        return codeAgenceIPRES;
    }

    public DeclarationJournal codeAgenceIPRES(String codeAgenceIPRES) {
        this.codeAgenceIPRES = codeAgenceIPRES;
        return this;
    }

    public void setCodeAgenceIPRES(String codeAgenceIPRES) {
        this.codeAgenceIPRES = codeAgenceIPRES;
    }

    public Agence getAgenceCSS() {
        return agenceCSS;
    }

    public void setAgenceCSS(Agence agenceCSS) {
        this.agenceCSS = agenceCSS;
    }

    public Agence getAgenceIPRES() {
        return agenceIPRES;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setAgenceIPRES(Agence agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
    }

    public String getRequeteBrute() {
        return requeteBrute;
    }

    public DeclarationJournal requeteBrute(String requeteBrute) {
        this.requeteBrute = requeteBrute;
        return this;
    }

    public void setRequeteBrute(String requeteBrute) {
        this.requeteBrute = requeteBrute;
    }

    public Long getNombreErreurs() {
        return nombreErreurs == null ? 0L : nombreErreurs;
    }

    public DeclarationJournal nombreErreurs(Long nombreErreurs) {
        this.nombreErreurs = nombreErreurs;
        return this;
    }

    public void setNombreErreurs(Long nombreErreurs) {
        this.nombreErreurs = nombreErreurs;
    }

    public Long getSynthese() {
        return synthese;
    }

    public void setSynthese(Long synthese) {
        this.synthese = synthese;
    }

    public String getInfosSynchronisation() {
        return infosSynchronisation;
    }

    public void setInfosSynchronisation(String infosSynchronisation) {
        this.infosSynchronisation = infosSynchronisation;
    }

    public Integer getBaseIdSynchronisation() {
        return baseIdSynchronisation;
    }

    public void setBaseIdSynchronisation(Integer baseIdSynchronisation) {
        this.baseIdSynchronisation = baseIdSynchronisation;
    }

    public StatutSynchronisation getStatutSynchronisation() {
        return statutSynchronisation;
    }

    public void setStatutSynchronisation(StatutSynchronisation statutSynchronisation) {
        this.statutSynchronisation = statutSynchronisation;
    }

    public String getRapportSynchronisation() {
        return rapportSynchronisation;
    }

    public void setRapportSynchronisation(String rapportSynchronisation) {
        this.rapportSynchronisation = rapportSynchronisation;
    }

    public Timestamp getDateSynchronisation() {
        return dateSynchronisation;
    }

    public void setDateSynchronisation(Timestamp dateSynchronisation) {
        this.dateSynchronisation = dateSynchronisation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeclarationJournal)) {
            return false;
        }
        return id != null && id.equals(((DeclarationJournal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Declaration{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", typeIdentifiant='" + getTypeIdentifiant() + "'" +
            ", idIdentifiant='" + getIdIdentifiant() + "'" +
            ", typeDeclaration='" + getTypeDeclaration() + "'" +
            ", debutCotisation='" + getDebutCotisation() + "'" +
            ", finCotisation='" + getFinCotisation() + "'" +
            ", address='" + getAddress() + "'" +
            ", processFlowId='" + getProcessFlowId() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", isUploaded='" + isIsUploaded() + "'" +
            ", fileURL='" + getFileURL() + "'" +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", iPDIDTRAITEMENT='" + getiPDIDTRAITEMENT() + "'" +
            ", details='" + details + "'" +
            ", status='" + getStatus() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", isRead='" + isIsRead() + "'" +
            ", ownerID=" + getOwnerID() +
            ", reponseBrute=" + getReponseBrute() +
            "}";
    }
}
