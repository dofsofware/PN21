package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.GrappeMember;
import com.secusociale.portail.domain.PieceJointe;
import com.secusociale.portail.domain.enumeration.StatutDeclarationSituationPersonnelle;
import com.secusociale.portail.domain.enumeration.TypeDeclarationSituationPersonnelle;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DeclarationSituationPersonnelleDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long numSalarie;

    private TypeDeclarationSituationPersonnelle typeDeclarationSituationPersonnelle;

    private LocalDate dateDeclaration;

    private String docLink;

    private StatutDeclarationSituationPersonnelle statut;

    private Long userIdValidate;

    private LocalDate dateValidate;

    private String motif;

    private LocalDate dateSoumission;

    private Long agenceIpresID;

    private Long agenceCssID;

    private Long grappeFamillialeId;

    private GrappeMember grappeMember;

    private List<PieceJointe>  pieceJointeSalaries;

    public List<PieceJointe> getPieceJointeSalaries() {
        return pieceJointeSalaries;
    }

    public void setPieceJointeSalaries(List<PieceJointe> pieceJointeSalaries) {
        this.pieceJointeSalaries = pieceJointeSalaries;
    }


    public GrappeMember getGrappeMember() {
        return grappeMember;
    }

    public void setGrappeMember(GrappeMember grappeMember) {
        this.grappeMember = grappeMember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgenceIpresID() { return agenceIpresID;  }

    public void setAgenceIpresID(Long agenceIpresID) { this.agenceIpresID = agenceIpresID; }

    public Long getGrappeFamillialeId() { return grappeFamillialeId;  }

    public void setGrappeFamillialeId(Long grappeFamillialeId) { this.grappeFamillialeId = grappeFamillialeId; }

    public Long getAgenceCssID() {
        return agenceCssID;
    }

    public void setAgenceCssID(Long agenceCssID) {
        this.agenceCssID = agenceCssID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNumSalarie() {
        return numSalarie;
    }

    public void setNumSalarie(Long numSalarie) {
        this.numSalarie = numSalarie;
    }

    public TypeDeclarationSituationPersonnelle getTypeDeclaration() {
        return typeDeclarationSituationPersonnelle;
    }

    public void setTypeDeclaration(TypeDeclarationSituationPersonnelle typeDeclarationSituationPersonnelle) {
        this.typeDeclarationSituationPersonnelle = typeDeclarationSituationPersonnelle;
    }

    public LocalDate getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(LocalDate dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getDocLink() {
        return docLink;
    }

    public void setDocLink(String docLink) {
        this.docLink = docLink;
    }

    public StatutDeclarationSituationPersonnelle getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclarationSituationPersonnelle statut) {
        this.statut = statut;
    }

    public Long getUserIdValidate() {
        return userIdValidate;
    }

    public void setUserIdValidate(Long userIdValidate) {
        this.userIdValidate = userIdValidate;
    }

    public LocalDate getDateValidate() {
        return dateValidate;
    }

    public void setDateValidate(LocalDate dateValidate) {
        this.dateValidate = dateValidate;
    }

    public LocalDate getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeclarationSituationPersonnelleDTO)) {
            return false;
        }

        DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO = (DeclarationSituationPersonnelleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, declarationSituationPersonnelleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "declarationSituationPersonnelleDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", numSalarie=" + getNumSalarie() +
            ", typeDeclaration='" + getTypeDeclaration() + "'" +
            ", dateDeclaration='" + getDateDeclaration() + "'" +
            ", docLink='" + getDocLink() + "'" +
            ", statut='" + getStatut() + "'" +
            ", userIdValidate='" + getUserIdValidate() + "'" +
            ", dateValidate='" + getDateValidate() + "'" +
            ", motif='" + getMotif() + "'" +
            ", dateSoumission='" + getDateSoumission() + "'" +
            ", agenceIpresID='" + getAgenceIpresID() + "'" +
            ", agenceCssID='" + getAgenceCssID() + "'" +
            ", getGrappeFamillialeId='" + getGrappeFamillialeId() + "'" +
            "}";
    }
}
