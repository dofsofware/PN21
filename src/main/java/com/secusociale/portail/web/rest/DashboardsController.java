package com.secusociale.portail.web.rest;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.repository.*;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.*;
import com.secusociale.portail.service.dto.*;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.security.SecurityUtils.isCurrentUserInRole;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * REST controller for handle.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DashboardsController {
    private final DeclarationQueryService declarationQueryService;
    private final UserRepository userRepository;
    private final UserQueryService userQueryService;
    private final ImmatriculationRecupereeQueryService immatriculationRecupereeQueryService;
    private final NouvelleImmatriculationQueryService nouvelleImmatriculationQueryService;
    private final DMTQueryService dmtQueryService;
    private final DemandeServiceQueryService demandeServiceQueryService;
    private final DashboardsService dashboardsService;
    // @PersistenceContext


    @GetMapping("/agent-stats")
//    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.CHEF_AGENCE + "\",\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_SALARIE + "\",\"" + AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR + "\")")


    public ResponseEntity<HashMap<String, Object>> getStatsAgent(
        @RequestParam(value = "from") Optional<LocalDate> fromDate,
        @RequestParam(value = "to") Optional<LocalDate> toDate
    ) {
        HashMap<String, Object> result = new HashMap<>();

        if (isCurrentUserInRole(AuthoritiesConstants.ADMIN) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
            HashMap<String, Long> immatriculationsVal = new HashMap<>();
            HashMap<String, Long> immatriculationsValLoc = new HashMap<>();
            immatriculationsValLoc.put("toutes", 0L);
            HashMap<String, Long> declarationsVal = new HashMap<>();
            HashMap<String, Object> immatOlds = new HashMap<>();
            HashMap<String, Object> immatlocals = new HashMap<>();
            immatriculationsVal.put("toutes", 0L);
            immatriculationsVal.put("encours", 0L);
            immatriculationsVal.put("actives", 0L);
            immatriculationsVal.put("rejetees", 0L);
            immatriculationsVal.put("traitees", 0L);
            immatriculationsVal.put("byagent", 0L);

            immatOlds.put("global", 0L);
            immatOlds.put("parMois", 0L);
            declarationsVal.put("toutes", 0L);
            declarationsVal.put("trimestrielles", 0L);
            declarationsVal.put("mensuelles",  0L);
            declarationsVal.put("encours",  0L);
            declarationsVal.put("actives",  0L);
            declarationsVal.put("rejetees",  0L);
            declarationsVal.put("traitees",  0L);
            declarationsVal.put("enmasse",  0L);
            declarationsVal.put("manuelles",  0L);
            immatlocals.put("global",immatriculationsValLoc);

            //by month
            HashMap<String, Object> imm = new HashMap<>();
            HashMap<String, Object> dec = new HashMap<>();
            imm.put("locales", immatlocals);
            imm.put("olds", immatOlds);
            dec.put("global", declarationsVal);
            dec.put("parMois", null);

            HashMap<String, Object> decParTypeEtParMois = new HashMap<>();
            decParTypeEtParMois.put("trimestrielles", null);
            decParTypeEtParMois.put("mensuelles", null);
            dec.put("parTypeEtParMois", null);

            result.put("immatriculations", imm);
            result.put("declarations", dec);
        } else {
            result.put("code", 400);
            result.put("error", "Vous n'avez pas le droit d'acces à cette ressource");
        }
        return ResponseEntity.ok(result);
    }

    //TODO à optimiser
    public ResponseEntity<HashMap<String, Object>> getStatsAgent_Backup(
        @RequestParam(value = "from") Optional<LocalDate> fromDate,
        @RequestParam(value = "to") Optional<LocalDate> toDate
    ) {
        HashMap<String, Object> result = new HashMap<>();
        Boolean forInterval = false;
        Instant debut = null;
        Instant fin = null;
        ImmatriculationRecupereeCriteria criteriaAG = new ImmatriculationRecupereeCriteria();
        ImmatriculationRecupereeCriteria criteriaAGUSER = new ImmatriculationRecupereeCriteria();
        NouvelleImmatriculationCriteria criterialoacal = new NouvelleImmatriculationCriteria();
        DeclarationCriteria declarationCriteriaAG = new DeclarationCriteria();
        if (fromDate.isPresent() && toDate.isPresent()) {
            System.out.println("fromDate >> " + fromDate.get());
            InstantFilter dateFilter = new InstantFilter();
            debut = fromDate.get().atStartOfDay().toInstant(ZoneOffset.UTC);
            fin = toDate.get().atStartOfDay().plusDays(1).toInstant(ZoneOffset.UTC);
            dateFilter.setGreaterThanOrEqual(debut);
            dateFilter.setLessThan(fin);
            criteriaAG.setDate(dateFilter);
            criteriaAGUSER.setDate(dateFilter);
            criterialoacal.setCreatedAt(dateFilter);
            declarationCriteriaAG.setCreateAt(dateFilter);
            forInterval = true;
        }
        String loggedusername = getCurrentUserLogin().orElse(null);

        if (isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_SALARIE) || isCurrentUserInRole(AuthoritiesConstants.AGENT) || isCurrentUserInRole(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR) || isCurrentUserInRole(AuthoritiesConstants.CHEF_AGENCE)) {
            User user = userRepository.findByLogin(loggedusername);
            if (StringUtils.isEmpty(user.getInstitution()) || StringUtils.isEmpty(user.getAgence())) {
                result.put("code", 400);
                result.put("error", "Vous n'avez pas le droit d'acces à cette ressource");
                return ResponseEntity.ok(result);
            }
            StringFilter sf = new StringFilter();
            LongFilter lf = new LongFilter();
            if (isEmpty(user.getAgence()) || isEmpty(user.getInstitution())) {
                sf.setEquals("NO_AGENCE_FILTER");
            }
            if (!isEmpty(user.getAgence())) {
                if (!isEmpty(user.getInstitution())) {
                    if (user.getInstitution().equalsIgnoreCase("CSS")) {
                        if (!isEmpty(user.getAgence())) {
                            sf.setContains(user.getAgence());
                            criteriaAG.setAgenceCss(sf);
                            declarationCriteriaAG.setCodeAgenceCSS(sf);
                        }
                    }
                    if (user.getInstitution().equalsIgnoreCase("IPRES")) {
                        if (!isEmpty(user.getAgence())) {
                            sf.setEquals(user.getAgence());
                            criteriaAG.setAgenceIpres(sf);
                            declarationCriteriaAG.setCodeAgenceIPRES(sf);
                        }
                    }
                } else {
                    criteriaAG.setAgenceIpres(sf);
                    declarationCriteriaAG.setCodeAgenceIPRES(sf);
                }
            } else {
                criteriaAG.setAgenceIpres(sf);
                declarationCriteriaAG.setCodeAgenceCSS(sf);
            }
            criteriaAGUSER = criteriaAG;
            LongFilter agentIdFilter = new LongFilter();
            agentIdFilter.setEquals(user.getId());
            criteriaAGUSER.setAgentId(agentIdFilter);
            List<ImmatriculationRecupereeDTO> oldImmatsAgences = immatriculationRecupereeQueryService.findByCriteria(criteriaAG);
            List<ImmatriculationRecupereeDTO> oldImmatsAgencesUser = immatriculationRecupereeQueryService.findByCriteria(criteriaAGUSER);
            System.out.println("declarationCriteriaAG >> " + declarationCriteriaAG.toString());
            List<Declaration> declarationsAG = declarationQueryService.findByCriteria(declarationCriteriaAG);
            List<NouvelleImmatriculationDTO> localimmatriculations = nouvelleImmatriculationQueryService.findByCriteria(criterialoacal);
            HashMap<String, Long> immatriculationsVal = new HashMap<>();
            HashMap<String, Long> immatriculationsValLoc = new HashMap<>();
            immatriculationsValLoc.put("toutes", (long) localimmatriculations.size());
            HashMap<String, Long> declarationsVal = new HashMap<>();
            HashMap<String, Object> immatOlds = new HashMap<>();
            HashMap<String, Object> immatlocals = new HashMap<>();
            immatlocals.put("global", immatriculationsValLoc);

            immatriculationsVal.put("toutes", (long) oldImmatsAgences.size());
            immatriculationsVal.put("encours", oldImmatsAgences.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("EN_COURS_DE_TRAITEMENT")).count());
            immatriculationsVal.put("actives", oldImmatsAgences.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("ACTIVEE")).count());
            immatriculationsVal.put("rejetees", oldImmatsAgences.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("REJETEE")).count());
            immatriculationsVal.put("traitees", oldImmatsAgences.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("REJETEE") || ai.getStatus().equalsIgnoreCase("ACTIVEE")).count());
            immatriculationsVal.put("byagent", oldImmatsAgencesUser.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("REJETEE") || ai.getStatus().equalsIgnoreCase("ACTIVEE")).count());

            immatOlds.put("global", immatriculationsVal);
