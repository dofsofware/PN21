package com.secusociale.portail.web.rest.salaries;

import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.service.salaries.CotisationJobService;
import com.secusociale.portail.service.salaries.CotisationService;
import com.secusociale.portail.service.salaries.DetailsCotisationService;
import com.secusociale.portail.service.soap.cotisationParAnne.CmRecupererInfosCotisationParAnnee;
import com.secusociale.portail.service.soap.detailsCotisationParAnne.CmRecupererInfosDetailsCotisation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@Transactional
public class CotisationResource {
    private final CotisationService cotisationService;
    private final DetailsCotisationService detailsCotisationService;
    private final CotisationJobService cotisationJobService;

    private final Logger log = LoggerFactory.getLogger(CotisationResource.class);

    public CotisationResource(CotisationService cotisationService, DetailsCotisationService detailsCotisationService, CotisationJobService cotisationJobService) {
        this.cotisationService = cotisationService;
        this.detailsCotisationService = detailsCotisationService;
        this.cotisationJobService = cotisationJobService;
    }


    @PostMapping("/get-cotisations")
    public ResponseEntity<Holder<CmRecupererInfosCotisationParAnnee>> getCotisations(@RequestBody CmRecupererInfosCotisationParAnnee requestOBJ) {
        log.info("REST request to get getPerExist list");
        try {
            return ResponseEntity.ok(cotisationService.cotisationParAnnee(requestOBJ));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get-cotisations-details")
    public ResponseEntity<Holder<CmRecupererInfosDetailsCotisation>> getCotisationDetails(@RequestBody CmRecupererInfosDetailsCotisation requestOBJ) {
        log.info("REST request to get getPerExist list");
        try {
            return ResponseEntity.ok(detailsCotisationService.detailsCotisation(requestOBJ));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/synchronisation-manuelle/par-annee")
    public ResponseEntity<HashMap<String, Object>> synchroManuel(@RequestBody Salarie salarie, @RequestParam String annee) {
        if (StringUtils.isEmpty(annee)) {
            annee = new SimpleDateFormat("yyyy").format(new Date());
        }
        this.cotisationJobService.jobForSingleSalarie(salarie, annee);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message", String.format("synchronisation pour l'année %s effective", annee));
        return ResponseEntity.ok(map);
    }

    @PostMapping("/synchronisation-manuelle")
    public ResponseEntity<HashMap<String, Object>> synchroManuel(@RequestBody Salarie salarie) {
        if (salarie == null || salarie.getId() == null) {
            throw Problem.builder().withTitle("Salarie doit exister").withStatus(Status.BAD_REQUEST).build();
        }
        this.cotisationJobService.jobForSingleSalarie(salarie);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message", "synchronisation effective");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/synchronisation-complete")
    public ResponseEntity<HashMap<String, Object>> synchroComplete() {
        this.cotisationJobService.job();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message", "synchronisation effective");
        return ResponseEntity.ok(map);
    }
}
