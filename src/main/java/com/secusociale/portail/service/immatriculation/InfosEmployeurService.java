package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.employeurInfos.*;
import com.secusociale.portail.service.soap.employeurInfos.CMEMPLOYEURINFOS.Input;
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
public class InfosEmployeurService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    private final Logger log = LoggerFactory.getLogger(InfosEmployeurService.class);

    public Holder<CMEMPLOYEURINFOS> getEmployeurInfos(CMEMPLOYEURINFOS empl) throws JAXBException {

        Holder<CMEMPLOYEURINFOS> employeurInfos = new Holder<CMEMPLOYEURINFOS>();

        Input input = new Input();
        if (empl.getInput() == null)
            throw new RuntimeException(
                "Input est manquant!"
            );
        input.setTypeIdentifiant(empl.getInput().getTypeIdentifiant());
        input.setNumeroIdentifiant(empl.getInput().getNumeroIdentifiant());
        input.setNumeroUnique(empl.getInput().getNumeroUnique());

        ObjectFactory obj = new ObjectFactory();

        employeurInfos.value = obj.createCMEMPLOYEURINFOS();
        employeurInfos.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMEMPLOYEURINFOS.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(employeurInfos.value, System.out);

        try {
            CMEMPLOYEURINFOSService cmemployeurinfosService = new CMEMPLOYEURINFOSService();
            CMEMPLOYEURINFOSPortType cmemployeurinfosPortType = cmemployeurinfosService.getCMEMPLOYEURINFOSPort();

            BindingProvider prov = (BindingProvider) cmemployeurinfosPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmemployeurinfosPortType.cmEMPLOYEURINFOS(employeurInfos);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_EMPLOYEUR_INFOS?WSDL");
            } else if (e instanceof CMEMPLOYEURINFOSFault) {
                throw new RuntimeException(((CMEMPLOYEURINFOSFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return employeurInfos;

    }
}
