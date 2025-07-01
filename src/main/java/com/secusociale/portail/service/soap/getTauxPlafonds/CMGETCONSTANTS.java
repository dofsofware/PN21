
package com.secusociale.portail.service.soap.getTauxPlafonds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


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
 *         &lt;element name="output" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="smig" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="plafondPF" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="plafondRG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="plafondRCC" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tauxPF" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tauxRG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tauxRCC" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
    "output"
})
@XmlRootElement(name = "CM_GET_CONSTANTS", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd")
public class CMGETCONSTANTS {

    @XmlElement(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd")
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

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
     *         &lt;element name="smig" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="plafondPF" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="plafondRG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="plafondRCC" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tauxPF" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tauxRG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tauxRCC" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "smig",
        "plafondPF",
        "plafondRG",
        "plafondRCC",
        "tauxPF",
        "tauxRG",
        "tauxRCC"
    })
    public static class Output {

        @XmlElementRef(name = "smig", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> smig;
        @XmlElementRef(name = "plafondPF", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> plafondPF;
        @XmlElementRef(name = "plafondRG", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> plafondRG;
        @XmlElementRef(name = "plafondRCC", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> plafondRCC;
        @XmlElementRef(name = "tauxPF", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxPF;
        @XmlElementRef(name = "tauxRG", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxRG;
        @XmlElementRef(name = "tauxRCC", namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxRCC;

        /**
         * Obtient la valeur de la propriété smig.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getSmig() {
            return smig;
        }

        /**
         * Définit la valeur de la propriété smig.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setSmig(JAXBElement<BigDecimal> value) {
            this.smig = value;
        }

        /**
         * Obtient la valeur de la propriété plafondPF.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getPlafondPF() {
            return plafondPF;
        }

        /**
         * Définit la valeur de la propriété plafondPF.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setPlafondPF(JAXBElement<BigDecimal> value) {
            this.plafondPF = value;
        }

        /**
         * Obtient la valeur de la propriété plafondRG.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getPlafondRG() {
            return plafondRG;
        }

        /**
         * Définit la valeur de la propriété plafondRG.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setPlafondRG(JAXBElement<BigDecimal> value) {
            this.plafondRG = value;
        }

        /**
         * Obtient la valeur de la propriété plafondRCC.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getPlafondRCC() {
            return plafondRCC;
        }

        /**
         * Définit la valeur de la propriété plafondRCC.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setPlafondRCC(JAXBElement<BigDecimal> value) {
            this.plafondRCC = value;
        }

        /**
         * Obtient la valeur de la propriété tauxPF.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getTauxPF() {
            return tauxPF;
        }

        /**
         * Définit la valeur de la propriété tauxPF.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setTauxPF(JAXBElement<BigDecimal> value) {
            this.tauxPF = value;
        }

        /**
         * Obtient la valeur de la propriété tauxRG.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getTauxRG() {
            return tauxRG;
        }

        /**
         * Définit la valeur de la propriété tauxRG.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setTauxRG(JAXBElement<BigDecimal> value) {
            this.tauxRG = value;
        }

        /**
         * Obtient la valeur de la propriété tauxRCC.
         *
         * @return possible object is
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public JAXBElement<BigDecimal> getTauxRCC() {
            return tauxRCC;
        }

        /**
         * Définit la valeur de la propriété tauxRCC.
         *
         * @param value allowed object is
         *              {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         */
        public void setTauxRCC(JAXBElement<BigDecimal> value) {
            this.tauxRCC = value;
        }

    }

}
