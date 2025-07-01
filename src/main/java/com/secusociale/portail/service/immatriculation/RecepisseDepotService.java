package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.recepisseDepot.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@Service
public class RecepisseDepotService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<GETRECEPISSEDEPOTURL> getRecepisseDepotUrl(String idDossier) {

        Holder<GETRECEPISSEDEPOTURL> recepisseInboud = new Holder<GETRECEPISSEDEPOTURL>();

        ObjectFactory obj = new ObjectFactory();
        recepisseInboud.value = obj.createGETRECEPISSEDEPOTURL();
        recepisseInboud.value.setProcessFolwId(idDossier);
        try {
            GETRECEPISSEDEPOTURLService getrecepissedepoturlService = new GETRECEPISSEDEPOTURLService();
            GETRECEPISSEDEPOTURLPortType getrecepissedepoturlPortType = getrecepissedepoturlService.getGETRECEPISSEDEPOTURLPort();

            BindingProvider prov = (BindingProvider) getrecepissedepoturlPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            getrecepissedepoturlPortType.getRECEPISSEDEPOTURL(recepisseInboud);
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
            } else if (e instanceof GETRECEPISSEDEPOTURLFault) {
                throw new RuntimeException(((GETRECEPISSEDEPOTURLFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        return recepisseInboud;


    }

}
