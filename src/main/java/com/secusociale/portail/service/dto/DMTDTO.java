package com.secusociale.portail.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.model.DmtModel;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.secusociale.portail.domain.DMT} entity.
 */
public class DMTDTO implements Serializable {

    private Long id;

    private String idEmployeur;

    private String status;

    private Instant date;

    private Long userId;

    @Lob
    private String file;

    @Lob
    private String reponsebrute;

    private String raisonSocial;

    @Lob
    private String autreInfos;

    @Lob
    private String employesList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEmployeur() {
        return idEmployeur;
    }

    public DMTDTO idEmployeur(String idEmployeur) {
        this.idEmployeur = idEmployeur;
        return this;
    }

    public void setIdEmployeur(String idEmployeur) {
        this.idEmployeur = idEmployeur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getReponsebrute() {
        return reponsebrute;
    }

    public void setReponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getAutreInfos() {
        return autreInfos;
    }

    public void setAutreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
    }

    public String getEmployesList() {
        return employesList;
    }

    public void setEmployesList(String employesList) {
        this.employesList = employesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DMTDTO dMTDTO = (DMTDTO) o;
        if (dMTDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dMTDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DMTDTO{" +
            "id=" + getId() +
            ", idEmployeur='" + getIdEmployeur() + "'" +
            ", status='" + getStatus() + "'" +
            ", date='" + getDate() + "'" +
            ", userId=" + getUserId() +
            ", file='" + getFile() + "'" +
            ", reponsebrute='" + getReponsebrute() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", autreInfos='" + getAutreInfos() + "'" +
            ", employesList='" + getEmployesList() + "'" +
            "}";
    }


    //builder

    public DMTDTO status(String status) {
        this.status = status;
        return this;
    }

    public DMTDTO date(Instant date) {
        this.date = date;
        return this;
    }

    public DMTDTO userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public DMTDTO file(String file) {
        this.file = file;
        return this;
    }

    public DMTDTO reponsebrute(String reponsebrute) {
        this.reponsebrute = reponsebrute;
        return this;
    }

    public DMTDTO raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public DMTDTO autreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
        return this;
    }

    public DMTDTO employesList(String employesList) {
        this.employesList = employesList;
        return this;
    }

    public DmtModel toModel() {
        try {
            DmtModel model = new DmtModel();
            ObjectMapper om = new ObjectMapper();
            model.setIdEmployeur(idEmployeur);
            model.setRaisonSocial(raisonSocial);
            model.setAutreInfos(autreInfos);
            model.setFile(file);

            DmtModel.Employes[] employes = om.readValue(employesList, DmtModel.Employes[].class);
            List<DmtModel.Employes> listEmp = Arrays.asList(employes);
            model.setEmployes(listEmp);
            return model;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
