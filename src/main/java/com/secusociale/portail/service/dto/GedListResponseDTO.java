package com.secusociale.portail.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public class GedListResponseDTO implements Serializable {
    //id, raisonSociale sociale, numéro css, numéro ipress, numéro unique, date, agence caisse, agence ipress, ninéa, mandat
    private Long id;
    private String raisonSociale;
    private String ninea;
    private String numeroUnique;
    private Instant date;
    private String agenceIPRES;
    private String agenceCSS;
    private String mandat;
    private String mandatFile;

    public GedListResponseDTO(HashMap<String, Object> source) {
        setId((Long) source.get("id"));
        setRaisonSociale((String) source.get("raisonSociale"));
        setNinea((String) source.get("ninea"));
        setNumeroUnique((String) source.get("numeroUnique"));
        setAgenceIPRES((String) source.get("agenceIPRES"));
        setAgenceCSS((String) source.get("agenceCSS"));
        setMandat((String) source.get("mandat"));
        setMandatFile((String) source.get("mandatFile"));
        setDate((Instant) source.get("date"));
    }

    public GedListResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getAgenceIPRES() {
        return agenceIPRES;
    }

    public void setAgenceIPRES(String agenceIPRES) {
        this.agenceIPRES = agenceIPRES;
    }

    public String getAgenceCSS() {
        return agenceCSS;
    }

    public void setAgenceCSS(String agenceCSS) {
        this.agenceCSS = agenceCSS;
    }

    public String getMandat() {
        return mandat;
    }

    public void setMandat(String mandat) {
        this.mandat = mandat;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GedListResponseDTO gedDTO = (GedListResponseDTO) o;
        if (gedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GedListResponseDTO{" +
            "id=" + id +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", ninea='" + ninea + '\'' +
            ", numeroUnique='" + numeroUnique + '\'' +
            ", date=" + date +
            ", agenceIPRES='" + agenceIPRES + '\'' +
            ", agenceCSS='" + agenceCSS + '\'' +
            ", mandat='" + mandat + '\'' +
            ", mandatFile='" + mandatFile + '\'' +
            '}';
    }
}
