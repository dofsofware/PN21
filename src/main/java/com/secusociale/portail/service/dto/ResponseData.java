package com.secusociale.portail.service.dto;


import java.util.List;

public class ResponseData {
    private String zone;
    private String personId;
    private int rowCount;
    private List<DeclaredEmployerDTO> results;
    private String dateTimeTagFormat;
    private String[] uniqueNumbers;
    private String[] raisonSociale;
    private String[] ancienNumIpres;
    private String[] ancienNumCss;
    private String[] adresse;

    // Getters et Setters
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<DeclaredEmployerDTO> getResults() {
        return results;
    }

    public void setResults(List<DeclaredEmployerDTO> results) {
        this.results = results;
    }

    public String getDateTimeTagFormat() {
        return dateTimeTagFormat;
    }

    public void setDateTimeTagFormat(String dateTimeTagFormat) {
        this.dateTimeTagFormat = dateTimeTagFormat;
    }


    public void setUniqueNumbers(String[] uniqueNumbers) {
        this.uniqueNumbers = uniqueNumbers;
    }

    public void setRaisonSociale(String[] raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
    public void setAncienNumIpres(String[] ancienNumIpres) {
        this.ancienNumIpres = ancienNumIpres;
    }
    public void setAncienNumCss(String[] ancienNumCss) {
        this.ancienNumCss = ancienNumCss;
    }

    public void setAdresse(String[] adresse) {
        this.adresse = adresse;
    }
}
