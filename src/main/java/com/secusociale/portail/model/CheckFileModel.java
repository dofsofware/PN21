package com.secusociale.portail.model;

public class CheckFileModel {
    private Long userId;
    private Long numeroUnique;
    private String employeur;
    private Long numeroCss;
    private Long numeroIpres;
    private String fileEncoded;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getNumeroUnique() { return numeroUnique; }
    public void setNumeroUnique(Long numeroUnique) { this.numeroUnique = numeroUnique; }

    public Long getNumeroCss() { return numeroCss; }
    public void setNumeroCss(Long numeroCss) { this.numeroCss = numeroCss; }

    public Long getNumeroIpres() { return numeroIpres; }
    public void setNumeroIpres(Long numeroIpres) { this.numeroIpres = numeroIpres; }

    public String getFileEncoded() { return fileEncoded; }
    public void setFileEncoded(String fileEncoded) { this.fileEncoded = fileEncoded; }

    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }
}
