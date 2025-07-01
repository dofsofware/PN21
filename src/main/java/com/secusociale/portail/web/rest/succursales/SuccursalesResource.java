package com.secusociale.portail.web.rest.succursales;

import com.secusociale.portail.service.soap.succursales.CMGETLISTESECURSALES;
import com.secusociale.portail.service.succursales.SuccursaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;

@RestController
@RequestMapping("/api")
@Transactional
public class SuccursalesResource {
    private final SuccursaleService succursaleService;
    private final Logger log = LoggerFactory.getLogger(SuccursalesResource.class);

    public SuccursalesResource(SuccursaleService succursaleService) {
        this.succursaleService = succursaleService;
    }

    @GetMapping("/succursales/{personId}")
    public Holder<CMGETLISTESECURSALES> listeSuccursales(@PathVariable String personId) throws JAXBException {
        return succursaleService.getListeSuccursales(personId);
    }
}
