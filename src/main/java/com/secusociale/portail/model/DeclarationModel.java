package com.secusociale.portail.model;

import com.google.protobuf.TextFormat.ParseException;
import com.secusociale.portail.service.dto.FactureIndividuelleDTO;
import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class DeclarationModel {

    private String numeroEmployeur;

    private String raisonSociale;

    private String adresse;

    private String typeDeclaration;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateDebutPeriodeCotisation;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateFinPeriodeCotisation;

    private BigDecimal totalNouvSalaries;

    private BigDecimal totalSalaries;

    private BigDecimal cumulTotSalAssIpresRg;

    private BigDecimal cumulTotSalAssIpresRcc;

    private BigDecimal cumulTotSalAssCssPf;

    private BigDecimal cumulTotSalAssCssAtmp;

    private BigDecimal totalSalVerses;

    private BigDecimal mntCotPfCalcParEmployeur;

    private BigDecimal mntCotAtMpCalcParEmployeur;

    private BigDecimal mntCotRgCalcParEmployeur;

    private BigDecimal mntCotRccCalcParEmployeur;

    private String processFlowId;
    private String formId;

    private Boolean isMultiSite;

    private List<DeclarationModel.EmployeModel> informationSalaries;

    private List<FactureIndividuelleDTO> factures;

    public void setInformationSalaries(List<EmployeModel> informationSalaries) {
        this.informationSalaries = informationSalaries;
    }

    public Boolean getMultiSite() {
        return isMultiSite;
    }

    public void setMultiSite(Boolean multiSite) {
        isMultiSite = multiSite;
    }

//    public String getTypeIdentifiant() {
//        return typeIdentifiant;
//    }
//
//
//    public void setTypeIdentifiant(String typeIdentifiant) {
//        this.typeIdentifiant = typeIdentifiant;
//    }
//
//
//    public String getIdIdentifiant() {
//        return idIdentifiant;
//    }
//
//
//    public void setIdIdentifiant(String idIdentifiant) {
//        this.idIdentifiant = idIdentifiant;
//    }


    public void setNumeroEmployeur(String numeroEmployeur) {
        this.numeroEmployeur = numeroEmployeur;
    }

    public String getNumeroEmployeur() {
        return numeroEmployeur;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }


    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }


    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getTypeDeclaration() {
        return typeDeclaration;
    }


    public void setTypeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }


    public Date getDateDebutPeriodeCotisation() {
        return dateDebutPeriodeCotisation;
    }


    public void setDateDebutPeriodeCotisation(Date dateDebutCotisation) {
        this.dateDebutPeriodeCotisation = dateDebutCotisation;
    }


    public Date getDateFinPeriodeCotisation() {
        return dateFinPeriodeCotisation;
    }


    public void setDateFinPeriodeCotisation(Date dateFinPeriodeCotisation) {
        this.dateFinPeriodeCotisation = dateFinPeriodeCotisation;
    }


    public BigDecimal getTotalNouvSalaries() {
        return totalNouvSalaries;
    }


    public void setTotalNouvSalaries(BigDecimal totalNouvSalaries) {
        this.totalNouvSalaries = totalNouvSalaries;
    }


    public BigDecimal getTotalSalaries() {
        return totalSalaries;
    }


    public void setTotalSalaries(BigDecimal totalSalaries) {
        this.totalSalaries = totalSalaries;
    }


    public BigDecimal getCumulTotSalAssIpresRg() {
        return cumulTotSalAssIpresRg;
    }


    public void setCumulTotSalAssIpresRg(BigDecimal cumulTotSalAssIpresRg) {
        this.cumulTotSalAssIpresRg = cumulTotSalAssIpresRg;
    }


    public BigDecimal getCumulTotSalAssIpresRcc() {
        return cumulTotSalAssIpresRcc;
    }


    public void setCumulTotSalAssIpresRcc(BigDecimal cumulTotSalAssIpresRcc) {
        this.cumulTotSalAssIpresRcc = cumulTotSalAssIpresRcc;
    }


    public BigDecimal getCumulTotSalAssCssPf() {
        return cumulTotSalAssCssPf;
    }


    public void setCumulTotSalAssCssPf(BigDecimal cumulTotSalAssCssPf) {
        this.cumulTotSalAssCssPf = cumulTotSalAssCssPf;
    }


    public BigDecimal getCumulTotSalAssCssAtmp() {
        return cumulTotSalAssCssAtmp;
    }


    public void setCumulTotSalAssCssAtmp(BigDecimal cumulTotSalAssCssAtmp) {
        this.cumulTotSalAssCssAtmp = cumulTotSalAssCssAtmp;
    }


    public BigDecimal getTotalSalVerses() {
        return totalSalVerses;
    }


    public void setTotalSalVerses(BigDecimal totalSalVerses) {
        this.totalSalVerses = totalSalVerses;
    }


    public BigDecimal getMntCotPfCalcParEmployeur() {
        return mntCotPfCalcParEmployeur;
    }


    public void setMntCotPfCalcParEmployeur(BigDecimal mntCotPfCalcParEmployeur) {
        this.mntCotPfCalcParEmployeur = mntCotPfCalcParEmployeur;
    }


    public BigDecimal getMntCotAtMpCalcParEmployeur() {
        return mntCotAtMpCalcParEmployeur;
    }


    public void setMntCotAtMpCalcParEmployeur(BigDecimal mntCotAtMpCalcParEmployeur) {
        this.mntCotAtMpCalcParEmployeur = mntCotAtMpCalcParEmployeur;
    }


    public BigDecimal getMntCotRgCalcParEmployeur() {
        return mntCotRgCalcParEmployeur;
    }


    public void setMntCotRgCalcParEmployeur(BigDecimal mntCotRgCalcParEmployeur) {
        this.mntCotRgCalcParEmployeur = mntCotRgCalcParEmployeur;
    }


    public BigDecimal getMntCotRccCalcParEmployeur() {
        return mntCotRccCalcParEmployeur;
    }


    public void setMntCotRccCalcParEmployeur(BigDecimal mntCotRccCalcParEmployeur) {
        this.mntCotRccCalcParEmployeur = mntCotRccCalcParEmployeur;
    }


    public String getProcessFlowId() {
        return processFlowId;
    }


    public void setProcessFlowId(String processFlowId) {
        this.processFlowId = processFlowId;
    }


    public String getFormId() {
        return formId;
    }


    public void setFormId(String formId) {
        this.formId = formId;
    }

    public List<FactureIndividuelleDTO> getFactures() {
        return factures;
    }

    public void setFactures(List<FactureIndividuelleDTO> factures) {
        this.factures = factures;
    }

    public List<DeclarationModel.EmployeModel> getInformationSalaries() {

        if (informationSalaries == null) {
            informationSalaries = new ArrayList<DeclarationModel.EmployeModel>();
        }
        return informationSalaries;
    }

    public XMLGregorianCalendar formatToGregorianCalendar(String dateFormat) throws ParseException, DatatypeConfigurationException, java.text.ParseException {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        if (dateFormat != null && !dateFormat.isEmpty() && !"".equals(dateFormat)) {

            Date format = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat);
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(format);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gCalendar.get(Calendar.YEAR), gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

            return xmlGregorianCalendar;
        } else {
            return null;
        }

    }

    public String formaToString(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        return formatDate.format(date);
    }

    public static class EmployeModel {


        private String numeroAssureSocial;

        private String nomEmploye;

        private String prenomEmploye;

        private String dateNaissance;

        private String typePieceIdentite;

        private String numPieceIdentite;

        private String natureContrat;

        private String dateEntree;

        private String dateSortie;

        private String motifSortie;

        private BigDecimal totSalAssCssPf1;

        private BigDecimal totSalAssCssAtmp1;

        private BigDecimal totSalAssIpresRg1;

        private BigDecimal totSalAssIpresRcc1;

        private BigDecimal salaireBrut1;

        private BigDecimal nombreJours1;

        private BigDecimal nombreHeures1;

        private String tempsTravail1;

        private String trancheTravail1;

        private Boolean regimeGeneral1;

        private Boolean regimCompCadre1;

        private String dateEffetRegimeCadre1;

        private String totSalAssCssPf2;

        private BigDecimal totSalAssCssAtmp2;

        private BigDecimal totSalAssIpresRg2;

        private BigDecimal totSalAssIpresRcc2;

        private BigDecimal salaireBrut2;

        private BigDecimal nombreJours2;

        private BigDecimal nombreHeures2;

        private String tempsTravail2;

        private String trancheTravail2;

        private Boolean regimeGeneral2;

        private Boolean regimCompCadre2;

        private String dateEffetRegimeCadre2;

        private BigDecimal totSalAssCssPf3;

        private BigDecimal totSalAssCssAtmp3;

        private BigDecimal totSalAssIpresRg3;

        private BigDecimal totSalAssIpresRcc3;

        private BigDecimal salaireBrut3;

        private BigDecimal nombreJours3;

        private BigDecimal nombreHeures3;

        private String tempsTravail3;

        private String fromWhere;

        private String trancheTravail3;

        private Boolean regimeGeneral3;

        private Boolean regimCompCadre3;

        private String dateEffetRegimeCadre3;

        public String getNumeroAssureSocial() {
            return numeroAssureSocial;
        }

        public void setNumeroAssureSocial(String numeroAssureSocial) {
            this.numeroAssureSocial = numeroAssureSocial;
        }

        public String getNomEmploye() {
            return nomEmploye;
        }

        public void setNomEmploye(String nomEmploye) {
            this.nomEmploye = nomEmploye;
        }

        public String getPrenomEmploye() {
            return prenomEmploye;
        }

        public void setPrenomEmploye(String prenomEmploye) {
            this.prenomEmploye = prenomEmploye;
        }

        public String getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(String string) {
            this.dateNaissance = string;
        }

        public String getTypePieceIdentite() {
            return typePieceIdentite;
        }

        public void setTypePieceIdentite(String typePieceIdentite) {
            this.typePieceIdentite = typePieceIdentite;
        }

        public String getNumPieceIdentite() {
            return numPieceIdentite;
        }

        public void setNumPieceIdentite(String numPieceIdentite) {
            this.numPieceIdentite = numPieceIdentite;
        }

        public String getNatureContrat() {
            return natureContrat;
        }

        public void setNatureContrat(String natureContrat) {
            this.natureContrat = natureContrat;
        }

        public String getDateEntree() {
            return dateEntree;
        }

        public void setDateEntree(String string) {
            this.dateEntree = string;
        }

        public String getDateSortie() {
            return dateSortie;
        }

        public String getFromWhere() {
            return fromWhere;
        }

        public void setFromWhere(String fromWhere) {
            this.fromWhere = fromWhere;
        }

        public void setDateSortie(String string) {
            this.dateSortie = string;
        }

        public String getMotifSortie() {
            return motifSortie;
        }

        public void setMotifSortie(String motifSortie) {
            this.motifSortie = motifSortie;
        }

        public BigDecimal getTotSalAssCssPf1() {
            return totSalAssCssPf1 == null ? BigDecimal.valueOf(0) : totSalAssCssPf1;
        }

        public void setTotSalAssCssPf1(BigDecimal totSalAssCssPf1) {
            this.totSalAssCssPf1 = totSalAssCssPf1;
        }

        public BigDecimal getTotSalAssCssAtmp1() {
            return totSalAssCssAtmp1 == null ? BigDecimal.valueOf(0) : totSalAssCssAtmp1;
        }

        public void setTotSalAssCssAtmp1(BigDecimal totSalAssCssAtmp1) {
            this.totSalAssCssAtmp1 = totSalAssCssAtmp1;
        }

        public BigDecimal getTotSalAssIpresRg1() {
            return totSalAssIpresRg1 == null ? BigDecimal.valueOf(0) : totSalAssIpresRg1;
        }

        public void setTotSalAssIpresRg1(BigDecimal totSalAssIpresRg1) {
            this.totSalAssIpresRg1 = totSalAssIpresRg1;
        }

        public BigDecimal getTotSalAssIpresRcc1() {
            return totSalAssIpresRcc1 == null ? BigDecimal.valueOf(0) : totSalAssIpresRcc1;
        }

        public void setTotSalAssIpresRcc1(BigDecimal totSalAssIpresRcc1) {
            this.totSalAssIpresRcc1 = totSalAssIpresRcc1;
        }

        public BigDecimal getSalaireBrut1() {
            return salaireBrut1 == null ? BigDecimal.valueOf(0) : salaireBrut1;
        }

        public void setSalaireBrut1(BigDecimal salaireBrut1) {
            this.salaireBrut1 = salaireBrut1;
        }

        public BigDecimal getNombreJours1() {
            return nombreJours1 == null ? BigDecimal.valueOf(0) : nombreJours1;
        }

        public void setNombreJours1(BigDecimal nombreJours1) {
            this.nombreJours1 = nombreJours1;
        }

        public BigDecimal getNombreHeures1() {
            return nombreHeures1 == null ? BigDecimal.valueOf(0) : nombreHeures1;
        }

        public void setNombreHeures1(BigDecimal nombreHeures1) {
            this.nombreHeures1 = nombreHeures1;
        }

        public String getTempsTravail1() {
            return tempsTravail1;
        }

        public void setTempsTravail1(String tempsTravail1) {
            this.tempsTravail1 = tempsTravail1;
        }

        public String getTrancheTravail1() {
            return trancheTravail1;
        }

        public void setTrancheTravail1(String trancheTravail1) {
            this.trancheTravail1 = trancheTravail1;
        }

        public Boolean getRegimeGeneral1() {
            return regimeGeneral1;
        }

        public void setRegimeGeneral1(Boolean boolean1) {
            this.regimeGeneral1 = boolean1;
        }

        public Boolean getRegimCompCadre1() {
            return regimCompCadre1;
        }

        public void setRegimCompCadre1(Boolean boolean1) {
            this.regimCompCadre1 = boolean1;
        }

        public String getDateEffetRegimeCadre1() {
            return dateEffetRegimeCadre1;
        }

        public void setDateEffetRegimeCadre1(String string) {
            this.dateEffetRegimeCadre1 = string;
        }

        public String getTotSalAssCssPf2() {
            return StringUtils.isEmpty(totSalAssCssPf2) ? "0" : totSalAssCssPf2;
        }

        public void setTotSalAssCssPf2(String string) {
            this.totSalAssCssPf2 = string;
        }

        public BigDecimal getTotSalAssCssAtmp2() {
            return totSalAssCssAtmp2;
        }

        public void setTotSalAssCssAtmp2(BigDecimal totSalAssCssAtmp2) {
            this.totSalAssCssAtmp2 = totSalAssCssAtmp2;
        }

        public BigDecimal getTotSalAssIpresRg2() {
            return totSalAssIpresRg2;
        }

        public void setTotSalAssIpresRg2(BigDecimal totSalAssIpresRg2) {
            this.totSalAssIpresRg2 = totSalAssIpresRg2;
        }

        public BigDecimal getTotSalAssIpresRcc2() {
            return totSalAssIpresRcc2;
        }

        public void setTotSalAssIpresRcc2(BigDecimal totSalAssIpresRcc2) {
            this.totSalAssIpresRcc2 = totSalAssIpresRcc2;
        }

        public BigDecimal getSalaireBrut2() {
            return salaireBrut2;
        }

        public void setSalaireBrut2(BigDecimal salaireBrut2) {
            this.salaireBrut2 = salaireBrut2;
        }

        public BigDecimal getNombreJours2() {
            return nombreJours2;
        }

        public void setNombreJours2(BigDecimal bigDecimal) {
            this.nombreJours2 = bigDecimal;
        }

        public BigDecimal getNombreHeures2() {
            return nombreHeures2;
        }

        public void setNombreHeures2(BigDecimal bigDecimal) {
            this.nombreHeures2 = bigDecimal;
        }

        public String getTempsTravail2() {
            return tempsTravail2;
        }

        public void setTempsTravail2(String tempsTravail2) {
            this.tempsTravail2 = tempsTravail2;
        }

        public String getTrancheTravail2() {
            return trancheTravail2;
        }

        public void setTrancheTravail2(String trancheTravail2) {
            this.trancheTravail2 = trancheTravail2;
        }

        public Boolean getRegimeGeneral2() {
            return regimeGeneral2;
        }

        public void setRegimeGeneral2(Boolean boolean1) {
            this.regimeGeneral2 = boolean1;
        }

        public Boolean getRegimCompCadre2() {
            return regimCompCadre2;
        }

        public void setRegimCompCadre2(Boolean boolean1) {
            this.regimCompCadre2 = boolean1;
        }

        public String getDateEffetRegimeCadre2() {
            return dateEffetRegimeCadre2;
        }

        public void setDateEffetRegimeCadre2(String date) {
            this.dateEffetRegimeCadre2 = date;
        }

        public BigDecimal getTotSalAssCssPf3() {
            return totSalAssCssPf3;
        }

        public void setTotSalAssCssPf3(BigDecimal totSalAssCssPf3) {
            this.totSalAssCssPf3 = totSalAssCssPf3;
        }

        public BigDecimal getTotSalAssCssAtmp3() {
            return totSalAssCssAtmp3;
        }

        public void setTotSalAssCssAtmp3(BigDecimal totSalAssCssAtmp3) {
            this.totSalAssCssAtmp3 = totSalAssCssAtmp3;
        }

        public BigDecimal getTotSalAssIpresRg3() {
            return totSalAssIpresRg3;
        }

        public void setTotSalAssIpresRg3(BigDecimal totSalAssIpresRg3) {
            this.totSalAssIpresRg3 = totSalAssIpresRg3;
        }

        public BigDecimal getTotSalAssIpresRcc3() {
            return totSalAssIpresRcc3;
        }

        public void setTotSalAssIpresRcc3(BigDecimal totSalAssIpresRcc3) {
            this.totSalAssIpresRcc3 = totSalAssIpresRcc3;
        }

        public BigDecimal getSalaireBrut3() {
            return salaireBrut3;
        }

        public void setSalaireBrut3(BigDecimal salaireBrut3) {
            this.salaireBrut3 = salaireBrut3;
        }

        public BigDecimal getNombreJours3() {
            return nombreJours3;
        }

        public void setNombreJours3(BigDecimal bigDecimal) {
            this.nombreJours3 = bigDecimal;
        }

        public BigDecimal getNombreHeures3() {
            return nombreHeures3;
        }

        public void setNombreHeures3(BigDecimal bigDecimal) {
            this.nombreHeures3 = bigDecimal;
        }

        public String getTempsTravail3() {
            return tempsTravail3;
        }

        public void setTempsTravail3(String tempsTravail3) {
            this.tempsTravail3 = tempsTravail3;
        }

        public String getTrancheTravail3() {
            return trancheTravail3;
        }

        public void setTrancheTravail3(String trancheTravail3) {
            this.trancheTravail3 = trancheTravail3;
        }

        public Boolean getRegimeGeneral3() {
            return regimeGeneral3;
        }

        public void setRegimeGeneral3(Boolean boolean1) {
            this.regimeGeneral3 = boolean1;
        }

        public Boolean getRegimCompCadre3() {
            return regimCompCadre3;
        }

        public void setRegimCompCadre3(Boolean boolean1) {
            this.regimCompCadre3 = boolean1;
        }

        public String getDateEffetRegimeCadre3() {
            return dateEffetRegimeCadre3;
        }

        public void setDateEffetRegimeCadre3(String date) {
            this.dateEffetRegimeCadre3 = date;
        }


    }

    @Override
    public String toString() {
        return "DeclarationModel{" +
            "numeroEmployeur='" + numeroEmployeur + '\'' +
            ", raisonSociale='" + raisonSociale + '\'' +
            ", adresse='" + adresse + '\'' +
            ", typeDeclaration='" + typeDeclaration + '\'' +
            ", dateDebutPeriodeCotisation=" + dateDebutPeriodeCotisation +
            ", dateFinPeriodeCotisation=" + dateFinPeriodeCotisation +
            ", totalNouvSalaries=" + totalNouvSalaries +
            ", totalSalaries=" + totalSalaries +
            ", cumulTotSalAssIpresRg=" + cumulTotSalAssIpresRg +
            ", cumulTotSalAssIpresRcc=" + cumulTotSalAssIpresRcc +
            ", cumulTotSalAssCssPf=" + cumulTotSalAssCssPf +
            ", cumulTotSalAssCssAtmp=" + cumulTotSalAssCssAtmp +
            ", totalSalVerses=" + totalSalVerses +
            ", mntCotPfCalcParEmployeur=" + mntCotPfCalcParEmployeur +
            ", mntCotAtMpCalcParEmployeur=" + mntCotAtMpCalcParEmployeur +
            ", mntCotRgCalcParEmployeur=" + mntCotRgCalcParEmployeur +
            ", mntCotRccCalcParEmployeur=" + mntCotRccCalcParEmployeur +
            ", processFlowId='" + processFlowId + '\'' +
            ", formId='" + formId + '\'' +
            ", isMultiSite=" + isMultiSite +
            ", informationSalaries=" + informationSalaries +
            ", factures=" + factures +
            '}';
    }
}
