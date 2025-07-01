package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.FactureDNS;
import com.secusociale.portail.domain.TauxAT;
import com.secusociale.portail.repository.FactureDNSRepository;
import com.secusociale.portail.repository.TauxATRepository;
import com.secusociale.portail.service.FactureDNSQueryService;
import com.secusociale.portail.service.FactureDNSService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.CorrectionTauxATMPResponseDTO;
import com.secusociale.portail.service.dto.FactureDNSCriteria;
import com.secusociale.portail.service.dto.FactureDNSDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.FactureDNS}.
 */
@RestController
@RequestMapping("/api")
public class FactureDNSResource {

    private final Logger log = LoggerFactory.getLogger(FactureDNSResource.class);

    private static final String ENTITY_NAME = "portailCssIpresV2FactureDns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactureDNSService factureDNSService;
    private final FactureDNSRepository factureDNSRepository;
    private final TauxATRepository tauxATRepository;

    private final FactureDNSQueryService factureDNSQueryService;

    public FactureDNSResource(FactureDNSService factureDNSService, FactureDNSRepository factureDNSRepository, TauxATRepository tauxATRepository, FactureDNSQueryService factureDNSQueryService) {
        this.factureDNSService = factureDNSService;
        this.factureDNSRepository = factureDNSRepository;
        this.tauxATRepository = tauxATRepository;
        this.factureDNSQueryService = factureDNSQueryService;
    }

    /**
     * {@code POST  /facture-dns} : Create a new factureDNS.
     *
     * @param factureDNSDTO the factureDNSDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new factureDNSDTO, or with status {@code 400 (Bad Request)} if the factureDNS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facture-dns")
    public ResponseEntity<FactureDNSDTO> createFactureDNS(@Valid @RequestBody FactureDNSDTO factureDNSDTO) throws URISyntaxException {
        log.debug("REST request to save FactureDNS : {}", factureDNSDTO);
        if (factureDNSDTO.getId() != null) {
            throw new BadRequestAlertException("A new factureDNS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactureDNSDTO result = factureDNSService.save(factureDNSDTO);
        return ResponseEntity.created(new URI("/api/facture-dns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facture-dns} : Updates an existing factureDNS.
     *
     * @param factureDNSDTO the factureDNSDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated factureDNSDTO,
     * or with status {@code 400 (Bad Request)} if the factureDNSDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the factureDNSDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facture-dns")
    public ResponseEntity<FactureDNSDTO> updateFactureDNS(@Valid @RequestBody FactureDNSDTO factureDNSDTO) throws URISyntaxException {
        log.debug("REST request to update FactureDNS : {}", factureDNSDTO);
        if (factureDNSDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactureDNSDTO result = factureDNSService.save(factureDNSDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, factureDNSDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facture-dns} : get all the factureDNS.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factureDNS in body.
     */
    @GetMapping("/facture-dns")
    public ResponseEntity<List<FactureDNSDTO>> getAllFactureDNS(FactureDNSCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FactureDNS by criteria: {}", criteria);
        Page<FactureDNSDTO> page = factureDNSQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /facture-dns/:id} : get the "id" factureDNS.
     *
     * @param idDeclaration the id of the factureDNSDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factureDNSDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facture-dns/check-declaration/{idDeclaration}")
    public ResponseEntity<HashMap<String, Object>> checkDeclarationFactureDNS(@PathVariable Long idDeclaration) {
        HashMap<String, Object> resultat = new HashMap<>();
        log.debug("REST request to check FactureDNS : {}", idDeclaration);

        List<FactureDNSDTO> factureDNSDTOs = factureDNSService.findFacturesByDeclaration(idDeclaration);

        if (!factureDNSDTOs.isEmpty()) {
            resultat.put("code", "200");
            resultat.put("exist", true);
            resultat.put("facture", factureDNSDTOs);
        } else {
            resultat.put("code", "400");
            resultat.put("exist", false);
        }
        return ResponseEntity.ok(resultat);
    }


    /**
     * {@code GET  /facture-dns/count} : count all the factureDNS.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/facture-dns/count")
    public ResponseEntity<Long> countFactureDNS(FactureDNSCriteria criteria) {
        log.debug("REST request to count FactureDNS by criteria: {}", criteria);
        return ResponseEntity.ok().body(factureDNSQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /facture-dns/:id} : get the "id" factureDNS.
     *
     * @param id the id of the factureDNSDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factureDNSDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facture-dns/{id}")
    public ResponseEntity<FactureDNSDTO> getFactureDNS(@PathVariable Long id) {
        log.debug("REST request to get FactureDNS : {}", id);
        Optional<FactureDNSDTO> factureDNSDTO = factureDNSService.findOne(id);
        return ResponseUtil.wrapOrNotFound(factureDNSDTO);
    }


    /**
     * {@code DELETE  /facture-dns/:id} : delete the "id" factureDNS.
     *
     * @param id the id of the factureDNSDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facture-dns/{id}")
    public ResponseEntity<Void> deleteFactureDNS(@PathVariable Long id) {
        log.debug("REST request to delete FactureDNS : {}", id);
        factureDNSService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/facture-dns/correction")
    public ApiResponse<CorrectionTauxATMPResponseDTO> corrigerTauxATMP() {
        log.debug("REST request pour corriger les taux ATMP des factures DNS");

        List<FactureDNS> factures = factureDNSRepository.findAll();
        CorrectionTauxATMPResponseDTO rapport = new CorrectionTauxATMPResponseDTO();
        List<String> facturesCorrigees = new ArrayList<>();
        List<String> erreurs = new ArrayList<>();

        for (FactureDNS facture : factures) {
            try {
                String numeroUnique = facture.getNumeroUnique();
                if (numeroUnique != null && !numeroUnique.isEmpty()) {

                    TauxAT tauxAT = tauxATRepository.findByNumeroUnique(numeroUnique);

                    if (tauxAT != null) {
                        try {
                            Float tauxATMPValue = Float.parseFloat(tauxAT.getTauxAT());

                            facture.setTauxATMP(tauxATMPValue);

                            if (facture.getAtmp() != null) {
                                Double mtAtmp = facture.getAtmp() * tauxATMPValue / 100;
                                facture.setMtAtmp(mtAtmp);

                                facture = factureDNSService.recalculerTotaux(facture);
                            }

                            factureDNSRepository.save(facture);
                            facturesCorrigees.add("Facuture numéro : "+facture.getNumeroFacture());
                        } catch (NumberFormatException e) {
                            erreurs.add("Impossible de convertir le taux ATMP pour la facture numéro "
                                + facture.getNumeroFacture() + " : " + e.getMessage());
                        }
                    } else {
                        erreurs.add("Aucun taux AT trouvé pour le numéro unique: " + numeroUnique);
                    }
                } else {
                    erreurs.add("Numéro unique manquant pour la facture numéro " + facture.getNumeroFacture());
                }
            } catch (Exception e) {
                erreurs.add("Erreur lors du traitement de la facture numéro " + facture.getNumeroFacture()
                    + " : " + e.getMessage());
            }
        }

        rapport.setFacturesCorrigees(facturesCorrigees);
        rapport.setNombreFacturesCorrigees(facturesCorrigees.size());
        rapport.setNombreFacturesTotal(factures.size());
        rapport.setErreurs(erreurs);

        return ApiResponse.success(rapport);
    }
}
