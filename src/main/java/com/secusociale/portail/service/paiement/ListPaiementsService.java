package com.secusociale.portail.service.paiement;

import com.secusociale.portail.model.ListPaiementInputModel;
import com.secusociale.portail.model.Utils;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.listPaiements.*;
import com.secusociale.portail.service.soap.listPaiements.CMGETPAYMENT.Input;
import com.secusociale.portail.service.soap.listPaiements.CMGETPAYMENT.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.text.ParseException;

@Service
public class ListPaiementsService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<CMGETPAYMENT> getListPaiements(ListPaiementInputModel model) throws JAXBException, DatatypeConfigurationException, ParseException {
        ObjectFactory obj = new ObjectFactory();
        Holder<CMGETPAYMENT> recuEmployeurHolder = new Holder<>();

        XMLGregorianCalendar from = Utils.formatToGregorianCalendar(Utils.formaToString(model.getDateFrom()));
        JAXBElement<XMLGregorianCalendar> dateFrom = obj.createCMGETPAYMENTInputDateFrom(from);

        XMLGregorianCalendar to = Utils.formatToGregorianCalendar(Utils.formaToString(model.getDateTo()));
        JAXBElement<XMLGregorianCalendar> dateTo = obj.createCMGETPAYMENTInputDateTo(to);

        Input input = new Input();
        Output output = new Output();
        input.setPerId(model.getPersonId());
        input.setDateFrom(dateFrom);
        input.setDateTo(dateTo);

        recuEmployeurHolder.value = obj.createCMGETPAYMENT();
        recuEmployeurHolder.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGETPAYMENT.class);
        final Marshaller marshaller = jc.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(recuEmployeurHolder.value, System.out);


        CMGETPAYMENTService service = new CMGETPAYMENTService();
        CMGETPAYMENTPortType portType = service.getCMGETPAYMENTPort();

        BindingProvider prov = (BindingProvider) portType;

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            portType.cmGETPAYMENT(recuEmployeurHolder);
        } catch (CMGETPAYMENTFault e) {
            System.out.println("faute >> " + e.getFaultInfo().getResponseText());
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        return recuEmployeurHolder;

    }

}
