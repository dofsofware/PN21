package com.secusociale.portail.service;

import com.secusociale.portail.domain.GrappeMember;
import com.secusociale.portail.domain.GrappeMember_;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.Salarie_;
import com.secusociale.portail.repository.GrappeMemberRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.service.dto.GrappeMemberCriteria;
import com.secusociale.portail.service.dto.GrappeMemberDTO;
import com.secusociale.portail.service.mapper.GrappeMemberMapper;
import com.secusociale.portail.service.mapper.SalarieMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Service for executing complex queries for {@link GrappeMember} entities in the database.
 * The main input is a {@link GrappeMemberCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GrappeMemberDTO} or a {@link Page} of {@link GrappeMemberDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GrappeMemberQueryService extends QueryService<GrappeMember> {

    private final Logger log = LoggerFactory.getLogger(GrappeMemberQueryService.class);

    private final GrappeMemberRepository grappeMemberRepository;

    private final GrappeMemberMapper grappeMemberMapper;

    private final SalarieRepository salarieRepository;
    private final SalarieMapper salarieMapper;

    public GrappeMemberQueryService(GrappeMemberRepository grappeMemberRepository, GrappeMemberMapper grappeMemberMapper, SalarieRepository salarieRepository, SalarieMapper salarieMapper) {
        this.grappeMemberRepository = grappeMemberRepository;
        this.grappeMemberMapper = grappeMemberMapper;
        this.salarieRepository = salarieRepository;
        this.salarieMapper = salarieMapper;
    }

    /**
     * Return a {@link List} of {@link GrappeMemberDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GrappeMemberDTO> findByCriteria(GrappeMemberCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GrappeMember> specification = createSpecification(criteria);
        return grappeMemberMapper.toDto(grappeMemberRepository.findAll(specification)).stream()
            .peek(grappeMemberDTO -> {
                Optional<Salarie> salarieOpt = salarieRepository.findById(grappeMemberDTO.getSalarieId());
                salarieOpt.ifPresent(salarie -> grappeMemberDTO.setSalarieObj(salarieMapper.toDto(salarie)));
            }).collect(Collectors.toList());
    }

    /**
     * Return a {@link Page} of {@link GrappeMemberDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GrappeMemberDTO> findByCriteria(GrappeMemberCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GrappeMember> specification = createSpecification(criteria);
        return grappeMemberRepository.findAll(specification, page)
            .map(grappeMemberMapper::toDto).map(grappeMemberDTO -> {
                Optional<Salarie> salarieOpt = salarieRepository.findById(grappeMemberDTO.getSalarieId());
                salarieOpt.ifPresent(salarie -> grappeMemberDTO.setSalarieObj(salarieMapper.toDto(salarie)));
                return grappeMemberDTO;
            });
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GrappeMemberCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<GrappeMember> specification = createSpecification(criteria);
        return grappeMemberRepository.count(specification);
    }

    /**
     * Function to convert {@link GrappeMemberCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<GrappeMember> createSpecification(GrappeMemberCriteria criteria) {
        Specification<GrappeMember> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), GrappeMember_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), GrappeMember_.type));
            }
            if (criteria.getDateNais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateNais(), GrappeMember_.dateNais));
            }
            if (criteria.getDateMariage() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateMariage(), GrappeMember_.dateMariage));
            }
            if (criteria.getSexe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSexe(), GrappeMember_.sexe));
            }
            if (criteria.getOrigine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrigine(), GrappeMember_.origine));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), GrappeMember_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), GrappeMember_.lastName));
            }
            if (criteria.getNumeroCni() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCni(), GrappeMember_.numeroCni));
            }
            if (criteria.getSalarieId() != null) {
                specification = specification.and(buildSpecification(criteria.getSalarieId(),
                    root -> root.join(GrappeMember_.salarie, JoinType.LEFT).get(Salarie_.id)));
            }
            if (criteria.getGlobalSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGlobalSearch(), GrappeMember_.type));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), GrappeMember_.sexe));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), GrappeMember_.origine));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), GrappeMember_.firstName));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), GrappeMember_.lastName));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), GrappeMember_.numeroCni));
            }
        }
        return specification;
    }
}
