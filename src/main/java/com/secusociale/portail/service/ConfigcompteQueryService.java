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

import com.secusociale.portail.domain.Configcompte;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.ConfigcompteRepository;
import com.secusociale.portail.service.dto.ConfigcompteCriteria;
import com.secusociale.portail.service.dto.ConfigcompteDTO;
import com.secusociale.portail.service.mapper.ConfigcompteMapper;

/**
 * Service for executing complex queries for {@link Configcompte} entities in the database.
 * The main input is a {@link ConfigcompteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ConfigcompteDTO} or a {@link Page} of {@link ConfigcompteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConfigcompteQueryService extends QueryService<Configcompte> {

    private final Logger log = LoggerFactory.getLogger(ConfigcompteQueryService.class);

    private final ConfigcompteRepository configcompteRepository;

    private final ConfigcompteMapper configcompteMapper;

    public ConfigcompteQueryService(ConfigcompteRepository configcompteRepository, ConfigcompteMapper configcompteMapper) {
        this.configcompteRepository = configcompteRepository;
        this.configcompteMapper = configcompteMapper;
    }

    /**
     * Return a {@link List} of {@link ConfigcompteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ConfigcompteDTO> findByCriteria(ConfigcompteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Configcompte> specification = createSpecification(criteria);
        return configcompteMapper.toDto(configcompteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ConfigcompteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ConfigcompteDTO> findByCriteria(ConfigcompteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Configcompte> specification = createSpecification(criteria);
        return configcompteRepository.findAll(specification, page)
            .map(configcompteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ConfigcompteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Configcompte> specification = createSpecification(criteria);
        return configcompteRepository.count(specification);
    }

    /**
     * Function to convert {@link ConfigcompteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Configcompte> createSpecification(ConfigcompteCriteria criteria) {
        Specification<Configcompte> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Configcompte_.id));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), Configcompte_.accountNumber));
            }
            if (criteria.getAccountHolderName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountHolderName(), Configcompte_.accountHolderName));
            }
            if (criteria.getManagerPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerPhoneNumber(), Configcompte_.managerPhoneNumber));
            }
            if (criteria.getManagerEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerEmail(), Configcompte_.managerEmail));
            }
            if (criteria.getSenderFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSenderFirstName(), Configcompte_.senderFirstName));
            }
            if (criteria.getAgenceCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceCode(), Configcompte_.agenceCode));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), Configcompte_.active));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Configcompte_.createdDate));
            }
            if (criteria.getSignatairePhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSignatairePhoneNumber(), Configcompte_.signatairePhoneNumber));
            }
            if (criteria.getSignataireEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSignataireEmail(), Configcompte_.signataireEmail));
            }
            if (criteria.getManagerFirstname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerFirstname(), Configcompte_.managerFirstname));
            }
            if (criteria.getManagerLastname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManagerLastname(), Configcompte_.managerLastname));
            }
            if (criteria.getAgenceId() != null) {
                specification = specification.and(buildSpecification(criteria.getAgenceId(),
                    root -> root.join(Configcompte_.agence, JoinType.LEFT).get(Agence_.id)));
            }
        }
        return specification;
    }
}
