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

import com.secusociale.portail.domain.PieceJointe;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.PieceJointeRepository;
import com.secusociale.portail.service.dto.PieceJointeCriteria;
import com.secusociale.portail.service.dto.PieceJointeDTO;
import com.secusociale.portail.service.mapper.PieceJointeMapper;

/**
 * Service for executing complex queries for {@link PieceJointe} entities in the database.
 * The main input is a {@link PieceJointeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PieceJointeDTO} or a {@link Page} of {@link PieceJointeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PieceJointeQueryService extends QueryService<PieceJointe> {

    private final Logger log = LoggerFactory.getLogger(PieceJointeQueryService.class);

    private final PieceJointeRepository pieceJointeRepository;

    private final PieceJointeMapper pieceJointeMapper;

    public PieceJointeQueryService(PieceJointeRepository pieceJointeRepository, PieceJointeMapper pieceJointeMapper) {
        this.pieceJointeRepository = pieceJointeRepository;
        this.pieceJointeMapper = pieceJointeMapper;
    }

    /**
     * Return a {@link List} of {@link PieceJointeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PieceJointeDTO> findByCriteria(PieceJointeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PieceJointe> specification = createSpecification(criteria);
        return pieceJointeMapper.toDto(pieceJointeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PieceJointeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PieceJointeDTO> findByCriteria(PieceJointeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PieceJointe> specification = createSpecification(criteria);
        return pieceJointeRepository.findAll(specification, page)
            .map(pieceJointeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PieceJointeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PieceJointe> specification = createSpecification(criteria);
        return pieceJointeRepository.count(specification);
    }

    /**
     * Function to convert {@link PieceJointeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PieceJointe> createSpecification(PieceJointeCriteria criteria) {
        Specification<PieceJointe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PieceJointe_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PieceJointe_.name));
            }
            if (criteria.getExtension() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExtension(), PieceJointe_.extension));
            }
            if (criteria.getEntityType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEntityType(), PieceJointe_.entityType));
            }
            if (criteria.getEntityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEntityId(), PieceJointe_.entityId));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), PieceJointe_.createdAt));
            }
            if (criteria.getUserCreated() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserCreated(), PieceJointe_.userCreated));
            }
            if (criteria.getChildId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChildId(), PieceJointe_.childId));
            }
        }
        return specification;
    }
}
