package com.secusociale.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * A TraceInfos.
 */
@Entity
@Table(name = "declaration_journal_job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeclarationJournalJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "declaration_id")
    private Declaration declaration;

    @ManyToOne
    @JoinColumn(name = "journal_job_id")
    private JournalJob journalJob;

    @Lob
    @Column(name = "rapport")
    private String rapport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Declaration getDeclaration() { return declaration; }
    public void setDeclaration(Declaration declaration) { this.declaration = declaration; }

    public JournalJob getJournalJob() { return journalJob; }
    public void setJournalJob(JournalJob journalJob) { this.journalJob = journalJob; }

    public String getRapport() {
        return rapport;
    }
    public void setRapport(String rapport) {
        this.rapport = rapport;
    }
}
