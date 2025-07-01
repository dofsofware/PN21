
package com.secusociale.portail.service.soap.employeurExistant;

import javax.xml.bind.annotation.*;


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
 *                   &lt;element name="typeIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="output" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="raisonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="address4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CM_GET_EMPLOYEUR_DETAILS")
public class CMGETEMPLOYEURDETAILS {

    @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

   public Input getInput() {
        return input;
    }

    public void setInput(Input value) {
        this.input = value;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output value) {
        this.output = value;
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
     *         &lt;element name="typeIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "typeIdentifiant",
        "numeroIdentifiant",
        "numeroUnique"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String typeIdentifiant;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String numeroIdentifiant;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String numeroUnique;

        /**
         * Obtient la valeur de la propriété typeIdentifiant.
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
         * Définit la valeur de la propriété typeIdentifiant.
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
         * Obtient la valeur de la propriété numeroIdentifiant.
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
         * Définit la valeur de la propriété numeroIdentifiant.
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
         * Obtient la valeur de la propriété numeroUnique.
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
         * Définit la valeur de la propriété numeroUnique.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroUnique(String value) {
            this.numeroUnique = value;
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
     *         &lt;element name="raisonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="address4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "raisonSocial",
        "address1",
        "address2",
        "address3",
        "address4",
        "tauxAt"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String raisonSocial;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String address1;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String address2;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String address3;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String address4;
        @XmlElement(namespace = "http://oracle.com/CM_GET_EMPLOYEUR_DETAILS.xsd")
        protected String tauxAt;

        /**
         * Obtient la valeur de la propriété raisonSocial.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRaisonSocial() {
            return raisonSocial;
        }

        /**
         * Définit la valeur de la propriété raisonSocial.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRaisonSocial(String value) {
            this.raisonSocial = value;
        }

        /**
         * Obtient la valeur de la propriété address1.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAddress1() {
            return address1;
        }

        /**
         * Définit la valeur de la propriété address1.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAddress1(String value) {
            this.address1 = value;
        }

        /**
         * Obtient la valeur de la propriété address2.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAddress2() {
            return address2;
        }

        /**
         * Définit la valeur de la propriété address2.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAddress2(String value) {
            this.address2 = value;
        }

        /**
         * Obtient la valeur de la propriété address3.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAddress3() {
            return address3;
        }

        /**
         * Définit la valeur de la propriété address3.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAddress3(String value) {
            this.address3 = value;
        }

        /**
         * Obtient la valeur de la propriété address4.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAddress4() {
            return address4;
        }

        /**
         * Définit la valeur de la propriété address4.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAddress4(String value) {
            this.address4 = value;
        }

        /**
         * Obtient la valeur de la propriété tauxAt.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTauxAt() {
            return tauxAt;
        }

        /**
         * Définit la valeur de la propriété tauxAt.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTauxAt(String value) {
            this.tauxAt = value;
        }

    }

}
