
package com.secusociale.portail.service.soap.CmFindEmployeurByTypeId;

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
 *
 */
@WebService(name = "CmFindEmployeurByTypeIdPortType", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmFindEmployeurByTypeId")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CmFindEmployeurByTypeIdPortType {


    /**
     *
     * @param body
     * @throws CmFindEmployeurByTypeIdFault
     */
    @WebMethod(operationName = "CmFindEmployeurByTypeId", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmFindEmployeurByTypeId")
    public void cmFindEmployeurByTypeId(
        @WebParam(name = "CmFindEmployeurByTypeId", targetNamespace = "http://oracle.com/CmFindEmployeurByTypeId.xsd", mode = WebParam.Mode.INOUT, partName = "body")
        Holder<CmFindEmployeurByTypeId> body)
        throws CmFindEmployeurByTypeIdFault
    ;

}
