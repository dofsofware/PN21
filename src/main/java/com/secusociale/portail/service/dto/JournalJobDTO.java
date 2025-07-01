package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.DeclarationJournalJob;
import com.secusociale.portail.domain.enumeration.ModeExecution;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.SuiviJob} entity.
 */
public class JournalJobDTO implements Serializable {

    private Long id;

    private String nom;

    private String statut;

    private Instant demarreLe;

    private Instant termineLe;

    private String demarrePar;

    @Lob
    private String erreurs;

    @Lob
    private String valides;

    @Lob
    private String totals;

    private ModeExecution modeExecution;


    private List<DeclarationJournalJob> declarations;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getDemarreLe() {
        return demarreLe;
    }

    public void setDemarreLe(Instant demarreLe) {
        this.demarreLe = demarreLe;
    }

    public Instant getTermineLe() {
        return termineLe;
    }

    public void setTermineLe(Instant termineLe) {
        this.termineLe = termineLe;
    }

    public String getDemarrePar() {
        return demarrePar;
    }

    public void setDemarrePar(String demarrePar) {
        this.demarrePar = demarrePar;
    }

    public String getErreurs() {
        return erreurs;
    }

    public void setErreurs(String erreurs) {
        this.erreurs = erreurs;
    }
    public String getValides() {
        return valides;
    }

    public void setValides(String valides) {
        this.valides = valides;
    }

    public String getTotals() {
        return totals;
    }

    public void setTotals(String totals) {
        this.totals = totals;
    }

    public ModeExecution getModeExecution() {
        return modeExecution;
    }

    public void setModeExecution(ModeExecution modeExecution) {
        this.modeExecution = modeExecution;
    }

    public List<DeclarationJournalJob> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<DeclarationJournalJob> declarations) {
        this.declarations = declarations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JournalJobDTO suiviJobDTO = (JournalJobDTO) o;
        if (suiviJobDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suiviJobDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuiviJobDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", statut='" + getStatut() + "'" +
            ", demarreLe='" + getDemarreLe() + "'" +
            ", termineLe='" + getTermineLe() + "'" +
            ", demarrePar='" + getDemarrePar() + "'" +
            ", erreurs='" + getErreurs() + "'" +
            ", valides='" + getValides() + "'" +
            ", totals='" + getTotals() + "'" +
            "}";
    }
}
