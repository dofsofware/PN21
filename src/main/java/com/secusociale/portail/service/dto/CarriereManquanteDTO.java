package com.secusociale.portail.service.dto;

import java.time.LocalDate;

public class CarriereManquanteDTO {

    private Long id;
    private String numeroUniqueEmployeur;
    private String raisonSociale;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double salaireBrut;
    private Double cotisationCss;
    private Double cotisationIpres;
    private String justificatifsCarriereManquantes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroUniqueEmployeur() {
        return numeroUniqueEmployeur;
    }

    public void setNumeroUniqueEmployeur(String numeroUniqueEmployeur) {
        this.numeroUniqueEmployeur = numeroUniqueEmployeur;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Double getSalaireBrut() {
        return salaireBrut;
    }

    public void setSalaireBrut(Double salaireBrut) {
        this.salaireBrut = salaireBrut;
    }

    public Double getCotisationCss() {
        return cotisationCss;
    }

    public void setCotisationCss(Double cotisationCss) {
        this.cotisationCss = cotisationCss;
    }

    public Double getCotisationIpres() {
        return cotisationIpres;
    }

    public void setCotisationIpres(Double cotisationIpres) {
        this.cotisationIpres = cotisationIpres;
    }

    public String getJustificatifsCarriereManquantes() {
        return justificatifsCarriereManquantes;
    }

    public void setJustificatifsCarriereManquantes(String justificatifsCarriereManquantes) {
        this.justificatifsCarriereManquantes = justificatifsCarriereManquantes;
    }

    @Override
    public String toString() {
        return "CarriereManquanteDTO{" +
            "id=" + id +
            ", numeroUniqueEmployeur='" + numeroUniqueEmployeur + '\'' +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", dateDebut=" + dateDebut +
            ", dateFin=" + dateFin +
            ", salaireBrut=" + salaireBrut +
            ", cotisationCss=" + cotisationCss +
            ", cotisationIpres=" + cotisationIpres +
            ", justificatifsCarriereManquantes='" + justificatifsCarriereManquantes + '\'' +
            '}';
    }
}
