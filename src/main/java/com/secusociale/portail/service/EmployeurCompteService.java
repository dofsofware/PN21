package com.secusociale.portail.service;

import com.secusociale.portail.domain.EmployeurCompte;
import com.secusociale.portail.repository.EmployeurCompteRepository;
import com.secusociale.portail.service.dto.EmployeurCompteDTO;
import com.secusociale.portail.service.mapper.EmployeurCompteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link EmployeurCompte}.
 */
@Service
@Transactional
public class EmployeurCompteService {

    private final Logger log = LoggerFactory.getLogger(EmployeurCompteService.class);

    private final EmployeurCompteRepository employeurCompteRepository;

    private final EmployeurCompteMapper employeurCompteMapper;

    public EmployeurCompteService(EmployeurCompteRepository employeurCompteRepository, EmployeurCompteMapper employeurCompteMapper) {
        this.employeurCompteRepository = employeurCompteRepository;
        this.employeurCompteMapper = employeurCompteMapper;
    }

    /**
     * Save a employeurCompte.
     *
     * @param employeurCompteDTO the entity to save.
     * @return the persisted entity.
     */
    public EmployeurCompteDTO save(EmployeurCompteDTO employeurCompteDTO) {
        log.debug("Request to save EmployeurCompte : {}", employeurCompteDTO);
        EmployeurCompte employeurCompte = employeurCompteMapper.toEntity(employeurCompteDTO);
        employeurCompte = employeurCompteRepository.save(employeurCompte);
        return employeurCompteMapper.toDto(employeurCompte);
    }

    /**
     * Get all the employeurComptes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeurCompteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmployeurComptes");
        return employeurCompteRepository.findAll(pageable)
            .map(employeurCompteMapper::toDto);
    }

    /**
     * Get one employeurCompte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmployeurCompteDTO> findOne(Long id) {
        log.debug("Request to get EmployeurCompte : {}", id);
        return employeurCompteRepository.findById(id)
            .map(employeurCompteMapper::toDto);
    }

    /**
     * Get list employeurCompte by numeroUnique.
     *
     * @param numeroUnique the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Page<EmployeurCompteDTO> finAllByNumeroUnique(String numeroUnique, Pageable pageable) {
        log.debug("Request to get EmployeurCompte : {}", numeroUnique);
        return employeurCompteRepository.findAllByNumeroUnique(numeroUnique, pageable)
            .map(employeurCompteMapper::toDto);
    }


    /**
     * Delete the employeurCompte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmployeurCompte : {}", id);
        employeurCompteRepository.deleteById(id);
    }
}
