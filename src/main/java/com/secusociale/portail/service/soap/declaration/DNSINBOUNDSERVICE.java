package com.secusociale.portail.service.soap.declaration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *                             &lt;element name="numeroEmployeur">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="10"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="raisonSociale" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="adresse" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="typeDeclaration" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="TRIMESTRIEL"/>
 *                                   &lt;enumeration value="MENSUEL"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="dateDebutCotisation" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="dateFinCotisation" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="synthese" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="totalNouvSalaries" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minExclusive value="-9999999999"/>
 *                                   &lt;maxExclusive value="9999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="totalSalaries">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="999999999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="cumulTotSalAssIpresRg" type="{http://ouaf.oracle.com/}money"/>
 *                             &lt;element name="cumulTotSalAssIpresRcc" type="{http://ouaf.oracle.com/}money"/>
 *                             &lt;element name="cumulTotSalAssCssPf" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="cumulTotSalAssCssAtmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totalSalVerses" type="{http://ouaf.oracle.com/}money"/>
 *                             &lt;element name="mntCotPfCalcParEmployeur" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="mntCotAtMpCalcParEmployeur" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="mntCotRgCalcParEmployeur" type="{http://ouaf.oracle.com/}money"/>
 *                             &lt;element name="mntCotRccCalcParEmployeur" type="{http://ouaf.oracle.com/}money"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="informationSalariesList" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="numeroAssureSocial" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="10"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nom" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="prenom" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="30"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="dateDeNaisssance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="typePieceIdentite">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="CDAO"/>
 *                                   &lt;enumeration value="NIN"/>
 *                                   &lt;enumeration value="PASS"/>
 *                                   &lt;enumeration value="IDF"/>
 *                                   &lt;enumeration value="CONC"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="numeroPieceIdentite">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="40"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="typeContrat" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="AUT"/>
 *                                   &lt;enumeration value="APP"/>
 *                                   &lt;enumeration value="JOU"/>
 *                                   &lt;enumeration value="STA"/>
 *                                   &lt;enumeration value="CDD"/>
 *                                   &lt;enumeration value="CDI"/>
 *                                   &lt;enumeration value="SPE"/>
 *                                   &lt;enumeration value="CSN"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="dateEntree" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="dateSortie" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="motifSortie" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="EXPIRATION_DE_CONTRAT"/>
 *                                   &lt;enumeration value="DEMISSION"/>
 *                                   &lt;enumeration value="LICENCIEMENT"/>
 *                                   &lt;enumeration value="ABANDON_DE_POSTE"/>
 *                                   &lt;enumeration value="MUTATION"/>
 *                                   &lt;enumeration value="RETRAITE"/>
 *                                   &lt;enumeration value="DECES"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="totSalAssCssPf1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssCssAtmp1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssIpresRg1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssIpresRcc1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="salaireBrut1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="nombreJours1" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="9999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nombreHeures1" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="99999999999999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="tempsTravail1" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="TPS_PARTIEL"/>
 *                                   &lt;enumeration value="TPS_PLEIN"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="trancheTravail1" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="regimeGeneral1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="regimCompCadre1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="dateEffetRegimeCadre1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="totSalAssCssPf2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssCssAtmp2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssIpresRg2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssIpresRcc2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="salaireBrut2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="nombreJours2" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="9999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nombreHeures2" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="99999999999999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="tempsTravail2" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="TPS_PARTIEL"/>
 *                                   &lt;enumeration value="TPS_PLEIN"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="trancheTravail2" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="regimeGeneral2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="regimCompCadre2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="dateEffetRegimeCadre2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="totSalAssCssPf3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssCssAtmp3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssIpresRg3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="totSalAssIpresRcc3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="salaireBrut3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                             &lt;element name="nombreJours3" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="9999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nombreHeures3" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;minInclusive value="0"/>
 *                                   &lt;maxExclusive value="99999999999999999999"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="tempsTravail3" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="TPS_PARTIEL"/>
 *                                   &lt;enumeration value="TPS_PLEIN"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="trancheTravail3" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="regimeGeneral3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="regimCompCadre3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="dateEffetRegimeCadre3" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
 *                   &lt;element name="formId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "DNS_INBOUND_SERVICE", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
public class DNSINBOUNDSERVICE {

    @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriété input.
     *
     * @return
     *     possible object is
     *     {@link Input }
     *
     */
    public Input getInput() {
        return input;
    }

    /**
     * Définit la valeur de la propriété input.
     *
     * @param value
     *     allowed object is
     *     {@link Input }
     *
     */
    public void setInput(Input value) {
        this.input = value;
    }

    /**
     * Obtient la valeur de la propriété output.
     *
     * @return
     *     possible object is
     *     {@link Output }
     *
     */
    public Output getOutput() {
        return output;
    }

