package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.ficheEmployeur.*;
import com.secusociale.portail.service.soap.ficheEmployeur.CMFICHEPORTXAI.Input;
import com.secusociale.portail.service.soap.numeroCompteEmployeur.CMPerAccountByIdFault;
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
public class FicheEmployeurService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMFICHEPORTXAI> updateEmployeur(CMFICHEPORTXAI employeurUpdate) throws JAXBException {

        Input input = new Input();

        input.setChangeStatus(employeurUpdate.getInput().getChangeStatus());
        input.setCommentaire(employeurUpdate.getInput().getCommentaire());
        input.setDemandeEcrite(employeurUpdate.getInput().getDemandeEcrite());
        input.setIdentifiant(employeurUpdate.getInput().getIdentifiant());
        input.setNouveauStatut(employeurUpdate.getInput().getNouveauStatut());
        input.setTypeOfContact(employeurUpdate.getInput().getTypeOfContact());

        Holder<CMFICHEPORTXAI> emp = new Holder<CMFICHEPORTXAI>();

        ObjectFactory obj = new ObjectFactory();

        emp.value = obj.createCMFICHEPORTXAI();
        emp.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMFICHEPORTXAI.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(emp.value, System.out);
        try {
            CMFICHEPORTXAIService cmficheportxaiService = new CMFICHEPORTXAIService();
            CMFICHEPORTXAIPortType cmficheportxaiPortType = cmficheportxaiService.getCMFICHEPORTXAIPort();


            BindingProvider prov = (BindingProvider) cmficheportxaiPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmficheportxaiPortType.cmFICHEPORTXAI(emp);
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
            } else if (e instanceof CMFICHEPORTXAIFault) {
                throw new RuntimeException(((CMFICHEPORTXAIFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        return emp;

    }

}
