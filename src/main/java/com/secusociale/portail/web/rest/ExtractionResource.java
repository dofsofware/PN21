package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.ExportationService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;


@RestController
@RequestMapping("/api")
public class ExtractionResource {

    private final ExportationService exportationService;
    private final UserRepository userRepository;

    public ExtractionResource(ExportationService exportationService, UserRepository userRepository) {
        this.exportationService = exportationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/export-actives")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathParam("agentId") Long agentId) throws Exception {
        String fileName = "";

        String loggedusername = getCurrentUserLogin().orElse(null);

        if (isCurrentUserInRole(AuthoritiesConstants.AGENT)) {
            User user = userRepository.findByLogin(loggedusername);
            agentId = user.getId();
        }
        try {
            exportationService.extractAllActives(agentId);
            File file = exportationService.getFile();
            fileName = file.getName();
            InputStream is = new FileInputStream(file);
            byte[] data = IOUtils.toByteArray(is);
            ByteArrayResource resource = new ByteArrayResource(data);
            String TEXT_CSV = "text/csv";
            MediaType mediaType = new MediaType("text", "csv");
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-fileName",fileName);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
            return ResponseEntity.ok()
                // Content-Disposition
                .headers(headers)
                // Content-Type
                .contentType(mediaType) //
                // Content-Length
                .contentLength(data.length) //
                .body(resource);

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception(Problem.builder().withTitle("Erreur lors du telechargement du fichier").build());
        }
    }

}
