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

import com.secusociale.portail.domain.Helpers;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.HelpersRepository;
import com.secusociale.portail.service.dto.HelpersCriteria;
import com.secusociale.portail.service.dto.HelpersDTO;
import com.secusociale.portail.service.mapper.HelpersMapper;

/**
 * Service for executing complex queries for {@link Helpers} entities in the database.
 * The main input is a {@link HelpersCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HelpersDTO} or a {@link Page} of {@link HelpersDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HelpersQueryService extends QueryService<Helpers> {

    private final Logger log = LoggerFactory.getLogger(HelpersQueryService.class);

    private final HelpersRepository helpersRepository;

    private final HelpersMapper helpersMapper;

    public HelpersQueryService(HelpersRepository helpersRepository, HelpersMapper helpersMapper) {
        this.helpersRepository = helpersRepository;
        this.helpersMapper = helpersMapper;
    }

    /**
     * Return a {@link List} of {@link HelpersDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HelpersDTO> findByCriteria(HelpersCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Helpers> specification = createSpecification(criteria);
        return helpersMapper.toDto(helpersRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HelpersDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HelpersDTO> findByCriteria(HelpersCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Helpers> specification = createSpecification(criteria);
        return helpersRepository.findAll(specification, page)
            .map(helpersMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HelpersCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Helpers> specification = createSpecification(criteria);
        return helpersRepository.count(specification);
    }

    /**
     * Function to convert {@link HelpersCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Helpers> createSpecification(HelpersCriteria criteria) {
        Specification<Helpers> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Helpers_.id));
            }
            if (criteria.getUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUser(), Helpers_.user));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Helpers_.date));
            }
        }
        return specification;
    }
}
