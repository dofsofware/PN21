package com.secusociale.portail.service.dto;

import java.util.HashSet;
import java.util.Set;

public class ReclamationDTO {

    private Long id;
    private Long userId;
    private Long agenceId;
    private Long numSalarie;
    private Long secondNumSalarie;
    private String libAutresRec;
    private String descriptionAutres;
    private Long agenceIpresID;
    private Long agenceCssID;
    private Set<CarriereManquanteDTO> carriereManquantes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }

    public Long getNumSalarie() {
        return numSalarie;
    }

    public void setNumSalarie(Long numSalarie) {
        this.numSalarie = numSalarie;
    }

    public Long getSecondNumSalarie() {
        return secondNumSalarie;
    }

    public void setSecondNumSalarie(Long secondNumSalarie) {
        this.secondNumSalarie = secondNumSalarie;
    }

    public String getLibAutresRec() {
        return libAutresRec;
    }

    public void setLibAutresRec(String libAutresRec) {
        this.libAutresRec = libAutresRec;
    }

    public String getDescriptionAutres() {
        return descriptionAutres;
    }

    public void setDescriptionAutres(String descriptionAutres) {
        this.descriptionAutres = descriptionAutres;
    }

    public Long getAgenceIpresID() {
        return agenceIpresID;
    }

    public void setAgenceIpresID(Long agenceIpresID) {
        this.agenceIpresID = agenceIpresID;
    }

    public Long getAgenceCssID() {
        return agenceCssID;
    }

    public void setAgenceCssID(Long agenceCssID) {
        this.agenceCssID = agenceCssID;
    }

    public Set<CarriereManquanteDTO> getCarriereManquantes() {
        return carriereManquantes;
    }

    public void setCarriereManquantes(Set<CarriereManquanteDTO> carriereManquantes) {
        this.carriereManquantes = carriereManquantes;
    }

    public void addCarriereManquante(CarriereManquanteDTO carriereManquante) {
        if (carriereManquantes == null) {
            carriereManquantes = new HashSet<>();
        }
        carriereManquantes.add(carriereManquante);
    }

    public void removeCarriereManquante(CarriereManquanteDTO carriereManquante) {
        this.carriereManquantes.remove(carriereManquante);
    }

    @Override
    public String toString() {
        return "ReclamationDTO{" +
            "id=" + id +
            ", userId=" + userId +
            ", agenceId=" + agenceId +
            ", numSalarie=" + numSalarie +
            ", secondNumSalarie=" + secondNumSalarie +
            ", libAutresRec='" + libAutresRec + '\'' +
            ", descriptionAutres='" + descriptionAutres + '\'' +
            ", agenceIpresID=" + agenceIpresID +
            ", agenceCssID=" + agenceCssID +
            ", carriereManquantes=" + carriereManquantes +
            '}';
    }
}
