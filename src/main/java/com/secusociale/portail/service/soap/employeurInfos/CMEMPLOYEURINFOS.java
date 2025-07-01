
package com.secusociale.portail.service.soap.employeurInfos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
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
 *                   &lt;element name="typeIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="identifiants" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="typeIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="descIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numeroIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="status" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="additional" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="employerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="sigle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="regType" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="BVOLN"/>
 *                                   &lt;enumeration value="CMPL"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="employerType" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="GIE"/>
 *                                   &lt;enumeration value="DOM"/>
 *                                   &lt;enumeration value="LIB"/>
 *                                   &lt;enumeration value="TIND"/>
 *                                   &lt;enumeration value="PUB_PARA"/>
 *                                   &lt;enumeration value="PVT"/>
 *                                   &lt;enumeration value="ASSO"/>
 *                                   &lt;enumeration value="COOP"/>
 *                                   &lt;enumeration value="INDV"/>
 *                                   &lt;enumeration value="NGO"/>
 *                                   &lt;enumeration value="PROJ"/>
 *                                   &lt;enumeration value="REP_DIP"/>
 *                                   &lt;enumeration value="MAIN"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="estType" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="CNST"/>
 *                                   &lt;enumeration value="BRNC"/>
 *                                   &lt;enumeration value="HDQT"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="legalStatus" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="DEVP"/>
 *                                   &lt;enumeration value="INDV"/>
 *                                   &lt;enumeration value="LOCE"/>
 *                                   &lt;enumeration value="PUBP"/>
 *                                   &lt;enumeration value="DIPR"/>
 *                                   &lt;enumeration value="LIBP"/>
 *                                   &lt;enumeration value="ENTE"/>
 *                                   &lt;enumeration value="PHYP"/>
 *                                   &lt;enumeration value="PUB"/>
 *                                   &lt;enumeration value="CONC"/>
 *                                   &lt;enumeration value="PVT"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="taxIdDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="tradeRegisterDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="dateOfInspection" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="dateOfFirstHire" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="businessSector" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="100"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="mainLineOfBusiness" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="100"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="atRate" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minExclusive value="-99999"/>
 *                                   &lt;maxExclusive value="99999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="noOfWorkersInGenScheme" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minExclusive value="-99999"/>
 *                                   &lt;maxExclusive value="99999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="noOfWorkersInBasicScheme" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minExclusive value="-99999"/>
 *                                   &lt;maxExclusive value="99999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="website" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="30"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="tradeRegisterNumber" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="19"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="departement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="postal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="secteurCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="secteurIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="representantLegal" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="legalRepPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="placeOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="typeOfIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="identityIdNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ninCedeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="arondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="qartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="landLineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CM_EMPLOYEUR_INFOS", namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
public class CMEMPLOYEURINFOS {

    @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
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
     *         &lt;element name="typeIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroUnique" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "typeIdentifiant",
        "numeroIdentifiant",
        "numeroUnique"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected String typeIdentifiant;
        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected String numeroIdentifiant;
        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected String numeroUnique;

