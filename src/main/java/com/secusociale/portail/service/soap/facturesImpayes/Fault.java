
package com.secusociale.portail.service.soap.facturesImpayes;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java pour anonymous complex type.
 *
 * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ResponseStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ResponseText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="parm1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm3" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm4" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm5" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm6" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm7" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm8" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="parm9" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="category" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="numParm" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ServerMessage" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CallSequence" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Table" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Field" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Fault", namespace = "http://ouaf.oracle.com/")
public class Fault {

    @XmlElement(name = "ResponseStatus", required = true)
    protected String responseStatus;
    @XmlElement(name = "ResponseCode")
    protected int responseCode;
    @XmlElement(name = "ResponseText", required = true)
    protected String responseText;
    @XmlElement(name = "ResponseData")
    protected ResponseData responseData;
    @XmlElement(name = "ServerMessage")
    protected ServerMessage serverMessage;

    /**
     * Obtient la valeur de la propriete responseStatus.
     *
     * @return possible object is
     * {@link String }
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     * Definit la valeur de la propriete responseStatus.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResponseStatus(String value) {
        this.responseStatus = value;
    }

    /**
     * Obtient la valeur de la propriete responseCode.
     *
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Definit la valeur de la propriete responseCode.
     *
     */
    public void setResponseCode(int value) {
        this.responseCode = value;
    }

    /**
     * Obtient la valeur de la propriete responseText.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getResponseText() {
        return responseText;
    }

    /**
     * Definit la valeur de la propriete responseText.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setResponseText(String value) {
        this.responseText = value;
    }

    /**
     * Obtient la valeur de la propriete responseData.
     *
     * @return
     *     possible object is
     *     {@link ResponseData }
     *
     */
    public ResponseData getResponseData() {
        return responseData;
    }

    /**
     * Definit la valeur de la propriete responseData.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseData }
     *
     */
    public void setResponseData(ResponseData value) {
        this.responseData = value;
    }

    /**
     * Obtient la valeur de la propriete serverMessage.
     *
     * @return
     *     possible object is
     *     {@link ServerMessage }
     *
     */
    public ServerMessage getServerMessage() {
        return serverMessage;
    }

