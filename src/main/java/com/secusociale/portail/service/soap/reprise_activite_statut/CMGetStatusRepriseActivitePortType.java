
package com.secusociale.portail.service.soap.reprise_activite_statut;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "CM-GetStatusRepriseActivitePortType", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-GetStatusRepriseActivite")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CMGetStatusRepriseActivitePortType {


    /**
     * @param body
     * @throws CMGetStatusRepriseActiviteFault
     */
    @WebMethod(operationName = "CM-GetStatusRepriseActivite", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-GetStatusRepriseActivite")
    public void cmGetStatusRepriseActivite(
        @WebParam(name = "CM-GetStatusRepriseActivite", targetNamespace = "http://oracle.com/CM-GetStatusRepriseActivite.xsd", mode = WebParam.Mode.INOUT, partName = "body")
            Holder<CMGetStatusRepriseActivite> body)
        throws CMGetStatusRepriseActiviteFault
    ;

}
