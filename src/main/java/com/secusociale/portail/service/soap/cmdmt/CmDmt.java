
package com.secusociale.portail.service.soap.cmdmt;

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
 *         &lt;element name="idEmployeur" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="employes" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
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
 *                   &lt;element name="sexe" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="HOMME"/>
 *                         &lt;enumeration value="FEMME"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="etatCivil" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="DIV"/>
 *                         &lt;enumeration value="VEU"/>
 *                         &lt;enumeration value="CEL"/>
 *                         &lt;enumeration value="MAR"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="nationalite" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="typePiece">
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
 *                   &lt;element name="numeroPiece" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="typeDeMouvement" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="ENTREE"/>
 *                         &lt;enumeration value="SORTIE"/>
 *                         &lt;enumeration value="SUSPENSION_LEGALE"/>
 *                         &lt;enumeration value="CHANGEMENT"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="motifMouvement" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="AUTRES"/>
 *                         &lt;enumeration value="CONGES_DE_MATERNITE"/>
 *                         &lt;enumeration value="DEMISSION"/>
 *                         &lt;enumeration value="CONGES_ADMINISTRATIF"/>
 *                         &lt;enumeration value="DISPONIBILITE"/>
 *                         &lt;enumeration value="CHGT_CATEGORIE_PRO"/>
 *                         &lt;enumeration value="CHOMAGE_TECHNIQUE"/>
 *                         &lt;enumeration value="PROMOTION_CADRE"/>
 *                         &lt;enumeration value="RETRAITE"/>
 *                         &lt;enumeration value="DECES"/>
 *                         &lt;enumeration value="MISE_A_PIED"/>
 *                         &lt;enumeration value="LICENCIEMENT"/>
 *                         &lt;enumeration value="MODIF_CONTRAT_TRAVAIL"/>
 *                         &lt;enumeration value="CHGT_RESID_HABITUEL"/>
 *                         &lt;enumeration value="MUTATION"/>
 *                         &lt;enumeration value="CHGT_SITUATION_FAMILLE"/>
 *                         &lt;enumeration value="CHGT_EMPLOI"/>
 *                         &lt;enumeration value="EMBAUCHE"/>
 *                         &lt;enumeration value="ARRET_MALADIE_ATMP"/>
 *                         &lt;enumeration value="EXP_NORM_CONTRAT"/>
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
 *                   &lt;element name="dateDebutMouvement" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="dateFinMouvement" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="profession" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="600"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="emploi" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="estCadre" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="conventionApplicable" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="CC21"/>
 *                         &lt;enumeration value="CC20"/>
 *                         &lt;enumeration value="NCATI"/>
 *                         &lt;enumeration value="CC1"/>
 *                         &lt;enumeration value="CC3"/>
 *                         &lt;enumeration value="CC2"/>
 *                         &lt;enumeration value="CC5"/>
 *                         &lt;enumeration value="CC4"/>
 *                         &lt;enumeration value="CC7"/>
 *                         &lt;enumeration value="CC19"/>
 *                         &lt;enumeration value="CC6"/>
 *                         &lt;enumeration value="CC18"/>
 *                         &lt;enumeration value="CC9"/>
 *                         &lt;enumeration value="CC17"/>
 *                         &lt;enumeration value="CC8"/>
 *                         &lt;enumeration value="CC16"/>
 *                         &lt;enumeration value="CC15"/>
 *                         &lt;enumeration value="CC14"/>
 *                         &lt;enumeration value="CC13"/>
 *                         &lt;enumeration value="CC12"/>
 *                         &lt;enumeration value="CC11"/>
 *                         &lt;enumeration value="CC10"/>
 *                         &lt;enumeration value="NCA1"/>
 *                         &lt;enumeration value="NCA2"/>
 *                         &lt;enumeration value="NCA3"/>
 *                         &lt;enumeration value="CC22"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="salaireBrute" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
 *                   &lt;element name="tempsTravail" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="TPS_PARTIEL"/>
 *                         &lt;enumeration value="TPS_PLEIN"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="categorie" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
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
    "idEmployeur",
    "employes"
})
@XmlRootElement(name = "CmDmt")
public class CmDmt {

