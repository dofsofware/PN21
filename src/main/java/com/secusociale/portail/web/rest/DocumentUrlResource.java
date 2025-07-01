package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.DocumentUrl;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.dto.custom.DocumentDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.DocumentUrl}.
 */
@RestController
@RequestMapping("/api")
public class DocumentUrlResource {

    private final Logger log = LoggerFactory.getLogger(DocumentUrlResource.class);

    private static final String ENTITY_NAME = "documentUrl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentUrlService documentUrlService;

    public DocumentUrlResource(DocumentUrlService documentUrlService) {
        this.documentUrlService = documentUrlService;
    }

    /**
     * {@code POST  /document-urls} : Create a new documentUrl.
     *
     * @param documentUrl the documentUrl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentUrl, or with status {@code 400 (Bad Request)} if the documentUrl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-urls")
    public ResponseEntity<DocumentUrl> createDocumentUrl(@RequestBody DocumentUrl documentUrl) throws URISyntaxException {
        log.debug("REST request to save DocumentUrl : {}", documentUrl);
        if (documentUrl.getId() != null) {
            throw new BadRequestAlertException("A new documentUrl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentUrl result = documentUrlService.save(documentUrl);
        return ResponseEntity.created(new URI("/api/document-urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /document-urls} : Updates an existing documentUrl.
     *
     * @param documentUrl the documentUrl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentUrl,
     * or with status {@code 400 (Bad Request)} if the documentUrl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentUrl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-urls")
    public ResponseEntity<DocumentUrl> updateDocumentUrl(@RequestBody DocumentUrl documentUrl) throws URISyntaxException {
        log.debug("REST request to update DocumentUrl : {}", documentUrl);
        if (documentUrl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentUrl result = documentUrlService.save(documentUrl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentUrl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /document-urls} : get all the documentUrls.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentUrls in body.
     */
    @GetMapping("/document-urls")
    public ResponseEntity<List<DocumentUrl>> getAllDocumentUrls(Pageable pageable) {
        log.debug("REST request to get a page of DocumentUrls");
        Page<DocumentUrl> page = documentUrlService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /document-urls/:id} : get the "id" documentUrl.
     *
     * @param id the id of the documentUrl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentUrl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-urls/{id}")
    public ResponseEntity<DocumentUrl> getDocumentUrl(@PathVariable Long id) {
        log.debug("REST request to get DocumentUrl : {}", id);
        Optional<DocumentUrl> documentUrl = documentUrlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentUrl);
    }

    /**
     * {@code DELETE  /document-urls/:id} : delete the "id" documentUrl.
     *
     * @param id the id of the documentUrl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-urls/{id}")
    public ResponseEntity<Void> deleteDocumentUrl(@PathVariable Long id) {
        log.debug("REST request to delete DocumentUrl : {}", id);
        documentUrlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    /**
     * {@code POST  /base64-to-url}
     *
     * @param documentDTO the documentUrl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentUrl, or with status {@code 400 (Bad Request)} if the documentUrl has already an ID.
     */
    @PostMapping("/base64-to-url")
    public ResponseEntity<HashMap<String, Object>> uploadDocumentUrl(@RequestBody @Valid DocumentDTO documentDTO) {
        HashMap<String, Object> map = new HashMap<>();
        String url = this.documentUrlService.transformBase64ToURL(documentDTO.getBase64(), documentDTO.getNom());
        if (url == null || url.isEmpty() || !url.contains("/")) {
            HashMap<String, Object> error = new HashMap<>();
            error.put("error", "L'URL générée est null ou vide. Vérifiez le contenu Base64.");
            error.put("message", url);
            error.put("code", 400);
            return ResponseEntity.ok(error);
        }
        map.put("url", url);
        map.put("code", 200);
        return ResponseEntity.ok(map);
    }
}
