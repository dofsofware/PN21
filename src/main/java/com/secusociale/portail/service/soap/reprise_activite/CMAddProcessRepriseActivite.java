
package com.secusociale.portail.service.soap.reprise_activite;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *                   &lt;element name="informationReprise" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nombreDmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="nombreContrat" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="typeDemande" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="Involontaire"/>
 *                                   &lt;enumeration value="Volontaire"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
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
 *                             &lt;element name="dmt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="formDeclarationReprise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="photocopiePiece" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="autreDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="nomAutreDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CM-AddProcessRepriseActivite")
public class CMAddProcessRepriseActivite {

    protected CMAddProcessRepriseActivite.Input input;
    protected CMAddProcessRepriseActivite.Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriété input.
     *
     * @return
     *     possible object is
     *     {@link CMAddProcessRepriseActivite.Input }
     *
     */
    public CMAddProcessRepriseActivite.Input getInput() {
        return input;
    }

    /**
     * Définit la valeur de la propriété input.
     *
     * @param value
     *     allowed object is
     *     {@link CMAddProcessRepriseActivite.Input }
     *
     */
    public void setInput(CMAddProcessRepriseActivite.Input value) {
        this.input = value;
    }

    /**
     * Obtient la valeur de la propriété output.
     *
     * @return
     *     possible object is
     *     {@link CMAddProcessRepriseActivite.Output }
     *
     */
    public CMAddProcessRepriseActivite.Output getOutput() {
        return output;
    }

