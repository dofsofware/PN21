package com.secusociale.portail.service.dto.extraction;

import java.util.List;

public class DemandeCarteCollectiveRequest {
    private Long agence;
    private List<DemandeCarteCollectiveSalarieDTO> salaries;

    public Long getAgence() {
        return agence;
    }

    public void setAgence(Long agence) {
        this.agence = agence;
    }

    public List<DemandeCarteCollectiveSalarieDTO> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<DemandeCarteCollectiveSalarieDTO> salaries) {
        this.salaries = salaries;
    }
}
