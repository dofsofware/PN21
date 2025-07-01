package com.secusociale.portail.service;

import com.secusociale.portail.domain.SuiviJob;
import com.secusociale.portail.repository.SuiviJobRepository;
import com.secusociale.portail.service.dto.SuiviJobDTO;
import com.secusociale.portail.service.mapper.SuiviJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SuiviJob}.
 */
@Service
@Transactional
public class SuiviJobService {

    private final Logger log = LoggerFactory.getLogger(SuiviJobService.class);

    private final SuiviJobRepository suiviJobRepository;

    private final SuiviJobMapper suiviJobMapper;

    public SuiviJobService(SuiviJobRepository suiviJobRepository, SuiviJobMapper suiviJobMapper) {
        this.suiviJobRepository = suiviJobRepository;
        this.suiviJobMapper = suiviJobMapper;
    }

    /**
     * Save a suiviJob.
     *
     * @param suiviJobDTO the entity to save.
     * @return the persisted entity.
     */
    public SuiviJobDTO save(SuiviJobDTO suiviJobDTO) {
        log.debug("Request to save SuiviJob : {}", suiviJobDTO);
        SuiviJob suiviJob = suiviJobMapper.toEntity(suiviJobDTO);
        suiviJob = suiviJobRepository.save(suiviJob);
        return suiviJobMapper.toDto(suiviJob);
    }

    /**
     * Get all the suiviJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SuiviJobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SuiviJobs");
        return suiviJobRepository.findAll(pageable)
            .map(suiviJobMapper::toDto);
    }

    /**
     * Get one suiviJob by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SuiviJobDTO> findOne(Long id) {
        log.debug("Request to get SuiviJob : {}", id);
        return suiviJobRepository.findById(id)
            .map(suiviJobMapper::toDto);
    }

    /**
     * Get one suiviJob by nom.
     *
     * @param nom the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SuiviJobDTO> findJob(String nom) {
        log.debug("Request to get SuiviJob : {}", nom);
        return suiviJobRepository.findByNom(nom)
            .map(suiviJobMapper::toDto);
    }

    /**
     * Delete the suiviJob by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SuiviJob : {}", id);
        suiviJobRepository.deleteById(id);
    }
}
