package com.secusociale.portail.service.dto.custom;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ReuploadMandatDTO implements Serializable {
    @NotNull
    private Long id;
    @Lob
    @NotNull
    private String mandatFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMandatFile() {
        return mandatFile;
    }

    public void setMandatFile(String mandatFile) {
        this.mandatFile = mandatFile;
    }
}
