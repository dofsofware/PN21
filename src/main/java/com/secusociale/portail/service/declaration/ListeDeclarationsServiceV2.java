package com.secusociale.portail.service.declaration;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.derniersDeclarationsV2.*;
import com.secusociale.portail.service.soap.derniersDeclarationsV2.DERNDNSEMPLOYEURSERVICEV2.Input;
import com.secusociale.portail.service.soap.getFacture.CMGETFACTURE;
import com.secusociale.portail.service.soap.getFacture.CMGETFACTUREFault;
import com.secusociale.portail.service.soap.getFacture.CMGETFACTUREPortType;
import com.secusociale.portail.service.soap.getFacture.CMGETFACTUREService;
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
public class ListeDeclarationsServiceV2 {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<DERNDNSEMPLOYEURSERVICEV2> getListeDeclarations(String numeroUnique) throws JAXBException {


        Holder<DERNDNSEMPLOYEURSERVICEV2> derniersDns = new Holder<DERNDNSEMPLOYEURSERVICEV2>();

        Input input = new Input();

        input.setPersonId(numeroUnique);

        ObjectFactory obj = new ObjectFactory();

        derniersDns.value = obj.createDERNDNSEMPLOYEURSERVICEV2();
        derniersDns.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(DERNDNSEMPLOYEURSERVICEV2.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(derniersDns.value, System.out);

        try {
            DERNDNSEMPLOYEURSERVICEV2Service derndnsemployeurserviceService = new DERNDNSEMPLOYEURSERVICEV2Service();
            DERNDNSEMPLOYEURSERVICEV2PortType derndnsemployeurservicePortType = derndnsemployeurserviceService.getDERNDNSEMPLOYEURSERVICEV2Port();

            BindingProvider prov = (BindingProvider) derndnsemployeurservicePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            derndnsemployeurservicePortType.dernDNSEMPLOYEURSERVICEV2(derniersDns);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/DERN_DNS_EMPLOYEUR_SERVICE_V2?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof DERNDNSEMPLOYEURSERVICEV2Fault) {
                throw new RuntimeException(((DERNDNSEMPLOYEURSERVICEV2Fault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return derniersDns;

    }

    public Holder<CMGETFACTURE> getFacture(String numfac) throws JAXBException {

        Holder<CMGETFACTURE> facture = new Holder<CMGETFACTURE>();

        CMGETFACTURE.Input input = new CMGETFACTURE.Input();

        input.setFormId(numfac);

        com.secusociale.portail.service.soap.getFacture.ObjectFactory obj = new com.secusociale.portail.service.soap.getFacture.ObjectFactory();

        facture.value = obj.createCMGETFACTURE();
        facture.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CMGETFACTURE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(facture.value, System.out);

        try {
            CMGETFACTUREService cmgetfactureService = new CMGETFACTUREService();
            CMGETFACTUREPortType cmgetfacturePortType = cmgetfactureService.getCMGETFACTUREPort();

            BindingProvider prov = (BindingProvider) cmgetfacturePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmgetfacturePortType.cmGETFACTURE(facture);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(),Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_FACTURE?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CMGETFACTUREFault) {
                throw new RuntimeException(((CMGETFACTUREFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return facture;

    }
}
