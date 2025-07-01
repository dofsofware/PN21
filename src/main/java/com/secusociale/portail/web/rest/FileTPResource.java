package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.FileTP;
import com.secusociale.portail.repository.FileTPRepository;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/file-tps")
@Transactional
public class FileTPResource {

    private static final Logger LOG = LoggerFactory.getLogger(FileTPResource.class);

    private static final String ENTITY_NAME = "jhipsterFileTp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileTPRepository fileTPRepository;

    public FileTPResource(FileTPRepository fileTPRepository) {
        this.fileTPRepository = fileTPRepository;
    }

    /**
     * {@code POST  /file-tps} : Create a new fileTP.
     *
     * @param fileTP the fileTP to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileTP, or with status {@code 400 (Bad Request)} if the fileTP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FileTP> createFileTP(@Valid @RequestBody FileTP fileTP) throws URISyntaxException {
        LOG.debug("REST request to save FileTP : {}", fileTP);
        if (fileTP.getId() != null) {
            throw new BadRequestAlertException("A new fileTP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fileTP = fileTPRepository.save(fileTP);
        return ResponseEntity.created(new URI("/api/file-tps/" + fileTP.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fileTP.getId().toString()))
            .body(fileTP);
    }

    /**
     * {@code PUT  /file-tps/:id} : Updates an existing fileTP.
     *
     * @param id the id of the fileTP to save.
     * @param fileTP the fileTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileTP,
     * or with status {@code 400 (Bad Request)} if the fileTP is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FileTP> updateFileTP(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FileTP fileTP
    ) throws URISyntaxException {
        LOG.debug("REST request to update FileTP : {}, {}", id, fileTP);
        if (fileTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileTP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fileTPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fileTP = fileTPRepository.save(fileTP);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileTP.getId().toString()))
            .body(fileTP);
    }

    /**
     * {@code PATCH  /file-tps/:id} : Partial updates given fields of an existing fileTP, field will ignore if it is null
     *
     * @param id the id of the fileTP to save.
     * @param fileTP the fileTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileTP,
     * or with status {@code 400 (Bad Request)} if the fileTP is not valid,
     * or with status {@code 404 (Not Found)} if the fileTP is not found,
     * or with status {@code 500 (Internal Server Error)} if the fileTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FileTP> partialUpdateFileTP(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FileTP fileTP
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FileTP partially : {}, {}", id, fileTP);
        if (fileTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileTP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fileTPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FileTP> result = fileTPRepository
            .findById(fileTP.getId())
            .map(existingFileTP -> {
                if (fileTP.getDateSoumission() != null) {
                    existingFileTP.setDateSoumission(fileTP.getDateSoumission());
                }
                if (fileTP.getStatut() != null) {
                    existingFileTP.setStatut(fileTP.getStatut());
                }
                if (fileTP.getMotif() != null) {
                    existingFileTP.setMotif(fileTP.getMotif());
                }
                if (fileTP.getDocLink() != null) {
                    existingFileTP.setDocLink(fileTP.getDocLink());
                }

                return existingFileTP;
            })
            .map(fileTPRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileTP.getId().toString())
        );
    }

    /**
     * {@code GET  /file-tps} : get all the fileTPS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileTPS in body.
     */
    @GetMapping("")
    public List<FileTP> getAllFileTPS(@RequestParam(name = "filter", required = false) String filter) {
        if ("tempsdepresence-is-null".equals(filter)) {
            LOG.debug("REST request to get all FileTPs where tempsDePresence is null");
            return StreamSupport.stream(fileTPRepository.findAll().spliterator(), false)
                .filter(fileTP -> fileTP.getTempsDePresence() == null)
                .collect(Collectors.toList());
        }
        LOG.debug("REST request to get all FileTPS");
        return fileTPRepository.findAll();
    }

    /**
     * {@code GET  /file-tps/:id} : get the "id" fileTP.
     *
     * @param id the id of the fileTP to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileTP, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FileTP> getFileTP(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FileTP : {}", id);
        Optional<FileTP> fileTP = fileTPRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fileTP);
    }

    /**
     * {@code DELETE  /file-tps/:id} : delete the "id" fileTP.
     *
     * @param id the id of the fileTP to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFileTP(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FileTP : {}", id);
        fileTPRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
