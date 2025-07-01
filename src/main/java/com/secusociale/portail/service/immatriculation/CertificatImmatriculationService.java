package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.certificatImmatriculation.*;
import com.secusociale.portail.service.soap.certificatImmatriculation.CmGetCertificatImmatriculation.Input;
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
public class CertificatImmatriculationService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CmGetCertificatImmatriculation> getCertificatImmatriculation(String idDossier) throws JAXBException {

        Input input = new Input();
        input.setIdDossier(idDossier);
        System.out.println("debug l 26");
        Holder<CmGetCertificatImmatriculation> certificatImmatrication = new Holder<CmGetCertificatImmatriculation>();
        ObjectFactory obj = new ObjectFactory();
        certificatImmatrication.value = obj.createCmGetCertificatImmatriculation();
        certificatImmatrication.value.setInput(input);
        final JAXBContext jc = JAXBContext.newInstance(CmGetCertificatImmatriculation.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(certificatImmatrication.value, System.out);
        try {
            CmGetCertificatImmatriculationService cmGetCertificatImmatriculationService = new CmGetCertificatImmatriculationService();
            CmGetCertificatImmatriculationPortType cmGetCertificatImmatriculationPortType = cmGetCertificatImmatriculationService.getCmGetCertificatImmatriculationPort();
            BindingProvider prov = (BindingProvider) cmGetCertificatImmatriculationPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            cmGetCertificatImmatriculationPortType.cmGetCertificatImmatriculation(certificatImmatrication);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), PortailConstant.CERTIFICAT_IMMAT_WSDL);
                throw new RuntimeException(e);
            } else if (e instanceof CmGetCertificatImmatriculationFault) {
                throw new RuntimeException(((CmGetCertificatImmatriculationFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return certificatImmatrication;
    }
}
