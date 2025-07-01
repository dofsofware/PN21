package com.secusociale.portail.service.demandeService;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.reprise_activite.*;
import com.secusociale.portail.service.soap.reprise_activite.CMAddProcessRepriseActivite.Input;
import com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionReprise;
import com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionRepriseFault;
import com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionReprisePortType;
import com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionRepriseService;
import com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActivite;
import com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActiviteFault;
import com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActivitePortType;
import com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActiviteService;
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
public class RepriseActiviteService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMAddProcessRepriseActivite> createReprise(CMAddProcessRepriseActivite demandeReprise) throws JAXBException {

        Input input = new Input();
        input.setInformationEmployer(demandeReprise.getInput().getInformationEmployer());
        input.setInformationReprise(demandeReprise.getInput().getInformationReprise());
        input.setDocuments(demandeReprise.getInput().getDocuments());

        Holder<CMAddProcessRepriseActivite> reprise = new Holder<>();

        ObjectFactory obj = new ObjectFactory();

        reprise.value = obj.createCMAddProcessRepriseActivite();
        reprise.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMAddProcessRepriseActivite.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(reprise.value, System.out);

        try {
            CMAddProcessRepriseActiviteService activiteService = new CMAddProcessRepriseActiviteService();
            CMAddProcessRepriseActivitePortType activitePortType = activiteService.getCMAddProcessRepriseActivitePort();

            BindingProvider prov = (BindingProvider) activitePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            activitePortType.cmAddProcessRepriseActivite(reprise);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM-AddProcessRepriseActivite?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CMAddProcessRepriseActiviteFault) {
                throw new RuntimeException(((CMAddProcessRepriseActiviteFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        return reprise;

    }


    public Holder<CMGetUrlAccuseReceptionReprise> getUrlAccuseReprise(String idDossier) throws JAXBException {

        com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionReprise.Input input = new com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionReprise.Input();

        input.setIdDossier(idDossier);

        Holder<CMGetUrlAccuseReceptionReprise> accuse = new Holder<CMGetUrlAccuseReceptionReprise>();

        com.secusociale.portail.service.soap.reprise_activite_accuse_url.ObjectFactory obj = new com.secusociale.portail.service.soap.reprise_activite_accuse_url.ObjectFactory();
        accuse.value = obj.createCMGetUrlAccuseReceptionReprise();
        accuse.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGetUrlAccuseReceptionReprise.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(accuse.value, System.out);


        CMGetUrlAccuseReceptionRepriseService accuseReceptionRepriseService = new CMGetUrlAccuseReceptionRepriseService();
        CMGetUrlAccuseReceptionReprisePortType accuseReceptionReprisePortType = accuseReceptionRepriseService.getCMGetUrlAccuseReceptionReprisePort();

        BindingProvider prov = (BindingProvider) accuseReceptionReprisePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            accuseReceptionReprisePortType.cmGetUrlAccuseReceptionReprise(accuse);
        } catch (CMGetUrlAccuseReceptionRepriseFault e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return accuse;


    }


    public Holder<CMGetStatusRepriseActivite> getStatutReprise(String idDossier) throws JAXBException {

        com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActivite.Input input = new com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActivite.Input();
        input.setIdDossier(idDossier);

        Holder<CMGetStatusRepriseActivite> statutReprise = new Holder<CMGetStatusRepriseActivite>();
        com.secusociale.portail.service.soap.reprise_activite_statut.ObjectFactory obj = new com.secusociale.portail.service.soap.reprise_activite_statut.ObjectFactory();

        statutReprise.value = obj.createCMGetStatusRepriseActivite();
        statutReprise.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGetStatusRepriseActivite.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(statutReprise.value, System.out);
        try {
            CMGetStatusRepriseActiviteService activiteService = new CMGetStatusRepriseActiviteService();
            CMGetStatusRepriseActivitePortType activitePortType = activiteService.getCMGetStatusRepriseActivitePort();

            BindingProvider prov = (BindingProvider) activitePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            activitePortType.cmGetStatusRepriseActivite(statutReprise);

            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
                throw new RuntimeException(e);
            } else if (e instanceof CMGetStatusRepriseActiviteFault) {
                throw new RuntimeException(((CMGetStatusRepriseActiviteFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return statutReprise;


    }


}
