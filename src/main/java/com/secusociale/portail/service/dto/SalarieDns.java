package com.secusociale.portail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalarieDns {
    private String nomFeuille;
    private int numeroLigne;
    private String nom;
    private String prenom;
    private String salaire;
    private String typeContrat;
    private String typeDocument;
    private String numeroDocument;
}
