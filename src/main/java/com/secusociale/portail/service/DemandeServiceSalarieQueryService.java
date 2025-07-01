package com.secusociale.portail.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.secusociale.portail.domain.DemandeServiceSalarie;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.DemandeServiceSalarieRepository;
import com.secusociale.portail.service.dto.DemandeServiceSalarieCriteria;
import com.secusociale.portail.service.dto.DemandeServiceSalarieDTO;
import com.secusociale.portail.service.mapper.DemandeServiceSalarieMapper;
import org.springframework.util.StringUtils;

/**
 * Service for executing complex queries for {@link DemandeServiceSalarie} entities in the database.
 * The main input is a {@link DemandeServiceSalarieCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DemandeServiceSalarieDTO} or a {@link Page} of {@link DemandeServiceSalarieDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DemandeServiceSalarieQueryService extends QueryService<DemandeServiceSalarie> {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceSalarieQueryService.class);

    private final DemandeServiceSalarieRepository demandeServiceSalarieRepository;

    private final DemandeServiceSalarieMapper demandeServiceSalarieMapper;

    public DemandeServiceSalarieQueryService(DemandeServiceSalarieRepository demandeServiceSalarieRepository, DemandeServiceSalarieMapper demandeServiceSalarieMapper) {
        this.demandeServiceSalarieRepository = demandeServiceSalarieRepository;
        this.demandeServiceSalarieMapper = demandeServiceSalarieMapper;
    }

    /**
     * Return a {@link List} of {@link DemandeServiceSalarieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DemandeServiceSalarieDTO> findByCriteria(DemandeServiceSalarieCriteria criteria) {
        log.debug("find by criteria : {}", criteria);

        // Créer la spécification basée sur les critères fournis
        final Specification<DemandeServiceSalarie> specification = createSpecification(criteria);

        // Récupérer les résultats en utilisant la spécification
        List<DemandeServiceSalarie> resultList = demandeServiceSalarieRepository.findAll(specification);

        // Mapper les résultats vers DemandeServiceSalarieDTO
        return demandeServiceSalarieMapper.toDto(resultList);
    }

    /**
     * Return a {@link Page} of {@link DemandeServiceSalarieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandeServiceSalarieDTO> findByCriteria(DemandeServiceSalarieCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        if (page.getSort().isEmpty()) {
            page = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(Sort.Order.desc("date")));
        }
        final Specification<DemandeServiceSalarie> specification = createSpecification(criteria);
        return demandeServiceSalarieRepository.findAll(specification, page)
            .map(demandeServiceSalarieMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DemandeServiceSalarieCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DemandeServiceSalarie> specification = createSpecification(criteria);
        return demandeServiceSalarieRepository.count(specification);
    }

    /**
     * Function to convert {@link DemandeServiceSalarieCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DemandeServiceSalarie> createSpecification(DemandeServiceSalarieCriteria criteria) {
        Specification<DemandeServiceSalarie> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DemandeServiceSalarie_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), DemandeServiceSalarie_.type));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatut(), DemandeServiceSalarie_.statut));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), DemandeServiceSalarie_.date));
            }
            if (criteria.getDateTraitement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTraitement(), DemandeServiceSalarie_.dateTraitement));
            }
            if (criteria.getNumeroUniqueEmployeur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUniqueEmployeur(), DemandeServiceSalarie_.numeroUniqueEmployeur));
            }
            if (criteria.getGestionnaireId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGestionnaireId(), DemandeServiceSalarie_.gestionnaireId));
            }
            if (criteria.getSalarieId() != null) {
                specification = specification.and(buildSpecification(criteria.getSalarieId(),
                    root -> root.join(DemandeServiceSalarie_.salarie, JoinType.LEFT).get(Salarie_.id)));
            }

            // Recherche globale
            if (criteria.getGlobalSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGlobalSearch(), DemandeServiceSalarie_.type));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), DemandeServiceSalarie_.statut));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), DemandeServiceSalarie_.motifOuDescription ));
            }
        }
        return specification;
    }
}
