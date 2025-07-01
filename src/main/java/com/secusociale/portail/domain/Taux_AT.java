package com.secusociale.portail.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "taux_at")
public class Taux_AT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "secteur_activite")
    @Column(name = "secteur_activite")
    private String secteurActivite;

    @CsvBindByName(column = "taux_AT")
    @Column(name = "taux_AT")
    private String tauxAT;

    @CsvBindByName(column = "activite_principale")
    @Column(name = "activite_principale")
    private String activitePrincipale;
}
