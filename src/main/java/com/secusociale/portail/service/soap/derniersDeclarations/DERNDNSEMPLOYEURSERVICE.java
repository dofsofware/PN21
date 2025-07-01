
package com.secusociale.portail.service.soap.derniersDeclarations;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="input" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="output" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="numeroFacture" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantRetraite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="montantPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="facturePdf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "input",
    "output"
})
@XmlRootElement(name = "DERN_DNS_EMPLOYEUR_SERVICE")
public class DERNDNSEMPLOYEURSERVICE {

    @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
    protected List<Output> output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

   public Input getInput() {
        return input;
    }

    public void setInput(Input value) {
        this.input = value;
    }

    /**
     * Gets the value of the output property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the output property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutput().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Output }
     *
     *
     */
    public List<Output> getOutput() {
        if (output == null) {
            output = new ArrayList<Output>();
        }
        return this.output;
    }

    public String getDateTimeTagFormat() {
        if (dateTimeTagFormat == null) {
            return "xsd:strict";
        } else {
            return dateTimeTagFormat;
        }
    }

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
     *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "personId"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String personId;

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
     *         &lt;element name="numeroFacture" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantRetraite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="facturePdf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "numeroFacture",
        "dateDebut",
        "dateFin",
        "montantRetraite",
        "montantAt",
        "montantPf",
        "facturePdf"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String numeroFacture;
        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String dateDebut;
        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String dateFin;
        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String montantRetraite;
        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String montantAt;
        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String montantPf;
        @XmlElement(namespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE.xsd")
        protected String facturePdf;

        /**
         * Obtient la valeur de la propriété numeroFacture.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumeroFacture() {
            return StringUtils.isEmpty(numeroFacture) ? "" : numeroFacture;
        }

        /**
         * Définit la valeur de la propriété numeroFacture.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroFacture(String value) {
            this.numeroFacture = value;
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
         * Obtient la valeur de la propriété dateFin.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDateFin() {
            return dateFin;
        }

        /**
         * Définit la valeur de la propriété dateFin.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDateFin(String value) {
            this.dateFin = value;
        }

        /**
         * Obtient la valeur de la propriété montantRetraite.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMontantRetraite() {
            return montantRetraite;
        }

        /**
         * Définit la valeur de la propriété montantRetraite.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMontantRetraite(String value) {
            this.montantRetraite = value;
        }

        /**
         * Obtient la valeur de la propriété montantAt.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMontantAt() {
            return montantAt;
        }

        /**
         * Définit la valeur de la propriété montantAt.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMontantAt(String value) {
            this.montantAt = value;
        }

        /**
         * Obtient la valeur de la propriété montantPf.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMontantPf() {
            return montantPf;
        }

        /**
         * Définit la valeur de la propriété montantPf.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMontantPf(String value) {
            this.montantPf = value;
        }

        /**
         * Obtient la valeur de la propriété facturePdf.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getFacturePdf() {
            return facturePdf;
        }

        /**
         * Définit la valeur de la propriété facturePdf.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setFacturePdf(String value) {
            this.facturePdf = value;
        }

    }

}
