package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.repository.*;
import com.secusociale.portail.service.dto.*;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DashboardsService {

    private final DeclarationRepository declarationRepository;
    private final OldImmatriculationRepository oldImmatriculationRepository;
    private final LocalimmatriculationRepository localimmatriculationRepository;
    private final DMTRepository dmtRepository;
    private final DemandeServiceRepository demandeServiceRepository;
    private final UserRepository userRepository;
    private final DMTQueryService dmtQueryService;
    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;
    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;
    private final DeclarationQueryService declarationQueryService;
    private final EntityManager entityManager;

    public DashboardsService(DeclarationRepository declarationRepository,
                             OldImmatriculationRepository oldImmatriculationRepository,
                             LocalimmatriculationRepository localimmatriculationRepository,
                             DMTRepository dmtRepository,
                             DemandeServiceRepository demandeServiceRepository,
                             UserRepository userRepository, DMTQueryService dmtQueryService, NouvelleImmatriculationRepository nouvelleImmatriculationRepository, ImmatriculationRecupereeRepository immatriculationRecupereeRepository, DeclarationQueryService declarationQueryService, EntityManager entityManager) {
        this.declarationRepository = declarationRepository;
        this.oldImmatriculationRepository = oldImmatriculationRepository;
        this.localimmatriculationRepository = localimmatriculationRepository;
        this.dmtRepository = dmtRepository;
        this.demandeServiceRepository = demandeServiceRepository;
        this.userRepository = userRepository;
        this.dmtQueryService = dmtQueryService;
        this.nouvelleImmatriculationRepository = nouvelleImmatriculationRepository;
        this.immatriculationRecupereeRepository = immatriculationRecupereeRepository;
        this.declarationQueryService = declarationQueryService;
        this.entityManager = entityManager;
    }

    public HashMap<String, Object> processAdmin(Optional<LocalDate> fromDate, Optional<LocalDate> toDate) {
        log.info("Debut calcule des statistiques Admin : "+ LocalDateTime.now());
        Instant from = fromDate.map(date -> date.atStartOfDay(ZoneId.systemDefault()).toInstant()).orElse(Instant.EPOCH);
        Instant to = toDate.map(date -> date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()).orElse(Instant.now());
        HashMap<String, Object> result = new HashMap<>();
        Boolean forInterval = false;
        Instant debut = null;
        Instant fin = null;
        UserCriteria userCriteria = new UserCriteria();
        ImmatriculationRecupereeCriteria criteriaAG = new ImmatriculationRecupereeCriteria();
        ImmatriculationRecupereeCriteria criteriaAGUSER = new ImmatriculationRecupereeCriteria();
        NouvelleImmatriculationCriteria criterialoacal = new NouvelleImmatriculationCriteria();
        DeclarationCriteria declarationCriteriaAG = new DeclarationCriteria();
        DemandeServiceCriteria demandeServiceCriteria = new DemandeServiceCriteria();
        StringFilter regulariteFilter = new StringFilter();
        regulariteFilter.setContains("attestation régularité");
        demandeServiceCriteria.setTypeDemande(regulariteFilter);
        DMTCriteria dmtCriteria = new DMTCriteria();

        if (fromDate.isPresent() && toDate.isPresent()) {
            InstantFilter dateFilter = new InstantFilter();
            debut = fromDate.get().atStartOfDay().toInstant(ZoneOffset.UTC);
            fin = toDate.get().atStartOfDay().plusDays(1).toInstant(ZoneOffset.UTC);
            dateFilter.setGreaterThanOrEqual(debut);
            dateFilter.setLessThan(fin);
            criteriaAG.setDate(dateFilter);
            criteriaAGUSER.setDate(dateFilter);
            criterialoacal.setCreatedAt(dateFilter);
            declarationCriteriaAG.setCreateAt(dateFilter);
            demandeServiceCriteria.setCreatedAt(dateFilter);
            dmtCriteria.setDate(dateFilter);
            userCriteria.setCreatedDate(dateFilter);
            forInterval = true;
        }

        ArrayList<String> filteredType = new ArrayList<>();
        filteredType.add(Constants.EMPLOYEUR);
        filteredType.add(Constants.TYPE_AGENT);
        filteredType.add(Constants.SALARIE);

        List<DMTDTO> dmtList = dmtQueryService.findByCriteria(dmtCriteria);
        HashMap<String, Long> immatriculationsVal = new HashMap<>();
        HashMap<String, Object> attestations = new HashMap<>();
        HashMap<String, Object> attestationsval = new HashMap<>();
        HashMap<String, Object> dmts = new HashMap<>();
        HashMap<String, Object> dmtsval = new HashMap<>();
        HashMap<String, Object> localVal = new HashMap<>();
        HashMap<String, Object> immatOlds = new HashMap<>();
        HashMap<String, Object> immatlocals = new HashMap<>();
        HashMap<String, Long> declarationsVal = new HashMap<>();

        //comptes
        HashMap<String, Object> comptesMap = new HashMap<>();
        HashMap<String, Object> comptesParMois = new HashMap<>();
        HashMap<String, Object> empParMois = new HashMap<>();
        HashMap<String, Object> salParMois = new HashMap<>();
        HashMap<String, Object> agentParMois = new HashMap<>();
        HashMap<String, Long> employeursVal = new HashMap<>();
        HashMap<String, Long> agentsVal = new HashMap<>();
        HashMap<String, Long> salariesVal = new HashMap<>();
        HashMap<String, Long> comptesVal = new HashMap<>();

        // Pour les employeurs
        Long totalEmployeurs = userRepository.countByTypeCompteAndCreatedDateBetween(Constants.EMPLOYEUR, from, to);
        Long validesEmployeurs = userRepository.countByTypeCompteAndActivatedTrueAndCreatedDateBetween(Constants.EMPLOYEUR, from, to);
        employeursVal.put("total", totalEmployeurs);
        employeursVal.put("valides", validesEmployeurs);
        comptesMap.put("employeurs", employeursVal);

        // Pour les agents
        Long totalAgents = userRepository.countByTypeCompteAndCreatedDateBetween(Constants.TYPE_AGENT, from, to);
        Long validesAgents = userRepository.countByTypeCompteAndActivatedTrueAndCreatedDateBetween(Constants.TYPE_AGENT, from, to);
        agentsVal.put("total", totalAgents);
        agentsVal.put("valides", validesAgents);
        comptesMap.put("agents", agentsVal);

        // Pour les salariés
        Long totalSalaries = userRepository.countByTypeCompteAndCreatedDateBetween(Constants.SALARIE, from, to);
        Long validesSalaries = userRepository.countByTypeCompteAndActivatedTrueAndCreatedDateBetween(Constants.SALARIE, from, to);
        salariesVal.put("total", totalSalaries);
        salariesVal.put("valides", validesSalaries);
        comptesMap.put("salaries", salariesVal);

        // Pour les statistiques globales
        List<String> filteredType2 = Arrays.asList(Constants.EMPLOYEUR, Constants.TYPE_AGENT, Constants.SALARIE);
        Long totalComptes = userRepository.countByTypeCompteInAndCreatedDateBetween(filteredType2, from, to);
        Long validesComptes = userRepository.countByTypeCompteInAndActivatedTrueAndCreatedDateBetween(filteredType2, from, to);
        comptesVal.put("total", totalComptes);
        comptesVal.put("valides", validesComptes);
        comptesMap.put("global", comptesVal);

        empParMois.put("total", byMonth("compte", null, true, Constants.EMPLOYEUR, forInterval, debut, fin, null));
        empParMois.put("valides", byMonth("compte", null, true, Constants.EMPLOYEUR, forInterval, debut, fin, "true"));

        salParMois.put("total", byMonth("compte", null, true, Constants.SALARIE, forInterval, debut, fin, null));
        salParMois.put("valides", byMonth("compte", null, true, Constants.SALARIE, forInterval, debut, fin, "true"));

        agentParMois.put("total", byMonth("compte", null, true, Constants.TYPE_AGENT, forInterval, debut, fin, null));
        agentParMois.put("valides", byMonth("compte", null, true, Constants.TYPE_AGENT, forInterval, debut, fin, "true"));

        comptesParMois.put("employeurs", empParMois);
        comptesParMois.put("salaries", salParMois);
        comptesParMois.put("agent", agentParMois);
        comptesMap.put("parMois", comptesParMois);

        attestationsval.put("total", demandeServiceRepository.countByCreatedAtBetween(from, to));
        attestationsval.put("valides", demandeServiceRepository.countByStatutAndCreatedAtBetween("IMPRESSION", from, to));
        attestationsval.put("encours", demandeServiceRepository.countByStatutAndCreatedAtBetween("PENDING", from, to));
        attestationsval.put("rejetes", demandeServiceRepository.countByStatutContainingAndCreatedAtBetween("REJETER", from, to));
        attestationsval.put("enattentes", demandeServiceRepository.countByStatutNotInAndCreatedAtBetween(Arrays.asList("REJETER", "PENDING", "IMPRESSION"), from, to));
        attestations.put("global", attestationsval);
        attestations.put("parMois", byMonth("attestation", null, true, null, forInterval, debut, fin, null));

        dmtsval.put("total", (long) dmtList.size());
        dmts.put("global", dmtsval);
        dmts.put("parMois", byMonth("dmt", null, true, null, forInterval, debut, fin, null));


        localVal.put("toutes", nouvelleImmatriculationRepository.countByCreatedAtBetween(from, to));
        localVal.put("actives", nouvelleImmatriculationRepository.countByStatusdocAndCreatedAtBetween("IESV", from, to));
        localVal.put("nonsoumises", nouvelleImmatriculationRepository.countByStatusdocAndCreatedAtBetween("PAS_ENCORE_SOUMISE", from, to));
        localVal.put("encours", nouvelleImmatriculationRepository.countByStatusdocNotInAndCreatedAtBetween(Arrays.asList("PAS_ENCORE_SOUMISE", "IESV"), from, to));

        immatriculationsVal.put("toutes", immatriculationRecupereeRepository.countByCreatedAtBetween(from, to));
        immatriculationsVal.put("encours", immatriculationRecupereeRepository.countByStatusAndCreatedAtBetween("EN_COURS_DE_TRAITEMENT", from, to));
        immatriculationsVal.put("actives", immatriculationRecupereeRepository.countByStatusAndCreatedAtBetween("ACTIVEE", from, to));
        immatriculationsVal.put("rejetees", immatriculationRecupereeRepository.countByStatusAndCreatedAtBetween("REJETEE", from, to));
        immatriculationsVal.put("traitees", immatriculationRecupereeRepository.countByStatusInAndCreatedAtBetween(Arrays.asList("REJETEE", "ACTIVEE"), from, to));
        immatriculationsVal.put("byagent", immatriculationRecupereeRepository.countByStatusInAndCreatedAtBetween(Arrays.asList("REJETEE", "ACTIVEE"), from, to));
        immatlocals.put("global", localVal);
        immatlocals.put("parMois", byMonth("local", null, true, null, forInterval, debut, fin, null));

        immatOlds.put("global", immatriculationsVal);
        immatOlds.put("parMois", byMonth("old", null, true, null, forInterval, debut, fin, null));


        declarationsVal.put("toutes", declarationRepository.countByCreateAtBetween(from, to));
        declarationsVal.put("trimestrielles", declarationRepository.countByTypeDeclarationAndCreateAtBetween("TRIMESTRIEL", from, to));
        declarationsVal.put("mensuelles", declarationRepository.countByTypeDeclarationAndCreateAtBetween("MENSUEL", from, to));
        declarationsVal.put("encours", declarationRepository.countByStatusAndCreateAtBetween("SOUMISE", from, to));
        declarationsVal.put("actives", declarationRepository.countByStatusAndCreateAtBetween("TRAITER", from, to));
        declarationsVal.put("rejetees", declarationRepository.countByStatusAndCreateAtBetween("INVALIDE", from, to));
        declarationsVal.put("traitees", declarationRepository.countByStatusInAndCreateAtBetween(Arrays.asList("INVALIDE", "TRAITER"), from, to));
        declarationsVal.put("enmasse", declarationRepository.countByIsUploadedTrueAndCreateAtBetween(from, to));
        declarationsVal.put("manuelles", declarationRepository.countByIsUploadedFalseAndCreateAtBetween(from, to));



        HashMap<String, Object> imm = new HashMap<>();
        imm.put("locales", immatlocals);
        imm.put("olds", immatOlds);
        HashMap<String, Object> dec = new HashMap<>();
        dec.put("global", declarationsVal);
        dec.put("parMois", byMonth("declaration", null, true, null, forInterval, debut, fin, null));
        HashMap<String, Object> decParTypeEtParMois = new HashMap<>();
        decParTypeEtParMois.put("trimestrielles", byMonth("declaration", null, true, "TRIMESTRIEL", forInterval, debut, fin, null));
        decParTypeEtParMois.put("mensuelles", byMonth("declaration", null, true, "MENSUEL", forInterval, debut, fin, null));

        dec.put("parTypeEtParMois", decParTypeEtParMois);
        dec.put("parAgences", declarationsGroupByAgence(fromDate, toDate));

        result.put("immatriculations", imm);
        result.put("declarations", dec);
        result.put("attestations", attestations);
        result.put("dmt", dmts);
        result.put("comptes", comptesMap);
        log.info("Fin calcule des statistiques Admin : "+LocalDateTime.now());
        return result;
    }

    public Map<String, Long> allMonths(Boolean isDec, User user, Boolean isAdmin, String type, Boolean forInterval, Instant from, Instant to) {
        HashMap<Integer, Long> result = new HashMap<>();
        Map<String, Long> out = new LinkedHashMap<>();
        Map<Integer, String> mois = new LinkedHashMap<>();
        mois.put(1, "Janvier");
        mois.put(2, "Février");
        mois.put(3, "Mars");
        mois.put(4, "Avril");
        mois.put(5, "Mai");
        mois.put(6, "Juin");
        mois.put(7, "Juillet");
        mois.put(8, "Août");
        mois.put(9, "Septembre");
        mois.put(10, "Octobre");
        mois.put(11, "Novembre");
        mois.put(12, "Décembre");

        CriteriaBuilder builder;
        if (!isAdmin) {
            if (isDec) {
                CriteriaQuery<Declaration> query;
                Root<Declaration> root;
                builder = entityManager.getCriteriaBuilder();
                query = builder.createQuery(Declaration.class);
                root = query.from(Declaration.class);
                int annee = LocalDate.now().getYear();
                if (forInterval) {
                    if (!StringUtils.isEmpty(type)) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    builder.equal(root.get("typeDeclaration"), type),
                                    user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                } else {
                    if (!StringUtils.isEmpty(type)) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee),
                                    builder.equal(root.get("typeDeclaration"), type),
                                    user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee),
                                    user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                }

            } else {
                CriteriaQuery<OldImmatriculation> query;
                Root<OldImmatriculation> root;
                builder = entityManager.getCriteriaBuilder();
                query = builder.createQuery(OldImmatriculation.class);
                root = query.from(OldImmatriculation.class);
                int annee = LocalDate.now().getYear();
                if (forInterval) {
                    for (Map.Entry<Integer, String> m : mois.entrySet()) {
                        query.select(root).where(
                                builder.between(root.get("date"), from, to),
                                builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()),
                                user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("agenceCSS"), user.getAgence()) : builder.equal(root.get("agenceIPRES"), user.getAgence()));
                        result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                        out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                    }
                } else {
                    for (Map.Entry<Integer, String> m : mois.entrySet()) {
                        query.select(root).where(
                                builder.between(root.get("date"), from, to),
                                builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()),
                                user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("agenceCSS"), user.getAgence()) : builder.equal(root.get("agenceIPRES"), user.getAgence()));
                        result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                        out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                    }
                }
            }
        } else {
            if (isDec) {
                CriteriaQuery<Declaration> query;
                Root<Declaration> root;
                builder = entityManager.getCriteriaBuilder();
                query = builder.createQuery(Declaration.class);
                root = query.from(Declaration.class);
                int annee = LocalDate.now().getYear();
                if (forInterval) {
                    if (!StringUtils.isEmpty(type)) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    builder.equal(root.get("typeDeclaration"), type));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                } else {
                    if (!StringUtils.isEmpty(type)) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee),
                                    builder.equal(root.get("typeDeclaration"), type));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                }

            } else {
                CriteriaQuery<OldImmatriculation> query;
                Root<OldImmatriculation> root;
                builder = entityManager.getCriteriaBuilder();
                query = builder.createQuery(OldImmatriculation.class);
                root = query.from(OldImmatriculation.class);
                int annee = LocalDate.now().getYear();
                if (forInterval) {
                    for (Map.Entry<Integer, String> m : mois.entrySet()) {
                        query.select(root).where(
                                builder.between(root.get("date"), from, to),
                                builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()));
                        result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                        out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                    }
                } else {
                    for (Map.Entry<Integer, String> m : mois.entrySet()) {
                        query.select(root).where(
                                builder.between(root.get("date"), from, to),
                                builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()));
                        result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                        out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                    }
                }
            }
        }
        return out;
    }

    public Map<String, Long> byMonth(String typeEntity, User user, Boolean isAdmin, String type, Boolean forInterval, Instant from, Instant to, String forActiveOnly) {
        HashMap<Integer, Long> result = new HashMap<>();
        Map<String, Long> out = new LinkedHashMap<>();
        Map<Integer, String> mois = new LinkedHashMap<>();
        mois.put(1, "Janvier");
        mois.put(2, "Février");
        mois.put(3, "Mars");
        mois.put(4, "Avril");
        mois.put(5, "Mai");
        mois.put(6, "Juin");
        mois.put(7, "Juillet");
        mois.put(8, "Août");
        mois.put(9, "Septembre");
        mois.put(10, "Octobre");
        mois.put(11, "Novembre");
        mois.put(12, "Décembre");

        CriteriaBuilder builder;
        CriteriaQuery query = null;
        Root root = null;
        int annee = LocalDate.now().getYear();
        if (!isAdmin) {
            switch (typeEntity) {
                case "declaration":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(Declaration.class);
                    root = query.from(Declaration.class);

                    if (forInterval) {
                        if (!StringUtils.isEmpty(type)) {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.between(root.get("createAt"), from, to),
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        builder.equal(root.get("typeDeclaration"), type),
                                        user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        } else {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.between(root.get("createAt"), from, to),
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        }
                    } else {
                        if (!StringUtils.isEmpty(type)) {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee),
                                        builder.equal(root.get("typeDeclaration"), type),
                                        user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        } else {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee),
                                        user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("codeAgenceCSS"), user.getAgence()) : builder.equal(root.get("codeAgenceIPRES"), user.getAgence()));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        }
                    }
                    break;
                case "old":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(OldImmatriculation.class);
                    root = query.from(OldImmatriculation.class);
                    if (forInterval) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("date"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()),
                                    user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("agenceCSS"), user.getAgence()) : builder.equal(root.get("agenceIPRES"), user.getAgence()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("date")), annee),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()),
                                    user.getInstitution().equalsIgnoreCase("CSS") ? builder.equal(root.get("agenceCSS"), user.getAgence()) : builder.equal(root.get("agenceIPRES"), user.getAgence()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                    break;
                case "local":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(Localimmatriculation.class);
                    root = query.from(Localimmatriculation.class);
                    if (forInterval) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createdAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createdAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createdAt")), annee),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createdAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {
            switch (typeEntity) {
                case "declaration":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(Declaration.class);
                    root = query.from(Declaration.class);
                    if (forInterval) {
                        if (!StringUtils.isEmpty(type)) {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.between(root.get("createAt"), from, to),
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        builder.equal(root.get("typeDeclaration"), type));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        } else {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.between(root.get("createAt"), from, to),
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        }
                    } else {
                        if (!StringUtils.isEmpty(type)) {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee),
                                        builder.equal(root.get("typeDeclaration"), type));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        } else {
                            for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                query.select(root).where(
                                        builder.equal(builder.function("MONTH", Integer.class, root.get("createAt")), m.getKey()),
                                        builder.equal(builder.function("YEAR", Integer.class, root.get("createAt")), annee));
                                result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                            }
                        }
                    }
                    break;
                case "old":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(OldImmatriculation.class);
                    root = query.from(OldImmatriculation.class);
                    if (forInterval) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("date"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("date")), annee),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                    break;
                case "local":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(Localimmatriculation.class);
                    root = query.from(Localimmatriculation.class);
                    if (forInterval) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createdAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createdAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createdAt")), annee),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createdAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                    break;
                case "dmt":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(DMT.class);
                    root = query.from(DMT.class);
                    if (forInterval) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("date"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("date")), annee),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("date")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                    break;
                case "attestation":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(DemandeService.class);
                    root = query.from(DemandeService.class);
                    if (forInterval) {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.between(root.get("createdAt"), from, to),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createdAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    } else {
                        for (Map.Entry<Integer, String> m : mois.entrySet()) {
                            query.select(root).where(
                                    builder.equal(builder.function("YEAR", Integer.class, root.get("createdAt")), annee),
                                    builder.equal(builder.function("MONTH", Integer.class, root.get("createdAt")), m.getKey()));
                            result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                            out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                        }
                    }
                    break;
                case "compte":
                    builder = entityManager.getCriteriaBuilder();
                    query = builder.createQuery(User.class);
                    root = query.from(User.class);
                    if (StringUtils.isEmpty(forActiveOnly)) {
                        if (StringUtils.isEmpty(type)) {
                            if (forInterval) {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.between(root.get("createdDate"), from, to),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            } else {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), annee),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            }
                        } else {
                            if (forInterval) {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.between(root.get("createdDate"), from, to),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()),
                                            builder.equal(root.get("typeCompte"), type));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            } else {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), annee),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()),
                                            builder.equal(root.get("typeCompte"), type));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            }
                        }
                    } else {
                        if (StringUtils.isEmpty(type)) {
                            if (forInterval) {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.between(root.get("createdDate"), from, to),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            } else {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), annee),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            }
                        } else {
                            if (forInterval) {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.between(root.get("createdDate"), from, to),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()),
                                            builder.equal(root.get("typeCompte"), type),
                                            builder.equal(root.get("activated"), true));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            } else {
                                for (Map.Entry<Integer, String> m : mois.entrySet()) {
                                    query.select(root).where(
                                            builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), annee),
                                            builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), m.getKey()),
                                            builder.equal(root.get("typeCompte"), type),
                                            builder.equal(root.get("activated"), true));
                                    result.put(m.getKey(), (long) entityManager.createQuery(query).getResultList().size());
                                    out.put(m.getValue(), (long) entityManager.createQuery(query).getResultList().size());
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return out;
    }

    public Map<String, Long> declarationsGroupByAgence(Optional<LocalDate> fromDate, Optional<LocalDate> toDate) {
        DeclarationCriteria declarationCriteria = new DeclarationCriteria();
        if (fromDate.isPresent() && toDate.isPresent()) {
            Instant debut = fromDate.get().atStartOfDay().toInstant(ZoneOffset.UTC);
            Instant fin = toDate.get().atStartOfDay().plusDays(1).toInstant(ZoneOffset.UTC);

            InstantFilter dateFilter = new InstantFilter();
            dateFilter.setGreaterThanOrEqual(debut);
            dateFilter.setLessThan(fin);

            declarationCriteria.setCreateAt(dateFilter);
        }
        List<Declaration> declarations = declarationQueryService.findByCriteria(declarationCriteria);

        Map<String, Long> countedCSS = declarations.stream().collect(Collectors.groupingBy(declaration ->
                !StringUtils.isEmpty(declaration.getCodeAgenceCSS()) && !StringUtils.isEmpty(declaration.getCodeAgenceIPRES()) ? declaration.getCodeAgenceCSS() : "nonlies", Collectors.counting()));
        Map<String, Long> countedIPRES = declarations.stream().collect(Collectors.groupingBy(declaration ->
                !StringUtils.isEmpty(declaration.getCodeAgenceCSS()) && !StringUtils.isEmpty(declaration.getCodeAgenceIPRES()) ? declaration.getCodeAgenceIPRES() : "nonlies", Collectors.counting()));
        Map<String, Long> all = new LinkedHashMap<>();
        countedCSS.remove("nonlies");
        countedCSS.forEach((key, value) -> {
            all.merge(key, value, Long::sum);
        });
        countedIPRES.forEach((key, value) -> {
            all.merge(key, value, Long::sum);
        });

        return all.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
