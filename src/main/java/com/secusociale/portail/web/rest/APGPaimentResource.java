package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.domain.APGPaiment;
import com.secusociale.portail.repository.APGPaimentRepository;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.dto.APGModel;
import com.secusociale.portail.service.dto.custom.DocDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Problem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.APGPaiment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class APGPaimentResource {

    private static final String ENTITY_NAME = "portailCssIpresV2ApgPaiment";
    private final Logger log = LoggerFactory.getLogger(APGPaimentResource.class);
    private final APGPaimentRepository aPGPaimentRepository;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private DocumentUrlService documentUrlService;

    public APGPaimentResource(APGPaimentRepository aPGPaimentRepository) {
        this.aPGPaimentRepository = aPGPaimentRepository;
    }

    /**
     * {@code POST  /apg-paiments} : Create a new aPGPaiment.
     *
     * @param aPGPaiment the aPGPaiment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aPGPaiment, or with status {@code 400 (Bad Request)} if the aPGPaiment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apg-paiments")
    public ResponseEntity<APGPaiment> createAPGPaiment(@RequestBody APGPaiment aPGPaiment) throws URISyntaxException {
        if (aPGPaiment.getId() != null) {
            throw new BadRequestAlertException("A new aPGPaiment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        APGPaiment result = aPGPaimentRepository.save(aPGPaiment);
        return ResponseEntity.created(new URI("/api/apg-paiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/apg-paiments/upload-signed")
    public ResponseEntity<APGPaiment> uploadSignedDocs(@RequestBody APGPaiment aPGPaiment) {
        if (aPGPaiment.getId() == null) {
            throw new BadRequestAlertException("Le paiement doit exister pour pouvoir importer des documents signes", ENTITY_NAME, "not idexists");
        }

        try {
            DocDTO[] documents = new ObjectMapper().readValue(aPGPaiment.getDocuments(), DocDTO[].class);

            for (DocDTO doc : documents) {
                String fileName = "signed-" + doc.getNom();
                MultipartFile multipartFile = Objects.requireNonNull(base64ToMultipart(doc.getSigne(), "signed"));
                String path = documentUrlService.uploadedExcel(multipartFile, fileName);
                doc.setSigne(path);
                doc.setSigned(true);
            }

            aPGPaiment.setDocuments(new ObjectMapper().writeValueAsString(documents));

        } catch (IOException e) {
            e.printStackTrace();
        }

        APGPaiment result = aPGPaimentRepository.save(aPGPaiment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aPGPaiment.getId().toString()))
            .body(result);
    }


    //String extension = StringUtils.isEmpty(preDnsInput.getSalariesFile().split(";")[0].split("/")[1]) ? ".xlsx" : extensions.get(preDnsInput.getSalariesFile().split(";")[0].split("/")[1]);
    //String fileName = "PW_EMP" + idEmployer + "_" + uuid + "_FID" + numeroUnique + extension;
    //MultipartFile multipartFile = Objects.requireNonNull(base64ToMultipart(preDnsInput.getSalariesFile(), "listeSalaries"));
    //String path = documentUrlService.uploadedExcel(multipartFile, fileName);

    /**
     * {@code PUT  /apg-paiments} : Updates an existing aPGPaiment.
     *
     * @param aPGPaiment the aPGPaiment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aPGPaiment,
     * or with status {@code 400 (Bad Request)} if the aPGPaiment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aPGPaiment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apg-paiments")
    public ResponseEntity<APGPaiment> updateAPGPaiment(@RequestBody APGPaiment aPGPaiment) throws URISyntaxException {
        if (aPGPaiment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        APGPaiment result = aPGPaimentRepository.save(aPGPaiment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aPGPaiment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /apg-paiments} : get all the aPGPaiments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aPGPaiments in body.
     */
    @GetMapping("/apg-paiments")
    public List<APGPaiment> getAllAPGPaiments() {
        return aPGPaimentRepository.findAll();
    }

    /**
     * {@code GET  /apg-paiments/:id} : get the "id" aPGPaiment.
     *
     * @param id the id of the aPGPaiment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aPGPaiment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apg-paiments/{id}")
    public ResponseEntity<APGPaiment> getAPGPaiment(@PathVariable Long id) {
        Optional<APGPaiment> aPGPaiment = aPGPaimentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aPGPaiment);
    }

    /**
     * {@code GET  /apg-paiments/:numeroUnique} : get the "numFac" aPGPaiment.
     *
     * @param numeroUnique the numeroUnique of the aPGPaiment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aPGPaiment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apg-paiments/prelevements/{numeroUnique}")
    public ResponseEntity<List<APGPaiment>> getAllAPGPaimentByPersonIdAndMethodePaiement(@PathVariable String numeroUnique) {
        List<APGPaiment> aPGPaiments = aPGPaimentRepository.findAllByPersonIdAndMethodeDePaiementLike(numeroUnique, "%PRELEVEMENT%");
        return ResponseEntity.ok(aPGPaiments);
    }

    @GetMapping("/apg-paiments/paiements-autres/{numeroUnique}")
    public ResponseEntity<List<APGPaiment>> getAllAPGPaimentByPersonIdAndMethodePaiementOther(@PathVariable String numeroUnique) {
        List<APGPaiment> aPGPaiments = aPGPaimentRepository.findAllByPersonIdAndMethodeDePaiementNotLike(numeroUnique, "%PRELEVEMENT%");
        return ResponseEntity.ok(aPGPaiments);
    }


    @PostMapping("/apg-paiments/prelevements/get-signed-docs")
    public ResponseEntity<Object> getSignedDocs(@RequestBody APGModel model) {
        if (model == null) {
            throw Problem.builder().withDetail("Veuillez poster le requestId et le transactionId").withTitle("Erreur").build();
        }
        if (model.getRequestId() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le requestId").withTitle("Erreur").build();
        }
        if (model.getTransactionId() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le transactionId").withTitle("Erreur").build();
        }
        List<APGPaiment> aPGPaiments = aPGPaimentRepository.findAllByReferencePaimentAndTransactionId(model.getRequestId(), model.getTransactionId());
        if (aPGPaiments.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        } else {
            try {
                APGPaiment aPGPaiment = aPGPaiments.get(0);
                DocDTO[] documents = new ObjectMapper().readValue(aPGPaiment.getDocuments(), DocDTO[].class);
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", "0000");
                map.put("message", "recuperation des documents signes!");
                map.put("docSignes", Arrays.stream(documents).map(DocDTO::getSigne).collect(Collectors.toList()));
                map.put("responseMessage", "OK");
                return ResponseEntity.ok(map);
            } catch (Exception exp) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", "4000");
                map.put("message", "impossible de recuperer les documents signes!");
                map.put("docSignes", null);
                map.put("responseMessage", "OK");
                return ResponseEntity.ok(new ArrayList<>());
            }
        }
        // DocDTO[] documents = new ObjectMapper().readValue(aPGPaiment.getDocuments(), DocDTO[].class);

    }

    @PostMapping("/apg-paiments/prelevements/set-signed-docs")
    public ResponseEntity<Object> setSignedDocs(@RequestBody APGModel model) {
        if (model == null) {
            throw Problem.builder().withDetail("Veuillez poster le requestId et le transactionId").withTitle("Erreur").build();
        }
        if (model.getRequestId() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le requestId").withTitle("Erreur").build();
        }
        if (model.getTransactionId() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le transactionId").withTitle("Erreur").build();
        }
        if (model.getDocSignes() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le tableau des documents signes").withTitle("Erreur").build();
        }
        List<APGPaiment> aPGPaiments = aPGPaimentRepository.findAllByReferencePaimentAndTransactionId(model.getRequestId(), model.getTransactionId());
        if (aPGPaiments.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        } else {
            try {
                APGPaiment aPGPaiment = aPGPaiments.get(0);
                aPGPaiment.setSignedDocs(new ObjectMapper().writeValueAsString(model.getDocSignes()));
                aPGPaimentRepository.save(aPGPaiment);
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", "0000");
                map.put("message", "chargement des documents signes !");
                map.put("responseMessage", "OK");
                return ResponseEntity.ok(map);
            } catch (Exception exp) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", "4000");
                map.put("message", "impossible de charger les documents signes !");
                map.put("responseMessage", "NOK");
                return ResponseEntity.ok(map);
            }
        }

    }

    @PostMapping("/apg-paiments/prelevements/update-status")
    public ResponseEntity<Object> updateStatut(@RequestBody APGModel model) {
        if (model == null) {
            throw Problem.builder().withDetail("Veuillez poster le requestId et le transactionId").withTitle("Erreur").build();
        }
        if (model.getRequestId() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le requestId").withTitle("Erreur").build();
        }
        if (model.getTransactionId() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le transactionId").withTitle("Erreur").build();
        }
        if (model.getStatus() == null) {
            throw Problem.builder().withDetail("Veuillez renseigner le nouveau statut du paiement").withTitle("Erreur").build();
        }
        List<APGPaiment> aPGPaiments = aPGPaimentRepository.findAllByReferencePaimentAndTransactionId(model.getRequestId(), model.getTransactionId());
        if (aPGPaiments.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        } else {
            try {
                APGPaiment aPGPaiment = aPGPaiments.get(0);
                aPGPaiment.setStatut(model.getStatus());
                aPGPaimentRepository.save(aPGPaiment);
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", "0000");
                map.put("message", "Statut change avec succes!");
                map.put("responseMessage", "OK");
                return ResponseEntity.ok(map);
            } catch (Exception exp) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", "4000");
                map.put("message", "impossible de changer le statut du paiement");
                map.put("responseMessage", "NOK");
                return ResponseEntity.ok(map);
            }
        }

    }


    /**
     * {@code GET  /apg-paiments/:numFac} : get the "numFac" aPGPaiment.
     *
     * @param numFac the id of the aPGPaiment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aPGPaiment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apg-paiments/numFac/{numFac}")
    public ResponseEntity<APGPaiment> getAPGPaimentByNumFac(@PathVariable String numFac) {
        Optional<APGPaiment> aPGPaiment = aPGPaimentRepository.findAllByFacturesConcerneesContains(numFac).stream().findFirst();
        return ResponseUtil.wrapOrNotFound(aPGPaiment);
    }

    /**
     * {@code DELETE  /apg-paiments/:id} : delete the "id" aPGPaiment.
     *
     * @param id the id of the aPGPaiment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apg-paiments/{id}")
    public ResponseEntity<Void> deleteAPGPaiment(@PathVariable Long id) {
        aPGPaimentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
