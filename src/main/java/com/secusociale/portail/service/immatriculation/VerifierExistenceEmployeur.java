package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.checkExistenceEmployeur.*;
import com.secusociale.portail.service.soap.checkExistenceEmployeur.CmCheckExistenceEmployeur.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@Service
public class VerifierExistenceEmployeur {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CmCheckExistenceEmployeur> verifierExistenceEmployeur(String typeIdentifiant, String numeroIdentifiant) throws JAXBException {

        Holder<CmCheckExistenceEmployeur> cmCheckExistenceEmployeur = new Holder<CmCheckExistenceEmployeur>();

        Input input = new Input();

        input.setTaxpayerIdentifierType(typeIdentifiant);
        input.setTaxpayerIdentifierValue(numeroIdentifiant);

        ObjectFactory obj = new ObjectFactory();
        JAXBElement<Boolean> mybool = obj.createCmCheckExistenceEmployeurInputCheckUsingTaxpayerIDOnly(true);
        input.setCheckUsingTaxpayerIDOnly(mybool);

        cmCheckExistenceEmployeur.value = obj.createCmCheckExistenceEmployeur();
        cmCheckExistenceEmployeur.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CmCheckExistenceEmployeur.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmCheckExistenceEmployeur.value, System.out);

        try {
            CmCheckExistenceEmployeurService checkExistenceEmployeurService = new CmCheckExistenceEmployeurService();
            CmCheckExistenceEmployeurPortType checkExistenceEmployeurPortType = checkExistenceEmployeurService.getCmCheckExistenceEmployeurPort();

            BindingProvider prov = (BindingProvider) checkExistenceEmployeurPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            checkExistenceEmployeurPortType.cmCheckExistenceEmployeur(cmCheckExistenceEmployeur);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(),PortailConstant.CHECK_EXISTENCE_EMPLOYEUR_WSDL);
            } else if (e instanceof CmCheckExistenceEmployeurFault) {
                throw new RuntimeException(((CmCheckExistenceEmployeurFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return cmCheckExistenceEmployeur;

    }

}
