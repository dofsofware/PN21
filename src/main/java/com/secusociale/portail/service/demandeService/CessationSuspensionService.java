package com.secusociale.portail.service.demandeService;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.cessation_suspension.*;
import com.secusociale.portail.service.soap.cessation_suspension.CMADDCESSATIONORSUSPENSION.Input;
import com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSION;
import com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSIONFault;
import com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSIONPortType;
import com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSIONService;
import com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspension;
import com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspensionFault;
import com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspensionPortType;
import com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspensionService;
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
public class CessationSuspensionService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMADDCESSATIONORSUSPENSION> createCessationSuspension(CMADDCESSATIONORSUSPENSION cess_suspension) throws JAXBException {

        Input input = new Input();

        Holder<CMADDCESSATIONORSUSPENSION> cessation_suspension = new Holder<CMADDCESSATIONORSUSPENSION>();
        input.setInformationEmployer(cess_suspension.getInput().getInformationEmployer());
        input.setInformationDemande(cess_suspension.getInput().getInformationDemande());
        input.setDocuments(cess_suspension.getInput().getDocuments());

        ObjectFactory obj = new ObjectFactory();

        cessation_suspension.value = obj.createCMADDCESSATIONORSUSPENSION();
        cessation_suspension.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CMADDCESSATIONORSUSPENSION.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cessation_suspension.value, System.out);

        try {
            CMADDCESSATIONORSUSPENSIONService cmaddcessationorsuspensionService = new CMADDCESSATIONORSUSPENSIONService();
            CMADDCESSATIONORSUSPENSIONPortType cmaddcessationorsuspensionPortType = cmaddcessationorsuspensionService.getCMADDCESSATIONORSUSPENSIONPort();

            BindingProvider prov = (BindingProvider) cmaddcessationorsuspensionPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmaddcessationorsuspensionPortType.cmADDCESSATIONORSUSPENSION(cessation_suspension);

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
            } else if (e instanceof CMADDCESSATIONORSUSPENSIONFault) {
                throw new RuntimeException(((CMADDCESSATIONORSUSPENSIONFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return cessation_suspension;


    }


    public Holder<CMGETSTATUSCESSATIONSUSPENSION> getStatutCessationSuspension(String idDossier) throws JAXBException {

        com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSION.Input input = new com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSION.Input();

        Holder<CMGETSTATUSCESSATIONSUSPENSION> statut_cessation = new Holder<CMGETSTATUSCESSATIONSUSPENSION>();

        input.setIdDossier(idDossier);

        com.secusociale.portail.service.soap.cessation_suspension_statut.ObjectFactory obj = new com.secusociale.portail.service.soap.cessation_suspension_statut.ObjectFactory();

        statut_cessation.value = obj.createCMGETSTATUSCESSATIONSUSPENSION();
        statut_cessation.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CMGETSTATUSCESSATIONSUSPENSION.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(statut_cessation.value, System.out);

        CMGETSTATUSCESSATIONSUSPENSIONService cmgetstatuscessationsuspensionService = new CMGETSTATUSCESSATIONSUSPENSIONService();

        CMGETSTATUSCESSATIONSUSPENSIONPortType cmgetstatuscessationsuspensionPortType = cmgetstatuscessationsuspensionService.getCMGETSTATUSCESSATIONSUSPENSIONPort();


        BindingProvider prov = (BindingProvider) cmgetstatuscessationsuspensionPortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            cmgetstatuscessationsuspensionPortType.cmGETSTATUSCESSATIONSUSPENSION(statut_cessation);
        } catch (CMGETSTATUSCESSATIONSUSPENSIONFault e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }


        return statut_cessation;


    }


    public Holder<CMGetUrlAccuseReceptionCessationAndSuspension> getUrlReceptionCessationSuspension(String idDossier) throws JAXBException {
        com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspension.Input input = new com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspension.Input();

        input.setIdDossier(idDossier);

        Holder<CMGetUrlAccuseReceptionCessationAndSuspension> urlCessationSuspension = new Holder<CMGetUrlAccuseReceptionCessationAndSuspension>();

        com.secusociale.portail.service.soap.url_reception_cessation_suspension.ObjectFactory obj = new com.secusociale.portail.service.soap.url_reception_cessation_suspension.ObjectFactory();

        urlCessationSuspension.value = obj.createCMGetUrlAccuseReceptionCessationAndSuspension();
        urlCessationSuspension.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CMGetUrlAccuseReceptionCessationAndSuspension.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(urlCessationSuspension.value, System.out);

        CMGetUrlAccuseReceptionCessationAndSuspensionService accuseReceptionCessationAndSuspensionService = new CMGetUrlAccuseReceptionCessationAndSuspensionService();
        CMGetUrlAccuseReceptionCessationAndSuspensionPortType accuseReceptionCessationAndSuspensionPortType = accuseReceptionCessationAndSuspensionService.getCMGetUrlAccuseReceptionCessationAndSuspensionPort();

        BindingProvider prov = (BindingProvider) accuseReceptionCessationAndSuspensionPortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


        try {
            accuseReceptionCessationAndSuspensionPortType.cmGetUrlAccuseReceptionCessationAndSuspension(urlCessationSuspension);
        } catch (CMGetUrlAccuseReceptionCessationAndSuspensionFault e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return urlCessationSuspension;

    }

}
