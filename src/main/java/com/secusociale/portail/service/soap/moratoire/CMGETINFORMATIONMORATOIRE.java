
package com.secusociale.portail.service.soap.moratoire;

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
 *                   &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="listMoratoire" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="idMoratoire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="payments" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="datePayment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="isPaymentDone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
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
@XmlRootElement(name = "CM_GET_INFORMATION_MORATOIRE")
public class CMGETINFORMATIONMORATOIRE {

    @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
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
     *         &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idEmployer",
        "dateDebut",
        "dateFin"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd", required = true)
        protected String idEmployer;
        @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
        protected String dateDebut;
        @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
        protected String dateFin;

        /**
         * Obtient la valeur de la propriété idEmployer.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdEmployer() {
            return idEmployer;
        }

        /**
         * Définit la valeur de la propriété idEmployer.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdEmployer(String value) {
            this.idEmployer = value;
        }

        /**
         * Obtient la valeur de la propriété dateDebut.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDateDebut() {
            return dateDebut;
        }

        /**
         * Définit la valeur de la propriété dateDebut.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDateDebut(String value) {
            this.dateDebut = value;
        }

        /**
         * Obtient la valeur de la propriété dateFin.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDateFin() {
            return dateFin;
        }

        /**
         * Définit la valeur de la propriété dateFin.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDateFin(String value) {
            this.dateFin = value;
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
     *         &lt;element name="listMoratoire" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="idMoratoire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="payments" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="datePayment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="isPaymentDone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "listMoratoire"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
        protected List<ListMoratoire> listMoratoire;

        /**
         * Gets the value of the listMoratoire property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the listMoratoire property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getListMoratoire().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ListMoratoire }
         */
        public List<ListMoratoire> getListMoratoire() {
            if (listMoratoire == null) {
                listMoratoire = new ArrayList<ListMoratoire>();
            }
            return this.listMoratoire;
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
         *         &lt;element name="idMoratoire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="payments" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="datePayment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="isPaymentDone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "idMoratoire",
            "payments"
        })
        public static class ListMoratoire {

            @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
            protected String idMoratoire;
            @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
            protected List<Payments> payments;

            /**
             * Obtient la valeur de la propriété idMoratoire.
             *
             * @return possible object is
             * {@link String }
             */
            public String getIdMoratoire() {
                return idMoratoire;
            }

            /**
             * Définit la valeur de la propriété idMoratoire.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setIdMoratoire(String value) {
                this.idMoratoire = value;
            }

            /**
             * Gets the value of the payments property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the payments property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getPayments().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Payments }
             */
            public List<Payments> getPayments() {
                if (payments == null) {
                    payments = new ArrayList<Payments>();
                }
                return this.payments;
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
             *         &lt;element name="datePayment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="isPaymentDone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "datePayment",
                "montant",
                "isPaymentDone"
            })
            public static class Payments {

                @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
                protected String datePayment;
                @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
                protected String montant;
                @XmlElement(namespace = "http://oracle.com/CM_GET_INFORMATION_MORATOIRE.xsd")
                protected String isPaymentDone;

                /**
                 * Obtient la valeur de la propriété datePayment.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getDatePayment() {
                    return datePayment;
                }

                /**
                 * Définit la valeur de la propriété datePayment.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setDatePayment(String value) {
                    this.datePayment = value;
                }

                /**
                 * Obtient la valeur de la propriété montant.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getMontant() {
                    return montant;
                }

                /**
                 * Définit la valeur de la propriété montant.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setMontant(String value) {
                    this.montant = value;
                }

                /**
                 * Obtient la valeur de la propriété isPaymentDone.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getIsPaymentDone() {
                    return isPaymentDone;
                }

                /**
                 * Définit la valeur de la propriété isPaymentDone.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setIsPaymentDone(String value) {
                    this.isPaymentDone = value;
                }

            }

        }

    }

}
