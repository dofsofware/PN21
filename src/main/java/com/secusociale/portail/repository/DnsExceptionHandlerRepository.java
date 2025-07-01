package com.secusociale.portail.repository;
import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.DnsExceptionHandler;
import com.secusociale.portail.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DnsExceptionHandlerRepository extends JpaRepository<DnsExceptionHandler, Long> , JpaSpecificationExecutor<DnsExceptionHandler>{

    List<DnsExceptionHandler> findByUser(User user);

    List<DnsExceptionHandler> findByDateCatchingBetween(LocalDateTime start, LocalDateTime end);

    List<DnsExceptionHandler> findByUserAndDateCatchingBetween(User user, LocalDateTime start, LocalDateTime end);

    List<DnsExceptionHandler> findByExceptionContaining(String exceptionText);
}
