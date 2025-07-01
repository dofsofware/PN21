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

import com.secusociale.portail.domain.ImmatriculationRecuperee;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeCriteria;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeDTO;
import com.secusociale.portail.service.mapper.ImmatriculationRecupereeMapper;

/**
 * Service for executing complex queries for {@link ImmatriculationRecuperee} entities in the database.
 * The main input is a {@link ImmatriculationRecupereeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ImmatriculationRecupereeDTO} or a {@link Page} of {@link ImmatriculationRecupereeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ImmatriculationRecupereeQueryService extends QueryService<ImmatriculationRecuperee> {

    private final Logger log = LoggerFactory.getLogger(ImmatriculationRecupereeQueryService.class);

    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;

    private final ImmatriculationRecupereeMapper immatriculationRecupereeMapper;

    public ImmatriculationRecupereeQueryService(ImmatriculationRecupereeRepository immatriculationRecupereeRepository, ImmatriculationRecupereeMapper immatriculationRecupereeMapper) {
        this.immatriculationRecupereeRepository = immatriculationRecupereeRepository;
        this.immatriculationRecupereeMapper = immatriculationRecupereeMapper;
    }

    /**
     * Return a {@link List} of {@link ImmatriculationRecupereeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ImmatriculationRecupereeDTO> findByCriteria(ImmatriculationRecupereeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ImmatriculationRecuperee> specification = createSpecification(criteria);
        return immatriculationRecupereeMapper.toDto(immatriculationRecupereeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ImmatriculationRecupereeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ImmatriculationRecupereeDTO> findByCriteria(ImmatriculationRecupereeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ImmatriculationRecuperee> specification = createSpecification(criteria);
        return immatriculationRecupereeRepository.findAll(specification, page)
            .map(immatriculationRecupereeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ImmatriculationRecupereeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ImmatriculationRecuperee> specification = createSpecification(criteria);
        return immatriculationRecupereeRepository.count(specification);
    }

    /**
     * Function to convert {@link ImmatriculationRecupereeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ImmatriculationRecuperee> createSpecification(ImmatriculationRecupereeCriteria criteria) {
        Specification<ImmatriculationRecuperee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ImmatriculationRecuperee_.id));
            }
            if (criteria.getMoyenConfirmation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoyenConfirmation(), ImmatriculationRecuperee_.moyenConfirmation));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), ImmatriculationRecuperee_.status));
            }
            if (criteria.getStatusdesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusdesc(), ImmatriculationRecuperee_.statusdesc));
            }
            if (criteria.getTypeIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIdentifiant(), ImmatriculationRecuperee_.typeIdentifiant));
            }
            if (criteria.getNumeroIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroIdentifiant(), ImmatriculationRecuperee_.numeroIdentifiant));
            }
            if (criteria.getNinea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNinea(), ImmatriculationRecuperee_.ninea));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), ImmatriculationRecuperee_.numeroUnique));
            }
            if (criteria.getNumeroCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCss(), ImmatriculationRecuperee_.numeroCss));
            }
            if (criteria.getNumeroIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroIpres(), ImmatriculationRecuperee_.numeroIpres));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), ImmatriculationRecuperee_.raisonSociale));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), ImmatriculationRecuperee_.userId));
            }
            if (criteria.getAgentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgentId(), ImmatriculationRecuperee_.agentId));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), ImmatriculationRecuperee_.createdAt));
            }
            if (criteria.getDateTraitement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTraitement(), ImmatriculationRecuperee_.dateTraitement));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), ImmatriculationRecuperee_.date));
            }
            if (criteria.getZoneIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZoneIpres(), ImmatriculationRecuperee_.zoneIpres));
            }
            if (criteria.getAgenceCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceCss(), ImmatriculationRecuperee_.agenceCss));
            }
            if (criteria.getAgenceIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceIpres(), ImmatriculationRecuperee_.agenceIpres));
            }
            if (criteria.getTauxAt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTauxAt(), ImmatriculationRecuperee_.tauxAt));
            }
            if (criteria.getRegistreCommerce() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegistreCommerce(), ImmatriculationRecuperee_.registreCommerce));
            }
        }
        return specification;
    }
}
