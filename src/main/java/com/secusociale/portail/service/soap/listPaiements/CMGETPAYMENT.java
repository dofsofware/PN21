
package com.secusociale.portail.service.soap.listPaiements;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour anonymous complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="input" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="perId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   <element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="output" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="payEventId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="payDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   <element name="tenderTypeDescr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="tenderAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="dateTimeTagFormat" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="xsd:strict" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CM_GET_PAYMENT")
public class CMGETPAYMENT {

    @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd")
    protected CMGETPAYMENT.Input input;
    @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd")
    protected List<CMGETPAYMENT.Output> output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriété input.
     *
     * @return
     *     possible object is
     *     {@link CMGETPAYMENT.Input }
     *
     */
    public CMGETPAYMENT.Input getInput() {
        return input;
    }

    /**
     * Définit la valeur de la propriété input.
     *
     * @param value
     *     allowed object is
     *     {@link CMGETPAYMENT.Input }
     *
     */
    public void setInput(CMGETPAYMENT.Input value) {
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
     * {@link CMGETPAYMENT.Output }
     *
     *
     */
    public List<CMGETPAYMENT.Output> getOutput() {
        if (output == null) {
            output = new ArrayList<CMGETPAYMENT.Output>();
        }
        return this.output;
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
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="perId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         <element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "perId",
        "dateFrom",
        "dateTo"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd")
        protected String perId;
        @XmlElementRef(name = "dateFrom", namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateFrom;
        @XmlElementRef(name = "dateTo", namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateTo;

        /**
         * Obtient la valeur de la propriété perId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPerId() {
            return perId;
        }

        /**
         * Définit la valeur de la propriété perId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPerId(String value) {
            this.perId = value;
        }

        /**
         * Obtient la valeur de la propriété dateFrom.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateFrom() {
            return dateFrom;
        }

        /**
         * Définit la valeur de la propriété dateFrom.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateFrom(JAXBElement<XMLGregorianCalendar> value) {
            this.dateFrom = value;
        }

        /**
         * Obtient la valeur de la propriété dateTo.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateTo() {
            return dateTo;
        }

        /**
         * Définit la valeur de la propriété dateTo.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateTo(JAXBElement<XMLGregorianCalendar> value) {
            this.dateTo = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="payEventId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="payDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         <element name="tenderTypeDescr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="tenderAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "payEventId",
        "payDate",
        "tenderTypeDescr",
        "tenderAmount"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd")
        protected String payEventId;
        @XmlElementRef(name = "payDate", namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> payDate;
        @XmlElement(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd")
        protected String tenderTypeDescr;
        @XmlElementRef(name = "tenderAmount", namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tenderAmount;

        /**
         * Obtient la valeur de la propriété payEventId.
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
         * Définit la valeur de la propriété payEventId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPayEventId(String value) {
            this.payEventId = value;
        }

        /**
         * Obtient la valeur de la propriété payDate.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getPayDate() {
            return payDate;
        }

        /**
         * Définit la valeur de la propriété payDate.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setPayDate(JAXBElement<XMLGregorianCalendar> value) {
            this.payDate = value;
        }

        /**
         * Obtient la valeur de la propriété tenderTypeDescr.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTenderTypeDescr() {
            return tenderTypeDescr;
        }

        /**
         * Définit la valeur de la propriété tenderTypeDescr.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTenderTypeDescr(String value) {
            this.tenderTypeDescr = value;
        }

        /**
         * Obtient la valeur de la propriété tenderAmount.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTenderAmount() {
            return tenderAmount;
        }

        /**
         * Définit la valeur de la propriété tenderAmount.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTenderAmount(JAXBElement<BigDecimal> value) {
            this.tenderAmount = value;
        }

    }

}
