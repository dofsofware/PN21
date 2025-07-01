package com.secusociale.portail.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.secusociale.portail.model.DeclarationModel;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

public class DeclarationModelDTO extends DeclarationModel {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Lob
    private String salariesFile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String numeroUnique;

    private Boolean isMultiSite;

    public String getSalariesFile() {
        return salariesFile;
    }

    public void setSalariesFile(String salariesFile) {
        this.salariesFile = salariesFile;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public Boolean getIsMultiSite() {
        return isMultiSite;
    }

    public void setIsMultiSite(Boolean multiSite) {
        isMultiSite = multiSite;
    }
}
