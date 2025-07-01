package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Configcompte;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Configcompte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfigcompteRepository extends JpaRepository<Configcompte, Long>, JpaSpecificationExecutor<Configcompte> {

    Optional<Configcompte> findByAgenceCode(String agenceCode);
}
