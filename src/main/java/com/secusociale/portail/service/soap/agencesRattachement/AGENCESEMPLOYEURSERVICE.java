
package com.secusociale.portail.service.soap.agencesRattachement;

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
 *                   &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codeAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nomAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adresseAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="telephoneAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "AGENCES_EMPLOYEUR_SERVICE")
public class AGENCESEMPLOYEURSERVICE {

    @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
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

        @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
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
     *         &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codeAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nomAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adresseAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="telephoneAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "institution",
        "codeAgence",
        "nomAgence",
        "adresseAgence",
        "telephoneAgence"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
        protected String institution;
        @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
        protected String codeAgence;
        @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
        protected String nomAgence;
        @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
        protected String adresseAgence;
        @XmlElement(namespace = "http://oracle.com/AGENCES_EMPLOYEUR_SERVICE.xsd")
        protected String telephoneAgence;

        /**
         * Obtient la valeur de la propriété institution.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getInstitution() {
            return institution;
        }

        /**
         * Définit la valeur de la propriété institution.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setInstitution(String value) {
            this.institution = value;
        }

        /**
         * Obtient la valeur de la propriété codeAgence.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCodeAgence() {
            return codeAgence;
        }

        /**
         * Définit la valeur de la propriété codeAgence.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCodeAgence(String value) {
            this.codeAgence = value;
        }

        /**
         * Obtient la valeur de la propriété nomAgence.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNomAgence() {
            return nomAgence;
        }

        /**
         * Définit la valeur de la propriété nomAgence.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNomAgence(String value) {
            this.nomAgence = value;
        }

        /**
         * Obtient la valeur de la propriété adresseAgence.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAdresseAgence() {
            return adresseAgence;
        }

        /**
         * Définit la valeur de la propriété adresseAgence.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAdresseAgence(String value) {
            this.adresseAgence = value;
        }

        /**
         * Obtient la valeur de la propriété telephoneAgence.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTelephoneAgence() {
            return telephoneAgence;
        }

        /**
         * Définit la valeur de la propriété telephoneAgence.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTelephoneAgence(String value) {
            this.telephoneAgence = value;
        }

    }

}
