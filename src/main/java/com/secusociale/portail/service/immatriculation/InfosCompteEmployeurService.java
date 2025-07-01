package com.secusociale.portail.service.immatriculation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.certificatImmatriculation.CmGetCertificatImmatriculation;
import com.secusociale.portail.service.soap.infosEmployeur.CMINFOSEMPLOYEUR;
import com.secusociale.portail.service.soap.infosEmployeur.CMINFOSEMPLOYEUR.Input;
import com.secusociale.portail.service.soap.infosEmployeur.CMINFOSEMPLOYEURFault;
import com.secusociale.portail.service.soap.infosEmployeur.CMINFOSEMPLOYEURPortType;
import com.secusociale.portail.service.soap.infosEmployeur.CMINFOSEMPLOYEURService;
import com.secusociale.portail.service.soap.infosEmployeur.ObjectFactory;

import java.time.Instant;

@Service
public class InfosCompteEmployeurService {


    @Autowired
    private MailService mailService;

    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMINFOSEMPLOYEUR> getInfosCompteEmployeur(String idDossier) throws JAXBException {

        Input input = new Input();
        input.setProcessFlowId(idDossier);

        Holder<CMINFOSEMPLOYEUR> infosEmployeur = new Holder<CMINFOSEMPLOYEUR>();
        ObjectFactory obj = new ObjectFactory();


        infosEmployeur.value = obj.createCMINFOSEMPLOYEUR();
        infosEmployeur.value.setInput(input);


        /*
         * final JAXBContext jc =
         * JAXBContext.newInstance(CmGetCertificatImmatriculation.class); final
         * Marshaller marshaller = jc.createMarshaller();
         * marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         * marshaller.marshal(infosEmployeur.value, System.out);
         */


        CMINFOSEMPLOYEURService cminfosemployeurService = new CMINFOSEMPLOYEURService();
        CMINFOSEMPLOYEURPortType cminfosemployeurPortType = cminfosemployeurService.getCMINFOSEMPLOYEURPort();

        BindingProvider prov = (BindingProvider) cminfosemployeurPortType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            cminfosemployeurPortType.cmINFOSEMPLOYEUR(infosEmployeur);
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
            } else if (e instanceof CMINFOSEMPLOYEURFault) {
                throw new RuntimeException(((CMINFOSEMPLOYEURFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return infosEmployeur;

    }
}
