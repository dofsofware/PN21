package com.secusociale.portail.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangeGrappeMemberStatusDTO {

    @NotNull(message = "Le statut est obligatoire")
    private String statutGrappe;

    @NotNull(message = "L'ID du salarié est obligatoire")
    private Long salarieId;

    @Size(max = 1000, message = "Le motif ne peut pas dépasser 1000 caractères")
    private String motif;

    public String getStatutGrappe() {
        return statutGrappe;
    }

    public void setStatutGrappe(String statutGrappe) {
        this.statutGrappe = statutGrappe;
    }

    public Long getSalarieId() {
        return salarieId;
    }

    public void setSalarieId(Long salarieId) {
        this.salarieId = salarieId;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}
