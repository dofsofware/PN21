package com.secusociale.portail.service.soap.detailsFactureDNS;

import com.secusociale.portail.config.Constants;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "CM_GET_FACTUREService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_FACTURE?WSDL")
public class DetailsFactureDNSServiceService extends Service {

    private final static URL DetailsFactureDNSServiceSERVICE_WSDL_LOCATION;
    private final static WebServiceException DetailsFactureDNSServiceSERVICE_EXCEPTION;
    private final static QName DetailsFactureDNSServiceSERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE", "CM_GET_FACTUREService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_FACTURE?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DetailsFactureDNSServiceSERVICE_WSDL_LOCATION = url;
        DetailsFactureDNSServiceSERVICE_EXCEPTION = e;
    }

    public DetailsFactureDNSServiceService() {
        super(__getWsdlLocation(), DetailsFactureDNSServiceSERVICE_QNAME);
    }

    public DetailsFactureDNSServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DetailsFactureDNSServiceSERVICE_QNAME, features);
    }

    public DetailsFactureDNSServiceService(URL wsdlLocation) {
        super(wsdlLocation, DetailsFactureDNSServiceSERVICE_QNAME);
    }

    public DetailsFactureDNSServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DetailsFactureDNSServiceSERVICE_QNAME, features);
    }

    public DetailsFactureDNSServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DetailsFactureDNSServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (DetailsFactureDNSServiceSERVICE_EXCEPTION != null) {
            throw DetailsFactureDNSServiceSERVICE_EXCEPTION;
        }
        return DetailsFactureDNSServiceSERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns CM_GET_FACTUREPortType
     */
    @WebEndpoint(name = "CM_GET_FACTUREPort")
    public DetailsFactureDNSServicePortType getCM_GET_FACTUREPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE", "CM_GET_FACTUREPort"), DetailsFactureDNSServicePortType.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns CM_GET_FACTUREPortType
     */
    @WebEndpoint(name = "CM_GET_FACTUREPort")
    public DetailsFactureDNSServicePortType getCM_GET_FACTUREPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE", "CM_GET_FACTUREPort"), DetailsFactureDNSServicePortType.class, features);
    }
}
