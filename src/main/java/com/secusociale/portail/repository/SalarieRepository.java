package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Salarie;

import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Salarie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalarieRepository extends JpaRepository<Salarie, Long>, JpaSpecificationExecutor<Salarie> {
    Optional<Salarie> findByUserId(Long userId);

    Optional<Salarie> findByNumeroUnique(String numeroUnique);

    Optional<Salarie> findByNumeroCni(String numeroCni);

    Optional<Object> findOneByNumeroUnique(String numeroUnique);

    Optional<Object> findFirstByNumeroUnique(String numeroUnique);

    @Modifying
    @Query("UPDATE Salarie s SET s.statusGrappeMembre = :statutGrappe, s.motifRejetRetourneGrappe = :motif WHERE s.id = :id")
    void updateStatusGrappeMembreAndMotifRejetRetourneGrappeById(
        @Param("statutGrappe") String statutGrappe,
        @Param("motif") String motif,
        @Param("id") Long id
    );
}
