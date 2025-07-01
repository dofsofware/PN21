package com.secusociale.portail.service.dto.custom;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UrlDTO {
    @NotNull
    @NotBlank
    private String url;
    private String fileName;

    public UrlDTO() {

    }

    public UrlDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
