
package com.secusociale.portail.service.soap.cessation_suspension_statut;

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
@WebService(name = "CM-GET_STATUS_CESSATION_SUSPENSIONPortType", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-GET_STATUS_CESSATION_SUSPENSION")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CMGETSTATUSCESSATIONSUSPENSIONPortType {


    /**
     * @param body
     * @throws CMGETSTATUSCESSATIONSUSPENSIONFault
     */
    @WebMethod(operationName = "CM-GET_STATUS_CESSATION_SUSPENSION", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM-GET_STATUS_CESSATION_SUSPENSION")
    public void cmGETSTATUSCESSATIONSUSPENSION(
        @WebParam(name = "CM-GET_STATUS_CESSATION_SUSPENSION", targetNamespace = "http://oracle.com/CM-GET_STATUS_CESSATION_SUSPENSION.xsd", mode = WebParam.Mode.INOUT, partName = "body")
            Holder<CMGETSTATUSCESSATIONSUSPENSION> body)
        throws CMGETSTATUSCESSATIONSUSPENSIONFault
    ;

}
