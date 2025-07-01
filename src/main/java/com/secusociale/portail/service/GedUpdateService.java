package com.secusociale.portail.service;

import com.secusociale.portail.domain.GedUpdate;
import com.secusociale.portail.repository.GedUpdateRepository;
import com.secusociale.portail.service.dto.GedUpdateDTO;
import com.secusociale.portail.service.mapper.GedUpdateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GedUpdate}.
 */
@Service
@Transactional
public class GedUpdateService {

    private final Logger log = LoggerFactory.getLogger(GedUpdateService.class);

    private final GedUpdateRepository gedUpdateRepository;

    private final GedUpdateMapper gedUpdateMapper;

    public GedUpdateService(GedUpdateRepository gedUpdateRepository, GedUpdateMapper gedUpdateMapper) {
        this.gedUpdateRepository = gedUpdateRepository;
        this.gedUpdateMapper = gedUpdateMapper;
    }

    /**
     * Save a gedUpdate.
     *
     * @param gedUpdateDTO the entity to save.
     * @return the persisted entity.
     */
    public GedUpdateDTO save(GedUpdateDTO gedUpdateDTO) {
        log.debug("Request to save GedUpdate : {}", gedUpdateDTO);
        GedUpdate gedUpdate = gedUpdateMapper.toEntity(gedUpdateDTO);
        gedUpdate = gedUpdateRepository.save(gedUpdate);
        return gedUpdateMapper.toDto(gedUpdate);
    }

    /**
     * Get all the gedUpdates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GedUpdateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GedUpdates");
        return gedUpdateRepository.findAll(pageable)
            .map(gedUpdateMapper::toDto);
    }

    /**
     * Get one gedUpdate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GedUpdateDTO> findOne(Long id) {
        log.debug("Request to get GedUpdate : {}", id);
        return gedUpdateRepository.findById(id)
            .map(gedUpdateMapper::toDto);
    }

    /**
     * Delete the gedUpdate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GedUpdate : {}", id);
        gedUpdateRepository.deleteById(id);
    }
}