        /**
         * Obtient la valeur de la propriété typeIdentifiant.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTypeIdentifiant() {
            return typeIdentifiant;
        }

        /**
         * Définit la valeur de la propriété typeIdentifiant.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTypeIdentifiant(String value) {
            this.typeIdentifiant = value;
        }

        /**
         * Obtient la valeur de la propriété numeroIdentifiant.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumeroIdentifiant() {
            return numeroIdentifiant;
        }

        /**
         * Définit la valeur de la propriété numeroIdentifiant.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroIdentifiant(String value) {
            this.numeroIdentifiant = value;
        }

        /**
         * Obtient la valeur de la propriété numeroUnique.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumeroUnique() {
            return numeroUnique;
        }

        /**
         * Définit la valeur de la propriété numeroUnique.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroUnique(String value) {
            this.numeroUnique = value;
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
     *         &lt;element name="identifiants" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="typeIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="descIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numeroIdentifiant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="status" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="additional" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="employerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="sigle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="regType" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="BVOLN"/>
     *                         &lt;enumeration value="CMPL"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="employerType" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="GIE"/>
     *                         &lt;enumeration value="DOM"/>
     *                         &lt;enumeration value="LIB"/>
     *                         &lt;enumeration value="TIND"/>
     *                         &lt;enumeration value="PUB_PARA"/>
     *                         &lt;enumeration value="PVT"/>
     *                         &lt;enumeration value="ASSO"/>
     *                         &lt;enumeration value="COOP"/>
     *                         &lt;enumeration value="INDV"/>
     *                         &lt;enumeration value="NGO"/>
     *                         &lt;enumeration value="PROJ"/>
     *                         &lt;enumeration value="REP_DIP"/>
     *                         &lt;enumeration value="MAIN"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="estType" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="CNST"/>
     *                         &lt;enumeration value="BRNC"/>
     *                         &lt;enumeration value="HDQT"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="legalStatus" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="DEVP"/>
     *                         &lt;enumeration value="INDV"/>
     *                         &lt;enumeration value="LOCE"/>
     *                         &lt;enumeration value="PUBP"/>
     *                         &lt;enumeration value="DIPR"/>
     *                         &lt;enumeration value="LIBP"/>
     *                         &lt;enumeration value="ENTE"/>
     *                         &lt;enumeration value="PHYP"/>
     *                         &lt;enumeration value="PUB"/>
     *                         &lt;enumeration value="CONC"/>
     *                         &lt;enumeration value="PVT"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="taxIdDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="tradeRegisterDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="dateOfInspection" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="dateOfFirstHire" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="businessSector" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="100"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="mainLineOfBusiness" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="100"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="atRate" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minExclusive value="-99999"/>
     *                         &lt;maxExclusive value="99999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="noOfWorkersInGenScheme" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minExclusive value="-99999"/>
     *                         &lt;maxExclusive value="99999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="noOfWorkersInBasicScheme" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minExclusive value="-99999"/>
     *                         &lt;maxExclusive value="99999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="website" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="30"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="tradeRegisterNumber" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="19"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="departement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="postal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="secteurCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="secteurIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="representantLegal" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="legalRepPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="placeOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="typeOfIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="identityIdNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ninCedeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="arondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="qartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="landLineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "identifiants",
        "status",
        "additional",
        "representantLegal"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected List<Identifiants> identifiants;
        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected Status status;
        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected Additional additional;
        @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
        protected RepresentantLegal representantLegal;

        /**
         * Gets the value of the identifiants property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the identifiants property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIdentifiants().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Identifiants }
         *
         *
         */
        public List<Identifiants> getIdentifiants() {
            if (identifiants == null) {
                identifiants = new ArrayList<Identifiants>();
            }
            return this.identifiants;
        }

        /**
         * Obtient la valeur de la propriété status.
         *
         * @return
         *     possible object is
         *     {@link Status }
         *
         */
        public Status getStatus() {
            return status;
        }

        /**
         * Définit la valeur de la propriété status.
         *
         * @param value
         *     allowed object is
         *     {@link Status }
         *
         */
        public void setStatus(Status value) {
            this.status = value;
        }

        /**
         * Obtient la valeur de la propriété additional.
         *
         * @return
         *     possible object is
         *     {@link Additional }
         *
         */
        public Additional getAdditional() {
            return additional;
        }

        /**
         * Définit la valeur de la propriété additional.
         *
         * @param value
         *     allowed object is
         *     {@link Additional }
         *
         */
        public void setAdditional(Additional value) {
            this.additional = value;
        }

        /**
         * Obtient la valeur de la propriété representantLegal.
         *
         * @return
         *     possible object is
         *     {@link RepresentantLegal }
         *
         */
        public RepresentantLegal getRepresentantLegal() {
            return representantLegal;
        }

