package com.secusociale.portail.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DeclaredEmployer.
 */
@Entity
@Table(name = "declared_employer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class DeclaredEmployer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ninea")
    private String ninea;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "numero_unique")
    private String numeroUnique;

    @Column(name = "ancien_num_ipres")
    private String ancienNumIpres;

    @Column(name = "ancien_num_css")
    private String ancienNumCss;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "region")
    private String region;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "effectif")
    private Long effectif;

    @Column(name = "statut_juridique")
    private String statutJuridique;

    @Column(name = "secteur_activite")
    private String secteurActivite;

    @Column(name = "sigle")
    private String sigle;

    @Column(name = "rccm")
    private String rccm;

    @Column(name = "agence_ipres")
    private String agenceIpres;

    @Column(name = "agence_css")
    private String agenceCss;

    @Column(name = "type_etablissement")
    private String typeEtablissement;

    @Column(name = "numero_unique_parent")
    private String numeroUniqueParent;

    @Column(name = "taux_at")
    private Double tauxAt;

    @Column(name = "code_secteur_cs")
    private String codeSecteurCs;

    @Column(name = "code_zone_geographique_css")
    private String codeZoneGeographiqueCss;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "type_employeur")
    private String typeEmployeur;

    @Column(name = "date_premiere_immatriculation")
    private LocalDate datePremiereImmatriculation;

    @Column(name = "nombre_employes_rc")
    private Integer nombreEmployesRc;

    @Column(name = "code_secteur_ipres")
    private String codeSecteurIpres;

    @Column(name = "code_zone_geographique_ipres")
    private String codeZoneGeographiqueIpres;

    @Column(name = "type_immatriculation")
    private String typeImmatriculation;

    @Column(name = "activite_principale")
    private String activitePrincipale;
}
