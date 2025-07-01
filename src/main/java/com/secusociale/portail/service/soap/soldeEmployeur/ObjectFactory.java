
package com.secusociale.portail.service.soap.soldeEmployeur;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.soldeEmployeur package.
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.soldeEmployeur
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
     * Create an instance of {@link XAIGETSOLDE }
     *
     */
    public XAIGETSOLDE createXAIGETSOLDE() {
        return new XAIGETSOLDE();
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
     * Create an instance of {@link XAIGETSOLDE.Input }
     *
     */
    public XAIGETSOLDE.Input createXAIGETSOLDEInput() {
        return new XAIGETSOLDE.Input();
    }

    /**
     * Create an instance of {@link XAIGETSOLDE.Output }
     *
     */
    public XAIGETSOLDE.Output createXAIGETSOLDEOutput() {
        return new XAIGETSOLDE.Output();
    }

}
