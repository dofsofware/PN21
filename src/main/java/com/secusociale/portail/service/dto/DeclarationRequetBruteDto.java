package com.secusociale.portail.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeclarationRequetBruteDto {
    private String numeroEmployeur;
    private String raisonSociale;
    private List<InformationSalarieDto> informationSalaries;

}
