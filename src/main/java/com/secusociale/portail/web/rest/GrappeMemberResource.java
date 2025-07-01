package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.GrappeMember;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.GrappeMemberQueryService;
import com.secusociale.portail.service.GrappeMemberService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.GrappeMember}.
 */
@RestController
@RequestMapping("/api")
public class GrappeMemberResource {

    private final Logger log = LoggerFactory.getLogger(GrappeMemberResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2GrappeMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrappeMemberService grappeMemberService;
    private final UserRepository userRepository;
    private final SalarieRepository salarieRepository;

    private final GrappeMemberQueryService grappeMemberQueryService;

    public GrappeMemberResource(GrappeMemberService grappeMemberService, UserRepository userRepository, SalarieRepository salarieRepository, GrappeMemberQueryService grappeMemberQueryService) {
        this.grappeMemberService = grappeMemberService;
        this.userRepository = userRepository;
        this.salarieRepository = salarieRepository;
        this.grappeMemberQueryService = grappeMemberQueryService;
    }

    /**
     * {@code POST  /salaries/grappe-members} : Create a new grappeMember.
     *
     * @param grappeMemberDTO the grappeMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grappeMemberDTO, or with status {@code 400 (Bad Request)} if the grappeMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/salaries/grappe-members")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<GrappeMemberDTO> createGrappeMember(@RequestBody GrappeMemberDTO grappeMemberDTO) throws URISyntaxException {
        log.debug("REST request to save GrappeMember : {}", grappeMemberDTO);
        HashMap<String, Object> resultat = new HashMap<>();
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Long userId = userRepository.findByLogin(username).getId();
        if (!salarieRepository.findByUserId(userId).isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas un salarie").withTitle("Erreur").build();
        }
        Long salarieId = salarieRepository.findByUserId(userId).get().getId();
        if (grappeMemberDTO.getId() != null) {
            throw new BadRequestAlertException("Un nouveau membre ne peut avoir un ID", ENTITY_NAME, "idexists");
        }
        grappeMemberDTO.setSalarieId(salarieId);
        GrappeMemberDTO result = grappeMemberService.save(grappeMemberDTO);
        return ResponseEntity.created(new URI("/api/salaries/grappe-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /salaries/grappe-members} : Updates an existing grappeMember.
     *
     * @param grappeMemberDTO the grappeMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grappeMemberDTO,
     * or with status {@code 400 (Bad Request)} if the grappeMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grappeMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/salaries/grappe-members")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<GrappeMemberDTO> updateGrappeMember(@RequestBody GrappeMemberDTO grappeMemberDTO) throws URISyntaxException {
        log.debug("REST request to update GrappeMember : {}", grappeMemberDTO);
        HashMap<String, Object> resultat = new HashMap<>();
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Long userId = userRepository.findByLogin(username).getId();
        if (!salarieRepository.findByUserId(userId).isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas un salarie").withTitle("Erreur").build();
        }
        Long salarieId = salarieRepository.findByUserId(userId).get().getId();
        if (grappeMemberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        grappeMemberDTO.setSalarieId(salarieId);
        GrappeMemberDTO result = grappeMemberService.save(grappeMemberDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grappeMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /salaries/grappe-members} : get all the grappeMembers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grappeMembers in body.
     */
    @GetMapping("/salaries/grappe-members")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<HashMap<String, Object>> getAllGrappeMembers(
        GrappeMemberCriteria criteria,
        Pageable pageable,
        @RequestParam(required = false, name = "search") String search // Nouveau paramètre de recherche
    ) {
        log.debug("REST request to get GrappeMembers by criteria: {}", criteria);

        // Vérification de la connexion de l'utilisateur
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour accéder à cette ressource").withTitle("Erreur").build();
        }

        String username = getCurrentUserLogin().get();
        Long userId = userRepository.findByLogin(username).getId();
        if (!salarieRepository.findByUserId(userId).isPresent()) {
            throw Problem.builder().withDetail("Vous n'êtes pas un salarié").withTitle("Erreur").build();
        }
        Long salarieId = salarieRepository.findByUserId(userId).get().getId();
        LongFilter lf = new LongFilter();
        lf.setEquals(salarieId);
        criteria.setSalarieId(lf);

        // Filtrage global
        if (!isEmpty(search)) {
            StringFilter globalFilter = new StringFilter();
            globalFilter.setContains(search.toUpperCase(Locale.ROOT));
            criteria.setGlobalSearch(globalFilter);
        }

        // Récupération des résultats avec les critères
        Page<GrappeMemberDTO> page = grappeMemberQueryService.findByCriteria(criteria, pageable);
        List<GrappeMemberDTO> list = page.getContent();
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
     * {@code GET  /salaries/grappe-members/count} : count all the grappeMembers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/salaries/grappe-members/count")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<Long> countGrappeMembers(GrappeMemberCriteria criteria) {
        HashMap<String, Object> resultat = new HashMap<>();
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        Long userId = userRepository.findByLogin(username).getId();
        if (!salarieRepository.findByUserId(userId).isPresent()) {
            throw Problem.builder().withDetail("Vous n'etes pas un salarie").withTitle("Erreur").build();
        }
        Long salarieId = salarieRepository.findByUserId(userId).get().getId();
        LongFilter lf = new LongFilter();
        lf.setEquals(salarieId);
        criteria.setSalarieId(lf);
        log.debug("REST request to count GrappeMembers by criteria: {}", criteria);
        return ResponseEntity.ok().body(grappeMemberQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /salaries/grappe-members/:id} : get the "id" grappeMember.
     *
     * @param id the id of the grappeMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grappeMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salaries/grappe-members/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<GrappeMemberDTO> getGrappeMember(@PathVariable Long id) {
        log.debug("REST request to get GrappeMember : {}", id);
        Optional<GrappeMemberDTO> grappeMemberDTO = grappeMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grappeMemberDTO);
    }

