package com.secusociale.portail.web.rest.attestation;

import com.secusociale.portail.service.attestation.DemandeAttestationService;
import com.secusociale.portail.service.attestation.GetAttestationUrlService;
import com.secusociale.portail.service.attestation.StatutDossierAttestationService;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegularite;
import com.secusociale.portail.service.soap.demandeAttestationReguralite.CmGetAttestationRegulariteFault;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestation;
import com.secusociale.portail.service.soap.statutAttestationRegularite.CMGetStatusDemandeAttestationFault;
import com.secusociale.portail.service.soap.urlAttestationReguralite.CMGENATTESTATION;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class DemandeAttestationRegulariteResource {

    @Autowired
    private DemandeAttestationService demandeAttestationService;
    @Autowired
    private StatutDossierAttestationService statutDossierAttestationService;
    @Autowired
    private GetAttestationUrlService getAttestationUrlService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @GetMapping("/attestation/create/{numeroEmployeur}")
    Holder<CmGetAttestationRegularite> createDossierAttestation(@PathVariable String numeroEmployeur) throws JAXBException, CmGetAttestationRegulariteFault {
        Holder<CmGetAttestationRegularite> cmGetAttestationRegularite = new Holder<CmGetAttestationRegularite>();
        cmGetAttestationRegularite = demandeAttestationService.createDossierAttestation(numeroEmployeur);
        return cmGetAttestationRegularite;
    }

    @GetMapping("/attestation/getStatus/{idDossier}")
    Holder<CMGetStatusDemandeAttestation> getStatutDossier(@PathVariable String idDossier) throws JAXBException, CMGetStatusDemandeAttestationFault {
        Holder<CMGetStatusDemandeAttestation> cmGetStatutAttestationRegularite = new Holder<CMGetStatusDemandeAttestation>();
        cmGetStatutAttestationRegularite = statutDossierAttestationService.getStatutDemandeAttestation(idDossier);
        return cmGetStatutAttestationRegularite;
    }

    @GetMapping("/attestation/getUrl/{idDossier}")
    ResponseEntity<ByteArrayResource> getUrlAttestationRegularite(@PathVariable String idDossier) throws Exception {
        Holder<CMGENATTESTATION> result = new Holder<CMGENATTESTATION>();
        String fileName = "";
        String stringUrl = "";
        try {
            result = getAttestationUrlService.getUrlAttestation(idDossier);
            if (result.value != null) {
                stringUrl = result.value.getOutput().getUrl();
                System.out.println("URL >> " + stringUrl);
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
}
