package com.secusociale.portail.repository;

import com.secusociale.portail.domain.PieceJointe;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PieceJointe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PieceJointeRepository extends JpaRepository<PieceJointe, Long>, JpaSpecificationExecutor<PieceJointe> {

    List<PieceJointe> findByEntityIdAndEntityType(Long entityId,String entityType);

}
