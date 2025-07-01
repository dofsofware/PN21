package com.secusociale.portail.service.dto;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Authority;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.service.dto.consultation.EmployeurConsultationDTO;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    private SalarieDTO salarieDTO;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private String phone;

    @Size(max = 256)
    private String imageUrl;

    @Size(max = 256)
    private String typeCompte;

    @Size(max = 256)
    private String institution;

    @Size(max = 256)
    private String agence;

    private boolean activated = false;

    private boolean locked = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    private Integer nbLocalImmat;
    private Integer nbOldImmat;
    private Integer nbImmats;
    private String otp;
    private String cachet;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.activated = user.getActivated();
        this.locked = user.getLocked();
        this.imageUrl = user.getImageUrl();
        this.typeCompte = user.getTypeCompte();
        this.agence = user.getAgence();
        this.institution = user.getInstitution();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.cachet = user.getCachet();
        this.authorities = user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(Collectors.toSet());
    }

    public UserDTO(SalarieDTO salarieDTO) {
        this.id = salarieDTO.getId();
        this.firstName = !StringUtils.isEmpty(salarieDTO.getFirstName()) ? salarieDTO.getFirstName() : salarieDTO.getPrenom();
        this.lastName = !StringUtils.isEmpty(salarieDTO.getLastName()) ? salarieDTO.getLastName() : salarieDTO.getNom();
        this.phone = !StringUtils.isEmpty(salarieDTO.getPhone()) ? salarieDTO.getPhone() : salarieDTO.getTelephone();
        this.login = salarieDTO.getLogin();
        this.email = salarieDTO.getEmail();
        this.activated = salarieDTO.isActive();
        this.typeCompte = Constants.SALARIE;
        this.langKey = Constants.DEFAULT_LANGUAGE;
        this.authorities = salarieDTO.getRoles();
    }

    public UserDTO(EmployeurConsultationDTO employeurConsultationDTO) {
        this.id = employeurConsultationDTO.getId();
        this.firstName = !StringUtils.isEmpty(employeurConsultationDTO.getFirstName()) ? employeurConsultationDTO.getFirstName() : employeurConsultationDTO.getPrenom();
        this.lastName = !StringUtils.isEmpty(employeurConsultationDTO.getLastName()) ? employeurConsultationDTO.getLastName() : employeurConsultationDTO.getNom();
        this.phone = !StringUtils.isEmpty(employeurConsultationDTO.getPhone()) ? employeurConsultationDTO.getPhone() : employeurConsultationDTO.getTelephone();
        this.login = employeurConsultationDTO.getLogin();
        this.email = employeurConsultationDTO.getEmail();
        this.activated = employeurConsultationDTO.getActive();
        this.typeCompte = Constants.CONSULTATION;
        this.langKey = Constants.DEFAULT_LANGUAGE;
        this.authorities = employeurConsultationDTO.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getAgence() {
        return agence;
    }

    public SalarieDTO getSalarieDTO() {
        return salarieDTO;
    }

    public void setSalarieDTO(SalarieDTO salarieDTO) {
        this.salarieDTO = salarieDTO;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public boolean isActivated() {
        return activated;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Integer getNbLocalImmat() {
        return nbLocalImmat;
    }

    public void setNbLocalImmat(Integer nbLocalImmat) {
        this.nbLocalImmat = nbLocalImmat;
    }

    public Integer getNbOldImmat() {
        return nbOldImmat;
    }

    public void setNbOldImmat(Integer nbOldImmat) {
        this.nbOldImmat = nbOldImmat;
    }

    public Integer getNbImmats() {
        return nbImmats;
    }

    public void setNbImmats(Integer nbImmats) {
        this.nbImmats = nbImmats;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getCachet() {
        return cachet;
    }

    public void setCachet(String cachet) {
        this.cachet = cachet;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "id='" + id + '\'' +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", typeCompte='" + typeCompte + '\'' +
            ", institution='" + institution + '\'' +
            ", agence='" + agence + '\'' +
            ", activated=" + activated +
            ", locked=" + locked +
            ", langKey='" + langKey + '\'' +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", authorities=" + authorities +
            ", cachet=" + cachet +
            "}";
    }
}
