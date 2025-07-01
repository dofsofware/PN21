package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DMT;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the DMT entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DMTRepository extends JpaRepository<DMT, Long>, JpaSpecificationExecutor<DMT> {
    List<DMT> findAllByStatus(String status);
}
