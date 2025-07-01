package com.secusociale.portail.web.rest.demandeService;

import com.secusociale.portail.service.demandeService.RepriseActiviteService;
import com.secusociale.portail.service.soap.reprise_activite.CMAddProcessRepriseActivite;
import com.secusociale.portail.service.soap.reprise_activite_accuse_url.CMGetUrlAccuseReceptionReprise;
import com.secusociale.portail.service.soap.reprise_activite_statut.CMGetStatusRepriseActivite;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class RepriseActiviteResource {

    @Autowired
    private RepriseActiviteService activiteService;

    @PostMapping("/reprise_activite/add")
    public Holder<CMAddProcessRepriseActivite> createRepriseActivite(@RequestBody CMAddProcessRepriseActivite reprise_activite) throws IOException, JAXBException {
        return activiteService.createReprise(reprise_activite);
    }

    @GetMapping("/reprise_activite/statut/{idDossier}")
    Holder<CMGetStatusRepriseActivite> getStatutRepriseActivite(@PathVariable String idDossier) throws JAXBException {
        return activiteService.getStatutReprise(idDossier);
    }

    @GetMapping("/reprise_activite/urlReception/{idDossier}")
    ResponseEntity<ByteArrayResource> getUrlRepriseActivite(@PathVariable String idDossier) throws Exception {
        Holder<CMGetUrlAccuseReceptionReprise> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        try {
            result = activiteService.getUrlAccuseReprise(idDossier);
            if (result.value != null) {
                stringUrl = result.value.getOutput().getUrl();
                String[] tab = stringUrl.substring(6).split("/");
                fileName = tab[tab.length - 1];
                if (StringUtils.isEmpty(stringUrl))
                    throw new Exception("Aucune reprise_activite pour cette immatriculation!");
                URL url = new URL(stringUrl);
                InputStream is = url.openStream();
                byte[] data = IOUtils.toByteArray(is);
                ByteArrayResource resource = new ByteArrayResource(data);
                MediaType mediaType = MediaType.APPLICATION_PDF;
                return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                    // Content-Type
                    .contentType(mediaType) //
                    // Content-Length
                    .contentLength(data.length) //
                    .body(resource);
            }
        } catch (Exception exception) {
            throw new Exception(Problem.builder().withTitle("Erreur lors du telechargement du fichier").with("stringUrl", stringUrl).withDetail("Verifier si l'url >> " + stringUrl + " existe").build());
        }
        return null;

    }
}
