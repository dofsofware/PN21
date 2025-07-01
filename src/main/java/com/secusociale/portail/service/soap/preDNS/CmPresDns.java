
package com.secusociale.portail.service.soap.preDNS;

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
 *         &lt;element name="address" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="typeDeclaration" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dateDebutPeriodeCotisation" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dateFinPeriodeCotisation" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="totalSalaries" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxExclusive value="999999999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="totalSalaireAssujetisRg" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="totalSalaireAssujetisRcc" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="totalSalaireAssujetisPf" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="totalSalaireAssujetisAtmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="totalSalaireVerses" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="montantCotisationPfEmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="montantCotisationAtmpEmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="montantCotisationRgEmpl" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="montantCotisationRccEmp" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *         &lt;element name="informationSalaries" maxOccurs="unbounded" minOccurs="0">
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
 *                   &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="typePiece" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="30"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="numeroPiece" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="40"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="natureContrat" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="30"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="dateEntree" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="dateSortie" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="motif" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="totalSalaireAssujetisPfMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisAtmpMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisRgMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisRccMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="salaireBrutMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="tempsPresenceJourMois1" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;minInclusive value="0"/>
 *                         &lt;maxExclusive value="9999999999"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tempsPresenceHeureMois1" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;minInclusive value="0"/>
 *                         &lt;maxExclusive value="99999999999999999999"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tempsTravailMois1" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tranceDeTravailMois1" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="regimeGeneralMois1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="regimeCadreMois1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="dateEffetRegimeCadreMois1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisPfMois2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisAtmpMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisRgMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisRccMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="salaireBrutMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="tempsPresenceJourMois2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tempsPresenceHeureMois2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tempsTravailMois2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tranceDeTravailMois2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="regimeGeneralMois2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="regimeCadreMois2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="dateEffetRegimeCadreMois2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisPfMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisAtmpMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisRgMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="totalSalaireAssujetisRccMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="salaireBrutMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="tempsPresenceJourMois3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tempsPresenceHeureMois3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="tempsTravailMois3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tranceDeTravailMois3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="regimeGeneralMois3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="regimeCadreMois3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="dateEffetRegimeCadreMois3" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="action" type="{http://ouaf.oracle.com/}listAction" />
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
    "numeroEmployeur",
    "raisonSociale",
    "address",
    "typeDeclaration",
    "dateDebutPeriodeCotisation",
    "dateFinPeriodeCotisation",
    "totalSalaries",
    "totalSalaireAssujetisRg",
    "totalSalaireAssujetisRcc",
    "totalSalaireAssujetisPf",
    "totalSalaireAssujetisAtmp",
    "totalSalaireVerses",
    "montantCotisationPfEmp",
    "montantCotisationAtmpEmp",
    "montantCotisationRgEmpl",
    "montantCotisationRccEmp",
    "informationSalaries"
})
@XmlRootElement(name = "CmPresDns", namespace = "http://oracle.com/CmPresDns.xsd")
public class CmPresDns {

