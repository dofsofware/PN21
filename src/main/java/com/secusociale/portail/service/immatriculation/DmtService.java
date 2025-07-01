package com.secusociale.portail.service.immatriculation;

import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.model.DmtModel;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.soap.cmdmt.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class DmtService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ServerCheckRepository serverCheckRepository;

    public CmDmt dmt(DmtModel dmtModel) {
        Holder<CmDmt> cmDmt = new Holder<CmDmt>();
        ObjectFactory obj = new ObjectFactory();
        try {
            List<CmDmt.Employes> listSalarier = new ArrayList<>();
            for (DmtModel.Employes employe : dmtModel.getEmployes()) {
                CmDmt.Employes employeModel = new CmDmt.Employes();
                //complexe attributes
                employeModel.setDateNaissance(obj.createCmDmtEmployesDateNaissance(formatToGregorianCalendar(employe.getDateNaissance())));
                employeModel.setDateDebutMouvement(obj.createCmDmtEmployesDateDebutMouvement(formatToGregorianCalendar(employe.getDateDebutMouvement())));
                employeModel.setDateFinMouvement(obj.createCmDmtEmployesDateFinMouvement(formatToGregorianCalendar(employe.getDateFinMouvement())));
                employeModel.setEstCadre(obj.createCmDmtEmployesEstCadre(employe.getEstCadre()));
                employeModel.setSalaireBrute(obj.createCmDmtEmployesSalaireBrute(employe.getSalaireBrute()));
                //simple attributes
                employeModel.setNom(employe.getNom());
                employeModel.setPrenom(employe.getPrenom());
                employeModel.setSexe(employe.getSexe());
                employeModel.setEtatCivil(employe.getEtatCivil());
                employeModel.setNationalite(employe.getNationalite());
                employeModel.setTypePiece(employe.getTypePiece());
                employeModel.setNumeroPiece(employe.getNumeroPiece());
                employeModel.setTypeDeMouvement(employe.getTypeDeMouvement());
                employeModel.setMotifMouvement(employe.getMotifMouvement());
                employeModel.setTypeContrat(employe.getTypeContrat());
                employeModel.setProfession(employe.getProfession());
                employeModel.setEmploi(employe.getEmploi());
                employeModel.setConventionApplicable(employe.getConventionApplicable());
                employeModel.setTempsTravail(employe.getTempsTravail());
                employeModel.setCategorie(employe.getCategorie());
                //add to list
                listSalarier.add(employeModel);
            }

            cmDmt.value = obj.createCmDmt();
            cmDmt.value.setIdEmployeur(dmtModel.getIdEmployeur());
            cmDmt.value.setEmployes(listSalarier);

            final JAXBContext jc = JAXBContext.newInstance(CmDmt.class);
            final Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(cmDmt.value, System.out);
            CmDmtService cmDtmService = new CmDmtService();
            CmDmtPortType cmDtmPortType = cmDtmService.getCmDmtPort();
            BindingProvider prov = (BindingProvider) cmDtmPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            //lancer la requette
            cmDtmPortType.cmDmt(cmDmt);

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
            } else if (e instanceof CmDmtFault) {
                throw new RuntimeException(((CmDmtFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return cmDmt.value;

    }


    public XMLGregorianCalendar formatToGregorianCalendar(String dateFormat) throws Exception {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        if (!StringUtils.isEmpty(dateFormat)) {
            Date format = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat);
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(format);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gCalendar.get(Calendar.YEAR), gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        }
        return xmlGregorianCalendar;
    }

    public String formaToString(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        return formatDate.format(date);
    }


}
