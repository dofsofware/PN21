package com.secusociale.portail.service.soap.detailsFactureDNS;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;

@WebService(name = "CM_GET_FACTUREPort", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DetailsFactureDNSServicePortType {

    @WebMethod(operationName = "CM_GET_FACTURE", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CM_GET_FACTURE")
    public void detailsFactureDNSService(
        @WebParam(name = "CM_GET_FACTURE", targetNamespace = "http://oracle.com/CM_GET_FACTURE.xsd", mode = WebParam.Mode.INOUT, partName = "body")
            Holder<DetailsFactureDNSService> body)
        throws DetailsFactureDNSServiceFault
        ;
}
