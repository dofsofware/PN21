
package com.secusociale.portail.service.soap.remise_gracieuse_add;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *                   &lt;element name="informationEmployeur" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="informationDemande" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="dateDebutPeriode" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="dateFinPeriode" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="motifDemande" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="documents" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="demandeEcrite" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="output" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idDossier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CmAddDemandeRemiseGracieuse", namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
public class CmAddDemandeRemiseGracieuse {

    @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
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
     *         &lt;element name="informationEmployeur" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="informationDemande" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="dateDebutPeriode" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="dateFinPeriode" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="motifDemande" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="documents" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="demandeEcrite" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "informationEmployeur",
        "informationDemande",
        "documents"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
        protected InformationEmployeur informationEmployeur;
        @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
        protected InformationDemande informationDemande;
        @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
        protected Documents documents;

        /**
         * Obtient la valeur de la propriété informationEmployeur.
         *
         * @return possible object is
         * {@link InformationEmployeur }
         */
        public InformationEmployeur getInformationEmployeur() {
            return informationEmployeur;
        }

        /**
         * Définit la valeur de la propriété informationEmployeur.
         *
         * @param value allowed object is
         *              {@link InformationEmployeur }
         */
        public void setInformationEmployeur(InformationEmployeur value) {
            this.informationEmployeur = value;
        }

        /**
         * Obtient la valeur de la propriété informationDemande.
         *
         * @return possible object is
         * {@link InformationDemande }
         */
        public InformationDemande getInformationDemande() {
            return informationDemande;
        }

        /**
         * Définit la valeur de la propriété informationDemande.
         *
         * @param value allowed object is
         *              {@link InformationDemande }
         */
        public void setInformationDemande(InformationDemande value) {
            this.informationDemande = value;
        }

        /**
         * Obtient la valeur de la propriété documents.
         *
         * @return possible object is
         * {@link Documents }
         */
        public Documents getDocuments() {
            return documents;
        }

        /**
         * Définit la valeur de la propriété documents.
         *
         * @param value allowed object is
         *              {@link Documents }
         */
        public void setDocuments(Documents value) {
            this.documents = value;
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
         *         &lt;element name="demandeEcrite" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "demandeEcrite"
        })
        public static class Documents {

            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd", required = true)
            protected String demandeEcrite;

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
         *         &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="dateDebutPeriode" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="dateFinPeriode" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="motifDemande" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "institution",
            "dateDebutPeriode",
            "dateFinPeriode",
            "motifDemande",
            "montant"
        })
        public static class InformationDemande {

            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd", required = true)
            protected String institution;
            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd", required = true, nillable = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dateDebutPeriode;
            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd", required = true, nillable = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dateFinPeriode;
            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
            protected String motifDemande;
            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
            protected String montant;

            /**
             * Obtient la valeur de la propriété institution.
             *
             * @return possible object is
             * {@link String }
             */
            public String getInstitution() {
                return institution;
            }

            /**
             * Définit la valeur de la propriété institution.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setInstitution(String value) {
                this.institution = value;
            }

            /**
             * Obtient la valeur de la propriété dateDebutPeriode.
             *
             * @return possible object is
             * {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getDateDebutPeriode() {
                return dateDebutPeriode;
            }

            /**
             * Définit la valeur de la propriété dateDebutPeriode.
             *
             * @param value allowed object is
             *              {@link XMLGregorianCalendar }
             */
            public void setDateDebutPeriode(XMLGregorianCalendar value) {
                this.dateDebutPeriode = value;
            }

            /**
             * Obtient la valeur de la propriété dateFinPeriode.
             *
             * @return possible object is
             * {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getDateFinPeriode() {
                return dateFinPeriode;
            }

            /**
             * Définit la valeur de la propriété dateFinPeriode.
             *
             * @param value allowed object is
             *              {@link XMLGregorianCalendar }
             */
            public void setDateFinPeriode(XMLGregorianCalendar value) {
                this.dateFinPeriode = value;
            }

            /**
             * Obtient la valeur de la propriété motifDemande.
             *
             * @return possible object is
             * {@link String }
             */
            public String getMotifDemande() {
                return motifDemande;
            }

            /**
             * Définit la valeur de la propriété motifDemande.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setMotifDemande(String value) {
                this.motifDemande = value;
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
         *         &lt;element name="numeroEmployeur" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "numeroEmployeur"
        })
        public static class InformationEmployeur {

            @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd", required = true)
            protected String numeroEmployeur;

            /**
             * Obtient la valeur de la propriété numeroEmployeur.
             *
             * @return possible object is
             * {@link String }
             */
            public String getNumeroEmployeur() {
                return numeroEmployeur;
            }

            /**
             * Définit la valeur de la propriété numeroEmployeur.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setNumeroEmployeur(String value) {
                this.numeroEmployeur = value;
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
     *         &lt;element name="idDossier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idDossier"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CmAddDemandeRemiseGracieuse.xsd")
        protected String idDossier;

        /**
         * Obtient la valeur de la propriété idDossier.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdDossier() {
            return idDossier;
        }

        /**
         * Définit la valeur de la propriété idDossier.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdDossier(String value) {
            this.idDossier = value;
        }

    }

}
