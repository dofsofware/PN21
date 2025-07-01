package com.secusociale.portail.web.rest.demandeService;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secusociale.portail.service.demandeService.MoratoireService;
import com.secusociale.portail.service.soap.moratoire.CMGETINFORMATIONMORATOIRE;

@RestController
@RequestMapping("/api")
public class MoratoireResource {

    @Autowired
    private MoratoireService moratoireService;

    @PostMapping("/moratoire/add")
    public Holder<CMGETINFORMATIONMORATOIRE> createMoratoire(@RequestBody CMGETINFORMATIONMORATOIRE moratoire) throws IOException, JAXBException {
        return moratoireService.demandeMoratoire(moratoire);
    }

}
