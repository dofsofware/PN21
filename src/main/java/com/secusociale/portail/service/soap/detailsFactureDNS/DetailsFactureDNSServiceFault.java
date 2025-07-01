package com.secusociale.portail.service.soap.detailsFactureDNS;

import javax.xml.ws.WebFault;

@WebFault(name = "Fault", targetNamespace = "http://ouaf.oracle.com/")
public class DetailsFactureDNSServiceFault extends Exception {

    private Fault faultInfo;

    public DetailsFactureDNSServiceFault(String message, Fault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public DetailsFactureDNSServiceFault(String message, Fault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public Fault getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(Fault faultInfo) {
        this.faultInfo = faultInfo;
    }
}
