
package com.secusociale.portail.service.soap.succursales;

import java.math.BigDecimal;
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
 * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zone" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="personId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="rowCount" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxExclusive value="99999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="results" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="numeroUnique" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ancienNumeroCSS" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ancienNumeroIPRES" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="raisonSociale" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="typeIdentifiant" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="numeroIdentifiant" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="adresse" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="4000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="action" type="{http://ouaf.oracle.com/}listAction" />
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
    "zone",
    "personId",
    "rowCount",
    "results"
})
@XmlRootElement(name = "CM_GET_LISTE_SECURSALES")
public class CMGETLISTESECURSALES {

    @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd", defaultValue = "CM_GET_SECS")
    protected String zone;
    @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
    protected String personId;
    @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
    protected BigDecimal rowCount;
    @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
    protected List<Results> results;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propri�t� zone.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getZone() {
        return zone;
    }

    /**
     * D�finit la valeur de la propri�t� zone.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setZone(String value) {
        this.zone = value;
    }

    /**
     * Obtient la valeur de la propri�t� personId.
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
     * D�finit la valeur de la propri�t� personId.
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
     * Obtient la valeur de la propri�t� rowCount.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getRowCount() {
        return rowCount;
    }

    /**
     * D�finit la valeur de la propri�t� rowCount.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setRowCount(BigDecimal value) {
        this.rowCount = value;
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
     * {@link Results }
     *
     *
     */
    public List<Results> getResults() {
        if (results == null) {
            results = new ArrayList<Results>();
        }
        return this.results;
    }

    /**
     * Obtient la valeur de la propri�t� dateTimeTagFormat.
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
     * D�finit la valeur de la propri�t� dateTimeTagFormat.
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
     * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="numeroUnique" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ancienNumeroCSS" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ancienNumeroIPRES" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="raisonSociale" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="typeIdentifiant" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="numeroIdentifiant" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="adresse" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="4000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="action" type="{http://ouaf.oracle.com/}listAction" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "numeroUnique",
        "ancienNumeroCSS",
        "ancienNumeroIPRES",
        "raisonSociale",
        "typeIdentifiant",
        "numeroIdentifiant",
        "adresse"
    })
    public static class Results {

        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String numeroUnique;
        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String ancienNumeroCSS;
        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String ancienNumeroIPRES;
        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String raisonSociale;
        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String typeIdentifiant;
        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String numeroIdentifiant;
        @XmlElement(namespace = "http://oracle.com/CM_GET_LISTE_SECURSALES.xsd")
        protected String adresse;
        @XmlAttribute(name = "action")
        protected ListAction action;

        /**
         * Obtient la valeur de la propri�t� numeroUnique.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumeroUnique() {
            return numeroUnique;
        }

        /**
         * D�finit la valeur de la propri�t� numeroUnique.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroUnique(String value) {
            this.numeroUnique = value;
        }

        /**
         * Obtient la valeur de la propri�t� ancienNumeroCSS.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAncienNumeroCSS() {
            return ancienNumeroCSS;
        }

        /**
         * D�finit la valeur de la propri�t� ancienNumeroCSS.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAncienNumeroCSS(String value) {
            this.ancienNumeroCSS = value;
        }

        /**
         * Obtient la valeur de la propri�t� ancienNumeroIPRES.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAncienNumeroIPRES() {
            return ancienNumeroIPRES;
        }

        /**
         * D�finit la valeur de la propri�t� ancienNumeroIPRES.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAncienNumeroIPRES(String value) {
            this.ancienNumeroIPRES = value;
        }

        /**
         * Obtient la valeur de la propri�t� raisonSociale.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRaisonSociale() {
            return raisonSociale;
        }

        /**
         * D�finit la valeur de la propri�t� raisonSociale.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRaisonSociale(String value) {
            this.raisonSociale = value;
        }

        /**
         * Obtient la valeur de la propri�t� typeIdentifiant.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTypeIdentifiant() {
            return typeIdentifiant;
        }

        /**
         * D�finit la valeur de la propri�t� typeIdentifiant.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTypeIdentifiant(String value) {
            this.typeIdentifiant = value;
        }

        /**
         * Obtient la valeur de la propri�t� numeroIdentifiant.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumeroIdentifiant() {
            return numeroIdentifiant;
        }

        /**
         * D�finit la valeur de la propri�t� numeroIdentifiant.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroIdentifiant(String value) {
            this.numeroIdentifiant = value;
        }

        /**
         * Obtient la valeur de la propri�t� adresse.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAdresse() {
            return adresse;
        }

        /**
         * D�finit la valeur de la propri�t� adresse.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAdresse(String value) {
            this.adresse = value;
        }

        /**
         * Obtient la valeur de la propri�t� action.
         *
         * @return
         *     possible object is
         *     {@link ListAction }
         *
         */
        public ListAction getAction() {
            return action;
        }

        /**
         * D�finit la valeur de la propri�t� action.
         *
         * @param value
         *     allowed object is
         *     {@link ListAction }
         *
         */
        public void setAction(ListAction value) {
            this.action = value;
        }

    }

}
