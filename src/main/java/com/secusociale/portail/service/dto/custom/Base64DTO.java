package com.secusociale.portail.service.dto.custom;

import javax.persistence.Lob;

public class Base64DTO {
    @Lob
    private String base64;
    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}

