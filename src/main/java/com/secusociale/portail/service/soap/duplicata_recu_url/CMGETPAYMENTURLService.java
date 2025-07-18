
package com.secusociale.portail.service.soap.duplicata_recu_url;

import com.secusociale.portail.config.Constants;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * CM_GET_PAYMENT_URL version 2: Get payment url
 * <p>
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "CM_GET_PAYMENT_URLService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_PAYMENT_URL", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_PAYMENT_URL?WSDL")
public class CMGETPAYMENTURLService
    extends Service {

    private final static URL CMGETPAYMENTURLSERVICE_WSDL_LOCATION;
    private final static WebServiceException CMGETPAYMENTURLSERVICE_EXCEPTION;
    private final static QName CMGETPAYMENTURLSERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_PAYMENT_URL", "CM_GET_PAYMENT_URLService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_PAYMENT_URL?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CMGETPAYMENTURLSERVICE_WSDL_LOCATION = url;
        CMGETPAYMENTURLSERVICE_EXCEPTION = e;
    }

    public CMGETPAYMENTURLService() {
        super(__getWsdlLocation(), CMGETPAYMENTURLSERVICE_QNAME);
    }

    public CMGETPAYMENTURLService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CMGETPAYMENTURLSERVICE_QNAME, features);
    }

    public CMGETPAYMENTURLService(URL wsdlLocation) {
        super(wsdlLocation, CMGETPAYMENTURLSERVICE_QNAME);
    }

    public CMGETPAYMENTURLService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CMGETPAYMENTURLSERVICE_QNAME, features);
    }

    public CMGETPAYMENTURLService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CMGETPAYMENTURLService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (CMGETPAYMENTURLSERVICE_EXCEPTION != null) {
            throw CMGETPAYMENTURLSERVICE_EXCEPTION;
        }
        return CMGETPAYMENTURLSERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns CMGETPAYMENTURLPortType
     */
    @WebEndpoint(name = "CM_GET_PAYMENT_URLPort")
    public CMGETPAYMENTURLPortType getCMGETPAYMENTURLPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_PAYMENT_URL", "CM_GET_PAYMENT_URLPort"), CMGETPAYMENTURLPortType.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns CMGETPAYMENTURLPortType
     */
    @WebEndpoint(name = "CM_GET_PAYMENT_URLPort")
    public CMGETPAYMENTURLPortType getCMGETPAYMENTURLPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_PAYMENT_URL", "CM_GET_PAYMENT_URLPort"), CMGETPAYMENTURLPortType.class, features);
    }

}
