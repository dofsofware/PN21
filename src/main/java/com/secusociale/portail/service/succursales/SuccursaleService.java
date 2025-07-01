package com.secusociale.portail.service.succursales;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.succursales.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@org.springframework.stereotype.Service
public class SuccursaleService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMGETLISTESECURSALES> getListeSuccursales(String personId) throws JAXBException, JAXBException {

        Holder<CMGETLISTESECURSALES> cmgetlistesecursalesHolder = new Holder<CMGETLISTESECURSALES>();

        ObjectFactory obj = new ObjectFactory();

        CMGETLISTESECURSALES suc = obj.createCMGETLISTESECURSALES();

        suc.setPersonId(personId);

        cmgetlistesecursalesHolder.value = suc;

        final JAXBContext jc = JAXBContext.newInstance(CMGETLISTESECURSALES.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmgetlistesecursalesHolder.value, System.out);
        try {
            CMGETLISTESECURSALESService cmgetlistesecursalesService = new CMGETLISTESECURSALESService();
            CMGETLISTESECURSALESPortType cmgetlistesecursalesPortType = cmgetlistesecursalesService.getCMGETLISTESECURSALESPort();

            BindingProvider prov = (BindingProvider) cmgetlistesecursalesPortType;


            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            cmgetlistesecursalesPortType.cmGETLISTESECURSALES(cmgetlistesecursalesHolder);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(),PortailConstant.CM_GET_LISTE_SECURSALES_REG_WSDL);
                throw new RuntimeException(e);
            } else if (e instanceof CMGETLISTESECURSALESFault) {
                throw new RuntimeException(((CMGETLISTESECURSALESFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return cmgetlistesecursalesHolder;

    }
}
