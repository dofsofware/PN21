package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.soldeEmployeur.*;
import com.secusociale.portail.service.soap.soldeEmployeur.XAIGETSOLDE.Input;
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
public class SoldeEmployeurService {

    @Autowired
    private MailService mailService;

    @Autowired
    private ServerCheckRepository serverCheckRepository;


    public Holder<XAIGETSOLDE> getSoldeEmployeur(String numeroUnique) throws JAXBException {

        Holder<XAIGETSOLDE> solde = new Holder<XAIGETSOLDE>();

        Input input = new Input();
        input.setPersonId(numeroUnique);

        ObjectFactory obj = new ObjectFactory();

        solde.value = obj.createXAIGETSOLDE();
        solde.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(XAIGETSOLDE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(solde.value, System.out);
        try {
        XAIGETSOLDEService service = new XAIGETSOLDEService();
        XAIGETSOLDEPortType portType = service.getXAIGETSOLDEPort();


        BindingProvider prov = (BindingProvider) portType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            portType.xaiGETSOLDE(solde);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/XAI_GETSOLDE?WSDL");
            } else if (e instanceof XAIGETSOLDEFault) {
                throw new RuntimeException(((XAIGETSOLDEFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return solde;

    }

}
