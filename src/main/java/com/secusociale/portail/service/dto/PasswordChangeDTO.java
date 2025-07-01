package com.secusociale.portail.service.dto;

import com.secusociale.portail.validators.ShouldMatchValue;

import javax.validation.constraints.NotEmpty;

/**
 * A DTO representing a password change required data - current and new password.
 */

@ShouldMatchValue(field = "newPassword", fieldMatch = "confirmPassword", message = "Les deux mots de passe doivent correspondre!")
public class PasswordChangeDTO {
    @NotEmpty
    private String currentPassword;
    @NotEmpty
    private String newPassword;
    private String confirmPassword;

    public PasswordChangeDTO() {
        // Empty constructor needed for Jackson.
    }

    public PasswordChangeDTO(String currentPassword, String newPassword, String confirmPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
