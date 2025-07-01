package com.secusociale.portail.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.secusociale.portail.domain.SuiviJob;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.SuiviJobRepository;
import com.secusociale.portail.service.dto.SuiviJobCriteria;
import com.secusociale.portail.service.dto.SuiviJobDTO;
import com.secusociale.portail.service.mapper.SuiviJobMapper;

/**
 * Service for executing complex queries for {@link SuiviJob} entities in the database.
 * The main input is a {@link SuiviJobCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SuiviJobDTO} or a {@link Page} of {@link SuiviJobDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SuiviJobQueryService extends QueryService<SuiviJob> {

    private final Logger log = LoggerFactory.getLogger(SuiviJobQueryService.class);

    private final SuiviJobRepository suiviJobRepository;

    private final SuiviJobMapper suiviJobMapper;

    public SuiviJobQueryService(SuiviJobRepository suiviJobRepository, SuiviJobMapper suiviJobMapper) {
        this.suiviJobRepository = suiviJobRepository;
        this.suiviJobMapper = suiviJobMapper;
    }

    /**
     * Return a {@link List} of {@link SuiviJobDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SuiviJobDTO> findByCriteria(SuiviJobCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SuiviJob> specification = createSpecification(criteria);
        return suiviJobMapper.toDto(suiviJobRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SuiviJobDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SuiviJobDTO> findByCriteria(SuiviJobCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SuiviJob> specification = createSpecification(criteria);
        return suiviJobRepository.findAll(specification, page)
            .map(suiviJobMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SuiviJobCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SuiviJob> specification = createSpecification(criteria);
        return suiviJobRepository.count(specification);
    }

    /**
     * Function to convert {@link SuiviJobCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SuiviJob> createSpecification(SuiviJobCriteria criteria) {
        Specification<SuiviJob> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SuiviJob_.id));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), SuiviJob_.nom));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatut(), SuiviJob_.statut));
            }
            if (criteria.getDemarreLe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDemarreLe(), SuiviJob_.demarreLe));
            }
            if (criteria.getTermineLe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTermineLe(), SuiviJob_.termineLe));
            }
            if (criteria.getDemarrePar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemarrePar(), SuiviJob_.demarrePar));
            }
        }
        return specification;
    }
}
