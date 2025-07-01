package com.secusociale.portail.domain;

import com.secusociale.portail.domain.enumeration.ModeExecution;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A SuiviJob.
 */
@Entity
@Table(name = "journal_job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JournalJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "statut")
    private String statut;

    @Column(name = "demarre_le")
    private Instant demarreLe;

    @Column(name = "termine_le")
    private Instant termineLe;

    @Column(name = "demarre_par")
    private String demarrePar;

    @Lob
    @Column(name = "erreurs")
    private String erreurs;

    @Lob
    @Column(name = "valides")
    private String valides;

    @Lob
    @Column(name = "totals")
    private String totals;

    @Lob
    @Column(name = "batchExecutionId")
    private String batchExecutionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_execution")
    private ModeExecution modeExecution;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public JournalJob nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getStatut() {
        return statut;
    }

    public JournalJob statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getDemarreLe() {
        return demarreLe;
    }

    public JournalJob demarreLe(Instant demarreLe) {
        this.demarreLe = demarreLe;
        return this;
    }

    public void setDemarreLe(Instant demarreLe) {
        this.demarreLe = demarreLe;
    }

    public Instant getTermineLe() {
        return termineLe;
    }

    public JournalJob termineLe(Instant termineLe) {
        this.termineLe = termineLe;
        return this;
    }

    public void setTermineLe(Instant termineLe) {
        this.termineLe = termineLe;
    }

    public String getDemarrePar() {
        return demarrePar;
    }

    public JournalJob demarrePar(String demarrePar) {
        this.demarrePar = demarrePar;
        return this;
    }

    public ModeExecution getModeExecution() {
        return modeExecution;
    }

    public void setModeExecution(ModeExecution modeExecution) {
        this.modeExecution = modeExecution;
    }

    public void setDemarrePar(String demarrePar) {
        this.demarrePar = demarrePar;
    }


    public String getErreurs() {
        return erreurs;
    }

    public JournalJob erreurs(String erreurs) {
        this.erreurs = erreurs;
        return this;
    }

    public void setErreurs(String erreurs) {
        this.erreurs = erreurs;
    }


    public String getValides() {
        return valides;
    }

    public JournalJob valides(String valides) {
        this.valides = valides;
        return this;
    }

    public void setValides(String valides) {
        this.valides = valides;
    }



    public String getTotals() {
        return totals;
    }

    public JournalJob totals(String totals) {
        this.totals = totals;
        return this;
    }

    public void setTotals(String totals) {
        this.totals = totals;
    }

    public String getBatchExecutionId() {
        return batchExecutionId;
    }

    public JournalJob batchExecutionId(String batchExecutionId) {
        this.batchExecutionId = batchExecutionId;
        return this;
    }

    public void setBatchExecutionId(String batchExecutionId) {
        this.batchExecutionId = batchExecutionId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JournalJob)) {
            return false;
        }
        return id != null && id.equals(((JournalJob) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "JournalJob{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", statut='" + getStatut() + "'" +
            ", demarreLe='" + getDemarreLe() + "'" +
            ", termineLe='" + getTermineLe() + "'" +
            ", demarrePar='" + getDemarrePar() + "'" +
            ", erreurs='" + getErreurs() + "'" +
            ", valides='" + getValides() + "'" +
            ", totals='" + getTotals() + "'" +
            ", batchExecutionId='" + getBatchExecutionId() + "'" +
            ", modeExecution='" + getModeExecution() + "'" +
            "}";
    }
}
