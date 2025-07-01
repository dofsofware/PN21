package com.secusociale.portail.service.attestation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.urlAttestationReguralite.*;
import com.secusociale.portail.service.soap.urlAttestationReguralite.CMGENATTESTATION.Input;
import com.secusociale.portail.service.soap.urlAttestationReguralite.CMGENATTESTATION.Input.Parameters;
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
public class GetAttestationUrlService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMGENATTESTATION> getUrlAttestation(String idDossier) throws JAXBException {

        String reportTemplate = "ATTESTATION_REGULARITE";
        String parameterName = "processFlowId";


        Holder<CMGENATTESTATION> cmGenAttestation = new Holder<CMGENATTESTATION>();

        Input input = new Input();
        Parameters parameters = new Parameters();

        parameters.setName(parameterName);
        parameters.setValue(idDossier);

        // Add parameter

        input.getParameters().add(parameters);

        // Set Report key

        input.setReportTemplate(reportTemplate);
        input.setReportKey(idDossier);

        ObjectFactory obj = new ObjectFactory();

        cmGenAttestation.value = obj.createCMGENATTESTATION();
        cmGenAttestation.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CMGENATTESTATION.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmGenAttestation.value, System.out);
        try {
            CMGENATTESTATIONService cmgenattestationService = new CMGENATTESTATIONService();
            CMGENATTESTATIONPortType cmgenattestationPortType = cmgenattestationService.getCMGENATTESTATIONPort();

            BindingProvider prov = (BindingProvider) cmgenattestationPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            cmgenattestationPortType.cmGENATTESTATION(cmGenAttestation);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(),PortailConstant.GEN_ATTESTATION_REG_WSDL);
                throw new RuntimeException(e);
            } else if (e instanceof CMGENATTESTATIONFault) {
                throw new RuntimeException(((CMGENATTESTATIONFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return cmGenAttestation;

    }

}