    /**
     * Définit la valeur de la propriété output.
     *
     * @param value
     *     allowed object is
     *     {@link Output }
     *
     */
    public void setOutput(Output value) {
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
     *         &lt;element name="informationEmployeur" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="numeroEmployeur">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="10"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="raisonSociale" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="adresse" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="typeDeclaration" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="TRIMESTRIEL"/>
     *                         &lt;enumeration value="MENSUEL"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="dateDebutCotisation" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="dateFinCotisation" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="synthese" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="totalNouvSalaries" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minExclusive value="-9999999999"/>
     *                         &lt;maxExclusive value="9999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="totalSalaries">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="999999999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="cumulTotSalAssIpresRg" type="{http://ouaf.oracle.com/}money"/>
     *                   &lt;element name="cumulTotSalAssIpresRcc" type="{http://ouaf.oracle.com/}money"/>
     *                   &lt;element name="cumulTotSalAssCssPf" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="cumulTotSalAssCssAtmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totalSalVerses" type="{http://ouaf.oracle.com/}money"/>
     *                   &lt;element name="mntCotPfCalcParEmployeur" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="mntCotAtMpCalcParEmployeur" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="mntCotRgCalcParEmployeur" type="{http://ouaf.oracle.com/}money"/>
     *                   &lt;element name="mntCotRccCalcParEmployeur" type="{http://ouaf.oracle.com/}money"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="informationSalariesList" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="numeroAssureSocial" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="10"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nom" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="prenom" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="30"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="dateDeNaisssance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="typePieceIdentite">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="CDAO"/>
     *                         &lt;enumeration value="NIN"/>
     *                         &lt;enumeration value="PASS"/>
     *                         &lt;enumeration value="IDF"/>
     *                         &lt;enumeration value="CONC"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="numeroPieceIdentite">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="40"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="typeContrat" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="AUT"/>
     *                         &lt;enumeration value="APP"/>
     *                         &lt;enumeration value="JOU"/>
     *                         &lt;enumeration value="STA"/>
     *                         &lt;enumeration value="CDD"/>
     *                         &lt;enumeration value="CDI"/>
     *                         &lt;enumeration value="SPE"/>
     *                         &lt;enumeration value="CSN"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="dateEntree" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="dateSortie" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="motifSortie" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="EXPIRATION_DE_CONTRAT"/>
     *                         &lt;enumeration value="DEMISSION"/>
     *                         &lt;enumeration value="LICENCIEMENT"/>
     *                         &lt;enumeration value="ABANDON_DE_POSTE"/>
     *                         &lt;enumeration value="MUTATION"/>
     *                         &lt;enumeration value="RETRAITE"/>
     *                         &lt;enumeration value="DECES"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="totSalAssCssPf1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssCssAtmp1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssIpresRg1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssIpresRcc1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="salaireBrut1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="nombreJours1" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="9999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nombreHeures1" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="99999999999999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="tempsTravail1" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="TPS_PARTIEL"/>
     *                         &lt;enumeration value="TPS_PLEIN"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="trancheTravail1" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="regimeGeneral1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="regimCompCadre1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="dateEffetRegimeCadre1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="totSalAssCssPf2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssCssAtmp2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssIpresRg2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssIpresRcc2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="salaireBrut2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="nombreJours2" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="9999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nombreHeures2" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="99999999999999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="tempsTravail2" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="TPS_PARTIEL"/>
     *                         &lt;enumeration value="TPS_PLEIN"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="trancheTravail2" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="regimeGeneral2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="regimCompCadre2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="dateEffetRegimeCadre2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="totSalAssCssPf3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssCssAtmp3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssIpresRg3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="totSalAssIpresRcc3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="salaireBrut3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *                   &lt;element name="nombreJours3" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="9999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nombreHeures3" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;minInclusive value="0"/>
     *                         &lt;maxExclusive value="99999999999999999999"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="tempsTravail3" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="TPS_PARTIEL"/>
     *                         &lt;enumeration value="TPS_PLEIN"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="trancheTravail3" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="regimeGeneral3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="regimCompCadre3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="dateEffetRegimeCadre3" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
        "informationEmployeur",
        "synthese",
        "informationSalariesList"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
        protected InformationEmployeur informationEmployeur;
        @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
        protected Synthese synthese;
        @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
        protected List<InformationSalariesList> informationSalariesList;

        /**
         * Obtient la valeur de la propriété informationEmployeur.
         *
         * @return
         *     possible object is
         *     {@link InformationEmployeur }
         *
         */
        public InformationEmployeur getInformationEmployeur() {
            return informationEmployeur;
        }

        /**
         * Définit la valeur de la propriété informationEmployeur.
         *
         * @param value
         *     allowed object is
         *     {@link InformationEmployeur }
         *
         */
        public void setInformationEmployeur(InformationEmployeur value) {
            this.informationEmployeur = value;
        }

        /**
         * Obtient la valeur de la propriété synthese.
         *
         * @return
         *     possible object is
         *     {@link Synthese }
         *
         */
        public Synthese getSynthese() {
            return synthese;
        }

        /**
         * Définit la valeur de la propriété synthese.
         *
         * @param value
         *     allowed object is
         *     {@link Synthese }
         *
         */
        public void setSynthese(Synthese value) {
            this.synthese = value;
        }

        /**
         * Gets the value of the informationSalariesList property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the informationSalariesList property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInformationSalariesList().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InformationSalariesList }
         *
         *
         */
        public List<InformationSalariesList> getInformationSalariesList() {
            if (informationSalariesList == null) {
                informationSalariesList = new ArrayList<InformationSalariesList>();
            }
            return this.informationSalariesList;
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
         *         &lt;element name="numeroEmployeur">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="10"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="raisonSociale" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="adresse" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="typeDeclaration" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="TRIMESTRIEL"/>
         *               &lt;enumeration value="MENSUEL"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="dateDebutCotisation" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="dateFinCotisation" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
            "numeroEmployeur",
            "raisonSociale",
            "adresse",
            "typeDeclaration",
            "dateDebutCotisation",
            "dateFinCotisation"
        })
        public static class InformationEmployeur {

            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true)
            protected String numeroEmployeur;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String raisonSociale;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String adresse;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String typeDeclaration;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dateDebutCotisation;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dateFinCotisation;

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
             * Obtient la valeur de la propriété adresse.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAdresse() {
                return adresse;
            }

