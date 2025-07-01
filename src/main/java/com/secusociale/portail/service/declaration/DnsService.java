package com.secusociale.portail.service.declaration;

import com.secusociale.portail.model.DeclarationModel;
import com.secusociale.portail.model.DeclarationModel.EmployeModel;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.declaration.*;
import com.secusociale.portail.service.soap.declaration.DNSINBOUNDSERVICE.Input;
import com.secusociale.portail.service.soap.declaration.DNSINBOUNDSERVICE.Input.InformationEmployeur;
import com.secusociale.portail.service.soap.declaration.DNSINBOUNDSERVICE.Input.InformationSalariesList;
import com.secusociale.portail.service.soap.declaration.DNSINBOUNDSERVICE.Input.Synthese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class DnsService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;
    public Holder<DeclarationModel> createDns(DeclarationModel dnsInput) throws DatatypeConfigurationException, ParseException, JAXBException, com.google.protobuf.TextFormat.ParseException {


        Holder<DNSINBOUNDSERVICE> dnsInbound = new Holder<DNSINBOUNDSERVICE>();
        Holder<DeclarationModel> dns = new Holder<DeclarationModel>();

        Input input = new Input();
        InformationEmployeur employeur = new InformationEmployeur();
        Synthese synthese = new Synthese();


        DeclarationModel model = new DeclarationModel();

        //SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");


        GregorianCalendar gDateDebutCalendar = new GregorianCalendar();
        gDateDebutCalendar.setTime(dnsInput.getDateDebutPeriodeCotisation());
        XMLGregorianCalendar dateDebutcotisation = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gDateDebutCalendar.get(Calendar.YEAR), gDateDebutCalendar.get(Calendar.MONTH) + 1, gDateDebutCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

        GregorianCalendar gDateFinCalendar = new GregorianCalendar();
        gDateFinCalendar.setTime(dnsInput.getDateFinPeriodeCotisation());
        XMLGregorianCalendar dateFinCotisation = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gDateFinCalendar.get(Calendar.YEAR), gDateFinCalendar.get(Calendar.MONTH) + 1, gDateFinCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);


        employeur.setAdresse(dnsInput.getAdresse());
        employeur.setNumeroEmployeur(dnsInput.getNumeroEmployeur());
