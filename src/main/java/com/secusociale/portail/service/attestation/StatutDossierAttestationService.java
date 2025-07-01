package com.secusociale.portail.service.attestation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.soap.duplicata_facture_recherche.CMGETNUMEROFACTUREFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestation;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestation.Input;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestationFault;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestationPortType;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestationService;
import com.secusociale.portail.service.soap.statutAttestationRegularite.ObjectFactory;

import java.time.Instant;

@Service
public class StatutDossierAttestationService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMGetStatusDemandeAttestation> getStatutDemandeAttestation(String idDossier) throws JAXBException {
        Input input = new Input();
        input.setIdDossier(idDossier);

        Holder<CMGetStatusDemandeAttestation> cmGetstatutDemandeAttestation = new Holder<CMGetStatusDemandeAttestation>();
        ObjectFactory obj = new ObjectFactory();
        cmGetstatutDemandeAttestation.value = obj.createCMGetStatusDemandeAttestation();
        cmGetstatutDemandeAttestation.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGetStatusDemandeAttestation.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmGetstatutDemandeAttestation.value, System.out);

        try {
            CMGetStatusDemandeAttestationService attestationService = new CMGetStatusDemandeAttestationService();
            CMGetStatusDemandeAttestationPortType attestationPortType = attestationService.getCMGetStatusDemandeAttestationPort();

            BindingProvider prov = (BindingProvider) attestationPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            attestationPortType.cmGetStatusDemandeAttestation(cmGetstatutDemandeAttestation);

            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CM-GetStatusDemandeAttestation?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CMGetStatusDemandeAttestationFault) {
                throw new RuntimeException(((CMGetStatusDemandeAttestationFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return cmGetstatutDemandeAttestation;


    }

}
