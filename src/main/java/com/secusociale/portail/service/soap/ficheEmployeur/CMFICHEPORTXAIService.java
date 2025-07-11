
package com.secusociale.portail.service.soap.ficheEmployeur;

import com.secusociale.portail.config.Constants;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * CM-FICHEPORT_XAI version 2: ss fiche employer update
 * <p>
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "CM-FICHEPORT_XAIService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-FICHEPORT_XAI", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM-FICHEPORT_XAI?WSDL")
public class CMFICHEPORTXAIService
    extends Service {

    private final static URL CMFICHEPORTXAISERVICE_WSDL_LOCATION;
    private final static WebServiceException CMFICHEPORTXAISERVICE_EXCEPTION;
    private final static QName CMFICHEPORTXAISERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-FICHEPORT_XAI", "CM-FICHEPORT_XAIService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM-FICHEPORT_XAI?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CMFICHEPORTXAISERVICE_WSDL_LOCATION = url;
        CMFICHEPORTXAISERVICE_EXCEPTION = e;
    }

    public CMFICHEPORTXAIService() {
        super(__getWsdlLocation(), CMFICHEPORTXAISERVICE_QNAME);
    }

    public CMFICHEPORTXAIService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CMFICHEPORTXAISERVICE_QNAME, features);
    }

    public CMFICHEPORTXAIService(URL wsdlLocation) {
        super(wsdlLocation, CMFICHEPORTXAISERVICE_QNAME);
    }

    public CMFICHEPORTXAIService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CMFICHEPORTXAISERVICE_QNAME, features);
    }

    public CMFICHEPORTXAIService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CMFICHEPORTXAIService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (CMFICHEPORTXAISERVICE_EXCEPTION != null) {
            throw CMFICHEPORTXAISERVICE_EXCEPTION;
        }
        return CMFICHEPORTXAISERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns CMFICHEPORTXAIPortType
     */
    @WebEndpoint(name = "CM-FICHEPORT_XAIPort")
    public CMFICHEPORTXAIPortType getCMFICHEPORTXAIPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-FICHEPORT_XAI", "CM-FICHEPORT_XAIPort"), CMFICHEPORTXAIPortType.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns CMFICHEPORTXAIPortType
     */
    @WebEndpoint(name = "CM-FICHEPORT_XAIPort")
    public CMFICHEPORTXAIPortType getCMFICHEPORTXAIPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-FICHEPORT_XAI", "CM-FICHEPORT_XAIPort"), CMFICHEPORTXAIPortType.class, features);
    }

}
