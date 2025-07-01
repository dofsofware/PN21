package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.listeEmployes.*;
import com.secusociale.portail.service.soap.listeEmployes.EMPLOYESLISTSERVICE.Input;
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
public class ListeEmployesService {

    @Autowired
    private MailService mailService;

    @Autowired
    private ServerCheckRepository serverCheckRepository;


    public Holder<EMPLOYESLISTSERVICE> getListeEmployes(String numeroUnique) throws JAXBException {

        Holder<EMPLOYESLISTSERVICE> listeEmployes = new Holder<EMPLOYESLISTSERVICE>();
        Input input = new Input();

        input.setPersonId(numeroUnique);
        ObjectFactory obj = new ObjectFactory();
        listeEmployes.value = obj.createEMPLOYESLISTSERVICE();
        listeEmployes.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(EMPLOYESLISTSERVICE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(listeEmployes.value, System.out);
        try {
            EMPLOYESLISTSERVICEService employeslistserviceService = new EMPLOYESLISTSERVICEService();
            EMPLOYESLISTSERVICEPortType employeslistservicePortType = employeslistserviceService.getEMPLOYESLISTSERVICEPort();

            BindingProvider prov = (BindingProvider) employeslistservicePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            employeslistservicePortType.employesLISTSERVICE(listeEmployes);

            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/EMPLOYES_LIST_SERVICE?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof EMPLOYESLISTSERVICEFault) {
                throw new RuntimeException(((EMPLOYESLISTSERVICEFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return listeEmployes;


    }

}
