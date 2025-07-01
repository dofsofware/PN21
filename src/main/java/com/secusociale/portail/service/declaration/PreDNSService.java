package com.secusociale.portail.service.declaration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
import javax.xml.ws.WebServiceException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secusociale.portail.model.DeclarationModel;
import com.secusociale.portail.model.DeclarationModel.EmployeModel;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.preDNS.CmPresDns;
import com.secusociale.portail.service.soap.preDNS.CmPresDns.InformationSalaries;
import com.secusociale.portail.service.soap.preDNS.CmPresDnsFault;
import com.secusociale.portail.service.soap.preDNS.CmPresDnsPortType;
import com.secusociale.portail.service.soap.preDNS.CmPresDnsService;
import com.secusociale.portail.service.soap.preDNS.ObjectFactory;

@Service
public class PreDNSService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public Holder<DeclarationModel> getPreDns(DeclarationModel preDnsInput) throws JAXBException, DatatypeConfigurationException {

        Holder<CmPresDns> cmPreDns = new Holder<CmPresDns>();

        Holder<DeclarationModel> preDNS = new Holder<DeclarationModel>();

        List<InformationSalaries> listSalaries = new ArrayList<CmPresDns.InformationSalaries>();

        DeclarationModel model = new DeclarationModel();

        // Input

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        GregorianCalendar gDateDebutCalendar = new GregorianCalendar();
        gDateDebutCalendar.setTime(preDnsInput.getDateDebutPeriodeCotisation());
        XMLGregorianCalendar dateDebutcotisation = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gDateDebutCalendar.get(Calendar.YEAR), gDateDebutCalendar.get(Calendar.MONTH) + 1, gDateDebutCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

        GregorianCalendar gDateFinCalendar = new GregorianCalendar();
        gDateFinCalendar.setTime(preDnsInput.getDateFinPeriodeCotisation());
        XMLGregorianCalendar dateFinCotisation = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gDateFinCalendar.get(Calendar.YEAR), gDateFinCalendar.get(Calendar.MONTH) + 1, gDateFinCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

        ObjectFactory obj = new ObjectFactory();

        JAXBElement<XMLGregorianCalendar> dateDebutCoti = obj.createCmPresDnsDateDebutPeriodeCotisation(dateDebutcotisation);
        JAXBElement<XMLGregorianCalendar> dateFinCoti = obj.createCmPresDnsDateFinPeriodeCotisation(dateFinCotisation);


        cmPreDns.value = obj.createCmPresDns();

        cmPreDns.value.setNumeroEmployeur(preDnsInput.getNumeroEmployeur());
        cmPreDns.value.setDateDebutPeriodeCotisation(dateDebutCoti);
        cmPreDns.value.setDateFinPeriodeCotisation(dateFinCoti);
        cmPreDns.value.setAddress(preDnsInput.getAdresse());
        cmPreDns.value.setRaisonSociale(preDnsInput.getRaisonSociale());
        cmPreDns.value.setTypeDeclaration(preDnsInput.getTypeDeclaration());

