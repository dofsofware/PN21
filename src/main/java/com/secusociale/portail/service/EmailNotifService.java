package com.secusociale.portail.service;

import com.secusociale.portail.domain.EmailNotif;
import com.secusociale.portail.repository.EmailNotifRepository;
import com.secusociale.portail.service.dto.EmailNotifDTO;
import com.secusociale.portail.service.mapper.EmailNotifMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EmailNotif}.
 */
@Service
@Transactional
public class EmailNotifService {

    private final Logger log = LoggerFactory.getLogger(EmailNotifService.class);

    private final EmailNotifRepository emailNotifRepository;

    private final EmailNotifMapper emailNotifMapper;

    public EmailNotifService(EmailNotifRepository emailNotifRepository, EmailNotifMapper emailNotifMapper) {
        this.emailNotifRepository = emailNotifRepository;
        this.emailNotifMapper = emailNotifMapper;
    }

    /**
     * Save a emailNotif.
     *
     * @param emailNotifDTO the entity to save.
     * @return the persisted entity.
     */
    public EmailNotifDTO save(EmailNotifDTO emailNotifDTO) {
        log.debug("Request to save EmailNotif : {}", emailNotifDTO);
        EmailNotif emailNotif = emailNotifMapper.toEntity(emailNotifDTO);
        emailNotif = emailNotifRepository.save(emailNotif);
        return emailNotifMapper.toDto(emailNotif);
    }

    /**
     * Get all the emailNotifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmailNotifDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmailNotifs");
        return emailNotifRepository.findAll(pageable)
            .map(emailNotifMapper::toDto);
    }

    /**
     * Get one emailNotif by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmailNotifDTO> findOne(Long id) {
        log.debug("Request to get EmailNotif : {}", id);
        return emailNotifRepository.findById(id)
            .map(emailNotifMapper::toDto);
    }

    /**
     * Delete the emailNotif by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmailNotif : {}", id);
        emailNotifRepository.deleteById(id);
    }
}
