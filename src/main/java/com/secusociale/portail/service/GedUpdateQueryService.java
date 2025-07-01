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

import com.secusociale.portail.domain.GedUpdate;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.GedUpdateRepository;
import com.secusociale.portail.service.dto.GedUpdateCriteria;
import com.secusociale.portail.service.dto.GedUpdateDTO;
import com.secusociale.portail.service.mapper.GedUpdateMapper;

/**
 * Service for executing complex queries for {@link GedUpdate} entities in the database.
 * The main input is a {@link GedUpdateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GedUpdateDTO} or a {@link Page} of {@link GedUpdateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GedUpdateQueryService extends QueryService<GedUpdate> {

    private final Logger log = LoggerFactory.getLogger(GedUpdateQueryService.class);

    private final GedUpdateRepository gedUpdateRepository;

    private final GedUpdateMapper gedUpdateMapper;

    public GedUpdateQueryService(GedUpdateRepository gedUpdateRepository, GedUpdateMapper gedUpdateMapper) {
        this.gedUpdateRepository = gedUpdateRepository;
        this.gedUpdateMapper = gedUpdateMapper;
    }

    /**
     * Return a {@link List} of {@link GedUpdateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GedUpdateDTO> findByCriteria(GedUpdateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GedUpdate> specification = createSpecification(criteria);
        return gedUpdateMapper.toDto(gedUpdateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GedUpdateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GedUpdateDTO> findByCriteria(GedUpdateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GedUpdate> specification = createSpecification(criteria);
        return gedUpdateRepository.findAll(specification, page)
            .map(gedUpdateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GedUpdateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<GedUpdate> specification = createSpecification(criteria);
        return gedUpdateRepository.count(specification);
    }

    /**
     * Function to convert {@link GedUpdateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<GedUpdate> createSpecification(GedUpdateCriteria criteria) {
        Specification<GedUpdate> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), GedUpdate_.id));
            }
            if (criteria.getOldPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOldPath(), GedUpdate_.oldPath));
            }
            if (criteria.getNewPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNewPath(), GedUpdate_.newPath));
            }
            if (criteria.getIdOld() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdOld(), GedUpdate_.idOld));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), GedUpdate_.numeroUnique));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), GedUpdate_.date));
            }
        }
        return specification;
    }
}
