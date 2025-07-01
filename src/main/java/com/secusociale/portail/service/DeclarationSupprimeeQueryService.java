package com.secusociale.portail.service;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.DeclarationSupprime;
import com.secusociale.portail.domain.DeclarationSupprime_;
import com.secusociale.portail.repository.DeclarationSupprimeRepository;
import com.secusociale.portail.service.dto.DeclarationCriteria;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for {@link Declaration} entities in the database.
 * The main input is a {@link DeclarationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Declaration} or a {@link Page} of {@link Declaration} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeclarationSupprimeeQueryService extends QueryService<DeclarationSupprime> {

    private final Logger log = LoggerFactory.getLogger(DeclarationSupprimeeQueryService.class);

    private final DeclarationSupprimeRepository declarationSupprimeRepository;


    public DeclarationSupprimeeQueryService(DeclarationSupprimeRepository declarationSupprimeRepository) {
        this.declarationSupprimeRepository = declarationSupprimeRepository;

    }

    /**
     * Return a {@link List} of {@link Declaration} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeclarationSupprime> findByCriteria(DeclarationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeclarationSupprime> specification = createSpecification(criteria);
        return declarationSupprimeRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Declaration} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeclarationSupprime> findByCriteria(DeclarationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeclarationSupprime> specification = createSpecification(criteria);
        return declarationSupprimeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeclarationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeclarationSupprime> specification = createSpecification(criteria);
        return declarationSupprimeRepository.count(specification);
    }

    /**
     * Function to convert {@link DeclarationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DeclarationSupprime> createSpecification(DeclarationCriteria criteria) {
        Specification<DeclarationSupprime> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DeclarationSupprime_.id));
            }
            if (criteria.getTypeIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIdentifiant(), DeclarationSupprime_.typeIdentifiant));
            }
            if (criteria.getIdIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdIdentifiant(), DeclarationSupprime_.idIdentifiant));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), DeclarationSupprime_.status));
            }
            if (criteria.getCodeAgenceCSS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeAgenceCSS(), DeclarationSupprime_.codeAgenceCSS));
            }
            if (criteria.getDebutCotisation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDebutCotisation(), DeclarationSupprime_.debutCotisation));
            }
            if (criteria.getFinCotisation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinCotisation(), DeclarationSupprime_.finCotisation));
            }
            if (criteria.getCreateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateAt(), DeclarationSupprime_.createAt));
            }
            if (criteria.getCodeAgenceIPRES() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeAgenceIPRES(), DeclarationSupprime_.codeAgenceIPRES));
            }
            if (criteria.getFileURL() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileURL(), DeclarationSupprime_.fileURL));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), DeclarationSupprime_.fileName));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), DeclarationSupprime_.numeroUnique));
            }
            if (criteria.getiPDIDTRAITEMENT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getiPDIDTRAITEMENT(), DeclarationSupprime_.iPDIDTRAITEMENT));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), DeclarationSupprime_.details));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), DeclarationSupprime_.raisonSociale));
            }
            if (criteria.getReponseBrute() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReponseBrute(), DeclarationSupprime_.reponseBrute));
            }
            if (criteria.getProcessFlowId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessFlowId(), DeclarationSupprime_.processFlowId));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), DeclarationSupprime_.raisonSociale));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), DeclarationSupprime_.address));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeDeclaration(), DeclarationSupprime_.typeDeclaration));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), DeclarationSupprime_.uuid));
            }
            if (criteria.getOwnerID() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOwnerID(), DeclarationSupprime_.ownerID));
            }
            if (criteria.getIsUploaded() != null) {
                specification = specification.and(buildSpecification(criteria.getIsUploaded(), DeclarationSupprime_.isUploaded));
            }
            if (criteria.getIsRead() != null) {
                specification = specification.and(buildSpecification(criteria.getIsRead(), DeclarationSupprime_.isRead));
            }
        }
        return specification;
    }
}
