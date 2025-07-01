package com.secusociale.portail.web.rest.salaries;

import com.secusociale.portail.service.salaries.SalariesService;
import com.secusociale.portail.service.soap.salaries.cm_per_exist.CMPEREXIST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;

@RestController
@RequestMapping("/api")
@Transactional
public class SalariesResource {
    private final SalariesService salariesService;

    private final Logger log = LoggerFactory.getLogger(SalariesService.class);

    public SalariesResource(SalariesService salariesService) {
        this.salariesService = salariesService;
    }

    @PostMapping("/checknumassure")
    public ResponseEntity<Holder<CMPEREXIST>> getPerExist(@RequestBody CMPEREXIST requestOBJ) {
        log.info("REST request to get getPerExist list");
        try {
            return ResponseEntity.ok(salariesService.getPerExist(requestOBJ));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
