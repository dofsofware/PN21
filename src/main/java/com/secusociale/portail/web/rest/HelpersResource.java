package com.secusociale.portail.web.rest;

import com.secusociale.portail.service.HelpersQueryService;
import com.secusociale.portail.service.HelpersService;
import com.secusociale.portail.service.dto.HelpersCriteria;
import com.secusociale.portail.service.dto.HelpersDTO;
import com.secusociale.portail.service.dto.custom.UrlDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Helpers}.
 */
@RestController
@RequestMapping("/api")
public class HelpersResource {

    private final Logger log = LoggerFactory.getLogger(HelpersResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2Helpers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HelpersService helpersService;

    private final HelpersQueryService helpersQueryService;

    public HelpersResource(HelpersService helpersService, HelpersQueryService helpersQueryService) {
        this.helpersService = helpersService;
        this.helpersQueryService = helpersQueryService;
    }

    /**
     * {@code POST  /helpers} : Create a new helpers.
     *
     * @param helpersDTO the helpersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new helpersDTO, or with status {@code 400 (Bad Request)} if the helpers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/helpers")
    public ResponseEntity<HelpersDTO> createHelpers(@RequestBody HelpersDTO helpersDTO) throws URISyntaxException {
        log.debug("REST request to save Helpers : {}", helpersDTO);
        if (helpersDTO.getId() != null) {
            throw new BadRequestAlertException("A new helpers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HelpersDTO result = helpersService.save(helpersDTO);
        return ResponseEntity.created(new URI("/api/helpers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /helpers} : Updates an existing helpers.
     *
     * @param helpersDTO the helpersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated helpersDTO,
     * or with status {@code 400 (Bad Request)} if the helpersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the helpersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/helpers")
    public ResponseEntity<HelpersDTO> updateHelpers(@RequestBody HelpersDTO helpersDTO) throws URISyntaxException {
        log.debug("REST request to update Helpers : {}", helpersDTO);
        if (helpersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HelpersDTO result = helpersService.save(helpersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, helpersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /helpers} : get all the helpers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of helpers in body.
     */
    @GetMapping("/helpers")
    public ResponseEntity<List<HelpersDTO>> getAllHelpers(HelpersCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Helpers by criteria: {}", criteria);
        Page<HelpersDTO> page = helpersQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /helpers/count} : count all the helpers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/helpers/count")
    public ResponseEntity<Long> countHelpers(HelpersCriteria criteria) {
        log.debug("REST request to count Helpers by criteria: {}", criteria);
        return ResponseEntity.ok().body(helpersQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /helpers/:id} : get the "id" helpers.
     *
     * @param id the id of the helpersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the helpersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/helpers/{id}")
    public ResponseEntity<HelpersDTO> getHelpers(@PathVariable Long id) {
        log.debug("REST request to get Helpers : {}", id);
        Optional<HelpersDTO> helpersDTO = helpersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(helpersDTO);
    }

    /**
     * {@code DELETE  /helpers/:id} : delete the "id" helpers.
     *
     * @param id the id of the helpersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/helpers/{id}")
    public ResponseEntity<Void> deleteHelpers(@PathVariable Long id) {
        log.debug("REST request to delete Helpers : {}", id);
        helpersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/helpers/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestBody UrlDTO urlDTO) throws Exception {
        String fileName = "";
        String stringUrl = urlDTO.getUrl();
        try {
            String[] tab = stringUrl.substring(6).split("/");
            fileName = StringUtils.isEmpty(urlDTO.getFileName()) ? tab[tab.length - 1] : urlDTO.getFileName();
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

        } catch (Exception exception) {
            throw new Exception(Problem.builder().withTitle("Erreur lors du telechargement du fichier").with("stringUrl", stringUrl).withDetail("Verifier si l'url >> " + stringUrl + " existe").build());
        }
    }
}