        /**
         * Définit la valeur de la propriété representantLegal.
         *
         * @param value
         *     allowed object is
         *     {@link RepresentantLegal }
         *
         */
        public void setRepresentantLegal(RepresentantLegal value) {
            this.representantLegal = value;
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
         *         &lt;element name="employerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="sigle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="regType" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="BVOLN"/>
         *               &lt;enumeration value="CMPL"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="employerType" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="GIE"/>
         *               &lt;enumeration value="DOM"/>
         *               &lt;enumeration value="LIB"/>
         *               &lt;enumeration value="TIND"/>
         *               &lt;enumeration value="PUB_PARA"/>
         *               &lt;enumeration value="PVT"/>
         *               &lt;enumeration value="ASSO"/>
         *               &lt;enumeration value="COOP"/>
         *               &lt;enumeration value="INDV"/>
         *               &lt;enumeration value="NGO"/>
         *               &lt;enumeration value="PROJ"/>
         *               &lt;enumeration value="REP_DIP"/>
         *               &lt;enumeration value="MAIN"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="estType" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="CNST"/>
         *               &lt;enumeration value="BRNC"/>
         *               &lt;enumeration value="HDQT"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="legalStatus" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="DEVP"/>
         *               &lt;enumeration value="INDV"/>
         *               &lt;enumeration value="LOCE"/>
         *               &lt;enumeration value="PUBP"/>
         *               &lt;enumeration value="DIPR"/>
         *               &lt;enumeration value="LIBP"/>
         *               &lt;enumeration value="ENTE"/>
         *               &lt;enumeration value="PHYP"/>
         *               &lt;enumeration value="PUB"/>
         *               &lt;enumeration value="CONC"/>
         *               &lt;enumeration value="PVT"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="taxIdDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="tradeRegisterDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="dateOfInspection" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="dateOfFirstHire" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="businessSector" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="100"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="mainLineOfBusiness" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="100"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="atRate" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minExclusive value="-99999"/>
         *               &lt;maxExclusive value="99999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="noOfWorkersInGenScheme" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minExclusive value="-99999"/>
         *               &lt;maxExclusive value="99999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="noOfWorkersInBasicScheme" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minExclusive value="-99999"/>
         *               &lt;maxExclusive value="99999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="website" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="30"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="tradeRegisterNumber" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="19"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="departement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="postal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="secteurCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="secteurIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "employerName",
            "sigle",
            "regType",
            "employerType",
            "estType",
            "legalStatus",
            "taxIdDate",
            "tradeRegisterDate",
            "dateOfInspection",
            "dateOfFirstHire",
            "businessSector",
            "mainLineOfBusiness",
            "atRate",
            "noOfWorkersInGenScheme",
            "noOfWorkersInBasicScheme",
            "website",
            "tradeRegisterNumber",
            "region",
            "departement",
            "arrondissement",
            "commune",
            "quartier",
            "address",
            "postal",
            "zoneCss",
            "zoneIpres",
            "secteurCss",
            "secteurIpres",
            "agenceCss",
            "agenceIpres"
        })
        public static class Additional {

            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String employerName;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String sigle;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String regType;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String employerType;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String estType;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String legalStatus;
            @XmlElementRef(name = "taxIdDate", namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> taxIdDate;
            @XmlElementRef(name = "tradeRegisterDate", namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> tradeRegisterDate;
            @XmlElementRef(name = "dateOfInspection", namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateOfInspection;
            @XmlElementRef(name = "dateOfFirstHire", namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateOfFirstHire;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String businessSector;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String mainLineOfBusiness;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected BigDecimal atRate;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected BigDecimal noOfWorkersInGenScheme;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected BigDecimal noOfWorkersInBasicScheme;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String website;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String tradeRegisterNumber;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String region;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String departement;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String arrondissement;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String commune;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String quartier;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String address;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String postal;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String zoneCss;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String zoneIpres;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String secteurCss;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String secteurIpres;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String agenceCss;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String agenceIpres;

            /**
             * Obtient la valeur de la propriété employerName.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEmployerName() {
                return employerName;
            }

            /**
             * Définit la valeur de la propriété employerName.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEmployerName(String value) {
                this.employerName = value;
            }

            /**
             * Obtient la valeur de la propriété sigle.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSigle() {
                return sigle;
            }

            /**
             * Définit la valeur de la propriété sigle.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSigle(String value) {
                this.sigle = value;
            }

            /**
             * Obtient la valeur de la propriété regType.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRegType() {
                return regType;
            }

            /**
             * Définit la valeur de la propriété regType.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRegType(String value) {
                this.regType = value;
            }

            /**
             * Obtient la valeur de la propriété employerType.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEmployerType() {
                return employerType;
            }

            /**
             * Définit la valeur de la propriété employerType.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEmployerType(String value) {
                this.employerType = value;
            }

            /**
             * Obtient la valeur de la propriété estType.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEstType() {
                return estType;
            }

            /**
             * Définit la valeur de la propriété estType.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEstType(String value) {
                this.estType = value;
            }

            /**
             * Obtient la valeur de la propriété legalStatus.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLegalStatus() {
                return legalStatus;
            }

            /**
             * Définit la valeur de la propriété legalStatus.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLegalStatus(String value) {
                this.legalStatus = value;
            }

            /**
             * Obtient la valeur de la propriété taxIdDate.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getTaxIdDate() {
                return taxIdDate;
            }

            /**
             * Définit la valeur de la propriété taxIdDate.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setTaxIdDate(JAXBElement<XMLGregorianCalendar> value) {
                this.taxIdDate = value;
            }

            /**
             * Obtient la valeur de la propriété tradeRegisterDate.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getTradeRegisterDate() {
                return tradeRegisterDate;
            }

            /**
             * Définit la valeur de la propriété tradeRegisterDate.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setTradeRegisterDate(JAXBElement<XMLGregorianCalendar> value) {
                this.tradeRegisterDate = value;
            }

            /**
             * Obtient la valeur de la propriété dateOfInspection.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateOfInspection() {
                return dateOfInspection;
            }

            /**
             * Définit la valeur de la propriété dateOfInspection.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateOfInspection(JAXBElement<XMLGregorianCalendar> value) {
                this.dateOfInspection = value;
            }

            /**
             * Obtient la valeur de la propriété dateOfFirstHire.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateOfFirstHire() {
                return dateOfFirstHire;
            }

            /**
             * Définit la valeur de la propriété dateOfFirstHire.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateOfFirstHire(JAXBElement<XMLGregorianCalendar> value) {
                this.dateOfFirstHire = value;
            }

            /**
             * Obtient la valeur de la propriété businessSector.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getBusinessSector() {
                return businessSector;
            }

            /**
             * Définit la valeur de la propriété businessSector.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setBusinessSector(String value) {
                this.businessSector = value;
            }

            /**
             * Obtient la valeur de la propriété mainLineOfBusiness.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getMainLineOfBusiness() {
                return mainLineOfBusiness;
            }

            /**
             * Définit la valeur de la propriété mainLineOfBusiness.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setMainLineOfBusiness(String value) {
                this.mainLineOfBusiness = value;
            }

            /**
             * Obtient la valeur de la propriété atRate.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getAtRate() {
                return atRate;
            }

            /**
             * Définit la valeur de la propriété atRate.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setAtRate(BigDecimal value) {
                this.atRate = value;
            }

            /**
             * Obtient la valeur de la propriété noOfWorkersInGenScheme.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNoOfWorkersInGenScheme() {
                return noOfWorkersInGenScheme;
            }

            /**
             * Définit la valeur de la propriété noOfWorkersInGenScheme.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNoOfWorkersInGenScheme(BigDecimal value) {
                this.noOfWorkersInGenScheme = value;
            }

            /**
             * Obtient la valeur de la propriété noOfWorkersInBasicScheme.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNoOfWorkersInBasicScheme() {
                return noOfWorkersInBasicScheme;
            }

            /**
             * Définit la valeur de la propriété noOfWorkersInBasicScheme.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNoOfWorkersInBasicScheme(BigDecimal value) {
                this.noOfWorkersInBasicScheme = value;
            }

            /**
             * Obtient la valeur de la propriété website.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getWebsite() {
                return website;
            }

            /**
             * Définit la valeur de la propriété website.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setWebsite(String value) {
                this.website = value;
            }

            /**
             * Obtient la valeur de la propriété tradeRegisterNumber.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTradeRegisterNumber() {
                return tradeRegisterNumber;
            }

            /**
             * Définit la valeur de la propriété tradeRegisterNumber.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTradeRegisterNumber(String value) {
                this.tradeRegisterNumber = value;
            }

            /**
             * Obtient la valeur de la propriété region.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRegion() {
                return region;
            }

            /**
             * Définit la valeur de la propriété region.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRegion(String value) {
                this.region = value;
            }

            /**
             * Obtient la valeur de la propriété departement.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDepartement() {
                return departement;
            }

            /**
             * Définit la valeur de la propriété departement.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDepartement(String value) {
                this.departement = value;
            }

            /**
             * Obtient la valeur de la propriété arrondissement.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getArrondissement() {
                return arrondissement;
            }

            /**
             * Définit la valeur de la propriété arrondissement.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setArrondissement(String value) {
                this.arrondissement = value;
            }

            /**
             * Obtient la valeur de la propriété commune.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCommune() {
                return commune;
            }

            /**
             * Définit la valeur de la propriété commune.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCommune(String value) {
                this.commune = value;
            }

            /**
             * Obtient la valeur de la propriété quartier.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getQuartier() {
                return quartier;
            }

            /**
             * Définit la valeur de la propriété quartier.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setQuartier(String value) {
                this.quartier = value;
            }

            /**
             * Obtient la valeur de la propriété address.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAddress() {
                return address;
            }

            /**
             * Définit la valeur de la propriété address.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAddress(String value) {
                this.address = value;
            }

            /**
             * Obtient la valeur de la propriété postal.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPostal() {
                return postal;
            }

            /**
             * Définit la valeur de la propriété postal.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPostal(String value) {
                this.postal = value;
            }

            /**
             * Obtient la valeur de la propriété zoneCss.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getZoneCss() {
                return zoneCss;
            }

            /**
             * Définit la valeur de la propriété zoneCss.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setZoneCss(String value) {
                this.zoneCss = value;
            }

            /**
             * Obtient la valeur de la propriété zoneIpres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getZoneIpres() {
                return zoneIpres;
            }

            /**
             * Définit la valeur de la propriété zoneIpres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setZoneIpres(String value) {
                this.zoneIpres = value;
            }

            /**
             * Obtient la valeur de la propriété secteurCss.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSecteurCss() {
                return secteurCss;
            }

            /**
             * Définit la valeur de la propriété secteurCss.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSecteurCss(String value) {
                this.secteurCss = value;
            }

            /**
             * Obtient la valeur de la propriété secteurIpres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSecteurIpres() {
                return secteurIpres;
            }

            /**
             * Définit la valeur de la propriété secteurIpres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSecteurIpres(String value) {
                this.secteurIpres = value;
            }

            /**
             * Obtient la valeur de la propriété agenceCss.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgenceCss() {
                return agenceCss;
            }

            /**
             * Définit la valeur de la propriété agenceCss.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgenceCss(String value) {
                this.agenceCss = value;
            }

            public String getAgenceIpres() {
                return agenceIpres;
            }

            public void setAgenceIpres(String value) {
                this.agenceIpres = value;
            }

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "typeIdentifiant",
            "descIdentifiant",
            "numeroIdentifiant"
        })
        public static class Identifiants {

            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String typeIdentifiant;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String descIdentifiant;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String numeroIdentifiant;


            public String getTypeIdentifiant() {
                return typeIdentifiant;
            }

            public void setTypeIdentifiant(String value) {
                this.typeIdentifiant = value;
            }

            public String getDescIdentifiant() {
                return descIdentifiant;
            }

            /**
             * Définit la valeur de la propriété descIdentifiant.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDescIdentifiant(String value) {
                this.descIdentifiant = value;
            }

            /**
             * Obtient la valeur de la propriété numeroIdentifiant.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumeroIdentifiant() {
                return numeroIdentifiant;
            }

            /**
             * Définit la valeur de la propriété numeroIdentifiant.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumeroIdentifiant(String value) {
                this.numeroIdentifiant = value;
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
         *         &lt;element name="legalRepPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="placeOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="typeOfIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="identityIdNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ninCedeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="arondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="qartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="landLineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "legalRepPerson",
            "lastName",
            "firstName",
            "birthdate",
            "nationality",
            "nin",
            "placeOfBirth",
            "cityOfBirth",
            "typeOfIdentity",
            "identityIdNumber",
            "ninCedeo",
            "issuedDate",
            "expiryDate",
            "region",
            "department",
            "arondissement",
            "commune",
            "qartier",
            "address",
            "landLineNumber",
            "mobileNumber",
            "email"
        })
        public static class RepresentantLegal {

            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String legalRepPerson;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String lastName;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String firstName;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String birthdate;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String nationality;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String nin;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String placeOfBirth;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String cityOfBirth;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String typeOfIdentity;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String identityIdNumber;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String ninCedeo;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String issuedDate;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String expiryDate;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String region;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String department;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String arondissement;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String commune;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String qartier;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String address;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String landLineNumber;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String mobileNumber;
            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String email;

            /**
             * Obtient la valeur de la propriété legalRepPerson.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLegalRepPerson() {
                return legalRepPerson;
            }

            /**
             * Définit la valeur de la propriété legalRepPerson.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLegalRepPerson(String value) {
                this.legalRepPerson = value;
            }

            /**
             * Obtient la valeur de la propriété lastName.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLastName() {
                return lastName;
            }

            /**
             * Définit la valeur de la propriété lastName.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLastName(String value) {
                this.lastName = value;
            }

            /**
             * Obtient la valeur de la propriété firstName.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFirstName() {
                return firstName;
            }

            /**
             * Définit la valeur de la propriété firstName.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFirstName(String value) {
                this.firstName = value;
            }

            /**
             * Obtient la valeur de la propriété birthdate.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getBirthdate() {
                return birthdate;
            }

            /**
             * Définit la valeur de la propriété birthdate.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setBirthdate(String value) {
                this.birthdate = value;
            }

            /**
             * Obtient la valeur de la propriété nationality.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNationality() {
                return nationality;
            }

            /**
             * Définit la valeur de la propriété nationality.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNationality(String value) {
                this.nationality = value;
            }

            /**
             * Obtient la valeur de la propriété nin.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNin() {
                return nin;
            }

            /**
             * Définit la valeur de la propriété nin.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNin(String value) {
                this.nin = value;
            }

            /**
             * Obtient la valeur de la propriété placeOfBirth.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPlaceOfBirth() {
                return placeOfBirth;
            }

            /**
             * Définit la valeur de la propriété placeOfBirth.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPlaceOfBirth(String value) {
                this.placeOfBirth = value;
            }

            /**
             * Obtient la valeur de la propriété cityOfBirth.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCityOfBirth() {
                return cityOfBirth;
            }

            /**
             * Définit la valeur de la propriété cityOfBirth.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCityOfBirth(String value) {
                this.cityOfBirth = value;
            }

            /**
             * Obtient la valeur de la propriété typeOfIdentity.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeOfIdentity() {
                return typeOfIdentity;
            }

            /**
             * Définit la valeur de la propriété typeOfIdentity.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeOfIdentity(String value) {
                this.typeOfIdentity = value;
            }

            /**
             * Obtient la valeur de la propriété identityIdNumber.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIdentityIdNumber() {
                return identityIdNumber;
            }

            /**
             * Définit la valeur de la propriété identityIdNumber.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIdentityIdNumber(String value) {
                this.identityIdNumber = value;
            }

            /**
             * Obtient la valeur de la propriété ninCedeo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNinCedeo() {
                return ninCedeo;
            }

            /**
             * Définit la valeur de la propriété ninCedeo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNinCedeo(String value) {
                this.ninCedeo = value;
            }

            /**
             * Obtient la valeur de la propriété issuedDate.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIssuedDate() {
                return issuedDate;
            }

            /**
             * Définit la valeur de la propriété issuedDate.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIssuedDate(String value) {
                this.issuedDate = value;
            }

            /**
             * Obtient la valeur de la propriété expiryDate.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getExpiryDate() {
                return expiryDate;
            }

            /**
             * Définit la valeur de la propriété expiryDate.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setExpiryDate(String value) {
                this.expiryDate = value;
            }

            /**
             * Obtient la valeur de la propriété region.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRegion() {
                return region;
            }

            /**
             * Définit la valeur de la propriété region.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRegion(String value) {
                this.region = value;
            }

            /**
             * Obtient la valeur de la propriété department.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDepartment() {
                return department;
            }

            /**
             * Définit la valeur de la propriété department.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDepartment(String value) {
                this.department = value;
            }

            /**
             * Obtient la valeur de la propriété arondissement.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getArondissement() {
                return arondissement;
            }

            /**
             * Définit la valeur de la propriété arondissement.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setArondissement(String value) {
                this.arondissement = value;
            }

            /**
             * Obtient la valeur de la propriété commune.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCommune() {
                return commune;
            }

            /**
             * Définit la valeur de la propriété commune.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCommune(String value) {
                this.commune = value;
            }

            /**
             * Obtient la valeur de la propriété qartier.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getQartier() {
                return qartier;
            }

            /**
             * Définit la valeur de la propriété qartier.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setQartier(String value) {
                this.qartier = value;
            }

            /**
             * Obtient la valeur de la propriété address.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAddress() {
                return address;
            }

            /**
             * Définit la valeur de la propriété address.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAddress(String value) {
                this.address = value;
            }

            /**
             * Obtient la valeur de la propriété landLineNumber.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLandLineNumber() {
                return landLineNumber;
            }

            /**
             * Définit la valeur de la propriété landLineNumber.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLandLineNumber(String value) {
                this.landLineNumber = value;
            }

            /**
             * Obtient la valeur de la propriété mobileNumber.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getMobileNumber() {
                return mobileNumber;
            }

            /**
             * Définit la valeur de la propriété mobileNumber.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setMobileNumber(String value) {
                this.mobileNumber = value;
            }

            /**
             * Obtient la valeur de la propriété email.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEmail() {
                return email;
            }

            /**
             * Définit la valeur de la propriété email.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEmail(String value) {
                this.email = value;
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
         *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "status"
        })
        public static class Status {

            @XmlElement(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd")
            protected String status;

            /**
             * Obtient la valeur de la propriété status.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getStatus() {
                return status;
            }

            /**
             * Définit la valeur de la propriété status.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setStatus(String value) {
                this.status = value;
            }

        }

    }

}
