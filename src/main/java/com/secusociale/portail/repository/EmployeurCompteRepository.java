package com.secusociale.portail.repository;

import com.secusociale.portail.domain.EmployeurCompte;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EmployeurCompte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeurCompteRepository extends JpaRepository<EmployeurCompte, Long>, JpaSpecificationExecutor<EmployeurCompte> {

    Page<EmployeurCompte> findAllByNumeroUnique(String numeroUnique, Pageable pageable);
}
