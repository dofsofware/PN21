
package com.secusociale.portail.service.soap.CmFindEmployeurByTypeId;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.find package.
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.find
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CmFindEmployeurByTypeId }
     *
     */
    public CmFindEmployeurByTypeId createCmFindEmployeurByTypeId() {
        return new CmFindEmployeurByTypeId();
    }

    /**
     * Create an instance of {@link Fault }
     *
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CmFindEmployeurByTypeId.Output }
     *
     */
    public CmFindEmployeurByTypeId.Output createCmFindEmployeurByTypeIdOutput() {
        return new CmFindEmployeurByTypeId.Output();
    }

    /**
     * Create an instance of {@link CmFindEmployeurByTypeId.Input }
     *
     */
    public CmFindEmployeurByTypeId.Input createCmFindEmployeurByTypeIdInput() {
        return new CmFindEmployeurByTypeId.Input();
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
     * Create an instance of {@link CmFindEmployeurByTypeId.Output.Employeurs }
     *
     */
    public CmFindEmployeurByTypeId.Output.Employeurs createCmFindEmployeurByTypeIdOutputEmployeurs() {
        return new CmFindEmployeurByTypeId.Output.Employeurs();
    }

}
