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

import com.secusociale.portail.domain.EmailNotif;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.EmailNotifRepository;
import com.secusociale.portail.service.dto.EmailNotifCriteria;
import com.secusociale.portail.service.dto.EmailNotifDTO;
import com.secusociale.portail.service.mapper.EmailNotifMapper;

/**
 * Service for executing complex queries for {@link EmailNotif} entities in the database.
 * The main input is a {@link EmailNotifCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmailNotifDTO} or a {@link Page} of {@link EmailNotifDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmailNotifQueryService extends QueryService<EmailNotif> {

    private final Logger log = LoggerFactory.getLogger(EmailNotifQueryService.class);

    private final EmailNotifRepository emailNotifRepository;

    private final EmailNotifMapper emailNotifMapper;

    public EmailNotifQueryService(EmailNotifRepository emailNotifRepository, EmailNotifMapper emailNotifMapper) {
        this.emailNotifRepository = emailNotifRepository;
        this.emailNotifMapper = emailNotifMapper;
    }

    /**
     * Return a {@link List} of {@link EmailNotifDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmailNotifDTO> findByCriteria(EmailNotifCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EmailNotif> specification = createSpecification(criteria);
        return emailNotifMapper.toDto(emailNotifRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmailNotifDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmailNotifDTO> findByCriteria(EmailNotifCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EmailNotif> specification = createSpecification(criteria);
        return emailNotifRepository.findAll(specification, page)
            .map(emailNotifMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmailNotifCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EmailNotif> specification = createSpecification(criteria);
        return emailNotifRepository.count(specification);
    }

    /**
     * Function to convert {@link EmailNotifCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EmailNotif> createSpecification(EmailNotifCriteria criteria) {
        Specification<EmailNotif> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EmailNotif_.id));
            }
            if (criteria.getGroupe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroupe(), EmailNotif_.groupe));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), EmailNotif_.active));
            }
            if (criteria.getLastSendDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastSendDate(), EmailNotif_.lastSendDate));
            }
        }
        return specification;
    }
}
