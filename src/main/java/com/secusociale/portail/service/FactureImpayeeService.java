package com.secusociale.portail.service;

import com.secusociale.portail.domain.FactureImpayee;
import com.secusociale.portail.repository.FactureImpayeeRepository;
import com.secusociale.portail.service.dto.FactureImpayeeDTO;
import com.secusociale.portail.service.mapper.FactureImpayeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FactureImpayee}.
 */
@Service
@Transactional
public class FactureImpayeeService {

    private final Logger log = LoggerFactory.getLogger(FactureImpayeeService.class);

    private final FactureImpayeeRepository factureImpayeeRepository;

    private final FactureImpayeeMapper factureImpayeeMapper;

    public FactureImpayeeService(FactureImpayeeRepository factureImpayeeRepository, FactureImpayeeMapper factureImpayeeMapper) {
        this.factureImpayeeRepository = factureImpayeeRepository;
        this.factureImpayeeMapper = factureImpayeeMapper;
    }

    /**
     * Save a factureImpayee.
     *
     * @param factureImpayeeDTO the entity to save.
     * @return the persisted entity.
     */
    public FactureImpayeeDTO save(FactureImpayeeDTO factureImpayeeDTO) {
        log.debug("Request to save FactureImpayee : {}", factureImpayeeDTO);
        FactureImpayee factureImpayee = factureImpayeeMapper.toEntity(factureImpayeeDTO);
        factureImpayee = factureImpayeeRepository.save(factureImpayee);
        return factureImpayeeMapper.toDto(factureImpayee);
    }

    /**
     * Get all the factureImpayees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FactureImpayeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FactureImpayees");
        return factureImpayeeRepository.findAll(pageable)
            .map(factureImpayeeMapper::toDto);
    }

    /**
     * Get one factureImpayee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FactureImpayeeDTO> findOne(Long id) {
        log.debug("Request to get FactureImpayee : {}", id);
        return factureImpayeeRepository.findById(id)
            .map(factureImpayeeMapper::toDto);
    }

    /**
     * Delete the factureImpayee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FactureImpayee : {}", id);
        factureImpayeeRepository.deleteById(id);
    }
}
