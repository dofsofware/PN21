package com.secusociale.portail.service;

import com.secusociale.portail.domain.Helpers;
import com.secusociale.portail.repository.HelpersRepository;
import com.secusociale.portail.service.dto.HelpersDTO;
import com.secusociale.portail.service.mapper.HelpersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Helpers}.
 */
@Service
@Transactional
public class HelpersService {

    private final Logger log = LoggerFactory.getLogger(HelpersService.class);

    private final HelpersRepository helpersRepository;

    private final HelpersMapper helpersMapper;

    public HelpersService(HelpersRepository helpersRepository, HelpersMapper helpersMapper) {
        this.helpersRepository = helpersRepository;
        this.helpersMapper = helpersMapper;
    }

    /**
     * Save a helpers.
     *
     * @param helpersDTO the entity to save.
     * @return the persisted entity.
     */
    public HelpersDTO save(HelpersDTO helpersDTO) {
        log.debug("Request to save Helpers : {}", helpersDTO);
        Helpers helpers = helpersMapper.toEntity(helpersDTO);
        helpers = helpersRepository.save(helpers);
        return helpersMapper.toDto(helpers);
    }

    /**
     * Get all the helpers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HelpersDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Helpers");
        return helpersRepository.findAll(pageable)
            .map(helpersMapper::toDto);
    }

    /**
     * Get one helpers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HelpersDTO> findOne(Long id) {
        log.debug("Request to get Helpers : {}", id);
        return helpersRepository.findById(id)
            .map(helpersMapper::toDto);
    }

    /**
     * Delete the helpers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Helpers : {}", id);
        helpersRepository.deleteById(id);
    }
}
