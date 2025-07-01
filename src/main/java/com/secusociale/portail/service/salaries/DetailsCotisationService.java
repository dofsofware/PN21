package com.secusociale.portail.service.salaries;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.detailsCotisationParAnne.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.time.Instant;

@Service
public class DetailsCotisationService {
    private final MailService mailService;
    private final ServerCheckRepository serverCheckRepository;

    public DetailsCotisationService(MailService mailService, ServerCheckRepository serverCheckRepository) {
        this.mailService = mailService;
        this.serverCheckRepository = serverCheckRepository;
    }

    public Holder<CmRecupererInfosDetailsCotisation> detailsCotisation(CmRecupererInfosDetailsCotisation input) throws JAXBException {
        Holder<CmRecupererInfosDetailsCotisation> holder = new Holder<>();

        ObjectFactory obj = new ObjectFactory();
        CmRecupererInfosDetailsCotisation infos = obj.createCmRecupererInfosDetailsCotisation();

        infos.setPersonId(input.getPersonId());
        infos.setTypePiece(input.getTypePiece());
        infos.setNumeroPiece(input.getNumeroPiece());
        infos.setAnnee(input.getAnnee());

        holder.value = infos;

        final JAXBContext jc = JAXBContext.newInstance(CmRecupererInfosDetailsCotisation.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(holder.value, System.out);
        try {
            CmRecupererInfosDetailsCotisationService infoservice = new CmRecupererInfosDetailsCotisationService();
            CmRecupererInfosDetailsCotisationPortType infosPortType = infoservice.getCmRecupererInfosDetailsCotisationPort();

            BindingProvider prov = (BindingProvider) infosPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            infosPortType.cmRecupererInfosDetailsCotisation(holder);

            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmRecupererInfosDetailsCotisation?wsdl");
                throw new RuntimeException(e);
            } else if (e instanceof CmRecupererInfosDetailsCotisationFault) {
                throw new RuntimeException(((CmRecupererInfosDetailsCotisationFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return holder;
    }
}