            /**
             * Définit la valeur de la propriété adresse.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAdresse(String value) {
                this.adresse = value;
            }

            /**
             * Obtient la valeur de la propriété typeDeclaration.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeDeclaration() {
                return typeDeclaration;
            }

            /**
             * Définit la valeur de la propriété typeDeclaration.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeDeclaration(String value) {
                this.typeDeclaration = value;
            }

            /**
             * Obtient la valeur de la propriété dateDebutCotisation.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getDateDebutCotisation() {
                return dateDebutCotisation;
            }

            /**
             * Définit la valeur de la propriété dateDebutCotisation.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setDateDebutCotisation(XMLGregorianCalendar value) {
                this.dateDebutCotisation = value;
            }

            /**
             * Obtient la valeur de la propriété dateFinCotisation.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getDateFinCotisation() {
                return dateFinCotisation;
            }

            /**
             * Définit la valeur de la propriété dateFinCotisation.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setDateFinCotisation(XMLGregorianCalendar value) {
                this.dateFinCotisation = value;
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
         *         &lt;element name="numeroAssureSocial" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="10"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nom" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="prenom" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="30"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="dateDeNaisssance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="typePieceIdentite">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="CDAO"/>
         *               &lt;enumeration value="NIN"/>
         *               &lt;enumeration value="PASS"/>
         *               &lt;enumeration value="IDF"/>
         *               &lt;enumeration value="CONC"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="numeroPieceIdentite">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="40"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="typeContrat" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="AUT"/>
         *               &lt;enumeration value="APP"/>
         *               &lt;enumeration value="JOU"/>
         *               &lt;enumeration value="STA"/>
         *               &lt;enumeration value="CDD"/>
         *               &lt;enumeration value="CDI"/>
         *               &lt;enumeration value="SPE"/>
         *               &lt;enumeration value="CSN"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="dateEntree" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="dateSortie" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="motifSortie" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="EXPIRATION_DE_CONTRAT"/>
         *               &lt;enumeration value="DEMISSION"/>
         *               &lt;enumeration value="LICENCIEMENT"/>
         *               &lt;enumeration value="ABANDON_DE_POSTE"/>
         *               &lt;enumeration value="MUTATION"/>
         *               &lt;enumeration value="RETRAITE"/>
         *               &lt;enumeration value="DECES"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="totSalAssCssPf1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssCssAtmp1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssIpresRg1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssIpresRcc1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="salaireBrut1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="nombreJours1" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="9999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nombreHeures1" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="99999999999999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="tempsTravail1" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="TPS_PARTIEL"/>
         *               &lt;enumeration value="TPS_PLEIN"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="trancheTravail1" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="regimeGeneral1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="regimCompCadre1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="dateEffetRegimeCadre1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="totSalAssCssPf2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssCssAtmp2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssIpresRg2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssIpresRcc2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="salaireBrut2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="nombreJours2" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="9999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nombreHeures2" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="99999999999999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="tempsTravail2" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="TPS_PARTIEL"/>
         *               &lt;enumeration value="TPS_PLEIN"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="trancheTravail2" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="regimeGeneral2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="regimCompCadre2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="dateEffetRegimeCadre2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="totSalAssCssPf3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssCssAtmp3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssIpresRg3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totSalAssIpresRcc3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="salaireBrut3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="nombreJours3" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="9999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nombreHeures3" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="99999999999999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="tempsTravail3" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="TPS_PARTIEL"/>
         *               &lt;enumeration value="TPS_PLEIN"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="trancheTravail3" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="regimeGeneral3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="regimCompCadre3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="dateEffetRegimeCadre3" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
            "numeroAssureSocial",
            "nom",
            "prenom",
            "dateDeNaisssance",
            "typePieceIdentite",
            "numeroPieceIdentite",
            "typeContrat",
            "dateEntree",
            "dateSortie",
            "motifSortie",
            "totSalAssCssPf1",
            "totSalAssCssAtmp1",
            "totSalAssIpresRg1",
            "totSalAssIpresRcc1",
            "salaireBrut1",
            "nombreJours1",
            "nombreHeures1",
            "tempsTravail1",
            "trancheTravail1",
            "regimeGeneral1",
            "regimCompCadre1",
            "dateEffetRegimeCadre1",
            "totSalAssCssPf2",
            "totSalAssCssAtmp2",
            "totSalAssIpresRg2",
            "totSalAssIpresRcc2",
            "salaireBrut2",
            "nombreJours2",
            "nombreHeures2",
            "tempsTravail2",
            "trancheTravail2",
            "regimeGeneral2",
            "regimCompCadre2",
            "dateEffetRegimeCadre2",
            "totSalAssCssPf3",
            "totSalAssCssAtmp3",
            "totSalAssIpresRg3",
            "totSalAssIpresRcc3",
            "salaireBrut3",
            "nombreJours3",
            "nombreHeures3",
            "tempsTravail3",
            "trancheTravail3",
            "regimeGeneral3",
            "regimCompCadre3",
            "dateEffetRegimeCadre3"
        })
        public static class InformationSalariesList {

            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String numeroAssureSocial;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String nom;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String prenom;
            @XmlElementRef(name = "dateDeNaisssance", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateDeNaisssance;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true)
            protected String typePieceIdentite;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true)
            protected String numeroPieceIdentite;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String typeContrat;
            @XmlElementRef(name = "dateEntree", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateEntree;
            @XmlElementRef(name = "dateSortie", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateSortie;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String motifSortie;
            @XmlElementRef(name = "totSalAssCssPf1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssCssPf1;
            @XmlElementRef(name = "totSalAssCssAtmp1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssCssAtmp1;
            @XmlElementRef(name = "totSalAssIpresRg1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssIpresRg1;
            @XmlElementRef(name = "totSalAssIpresRcc1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssIpresRcc1;
            @XmlElementRef(name = "salaireBrut1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> salaireBrut1;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal nombreJours1;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal nombreHeures1;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String tempsTravail1;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String trancheTravail1;
            @XmlElementRef(name = "regimeGeneral1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> regimeGeneral1;
            @XmlElementRef(name = "regimCompCadre1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> regimCompCadre1;
            @XmlElementRef(name = "dateEffetRegimeCadre1", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateEffetRegimeCadre1;
            @XmlElementRef(name = "totSalAssCssPf2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssCssPf2;
            @XmlElementRef(name = "totSalAssCssAtmp2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssCssAtmp2;
            @XmlElementRef(name = "totSalAssIpresRg2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssIpresRg2;
            @XmlElementRef(name = "totSalAssIpresRcc2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssIpresRcc2;
            @XmlElementRef(name = "salaireBrut2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> salaireBrut2;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal nombreJours2;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal nombreHeures2;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String tempsTravail2;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String trancheTravail2;
            @XmlElementRef(name = "regimeGeneral2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> regimeGeneral2;
            @XmlElementRef(name = "regimCompCadre2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> regimCompCadre2;
            @XmlElementRef(name = "dateEffetRegimeCadre2", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateEffetRegimeCadre2;
            @XmlElementRef(name = "totSalAssCssPf3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssCssPf3;
            @XmlElementRef(name = "totSalAssCssAtmp3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssCssAtmp3;
            @XmlElementRef(name = "totSalAssIpresRg3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssIpresRg3;
            @XmlElementRef(name = "totSalAssIpresRcc3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> totSalAssIpresRcc3;
            @XmlElementRef(name = "salaireBrut3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> salaireBrut3;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal nombreJours3;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal nombreHeures3;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String tempsTravail3;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected String trancheTravail3;
            @XmlElementRef(name = "regimeGeneral3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> regimeGeneral3;
            @XmlElementRef(name = "regimCompCadre3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<Boolean> regimCompCadre3;
            @XmlElementRef(name = "dateEffetRegimeCadre3", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> dateEffetRegimeCadre3;

            /**
             * Obtient la valeur de la propriété numeroAssureSocial.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumeroAssureSocial() {
                return numeroAssureSocial;
            }

            /**
             * Définit la valeur de la propriété numeroAssureSocial.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumeroAssureSocial(String value) {
                this.numeroAssureSocial = value;
            }

            /**
             * Obtient la valeur de la propriété nom.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNom() {
                return nom;
            }

            /**
             * Définit la valeur de la propriété nom.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propriété prenom.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * Définit la valeur de la propriété prenom.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }

            /**
             * Obtient la valeur de la propriété dateDeNaisssance.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateDeNaisssance() {
                return dateDeNaisssance;
            }

            /**
             * Définit la valeur de la propriété dateDeNaisssance.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateDeNaisssance(JAXBElement<XMLGregorianCalendar> value) {
                this.dateDeNaisssance = value;
            }

            /**
             * Obtient la valeur de la propriété typePieceIdentite.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypePieceIdentite() {
                return typePieceIdentite;
            }

            /**
             * Définit la valeur de la propriété typePieceIdentite.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypePieceIdentite(String value) {
                this.typePieceIdentite = value;
            }

            /**
             * Obtient la valeur de la propriété numeroPieceIdentite.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumeroPieceIdentite() {
                return numeroPieceIdentite;
            }

            /**
             * Définit la valeur de la propriété numeroPieceIdentite.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumeroPieceIdentite(String value) {
                this.numeroPieceIdentite = value;
            }

            /**
             * Obtient la valeur de la propriété typeContrat.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeContrat() {
                return typeContrat;
            }

            /**
             * Définit la valeur de la propriété typeContrat.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeContrat(String value) {
                this.typeContrat = value;
            }

            /**
             * Obtient la valeur de la propriété dateEntree.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateEntree() {
                return dateEntree;
            }

            /**
             * Définit la valeur de la propriété dateEntree.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateEntree(JAXBElement<XMLGregorianCalendar> value) {
                this.dateEntree = value;
            }

            /**
             * Obtient la valeur de la propriété dateSortie.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateSortie() {
                return dateSortie;
            }

            /**
             * Définit la valeur de la propriété dateSortie.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateSortie(JAXBElement<XMLGregorianCalendar> value) {
                this.dateSortie = value;
            }

            /**
             * Obtient la valeur de la propriété motifSortie.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getMotifSortie() {
                return motifSortie;
            }

            /**
             * Définit la valeur de la propriété motifSortie.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setMotifSortie(String value) {
                this.motifSortie = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssCssPf1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssCssPf1() {
                return totSalAssCssPf1;
            }

            /**
             * Définit la valeur de la propriété totSalAssCssPf1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssCssPf1(JAXBElement<BigDecimal> value) {
                this.totSalAssCssPf1 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssCssAtmp1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssCssAtmp1() {
                return totSalAssCssAtmp1;
            }

            /**
             * Définit la valeur de la propriété totSalAssCssAtmp1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssCssAtmp1(JAXBElement<BigDecimal> value) {
                this.totSalAssCssAtmp1 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssIpresRg1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssIpresRg1() {
                return totSalAssIpresRg1;
            }

            /**
             * Définit la valeur de la propriété totSalAssIpresRg1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssIpresRg1(JAXBElement<BigDecimal> value) {
                this.totSalAssIpresRg1 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssIpresRcc1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssIpresRcc1() {
                return totSalAssIpresRcc1;
            }

            /**
             * Définit la valeur de la propriété totSalAssIpresRcc1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssIpresRcc1(JAXBElement<BigDecimal> value) {
                this.totSalAssIpresRcc1 = value;
            }

            /**
             * Obtient la valeur de la propriété salaireBrut1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getSalaireBrut1() {
                return salaireBrut1;
            }

            /**
             * Définit la valeur de la propriété salaireBrut1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setSalaireBrut1(JAXBElement<BigDecimal> value) {
                this.salaireBrut1 = value;
            }

            /**
             * Obtient la valeur de la propriété nombreJours1.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNombreJours1() {
                return nombreJours1;
            }

            /**
             * Définit la valeur de la propriété nombreJours1.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNombreJours1(BigDecimal value) {
                this.nombreJours1 = value;
            }

            /**
             * Obtient la valeur de la propriété nombreHeures1.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNombreHeures1() {
                return nombreHeures1;
            }

            /**
             * Définit la valeur de la propriété nombreHeures1.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNombreHeures1(BigDecimal value) {
                this.nombreHeures1 = value;
            }

            /**
             * Obtient la valeur de la propriété tempsTravail1.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTempsTravail1() {
                return tempsTravail1;
            }

            /**
             * Définit la valeur de la propriété tempsTravail1.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTempsTravail1(String value) {
                this.tempsTravail1 = value;
            }

            /**
             * Obtient la valeur de la propriété trancheTravail1.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTrancheTravail1() {
                return trancheTravail1;
            }

            /**
             * Définit la valeur de la propriété trancheTravail1.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTrancheTravail1(String value) {
                this.trancheTravail1 = value;
            }

            /**
             * Obtient la valeur de la propriété regimeGeneral1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getRegimeGeneral1() {
                return regimeGeneral1;
            }

            /**
             * Définit la valeur de la propriété regimeGeneral1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setRegimeGeneral1(JAXBElement<Boolean> value) {
                this.regimeGeneral1 = value;
            }

            /**
             * Obtient la valeur de la propriété regimCompCadre1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getRegimCompCadre1() {
                return regimCompCadre1;
            }

            /**
             * Définit la valeur de la propriété regimCompCadre1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setRegimCompCadre1(JAXBElement<Boolean> value) {
                this.regimCompCadre1 = value;
            }

            /**
             * Obtient la valeur de la propriété dateEffetRegimeCadre1.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateEffetRegimeCadre1() {
                return dateEffetRegimeCadre1;
            }

            /**
             * Définit la valeur de la propriété dateEffetRegimeCadre1.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateEffetRegimeCadre1(JAXBElement<XMLGregorianCalendar> value) {
                this.dateEffetRegimeCadre1 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssCssPf2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssCssPf2() {
                return totSalAssCssPf2;
            }

            /**
             * Définit la valeur de la propriété totSalAssCssPf2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssCssPf2(JAXBElement<BigDecimal> value) {
                this.totSalAssCssPf2 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssCssAtmp2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssCssAtmp2() {
                return totSalAssCssAtmp2;
            }

            /**
             * Définit la valeur de la propriété totSalAssCssAtmp2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssCssAtmp2(JAXBElement<BigDecimal> value) {
                this.totSalAssCssAtmp2 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssIpresRg2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssIpresRg2() {
                return totSalAssIpresRg2;
            }

            /**
             * Définit la valeur de la propriété totSalAssIpresRg2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssIpresRg2(JAXBElement<BigDecimal> value) {
                this.totSalAssIpresRg2 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssIpresRcc2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssIpresRcc2() {
                return totSalAssIpresRcc2;
            }

            /**
             * Définit la valeur de la propriété totSalAssIpresRcc2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssIpresRcc2(JAXBElement<BigDecimal> value) {
                this.totSalAssIpresRcc2 = value;
            }

            /**
             * Obtient la valeur de la propriété salaireBrut2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getSalaireBrut2() {
                return salaireBrut2;
            }

            /**
             * Définit la valeur de la propriété salaireBrut2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setSalaireBrut2(JAXBElement<BigDecimal> value) {
                this.salaireBrut2 = value;
            }

            /**
             * Obtient la valeur de la propriété nombreJours2.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNombreJours2() {
                return nombreJours2;
            }

            /**
             * Définit la valeur de la propriété nombreJours2.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNombreJours2(BigDecimal value) {
                this.nombreJours2 = value;
            }

            /**
             * Obtient la valeur de la propriété nombreHeures2.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNombreHeures2() {
                return nombreHeures2;
            }

            /**
             * Définit la valeur de la propriété nombreHeures2.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNombreHeures2(BigDecimal value) {
                this.nombreHeures2 = value;
            }

            /**
             * Obtient la valeur de la propriété tempsTravail2.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTempsTravail2() {
                return tempsTravail2;
            }

            /**
             * Définit la valeur de la propriété tempsTravail2.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTempsTravail2(String value) {
                this.tempsTravail2 = value;
            }

            /**
             * Obtient la valeur de la propriété trancheTravail2.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTrancheTravail2() {
                return trancheTravail2;
            }

            /**
             * Définit la valeur de la propriété trancheTravail2.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTrancheTravail2(String value) {
                this.trancheTravail2 = value;
            }

            /**
             * Obtient la valeur de la propriété regimeGeneral2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getRegimeGeneral2() {
                return regimeGeneral2;
            }

            /**
             * Définit la valeur de la propriété regimeGeneral2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setRegimeGeneral2(JAXBElement<Boolean> value) {
                this.regimeGeneral2 = value;
            }

            /**
             * Obtient la valeur de la propriété regimCompCadre2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getRegimCompCadre2() {
                return regimCompCadre2;
            }

            /**
             * Définit la valeur de la propriété regimCompCadre2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setRegimCompCadre2(JAXBElement<Boolean> value) {
                this.regimCompCadre2 = value;
            }

            /**
             * Obtient la valeur de la propriété dateEffetRegimeCadre2.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateEffetRegimeCadre2() {
                return dateEffetRegimeCadre2;
            }

            /**
             * Définit la valeur de la propriété dateEffetRegimeCadre2.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateEffetRegimeCadre2(JAXBElement<XMLGregorianCalendar> value) {
                this.dateEffetRegimeCadre2 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssCssPf3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssCssPf3() {
                return totSalAssCssPf3;
            }

            /**
             * Définit la valeur de la propriété totSalAssCssPf3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssCssPf3(JAXBElement<BigDecimal> value) {
                this.totSalAssCssPf3 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssCssAtmp3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssCssAtmp3() {
                return totSalAssCssAtmp3;
            }

            /**
             * Définit la valeur de la propriété totSalAssCssAtmp3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssCssAtmp3(JAXBElement<BigDecimal> value) {
                this.totSalAssCssAtmp3 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssIpresRg3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssIpresRg3() {
                return totSalAssIpresRg3;
            }

            /**
             * Définit la valeur de la propriété totSalAssIpresRg3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssIpresRg3(JAXBElement<BigDecimal> value) {
                this.totSalAssIpresRg3 = value;
            }

            /**
             * Obtient la valeur de la propriété totSalAssIpresRcc3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getTotSalAssIpresRcc3() {
                return totSalAssIpresRcc3;
            }

            /**
             * Définit la valeur de la propriété totSalAssIpresRcc3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setTotSalAssIpresRcc3(JAXBElement<BigDecimal> value) {
                this.totSalAssIpresRcc3 = value;
            }

            /**
             * Obtient la valeur de la propriété salaireBrut3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getSalaireBrut3() {
                return salaireBrut3;
            }

            /**
             * Définit la valeur de la propriété salaireBrut3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setSalaireBrut3(JAXBElement<BigDecimal> value) {
                this.salaireBrut3 = value;
            }

            /**
             * Obtient la valeur de la propriété nombreJours3.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNombreJours3() {
                return nombreJours3;
            }

            /**
             * Définit la valeur de la propriété nombreJours3.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNombreJours3(BigDecimal value) {
                this.nombreJours3 = value;
            }

            /**
             * Obtient la valeur de la propriété nombreHeures3.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getNombreHeures3() {
                return nombreHeures3;
            }

            /**
             * Définit la valeur de la propriété nombreHeures3.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setNombreHeures3(BigDecimal value) {
                this.nombreHeures3 = value;
            }

            /**
             * Obtient la valeur de la propriété tempsTravail3.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTempsTravail3() {
                return tempsTravail3;
            }

            /**
             * Définit la valeur de la propriété tempsTravail3.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTempsTravail3(String value) {
                this.tempsTravail3 = value;
            }

            /**
             * Obtient la valeur de la propriété trancheTravail3.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTrancheTravail3() {
                return trancheTravail3;
            }

            /**
             * Définit la valeur de la propriété trancheTravail3.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTrancheTravail3(String value) {
                this.trancheTravail3 = value;
            }

            /**
             * Obtient la valeur de la propriété regimeGeneral3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getRegimeGeneral3() {
                return regimeGeneral3;
            }

            /**
             * Définit la valeur de la propriété regimeGeneral3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setRegimeGeneral3(JAXBElement<Boolean> value) {
                this.regimeGeneral3 = value;
            }

            /**
             * Obtient la valeur de la propriété regimCompCadre3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public JAXBElement<Boolean> getRegimCompCadre3() {
                return regimCompCadre3;
            }

            /**
             * Définit la valeur de la propriété regimCompCadre3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
             *
             */
            public void setRegimCompCadre3(JAXBElement<Boolean> value) {
                this.regimCompCadre3 = value;
            }

