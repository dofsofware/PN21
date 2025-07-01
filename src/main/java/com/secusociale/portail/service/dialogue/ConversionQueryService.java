package com.secusociale.portail.service.dialogue;

import com.secusociale.portail.domain.dialogue.Conversation;
import com.secusociale.portail.domain.dialogue.Conversation_;
import com.secusociale.portail.repository.dialogue.ConversationRepository;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConversionQueryService extends QueryService<Conversation> {

    private final Logger log = LoggerFactory.getLogger(ConversionQueryService.class);

    private final ConversationRepository conversationRepository;

    @Transactional(readOnly = true)
    public Page<Conversation> findByCriteria(ConversationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Conversation> specification = createSpecification(criteria);
        return conversationRepository.findAll(specification, page);
    }


    /**
     * Function to convert {@link ConversationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Conversation> createSpecification(ConversationCriteria criteria) {
        Specification<Conversation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Conversation_.id));
            }
            if (criteria.getUserInit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserInit(), Conversation_.userInit));
            }

            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Conversation_.status));
            }
            if (criteria.getAgence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgence(), Conversation_.agence));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Conversation_.title));
            }

            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Conversation_.createdDate));
            }
        }
        return specification;
    }
}
