
package com.secusociale.portail.service.soap.cessation_suspension;

import javax.xml.bind.JAXBElement;
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
 *                   &lt;element name="informationEmployer" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *                             &lt;element name="typeProcess" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="STOP"/>
 *                                   &lt;enumeration value="SUS"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="typeDemande" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="Involontaire"/>
 *                                   &lt;enumeration value="Volontaire"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="optionAjouterAutreDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="nomDocumentOptional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                             &lt;element name="demandeEmployer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="declartionCessationActivite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="dmtDeSortie" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="decisionJudiciare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="decisionAdministrative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlRootElement(name = "CM-ADD_CESSATION_OR_SUSPENSION", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
public class CMADDCESSATIONORSUSPENSION {

    @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
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
     *         &lt;element name="informationEmployer" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
     *                   &lt;element name="typeProcess" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="STOP"/>
     *                         &lt;enumeration value="SUS"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="typeDemande" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="Involontaire"/>
     *                         &lt;enumeration value="Volontaire"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="optionAjouterAutreDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="nomDocumentOptional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *                   &lt;element name="demandeEmployer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="declartionCessationActivite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="dmtDeSortie" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="decisionJudiciare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="decisionAdministrative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "informationEmployer",
        "informationDemande",
        "documents"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
        protected InformationEmployer informationEmployer;
        @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
        protected InformationDemande informationDemande;
        @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
        protected Documents documents;

        /**
         * Obtient la valeur de la propriété informationEmployer.
         *
         * @return possible object is
         * {@link InformationEmployer }
         */
        public InformationEmployer getInformationEmployer() {
            return informationEmployer;
        }

        /**
         * Définit la valeur de la propriété informationEmployer.
         *
         * @param value allowed object is
         *              {@link InformationEmployer }
         */
        public void setInformationEmployer(InformationEmployer value) {
            this.informationEmployer = value;
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
         *         &lt;element name="demandeEmployer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="declartionCessationActivite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="dmtDeSortie" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="decisionJudiciare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="decisionAdministrative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "demandeEmployer",
            "declartionCessationActivite",
            "dmtDeSortie",
            "decisionJudiciare",
            "decisionAdministrative"
        })
        public static class Documents {

            @XmlElementRef(name = "demandeEmployer", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> demandeEmployer;
            @XmlElementRef(name = "declartionCessationActivite", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> declartionCessationActivite;
            @XmlElementRef(name = "dmtDeSortie", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> dmtDeSortie;
            @XmlElementRef(name = "decisionJudiciare", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> decisionJudiciare;
            @XmlElementRef(name = "decisionAdministrative", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> decisionAdministrative;

            /**
             * Obtient la valeur de la propriété demandeEmployer.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public JAXBElement<Boolean> getDemandeEmployer() {
                return demandeEmployer;
            }

            /**
             * Définit la valeur de la propriété demandeEmployer.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public void setDemandeEmployer(JAXBElement<Boolean> value) {
                this.demandeEmployer = value;
            }

            /**
             * Obtient la valeur de la propriété declartionCessationActivite.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public JAXBElement<Boolean> getDeclartionCessationActivite() {
                return declartionCessationActivite;
            }

            /**
             * Définit la valeur de la propriété declartionCessationActivite.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public void setDeclartionCessationActivite(JAXBElement<Boolean> value) {
                this.declartionCessationActivite = value;
            }

            /**
             * Obtient la valeur de la propriété dmtDeSortie.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public JAXBElement<Boolean> getDmtDeSortie() {
                return dmtDeSortie;
            }

            /**
             * Définit la valeur de la propriété dmtDeSortie.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public void setDmtDeSortie(JAXBElement<Boolean> value) {
                this.dmtDeSortie = value;
            }

            /**
             * Obtient la valeur de la propriété decisionJudiciare.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public JAXBElement<Boolean> getDecisionJudiciare() {
                return decisionJudiciare;
            }

            /**
             * Définit la valeur de la propriété decisionJudiciare.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public void setDecisionJudiciare(JAXBElement<Boolean> value) {
                this.decisionJudiciare = value;
            }

            /**
             * Obtient la valeur de la propriété decisionAdministrative.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public JAXBElement<Boolean> getDecisionAdministrative() {
                return decisionAdministrative;
            }

            /**
             * Définit la valeur de la propriété decisionAdministrative.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public void setDecisionAdministrative(JAXBElement<Boolean> value) {
                this.decisionAdministrative = value;
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
         *         &lt;element name="typeProcess" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="STOP"/>
         *               &lt;enumeration value="SUS"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="typeDemande" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="Involontaire"/>
         *               &lt;enumeration value="Volontaire"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="optionAjouterAutreDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="nomDocumentOptional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "typeProcess",
            "typeDemande",
            "dateDebut",
            "dateFin",
            "optionAjouterAutreDocument",
            "nomDocumentOptional"
        })
        public static class InformationDemande {

            @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
            protected String typeProcess;
            @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
            protected String typeDemande;
            @XmlElementRef(name = "dateDebut", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateDebut;
            @XmlElementRef(name = "dateFin", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateFin;
            @XmlElementRef(name = "optionAjouterAutreDocument", namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> optionAjouterAutreDocument;
            @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
            protected String nomDocumentOptional;

            /**
             * Obtient la valeur de la propriété typeProcess.
             *
             * @return possible object is
             * {@link String }
             */
            public String getTypeProcess() {
                return typeProcess;
            }

            /**
             * Définit la valeur de la propriété typeProcess.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setTypeProcess(String value) {
                this.typeProcess = value;
            }

            /**
             * Obtient la valeur de la propriété typeDemande.
             *
             * @return possible object is
             * {@link String }
             */
            public String getTypeDemande() {
                return typeDemande;
            }

            /**
             * Définit la valeur de la propriété typeDemande.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setTypeDemande(String value) {
                this.typeDemande = value;
            }

            /**
             * Obtient la valeur de la propriété dateDebut.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             */
            public JAXBElement<XMLGregorianCalendar> getDateDebut() {
                return dateDebut;
            }

            /**
             * Définit la valeur de la propriété dateDebut.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             */
            public void setDateDebut(JAXBElement<XMLGregorianCalendar> value) {
                this.dateDebut = value;
            }

            /**
             * Obtient la valeur de la propriété dateFin.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             */
            public JAXBElement<XMLGregorianCalendar> getDateFin() {
                return dateFin;
            }

            /**
             * Définit la valeur de la propriété dateFin.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             */
            public void setDateFin(JAXBElement<XMLGregorianCalendar> value) {
                this.dateFin = value;
            }

            /**
             * Obtient la valeur de la propriété optionAjouterAutreDocument.
             *
             * @return possible object is
             * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public JAXBElement<Boolean> getOptionAjouterAutreDocument() {
                return optionAjouterAutreDocument;
            }

            /**
             * Définit la valeur de la propriété optionAjouterAutreDocument.
             *
             * @param value allowed object is
             *              {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             */
            public void setOptionAjouterAutreDocument(JAXBElement<Boolean> value) {
                this.optionAjouterAutreDocument = value;
            }

            /**
             * Obtient la valeur de la propriété nomDocumentOptional.
             *
             * @return possible object is
             * {@link String }
             */
            public String getNomDocumentOptional() {
                return nomDocumentOptional;
            }

            /**
             * Définit la valeur de la propriété nomDocumentOptional.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setNomDocumentOptional(String value) {
                this.nomDocumentOptional = value;
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
         *         &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "idEmployer"
        })
        public static class InformationEmployer {

            @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", required = true)
            protected String idEmployer;

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

        @XmlElement(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd")
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