    /**
     * {@code DELETE  /salaries/grappe-members/:id} : delete the "id" grappeMember.
     *
     * @param id the id of the grappeMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/salaries/grappe-members/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.SALARIE + "\")")
    public ResponseEntity<Void> deleteGrappeMember(@PathVariable Long id) {
        log.debug("REST request to delete GrappeMember : {}", id);
        grappeMemberService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

@PutMapping("/grappe-members/{grappeMemberId}/status/{newStatus}")
public ApiResponse<String> updateGrappeMemberStatus(
    @PathVariable Long grappeMemberId,
    @PathVariable String newStatus
) {
    log.debug("REST request to update GrappeMember status, id: {}, new status: {}", grappeMemberId, newStatus);

    try {
        StatutGrappeMembre statutGrappeMembre = StatutGrappeMembre.valueOf(newStatus.toUpperCase());
        grappeMemberService.updateStatusById(grappeMemberId, statutGrappeMembre);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, "Mise à jour du statut du membre de la grappe réussie.");
    } catch (ResponseStatusException e) {
        throw e;
    } catch (Exception e) {
        throw Problem.builder()
            .withDetail("Erreur lors de la mise à jour du statut du membre de la grappe")
            .withTitle("Erreur")
            .build();
    }
}

    @PutMapping("/grappe-members/{statutGrappe}/{salarieId}")
    public ApiResponse<String> validateGrappeMemberBySalarieId(
        @PathVariable String statutGrappe,
        @PathVariable Long salarieId
    ) {
        log.debug("REST request to update GrappeMember status by salarieId: {}, new status: {}", salarieId, statutGrappe);
        grappeMemberService.updateStatusBySalarieId(statutGrappe, salarieId);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, "Mise à jour du statut du membre de la grappe réussie.");
    }

    /**
     * Modifier le statut des membres d'une grappe grace au salarieId
     * @param changeGrappeMemberStatusDTO
     * @return
     */
    @PutMapping("/grappe-members/change-status")
    public ApiResponse<String> changeGrappeMemberStatus(
        @Valid @RequestBody ChangeGrappeMemberStatusDTO changeGrappeMemberStatusDTO
    ) {
        String statuStr = changeGrappeMemberStatusDTO.getStatutGrappe().toUpperCase();
        log.debug("REST request to update GrappeMember status by salarieId: {}, new status: {}",
            changeGrappeMemberStatusDTO.getSalarieId(), statuStr);

        StatutGrappeMembre statut;

        try {
            statut = StatutGrappeMembre.valueOf(statuStr);
        } catch (IllegalArgumentException e) {
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.BAD_REQUEST, "Statut invalide Les valeurs possibles sont: " + Arrays.toString(StatutGrappeMembre.values()));
        }

