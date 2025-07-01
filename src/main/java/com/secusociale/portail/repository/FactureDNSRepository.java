package com.secusociale.portail.repository;

import com.secusociale.portail.domain.FactureDNS;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FactureDNS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureDNSRepository extends JpaRepository<FactureDNS, Long>, JpaSpecificationExecutor<FactureDNS> {
    Page<FactureDNS> findAllByNumeroUnique(String numeroUnique, Pageable pageable);

    Optional<FactureDNS> findByIdDeclaration(Long idDeclaration);

    List<FactureDNS> findAllByIdDeclaration(Long idDeclaration);

    Optional<FactureDNS> findByNumeroFacture(String numeroFacture);

    @Query("SELECT f FROM FactureDNS f WHERE f.numeroUnique = :numeroUnique " +
        "AND f.debutPeriode <= :dateFin AND f.finPeriode >= :dateDebut")
    List<FactureDNS> findByNumeroUniqueAndPeriodeChevauchante(
        @Param("numeroUnique") String numeroUnique,
        @Param("dateDebut") Instant dateDebut,
        @Param("dateFin") Instant dateFin);



}
