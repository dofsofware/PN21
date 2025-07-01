package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.statutDossierImmatriculation.*;
import com.secusociale.portail.service.soap.statutDossierImmatriculation.CmGetStatusDossierImmatriculation.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

@Service
public class StatutDossierImmatriculationService {

    @Autowired
    private MailService mailService;

    public Holder<CmGetStatusDossierImmatriculation> getStatutDossierImmatriculation(String idDossier) throws JAXBException {

        Holder<CmGetStatusDossierImmatriculation> statutDossierImmatriculation = new Holder<CmGetStatusDossierImmatriculation>();

        Input input = new Input();

        input.setIdDossierImmatriculation(idDossier);

        ObjectFactory obj = new ObjectFactory();

        statutDossierImmatriculation.value = obj.createCmGetStatusDossierImmatriculation();
        statutDossierImmatriculation.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(CmGetStatusDossierImmatriculation.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(statutDossierImmatriculation.value, System.out);

        try {
            CmGetStatusDossierImmatriculationService cmGetStatusDossierImmatriculationService = new CmGetStatusDossierImmatriculationService();
            CmGetStatusDossierImmatriculationPortType cmGetStatusDossierImmatriculationPortType = cmGetStatusDossierImmatriculationService.getCmGetStatusDossierImmatriculationPort();

            BindingProvider prov = (BindingProvider) cmGetStatusDossierImmatriculationPortType;

            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmGetStatusDossierImmatriculationPortType.cmGetStatusDossierImmatriculation(statutDossierImmatriculation);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), PortailConstant.STATUT_DOSSIER_IMMAT_WSDL);
            } else if (e instanceof CmGetStatusDossierImmatriculationFault) {
                throw new RuntimeException(((CmGetStatusDossierImmatriculationFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return statutDossierImmatriculation;

    }

}
