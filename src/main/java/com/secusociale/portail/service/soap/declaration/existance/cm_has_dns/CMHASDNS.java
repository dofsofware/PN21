
package com.secusociale.portail.service.soap.declaration.existance.cm_has_dns;

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
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
 *         &lt;element name="dateDebut" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="results" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="hasDns" minOccurs="0">
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
    "dateDebut",
    "results"
})
@XmlRootElement(name = "CM_HAS_DNS")
public class CMHASDNS {

    @XmlElement(namespace = "http://oracle.com/CM_HAS_DNS.xsd", defaultValue = "CM_HAS_DNS")
    protected String zone;
    @XmlElement(namespace = "http://oracle.com/CM_HAS_DNS.xsd")
    protected String personId;
    @XmlElement(namespace = "http://oracle.com/CM_HAS_DNS.xsd")
    protected String dateDebut;
    @XmlElement(namespace = "http://oracle.com/CM_HAS_DNS.xsd")
    protected List<Results> results;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriété zone.
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
     * Définit la valeur de la propriété zone.
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
     * Obtient la valeur de la propriété personId.
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
     * Définit la valeur de la propriété personId.
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
     * Obtient la valeur de la propriété dateDebut.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la valeur de la propriété dateDebut.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDateDebut(String value) {
        this.dateDebut = value;
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
     * Obtient la valeur de la propriété dateTimeTagFormat.
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
     * Définit la valeur de la propriété dateTimeTagFormat.
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
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="hasDns" minOccurs="0">
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
        "hasDns"
    })
    public static class Results {

        @XmlElement(namespace = "http://oracle.com/CM_HAS_DNS.xsd")
        protected String hasDns;
        @XmlAttribute(name = "action")
        protected ListAction action;

        /**
         * Obtient la valeur de la propriété hasDns.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getHasDns() {
            return hasDns;
        }

        /**
         * Définit la valeur de la propriété hasDns.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setHasDns(String value) {
            this.hasDns = value;
        }

        /**
         * Obtient la valeur de la propriété action.
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
         * Définit la valeur de la propriété action.
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
