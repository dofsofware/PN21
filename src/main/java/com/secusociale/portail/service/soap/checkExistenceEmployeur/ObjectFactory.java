
package com.secusociale.portail.service.soap.checkExistenceEmployeur;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.secusociale.portail.service.soap.checkExistenceEmployeur package. 
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

    private final static QName _CmCheckExistenceEmployeurInputCheckUsingTaxpayerIDOnly_QNAME = new QName("http://oracle.com/CmCheckExistenceEmployeur.xsd", "checkUsingTaxpayerIDOnly");
    private final static QName _CmCheckExistenceEmployeurOutputIsTaxpayerNameExist_QNAME = new QName("http://oracle.com/CmCheckExistenceEmployeur.xsd", "isTaxpayerNameExist");
    private final static QName _CmCheckExistenceEmployeurOutputIsTaxpayerIdentifierExist_QNAME = new QName("http://oracle.com/CmCheckExistenceEmployeur.xsd", "isTaxpayerIdentifierExist");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.checkExistenceEmployeur
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
     * Create an instance of {@link CmCheckExistenceEmployeur }
     * 
     */
    public CmCheckExistenceEmployeur createCmCheckExistenceEmployeur() {
        return new CmCheckExistenceEmployeur();
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
     * Create an instance of {@link CmCheckExistenceEmployeur.Input }
     * 
     */
    public CmCheckExistenceEmployeur.Input createCmCheckExistenceEmployeurInput() {
        return new CmCheckExistenceEmployeur.Input();
    }

    /**
     * Create an instance of {@link CmCheckExistenceEmployeur.Output }
     * 
     */
    public CmCheckExistenceEmployeur.Output createCmCheckExistenceEmployeurOutput() {
        return new CmCheckExistenceEmployeur.Output();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd", name = "checkUsingTaxpayerIDOnly", scope = CmCheckExistenceEmployeur.Input.class)
    public JAXBElement<Boolean> createCmCheckExistenceEmployeurInputCheckUsingTaxpayerIDOnly(Boolean value) {
        return new JAXBElement<Boolean>(_CmCheckExistenceEmployeurInputCheckUsingTaxpayerIDOnly_QNAME, Boolean.class, CmCheckExistenceEmployeur.Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd", name = "isTaxpayerNameExist", scope = CmCheckExistenceEmployeur.Output.class)
    public JAXBElement<Boolean> createCmCheckExistenceEmployeurOutputIsTaxpayerNameExist(Boolean value) {
        return new JAXBElement<Boolean>(_CmCheckExistenceEmployeurOutputIsTaxpayerNameExist_QNAME, Boolean.class, CmCheckExistenceEmployeur.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd", name = "isTaxpayerIdentifierExist", scope = CmCheckExistenceEmployeur.Output.class)
    public JAXBElement<Boolean> createCmCheckExistenceEmployeurOutputIsTaxpayerIdentifierExist(Boolean value) {
        return new JAXBElement<Boolean>(_CmCheckExistenceEmployeurOutputIsTaxpayerIdentifierExist_QNAME, Boolean.class, CmCheckExistenceEmployeur.Output.class, value);
    }

}
