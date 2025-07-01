package com.secusociale.portail.domain.demandeDeCarte;

import com.secusociale.portail.domain.Salarie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "demande_de_carte")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDeCarteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroDemande;
    private String statut;
    private LocalDate dateDemande;
    private Long idAgent;
    private Long salarieId;
    private LocalDate dateDeTraitement;
    private Long agence;
    private String photo;
    private String nin;
    private String lot;
}