    protected String idEmployeur;
    protected List<Employes> employes;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriété idEmployeur.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdEmployeur() {
        return idEmployeur;
    }

    /**
     * Définit la valeur de la propriété idEmployeur.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdEmployeur(String value) {
        this.idEmployeur = value;
    }

    /**
     * Gets the value of the employes property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employes property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployes().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Employes }
     *
     *
     */
    public List<Employes> getEmployes() {
        if (employes == null) {
            employes = new ArrayList<Employes>();
        }
        return this.employes;
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
     *         &lt;element name="sexe" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="HOMME"/>
     *               &lt;enumeration value="FEMME"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="etatCivil" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="DIV"/>
     *               &lt;enumeration value="VEU"/>
     *               &lt;enumeration value="CEL"/>
     *               &lt;enumeration value="MAR"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="dateNaissance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="nationalite" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="typePiece">
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
     *         &lt;element name="numeroPiece" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="typeDeMouvement" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="ENTREE"/>
     *               &lt;enumeration value="SORTIE"/>
     *               &lt;enumeration value="SUSPENSION_LEGALE"/>
     *               &lt;enumeration value="CHANGEMENT"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="motifMouvement" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="AUTRES"/>
     *               &lt;enumeration value="CONGES_DE_MATERNITE"/>
     *               &lt;enumeration value="DEMISSION"/>
     *               &lt;enumeration value="CONGES_ADMINISTRATIF"/>
     *               &lt;enumeration value="DISPONIBILITE"/>
     *               &lt;enumeration value="CHGT_CATEGORIE_PRO"/>
     *               &lt;enumeration value="CHOMAGE_TECHNIQUE"/>
     *               &lt;enumeration value="PROMOTION_CADRE"/>
     *               &lt;enumeration value="RETRAITE"/>
     *               &lt;enumeration value="DECES"/>
     *               &lt;enumeration value="MISE_A_PIED"/>
     *               &lt;enumeration value="LICENCIEMENT"/>
     *               &lt;enumeration value="MODIF_CONTRAT_TRAVAIL"/>
     *               &lt;enumeration value="CHGT_RESID_HABITUEL"/>
     *               &lt;enumeration value="MUTATION"/>
     *               &lt;enumeration value="CHGT_SITUATION_FAMILLE"/>
     *               &lt;enumeration value="CHGT_EMPLOI"/>
     *               &lt;enumeration value="EMBAUCHE"/>
     *               &lt;enumeration value="ARRET_MALADIE_ATMP"/>
     *               &lt;enumeration value="EXP_NORM_CONTRAT"/>
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
     *         &lt;element name="dateDebutMouvement" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="dateFinMouvement" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="profession" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="600"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="emploi" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="estCadre" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="conventionApplicable" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="CC21"/>
     *               &lt;enumeration value="CC20"/>
     *               &lt;enumeration value="NCATI"/>
     *               &lt;enumeration value="CC1"/>
     *               &lt;enumeration value="CC3"/>
     *               &lt;enumeration value="CC2"/>
     *               &lt;enumeration value="CC5"/>
     *               &lt;enumeration value="CC4"/>
     *               &lt;enumeration value="CC7"/>
     *               &lt;enumeration value="CC19"/>
     *               &lt;enumeration value="CC6"/>
     *               &lt;enumeration value="CC18"/>
     *               &lt;enumeration value="CC9"/>
     *               &lt;enumeration value="CC17"/>
     *               &lt;enumeration value="CC8"/>
     *               &lt;enumeration value="CC16"/>
     *               &lt;enumeration value="CC15"/>
     *               &lt;enumeration value="CC14"/>
     *               &lt;enumeration value="CC13"/>
     *               &lt;enumeration value="CC12"/>
     *               &lt;enumeration value="CC11"/>
     *               &lt;enumeration value="CC10"/>
     *               &lt;enumeration value="NCA1"/>
     *               &lt;enumeration value="NCA2"/>
     *               &lt;enumeration value="NCA3"/>
     *               &lt;enumeration value="CC22"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="salaireBrute" type="{http://ouaf.oracle.com/}money" minOccurs="0"/>
     *         &lt;element name="tempsTravail" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="TPS_PARTIEL"/>
     *               &lt;enumeration value="TPS_PLEIN"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="categorie" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "nom",
        "prenom",
        "sexe",
        "etatCivil",
        "dateNaissance",
        "nationalite",
        "typePiece",
        "numeroPiece",
        "typeDeMouvement",
        "motifMouvement",
        "typeContrat",
        "dateDebutMouvement",
        "dateFinMouvement",
        "profession",
        "emploi",
        "estCadre",
        "conventionApplicable",
        "salaireBrute",
        "tempsTravail",
        "categorie"
    })
    public static class Employes {
        protected String nom;
        protected String prenom;
        protected String sexe;
        protected String etatCivil;
        @XmlElementRef(name = "dateNaissance", namespace = "http://oracle.com/CmDmt.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateNaissance;
        protected String nationalite;
        @XmlElement(required = true)
        protected String typePiece;
        protected String numeroPiece;
        protected String typeDeMouvement;
        protected String motifMouvement;
        protected String typeContrat;
        @XmlElementRef(name = "dateDebutMouvement", namespace = "http://oracle.com/CmDmt.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateDebutMouvement;
        @XmlElementRef(name = "dateFinMouvement", namespace = "http://oracle.com/CmDmt.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dateFinMouvement;
        protected String profession;
        protected String emploi;
        @XmlElementRef(name = "estCadre", namespace = "http://oracle.com/CmDmt.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> estCadre;
        protected String conventionApplicable;
        @XmlElementRef(name = "salaireBrute", namespace = "http://oracle.com/CmDmt.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> salaireBrute;
        protected String tempsTravail;
        protected String categorie;
        @XmlAttribute(name = "action")
        protected ListAction action;

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
         * Obtient la valeur de la propriété typeDeMouvement.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTypeDeMouvement() {
            return typeDeMouvement;
        }

        /**
         * Définit la valeur de la propriété typeDeMouvement.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTypeDeMouvement(String value) {
            this.typeDeMouvement = value;
        }

        /**
         * Obtient la valeur de la propriété motifMouvement.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMotifMouvement() {
            return motifMouvement;
        }

        /**
         * Définit la valeur de la propriété motifMouvement.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMotifMouvement(String value) {
            this.motifMouvement = value;
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
         * Obtient la valeur de la propriété dateDebutMouvement.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateDebutMouvement() {
            return dateDebutMouvement;
        }

        /**
         * Définit la valeur de la propriété dateDebutMouvement.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateDebutMouvement(JAXBElement<XMLGregorianCalendar> value) {
            this.dateDebutMouvement = value;
        }

        /**
         * Obtient la valeur de la propriété dateFinMouvement.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public JAXBElement<XMLGregorianCalendar> getDateFinMouvement() {
            return dateFinMouvement;
        }

        /**
         * Définit la valeur de la propriété dateFinMouvement.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *
         */
        public void setDateFinMouvement(JAXBElement<XMLGregorianCalendar> value) {
            this.dateFinMouvement = value;
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
         * Obtient la valeur de la propriété estCadre.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public JAXBElement<Boolean> getEstCadre() {
            return estCadre;
        }

        /**
         * Définit la valeur de la propriété estCadre.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *
         */
        public void setEstCadre(JAXBElement<Boolean> value) {
            this.estCadre = value;
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
         * Obtient la valeur de la propriété salaireBrute.
         *
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public JAXBElement<BigDecimal> getSalaireBrute() {
            return salaireBrute;
        }

        /**
         * Définit la valeur de la propriété salaireBrute.
         *
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *
         */
        public void setSalaireBrute(JAXBElement<BigDecimal> value) {
            this.salaireBrute = value;
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

    public void setEmployes(List<Employes> employes) {
        this.employes = employes;
    }
}
