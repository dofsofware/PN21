package com.secusociale.portail.service.paiement;

import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.detailsFactureDNS.*;
import com.secusociale.portail.service.soap.detailsFactureDNS.DetailsFactureDNSService.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

@Service
public class DetailsFactureService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    public Holder<DetailsFactureDNSService> getUrlFacture(String formId) throws JAXBException {

        Holder<DetailsFactureDNSService> impayes = new Holder<DetailsFactureDNSService>();
        Input input = new Input();
        input.setFormId(formId);

        ObjectFactory obj = new ObjectFactory();
        impayes.value = obj.createDetailsFactureDNSService();
        impayes.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(DetailsFactureDNSService.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(impayes.value, System.out);


        DetailsFactureDNSServiceService detailsFactureDNSServiceService = new DetailsFactureDNSServiceService();
        DetailsFactureDNSServicePortType detailsFactureDNSServicePortType = detailsFactureDNSServiceService.getCM_GET_FACTUREPort();

        BindingProvider prov = (BindingProvider) detailsFactureDNSServicePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            detailsFactureDNSServicePortType.detailsFactureDNSService(impayes);
        } catch (DetailsFactureDNSServiceFault e) {
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return impayes;

    }
}
