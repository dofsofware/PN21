package com.secusociale.portail.service.salaries;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.cmdmt.CmDmtFault;
import com.secusociale.portail.service.soap.salaries.cm_per_exist.*;
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
public class SalariesService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMPEREXIST> getPerExist(CMPEREXIST requestOBJ) throws JAXBException, JAXBException {

        Holder<CMPEREXIST> cmgetlistesecursalesHolder = new Holder<CMPEREXIST>();

        ObjectFactory obj = new ObjectFactory();

        CMPEREXIST cmperexist = obj.createCMPEREXIST();

        cmperexist.setPersonId(requestOBJ.getPersonId());
        cmperexist.setZone(requestOBJ.getZone());

        cmgetlistesecursalesHolder.value = cmperexist;

        final JAXBContext jc = JAXBContext.newInstance(CMPEREXIST.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmgetlistesecursalesHolder.value, System.out);
        try {
            CMPEREXISTService cmperexistService = new CMPEREXISTService();
            CMPEREXISTPortType cmperexistPort = cmperexistService.getCMPEREXISTPort();

            BindingProvider prov = (BindingProvider) cmperexistPort;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            cmperexistPort.cmPEREXIST(cmgetlistesecursalesHolder);

            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM_PER_EXIST?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CMPEREXISTFault) {
                throw new RuntimeException(((CMPEREXISTFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return cmgetlistesecursalesHolder;

    }
}
