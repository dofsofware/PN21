
package com.secusociale.portail.service.soap.derniersPaiements;

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
 *                   &lt;element name="montantEncaisse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="idPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="recuPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "DERN_PAY_EMPLOYEUR_SERVICE")
public class DERNPAYEMPLOYEURSERVICE {

    @XmlElement(namespace = "http://oracle.com/DERN_PAY_EMPLOYEUR_SERVICE.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/DERN_PAY_EMPLOYEUR_SERVICE.xsd")
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

        @XmlElement(namespace = "http://oracle.com/DERN_PAY_EMPLOYEUR_SERVICE.xsd")
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
     *         &lt;element name="montantEncaisse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="idPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="recuPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "montantEncaisse",
        "idPaiement",
        "recuPaiement"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/DERN_PAY_EMPLOYEUR_SERVICE.xsd")
        protected String montantEncaisse;
        @XmlElement(namespace = "http://oracle.com/DERN_PAY_EMPLOYEUR_SERVICE.xsd")
        protected String idPaiement;
        @XmlElement(namespace = "http://oracle.com/DERN_PAY_EMPLOYEUR_SERVICE.xsd")
        protected String recuPaiement;

        /**
         * Obtient la valeur de la propriété montantEncaisse.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMontantEncaisse() {
            return montantEncaisse;
        }

        /**
         * Définit la valeur de la propriété montantEncaisse.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMontantEncaisse(String value) {
            this.montantEncaisse = value;
        }

        /**
         * Obtient la valeur de la propriété idPaiement.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIdPaiement() {
            return idPaiement;
        }

        /**
         * Définit la valeur de la propriété idPaiement.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIdPaiement(String value) {
            this.idPaiement = value;
        }

        /**
         * Obtient la valeur de la propriété recuPaiement.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRecuPaiement() {
            return recuPaiement;
        }

        /**
         * Définit la valeur de la propriété recuPaiement.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRecuPaiement(String value) {
            this.recuPaiement = value;
        }

    }

}