            /**
             * Obtient la valeur de la propriété dateEffetRegimeCadre3.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public JAXBElement<XMLGregorianCalendar> getDateEffetRegimeCadre3() {
                return dateEffetRegimeCadre3;
            }

            /**
             * Définit la valeur de la propriété dateEffetRegimeCadre3.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *
             */
            public void setDateEffetRegimeCadre3(JAXBElement<XMLGregorianCalendar> value) {
                this.dateEffetRegimeCadre3 = value;
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
         *         &lt;element name="totalNouvSalaries" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minExclusive value="-9999999999"/>
         *               &lt;maxExclusive value="9999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="totalSalaries">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;minInclusive value="0"/>
         *               &lt;maxExclusive value="999999999999999"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="cumulTotSalAssIpresRg" type="{http://ouaf.oracle.com/}money"/>
         *         &lt;element name="cumulTotSalAssIpresRcc" type="{http://ouaf.oracle.com/}money"/>
         *         &lt;element name="cumulTotSalAssCssPf" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="cumulTotSalAssCssAtmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="totalSalVerses" type="{http://ouaf.oracle.com/}money"/>
         *         &lt;element name="mntCotPfCalcParEmployeur" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="mntCotAtMpCalcParEmployeur" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
         *         &lt;element name="mntCotRgCalcParEmployeur" type="{http://ouaf.oracle.com/}money"/>
         *         &lt;element name="mntCotRccCalcParEmployeur" type="{http://ouaf.oracle.com/}money"/>
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
            "totalNouvSalaries",
            "totalSalaries",
            "cumulTotSalAssIpresRg",
            "cumulTotSalAssIpresRcc",
            "cumulTotSalAssCssPf",
            "cumulTotSalAssCssAtmp",
            "totalSalVerses",
            "mntCotPfCalcParEmployeur",
            "mntCotAtMpCalcParEmployeur",
            "mntCotRgCalcParEmployeur",
            "mntCotRccCalcParEmployeur"
        })
        public static class Synthese {

            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
            protected BigDecimal totalNouvSalaries;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true)
            protected BigDecimal totalSalaries;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            protected BigDecimal cumulTotSalAssIpresRg;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            protected BigDecimal cumulTotSalAssIpresRcc;
            @XmlElementRef(name = "cumulTotSalAssCssPf", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> cumulTotSalAssCssPf;
            @XmlElementRef(name = "cumulTotSalAssCssAtmp", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> cumulTotSalAssCssAtmp;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            protected BigDecimal totalSalVerses;
            @XmlElementRef(name = "mntCotPfCalcParEmployeur", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> mntCotPfCalcParEmployeur;
            @XmlElementRef(name = "mntCotAtMpCalcParEmployeur", namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> mntCotAtMpCalcParEmployeur;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            protected BigDecimal mntCotRgCalcParEmployeur;
            @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd", required = true, nillable = true)
            protected BigDecimal mntCotRccCalcParEmployeur;

            /**
             * Obtient la valeur de la propriété totalNouvSalaries.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getTotalNouvSalaries() {
                return totalNouvSalaries;
            }

            /**
             * Définit la valeur de la propriété totalNouvSalaries.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setTotalNouvSalaries(BigDecimal value) {
                this.totalNouvSalaries = value;
            }

            /**
             * Obtient la valeur de la propriété totalSalaries.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getTotalSalaries() {
                return totalSalaries;
            }

            /**
             * Définit la valeur de la propriété totalSalaries.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setTotalSalaries(BigDecimal value) {
                this.totalSalaries = value;
            }

            /**
             * Obtient la valeur de la propriété cumulTotSalAssIpresRg.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getCumulTotSalAssIpresRg() {
                return cumulTotSalAssIpresRg;
            }

            /**
             * Définit la valeur de la propriété cumulTotSalAssIpresRg.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setCumulTotSalAssIpresRg(BigDecimal value) {
                this.cumulTotSalAssIpresRg = value;
            }

            /**
             * Obtient la valeur de la propriété cumulTotSalAssIpresRcc.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getCumulTotSalAssIpresRcc() {
                return cumulTotSalAssIpresRcc;
            }

            /**
             * Définit la valeur de la propriété cumulTotSalAssIpresRcc.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setCumulTotSalAssIpresRcc(BigDecimal value) {
                this.cumulTotSalAssIpresRcc = value;
            }

            /**
             * Obtient la valeur de la propriété cumulTotSalAssCssPf.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getCumulTotSalAssCssPf() {
                return cumulTotSalAssCssPf;
            }

            /**
             * Définit la valeur de la propriété cumulTotSalAssCssPf.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setCumulTotSalAssCssPf(JAXBElement<BigDecimal> value) {
                this.cumulTotSalAssCssPf = value;
            }

            /**
             * Obtient la valeur de la propriété cumulTotSalAssCssAtmp.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getCumulTotSalAssCssAtmp() {
                return cumulTotSalAssCssAtmp;
            }

            /**
             * Définit la valeur de la propriété cumulTotSalAssCssAtmp.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setCumulTotSalAssCssAtmp(JAXBElement<BigDecimal> value) {
                this.cumulTotSalAssCssAtmp = value;
            }

            /**
             * Obtient la valeur de la propriété totalSalVerses.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getTotalSalVerses() {
                return totalSalVerses;
            }

            /**
             * Définit la valeur de la propriété totalSalVerses.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setTotalSalVerses(BigDecimal value) {
                this.totalSalVerses = value;
            }

            /**
             * Obtient la valeur de la propriété mntCotPfCalcParEmployeur.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getMntCotPfCalcParEmployeur() {
                return mntCotPfCalcParEmployeur;
            }

            /**
             * Définit la valeur de la propriété mntCotPfCalcParEmployeur.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setMntCotPfCalcParEmployeur(JAXBElement<BigDecimal> value) {
                this.mntCotPfCalcParEmployeur = value;
            }

            /**
             * Obtient la valeur de la propriété mntCotAtMpCalcParEmployeur.
             *
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public JAXBElement<BigDecimal> getMntCotAtMpCalcParEmployeur() {
                return mntCotAtMpCalcParEmployeur;
            }

            /**
             * Définit la valeur de la propriété mntCotAtMpCalcParEmployeur.
             *
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *
             */
            public void setMntCotAtMpCalcParEmployeur(JAXBElement<BigDecimal> value) {
                this.mntCotAtMpCalcParEmployeur = value;
            }

            /**
             * Obtient la valeur de la propriété mntCotRgCalcParEmployeur.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getMntCotRgCalcParEmployeur() {
                return mntCotRgCalcParEmployeur;
            }

            /**
             * Définit la valeur de la propriété mntCotRgCalcParEmployeur.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setMntCotRgCalcParEmployeur(BigDecimal value) {
                this.mntCotRgCalcParEmployeur = value;
            }

            /**
             * Obtient la valeur de la propriété mntCotRccCalcParEmployeur.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getMntCotRccCalcParEmployeur() {
                return mntCotRccCalcParEmployeur;
            }

            /**
             * Définit la valeur de la propriété mntCotRccCalcParEmployeur.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setMntCotRccCalcParEmployeur(BigDecimal value) {
                this.mntCotRccCalcParEmployeur = value;
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
     *         &lt;element name="formId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "formId"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/DNS_INBOUND_SERVICE.xsd")
        protected String formId;

        /**
         * Obtient la valeur de la propriété formId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getFormId() {
            return formId;
        }

        /**
         * Définit la valeur de la propriété formId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setFormId(String value) {
            this.formId = value;
        }

    }

}
