package com.secusociale.portail.service.dto.custom;

import javax.validation.constraints.NotBlank;

public class ResendActivationEmailDTO  {
    @NotBlank
    private String activationFrontUrl;
    @NotBlank
    private String userEmail;

    public ResendActivationEmailDTO() {

    }

    public ResendActivationEmailDTO(String userEmail, String activationFrontUrl) {
        this.userEmail = userEmail;
        this.activationFrontUrl = activationFrontUrl;
    }

    public String getActivationFrontUrl() {
        return activationFrontUrl;
    }

    public void setActivationFrontUrl(String activationFrontUrl) {
        this.activationFrontUrl = activationFrontUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
