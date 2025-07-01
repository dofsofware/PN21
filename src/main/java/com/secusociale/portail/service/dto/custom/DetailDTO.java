package com.secusociale.portail.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class DetailDTO {
    @JsonProperty("NOM_FEUILLE")
    private String NOM_FEUILLE;
    @JsonProperty("ID_EMPLOYEUR")
    private String ID_EMPLOYEUR;
    @JsonProperty("ID_DNS")
    private String ID_DNS;
    @JsonProperty("ERREUR_EMPLOYEUR")
    private Map<String, String>[] ERREUR_EMPLOYEUR;
    @JsonProperty("ERREUR_SYNTHESE")
    private Map<String, String>[] ERREUR_SYNTHESE;
    @JsonProperty("ERREUR_SALARIER")
    private Map<String, String>[] ERREUR_SALARIER;

    public String getNOM_FEUILLE() {
        return NOM_FEUILLE;
    }

    public void setNOM_FEUILLE(String NOM_FEUILLE) {
        this.NOM_FEUILLE = NOM_FEUILLE;
    }

    public String getID_EMPLOYEUR() {
        return ID_EMPLOYEUR;
    }

    public void setID_EMPLOYEUR(String ID_EMPLOYEUR) {
        this.ID_EMPLOYEUR = ID_EMPLOYEUR;
    }

    public String getID_DNS() {
        return ID_DNS;
    }

    public void setID_DNS(String ID_DNS) {
        this.ID_DNS = ID_DNS;
    }

    public Map<String, String>[] getERREUR_EMPLOYEUR() {
        return ERREUR_EMPLOYEUR;
    }

    public void setERREUR_EMPLOYEUR(Map<String, String>[] ERREUR_EMPLOYEUR) {
        this.ERREUR_EMPLOYEUR = ERREUR_EMPLOYEUR;
    }

    public Map<String, String>[] getERREUR_SYNTHESE() {
        return ERREUR_SYNTHESE;
    }

    public void setERREUR_SYNTHESE(Map<String, String>[] ERREUR_SYNTHESE) {
        this.ERREUR_SYNTHESE = ERREUR_SYNTHESE;
    }

    public Map<String, String>[] getERREUR_SALARIER() {
        return ERREUR_SALARIER;
    }

    public void setERREUR_SALARIER(Map<String, String>[] ERREUR_SALARIER) {
        this.ERREUR_SALARIER = ERREUR_SALARIER;
    }
}
