package com.secusociale.portail.service.dto.custom;

import com.secusociale.portail.service.soap.cotisationParAnne.CmRecupererInfosCotisationParAnnee;
import com.secusociale.portail.service.soap.detailsCotisationParAnne.CmRecupererInfosDetailsCotisation;

public class ExtraInfosDTO {
    private CmRecupererInfosCotisationParAnnee.Results cotisationAnnuelle;
    private CmRecupererInfosDetailsCotisation.Results details;

    public CmRecupererInfosCotisationParAnnee.Results getCotisationAnnuelle() {
        return cotisationAnnuelle;
    }

    public void setCotisationAnnuelle(CmRecupererInfosCotisationParAnnee.Results cotisationAnnuelle) {
        this.cotisationAnnuelle = cotisationAnnuelle;
    }

    public CmRecupererInfosDetailsCotisation.Results getDetails() {
        return details;
    }

    public void setDetails(CmRecupererInfosDetailsCotisation.Results details) {
        this.details = details;
    }

    public ExtraInfosDTO() {
    }

    public ExtraInfosDTO(CmRecupererInfosCotisationParAnnee.Results cotisationAnnuelle, CmRecupererInfosDetailsCotisation.Results details) {
        this.cotisationAnnuelle = cotisationAnnuelle;
        this.details = details;
    }
}

