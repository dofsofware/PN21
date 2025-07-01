
package com.secusociale.portail.service.soap.cessation_suspension;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.cessation_suspension package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CMADDCESSATIONORSUSPENSIONInputDocumentsDeclartionCessationActivite_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "declartionCessationActivite");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputDocumentsDecisionJudiciare_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "decisionJudiciare");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputDocumentsDemandeEmployer_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "demandeEmployer");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputDocumentsDmtDeSortie_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "dmtDeSortie");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputDocumentsDecisionAdministrative_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "decisionAdministrative");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputInformationDemandeDateFin_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "dateFin");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputInformationDemandeDateDebut_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "dateDebut");
    private final static QName _CMADDCESSATIONORSUSPENSIONInputInformationDemandeOptionAjouterAutreDocument_QNAME = new QName("http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", "optionAjouterAutreDocument");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.cessation_suspension
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CMADDCESSATIONORSUSPENSION }
     */
    public CMADDCESSATIONORSUSPENSION createCMADDCESSATIONORSUSPENSION() {
        return new CMADDCESSATIONORSUSPENSION();
    }

    /**
     * Create an instance of {@link Fault }
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CMADDCESSATIONORSUSPENSION.Input }
     */
    public CMADDCESSATIONORSUSPENSION.Input createCMADDCESSATIONORSUSPENSIONInput() {
        return new CMADDCESSATIONORSUSPENSION.Input();
    }

    /**
     * Create an instance of {@link CMADDCESSATIONORSUSPENSION.Output }
     */
    public CMADDCESSATIONORSUSPENSION.Output createCMADDCESSATIONORSUSPENSIONOutput() {
        return new CMADDCESSATIONORSUSPENSION.Output();
    }

    /**
     * Create an instance of {@link Fault.ResponseData }
     */
    public Fault.ResponseData createFaultResponseData() {
        return new Fault.ResponseData();
    }

    /**
     * Create an instance of {@link Fault.ServerMessage }
     */
    public Fault.ServerMessage createFaultServerMessage() {
        return new Fault.ServerMessage();
    }

    /**
     * Create an instance of {@link CMADDCESSATIONORSUSPENSION.Input.InformationEmployer }
     */
    public CMADDCESSATIONORSUSPENSION.Input.InformationEmployer createCMADDCESSATIONORSUSPENSIONInputInformationEmployer() {
        return new CMADDCESSATIONORSUSPENSION.Input.InformationEmployer();
    }

    /**
     * Create an instance of {@link CMADDCESSATIONORSUSPENSION.Input.InformationDemande }
     */
    public CMADDCESSATIONORSUSPENSION.Input.InformationDemande createCMADDCESSATIONORSUSPENSIONInputInformationDemande() {
        return new CMADDCESSATIONORSUSPENSION.Input.InformationDemande();
    }

    /**
     * Create an instance of {@link CMADDCESSATIONORSUSPENSION.Input.Documents }
     */
    public CMADDCESSATIONORSUSPENSION.Input.Documents createCMADDCESSATIONORSUSPENSIONInputDocuments() {
        return new CMADDCESSATIONORSUSPENSION.Input.Documents();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "declartionCessationActivite", scope = CMADDCESSATIONORSUSPENSION.Input.Documents.class)
    public JAXBElement<Boolean> createCMADDCESSATIONORSUSPENSIONInputDocumentsDeclartionCessationActivite(Boolean value) {
        return new JAXBElement<Boolean>(_CMADDCESSATIONORSUSPENSIONInputDocumentsDeclartionCessationActivite_QNAME, Boolean.class, CMADDCESSATIONORSUSPENSION.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "decisionJudiciare", scope = CMADDCESSATIONORSUSPENSION.Input.Documents.class)
    public JAXBElement<Boolean> createCMADDCESSATIONORSUSPENSIONInputDocumentsDecisionJudiciare(Boolean value) {
        return new JAXBElement<Boolean>(_CMADDCESSATIONORSUSPENSIONInputDocumentsDecisionJudiciare_QNAME, Boolean.class, CMADDCESSATIONORSUSPENSION.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "demandeEmployer", scope = CMADDCESSATIONORSUSPENSION.Input.Documents.class)
    public JAXBElement<Boolean> createCMADDCESSATIONORSUSPENSIONInputDocumentsDemandeEmployer(Boolean value) {
        return new JAXBElement<Boolean>(_CMADDCESSATIONORSUSPENSIONInputDocumentsDemandeEmployer_QNAME, Boolean.class, CMADDCESSATIONORSUSPENSION.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "dmtDeSortie", scope = CMADDCESSATIONORSUSPENSION.Input.Documents.class)
    public JAXBElement<Boolean> createCMADDCESSATIONORSUSPENSIONInputDocumentsDmtDeSortie(Boolean value) {
        return new JAXBElement<Boolean>(_CMADDCESSATIONORSUSPENSIONInputDocumentsDmtDeSortie_QNAME, Boolean.class, CMADDCESSATIONORSUSPENSION.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "decisionAdministrative", scope = CMADDCESSATIONORSUSPENSION.Input.Documents.class)
    public JAXBElement<Boolean> createCMADDCESSATIONORSUSPENSIONInputDocumentsDecisionAdministrative(Boolean value) {
        return new JAXBElement<Boolean>(_CMADDCESSATIONORSUSPENSIONInputDocumentsDecisionAdministrative_QNAME, Boolean.class, CMADDCESSATIONORSUSPENSION.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "dateFin", scope = CMADDCESSATIONORSUSPENSION.Input.InformationDemande.class)
    public JAXBElement<XMLGregorianCalendar> createCMADDCESSATIONORSUSPENSIONInputInformationDemandeDateFin(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMADDCESSATIONORSUSPENSIONInputInformationDemandeDateFin_QNAME, XMLGregorianCalendar.class, CMADDCESSATIONORSUSPENSION.Input.InformationDemande.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "dateDebut", scope = CMADDCESSATIONORSUSPENSION.Input.InformationDemande.class)
    public JAXBElement<XMLGregorianCalendar> createCMADDCESSATIONORSUSPENSIONInputInformationDemandeDateDebut(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMADDCESSATIONORSUSPENSIONInputInformationDemandeDateDebut_QNAME, XMLGregorianCalendar.class, CMADDCESSATIONORSUSPENSION.Input.InformationDemande.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-ADD_CESSATION_OR_SUSPENSION.xsd", name = "optionAjouterAutreDocument", scope = CMADDCESSATIONORSUSPENSION.Input.InformationDemande.class)
    public JAXBElement<Boolean> createCMADDCESSATIONORSUSPENSIONInputInformationDemandeOptionAjouterAutreDocument(Boolean value) {
        return new JAXBElement<Boolean>(_CMADDCESSATIONORSUSPENSIONInputInformationDemandeOptionAjouterAutreDocument_QNAME, Boolean.class, CMADDCESSATIONORSUSPENSION.Input.InformationDemande.class, value);
    }

}
