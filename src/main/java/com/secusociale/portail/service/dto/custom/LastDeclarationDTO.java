package com.secusociale.portail.service.dto.custom;

import javax.annotation.Nullable;
import java.time.Instant;

public class LastDeclarationDTO {
    private String numeroUnique;
    private Instant debut;
    @Nullable
    private Instant fin;
    private String type;

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Instant getDebut() {
        return debut;
    }

    public Instant getFin() {
        return fin;
    }

    public void setDebut(Instant debut) {
        this.debut = debut;
    }

    public void setFin(Instant fin) {
        this.fin = fin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LastDeclarationDTO(String numeroUnique, Instant debut, @Nullable Instant fin, String type) {
        this.numeroUnique = numeroUnique;
        this.debut = debut;
        this.fin = fin;
        this.type = type;
    }

    public LastDeclarationDTO() {
    }
}
