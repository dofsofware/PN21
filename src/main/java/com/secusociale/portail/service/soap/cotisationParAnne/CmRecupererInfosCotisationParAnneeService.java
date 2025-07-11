
package com.secusociale.portail.service.soap.cotisationParAnne;

import com.secusociale.portail.config.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * CmRecupererInfosCotisationParAnnee version 2: Recuperer les informations de cotisation par annee
 *
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "CmRecupererInfosCotisationParAnneeService", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmRecupererInfosCotisationParAnnee", wsdlLocation = Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmRecupererInfosCotisationParAnnee?wsdl")
public class CmRecupererInfosCotisationParAnneeService
    extends Service
{

    private final static URL CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_WSDL_LOCATION;
    private final static WebServiceException CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_EXCEPTION;
    private final static QName CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_QNAME = new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmRecupererInfosCotisationParAnnee", "CmRecupererInfosCotisationParAnneeService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmRecupererInfosCotisationParAnnee?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_WSDL_LOCATION = url;
        CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_EXCEPTION = e;
    }

    public CmRecupererInfosCotisationParAnneeService() {
        super(__getWsdlLocation(), CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_QNAME);
    }

    public CmRecupererInfosCotisationParAnneeService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_QNAME, features);
    }

    public CmRecupererInfosCotisationParAnneeService(URL wsdlLocation) {
        super(wsdlLocation, CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_QNAME);
    }

    public CmRecupererInfosCotisationParAnneeService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_QNAME, features);
    }

    public CmRecupererInfosCotisationParAnneeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CmRecupererInfosCotisationParAnneeService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns CmRecupererInfosCotisationParAnneePortType
     */
    @WebEndpoint(name = "CmRecupererInfosCotisationParAnneePort")
    public CmRecupererInfosCotisationParAnneePortType getCmRecupererInfosCotisationParAnneePort() {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmRecupererInfosCotisationParAnnee", "CmRecupererInfosCotisationParAnneePort"), CmRecupererInfosCotisationParAnneePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CmRecupererInfosCotisationParAnneePortType
     */
    @WebEndpoint(name = "CmRecupererInfosCotisationParAnneePort")
    public CmRecupererInfosCotisationParAnneePortType getCmRecupererInfosCotisationParAnneePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmRecupererInfosCotisationParAnnee", "CmRecupererInfosCotisationParAnneePort"), CmRecupererInfosCotisationParAnneePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_EXCEPTION!= null) {
            throw CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_EXCEPTION;
        }
        return CMRECUPERERINFOSCOTISATIONPARANNEESERVICE_WSDL_LOCATION;
    }

}
