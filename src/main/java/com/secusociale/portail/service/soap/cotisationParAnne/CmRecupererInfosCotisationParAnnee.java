
package com.secusociale.portail.service.soap.cotisationParAnne;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "typePiece",
    "numeroPiece",
    "personId",
    "results"
})
@XmlRootElement(name = "CmRecupererInfosCotisationParAnnee")
public class CmRecupererInfosCotisationParAnnee {

    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
    protected String typePiece;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
    protected String numeroPiece;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
    protected String personId;
    @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
    protected List<CmRecupererInfosCotisationParAnnee.Results> results;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propri?t? typePiece.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypePiece() {
        return typePiece;
    }

    /**
     * D?finit la valeur de la propri?t? typePiece.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypePiece(String value) {
        this.typePiece = value;
    }

    /**
     * Obtient la valeur de la propri?t? numeroPiece.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPiece() {
        return numeroPiece;
    }

    /**
     * D?finit la valeur de la propri?t? numeroPiece.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPiece(String value) {
        this.numeroPiece = value;
    }

    /**
     * Obtient la valeur de la propri?t? personId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * D?finit la valeur de la propri?t? personId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonId(String value) {
        this.personId = value;
    }

    /**
     * Gets the value of the results property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the results property.
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
     * {@link CmRecupererInfosCotisationParAnnee.Results }
     * 
     * 
     */
    public List<CmRecupererInfosCotisationParAnnee.Results> getResults() {
        if (results == null) {
            results = new ArrayList<CmRecupererInfosCotisationParAnnee.Results>();
        }
        return this.results;
    }

    /**
     * Obtient la valeur de la propri?t? dateTimeTagFormat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTimeTagFormat() {
        if (dateTimeTagFormat == null) {
            return "xsd:strict";
        } else {
            return dateTimeTagFormat;
        }
    }

    /**
     * D?finit la valeur de la propri?t? dateTimeTagFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
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
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "annee",
        "montantRG",
        "montantRC",
        "montantPF",
        "montantATMP",
        "nombrePointCotisationRC",
        "nombrePointCotisationRG"
    })
    public static class Results {

        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String annee;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String montantRG;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String montantRC;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String montantPF;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String montantATMP;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String nombrePointCotisationRC;
        @XmlElement(namespace = "http://oracle.com/CmRecupererInfosCotisationParAnnee.xsd")
        protected String nombrePointCotisationRG;

        /**
         * Obtient la valeur de la propri?t? annee.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnnee() {
            return annee;
        }

        /**
         * D?finit la valeur de la propri?t? annee.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnnee(String value) {
            this.annee = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantRG.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMontantRG() {
            return montantRG;
        }

        /**
         * D?finit la valeur de la propri?t? montantRG.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMontantRG(String value) {
            this.montantRG = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantRC.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMontantRC() {
            return montantRC;
        }

        /**
         * D?finit la valeur de la propri?t? montantRC.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMontantRC(String value) {
            this.montantRC = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantPF.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMontantPF() {
            return montantPF;
        }

        /**
         * D?finit la valeur de la propri?t? montantPF.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMontantPF(String value) {
            this.montantPF = value;
        }

        /**
         * Obtient la valeur de la propri?t? montantATMP.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMontantATMP() {
            return montantATMP;
        }

        /**
         * D?finit la valeur de la propri?t? montantATMP.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMontantATMP(String value) {
            this.montantATMP = value;
        }

        /**
         * Obtient la valeur de la propri?t? nombrePointCotisationRC.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombrePointCotisationRC() {
            return nombrePointCotisationRC;
        }

        /**
         * D?finit la valeur de la propri?t? nombrePointCotisationRC.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombrePointCotisationRC(String value) {
            this.nombrePointCotisationRC = value;
        }

        /**
         * Obtient la valeur de la propri?t? nombrePointCotisationRG.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombrePointCotisationRG() {
            return nombrePointCotisationRG;
        }

        /**
         * D?finit la valeur de la propri?t? nombrePointCotisationRG.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombrePointCotisationRG(String value) {
            this.nombrePointCotisationRG = value;
        }

    }

}
