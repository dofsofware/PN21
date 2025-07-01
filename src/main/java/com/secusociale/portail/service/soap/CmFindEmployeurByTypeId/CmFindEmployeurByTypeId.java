
package com.secusociale.portail.service.soap.CmFindEmployeurByTypeId;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CmFindEmployeurByTypeId")
public class CmFindEmployeurByTypeId {

    protected Input input;
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriété input.
     *
     * @return
     *     possible object is
     *     {@link Input }
     *
     */
    public Input getInput() {
        return input;
    }

    /**
     * Définit la valeur de la propriété input.
     *
     * @param value
     *     allowed object is
     *     {@link Input }
     *
     */
    public void setInput(Input value) {
        this.input = value;
    }

    /**
     * Obtient la valeur de la propriété output.
     *
     * @return
     *     possible object is
     *     {@link Output }
     *
     */
    public Output getOutput() {
        return output;
    }

    /**
     * Définit la valeur de la propriété output.
     *
     * @param value
     *     allowed object is
     *     {@link Output }
     *
     */
    public void setOutput(Output value) {
        this.output = value;
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
     *         &lt;element name="identifierType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="identifierValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "identifierType",
        "identifierValue"
    })
    public static class Input {

        protected String identifierType;
        protected String identifierValue;

        /**
         * Obtient la valeur de la propriété identifierType.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIdentifierType() {
            return identifierType;
        }

        /**
         * Définit la valeur de la propriété identifierType.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIdentifierType(String value) {
            this.identifierType = value;
        }

        /**
         * Obtient la valeur de la propriété identifierValue.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIdentifierValue() {
            return identifierValue;
        }

        /**
         * Définit la valeur de la propriété identifierValue.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIdentifierValue(String value) {
            this.identifierValue = value;
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
     *         &lt;element name="employeurs" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ancienNumeroCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ancienNumeroIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceCodeCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceCodeIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceDescriptionCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceDescriptionIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "employeurs"
    })
    public static class Output {

        protected List<Employeurs> employeurs;

        /**
         * Gets the value of the employeurs property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the employeurs property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEmployeurs().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Employeurs }
         *
         *
         */
        public List<Employeurs> getEmployeurs() {
            if (employeurs == null) {
                employeurs = new ArrayList<Employeurs>();
            }
            return this.employeurs;
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
         *         &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ancienNumeroCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ancienNumeroIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceCodeCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceCodeIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceDescriptionCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceDescriptionIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "numeroEmployeur",
            "ancienNumeroCSS",
            "ancienNumeroIPRES",
            "raisonSociale",
            "adresse",
            "agenceCodeCSS",
            "agenceCodeIPRES",
            "agenceDescriptionCSS",
            "agenceDescriptionIPRES"
        })
        public static class Employeurs {

            protected String numeroEmployeur;
            protected String ancienNumeroCSS;
            protected String ancienNumeroIPRES;
            protected String raisonSociale;
            protected String adresse;
            protected String agenceCodeCSS;
            protected String agenceCodeIPRES;
            protected String agenceDescriptionCSS;
            protected String agenceDescriptionIPRES;

            /**
             * Obtient la valeur de la propriété numeroEmployeur.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumeroEmployeur() {
                return numeroEmployeur;
            }

            /**
             * Définit la valeur de la propriété numeroEmployeur.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumeroEmployeur(String value) {
                this.numeroEmployeur = value;
            }

            /**
             * Obtient la valeur de la propriété ancienNumeroCSS.
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
             * Définit la valeur de la propriété ancienNumeroCSS.
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
             * Obtient la valeur de la propriété ancienNumeroIPRES.
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
             * Définit la valeur de la propriété ancienNumeroIPRES.
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
             * Obtient la valeur de la propriété raisonSociale.
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
             * Définit la valeur de la propriété raisonSociale.
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
             * Obtient la valeur de la propriété adresse.
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
             * Définit la valeur de la propriété adresse.
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
             * Obtient la valeur de la propriété agenceCodeCSS.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgenceCodeCSS() {
                return agenceCodeCSS;
            }

            /**
             * Définit la valeur de la propriété agenceCodeCSS.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgenceCodeCSS(String value) {
                this.agenceCodeCSS = value;
            }

            /**
             * Obtient la valeur de la propriété agenceCodeIPRES.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgenceCodeIPRES() {
                return agenceCodeIPRES;
            }

            /**
             * Définit la valeur de la propriété agenceCodeIPRES.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgenceCodeIPRES(String value) {
                this.agenceCodeIPRES = value;
            }

            /**
             * Obtient la valeur de la propriété agenceDescriptionCSS.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgenceDescriptionCSS() {
                return agenceDescriptionCSS;
            }

            /**
             * Définit la valeur de la propriété agenceDescriptionCSS.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgenceDescriptionCSS(String value) {
                this.agenceDescriptionCSS = value;
            }

            /**
             * Obtient la valeur de la propriété agenceDescriptionIPRES.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgenceDescriptionIPRES() {
                return agenceDescriptionIPRES;
            }

            /**
             * Définit la valeur de la propriété agenceDescriptionIPRES.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgenceDescriptionIPRES(String value) {
                this.agenceDescriptionIPRES = value;
            }

        }

    }

}
