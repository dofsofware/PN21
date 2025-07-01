package com.secusociale.portail.service.attestation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.soap.derniersDeclarationsV2.DERNDNSEMPLOYEURSERVICEV2Fault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegularite;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegulariteFault;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegularitePortType;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegulariteService;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.ObjectFactory;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegularite.Input;

import java.time.Instant;

@Service
public class DemandeAttestationService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CmGetAttestationRegularite> createDossierAttestation(String numeroEmployeur) throws JAXBException {


        Input input = new Input();
        input.setNumeroEmployeur(numeroEmployeur);
//        input.setNumeroIdentifiant(numeroIdentifiant);

        Holder<CmGetAttestationRegularite> cmGetAttestationRegularite = new Holder<CmGetAttestationRegularite>();

        ObjectFactory obj = new ObjectFactory();
        cmGetAttestationRegularite.value = obj.createCmGetAttestationRegularite();

        cmGetAttestationRegularite.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CmGetAttestationRegularite.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmGetAttestationRegularite.value, System.out);

        try {
            CmGetAttestationRegulariteService attestationRegulariteService = new CmGetAttestationRegulariteService();
            CmGetAttestationRegularitePortType attestationRegularitePortType = attestationRegulariteService.getCmGetAttestationRegularitePort();

            BindingProvider prov = (BindingProvider) attestationRegularitePortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            attestationRegularitePortType.cmGetAttestationRegularite(cmGetAttestationRegularite);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), PortailConstant.ATTESTATION_REGU_WSDL);
                throw new RuntimeException(e);
            } else if (e instanceof CmGetAttestationRegulariteFault) {
                throw new RuntimeException(((CmGetAttestationRegulariteFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        return cmGetAttestationRegularite;

    }

}
