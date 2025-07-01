package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.identifiantsEmployeurs.*;
import com.secusociale.portail.service.soap.identifiantsEmployeurs.IDsEMPLOYEURSERVICE.Input;
import com.secusociale.portail.web.rest.AccountResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@Service
public class IdentifiantsEmployeurService {
    private final Logger log = LoggerFactory.getLogger(IdentifiantsEmployeurService.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<IDsEMPLOYEURSERVICE> getIdentifiantsEmployeurs(String numeroUnique) throws JAXBException {

        Holder<IDsEMPLOYEURSERVICE> identifiants = new Holder<IDsEMPLOYEURSERVICE>();
        Input input = new Input();
        input.setPersonId(numeroUnique);

        ObjectFactory obj = new ObjectFactory();
        identifiants.value = obj.createIDsEMPLOYEURSERVICE();
        identifiants.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(IDsEMPLOYEURSERVICE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(identifiants.value, System.out);

        try {
            IDsEMPLOYEURSERVICEService dsEMPLOYEURSERVICEService = new IDsEMPLOYEURSERVICEService();
            IDsEMPLOYEURSERVICEPortType dsEMPLOYEURSERVICEPortType = dsEMPLOYEURSERVICEService.getIDsEMPLOYEURSERVICEPort();

            BindingProvider prov = (BindingProvider) dsEMPLOYEURSERVICEPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            Holder<IDsEMPLOYEURSERVICE> ids = new Holder<IDsEMPLOYEURSERVICE>();
            dsEMPLOYEURSERVICEPortType.iDsEMPLOYEURSERVICE(identifiants);

            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/IDs_EMPLOYEUR_SERVICE?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof IDsEMPLOYEURSERVICEFault) {
                throw new RuntimeException(((IDsEMPLOYEURSERVICEFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return identifiants;

    }

}
