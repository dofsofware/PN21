package com.secusociale.portail.domain;

import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Salarie.
 */
@Entity
@Table(name = "salarie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Salarie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_unique", unique = true)
    private String numeroUnique;

    @NotNull
    @Column(name = "numero_cni", nullable = false, unique = true)
    private String numeroCni;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "date_nais")
    private LocalDate dateNais;

    @Column(name = "lieu_nais")
    private String lieuNais;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "agent_id")
    private Long agentId;
    @Lob
    @Column(name = "extra_infos")
    private String extraInfos;

    private String attestationDeTravail;

    private String contratDeTravail;

    private String cniRecto;

    private String cniVerso;

    private String photo;

    private String dmt;

    private Boolean activite;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_update_at")
    private Instant lastUpdateAt;

    private Instant activatedAt;

    private String statut = "DESACTIVER";

    @Column(length = 1000)
    private String motif;

    @Column(name = "statut_grappe")
    private String statusGrappeMembre = StatutGrappeMembre.SAISIE.toString();

    @Column(length = 1000,name = "motif_rejet_retourne_grappe")
    private String motifRejetRetourneGrappe;

    @Column(name = "is_submitted")
    private Boolean isSubmitted = false;

    @Column(name = "agence_css_id")
    private Long agenceCssId;

    @Column(name = "agence_ipres_id")
    private Long agenceIpresId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public Salarie numeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
        return this;
    }

    public String getDmt() {
        return dmt;
    }

    public void setDmt(String dmt) {
        this.dmt = dmt;
    }

    public String getContratDeTravail() {
        return contratDeTravail;
    }

    public void setContratDeTravail(String contratDeTravail) {
        this.contratDeTravail = contratDeTravail;
    }

    public String getAttestationDeTravail() {
        return attestationDeTravail;
    }

    public void setAttestationDeTravail(String attestationDeTravail) {
        this.attestationDeTravail = attestationDeTravail;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getNumeroCni() {
        return numeroCni;
    }

    public Salarie numeroCni(String numeroCni) {
        this.numeroCni = numeroCni;
        return this;
    }

    public void setNumeroCni(String numeroCni) {
        this.numeroCni = numeroCni;
    }

    public String getPrenom() {
        return prenom;
    }

    public Salarie prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public Salarie nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatusGrappeMembre() {
        return statusGrappeMembre;
    }

    public void setStatusGrappeMembre(String statusGrappeMembre) {
        this.statusGrappeMembre = statusGrappeMembre;
    }

    public Boolean getActive() {
        return active;
    }

    public String getMotifRejetRetourneGrappe() {
        return motifRejetRetourneGrappe;
    }

    public void setMotifRejetRetourneGrappe(String motifRejetRetourneGrappe) {
        this.motifRejetRetourneGrappe = motifRejetRetourneGrappe;
    }

    public String getTelephone() {
        return telephone;
    }

    public Salarie telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public Salarie email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public Salarie sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNais() {
        return dateNais;
    }

    public Salarie dateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
        return this;
    }

    public void setDateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
    }

    public String getLieuNais() {
        return lieuNais;
    }

    public Salarie lieuNais(String lieuNais) {
        this.lieuNais = lieuNais;
        return this;
    }

    public void setLieuNais(String lieuNais) {
        this.lieuNais = lieuNais;
    }

    public Boolean isActive() {
        return active;
    }

    public Salarie active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public Salarie userId(Long userId) {
        this.userId = userId;
        return this;
    }


    public Boolean getSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(Boolean submitted) {
        isSubmitted = submitted;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public Salarie agentId(Long agentId) {
        this.agentId = agentId;
        return this;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public Salarie extraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
        return this;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Salarie createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdateAt() {
        return lastUpdateAt;
    }

    public Boolean getActivite() {
        return activite;
    }

    public void setActivite(Boolean activite) {
        this.activite = activite;
    }

    public Salarie lastUpdateAt(Instant lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
        return this;
    }

    public String getCniRecto() {
        return cniRecto;
    }

    public void setCniRecto(String cniRecto) {
        this.cniRecto = cniRecto;
    }

    public String getCniVerso() {
        return cniVerso;
    }

    public void setCniVerso(String cniVerso) {
        this.cniVerso = cniVerso;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setLastUpdateAt(Instant lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Long getAgenceCssId() {
        return agenceCssId;
    }

    public void setAgenceCssId(Long agenceCssId) {
        this.agenceCssId = agenceCssId;
    }

    public Long getAgenceIpresId() {
        return agenceIpresId;
    }

    public void setAgenceIpresId(Long agencIpresId) {
        this.agenceIpresId = agencIpresId;
    }

    public Instant getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Instant activatedAt) {
        this.activatedAt = activatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salarie)) {
            return false;
        }
        return id != null && id.equals(((Salarie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Salarie{" +
            "id=" + getId() +
            ", numeroUnique='" + getNumeroUnique() + "'" +
            ", numeroCni='" + getNumeroCni() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", dateNais='" + getDateNais() + "'" +
            ", lieuNais='" + getLieuNais() + "'" +
            ", active='" + isActive() + "'" +
            ", userId=" + getUserId() +
            ", agentId=" + getAgentId() +
            ", extraInfos='" + getExtraInfos() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", lastUpdateAt='" + getLastUpdateAt() + "'" +
            "}";
    }
}
