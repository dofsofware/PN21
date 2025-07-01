package com.secusociale.portail.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.secusociale.portail.domain.enumeration.StatutTP;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "file_tp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FileTP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date_soumission", nullable = false)
    private LocalDate dateSoumission;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutTP statut;

    @Lob
    @Column(name = "motif")
    private String motif;

    @Lob
    @Column(name = "fileEncoded")
    @JsonIgnore
    private String fileEncoded;

    @Column(name = "doc_link")
    private String docLink;

//    @JsonIgnoreProperties(value = { "salarieTPs", "fileTP" }, allowSetters = true)
//    @OneToOne(fetch = FetchType.LAZY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private ContenuTP contenuTP;

    @JsonIgnoreProperties(value = { "fileTP" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fileTP")
    private TempsDePresence tempsDePresence;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FileTP id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateSoumission() {
        return this.dateSoumission;
    }

    public FileTP dateSoumission(LocalDate dateSoumission) {
        this.setDateSoumission(dateSoumission);
        return this;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public StatutTP getStatut() {
        return this.statut;
    }

    public FileTP statut(StatutTP statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(StatutTP statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return this.motif;
    }

    public FileTP motif(String motif) {
        this.setMotif(motif);
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDocLink() {
        return this.docLink;
    }

    public FileTP docLink(String docLink) {
        this.setDocLink(docLink);
        return this;
    }


    public String getFileEncoded() {
        return fileEncoded;
    }

    public void setFileEncoded(String fileEncoded) {
        this.fileEncoded = fileEncoded;
    }

    public void setDocLink(String docLink) {
        this.docLink = docLink;
    }

    public ContenuTP getContenuTP() {
        return this.contenuTP;
    }

    public void setContenuTP(ContenuTP contenuTP) {
        this.contenuTP = contenuTP;
    }

    public FileTP contenuTP(ContenuTP contenuTP) {
        this.setContenuTP(contenuTP);
        return this;
    }

    @JsonIgnore
    public TempsDePresence getTempsDePresence() {
        return this.tempsDePresence;
    }

    public void setTempsDePresence(TempsDePresence tempsDePresence) {
        if (this.tempsDePresence != null) {
            this.tempsDePresence.setFileTP(null);
        }
        if (tempsDePresence != null) {
            tempsDePresence.setFileTP(this);
        }
        this.tempsDePresence = tempsDePresence;
    }

    public FileTP tempsDePresence(TempsDePresence tempsDePresence) {
        this.setTempsDePresence(tempsDePresence);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileTP)) {
            return false;
        }
        return getId() != null && getId().equals(((FileTP) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileTP{" +
            "id=" + getId() +
            ", dateSoumission='" + getDateSoumission() + "'" +
            ", statut='" + getStatut() + "'" +
            ", motif='" + getMotif() + "'" +
            ", docLink='" + getDocLink() + "'" +
            "}";
    }
}

