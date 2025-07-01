package com.secusociale.portail.web.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import com.secusociale.portail.domain.DeclaredEmployer;
import com.secusociale.portail.domain.enumeration.SearchType;
import com.secusociale.portail.repository.DeclaredEmployerRepository;
import com.secusociale.portail.service.DeclaredEmployerService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.DeclaredEmployerDTO;
import com.secusociale.portail.service.dto.ResponseData;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/declared-employers")
@Transactional
public class DeclaredEmployerResource {

    private static final Logger LOG = LoggerFactory.getLogger(DeclaredEmployerResource.class);

    private static final String ENTITY_NAME = "jhipsterDeclaredEmployer";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeclaredEmployerRepository declaredEmployerRepository;
    private final DeclaredEmployerService declaredEmployerService;

    public DeclaredEmployerResource(DeclaredEmployerRepository declaredEmployerRepository,DeclaredEmployerService declaredEmployerService) {
        this.declaredEmployerRepository = declaredEmployerRepository;
        this.declaredEmployerService = declaredEmployerService;
    }

    /**
     * {@code POST  /declared-employers} : Create a new declaredEmployer.
     *
     * @param declaredEmployer the declaredEmployer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new declaredEmployer, or with status {@code 400 (Bad Request)} if the declaredEmployer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DeclaredEmployer> createDeclaredEmployer(@Valid @RequestBody DeclaredEmployer declaredEmployer)
        throws URISyntaxException {
        LOG.debug("REST request to save DeclaredEmployer : {}", declaredEmployer);
        if (declaredEmployer.getId() != null) {
            throw new BadRequestAlertException("A new declaredEmployer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        declaredEmployer = declaredEmployerRepository.save(declaredEmployer);
        return ResponseEntity.created(new URI("/api/declared-employers/" + declaredEmployer.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, declaredEmployer.getId().toString()))
            .body(declaredEmployer);
    }

    /**
     * {@code PUT  /declared-employers/:id} : Updates an existing declaredEmployer.
     *
     * @param id the id of the declaredEmployer to save.
     * @param declaredEmployer the declaredEmployer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated declaredEmployer,
     * or with status {@code 400 (Bad Request)} if the declaredEmployer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the declaredEmployer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeclaredEmployer> updateDeclaredEmployer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DeclaredEmployer declaredEmployer
    ) throws URISyntaxException {
        LOG.debug("REST request to update DeclaredEmployer : {}, {}", id, declaredEmployer);
        if (declaredEmployer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, declaredEmployer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!declaredEmployerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        declaredEmployer = declaredEmployerRepository.save(declaredEmployer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, declaredEmployer.getId().toString()))
            .body(declaredEmployer);
    }

    /**
     * {@code PATCH  /declared-employers/:id} : Partial updates given fields of an existing declaredEmployer, field will ignore if it is null
     *
     * @param id the id of the declaredEmployer to save.
     * @param declaredEmployer the declaredEmployer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated declaredEmployer,
     * or with status {@code 400 (Bad Request)} if the declaredEmployer is not valid,
     * or with status {@code 404 (Not Found)} if the declaredEmployer is not found,
     * or with status {@code 500 (Internal Server Error)} if the declaredEmployer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeclaredEmployer> partialUpdateDeclaredEmployer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DeclaredEmployer declaredEmployer
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DeclaredEmployer partially : {}, {}", id, declaredEmployer);
        if (declaredEmployer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, declaredEmployer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!declaredEmployerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeclaredEmployer> result = declaredEmployerRepository
            .findById(declaredEmployer.getId())
            .map(existingDeclaredEmployer -> {
                if (declaredEmployer.getNinea() != null) {
                    existingDeclaredEmployer.setNinea(declaredEmployer.getNinea());
                }
                if (declaredEmployer.getRaisonSociale() != null) {
                    existingDeclaredEmployer.setRaisonSociale(declaredEmployer.getRaisonSociale());
                }
                if (declaredEmployer.getNumeroUnique() != null) {
                    existingDeclaredEmployer.setNumeroUnique(declaredEmployer.getNumeroUnique());
                }
                if (declaredEmployer.getAncienNumIpres() != null) {
                    existingDeclaredEmployer.setAncienNumIpres(declaredEmployer.getAncienNumIpres());
                }
                if (declaredEmployer.getAncienNumCss() != null) {
                    existingDeclaredEmployer.setAncienNumCss(declaredEmployer.getAncienNumCss());
                }
                if (declaredEmployer.getAdresse() != null) {
                    existingDeclaredEmployer.setAdresse(declaredEmployer.getAdresse());
                }
                if (declaredEmployer.getRegion() != null) {
                    existingDeclaredEmployer.setRegion(declaredEmployer.getRegion());
                }
                if (declaredEmployer.getTelephone() != null) {
                    existingDeclaredEmployer.setTelephone(declaredEmployer.getTelephone());
                }
                if (declaredEmployer.getEffectif() != null) {
                    existingDeclaredEmployer.setEffectif(declaredEmployer.getEffectif());
                }
                if (declaredEmployer.getStatutJuridique() != null) {
                    existingDeclaredEmployer.setStatutJuridique(declaredEmployer.getStatutJuridique());
                }
                if (declaredEmployer.getSecteurActivite() != null) {
                    existingDeclaredEmployer.setSecteurActivite(declaredEmployer.getSecteurActivite());
                }
                if (declaredEmployer.getSigle() != null) {
                    existingDeclaredEmployer.setSigle(declaredEmployer.getSigle());
                }
                if (declaredEmployer.getRccm() != null) {
                    existingDeclaredEmployer.setRccm(declaredEmployer.getRccm());
                }
                if (declaredEmployer.getAgenceIpres() != null) {
                    existingDeclaredEmployer.setAgenceIpres(declaredEmployer.getAgenceIpres());
                }
                if (declaredEmployer.getAgenceCss() != null) {
                    existingDeclaredEmployer.setAgenceCss(declaredEmployer.getAgenceCss());
                }

                return existingDeclaredEmployer;
            })
            .map(declaredEmployerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, declaredEmployer.getId().toString())
        );
    }

    /**
     * {@code GET  /declared-employers} : get all the declaredEmployers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of declaredEmployers in body.
     */
    @GetMapping("")
    public List<DeclaredEmployer> getAllDeclaredEmployers() {
        LOG.debug("REST request to get all DeclaredEmployers");
        return declaredEmployerRepository.findAll();
    }

