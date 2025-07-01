package com.secusociale.portail.repository;

import com.secusociale.portail.domain.TestEntity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TestEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

}
