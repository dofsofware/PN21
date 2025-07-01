package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Activities;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Activities entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, Long>, JpaSpecificationExecutor<Activities> {

}
