package com.secusociale.portail.service.immatriculation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.domain.ImmatriculationRecuperee;
import com.secusociale.portail.domain.NouvelleImmatriculation;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.repository.ServerCheckRepository;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.mapper.ImmatriculationRecupereeMapper;
import com.secusociale.portail.service.mapper.NouvelleImmatriculationMapper;
import com.secusociale.portail.service.soap.demandeImmatriculation.*;
import com.secusociale.portail.service.soap.demandeImmatriculation.IMMATRICULATIONINBOUND.Input;
import com.secusociale.portail.service.soap.domestique.InboundDomFrm;
import com.secusociale.portail.service.soap.domestique.InboundDomFrmFault;
import com.secusociale.portail.service.soap.domestique.InboundDomFrmPortType;
import com.secusociale.portail.service.soap.domestique.InboundDomFrmService;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILS;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILSFault;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILSPortType;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILSService;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUND;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUNDFault;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUNDPortType;
import com.secusociale.portail.service.soap.immatPublicParapublic.IMMAT2INBOUNDService;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLO;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLOFault;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLOPortType;
import com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.IMMATREPDIPLOService;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAI;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAIFault;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAIPortType;
import com.secusociale.portail.service.soap.independant.CMCrtIndForXAIService;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUND;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUNDFault;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUNDPortType;
import com.secusociale.portail.service.soap.maintientAffiliation.MAINTAFFINBOUNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ImmatPortailService {

    private final DocumentUrlService documentUrlService;
    private final MailService mailService;
    private final ServerCheckRepository serverCheckRepository;
    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;
    private final ImmatriculationRecupereeMapper immatriculationRecupereeMapper;
    private final NouvelleImmatriculationMapper nouvelleImmatriculationMapper;

    public List<Object> searchEmployeurByUniqueNumber(String numeroUnique) {
        List<NouvelleImmatriculation> nouvelleImmatriculations = nouvelleImmatriculationRepository.findAllByNumeroUnique(numeroUnique);
        List<ImmatriculationRecuperee> immatriculationRecuperees = immatriculationRecupereeRepository.findAllByNumeroUnique(numeroUnique);
        List<Object> objects = new ArrayList<>();
        objects.addAll(nouvelleImmatriculations);
        objects.addAll(immatriculationRecuperees);
        return objects;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Holder<IMMATRICULATIONINBOUND> createImmatriculationPortail(IMMATRICULATIONINBOUND immatriculation) throws IOException, IllegalAccessException {

        Holder<IMMATRICULATIONINBOUND> immatriculationInbound = new Holder<IMMATRICULATIONINBOUND>();

        Input input = new Input();

        input.getEmployeList().addAll(immatriculation.getInput().getEmployeList());
        input.setEmployerQuery(immatriculation.getInput().getEmployerQuery());
        input.setMainRegistrationForm(immatriculation.getInput().getMainRegistrationForm());
        input.setLegalRepresentativeForm(immatriculation.getInput().getLegalRepresentativeForm());


        // handle documents
        input.setDocuments(immatriculation.getInput().getDocuments());
        if (immatriculation.getInput().getDocuments() != null) {


            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getDemandeEcrit() != null
                && immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl() != null
                && !immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl(), "demande_ecrit");
                immatriculation.getInput().getDocuments().getDemandeEcrit().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getFormDemande() != null
                && immatriculation.getInput().getDocuments().getFormDemande().getUrl() != null
                && !immatriculation.getInput().getDocuments().getFormDemande().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getFormDemande().getUrl(), "form_demande");
                immatriculation.getInput().getDocuments().getFormDemande().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getRegistreCommerce() != null
                && immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl() != null
                && !immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl(), "registre_commerce");
                immatriculation.getInput().getDocuments().getRegistreCommerce().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getDeclarationEtablissement() != null
                && immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl() != null
                && !immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl(), "declaration_etablissement");
                immatriculation.getInput().getDocuments().getDeclarationEtablissement().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getPhotocopieStatus() != null
                && immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl() != null
                && !immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl(), "photocopie_status");
                immatriculation.getInput().getDocuments().getPhotocopieStatus().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getDecretMinisteriel() != null
                && immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl() != null
                && !immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl(), "decret_ministeriel");
                immatriculation.getInput().getDocuments().getDecretMinisteriel().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getAvisImmatriculation() != null
                && immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl() != null
                && !immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl(), "avis_immatriculation");
                immatriculation.getInput().getDocuments().getAvisImmatriculation().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getDmt() != null
                && immatriculation.getInput().getDocuments().getDmt().getUrl() != null
                && !immatriculation.getInput().getDocuments().getDmt().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDmt().getUrl(), "dmt");
                immatriculation.getInput().getDocuments().getDmt().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getContratsTravail() != null
                && immatriculation.getInput().getDocuments().getContratsTravail().getUrl() != null
                && !immatriculation.getInput().getDocuments().getContratsTravail().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getContratsTravail().getUrl(), "contrats_travail");
                immatriculation.getInput().getDocuments().getContratsTravail().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getCni() != null
                && immatriculation.getInput().getDocuments().getCni().getUrl() != null
                && !immatriculation.getInput().getDocuments().getCni().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCni().getUrl(), "cni");
                immatriculation.getInput().getDocuments().getCni().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire() != null
                && immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl() != null
                && !immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl(), "carte_identite_consulaire");
                immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getEtatRecensement() != null
                && immatriculation.getInput().getDocuments().getEtatRecensement().getUrl() != null
                && !immatriculation.getInput().getDocuments().getEtatRecensement().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getEtatRecensement().getUrl(), "etat_recensement");
                immatriculation.getInput().getDocuments().getEtatRecensement().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getAttestationChomage() != null
                && immatriculation.getInput().getDocuments().getAttestationChomage().getUrl() != null
                && !immatriculation.getInput().getDocuments().getAttestationChomage().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAttestationChomage().getUrl(), "attestation_chomage");
                immatriculation.getInput().getDocuments().getAttestationChomage().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getBulletinsSalaire() != null
                && immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl() != null
                && !immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl(), "bulletins_salaire");
                immatriculation.getInput().getDocuments().getBulletinsSalaire().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getCessationActivity() != null
                && immatriculation.getInput().getDocuments().getCessationActivity().getUrl() != null
                && !immatriculation.getInput().getDocuments().getCessationActivity().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCessationActivity().getUrl(), "cessation_activity");
                immatriculation.getInput().getDocuments().getCessationActivity().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getCarteNationaleIdentite() != null
                && immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl() != null
                && !immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl(), "carte_nationale_identite");
                immatriculation.getInput().getDocuments().getCarteNationaleIdentite().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getDerniersBulletins() != null
                && immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl() != null
                && !immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl(), "derniers_bulletins");
                immatriculation.getInput().getDocuments().getDerniersBulletins().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getManuscriteAdressee() != null
                && immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl() != null
                && !immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl(), "manuscrite_adressee");
                immatriculation.getInput().getDocuments().getManuscriteAdressee().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getPassportDoc() != null
                && immatriculation.getInput().getDocuments().getPassportDoc().getUrl() != null
                && !immatriculation.getInput().getDocuments().getPassportDoc().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPassportDoc().getUrl(), "passport_doc");
                immatriculation.getInput().getDocuments().getPassportDoc().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getPieceIdDoc() != null
                && immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl() != null
                && !immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl(), "piece_id_Doc");
                immatriculation.getInput().getDocuments().getPieceIdDoc().setUrl(url);
            }

            if (immatriculation != null
                && immatriculation.getInput() != null
                && immatriculation.getInput().getDocuments() != null
                && immatriculation.getInput().getDocuments().getPieceIdGerantDoc() != null
                && immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl()  != null
                && !immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl().equals("")) {
                String url = this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl(), "piece_id_gerantDoc");
                immatriculation.getInput().getDocuments().getPieceIdGerantDoc().setUrl(url);
            }

        }
        input.setDocuments(immatriculation.getInput().getDocuments());
        // end handle docs

        ObjectFactory obj = new ObjectFactory();
        immatriculationInbound.value = obj.createIMMATRICULATIONINBOUND();
        immatriculationInbound.value.setInput(input);
        try {
            IMMATRICULATIONINBOUNDService immatriculationinboundService = new IMMATRICULATIONINBOUNDService();
            IMMATRICULATIONINBOUNDPortType immatriculationinboundPortType = immatriculationinboundService.getIMMATRICULATIONINBOUNDPort();

            BindingProvider prov = (BindingProvider) immatriculationinboundPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            immatriculationinboundPortType.immatriculationINBOUND(immatriculationInbound);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage(), PortailConstant.IMMATRICULATION_WSDL);
            } else if (e instanceof IMMATRICULATIONINBOUNDFault) {
                throw new RuntimeException(((IMMATRICULATIONINBOUNDFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return immatriculationInbound;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Holder<MAINTAFFINBOUND> createImmatriculationMaintienAffiliation(MAINTAFFINBOUND immatriculation) throws JAXBException, JAXBException, IOException {
        //String immatriculationType = "BVOLN" ;   //Immatriculation Volontaire
        Holder<MAINTAFFINBOUND> immatriculationAffiliation = new Holder<MAINTAFFINBOUND>();
        MAINTAFFINBOUND.Input input = new MAINTAFFINBOUND.Input();
        // input.getEmployeList().addAll(immatriculation.getInput().getEmployeList());
        input.setInformationsGenerales(immatriculation.getInput().getInformationsGenerales());
        input.setRegistrationFormInfos(immatriculation.getInput().getRegistrationFormInfos());
        input.setInfosSupplementaires(immatriculation.getInput().getInfosSupplementaires());
        input.setDocuments(immatriculation.getInput().getDocuments());

        // handle documents
        input.setDocuments(immatriculation.getInput().getDocuments());
        if (immatriculation.getInput().getDocuments() != null) {

            if (immatriculation.getInput().getDocuments().getDemandeEcrit() != null && !immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDemandeEcrit().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl(), "demande_ecrit"));
            }

            if (immatriculation.getInput().getDocuments().getFormDemande() != null && !immatriculation.getInput().getDocuments().getFormDemande().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getFormDemande().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getFormDemande().getUrl(), "form_demande"));
            }
            if (immatriculation.getInput().getDocuments().getRegistreCommerce() != null && !immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getRegistreCommerce().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl(), "registre_commerce"));
            }
            if (immatriculation.getInput().getDocuments().getDeclarationEtablissement() != null && !immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDeclarationEtablissement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl(), "declaration_etablissement"));
            }
            if (immatriculation.getInput().getDocuments().getPhotocopieStatus() != null && !immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPhotocopieStatus().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl(), "photocopie_status"));
            }
            if (immatriculation.getInput().getDocuments().getDecretMinisteriel() != null && !immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDecretMinisteriel().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl(), "decret_ministeriel"));
            }
            if (immatriculation.getInput().getDocuments().getAvisImmatriculation() != null && !immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAvisImmatriculation().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl(), "avis_immatriculation"));
            }
            if (immatriculation.getInput().getDocuments().getDmt() != null && !immatriculation.getInput().getDocuments().getDmt().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDmt().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDmt().getUrl(), "dmt"));
            }
            if (immatriculation.getInput().getDocuments().getContratsTravail() != null && !immatriculation.getInput().getDocuments().getContratsTravail().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getContratsTravail().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getContratsTravail().getUrl(), "contrats_travail"));
            }
            if (immatriculation.getInput().getDocuments().getCni() != null && !immatriculation.getInput().getDocuments().getCni().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCni().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCni().getUrl(), "cni"));
            }
            if (immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire() != null && !immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl(), "carte_identite_consulaire"));
            }
            if (immatriculation.getInput().getDocuments().getEtatRecensement() != null && !immatriculation.getInput().getDocuments().getEtatRecensement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getEtatRecensement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getEtatRecensement().getUrl(), "etat_recensement"));
            }
            if (immatriculation.getInput().getDocuments().getAttestationChomage() != null && !immatriculation.getInput().getDocuments().getAttestationChomage().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAttestationChomage().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAttestationChomage().getUrl(), "attestation_chomage"));
            }
            if (immatriculation.getInput().getDocuments().getBulletinsSalaire() != null && !immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getBulletinsSalaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl(), "bulletins_salaire"));
            }
            if (immatriculation.getInput().getDocuments().getCessationActivity() != null && !immatriculation.getInput().getDocuments().getCessationActivity().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCessationActivity().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCessationActivity().getUrl(), "cessation_activity"));
            }
            if (immatriculation.getInput().getDocuments().getCarteNationaleIdentite() != null && !immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteNationaleIdentite().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl(), "carte_nationale_identite"));
            }
            if (immatriculation.getInput().getDocuments().getDerniersBulletins() != null && !immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDerniersBulletins().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl(), "derniers_bulletins"));
            }
            if (immatriculation.getInput().getDocuments().getManuscriteAdressee() != null && !immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getManuscriteAdressee().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl(), "manuscrite_adressee"));
            }
            if (immatriculation.getInput().getDocuments().getPassportDoc() != null && !immatriculation.getInput().getDocuments().getPassportDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPassportDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPassportDoc().getUrl(), "passport_doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl(), "piece_id_Doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdGerantDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdGerantDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl(), "piece_id_gerantDoc"));
            }

        }
        input.setDocuments(immatriculation.getInput().getDocuments());
        // end handle docs

        com.secusociale.portail.service.soap.maintientAffiliation.ObjectFactory obj = new com.secusociale.portail.service.soap.maintientAffiliation.ObjectFactory();
        immatriculationAffiliation.value = obj.createMAINTAFFINBOUND();
        immatriculationAffiliation.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(MAINTAFFINBOUND.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(immatriculationAffiliation.value, System.out);

        try {
            MAINTAFFINBOUNDService immatriculationMainAffiliationService = new MAINTAFFINBOUNDService();
            MAINTAFFINBOUNDPortType immatriculationMainAffiliationPortType = immatriculationMainAffiliationService.getMAINTAFFINBOUNDPort();
            BindingProvider prov = (BindingProvider) immatriculationMainAffiliationPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            immatriculationMainAffiliationPortType.maintAFFINBOUND(immatriculationAffiliation);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof MAINTAFFINBOUNDFault) {
                throw new RuntimeException(((MAINTAFFINBOUNDFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return immatriculationAffiliation;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Holder<IMMATREPDIPLO> createImmatriculationRepresentatnt(IMMATREPDIPLO immatriculation) throws JAXBException, IOException {
        // String immatriculationType = "BVOLN" ;   //Immatriculation Volontaire
        Holder<IMMATREPDIPLO> immatriculationRepresentatnt = new Holder<IMMATREPDIPLO>();
        IMMATREPDIPLO.Input input = new IMMATREPDIPLO.Input();
        input.getEmployeList().addAll(immatriculation.getInput().getEmployeList());
        input.setEmployerQuery(immatriculation.getInput().getEmployerQuery());
        input.setMainRegistrationForm(immatriculation.getInput().getMainRegistrationForm());
        input.setPersonneContact(immatriculation.getInput().getPersonneContact());
        input.setDocuments(immatriculation.getInput().getDocuments());

        // handle documents
        input.setDocuments(immatriculation.getInput().getDocuments());
        if (immatriculation.getInput().getDocuments() != null) {

            if (immatriculation.getInput().getDocuments().getDemandeEcrit() != null && !immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDemandeEcrit().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl(), "demande_ecrit"));
            }

            if (immatriculation.getInput().getDocuments().getFormDemande() != null && !immatriculation.getInput().getDocuments().getFormDemande().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getFormDemande().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getFormDemande().getUrl(), "form_demande"));
            }
            if (immatriculation.getInput().getDocuments().getRegistreCommerce() != null && !immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getRegistreCommerce().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl(), "registre_commerce"));
            }
            if (immatriculation.getInput().getDocuments().getDeclarationEtablissement() != null && !immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDeclarationEtablissement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl(), "declaration_etablissement"));
            }
            if (immatriculation.getInput().getDocuments().getPhotocopieStatus() != null && !immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPhotocopieStatus().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl(), "photocopie_status"));
            }
            if (immatriculation.getInput().getDocuments().getDecretMinisteriel() != null && !immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDecretMinisteriel().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl(), "decret_ministeriel"));
            }
            if (immatriculation.getInput().getDocuments().getAvisImmatriculation() != null && !immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAvisImmatriculation().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl(), "avis_immatriculation"));
            }
            if (immatriculation.getInput().getDocuments().getDmt() != null && !immatriculation.getInput().getDocuments().getDmt().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDmt().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDmt().getUrl(), "dmt"));
            }
            if (immatriculation.getInput().getDocuments().getContratsTravail() != null && !immatriculation.getInput().getDocuments().getContratsTravail().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getContratsTravail().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getContratsTravail().getUrl(), "contrats_travail"));
            }
            if (immatriculation.getInput().getDocuments().getCni() != null && !immatriculation.getInput().getDocuments().getCni().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCni().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCni().getUrl(), "cni"));
            }
            if (immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire() != null && !immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl(), "carte_identite_consulaire"));
            }
            if (immatriculation.getInput().getDocuments().getEtatRecensement() != null && !immatriculation.getInput().getDocuments().getEtatRecensement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getEtatRecensement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getEtatRecensement().getUrl(), "etat_recensement"));
            }
            if (immatriculation.getInput().getDocuments().getAttestationChomage() != null && !immatriculation.getInput().getDocuments().getAttestationChomage().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAttestationChomage().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAttestationChomage().getUrl(), "attestation_chomage"));
            }
            if (immatriculation.getInput().getDocuments().getBulletinsSalaire() != null && !immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getBulletinsSalaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl(), "bulletins_salaire"));
            }
            if (immatriculation.getInput().getDocuments().getCessationActivity() != null && !immatriculation.getInput().getDocuments().getCessationActivity().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCessationActivity().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCessationActivity().getUrl(), "cessation_activity"));
            }
            if (immatriculation.getInput().getDocuments().getCarteNationaleIdentite() != null && !immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteNationaleIdentite().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl(), "carte_nationale_identite"));
            }
            if (immatriculation.getInput().getDocuments().getDerniersBulletins() != null && !immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDerniersBulletins().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl(), "derniers_bulletins"));
            }
            if (immatriculation.getInput().getDocuments().getManuscriteAdressee() != null && !immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getManuscriteAdressee().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl(), "manuscrite_adressee"));
            }
            if (immatriculation.getInput().getDocuments().getPassportDoc() != null && !immatriculation.getInput().getDocuments().getPassportDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPassportDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPassportDoc().getUrl(), "passport_doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl(), "piece_id_Doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdGerantDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdGerantDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl(), "piece_id_gerantDoc"));
            }

        }
        input.setDocuments(immatriculation.getInput().getDocuments());
        // end handle docs

        com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.ObjectFactory obj = new com.secusociale.portail.service.soap.immatRepresentantationDiplomatique.ObjectFactory();
        immatriculationRepresentatnt.value = obj.createIMMATREPDIPLO();
        immatriculationRepresentatnt.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(IMMATREPDIPLO.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(immatriculationRepresentatnt.value, System.out);
        try {

            IMMATREPDIPLOService immatriculatioRepresentantService = new IMMATREPDIPLOService();
            IMMATREPDIPLOPortType immatriculationRepresentantPortType = immatriculatioRepresentantService.getIMMATREPDIPLOPort();
            BindingProvider prov = (BindingProvider) immatriculationRepresentantPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            immatriculationRepresentantPortType.immatREPDIPLO(immatriculationRepresentatnt);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof IMMATREPDIPLOFault) {
                throw new RuntimeException(((IMMATREPDIPLOFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return immatriculationRepresentatnt;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Holder<IMMAT2INBOUND> createImmatPublicParapublique(IMMAT2INBOUND immatriculation) throws JAXBException, IOException {

        Holder<IMMAT2INBOUND> immatPublicPara = new Holder<IMMAT2INBOUND>();
        IMMAT2INBOUND.Input input = new IMMAT2INBOUND.Input();
        input.getEmployeList().addAll(immatriculation.getInput().getEmployeList());
        input.setEmployerQuery(immatriculation.getInput().getEmployerQuery());
        input.setMainRegistrationForm(immatriculation.getInput().getMainRegistrationForm());
        input.setPersonneContact(immatriculation.getInput().getPersonneContact());
        input.setDocuments(immatriculation.getInput().getDocuments());

        // handle documents
        input.setDocuments(immatriculation.getInput().getDocuments());
        if (immatriculation.getInput().getDocuments() != null) {

            if (immatriculation.getInput().getDocuments().getDemandeEcrit() != null && !immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDemandeEcrit().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl(), "demande_ecrit"));
            }

            if (immatriculation.getInput().getDocuments().getFormDemande() != null && !immatriculation.getInput().getDocuments().getFormDemande().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getFormDemande().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getFormDemande().getUrl(), "form_demande"));
            }
            if (immatriculation.getInput().getDocuments().getRegistreCommerce() != null && !immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getRegistreCommerce().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl(), "registre_commerce"));
            }
            if (immatriculation.getInput().getDocuments().getDeclarationEtablissement() != null && !immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDeclarationEtablissement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl(), "declaration_etablissement"));
            }
            if (immatriculation.getInput().getDocuments().getPhotocopieStatus() != null && !immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPhotocopieStatus().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl(), "photocopie_status"));
            }
            if (immatriculation.getInput().getDocuments().getDecretMinisteriel() != null && !immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDecretMinisteriel().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl(), "decret_ministeriel"));
            }
            if (immatriculation.getInput().getDocuments().getAvisImmatriculation() != null && !immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAvisImmatriculation().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl(), "avis_immatriculation"));
            }
            if (immatriculation.getInput().getDocuments().getDmt() != null && !immatriculation.getInput().getDocuments().getDmt().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDmt().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDmt().getUrl(), "dmt"));
            }
            if (immatriculation.getInput().getDocuments().getContratsTravail() != null && !immatriculation.getInput().getDocuments().getContratsTravail().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getContratsTravail().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getContratsTravail().getUrl(), "contrats_travail"));
            }
            if (immatriculation.getInput().getDocuments().getCni() != null && !immatriculation.getInput().getDocuments().getCni().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCni().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCni().getUrl(), "cni"));
            }
            if (immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire() != null && !immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl(), "carte_identite_consulaire"));
            }
            if (immatriculation.getInput().getDocuments().getEtatRecensement() != null && !immatriculation.getInput().getDocuments().getEtatRecensement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getEtatRecensement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getEtatRecensement().getUrl(), "etat_recensement"));
            }
            if (immatriculation.getInput().getDocuments().getAttestationChomage() != null && !immatriculation.getInput().getDocuments().getAttestationChomage().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAttestationChomage().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAttestationChomage().getUrl(), "attestation_chomage"));
            }
            if (immatriculation.getInput().getDocuments().getBulletinsSalaire() != null && !immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getBulletinsSalaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl(), "bulletins_salaire"));
            }
            if (immatriculation.getInput().getDocuments().getCessationActivity() != null && !immatriculation.getInput().getDocuments().getCessationActivity().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCessationActivity().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCessationActivity().getUrl(), "cessation_activity"));
            }
            if (immatriculation.getInput().getDocuments().getCarteNationaleIdentite() != null && !immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteNationaleIdentite().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl(), "carte_nationale_identite"));
            }
            if (immatriculation.getInput().getDocuments().getDerniersBulletins() != null && !immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDerniersBulletins().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl(), "derniers_bulletins"));
            }
            if (immatriculation.getInput().getDocuments().getManuscriteAdressee() != null && !immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getManuscriteAdressee().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl(), "manuscrite_adressee"));
            }
            if (immatriculation.getInput().getDocuments().getPassportDoc() != null && !immatriculation.getInput().getDocuments().getPassportDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPassportDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPassportDoc().getUrl(), "passport_doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl(), "piece_id_Doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdGerantDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdGerantDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl(), "piece_id_gerantDoc"));
            }

        }
        input.setDocuments(immatriculation.getInput().getDocuments());
        // end handle docs

        System.out.println(new ObjectMapper().writeValueAsString(input));

        com.secusociale.portail.service.soap.immatPublicParapublic.ObjectFactory obj = new com.secusociale.portail.service.soap.immatPublicParapublic.ObjectFactory();
        immatPublicPara.value = obj.createIMMAT2INBOUND();
        immatPublicPara.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(IMMAT2INBOUND.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(immatPublicPara.value, System.out);
        try {
            IMMAT2INBOUNDService immat2inboundService = new IMMAT2INBOUNDService();
            IMMAT2INBOUNDPortType immat2inboundPortType = immat2inboundService.getIMMAT2INBOUNDPort();

            BindingProvider prov = (BindingProvider) immat2inboundPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            immat2inboundPortType.immat2INBOUND(immatPublicPara);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof IMMAT2INBOUNDFault) {
                throw new RuntimeException(((IMMAT2INBOUNDFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return immatPublicPara;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Holder<InboundDomFrm> createImmatDomestique(InboundDomFrm immatriculation) throws JAXBException, IOException {

        Holder<InboundDomFrm> inboundDomForm = new Holder<InboundDomFrm>();

        InboundDomFrm.Input input = new InboundDomFrm.Input();

        input.getEmployeList().addAll(immatriculation.getInput().getEmployeList());
        input.setRegistrationFormInfos(immatriculation.getInput().getRegistrationFormInfos());
        input.setDomesticRegistrationForm(immatriculation.getInput().getDomesticRegistrationForm());
        input.setDocuments(immatriculation.getInput().getDocuments());

        // handle documents
        input.setDocuments(immatriculation.getInput().getDocuments());
        if (immatriculation.getInput().getDocuments() != null) {

            if (immatriculation.getInput().getDocuments().getDemandeEcrit() != null && !immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDemandeEcrit().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl(), "demande_ecrit"));
            }

            if (immatriculation.getInput().getDocuments().getFormDemande() != null && !immatriculation.getInput().getDocuments().getFormDemande().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getFormDemande().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getFormDemande().getUrl(), "form_demande"));
            }
            if (immatriculation.getInput().getDocuments().getRegistreCommerce() != null && !immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getRegistreCommerce().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl(), "registre_commerce"));
            }
            if (immatriculation.getInput().getDocuments().getDeclarationEtablissement() != null && !immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDeclarationEtablissement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl(), "declaration_etablissement"));
            }
            if (immatriculation.getInput().getDocuments().getPhotocopieStatus() != null && !immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPhotocopieStatus().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl(), "photocopie_status"));
            }
            if (immatriculation.getInput().getDocuments().getDecretMinisteriel() != null && !immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDecretMinisteriel().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl(), "decret_ministeriel"));
            }
            if (immatriculation.getInput().getDocuments().getAvisImmatriculation() != null && !immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAvisImmatriculation().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl(), "avis_immatriculation"));
            }
            if (immatriculation.getInput().getDocuments().getDmt() != null && !immatriculation.getInput().getDocuments().getDmt().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDmt().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDmt().getUrl(), "dmt"));
            }
            if (immatriculation.getInput().getDocuments().getContratsTravail() != null && !immatriculation.getInput().getDocuments().getContratsTravail().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getContratsTravail().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getContratsTravail().getUrl(), "contrats_travail"));
            }
            if (immatriculation.getInput().getDocuments().getCni() != null && !immatriculation.getInput().getDocuments().getCni().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCni().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCni().getUrl(), "cni"));
            }
            if (immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire() != null && !immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl(), "carte_identite_consulaire"));
            }
            if (immatriculation.getInput().getDocuments().getEtatRecensement() != null && !immatriculation.getInput().getDocuments().getEtatRecensement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getEtatRecensement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getEtatRecensement().getUrl(), "etat_recensement"));
            }
            if (immatriculation.getInput().getDocuments().getAttestationChomage() != null && !immatriculation.getInput().getDocuments().getAttestationChomage().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAttestationChomage().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAttestationChomage().getUrl(), "attestation_chomage"));
            }
            if (immatriculation.getInput().getDocuments().getBulletinsSalaire() != null && !immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getBulletinsSalaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl(), "bulletins_salaire"));
            }
            if (immatriculation.getInput().getDocuments().getCessationActivity() != null && !immatriculation.getInput().getDocuments().getCessationActivity().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCessationActivity().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCessationActivity().getUrl(), "cessation_activity"));
            }
            if (immatriculation.getInput().getDocuments().getCarteNationaleIdentite() != null && !immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteNationaleIdentite().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl(), "carte_nationale_identite"));
            }
            if (immatriculation.getInput().getDocuments().getDerniersBulletins() != null && !immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDerniersBulletins().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl(), "derniers_bulletins"));
            }
            if (immatriculation.getInput().getDocuments().getManuscriteAdressee() != null && !immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getManuscriteAdressee().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl(), "manuscrite_adressee"));
            }
            if (immatriculation.getInput().getDocuments().getPassportDoc() != null && !immatriculation.getInput().getDocuments().getPassportDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPassportDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPassportDoc().getUrl(), "passport_doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl(), "piece_id_Doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdGerantDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdGerantDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl(), "piece_id_gerantDoc"));
            }

        }
        input.setDocuments(immatriculation.getInput().getDocuments());
        // end handle docs

        com.secusociale.portail.service.soap.domestique.ObjectFactory obj = new com.secusociale.portail.service.soap.domestique.ObjectFactory();

        inboundDomForm.value = obj.createInboundDomFrm();
        inboundDomForm.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(InboundDomFrm.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(inboundDomForm.value, System.out);
        try {
            InboundDomFrmService domFrmService = new InboundDomFrmService();
            InboundDomFrmPortType domFrmPortType = domFrmService.getInboundDomFrmPort();

            BindingProvider prov = (BindingProvider) domFrmPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            domFrmPortType.inboundDomFrm(inboundDomForm);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof InboundDomFrmFault) {
                throw new RuntimeException(((InboundDomFrmFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return inboundDomForm;


    }

    // Immaatriculation avec upload de file excel pour charger les employés
    public Holder<IMMATRICULATIONINBOUND> createImmatriculationUploadFilePortail(IMMATRICULATIONINBOUND immatriculation) throws IOException {

        //String immatriculationType = "BVOLN" ;   //Immatriculation Volontaire


        Holder<IMMATRICULATIONINBOUND> immatriculationInbound = new Holder<IMMATRICULATIONINBOUND>();

        Input input = new Input();


        input.getEmployeList().addAll(immatriculation.getInput().getEmployeList());

        input.setEmployerQuery(immatriculation.getInput().getEmployerQuery());
        input.setMainRegistrationForm(immatriculation.getInput().getMainRegistrationForm());
        input.setLegalRepresentativeForm(immatriculation.getInput().getLegalRepresentativeForm());
        //input.setDocuments(immatriculation.getInput().getDocuments());


        // Chargement de la liste des employés via le fichier uploader
        // List<Input.EmployeList> employes = this.employeurService.mapReapExcelDataEmployeDB(input.getFileData());
        //  input.setEmployeList(employes);


        ObjectFactory obj = new ObjectFactory();
        immatriculationInbound.value = obj.createIMMATRICULATIONINBOUND();
        immatriculationInbound.value.setInput(input);
        try {
            IMMATRICULATIONINBOUNDService immatriculationinboundService = new IMMATRICULATIONINBOUNDService();
            IMMATRICULATIONINBOUNDPortType immatriculationinboundPortType = immatriculationinboundService.getIMMATRICULATIONINBOUNDPort();

            BindingProvider prov = (BindingProvider) immatriculationinboundPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);

            immatriculationinboundPortType.immatriculationINBOUND(immatriculationInbound);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof IMMATRICULATIONINBOUNDFault) {
                throw new RuntimeException(((IMMATRICULATIONINBOUNDFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return immatriculationInbound;

    }

    public Holder<CMGETEMPLOYEURDETAILS> getEmployeurExistant(CMGETEMPLOYEURDETAILS employeurDetail) throws JAXBException {

        Holder<CMGETEMPLOYEURDETAILS> cmGetEmployeurDetails = new Holder<CMGETEMPLOYEURDETAILS>();

        CMGETEMPLOYEURDETAILS.Input input = new CMGETEMPLOYEURDETAILS.Input();
        input.setTypeIdentifiant(employeurDetail.getInput().getTypeIdentifiant());
        input.setNumeroIdentifiant(employeurDetail.getInput().getNumeroIdentifiant());
        input.setNumeroUnique(employeurDetail.getInput().getNumeroUnique());

        com.secusociale.portail.service.soap.employeurExistant.ObjectFactory obj = new com.secusociale.portail.service.soap.employeurExistant.ObjectFactory();
        cmGetEmployeurDetails.value = obj.createCMGETEMPLOYEURDETAILS();
        cmGetEmployeurDetails.value.setInput(input);

        final JAXBContext jc = JAXBContext.newInstance(CMGETEMPLOYEURDETAILS.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cmGetEmployeurDetails.value, System.out);
        try {
            CMGETEMPLOYEURDETAILSService cmgetemployeurdetailsService = new CMGETEMPLOYEURDETAILSService();
            CMGETEMPLOYEURDETAILSPortType cmgetemployeurdetailsPortType = cmgetemployeurdetailsService.getCMGETEMPLOYEURDETAILSPort();


            BindingProvider prov = (BindingProvider) cmgetemployeurdetailsPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmgetemployeurdetailsPortType.cmGETEMPLOYEURDETAILS(cmGetEmployeurDetails);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof CMGETEMPLOYEURDETAILSFault) {
                throw new RuntimeException(((CMGETEMPLOYEURDETAILSFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return cmGetEmployeurDetails;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Holder<CMCrtIndForXAI> createImmatIndependant(CMCrtIndForXAI immatriculation) throws JAXBException, IOException {

        Holder<CMCrtIndForXAI> immat = new Holder<CMCrtIndForXAI>();
        CMCrtIndForXAI.Input input = immatriculation.getInput();

        input.setDocuments(immatriculation.getInput().getDocuments());
        if (immatriculation.getInput().getDocuments() != null) {

            if (immatriculation.getInput().getDocuments().getDemandeEcrit() != null && !immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDemandeEcrit().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDemandeEcrit().getUrl(), "demande_ecrit"));
            }

            if (immatriculation.getInput().getDocuments().getFormDemande() != null && !immatriculation.getInput().getDocuments().getFormDemande().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getFormDemande().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getFormDemande().getUrl(), "form_demande"));
            }
            if (immatriculation.getInput().getDocuments().getRegistreCommerce() != null && !immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getRegistreCommerce().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getRegistreCommerce().getUrl(), "registre_commerce"));
            }
            if (immatriculation.getInput().getDocuments().getDeclarationEtablissement() != null && !immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDeclarationEtablissement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDeclarationEtablissement().getUrl(), "declaration_etablissement"));
            }
            if (immatriculation.getInput().getDocuments().getPhotocopieStatus() != null && !immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPhotocopieStatus().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPhotocopieStatus().getUrl(), "photocopie_status"));
            }
            if (immatriculation.getInput().getDocuments().getDecretMinisteriel() != null && !immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDecretMinisteriel().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDecretMinisteriel().getUrl(), "decret_ministeriel"));
            }
            if (immatriculation.getInput().getDocuments().getAvisImmatriculation() != null && !immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAvisImmatriculation().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAvisImmatriculation().getUrl(), "avis_immatriculation"));
            }
            if (immatriculation.getInput().getDocuments().getDmt() != null && !immatriculation.getInput().getDocuments().getDmt().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDmt().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDmt().getUrl(), "dmt"));
            }
            if (immatriculation.getInput().getDocuments().getContratsTravail() != null && !immatriculation.getInput().getDocuments().getContratsTravail().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getContratsTravail().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getContratsTravail().getUrl(), "contrats_travail"));
            }
            if (immatriculation.getInput().getDocuments().getCni() != null && !immatriculation.getInput().getDocuments().getCni().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCni().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCni().getUrl(), "cni"));
            }
            if (immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire() != null && !immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteIdentiteConsulaire().getUrl(), "carte_identite_consulaire"));
            }
            if (immatriculation.getInput().getDocuments().getEtatRecensement() != null && !immatriculation.getInput().getDocuments().getEtatRecensement().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getEtatRecensement().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getEtatRecensement().getUrl(), "etat_recensement"));
            }
            if (immatriculation.getInput().getDocuments().getAttestationChomage() != null && !immatriculation.getInput().getDocuments().getAttestationChomage().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getAttestationChomage().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getAttestationChomage().getUrl(), "attestation_chomage"));
            }
            if (immatriculation.getInput().getDocuments().getBulletinsSalaire() != null && !immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getBulletinsSalaire().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getBulletinsSalaire().getUrl(), "bulletins_salaire"));
            }
            if (immatriculation.getInput().getDocuments().getCessationActivity() != null && !immatriculation.getInput().getDocuments().getCessationActivity().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCessationActivity().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCessationActivity().getUrl(), "cessation_activity"));
            }
            if (immatriculation.getInput().getDocuments().getCarteNationaleIdentite() != null && !immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getCarteNationaleIdentite().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getCarteNationaleIdentite().getUrl(), "carte_nationale_identite"));
            }
            if (immatriculation.getInput().getDocuments().getDerniersBulletins() != null && !immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getDerniersBulletins().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getDerniersBulletins().getUrl(), "derniers_bulletins"));
            }
            if (immatriculation.getInput().getDocuments().getManuscriteAdressee() != null && !immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getManuscriteAdressee().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getManuscriteAdressee().getUrl(), "manuscrite_adressee"));
            }
            if (immatriculation.getInput().getDocuments().getPassportDoc() != null && !immatriculation.getInput().getDocuments().getPassportDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPassportDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPassportDoc().getUrl(), "passport_doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdDoc().getUrl(), "piece_id_Doc"));
            }
            if (immatriculation.getInput().getDocuments().getPieceIdGerantDoc() != null && !immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl().equals("")) {
                immatriculation.getInput().getDocuments().getPieceIdGerantDoc().setUrl(this.documentUrlService.transformBase64ToURL(immatriculation.getInput().getDocuments().getPieceIdGerantDoc().getUrl(), "piece_id_gerantDoc"));
            }

        }
        input.setDocuments(immatriculation.getInput().getDocuments());

        com.secusociale.portail.service.soap.independant.ObjectFactory obj = new com.secusociale.portail.service.soap.independant.ObjectFactory();
        immat.value = obj.createCMCrtIndForXAI();
        immat.value.setInput(immatriculation.getInput());

        final JAXBContext jc = JAXBContext.newInstance(CMCrtIndForXAI.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(immat.value, System.out);
        try {
            CMCrtIndForXAIService cmCrtIndForXAIService = new CMCrtIndForXAIService();
            CMCrtIndForXAIPortType cmCrtIndForXAIPortType = cmCrtIndForXAIService.getCMCrtIndForXAIPort();

            BindingProvider prov = (BindingProvider) cmCrtIndForXAIPortType;
            prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PortailConstant.USERNAME);
            prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PortailConstant.PASSWORD);


            cmCrtIndForXAIPortType.cmCrtIndForXAI(immat);
            ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
            if (serverCheck == null || !serverCheck.getEtat().equalsIgnoreCase("UP")) {
                ServerCheck sc = new ServerCheck();
                sc.code("PCRM").etat("UP").date(Instant.now());
                serverCheckRepository.save(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WebServiceException) {
                mailService.sendEmailNotif("mail/indisponibiliteEmail", "prcmdown", "Indisponibilité du serveur (PSRM)", 500, e.getMessage());
            } else if (e instanceof CMCrtIndForXAIFault) {
                throw new RuntimeException(((CMCrtIndForXAIFault) e).getFaultInfo().getServerMessage().getText(), e);
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return immat;

    }


}
