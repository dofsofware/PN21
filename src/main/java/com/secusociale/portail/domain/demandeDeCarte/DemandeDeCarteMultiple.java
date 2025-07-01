package com.secusociale.portail.domain.demandeDeCarte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class DemandeDeCarteMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idEmployeur;
    private Long idAgence;
    private String statut;
    private LocalDate dateDemande;
}