    /**
     * {@code GET  /declared-employers/:id} : get the "id" declaredEmployer.
     *
     * @param id the id of the declaredEmployer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the declaredEmployer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeclaredEmployer> getDeclaredEmployer(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DeclaredEmployer : {}", id);
        Optional<DeclaredEmployer> declaredEmployer = declaredEmployerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(declaredEmployer);
    }

    /**
     * {@code DELETE  /declared-employers/:id} : delete the "id" declaredEmployer.
     *
     * @param id the id of the declaredEmployer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeclaredEmployer(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DeclaredEmployer : {}", id);
        declaredEmployerRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/import-from-file")
    public ApiResponse<HashMap<String, Object>> importDeclaredEmployer(@RequestBody Map<String, String> payload) throws IOException {
        String base64File = payload.get("base64File");
        if (base64File == null || base64File.trim().isEmpty()) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error" , "Le champ 'base64File' est obligatoire.");
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.BAD_REQUEST,response);
        }
        HashMap<String, Object> result = declaredEmployerService.importDeclaredEmployer(base64File);
        if(result != null){
            log.error("API : import-from-file , Error : {}", result);
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.INTERNAL_ERROR,result);
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("message" , "Fichier importé avec succès !");
        log.info("API : import-from-file , Reponse : {}", response);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK,response);
    }

    @GetMapping("/does-employer-exist")
    public ApiResponse<Object> doesEmployerExist(
        @RequestParam String keyWanted,
        @RequestParam String typeOfSearch
    ) {
        if (keyWanted.trim().isEmpty()) {
            return ApiResponse.errorResponse(
                ApiResponse.ResponseCode.BAD_REQUEST,
                "Le champ 'keyWanted' est obligatoire."
            );
        }
        if (typeOfSearch.trim().isEmpty()) {
            return ApiResponse.errorResponse(
                ApiResponse.ResponseCode.BAD_REQUEST,
                "Le champ 'typeOfSearch' est obligatoire."
            );
        }
//        keyWanted = keyWanted.replaceFirst("^0+", "");

        SearchType typeSearch = null;
        try {
            typeSearch = SearchType.valueOf(typeOfSearch.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Statut invalide. Les valeurs possibles sont: " + Arrays.toString(SearchType.values())
            );
        }
        Optional<DeclaredEmployer> employer = declaredEmployerService.findEmployerByCriteria(keyWanted, typeSearch);
        if (employer.isPresent()) {
            return ApiResponse.success(employer.get());
        } else {
            return ApiResponse.error404("Aucun employeur trouvé avec ces critères [keyWanted : " + keyWanted + ", typeOfSearch : " + typeSearch + "]");
        }

    }

    @GetMapping("/find-by-raison-rociale")
    public ApiResponse<?> findByRaisonSociale(
        @RequestParam String raisonSocial
    ) {
        if (raisonSocial == null || raisonSocial.trim().isEmpty()) {
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.BAD_REQUEST,"Le champ 'raisonSocial' est obligatoire.");
        }
        List<DeclaredEmployer> employers = declaredEmployerService.findByRaisonSociale(raisonSocial);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK,employers);
    }


    @GetMapping("/succursales/{numeroUnique}")
    public ResponseEntity<Map<String, Object>> getEmployerByNumeroUniqueAndTypeEtablissement(@PathVariable String numeroUnique,
                                                                                             @RequestParam(defaultValue = "0") int page,
                                                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DeclaredEmployerDTO> declarationsPage = declaredEmployerService.getEmployersByNumeroUnique(numeroUnique, pageable);
        List<DeclaredEmployerDTO> employers = declarationsPage.getContent();

        if (employers.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", "404");
            result.put("message", "Aucune succursale trouvée pour le numéro unique : " + numeroUnique);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        String[] uniqueNumbers = employers.stream()
            .map(DeclaredEmployerDTO::getNumeroUnique)
            .distinct()
            .toArray(String[]::new);

        String[] raisonSociale = employers.stream()
            .map(DeclaredEmployerDTO::getRaisonSociale)
            .distinct()
            .toArray(String[]::new);

        String[] ancienNumIpres = employers.stream()
            .map(DeclaredEmployerDTO::getAncienNumIpres)
            .distinct()
            .toArray(String[]::new);

        String[] ancienNumCss = employers.stream()
            .map(DeclaredEmployerDTO::getAncienNumCss)
            .distinct()
            .toArray(String[]::new);

        String[] adresse = employers.stream()
            .map(DeclaredEmployerDTO::getAdresse)
            .distinct()
            .toArray(String[]::new);

        ResponseData responseData = new ResponseData();
        responseData.setZone("CM_GET_SECS");
        responseData.setPersonId("0007246714");
        responseData.setRowCount(employers.size());
        responseData.setResults(employers);
        responseData.setDateTimeTagFormat("xsd:strict");
        responseData.setUniqueNumbers(uniqueNumbers);
        responseData.setRaisonSociale(raisonSociale);
        responseData.setAncienNumIpres(ancienNumIpres);
        responseData.setAncienNumCss(ancienNumCss);
        responseData.setAncienNumCss(adresse);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("page", declarationsPage.getNumber());
        pagination.put("size", declarationsPage.getSize());
        pagination.put("totalElements", declarationsPage.getTotalElements());

        result.put("code", "200");
        result.put("value", responseData);
        result.put("pagination", pagination);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), declarationsPage);

        return ResponseEntity.ok().headers(headers).body(result);
    }

    @PostMapping("/import-declared-employer-from-csv")
    public ApiResponse<String> importDeclaredEmployerFromCSV(@RequestParam("file") MultipartFile file) {
        try {
            declaredEmployerService.importDeclaredEmployerFromCSV(file);
            return ApiResponse.success("DeclaredEmployers importés avec succès !");
        } catch (Exception e) {
            return ApiResponse.error500("Une erreur est survenue lors de l'importation des DeclaredEmployer");
        }
    }

}
