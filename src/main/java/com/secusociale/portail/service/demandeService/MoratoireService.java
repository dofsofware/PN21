package com.secusociale.portail.service.demandeService;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.moratoire.*;
import com.secusociale.portail.service.soap.moratoire.CMGETINFORMATIONMORATOIRE.Input;
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
public class MoratoireService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMGETINFORMATIONMORATOIRE> demandeMoratoire(CMGETINFORMATIONMORATOIRE demandeMoratoire) throws JAXBException {

        Input input = new Input();

        input.setIdEmployer(demandeMoratoire.getInput().getIdEmployer());
        input.setDateDebut(demandeMoratoire.getInput().getDateDebut());
        input.setDateFin(demandeMoratoire.getInput().getDateFin());

        Holder<CMGETINFORMATIONMORATOIRE> moratoire = new Holder<CMGETINFORMATIONMORATOIRE>();
        ObjectFactory obj = new ObjectFactory();

        moratoire.value = obj.createCMGETINFORMATIONMORATOIRE();
        moratoire.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGETINFORMATIONMORATOIRE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(moratoire.value, System.out);
        try {
            CMGETINFORMATIONMORATOIREService cmgetinformationmoratoireService = new CMGETINFORMATIONMORATOIREService();
            CMGETINFORMATIONMORATOIREPortType cmgetinformationmoratoirePortType = cmgetinformationmoratoireService.getCMGETINFORMATIONMORATOIREPort();

            BindingProvider prov = (BindingProvider) cmgetinformationmoratoirePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmgetinformationmoratoirePortType.cmGETINFORMATIONMORATOIRE(moratoire);
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
            } else if (e instanceof CMGETINFORMATIONMORATOIREFault) {
                throw new RuntimeException(((CMGETINFORMATIONMORATOIREFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return moratoire;

    }

}
