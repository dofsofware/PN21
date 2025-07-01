package com.secusociale.portail.web.rest.demandeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.service.demandeService.RemiseGracieuseService;
import com.secusociale.portail.service.soap.remise_gracieuse_accuse_url.CmGetUrlAccuseReceptionRemiseGracieuse;
import com.secusociale.portail.service.soap.remise_gracieuse_add.CmAddDemandeRemiseGracieuse;
import com.secusociale.portail.service.soap.remise_gracieuse_notification_url.CMGETURLNOTIFICATION;
import com.secusociale.portail.service.soap.remise_gracieuse_statut.CMGetStatusRemiseGracieuse;
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
public class RemiseGracieuseResource {

    @Autowired
    private RemiseGracieuseService gracieuseService;

    @PostMapping("/remise_gracieuse/add")
    public Holder<CmAddDemandeRemiseGracieuse> createRepriseActivite(@RequestBody CmAddDemandeRemiseGracieuse remise_gracieuse) throws IOException, JAXBException {
        System.out.println(new ObjectMapper().writeValueAsString(remise_gracieuse.getInput()));
        return gracieuseService.createRemiseGracieuse(remise_gracieuse);

    }


    @GetMapping("/remise_gracieuse/statut/{idDossier}")
    Holder<CMGetStatusRemiseGracieuse> getStatutRepriseActivite(@PathVariable String idDossier) throws JAXBException {

        return gracieuseService.getStatutRemise(idDossier);

    }


    @GetMapping("/remise_gracieuse/accuseReception/{idDossier}")
    ResponseEntity<ByteArrayResource> getAccuseReception(@PathVariable String idDossier) throws Exception {
        Holder<CmGetUrlAccuseReceptionRemiseGracieuse> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        try {
            result = gracieuseService.getUrlRemise(idDossier);
            if (result.value != null) {
                stringUrl = result.value.getOutput().getUrl();
                String[] tab = stringUrl.substring(6).split("/");
                fileName = tab[tab.length - 1];
                if (StringUtils.isEmpty(stringUrl))
                    throw new Exception("Aucun certificat de régularité pour cette immatriculation!");
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

    @GetMapping("/remise_gracieuse/notification/{idDossier}")
    Holder<CMGETURLNOTIFICATION> getUrlNotification(@PathVariable String idDossier) throws JAXBException {

        return gracieuseService.getUrlNotification(idDossier);

    }


}
