package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.GedUpdate;
import com.secusociale.portail.domain.ImmatriculationRecuperee;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.GedUpdateRepository;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.service.ImmatriculationRecupereeQueryService;
import com.secusociale.portail.service.dto.*;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for handle.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GedController {
    private final AgenceRepository agenceRepository;
    private final ImmatriculationRecupereeQueryService immatriculationRecupereeQueryService;
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;
    private final GedUpdateRepository gedUpdateRepository;


    @GetMapping("/ged/list")
    public ResponseEntity<List<GedListResponseDTO>> listMandat(
        @RequestParam(value = "debut") Optional<LocalDate> fromDate,
        @RequestParam(value = "fin") Optional<LocalDate> toDate) {

        List<GedListResponseDTO> data = new ArrayList<>();
        Instant debut = null;
        Instant fin = null;
        ImmatriculationRecupereeCriteria criteria = new ImmatriculationRecupereeCriteria();

        if (fromDate.isPresent() && toDate.isPresent()) {
            InstantFilter dateFilter = new InstantFilter();
            debut = fromDate.get().atStartOfDay().toInstant(ZoneOffset.UTC);
            fin = toDate.get().atStartOfDay().plusDays(1).toInstant(ZoneOffset.UTC);
            dateFilter.setGreaterThanOrEqual(debut);
            dateFilter.setLessThan(fin);
            criteria.setDate(dateFilter);
        }
        StringFilter sf = new StringFilter();
        sf.setEquals("ACTIVEE");
        criteria.setStatus(sf);

        List<ImmatriculationRecupereeDTO> mandatList = immatriculationRecupereeQueryService.findByCriteria(criteria);

        mandatList.forEach(ai -> {
            HashMap<String, Object> line = new HashMap<>();
            // id, raisonSociale sociale, numéro css, numéro ipress, numéro unique, date, agence caisse, agence ipress, ninéa, mandat
            if (ai.getAgenceIpres() != null && !ai.getAgenceIpres().equalsIgnoreCase(StringUtils.EMPTY))
                ai.setAgenceIPRESObject(agenceRepository.findByCode(ai.getAgenceIpres()));

            if (ai.getAgenceCss() != null && !ai.getAgenceCss().equalsIgnoreCase(StringUtils.EMPTY))
                ai.setAgenceCSSObject(agenceRepository.findByCode(ai.getAgenceCss()));

            line.put("id", ai.getId());
            line.put("raisonSociale", ai.getRaisonSociale());
            line.put("ninea", ai.getNineaFile());
            line.put("numeroUnique", ai.getNumeroUnique());
            line.put("date", ai.getDate());
            line.put("agenceIPRES", ai.getAgenceIPRESObject() != null ? ai.getAgenceIPRESObject().getNom() : "");
            line.put("agenceCSS", ai.getAgenceCSSObject() != null ? ai.getAgenceCSSObject().getNom() : "");
            String mandatLink = ai.getMandatFile(); // https://preprodonline.secusociale.sn/documents/mandatFile_K2mpAFmnlH.pdf
            line.put("mandat", mandatLink);
            boolean notEmpty = StringUtils.isNotEmpty(mandatLink);
            String mandatFile = notEmpty ? mandatLink.substring(mandatLink.lastIndexOf('/') + 1) : null;
            line.put("mandatFile", mandatFile);

            data.add(new GedListResponseDTO(line));
        });

        return ResponseEntity.ok(data);
    }

    @PutMapping("/ged/update")
    @Transactional
    public ResponseEntity<GedDTOResponse> updatePath(@RequestBody GedDTO gedDTO) {
        HashMap<String, String> result = new HashMap<>();
        if (gedDTO == null || StringUtils.isEmpty(gedDTO.getNumeroUnique()) || StringUtils.isEmpty(gedDTO.getMandat())) {
            result.put("code", "400");
            result.put("erreur", "Le numero unique et le nouveau path du mandat sont obligatoires");
            result.put("message", "Le numero unique et le nouveau path du mandat sont obligatoires");
            return ResponseEntity.badRequest().body(new GedDTOResponse(result));
        }
        Optional<ImmatriculationRecuperee> immatriculation = immatriculationRecupereeRepository.findByNumeroUnique(gedDTO.getNumeroUnique());
        if (!immatriculation.isPresent()) {
            result.put("code", "400");
            result.put("message", "Aucun mandat trouvé avec le numéro unique " + gedDTO.getMandat());
            result.put("erreur", "Aucun mandat trouvé avec le numéro unique " + gedDTO.getMandat());
            return ResponseEntity.badRequest().body(new GedDTOResponse(result));
        }
        ImmatriculationRecuperee oldImmatriculation = immatriculation.get();
        String oldMandat = oldImmatriculation.getMandatFile();
        oldImmatriculation.setMandatFile(gedDTO.getMandat());
        GedUpdate gedUpdate = new GedUpdate();
        gedUpdate.setNumeroUnique(oldImmatriculation.getNumeroUnique());
        gedUpdate.setOldPath(oldMandat);
        gedUpdate.setIdOld(oldImmatriculation.getId());
        immatriculationRecupereeRepository.save(oldImmatriculation);
        gedUpdate.setNewPath(oldImmatriculation.getMandatFile());
        gedUpdate.setDate(Instant.now());
        gedUpdateRepository.save(gedUpdate);

        result.put("code", "200");
        result.put("message", "Lien modifié avec succès");
        return ResponseEntity.ok(new GedDTOResponse(result));
    }

}
