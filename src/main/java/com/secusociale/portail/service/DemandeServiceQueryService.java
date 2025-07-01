package com.secusociale.portail.service;

import com.secusociale.portail.domain.DemandeService;
import com.secusociale.portail.domain.DemandeServiceSalarie_;
import com.secusociale.portail.domain.DemandeService_;
import com.secusociale.portail.repository.NewDemandeServiceRepository;
import com.secusociale.portail.service.dto.DemandeServiceCriteria;
import com.secusociale.portail.service.dto.DemandeServiceDTO;
import com.secusociale.portail.service.mapper.DemandeServiceMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Service for executing complex queries for {@link DemandeService} entities in the database.
 * The main input is a {@link DemandeServiceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DemandeServiceDTO} or a {@link Page} of {@link DemandeServiceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DemandeServiceQueryService extends QueryService<DemandeService> {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceQueryService.class);

    private final NewDemandeServiceRepository demandeServiceRepository;

    private final DemandeServiceMapper demandeServiceMapper;

    public DemandeServiceQueryService(NewDemandeServiceRepository demandeServiceRepository, DemandeServiceMapper demandeServiceMapper) {
        this.demandeServiceRepository = demandeServiceRepository;
        this.demandeServiceMapper = demandeServiceMapper;
    }

    /**
     * Return a {@link List} of {@link DemandeServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DemandeServiceDTO> findByCriteria(DemandeServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DemandeService> specification = createSpecification(criteria);
        return demandeServiceMapper.toDto(demandeServiceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DemandeServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandeServiceDTO> findByCriteria(DemandeServiceCriteria criteria, Pageable page) {
        log.debug("Recherche par critères : {}, page : {}", criteria, page);
        final Specification<DemandeService> specification = createSpecification(criteria);
        Page<DemandeService> demandeServicePage = demandeServiceRepository.findAll(specification, page);
        Page<DemandeServiceDTO> demandeServiceDTOs = demandeServicePage.map(demandeServiceMapper::toDto);
        log.debug("Résultats trouvés : {}", demandeServiceDTOs.getContent());
        return demandeServiceDTOs;
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DemandeServiceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DemandeService> specification = createSpecification(criteria);
        return demandeServiceRepository.count(specification);
    }

    /**
     * Function to convert {@link DemandeServiceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DemandeService> createSpecification(DemandeServiceCriteria criteria) {
        Specification<DemandeService> specification = Specification.where(null);
        if (criteria != null) {
            // Recherche globale
            if (criteria.getGlobalSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGlobalSearch(), DemandeService_.numeroUnique));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), DemandeService_.statut));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), DemandeService_.typeDemande));
            }

            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DemandeService_.id));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatut(), DemandeService_.statut));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), DemandeService_.createdAt));
            }
            if (criteria.getEmployeur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmployeur(), DemandeService_.employeur));
            }
            if (criteria.getTypeDemande() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeDemande(), DemandeService_.typeDemande));
            }
            if (criteria.getIdDossier() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdDossier(), DemandeService_.idDossier));
            }
            if (criteria.getReponseBrute() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReponseBrute(), DemandeService_.reponsebrute));
            }
        }

        return specification;
    }
}
