package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.agencesRattachement.*;
import com.secusociale.portail.service.soap.agencesRattachement.AGENCESEMPLOYEURSERVICE.Input;
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
public class AgencesRattachementServicev2 {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    private final Logger log = LoggerFactory.getLogger(AgencesRattachementServicev2.class);

    public Holder<AGENCESEMPLOYEURSERVICE> getAgencesRattachement(String numeroUnique) throws JAXBException {

        Holder<AGENCESEMPLOYEURSERVICE> agences = new Holder<AGENCESEMPLOYEURSERVICE>();

        Input input = new Input();
        input.setPersonId(numeroUnique);

        ObjectFactory obj = new ObjectFactory();
        agences.value = obj.createAGENCESEMPLOYEURSERVICE();
        agences.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(AGENCESEMPLOYEURSERVICE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(agences.value, System.out);

        AGENCESEMPLOYEURSERVICEService agencesemployeurserviceService = new AGENCESEMPLOYEURSERVICEService();
        AGENCESEMPLOYEURSERVICEPortType agencesemployeurservicePortType = agencesemployeurserviceService.getAGENCESEMPLOYEURSERVICEPort();

        try {

        BindingProvider prov = (BindingProvider) agencesemployeurservicePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            agencesemployeurservicePortType.agencesEMPLOYEURSERVICE(agences);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                log.info("WebServiceException >> line 1199 ");
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/AGENCES_EMPLOYEUR_SERVICE?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof AGENCESEMPLOYEURSERVICEFault) {
                log.info("CMEMPLOYEURINFOSFault >> line 1202 ");
                throw new RuntimeException(((AGENCESEMPLOYEURSERVICEFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                log.info("RuntimeException >> line 1205 ");
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return agences;


    }

}
