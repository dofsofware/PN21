package com.secusociale.portail.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class DemandeCertificat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeDemande;
    private LocalDateTime dateDemande = LocalDateTime.now();
    private LocalDateTime dateUpdate;
    private  String statut = "EN_ATTENTE";
    private String updatedBy;
    @Column(length = 1000)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "salarie_id")
    private Salarie salarie;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

}
