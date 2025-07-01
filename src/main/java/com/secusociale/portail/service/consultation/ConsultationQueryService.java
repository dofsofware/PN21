package com.secusociale.portail.service.consultation;

import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import com.secusociale.portail.domain.consultation.EmployeurConsultation_;
import com.secusociale.portail.repository.consultation.ConsultationRepository;
import com.secusociale.portail.service.dto.consultation.EmployeurConsultationCriteria;
import com.secusociale.portail.service.dto.dialogue.ConversationCriteria;
import io.github.jhipster.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsultationQueryService extends QueryService<EmployeurConsultation> {
    private final ConsultationRepository consultationRepository;
    private final Logger log = LoggerFactory.getLogger(ConsultationQueryService.class);

    @Transactional(readOnly = true)
    public Page<EmployeurConsultation> findByCriteria(EmployeurConsultationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EmployeurConsultation> specification = createSpecification(criteria);
        return consultationRepository.findAll(specification, page);
    }


    /**
     * Function to convert {@link ConversationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EmployeurConsultation> createSpecification(EmployeurConsultationCriteria criteria) {
        Specification<EmployeurConsultation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EmployeurConsultation_.id));
            }
            if (criteria.getAgentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgentId(), EmployeurConsultation_.agentId));
            }

            if (criteria.getNumeroUnique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroUnique(), EmployeurConsultation_.numeroUnique));
            }

            if (criteria.getNumeroCni() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroCni(), EmployeurConsultation_.numeroCni));
            }

            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), EmployeurConsultation_.prenom));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), EmployeurConsultation_.nom));
            }

            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), EmployeurConsultation_.email));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), EmployeurConsultation_.telephone));
            }

            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), EmployeurConsultation_.createdAt));
            }

            if (criteria.getValidateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidateAt(), EmployeurConsultation_.validateAt));
            }
        }
        return specification;
    }
}
