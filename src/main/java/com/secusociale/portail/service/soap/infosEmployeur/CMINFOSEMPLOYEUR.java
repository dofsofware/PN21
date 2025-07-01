
package com.secusociale.portail.service.soap.infosEmployeur;

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
 *         &lt;element name="input" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="processFlowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Output" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="activitePrincipale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adresseEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tauxRG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tauxRC" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tauxPF" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="ninea" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="dateEmbauchePremierSalarie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="agenceCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="agenceIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adresseAgenceCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="telephoneAgCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="localiteAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adresseAgenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="telephoneAgIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CM_INFOS_EMPLOYEUR")
public class CMINFOSEMPLOYEUR {

    protected Input input;
    @XmlElement(name = "Output")
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
     *         &lt;element name="processFlowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "processFlowId"
    })
    public static class Input {

        protected String processFlowId;

        /**
         * Obtient la valeur de la propriété processFlowId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProcessFlowId() {
            return processFlowId;
        }

        /**
         * Définit la valeur de la propriété processFlowId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProcessFlowId(String value) {
            this.processFlowId = value;
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
     *         &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="activitePrincipale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adresseEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tauxRG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tauxRC" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tauxPF" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="ninea" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="dateEmbauchePremierSalarie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="agenceCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="agenceIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adresseAgenceCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="telephoneAgCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="localiteAgence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adresseAgenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="telephoneAgIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "raisonSociale",
        "activitePrincipale",
        "adresseEmployeur",
        "tauxAt",
        "tauxRG",
        "tauxRC",
        "numeroEmployeur",
        "tauxPF",
        "ninea",
        "dateEmbauchePremierSalarie",
        "agenceCSS",
        "agenceIPRES",
        "adresseAgenceCSS",
        "telephoneAgCSS",
        "localiteAgence",
        "adresseAgenceIpres",
        "telephoneAgIpres"
    })
    public static class Output {

        protected String raisonSociale;
        protected String activitePrincipale;
        protected String adresseEmployeur;
        @XmlElementRef(name = "tauxAt", namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxAt;
        @XmlElementRef(name = "tauxRG", namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxRG;
        @XmlElementRef(name = "tauxRC", namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxRC;
        protected String numeroEmployeur;
        @XmlElementRef(name = "tauxPF", namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tauxPF;
        @XmlElementRef(name = "ninea", namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> ninea;
        protected String dateEmbauchePremierSalarie;
        protected String agenceCSS;
        protected String agenceIPRES;
        protected String adresseAgenceCSS;
        protected String telephoneAgCSS;
        protected String localiteAgence;
        protected String adresseAgenceIpres;
        protected String telephoneAgIpres;

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
         * Obtient la valeur de la propriété activitePrincipale.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getActivitePrincipale() {
            return activitePrincipale;
        }

        /**
         * Définit la valeur de la propriété activitePrincipale.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setActivitePrincipale(String value) {
            this.activitePrincipale = value;
        }

        /**
         * Obtient la valeur de la propriété adresseEmployeur.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAdresseEmployeur() {
            return adresseEmployeur;
        }

        /**
         * Définit la valeur de la propriété adresseEmployeur.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAdresseEmployeur(String value) {
            this.adresseEmployeur = value;
        }

        /**
         * Obtient la valeur de la propriété tauxAt.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTauxAt() {
            return tauxAt;
        }

        /**
         * Définit la valeur de la propriété tauxAt.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTauxAt(JAXBElement<BigDecimal> value) {
            this.tauxAt = value;
        }

        /**
         * Obtient la valeur de la propriété tauxRG.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTauxRG() {
            return tauxRG;
        }

        /**
         * Définit la valeur de la propriété tauxRG.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTauxRG(JAXBElement<BigDecimal> value) {
            this.tauxRG = value;
        }

        /**
         * Obtient la valeur de la propriété tauxRC.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTauxRC() {
            return tauxRC;
        }

        /**
         * Définit la valeur de la propriété tauxRC.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTauxRC(JAXBElement<BigDecimal> value) {
            this.tauxRC = value;
        }

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
         * Obtient la valeur de la propriété tauxPF.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTauxPF() {
            return tauxPF;
        }

        /**
         * Définit la valeur de la propriété tauxPF.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTauxPF(JAXBElement<BigDecimal> value) {
            this.tauxPF = value;
        }

        /**
         * Obtient la valeur de la propriété ninea.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getNinea() {
            return ninea;
        }

        /**
         * Définit la valeur de la propriété ninea.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setNinea(JAXBElement<BigDecimal> value) {
            this.ninea = value;
        }

        /**
         * Obtient la valeur de la propriété dateEmbauchePremierSalarie.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDateEmbauchePremierSalarie() {
            return dateEmbauchePremierSalarie;
        }

        /**
         * Définit la valeur de la propriété dateEmbauchePremierSalarie.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDateEmbauchePremierSalarie(String value) {
            this.dateEmbauchePremierSalarie = value;
        }

        /**
         * Obtient la valeur de la propriété agenceCSS.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAgenceCSS() {
            return agenceCSS;
        }

        /**
         * Définit la valeur de la propriété agenceCSS.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAgenceCSS(String value) {
            this.agenceCSS = value;
        }

        /**
         * Obtient la valeur de la propriété agenceIPRES.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAgenceIPRES() {
            return agenceIPRES;
        }

        /**
         * Définit la valeur de la propriété agenceIPRES.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAgenceIPRES(String value) {
            this.agenceIPRES = value;
        }

        /**
         * Obtient la valeur de la propriété adresseAgenceCSS.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAdresseAgenceCSS() {
            return adresseAgenceCSS;
        }

        /**
         * Définit la valeur de la propriété adresseAgenceCSS.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAdresseAgenceCSS(String value) {
            this.adresseAgenceCSS = value;
        }

        /**
         * Obtient la valeur de la propriété telephoneAgCSS.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTelephoneAgCSS() {
            return telephoneAgCSS;
        }

        /**
         * Définit la valeur de la propriété telephoneAgCSS.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTelephoneAgCSS(String value) {
            this.telephoneAgCSS = value;
        }

        /**
         * Obtient la valeur de la propriété localiteAgence.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getLocaliteAgence() {
            return localiteAgence;
        }

        /**
         * Définit la valeur de la propriété localiteAgence.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setLocaliteAgence(String value) {
            this.localiteAgence = value;
        }

        /**
         * Obtient la valeur de la propriété adresseAgenceIpres.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAdresseAgenceIpres() {
            return adresseAgenceIpres;
        }

        /**
         * Définit la valeur de la propriété adresseAgenceIpres.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAdresseAgenceIpres(String value) {
            this.adresseAgenceIpres = value;
        }

        /**
         * Obtient la valeur de la propriété telephoneAgIpres.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTelephoneAgIpres() {
            return telephoneAgIpres;
        }

        /**
         * Définit la valeur de la propriété telephoneAgIpres.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTelephoneAgIpres(String value) {
            this.telephoneAgIpres = value;
        }

    }

}
