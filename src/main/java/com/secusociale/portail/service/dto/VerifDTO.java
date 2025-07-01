package com.secusociale.portail.service.dto;

import lombok.Data;

@Data
public class VerifDTO {
    String password;
    String confirmPassword;
    String otp;
    Long id;
}
