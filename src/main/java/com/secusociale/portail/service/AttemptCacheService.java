package com.secusociale.portail.service;

import com.secusociale.portail.domain.AttemptCache;
import com.secusociale.portail.repository.AttemptCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AttemptCache}.
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AttemptCacheService {

    private final Logger log = LoggerFactory.getLogger(AttemptCacheService.class);

    private final AttemptCacheRepository attemptCacheRepository;

    public AttemptCacheService(AttemptCacheRepository attemptCacheRepository) {
        this.attemptCacheRepository = attemptCacheRepository;
    }

    /**
     * Save a attemptCache.
     *
     * @param attemptCache the entity to save.
     * @return the persisted entity.
     */
    public AttemptCache save(AttemptCache attemptCache) {
        log.debug("Request to save AttemptCache : {}", attemptCache);
        return attemptCacheRepository.save(attemptCache);
    }

    /**
     * Get all the attemptCaches.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AttemptCache> findAll() {
        log.debug("Request to get all AttemptCaches");
        return attemptCacheRepository.findAll();
    }

    /**
     * Get one attemptCache by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttemptCache> findOne(Long id) {
        log.debug("Request to get AttemptCache : {}", id);
        return attemptCacheRepository.findById(id);
    }

    /**
     * Delete the attemptCache by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AttemptCache : {}", id);
        attemptCacheRepository.deleteById(id);
    }
}
