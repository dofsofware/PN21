package com.secusociale.portail.service;

import com.secusociale.portail.domain.Authority;
import com.secusociale.portail.domain.Authority_;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.User_;
import com.secusociale.portail.repository.NewUserRepository;
import com.secusociale.portail.service.dto.UserCriteria;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.mapper.UserMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link User} entities in the database.
 * The main input is a {@link UserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserDTO} or a {@link Page} of {@link UserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserQueryService extends QueryService<User> {

    private final Logger log = LoggerFactory.getLogger(UserQueryService.class);

    private final NewUserRepository userRepository;

    private final UserMapper newUserMapper;

    public UserQueryService(NewUserRepository userRepository, UserMapper newUserMapper) {
        this.userRepository = userRepository;
        this.newUserMapper = newUserMapper;
    }

    /**
     * Return a {@link List} of {@link UserDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserDTO> findByCriteria(UserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<User> specification = createSpecification(criteria);
        return newUserMapper.usersToUserDTOs(userRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserDTO> findByCriteria(UserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification, page).map(UserDTO::new);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.count(specification);
    }

    /**
     * Function to convert {@link UserCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), User_.id));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), User_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), User_.lastName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), User_.email));
            }
            if (criteria.getLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogin(), User_.login));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), User_.phone));
            }
            if (criteria.getTypeCompte() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeCompte(), User_.typeCompte));
            }
            if (criteria.getInstitution() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstitution(), User_.institution));
            }
            if (criteria.getAgence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgence(), User_.agence));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), User_.createdDate));
            }
            if (criteria.getActivated() != null) {
                specification = specification.and(buildSpecification(criteria.getActivated(), User_.activated));
            }

            if (criteria.getRole() != null) {
                specification = specification.and(buildSpecification(criteria.getRole(),
                    root -> root.join(User_.authorities, JoinType.LEFT).get(Authority_.name)));
            }
        }
        return specification;
    }
}
