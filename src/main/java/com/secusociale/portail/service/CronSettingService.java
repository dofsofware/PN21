package com.secusociale.portail.service;

import com.secusociale.portail.domain.CronSetting;
import com.secusociale.portail.repository.CronSettingRepository;
import com.secusociale.portail.service.dto.CronSettingDTO;
import com.secusociale.portail.service.mapper.CronSettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CronSetting}.
 */
@Service
@Transactional
public class CronSettingService {

    private final Logger log = LoggerFactory.getLogger(CronSettingService.class);

    private final CronSettingRepository cronSettingRepository;

    private final CronSettingMapper cronSettingMapper;

    public CronSettingService(CronSettingRepository cronSettingRepository, CronSettingMapper cronSettingMapper) {
        this.cronSettingRepository = cronSettingRepository;
        this.cronSettingMapper = cronSettingMapper;
    }

    /**
     * Save a cronSetting.
     *
     * @param cronSettingDTO the entity to save.
     * @return the persisted entity.
     */
    public CronSettingDTO save(CronSettingDTO cronSettingDTO) {
        log.debug("Request to save CronSetting : {}", cronSettingDTO);
        CronSetting cronSetting = cronSettingMapper.toEntity(cronSettingDTO);
        cronSetting = cronSettingRepository.save(cronSetting);
        return cronSettingMapper.toDto(cronSetting);
    }

    /**
     * Get all the cronSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CronSettingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CronSettings");
        return cronSettingRepository.findAll(pageable)
            .map(cronSettingMapper::toDto);
    }

    /**
     * Get one cronSetting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CronSettingDTO> findOne(Long id) {
        log.debug("Request to get CronSetting : {}", id);
        return cronSettingRepository.findById(id)
            .map(cronSettingMapper::toDto);
    }

    /**
     * Get one cronSetting by code.
     *
     * @param code the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CronSettingDTO> getOne(String code) {
        log.debug("Request to get CronSetting : {}", code);
        return cronSettingRepository.findByCode(code)
            .map(cronSettingMapper::toDto);
    }

    /**
     * Delete the cronSetting by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CronSetting : {}", id);
        cronSettingRepository.deleteById(id);
    }
}
