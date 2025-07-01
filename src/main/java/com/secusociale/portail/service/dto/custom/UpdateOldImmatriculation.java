package com.secusociale.portail.service.dto.custom;

public class UpdateOldImmatriculation {
    private Long id;
    private Boolean valid;
    private String motif;

    public UpdateOldImmatriculation(Long id, Boolean valid, String motif) {
        this.id = id;
        this.valid = valid;
        this.motif = motif;
    }

    public UpdateOldImmatriculation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}
