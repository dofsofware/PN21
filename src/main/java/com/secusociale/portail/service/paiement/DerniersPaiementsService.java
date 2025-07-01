package com.secusociale.portail.service.paiement;

import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.derniersPaiements.*;
import com.secusociale.portail.service.soap.derniersPaiements.DERNPAYEMPLOYEURSERVICE.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

@Service
public class DerniersPaiementsService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    public Holder<DERNPAYEMPLOYEURSERVICE> getDerniersPaiementEmployeur(String numeroUnique) throws JAXBException {

        Holder<DERNPAYEMPLOYEURSERVICE> derniersPaiements = new Holder<DERNPAYEMPLOYEURSERVICE>();
        Input input = new Input();
        input.setPersonId(numeroUnique);

        ObjectFactory obj = new ObjectFactory();
        derniersPaiements.value = obj.createDERNPAYEMPLOYEURSERVICE();
        derniersPaiements.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(DERNPAYEMPLOYEURSERVICE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(derniersPaiements.value, System.out);

        DERNPAYEMPLOYEURSERVICEService dernpayemployeurserviceService = new DERNPAYEMPLOYEURSERVICEService();
        DERNPAYEMPLOYEURSERVICEPortType dernpayemployeurservicePortType = dernpayemployeurserviceService.getDERNPAYEMPLOYEURSERVICEPort();

        BindingProvider prov = (BindingProvider) dernpayemployeurservicePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            dernpayemployeurservicePortType.dernPAYEMPLOYEURSERVICE(derniersPaiements);
        } catch (DERNPAYEMPLOYEURSERVICEFault e) {
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }


        return derniersPaiements;


    }
}
