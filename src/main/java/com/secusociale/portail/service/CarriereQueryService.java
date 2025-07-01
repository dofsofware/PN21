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

import com.secusociale.portail.domain.Carriere;
import com.secusociale.portail.domain.*; // for static metamodels
import com.secusociale.portail.repository.CarriereRepository;
import com.secusociale.portail.service.dto.CarriereCriteria;
import com.secusociale.portail.service.dto.CarriereDTO;
import com.secusociale.portail.service.mapper.CarriereMapper;

/**
 * Service for executing complex queries for {@link Carriere} entities in the database.
 * The main input is a {@link CarriereCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CarriereDTO} or a {@link Page} of {@link CarriereDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CarriereQueryService extends QueryService<Carriere> {

    private final Logger log = LoggerFactory.getLogger(CarriereQueryService.class);

    private final CarriereRepository carriereRepository;

    private final CarriereMapper carriereMapper;

    public CarriereQueryService(CarriereRepository carriereRepository, CarriereMapper carriereMapper) {
        this.carriereRepository = carriereRepository;
        this.carriereMapper = carriereMapper;
    }

    /**
     * Return a {@link List} of {@link CarriereDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CarriereDTO> findByCriteria(CarriereCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Carriere> specification = createSpecification(criteria);
        return carriereMapper.toDto(carriereRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CarriereDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CarriereDTO> findByCriteria(CarriereCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Carriere> specification = createSpecification(criteria);
        return carriereRepository.findAll(specification, page)
            .map(carriereMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CarriereCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Carriere> specification = createSpecification(criteria);
        return carriereRepository.count(specification);
    }

    /**
     * Function to convert {@link CarriereCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Carriere> createSpecification(CarriereCriteria criteria) {
        Specification<Carriere> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Carriere_.id));
            }
            if (criteria.getNumeroUniqueSalarie() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUniqueSalarie(), Carriere_.numeroUniqueSalarie));
            }
            if (criteria.getNumeroUniqueEmployeur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUniqueEmployeur(), Carriere_.numeroUniqueEmployeur));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), Carriere_.raisonSociale));
            }
            if (criteria.getTypeRegime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeRegime(), Carriere_.typeRegime));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeDeclaration(), Carriere_.typeDeclaration));
            }
            if (criteria.getDebutCotisation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDebutCotisation(), Carriere_.debutCotisation));
            }
            if (criteria.getFinCotisation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinCotisation(), Carriere_.finCotisation));
            }
            if (criteria.getExercice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExercice(), Carriere_.exercice));
            }
            if (criteria.getSalaireBrute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaireBrute(), Carriere_.salaireBrute));
            }
            if (criteria.getSalaireAssujettie() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaireAssujettie(), Carriere_.salaireAssujettie));
            }
            if (criteria.getCotisationIpres() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCotisationIpres(), Carriere_.cotisationIpres));
            }
            if (criteria.getCotisationCss() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCotisationCss(), Carriere_.cotisationCss));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Carriere_.createdAt));
            }
            if (criteria.getSalarieId() != null) {
                specification = specification.and(buildSpecification(criteria.getSalarieId(),
                    root -> root.join(Carriere_.salarie, JoinType.LEFT).get(Salarie_.id)));
            }
        }
        return specification;
    }
}
