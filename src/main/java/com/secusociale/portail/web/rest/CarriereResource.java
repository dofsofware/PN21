package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.dto.*;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Carriere}.
 */
@RestController
@RequestMapping("/api")
public class CarriereResource {

    private final Logger log = LoggerFactory.getLogger(CarriereResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2Carriere";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarriereService carriereService;
    private final SalarieService salarieService;
    private final UserRepository userRepository;
    private final UserService userService;

    private final CarriereQueryService carriereQueryService;
    private final SalarieQueryService salarieQueryService;

    public CarriereResource(CarriereService carriereService, SalarieService salarieService, UserRepository userRepository, UserService userService, CarriereQueryService carriereQueryService, SalarieQueryService salarieQueryService) {
        this.carriereService = carriereService;
        this.salarieService = salarieService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.carriereQueryService = carriereQueryService;
        this.salarieQueryService = salarieQueryService;
    }

    /**
     * {@code POST  /salaries/carrieres} : Create a new carriere.
     *
     * @param carriereDTO the carriereDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carriereDTO, or with status {@code 400 (Bad Request)} if the carriere has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/salaries/carrieres")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<CarriereDTO> createCarriere(@RequestBody CarriereDTO carriereDTO) throws URISyntaxException {
        log.debug("REST request to save Carriere : {}", carriereDTO);
        if (carriereDTO.getId() != null) {
            throw new BadRequestAlertException("A new carriere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findByLogin(username);

        Long userId = user.getId();
        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        if (!salarieDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas salarie, vous ne pouvez pas acceder a cette ressource").withTitle("Erreur").build();
        }
        SalarieDTO sdto = salarieDTO.get();
        Long salarieId = sdto.getId();
        String numeroUnique = sdto.getNumeroUnique();
        carriereDTO.setSalarieId(salarieId);
        carriereDTO.setNumeroUniqueSalarie(numeroUnique);
        carriereDTO.setCreatedAt(Instant.now());
        CarriereDTO result = carriereService.save(carriereDTO);
        return ResponseEntity.created(new URI("/api/salaries/carrieres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /salaries/carrieres} : Updates an existing carriere.
     *
     * @param carriereDTO the carriereDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carriereDTO,
     * or with status {@code 400 (Bad Request)} if the carriereDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carriereDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/salaries/carrieres")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<CarriereDTO> updateCarriere(@RequestBody CarriereDTO carriereDTO) throws URISyntaxException {
        log.debug("REST request to update Carriere : {}", carriereDTO);
        if (carriereDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findByLogin(username);

        Long userId = user.getId();
        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        if (!salarieDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas salarie, vous ne pouvez pas acceder a cette ressource").withTitle("Erreur").build();
        }
        SalarieDTO sdto = salarieDTO.get();
        Long salarieId = sdto.getId();
        String numeroUnique = sdto.getNumeroUnique();
        carriereDTO.setSalarieId(salarieId);
        carriereDTO.setNumeroUniqueSalarie(numeroUnique);
        CarriereDTO result = carriereService.save(carriereDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carriereDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /salaries/carrieres} : get all the carrieres.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carrieres in body.
     */
    @GetMapping("/salaries/carrieres")
    public ResponseEntity<HashMap<String, Object>> getAllCarrieres(CarriereCriteria criteria, Pageable pageable) {
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";

        Optional<UserDTO> optionalUserDTO = userService.getUserWithAuthoritiesByLogin(username)
            .map(UserDTO::new);
        if (!optionalUserDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        UserDTO user = optionalUserDTO.get();

        Set<String> roles = user.getAuthorities();

        if (roles.contains(AuthoritiesConstants.SALARIE)) {

            Long userId = user.getId();
            Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
            if (!salarieDTO.isPresent()) {
                throw Problem.builder().withDetail("Vous n'etes pas salarie, vous ne pouvez pas acceder a cette ressource").withTitle("Erreur").build();
            }

            Long salarieId = salarieDTO.get().getId();
            String numeroUnique = salarieDTO.get().getNumeroUnique();
            LongFilter lf = new LongFilter();
            StringFilter sf = new StringFilter();
            lf.setEquals(salarieId);
            sf.setEquals(numeroUnique);
            criteria.setSalarieId(lf);
            criteria.setNumeroUniqueSalarie(sf);
        }

        log.debug("REST request to get Carrieres by criteria: {}", criteria);
        Page<CarriereDTO> page = carriereQueryService.findByCriteria(criteria, pageable);
        List<CarriereDTO> list = page.getContent();
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
     * {@code GET  /salaries/carrieres/count} : count all the carrieres.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/salaries/carrieres/count")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<Long> countCarrieres(CarriereCriteria criteria) {
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findByLogin(username);

        Long userId = user.getId();
        Optional<SalarieDTO> salarieDTO = salarieService.findByUserId(userId);
        if (!salarieDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas salarie, vous ne pouvez pas acceder a cette ressource").withTitle("Erreur").build();
        }
        SalarieDTO sdto = salarieDTO.get();
        Long salarieId = sdto.getId();
        String numeroUnique = sdto.getNumeroUnique();
        LongFilter lf = new LongFilter();
        StringFilter sf = new StringFilter();
        lf.setEquals(salarieId);
        sf.setEquals(numeroUnique);
        criteria.setSalarieId(lf);
        criteria.setNumeroUniqueSalarie(sf);
        log.debug("REST request to count Carrieres by criteria: {}", criteria);
        return ResponseEntity.ok().body(carriereQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /salaries/carrieres/:id} : get the "id" carriere.
     *
     * @param id the id of the carriereDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carriereDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/carrieres/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<CarriereDTO> getCarriere(@PathVariable Long id) {
        log.debug("REST request to get Carriere : {}", id);
        Optional<CarriereDTO> carriereDTO = carriereService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carriereDTO);
    }

    /**
     * {@code DELETE  /salaries/carrieres/:id} : delete the "id" carriere.
     *
     * @param id the id of the carriereDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/salaries/carrieres/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<Void> deleteCarriere(@PathVariable Long id) {
        log.debug("REST request to delete Carriere : {}", id);
        carriereService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /salaries/my-carriere} : get the "userId" salarie.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salarieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/my-carriere")
    @PreAuthorize("hasRole('" + AuthoritiesConstants.SALARIE + "') or hasRole('" + AuthoritiesConstants.AGENT + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "') or hasRole('" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "') or hasRole('" + AuthoritiesConstants.CHEF_AGENCE + "')")
    public ResponseEntity<HashMap<String, Object>> getMyCarriere(
        @RequestParam(value = "user-id", required = false) Long userId,
        @RequestParam(value = "salarie-id", required = false) Long salarie_id,
        CarriereCriteria criteria, Pageable pageable) {
        Optional<SalarieDTO> salarieDTO;
        if (userId == null && salarie_id == null) {
            if (!getCurrentUserLogin().isPresent()) {
                throw Problem.builder().withDetail("Vous devez vous connecter pour accéder à cette ressource").withTitle("Erreur").build();
            }
            String username = getCurrentUserLogin().get();
            userId = userRepository.findByLogin(username).getId();
        }
        if(userId != null){
            salarieDTO = salarieService.findByUserId(userId);
            if (!salarieDTO.isPresent()) {
                throw Problem.builder().withDetail("Aucun salarié trouver avec un userId égal à "+userId).withTitle("Erreur").build();
            }
        }else {
            salarieDTO = salarieService.findSalarieDtoId(salarie_id);
            if (!salarieDTO.isPresent()) {
                throw Problem.builder().withDetail("Aucun salarié trouver avec un id égal à "+salarie_id).withTitle("Erreur").build();
            }
        }
        Long salarieId = salarieDTO.get().getId();
        String numeroUnique = salarieDTO.get().getNumeroUnique();
        LongFilter lf = new LongFilter();
        StringFilter sf = new StringFilter();
        lf.setEquals(salarieId);
        sf.setEquals(numeroUnique);
        criteria.setSalarieId(lf);
//        criteria.setNumeroUniqueSalarie(sf);

        log.debug("REST request to get Carrieres by criteria: {}", criteria);
        Page<CarriereDTO> page = carriereQueryService.findByCriteria(criteria, pageable);

        List<CarriereDTO> list = page.getContent();
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


    @GetMapping("/salaries/search-carriere")
//    @PreAuthorize("hasRole('" + AuthoritiesConstants.SALARIE + "') or hasRole('" + AuthoritiesConstants.AGENT + "')")
    public Object searchCarriereBySalarie(
        @RequestParam(value = "salarieId", required = false) Long salarieId,
        @RequestParam(value = "numeroUnique", required = false) String numeroUnique,
        @RequestParam(value = "numeroCni", required = false) String numeroCni) {

        if (salarieId == null &&
            (numeroUnique == null || numeroUnique.isEmpty()) &&
            (numeroCni == null || numeroCni.isEmpty())) {
            return ApiResponse.error400("Au moins un paramètre de recherche doit être fourni");
        }

        Optional<SalarieDTO> salarieDTO = Optional.empty();

        if (salarieId != null) {
            salarieDTO = salarieService.findSalarieDtoId(salarieId);
        }

        if (!salarieDTO.isPresent() && numeroUnique != null && !numeroUnique.isEmpty()) {
            salarieDTO = salarieService.findByNumeroUnique(numeroUnique);
        }

        if (!salarieDTO.isPresent() && numeroCni != null && !numeroCni.isEmpty()) {
            salarieDTO = salarieService.findByNumeroCni(numeroCni);
        }

        if (!salarieDTO.isPresent()) {
            return ApiResponse.error404("Aucun salarié trouvé avec les paramètres fournis");
        }

        CarriereCriteria criteria = new CarriereCriteria();
        LongFilter lf = new LongFilter();
        lf.setEquals(salarieDTO.get().getId());
        criteria.setSalarieId(lf);

        log.debug("REST request pour rechercher la carrière d'un salarié : {}", salarieDTO.get().getId());
        List<CarriereDTO> carrieres = new ArrayList<>(carriereQueryService.findByCriteria(criteria, Pageable.unpaged()).getContent());

        Collections.sort(carrieres, new Comparator<CarriereDTO>() {
            @Override
            public int compare(CarriereDTO c1, CarriereDTO c2) {
                return c2.getId().compareTo(c1.getId());
            }
        });

        return ApiResponse.success(carrieres);
    }

    @GetMapping("/salaries/carrieres-groupees")
    public ResponseEntity<HashMap<String, Object>> getCarrieresGroupees(CarriereCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Carrieres grouped by salarie with salarie pagination");

        Page<SalarieDTO> salariePage = salarieQueryService.findByCriteria(new SalarieCriteria(), pageable);

        List<Map<String, Object>> resultList = new ArrayList<>();
        List<SalarieDTO> filteredSalaries = new ArrayList<>();

        for (SalarieDTO salarie : salariePage.getContent()) {
            CarriereCriteria salarieCriteria = new CarriereCriteria();
            LongFilter salarieFilter = new LongFilter();
            salarieFilter.setEquals(salarie.getId());
            salarieCriteria.setSalarieId(salarieFilter);

            List<CarriereDTO> carrieresList = carriereQueryService.findByCriteria(salarieCriteria);

            if (!carrieresList.isEmpty()) {
                Map<String, Object> salarieInfo = new HashMap<>();
                salarieInfo.put("salarieId", salarie.getId());
                salarieInfo.put("nom", salarie.getNom());
                salarieInfo.put("prenom", salarie.getPrenom());
                salarieInfo.put("dateNaissance", salarie.getDateNais());
                salarieInfo.put("nin", salarie.getNumeroCni());
                salarieInfo.put("numeroUnique", salarie.getNumeroUnique());
                salarieInfo.put("statut", salarie.getStatut());
                salarieInfo.put("carrieres", carrieresList);

                resultList.add(salarieInfo);
                filteredSalaries.add(salarie);
            }
        }

        Page<SalarieDTO> filteredPage = new PageImpl<>(
            filteredSalaries,
            pageable,
            filteredSalaries.size()
        );

        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();

        pagination.put("page", filteredPage.getNumber());
        pagination.put("size", filteredPage.getSize());
        pagination.put("totalElements", filteredPage.getTotalElements());

        response.put("code", 200);
        response.put("list", resultList);
        response.put("pagination", pagination);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
            ServletUriComponentsBuilder.fromCurrentRequest(),
            filteredPage
        );

        return ResponseEntity.ok().headers(headers).body(response);
    }
}
