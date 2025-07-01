package com.secusociale.portail.web.rest;

import com.secusociale.portail.model.MultipartWithExtension;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.PieceJointeQueryService;
import com.secusociale.portail.service.PieceJointeService;
import com.secusociale.portail.service.dto.PieceJointeCriteria;
import com.secusociale.portail.service.dto.PieceJointeDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipartWithExtension;
import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.PieceJointe}.
 */
@RestController
@RequestMapping("/api")
public class PieceJointeResource {

    private final Logger log = LoggerFactory.getLogger(PieceJointeResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2PieceJointe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PieceJointeService pieceJointeService;
    private final DocumentUrlService documentUrlService;


    private final PieceJointeQueryService pieceJointeQueryService;

    public PieceJointeResource(PieceJointeService pieceJointeService, DocumentUrlService documentUrlService, PieceJointeQueryService pieceJointeQueryService) {
        this.pieceJointeService = pieceJointeService;
        this.documentUrlService = documentUrlService;
        this.pieceJointeQueryService = pieceJointeQueryService;
    }

    /**
     * {@code POST  /piece-jointes} : Create a new pieceJointe.
     *
     * @param pieceJointeDTO the pieceJointeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pieceJointeDTO, or with status {@code 400 (Bad Request)} if the pieceJointe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/piece-jointes")
    public ResponseEntity<PieceJointeDTO> createPieceJointe(@RequestBody PieceJointeDTO pieceJointeDTO) throws URISyntaxException {
        log.debug("REST request to save PieceJointe : {}", pieceJointeDTO);
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        if (pieceJointeDTO.getId() != null) {
            throw new BadRequestAlertException("A new pieceJointe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        try {
            if (!pieceJointeDTO.getFile().startsWith("http") && !pieceJointeDTO.getFile().startsWith("localhost")) {
                MultipartWithExtension multipartFile = base64ToMultipartWithExtension(pieceJointeDTO.getFile(), pieceJointeDTO.getName().trim().replaceAll(" ", "_"));
                String url = this.documentUrlService.uploadedDocument(Objects.requireNonNull(multipartFile.getMultipartFile()));
                pieceJointeDTO.setFile(url);
                pieceJointeDTO.setExtension(multipartFile.getExtension());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw Problem.builder().withDetail("Impossible d'uploader le fichier!, une erreur s'est produite (" + e.getMessage() + ")").withTitle("Erreur").build();
        }
        pieceJointeDTO.setCreatedAt(Instant.now());
        pieceJointeDTO.setUserCreated(username);
        PieceJointeDTO result = pieceJointeService.save(pieceJointeDTO);
        return ResponseEntity.created(new URI("/api/piece-jointes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /piece-jointes} : Updates an existing pieceJointe.
     *
     * @param pieceJointeDTO the pieceJointeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pieceJointeDTO,
     * or with status {@code 400 (Bad Request)} if the pieceJointeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pieceJointeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/piece-jointes")
    public ResponseEntity<PieceJointeDTO> updatePieceJointe(@RequestBody PieceJointeDTO pieceJointeDTO) throws URISyntaxException {
        log.debug("REST request to update PieceJointe : {}", pieceJointeDTO);
        if (pieceJointeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        try {
            if (!pieceJointeDTO.getFile().startsWith("http") && !pieceJointeDTO.getFile().startsWith("localhost")) {
                MultipartWithExtension multipartFile = base64ToMultipartWithExtension(pieceJointeDTO.getFile(), pieceJointeDTO.getName().trim().replaceAll(" ", "_"));
                String url = this.documentUrlService.uploadedDocument(Objects.requireNonNull(multipartFile.getMultipartFile()));
                pieceJointeDTO.setFile(url);
                pieceJointeDTO.setExtension(multipartFile.getExtension());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw Problem.builder().withDetail("Impossible d'uploader le fichier!, une erreur s'est produite (" + e.getMessage() + ")").withTitle("Erreur").build();
        }
        PieceJointeDTO result = pieceJointeService.save(pieceJointeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pieceJointeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /piece-jointes} : get all the pieceJointes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pieceJointes in body.
     */
    @GetMapping("/piece-jointes")
    public ResponseEntity<HashMap<String, Object>> getAllPieceJointes(PieceJointeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PieceJointes by criteria: {}", criteria);
        Page<PieceJointeDTO> page = pieceJointeQueryService.findByCriteria(criteria, pageable);
        List<PieceJointeDTO> list = page.getContent();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    /**
     * {@code GET  /mes-piece-jointes} : get all the pieceJointes.
     *
     * @param pageable   the pagination information.
     * @param criteria   the criteria which the requested entities should match.
     * @param idEntity   the id of the entity owned the pieceJointes.
     * @param typeEntity the type of the entity owned the pieceJointes.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pieceJointes in body.
     */
    @GetMapping("/mes-piece-jointes")
    public ResponseEntity<HashMap<String, Object>> getMyPieceJointes(@PathParam("idEntity") Long idEntity, @PathParam("typeEntity") String typeEntity, PieceJointeCriteria criteria, Pageable pageable) {
        if (idEntity == null || StringUtils.isEmpty(typeEntity)) {
            throw Problem.builder().withDetail("Le type (typeEntity) et l'id (idEntity) sont requis pour recuperer la liste des pieces jointes!").withTitle("Erreur").build();
        }
        LongFilter lf = new LongFilter();
        StringFilter sf = new StringFilter();
        lf.setEquals(idEntity);
        sf.setEquals(typeEntity);
        criteria.setEntityId(lf);
        criteria.setEntityType(sf);
        log.debug("REST request to get PieceJointes by criteria: {}", criteria);
        Page<PieceJointeDTO> page = pieceJointeQueryService.findByCriteria(criteria, pageable);
        List<PieceJointeDTO> list = page.getContent();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }


    /**
     * {@code GET  /piece-jointes/count} : count all the pieceJointes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/piece-jointes/count")
    public ResponseEntity<Long> countPieceJointes(PieceJointeCriteria criteria) {
        log.debug("REST request to count PieceJointes by criteria: {}", criteria);
        return ResponseEntity.ok().body(pieceJointeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /piece-jointes/:id} : get the "id" pieceJointe.
     *
     * @param id the id of the pieceJointeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pieceJointeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/piece-jointes/{id}")
    public ResponseEntity<PieceJointeDTO> getPieceJointe(@PathVariable Long id) {
        log.debug("REST request to get PieceJointe : {}", id);
        Optional<PieceJointeDTO> pieceJointeDTO = pieceJointeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pieceJointeDTO);
    }

    /**
     * {@code DELETE  /piece-jointes/:id} : delete the "id" pieceJointe.
     *
     * @param id the id of the pieceJointeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/piece-jointes/{id}")
    public ResponseEntity<Void> deletePieceJointe(@PathVariable Long id) {
        log.debug("REST request to delete PieceJointe : {}", id);
        pieceJointeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
