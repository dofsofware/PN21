package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.DemandeServiceSalarie;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.DemandeServiceSalarie} entity.
 */
public class DemandeServiceSalarieDTO implements Serializable {

    private SalarieDTO salarieDTO;
    private UserDTO gestionnaireDTO;

    private Long id;

    @NotNull
    private String type;

    private String statut;

    private Instant date;

    @Lob
    private String contenu;

    @Lob
    private String motifOuDescription;

    private Instant dateTraitement;

    private String numeroUniqueEmployeur;

    private String employeur;

    private Long gestionnaireId;

    private String acteDeTitularisation;

    private Long agenceId;

    private String certificat;

    @Lob
    private String oldEyes;

    private SalarieDTO salarie;

    private Long salarieId;

    private String motif;

    private String fonction;

    private String matriculeSolde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getMotifOuDescription() {
        return motifOuDescription;
    }

    public void setMotifOuDescription(String motifOuDescription) {
        this.motifOuDescription = motifOuDescription;
    }

    public Instant getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Instant dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public void setNumeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }
    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public Long getGestionnaireId() {
        return gestionnaireId;
    }

    public void setGestionnaireId(Long gestionnaireId) {
        this.gestionnaireId = gestionnaireId;
    }

    public String getOldEyes() {
        return oldEyes;
    }

    public void setOldEyes(String oldEyes) {
        this.oldEyes = oldEyes;
    }

    public Long getSalarieId() {
        return salarieId;
    }

    public void setSalarieId(Long salarieId) {
        this.salarieId = salarieId;
    }

    public void setGestionnaireDTO(UserDTO gestionnaireDTO) {
        this.gestionnaireDTO = gestionnaireDTO;
    }

    public UserDTO getGestionnaireDTO() {
        return gestionnaireDTO;
    }

    public void setSalarieDTO(SalarieDTO salarieDTO) {
        this.salarieDTO = salarieDTO;
    }

    public SalarieDTO getSalarieDTO() {
        return salarieDTO;
    }

    public SalarieDTO getSalarie() {
        return salarie;
    }

    public void setSalarie(SalarieDTO salarie) {
        this.salarie = salarie;
    }

    public String getActeDeTitularisation() {
        return acteDeTitularisation;
    }

    public void setActeDeTitularisation(String acteDeTitularisation) {
        this.acteDeTitularisation = acteDeTitularisation;
    }

    public Long getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }

    public String getCertificat() {
        return certificat;
    }

    public void setCertificat(String certificat) {
        this.certificat = certificat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getMatriculeSolde() {
        return matriculeSolde;
    }

    public void setMatriculeSolde(String matriculeSolde) {
        this.matriculeSolde = matriculeSolde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DemandeServiceSalarieDTO demandeServiceSalarieDTO = (DemandeServiceSalarieDTO) o;
        if (demandeServiceSalarieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeServiceSalarieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandeServiceSalarieDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", statut='" + getStatut() + "'" +
            ", date='" + getDate() + "'" +
            ", contenu='" + getContenu() + "'" +
            ", motifOuDescription='" + getMotifOuDescription() + "'" +
            ", dateTraitement='" + getDateTraitement() + "'" +
            ", employeur='" + getEmployeur() + "'" +
            ", gestionnaireId=" + getGestionnaireId() +
            ", oldEyes='" + getOldEyes() + "'" +
            ", salarieId=" + getSalarieId() +
            ", motif=" + getMotif() +
            "}";
    }
}
