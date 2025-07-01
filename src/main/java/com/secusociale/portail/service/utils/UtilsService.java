package com.secusociale.portail.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.secusociale.portail.config.ApplicationProperties;
import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Employeur;
import com.secusociale.portail.domain.GrappeMember;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.enumeration.TypeDocument;
import com.secusociale.portail.domain.enumeration.TypeImmatriculation;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.GrappeMemberRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.PieceJointeService;
import com.secusociale.portail.service.dto.NouvelleImmatriculationDTO;
import com.secusociale.portail.service.dto.PieceJointeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtilsService {

    @Autowired
    Environment environment;
    private ApplicationProperties properties;
    @Value("${cssipres.docdir:/opt/tomcat/webapps/documents}")
    private String DOCDIR;
  //  @Value("${cssipres.docuri:http://online.secusociale.sn/documents}")
    @Value("https://preprodonline.secusociale.sn/documents")
    private String DOCURI;

    @Autowired
    private PieceJointeService pieceJointeService;

    @Autowired
    private SalarieRepository salarieRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private GrappeMemberRepository grappeMemberRepository;
    @Autowired
    private UserRepository userRepository;


    public String generateOtp(int length) {
        ObjectMapper om = new ObjectMapper();

        String characters = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        return otp.toString();
    }
    public static void addAllPermissions(Path filePath) throws IOException {
        // Vérifie si le fichier existe
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Le fichier n'existe pas : " + filePath);
        }

        // Crée un ensemble de toutes les permissions possibles (Lecture, Écriture, Exécution pour le propriétaire, le groupe et les autres)
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);    // Lecture par le propriétaire
        perms.add(PosixFilePermission.OWNER_WRITE);   // Écriture par le propriétaire
        perms.add(PosixFilePermission.OWNER_EXECUTE); // Exécution par le propriétaire
        perms.add(PosixFilePermission.GROUP_READ);    // Lecture par le groupe
        perms.add(PosixFilePermission.GROUP_WRITE);   // Écriture par le groupe
        perms.add(PosixFilePermission.GROUP_EXECUTE); // Exécution par le groupe
        perms.add(PosixFilePermission.OTHERS_READ);   // Lecture par les autres
        perms.add(PosixFilePermission.OTHERS_WRITE);  // Écriture par les autres
        perms.add(PosixFilePermission.OTHERS_EXECUTE);// Exécution par les autres

        // Applique les permissions au fichier
        Files.setPosixFilePermissions(filePath, perms);
    }


    public String generateCertificatDeNonInscription(Salarie salarie, Agence agence, String matricule, String fonction,Long idCertificat,String cachet) throws IOException, DocumentException {
        String docdir = DOCDIR;
        String docuri = DOCURI;
        //Salarie salarie = salarieRepository.findById(demandeCertificat.getSalarie().getId()).get();
        //Agence agence = agenceRepository.findById(demandeCertificat.getAgence().getId()).get();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("intitule", salarie.getPrenom()+" "+salarie.getNom());
        context.setVariable("date", salarie.getDateNais().format(formatter));
        context.setVariable("dateGenerate", LocalDate.now().format(formatter));
        context.setVariable("nin", salarie.getNumeroCni());
        context.setVariable("agence", agence.getNom());
        context.setVariable("numeroCertificat", idCertificat);
        context.setVariable("matricule", matricule);
        context.setVariable("fonction", fonction);
        context.setVariable("codeAgence", getCodeAgence(agence.getNom()));
        context.setVariable("titre", getTitleByCni(salarie.getNumeroCni()));
        context.setVariable("cachet", cachet);
        String html = templateEngine.process("templates/certificat/certificat_de_non_inscription", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        String fileName = "certificat_de_non_inscription_" + UUID.randomUUID() + ".pdf";
        String outputFolder = docdir + "/"+fileName;
        System.out.println(outputFolder);
        OutputStream outputStream = new FileOutputStream(outputFolder);
        renderer.createPDF(outputStream);
        PieceJointeDTO dto = new PieceJointeDTO();
        dto.setName(fileName);
        dto.setExtension(".pdf");
        System.out.println(docuri);
        dto.setFile(String.format("%s/%s", docuri, StringUtils.normalizeSpace(dto.getName())));
        dto.setEntityType("Salarie");
        dto.setEntityId(salarie.getId());
        dto.setDescription("Certificat de non inscription");
        pieceJointeService.save(dto);
        outputStream.close();
        Path filePath = Paths.get(outputFolder);
        addAllPermissions(filePath);
        return dto.getFile();
    }

    public String generateCertificatDeRadiation( Salarie salarie, Agence agence, String cachet) throws IOException, DocumentException {
        String docdir =  DOCDIR;
        String docuri =  DOCURI;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Salarie salarie = salarieRepository.findById(demandeCertificat.getSalarie().getId()).get();
        //Agence agence = agenceRepository.findById(demandeCertificat.getAgence().getId()).get();
        List<GrappeMember> enfants = grappeMemberRepository.findAllByTypeAndSalarie("ENFANT", salarie);
        List<Map<String,Object>> enfantData = enfants.stream().map(enfant -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nom", enfant.getFirstName()+" "+enfant.getLastName());
            map.put("dateNais", enfant.getDateNais().format(formatter));
            return map;
        }).collect(Collectors.toList());
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("intitule", salarie.getPrenom()+" "+salarie.getNom());
        context.setVariable("date", salarie.getDateNais().format(formatter));
        context.setVariable("dateGenerate", LocalDate.now().format(formatter));
        context.setVariable("nin", salarie.getNumeroCni());
        context.setVariable("agence", agence.getNom());
        context.setVariable("enfants", enfantData);
        context.setVariable("codeAgence", getCodeAgence(agence.getNom()));
        context.setVariable("titre", getTitleByCni(salarie.getNumeroCni()));
        context.setVariable("cachet", cachet);
        String html = templateEngine.process("templates/certificat/certificat_de_radiation", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        String fileName = "certificat_de_rediation_" + UUID.randomUUID() + ".pdf";
        String outputFolder = docdir + "/"+fileName;
        System.out.println(outputFolder);
        OutputStream outputStream = new FileOutputStream(outputFolder);
        renderer.createPDF(outputStream);
        PieceJointeDTO dto = new PieceJointeDTO();
        dto.setName(fileName);
        dto.setExtension(".pdf");
        System.out.println(docuri);
        dto.setFile(String.format("%s/%s", docuri, StringUtils.normalizeSpace(dto.getName())));
        dto.setEntityType("Salarie");
        dto.setEntityId(salarie.getId());
        dto.setDescription("Certificat de radiation");
        pieceJointeService.save(dto);
        outputStream.close();
        Path filePath = Paths.get(outputFolder);
        addAllPermissions(filePath);

        return dto.getFile();
    }

    public String generateCertificat(NouvelleImmatriculationDTO nouvelleImmatriculationDTO, TypeDocument typeDocument, String cachet) throws IOException, DocumentException {
        String docdir =  DOCDIR;
        String docuri =  DOCURI;
        Agence agenceIpres = agenceRepository.findByCode(nouvelleImmatriculationDTO.getAgenceIpres());
        Agence agenceCss = agenceRepository.findByCode(nouvelleImmatriculationDTO.getAgenceCss());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String adresse =  "[non renseigné]";
        String localite =  "[non renseigné]";
        String sigle =  "[non renseigné]";
        String activitePrincipale =  "[non renseigné]";
        String typeCertif;
        ZonedDateTime zonedDateTime = nouvelleImmatriculationDTO.getCreatedAt().atZone(ZoneId.of("UTC"));
        LocalDate localDate = zonedDateTime.toLocalDate();
        String dateEffet = localDate.format(formatter);
        String numSecuriteSocial = nouvelleImmatriculationDTO.getNumeroUnique() != null ? nouvelleImmatriculationDTO.getNumeroUnique() : "[non renseigné]";
        String numeroDossier = nouvelleImmatriculationDTO.getNumeroDossier() != null ? nouvelleImmatriculationDTO.getNumeroDossier() : "[non renseigné]";
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String payload = nouvelleImmatriculationDTO.getPayload();
            if (payload != null) {
                JsonNode rootNode = mapper.readTree(payload);
                TypeImmatriculation type = TypeImmatriculation.fromCode(nouvelleImmatriculationDTO.getType());
                JsonNode registrationNode;

                if (type.getRegistrationNodeName().isEmpty()) {
                    registrationNode = rootNode.path("input");
                } else {
                    registrationNode = rootNode.path("input").path(type.getNodeNameForRegistration());
                }

                adresse = type == TypeImmatriculation.MAINTIENT_AFFILIATION ?
                    registrationNode.path("addresse").asText() :
                    registrationNode.path("address").asText();

                localite = registrationNode.path("region").asText();
                sigle = type.hasShortName() ? registrationNode.path("shortName").asText() : "";
                activitePrincipale = type.hasMainLineOfBusiness() ? registrationNode.path("mainLineOfBusiness").asText() : "";
            }
        } catch (Exception ignored){}

        context.setVariable("agenceIpres", (agenceIpres != null ? agenceIpres.getNom():"[non renseigné]"));
        context.setVariable("agenceCss", (agenceCss != null ? agenceCss.getNom():"[non renseigné]"));
        context.setVariable("sectorCss", (nouvelleImmatriculationDTO.getSectorCss() != null ?nouvelleImmatriculationDTO.getSectorCss():"[non renseigné]")+" "+(nouvelleImmatriculationDTO.getZoneCss() != null?nouvelleImmatriculationDTO.getZoneCss():"[non renseigné]"));
        context.setVariable("sectorIpres", (nouvelleImmatriculationDTO.getSectorIpres() != null?nouvelleImmatriculationDTO.getSectorIpres():"[non renseigné]")+" "+(nouvelleImmatriculationDTO.getZoneIpres() != null?nouvelleImmatriculationDTO.getZoneIpres():"[non renseigné]"));
        context.setVariable("raisonSocial", nouvelleImmatriculationDTO.getRaisonSociale());
        if(typeDocument == TypeDocument.CERTIFICATION_D_IMMATRICULATION){
            typeCertif =  "CERTIFICAT D'IMMATRICULATION";
            context.setVariable("numIdentifiant", numSecuriteSocial);
            context.setVariable("identifiant", "NUMERO SECURITE SOCIALE ");
            context.setVariable("isRecepisse", false);
        }else{
            typeCertif =  "RÉCÉPISSÉ DE DÉPÔT D'IMMATRICULATION";
            context.setVariable("numIdentifiant", numeroDossier);
            context.setVariable("identifiant", "NUMERO DE DOSSIER");
            context.setVariable("isRecepisse", true);
        }

        context.setVariable("ninea", nouvelleImmatriculationDTO.getNinea());
        context.setVariable("registreCom", nouvelleImmatriculationDTO.getRegistreCommerce());
        context.setVariable("adresse", adresse);
        context.setVariable("localite", localite);
        context.setVariable("sigle", sigle);
        context.setVariable("activitePrincipale", activitePrincipale);
        context.setVariable("dateEffet", dateEffet);
        context.setVariable("faitA", (nouvelleImmatriculationDTO.getZoneIpres() != null?nouvelleImmatriculationDTO.getZoneIpres():"[non renseigné]"));
        context.setVariable("dateDuJour", LocalDate.now().format(formatter));
        context.setVariable("typeCertif", typeCertif);
        context.setVariable("cachet", cachet);
        context.setVariable("tauxAt", (nouvelleImmatriculationDTO.getTauxAt() !=null?nouvelleImmatriculationDTO.getTauxAt():"[non renseigné]"));
        String html = templateEngine.process("templates/certificat/certificat_immatriculation", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        String fileName = typeDocument + "_" + UUID.randomUUID() + ".pdf";
        String outputFolder = docdir + "/"+fileName;
        OutputStream outputStream = new FileOutputStream(outputFolder);
        renderer.createPDF(outputStream);
        PieceJointeDTO dto = new PieceJointeDTO();
        dto.setName(fileName);
        dto.setExtension(".pdf");
        dto.setFile(String.format("%s/%s", docuri, StringUtils.normalizeSpace(dto.getName())));
        dto.setEntityType("NouvelleImmatriculation");
        dto.setEntityId(nouvelleImmatriculationDTO.getId());
        dto.setDescription("Certificat d'immatriculation");
        pieceJointeService.save(dto);
        outputStream.close();
        Path filePath = Paths.get(outputFolder);
        //addAllPermissions(filePath);

        return dto.getFile();
    }

    public String generateAttestationDeRegularite(Employeur employeur) throws IOException, DocumentException {
        String docdir =  DOCDIR;
        String docuri =  DOCURI;
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("raisonSociale",employeur.getEmployerName());
        context.setVariable("numeroUnique",employeur.getNumeroIdentifiant());
        context.setVariable("adresse", employeur.getAddress());
        context.setVariable("effectif", 0);
        context.setVariable("permenant",0);
        context.setVariable("journalier", 0);
        context.setVariable("temporaire", 0);
        context.setVariable("stagiaire", 0);
        String html = templateEngine.process("templates/certificat/attestation_de_regularite", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        String fileName = "attestation_de_regularite_" + UUID.randomUUID() + ".pdf";
        String outputFolder = docdir + "/"+fileName;
        System.out.println(outputFolder);
        OutputStream outputStream = new FileOutputStream(outputFolder);
        renderer.createPDF(outputStream);
        PieceJointeDTO dto = new PieceJointeDTO();
        dto.setName(fileName);
        dto.setExtension(".pdf");
        System.out.println(docuri);
        dto.setFile(String.format("%s/%s", docuri, StringUtils.normalizeSpace(dto.getName())));
        dto.setEntityType("Salarie");
        dto.setEntityId(1L);
        dto.setDescription("Certificat de radiation");
        pieceJointeService.save(dto);
        outputStream.close();
        Path filePath = Paths.get(outputFolder);
        addAllPermissions(filePath);

        return dto.getFile();
    }
    String getCodeAgence(String nomAgence){
        return nomAgence.replaceFirst("Agence_CSS_","").substring(0,3);
    }

    String getTitleByCni(String cni){
        if (cni.startsWith("1"))
            return "MONSIEUR";
        else if (cni.startsWith("2"))
            return "MADAME";
        else
            return "";
    }

}
