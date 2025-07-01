
package com.secusociale.portail.service.soap.facturesImpayes;

import com.secusociale.portail.config.Constants;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "CM_GET_FACTURE10Service", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE10", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_FACTURE10?WSDL")
public class FACTURESIMPAYEESSERVICEService
    extends Service {

    private final static URL FACTURESIMPAYEESSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException FACTURESIMPAYEESSERVICESERVICE_EXCEPTION;
    private final static QName FACTURESIMPAYEESSERVICESERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE10", "CM_GET_FACTURE10Service");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_FACTURE10?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FACTURESIMPAYEESSERVICESERVICE_WSDL_LOCATION = url;
        FACTURESIMPAYEESSERVICESERVICE_EXCEPTION = e;
    }

    public FACTURESIMPAYEESSERVICEService() {
        super(__getWsdlLocation(), FACTURESIMPAYEESSERVICESERVICE_QNAME);
    }

    public FACTURESIMPAYEESSERVICEService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FACTURESIMPAYEESSERVICESERVICE_QNAME, features);
    }

    public FACTURESIMPAYEESSERVICEService(URL wsdlLocation) {
        super(wsdlLocation, FACTURESIMPAYEESSERVICESERVICE_QNAME);
    }

    public FACTURESIMPAYEESSERVICEService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FACTURESIMPAYEESSERVICESERVICE_QNAME, features);
    }

    public FACTURESIMPAYEESSERVICEService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FACTURESIMPAYEESSERVICEService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (FACTURESIMPAYEESSERVICESERVICE_EXCEPTION != null) {
            throw FACTURESIMPAYEESSERVICESERVICE_EXCEPTION;
        }
        return FACTURESIMPAYEESSERVICESERVICE_WSDL_LOCATION;
    }

    /**
     *
     * @return
     *     returns FACTURESIMPAYEESSERVICEPortType
     */
    @WebEndpoint(name = "CM_GET_FACTURE10Port")
    public FACTURESIMPAYEESSERVICEPortType getFACTURESIMPAYEESSERVICEPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE10", "CM_GET_FACTURE10Port"), FACTURESIMPAYEESSERVICEPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FACTURESIMPAYEESSERVICEPortType
     */
    @WebEndpoint(name = "CM_GET_FACTURE10Port")
    public FACTURESIMPAYEESSERVICEPortType getFACTURESIMPAYEESSERVICEPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE10", "CM_GET_FACTURE10Port"), FACTURESIMPAYEESSERVICEPortType.class, features);
    }

}
