package com.secusociale.portail.service.declaration;

import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.declaration.existance.cm_has_dns.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

@Service
public class DeclarationExistanceService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    public Holder<CMHASDNS> hasDNS(CMHASDNS requestOBJ) throws JAXBException {

        Holder<CMHASDNS> cmhasdnsHolder = new Holder<CMHASDNS>();

        ObjectFactory obj = new ObjectFactory();
        CMHASDNS cmhasdns = obj.createCMHASDNS();
        cmhasdns.setPersonId(requestOBJ.getPersonId());
        cmhasdns.setDateDebut(requestOBJ.getDateDebut());
        cmhasdns.setZone(requestOBJ.getZone());

        cmhasdnsHolder.value = cmhasdns;

        final JAXBContext jc = JAXBContext.newInstance(CMHASDNS.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmhasdnsHolder.value, System.out);

        CMHASDNSService cmhasdnsService = new CMHASDNSService();
        CMHASDNSPortType cmhasdnsPort = cmhasdnsService.getCMHASDNSPort();

        BindingProvider prov = (BindingProvider) cmhasdnsPort;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            cmhasdnsPort.cmHASDNS(cmhasdnsHolder);
        } catch (CMHASDNSFault e) {
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return cmhasdnsHolder;

    }
}