//            immatOlds.put("parMois", allMonths(false, user, false, null, forInterval, debut, fin,null));
            immatOlds.put("parMois", dashboardsService.byMonth("old", user, false, null, forInterval, debut, fin, null));

            declarationsVal.put("toutes", (long) declarationsAG.size());
            declarationsVal.put("trimestrielles", declarationsAG.stream().filter(ai -> ai.getTypeDeclaration().equalsIgnoreCase("TRIMESTRIEL")).count());
            declarationsVal.put("mensuelles", declarationsAG.stream().filter(ai -> ai.getTypeDeclaration().equalsIgnoreCase("MENSUEL")).count());
            declarationsVal.put("encours", declarationsAG.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("SOUMISE")).count());
            declarationsVal.put("actives", declarationsAG.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("TRAITER")).count());
            declarationsVal.put("rejetees", declarationsAG.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("INVALIDE")).count());
            declarationsVal.put("traitees", declarationsAG.stream().filter(ai -> ai.getStatus().equalsIgnoreCase("INVALIDE") || ai.getStatus().equalsIgnoreCase("TRAITER")).count());
            declarationsVal.put("enmasse", declarationsAG.stream().filter(Declaration::isIsUploaded).count());
            declarationsVal.put("manuelles", declarationsAG.stream().filter(ai -> !ai.isIsUploaded()).count());

            //by month
            HashMap<String, Object> imm = new HashMap<>();
            HashMap<String, Object> dec = new HashMap<>();
            imm.put("locales", immatlocals);
            imm.put("olds", immatOlds);
            dec.put("global", declarationsVal);
