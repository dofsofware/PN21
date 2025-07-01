
package com.secusociale.portail.service.soap.detailsCotisationParAnne;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java pour anonymous complex type.
 *
 * <p>Le fragment de sch?ma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typePiece" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroPiece" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="results" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="annee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantRG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantRC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantATMP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombrePointCotisationRC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nombrePointCotisationRG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="dateTimeTagFormat" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="xsd:strict" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "typePiece",
    "numeroPiece",
    "personId",
    "annee",
    "results"
})
@XmlRootElement(name = "CmRecupererInfosDetailsCotisation")
public class CmRecupererInfosDetailsCotisation {

    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
    protected String typePiece;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
    protected String numeroPiece;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
    protected String personId;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
    protected String annee;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
    protected List<CmRecupererInfosDetailsCotisation.Results> results;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propri?t? typePiece.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTypePiece() {
        return typePiece;
    }

    /**
     * Définit la valeur de la propri?t? typePiece.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTypePiece(String value) {
        this.typePiece = value;
    }

    /**
     * Obtient la valeur de la propri?t? numeroPiece.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumeroPiece() {
        return numeroPiece;
    }

    /**
     * Définit la valeur de la propri?t? numeroPiece.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumeroPiece(String value) {
        this.numeroPiece = value;
    }

    /**
     * Obtient la valeur de la propri?t? personId.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * Définit la valeur de la propri?t? personId.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPersonId(String value) {
        this.personId = value;
    }

    /**
     * Obtient la valeur de la propri?t? annee.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAnnee() {
        return annee;
    }

    /**
     * Définit la valeur de la propri?t? annee.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAnnee(String value) {
        this.annee = value;
    }


    /**
     * Gets the value of the results' property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the results' property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResults().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CmRecupererInfosDetailsCotisation.Results }
     */
    public List<CmRecupererInfosDetailsCotisation.Results> getResults() {
        if (results == null) {
            results = new ArrayList<>();
        }
        return this.results;
    }

    /**
     * Obtient la valeur de la propri?t? dateTimeTagFormat.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDateTimeTagFormat() {
        if (dateTimeTagFormat == null) {
            return "xsd:strict";
        } else {
            return dateTimeTagFormat;
        }
    }

    /**
     * Définit la valeur de la propri?t? dateTimeTagFormat.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDateTimeTagFormat(String value) {
        this.dateTimeTagFormat = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de sch?ma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="annee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantRG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantRC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantATMP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombrePointCotisationRC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombrePointCotisationRG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "annee",
        "dateDebutPeriodeCotisation",
        "dateFinPeriodeCotisation",
        "idEmployeur",
        "montantRG",
        "montantRC",
        "montantPF",
        "montantATMP"
    })
    public static class Results {

        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String annee;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String dateDebutPeriodeCotisation;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String dateFinPeriodeCotisation;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String idEmployeur;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String montantRG;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String montantRC;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String montantPF;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosDetailsCotisation.xsd")
        protected String montantATMP;

        /**
         * Obtient la valeur de la propri?t? annee.
         *
         * @return possible object is
         * {@link String }
         */
        public String getAnnee() {
            if (!StringUtils.isEmpty(dateDebutPeriodeCotisation))
                return dateDebutPeriodeCotisation.split("-")[0];
            return annee;
        }

        /**
         * Définit la valeur de la propri?t? annee.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setAnnee(String value) {
            this.annee = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantRG.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMontantRG() {
            return montantRG;
        }

        /**
         * Définit la valeur de la propri?t? montantRG.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMontantRG(String value) {
            this.montantRG = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantRC.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMontantRC() {
            return montantRC;
        }

        /**
         * Définit la valeur de la propri?t? montantRC.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMontantRC(String value) {
            this.montantRC = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantPF.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMontantPF() {
            return montantPF;
        }

        /**
         * Définit la valeur de la propri?t? montantPF.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMontantPF(String value) {
            this.montantPF = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantATMP.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMontantATMP() {
            return montantATMP;
        }

        /**
         * Définit la valeur de la propri?t? montantATMP.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMontantATMP(String value) {
            this.montantATMP = value;
        }

        public String getDateDebutPeriodeCotisation() {
            return dateDebutPeriodeCotisation;
        }

        public void setDateDebutPeriodeCotisation(String dateDebutPeriodeCotisation) {
            this.dateDebutPeriodeCotisation = dateDebutPeriodeCotisation;
        }

        public String getDateFinPeriodeCotisation() {
            return dateFinPeriodeCotisation;
        }

        public void setDateFinPeriodeCotisation(String dateFinPeriodeCotisation) {
            this.dateFinPeriodeCotisation = dateFinPeriodeCotisation;
        }

        public String getIdEmployeur() {
            return idEmployeur;
        }

        public void setIdEmployeur(String idEmployeur) {
            this.idEmployeur = idEmployeur;
        }
    }

}
