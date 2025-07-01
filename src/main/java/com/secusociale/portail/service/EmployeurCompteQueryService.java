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

import com.secusociale.portail.domain.EmployeurCompte;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.EmployeurCompteRepository;
import com.secusociale.portail.service.dto.EmployeurCompteCriteria;
import com.secusociale.portail.service.dto.EmployeurCompteDTO;
import com.secusociale.portail.service.mapper.EmployeurCompteMapper;

/**
 * Service for executing complex queries for {@link EmployeurCompte} entities in the database.
 * The main input is a {@link EmployeurCompteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmployeurCompteDTO} or a {@link Page} of {@link EmployeurCompteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmployeurCompteQueryService extends QueryService<EmployeurCompte> {

    private final Logger log = LoggerFactory.getLogger(EmployeurCompteQueryService.class);

    private final EmployeurCompteRepository employeurCompteRepository;

    private final EmployeurCompteMapper employeurCompteMapper;

    public EmployeurCompteQueryService(EmployeurCompteRepository employeurCompteRepository, EmployeurCompteMapper employeurCompteMapper) {
        this.employeurCompteRepository = employeurCompteRepository;
        this.employeurCompteMapper = employeurCompteMapper;
    }

    /**
     * Return a {@link List} of {@link EmployeurCompteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmployeurCompteDTO> findByCriteria(EmployeurCompteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EmployeurCompte> specification = createSpecification(criteria);
        return employeurCompteMapper.toDto(employeurCompteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmployeurCompteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeurCompteDTO> findByCriteria(EmployeurCompteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EmployeurCompte> specification = createSpecification(criteria);
        return employeurCompteRepository.findAll(specification, page)
            .map(employeurCompteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmployeurCompteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EmployeurCompte> specification = createSpecification(criteria);
        return employeurCompteRepository.count(specification);
    }

    /**
     * Function to convert {@link EmployeurCompteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EmployeurCompte> createSpecification(EmployeurCompteCriteria criteria) {
        Specification<EmployeurCompte> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EmployeurCompte_.id));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), EmployeurCompte_.accountNumber));
            }
            if (criteria.getAccountHolderName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountHolderName(), EmployeurCompte_.accountHolderName));
            }
            if (criteria.getManagerPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerPhoneNumber(), EmployeurCompte_.managerPhoneNumber));
            }
            if (criteria.getManagerEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerEmail(), EmployeurCompte_.managerEmail));
            }
            if (criteria.getSenderFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderFirstName(), EmployeurCompte_.senderFirstName));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), EmployeurCompte_.active));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), EmployeurCompte_.createdDate));
            }
            if (criteria.getSignatairePhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSignatairePhoneNumber(), EmployeurCompte_.signatairePhoneNumber));
            }
            if (criteria.getSignataireEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSignataireEmail(), EmployeurCompte_.signataireEmail));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), EmployeurCompte_.numeroUnique));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), EmployeurCompte_.userId));
            }
            if (criteria.getManagerFirstname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerFirstname(), EmployeurCompte_.managerFirstname));
            }
            if (criteria.getManagerLastname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerLastname(), EmployeurCompte_.managerLastname));
            }
        }
        return specification;
    }
}
