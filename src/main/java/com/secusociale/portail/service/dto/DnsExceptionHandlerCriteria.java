package com.secusociale.portail.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class DnsExceptionHandlerCriteria implements Serializable, Criteria {

    private Long userId;
    private LocalDate dateCatching;
    private String errorCode;
    private Boolean treated;
    private String raisonSociale;


    public DnsExceptionHandlerCriteria() {
    }
    public DnsExceptionHandlerCriteria(DnsExceptionHandlerCriteria other) {
      //  this.userId = other.userId == null ? null : other.userId.copy();
     //   this.raisonSociale = other.raisonSociale == null ? null : other.raisonSociale.copy();

    }

    // Getters et Setters pour chaque champ
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getDateCatching() { return dateCatching; }
    public void setDateCatching(LocalDate dateCatching) { this.dateCatching = dateCatching; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public Boolean isTreated() { return treated; }
    public void setTreated(Boolean treated) { this.treated = treated; }

    public String getRaisonSociale() { return raisonSociale; }
    public void setRaisonSociale(String raisonSociale) { this.raisonSociale = raisonSociale; }

    @Override
    public DnsExceptionHandlerCriteria copy() {
        DnsExceptionHandlerCriteria copy = new DnsExceptionHandlerCriteria();
        copy.setUserId(this.userId);
        copy.setDateCatching(this.dateCatching);
        copy.setErrorCode(this.errorCode);
        copy.setTreated(this.treated);
        copy.setRaisonSociale(this.raisonSociale);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsExceptionHandlerCriteria)) return false;
        DnsExceptionHandlerCriteria that = (DnsExceptionHandlerCriteria) o;
        return Objects.equals(userId, that.userId) &&
            Objects.equals(dateCatching, that.dateCatching) &&
            Objects.equals(errorCode, that.errorCode) &&
            Objects.equals(treated, that.treated) &&
            Objects.equals(raisonSociale, that.raisonSociale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, dateCatching, errorCode, treated, raisonSociale);
    }


    @Override
    public String toString() {
        return "DeclarationCriteria{" +
            "userId=" + userId +
            ", raisonSociale=" + raisonSociale +
            ", dateCatching=" + dateCatching +
            ", treated=" + treated +
            '}';
    }
}
