package com.secusociale.portail.service;

import com.secusociale.portail.domain.Activities;
import com.secusociale.portail.repository.ActivitiesRepository;
import com.secusociale.portail.service.dto.ActivitiesDTO;
import com.secusociale.portail.service.mapper.ActivitiesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Activities}.
 */
@Service
@Transactional
public class ActivitiesService {

    private final Logger log = LoggerFactory.getLogger(ActivitiesService.class);

    private final ActivitiesRepository activitiesRepository;

    private final ActivitiesMapper activitiesMapper;

    public ActivitiesService(ActivitiesRepository activitiesRepository, ActivitiesMapper activitiesMapper) {
        this.activitiesRepository = activitiesRepository;
        this.activitiesMapper = activitiesMapper;
    }

    /**
     * Save a activities.
     *
     * @param activitiesDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivitiesDTO save(ActivitiesDTO activitiesDTO) {
        log.debug("Request to save Activities : {}", activitiesDTO);
        Activities activities = activitiesMapper.toEntity(activitiesDTO);
        activities = activitiesRepository.save(activities);
        return activitiesMapper.toDto(activities);
    }

    /**
     * Get all the activities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivitiesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Activities");
        return activitiesRepository.findAll(pageable)
            .map(activitiesMapper::toDto);
    }

    /**
     * Get one activities by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ActivitiesDTO> findOne(Long id) {
        log.debug("Request to get Activities : {}", id);
        return activitiesRepository.findById(id)
            .map(activitiesMapper::toDto);
    }

    /**
     * Delete the activities by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Activities : {}", id);
        activitiesRepository.deleteById(id);
    }
}
