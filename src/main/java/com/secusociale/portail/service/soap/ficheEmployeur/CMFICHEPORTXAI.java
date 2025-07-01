
package com.secusociale.portail.service.soap.ficheEmployeur;

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
 *                   &lt;element name="identifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="changeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="typeOfContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="demandeEcrite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nouveauStatut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="commentaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="procFlowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CM-FICHEPORT_XAI")
public class CMFICHEPORTXAI {

    @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
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


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "identifiant",
        "changeStatus",
        "typeOfContact",
        "demandeEcrite",
        "nouveauStatut",
        "commentaire"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String identifiant;
        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String changeStatus;
        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String typeOfContact;
        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String demandeEcrite;
        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String nouveauStatut;
        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String commentaire;

        /**
         * Obtient la valeur de la propriété identifiant.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdentifiant() {
            return identifiant;
        }

        /**
         * Définit la valeur de la propriété identifiant.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdentifiant(String value) {
            this.identifiant = value;
        }

        /**
         * Obtient la valeur de la propriété changeStatus.
         *
         * @return possible object is
         * {@link String }
         */
        public String getChangeStatus() {
            return changeStatus;
        }

        /**
         * Définit la valeur de la propriété changeStatus.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChangeStatus(String value) {
            this.changeStatus = value;
        }

        /**
         * Obtient la valeur de la propriété typeOfContact.
         *
         * @return possible object is
         * {@link String }
         */
        public String getTypeOfContact() {
            return typeOfContact;
        }

        /**
         * Définit la valeur de la propriété typeOfContact.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTypeOfContact(String value) {
            this.typeOfContact = value;
        }

        /**
         * Obtient la valeur de la propriété demandeEcrite.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDemandeEcrite() {
            return demandeEcrite;
        }

        /**
         * Définit la valeur de la propriété demandeEcrite.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDemandeEcrite(String value) {
            this.demandeEcrite = value;
        }

        /**
         * Obtient la valeur de la propriété nouveauStatut.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNouveauStatut() {
            return nouveauStatut;
        }

        /**
         * Définit la valeur de la propriété nouveauStatut.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNouveauStatut(String value) {
            this.nouveauStatut = value;
        }

        /**
         * Obtient la valeur de la propriété commentaire.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCommentaire() {
            return commentaire;
        }

        /**
         * Définit la valeur de la propriété commentaire.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCommentaire(String value) {
            this.commentaire = value;
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
     *         &lt;element name="procFlowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "procFlowId"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CM-FICHEPORT_XAI.xsd")
        protected String procFlowId;

        /**
         * Obtient la valeur de la propriété procFlowId.
         *
         * @return possible object is
         * {@link String }
         */
        public String getProcFlowId() {
            return procFlowId;
        }

        /**
         * Définit la valeur de la propriété procFlowId.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setProcFlowId(String value) {
            this.procFlowId = value;
        }

    }

}
