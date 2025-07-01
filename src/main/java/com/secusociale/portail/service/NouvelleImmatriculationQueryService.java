package com.secusociale.portail.service;

import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.SingularAttribute;

import com.secusociale.portail.domain.enumeration.StatusDocP;
import com.secusociale.portail.service.impl.EnumFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.secusociale.portail.domain.NouvelleImmatriculation;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.service.dto.NouvelleImmatriculationCriteria;
import com.secusociale.portail.service.dto.NouvelleImmatriculationDTO;
import com.secusociale.portail.service.mapper.NouvelleImmatriculationMapper;

/**
 * Service for executing complex queries for {@link NouvelleImmatriculation} entities in the database.
 * The main input is a {@link NouvelleImmatriculationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NouvelleImmatriculationDTO} or a {@link Page} of {@link NouvelleImmatriculationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NouvelleImmatriculationQueryService extends QueryService<NouvelleImmatriculation> {

    private final Logger log = LoggerFactory.getLogger(NouvelleImmatriculationQueryService.class);

    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;

    private final NouvelleImmatriculationMapper nouvelleImmatriculationMapper;

    public NouvelleImmatriculationQueryService(NouvelleImmatriculationRepository nouvelleImmatriculationRepository, NouvelleImmatriculationMapper nouvelleImmatriculationMapper) {
        this.nouvelleImmatriculationRepository = nouvelleImmatriculationRepository;
        this.nouvelleImmatriculationMapper = nouvelleImmatriculationMapper;
    }

    /**
     * Return a {@link List} of {@link NouvelleImmatriculationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NouvelleImmatriculationDTO> findByCriteria(NouvelleImmatriculationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NouvelleImmatriculation> specification = createSpecification(criteria);
        return nouvelleImmatriculationMapper.toDto(nouvelleImmatriculationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NouvelleImmatriculationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NouvelleImmatriculationDTO> findByCriteria(NouvelleImmatriculationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NouvelleImmatriculation> specification = createSpecification(criteria);
        return nouvelleImmatriculationRepository.findAll(specification, page)
            .map(nouvelleImmatriculationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NouvelleImmatriculationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NouvelleImmatriculation> specification = createSpecification(criteria);
        return nouvelleImmatriculationRepository.count(specification);
    }

    protected <T extends Enum<T>> Specification<NouvelleImmatriculation> buildEnumSpecification(
        EnumFilter<T> filter, SingularAttribute<NouvelleImmatriculation, T> field) {
        if (filter != null && filter.getEquals() != null) {
            // Comparaison pour les enums
            return (root, query, builder) -> builder.equal(root.get(field), filter.getEquals());
        }
        return null;
    }

    /**
     * Function to convert {@link NouvelleImmatriculationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NouvelleImmatriculation> createSpecification(NouvelleImmatriculationCriteria criteria) {
        Specification<NouvelleImmatriculation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NouvelleImmatriculation_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), NouvelleImmatriculation_.type));
            }
            if (criteria.getNumdoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumdoc(), NouvelleImmatriculation_.numdoc));
            }
            if (criteria.getStatusdoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusdoc(), NouvelleImmatriculation_.statusdoc));
            }
            if (criteria.getStatusdocp() != null) {
                String statusdocpValue = criteria.getStatusdocp().getEquals();
                try {
                    StatusDocP statusDocP = StatusDocP.valueOf(statusdocpValue.toUpperCase());

                    EnumFilter<StatusDocP> statusDocPFilter = new EnumFilter<>();
                    statusDocPFilter.setEquals(statusDocP);

                    specification = specification.and(buildEnumSpecification(statusDocPFilter, NouvelleImmatriculation_.statusDocP));
                } catch (IllegalArgumentException e) {
                    // Si la valeur n'est pas valide pour StatusDocP
                    log.error("StatusDocP non valide: {}", statusdocpValue);
                }
            }
            if (criteria.getStatusdesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusdesc(), NouvelleImmatriculation_.statusdesc));
            }
            if (criteria.getTypeIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIdentifiant(), NouvelleImmatriculation_.typeIdentifiant));
            }
            if (criteria.getNinea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNinea(), NouvelleImmatriculation_.ninea));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), NouvelleImmatriculation_.numeroUnique));
            }
            if (criteria.getNumeroCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCss(), NouvelleImmatriculation_.numeroCss));
            }
            if (criteria.getNumeroIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroIpres(), NouvelleImmatriculation_.numeroIpres));
            }
            if (criteria.getUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUser(), NouvelleImmatriculation_.user));
            }
            if (criteria.getIsSubmit() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSubmit(), NouvelleImmatriculation_.isSubmit));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), NouvelleImmatriculation_.createdAt));
            }
            if (criteria.getEmployerRegistrationFormId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployerRegistrationFormId(), NouvelleImmatriculation_.employerRegistrationFormId));
            }
            if (criteria.getEmployeeRegistrationFormId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeRegistrationFormId(), NouvelleImmatriculation_.employeeRegistrationFormId));
            }
            if (criteria.getZoneIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZoneIpres(), NouvelleImmatriculation_.zoneIpres));
            }
            if (criteria.getAgenceCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceCss(), NouvelleImmatriculation_.agenceCss));
            }
            if (criteria.getAgenceIpres() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenceIpres(), NouvelleImmatriculation_.agenceIpres));
            }
            if (criteria.getTauxAt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTauxAt(), NouvelleImmatriculation_.tauxAt));
            }
            if (criteria.getRegistreCommerce() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegistreCommerce(), NouvelleImmatriculation_.registreCommerce));
            }

            if (criteria.getGlobalSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.type));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.registreCommerce));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.agenceIpres));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.numeroCss));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.numdoc));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.ninea));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.raisonSociale));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.numeroUnique));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.numeroIpres));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), NouvelleImmatriculation_.numeroCss));
            }
        }
        return specification;
    }
}
