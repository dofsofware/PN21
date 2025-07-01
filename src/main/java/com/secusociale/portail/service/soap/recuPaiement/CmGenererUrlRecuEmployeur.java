
package com.secusociale.portail.service.soap.recuPaiement;

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
 *         &lt;element name="input" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="payEventId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "cmGenererUrlRecuEmployeur")
public class CmGenererUrlRecuEmployeur {

    @XmlElement(namespace = "http://oracle.com/cmGenererUrlRecuEmployeur.xsd")
    protected CmGenererUrlRecuEmployeur.Input input;
    @XmlElement(namespace = "http://oracle.com/cmGenererUrlRecuEmployeur.xsd")
    protected CmGenererUrlRecuEmployeur.Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propri?t? input.
     * 
     * @return
     *     possible object is
     *     {@link CmGenererUrlRecuEmployeur.Input }
     *     
     */
    public CmGenererUrlRecuEmployeur.Input getInput() {
        return input;
    }

    /**
     * D?finit la valeur de la propri?t? input.
     * 
     * @param value
     *     allowed object is
     *     {@link CmGenererUrlRecuEmployeur.Input }
     *     
     */
    public void setInput(CmGenererUrlRecuEmployeur.Input value) {
        this.input = value;
    }

    /**
     * Obtient la valeur de la propri?t? output.
     * 
     * @return
     *     possible object is
     *     {@link CmGenererUrlRecuEmployeur.Output }
     *     
     */
    public CmGenererUrlRecuEmployeur.Output getOutput() {
        return output;
    }

    /**
     * D?finit la valeur de la propri?t? output.
     * 
     * @param value
     *     allowed object is
     *     {@link CmGenererUrlRecuEmployeur.Output }
     *     
     */
    public void setOutput(CmGenererUrlRecuEmployeur.Output value) {
        this.output = value;
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
     *         &lt;element name="payEventId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "payEventId"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/cmGenererUrlRecuEmployeur.xsd")
        protected String payEventId;

        /**
         * Obtient la valeur de la propri?t? payEventId.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPayEventId() {
            return payEventId;
        }

        /**
         * D?finit la valeur de la propri?t? payEventId.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPayEventId(String value) {
            this.payEventId = value;
        }

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
     *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "url"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/cmGenererUrlRecuEmployeur.xsd")
        protected String url;

        /**
         * Obtient la valeur de la propri?t? url.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUrl() {
            return url;
        }

        /**
         * D?finit la valeur de la propri?t? url.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUrl(String value) {
            this.url = value;
        }

    }

}
