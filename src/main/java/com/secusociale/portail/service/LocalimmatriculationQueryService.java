package com.secusociale.portail.service;

import com.secusociale.portail.domain.*;
import com.secusociale.portail.repository.LocalimmatriculationRepository;
import com.secusociale.portail.service.dto.LocalimmatriculationCriteria;
import com.secusociale.portail.service.dto.LocalimmatriculationDTO;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link Localimmatriculation} entities in the database.
 * The main input is a {@link LocalimmatriculationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LocalimmatriculationDTO} or a {@link Page} of {@link LocalimmatriculationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LocalimmatriculationQueryService extends QueryService<Localimmatriculation> {

    private final Logger log = LoggerFactory.getLogger(LocalimmatriculationQueryService.class);

    private final LocalimmatriculationRepository localimmatriculationRepository;

//    private final LocalimmatriculationMapper localimmatriculationMapper;

    public LocalimmatriculationQueryService(LocalimmatriculationRepository localimmatriculationRepository) {
        this.localimmatriculationRepository = localimmatriculationRepository;
    }


    /**
     * Return a {@link List} of {@link LocalimmatriculationDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Localimmatriculation> findByCriteria(LocalimmatriculationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Localimmatriculation> specification = createSpecification(criteria);
        return localimmatriculationRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link LocalimmatriculationDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Localimmatriculation> findByCriteria(LocalimmatriculationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Localimmatriculation> specification = createSpecification(criteria);
        return localimmatriculationRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public List<Localimmatriculation> findAllByCriteria(LocalimmatriculationCriteria criteria) {
        log.debug("findAll by criteria : {}", criteria);
        final Specification<Localimmatriculation> specification = createSpecification(criteria);
        return localimmatriculationRepository.findAll(specification);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LocalimmatriculationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Localimmatriculation> specification = createSpecification(criteria);
        return localimmatriculationRepository.count(specification);
    }

    /**
     * Function to convert {@link LocalimmatriculationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Localimmatriculation> createSpecification(LocalimmatriculationCriteria criteria) {
        Specification<Localimmatriculation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Localimmatriculation_.id));
            }
            if (criteria.getTypeIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIdentifiant(), Localimmatriculation_.typeIdentifiant));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Localimmatriculation_.type));
            }
            if (criteria.getNumdoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumdoc(), Localimmatriculation_.numdoc));
            }
            if (criteria.getStatusdoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusdoc(), Localimmatriculation_.statusdoc));
            }
            if (criteria.getStatusdesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusdesc(), Localimmatriculation_.statusdesc));
            }
            if (criteria.getPayload() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPayload(), Localimmatriculation_.payload));
            }
            if (criteria.getUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUser(), Localimmatriculation_.user));
            }
            if (criteria.getEmployeur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmployeur(), Localimmatriculation_.employeur));
            }
            if (criteria.getIsSubmit() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSubmit(), Localimmatriculation_.isSubmit));
            }
            if (criteria.getNinea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNinea(), Localimmatriculation_.ninea));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Localimmatriculation_.createdAt));
            }
        }
        return specification;
    }
}
