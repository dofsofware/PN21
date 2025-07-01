package com.secusociale.portail.service.dto.extraction;

import com.secusociale.portail.domain.enumeration.StatutDemandeDeCarte;

import java.util.List;

public class DemandeDeCarteRequest {
    private StatutDemandeDeCarte statut;
    private List<Long> demandeIdList;

    public StatutDemandeDeCarte getStatut() {
        return statut;
    }

    public void setStatut(StatutDemandeDeCarte statut) {
        this.statut = statut;
    }

    public List<Long> getDemandeIdList() {
        return demandeIdList;
    }

    public void setDemandeIdList(List<Long> demandeIdList) {
        this.demandeIdList = demandeIdList;
    }
}
