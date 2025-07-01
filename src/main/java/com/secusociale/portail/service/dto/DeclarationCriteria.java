package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.secusociale.portail.domain.Declaration} entity. This class is used
 * in {@link com.secusociale.portail.web.rest.DeclarationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /old-immatriculations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DeclarationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter typeIdentifiant;

    private StringFilter idIdentifiant;

    private StringFilter typeDeclaration;

    private InstantFilter debutCotisation;

    private InstantFilter finCotisation;

    private StringFilter address;

    private StringFilter processFlowId;

    private StringFilter raisonSociale;

    private InstantFilter createAt;

    private BooleanFilter isUploaded;

    private StringFilter fileURL;

    private StringFilter numeroUnique;

    private StringFilter iPDIDTRAITEMENT;

    private StringFilter details;

    private StringFilter status;

    private StringFilter fileName;

    private BooleanFilter isRead;

    private LongFilter ownerID;

    private StringFilter reponseBrute;

    private StringFilter codeAgenceCSS;

    private StringFilter codeAgenceIPRES;

    public DeclarationCriteria() {
    }

    public DeclarationCriteria(DeclarationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.typeIdentifiant = other.typeIdentifiant == null ? null : other.typeIdentifiant.copy();
        this.idIdentifiant = other.idIdentifiant == null ? null : other.idIdentifiant.copy();
        this.typeDeclaration = other.typeDeclaration == null ? null : other.typeDeclaration.copy();
        this.debutCotisation = other.debutCotisation == null ? null : other.debutCotisation.copy();
        this.finCotisation = other.finCotisation == null ? null : other.finCotisation.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.processFlowId = other.processFlowId == null ? null : other.processFlowId.copy();
        this.raisonSociale = other.raisonSociale == null ? null : other.raisonSociale.copy();
        this.createAt = other.createAt == null ? null : other.createAt.copy();
        this.isUploaded = other.isUploaded == null ? null : other.isUploaded.copy();
        this.fileURL = other.fileURL == null ? null : other.fileURL.copy();
        this.numeroUnique = other.numeroUnique == null ? null : other.numeroUnique.copy();
        this.iPDIDTRAITEMENT = other.iPDIDTRAITEMENT == null ? null : other.iPDIDTRAITEMENT.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
        this.isRead = other.isRead == null ? null : other.isRead.copy();
        this.ownerID = other.ownerID == null ? null : other.ownerID.copy();
        this.reponseBrute = other.reponseBrute == null ? null : other.reponseBrute.copy();
        this.codeAgenceCSS = other.codeAgenceCSS == null ? null : other.codeAgenceCSS.copy();
        this.codeAgenceIPRES = other.codeAgenceIPRES == null ? null : other.codeAgenceIPRES.copy();
    }

    @Override
    public DeclarationCriteria copy() {
        return new DeclarationCriteria(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeclarationCriteria)) return false;
        DeclarationCriteria that = (DeclarationCriteria) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getTypeIdentifiant(), that.getTypeIdentifiant()) && Objects.equals(getIdIdentifiant(), that.getIdIdentifiant()) && Objects.equals(getTypeDeclaration(), that.getTypeDeclaration()) && Objects.equals(getDebutCotisation(), that.getDebutCotisation()) && Objects.equals(getFinCotisation(), that.getFinCotisation()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getProcessFlowId(), that.getProcessFlowId()) && Objects.equals(getRaisonSociale(), that.getRaisonSociale()) && Objects.equals(getCreateAt(), that.getCreateAt()) && Objects.equals(getIsUploaded(), that.getIsUploaded()) && Objects.equals(getFileURL(), that.getFileURL()) && Objects.equals(getNumeroUnique(), that.getNumeroUnique()) && Objects.equals(getiPDIDTRAITEMENT(), that.getiPDIDTRAITEMENT()) && Objects.equals(getDetails(), that.getDetails()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getFileName(), that.getFileName()) && Objects.equals(getIsRead(), that.getIsRead()) && Objects.equals(getOwnerID(), that.getOwnerID()) && Objects.equals(getReponseBrute(), that.getReponseBrute()) && Objects.equals(getCodeAgenceCSS(), that.getCodeAgenceCSS()) && Objects.equals(getCodeAgenceIPRES(), that.getCodeAgenceIPRES());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getTypeIdentifiant(), getIdIdentifiant(), getTypeDeclaration(), getDebutCotisation(), getFinCotisation(), getAddress(), getProcessFlowId(), getRaisonSociale(), getCreateAt(), getIsUploaded(), getFileURL(), getNumeroUnique(), getiPDIDTRAITEMENT(), getDetails(), getStatus(), getFileName(), getIsRead(), getOwnerID(), getReponseBrute(), getCodeAgenceCSS(), getCodeAgenceIPRES());
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUuid() {
        return uuid;
    }

    public void setUuid(StringFilter uuid) {
        this.uuid = uuid;
    }

    public StringFilter getTypeIdentifiant() {
        return typeIdentifiant;
    }

    public void setTypeIdentifiant(StringFilter typeIdentifiant) {
        this.typeIdentifiant = typeIdentifiant;
    }

    public StringFilter getIdIdentifiant() {
        return idIdentifiant;
    }

    public void setIdIdentifiant(StringFilter idIdentifiant) {
        this.idIdentifiant = idIdentifiant;
    }

    public StringFilter getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(StringFilter typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public InstantFilter getDebutCotisation() {
        return debutCotisation;
    }

    public void setDebutCotisation(InstantFilter debutCotisation) {
        this.debutCotisation = debutCotisation;
    }

    public InstantFilter getFinCotisation() {
        return finCotisation;
    }

    public void setFinCotisation(InstantFilter finCotisation) {
        this.finCotisation = finCotisation;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getProcessFlowId() {
        return processFlowId;
    }

    public void setProcessFlowId(StringFilter processFlowId) {
        this.processFlowId = processFlowId;
    }

    public StringFilter getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(StringFilter raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public InstantFilter getCreateAt() {
        return createAt;
    }

    public void setCreateAt(InstantFilter createAt) {
        this.createAt = createAt;
    }

    public BooleanFilter getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(BooleanFilter isUploaded) {
        this.isUploaded = isUploaded;
    }

    public StringFilter getFileURL() {
        return fileURL;
    }

    public void setFileURL(StringFilter fileURL) {
        this.fileURL = fileURL;
    }

    public StringFilter getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(StringFilter numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public StringFilter getiPDIDTRAITEMENT() {
        return iPDIDTRAITEMENT;
    }

    public void setiPDIDTRAITEMENT(StringFilter iPDIDTRAITEMENT) {
        this.iPDIDTRAITEMENT = iPDIDTRAITEMENT;
    }

    public StringFilter getDetails() {
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getFileName() {
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    public BooleanFilter getIsRead() {
        return isRead;
    }

    public void setIsRead(BooleanFilter isRead) {
        this.isRead = isRead;
    }

    public LongFilter getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(LongFilter ownerID) {
        this.ownerID = ownerID;
    }

    public StringFilter getReponseBrute() {
        return reponseBrute;
    }

    public void setReponseBrute(StringFilter reponseBrute) {
        this.reponseBrute = reponseBrute;
    }

    public StringFilter getCodeAgenceCSS() {
        return codeAgenceCSS;
    }

    public void setCodeAgenceCSS(StringFilter codeAgenceCSS) {
        this.codeAgenceCSS = codeAgenceCSS;
    }

    public StringFilter getCodeAgenceIPRES() {
        return codeAgenceIPRES;
    }

    public void setCodeAgenceIPRES(StringFilter codeAgenceIPRES) {
        this.codeAgenceIPRES = codeAgenceIPRES;
    }

    @Override
    public String toString() {
        return "DeclarationCriteria{" +
            "id=" + id +
            ", uuid=" + uuid +
            ", typeIdentifiant=" + typeIdentifiant +
            ", idIdentifiant=" + idIdentifiant +
            ", typeDeclaration=" + typeDeclaration +
            ", debutCotisation=" + debutCotisation +
            ", finCotisation=" + finCotisation +
            ", address=" + address +
            ", processFlowId=" + processFlowId +
            ", raisonSociale=" + raisonSociale +
            ", createAt=" + createAt +
            ", isUploaded=" + isUploaded +
            ", fileURL=" + fileURL +
            ", numeroUnique=" + numeroUnique +
            ", iPDIDTRAITEMENT=" + iPDIDTRAITEMENT +
            ", details=" + details +
            ", status=" + status +
            ", fileName=" + fileName +
            ", isRead=" + isRead +
            ", ownerID=" + ownerID +
            ", reponseBrute=" + reponseBrute +
            ", codeAgenceCSS=" + codeAgenceCSS +
            ", codeAgenceIPRES=" + codeAgenceIPRES +
            '}';
    }
}
