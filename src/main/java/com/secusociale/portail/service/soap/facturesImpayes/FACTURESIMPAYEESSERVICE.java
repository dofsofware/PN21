
package com.secusociale.portail.service.soap.facturesImpayes;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CM_GET_FACTURE10")
public class FACTURESIMPAYEESSERVICE {

    @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
    protected List<Output> output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriete input.
     *
     * @return possible object is
     * {@link Input }
     */
    public Input getInput() {
        return input;
    }

    /**
     * Definit la valeur de la propriete input.
     *
     * @param value allowed object is
     *              {@link Input }
     */
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

    public void setOutput(List<Output> output) {
        this.output = output;
    }

    /**
     * Obtient la valeur de la propriete dateTimeTagFormat.
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
     * Definit la valeur de la propriete dateTimeTagFormat.
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
     * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
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

        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String personId;

        /**
         * Obtient la valeur de la propriete personId.
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
         * Definit la valeur de la propriete personId.
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
     * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="numeroFacture" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="montantTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "numeroFacture",
        "typeFacture",
        "dateEcheance",
        "montantPrincipal",
        "majorations",
        "montantTotal",
        "dateDebut",
        "dateFin",
        "dette",
        "montantPaye",
        "penalite"
    })
    public static class Output {
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String numeroFacture;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String typeFacture;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String dateEcheance;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String montantPrincipal;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String majorations;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String montantTotal;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String dateDebut;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String dateFin;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String dette;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String montantPaye;
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE10.xsd")
        protected String penalite;

        /**
         * Obtient la valeur de la propriete numeroFacture.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNumeroFacture() {
            return numeroFacture;
        }

        /**
         * Definit la valeur de la propriete numeroFacture.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroFacture(String value) {
            this.numeroFacture = value;
        }

        /**
         * Obtient la valeur de la propriete dateDebut.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDateDebut() {
            return dateDebut;
        }

        /**
         * Definit la valeur de la propriete dateDebut.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDateDebut(String value) {
            this.dateDebut = value;
        }

        /**
         * Obtient la valeur de la propriete dateFin.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDateFin() {
            return dateFin;
        }

        /**
         * Definit la valeur de la propriete dateFin.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDateFin(String value) {
            this.dateFin = value;
        }

        /**
         * Obtient la valeur de la propriete montantPrincipal.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMontantPrincipal() {
            return montantPrincipal;
        }

        /**
         * Definit la valeur de la propriete montantPrincipal.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMontantPrincipal(String value) {
            this.montantPrincipal = value;
        }

        /**
         * Obtient la valeur de la propriete montantTotal.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMontantTotal() {
            return montantTotal;
        }

        /**
         * Definit la valeur de la propriete montantTotal.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMontantTotal(String value) {
            this.montantTotal = value;
        }

        public String getTypeFacture() {
            return typeFacture;
        }

        public void setTypeFacture(String typeFacture) {
            this.typeFacture = typeFacture;
        }

        public String getDateEcheance() {
            return dateEcheance;
        }

        public void setDateEcheance(String dateEcheance) {
            this.dateEcheance = dateEcheance;
        }

        public String getMajorations() {
            return majorations;
        }

        public void setMajorations(String majorations) {
            this.majorations = majorations;
        }

        public String getDette() {
            return dette;
        }

        public void setDette(String dette) {
            this.dette = dette;
        }

        public String getMontantPaye() {
            return montantPaye;
        }

        public void setMontantPaye(String montantPaye) {
            this.montantPaye = montantPaye;
        }

        public String getPenalite() {
            return penalite;
        }

        public void setPenalite(String penalite) {
            this.penalite = penalite;
        }
    }

}
