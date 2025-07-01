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

import com.secusociale.portail.domain.CronSetting;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.CronSettingRepository;
import com.secusociale.portail.service.dto.CronSettingCriteria;
import com.secusociale.portail.service.dto.CronSettingDTO;
import com.secusociale.portail.service.mapper.CronSettingMapper;

/**
 * Service for executing complex queries for {@link CronSetting} entities in the database.
 * The main input is a {@link CronSettingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CronSettingDTO} or a {@link Page} of {@link CronSettingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CronSettingQueryService extends QueryService<CronSetting> {

    private final Logger log = LoggerFactory.getLogger(CronSettingQueryService.class);

    private final CronSettingRepository cronSettingRepository;

    private final CronSettingMapper cronSettingMapper;

    public CronSettingQueryService(CronSettingRepository cronSettingRepository, CronSettingMapper cronSettingMapper) {
        this.cronSettingRepository = cronSettingRepository;
        this.cronSettingMapper = cronSettingMapper;
    }

    /**
     * Return a {@link List} of {@link CronSettingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CronSettingDTO> findByCriteria(CronSettingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CronSetting> specification = createSpecification(criteria);
        return cronSettingMapper.toDto(cronSettingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CronSettingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CronSettingDTO> findByCriteria(CronSettingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CronSetting> specification = createSpecification(criteria);
        return cronSettingRepository.findAll(specification, page)
            .map(cronSettingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CronSettingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CronSetting> specification = createSpecification(criteria);
        return cronSettingRepository.count(specification);
    }

    /**
     * Function to convert {@link CronSettingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CronSetting> createSpecification(CronSettingCriteria criteria) {
        Specification<CronSetting> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CronSetting_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CronSetting_.code));
            }
            if (criteria.getExpression() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExpression(), CronSetting_.expression));
            }
        }
        return specification;
    }
}
