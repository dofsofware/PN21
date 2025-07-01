package com.secusociale.portail.service;

import com.secusociale.portail.domain.Configcompte;
import com.secusociale.portail.repository.ConfigcompteRepository;
import com.secusociale.portail.service.dto.ConfigcompteDTO;
import com.secusociale.portail.service.mapper.ConfigcompteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Configcompte}.
 */
@Service
@Transactional
public class ConfigcompteService {

    private final Logger log = LoggerFactory.getLogger(ConfigcompteService.class);

    private final ConfigcompteRepository configcompteRepository;

    private final ConfigcompteMapper configcompteMapper;

    public ConfigcompteService(ConfigcompteRepository configcompteRepository, ConfigcompteMapper configcompteMapper) {
        this.configcompteRepository = configcompteRepository;
        this.configcompteMapper = configcompteMapper;
    }

    /**
     * Save a configcompte.
     *
     * @param configcompteDTO the entity to save.
     * @return the persisted entity.
     */
    public ConfigcompteDTO save(ConfigcompteDTO configcompteDTO) {
        log.debug("Request to save Configcompte : {}", configcompteDTO);
        Configcompte configcompte = configcompteMapper.toEntity(configcompteDTO);
        configcompte = configcompteRepository.save(configcompte);
        return configcompteMapper.toDto(configcompte);
    }

    /**
     * Get all the configcomptes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ConfigcompteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Configcomptes");
        return configcompteRepository.findAll(pageable)
            .map(configcompteMapper::toDto);
    }

    /**
     * Get one configcompte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConfigcompteDTO> findOne(Long id) {
        log.debug("Request to get Configcompte : {}", id);
        return configcompteRepository.findById(id)
            .map(configcompteMapper::toDto);
    }

    /**
     * Get one configcompte by id.
     *
     * @param agenceCode the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConfigcompteDTO> findOneByAgenceCode(String agenceCode) {
        log.debug("Request to get Configcompte : {}", agenceCode);
        return configcompteRepository.findByAgenceCode(agenceCode)
            .map(configcompteMapper::toDto);
    }

    /**
     * Delete the configcompte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Configcompte : {}", id);
        configcompteRepository.deleteById(id);
    }
}
