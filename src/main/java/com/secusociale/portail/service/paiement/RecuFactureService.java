package com.secusociale.portail.service.paiement;

import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.recuPaiement.*;
import com.secusociale.portail.service.soap.recuPaiement.CmGenererUrlRecuEmployeur.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

@Service
public class RecuFactureService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CmGenererUrlRecuEmployeur> getRecu(String payEventId) throws JAXBException {

        Holder<CmGenererUrlRecuEmployeur> recuEmployeurHolder = new Holder<CmGenererUrlRecuEmployeur>();
        Input input = new Input();
        input.setPayEventId(payEventId);

        ObjectFactory obj = new ObjectFactory();
        recuEmployeurHolder.value = obj.createCmGenererUrlRecuEmployeur();
        recuEmployeurHolder.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CmGenererUrlRecuEmployeur.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(recuEmployeurHolder.value, System.out);


        CmGenererUrlRecuEmployeurService recuEmployeurService = new CmGenererUrlRecuEmployeurService();
        CmGenererUrlRecuEmployeurPortType recuEmployeurPortType = recuEmployeurService.getCmGenererUrlRecuEmployeurPort();

        BindingProvider prov = (BindingProvider) recuEmployeurPortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            recuEmployeurPortType.cmGenererUrlRecuEmployeur(recuEmployeurHolder);
        } catch (CmGenererUrlRecuEmployeurFault e) {
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return recuEmployeurHolder;

    }

}
