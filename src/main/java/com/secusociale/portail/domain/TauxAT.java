package com.secusociale.portail.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tauxat")
public class TauxAT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "numero_unique")
    @Column(name = "numero_unique")
    private String numeroUnique;

    @CsvBindByName(column = "taux_AT")
    @Column(name = "taux_AT")
    private String tauxAT;
}