    @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd", required = true)
    protected String numeroEmployeur;
    @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
    protected String raisonSociale;
    @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
    protected String address;
    @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
    protected String typeDeclaration;
    @XmlElementRef(name = "dateDebutPeriodeCotisation", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> dateDebutPeriodeCotisation;
    @XmlElementRef(name = "dateFinPeriodeCotisation", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> dateFinPeriodeCotisation;
    @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
    protected BigDecimal totalSalaries;
    @XmlElementRef(name = "totalSalaireAssujetisRg", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> totalSalaireAssujetisRg;
    @XmlElementRef(name = "totalSalaireAssujetisRcc", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> totalSalaireAssujetisRcc;
    @XmlElementRef(name = "totalSalaireAssujetisPf", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> totalSalaireAssujetisPf;
    @XmlElementRef(name = "totalSalaireAssujetisAtmp", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> totalSalaireAssujetisAtmp;
    @XmlElementRef(name = "totalSalaireVerses", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> totalSalaireVerses;
    @XmlElementRef(name = "montantCotisationPfEmp", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> montantCotisationPfEmp;
    @XmlElementRef(name = "montantCotisationAtmpEmp", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> montantCotisationAtmpEmp;
    @XmlElementRef(name = "montantCotisationRgEmpl", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> montantCotisationRgEmpl;
    @XmlElementRef(name = "montantCotisationRccEmp", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> montantCotisationRccEmp;
    @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
    protected List<InformationSalaries> informationSalaries;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

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
     * Obtient la valeur de la propriété dateDebutPeriodeCotisation.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *
     */
    public JAXBElement<XMLGregorianCalendar> getDateDebutPeriodeCotisation() {
        return dateDebutPeriodeCotisation;
    }

    /**
     * Définit la valeur de la propriété dateDebutPeriodeCotisation.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *
     */
    public void setDateDebutPeriodeCotisation(JAXBElement<XMLGregorianCalendar> value) {
        this.dateDebutPeriodeCotisation = value;
    }

    /**
     * Obtient la valeur de la propriété dateFinPeriodeCotisation.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *
     */
    public JAXBElement<XMLGregorianCalendar> getDateFinPeriodeCotisation() {
        return dateFinPeriodeCotisation;
    }

    /**
     * Définit la valeur de la propriété dateFinPeriodeCotisation.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *
     */
    public void setDateFinPeriodeCotisation(JAXBElement<XMLGregorianCalendar> value) {
        this.dateFinPeriodeCotisation = value;
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
     * Obtient la valeur de la propriété totalSalaireAssujetisRg.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getTotalSalaireAssujetisRg() {
        return totalSalaireAssujetisRg;
    }

    /**
     * Définit la valeur de la propriété totalSalaireAssujetisRg.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setTotalSalaireAssujetisRg(JAXBElement<BigDecimal> value) {
        this.totalSalaireAssujetisRg = value;
    }

    /**
     * Obtient la valeur de la propriété totalSalaireAssujetisRcc.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getTotalSalaireAssujetisRcc() {
        return totalSalaireAssujetisRcc;
    }

    /**
     * Définit la valeur de la propriété totalSalaireAssujetisRcc.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setTotalSalaireAssujetisRcc(JAXBElement<BigDecimal> value) {
        this.totalSalaireAssujetisRcc = value;
    }

    /**
     * Obtient la valeur de la propriété totalSalaireAssujetisPf.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getTotalSalaireAssujetisPf() {
        return totalSalaireAssujetisPf;
    }

    /**
     * Définit la valeur de la propriété totalSalaireAssujetisPf.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setTotalSalaireAssujetisPf(JAXBElement<BigDecimal> value) {
        this.totalSalaireAssujetisPf = value;
    }

    /**
     * Obtient la valeur de la propriété totalSalaireAssujetisAtmp.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getTotalSalaireAssujetisAtmp() {
        return totalSalaireAssujetisAtmp;
    }

    /**
     * Définit la valeur de la propriété totalSalaireAssujetisAtmp.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setTotalSalaireAssujetisAtmp(JAXBElement<BigDecimal> value) {
        this.totalSalaireAssujetisAtmp = value;
    }

    /**
     * Obtient la valeur de la propriété totalSalaireVerses.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getTotalSalaireVerses() {
        return totalSalaireVerses;
    }

    /**
     * Définit la valeur de la propriété totalSalaireVerses.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setTotalSalaireVerses(JAXBElement<BigDecimal> value) {
        this.totalSalaireVerses = value;
    }

    /**
     * Obtient la valeur de la propriété montantCotisationPfEmp.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getMontantCotisationPfEmp() {
        return montantCotisationPfEmp;
    }

    /**
     * Définit la valeur de la propriété montantCotisationPfEmp.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setMontantCotisationPfEmp(JAXBElement<BigDecimal> value) {
        this.montantCotisationPfEmp = value;
    }

    /**
     * Obtient la valeur de la propriété montantCotisationAtmpEmp.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getMontantCotisationAtmpEmp() {
        return montantCotisationAtmpEmp;
    }

    /**
     * Définit la valeur de la propriété montantCotisationAtmpEmp.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setMontantCotisationAtmpEmp(JAXBElement<BigDecimal> value) {
        this.montantCotisationAtmpEmp = value;
    }

    /**
     * Obtient la valeur de la propriété montantCotisationRgEmpl.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getMontantCotisationRgEmpl() {
        return montantCotisationRgEmpl;
    }

    /**
     * Définit la valeur de la propriété montantCotisationRgEmpl.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setMontantCotisationRgEmpl(JAXBElement<BigDecimal> value) {
        this.montantCotisationRgEmpl = value;
    }

    /**
     * Obtient la valeur de la propriété montantCotisationRccEmp.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getMontantCotisationRccEmp() {
        return montantCotisationRccEmp;
    }

    /**
     * Définit la valeur de la propriété montantCotisationRccEmp.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setMontantCotisationRccEmp(JAXBElement<BigDecimal> value) {
        this.montantCotisationRccEmp = value;
    }

    /**
     * Gets the value of the informationSalaries property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informationSalaries property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformationSalaries().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InformationSalaries }
     *
     *
     */
    public List<InformationSalaries> getInformationSalaries() {
        if (informationSalaries == null) {
            informationSalaries = new ArrayList<InformationSalaries>();
        }
        return this.informationSalaries;
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
     *         &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="typePiece" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="30"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="numeroPiece" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="40"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="natureContrat" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="30"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="dateEntree" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="dateSortie" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="motif" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="totalSalaireAssujetisPfMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisAtmpMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisRgMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisRccMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="salaireBrutMois1" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="tempsPresenceJourMois1" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;minInclusive value="0"/>
     *               &lt;maxExclusive value="9999999999"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tempsPresenceHeureMois1" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;minInclusive value="0"/>
     *               &lt;maxExclusive value="99999999999999999999"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tempsTravailMois1" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tranceDeTravailMois1" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="regimeGeneralMois1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="regimeCadreMois1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="dateEffetRegimeCadreMois1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisPfMois2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisAtmpMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisRgMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisRccMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="salaireBrutMois2" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="tempsPresenceJourMois2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tempsPresenceHeureMois2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tempsTravailMois2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tranceDeTravailMois2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="regimeGeneralMois2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="regimeCadreMois2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="dateEffetRegimeCadreMois2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisPfMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisAtmpMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisRgMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="totalSalaireAssujetisRccMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="salaireBrutMois3" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="tempsPresenceJourMois3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tempsPresenceHeureMois3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="tempsTravailMois3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tranceDeTravailMois3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="regimeGeneralMois3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="regimeCadreMois3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="dateEffetRegimeCadreMois3" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="action" type="{http://ouaf.oracle.com/}listAction" />
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
        "dateNaissance",
        "typePiece",
        "numeroPiece",
        "natureContrat",
        "dateEntree",
        "dateSortie",
        "motif",
        "totalSalaireAssujetisPfMois1",
        "totalSalaireAssujetisAtmpMois1",
        "totalSalaireAssujetisRgMois1",
        "totalSalaireAssujetisRccMois1",
        "salaireBrutMois1",
        "tempsPresenceJourMois1",
        "tempsPresenceHeureMois1",
        "tempsTravailMois1",
        "tranceDeTravailMois1",
        "regimeGeneralMois1",
        "regimeCadreMois1",
        "dateEffetRegimeCadreMois1",
        "totalSalaireAssujetisPfMois2",
        "totalSalaireAssujetisAtmpMois2",
        "totalSalaireAssujetisRgMois2",
        "totalSalaireAssujetisRccMois2",
        "salaireBrutMois2",
        "tempsPresenceJourMois2",
        "tempsPresenceHeureMois2",
        "tempsTravailMois2",
        "tranceDeTravailMois2",
        "regimeGeneralMois2",
        "regimeCadreMois2",
        "dateEffetRegimeCadreMois2",
        "totalSalaireAssujetisPfMois3",
        "totalSalaireAssujetisAtmpMois3",
        "totalSalaireAssujetisRgMois3",
        "totalSalaireAssujetisRccMois3",
        "salaireBrutMois3",
        "tempsPresenceJourMois3",
        "tempsPresenceHeureMois3",
        "tempsTravailMois3",
        "tranceDeTravailMois3",
        "regimeGeneralMois3",
        "regimeCadreMois3",
        "dateEffetRegimeCadreMois3"
    })
    public static class InformationSalaries {

        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String numeroAssureSocial;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String nom;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String prenom;
        @XmlElementRef(name = "dateNaissance", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateNaissance;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String typePiece;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String numeroPiece;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String natureContrat;
        @XmlElementRef(name = "dateEntree", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateEntree;
        @XmlElementRef(name = "dateSortie", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateSortie;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String motif;
        @XmlElementRef(name = "totalSalaireAssujetisPfMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisPfMois1;
        @XmlElementRef(name = "totalSalaireAssujetisAtmpMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisAtmpMois1;
        @XmlElementRef(name = "totalSalaireAssujetisRgMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisRgMois1;
        @XmlElementRef(name = "totalSalaireAssujetisRccMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisRccMois1;
        @XmlElementRef(name = "salaireBrutMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> salaireBrutMois1;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected BigDecimal tempsPresenceJourMois1;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected BigDecimal tempsPresenceHeureMois1;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String tempsTravailMois1;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String tranceDeTravailMois1;
        @XmlElementRef(name = "regimeGeneralMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> regimeGeneralMois1;
        @XmlElementRef(name = "regimeCadreMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> regimeCadreMois1;
        @XmlElementRef(name = "dateEffetRegimeCadreMois1", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateEffetRegimeCadreMois1;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String totalSalaireAssujetisPfMois2;
        @XmlElementRef(name = "totalSalaireAssujetisAtmpMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisAtmpMois2;
        @XmlElementRef(name = "totalSalaireAssujetisRgMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisRgMois2;
        @XmlElementRef(name = "totalSalaireAssujetisRccMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisRccMois2;
        @XmlElementRef(name = "salaireBrutMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> salaireBrutMois2;
        @XmlElementRef(name = "tempsPresenceJourMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tempsPresenceJourMois2;
        @XmlElementRef(name = "tempsPresenceHeureMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tempsPresenceHeureMois2;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String tempsTravailMois2;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String tranceDeTravailMois2;
        @XmlElementRef(name = "regimeGeneralMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> regimeGeneralMois2;
        @XmlElementRef(name = "regimeCadreMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> regimeCadreMois2;
        @XmlElementRef(name = "dateEffetRegimeCadreMois2", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateEffetRegimeCadreMois2;
        @XmlElementRef(name = "totalSalaireAssujetisPfMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisPfMois3;
        @XmlElementRef(name = "totalSalaireAssujetisAtmpMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisAtmpMois3;
        @XmlElementRef(name = "totalSalaireAssujetisRgMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisRgMois3;
        @XmlElementRef(name = "totalSalaireAssujetisRccMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> totalSalaireAssujetisRccMois3;
        @XmlElementRef(name = "salaireBrutMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> salaireBrutMois3;
        @XmlElementRef(name = "tempsPresenceJourMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tempsPresenceJourMois3;
        @XmlElementRef(name = "tempsPresenceHeureMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> tempsPresenceHeureMois3;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String tempsTravailMois3;
        @XmlElement(namespace = "http://oracle.com/CmPresDns.xsd")
        protected String tranceDeTravailMois3;
        @XmlElementRef(name = "regimeGeneralMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> regimeGeneralMois3;
        @XmlElementRef(name = "regimeCadreMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> regimeCadreMois3;
        @XmlElementRef(name = "dateEffetRegimeCadreMois3", namespace = "http://oracle.com/CmPresDns.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateEffetRegimeCadreMois3;
        @XmlAttribute(name = "action")
        protected ListAction action;

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
         * Obtient la valeur de la propriété dateNaissance.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateNaissance() {
            return dateNaissance;
        }

        /**
         * Définit la valeur de la propriété dateNaissance.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateNaissance(JAXBElement<XMLGregorianCalendar> value) {
            this.dateNaissance = value;
        }

        /**
         * Obtient la valeur de la propriété typePiece.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTypePiece() {
            return typePiece;
        }

        /**
         * Définit la valeur de la propriété typePiece.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTypePiece(String value) {
            this.typePiece = value;
        }

        /**
         * Obtient la valeur de la propriété numeroPiece.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumeroPiece() {
            return numeroPiece;
        }

        /**
         * Définit la valeur de la propriété numeroPiece.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumeroPiece(String value) {
            this.numeroPiece = value;
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
         * Obtient la valeur de la propriété motif.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMotif() {
            return motif;
        }

        /**
         * Définit la valeur de la propriété motif.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMotif(String value) {
            this.motif = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisPfMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisPfMois1() {
            return totalSalaireAssujetisPfMois1;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisPfMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisPfMois1(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisPfMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisAtmpMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisAtmpMois1() {
            return totalSalaireAssujetisAtmpMois1;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisAtmpMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisAtmpMois1(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisAtmpMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisRgMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisRgMois1() {
            return totalSalaireAssujetisRgMois1;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisRgMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisRgMois1(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisRgMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisRccMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisRccMois1() {
            return totalSalaireAssujetisRccMois1;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisRccMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisRccMois1(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisRccMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété salaireBrutMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getSalaireBrutMois1() {
            return salaireBrutMois1;
        }

        /**
         * Définit la valeur de la propriété salaireBrutMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setSalaireBrutMois1(JAXBElement<BigDecimal> value) {
            this.salaireBrutMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsPresenceJourMois1.
         *
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *
         */
        public BigDecimal getTempsPresenceJourMois1() {
            return tempsPresenceJourMois1;
        }

        /**
         * Définit la valeur de la propriété tempsPresenceJourMois1.
         *
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *
         */
        public void setTempsPresenceJourMois1(BigDecimal value) {
            this.tempsPresenceJourMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsPresenceHeureMois1.
         *
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *
         */
        public BigDecimal getTempsPresenceHeureMois1() {
            return tempsPresenceHeureMois1;
        }

        /**
         * Définit la valeur de la propriété tempsPresenceHeureMois1.
         *
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *
         */
        public void setTempsPresenceHeureMois1(BigDecimal value) {
            this.tempsPresenceHeureMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsTravailMois1.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTempsTravailMois1() {
            return tempsTravailMois1;
        }

        /**
         * Définit la valeur de la propriété tempsTravailMois1.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTempsTravailMois1(String value) {
            this.tempsTravailMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété tranceDeTravailMois1.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTranceDeTravailMois1() {
            return tranceDeTravailMois1;
        }

        /**
         * Définit la valeur de la propriété tranceDeTravailMois1.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTranceDeTravailMois1(String value) {
            this.tranceDeTravailMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété regimeGeneralMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getRegimeGeneralMois1() {
            return regimeGeneralMois1;
        }

        /**
         * Définit la valeur de la propriété regimeGeneralMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setRegimeGeneralMois1(JAXBElement<Boolean> value) {
            this.regimeGeneralMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété regimeCadreMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getRegimeCadreMois1() {
            return regimeCadreMois1;
        }

        /**
         * Définit la valeur de la propriété regimeCadreMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setRegimeCadreMois1(JAXBElement<Boolean> value) {
            this.regimeCadreMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété dateEffetRegimeCadreMois1.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateEffetRegimeCadreMois1() {
            return dateEffetRegimeCadreMois1;
        }

        /**
         * Définit la valeur de la propriété dateEffetRegimeCadreMois1.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateEffetRegimeCadreMois1(JAXBElement<XMLGregorianCalendar> value) {
            this.dateEffetRegimeCadreMois1 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisPfMois2.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTotalSalaireAssujetisPfMois2() {
            return totalSalaireAssujetisPfMois2;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisPfMois2.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTotalSalaireAssujetisPfMois2(String value) {
            this.totalSalaireAssujetisPfMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisAtmpMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisAtmpMois2() {
            return totalSalaireAssujetisAtmpMois2;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisAtmpMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisAtmpMois2(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisAtmpMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisRgMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisRgMois2() {
            return totalSalaireAssujetisRgMois2;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisRgMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisRgMois2(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisRgMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisRccMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisRccMois2() {
            return totalSalaireAssujetisRccMois2;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisRccMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisRccMois2(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisRccMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété salaireBrutMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getSalaireBrutMois2() {
            return salaireBrutMois2;
        }

        /**
         * Définit la valeur de la propriété salaireBrutMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setSalaireBrutMois2(JAXBElement<BigDecimal> value) {
            this.salaireBrutMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsPresenceJourMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTempsPresenceJourMois2() {
            return tempsPresenceJourMois2;
        }

        /**
         * Définit la valeur de la propriété tempsPresenceJourMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTempsPresenceJourMois2(JAXBElement<BigDecimal> value) {
            this.tempsPresenceJourMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsPresenceHeureMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTempsPresenceHeureMois2() {
            return tempsPresenceHeureMois2;
        }

        /**
         * Définit la valeur de la propriété tempsPresenceHeureMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTempsPresenceHeureMois2(JAXBElement<BigDecimal> value) {
            this.tempsPresenceHeureMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsTravailMois2.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTempsTravailMois2() {
            return tempsTravailMois2;
        }

        /**
         * Définit la valeur de la propriété tempsTravailMois2.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTempsTravailMois2(String value) {
            this.tempsTravailMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété tranceDeTravailMois2.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTranceDeTravailMois2() {
            return tranceDeTravailMois2;
        }

        /**
         * Définit la valeur de la propriété tranceDeTravailMois2.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTranceDeTravailMois2(String value) {
            this.tranceDeTravailMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété regimeGeneralMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getRegimeGeneralMois2() {
            return regimeGeneralMois2;
        }

        /**
         * Définit la valeur de la propriété regimeGeneralMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setRegimeGeneralMois2(JAXBElement<Boolean> value) {
            this.regimeGeneralMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété regimeCadreMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getRegimeCadreMois2() {
            return regimeCadreMois2;
        }

        /**
         * Définit la valeur de la propriété regimeCadreMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setRegimeCadreMois2(JAXBElement<Boolean> value) {
            this.regimeCadreMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété dateEffetRegimeCadreMois2.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateEffetRegimeCadreMois2() {
            return dateEffetRegimeCadreMois2;
        }

        /**
         * Définit la valeur de la propriété dateEffetRegimeCadreMois2.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateEffetRegimeCadreMois2(JAXBElement<XMLGregorianCalendar> value) {
            this.dateEffetRegimeCadreMois2 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisPfMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisPfMois3() {
            return totalSalaireAssujetisPfMois3;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisPfMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisPfMois3(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisPfMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisAtmpMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisAtmpMois3() {
            return totalSalaireAssujetisAtmpMois3;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisAtmpMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisAtmpMois3(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisAtmpMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisRgMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisRgMois3() {
            return totalSalaireAssujetisRgMois3;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisRgMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisRgMois3(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisRgMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété totalSalaireAssujetisRccMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTotalSalaireAssujetisRccMois3() {
            return totalSalaireAssujetisRccMois3;
        }

        /**
         * Définit la valeur de la propriété totalSalaireAssujetisRccMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTotalSalaireAssujetisRccMois3(JAXBElement<BigDecimal> value) {
            this.totalSalaireAssujetisRccMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété salaireBrutMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getSalaireBrutMois3() {
            return salaireBrutMois3;
        }

        /**
         * Définit la valeur de la propriété salaireBrutMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setSalaireBrutMois3(JAXBElement<BigDecimal> value) {
            this.salaireBrutMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsPresenceJourMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTempsPresenceJourMois3() {
            return tempsPresenceJourMois3;
        }

        /**
         * Définit la valeur de la propriété tempsPresenceJourMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTempsPresenceJourMois3(JAXBElement<BigDecimal> value) {
            this.tempsPresenceJourMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsPresenceHeureMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getTempsPresenceHeureMois3() {
            return tempsPresenceHeureMois3;
        }

        /**
         * Définit la valeur de la propriété tempsPresenceHeureMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setTempsPresenceHeureMois3(JAXBElement<BigDecimal> value) {
            this.tempsPresenceHeureMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété tempsTravailMois3.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTempsTravailMois3() {
            return tempsTravailMois3;
        }

        /**
         * Définit la valeur de la propriété tempsTravailMois3.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTempsTravailMois3(String value) {
            this.tempsTravailMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété tranceDeTravailMois3.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTranceDeTravailMois3() {
            return tranceDeTravailMois3;
        }

        /**
         * Définit la valeur de la propriété tranceDeTravailMois3.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTranceDeTravailMois3(String value) {
            this.tranceDeTravailMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété regimeGeneralMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getRegimeGeneralMois3() {
            return regimeGeneralMois3;
        }

        /**
         * Définit la valeur de la propriété regimeGeneralMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setRegimeGeneralMois3(JAXBElement<Boolean> value) {
            this.regimeGeneralMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété regimeCadreMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getRegimeCadreMois3() {
            return regimeCadreMois3;
        }

        /**
         * Définit la valeur de la propriété regimeCadreMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setRegimeCadreMois3(JAXBElement<Boolean> value) {
            this.regimeCadreMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété dateEffetRegimeCadreMois3.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateEffetRegimeCadreMois3() {
            return dateEffetRegimeCadreMois3;
        }

        /**
         * Définit la valeur de la propriété dateEffetRegimeCadreMois3.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateEffetRegimeCadreMois3(JAXBElement<XMLGregorianCalendar> value) {
            this.dateEffetRegimeCadreMois3 = value;
        }

        /**
         * Obtient la valeur de la propriété action.
         *
         * @return
         *     possible object is
         *     {@link ListAction }
         *
         */
        public ListAction getAction() {
            return action;
        }

        /**
         * Définit la valeur de la propriété action.
         *
         * @param value
         *     allowed object is
         *     {@link ListAction }
         *
         */
        public void setAction(ListAction value) {
            this.action = value;
        }

    }

}
