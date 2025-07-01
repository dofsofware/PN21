package com.secusociale.portail.web;

import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.dto.custom.Base64DTO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Problem;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;

@Controller
public class HelpersController {

    private final ServletContext servletContext;

    @Autowired
    private DocumentUrlService documentUrlService;

    private final Logger log = LoggerFactory.getLogger(HelpersController.class);

    public HelpersController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName);
        try {
            return MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    @GetMapping(value = "api/helpers/getPDFStreamFromUrl", params = {"stringUrl", "fileName"})
    public ResponseEntity<Object> getStream(@RequestParam("stringUrl") String stringUrl, @RequestParam(value = "fileName", defaultValue = "streamPDF", required = false) String fileName) throws Exception {

        log.info("get stream pdf , {'stringUrl': {}, 'fileName': {}}", stringUrl, fileName);
        try {
            if (StringUtils.isAllEmpty(fileName))
                fileName = "facture";
            if (StringUtils.isEmpty(stringUrl))
                throw new Exception("StringUrl must be set");
            URL url = new URL(stringUrl);
            InputStream is = url.openStream();

            byte[] data = IOUtils.toByteArray(is);
            ByteArrayResource resource = new ByteArrayResource(data);
            if (fileName.endsWith(".pdf"))
                fileName = fileName.substring(0, fileName.length() - 4);

            MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);
            return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf")
                // Content-Type
                .contentType(mediaType) //
                // Content-Length
                .contentLength(data.length) //
                .body(resource);
        } catch (Exception exception) {
            throw new Exception(Problem.builder().withTitle("Erreur lors du telechargement du fichier").with("stringUrl", stringUrl).withDetail("Verifier si l'url >> " + stringUrl + " existe").build());
        }
    }

    @PostMapping(value = "api/helpers/base64ToUrl")
    public ResponseEntity<Object> getUrl(@RequestBody Base64DTO base64DTO) {
        Map<String, Object> rep = new HashMap<>();
        //file handling
        try {
            MultipartFile multipartFile = base64ToMultipart(base64DTO.getBase64());
            String filePath = documentUrlService.uploadedDocument(multipartFile);
            rep.put("code", "200");
            rep.put("url", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            rep.put("code", "400");
            rep.put("url", null);
            rep.put("erreur", e.getMessage());
        }

        return ResponseEntity.ok(rep);
    }

}
