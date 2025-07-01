package com.secusociale.portail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "dmt_historique",
    indexes = {
        @Index(name = "idx_numero_unique_employeur", columnList = "numeroUniqueEmployeur"),
        @Index(name = "idx_type_piece", columnList = "typePiece"),
        @Index(name = "idx_numero_piece", columnList = "numeroPiece"),
        @Index(name = "idx_type_piece_numero_piece", columnList = "typePiece,numeroPiece"),
        @Index(name = "idx_employeur_type_piece", columnList = "numeroUniqueEmployeur,typePiece"),
        @Index(name = "idx_employeur_piece_complet", columnList = "numeroUniqueEmployeur,typePiece,numeroPiece")
    })

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmtHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroUniqueEmployeur;
    private String typePiece;
    private String numeroPiece;
    private String prenom;
    private String nom;
}
