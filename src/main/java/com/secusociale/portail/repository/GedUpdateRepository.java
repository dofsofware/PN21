package com.secusociale.portail.repository;

import com.secusociale.portail.domain.GedUpdate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GedUpdate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GedUpdateRepository extends JpaRepository<GedUpdate, Long>, JpaSpecificationExecutor<GedUpdate> {

}
