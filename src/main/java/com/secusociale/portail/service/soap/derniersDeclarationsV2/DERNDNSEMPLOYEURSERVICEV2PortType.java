
package com.secusociale.portail.service.soap.derniersDeclarationsV2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.BindingType;
import javax.xml.ws.Holder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebService(name = "DERN_DNS_EMPLOYEUR_SERVICE_V2PortType", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/DERN_DNS_EMPLOYEUR_SERVICE_V2")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DERNDNSEMPLOYEURSERVICEV2PortType {


    /**
     *
     * @param body
     * @throws DERNDNSEMPLOYEURSERVICEV2Fault
     */
    @WebMethod(operationName = "DERN_DNS_EMPLOYEUR_SERVICE_V2", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/DERN_DNS_EMPLOYEUR_SERVICE_V2")
    public void dernDNSEMPLOYEURSERVICEV2(
        @WebParam(name = "DERN_DNS_EMPLOYEUR_SERVICE_V2", targetNamespace = "http://oracle.com/DERN_DNS_EMPLOYEUR_SERVICE_V2.xsd", mode = WebParam.Mode.INOUT, partName = "body")
        Holder<DERNDNSEMPLOYEURSERVICEV2> body)
        throws DERNDNSEMPLOYEURSERVICEV2Fault
    ;

}
