package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Helpers;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Helpers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HelpersRepository extends JpaRepository<Helpers, Long>, JpaSpecificationExecutor<Helpers> {

}
