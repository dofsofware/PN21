
package com.secusociale.portail.service.soap.reprise_activite;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.reprise_activite package.
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

    private final static QName _CMAddProcessRepriseActiviteInputDocumentsPhotocopiePiece_QNAME = new QName("http://oracle.com/CM-AddProcessRepriseActivite.xsd", "photocopiePiece");
    private final static QName _CMAddProcessRepriseActiviteInputDocumentsAutreDocument_QNAME = new QName("http://oracle.com/CM-AddProcessRepriseActivite.xsd", "autreDocument");
    private final static QName _CMAddProcessRepriseActiviteInputDocumentsFormDeclarationReprise_QNAME = new QName("http://oracle.com/CM-AddProcessRepriseActivite.xsd", "formDeclarationReprise");
    private final static QName _CMAddProcessRepriseActiviteInputDocumentsDmt_QNAME = new QName("http://oracle.com/CM-AddProcessRepriseActivite.xsd", "dmt");
    private final static QName _CMAddProcessRepriseActiviteInputInformationRepriseNombreDmt_QNAME = new QName("http://oracle.com/CM-AddProcessRepriseActivite.xsd", "nombreDmt");
    private final static QName _CMAddProcessRepriseActiviteInputInformationRepriseNombreContrat_QNAME = new QName("http://oracle.com/CM-AddProcessRepriseActivite.xsd", "nombreContrat");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.reprise_activite
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
     * Create an instance of {@link CMAddProcessRepriseActivite }
     *
     */
    public CMAddProcessRepriseActivite createCMAddProcessRepriseActivite() {
        return new CMAddProcessRepriseActivite();
    }

    /**
     * Create an instance of {@link CMAddProcessRepriseActivite.Input }
     *
     */
    public CMAddProcessRepriseActivite.Input createCMAddProcessRepriseActiviteInput() {
        return new CMAddProcessRepriseActivite.Input();
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
     * Create an instance of {@link CMAddProcessRepriseActivite.Output }
     *
     */
    public CMAddProcessRepriseActivite.Output createCMAddProcessRepriseActiviteOutput() {
        return new CMAddProcessRepriseActivite.Output();
    }

    /**
     * Create an instance of {@link CMAddProcessRepriseActivite.Input.InformationEmployer }
     *
     */
    public CMAddProcessRepriseActivite.Input.InformationEmployer createCMAddProcessRepriseActiviteInputInformationEmployer() {
        return new CMAddProcessRepriseActivite.Input.InformationEmployer();
    }

    /**
     * Create an instance of {@link CMAddProcessRepriseActivite.Input.InformationReprise }
     *
     */
    public CMAddProcessRepriseActivite.Input.InformationReprise createCMAddProcessRepriseActiviteInputInformationReprise() {
        return new CMAddProcessRepriseActivite.Input.InformationReprise();
    }

    /**
     * Create an instance of {@link CMAddProcessRepriseActivite.Input.Documents }
     *
     */
    public CMAddProcessRepriseActivite.Input.Documents createCMAddProcessRepriseActiviteInputDocuments() {
        return new CMAddProcessRepriseActivite.Input.Documents();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", name = "photocopiePiece", scope = CMAddProcessRepriseActivite.Input.Documents.class)
    public JAXBElement<Boolean> createCMAddProcessRepriseActiviteInputDocumentsPhotocopiePiece(Boolean value) {
        return new JAXBElement<Boolean>(_CMAddProcessRepriseActiviteInputDocumentsPhotocopiePiece_QNAME, Boolean.class, CMAddProcessRepriseActivite.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", name = "autreDocument", scope = CMAddProcessRepriseActivite.Input.Documents.class)
    public JAXBElement<Boolean> createCMAddProcessRepriseActiviteInputDocumentsAutreDocument(Boolean value) {
        return new JAXBElement<Boolean>(_CMAddProcessRepriseActiviteInputDocumentsAutreDocument_QNAME, Boolean.class, CMAddProcessRepriseActivite.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", name = "formDeclarationReprise", scope = CMAddProcessRepriseActivite.Input.Documents.class)
    public JAXBElement<Boolean> createCMAddProcessRepriseActiviteInputDocumentsFormDeclarationReprise(Boolean value) {
        return new JAXBElement<Boolean>(_CMAddProcessRepriseActiviteInputDocumentsFormDeclarationReprise_QNAME, Boolean.class, CMAddProcessRepriseActivite.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", name = "dmt", scope = CMAddProcessRepriseActivite.Input.Documents.class)
    public JAXBElement<Boolean> createCMAddProcessRepriseActiviteInputDocumentsDmt(Boolean value) {
        return new JAXBElement<Boolean>(_CMAddProcessRepriseActiviteInputDocumentsDmt_QNAME, Boolean.class, CMAddProcessRepriseActivite.Input.Documents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", name = "nombreDmt", scope = CMAddProcessRepriseActivite.Input.InformationReprise.class)
    public JAXBElement<BigDecimal> createCMAddProcessRepriseActiviteInputInformationRepriseNombreDmt(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMAddProcessRepriseActiviteInputInformationRepriseNombreDmt_QNAME, BigDecimal.class, CMAddProcessRepriseActivite.Input.InformationReprise.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-AddProcessRepriseActivite.xsd", name = "nombreContrat", scope = CMAddProcessRepriseActivite.Input.InformationReprise.class)
    public JAXBElement<BigDecimal> createCMAddProcessRepriseActiviteInputInformationRepriseNombreContrat(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMAddProcessRepriseActiviteInputInformationRepriseNombreContrat_QNAME, BigDecimal.class, CMAddProcessRepriseActivite.Input.InformationReprise.class, value);
    }

}
