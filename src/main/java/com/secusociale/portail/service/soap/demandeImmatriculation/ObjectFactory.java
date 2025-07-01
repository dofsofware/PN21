
package com.secusociale.portail.service.soap.demandeImmatriculation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.secusociale.portail.service.soap.immatriculation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME = new QName("http://oracle.com/IMMATRICULATION_INBOUND.xsd", "delivered");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.immatriculation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND }
     * 
     */
    public IMMATRICULATIONINBOUND createIMMATRICULATIONINBOUND() {
        return new IMMATRICULATIONINBOUND();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input }
     * 
     */
    public IMMATRICULATIONINBOUND.Input createIMMATRICULATIONINBOUNDInput() {
        return new IMMATRICULATIONINBOUND.Input();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents createIMMATRICULATIONINBOUNDInputDocuments() {
        return new IMMATRICULATIONINBOUND.Input.Documents();
    }

    /**
     * Create an instance of {@link Fault.ResponseData }
     * 
     */
    public Fault.ResponseData createFaultResponseData() {
        return new Fault.ResponseData();
    }

    /**
     * Create an instance of {@link Fault.ServerMessage }
     * 
     */
    public Fault.ServerMessage createFaultServerMessage() {
        return new Fault.ServerMessage();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Output }
     * 
     */
    public IMMATRICULATIONINBOUND.Output createIMMATRICULATIONINBOUNDOutput() {
        return new IMMATRICULATIONINBOUND.Output();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.EmployerQuery }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.EmployerQuery createIMMATRICULATIONINBOUNDInputEmployerQuery() {
        return new IMMATRICULATIONINBOUND.Input.EmployerQuery();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.MainRegistrationForm }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.MainRegistrationForm createIMMATRICULATIONINBOUNDInputMainRegistrationForm() {
        return new IMMATRICULATIONINBOUND.Input.MainRegistrationForm();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.LegalRepresentativeForm }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.LegalRepresentativeForm createIMMATRICULATIONINBOUNDInputLegalRepresentativeForm() {
        return new IMMATRICULATIONINBOUND.Input.LegalRepresentativeForm();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.EmployeList }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.EmployeList createIMMATRICULATIONINBOUNDInputEmployeList() {
        return new IMMATRICULATIONINBOUND.Input.EmployeList();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.DemandeEcrit }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.DemandeEcrit createIMMATRICULATIONINBOUNDInputDocumentsDemandeEcrit() {
        return new IMMATRICULATIONINBOUND.Input.Documents.DemandeEcrit();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.FormDemande }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.FormDemande createIMMATRICULATIONINBOUNDInputDocumentsFormDemande() {
        return new IMMATRICULATIONINBOUND.Input.Documents.FormDemande();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.RegistreCommerce }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.RegistreCommerce createIMMATRICULATIONINBOUNDInputDocumentsRegistreCommerce() {
        return new IMMATRICULATIONINBOUND.Input.Documents.RegistreCommerce();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.DeclarationEtablissement }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.DeclarationEtablissement createIMMATRICULATIONINBOUNDInputDocumentsDeclarationEtablissement() {
        return new IMMATRICULATIONINBOUND.Input.Documents.DeclarationEtablissement();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.PhotocopieStatus }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.PhotocopieStatus createIMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatus() {
        return new IMMATRICULATIONINBOUND.Input.Documents.PhotocopieStatus();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.DecretMinisteriel }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.DecretMinisteriel createIMMATRICULATIONINBOUNDInputDocumentsDecretMinisteriel() {
        return new IMMATRICULATIONINBOUND.Input.Documents.DecretMinisteriel();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.AvisImmatriculation }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.AvisImmatriculation createIMMATRICULATIONINBOUNDInputDocumentsAvisImmatriculation() {
        return new IMMATRICULATIONINBOUND.Input.Documents.AvisImmatriculation();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.Dmt }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.Dmt createIMMATRICULATIONINBOUNDInputDocumentsDmt() {
        return new IMMATRICULATIONINBOUND.Input.Documents.Dmt();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.ContratsTravail }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.ContratsTravail createIMMATRICULATIONINBOUNDInputDocumentsContratsTravail() {
        return new IMMATRICULATIONINBOUND.Input.Documents.ContratsTravail();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.Cni }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.Cni createIMMATRICULATIONINBOUNDInputDocumentsCni() {
        return new IMMATRICULATIONINBOUND.Input.Documents.Cni();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.CarteIdentiteConsulaire }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.CarteIdentiteConsulaire createIMMATRICULATIONINBOUNDInputDocumentsCarteIdentiteConsulaire() {
        return new IMMATRICULATIONINBOUND.Input.Documents.CarteIdentiteConsulaire();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.EtatRecensement }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.EtatRecensement createIMMATRICULATIONINBOUNDInputDocumentsEtatRecensement() {
        return new IMMATRICULATIONINBOUND.Input.Documents.EtatRecensement();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.AttestationChomage }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.AttestationChomage createIMMATRICULATIONINBOUNDInputDocumentsAttestationChomage() {
        return new IMMATRICULATIONINBOUND.Input.Documents.AttestationChomage();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.BulletinsSalaire }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.BulletinsSalaire createIMMATRICULATIONINBOUNDInputDocumentsBulletinsSalaire() {
        return new IMMATRICULATIONINBOUND.Input.Documents.BulletinsSalaire();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.CessationActivity }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.CessationActivity createIMMATRICULATIONINBOUNDInputDocumentsCessationActivity() {
        return new IMMATRICULATIONINBOUND.Input.Documents.CessationActivity();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.CarteNationaleIdentite }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.CarteNationaleIdentite createIMMATRICULATIONINBOUNDInputDocumentsCarteNationaleIdentite() {
        return new IMMATRICULATIONINBOUND.Input.Documents.CarteNationaleIdentite();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.DerniersBulletins }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.DerniersBulletins createIMMATRICULATIONINBOUNDInputDocumentsDerniersBulletins() {
        return new IMMATRICULATIONINBOUND.Input.Documents.DerniersBulletins();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.ManuscriteAdressee }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.ManuscriteAdressee createIMMATRICULATIONINBOUNDInputDocumentsManuscriteAdressee() {
        return new IMMATRICULATIONINBOUND.Input.Documents.ManuscriteAdressee();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.PassportDoc }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.PassportDoc createIMMATRICULATIONINBOUNDInputDocumentsPassportDoc() {
        return new IMMATRICULATIONINBOUND.Input.Documents.PassportDoc();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.PieceIdDoc }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.PieceIdDoc createIMMATRICULATIONINBOUNDInputDocumentsPieceIdDoc() {
        return new IMMATRICULATIONINBOUND.Input.Documents.PieceIdDoc();
    }

    /**
     * Create an instance of {@link IMMATRICULATIONINBOUND.Input.Documents.PieceIdGerantDoc }
     * 
     */
    public IMMATRICULATIONINBOUND.Input.Documents.PieceIdGerantDoc createIMMATRICULATIONINBOUNDInputDocumentsPieceIdGerantDoc() {
        return new IMMATRICULATIONINBOUND.Input.Documents.PieceIdGerantDoc();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.PhotocopieStatus.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.PhotocopieStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.FormDemande.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsFormDemandeDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.FormDemande.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.DerniersBulletins.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsDerniersBulletinsDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.DerniersBulletins.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.CessationActivity.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsCessationActivityDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.CessationActivity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.Dmt.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsDmtDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.Dmt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.ManuscriteAdressee.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsManuscriteAdresseeDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.ManuscriteAdressee.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.DecretMinisteriel.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsDecretMinisterielDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.DecretMinisteriel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.PieceIdDoc.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsPieceIdDocDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.PieceIdDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.AvisImmatriculation.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsAvisImmatriculationDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.AvisImmatriculation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.EtatRecensement.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsEtatRecensementDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.EtatRecensement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.Cni.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsCniDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.Cni.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.PassportDoc.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsPassportDocDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.PassportDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.CarteIdentiteConsulaire.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsCarteIdentiteConsulaireDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.CarteIdentiteConsulaire.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.BulletinsSalaire.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsBulletinsSalaireDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.BulletinsSalaire.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.AttestationChomage.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsAttestationChomageDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.AttestationChomage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.DeclarationEtablissement.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsDeclarationEtablissementDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.DeclarationEtablissement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.CarteNationaleIdentite.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsCarteNationaleIdentiteDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.CarteNationaleIdentite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.RegistreCommerce.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsRegistreCommerceDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.RegistreCommerce.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.PieceIdGerantDoc.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsPieceIdGerantDocDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.PieceIdGerantDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.DemandeEcrit.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsDemandeEcritDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.DemandeEcrit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/IMMATRICULATION_INBOUND.xsd", name = "delivered", scope = IMMATRICULATIONINBOUND.Input.Documents.ContratsTravail.class)
    public JAXBElement<Boolean> createIMMATRICULATIONINBOUNDInputDocumentsContratsTravailDelivered(Boolean value) {
        return new JAXBElement<Boolean>(_IMMATRICULATIONINBOUNDInputDocumentsPhotocopieStatusDelivered_QNAME, Boolean.class, IMMATRICULATIONINBOUND.Input.Documents.ContratsTravail.class, value);
    }

}
