package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.numeroCompteEmployeur.*;
import com.secusociale.portail.service.soap.numeroCompteEmployeur.CMPerAccountById.Input;
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
public class NumeroCompteEmployeurService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMPerAccountById> getNumeroCompteByPersonId(String personId) throws JAXBException {

        Holder<CMPerAccountById> numeroCompte = new Holder<CMPerAccountById>();

        Input input = new Input();
        input.setPersonId(personId);

        ObjectFactory obj = new ObjectFactory();

        numeroCompte.value = obj.createCMPerAccountById();
        numeroCompte.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMPerAccountById.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(numeroCompte.value, System.out);

        try {
            CMPerAccountByIdService accountByIdService = new CMPerAccountByIdService();
            CMPerAccountByIdPortType accountByIdPortType = accountByIdService.getCMPerAccountByIdPort();

            BindingProvider prov = (BindingProvider) accountByIdPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            accountByIdPortType.cmPerAccountById(numeroCompte);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage());
                throw new RuntimeException(e);
            } else if (e instanceof CMPerAccountByIdFault) {
                throw new RuntimeException(((CMPerAccountByIdFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return numeroCompte;


    }

}
