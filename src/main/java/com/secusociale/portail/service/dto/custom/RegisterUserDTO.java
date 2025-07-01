package com.secusociale.portail.service.dto.custom;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.service.dto.UserDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserDTO extends UserDTO {
    @NotBlank
    private String activationFrontUrl;

    private String profil;

    public RegisterUserDTO() {

    }

    public RegisterUserDTO(User user, String activationFrontUrl) {
        super(user);
        this.activationFrontUrl = activationFrontUrl;
    }

    public String getActivationFrontUrl() {
        return activationFrontUrl;
    }

    public void setActivationFrontUrl(String activationFrontUrl) {
        this.activationFrontUrl = activationFrontUrl;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
