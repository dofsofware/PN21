package com.secusociale.portail.repository;

import com.secusociale.portail.domain.AttemptCache;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the AttemptCache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttemptCacheRepository extends JpaRepository<AttemptCache, Long> {
    Optional<AttemptCache> findByKey(String key);
}
