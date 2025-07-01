
package com.secusociale.portail.service.soap.domestique;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
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
 *                   &lt;element name="domesticRegistrationForm" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="dateEmbauchePremierSalarie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="regType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ninCedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="countryOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="businessSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="mainLineOfBusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="atRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="sectorCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="sectorIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="agencyCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="agencyIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="employeList" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="rechercheEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nomEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="prenomEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="sexe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="etatCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numRegNaiss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nomPere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="prenomPere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nomMere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="prenomMere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ninCedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="numPieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="delivreLe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LieuDelivrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="expireLe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="villeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="paysNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="employeurPrec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="pays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="departement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="boitePostale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="typeMouvement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="natureContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateDebutContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="dateFinContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="profession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="emploi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nonCadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ouiCadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="conventionApplicable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="salaireContractuel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="tempsTravail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="categorie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="folderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "inboundDomFrm")
public class InboundDomFrm {

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
     *         &lt;element name="domesticRegistrationForm" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="dateEmbauchePremierSalarie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="regType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ninCedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="countryOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="businessSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="mainLineOfBusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="atRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="sectorCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="sectorIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agencyCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="agencyIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="employeList" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="rechercheEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nomEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="prenomEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="sexe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="etatCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numRegNaiss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nomPere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="prenomPere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nomMere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="prenomMere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ninCedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="numPieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="delivreLe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LieuDelivrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="expireLe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="villeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="paysNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="employeurPrec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="pays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="departement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="boitePostale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="typeMouvement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="natureContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateDebutContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="dateFinContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="profession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="emploi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nonCadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ouiCadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="conventionApplicable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="salaireContractuel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="tempsTravail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="categorie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "domesticRegistrationForm",
        "employeList",
        "documents"
    })
    public static class Input {

        protected RegistrationFormInfos registrationFormInfos;
        protected DomesticRegistrationForm domesticRegistrationForm;
        protected List<EmployeList> employeList;
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
         * Obtient la valeur de la propriété domesticRegistrationForm.
         *
         * @return
         *     possible object is
         *     {@link DomesticRegistrationForm }
         *
         */
        public DomesticRegistrationForm getDomesticRegistrationForm() {
            return domesticRegistrationForm;
        }

        /**
         * Définit la valeur de la propriété domesticRegistrationForm.
         *
         * @param value
         *     allowed object is
         *     {@link DomesticRegistrationForm }
         *
         */
        public void setDomesticRegistrationForm(DomesticRegistrationForm value) {
            this.domesticRegistrationForm = value;
        }

        /**
         * Gets the value of the employeList property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the employeList property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEmployeList().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EmployeList }
         *
         *
         */
        public List<EmployeList> getEmployeList() {
            if (employeList == null) {
                employeList = new ArrayList<EmployeList>();
            }
            return this.employeList;
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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

                @XmlElementRef(name = "delivered", namespace = "http://oracle.com/inboundDomFrm.xsd", type = JAXBElement.class, required = false)
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
         *         &lt;element name="dateEmbauchePremierSalarie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="regType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ninCedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="idNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="issuedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="countryOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="cityOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="businessSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="mainLineOfBusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="atRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="zoneCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="zoneIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="sectorCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="sectorIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agencyCss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="agencyIpres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "dateEmbauchePremierSalarie",
            "regType",
            "idType",
            "nin",
            "lastName",
            "firstName",
            "ninCedeao",
            "nationality",
            "idNumber",
            "issuedDate",
            "expiryDate",
            "dateOfBirth",
            "countryOfBirth",
            "cityOfBirth",
            "phoneNumber",
            "email",
            "businessSector",
            "mainLineOfBusiness",
            "atRate",
            "region",
            "department",
            "arrondissement",
            "commune",
            "quartier",
            "address",
            "zoneCss",
            "zoneIpres",
            "sectorCss",
            "sectorIpres",
            "agencyCss",
            "agencyIpres"
        })
        public static class DomesticRegistrationForm {

            protected String dateEmbauchePremierSalarie;
            protected String regType;
            protected String idType;
            protected String nin;
            protected String lastName;
            protected String firstName;
            protected String ninCedeao;
            protected String nationality;
            protected String idNumber;
            protected String issuedDate;
            protected String expiryDate;
            protected String dateOfBirth;
            protected String countryOfBirth;
            protected String cityOfBirth;
            protected String phoneNumber;
            protected String email;
            protected String businessSector;
            protected String mainLineOfBusiness;
            protected String atRate;
            protected String region;
            protected String department;
            protected String arrondissement;
            protected String commune;
            protected String quartier;
            protected String address;
            protected String zoneCss;
            protected String zoneIpres;
            protected String sectorCss;
            protected String sectorIpres;
            protected String agencyCss;
            protected String agencyIpres;

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
             * Obtient la valeur de la propriété idType.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIdType() {
                return idType;
            }

            /**
             * Définit la valeur de la propriété idType.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIdType(String value) {
                this.idType = value;
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
             * Obtient la valeur de la propriété ninCedeao.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNinCedeao() {
                return ninCedeao;
            }

            /**
             * Définit la valeur de la propriété ninCedeao.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNinCedeao(String value) {
                this.ninCedeao = value;
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
             * Obtient la valeur de la propriété idNumber.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIdNumber() {
                return idNumber;
            }

            /**
             * Définit la valeur de la propriété idNumber.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIdNumber(String value) {
                this.idNumber = value;
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
             * Obtient la valeur de la propriété countryOfBirth.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCountryOfBirth() {
                return countryOfBirth;
            }

            /**
             * Définit la valeur de la propriété countryOfBirth.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCountryOfBirth(String value) {
                this.countryOfBirth = value;
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
             * Obtient la valeur de la propriété phoneNumber.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPhoneNumber() {
                return phoneNumber;
            }

            /**
             * Définit la valeur de la propriété phoneNumber.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPhoneNumber(String value) {
                this.phoneNumber = value;
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
             * Obtient la valeur de la propriété atRate.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAtRate() {
                return atRate;
            }

            /**
             * Définit la valeur de la propriété atRate.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAtRate(String value) {
                this.atRate = value;
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
             * Obtient la valeur de la propriété agencyCss.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgencyCss() {
                return agencyCss;
            }

            /**
             * Définit la valeur de la propriété agencyCss.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgencyCss(String value) {
                this.agencyCss = value;
            }

            /**
             * Obtient la valeur de la propriété agencyIpres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAgencyIpres() {
                return agencyIpres;
            }

            /**
             * Définit la valeur de la propriété agencyIpres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAgencyIpres(String value) {
                this.agencyIpres = value;
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
         *         &lt;element name="rechercheEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nomEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="prenomEmploye" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="sexe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="etatCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="numRegNaiss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nomPere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="prenomPere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nomMere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="prenomMere" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nationalite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="typePieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ninCedeao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="numPieceIdentite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="delivreLe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LieuDelivrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="expireLe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="villeNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="paysNaissance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="employeurPrec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="pays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="departement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="arrondissement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="quartier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="boitePostale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="typeMouvement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="natureContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateDebutContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="dateFinContrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="profession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="emploi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nonCadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ouiCadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="conventionApplicable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="salaireContractuel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="tempsTravail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="categorie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "rechercheEmploye",
            "nomEmploye",
            "prenomEmploye",
            "sexe",
            "etatCivil",
            "dateNaissance",
            "numRegNaiss",
            "nomPere",
            "prenomPere",
            "nomMere",
            "prenomMere",
            "nationalite",
            "typePieceIdentite",
            "nin",
            "ninCedeao",
            "numPieceIdentite",
            "delivreLe",
            "lieuDelivrance",
            "expireLe",
            "villeNaissance",
            "paysNaissance",
            "employeurPrec",
            "pays",
            "region",
            "departement",
            "arrondissement",
            "commune",
            "quartier",
            "adresse",
            "boitePostale",
            "typeMouvement",
            "natureContrat",
            "dateDebutContrat",
            "dateFinContrat",
            "profession",
            "emploi",
            "nonCadre",
            "ouiCadre",
            "conventionApplicable",
            "salaireContractuel",
            "tempsTravail",
            "categorie"
        })
        public static class EmployeList {

            protected String rechercheEmploye;
            protected String nomEmploye;
            protected String prenomEmploye;
            protected String sexe;
            protected String etatCivil;
            protected String dateNaissance;
            protected String numRegNaiss;
            protected String nomPere;
            protected String prenomPere;
            protected String nomMere;
            protected String prenomMere;
            protected String nationalite;
            protected String typePieceIdentite;
            protected String nin;
            protected String ninCedeao;
            protected String numPieceIdentite;
            protected String delivreLe;
            @XmlElement(name = "LieuDelivrance")
            protected String lieuDelivrance;
            protected String expireLe;
            protected String villeNaissance;
            protected String paysNaissance;
            protected String employeurPrec;
            protected String pays;
            protected String region;
            protected String departement;
            protected String arrondissement;
            protected String commune;
            protected String quartier;
            protected String adresse;
            protected String boitePostale;
            protected String typeMouvement;
            protected String natureContrat;
            protected String dateDebutContrat;
            protected String dateFinContrat;
            protected String profession;
            protected String emploi;
            protected String nonCadre;
            protected String ouiCadre;
            protected String conventionApplicable;
            protected String salaireContractuel;
            protected String tempsTravail;
            protected String categorie;

            /**
             * Obtient la valeur de la propriété rechercheEmploye.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRechercheEmploye() {
                return rechercheEmploye;
            }

            /**
             * Définit la valeur de la propriété rechercheEmploye.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRechercheEmploye(String value) {
                this.rechercheEmploye = value;
            }

            /**
             * Obtient la valeur de la propriété nomEmploye.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNomEmploye() {
                return nomEmploye;
            }

            /**
             * Définit la valeur de la propriété nomEmploye.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNomEmploye(String value) {
                this.nomEmploye = value;
            }

            /**
             * Obtient la valeur de la propriété prenomEmploye.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPrenomEmploye() {
                return prenomEmploye;
            }

            /**
             * Définit la valeur de la propriété prenomEmploye.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPrenomEmploye(String value) {
                this.prenomEmploye = value;
            }

            /**
             * Obtient la valeur de la propriété sexe.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSexe() {
                return sexe;
            }

            /**
             * Définit la valeur de la propriété sexe.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSexe(String value) {
                this.sexe = value;
            }

            /**
             * Obtient la valeur de la propriété etatCivil.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEtatCivil() {
                return etatCivil;
            }

            /**
             * Définit la valeur de la propriété etatCivil.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEtatCivil(String value) {
                this.etatCivil = value;
            }

            /**
             * Obtient la valeur de la propriété dateNaissance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDateNaissance() {
                return dateNaissance;
            }

            /**
             * Définit la valeur de la propriété dateNaissance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDateNaissance(String value) {
                this.dateNaissance = value;
            }

            /**
             * Obtient la valeur de la propriété numRegNaiss.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumRegNaiss() {
                return numRegNaiss;
            }

            /**
             * Définit la valeur de la propriété numRegNaiss.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumRegNaiss(String value) {
                this.numRegNaiss = value;
            }

            /**
             * Obtient la valeur de la propriété nomPere.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNomPere() {
                return nomPere;
            }

            /**
             * Définit la valeur de la propriété nomPere.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNomPere(String value) {
                this.nomPere = value;
            }

            /**
             * Obtient la valeur de la propriété prenomPere.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPrenomPere() {
                return prenomPere;
            }

            /**
             * Définit la valeur de la propriété prenomPere.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPrenomPere(String value) {
                this.prenomPere = value;
            }

            /**
             * Obtient la valeur de la propriété nomMere.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNomMere() {
                return nomMere;
            }

            /**
             * Définit la valeur de la propriété nomMere.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNomMere(String value) {
                this.nomMere = value;
            }

            /**
             * Obtient la valeur de la propriété prenomMere.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPrenomMere() {
                return prenomMere;
            }

            /**
             * Définit la valeur de la propriété prenomMere.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPrenomMere(String value) {
                this.prenomMere = value;
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
             * Obtient la valeur de la propriété ninCedeao.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNinCedeao() {
                return ninCedeao;
            }

            /**
             * Définit la valeur de la propriété ninCedeao.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNinCedeao(String value) {
                this.ninCedeao = value;
            }

            /**
             * Obtient la valeur de la propriété numPieceIdentite.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNumPieceIdentite() {
                return numPieceIdentite;
            }

            /**
             * Définit la valeur de la propriété numPieceIdentite.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNumPieceIdentite(String value) {
                this.numPieceIdentite = value;
            }

            /**
             * Obtient la valeur de la propriété delivreLe.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDelivreLe() {
                return delivreLe;
            }

            /**
             * Définit la valeur de la propriété delivreLe.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDelivreLe(String value) {
                this.delivreLe = value;
            }

            /**
             * Obtient la valeur de la propriété lieuDelivrance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLieuDelivrance() {
                return lieuDelivrance;
            }

            /**
             * Définit la valeur de la propriété lieuDelivrance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLieuDelivrance(String value) {
                this.lieuDelivrance = value;
            }

            /**
             * Obtient la valeur de la propriété expireLe.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getExpireLe() {
                return expireLe;
            }

            /**
             * Définit la valeur de la propriété expireLe.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setExpireLe(String value) {
                this.expireLe = value;
            }

            /**
             * Obtient la valeur de la propriété villeNaissance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getVilleNaissance() {
                return villeNaissance;
            }

            /**
             * Définit la valeur de la propriété villeNaissance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setVilleNaissance(String value) {
                this.villeNaissance = value;
            }

            /**
             * Obtient la valeur de la propriété paysNaissance.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPaysNaissance() {
                return paysNaissance;
            }

            /**
             * Définit la valeur de la propriété paysNaissance.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPaysNaissance(String value) {
                this.paysNaissance = value;
            }

            /**
             * Obtient la valeur de la propriété employeurPrec.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEmployeurPrec() {
                return employeurPrec;
            }

            /**
             * Définit la valeur de la propriété employeurPrec.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEmployeurPrec(String value) {
                this.employeurPrec = value;
            }

            /**
             * Obtient la valeur de la propriété pays.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPays() {
                return pays;
            }

            /**
             * Définit la valeur de la propriété pays.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPays(String value) {
                this.pays = value;
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
             * Obtient la valeur de la propriété boitePostale.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getBoitePostale() {
                return boitePostale;
            }

            /**
             * Définit la valeur de la propriété boitePostale.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setBoitePostale(String value) {
                this.boitePostale = value;
            }

            /**
             * Obtient la valeur de la propriété typeMouvement.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTypeMouvement() {
                return typeMouvement;
            }

            /**
             * Définit la valeur de la propriété typeMouvement.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTypeMouvement(String value) {
                this.typeMouvement = value;
            }

            /**
             * Obtient la valeur de la propriété natureContrat.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNatureContrat() {
                return natureContrat;
            }

            /**
             * Définit la valeur de la propriété natureContrat.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNatureContrat(String value) {
                this.natureContrat = value;
            }

            /**
             * Obtient la valeur de la propriété dateDebutContrat.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDateDebutContrat() {
                return dateDebutContrat;
            }

            /**
             * Définit la valeur de la propriété dateDebutContrat.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDateDebutContrat(String value) {
                this.dateDebutContrat = value;
            }

            /**
             * Obtient la valeur de la propriété dateFinContrat.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDateFinContrat() {
                return dateFinContrat;
            }

            /**
             * Définit la valeur de la propriété dateFinContrat.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDateFinContrat(String value) {
                this.dateFinContrat = value;
            }

            /**
             * Obtient la valeur de la propriété profession.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getProfession() {
                return profession;
            }

            /**
             * Définit la valeur de la propriété profession.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setProfession(String value) {
                this.profession = value;
            }

            /**
             * Obtient la valeur de la propriété emploi.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEmploi() {
                return emploi;
            }

            /**
             * Définit la valeur de la propriété emploi.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEmploi(String value) {
                this.emploi = value;
            }

            /**
             * Obtient la valeur de la propriété nonCadre.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNonCadre() {
                return nonCadre;
            }

            /**
             * Définit la valeur de la propriété nonCadre.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNonCadre(String value) {
                this.nonCadre = value;
            }

            /**
             * Obtient la valeur de la propriété ouiCadre.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getOuiCadre() {
                return ouiCadre;
            }

            /**
             * Définit la valeur de la propriété ouiCadre.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setOuiCadre(String value) {
                this.ouiCadre = value;
            }

            /**
             * Obtient la valeur de la propriété conventionApplicable.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getConventionApplicable() {
                return conventionApplicable;
            }

            /**
             * Définit la valeur de la propriété conventionApplicable.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setConventionApplicable(String value) {
                this.conventionApplicable = value;
            }

            /**
             * Obtient la valeur de la propriété salaireContractuel.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSalaireContractuel() {
                return salaireContractuel;
            }

            /**
             * Définit la valeur de la propriété salaireContractuel.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSalaireContractuel(String value) {
                this.salaireContractuel = value;
            }

            /**
             * Obtient la valeur de la propriété tempsTravail.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTempsTravail() {
                return tempsTravail;
            }

            /**
             * Définit la valeur de la propriété tempsTravail.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTempsTravail(String value) {
                this.tempsTravail = value;
            }

            /**
             * Obtient la valeur de la propriété categorie.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCategorie() {
                return categorie;
            }

            /**
             * Définit la valeur de la propriété categorie.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCategorie(String value) {
                this.categorie = value;
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
     *         &lt;element name="folderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "registrationFormId",
        "folderId",
        "zoneCss",
        "zoneIpres",
        "sectorCss",
        "sectorIpres",
        "agenceCss",
        "agenceIpres",
        "tauxAt"
    })
    public static class Output {

        protected String registrationFormId;
        protected String folderId;
        protected String zoneCss;
        protected String zoneIpres;
        protected String sectorCss;
        protected String sectorIpres;
        protected String agenceCss;
        protected String agenceIpres;
        protected String tauxAt;

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
         * Obtient la valeur de la propriété folderId.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getFolderId() {
            return folderId;
        }

        /**
         * Définit la valeur de la propriété folderId.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setFolderId(String value) {
            this.folderId = value;
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
