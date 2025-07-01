package com.secusociale.portail.service.paiement;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.newfactureimpayees.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class NewFacturesImpayesService {

    private final Logger log = LoggerFactory.getLogger(NewFacturesImpayesService.class);
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;


    public Holder<CMPAYDNSXAI> getFactureImpaye(String numeroUnique) throws JAXBException {

        Holder<CMPAYDNSXAI> impayes = new Holder<CMPAYDNSXAI>();

        ObjectFactory obj = new ObjectFactory();
        impayes.value = obj.createCMPAYDNSXAI();
        impayes.value.setPerId(numeroUnique);

        final JAXBContext jc = JAXBContext.newInstance(CMPAYDNSXAI.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(impayes.value, System.out);

        try {
            CMPAYDNSXAIService facturesimpayeesserviceService = new CMPAYDNSXAIService();
            CMPAYDNSXAIPortType facturesimpayeesservicePortType = facturesimpayeesserviceService.getCMPAYDNSXAIPort();

            BindingProvider prov = (BindingProvider) facturesimpayeesservicePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            facturesimpayeesservicePortType.cmPAYDNSXAI(impayes);
            System.out.println("Get Impayees complete!");
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
//            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                log.info("WebServiceException >> line 1199 ");
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_FACTURE10?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CMPAYDNSXAIFault) {
                log.info("FACTURESIMPAYEESSERVICEFault >> line 1202 ");
                throw new RuntimeException(((CMPAYDNSXAIFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                log.info("RuntimeException >> line 1205 ");
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return impayes;

    }

}
