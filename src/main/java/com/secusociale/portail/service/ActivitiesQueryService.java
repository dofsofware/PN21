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

import com.secusociale.portail.domain.Activities;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.ActivitiesRepository;
import com.secusociale.portail.service.dto.ActivitiesCriteria;
import com.secusociale.portail.service.dto.ActivitiesDTO;
import com.secusociale.portail.service.mapper.ActivitiesMapper;

/**
 * Service for executing complex queries for {@link Activities} entities in the database.
 * The main input is a {@link ActivitiesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActivitiesDTO} or a {@link Page} of {@link ActivitiesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActivitiesQueryService extends QueryService<Activities> {

    private final Logger log = LoggerFactory.getLogger(ActivitiesQueryService.class);

    private final ActivitiesRepository activitiesRepository;

    private final ActivitiesMapper activitiesMapper;

    public ActivitiesQueryService(ActivitiesRepository activitiesRepository, ActivitiesMapper activitiesMapper) {
        this.activitiesRepository = activitiesRepository;
        this.activitiesMapper = activitiesMapper;
    }

    /**
     * Return a {@link List} of {@link ActivitiesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActivitiesDTO> findByCriteria(ActivitiesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Activities> specification = createSpecification(criteria);
        return activitiesMapper.toDto(activitiesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActivitiesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivitiesDTO> findByCriteria(ActivitiesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Activities> specification = createSpecification(criteria);
        return activitiesRepository.findAll(specification, page)
            .map(activitiesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActivitiesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Activities> specification = createSpecification(criteria);
        return activitiesRepository.count(specification);
    }

    /**
     * Function to convert {@link ActivitiesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Activities> createSpecification(ActivitiesCriteria criteria) {
        Specification<Activities> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Activities_.id));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), Activities_.userId));
            }
            if (criteria.getOperation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperation(), Activities_.operation));
            }
            if (criteria.getDateOperation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateOperation(), Activities_.dateOperation));
            }
            if (criteria.getTypeObjet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeObjet(), Activities_.typeObjet));
            }
        }
        return specification;
    }
}