        final JAXBContext jc = JAXBContext.newInstance(CmPresDns.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmPreDns.value, System.out);
        try {
            CmPresDnsService cmPresDnsService = new CmPresDnsService();
            CmPresDnsPortType cmPresDnsPortType = cmPresDnsService.getCmPresDnsPort();

            BindingProvider prov = (BindingProvider) cmPresDnsPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            System.out.println((new ObjectMapper()).writeValueAsString(cmPreDns));
            cmPresDnsPortType.cmPresDns(cmPreDns);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck==null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500,e.getMessage(), Constants.PSRM_URL+"/ouaf/XAIApp/xaiserver/CmPresDns?WSDL");
                throw new RuntimeException(e);
            } else if (e instanceof CmPresDnsFault) {
                throw new RuntimeException(((CmPresDnsFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        // Output (Insertion des valeur dans le model)
        preDnsInput.setTotalSalaries(cmPreDns.value.getTotalSalaries());
        preDnsInput.setCumulTotSalAssCssAtmp(cmPreDns.value.getTotalSalaireAssujetisAtmp().getValue());
        preDnsInput.setCumulTotSalAssCssPf(cmPreDns.value.getTotalSalaireAssujetisPf().getValue());
        preDnsInput.setCumulTotSalAssIpresRcc(cmPreDns.value.getTotalSalaireAssujetisRcc().getValue());
        preDnsInput.setCumulTotSalAssIpresRg(cmPreDns.value.getTotalSalaireAssujetisRg().getValue());
        preDnsInput.setTotalSalVerses(cmPreDns.value.getTotalSalaireVerses().getValue());
        preDnsInput.setMntCotAtMpCalcParEmployeur(cmPreDns.value.getMontantCotisationAtmpEmp().getValue());
        preDnsInput.setMntCotPfCalcParEmployeur(cmPreDns.value.getMontantCotisationPfEmp().getValue());
        preDnsInput.setMntCotRccCalcParEmployeur(cmPreDns.value.getMontantCotisationRccEmp().getValue());
        preDnsInput.setMntCotRgCalcParEmployeur(cmPreDns.value.getMontantCotisationRgEmpl().getValue());
        listSalaries = cmPreDns.value.getInformationSalaries();

        List<EmployeModel> listSalarier = new ArrayList<>();
        for (InformationSalaries listSalary : listSalaries) {
            EmployeModel employeModel = new EmployeModel();
            JAXBElement<XMLGregorianCalendar> rcc1 = listSalary.getDateEffetRegimeCadreMois1();
            JAXBElement<XMLGregorianCalendar> rcc2 = listSalary.getDateEffetRegimeCadreMois2();
            JAXBElement<XMLGregorianCalendar> rcc3 = listSalary.getDateEffetRegimeCadreMois3();
            JAXBElement<XMLGregorianCalendar> dateEntree = listSalary.getDateEntree();
            JAXBElement<XMLGregorianCalendar> dateSortie = listSalary.getDateSortie();
            JAXBElement<XMLGregorianCalendar> dateNaissance = listSalary.getDateNaissance();

            employeModel.setNumeroAssureSocial(listSalary.getNumeroAssureSocial());
            employeModel.setNomEmploye(listSalary.getNom());
            employeModel.setPrenomEmploye(listSalary.getPrenom());

            if (dateNaissance != null) {
                employeModel.setDateNaissance(model.formaToString(dateNaissance.getValue().toGregorianCalendar().getTime()));
            }

            employeModel.setTypePieceIdentite(listSalary.getTypePiece());
            employeModel.setNumPieceIdentite(listSalary.getNumeroPiece());
            employeModel.setNatureContrat(listSalary.getNatureContrat());

            if (dateEntree != null) {
                employeModel.setDateEntree(model.formaToString(dateEntree.getValue().toGregorianCalendar().getTime()));
            }

            if (dateSortie != null) {
                employeModel.setDateSortie(model.formaToString(dateSortie.getValue().toGregorianCalendar().getTime()));
            }
            employeModel.setMotifSortie(listSalary.getMotif());

            employeModel.setTotSalAssCssAtmp1(listSalary.getTotalSalaireAssujetisAtmpMois1().getValue());
            employeModel.setTotSalAssCssPf1(listSalary.getTotalSalaireAssujetisPfMois1().getValue());
            employeModel.setTotSalAssIpresRcc1(listSalary.getTotalSalaireAssujetisRccMois1().getValue());
            employeModel.setTotSalAssIpresRg1(listSalary.getTotalSalaireAssujetisRgMois1().getValue());
            employeModel.setSalaireBrut1(listSalary.getSalaireBrutMois1().getValue());
            employeModel.setNombreJours1(listSalary.getTempsPresenceJourMois1());
            employeModel.setNombreHeures1(listSalary.getTempsPresenceHeureMois1());
            employeModel.setTempsTravail1(listSalary.getTempsTravailMois1());
            employeModel.setTrancheTravail1(listSalary.getTranceDeTravailMois1());
            employeModel.setRegimeGeneral1(listSalary.getRegimeGeneralMois1().getValue());
            employeModel.setRegimCompCadre1(listSalary.getRegimeCadreMois1().getValue());

            if (rcc1 != null) {
                employeModel.setDateEffetRegimeCadre1(model.formaToString(rcc1.getValue().toGregorianCalendar().getTime()));
            }

            employeModel.setTotSalAssCssAtmp2(listSalary.getTotalSalaireAssujetisAtmpMois2().getValue());
            employeModel.setTotSalAssCssPf2(listSalary.getTotalSalaireAssujetisPfMois2());
            employeModel.setTotSalAssIpresRcc2(listSalary.getTotalSalaireAssujetisRccMois2().getValue());
            employeModel.setTotSalAssIpresRg2(listSalary.getTotalSalaireAssujetisRgMois2().getValue());
            employeModel.setSalaireBrut2(listSalary.getSalaireBrutMois2().getValue());
            employeModel.setNombreJours2(listSalary.getTempsPresenceJourMois2().getValue());
            employeModel.setNombreHeures2(listSalary.getTempsPresenceHeureMois2().getValue());
            employeModel.setTempsTravail2(listSalary.getTempsTravailMois2());
            employeModel.setTrancheTravail2(listSalary.getTranceDeTravailMois2());
            employeModel.setRegimeGeneral2(listSalary.getRegimeGeneralMois2().getValue());
            employeModel.setRegimCompCadre2(listSalary.getRegimeCadreMois2().getValue());
            if (rcc2 != null) {
                employeModel.setDateEffetRegimeCadre2(model.formaToString(rcc2.getValue().toGregorianCalendar().getTime()));
            }

            employeModel.setTotSalAssCssAtmp3(listSalary.getTotalSalaireAssujetisAtmpMois3().getValue());
            employeModel.setTotSalAssCssPf3(listSalary.getTotalSalaireAssujetisPfMois3().getValue());
            employeModel.setTotSalAssIpresRcc3(listSalary.getTotalSalaireAssujetisRccMois3().getValue());
            employeModel.setTotSalAssIpresRg3(listSalary.getTotalSalaireAssujetisRgMois3().getValue());
            employeModel.setSalaireBrut3(listSalary.getSalaireBrutMois3().getValue());
            employeModel.setNombreJours3(listSalary.getTempsPresenceJourMois3().getValue());
            employeModel.setNombreHeures3(listSalary.getTempsPresenceHeureMois3().getValue());
            employeModel.setTempsTravail3(listSalary.getTempsTravailMois3());
            employeModel.setTrancheTravail3(listSalary.getTranceDeTravailMois3());
            employeModel.setRegimeGeneral3(listSalary.getRegimeGeneralMois3().getValue());
            employeModel.setRegimCompCadre3(listSalary.getRegimeCadreMois3().getValue());
            if (rcc3 != null) {
                employeModel.setDateEffetRegimeCadre3(model.formaToString(rcc3.getValue().toGregorianCalendar().getTime()));
            }

            listSalarier.add(employeModel);
        }
        preDnsInput.setInformationSalaries(listSalarier);

        preDNS.value = preDnsInput;
        return preDNS;
    }

}

