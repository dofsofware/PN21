package com.secusociale.portail.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class CallBackDTO {
    @JsonProperty("IPD_ID_TRAITEMENT")
    private String IPD_ID_TRAITEMENT;
    @JsonProperty("NOM_FICHIER")
    private String NOM_FICHIER;
    @JsonProperty("STATUS")
    private String STATUS;
    @JsonProperty("DETAILS")
    private DetailDTO[] DETAILS;

    public String getIPD_ID_TRAITEMENT() {
        return IPD_ID_TRAITEMENT;
    }

    public void setIPD_ID_TRAITEMENT(String IPD_ID_TRAITEMENT) {
        this.IPD_ID_TRAITEMENT = IPD_ID_TRAITEMENT;
    }

    public String getNOM_FICHIER() {
        return NOM_FICHIER;
    }

    public void setNOM_FICHIER(String NOM_FICHIER) {
        this.NOM_FICHIER = NOM_FICHIER;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public DetailDTO[] getDETAILS() {
        return DETAILS;
    }

    public void setDETAILS(DetailDTO[] DETAILS) {
        this.DETAILS = DETAILS;
    }
}