//            dec.put("parMois", allMonths(true, user, false, null, forInterval, debut, fin,null));
            dec.put("parMois", dashboardsService.byMonth("declaration", user, false, null, forInterval, debut, fin, null));

            HashMap<String, Object> decParTypeEtParMois = new HashMap<>();
//            decParTypeEtParMois.put("trimestrielles", allMonths(true, user, false, "TRIMESTRIEL", forInterval, debut, fin,null));
//            decParTypeEtParMois.put("mensuelles", allMonths(true, user, false, "MENSUEL", forInterval, debut, fin,null));
            decParTypeEtParMois.put("trimestrielles", dashboardsService.byMonth("declaration", user, false, "TRIMESTRIEL", forInterval, debut, fin, null));
            decParTypeEtParMois.put("mensuelles", dashboardsService.byMonth("declaration", user, false, "MENSUEL", forInterval, debut, fin, null));
            dec.put("parTypeEtParMois", decParTypeEtParMois);

            result.put("immatriculations", imm);
            result.put("declarations", dec);
        } else {
            result.put("code", 400);
            result.put("error", "Vous n'avez pas le droit d'acces à cette ressource");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/admin-stats")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<HashMap<String, Object>> getStatsAdmin(
        @RequestParam(value = "from") Optional<LocalDate> fromDate,
        @RequestParam(value = "to") Optional<LocalDate> toDate) {

        HashMap<String, Object> result = dashboardsService.processAdmin(fromDate, toDate);

        return ResponseEntity.ok(result);
    }

    //crée une API qui retourne le tableau de déclarations, immatriculations, d'attestation de régularité et DMT groupés par mois
    @GetMapping("/declarations-par-agence")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.ADMIN + "\",\"" + AuthoritiesConstants.AGENT + "\",\"" + AuthoritiesConstants.PORTAIL + "\")")
    public ResponseEntity<Map<String, Long>> getTabs(
        @RequestParam(value = "from") Optional<LocalDate> fromDate,
        @RequestParam(value = "to") Optional<LocalDate> toDate) {

        return ResponseEntity.ok(dashboardsService.declarationsGroupByAgence(fromDate, toDate));
    }

}
