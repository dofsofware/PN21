package com.secusociale.portail.service.soap.detailsFactureDNS;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.facturesImpayes
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DetailsFactureDNSService }
     */
    public DetailsFactureDNSService createDetailsFactureDNSService() {
        return new DetailsFactureDNSService();
    }

    /**
     * Create an instance of {@link Fault }
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link DetailsFactureDNSService.Input }
     */
    public DetailsFactureDNSService.Input createDetailsFactureDNSServiceInput() {
        return new DetailsFactureDNSService.Input();
    }

    /**
     * Create an instance of {@link DetailsFactureDNSService.Output }
     */
    public DetailsFactureDNSService.Output createDetailsFactureDNSServiceOutput() {
        return new DetailsFactureDNSService.Output();
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
}
