
package com.secusociale.portail.service.soap.immatPublicParapublic;

import com.secusociale.portail.service.PortailConstant;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * IMMAT2_INBOUND version 10: IMMATRICULATION INBOUND SERVICE
 *
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "IMMAT2_INBOUNDService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/IMMAT2_INBOUND", wsdlLocation = PortailConstant.PUBLIC_PARAPUBLIC_WSDL)
public class IMMAT2INBOUNDService
    extends Service
{

    private final static URL IMMAT2INBOUNDSERVICE_WSDL_LOCATION;
    private final static WebServiceException IMMAT2INBOUNDSERVICE_EXCEPTION;
    private final static QName IMMAT2INBOUNDSERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/IMMAT2_INBOUND", "IMMAT2_INBOUNDService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(PortailConstant.PUBLIC_PARAPUBLIC_WSDL);
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        IMMAT2INBOUNDSERVICE_WSDL_LOCATION = url;
        IMMAT2INBOUNDSERVICE_EXCEPTION = e;
    }

    public IMMAT2INBOUNDService() {
        super(__getWsdlLocation(), IMMAT2INBOUNDSERVICE_QNAME);
    }

    public IMMAT2INBOUNDService(WebServiceFeature... features) {
        super(__getWsdlLocation(), IMMAT2INBOUNDSERVICE_QNAME, features);
    }

    public IMMAT2INBOUNDService(URL wsdlLocation) {
        super(wsdlLocation, IMMAT2INBOUNDSERVICE_QNAME);
    }

    public IMMAT2INBOUNDService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, IMMAT2INBOUNDSERVICE_QNAME, features);
    }

    public IMMAT2INBOUNDService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IMMAT2INBOUNDService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IMMAT2INBOUNDPortType
     */
    @WebEndpoint(name = "IMMAT2_INBOUNDPort")
    public IMMAT2INBOUNDPortType getIMMAT2INBOUNDPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/IMMAT2_INBOUND", "IMMAT2_INBOUNDPort"), IMMAT2INBOUNDPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMMAT2INBOUNDPortType
     */
    @WebEndpoint(name = "IMMAT2_INBOUNDPort")
    public IMMAT2INBOUNDPortType getIMMAT2INBOUNDPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/IMMAT2_INBOUND", "IMMAT2_INBOUNDPort"), IMMAT2INBOUNDPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (IMMAT2INBOUNDSERVICE_EXCEPTION!= null) {
            throw IMMAT2INBOUNDSERVICE_EXCEPTION;
        }
        return IMMAT2INBOUNDSERVICE_WSDL_LOCATION;
    }

}
