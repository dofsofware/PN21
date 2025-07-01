package com.secusociale.portail.service.declaration;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.getTauxPlafonds.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@Service
public class GetTauxEtPlafondsService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMGETCONSTANTS> getTauxPlafonds() {

        Holder<CMGETCONSTANTS> constantes = new Holder<CMGETCONSTANTS>();

        ObjectFactory obj = new ObjectFactory();
        constantes.value = obj.createCMGETCONSTANTS();

        try {
            CMGETCONSTANTSService cmgetconstantsService = new CMGETCONSTANTSService();
            CMGETCONSTANTSPortType cmgetconstantsPortType = cmgetconstantsService.getCMGETCONSTANTSPort();

            BindingProvider prov = (BindingProvider) cmgetconstantsPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            cmgetconstantsPortType.cmGETCONSTANTS(constantes);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_GET_CONSTANTS?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CMGETCONSTANTSFault) {
                throw new RuntimeException(((CMGETCONSTANTSFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return constantes;

    }

}
