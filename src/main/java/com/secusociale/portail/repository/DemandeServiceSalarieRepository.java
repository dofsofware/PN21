package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DemandeServiceSalarie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DemandeServiceSalarie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeServiceSalarieRepository extends JpaRepository<DemandeServiceSalarie, Long>, JpaSpecificationExecutor<DemandeServiceSalarie> {

}
