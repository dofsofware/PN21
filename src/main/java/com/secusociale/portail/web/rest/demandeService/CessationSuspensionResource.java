package com.secusociale.portail.web.rest.demandeService;

import com.secusociale.portail.service.demandeService.CessationSuspensionService;
import com.secusociale.portail.service.soap.cessation_suspension.CMADDCESSATIONORSUSPENSION;
import com.secusociale.portail.service.soap.cessation_suspension_statut.CMGETSTATUSCESSATIONSUSPENSION;
import com.secusociale.portail.service.soap.url_reception_cessation_suspension.CMGetUrlAccuseReceptionCessationAndSuspension;
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
public class CessationSuspensionResource {

    @Autowired
    private CessationSuspensionService cessationSuspensionService;

    @PostMapping("/cessation_suspension/add")
    public Holder<CMADDCESSATIONORSUSPENSION> createCessationSuspension(@RequestBody CMADDCESSATIONORSUSPENSION cessation_suspension) throws IOException, JAXBException {
        return cessationSuspensionService.createCessationSuspension(cessation_suspension);
    }

    @GetMapping("/cessation_suspension/statut/{idDossier}")
    Holder<CMGETSTATUSCESSATIONSUSPENSION> getStatutCessationSuspension(@PathVariable String idDossier) throws JAXBException {
        return cessationSuspensionService.getStatutCessationSuspension(idDossier);
    }

    @GetMapping("/cessation_suspension/urlReception/{idDossier}")
    ResponseEntity<ByteArrayResource> getUrlCessationSuspension(@PathVariable String idDossier) throws Exception {
        //return
        Holder<CMGetUrlAccuseReceptionCessationAndSuspension> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        try {
            result = cessationSuspensionService.getUrlReceptionCessationSuspension(idDossier);
            if (result.value != null) {
                stringUrl = result.value.getOutput().getUrl();
                String[] tab = stringUrl.substring(6).split("/");
                fileName = tab[tab.length - 1];
                if (StringUtils.isEmpty(stringUrl))
                    throw new Exception("Aucune cessation_suspension pour cette immatriculation!");
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
