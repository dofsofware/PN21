package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.Reclamation;
import com.secusociale.portail.domain.TauxAT;
import com.secusociale.portail.domain.Taux_AT;
import com.secusociale.portail.repository.TauxATRepository;
import com.secusociale.portail.repository.Taux_ATRepository;
import com.secusociale.portail.service.TauxATService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@RestController
@RequestMapping("/api/taux-at")
public class TauxATResource {

    private static final String ENTITY_NAME = "portailCssIpresV2TauxAT";
    private final Logger log = LoggerFactory.getLogger(DeclarationResource.class);
    @Autowired
    private TauxATRepository tauxATRepository;

    @Autowired
    private TauxATService tauxATService;

    @GetMapping("/search")
    public ApiResponse<Object> searchMostRelevantTauxAT(@RequestParam String searchValue) {
        Taux_AT tauxAT = tauxATService.searchMostRelevantTauxAT(searchValue);
        if (tauxAT != null) {
            return ApiResponse.success(tauxAT);
        } else {
            return ApiResponse.error404("Aucun taux AT trouvé pour la valeur : '" + searchValue + "'");
        }
    }

    @PostMapping("")
    public ApiResponse<TauxAT> createTauxAt(@RequestBody TauxAT tauxAT) throws URISyntaxException {
        log.debug("REST request to save tauxAT : {}", tauxAT);
        if (tauxAT.getId() != null) {
            throw new BadRequestAlertException("A new tauxAt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TauxAT result = tauxATRepository.save(tauxAT);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK,result);
    }

    @PutMapping("")
    public ApiResponse<TauxAT> updateTauxAT(
        @Valid @RequestBody TauxAT tauxAT
    ) throws URISyntaxException {
        Long id = tauxAT.getId();
        if (tauxAT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        log.debug("REST request to update tauxAT : {}, {}", id, tauxAT);
        if (!Objects.equals(id, tauxAT.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tauxATRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tauxAT = tauxATRepository.save(tauxAT);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, tauxAT);
    }
}
