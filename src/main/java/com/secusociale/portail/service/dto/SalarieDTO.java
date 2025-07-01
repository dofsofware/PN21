package com.secusociale.portail.service.dto;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import com.secusociale.portail.service.dto.custom.SimplifiedAgentDto;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.secusociale.portail.domain.Salarie} entity.
 */
public class SalarieDTO implements Serializable {

    private Long id;

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    private String activationFrontUrl;

    private UserDTO user;
    private AgentDTO agent;
    private SimplifiedAgentDto simplifiedAgent;
    private Instant activatedAt;

    private String numeroUnique;

    @NotNull
    private String numeroCni;

    private String prenom;
    private String firstName;

    private String nom;
    private String lastName;

    private String telephone;
    private String phone;

    private String email;

    private String sexe;

    private LocalDate dateNais;

    private String lieuNais;

    private Boolean active = false;

    private Long userId;

    private Long agentId;

    private String attestationDeTravail;

    private String contratDeTravail;

    private String dmt;

    private String cniRecto;

    private String cniVerso;

    private String photo;

    private Boolean activite;

    @Lob
    private String extraInfos;

    private Instant createdAt;

    private Instant lastUpdateAt;

    private String statut;

    private String motif;

    private Boolean isSubmitted = false;

    private String statusGrappeMembre;

    private String motifRejetRetourneGrappe;

    private Long agenceCssId;

    private Long agencIpresId;

    public String getContratDeTravail() {
        return contratDeTravail;
    }

    public void setContratDeTravail(String contratDeTravail) {
        this.contratDeTravail = contratDeTravail;
    }

    public String getDmt() {
        return dmt;
    }

    public void setDmt(String dmt) {
        this.dmt = dmt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getNumeroCni() {
        return numeroCni;
    }

    public void setNumeroCni(String numeroCni) {
        this.numeroCni = numeroCni;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNais() {
        return dateNais;
    }

    public void setDateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
    }

    public String getLieuNais() {
        return lieuNais;
    }

    public void setLieuNais(String lieuNais) {
        this.lieuNais = lieuNais;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(String extraInfos) {
        this.extraInfos = extraInfos;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(Instant lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public Set<String> getRoles() {
        Set<String> roles = new HashSet<>();
        roles.add(AuthoritiesConstants.SALARIE);
        return roles;
    }

    public String getAttestationDeTravail() {
        return attestationDeTravail;
    }

    public void setAttestationDeTravail(String attestationDeTravail) {
        this.attestationDeTravail = attestationDeTravail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getActivationFrontUrl() {
        return activationFrontUrl;
    }

    public void setActivationFrontUrl(String activationFrontUrl) {
        this.activationFrontUrl = activationFrontUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public String getFirstName() {
        return StringUtils.isEmpty(firstName) ? prenom : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return StringUtils.isEmpty(lastName) ? prenom : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return StringUtils.isEmpty(phone) ? telephone : phone;
    }

    public Boolean getActivite() {
        return activite;
    }

    public void setActivite(Boolean activite) {
        this.activite = activite;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getMotifRejetRetourneGrappe() {
        return motifRejetRetourneGrappe;
    }

    public void setMotifRejetRetourneGrappe(String motifRejetRetourneGrappe) {
        this.motifRejetRetourneGrappe = motifRejetRetourneGrappe;
    }

    public Long getAgenceCssId() {
        return agenceCssId;
    }

    public void setAgenceCssId(Long agenceCssId) {
        this.agenceCssId = agenceCssId;
    }

    public Long getAgenceIpresId() {
        return agencIpresId;
    }

    public void setAgenceIpresId(Long agencIpresId) {
        this.agencIpresId = agencIpresId;
    }

    public String getStatusGrappeMembre() {
        return statusGrappeMembre;
    }

    public void setStatusGrappeMembre(String statusGrappeMembre) {
        this.statusGrappeMembre = statusGrappeMembre;
    }

    public Boolean getSubmitted() { return isSubmitted; }

    public void setSubmitted(Boolean submitted) { isSubmitted = submitted; }

    public SimplifiedAgentDto getSimplifiedAgent() {
        return simplifiedAgent;
    }

    public void setSimplifiedAgent(SimplifiedAgentDto simplifiedAgent) {
        this.simplifiedAgent = simplifiedAgent;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SalarieDTO salarieDTO = (SalarieDTO) o;
        if (salarieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salarieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalarieDTO{" +
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
