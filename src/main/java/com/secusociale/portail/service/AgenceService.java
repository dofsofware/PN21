package com.secusociale.portail.service;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.service.dto.AgenceDTO;
import com.secusociale.portail.service.mapper.AgenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Agence}.
 */
@Service
@Transactional
public class AgenceService {

    private final Logger log = LoggerFactory.getLogger(AgenceService.class);

    private final AgenceRepository agenceRepository;

    private final AgenceMapper agenceMapper;

    public AgenceService(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    /**
     * Save a helpers.
     *
     * @param helpersDTO the entity to save.
     * @return the persisted entity.
     */
    public AgenceDTO save(AgenceDTO helpersDTO) {
        Agence helpers = agenceMapper.toEntity(helpersDTO);
        helpers = agenceRepository.save(helpers);
        return agenceMapper.toDto(helpers);
    }

    /**
     * Get all the helpers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AgenceDTO> findAll(Pageable pageable) {
        return agenceRepository.findAll(pageable)
            .map(agenceMapper::toDto);
    }

    /**
     * Get one helpers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgenceDTO> findOne(Long id) {
        return agenceRepository.findById(id)
            .map(agenceMapper::toDto);
    }

    /**
     * Delete the helpers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Agence : {}", id);
        agenceRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Agence> searchAgences(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Le terme de recherche ne peut pas être vide");
        }
        return agenceRepository.findBySearchTerm(searchTerm.trim());
    }
}
