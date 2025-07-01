package com.secusociale.portail.service;

import com.secusociale.portail.config.ApplicationProperties;
import com.secusociale.portail.domain.DocumentUrl;
import com.secusociale.portail.repository.DocumentUrlRepository;
import com.secusociale.portail.service.utils.UtilsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;

/**
 * Service Implementation for managing {@link DocumentUrl}.
 */
@Service
@Transactional
public class DocumentUrlService {

    @Autowired
    Environment environment;
    @Autowired
    private ApplicationProperties properties;
    @Value("${cssipres.docdir:/opt/tomcat/webapps/documents}")
    private String DOCDIR;
 //   @Value("${cssipres.docuri:http://online.secusociale.sn/documents}")
    @Value("https://preprodonline.secusociale.sn/documents")
    private String DOCURI;


    private final Logger log = LoggerFactory.getLogger(DocumentUrlService.class);

    private final DocumentUrlRepository documentUrlRepository;

    public DocumentUrlService(DocumentUrlRepository documentUrlRepository) {
        this.documentUrlRepository = documentUrlRepository;
    }

    /**
     * Save a documentUrl.
     *
     * @param documentUrl the entity to save.
     * @return the persisted entity.
     */
    public DocumentUrl save(DocumentUrl documentUrl) {
        log.debug("Request to save DocumentUrl : {}", documentUrl);
        return documentUrlRepository.save(documentUrl);
    }


    /**
     * Get all the documentUrls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DocumentUrl> findAll(Pageable pageable) {
        log.debug("Request to get all DocumentUrls");
        return documentUrlRepository.findAll(pageable);
    }

    /**
     * Get one documentUrl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DocumentUrl> findOne(Long id) {
        log.debug("Request to get DocumentUrl : {}", id);
        return documentUrlRepository.findById(id);
    }

    /**
     * Delete the documentUrl by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DocumentUrl : {}", id);
        documentUrlRepository.deleteById(id);
    }

    public String transformBase64ToURL(String base64, String name) {
        if (StringUtils.isEmpty(base64)) {
            return "La chaine base64 est vide";
        }
        if (base64.startsWith("http") || base64.startsWith("localhost")) {
            return base64;
        }
        try {
            if (base64.split(",").length < 2) {
                return "Format incorrect : La chaine base64 doit contenir une virgule et les 2 parties non vide";
            }
            return uploadedDocument(base64ToMultipart(base64, name));
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public String uploadedDocument(MultipartFile document) throws IOException {
        String docdir = environment.getProperty("cssipres.docdir") == null ? System.getProperty("cssipres.docdir") : DOCDIR;
        String docuri = environment.getProperty("cssipres.docuri") == null ? System.getProperty("cssipres.docdir") : DOCURI;

        if (StringUtils.isEmpty(docdir)) {
            docdir = properties.getDocdir();
        }
        if (StringUtils.isEmpty(docuri)) {
            docuri = properties.getDocuri();
        }
        String dirdest = String.format("%s/%s", docdir, StringUtils.normalizeSpace(document.getOriginalFilename()));
        File file = new File(dirdest);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        document.transferTo(file);
        Path filePath = Paths.get(dirdest);
        UtilsService.addAllPermissions(filePath);
        return String.format("%s/%s", docuri, StringUtils.normalizeSpace(document.getOriginalFilename()));
    }

    public String uploadedExcel(MultipartFile document, String nom) throws IOException {
        String docdir = environment.getProperty("cssipres.docdir") == null ? System.getProperty("cssipres.docdir") : DOCDIR;
        String docuri = environment.getProperty("cssipres.docuri") == null ? System.getProperty("cssipres.docdir") : DOCURI;

        if (StringUtils.isEmpty(docdir)) {
            docdir = properties.getDocdir();
        }
        if (StringUtils.isEmpty(docuri)) {
            docuri = properties.getDocuri();
        }
        String dirdest = String.format("%s/%s", docdir, StringUtils.normalizeSpace(nom));
        File file = new File(dirdest);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        document.transferTo(file);
        return String.format("%s/%s", docuri, StringUtils.normalizeSpace(nom));
    }

    public String uploadMandat(MultipartFile document) throws IOException {
        String docdir = environment.getProperty("cssipres.docdir") == null ? System.getProperty("cssipres.docdir") : DOCDIR;
        String docuri = environment.getProperty("cssipres.docuri") == null ? System.getProperty("cssipres.docdir") : DOCURI;

        if (StringUtils.isEmpty(docdir)) {
            docdir = properties.getDocdir();
        }
        if (StringUtils.isEmpty(docuri)) {
            docuri = properties.getDocuri();
        }
        String dirdest = String.format("%s/%s", docdir, StringUtils.normalizeSpace(document.getOriginalFilename()));
        File file = new File(dirdest);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        document.transferTo(file);
        return String.format("%s/%s", docuri, StringUtils.normalizeSpace(document.getOriginalFilename()));
    }

//    public byte[] getBytesFromBase64String(String base64) throws IOException {
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] b = new byte[0];
//        b = decoder.decodeBuffer(base64);
//
//        for (int i = 0; i < b.length; ++i) {
//            if (b[i] < 0) {
//                b[i] += 256;
//            }
//        }
//        return b;
//    }

    public String uploadDocument(MultipartFile document, String dest, String fileName) throws IOException {
        String docdir = environment.getProperty("cssipres.docdir");
        String docuri = environment.getProperty("cssipres.docuri");
//        System.out.println("inject by @Value >> Docdir: " + DOCDIR);
//        System.out.println("inject by @Value >> Docuri: " + DOCURI);
        if (StringUtils.isEmpty(docdir)) {
            docdir = "/var/www/html/Portail/documents";
        }
        if (StringUtils.isEmpty(docuri)) {
            docuri = "http://192.168.0.103/Portail/documents";
        }
        String dirdest = String.format("%s/%s", docdir, StringUtils.normalizeSpace(document.getOriginalFilename()));
        File file = new File(dirdest);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        document.transferTo(file);
        return String.format("Emplcement: %s/%s/%s", docdir, dest, fileName);

        //prod
        //document.transferTo(new File("/var/www/html/Portail/documents/"+document.getOriginalFilename()));
        //return "http://192.168.0.103/Portail/documents/"+document.getOriginalFilename();

        /*document.transferTo(new File("/var/www/html/Portail/documents/"+document.getOriginalFilename()));
        return "/var/www/html/Portail/documents/ "+document.getOriginalFilename();*/
    }
}
