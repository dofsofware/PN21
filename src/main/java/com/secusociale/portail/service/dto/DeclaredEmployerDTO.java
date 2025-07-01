package com.secusociale.portail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclaredEmployerDTO  {

    private String numeroUnique;
    private String raisonSociale;
    private String ancienNumIpres;
    private String ancienNumCss;
    private String adresse;

}
