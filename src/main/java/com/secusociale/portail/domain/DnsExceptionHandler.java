package com.secusociale.portail.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Entity
@Table(name = "dns_exception_handler")
public class DnsExceptionHandler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String exception;

    @Column(name = "date_catching")
    private LocalDateTime dateCatching;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "is_treated",columnDefinition = "boolean default false")
    private boolean isTreated;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    public DnsExceptionHandler() {
    }

    public DnsExceptionHandler(User user, String exception, LocalDateTime dateCatching,String errorCode, String raisonSociale) {
        this.user = user;
        this.exception = exception;
        this.dateCatching = dateCatching;
        this.errorCode = errorCode ;
        this.raisonSociale = raisonSociale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public LocalDateTime getDateCatching() {
        return dateCatching;
    }

    public void setDateCatching(LocalDateTime dateCatching) {
        this.dateCatching = dateCatching;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isTreated() {return isTreated;  }

    public void setTreated(boolean treated) { isTreated = treated; }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    @Override
    public String toString() {
        return "DnsExceptionHandler{" +
            "id=" + id +
            ", user=" + user +
            ", exception='" + exception + '\'' +
            ", dateCatching=" + dateCatching +
            ", errorCode='" + errorCode + '\'' +
            ", isTreated=" + isTreated +
            ", raisonSociale='" + raisonSociale + '\'' +
            '}';
    }
}
