package com.secusociale.portail.service.dto;

import lombok.Data;

@Data
public class ValidateRequest {
    private String typeOperation;
    private String motif;
    private ReclamationDTO reclamation;
}
