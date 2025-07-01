
package com.secusociale.portail.service.soap.demandeAttestationReguralite;

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
 *                   &lt;element name="typeIdentifiant">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="CDAO"/>
 *                         &lt;enumeration value="TRN"/>
 *                         &lt;enumeration value="NIN"/>
 *                         &lt;enumeration value="PASS"/>
 *                         &lt;enumeration value="PIN"/>
 *                         &lt;enumeration value="ANC"/>
 *                         &lt;enumeration value="SCI"/>
 *                         &lt;enumeration value="ANI"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="numeroIdentifiant">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
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
 *                   &lt;element name="idDossier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CmGetAttestationRegularite")
public class CmGetAttestationRegularite {

    @XmlElement(namespace = "http://oracle.com/CmGetAttestationRegularite.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CmGetAttestationRegularite.xsd")
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
     *         &lt;element name="typeIdentifiant">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="CDAO"/>
     *               &lt;enumeration value="TRN"/>
     *               &lt;enumeration value="NIN"/>
     *               &lt;enumeration value="PASS"/>
     *               &lt;enumeration value="PIN"/>
     *               &lt;enumeration value="ANC"/>
     *               &lt;enumeration value="SCI"/>
     *               &lt;enumeration value="ANI"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="numeroIdentifiant">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
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
        "numeroEmployeur"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CmGetAttestationRegularite.xsd", required = true)
        protected String numeroEmployeur;
//        @XmlElement(namespace = "http://oracle.com/CmGetAttestationRegularite.xsd", required = true)
//        protected String typeIdentifiant;
//        @XmlElement(namespace = "http://oracle.com/CmGetAttestationRegularite.xsd", required = true)
//        protected String numeroIdentifiant;


//        public String getTypeIdentifiant() {
//            return typeIdentifiant;
//        }
//        public void setTypeIdentifiant(String value) {
//            this.typeIdentifiant = value;
//        }
//        public String getNumeroIdentifiant() {
//            return numeroIdentifiant;
//        }
//        public void setNumeroIdentifiant(String value) {
//            this.numeroIdentifiant = value;
//        }


        public void setNumeroEmployeur(String numeroEmployeur) {
            this.numeroEmployeur = numeroEmployeur;
        }

        public String getNumeroEmployeur() {
            return numeroEmployeur;
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
     *         &lt;element name="idDossier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "idDossier"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CmGetAttestationRegularite.xsd")
        protected String idDossier;

        /**
         * Obtient la valeur de la propriété idDossier.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIdDossier() {
            return idDossier;
        }

        /**
         * Définit la valeur de la propriété idDossier.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIdDossier(String value) {
            this.idDossier = value;
        }

    }

}
