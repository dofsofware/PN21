package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.DeclaredEmployer;
import com.secusociale.portail.domain.NouvelleImmatriculation;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.enumeration.TypeDocument;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.DeclaredEmployerService;
import com.secusociale.portail.service.NouvelleImmatriculationService;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.service.mapper.NouvelleImmatriculationMapper;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import com.secusociale.portail.service.NouvelleImmatriculationQueryService;

import io.github.jhipster.service.filter.InstantFilter;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.NouvelleImmatriculation}.
 */
@RestController
@RequestMapping("/api")
public class NouvelleImmatriculationResource {

    private final Logger log = LoggerFactory.getLogger(NouvelleImmatriculationResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2NouvelleImmatriculation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NouvelleImmatriculationService nouvelleImmatriculationService;

    private final DeclaredEmployerService declaredEmployerService;

    private final NouvelleImmatriculationQueryService nouvelleImmatriculationQueryService;

    private final UserRepository userRepository;

    private final NouvelleImmatriculationMapper nouvelleImmatriculationMapper;

    public NouvelleImmatriculationResource(NouvelleImmatriculationService nouvelleImmatriculationService, DeclaredEmployerService declaredEmployerService, NouvelleImmatriculationQueryService nouvelleImmatriculationQueryService, UserRepository userRepository, NouvelleImmatriculationMapper nouvelleImmatriculationMapper) {
        this.nouvelleImmatriculationService = nouvelleImmatriculationService;
        this.declaredEmployerService = declaredEmployerService;
        this.nouvelleImmatriculationQueryService = nouvelleImmatriculationQueryService;
        this.userRepository = userRepository;
        this.nouvelleImmatriculationMapper = nouvelleImmatriculationMapper;
    }

    /**
     * {@code POST  /nouvelle-immatriculations} : Create a new nouvelleImmatriculation.
     *
     * @param nouvelleImmatriculationDTO the nouvelleImmatriculationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nouvelleImmatriculationDTO, or with status {@code 400 (Bad Request)} if the nouvelleImmatriculation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nouvelle-immatriculations")
    public Object createNouvelleImmatriculation(@Valid @RequestBody NouvelleImmatriculationDTO nouvelleImmatriculationDTO) throws URISyntaxException {
        log.debug("REST request to save NouvelleImmatriculation : {}", nouvelleImmatriculationDTO);
        ApiResponse<String> response = nouvelleImmatriculationService.validateImmatriculationDTO(nouvelleImmatriculationDTO,false);
        if(response != null){
            return response;
        }
        NouvelleImmatriculationDTO nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.save(nouvelleImmatriculationDTO);
        if(nouvelleImmatriculationDTOSaved.getId() != null){
            nouvelleImmatriculationDTOSaved = nouvelleImmatriculationService.getNumeroDossier(nouvelleImmatriculationDTOSaved);
            nouvelleImmatriculationService.sendCertificatDimmatriculation(nouvelleImmatriculationDTOSaved, TypeDocument.RECEPISSE_DE_DEPOT_IMMATRICULATION,"");
        }
        return ApiResponse.success(nouvelleImmatriculationDTOSaved);
    }

    /**
     * {@code PUT  /nouvelle-immatriculations} : Updates an existing nouvelleImmatriculation.
     *
     * @param nouvelleImmatriculationDTO the nouvelleImmatriculationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nouvelleImmatriculationDTO,
     * or with status {@code 400 (Bad Request)} if the nouvelleImmatriculationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nouvelleImmatriculationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nouvelle-immatriculations")
    public ResponseEntity<NouvelleImmatriculationDTO> updateNouvelleImmatriculation(@Valid @RequestBody NouvelleImmatriculationDTO nouvelleImmatriculationDTO) throws URISyntaxException {
        log.debug("REST request to update NouvelleImmatriculation : {}", nouvelleImmatriculationDTO);
        if (nouvelleImmatriculationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NouvelleImmatriculationDTO result = nouvelleImmatriculationService.save(nouvelleImmatriculationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nouvelleImmatriculationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nouvelle-immatriculations} : get all the nouvelleImmatriculations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nouvelleImmatriculations in body.
     */
    @GetMapping("/nouvelle-immatriculations")
    public ResponseEntity<List<NouvelleImmatriculationDTO>> getAllNouvelleImmatriculations(NouvelleImmatriculationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NouvelleImmatriculations by criteria: {}", criteria);
        Page<NouvelleImmatriculationDTO> page = nouvelleImmatriculationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nouvelle-immatriculations/count} : count all the nouvelleImmatriculations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/nouvelle-immatriculations/count")
    public ResponseEntity<Long> countNouvelleImmatriculations(NouvelleImmatriculationCriteria criteria) {
        log.debug("REST request to count NouvelleImmatriculations by criteria: {}", criteria);
        return ResponseEntity.ok().body(nouvelleImmatriculationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nouvelle-immatriculations/:id} : get the "id" nouvelleImmatriculation.
     *
     * @param id the id of the nouvelleImmatriculationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nouvelleImmatriculationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nouvelle-immatriculations/{id}")
    public ResponseEntity<NouvelleImmatriculationDTO> getNouvelleImmatriculation(@PathVariable Long id) {
        log.debug("REST request to get NouvelleImmatriculation : {}", id);
        Optional<NouvelleImmatriculationDTO> nouvelleImmatriculationDTO = nouvelleImmatriculationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nouvelleImmatriculationDTO);
    }

    /**
     * {@code DELETE  /nouvelle-immatriculations/:id} : delete the "id" nouvelleImmatriculation.
     *
     * @param id the id of the nouvelleImmatriculationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nouvelle-immatriculations/{id}")
    public ResponseEntity<Void> deleteNouvelleImmatriculation(@PathVariable Long id) {
        log.debug("REST request to delete NouvelleImmatriculation : {}", id);
        nouvelleImmatriculationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/nouvelle-immatriculations-agent")
   // @PreAuthorize("hasRole(\"" + AuthoritiesConstants.AGENT + "\")")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.SUPER_ADMIN + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllNouvelleImmatriculations(@PathParam("query") String query, NouvelleImmatriculationCriteria criteria, Pageable pageable) throws ParseException {
        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();
        InstantFilter ifl = new InstantFilter();
        if (!isEmpty(query)) {
            ifl.setGreaterThanOrEqual(new SimpleDateFormat("yyyy-MM-dd").parse(query).toInstant());
            ifl.setLessThan(new SimpleDateFormat("yyyy-MM-dd").parse(query).toInstant().plus(1, ChronoUnit.DAYS));
            criteria.setCreatedAt(ifl);
        }
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            if ( isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                User user = userRepository.findByLogin(username);
                StringFilter sf = new StringFilter();
                StringFilter queryFilter = new StringFilter();
                LongFilter lf = new LongFilter();
                if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                    sf.setEquals("NO_AGENCE_FILTER");
                }

                if (!isEmpty(user.getAgence())) {
                    if (!isEmpty(user.getInstitution())) {
                        if (user.getInstitution().equalsIgnoreCase("CSS")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setContains(user.getAgence());
                                criteria.setAgenceCss(sf);
                            }
                        }
                        if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                            if (!isEmpty(user.getAgence())) {
                                sf.setEquals(user.getAgence());
                                criteria.setAgenceIpres(sf);
                            }
                        }
                    } else {
                        criteria.setAgenceIpres(sf);
                    }
                } else {
                    criteria.setAgenceIpres(sf);
                }
//                String currentStatus = criteria.getStatus().getEquals();
//                if ((currentStatus != null && !currentStatus.equalsIgnoreCase("INEXISTANT"))) {
//                    if (!currentStatus.equalsIgnoreCase("DOUBLON"))
//
//                }
            }
            log.debug("REST request to get NouvelleImmatriculation by criteria: {}", criteria);
            Page<NouvelleImmatriculationDTO> page = nouvelleImmatriculationQueryService.findByCriteria(criteria, pageable);
            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            List<NouvelleImmatriculationDTO> list = page.getContent();
            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);
            return ResponseEntity.ok().headers(headers).body(result);
        } else {
            return null;
        }
    }

    @PutMapping("/check-salarie-file")
    public Object checkSalarieFile(@RequestBody FileUploaded fileUploaded) {
        ApiResponse<Object> duplicateCheck = nouvelleImmatriculationService.checkSalarieDuplicates(fileUploaded);
        if (duplicateCheck != null) {
            return duplicateCheck;
        }

        return ApiResponse.success("Aucune duplication trouvée.");
    }

    @GetMapping("/new-immatriculations")
    public ResponseEntity<HashMap<String, Object>> getAllNewImmatriculations(
        @RequestParam(required = false, name = "query") String query,
        @RequestParam(required = false, name = "search") String search,
        NouvelleImmatriculationCriteria criteria,
        Pageable pageable,
        @RequestParam(value = "statut", defaultValue = "", required = false) String statut) throws ParseException {

        HashMap<String, Object> result = new HashMap<>();
        Optional<String> loggedusername = getCurrentUserLogin();

        // Filtre par statut
        if (!StringUtils.isEmpty(statut)) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setEquals(statut.toUpperCase(Locale.ROOT));
            criteria.setStatusdocp(stringFilter);
        }

        // Gérer la date avec le paramètre query
        if (!isEmpty(query)) {
            InstantFilter ifl = new InstantFilter();
            Instant queryInstant = new SimpleDateFormat("yyyy-MM-dd").parse(query).toInstant();
            ifl.setGreaterThanOrEqual(queryInstant);
            ifl.setLessThan(queryInstant.plus(1, ChronoUnit.DAYS));
            criteria.setCreatedAt(ifl);
        }

        if (!isEmpty(search)) {
            StringFilter globalSearchFilter = new StringFilter();
            globalSearchFilter.setContains(search.toUpperCase(Locale.ROOT));
            criteria.setGlobalSearch(globalSearchFilter);
        }

        // Si l'utilisateur est connecté, appliquer des filtres dynamiques selon son rôle
        if (loggedusername.isPresent()) {
            String username = loggedusername.get();
            User user = userRepository.findByLogin(username);

            // Initialisation des filtres
            StringFilter agenceFilter = new StringFilter();
            StringFilter statusFilter = new StringFilter();

            // Filtre spécifique pour certains utilisateurs
            if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                agenceFilter.setEquals("NO_AGENCE_FILTER");
            } else {
                // Si l'utilisateur a une agence
                if (!isEmpty(user.getInstitution())) {
                    if (user.getInstitution().equalsIgnoreCase("CSS")) {
                        agenceFilter.setEquals(user.getAgence());
                        criteria.setAgenceCss(agenceFilter);
                        if (isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                            System.out.println("L'utilisateur a le rôle CHEF_AGENCE_CSS");
                            statusFilter.setEquals("VALIDATION_CHEF_AGENCE_CSS");
                            criteria.setStatusdocp(statusFilter);
                        }
                    } else if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                        agenceFilter.setEquals(user.getAgence());
                        criteria.setAgenceIpres(agenceFilter);
                        if (isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
                            statusFilter.setEquals("VALIDATION_CHEF_AGENCE_IPRES");
                            criteria.setStatusdocp(statusFilter);
                        }
                    }
                }
            }
            if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR)) {
                statusFilter.setEquals("VALIDATION_GESTIONNAIRE_DE_COMPTE");
                criteria.setStatusdocp(statusFilter);
            }

            log.debug("REST request to get NouvelleImmatriculation by criteria: {}", criteria);
            Page<NouvelleImmatriculationDTO> page = nouvelleImmatriculationQueryService.findByCriteria(criteria, pageable);

            HashMap<String, Object> pagination = new HashMap<>();
            pagination.put("page", page.getNumber());
            pagination.put("size", page.getSize());
            pagination.put("totalElements", page.getTotalElements());

            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);

            List<NouvelleImmatriculationDTO> list = page.getContent();

            result.put("code", "200");
            result.put("list", list);
            result.put("pagination", pagination);

            return ResponseEntity.ok().headers(headers).body(result);
        } else {
            // Cas où l'utilisateur n'est pas connecté
            System.out.println("Aucun utilisateur connecté.");
            result.put("code", "401");
            result.put("message", "Utilisateur non autorisé. Veuillez vous connecter.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }


    @PutMapping("/nouvelle-immatriculations/validation")
    public Object validateNouvelleImmatriculation(@RequestBody ValidationImmatriculationDTO validationDTO) {
        Object reponse = nouvelleImmatriculationService.validateImmatriculation(validationDTO);
        NouvelleImmatriculationDTO nouvelleImmatriculation = reponse instanceof NouvelleImmatriculationDTO ?
            ((NouvelleImmatriculationDTO) reponse) : null;
        if (nouvelleImmatriculation == null) {
            return reponse;
        }
        NouvelleImmatriculationDTO result = nouvelleImmatriculationService.save(nouvelleImmatriculation);
        return ApiResponse.success(result);
    }

    @GetMapping("/nouvelle-immatriculations/succursales/{numeroUnique}")
    public ApiResponse<Object> getBranchOffices(@PathVariable String numeroUnique) {
        boolean isHeadquarters = nouvelleImmatriculationService.isHeadquarters(numeroUnique);
        if (!isHeadquarters){
            return ApiResponse.error400("L'employeur avec le numéro Unique : "+numeroUnique+" n'est pas un siège");
        }
        List<NouvelleImmatriculation> result = nouvelleImmatriculationService.findBranchOffices(numeroUnique);
        List<NouvelleImmatriculationDTO> resultDTO = nouvelleImmatriculationMapper.toDto(result);
        NouvelleImmatriculationDTO siege = nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationService.findByNumeroUnique(numeroUnique).orElse(null));
        Map<String, Object> rep = new HashMap<>();
        rep.put("nbSuccursale",resultDTO.size());
        rep.put("succursales",resultDTO);
        rep.put("siege",siege);
        return ApiResponse.success(rep);
    }

    @GetMapping("/nouvelle-immatriculations/siege/{numeroUnique}")
    public ApiResponse<Object> isHeadquarters(@PathVariable String numeroUnique) {
        boolean isHeadquarters = nouvelleImmatriculationService.isHeadquarters(numeroUnique);
        return ApiResponse.success(isHeadquarters);
    }

}
