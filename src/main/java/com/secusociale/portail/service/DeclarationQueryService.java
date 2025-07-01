package com.secusociale.portail.service;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.Declaration_;
import com.secusociale.portail.repository.DeclarationRepository;
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
public class DeclarationQueryService extends QueryService<Declaration> {

    private final Logger log = LoggerFactory.getLogger(DeclarationQueryService.class);

    private final DeclarationRepository declarationRepository;


    public DeclarationQueryService(DeclarationRepository declarationRepository) {
        this.declarationRepository = declarationRepository;

    }

    /**
     * Return a {@link List} of {@link Declaration} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Declaration> findByCriteria(DeclarationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Declaration> specification = createSpecification(criteria);
        return declarationRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Declaration} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Declaration> findByCriteria(DeclarationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Declaration> specification = createSpecification(criteria);
        return declarationRepository.findAll(specification, page);
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
        final Specification<Declaration> specification = createSpecification(criteria);
        return declarationRepository.count(specification);
    }

    /**
     * Function to convert {@link DeclarationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Declaration> createSpecification(DeclarationCriteria criteria) {
        Specification<Declaration> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Declaration_.id));
            }
            if (criteria.getTypeIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIdentifiant(), Declaration_.typeIdentifiant));
            }
            if (criteria.getIdIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdIdentifiant(), Declaration_.idIdentifiant));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Declaration_.status));
            }
            if (criteria.getCodeAgenceCSS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeAgenceCSS(), Declaration_.codeAgenceCSS));
            }
            if (criteria.getDebutCotisation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDebutCotisation(), Declaration_.debutCotisation));
            }
            if (criteria.getFinCotisation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinCotisation(), Declaration_.finCotisation));
            }
            if (criteria.getCreateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateAt(), Declaration_.createAt));
            }
            if (criteria.getCodeAgenceIPRES() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeAgenceIPRES(), Declaration_.codeAgenceIPRES));
            }
            if (criteria.getFileURL() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileURL(), Declaration_.fileURL));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), Declaration_.fileName));
            }
            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), Declaration_.numeroUnique));
            }
            if (criteria.getiPDIDTRAITEMENT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getiPDIDTRAITEMENT(), Declaration_.iPDIDTRAITEMENT));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), Declaration_.details));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), Declaration_.raisonSociale));
            }
            if (criteria.getReponseBrute() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReponseBrute(), Declaration_.reponseBrute));
            }
            if (criteria.getProcessFlowId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessFlowId(), Declaration_.processFlowId));
            }
            if (criteria.getRaisonSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSociale(), Declaration_.raisonSociale));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Declaration_.address));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeDeclaration(), Declaration_.typeDeclaration));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), Declaration_.uuid));
            }
            if (criteria.getOwnerID() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOwnerID(), Declaration_.ownerID));
            }
            if (criteria.getIsUploaded() != null) {
                specification = specification.and(buildSpecification(criteria.getIsUploaded(), Declaration_.isUploaded));
            }
            if (criteria.getIsRead() != null) {
                specification = specification.and(buildSpecification(criteria.getIsRead(), Declaration_.isRead));
            }
        }
        return specification;
    }
}
