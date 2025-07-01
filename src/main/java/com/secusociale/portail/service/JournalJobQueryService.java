package com.secusociale.portail.service;

import com.secusociale.portail.domain.JournalJob;
import com.secusociale.portail.domain.JournalJob_;
import com.secusociale.portail.domain.JournalJob;
import com.secusociale.portail.repository.JournalJobRepository;
import com.secusociale.portail.repository.JournalJobRepository;
import com.secusociale.portail.service.dto.JournalJobCriteria;
import com.secusociale.portail.service.dto.JournalJobCriteria;
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
 * Service for executing complex queries for {@link JournalJob} entities in the database.
 * The main input is a {@link JournalJobCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JournalJob} or a {@link Page} of {@link JournalJob} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JournalJobQueryService extends QueryService<JournalJob> {

    private final Logger log = LoggerFactory.getLogger(JournalJobQueryService.class);

    private final JournalJobRepository journalJobRepository;


    public JournalJobQueryService(JournalJobRepository journalJobRepository) {
        this.journalJobRepository = journalJobRepository;

    }

    /**
     * Return a {@link List} of {@link JournalJob} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
//    @Transactional(readOnly = true)
//    public List<JournalJob> findByCriteria(JournalJobCriteria criteria) {
//        log.debug("find by criteria : {}", criteria);
//        final Specification<JournalJob> specification = createSpecification(criteria);
//        return journalJobRepository.findAll(specification);
//    }

    /**
     * Return a {@link Page} of {@link JournalJob} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JournalJob> findByCriteria(JournalJobCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JournalJob> specification = createSpecification(criteria);
        return journalJobRepository.findAll(specification, page);
    }


    /**
     * Function to convert {@link JournalJobCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<JournalJob> createSpecification(JournalJobCriteria criteria) {
        Specification<JournalJob> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
             //   specification = specification.and(buildRangeSpecification(criteria.getId(), JournalJob_.id));
            }

        }
        return specification;
    }
}
