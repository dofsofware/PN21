package com.secusociale.portail.repository;

import com.secusociale.portail.domain.ServerCheck;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServerCheck entity.
 */
@Repository
public interface ServerCheckRepository extends JpaRepository<ServerCheck, Long> {
    ServerCheck findTopByCodeOrderByIdDesc(String code);
    ServerCheck findTopByCodeOrderByIdAsc(String code);
}
