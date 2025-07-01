package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.secusociale.portail.domain.enumeration.StatutTP;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contenu_tp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContenuTP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numero_unique", nullable = false)
    private Long numeroUnique;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "trimestre", nullable = true)
    private Integer trimestre;

    @Column(name = "employeur", nullable = true)
    private String employeur;

    @Column(name = "ancien_numero_css", nullable = true)
    private Long ancienNumeroCss;

    @Column(name = "ancien_numero_ipres", nullable = true)
    private Long ancienNumeroIpres;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private StatutTP statut;

    @Lob
    @Column(name = "motif")
    private String motif;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contenuTP", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"contenuTP"}, allowSetters = true)
    private Set<SalarieTP> salarieTPs = new HashSet<>();

    @JsonIgnoreProperties(value = {"contenuTP", "tempsDePresence"}, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contenuTP")
    private FileTP fileTP;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContenuTP id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroUnique() {
        return this.numeroUnique;
    }

    public ContenuTP numeroUnique(Long numeroUnique) {
        this.setNumeroUnique(numeroUnique);
        return this;
    }

    public void setNumeroUnique(Long numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Integer getAnnee() {
        return this.annee;
    }

    public ContenuTP annee(Integer annee) {
        this.setAnnee(annee);
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getTrimestre() {
        return this.trimestre;
    }

    public ContenuTP trimestre(Integer trimestre) {
        this.setTrimestre(trimestre);
        return this;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public Long getAncienNumeroCss() {
        return this.ancienNumeroCss;
    }

    public ContenuTP ancienNumeroCss(Long ancienNumeroCss) {
        this.setAncienNumeroCss(ancienNumeroCss);
        return this;
    }

    public void setAncienNumeroCss(Long ancienNumeroCss) {
        this.ancienNumeroCss = ancienNumeroCss;
    }

    public Long getAncienNumeroIpres() {
        return this.ancienNumeroIpres;
    }

    public ContenuTP ancienNumeroIpres(Long ancienNumeroIpres) {
        this.setAncienNumeroIpres(ancienNumeroIpres);
        return this;
    }

    public void setAncienNumeroIpres(Long ancienNumeroIpres) {
        this.ancienNumeroIpres = ancienNumeroIpres;
    }

    public Set<SalarieTP> getSalarieTPs() {
        return this.salarieTPs;
    }

    public void setSalarieTPs(Set<SalarieTP> salarieTPS) {
        if (this.salarieTPs != null) {
            this.salarieTPs.forEach(i -> i.setContenuTP(null));
        }
        if (salarieTPS != null) {
            salarieTPS.forEach(i -> i.setContenuTP(this));
        }
        this.salarieTPs = salarieTPS;
    }

    public ContenuTP salarieTPs(Set<SalarieTP> salarieTPS) {
        this.setSalarieTPs(salarieTPS);
        return this;
    }

    public ContenuTP addSalarieTPs(SalarieTP salarieTP) {
        this.salarieTPs.add(salarieTP);
        salarieTP.setContenuTP(this);
        return this;
    }

    public ContenuTP removeSalarieTPs(SalarieTP salarieTP) {
        this.salarieTPs.remove(salarieTP);
        salarieTP.setContenuTP(null);
        return this;
    }

    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public @NotNull StatutTP getStatut() {
        return statut;
    }

    public void setStatut(@NotNull StatutTP statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @JsonIgnore
    public FileTP getFileTP() {
        return this.fileTP;
    }

    public void setFileTP(FileTP fileTP) {
        if (this.fileTP != null) {
            this.fileTP.setContenuTP(null);
        }
        if (fileTP != null) {
            fileTP.setContenuTP(this);
        }
        this.fileTP = fileTP;
    }

    public ContenuTP fileTP(FileTP fileTP) {
        this.setFileTP(fileTP);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContenuTP)) {
            return false;
        }
        return getId() != null && getId().equals(((ContenuTP) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContenuTP{" +
            "id=" + getId() +
            ", numeroUnique=" + getNumeroUnique() +
            ", annee=" + getAnnee() +
            ", trimestre=" + getTrimestre() +
            ", ancienNumeroCss=" + getAncienNumeroCss() +
            ", ancienNumeroIpres=" + getAncienNumeroIpres() +
            "}";
    }
}
