package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.CmFindEmployeurByTypeId.*;
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
public class FindEmployeurByIdService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    private final Logger log = LoggerFactory.getLogger(FindEmployeurByIdService.class);

    public Holder<CmFindEmployeurByTypeId> findEmployeurByTypeId(String typeIdentifiant, String numeroIdentifiant) throws JAXBException {

        Holder<CmFindEmployeurByTypeId> cmCheckExistenceEmployeur = new Holder<CmFindEmployeurByTypeId>();
        CmFindEmployeurByTypeId.Input input = new CmFindEmployeurByTypeId.Input();

        input.setIdentifierType(typeIdentifiant);
        input.setIdentifierValue(numeroIdentifiant);

        ObjectFactory obj = new ObjectFactory();
        ;

        cmCheckExistenceEmployeur.value = obj.createCmFindEmployeurByTypeId();
        cmCheckExistenceEmployeur.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CmFindEmployeurByTypeId.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmCheckExistenceEmployeur.value, System.out);

        try {
            CmFindEmployeurByTypeIdService checkExistenceEmployeurService = new CmFindEmployeurByTypeIdService();
            CmFindEmployeurByTypeIdPortType checkExistenceEmployeurPortType = checkExistenceEmployeurService.getCmFindEmployeurByTypeIdPort();

            BindingProvider prov = (BindingProvider) checkExistenceEmployeurPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            checkExistenceEmployeurPortType.cmFindEmployeurByTypeId(cmCheckExistenceEmployeur);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {

            if (e instanceof WebServiceException) {
                e.printStackTrace();
                log.info("WebServiceException >> line 1199 ");
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmFindEmployeurByTypeId?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CmFindEmployeurByTypeIdFault) {
                throw new RuntimeException(((CmFindEmployeurByTypeIdFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return cmCheckExistenceEmployeur;

    }

}
