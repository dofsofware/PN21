
package com.secusociale.portail.service.soap.derniersPaiements;

import com.secusociale.portail.config.Constants;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * DERN_PAY_EMPLOYEUR_SERVICE version 2: La liste des 10 derniéres paiements
 * <p>
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "DERN_PAY_EMPLOYEUR_SERVICEService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/DERN_PAY_EMPLOYEUR_SERVICE", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/DERN_PAY_EMPLOYEUR_SERVICE?WSDL")
public class DERNPAYEMPLOYEURSERVICEService
    extends Service {

    private final static URL DERNPAYEMPLOYEURSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException DERNPAYEMPLOYEURSERVICESERVICE_EXCEPTION;
    private final static QName DERNPAYEMPLOYEURSERVICESERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/DERN_PAY_EMPLOYEUR_SERVICE", "DERN_PAY_EMPLOYEUR_SERVICEService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/DERN_PAY_EMPLOYEUR_SERVICE?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DERNPAYEMPLOYEURSERVICESERVICE_WSDL_LOCATION = url;
        DERNPAYEMPLOYEURSERVICESERVICE_EXCEPTION = e;
    }

    public DERNPAYEMPLOYEURSERVICEService() {
        super(__getWsdlLocation(), DERNPAYEMPLOYEURSERVICESERVICE_QNAME);
    }

    public DERNPAYEMPLOYEURSERVICEService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DERNPAYEMPLOYEURSERVICESERVICE_QNAME, features);
    }

    public DERNPAYEMPLOYEURSERVICEService(URL wsdlLocation) {
        super(wsdlLocation, DERNPAYEMPLOYEURSERVICESERVICE_QNAME);
    }

    public DERNPAYEMPLOYEURSERVICEService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DERNPAYEMPLOYEURSERVICESERVICE_QNAME, features);
    }

    public DERNPAYEMPLOYEURSERVICEService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DERNPAYEMPLOYEURSERVICEService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (DERNPAYEMPLOYEURSERVICESERVICE_EXCEPTION != null) {
            throw DERNPAYEMPLOYEURSERVICESERVICE_EXCEPTION;
        }
        return DERNPAYEMPLOYEURSERVICESERVICE_WSDL_LOCATION;
    }

    /**
     *
     * @return
     *     returns DERNPAYEMPLOYEURSERVICEPortType
     */
    @WebEndpoint(name = "DERN_PAY_EMPLOYEUR_SERVICEPort")
    public DERNPAYEMPLOYEURSERVICEPortType getDERNPAYEMPLOYEURSERVICEPort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/DERN_PAY_EMPLOYEUR_SERVICE", "DERN_PAY_EMPLOYEUR_SERVICEPort"), DERNPAYEMPLOYEURSERVICEPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DERNPAYEMPLOYEURSERVICEPortType
     */
    @WebEndpoint(name = "DERN_PAY_EMPLOYEUR_SERVICEPort")
    public DERNPAYEMPLOYEURSERVICEPortType getDERNPAYEMPLOYEURSERVICEPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/DERN_PAY_EMPLOYEUR_SERVICE", "DERN_PAY_EMPLOYEUR_SERVICEPort"), DERNPAYEMPLOYEURSERVICEPortType.class, features);
    }

}
