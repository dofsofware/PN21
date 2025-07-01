package com.secusociale.portail.service.demandeService;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieuse;
import com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieuseFault;
import com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieusePortType;
import com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieuseService;
import com.secusociale.portail.service.soap.remise_gracieuse_add.*;
import com.secusociale.portail.service.soap.remise_gracieuse_add.CmAddDemandeRemiseGracieuse.Input;
import com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATION;
import com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATIONFault;
import com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATIONPortType;
import com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATIONService;
import com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieuse;
import com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieuseFault;
import com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieusePortType;
import com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@Service
public class RemiseGracieuseService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CmAddDemandeRemiseGracieuse> createRemiseGracieuse(CmAddDemandeRemiseGracieuse demandeRemiseGracieuse) throws JAXBException {

        Input input = new Input();
        Input.InformationDemande informationDemande = demandeRemiseGracieuse.getInput().getInformationDemande();
        XMLGregorianCalendar dd = informationDemande.getDateDebutPeriode();
        XMLGregorianCalendar df = informationDemande.getDateFinPeriode();
//        String strDateDebut = String.format("%d-%d-%d", dd.getYear(), dd.getMonth(), dd.getDay());
//        String strDateFin = String.format("%d-%d-%d", df.getYear(), df.getMonth(), dd.getDay());
//        Date debut = new SimpleDateFormat("yyyy-MM-dd").parse(strDateDebut);
//        Date fin = new SimpleDateFormat("yyyy-MM-dd").parse(strDateFin);
        try {
            informationDemande.setDateDebutPeriode(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(dd.getYear(), dd.getMonth(), dd.getDay(), DatatypeConstants.FIELD_UNDEFINED));
            informationDemande.setDateFinPeriode(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(df.getYear(), df.getMonth(), df.getDay(), DatatypeConstants.FIELD_UNDEFINED));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        input.setInformationEmployeur(demandeRemiseGracieuse.getInput().getInformationEmployeur());
        input.setInformationDemande(informationDemande);
        input.setDocuments(demandeRemiseGracieuse.getInput().getDocuments());

        Holder<CmAddDemandeRemiseGracieuse> remiseGracieuse = new Holder<CmAddDemandeRemiseGracieuse>();

        ObjectFactory obj = new ObjectFactory();

        remiseGracieuse.value = obj.createCmAddDemandeRemiseGracieuse();
        remiseGracieuse.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CmAddDemandeRemiseGracieuse.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(remiseGracieuse.value, System.out);
        try {
            CmAddDemandeRemiseGracieuseService addDemandeRemiseGracieuseService = new CmAddDemandeRemiseGracieuseService();
            CmAddDemandeRemiseGracieusePortType addDemandeRemiseGracieusePortType = addDemandeRemiseGracieuseService.getCmAddDemandeRemiseGracieusePort();
            BindingProvider prov = (BindingProvider) addDemandeRemiseGracieusePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            addDemandeRemiseGracieusePortType.cmAddDemandeRemiseGracieuse(remiseGracieuse);

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
            } else if (e instanceof CmAddDemandeRemiseGracieuseFault) {
                throw new RuntimeException(((CmAddDemandeRemiseGracieuseFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return remiseGracieuse;

    }


    public Holder<CmGetUrlAccuseReceptionRemiseGracieuse> getUrlRemise(String idDossier) throws JAXBException {

        com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieuse.Input input = new com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieuse.Input();

        input.setIdDossier(idDossier);

        Holder<CmGetUrlAccuseReceptionRemiseGracieuse> urlAccuseRemise = new Holder<CmGetUrlAccuseReceptionRemiseGracieuse>();

        com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.ObjectFactory obj = new com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.ObjectFactory();

        urlAccuseRemise.value = obj.createCmGetUrlAccuseReceptionRemiseGracieuse();
        urlAccuseRemise.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CmGetUrlAccuseReceptionRemiseGracieuse.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(urlAccuseRemise.value, System.out);

        CmGetUrlAccuseReceptionRemiseGracieuseService accuseReceptionRemiseGracieuseService = new CmGetUrlAccuseReceptionRemiseGracieuseService();
        CmGetUrlAccuseReceptionRemiseGracieusePortType accuseReceptionRemiseGracieusePortType = accuseReceptionRemiseGracieuseService.getCmGetUrlAccuseReceptionRemiseGracieusePort();

        BindingProvider prov = (BindingProvider) accuseReceptionRemiseGracieusePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            accuseReceptionRemiseGracieusePortType.cmGetUrlAccuseReceptionRemiseGracieuse(urlAccuseRemise);
        } catch (CmGetUrlAccuseReceptionRemiseGracieuseFault e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }


        return urlAccuseRemise;


    }


    public Holder<CMGetStatusRemiseGracieuse> getStatutRemise(String idDossier) throws JAXBException {

        com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieuse.Input input = new com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieuse.Input();

        input.setIdDossier(idDossier);

        Holder<CMGetStatusRemiseGracieuse> statutRemise = new Holder<CMGetStatusRemiseGracieuse>();

        com.secusociale.portail.service.soap.remise_gracieuse_statut.ObjectFactory obj = new com.secusociale.portail.service.soap.remise_gracieuse_statut.ObjectFactory();
        statutRemise.value = obj.createCMGetStatusRemiseGracieuse();
        statutRemise.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGetStatusRemiseGracieuse.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(statutRemise.value, System.out);

        CMGetStatusRemiseGracieuseService cmGetStatusRemiseGracieuseService = new CMGetStatusRemiseGracieuseService();
        CMGetStatusRemiseGracieusePortType cmGetStatusRemiseGracieusePortType = cmGetStatusRemiseGracieuseService.getCMGetStatusRemiseGracieusePort();

        BindingProvider prov = (BindingProvider) cmGetStatusRemiseGracieusePortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            cmGetStatusRemiseGracieusePortType.cmGetStatusRemiseGracieuse(statutRemise);
        } catch (CMGetStatusRemiseGracieuseFault e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return statutRemise;

    }

    public Holder<CMGETURLNOTIFICATION> getUrlNotification(String idDossier) throws JAXBException {

        com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATION.Input input = new com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATION.Input();
        input.setIdDossier(idDossier);

        Holder<CMGETURLNOTIFICATION> urlNotification = new Holder<CMGETURLNOTIFICATION>();
        com.secusociale.portail.service.soap.remise_gracieuse_notification_url.ObjectFactory obj = new com.secusociale.portail.service.soap.remise_gracieuse_notification_url.ObjectFactory();

        urlNotification.value = obj.createCMGETURLNOTIFICATION();
        urlNotification.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CMGETURLNOTIFICATION.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(urlNotification.value, System.out);

        CMGETURLNOTIFICATIONService cmgeturlnotificationService = new CMGETURLNOTIFICATIONService();
        CMGETURLNOTIFICATIONPortType cmgeturlnotificationPortType = cmgeturlnotificationService.getCMGETURLNOTIFICATIONPort();

        BindingProvider prov = (BindingProvider) cmgeturlnotificationPortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


        try {
            cmgeturlnotificationPortType.cmGETURLNOTIFICATION(urlNotification);
        } catch (CMGETURLNOTIFICATIONFault e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return urlNotification;

    }


}
