package com.secusociale.portail.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.secusociale.portail.domain.Salarie_;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.dto.custom.AgentDTO;
import com.secusociale.portail.service.dto.custom.SimplifiedAgentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.service.dto.SalarieCriteria;
import com.secusociale.portail.service.dto.SalarieDTO;
import com.secusociale.portail.service.mapper.SalarieMapper;

/**
 * Service for executing complex queries for {@link Salarie} entities in the database.
 * The main input is a {@link SalarieCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SalarieDTO} or a {@link Page} of {@link SalarieDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SalarieQueryService extends QueryService<Salarie> {

    private final Logger log = LoggerFactory.getLogger(SalarieQueryService.class);

    private final SalarieRepository salarieRepository;
    private final UserRepository userRepository;

    private final SalarieMapper salarieMapper;

    public SalarieQueryService(SalarieRepository salarieRepository, UserRepository userRepository, SalarieMapper salarieMapper) {
        this.salarieRepository = salarieRepository;
        this.userRepository = userRepository;
        this.salarieMapper = salarieMapper;
    }

    /**
     * Return a {@link List} of {@link SalarieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SalarieDTO> findByCriteria(SalarieCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Salarie> specification = createSpecification(criteria);
        return salarieMapper.toDto(salarieRepository.findAll(specification)).stream().peek(salarieDTO -> salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))))).collect(Collectors.toList());
    }

    /**
     * Return a {@link Page} of {@link SalarieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SalarieDTO> findByCriteria(SalarieCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Salarie> specification = createSpecification(criteria);
        return salarieRepository.findAll(specification, page)
            .map(salarieMapper::toDto).map(salarieDTO -> {
                salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
                User user = userRepository.findById(salarieDTO.getUserId()).orElse(null);
                if (user != null) {
                    SimplifiedAgentDto agent = new SimplifiedAgentDto();
                    agent.setFirstName(user.getFirstName());
                    agent.setLastName(user.getLastName());
                    agent.setId(user.getId());
                    salarieDTO.setSimplifiedAgent(agent);
                }
                return salarieDTO;
            });
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SalarieCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Salarie> specification = createSpecification(criteria);
        return salarieRepository.count(specification);
    }

    /**
     * Function to convert {@link SalarieCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Salarie> createSpecification(SalarieCriteria criteria) {
        Specification<Salarie> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Salarie_.id));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), Salarie_.numeroUnique));
            }
            if (criteria.getNumeroCni() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCni(), Salarie_.numeroCni));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), Salarie_.prenom));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Salarie_.nom));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), Salarie_.telephone));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Salarie_.email));
            }
            if (criteria.getSexe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSexe(), Salarie_.sexe));
            }
            if (criteria.getDateNais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateNais(), Salarie_.dateNais));
            }
            if (criteria.getLieuNais() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLieuNais(), Salarie_.lieuNais));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), Salarie_.active));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), Salarie_.userId));
            }
            if (criteria.getAgentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgentId(), Salarie_.agentId));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Salarie_.createdAt));
            }
            if (criteria.getLastUpdateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastUpdateAt(), Salarie_.lastUpdateAt));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatut(), Salarie_.statut));
            }

            if (criteria.getGlobalSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.nom));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.prenom));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.telephone));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.email));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.sexe));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.statut));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.lieuNais));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.numeroCni));
                specification = specification.or(buildStringSpecification(criteria.getGlobalSearch(), Salarie_.numeroUnique));
            }
        }
        return specification;
    }
}
