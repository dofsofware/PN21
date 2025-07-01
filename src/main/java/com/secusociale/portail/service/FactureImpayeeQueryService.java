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

import com.secusociale.portail.domain.FactureImpayee;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.FactureImpayeeRepository;
import com.secusociale.portail.service.dto.FactureImpayeeCriteria;
import com.secusociale.portail.service.dto.FactureImpayeeDTO;
import com.secusociale.portail.service.mapper.FactureImpayeeMapper;

/**
 * Service for executing complex queries for {@link FactureImpayee} entities in the database.
 * The main input is a {@link FactureImpayeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FactureImpayeeDTO} or a {@link Page} of {@link FactureImpayeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FactureImpayeeQueryService extends QueryService<FactureImpayee> {

    private final Logger log = LoggerFactory.getLogger(FactureImpayeeQueryService.class);

    private final FactureImpayeeRepository factureImpayeeRepository;

    private final FactureImpayeeMapper factureImpayeeMapper;

    public FactureImpayeeQueryService(FactureImpayeeRepository factureImpayeeRepository, FactureImpayeeMapper factureImpayeeMapper) {
        this.factureImpayeeRepository = factureImpayeeRepository;
        this.factureImpayeeMapper = factureImpayeeMapper;
    }

    /**
     * Return a {@link List} of {@link FactureImpayeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FactureImpayeeDTO> findByCriteria(FactureImpayeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FactureImpayee> specification = createSpecification(criteria);
        return factureImpayeeMapper.toDto(factureImpayeeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FactureImpayeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FactureImpayeeDTO> findByCriteria(FactureImpayeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FactureImpayee> specification = createSpecification(criteria);
        return factureImpayeeRepository.findAll(specification, page)
            .map(factureImpayeeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FactureImpayeeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FactureImpayee> specification = createSpecification(criteria);
        return factureImpayeeRepository.count(specification);
    }

    /**
     * Function to convert {@link FactureImpayeeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FactureImpayee> createSpecification(FactureImpayeeCriteria criteria) {
        Specification<FactureImpayee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FactureImpayee_.id));
            }
            if (criteria.getNumeroFacture() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroFacture(), FactureImpayee_.numeroFacture));
            }
            if (criteria.getTypeFacture() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeFacture(), FactureImpayee_.typeFacture));
            }
            if (criteria.getDateEcheance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateEcheance(), FactureImpayee_.dateEcheance));
            }
            if (criteria.getDateDebut() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateDebut(), FactureImpayee_.dateDebut));
            }
            if (criteria.getDateFin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateFin(), FactureImpayee_.dateFin));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), FactureImpayee_.raisonSociale));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), FactureImpayee_.status));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), FactureImpayee_.numeroUnique));
            }
            if (criteria.getDetteinput() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDetteinput(), FactureImpayee_.detteinput));
            }
            if (criteria.getMontantVerse() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantVerse(), FactureImpayee_.montantVerse));
            }
            if (criteria.getPenalite() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPenalite(), FactureImpayee_.penalite));
            }
            if (criteria.getMontantPaye() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantPaye(), FactureImpayee_.montantPaye));
            }
            if (criteria.getDette() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDette(), FactureImpayee_.dette));
            }
            if (criteria.getMontantTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotal(), FactureImpayee_.montantTotal));
            }
            if (criteria.getMajorations() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMajorations(), FactureImpayee_.majorations));
            }
            if (criteria.getMontantPrincipal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantPrincipal(), FactureImpayee_.montantPrincipal));
            }
        }
        return specification;
    }
}
