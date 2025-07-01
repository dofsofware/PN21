
package com.secusociale.portail.service.soap.urlAttestationReguralite;

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
 *                   &lt;element name="reportName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="reportTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="reportKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="reportLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="parameters" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sendEmail" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="emailBody" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="emailSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="emailTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendEmail",
    "output"
})
@XmlRootElement(name = "CM_GEN_ATTESTATION")
public class CMGENATTESTATION {

    protected Input input;
    protected SendEmail sendEmail;
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

   public Input getInput() {
        return input;
    }

    public void setInput(Input value) {
        this.input = value;
    }

    /**
     * Obtient la valeur de la propriété sendEmail.
     *
     * @return
     *     possible object is
     *     {@link SendEmail }
     *
     */
    public SendEmail getSendEmail() {
        return sendEmail;
    }

    /**
     * Définit la valeur de la propriété sendEmail.
     *
     * @param value
     *     allowed object is
     *     {@link SendEmail }
     *
     */
    public void setSendEmail(SendEmail value) {
        this.sendEmail = value;
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
     *         &lt;element name="reportName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="reportTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="reportKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="reportLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="parameters" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "reportName",
        "reportTemplate",
        "reportKey",
        "reportLanguage",
        "parameters"
    })
    public static class Input {

        protected String reportName;
        protected String reportTemplate;
        protected String reportKey;
        protected String reportLanguage;
        protected List<Parameters> parameters;

        /**
         * Obtient la valeur de la propriété reportName.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getReportName() {
            return reportName;
        }

        /**
         * Définit la valeur de la propriété reportName.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setReportName(String value) {
            this.reportName = value;
        }

        /**
         * Obtient la valeur de la propriété reportTemplate.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getReportTemplate() {
            return reportTemplate;
        }

        /**
         * Définit la valeur de la propriété reportTemplate.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setReportTemplate(String value) {
            this.reportTemplate = value;
        }

        /**
         * Obtient la valeur de la propriété reportKey.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getReportKey() {
            return reportKey;
        }

        /**
         * Définit la valeur de la propriété reportKey.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setReportKey(String value) {
            this.reportKey = value;
        }

        /**
         * Obtient la valeur de la propriété reportLanguage.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getReportLanguage() {
            return reportLanguage;
        }

        /**
         * Définit la valeur de la propriété reportLanguage.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setReportLanguage(String value) {
            this.reportLanguage = value;
        }

        /**
         * Gets the value of the parameters property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parameters property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParameters().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Parameters }
         *
         *
         */
        public List<Parameters> getParameters() {
            if (parameters == null) {
                parameters = new ArrayList<Parameters>();
            }
            return this.parameters;
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
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "name",
            "value"
        })
        public static class Parameters {

            protected String name;
            protected String value;

            /**
             * Obtient la valeur de la propriété name.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getName() {
                return name;
            }

            /**
             * Définit la valeur de la propriété name.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Obtient la valeur de la propriété value.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getValue() {
                return value;
            }

            /**
             * Définit la valeur de la propriété value.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setValue(String value) {
                this.value = value;
            }

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

        protected String url;

        /**
         * Obtient la valeur de la propriété url.
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
         * Définit la valeur de la propriété url.
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
     *         &lt;element name="emailBody" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="emailSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="emailTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "emailBody",
        "emailSubject",
        "emailTo"
    })
    public static class SendEmail {

        protected String emailBody;
        protected String emailSubject;
        protected String emailTo;

        /**
         * Obtient la valeur de la propriété emailBody.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getEmailBody() {
            return emailBody;
        }

        /**
         * Définit la valeur de la propriété emailBody.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setEmailBody(String value) {
            this.emailBody = value;
        }

        /**
         * Obtient la valeur de la propriété emailSubject.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getEmailSubject() {
            return emailSubject;
        }

        /**
         * Définit la valeur de la propriété emailSubject.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setEmailSubject(String value) {
            this.emailSubject = value;
        }

        /**
         * Obtient la valeur de la propriété emailTo.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getEmailTo() {
            return emailTo;
        }

        /**
         * Définit la valeur de la propriété emailTo.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setEmailTo(String value) {
            this.emailTo = value;
        }

    }

}