//		employeur.setTypeIdentifiant(dnsInput.getTypeIdentifiant());
//		employeur.setIdIdentifiant(dnsInput.getIdIdentifiant());
        employeur.setRaisonSociale(dnsInput.getRaisonSociale());
        employeur.setTypeDeclaration(dnsInput.getTypeDeclaration());
        employeur.setDateDebutCotisation(dateDebutcotisation);
        employeur.setDateFinCotisation(dateFinCotisation);


        ObjectFactory obj = new ObjectFactory();

        JAXBElement<BigDecimal> cumulTotSalAssCssAtmp = obj.createDNSINBOUNDSERVICEInputSyntheseCumulTotSalAssCssAtmp(dnsInput.getCumulTotSalAssCssAtmp());
        JAXBElement<BigDecimal> cumulTotSalAssCssPf = obj.createDNSINBOUNDSERVICEInputSyntheseCumulTotSalAssCssPf(dnsInput.getCumulTotSalAssCssPf());
        JAXBElement<BigDecimal> mntCotAtMpCalcParEmployeur = obj.createDNSINBOUNDSERVICEInputSyntheseMntCotAtMpCalcParEmployeur(dnsInput.getMntCotAtMpCalcParEmployeur());
        JAXBElement<BigDecimal> mntCotPfCalcParEmployeur = obj.createDNSINBOUNDSERVICEInputSyntheseMntCotPfCalcParEmployeur(dnsInput.getMntCotPfCalcParEmployeur());

        synthese.setCumulTotSalAssCssAtmp(cumulTotSalAssCssAtmp);
        synthese.setCumulTotSalAssCssPf(cumulTotSalAssCssPf);
        synthese.setCumulTotSalAssIpresRcc(dnsInput.getCumulTotSalAssIpresRcc());
        synthese.setCumulTotSalAssIpresRg(dnsInput.getCumulTotSalAssIpresRg());
        synthese.setMntCotAtMpCalcParEmployeur(mntCotAtMpCalcParEmployeur);
        synthese.setMntCotPfCalcParEmployeur(mntCotPfCalcParEmployeur);
        synthese.setMntCotRccCalcParEmployeur(dnsInput.getMntCotRccCalcParEmployeur());
        synthese.setMntCotRgCalcParEmployeur(dnsInput.getMntCotRgCalcParEmployeur());
        synthese.setTotalNouvSalaries(dnsInput.getTotalNouvSalaries());
        synthese.setTotalSalaries(dnsInput.getTotalSalaries());
        synthese.setTotalSalVerses(dnsInput.getTotalSalVerses());

        List<EmployeModel> listemployes = dnsInput.getInformationSalaries();

        for (int i = 0; i < listemployes.size(); i++) {

            InformationSalariesList salarie = new InformationSalariesList();
            // ObjectFactory obj2 = new ObjectFactory();


            XMLGregorianCalendar dateNaissance = model.formatToGregorianCalendar(listemployes.get(i).getDateNaissance());
            XMLGregorianCalendar dateEntree = model.formatToGregorianCalendar(listemployes.get(i).getDateEntree());
            XMLGregorianCalendar dateSortie = model.formatToGregorianCalendar(listemployes.get(i).getDateSortie());
            XMLGregorianCalendar dateEffetCadre1 = model.formatToGregorianCalendar(listemployes.get(i).getDateEffetRegimeCadre1());
            XMLGregorianCalendar dateEffetCadre2 = model.formatToGregorianCalendar(listemployes.get(i).getDateEffetRegimeCadre2());
            XMLGregorianCalendar dateEffetCadre3 = model.formatToGregorianCalendar(listemployes.get(i).getDateEffetRegimeCadre3());


            JAXBElement<XMLGregorianCalendar> dateNaiss = obj.createDNSINBOUNDSERVICEInputInformationSalariesListDateDeNaisssance(dateNaissance);
            JAXBElement<XMLGregorianCalendar> dateEntr = obj.createDNSINBOUNDSERVICEInputInformationSalariesListDateEntree(dateEntree);
            JAXBElement<XMLGregorianCalendar> dateSorti = obj.createDNSINBOUNDSERVICEInputInformationSalariesListDateSortie(dateSortie);
            JAXBElement<XMLGregorianCalendar> dateEffetCadr1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListDateEffetRegimeCadre1(dateEffetCadre1);
            JAXBElement<XMLGregorianCalendar> dateEffetCadr2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListDateEffetRegimeCadre2(dateEffetCadre2);
            JAXBElement<XMLGregorianCalendar> dateEffetCadr3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListDateEffetRegimeCadre3(dateEffetCadre3);

            JAXBElement<Boolean> regimeGenral1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListRegimeGeneral1(listemployes.get(i).getRegimeGeneral1());
            JAXBElement<Boolean> regimeGenral2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListRegimeGeneral2(listemployes.get(i).getRegimeGeneral2());
            JAXBElement<Boolean> regimeGenral3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListRegimeGeneral3(listemployes.get(i).getRegimeGeneral3());

            JAXBElement<Boolean> regimeCadre1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListRegimCompCadre1(listemployes.get(i).getRegimCompCadre1());
            JAXBElement<Boolean> regimeCadre2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListRegimCompCadre2(listemployes.get(i).getRegimCompCadre2());
            JAXBElement<Boolean> regimeCadre3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListRegimCompCadre3(listemployes.get(i).getRegimCompCadre3());

            JAXBElement<BigDecimal> salaireBrut1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListSalaireBrut1(listemployes.get(i).getSalaireBrut1());
            JAXBElement<BigDecimal> salaireBrut2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListSalaireBrut2(listemployes.get(i).getSalaireBrut2());
            JAXBElement<BigDecimal> salaireBrut3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListSalaireBrut3(listemployes.get(i).getSalaireBrut3());


            JAXBElement<BigDecimal> totSalAssCssAtmp1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssCssAtmp1(listemployes.get(i).getTotSalAssCssAtmp1());
            JAXBElement<BigDecimal> totSalAssCssAtmp2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssCssAtmp2(listemployes.get(i).getTotSalAssCssAtmp2());
            JAXBElement<BigDecimal> totSalAssCssAtmp3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssCssAtmp3(listemployes.get(i).getTotSalAssCssAtmp3());


            JAXBElement<BigDecimal> totSalAssIpresRcc1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssIpresRcc1(listemployes.get(i).getTotSalAssIpresRcc1());
            JAXBElement<BigDecimal> totSalAssIpresRcc2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssIpresRcc2(listemployes.get(i).getTotSalAssIpresRcc2());
            JAXBElement<BigDecimal> totSalAssIpresRcc3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssIpresRcc3(listemployes.get(i).getTotSalAssIpresRcc3());

            JAXBElement<BigDecimal> totSalAssIpresRg1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssIpresRg1(listemployes.get(i).getTotSalAssIpresRg1());
            JAXBElement<BigDecimal> totSalAssIpresRg2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssIpresRg2(listemployes.get(i).getTotSalAssIpresRg2());
            JAXBElement<BigDecimal> totSalAssIpresRg3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssIpresRg3(listemployes.get(i).getTotSalAssIpresRg3());


            BigDecimal totSalaireCssPf2 = new BigDecimal(listemployes.get(i).getTotSalAssCssPf2());
            JAXBElement<BigDecimal> totSalAssCssPf1 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssCssPf1(listemployes.get(i).getTotSalAssCssPf1());
            JAXBElement<BigDecimal> totSalAssCssPf2 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssCssPf2(totSalaireCssPf2);
            JAXBElement<BigDecimal> totSalAssCssPf3 = obj.createDNSINBOUNDSERVICEInputInformationSalariesListTotSalAssCssPf3(listemployes.get(i).getTotSalAssCssPf3());

            salarie.setTypeContrat(listemployes.get(i).getNatureContrat());
            salarie.setNumeroAssureSocial(listemployes.get(i).getNumeroAssureSocial());
            salarie.setPrenom(listemployes.get(i).getPrenomEmploye());
            salarie.setNom(listemployes.get(i).getNomEmploye());
            salarie.setDateDeNaisssance(dateNaiss);
            salarie.setTypePieceIdentite(listemployes.get(i).getTypePieceIdentite());
            salarie.setNumeroPieceIdentite(listemployes.get(i).getNumPieceIdentite());
            salarie.setDateEffetRegimeCadre1(dateEffetCadr1);
            salarie.setDateEffetRegimeCadre2(dateEffetCadr2);
            salarie.setDateEffetRegimeCadre3(dateEffetCadr3);
            salarie.setDateEntree(dateEntr);
            salarie.setDateSortie(dateSorti);
            salarie.setMotifSortie(listemployes.get(i).getMotifSortie());


            salarie.setNombreHeures1(listemployes.get(i).getNombreHeures1());
            salarie.setNombreHeures2(listemployes.get(i).getNombreHeures2());
            salarie.setNombreHeures3(listemployes.get(i).getNombreHeures3());
            salarie.setNombreJours1(listemployes.get(i).getNombreJours1());
            salarie.setNombreJours2(listemployes.get(i).getNombreJours2());
            salarie.setNombreJours3(listemployes.get(i).getNombreJours3());
            salarie.setTempsTravail1(listemployes.get(i).getTempsTravail1());
            salarie.setTempsTravail2(listemployes.get(i).getTempsTravail2());
            salarie.setTempsTravail3(listemployes.get(i).getTempsTravail3());
            salarie.setTrancheTravail1(listemployes.get(i).getTrancheTravail1());
            salarie.setTrancheTravail2(listemployes.get(i).getTrancheTravail2());
            salarie.setTrancheTravail3(listemployes.get(i).getTrancheTravail3());
            salarie.setNombreJours1(listemployes.get(i).getNombreJours1());
            salarie.setNombreJours2(listemployes.get(i).getNombreJours2());
            salarie.setNombreJours3(listemployes.get(i).getNombreJours3());
            salarie.setRegimeGeneral1(regimeGenral1);
            salarie.setRegimeGeneral2(regimeGenral2);
            salarie.setRegimeGeneral3(regimeGenral3);

            salarie.setRegimCompCadre1(regimeCadre1);
            salarie.setRegimCompCadre2(regimeCadre2);
            salarie.setRegimCompCadre3(regimeCadre3);

            salarie.setSalaireBrut1(salaireBrut1);
            salarie.setSalaireBrut2(salaireBrut2);
            salarie.setSalaireBrut3(salaireBrut3);


            salarie.setTotSalAssCssPf1(totSalAssCssPf1);
            salarie.setTotSalAssCssPf2(totSalAssCssPf2);
            salarie.setTotSalAssCssPf3(totSalAssCssPf3);
            salarie.setTotSalAssCssAtmp1(totSalAssCssAtmp1);
            salarie.setTotSalAssCssAtmp2(totSalAssCssAtmp2);
            salarie.setTotSalAssCssAtmp3(totSalAssCssAtmp3);

            salarie.setTotSalAssIpresRcc1(totSalAssIpresRcc1);
            salarie.setTotSalAssIpresRcc2(totSalAssIpresRcc2);
            salarie.setTotSalAssIpresRcc3(totSalAssIpresRcc3);

            salarie.setTotSalAssIpresRg1(totSalAssIpresRg1);
            salarie.setTotSalAssIpresRg2(totSalAssIpresRg2);
            salarie.setTotSalAssIpresRg3(totSalAssIpresRg3);

            input.getInformationSalariesList().add(salarie);
        }


        input.setInformationEmployeur(employeur);
        input.setSynthese(synthese);

        dnsInbound.value = obj.createDNSINBOUNDSERVICE();
        dnsInbound.value.setInput(input);


        final JAXBContext jc = JAXBContext.newInstance(DNSINBOUNDSERVICE.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dnsInbound.value, System.out);

        DNSINBOUNDSERVICEService dnsinboundserviceService = new DNSINBOUNDSERVICEService();
        DNSINBOUNDSERVICEPortType dnsinboundservicePortType = dnsinboundserviceService.getDNSINBOUNDSERVICEPort();

        BindingProvider prov = (BindingProvider) dnsinboundservicePortType;
        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

        try {
            dnsinboundservicePortType.dnsINBOUNDSERVICE(dnsInbound);
        } catch (DNSINBOUNDSERVICEFault e) {
            throw new RuntimeException(e.getFaultInfo().getServerMessage().getText(), e);
        }

        dnsInput.setFormId(dnsInbound.value.getOutput().getFormId());

        dns.value = dnsInput;

        return dns;


    }

}
