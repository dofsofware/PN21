package com.secusociale.portail.service.dto.extraction;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.demandeDeCarte.DemandeDeCarteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DemandeDeCarteDTO {

    private Long id;
    private String numeroDemande;
    private LocalDate dateDemande;
    private String statut;
    private Long idAgent;
    private Long salarieId;
    private String nomAgent;
    private Salarie salarie;
    private LocalDate dateDeTraitement;
    private Long agence;
    private Agence agency;
    private String photo;
    private String nin;
    private String lot;
}
