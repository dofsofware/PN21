package com.secusociale.portail.repository;

import com.secusociale.portail.domain.OldImmatriculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the OldImmatriculation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OldImmatriculationRepository extends JpaRepository<OldImmatriculation, Long>, JpaSpecificationExecutor<OldImmatriculation> {


    @Query("select oldImmat from OldImmatriculation oldImmat where oldImmat.status = 'ACTIVEE'")
    List<OldImmatriculation> findAllActives();

    @Query("select oldImmat from OldImmatriculation oldImmat where oldImmat.status = 'ACTIVEE' and oldImmat.agentId = :#{#agentId} ")
    List<OldImmatriculation> findAllActivesByAgent(@Param("agentId") Long agentId);

    List<OldImmatriculation> findAllByUserId(Long id);

    Optional<OldImmatriculation> findByTypeIdentifiantAndNumeroIdentifiant(String typeIdentifiant, String numeroIdentifiant);

    Optional<OldImmatriculation> findByNumeroUnique(String numeroUnique);
}
