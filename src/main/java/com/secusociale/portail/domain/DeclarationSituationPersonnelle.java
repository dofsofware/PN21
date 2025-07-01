package com.secusociale.portail.domain;

import com.secusociale.portail.domain.enumeration.StatutDeclarationSituationPersonnelle;
import com.secusociale.portail.domain.enumeration.TypeDeclarationSituationPersonnelle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "declarationSituationPersonnelle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeclarationSituationPersonnelle  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "num_salarie")
    private Long numSalarie;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_declaration")
    private TypeDeclarationSituationPersonnelle typeDeclarationSituationPersonnelle;

    @Column(name = "date_declaration")
    private LocalDate dateDeclaration;

    @Column(name = "doc_link")
    private String docLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutDeclarationSituationPersonnelle statut;

    @Column(name = "user_id_validate")
    private Long userIdValidate;

    @Column(name = "date_validate")
    private LocalDate dateValidate;

    @Column(name = "motif")
    private String motif;

    @Column(name = "date_soumission")
    private LocalDate dateSoumission;

    @Column(name = "agence_ipres_id")
    private Long agenceIpresID;

    @Column(name = "agence_css_id")
    private Long agenceCssID;

    @Column(name = "grappe_familliale_id")
    private Long grappeFamillialeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DeclarationSituationPersonnelle id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public DeclarationSituationPersonnelle userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNumSalarie() {
        return this.numSalarie;
    }

    public DeclarationSituationPersonnelle numSalarie(Long numSalarie) {
        this.setNumSalarie(numSalarie);
        return this;
    }

    public void setNumSalarie(Long numSalarie) {
        this.numSalarie = numSalarie;
    }

    public TypeDeclarationSituationPersonnelle getTypeDeclaration() {
        return this.typeDeclarationSituationPersonnelle;
    }

    public DeclarationSituationPersonnelle typeDeclaration(TypeDeclarationSituationPersonnelle typeDeclarationSituationPersonnelle) {
        this.setTypeDeclaration(typeDeclarationSituationPersonnelle);
        return this;
    }

    public void setTypeDeclaration(TypeDeclarationSituationPersonnelle typeDeclarationSituationPersonnelle) {
        this.typeDeclarationSituationPersonnelle = typeDeclarationSituationPersonnelle;
    }

    public LocalDate getDateDeclaration() {
        return this.dateDeclaration;
    }

    public DeclarationSituationPersonnelle dateDeclaration(LocalDate dateDeclaration) {
        this.setDateDeclaration(dateDeclaration);
        return this;
    }

    public void setDateDeclaration(LocalDate dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getDocLink() {
        return this.docLink;
    }

    public DeclarationSituationPersonnelle docLink(String docLink) {
        this.setDocLink(docLink);
        return this;
    }

    public void setDocLink(String docLink) {
        this.docLink = docLink;
    }

    public StatutDeclarationSituationPersonnelle getStatut() {
        return this.statut;
    }

    public DeclarationSituationPersonnelle statut(StatutDeclarationSituationPersonnelle statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(StatutDeclarationSituationPersonnelle statut) {
        this.statut = statut;
    }

    public Long getUserIdValidate() {
        return this.userIdValidate;
    }

    public DeclarationSituationPersonnelle userIdValidate(Long userIdValidate) {
        this.setUserIdValidate(userIdValidate);
        return this;
    }

    public void setUserIdValidate(Long userIdValidate) {
        this.userIdValidate = userIdValidate;
    }

    public LocalDate getDateValidate() {
        return this.dateValidate;
    }

    public DeclarationSituationPersonnelle dateValidate(LocalDate dateValidate) {
        this.setDateValidate(dateValidate);
        return this;
    }

    public void setDateValidate(LocalDate dateValidate) {
        this.dateValidate = dateValidate;
    }

    public LocalDate getDateSoumission() {
        return this.dateSoumission;
    }

    public DeclarationSituationPersonnelle dateSoumission(LocalDate dateSoumission) {
        this.setDateSoumission(dateSoumission);
        return this;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getMotif() {
        return this.motif;
    }

    public DeclarationSituationPersonnelle motif(String motif) {
        this.setMotif(motif);
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Long getAgenceIpresID() {
        return this.agenceIpresID;
    }

    public DeclarationSituationPersonnelle agenceIpresID(Long agenceIpresID) {
        this.setAgenceIpresID(agenceIpresID);
        return this;
    }

    public void setAgenceIpresID(Long agenceIpresID) {
        this.agenceIpresID = agenceIpresID;
    }


    public Long getAgenceCssID() { return this.agenceCssID; }

    public DeclarationSituationPersonnelle agenceCssID(Long agenceCssID) {
        this.setAgenceCssID(agenceCssID);
        return this;
    }

    public void setAgenceCssID(Long agenceCssID) {
        this.agenceCssID = agenceCssID;
    }

    public Long getGrappeFamillialeId() { return this.grappeFamillialeId; }

    public DeclarationSituationPersonnelle grappeFamillialeId(Long grappeFamillialeId) {
        this.setGrappeFamillialeId(grappeFamillialeId);
        return this;
    }

    public void setGrappeFamillialeId(Long grappeFamillialeId) {
        this.grappeFamillialeId = grappeFamillialeId;
    }



    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Declaration)) {
            return false;
        }
        return getId() != null && getId().equals(((Declaration) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeclarationSituationPersonnelle{" +
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
            "}";
    }
}
