package com.secusociale.portail.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.secusociale.portail.domain.enumeration.StatutTP;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "salarie_tp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalarieTP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_assure_social", nullable = true)
    private Long numeroAssureSocial;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "type_piece", nullable = false)
    private String typePiece;

    @NotNull
    @Column(name = "numero_piece", nullable = false)
    private String numeroPiece;

    @Max(value = 65)
    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "nombre_enfant_eligibre", nullable = true)
    private Integer nombreEnfantEligibre;

    @Column(name = "temps_de_presence_heure_mois_1", nullable = true)
    private Integer tempsDePresenceHeureMois1;

    @Column(name = "temps_de_presence_heure_mois_2", nullable = true)
    private Integer tempsDePresenceHeureMois2;

    @Column(name = "temps_de_presence_heure_mois_3", nullable = true)
    private Integer tempsDePresenceHeureMois3;

    @Lob
    @Column(name = "observation")
    private String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "salarieTPs", "fileTP" }, allowSetters = true)
    private ContenuTP contenuTP;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SalarieTP id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroAssureSocial() {
        return this.numeroAssureSocial;
    }

    public SalarieTP numeroAssureSocial(Long numeroAssureSocial) {
        this.setNumeroAssureSocial(numeroAssureSocial);
        return this;
    }

    public void setNumeroAssureSocial(Long numeroAssureSocial) {
        this.numeroAssureSocial = numeroAssureSocial;
    }

    public String getNom() {
        return this.nom;
    }

    public SalarieTP nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public SalarieTP prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public @NotNull String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(@NotNull String typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumeroPiece() {
        return this.numeroPiece;
    }

    public SalarieTP numeroPiece(String numeroPiece) {
        this.setNumeroPiece(numeroPiece);
        return this;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public Integer getAge() {
        return this.age;
    }

    public SalarieTP age(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNombreEnfantEligibre() {
        return this.nombreEnfantEligibre;
    }

    public SalarieTP nombreEnfantEligibre(Integer nombreEnfantEligibre) {
        this.setNombreEnfantEligibre(nombreEnfantEligibre);
        return this;
    }

    public void setNombreEnfantEligibre(Integer nombreEnfantEligibre) {
        this.nombreEnfantEligibre = nombreEnfantEligibre;
    }

    public Integer getTempsDePresenceHeureMois1() {
        return this.tempsDePresenceHeureMois1;
    }

    public SalarieTP tempsDePresenceHeureMois1(Integer tempsDePresenceHeureMois1) {
        this.setTempsDePresenceHeureMois1(tempsDePresenceHeureMois1);
        return this;
    }

    public void setTempsDePresenceHeureMois1(Integer tempsDePresenceHeureMois1) {
        tempsDePresenceHeureMois1 = (tempsDePresenceHeureMois1 != null) ? tempsDePresenceHeureMois1 : 0;
        this.tempsDePresenceHeureMois1 = tempsDePresenceHeureMois1;
    }

    public Integer getTempsDePresenceHeureMois2() {
        return this.tempsDePresenceHeureMois2;
    }

    public SalarieTP tempsDePresenceHeureMois2(Integer tempsDePresenceHeureMois2) {
        this.setTempsDePresenceHeureMois2(tempsDePresenceHeureMois2);
        return this;
    }

    public void setTempsDePresenceHeureMois2(Integer tempsDePresenceHeureMois2) {
        tempsDePresenceHeureMois2 = (tempsDePresenceHeureMois2 != null) ? tempsDePresenceHeureMois2 : 0;
        this.tempsDePresenceHeureMois2 = tempsDePresenceHeureMois2;
    }

    public Integer getTempsDePresenceHeureMois3() {
        return this.tempsDePresenceHeureMois3;
    }

    public SalarieTP tempsDePresenceHeureMois3(Integer tempsDePresenceHeureMois3) {
        this.setTempsDePresenceHeureMois3(tempsDePresenceHeureMois3);
        return this;
    }

    public void setTempsDePresenceHeureMois3(Integer tempsDePresenceHeureMois3) {
        tempsDePresenceHeureMois3 = (tempsDePresenceHeureMois3 != null) ? tempsDePresenceHeureMois3 : 0;
        this.tempsDePresenceHeureMois3 = tempsDePresenceHeureMois3;
    }

    public String getObservation() {
        return this.observation;
    }

    public SalarieTP observation(String observation) {
        this.setObservation(observation);
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ContenuTP getContenuTP() {
        return this.contenuTP;
    }

    public void setContenuTP(ContenuTP contenuTP) {
        this.contenuTP = contenuTP;
    }

    public SalarieTP contenuTP(ContenuTP contenuTP) {
        this.setContenuTP(contenuTP);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalarieTP)) {
            return false;
        }
        return getId() != null && getId().equals(((SalarieTP) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalarieTP{" +
            "id=" + getId() +
            ", numeroAssureSocial=" + getNumeroAssureSocial() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", numeroPiece=" + getNumeroPiece() +
            ", age=" + getAge() +
            ", nombreEnfantEligibre=" + getNombreEnfantEligibre() +
            ", tempsDePresenceHeureMois1=" + getTempsDePresenceHeureMois1() +
            ", tempsDePresenceHeureMois2=" + getTempsDePresenceHeureMois2() +
            ", tempsDePresenceHeureMois3=" + getTempsDePresenceHeureMois3() +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
