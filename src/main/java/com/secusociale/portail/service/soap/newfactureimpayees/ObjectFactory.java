
package com.secusociale.portail.service.soap.newfactureimpayees;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.secusociale.portail.service.soap.newfactureimpayees package. 
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

    private final static QName _CMPAYDNSXAIResultsChkBox_QNAME = new QName("http://oracle.com/CM-PAYDNSXAI.xsd", "chkBox");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.newfactureimpayees
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CMPAYDNSXAI }
     * 
     */
    public CMPAYDNSXAI createCMPAYDNSXAI() {
        return new CMPAYDNSXAI();
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CMPAYDNSXAI.Results }
     * 
     */
    public CMPAYDNSXAI.Results createCMPAYDNSXAIResults() {
        return new CMPAYDNSXAI.Results();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM-PAYDNSXAI.xsd", name = "chkBox", scope = CMPAYDNSXAI.Results.class)
    public JAXBElement<Boolean> createCMPAYDNSXAIResultsChkBox(Boolean value) {
        return new JAXBElement<Boolean>(_CMPAYDNSXAIResultsChkBox_QNAME, Boolean.class, CMPAYDNSXAI.Results.class, value);
    }

}
