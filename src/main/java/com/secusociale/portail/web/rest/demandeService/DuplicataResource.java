package com.secusociale.portail.web.rest.demandeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.service.demandeService.DuplicataService;
import com.secusociale.portail.service.soap.duplicata_facture_recherche.CMGETNUMEROFACTURE;
import com.secusociale.portail.service.soap.duplicata_facture_url.CMGETFACTURE;
import com.secusociale.portail.service.soap.duplicata_recu_recherche.CMGETPAYMENT;
import com.secusociale.portail.service.soap.duplicata_recu_url.CMGETPAYMENTURL;
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
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class DuplicataResource {

    @Autowired
    private DuplicataService duplicataService;


    @GetMapping("/duplicata/facture/{numeroFacture}")
    ResponseEntity<ByteArrayResource> getFacture(@PathVariable String numeroFacture) throws Exception {
        Holder<CMGETFACTURE> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        result = duplicataService.getDuplicataFacture(numeroFacture);
        System.out.println(new ObjectMapper().writeValueAsString(result));
        if (result.value != null) {
            stringUrl = result.value.getOutput().getFacturePdf();
            if (StringUtils.isEmpty(stringUrl)) {
                throw Problem.builder().withTitle("Aucun duplicata pour cette facture!").with("stringUrl", stringUrl).withDetail("Aucun duplicata pour cette facture!").build();
            }
            String[] tab = stringUrl.substring(6).split("/");
            fileName = tab[tab.length - 1];

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

        return null;
    }

    @PostMapping("/duplicata/rechercheFacture")
    Holder<CMGETNUMEROFACTURE> rechercheFacture(@RequestBody CMGETNUMEROFACTURE rechercheFacture) throws JAXBException {
        return duplicataService.rechercheFacture(rechercheFacture);
    }

    @GetMapping("/duplicata/recu/{idPaiement}")
    ResponseEntity<ByteArrayResource> getRecu(@PathVariable String idPaiement) throws Exception {
        Holder<CMGETPAYMENTURL> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";
        result = duplicataService.getDuplicataRecu(idPaiement);
        if (result.value != null) {
            stringUrl = result.value.getOutput().getRecuPaiement();
            if (StringUtils.isEmpty(stringUrl)) {
                throw Problem.builder().withTitle("Aucun duplicata pour ce reçu!").with("stringUrl", stringUrl).withDetail("Aucun duplicata pour ce reçu!").build();
            }
            String[] tab = stringUrl.substring(6).split("/");
            fileName = tab[tab.length - 1];
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

        return null;
    }

    @PostMapping("/duplicata/rechercheRecu")
    Holder<CMGETPAYMENT> rechercheFacture(@RequestBody CMGETPAYMENT rechercheRecu) throws JAXBException {
        return duplicataService.rechercheRecu(rechercheRecu);
    }

}
