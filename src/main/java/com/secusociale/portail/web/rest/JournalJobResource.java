package com.secusociale.portail.web.rest;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.DeclarationJournalJob;
import com.secusociale.portail.domain.JournalJob;
import com.secusociale.portail.service.JournalJobQueryService;
import com.secusociale.portail.service.JournalJobService;
import com.secusociale.portail.service.SuiviJobQueryService;
import com.secusociale.portail.service.dto.CarriereDTO;
import com.secusociale.portail.service.dto.JournalJobCriteria;
import com.secusociale.portail.service.dto.JournalJobDTO;
import com.secusociale.portail.service.dto.SuiviJobDTO;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.SuiviJob}.
 */
@RestController
@RequestMapping("/api")
public class JournalJobResource {

    private final Logger log = LoggerFactory.getLogger(JournalJobResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2JournalJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JournalJobService journalJobService;

    private final JournalJobQueryService journalJobQueryService;

    public JournalJobResource(JournalJobService journalJobService, JournalJobQueryService journalJobQueryService) {
        this.journalJobService = journalJobService;
        this.journalJobQueryService = journalJobQueryService;
    }


    /**
     * {@code GET  /suivi-jobs} : get all the suiviJobs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suiviJobs in body.
     */
    @GetMapping("/journal-jobs")
    public ResponseEntity<HashMap<String, Object>> getAllJournalJobs(JournalJobCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SuiviJobs by criteria: {}", criteria);
        if (!pageable.getSort().isSorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "demarreLe"));
        }

        Page<JournalJob> page = journalJobQueryService.findByCriteria(criteria, pageable);
        List<JournalJob> list = page.getContent();
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

    @GetMapping("/journal-jobs/{id}")
    public ResponseEntity<JournalJobDTO> getJournalJob(@PathVariable Long id) {
        log.debug("REST request to get JournalJob : {}", id);
        Optional<JournalJobDTO> journalJobDTO = journalJobService.findOne(id);

        // Récupérer les informations de la déclaration associée via la table de liaison DeclarationJournalJob
        if (journalJobDTO.isPresent()) {
            List<DeclarationJournalJob> declarations = journalJobService.findDeclarationsByJournalJobId(id);
            journalJobDTO.get().setDeclarations(declarations);
            return ResponseEntity.ok(journalJobDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
