
package com.secusociale.portail.service.soap.remise_gracieuse_add;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.remise_gracieuse_add package.
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.remise_gracieuse_add
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CmAddDemandeRemiseGracieuse }
     */
    public CmAddDemandeRemiseGracieuse createCmAddDemandeRemiseGracieuse() {
        return new CmAddDemandeRemiseGracieuse();
    }

    /**
     * Create an instance of {@link Fault }
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CmAddDemandeRemiseGracieuse.Input }
     */
    public CmAddDemandeRemiseGracieuse.Input createCmAddDemandeRemiseGracieuseInput() {
        return new CmAddDemandeRemiseGracieuse.Input();
    }

    /**
     * Create an instance of {@link CmAddDemandeRemiseGracieuse.Output }
     */
    public CmAddDemandeRemiseGracieuse.Output createCmAddDemandeRemiseGracieuseOutput() {
        return new CmAddDemandeRemiseGracieuse.Output();
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
     * Create an instance of {@link CmAddDemandeRemiseGracieuse.Input.InformationEmployeur }
     */
    public CmAddDemandeRemiseGracieuse.Input.InformationEmployeur createCmAddDemandeRemiseGracieuseInputInformationEmployeur() {
        return new CmAddDemandeRemiseGracieuse.Input.InformationEmployeur();
    }

    /**
     * Create an instance of {@link CmAddDemandeRemiseGracieuse.Input.InformationDemande }
     */
    public CmAddDemandeRemiseGracieuse.Input.InformationDemande createCmAddDemandeRemiseGracieuseInputInformationDemande() {
        return new CmAddDemandeRemiseGracieuse.Input.InformationDemande();
    }

    /**
     * Create an instance of {@link CmAddDemandeRemiseGracieuse.Input.Documents }
     */
    public CmAddDemandeRemiseGracieuse.Input.Documents createCmAddDemandeRemiseGracieuseInputDocuments() {
        return new CmAddDemandeRemiseGracieuse.Input.Documents();
    }

}
