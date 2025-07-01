
package com.secusociale.portail.service.soap.declarations_manquantes;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
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
 *                   &lt;element name="ancienNumeroIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="declarationsManquantes" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="zone" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="12"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="idEmployer" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="4000"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="ancienNumeroIpres" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="4000"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="rowCount" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="99999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="results" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ancienNumeroIpres" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="raisonSociale" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="zone" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="adresse" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="exercice" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="regime" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="telephone" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="effectif" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="codeAgence" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="versementTrimestre1" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="versementTrimestre2" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="versementTrimestre3" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="versementTrimestre4" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="4000"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
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
 *                   &lt;element name="informationCss" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numeroEmployerCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateImmatCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nomRepresCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="telephoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CM_INFORMATION_MANQUE_DNS_IPRES")
public class CMINFORMATIONMANQUEDNSIPRES {

    protected Input input;
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
     *         &lt;element name="ancienNumeroIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="idEmployer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ancienNumeroIpres",
        "idEmployer"
    })
    public static class Input {

        protected String ancienNumeroIpres;
        protected String idEmployer;

        /**
         * Obtient la valeur de la propriété ancienNumeroIpres.
         *
         * @return possible object is
         * {@link String }
         */
        public String getAncienNumeroIpres() {
            return ancienNumeroIpres;
        }

        /**
         * Définit la valeur de la propriété ancienNumeroIpres.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setAncienNumeroIpres(String value) {
            this.ancienNumeroIpres = value;
        }

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
     *         &lt;element name="declarationsManquantes" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="zone" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="12"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="idEmployer" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="4000"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="ancienNumeroIpres" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="4000"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="rowCount" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="99999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="results" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ancienNumeroIpres" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="raisonSociale" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="zone" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="adresse" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="exercice" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="regime" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="telephone" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="effectif" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="codeAgence" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="versementTrimestre1" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="versementTrimestre2" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="versementTrimestre3" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="versementTrimestre4" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="4000"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
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
     *         &lt;element name="informationCss" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numeroEmployerCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateImmatCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nomRepresCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="telephoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "declarationsManquantes",
        "informationCss"
    })
    public static class Output {

        protected DeclarationsManquantes declarationsManquantes;
        protected InformationCss informationCss;

        /**
         * Obtient la valeur de la propriété declarationsManquantes.
         *
         * @return possible object is
         * {@link DeclarationsManquantes }
         */
        public DeclarationsManquantes getDeclarationsManquantes() {
            return declarationsManquantes;
        }

        /**
         * Définit la valeur de la propriété declarationsManquantes.
         *
         * @param value allowed object is
         *              {@link DeclarationsManquantes }
         */
        public void setDeclarationsManquantes(DeclarationsManquantes value) {
            this.declarationsManquantes = value;
        }

        /**
         * Obtient la valeur de la propriété informationCss.
         *
         * @return possible object is
         * {@link InformationCss }
         */
        public InformationCss getInformationCss() {
            return informationCss;
        }

        /**
         * Définit la valeur de la propriété informationCss.
         *
         * @param value allowed object is
         *              {@link InformationCss }
         */
        public void setInformationCss(InformationCss value) {
            this.informationCss = value;
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
         *         &lt;element name="zone" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="12"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="idEmployer" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="4000"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="ancienNumeroIpres" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="4000"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="rowCount" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="99999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="results" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ancienNumeroIpres" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="raisonSociale" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="zone" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="adresse" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="exercice" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="regime" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="telephone" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="effectif" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="codeAgence" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="versementTrimestre1" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="versementTrimestre2" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="versementTrimestre3" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="versementTrimestre4" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="4000"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
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
            "zone",
            "idEmployer",
            "ancienNumeroIpres",
            "rowCount",
            "results"
        })
        public static class DeclarationsManquantes {

            @XmlElement(defaultValue = "CM-DNS-MQTES")
            protected String zone;
            protected String idEmployer;
            protected String ancienNumeroIpres;
            protected BigDecimal rowCount;
            protected List<Results> results;

            /**
             * Obtient la valeur de la propriété zone.
             *
             * @return possible object is
             * {@link String }
             */
            public String getZone() {
                return zone;
            }

            /**
             * Définit la valeur de la propriété zone.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setZone(String value) {
                this.zone = value;
            }

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
             * Obtient la valeur de la propriété ancienNumeroIpres.
             *
             * @return possible object is
             * {@link String }
             */
            public String getAncienNumeroIpres() {
                return ancienNumeroIpres;
            }

            /**
             * Définit la valeur de la propriété ancienNumeroIpres.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setAncienNumeroIpres(String value) {
                this.ancienNumeroIpres = value;
            }

            /**
             * Obtient la valeur de la propriété rowCount.
             *
             * @return possible object is
             * {@link BigDecimal }
             */
            public BigDecimal getRowCount() {
                return rowCount;
            }

            /**
             * Définit la valeur de la propriété rowCount.
             *
             * @param value allowed object is
             *              {@link BigDecimal }
             */
            public void setRowCount(BigDecimal value) {
                this.rowCount = value;
            }

            /**
             * Gets the value of the results property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the results property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getResults().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Results }
             */
            public List<Results> getResults() {
                if (results == null) {
                    results = new ArrayList<Results>();
                }
                return this.results;
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
             *         &lt;element name="ancienNumeroIpres" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="raisonSociale" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="zone" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="adresse" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="exercice" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="regime" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="telephone" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="effectif" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="codeAgence" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="versementTrimestre1" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="versementTrimestre2" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="versementTrimestre3" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="versementTrimestre4" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="4000"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "ancienNumeroIpres",
                "raisonSociale",
                "zone",
                "adresse",
                "exercice",
                "regime",
                "telephone",
                "effectif",
                "codeAgence",
                "versementTrimestre1",
                "versementTrimestre2",
                "versementTrimestre3",
                "versementTrimestre4"
            })
            public static class Results {

                protected String ancienNumeroIpres;
                protected String raisonSociale;
                protected String zone;
                protected String adresse;
                protected String exercice;
                protected String regime;
                protected String telephone;
                protected String effectif;
                protected String codeAgence;
                protected String versementTrimestre1;
                protected String versementTrimestre2;
                protected String versementTrimestre3;
                protected String versementTrimestre4;

                /**
                 * Obtient la valeur de la propriété ancienNumeroIpres.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getAncienNumeroIpres() {
                    return ancienNumeroIpres;
                }

                /**
                 * Définit la valeur de la propriété ancienNumeroIpres.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setAncienNumeroIpres(String value) {
                    this.ancienNumeroIpres = value;
                }

                /**
                 * Obtient la valeur de la propriété raisonSociale.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getRaisonSociale() {
                    return raisonSociale;
                }

                /**
                 * Définit la valeur de la propriété raisonSociale.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setRaisonSociale(String value) {
                    this.raisonSociale = value;
                }

                /**
                 * Obtient la valeur de la propriété zone.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getZone() {
                    return zone;
                }

                /**
                 * Définit la valeur de la propriété zone.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setZone(String value) {
                    this.zone = value;
                }

                /**
                 * Obtient la valeur de la propriété adresse.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getAdresse() {
                    return adresse;
                }

                /**
                 * Définit la valeur de la propriété adresse.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setAdresse(String value) {
                    this.adresse = value;
                }

                /**
                 * Obtient la valeur de la propriété exercice.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getExercice() {
                    return exercice;
                }

                /**
                 * Définit la valeur de la propriété exercice.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setExercice(String value) {
                    this.exercice = value;
                }

                /**
                 * Obtient la valeur de la propriété regime.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getRegime() {
                    return regime;
                }

                /**
                 * Définit la valeur de la propriété regime.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setRegime(String value) {
                    this.regime = value;
                }

                /**
                 * Obtient la valeur de la propriété telephone.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getTelephone() {
                    return telephone;
                }

                /**
                 * Définit la valeur de la propriété telephone.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setTelephone(String value) {
                    this.telephone = value;
                }

                /**
                 * Obtient la valeur de la propriété effectif.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getEffectif() {
                    return effectif;
                }

                /**
                 * Définit la valeur de la propriété effectif.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setEffectif(String value) {
                    this.effectif = value;
                }

                /**
                 * Obtient la valeur de la propriété codeAgence.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getCodeAgence() {
                    return codeAgence;
                }

                /**
                 * Définit la valeur de la propriété codeAgence.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setCodeAgence(String value) {
                    this.codeAgence = value;
                }

                /**
                 * Obtient la valeur de la propriété versementTrimestre1.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVersementTrimestre1() {
                    return versementTrimestre1;
                }

                /**
                 * Définit la valeur de la propriété versementTrimestre1.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVersementTrimestre1(String value) {
                    this.versementTrimestre1 = value;
                }

                /**
                 * Obtient la valeur de la propriété versementTrimestre2.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVersementTrimestre2() {
                    return versementTrimestre2;
                }

                /**
                 * Définit la valeur de la propriété versementTrimestre2.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVersementTrimestre2(String value) {
                    this.versementTrimestre2 = value;
                }

                /**
                 * Obtient la valeur de la propriété versementTrimestre3.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVersementTrimestre3() {
                    return versementTrimestre3;
                }

                /**
                 * Définit la valeur de la propriété versementTrimestre3.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVersementTrimestre3(String value) {
                    this.versementTrimestre3 = value;
                }

                /**
                 * Obtient la valeur de la propriété versementTrimestre4.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVersementTrimestre4() {
                    return versementTrimestre4;
                }

                /**
                 * Définit la valeur de la propriété versementTrimestre4.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVersementTrimestre4(String value) {
                    this.versementTrimestre4 = value;
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
         *         &lt;element name="raisonSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="numeroEmployerCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateImmatCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nomRepresCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="telephoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "raisonSociale",
            "zone",
            "numeroUnique",
            "agenceCss",
            "numeroEmployerCss",
            "dateImmatCss",
            "nomRepresCss",
            "telephoneCss"
        })
        public static class InformationCss {

            protected String raisonSociale;
            protected String zone;
            protected String numeroUnique;
            protected String agenceCss;
            protected String numeroEmployerCss;
            protected String dateImmatCss;
            protected String nomRepresCss;
            protected String telephoneCss;

            /**
             * Obtient la valeur de la propriété raisonSociale.
             *
             * @return possible object is
             * {@link String }
             */
            public String getRaisonSociale() {
                return raisonSociale;
            }

            /**
             * Définit la valeur de la propriété raisonSociale.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setRaisonSociale(String value) {
                this.raisonSociale = value;
            }

            /**
             * Obtient la valeur de la propriété zone.
             *
             * @return possible object is
             * {@link String }
             */
            public String getZone() {
                return zone;
            }

            /**
             * Définit la valeur de la propriété zone.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setZone(String value) {
                this.zone = value;
            }

            /**
             * Obtient la valeur de la propriété numeroUnique.
             *
             * @return possible object is
             * {@link String }
             */
            public String getNumeroUnique() {
                return numeroUnique;
            }

            /**
             * Définit la valeur de la propriété numeroUnique.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setNumeroUnique(String value) {
                this.numeroUnique = value;
            }

            /**
             * Obtient la valeur de la propriété agenceCss.
             *
             * @return possible object is
             * {@link String }
             */
            public String getAgenceCss() {
                return agenceCss;
            }

            /**
             * Définit la valeur de la propriété agenceCss.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setAgenceCss(String value) {
                this.agenceCss = value;
            }

            /**
             * Obtient la valeur de la propriété numeroEmployerCss.
             *
             * @return possible object is
             * {@link String }
             */
            public String getNumeroEmployerCss() {
                return numeroEmployerCss;
            }

            /**
             * Définit la valeur de la propriété numeroEmployerCss.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setNumeroEmployerCss(String value) {
                this.numeroEmployerCss = value;
            }

            /**
             * Obtient la valeur de la propriété dateImmatCss.
             *
             * @return possible object is
             * {@link String }
             */
            public String getDateImmatCss() {
                return dateImmatCss;
            }

            /**
             * Définit la valeur de la propriété dateImmatCss.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setDateImmatCss(String value) {
                this.dateImmatCss = value;
            }

            /**
             * Obtient la valeur de la propriété nomRepresCss.
             *
             * @return possible object is
             * {@link String }
             */
            public String getNomRepresCss() {
                return nomRepresCss;
            }

            /**
             * Définit la valeur de la propriété nomRepresCss.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setNomRepresCss(String value) {
                this.nomRepresCss = value;
            }

            /**
             * Obtient la valeur de la propriété telephoneCss.
             *
             * @return possible object is
             * {@link String }
             */
            public String getTelephoneCss() {
                return telephoneCss;
            }

            /**
             * Définit la valeur de la propriété telephoneCss.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setTelephoneCss(String value) {
                this.telephoneCss = value;
            }

        }

    }

}
