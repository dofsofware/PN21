package com.secusociale.portail.repository;

import com.secusociale.portail.domain.SuiviJob;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the SuiviJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuiviJobRepository extends JpaRepository<SuiviJob, Long>, JpaSpecificationExecutor<SuiviJob> {

    Optional<SuiviJob> findByNom(String nom);
}
