package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.secusociale.portail.domain.enumeration.StatutReclamation;
import com.secusociale.portail.domain.enumeration.TypeReclamation;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A Reclamation.
 */
@Entity
@Table(name = "reclamation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_reclamation", nullable = false)
    private TypeReclamation typeReclamation;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "agence_id", nullable = false)
    private Long agenceId;

    @Column(name = "num_salarie")
    private Long numSalarie;

    @Column(name = "second_num_salarie")
    private Long secondNumSalarie;

    @Column(name = "lib_autres_rec")
    private String libAutresRec;

    @Lob
    @Column(name = "description_autres")
    private String descriptionAutres;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutReclamation statut;

    @Column(name = "date_soumission")
    private LocalDate dateSoumission;

    @Column(name = "date_validate_gc")
    private LocalDate dateValidateGC;

    @Column(name = "user_id_validate_gc")
    private Long userIdValidateGC;

    @Column(name = "date_validate_cf_css")
    private LocalDate dateValidateCFCSS;

    @Column(name = "user_id_validate_cf_css")
    private Long userIdValidateCFCSS;

    @Column(name = "date_validate_cf_ipres")
    private LocalDate dateValidateCFIPRES;

    @Column(name = "user_id_validate_cf_ipres")
    private Long userIdValidateCFIPRES;

    @Column(name = "motif")
    private String motif;

    @Column(name = "agence_ipres_id")
    private Long agenceIpresID;

    @Column(name = "agence_css_id")
    private Long agenceCssID;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("reclamation")
    private Set<CarriereManquantes> carriereManquantes = new HashSet<>();

    public Set<CarriereManquantes> getCarriereManquantes() {
        return carriereManquantes;
    }

    public void setCarriereManquantes(Set<CarriereManquantes> carriereManquantes) {
        this.carriereManquantes = carriereManquantes;
    }

    public void addCarriereManquante(CarriereManquantes carriereManquante) {

        if (carriereManquantes == null) {
            carriereManquantes = new HashSet<>();
        }
        carriereManquantes.add(carriereManquante);
        carriereManquante.setReclamation(this);
    }

    public void removeCarriereManquante(CarriereManquantes carriereManquante) {
        carriereManquante.setReclamation(null);
        this.carriereManquantes.remove(carriereManquante);
    }


    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Reclamation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeReclamation getTypeReclamation() {
        return this.typeReclamation;
    }

    public Reclamation typeReclamation(TypeReclamation typeReclamation) {
        this.setTypeReclamation(typeReclamation);
        return this;
    }

    public void setTypeReclamation(TypeReclamation typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Reclamation userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgenceId() {
        return this.agenceId;
    }

    public Reclamation agenceId(Long agenceId) {
        this.setAgenceId(agenceId);
        return this;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }

    public Long getNumSalarie() {
        return this.numSalarie;
    }

    public Reclamation numSalarie(Long numSalarie) {
        this.setNumSalarie(numSalarie);
        return this;
    }

    public void setNumSalarie(Long numSalarie) {
        this.numSalarie = numSalarie;
    }

    public Long getSecondNumSalarie() {
        return this.secondNumSalarie;
    }

    public Reclamation secondNumSalarie(Long secondNumSalarie) {
        this.setSecondNumSalarie(secondNumSalarie);
        return this;
    }

    public void setSecondNumSalarie(Long secondNumSalarie) {
        this.secondNumSalarie = secondNumSalarie;
    }

    public String getLibAutresRec() {
        return this.libAutresRec;
    }

    public Reclamation libAutresRec(String libAutresRec) {
        this.setLibAutresRec(libAutresRec);
        return this;
    }

    public void setLibAutresRec(String libAutresRec) {
        this.libAutresRec = libAutresRec;
    }

    public String getDescriptionAutres() {
        return this.descriptionAutres;
    }

    public Reclamation descriptionAutres(String descriptionAutres) {
        this.setDescriptionAutres(descriptionAutres);
        return this;
    }

    public void setDescriptionAutres(String descriptionAutres) {
        this.descriptionAutres = descriptionAutres;
    }

    public StatutReclamation getStatut() {
        return this.statut;
    }

    public Reclamation statut(StatutReclamation statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(StatutReclamation statut) {
        this.statut = statut;
    }

    public LocalDate getDateSoumission() {
        return this.dateSoumission;
    }

    public Reclamation dateSoumission(LocalDate dateSoumission) {
        this.setDateSoumission(dateSoumission);
        return this;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public LocalDate getDateValidateGC() {
        return this.dateValidateGC;
    }

    public Reclamation dateValidateGC(LocalDate dateValidateGC) {
        this.setDateValidateGC(dateValidateGC);
        return this;
    }

    public void setDateValidateGC(LocalDate dateValidateGC) {
        this.dateValidateGC = dateValidateGC;
    }

    public Long getUserIdValidateGC() {
        return this.userIdValidateGC;
    }

    public Reclamation userIdValidateGC(Long userIdValidateGC) {
        this.setUserIdValidateGC(userIdValidateGC);
        return this;
    }

    public void setUserIdValidateGC(Long userIdValidateGC) {
        this.userIdValidateGC = userIdValidateGC;
    }

    public LocalDate getDateValidateCFCSS() {
        return this.dateValidateCFCSS;
    }

    public Reclamation dateValidateCFCSS(LocalDate dateValidateCFCSS) {
        this.setDateValidateCFCSS(dateValidateCFCSS);
        return this;
    }

    public void setDateValidateCFCSS(LocalDate dateValidateCFCSS) {
        this.dateValidateCFCSS = dateValidateCFCSS;
    }

    public Long getUserIdValidateCFCSS() {
        return this.userIdValidateCFCSS;
    }

    public Reclamation userIdValidateCFCSS(Long userIdValidateCFCSS) {
        this.setUserIdValidateCFCSS(userIdValidateCFCSS);
        return this;
    }

    public void setUserIdValidateCFCSS(Long userIdValidateCFCSS) {
        this.userIdValidateCFCSS = userIdValidateCFCSS;
    }

    public LocalDate getDateValidateCFIPRES() {
        return this.dateValidateCFIPRES;
    }

    public Reclamation dateValidateCFIPRES(LocalDate dateValidateCFIPRES) {
        this.setDateValidateCFIPRES(dateValidateCFIPRES);
        return this;
    }

    public void setDateValidateCFIPRES(LocalDate dateValidateCFIPRES) {
        this.dateValidateCFIPRES = dateValidateCFIPRES;
    }

    public Long getUserIdValidateCFIPRES() {
        return this.userIdValidateCFIPRES;
    }

    public Reclamation userIdValidateCFIPRES(Long userIdValidateCFIPRES) {
        this.setUserIdValidateCFIPRES(userIdValidateCFIPRES);
        return this;
    }

    public void setUserIdValidateCFIPRES(Long userIdValidateCFIPRES) {
        this.userIdValidateCFIPRES = userIdValidateCFIPRES;
    }

    public String getMotif() {
        return this.motif;
    }

    public Reclamation motif(String motif) {
        this.setMotif(motif);
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Long getAgenceIpresID() {
        return this.agenceIpresID;
    }

    public Reclamation agenceIpresID(Long agenceIpresID) {
        this.setAgenceIpresID(agenceIpresID);
        return this;
    }

    public void setAgenceIpresID(Long agenceIpresID) {
        this.agenceIpresID = agenceIpresID;
    }

    public Long getAgenceCssID() {
        return this.agenceCssID;
    }

    public Reclamation agenceCssID(Long agenceCssID) {
        this.setAgenceCssID(agenceCssID);
        return this;
    }

    public void setAgenceCssID(Long agenceCssID) {
        this.agenceCssID = agenceCssID;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reclamation)) {
            return false;
        }
        return getId() != null && getId().equals(((Reclamation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reclamation{" +
            "id=" + id +
            ", typeReclamation=" + typeReclamation +
            ", userId=" + userId +
            ", agenceId=" + agenceId +
            ", numSalarie=" + numSalarie +
            ", secondNumSalarie=" + secondNumSalarie +
            ", libAutresRec='" + libAutresRec + '\'' +
            ", descriptionAutres='" + descriptionAutres + '\'' +
            ", statut=" + statut +
            ", dateSoumission=" + dateSoumission +
            ", dateValidateGC=" + dateValidateGC +
            ", userIdValidateGC=" + userIdValidateGC +
            ", dateValidateCFCSS=" + dateValidateCFCSS +
            ", userIdValidateCFCSS=" + userIdValidateCFCSS +
            ", dateValidateCFIPRES=" + dateValidateCFIPRES +
            ", userIdValidateCFIPRES=" + userIdValidateCFIPRES +
            ", motif='" + motif + '\'' +
            ", agenceIpresID=" + agenceIpresID +
            ", agenceCssID=" + agenceCssID +
            ", carriereManquantes=" + carriereManquantes +
            '}';
    }
}
