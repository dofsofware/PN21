package com.secusociale.portail.service.dto.extraction;

import java.util.List;

public class DemandeDeCarteCollectiveResponse {
    private final List<Object> resultats;

    public DemandeDeCarteCollectiveResponse(List<Object> resultats) {
        this.resultats = resultats;
    }

    public List<Object> getResultats() {
        return resultats;
    }
}
