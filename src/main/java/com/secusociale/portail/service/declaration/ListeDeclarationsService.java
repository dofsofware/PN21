package com.secusociale.portail.service.declaration;

import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.derniersDeclarations.*;
import com.secusociale.portail.service.soap.derniersDeclarations.DERNDNSEMPLOYEURSERVICE.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

@Service
public class ListeDeclarationsService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    public Holder<DERNDNSEMPLOYEURSERVICE> getListeDeclarations(String numeroUnique) throws JAXBException {


        Holder<DERNDNSEMPLOYEURSERVICE> derniersDns = new Holder<DERNDNSEMPLOYEURSERVICE>();

        Input input = new Input();

        input.setPersonId(numeroUnique);

        ObjectFactory obj = new ObjectFactory();

        derniersDns.value = obj.createDERNDNSEMPLOYEURSERVICE();
        derniersDns.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(DERNDNSEMPLOYEURSERVICE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(derniersDns.value, System.out);


        DERNDNSEMPLOYEURSERVICEService derndnsemployeurserviceService = new DERNDNSEMPLOYEURSERVICEService();
        DERNDNSEMPLOYEURSERVICEPortType derndnsemployeurservicePortType = derndnsemployeurserviceService.getDERNDNSEMPLOYEURSERVICEPort();

        BindingProvider prov = (BindingProvider) derndnsemployeurservicePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            derndnsemployeurservicePortType.dernDNSEMPLOYEURSERVICE(derniersDns);
        } catch (DERNDNSEMPLOYEURSERVICEFault e) {
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }
        return derniersDns;

    }
}
