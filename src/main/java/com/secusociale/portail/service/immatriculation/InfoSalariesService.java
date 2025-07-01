package com.secusociale.portail.service.immatriculation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.soap.numeroCompteEmployeur.CMPerAccountByIdFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.certificatImmatriculation.CmGetCertificatImmatriculation;
import com.secusociale.portail.service.soap.infosSalaries.CMGETPERSONSLINKTOEMPLOYER;
import com.secusociale.portail.service.soap.infosSalaries.CMGETPERSONSLINKTOEMPLOYERFault;
import com.secusociale.portail.service.soap.infosSalaries.CMGETPERSONSLINKTOEMPLOYERPortType;
import com.secusociale.portail.service.soap.infosSalaries.CMGETPERSONSLINKTOEMPLOYERService;
import com.secusociale.portail.service.soap.infosSalaries.ObjectFactory;

import java.time.Instant;

@Service
public class InfoSalariesService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
	public Holder<CMGETPERSONSLINKTOEMPLOYER> getInfosalarie (String numeroEmployeur) throws JAXBException{

		Holder<CMGETPERSONSLINKTOEMPLOYER> infoSalaries = new Holder<CMGETPERSONSLINKTOEMPLOYER>();
		ObjectFactory obj = new ObjectFactory() ;

		infoSalaries.value = obj.createCMGETPERSONSLINKTOEMPLOYER();
		infoSalaries.value.setNumeroEmployeur(numeroEmployeur);


		/*
		 * final JAXBContext jc =
		 * JAXBContext.newInstance(CmGetCertificatImmatriculation.class); final
		 * Marshaller marshaller = jc.createMarshaller();
		 * marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 * marshaller.marshal(infoSalaries.value, System.out);
		 */
        try {
		CMGETPERSONSLINKTOEMPLOYERService cmgetpersonslinktoemployerService = new CMGETPERSONSLINKTOEMPLOYERService() ;
		CMGETPERSONSLINKTOEMPLOYERPortType cmgetpersonslinktoemployerPortType = cmgetpersonslinktoemployerService.getCMGETPERSONSLINKTOEMPLOYERPort();

        BindingProvider prov = (BindingProvider) cmgetpersonslinktoemployerPortType ;

	    prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


			cmgetpersonslinktoemployerPortType.cmGETPERSONSLINKTOEMPLOYER(infoSalaries);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage());
                throw new RuntimeException(e);
            } else if (e instanceof CMGETPERSONSLINKTOEMPLOYERFault) {
                throw new RuntimeException(((CMGETPERSONSLINKTOEMPLOYERFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

		return infoSalaries;

	}

}
