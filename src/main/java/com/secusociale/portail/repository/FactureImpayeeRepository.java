package com.secusociale.portail.repository;

import com.secusociale.portail.domain.FactureImpayee;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FactureImpayee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureImpayeeRepository extends JpaRepository<FactureImpayee, Long>, JpaSpecificationExecutor<FactureImpayee> {

}