        if ((statut == StatutGrappeMembre.REJETE || statut == StatutGrappeMembre.RETOURNE)) {
            if (changeGrappeMemberStatusDTO.getMotif() == null || changeGrappeMemberStatusDTO.getMotif().trim().isEmpty()) {
                return ApiResponse.errorResponse(
                    ApiResponse.ResponseCode.BAD_REQUEST,
                    "Le motif est obligatoire pour les statuts REJETE et RETOURNE"
                );
            }

            grappeMemberService.updateStatusAndMotifBySalarieId(
                statut,
                changeGrappeMemberStatusDTO.getSalarieId(),
                changeGrappeMemberStatusDTO.getMotif()
            );
        } else {
            grappeMemberService.updateStatusAndMotifBySalarieId(
                statut,
                changeGrappeMemberStatusDTO.getSalarieId(),
                null
            );
        }

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, "Mise à jour du statut du membre de la grappe réussie.");
    }

    @PutMapping("/grappe-members/change-member-status")
    public ApiResponse<String> changeGrappeMemberStatusById(
        @Valid @RequestBody ChangeMemberStatusDTO changeMemberStatusDTO
    ) {
        String statuStr = changeMemberStatusDTO.getStatutGrappe().toUpperCase();
        log.debug("REST request to update GrappeMember status by grappeId: {}, new status: {}",
            changeMemberStatusDTO.getGrappeId(), statuStr);

        StatutGrappeMembre statut;

        try {
            statut = StatutGrappeMembre.valueOf(statuStr);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error400("Statut invalide Les valeurs possibles sont: " + Arrays.toString(StatutGrappeMembre.values()));
        }

        if ((statut == StatutGrappeMembre.REJETE || statut == StatutGrappeMembre.RETOURNE)) {
            if (changeMemberStatusDTO.getMotif() == null || changeMemberStatusDTO.getMotif().trim().isEmpty()) {
                return ApiResponse.error400("Le motif est obligatoire pour les statuts REJETE et RETOURNE"
                );
            }

            grappeMemberService.updateStatusAndMotifBySId(
                statut,
                changeMemberStatusDTO.getGrappeId(),
                changeMemberStatusDTO.getMotif()
            );
        } else {
            grappeMemberService.updateStatusAndMotifBySId(
                statut,
                changeMemberStatusDTO.getGrappeId(),
                null
            );
        }

        return ApiResponse.success("Mise à jour du statut du membre de la grappe réussie.");
    }

    @GetMapping("/grappe-members/{statutGrappe}")
    public ApiResponse<List<GrappeMember>> getGrappeMembersByStatus(
        @PathVariable String statutGrappe
    ) {
        log.debug("REST request to get GrappeMembers with status: {}", statutGrappe);
        List<GrappeMember> list = grappeMemberService.getGrappeMembersByStatus(statutGrappe);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, list);
    }

    @GetMapping("/grappe-members/salarieId")
    public ResponseEntity<HashMap<String, Object>> getGrappeMembersByStatus(
        @RequestParam(required = false, name = "id") Long id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        log.debug("REST request to get GrappeMembers with status: {}", id);
        Page<GrappeMember> list = grappeMemberService.searchGrappeMembersBySalarieId(id, page, size);
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        result.put("body", list.getContent());

        // Ajouter la pagination si nécessaire
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("totalElements", list.getTotalElements() );
        pagination.put("size", list.getSize() );
        pagination.put("page", list.getNumber() );
        result.put("pagination", pagination);

        //  return ApiResponse.successResponse(ApiResponse.ResponseCo, result);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), list);
        return ResponseEntity.ok().headers(headers).body(result);
    }
}
