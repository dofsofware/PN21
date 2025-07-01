package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A GrappeMember.
 */
@Entity
@Table(name = "grappe_member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GrappeMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "date_nais")
    private LocalDate dateNais;

    @Column(name = "date_mariage")
    private LocalDate dateMariage;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "origine")
    private String origine;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "numero_cni")
    private String numeroCni;

    @Lob
    @Column(name = "pj_1")
    private String pj1;

    @Lob
    @Column(name = "pj_2")
    private String pj2;

    @Lob
    @Column(name = "pj_3")
    private String pj3;

    @Lob
    @Column(name = "pj_4")
    private String pj4;

    @Lob
    @Column(name = "pj_5")
    private String pj5;

    @Lob
    @Column(name = "pj_6")
    private String pj6;

    @Lob
    @Column(name = "pj_7")
    private String pj7;

    @Lob
    @Column(name = "pj_8")
    private String pj8;

    @Lob
    @Column(name = "pj_9")
    private String pj9;

    @Lob
    @Column(name = "pj_10")
    private String pj10;

    @Lob
    @Column(name = "extra_infos")
    private String extraInfos;

    @ManyToOne
    @JsonIgnoreProperties("grappeMembers")
    private Salarie salarie;

    @Lob
    @Column(name = "motif_rejet_retourne")
    private String motif;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutGrappeMembre statut = StatutGrappeMembre.SAISIE;

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

    public GrappeMember type(String type) {
        this.type = type;
        return this;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public StatutGrappeMembre getStatut() {
        return statut;
    }

    public void setStatut(StatutGrappeMembre statut) {
        this.statut = statut;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateNais() {
        return dateNais;
    }

    public GrappeMember dateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
        return this;
    }

    public void setDateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
    }

    public LocalDate getDateMariage() {
        return dateMariage;
    }

    public GrappeMember dateMariage(LocalDate dateMariage) {
        this.dateMariage = dateMariage;
        return this;
    }

    public void setDateMariage(LocalDate dateMariage) {
        this.dateMariage = dateMariage;
    }

    public String getSexe() {
        return sexe;
    }

    public GrappeMember sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getOrigine() {
        return origine;
    }

    public GrappeMember origine(String origine) {
        this.origine = origine;
        return this;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getFirstName() {
        return firstName;
    }

    public GrappeMember firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GrappeMember lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumeroCni() {
        return numeroCni;
    }

    public GrappeMember numeroCni(String numeroCni) {
        this.numeroCni = numeroCni;
        return this;
    }

    public void setNumeroCni(String numeroCni) {
        this.numeroCni = numeroCni;
    }

    public String getPj1() {
        return pj1;
    }

    public GrappeMember pj1(String pj1) {
        this.pj1 = pj1;
        return this;
    }

    public void setPj1(String pj1) {
        this.pj1 = pj1;
    }

    public String getPj2() {
        return pj2;
    }

    public GrappeMember pj2(String pj2) {
        this.pj2 = pj2;
        return this;
    }

    public void setPj2(String pj2) {
        this.pj2 = pj2;
    }

    public String getPj3() {
        return pj3;
    }

    public GrappeMember pj3(String pj3) {
        this.pj3 = pj3;
        return this;
    }

    public void setPj3(String pj3) {
        this.pj3 = pj3;
    }

    public String getPj4() {
        return pj4;
    }

    public GrappeMember pj4(String pj4) {
        this.pj4 = pj4;
        return this;
    }

    public void setPj4(String pj4) {
        this.pj4 = pj4;
    }

    public String getPj5() {
        return pj5;
    }

    public GrappeMember pj5(String pj5) {
        this.pj5 = pj5;
        return this;
    }

    public void setPj5(String pj5) {
        this.pj5 = pj5;
    }

    public String getPj6() {
        return pj6;
    }

    public GrappeMember pj6(String pj6) {
        this.pj6 = pj6;
        return this;
    }

    public void setPj6(String pj6) {
        this.pj6 = pj6;
    }

    public String getPj7() {
        return pj7;
    }

    public GrappeMember pj7(String pj7) {
        this.pj7 = pj7;
        return this;
    }

    public void setPj7(String pj7) {
        this.pj7 = pj7;
    }

    public String getPj8() {
        return pj8;
    }

    public GrappeMember pj8(String pj8) {
        this.pj8 = pj8;
        return this;
    }

    public void setPj8(String pj8) {
        this.pj8 = pj8;
    }

    public String getPj9() {
        return pj9;
    }

    public GrappeMember pj9(String pj9) {
        this.pj9 = pj9;
        return this;
    }

    public void setPj9(String pj9) {
        this.pj9 = pj9;
    }

    public String getPj10() {
        return pj10;
    }

    public GrappeMember pj10(String pj10) {
        this.pj10 = pj10;
        return this;
    }

    public void setPj10(String pj10) {
        this.pj10 = pj10;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public GrappeMember extraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
        return this;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Salarie getSalarie() {
        return salarie;
    }

    public GrappeMember salarie(Salarie salarie) {
        this.salarie = salarie;
        return this;
    }

    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrappeMember)) {
            return false;
        }
        return id != null && id.equals(((GrappeMember) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GrappeMember{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", dateNais='" + getDateNais() + "'" +
            ", dateMariage='" + getDateMariage() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", origine='" + getOrigine() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", numeroCni='" + getNumeroCni() + "'" +
            ", pj1='" + getPj1() + "'" +
            ", pj2='" + getPj2() + "'" +
            ", pj3='" + getPj3() + "'" +
            ", pj4='" + getPj4() + "'" +
            ", pj5='" + getPj5() + "'" +
            ", pj6='" + getPj6() + "'" +
            ", pj7='" + getPj7() + "'" +
            ", pj8='" + getPj8() + "'" +
            ", pj9='" + getPj9() + "'" +
            ", pj10='" + getPj10() + "'" +
            ", extraInfos='" + getExtraInfos() + "'" +
            "}";
    }
}
