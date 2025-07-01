
package com.secusociale.portail.service.soap.duplicata_recu_url;

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
 *                   &lt;element name="idPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CM_GET_PAYMENT_URL")
public class CMGETPAYMENTURL {

    @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT_URL.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT_URL.xsd")
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

   public Input getInput() {
        return input;
    }

    /**
     * Définit la valeur de la propriété input.
     *
     * @param value allowed object is
     *              {@link Input }
     */
    public void setInput(Input value) {
        this.input = value;
    }

    /**
     * Obtient la valeur de la propriété output.
     *
     * @return possible object is
     * {@link Output }
     */
    public Output getOutput() {
        return output;
    }

    /**
     * Définit la valeur de la propriété output.
     *
     * @param value allowed object is
     *              {@link Output }
     */
    public void setOutput(Output value) {
        this.output = value;
    }

    /**
     * Obtient la valeur de la propriété dateTimeTagFormat.
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
     * Définit la valeur de la propriété dateTimeTagFormat.
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
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="idPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idPaiement"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT_URL.xsd")
        protected String idPaiement;

        /**
         * Obtient la valeur de la propriété idPaiement.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdPaiement() {
            return idPaiement;
        }

        /**
         * Définit la valeur de la propriété idPaiement.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdPaiement(String value) {
            this.idPaiement = value;
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
     *         &lt;element name="recuPaiement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "recuPaiement"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT_URL.xsd")
        protected String recuPaiement;

        /**
         * Obtient la valeur de la propriété recuPaiement.
         *
         * @return possible object is
         * {@link String }
         */
        public String getRecuPaiement() {
            return recuPaiement;
        }

        /**
         * Définit la valeur de la propriété recuPaiement.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRecuPaiement(String value) {
            this.recuPaiement = value;
        }

    }

}
