package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.DeclarationSituationPersonnelle;
import com.secusociale.portail.domain.GrappeMember;
import com.secusociale.portail.domain.PieceJointe;
import com.secusociale.portail.domain.enumeration.StatutDeclarationSituationPersonnelle;
import com.secusociale.portail.domain.enumeration.TypeDeclarationSituationPersonnelle;
import com.secusociale.portail.repository.DeclarationSituationPersonnelleRepository;
import com.secusociale.portail.repository.GrappeMemberRepository;
import com.secusociale.portail.repository.PieceJointeRepository;
import com.secusociale.portail.service.DeclarationSituationPersonnelleService;
import com.secusociale.portail.service.GrappeMemberService;
import com.secusociale.portail.service.dto.DeclarationSituationPersonnelleDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/declarations-situation-personnelle")
public class DeclarationSituationPersonnelleResource {

    private static final Logger LOG = LoggerFactory.getLogger(DeclarationSituationPersonnelleResource.class);

    private static final String ENTITY_NAME = "jhipsterDeclaration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeclarationSituationPersonnelleService declarationSituationPersonnelleService;

    private final DeclarationSituationPersonnelleRepository declarationSituationPersonnelleRepository;

    private final GrappeMemberRepository grappeMemberRepository;

    private final PieceJointeRepository pieceJointeRepository;

    public DeclarationSituationPersonnelleResource(PieceJointeRepository pieceJointeRepository, GrappeMemberRepository grappeMemberRepository, DeclarationSituationPersonnelleService declarationSituationPersonnelleService, DeclarationSituationPersonnelleRepository declarationSituationPersonnelleRepository) {
        this.declarationSituationPersonnelleService = declarationSituationPersonnelleService;
        this.declarationSituationPersonnelleRepository = declarationSituationPersonnelleRepository;
        this.grappeMemberRepository = grappeMemberRepository;
        this.pieceJointeRepository = pieceJointeRepository;
    }


    @PostMapping("")
    public ResponseEntity<DeclarationSituationPersonnelleDTO> createDeclaration(@RequestBody DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO) throws URISyntaxException {
        LOG.debug("REST request to save Declaration : {}", declarationSituationPersonnelleDTO);
        declarationSituationPersonnelleDTO.setStatut(StatutDeclarationSituationPersonnelle.SAISIE);
        declarationSituationPersonnelleDTO.setDateValidate(null);
        declarationSituationPersonnelleDTO.setUserIdValidate(null);
        declarationSituationPersonnelleDTO.setMotif(null);
        declarationSituationPersonnelleDTO.setDateSoumission(null);
        if (declarationSituationPersonnelleDTO.getId() != null) {
            throw new BadRequestAlertException("A new declaration cannot already have an ID", ENTITY_NAME, "idexists");
        }

        declarationSituationPersonnelleDTO = declarationSituationPersonnelleService.save(declarationSituationPersonnelleDTO);
        return ResponseEntity.created(new URI("/api/declarations/" + declarationSituationPersonnelleDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, declarationSituationPersonnelleDTO.getId().toString()))
            .body(declarationSituationPersonnelleDTO);
    }


    @GetMapping("/get-by-type-and-iduser")
    public ResponseEntity<List<DeclarationSituationPersonnelleDTO>> getDeclarationsByTypeAndUserId(
        @RequestParam("typeDeclaration") String typeDeclarationStr,
        @RequestParam("userId") Long userId) {

        LOG.debug("REST request to get Declarations by typeDeclaration: {} and userId: {}", typeDeclarationStr, userId);

        TypeDeclarationSituationPersonnelle typeDeclaration;
        try {
            typeDeclaration = TypeDeclarationSituationPersonnelle.valueOf(typeDeclarationStr);
        } catch (IllegalArgumentException e) {
            throw new BadRequestAlertException("Invalid typeDeclaration", ENTITY_NAME, "typenotfound");
        }

        List<DeclarationSituationPersonnelleDTO> declarations =
            declarationSituationPersonnelleService.findByTypeDeclarationSituationPersonnelleAndUserId(typeDeclaration, userId);

        return ResponseEntity.ok(declarations);
    }

    @PutMapping("/update")
    public ResponseEntity<DeclarationSituationPersonnelleDTO> updateDeclaration(
        @RequestBody DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO
    ) {
        Long id = declarationSituationPersonnelleDTO.getId();
        if (declarationSituationPersonnelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LOG.debug("REST request to update Declaration : {}, {}", id, declarationSituationPersonnelleDTO);
        if (!Objects.equals(id, declarationSituationPersonnelleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!declarationSituationPersonnelleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        declarationSituationPersonnelleService.updateStatusGrappeAndCheckMotif(declarationSituationPersonnelleDTO);

        declarationSituationPersonnelleDTO = declarationSituationPersonnelleService.update(declarationSituationPersonnelleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, declarationSituationPersonnelleDTO.getId().toString()))
            .body(declarationSituationPersonnelleDTO);
    }



    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeclarationSituationPersonnelleDTO> partialUpdateDeclaration(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Declaration partially : {}, {}", id, declarationSituationPersonnelleDTO);
        if (declarationSituationPersonnelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, declarationSituationPersonnelleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!declarationSituationPersonnelleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeclarationSituationPersonnelleDTO> result = declarationSituationPersonnelleService.partialUpdate(declarationSituationPersonnelleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, declarationSituationPersonnelleDTO.getId().toString())
        );
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllDeclarations(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        LOG.debug("REST request to get all Declarations with pagination: page={}, size={}", page, size);

        Page<DeclarationSituationPersonnelleDTO> declarationsPage = declarationSituationPersonnelleService.findAll(PageRequest.of(page, size));
        List<DeclarationSituationPersonnelleDTO> declarations = declarationsPage.getContent();

        // Préparer la réponse
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", declarationsPage.getNumber());
        pagination.put("size", declarationsPage.getSize());
        pagination.put("totalElements", declarationsPage.getTotalElements());

        result.put("code", "200");
        result.put("list", declarations);
        result.put("pagination", pagination);

        // Créer les en-têtes de pagination
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), declarationsPage);

        return ResponseEntity.ok().headers(headers).body(result);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DeclarationSituationPersonnelleDTO> getDeclaration(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Declaration : {}", id);
        Optional<DeclarationSituationPersonnelleDTO> declarationDTO = declarationSituationPersonnelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(declarationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeclaration(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Declaration : {}", id);
        declarationSituationPersonnelleService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/salarie")
    public ResponseEntity<Map<String, Object>> getAllDeclarations(
            @RequestParam(required = false, name = "salarieNum") Long salarieNum,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        LOG.debug("REST request to get all Declarations with pagination: page={}, size={}", page, size);

        Pageable pageable = PageRequest.of(page, size);

        // Page<DeclarationSituationPersonnelleDTO> declarationsPage = declarationSituationPersonnelleService.findAll(PageRequest.of(page, size));
        Page<DeclarationSituationPersonnelle> declarationsPage = declarationSituationPersonnelleRepository.searchById(salarieNum, pageable);
        List<DeclarationSituationPersonnelle> declarations = declarationsPage.getContent();

        // Préparer la réponse
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", declarationsPage.getNumber());
        pagination.put("size", declarationsPage.getSize());
        pagination.put("totalElements", declarationsPage.getTotalElements());

        result.put("code", "200");
        result.put("list", declarations);
        result.put("pagination", pagination);

        // Créer les en-têtes de pagination
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), declarationsPage);

        return ResponseEntity.ok().headers(headers).body(result);
    }
}
