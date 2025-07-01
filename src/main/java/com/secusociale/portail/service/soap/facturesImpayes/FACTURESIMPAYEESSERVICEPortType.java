
package com.secusociale.portail.service.soap.facturesImpayes;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;


@WebService(name = "CM_GET_FACTURE10Port", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE10")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FACTURESIMPAYEESSERVICEPortType {

    @WebMethod(operationName = "CM_GET_FACTURE10", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE10")
    public void facturesIMPAYEESSERVICE(
        @WebParam(name = "CM_GET_FACTURE10", targetNamespace = "http://oracle.com/CM_GET_FACTURE10.xsd", mode = WebParam.Mode.INOUT, partName = "body")
            Holder<FACTURESIMPAYEESSERVICE> body)
        throws FACTURESIMPAYEESSERVICEFault
    ;

}
