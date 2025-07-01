package com.secusociale.portail.service;

import com.secusociale.portail.domain.DnsExceptionHandler;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.DnsExceptionHandlerRepository;
import com.secusociale.portail.service.dto.DnsExceptionHandlerCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DnsExceptionHandlerService {

    private final DnsExceptionHandlerRepository dnsExceptionHandlerRepository;

    @Autowired
    public DnsExceptionHandlerService(DnsExceptionHandlerRepository dnsExceptionHandlerRepository) {
        this.dnsExceptionHandlerRepository = dnsExceptionHandlerRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DnsExceptionHandler save(DnsExceptionHandler dnsExceptionHandler) {
        return dnsExceptionHandlerRepository.save(dnsExceptionHandler);
    }

    public Page<DnsExceptionHandler> findAll(DnsExceptionHandlerCriteria criteria, Pageable pageable) {

        final Specification<DnsExceptionHandler> specification = createSpecification(criteria);
        return dnsExceptionHandlerRepository.findAll(specification,pageable);
    }

    public Optional<DnsExceptionHandler> findById(Long id) {
        return dnsExceptionHandlerRepository.findById(id);
    }

    public void deleteById(Long id) {
        dnsExceptionHandlerRepository.deleteById(id);
    }

    public List<DnsExceptionHandler> findByUser(User user) {
        return dnsExceptionHandlerRepository.findByUser(user);
    }

    public List<DnsExceptionHandler> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return dnsExceptionHandlerRepository.findByDateCatchingBetween(start, end);
    }

    public List<DnsExceptionHandler> findByUserAndDateRange(User user, LocalDateTime start, LocalDateTime end) {
        return dnsExceptionHandlerRepository.findByUserAndDateCatchingBetween(user, start, end);
    }

    public List<DnsExceptionHandler> findByExceptionText(String exceptionText) {
        return dnsExceptionHandlerRepository.findByExceptionContaining(exceptionText);
    }

    private Specification<DnsExceptionHandler> createSpecification(DnsExceptionHandlerCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            // Filtrer par date de capture
            if (criteria.getDateCatching() != null) {
                LocalDate startDate = criteria.getDateCatching();
                LocalDate endDate = startDate.plusDays(1);
                predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dateCatching"), startDate.atStartOfDay()),
                    criteriaBuilder.lessThan(root.get("dateCatching"), endDate.atStartOfDay()));
            }
            // Filtrer par code d'erreur
            if (criteria.getErrorCode() != null && !criteria.getErrorCode().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("errorCode"), "%" + criteria.getErrorCode() + "%"));
            }

            // Filtrer par état traité
            if (criteria.isTreated() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("isTreated"), criteria.isTreated()));
            }

            // Filtrer par raison sociale
            if (criteria.getRaisonSociale() != null && !criteria.getRaisonSociale().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("raisonSociale"), "%" + criteria.getRaisonSociale() + "%"));
            }

            return predicate;
        };
    }

}
