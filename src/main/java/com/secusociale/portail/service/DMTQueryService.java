package com.secusociale.portail.service;

import com.secusociale.portail.domain.DMT;
import com.secusociale.portail.domain.DMT_;
import com.secusociale.portail.repository.DMTRepository;
import com.secusociale.portail.service.dto.DMTCriteria;
import com.secusociale.portail.service.dto.DMTDTO;
import com.secusociale.portail.service.mapper.DMTMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for executing complex queries for {@link DMT} entities in the database.
 * The main input is a {@link DMTCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DMTDTO} or a {@link Page} of {@link DMTDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DMTQueryService extends QueryService<DMT> {

    private final Logger log = LoggerFactory.getLogger(DMTQueryService.class);

    private final DMTRepository dMTRepository;

    private final DMTMapper dMTMapper;

    public DMTQueryService(DMTRepository dMTRepository, DMTMapper dMTMapper) {
        this.dMTRepository = dMTRepository;
        this.dMTMapper = dMTMapper;
    }

    /**
     * Return a {@link List} of {@link DMTDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DMTDTO> findByCriteria(DMTCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DMT> specification = createSpecification(criteria);
        return dMTMapper.toDto(dMTRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DMTDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DMTDTO> findByCriteria(DMTCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DMT> specification = createSpecification(criteria);
        return dMTRepository.findAll(specification, page)
            .map(dMTMapper::toDto);
    }


    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DMTCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DMT> specification = createSpecification(criteria);
        return dMTRepository.count(specification);
    }

    /**
     * Function to convert {@link DMTCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DMT> createSpecification(DMTCriteria criteria) {
        Specification<DMT> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DMT_.id));
            }
            if (criteria.getIdEmployeur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdEmployeur(), DMT_.idEmployeur));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), DMT_.status));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), DMT_.date));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), DMT_.userId));
            }
            if (criteria.getRaisonSocial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSocial(), DMT_.raisonSocial));
            }
        }
        return specification;
    }

}