    /**
     * Definit la valeur de la propriete serverMessage.
     *
     * @param value
     *     allowed object is
     *     {@link ServerMessage }
     *
     */
    public void setServerMessage(ServerMessage value) {
        this.serverMessage = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="parm1" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm2" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm3" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm4" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm5" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm6" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm7" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm8" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="parm9" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="category" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="numParm" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ResponseData {

        @XmlAttribute(name = "parm1")
        protected String parm1;
        @XmlAttribute(name = "parm2")
        protected String parm2;
        @XmlAttribute(name = "parm3")
        protected String parm3;
        @XmlAttribute(name = "parm4")
        protected String parm4;
        @XmlAttribute(name = "parm5")
        protected String parm5;
        @XmlAttribute(name = "parm6")
        protected String parm6;
        @XmlAttribute(name = "parm7")
        protected String parm7;
        @XmlAttribute(name = "parm8")
        protected String parm8;
        @XmlAttribute(name = "parm9")
        protected String parm9;
        @XmlAttribute(name = "text")
        protected String text;
        @XmlAttribute(name = "category")
        protected String category;
        @XmlAttribute(name = "numParm")
        protected Integer numParm;
        @XmlAttribute(name = "number")
        protected Integer number;

        /**
         * Obtient la valeur de la propriete parm1.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm1() {
            return parm1;
        }

        /**
         * Definit la valeur de la propriete parm1.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm1(String value) {
            this.parm1 = value;
        }

        /**
         * Obtient la valeur de la propriete parm2.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm2() {
            return parm2;
        }

        /**
         * Definit la valeur de la propriete parm2.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm2(String value) {
            this.parm2 = value;
        }

        /**
         * Obtient la valeur de la propriete parm3.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm3() {
            return parm3;
        }

        /**
         * Definit la valeur de la propriete parm3.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm3(String value) {
            this.parm3 = value;
        }

        /**
         * Obtient la valeur de la propriete parm4.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm4() {
            return parm4;
        }

        /**
         * Definit la valeur de la propriete parm4.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm4(String value) {
            this.parm4 = value;
        }

        /**
         * Obtient la valeur de la propriete parm5.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm5() {
            return parm5;
        }

        /**
         * Definit la valeur de la propriete parm5.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm5(String value) {
            this.parm5 = value;
        }

        /**
         * Obtient la valeur de la propriete parm6.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm6() {
            return parm6;
        }

        /**
         * Definit la valeur de la propriete parm6.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm6(String value) {
            this.parm6 = value;
        }

        /**
         * Obtient la valeur de la propriete parm7.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm7() {
            return parm7;
        }

        /**
         * Definit la valeur de la propriete parm7.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm7(String value) {
            this.parm7 = value;
        }

        /**
         * Obtient la valeur de la propriete parm8.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm8() {
            return parm8;
        }

        /**
         * Definit la valeur de la propriete parm8.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm8(String value) {
            this.parm8 = value;
        }

        /**
         * Obtient la valeur de la propriete parm9.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getParm9() {
            return parm9;
        }

        /**
         * Definit la valeur de la propriete parm9.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setParm9(String value) {
            this.parm9 = value;
        }

        /**
         * Obtient la valeur de la propriete text.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getText() {
            return text;
        }

        /**
         * Definit la valeur de la propriete text.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setText(String value) {
            this.text = value;
        }

        /**
         * Obtient la valeur de la propriete category.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCategory() {
            return category;
        }

        /**
         * Definit la valeur de la propriete category.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCategory(String value) {
            this.category = value;
        }

        /**
         * Obtient la valeur de la propriete numParm.
         *
         * @return
         *     possible object is
         *     {@link Integer }
         *
         */
        public Integer getNumParm() {
            return numParm;
        }

        /**
         * Definit la valeur de la propriete numParm.
         *
         * @param value
         *     allowed object is
         *     {@link Integer }
         *
         */
        public void setNumParm(Integer value) {
            this.numParm = value;
        }

        /**
         * Obtient la valeur de la propriete number.
         *
         * @return
         *     possible object is
         *     {@link Integer }
         *
         */
        public Integer getNumber() {
            return number;
        }

        /**
         * Definit la valeur de la propriete number.
         *
         * @param value
         *     allowed object is
         *     {@link Integer }
         *
         */
        public void setNumber(Integer value) {
            this.number = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CallSequence" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Table" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Field" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class ServerMessage {

        @XmlElement(name = "Category", required = true)
        protected String category;
        @XmlElement(name = "Number", required = true)
        protected String number;
        @XmlElement(name = "CallSequence", required = true)
        protected String callSequence;
        @XmlElement(name = "ProgramName", required = true)
        protected String programName;
        @XmlElement(name = "Text", required = true)
        protected String text;
        @XmlElement(name = "Description", required = true)
        protected String description;
        @XmlElement(name = "Table", required = true)
        protected String table;
        @XmlElement(name = "Field", required = true)
        protected String field;

        /**
         * Obtient la valeur de la propriete category.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCategory() {
            return category;
        }

        /**
         * Definit la valeur de la propriete category.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCategory(String value) {
            this.category = value;
        }

        /**
         * Obtient la valeur de la propriete number.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumber() {
            return number;
        }

        /**
         * Definit la valeur de la propriete number.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumber(String value) {
            this.number = value;
        }

        /**
         * Obtient la valeur de la propriete callSequence.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCallSequence() {
            return callSequence;
        }

        /**
         * Definit la valeur de la propriete callSequence.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCallSequence(String value) {
            this.callSequence = value;
        }

        /**
         * Obtient la valeur de la propriete programName.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProgramName() {
            return programName;
        }

        /**
         * Definit la valeur de la propriete programName.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProgramName(String value) {
            this.programName = value;
        }

        /**
         * Obtient la valeur de la propriete text.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getText() {
            return text;
        }

        /**
         * Definit la valeur de la propriete text.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setText(String value) {
            this.text = value;
        }

        /**
         * Obtient la valeur de la propriete description.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDescription() {
            return description;
        }

        /**
         * Definit la valeur de la propriete description.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Obtient la valeur de la propriete table.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTable() {
            return table;
        }

        /**
         * Definit la valeur de la propriete table.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTable(String value) {
            this.table = value;
        }

        /**
         * Obtient la valeur de la propriete field.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getField() {
            return field;
        }

        /**
         * Definit la valeur de la propriete field.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setField(String value) {
            this.field = value;
        }

    }

}
