
package com.secusociale.portail.service.soap.independant;

import javax.xml.bind.JAXBElement;
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
 *                   &lt;element name="typeDemandeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="carteConsulaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="carteIdentiteNationale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="passport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="issueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="landLineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="businessSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="mainLineOfBusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="zoneCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="zoneIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sectorCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sectorIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="agenceCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="agenceIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="documents" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="demandeEcrit" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="formDemande" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="registreCommerce" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="declarationEtablissement" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="photocopieStatus" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="decretMinisteriel" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="avisImmatriculation" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="dmt" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="contratsTravail" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="cni" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="carteIdentiteConsulaire" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="etatRecensement" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="attestationChomage" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="bulletinsSalaire" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="cessationActivity" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="carteNationaleIdentite" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="derniersBulletins" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="manuscriteAdressee" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="passportDoc" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="pieceIdDoc" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="pieceIdGerantDoc" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="output" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="formulaireId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dossierId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sectorCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sectorIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "CM-CrtIndForXAI")
public class CMCrtIndForXAI {

    protected Input input;
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
     *         &lt;element name="typeDemandeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="carteConsulaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="carteIdentiteNationale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="passport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="issueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="landLineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="businessSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="mainLineOfBusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="zoneCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="zoneIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sectorCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sectorIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="agenceCSS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="agenceIPRES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="documents" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="demandeEcrit" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="formDemande" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="registreCommerce" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="declarationEtablissement" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="photocopieStatus" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="decretMinisteriel" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="avisImmatriculation" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="dmt" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="contratsTravail" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="cni" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="carteIdentiteConsulaire" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="etatRecensement" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="attestationChomage" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="bulletinsSalaire" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="cessationActivity" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="carteNationaleIdentite" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="derniersBulletins" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="manuscriteAdressee" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="passportDoc" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="pieceIdDoc" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="pieceIdGerantDoc" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "typeDemandeur",
        "nom",
        "prenom",
        "typePieceIdentite",
        "carteConsulaire",
        "carteIdentiteNationale",
        "passport",
        "nationalite",
        "issueDate",
        "expiryDate",
        "dateOfBirth",
        "country",
        "cityOfBirth",
        "landLineNumber",
        "mobileNumber",
        "email",
        "businessSector",
        "mainLineOfBusiness",
        "region",
        "department",
        "arrondissement",
        "commune",
        "quartier",
        "address",
        "tauxAt",
        "zoneCSS",
        "zoneIPRES",
        "sectorCSS",
        "sectorIPRES",
        "agenceCSS",
        "agenceIPRES",
        "documents"
    })
    public static class Input {

        protected String typeDemandeur;
        protected String nom;
        protected String prenom;
        protected String typePieceIdentite;
        protected String carteConsulaire;
        protected String carteIdentiteNationale;
        protected String passport;
        protected String nationalite;
        protected String issueDate;
        protected String expiryDate;
        protected String dateOfBirth;
        protected String country;
        protected String cityOfBirth;
        protected String landLineNumber;
        protected String mobileNumber;
        protected String email;
        protected String businessSector;
        protected String mainLineOfBusiness;
        protected String region;
        protected String department;
        protected String arrondissement;
        protected String commune;
        protected String quartier;
        protected String address;
        protected String tauxAt;
        protected String zoneCSS;
        protected String zoneIPRES;
        protected String sectorCSS;
        protected String sectorIPRES;
        protected String agenceCSS;
        protected String agenceIPRES;
        protected Documents documents;

        /**
         * Obtient la valeur de la propriété typeDemandeur.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTypeDemandeur() {
            return typeDemandeur;
        }

        /**
         * Définit la valeur de la propriété typeDemandeur.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTypeDemandeur(String value) {
            this.typeDemandeur = value;
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
         * Obtient la valeur de la propriété carteConsulaire.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCarteConsulaire() {
            return carteConsulaire;
        }

        /**
         * Définit la valeur de la propriété carteConsulaire.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCarteConsulaire(String value) {
            this.carteConsulaire = value;
        }

        /**
         * Obtient la valeur de la propriété carteIdentiteNationale.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCarteIdentiteNationale() {
            return carteIdentiteNationale;
        }

        /**
         * Définit la valeur de la propriété carteIdentiteNationale.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCarteIdentiteNationale(String value) {
            this.carteIdentiteNationale = value;
        }

        /**
         * Obtient la valeur de la propriété passport.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPassport() {
            return passport;
        }

        /**
         * Définit la valeur de la propriété passport.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPassport(String value) {
            this.passport = value;
        }

        /**
         * Obtient la valeur de la propriété nationalite.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNationalite() {
            return nationalite;
        }

        /**
         * Définit la valeur de la propriété nationalite.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNationalite(String value) {
            this.nationalite = value;
        }

        /**
         * Obtient la valeur de la propriété issueDate.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIssueDate() {
            return issueDate;
        }

        /**
         * Définit la valeur de la propriété issueDate.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIssueDate(String value) {
            this.issueDate = value;
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
         * Obtient la valeur de la propriété dateOfBirth.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDateOfBirth() {
            return dateOfBirth;
        }

        /**
         * Définit la valeur de la propriété dateOfBirth.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDateOfBirth(String value) {
            this.dateOfBirth = value;
        }

        /**
         * Obtient la valeur de la propriété country.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCountry() {
            return country;
        }

        /**
         * Définit la valeur de la propriété country.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCountry(String value) {
            this.country = value;
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
         * Obtient la valeur de la propriété tauxAt.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTauxAt() {
            return tauxAt;
        }

        /**
         * Définit la valeur de la propriété tauxAt.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTauxAt(String value) {
            this.tauxAt = value;
        }

        /**
         * Obtient la valeur de la propriété zoneCSS.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getZoneCSS() {
            return zoneCSS;
        }

        /**
         * Définit la valeur de la propriété zoneCSS.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setZoneCSS(String value) {
            this.zoneCSS = value;
        }

        /**
         * Obtient la valeur de la propriété zoneIPRES.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getZoneIPRES() {
            return zoneIPRES;
        }

        /**
         * Définit la valeur de la propriété zoneIPRES.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setZoneIPRES(String value) {
            this.zoneIPRES = value;
        }

        /**
         * Obtient la valeur de la propriété sectorCSS.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSectorCSS() {
            return sectorCSS;
        }

        /**
         * Définit la valeur de la propriété sectorCSS.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSectorCSS(String value) {
            this.sectorCSS = value;
        }

        /**
         * Obtient la valeur de la propriété sectorIPRES.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSectorIPRES() {
            return sectorIPRES;
        }

        /**
         * Définit la valeur de la propriété sectorIPRES.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSectorIPRES(String value) {
            this.sectorIPRES = value;
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
         * Obtient la valeur de la propriété documents.
         *
         * @return
         *     possible object is
         *     {@link Documents }
         *
         */
        public Documents getDocuments() {
            return documents;
        }

        /**
         * Définit la valeur de la propriété documents.
         *
         * @param value
         *     allowed object is
         *     {@link Documents }
         *
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
         *         &lt;element name="demandeEcrit" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="formDemande" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="registreCommerce" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="declarationEtablissement" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="photocopieStatus" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="decretMinisteriel" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="avisImmatriculation" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="dmt" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="contratsTravail" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="cni" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="carteIdentiteConsulaire" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="etatRecensement" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="attestationChomage" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="bulletinsSalaire" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="cessationActivity" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="carteNationaleIdentite" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="derniersBulletins" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="manuscriteAdressee" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="passportDoc" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="pieceIdDoc" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="pieceIdGerantDoc" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "demandeEcrit",
            "formDemande",
            "registreCommerce",
            "declarationEtablissement",
            "photocopieStatus",
            "decretMinisteriel",
            "avisImmatriculation",
            "dmt",
            "contratsTravail",
            "cni",
            "carteIdentiteConsulaire",
            "etatRecensement",
            "attestationChomage",
            "bulletinsSalaire",
            "cessationActivity",
            "carteNationaleIdentite",
            "derniersBulletins",
            "manuscriteAdressee",
            "passportDoc",
            "pieceIdDoc",
            "pieceIdGerantDoc"
        })
        public static class Documents {

            protected DemandeEcrit demandeEcrit;
            protected FormDemande formDemande;
            protected RegistreCommerce registreCommerce;
            protected DeclarationEtablissement declarationEtablissement;
            protected PhotocopieStatus photocopieStatus;
            protected DecretMinisteriel decretMinisteriel;
            protected AvisImmatriculation avisImmatriculation;
            protected Dmt dmt;
            protected ContratsTravail contratsTravail;
            protected Cni cni;
            protected CarteIdentiteConsulaire carteIdentiteConsulaire;
            protected EtatRecensement etatRecensement;
            protected AttestationChomage attestationChomage;
            protected BulletinsSalaire bulletinsSalaire;
            protected CessationActivity cessationActivity;
            protected CarteNationaleIdentite carteNationaleIdentite;
            protected DerniersBulletins derniersBulletins;
            protected ManuscriteAdressee manuscriteAdressee;
            protected PassportDoc passportDoc;
            protected PieceIdDoc pieceIdDoc;
            protected PieceIdGerantDoc pieceIdGerantDoc;

            /**
             * Obtient la valeur de la propriété demandeEcrit.
             *
             * @return
             *     possible object is
             *     {@link DemandeEcrit }
             *
             */
            public DemandeEcrit getDemandeEcrit() {
                return demandeEcrit;
            }

            /**
             * Définit la valeur de la propriété demandeEcrit.
             *
             * @param value
             *     allowed object is
             *     {@link DemandeEcrit }
             *
             */
            public void setDemandeEcrit(DemandeEcrit value) {
                this.demandeEcrit = value;
            }

            /**
             * Obtient la valeur de la propriété formDemande.
             *
             * @return
             *     possible object is
             *     {@link FormDemande }
             *
             */
            public FormDemande getFormDemande() {
                return formDemande;
            }

            /**
             * Définit la valeur de la propriété formDemande.
             *
             * @param value
             *     allowed object is
             *     {@link FormDemande }
             *
             */
            public void setFormDemande(FormDemande value) {
                this.formDemande = value;
            }

            /**
             * Obtient la valeur de la propriété registreCommerce.
             *
             * @return
             *     possible object is
             *     {@link RegistreCommerce }
             *
             */
            public RegistreCommerce getRegistreCommerce() {
                return registreCommerce;
            }

            /**
             * Définit la valeur de la propriété registreCommerce.
             *
             * @param value
             *     allowed object is
             *     {@link RegistreCommerce }
             *
             */
            public void setRegistreCommerce(RegistreCommerce value) {
                this.registreCommerce = value;
            }

            /**
             * Obtient la valeur de la propriété declarationEtablissement.
             *
             * @return
             *     possible object is
             *     {@link DeclarationEtablissement }
             *
             */
            public DeclarationEtablissement getDeclarationEtablissement() {
                return declarationEtablissement;
            }

            /**
             * Définit la valeur de la propriété declarationEtablissement.
             *
             * @param value
             *     allowed object is
             *     {@link DeclarationEtablissement }
             *
             */
            public void setDeclarationEtablissement(DeclarationEtablissement value) {
                this.declarationEtablissement = value;
            }

            /**
             * Obtient la valeur de la propriété photocopieStatus.
             *
             * @return
             *     possible object is
             *     {@link PhotocopieStatus }
             *
             */
            public PhotocopieStatus getPhotocopieStatus() {
                return photocopieStatus;
            }

            /**
             * Définit la valeur de la propriété photocopieStatus.
             *
             * @param value
             *     allowed object is
             *     {@link PhotocopieStatus }
             *
             */
            public void setPhotocopieStatus(PhotocopieStatus value) {
                this.photocopieStatus = value;
            }

            /**
             * Obtient la valeur de la propriété decretMinisteriel.
             *
             * @return
             *     possible object is
             *     {@link DecretMinisteriel }
             *
             */
            public DecretMinisteriel getDecretMinisteriel() {
                return decretMinisteriel;
            }

            /**
             * Définit la valeur de la propriété decretMinisteriel.
             *
             * @param value
             *     allowed object is
             *     {@link DecretMinisteriel }
             *
             */
            public void setDecretMinisteriel(DecretMinisteriel value) {
                this.decretMinisteriel = value;
            }

            /**
             * Obtient la valeur de la propriété avisImmatriculation.
             *
             * @return
             *     possible object is
             *     {@link AvisImmatriculation }
             *
             */
            public AvisImmatriculation getAvisImmatriculation() {
                return avisImmatriculation;
            }

            /**
             * Définit la valeur de la propriété avisImmatriculation.
             *
             * @param value
             *     allowed object is
             *     {@link AvisImmatriculation }
             *
             */
            public void setAvisImmatriculation(AvisImmatriculation value) {
                this.avisImmatriculation = value;
            }

            /**
             * Obtient la valeur de la propriété dmt.
             *
             * @return
             *     possible object is
             *     {@link Dmt }
             *
             */
            public Dmt getDmt() {
                return dmt;
            }

            /**
             * Définit la valeur de la propriété dmt.
             *
             * @param value
             *     allowed object is
             *     {@link Dmt }
             *
             */
            public void setDmt(Dmt value) {
                this.dmt = value;
            }

            /**
             * Obtient la valeur de la propriété contratsTravail.
             *
             * @return
             *     possible object is
             *     {@link ContratsTravail }
             *
             */
            public ContratsTravail getContratsTravail() {
                return contratsTravail;
            }

            /**
             * Définit la valeur de la propriété contratsTravail.
             *
             * @param value
             *     allowed object is
             *     {@link ContratsTravail }
             *
             */
            public void setContratsTravail(ContratsTravail value) {
                this.contratsTravail = value;
            }

            /**
             * Obtient la valeur de la propriété cni.
             *
             * @return
             *     possible object is
             *     {@link Cni }
             *
             */
            public Cni getCni() {
                return cni;
            }

            /**
             * Définit la valeur de la propriété cni.
             *
             * @param value
             *     allowed object is
             *     {@link Cni }
             *
             */
            public void setCni(Cni value) {
                this.cni = value;
            }

            /**
             * Obtient la valeur de la propriété carteIdentiteConsulaire.
             *
             * @return
             *     possible object is
             *     {@link CarteIdentiteConsulaire }
             *
             */
            public CarteIdentiteConsulaire getCarteIdentiteConsulaire() {
                return carteIdentiteConsulaire;
            }

            /**
             * Définit la valeur de la propriété carteIdentiteConsulaire.
             *
             * @param value
             *     allowed object is
             *     {@link CarteIdentiteConsulaire }
             *
             */
            public void setCarteIdentiteConsulaire(CarteIdentiteConsulaire value) {
                this.carteIdentiteConsulaire = value;
            }

            /**
             * Obtient la valeur de la propriété etatRecensement.
             *
             * @return
             *     possible object is
             *     {@link EtatRecensement }
             *
             */
            public EtatRecensement getEtatRecensement() {
                return etatRecensement;
            }

            /**
             * Définit la valeur de la propriété etatRecensement.
             *
             * @param value
             *     allowed object is
             *     {@link EtatRecensement }
             *
             */
            public void setEtatRecensement(EtatRecensement value) {
                this.etatRecensement = value;
            }

            /**
             * Obtient la valeur de la propriété attestationChomage.
             *
             * @return
             *     possible object is
             *     {@link AttestationChomage }
             *
             */
            public AttestationChomage getAttestationChomage() {
                return attestationChomage;
            }

            /**
             * Définit la valeur de la propriété attestationChomage.
             *
             * @param value
             *     allowed object is
             *     {@link AttestationChomage }
             *
             */
            public void setAttestationChomage(AttestationChomage value) {
                this.attestationChomage = value;
            }

            /**
             * Obtient la valeur de la propriété bulletinsSalaire.
             *
             * @return
             *     possible object is
             *     {@link BulletinsSalaire }
             *
             */
            public BulletinsSalaire getBulletinsSalaire() {
                return bulletinsSalaire;
            }

            /**
             * Définit la valeur de la propriété bulletinsSalaire.
             *
             * @param value
             *     allowed object is
             *     {@link BulletinsSalaire }
             *
             */
            public void setBulletinsSalaire(BulletinsSalaire value) {
                this.bulletinsSalaire = value;
            }

            /**
             * Obtient la valeur de la propriété cessationActivity.
             *
             * @return
             *     possible object is
             *     {@link CessationActivity }
             *
             */
            public CessationActivity getCessationActivity() {
                return cessationActivity;
            }

            /**
             * Définit la valeur de la propriété cessationActivity.
             *
             * @param value
             *     allowed object is
             *     {@link CessationActivity }
             *
             */
            public void setCessationActivity(CessationActivity value) {
                this.cessationActivity = value;
            }

            /**
             * Obtient la valeur de la propriété carteNationaleIdentite.
             *
             * @return
             *     possible object is
             *     {@link CarteNationaleIdentite }
             *
             */
            public CarteNationaleIdentite getCarteNationaleIdentite() {
                return carteNationaleIdentite;
            }

            /**
             * Définit la valeur de la propriété carteNationaleIdentite.
             *
             * @param value
             *     allowed object is
             *     {@link CarteNationaleIdentite }
             *
             */
            public void setCarteNationaleIdentite(CarteNationaleIdentite value) {
                this.carteNationaleIdentite = value;
            }

            /**
             * Obtient la valeur de la propriété derniersBulletins.
             *
             * @return
             *     possible object is
             *     {@link DerniersBulletins }
             *
             */
            public DerniersBulletins getDerniersBulletins() {
                return derniersBulletins;
            }

            /**
             * Définit la valeur de la propriété derniersBulletins.
             *
             * @param value
             *     allowed object is
             *     {@link DerniersBulletins }
             *
             */
            public void setDerniersBulletins(DerniersBulletins value) {
                this.derniersBulletins = value;
            }

            /**
             * Obtient la valeur de la propriété manuscriteAdressee.
             *
             * @return
             *     possible object is
             *     {@link ManuscriteAdressee }
             *
             */
            public ManuscriteAdressee getManuscriteAdressee() {
                return manuscriteAdressee;
            }

            /**
             * Définit la valeur de la propriété manuscriteAdressee.
             *
             * @param value
             *     allowed object is
             *     {@link ManuscriteAdressee }
             *
             */
            public void setManuscriteAdressee(ManuscriteAdressee value) {
                this.manuscriteAdressee = value;
            }

            /**
             * Obtient la valeur de la propriété passportDoc.
             *
             * @return
             *     possible object is
             *     {@link PassportDoc }
             *
             */
            public PassportDoc getPassportDoc() {
                return passportDoc;
            }

            /**
             * Définit la valeur de la propriété passportDoc.
             *
             * @param value
             *     allowed object is
             *     {@link PassportDoc }
             *
             */
            public void setPassportDoc(PassportDoc value) {
                this.passportDoc = value;
            }

            /**
             * Obtient la valeur de la propriété pieceIdDoc.
             *
             * @return
             *     possible object is
             *     {@link PieceIdDoc }
             *
             */
            public PieceIdDoc getPieceIdDoc() {
                return pieceIdDoc;
            }

            /**
             * Définit la valeur de la propriété pieceIdDoc.
             *
             * @param value
             *     allowed object is
             *     {@link PieceIdDoc }
             *
             */
            public void setPieceIdDoc(PieceIdDoc value) {
                this.pieceIdDoc = value;
            }

            /**
             * Obtient la valeur de la propriété pieceIdGerantDoc.
             *
             * @return
             *     possible object is
             *     {@link PieceIdGerantDoc }
             *
             */
            public PieceIdGerantDoc getPieceIdGerantDoc() {
                return pieceIdGerantDoc;
            }

            /**
             * Définit la valeur de la propriété pieceIdGerantDoc.
             *
             * @param value
             *     allowed object is
             *     {@link PieceIdGerantDoc }
             *
             */
            public void setPieceIdGerantDoc(PieceIdGerantDoc value) {
                this.pieceIdGerantDoc = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class AttestationChomage {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class AvisImmatriculation {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class BulletinsSalaire {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class CarteIdentiteConsulaire {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class CarteNationaleIdentite {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class CessationActivity {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class Cni {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class ContratsTravail {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class DeclarationEtablissement {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class DecretMinisteriel {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class DemandeEcrit {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class DerniersBulletins {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class Dmt {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class EtatRecensement {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class FormDemande {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class ManuscriteAdressee {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class PassportDoc {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class PhotocopieStatus {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class PieceIdDoc {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class PieceIdGerantDoc {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
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
             *         &lt;element name="delivered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "delivered",
                "url"
            })
            public static class RegistreCommerce {

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/CM-CrtIndForXAI.xsd", type = JAXBElement.class, required = false)
                protected JAXBElement<Boolean> delivered;
                protected String url;

                /**
                 * Obtient la valeur de la propriété delivered.
                 *
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public JAXBElement<Boolean> getDelivered() {
                    return delivered;
                }

                /**
                 * Définit la valeur de la propriété delivered.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
                 *
                 */
                public void setDelivered(JAXBElement<Boolean> value) {
                    this.delivered = value;
                }

                /**
                 * Obtient la valeur de la propriété url.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUrl() {
                    return url;
                }

                /**
                 * Définit la valeur de la propriété url.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUrl(String value) {
                    this.url = value;
                }

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
     *         &lt;element name="formulaireId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dossierId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sectorCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sectorIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="agenceCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tauxAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "formulaireId",
        "dossierId",
        "zoneCss",
        "zoneIpres",
        "sectorCss",
        "sectorIpres",
        "agenceCss",
        "agenceIpres",
        "tauxAt"
    })
    public static class Output {

        protected String formulaireId;
        protected String dossierId;
        protected String zoneCss;
        protected String zoneIpres;
        protected String sectorCss;
        protected String sectorIpres;
        protected String agenceCss;
        protected String agenceIpres;
        protected String tauxAt;

        /**
         * Obtient la valeur de la propriété formulaireId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getFormulaireId() {
            return formulaireId;
        }

        /**
         * Définit la valeur de la propriété formulaireId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setFormulaireId(String value) {
            this.formulaireId = value;
        }

        /**
         * Obtient la valeur de la propriété dossierId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDossierId() {
            return dossierId;
        }

        /**
         * Définit la valeur de la propriété dossierId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDossierId(String value) {
            this.dossierId = value;
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
         * Obtient la valeur de la propriété sectorCss.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSectorCss() {
            return sectorCss;
        }

        /**
         * Définit la valeur de la propriété sectorCss.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSectorCss(String value) {
            this.sectorCss = value;
        }

        /**
         * Obtient la valeur de la propriété sectorIpres.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSectorIpres() {
            return sectorIpres;
        }

        /**
         * Définit la valeur de la propriété sectorIpres.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSectorIpres(String value) {
            this.sectorIpres = value;
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

        /**
         * Obtient la valeur de la propriété agenceIpres.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAgenceIpres() {
            return agenceIpres;
        }

        /**
         * Définit la valeur de la propriété agenceIpres.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAgenceIpres(String value) {
            this.agenceIpres = value;
        }

        /**
         * Obtient la valeur de la propriété tauxAt.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTauxAt() {
            return tauxAt;
        }

        /**
         * Définit la valeur de la propriété tauxAt.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTauxAt(String value) {
            this.tauxAt = value;
        }

    }

}
