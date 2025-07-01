
package com.secusociale.portail.service.soap.maintientAffiliation;

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
 *                   &lt;element name="registrationFormInfos" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="receiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="documentLocator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="informationsGenerales" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="typeImmatriculation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="typeEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="recherche" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nincedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numIdNationale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateDelivrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateExpiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="paysDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="villeDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nbrAnneeCot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="telephoneFixe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numeroMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infosSupplementaires" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="secteursIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="zoneGeoIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="registrationFormId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="processFlowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "MAINT-AFF_INBOUND")
public class MAINTAFFINBOUND {

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
     *         &lt;element name="registrationFormInfos" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="receiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="documentLocator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="informationsGenerales" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="typeImmatriculation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="typeEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="recherche" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nincedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numIdNationale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateDelivrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateExpiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="paysDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="villeDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nbrAnneeCot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="telephoneFixe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numeroMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infosSupplementaires" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="secteursIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="zoneGeoIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "registrationFormInfos",
        "informationsGenerales",
        "infosSupplementaires",
        "documents"
    })
    public static class Input {

        protected RegistrationFormInfos registrationFormInfos;
        protected InformationsGenerales informationsGenerales;
        protected InfosSupplementaires infosSupplementaires;
        protected Documents documents;

        /**
         * Obtient la valeur de la propriété registrationFormInfos.
         *
         * @return
         *     possible object is
         *     {@link RegistrationFormInfos }
         *
         */
        public RegistrationFormInfos getRegistrationFormInfos() {
            return registrationFormInfos;
        }

        /**
         * Définit la valeur de la propriété registrationFormInfos.
         *
         * @param value
         *     allowed object is
         *     {@link RegistrationFormInfos }
         *
         */
        public void setRegistrationFormInfos(RegistrationFormInfos value) {
            this.registrationFormInfos = value;
        }

        /**
         * Obtient la valeur de la propriété informationsGenerales.
         *
         * @return
         *     possible object is
         *     {@link InformationsGenerales }
         *
         */
        public InformationsGenerales getInformationsGenerales() {
            return informationsGenerales;
        }

        /**
         * Définit la valeur de la propriété informationsGenerales.
         *
         * @param value
         *     allowed object is
         *     {@link InformationsGenerales }
         *
         */
        public void setInformationsGenerales(InformationsGenerales value) {
            this.informationsGenerales = value;
        }

        /**
         * Obtient la valeur de la propriété infosSupplementaires.
         *
         * @return
         *     possible object is
         *     {@link InfosSupplementaires }
         *
         */
        public InfosSupplementaires getInfosSupplementaires() {
            return infosSupplementaires;
        }

        /**
         * Définit la valeur de la propriété infosSupplementaires.
         *
         * @param value
         *     allowed object is
         *     {@link InfosSupplementaires }
         *
         */
        public void setInfosSupplementaires(InfosSupplementaires value) {
            this.infosSupplementaires = value;
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/MAINT-AFF_INBOUND.xsd", type = JAXBElement.class, required = false)
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
         *         &lt;element name="typeImmatriculation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="typeEmployeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="recherche" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nincedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="numIdNationale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateDelivrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateExpiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="paysDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="villeDeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nbrAnneeCot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="telephoneFixe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="numeroMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "typeImmatriculation",
            "typeEmployeur",
            "recherche",
            "nom",
            "prenom",
            "nationalite",
            "typePieceIdentite",
            "nin",
            "nincedeao",
            "numIdNationale",
            "dateDelivrance",
            "dateExpiration",
            "dateDeNaissance",
            "paysDeNaissance",
            "villeDeNaissance",
            "nbrAnneeCot",
            "telephoneFixe",
            "numeroMobile",
            "email"
        })
        public static class InformationsGenerales {

            protected String typeImmatriculation;
            protected String typeEmployeur;
            protected String recherche;
            protected String nom;
            protected String prenom;
            protected String nationalite;
            protected String typePieceIdentite;
            protected String nin;
            protected String nincedeao;
            protected String numIdNationale;
            protected String dateDelivrance;
            protected String dateExpiration;
            protected String dateDeNaissance;
            protected String paysDeNaissance;
            protected String villeDeNaissance;
            protected String nbrAnneeCot;
            protected String telephoneFixe;
            protected String numeroMobile;
            protected String email;

            /**
             * Obtient la valeur de la propriété typeImmatriculation.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeImmatriculation() {
                return typeImmatriculation;
            }

            /**
             * Définit la valeur de la propriété typeImmatriculation.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeImmatriculation(String value) {
                this.typeImmatriculation = value;
            }

            /**
             * Obtient la valeur de la propriété typeEmployeur.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeEmployeur() {
                return typeEmployeur;
            }

            /**
             * Définit la valeur de la propriété typeEmployeur.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeEmployeur(String value) {
                this.typeEmployeur = value;
            }

            /**
             * Obtient la valeur de la propriété recherche.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRecherche() {
                return recherche;
            }

            /**
             * Définit la valeur de la propriété recherche.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRecherche(String value) {
                this.recherche = value;
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
             * Obtient la valeur de la propriété nincedeao.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNincedeao() {
                return nincedeao;
            }

            /**
             * Définit la valeur de la propriété nincedeao.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNincedeao(String value) {
                this.nincedeao = value;
            }

            /**
             * Obtient la valeur de la propriété numIdNationale.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumIdNationale() {
                return numIdNationale;
            }

            /**
             * Définit la valeur de la propriété numIdNationale.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumIdNationale(String value) {
                this.numIdNationale = value;
            }

            /**
             * Obtient la valeur de la propriété dateDelivrance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDateDelivrance() {
                return dateDelivrance;
            }

            /**
             * Définit la valeur de la propriété dateDelivrance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDateDelivrance(String value) {
                this.dateDelivrance = value;
            }

            /**
             * Obtient la valeur de la propriété dateExpiration.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDateExpiration() {
                return dateExpiration;
            }

            /**
             * Définit la valeur de la propriété dateExpiration.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDateExpiration(String value) {
                this.dateExpiration = value;
            }

            /**
             * Obtient la valeur de la propriété dateDeNaissance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDateDeNaissance() {
                return dateDeNaissance;
            }

            /**
             * Définit la valeur de la propriété dateDeNaissance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDateDeNaissance(String value) {
                this.dateDeNaissance = value;
            }

            /**
             * Obtient la valeur de la propriété paysDeNaissance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPaysDeNaissance() {
                return paysDeNaissance;
            }

            /**
             * Définit la valeur de la propriété paysDeNaissance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPaysDeNaissance(String value) {
                this.paysDeNaissance = value;
            }

            /**
             * Obtient la valeur de la propriété villeDeNaissance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getVilleDeNaissance() {
                return villeDeNaissance;
            }

            /**
             * Définit la valeur de la propriété villeDeNaissance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setVilleDeNaissance(String value) {
                this.villeDeNaissance = value;
            }

            /**
             * Obtient la valeur de la propriété nbrAnneeCot.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNbrAnneeCot() {
                return nbrAnneeCot;
            }

            /**
             * Définit la valeur de la propriété nbrAnneeCot.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNbrAnneeCot(String value) {
                this.nbrAnneeCot = value;
            }

            /**
             * Obtient la valeur de la propriété telephoneFixe.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTelephoneFixe() {
                return telephoneFixe;
            }

            /**
             * Définit la valeur de la propriété telephoneFixe.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTelephoneFixe(String value) {
                this.telephoneFixe = value;
            }

            /**
             * Obtient la valeur de la propriété numeroMobile.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumeroMobile() {
                return numeroMobile;
            }

            /**
             * Définit la valeur de la propriété numeroMobile.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumeroMobile(String value) {
                this.numeroMobile = value;
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
         *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agenceIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="secteursIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="zoneGeoIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "region",
            "department",
            "arrondissement",
            "commune",
            "quartier",
            "adresse",
            "agenceIpres",
            "secteursIpres",
            "zoneGeoIpres"
        })
        public static class InfosSupplementaires {

            protected String region;
            protected String department;
            protected String arrondissement;
            protected String commune;
            protected String quartier;
            protected String adresse;
            protected String agenceIpres;
            protected String secteursIpres;
            protected String zoneGeoIpres;

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
             * Obtient la valeur de la propriété secteursIpres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSecteursIpres() {
                return secteursIpres;
            }

            /**
             * Définit la valeur de la propriété secteursIpres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSecteursIpres(String value) {
                this.secteursIpres = value;
            }

            /**
             * Obtient la valeur de la propriété zoneGeoIpres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getZoneGeoIpres() {
                return zoneGeoIpres;
            }

            /**
             * Définit la valeur de la propriété zoneGeoIpres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setZoneGeoIpres(String value) {
                this.zoneGeoIpres = value;
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
         *         &lt;element name="receiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="documentLocator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "receiveDate",
            "documentLocator"
        })
        public static class RegistrationFormInfos {

            protected String receiveDate;
            protected String documentLocator;

            /**
             * Obtient la valeur de la propriété receiveDate.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getReceiveDate() {
                return receiveDate;
            }

            /**
             * Définit la valeur de la propriété receiveDate.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setReceiveDate(String value) {
                this.receiveDate = value;
            }

            /**
             * Obtient la valeur de la propriété documentLocator.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDocumentLocator() {
                return documentLocator;
            }

            /**
             * Définit la valeur de la propriété documentLocator.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDocumentLocator(String value) {
                this.documentLocator = value;
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
     *         &lt;element name="registrationFormId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "registrationFormId",
        "processFlowId"
    })
    public static class Output {

        protected String registrationFormId;
        protected String processFlowId;

        /**
         * Obtient la valeur de la propriété registrationFormId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRegistrationFormId() {
            return registrationFormId;
        }

        /**
         * Définit la valeur de la propriété registrationFormId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRegistrationFormId(String value) {
            this.registrationFormId = value;
        }

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

}