    /**
     * Définit la valeur de la propriété output.
     *
     * @param value
     *     allowed object is
     *     {@link CMAddProcessRepriseActivite.Output }
     *
     */
    public void setOutput(CMAddProcessRepriseActivite.Output value) {
        this.output = value;
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
     *         &lt;element name="informationReprise" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nombreDmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="nombreContrat" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="typeDemande" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="Involontaire"/>
     *                         &lt;enumeration value="Volontaire"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
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
     *                   &lt;element name="dmt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="formDeclarationReprise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="photocopiePiece" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="autreDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="nomAutreDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "informationEmployer",
        "informationReprise",
        "documents"
    })
    public static class Input {

        protected CMAddProcessRepriseActivite.Input.InformationEmployer informationEmployer;
        protected CMAddProcessRepriseActivite.Input.InformationReprise informationReprise;
        protected CMAddProcessRepriseActivite.Input.Documents documents;

        /**
         * Obtient la valeur de la propriété informationEmployer.
         *
         * @return
         *     possible object is
         *     {@link CMAddProcessRepriseActivite.Input.InformationEmployer }
         *
         */
        public CMAddProcessRepriseActivite.Input.InformationEmployer getInformationEmployer() {
            return informationEmployer;
        }

        /**
         * Définit la valeur de la propriété informationEmployer.
         *
         * @param value
         *     allowed object is
         *     {@link CMAddProcessRepriseActivite.Input.InformationEmployer }
         *
         */
        public void setInformationEmployer(CMAddProcessRepriseActivite.Input.InformationEmployer value) {
            this.informationEmployer = value;
        }

        /**
         * Obtient la valeur de la propriété informationReprise.
         *
         * @return
         *     possible object is
         *     {@link CMAddProcessRepriseActivite.Input.InformationReprise }
         *
         */
        public CMAddProcessRepriseActivite.Input.InformationReprise getInformationReprise() {
            return informationReprise;
        }

        /**
         * Définit la valeur de la propriété informationReprise.
         *
         * @param value
         *     allowed object is
         *     {@link CMAddProcessRepriseActivite.Input.InformationReprise }
         *
         */
        public void setInformationReprise(CMAddProcessRepriseActivite.Input.InformationReprise value) {
            this.informationReprise = value;
        }

        /**
         * Obtient la valeur de la propriété documents.
         *
         * @return
         *     possible object is
         *     {@link CMAddProcessRepriseActivite.Input.Documents }
         *
         */
        public CMAddProcessRepriseActivite.Input.Documents getDocuments() {
            return documents;
        }

        /**
         * Définit la valeur de la propriété documents.
         *
         * @param value
         *     allowed object is
         *     {@link CMAddProcessRepriseActivite.Input.Documents }
         *
         */
        public void setDocuments(CMAddProcessRepriseActivite.Input.Documents value) {
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
         *         &lt;element name="dmt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="formDeclarationReprise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="photocopiePiece" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="autreDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="nomAutreDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "dmt",
            "formDeclarationReprise",
            "photocopiePiece",
            "autreDocument",
            "nomAutreDocument"
        })
        public static class Documents {

            @XmlElementRef(name = "dmt", namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> dmt;
            @XmlElementRef(name = "formDeclarationReprise", namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> formDeclarationReprise;
            @XmlElementRef(name = "photocopiePiece", namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> photocopiePiece;
            @XmlElementRef(name = "autreDocument", namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> autreDocument;
            protected String nomAutreDocument;

            /**
             * Obtient la valeur de la propriété dmt.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getDmt() {
                return dmt;
            }

            /**
             * Définit la valeur de la propriété dmt.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setDmt(JAXBElement<Boolean> value) {
                this.dmt = value;
            }

            /**
             * Obtient la valeur de la propriété formDeclarationReprise.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getFormDeclarationReprise() {
                return formDeclarationReprise;
            }

            /**
             * Définit la valeur de la propriété formDeclarationReprise.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setFormDeclarationReprise(JAXBElement<Boolean> value) {
                this.formDeclarationReprise = value;
            }

            /**
             * Obtient la valeur de la propriété photocopiePiece.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getPhotocopiePiece() {
                return photocopiePiece;
            }

            /**
             * Définit la valeur de la propriété photocopiePiece.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setPhotocopiePiece(JAXBElement<Boolean> value) {
                this.photocopiePiece = value;
            }

            /**
             * Obtient la valeur de la propriété autreDocument.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getAutreDocument() {
                return autreDocument;
            }

            /**
             * Définit la valeur de la propriété autreDocument.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setAutreDocument(JAXBElement<Boolean> value) {
                this.autreDocument = value;
            }

            /**
             * Obtient la valeur de la propriété nomAutreDocument.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNomAutreDocument() {
                return nomAutreDocument;
            }

            /**
             * Définit la valeur de la propriété nomAutreDocument.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNomAutreDocument(String value) {
                this.nomAutreDocument = value;
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
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "idEmployer"
        })
        public static class InformationEmployer {

            @XmlElement(required = true)
            protected String idEmployer;

            /**
             * Obtient la valeur de la propriété idEmployer.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIdEmployer() {
                return idEmployer;
            }

            /**
             * Définit la valeur de la propriété idEmployer.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIdEmployer(String value) {
                this.idEmployer = value;
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
         *         &lt;element name="nombreDmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="nombreContrat" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="typeDemande" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="Involontaire"/>
         *               &lt;enumeration value="Volontaire"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
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
            "nombreDmt",
            "nombreContrat",
            "typeDemande"
        })
        public static class InformationReprise {

            @XmlElementRef(name = "nombreDmt", namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> nombreDmt;
            @XmlElementRef(name = "nombreContrat", namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> nombreContrat;
            protected String typeDemande;

            /**
             * Obtient la valeur de la propriété nombreDmt.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getNombreDmt() {
                return nombreDmt;
            }

            /**
             * Définit la valeur de la propriété nombreDmt.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setNombreDmt(JAXBElement<BigDecimal> value) {
                this.nombreDmt = value;
            }

            /**
             * Obtient la valeur de la propriété nombreContrat.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getNombreContrat() {
                return nombreContrat;
            }

            /**
             * Définit la valeur de la propriété nombreContrat.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setNombreContrat(JAXBElement<BigDecimal> value) {
                this.nombreContrat = value;
            }

            /**
             * Obtient la valeur de la propriété typeDemande.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeDemande() {
                return typeDemande;
            }

            /**
             * Définit la valeur de la propriété typeDemande.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeDemande(String value) {
                this.typeDemande = value;
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idDossier"
    })
    public static class Output {

        protected String idDossier;

        /**
         * Obtient la valeur de la propriété idDossier.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIdDossier() {
            return idDossier;
        }

        /**
         * Définit la valeur de la propriété idDossier.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIdDossier(String value) {
            this.idDossier = value;
        }

    }

}
