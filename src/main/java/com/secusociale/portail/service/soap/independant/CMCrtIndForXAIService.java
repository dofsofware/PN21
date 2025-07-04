
package com.secusociale.portail.service.soap.independant;

import com.secusociale.portail.config.Constants;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * CM-CrtIndForXAI version 6: formulaire individuelle
 * <p>
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "CM-CrtIndForXAIService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-CrtIndForXAI", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM-CrtIndForXAI?WSDL")
public class CMCrtIndForXAIService
    extends Service {

    private final static URL CMCRTINDFORXAISERVICE_WSDL_LOCATION;
    private final static WebServiceException CMCRTINDFORXAISERVICE_EXCEPTION;
    private final static QName CMCRTINDFORXAISERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-CrtIndForXAI", "CM-CrtIndForXAIService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM-CrtIndForXAI?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CMCRTINDFORXAISERVICE_WSDL_LOCATION = url;
        CMCRTINDFORXAISERVICE_EXCEPTION = e;
    }

    public CMCrtIndForXAIService() {
        super(__getWsdlLocation(), CMCRTINDFORXAISERVICE_QNAME);
    }

    public CMCrtIndForXAIService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CMCRTINDFORXAISERVICE_QNAME, features);
    }

    public CMCrtIndForXAIService(URL wsdlLocation) {
        super(wsdlLocation, CMCRTINDFORXAISERVICE_QNAME);
    }

    public CMCrtIndForXAIService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CMCRTINDFORXAISERVICE_QNAME, features);
    }

    public CMCrtIndForXAIService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CMCrtIndForXAIService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (CMCRTINDFORXAISERVICE_EXCEPTION != null) {
            throw CMCRTINDFORXAISERVICE_EXCEPTION;
        }
        return CMCRTINDFORXAISERVICE_WSDL_LOCATION;
    }

    /**
     *
     * @return
     *     returns CMCrtIndForXAIPortType
     */
    @WebEndpoint(name = "CM-CrtIndForXAIPort")
    public CMCrtIndForXAIPortType getCMCrtIndForXAIPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-CrtIndForXAI", "CM-CrtIndForXAIPort"), CMCrtIndForXAIPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CMCrtIndForXAIPortType
     */
    @WebEndpoint(name = "CM-CrtIndForXAIPort")
    public CMCrtIndForXAIPortType getCMCrtIndForXAIPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-CrtIndForXAI", "CM-CrtIndForXAIPort"), CMCrtIndForXAIPortType.class, features);
    }

}
