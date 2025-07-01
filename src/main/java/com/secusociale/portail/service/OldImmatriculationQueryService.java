package com.secusociale.portail.service;

import com.secusociale.portail.domain.OldImmatriculation;
import com.secusociale.portail.domain.OldImmatriculation_;
import com.secusociale.portail.repository.OldImmatriculationRepository;
import com.secusociale.portail.service.dto.OldImmatriculationCriteria;
import com.secusociale.portail.service.dto.OldImmatriculationDTO;
import com.secusociale.portail.service.dto.custom.AncienneImmatriculationDTO;
import com.secusociale.portail.service.mapper.AncienneImmatriculationMapper;
import com.secusociale.portail.service.mapper.OldImmatriculationMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for {@link OldImmatriculation} entities in the database.
 * The main input is a {@link OldImmatriculationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OldImmatriculationDTO} or a {@link Page} of {@link OldImmatriculationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OldImmatriculationQueryService extends QueryService<OldImmatriculation> {

    private final Logger log = LoggerFactory.getLogger(OldImmatriculationQueryService.class);

    private final OldImmatriculationRepository oldImmatriculationRepository;

    private final OldImmatriculationMapper oldImmatriculationMapper;

    private final AncienneImmatriculationMapper ancienneImmatriculationMapper;

    public OldImmatriculationQueryService(OldImmatriculationRepository oldImmatriculationRepository, OldImmatriculationMapper oldImmatriculationMapper, AncienneImmatriculationMapper ancienneImmatriculationMapper) {
        this.oldImmatriculationRepository = oldImmatriculationRepository;
        this.oldImmatriculationMapper = oldImmatriculationMapper;
        this.ancienneImmatriculationMapper = ancienneImmatriculationMapper;
    }

    /**
     * Return a {@link List} of {@link OldImmatriculationDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AncienneImmatriculationDTO> findByCriteria(OldImmatriculationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OldImmatriculation> specification = createSpecification(criteria);
        return ancienneImmatriculationMapper.toDto(oldImmatriculationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OldImmatriculationDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AncienneImmatriculationDTO> findByCriteria(OldImmatriculationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OldImmatriculation> specification = createSpecification(criteria);
        return oldImmatriculationRepository.findAll(specification, page)
            .map(ancienneImmatriculationMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<AncienneImmatriculationDTO> findAllByCriteria(OldImmatriculationCriteria criteria) {
        log.debug("find by criteria : {}, page: {}", criteria);
        final Specification<OldImmatriculation> specification = createSpecification(criteria);
        return ancienneImmatriculationMapper.toDto(oldImmatriculationRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OldImmatriculationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OldImmatriculation> specification = createSpecification(criteria);
        return oldImmatriculationRepository.count(specification);
    }

    /**
     * Function to convert {@link OldImmatriculationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OldImmatriculation> createSpecification(OldImmatriculationCriteria criteria) {
        Specification<OldImmatriculation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OldImmatriculation_.id));
            }
            if (criteria.getTypeIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIdentifiant(), OldImmatriculation_.typeIdentifiant));
            }
            if (criteria.getNumeroIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroIdentifiant(), OldImmatriculation_.numeroIdentifiant));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), OldImmatriculation_.numeroUnique));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), OldImmatriculation_.status));
            }
            if (criteria.getMoyenConfirmation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoyenConfirmation(), OldImmatriculation_.moyenConfirmation));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), OldImmatriculation_.date));
            }
            if (criteria.getNumeroRC() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroRC(), OldImmatriculation_.numeroRC));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), OldImmatriculation_.userId));
            }
            if (criteria.getAgenceCSS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceCSS(), OldImmatriculation_.agenceCSS));
            }
            if (criteria.getAgenceIPRES() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceIPRES(), OldImmatriculation_.agenceIPRES));
            }
            if (criteria.getAgentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgentId(), OldImmatriculation_.agentId));
            }
        }
        return specification;
    }
}
