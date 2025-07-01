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

import com.secusociale.portail.domain.FactureDNS;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.FactureDNSRepository;
import com.secusociale.portail.service.dto.FactureDNSCriteria;
import com.secusociale.portail.service.dto.FactureDNSDTO;
import com.secusociale.portail.service.mapper.FactureDNSMapper;

/**
 * Service for executing complex queries for {@link FactureDNS} entities in the database.
 * The main input is a {@link FactureDNSCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FactureDNSDTO} or a {@link Page} of {@link FactureDNSDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FactureDNSQueryService extends QueryService<FactureDNS> {

    private final Logger log = LoggerFactory.getLogger(FactureDNSQueryService.class);

    private final FactureDNSRepository factureDNSRepository;

    private final FactureDNSMapper factureDNSMapper;

    public FactureDNSQueryService(FactureDNSRepository factureDNSRepository, FactureDNSMapper factureDNSMapper) {
        this.factureDNSRepository = factureDNSRepository;
        this.factureDNSMapper = factureDNSMapper;
    }

    /**
     * Return a {@link List} of {@link FactureDNSDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FactureDNSDTO> findByCriteria(FactureDNSCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FactureDNS> specification = createSpecification(criteria);
        return factureDNSMapper.toDto(factureDNSRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FactureDNSDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FactureDNSDTO> findByCriteria(FactureDNSCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FactureDNS> specification = createSpecification(criteria);
        return factureDNSRepository.findAll(specification, page)
            .map(factureDNSMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FactureDNSCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FactureDNS> specification = createSpecification(criteria);
        return factureDNSRepository.count(specification);
    }

    /**
     * Function to convert {@link FactureDNSCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FactureDNS> createSpecification(FactureDNSCriteria criteria) {
        Specification<FactureDNS> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FactureDNS_.id));
            }
            if (criteria.getNumeroFacture() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroFacture(), FactureDNS_.numeroFacture));
            }
            if (criteria.getIdDeclaration() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDeclaration(), FactureDNS_.idDeclaration));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), FactureDNS_.numeroUnique));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), FactureDNS_.raisonSociale));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), FactureDNS_.adresse));
            }
            if (criteria.getAgenceIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceIpres(), FactureDNS_.agenceIpres));
            }
            if (criteria.getAgenceCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceCss(), FactureDNS_.agenceCss));
            }
            if (criteria.getDebutPeriode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDebutPeriode(), FactureDNS_.debutPeriode));
            }
            if (criteria.getFinPeriode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinPeriode(), FactureDNS_.finPeriode));
            }
            if (criteria.getDateReception() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateReception(), FactureDNS_.dateReception));
            }
            if (criteria.getVieillesseRG() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVieillesseRG(), FactureDNS_.vieillesseRG));
            }
            if (criteria.getVieillesseRC() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVieillesseRC(), FactureDNS_.vieillesseRC));
            }
            if (criteria.getTauxRG() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxRG(), FactureDNS_.tauxRG));
            }
            if (criteria.getTauxRC() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxRC(), FactureDNS_.tauxRC));
            }
            if (criteria.getMtVieillesseRG() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMtVieillesseRG(), FactureDNS_.mtVieillesseRG));
            }
            if (criteria.getMtVieillesseRC() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMtVieillesseRC(), FactureDNS_.mtVieillesseRC));
            }
            if (criteria.getMtMajorationCss() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMtMajorationCss(), FactureDNS_.mtMajorationCss));
            }
            if (criteria.getPrestationFam() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrestationFam(), FactureDNS_.prestationFam));
            }
            if (criteria.getTauxPrestationFam() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxPrestationFam(), FactureDNS_.tauxPrestationFam));
            }
            if (criteria.getMtPrestationFam() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMtPrestationFam(), FactureDNS_.mtPrestationFam));
            }
            if (criteria.getAtmp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAtmp(), FactureDNS_.atmp));
            }
            if (criteria.getTauxATMP() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTauxATMP(), FactureDNS_.tauxATMP));
            }
            if (criteria.getMtAtmp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMtAtmp(), FactureDNS_.mtAtmp));
            }
            if (criteria.getTotalIpres() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalIpres(), FactureDNS_.totalIpres));
            }
            if (criteria.getTotalCss() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalCss(), FactureDNS_.totalCss));
            }
            if (criteria.getTotalAPayer() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAPayer(), FactureDNS_.totalAPayer));
            }
            if (criteria.getMtMajorationIpres() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMtMajorationIpres(), FactureDNS_.mtMajorationIpres));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatut(), FactureDNS_.statut));
            }
            if (criteria.getEcheance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEcheance(), FactureDNS_.echeance));
            }
        }
        return specification;
    }
}
