package com.secusociale.portail.service.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;

@Embeddable
public class OTPCode implements Serializable {

    @Column(name = "code")
    private String code;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    // Getters et setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }
}
