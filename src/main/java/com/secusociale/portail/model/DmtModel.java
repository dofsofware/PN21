package com.secusociale.portail.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Lob;
import java.math.BigDecimal;
import java.util.List;

public class DmtModel {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Lob
    private String file;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String raisonSocial;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Lob
    private String autreInfos;

    private String idEmployeur;
    private List<Employes> employes;

    public String getIdEmployeur() {
        return idEmployeur;
    }

    public void setIdEmployeur(String idEmployeur) {
        this.idEmployeur = idEmployeur;
    }

    public List<Employes> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employes> employes) {
        this.employes = employes;
    }

    public static class Employes {

        private String nom;
        private String prenom;
        private String sexe;
        private String etatCivil;
        private String dateNaissance;
        private String nationalite;
        private String typePiece;
        private String numeroPiece;
        private String typeDeMouvement;
        private String motifMouvement;
        private String typeContrat;
        private String dateDebutMouvement;
        private String dateFinMouvement;
        private String profession;
        private String emploi;
        private Boolean estCadre;
        private String conventionApplicable;
        private BigDecimal salaireBrute;
        private String tempsTravail;
        private String categorie;

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getSexe() {
            return sexe;
        }

        public void setSexe(String sexe) {
            this.sexe = sexe;
        }

        public String getEtatCivil() {
            return etatCivil;
        }

        public void setEtatCivil(String etatCivil) {
            this.etatCivil = etatCivil;
        }

        public String getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(String dateNaissance) {
            this.dateNaissance = dateNaissance;
        }

        public String getNationalite() {
            return nationalite;
        }

        public void setNationalite(String nationalite) {
            this.nationalite = nationalite;
        }

        public String getTypePiece() {
            return typePiece;
        }

        public void setTypePiece(String typePiece) {
            this.typePiece = typePiece;
        }

        public String getNumeroPiece() {
            return numeroPiece;
        }

        public void setNumeroPiece(String numeroPiece) {
            this.numeroPiece = numeroPiece;
        }

        public String getTypeDeMouvement() {
            return typeDeMouvement;
        }

        public void setTypeDeMouvement(String typeDeMouvement) {
            this.typeDeMouvement = typeDeMouvement;
        }

        public String getMotifMouvement() {
            return motifMouvement;
        }

        public void setMotifMouvement(String motifMouvement) {
            this.motifMouvement = motifMouvement;
        }

        public String getTypeContrat() {
            return typeContrat;
        }

        public void setTypeContrat(String typeContrat) {
            this.typeContrat = typeContrat;
        }

        public String getDateDebutMouvement() {
            return dateDebutMouvement;
        }

        public void setDateDebutMouvement(String dateDebutMouvement) {
            this.dateDebutMouvement = dateDebutMouvement;
        }

        public String getDateFinMouvement() {
            return dateFinMouvement;
        }

        public void setDateFinMouvement(String dateFinMouvement) {
            this.dateFinMouvement = dateFinMouvement;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getEmploi() {
            return emploi;
        }

        public void setEmploi(String emploi) {
            this.emploi = emploi;
        }

        public Boolean getEstCadre() {
            return estCadre;
        }

        public void setEstCadre(Boolean estCadre) {
            this.estCadre = estCadre;
        }

        public String getConventionApplicable() {
            return conventionApplicable;
        }

        public void setConventionApplicable(String conventionApplicable) {
            this.conventionApplicable = conventionApplicable;
        }

        public BigDecimal getSalaireBrute() {
            return salaireBrute;
        }

        public void setSalaireBrute(BigDecimal salaireBrute) {
            this.salaireBrute = salaireBrute;
        }

        public String getTempsTravail() {
            return tempsTravail;
        }

        public void setTempsTravail(String tempsTravail) {
            this.tempsTravail = tempsTravail;
        }

        public String getCategorie() {
            return categorie;
        }

        public void setCategorie(String categorie) {
            this.categorie = categorie;
        }


    }

    public String getAutreInfos() {
        return autreInfos;
    }

    public void setAutreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
