package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.model.DmtModel;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.DMTQueryService;
import com.secusociale.portail.service.DMTService;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.dto.DMTCriteria;
import com.secusociale.portail.service.dto.DMTDTO;
import com.secusociale.portail.service.immatriculation.DmtService;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.DMT}.
 */
@RestController
@RequestMapping("/api")
public class DMTResource {

    private final Logger log = LoggerFactory.getLogger(DMTResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2Dmt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DMTService dMTService;

    @Autowired
    private DmtService dmtService;

    private final DMTQueryService dMTQueryService;

    private final UserRepository userRepository;

    @Autowired
    private DocumentUrlService documentUrlService;

    public DMTResource(DMTService dMTService, DMTQueryService dMTQueryService, UserRepository userRepository) {
        this.dMTService = dMTService;
        this.dMTQueryService = dMTQueryService;
        this.userRepository = userRepository;
    }

    /**
     * {@code POST  /dmts} : Create a new dMT.
     * <p>
     * //     * @param dMTDTO the dMTDTO to create.
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dMTDTO, or with status {@code 400 (Bad Request)} if the dMT has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PostMapping("/dmts")
//    public ResponseEntity<DMTDTO> createDMT(@RequestBody DMTDTO dMTDTO) throws URISyntaxException {
//        log.debug("REST request to save DMT : {}", dMTDTO);
//        if (dMTDTO.getId() != null) {
//            throw new BadRequestAlertException("A new dMT cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        DMTDTO result = dMTService.save(dMTDTO);
//        return ResponseEntity.created(new URI("/api/dmts/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
    //
//    }
    @PostMapping("/dmts")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HashMap<String, Object>> sendDMT(@RequestBody DmtModel dmtModel) {
        HashMap<String, Object> result = new HashMap<>();
        DMTDTO dmtlocal = new DMTDTO();
        try {
            for (DmtModel.Employes employe : dmtModel.getEmployes()) {
                if (isEmpty(employe.getTypePiece())) {
                    result.put("code", "400");
                    result.put("erreur", "Vérifier que le type de pièce est renseigné pour tous les employés!");
                    return ResponseEntity.ok(result);
                }
            }
            Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
            String username = loggedusername.orElse(null);
            Long userId = userRepository.findByLogin(username).getId();
            HashMap<String, String> extensions = new HashMap<>();
            extensions.put("vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
            extensions.put("msexcel", ".xls");
            String uuid = RandomStringUtils.randomAlphanumeric(7);
            String extension = isEmpty(dmtModel.getFile().split(";")[0].split("/")[1]) ? ".xlsx" : extensions.get(dmtModel.getFile().split(";")[0].split("/")[1]);
            String fileName = "DMT_" + dmtModel.getIdEmployeur() + "_" + uuid + extension;
            MultipartFile multipartFile = Objects.requireNonNull(base64ToMultipart(dmtModel.getFile(), "listeSalaries"));

            String path = documentUrlService.uploadedExcel(multipartFile, fileName);
            ObjectMapper om = new ObjectMapper();

            dmtlocal.idEmployeur(dmtModel.getIdEmployeur())
                .date(Instant.now())
                .employesList(om.writeValueAsString(dmtModel.getEmployes()))
                .status("PENDING")
                .raisonSocial(dmtModel.getRaisonSocial())
                .file(path)
                .userId(userId);
            dmtlocal = dMTService.save(dmtlocal);
//            CmDmt dmt = dmtService.dmt(dmtModel);
//            if (dmt != null) {
//                dmtlocal.reponsebrute((new ObjectMapper()).writeValueAsString(dmt))
//                    .status("SUCCESS");
//                dMTService.save(dmtlocal);
//            } else {
//                dmtlocal.status("FAILED");
//                dMTService.save(dmtlocal);
//            }
            result.put("code", "200");
            result.put("data", dmtlocal);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            dmtlocal.status("FAILED");
            dMTService.save(dmtlocal);
            log.error(exception.getMessage(), exception);
            result.put("code", "400");
            result.put("erreur", exception.getMessage());
            return ResponseEntity.ok(result);
        }
    }

    /**
     * {@code PUT  /dmts} : Updates an existing dMT.
     *
     * @param dMTDTO the dMTDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dMTDTO,
     * or with status {@code 400 (Bad Request)} if the dMTDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dMTDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/dmts")
//    public ResponseEntity<DMTDTO> updateDMT(@RequestBody DMTDTO dMTDTO) throws URISyntaxException {
//        log.debug("REST request to update DMT : {}", dMTDTO);
//        if (dMTDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        DMTDTO result = dMTService.save(dMTDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dMTDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * {@code GET  /dmts} : get all the dMTS.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dMTS in body.
     */
    @GetMapping("/dmts")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllDMTS(DMTCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DMTS by criteria: {}", criteria);
        Page<DMTDTO> page = dMTQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        HashMap<String, Object> pagination = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    /**
     * {@code GET  /dmts} : get all the dMTS.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dMTS in body.
     */
    @GetMapping("/dmts/mines")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllMyDMTS(DMTCriteria criteria, Pageable pageable, @RequestParam(name = "statut", required = false, defaultValue = "") String statut) {
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
        String username = loggedusername.orElse(null);
        Long userId = userRepository.findByLogin(username).getId();
        LongFilter userFilter = new LongFilter();
        userFilter.setEquals(userId);
        criteria.setUserId(userFilter);
        StringFilter stringFilter = new StringFilter();
        stringFilter.setContains(statut);
//        criteria.setStatus(stringFilter);
        log.debug("REST request to get DMTS by criteria: {}", criteria);
        Page<DMTDTO> page = dMTQueryService.findByCriteria(criteria, pageable);
        HashMap<String, Object> pagination = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", page.getContent());
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    /**
     * {@code GET  /dmts/count} : count all the dMTS.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dmts/count")
    public ResponseEntity<Long> countDMTS(DMTCriteria criteria) {
        log.debug("REST request to count DMTS by criteria: {}", criteria);
        return ResponseEntity.ok().body(dMTQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmts/:id} : get the "id" dMT.
     *
     * @param id the id of the dMTDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dMTDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmts/{id}")
    public ResponseEntity<DMTDTO> getDMT(@PathVariable Long id) {
        log.debug("REST request to get DMT : {}", id);
        Optional<DMTDTO> dMTDTO = dMTService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dMTDTO);
    }

    /**
     * {@code DELETE  /dmts/:id} : delete the "id" dMT.
     *
     * @param id the id of the dMTDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmts/{id}")
    public ResponseEntity<Void> deleteDMT(@PathVariable Long id) {
        log.debug("REST request to delete DMT : {}", id);
        dMTService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
