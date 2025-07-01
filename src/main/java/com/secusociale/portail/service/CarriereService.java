package com.secusociale.portail.service;

import com.secusociale.portail.domain.Carriere;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.repository.CarriereRepository;
import com.secusociale.portail.service.dto.CarriereDTO;
import com.secusociale.portail.service.mapper.CarriereMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Carriere}.
 */
@Service
@Transactional
public class CarriereService {

    private final Logger log = LoggerFactory.getLogger(CarriereService.class);

    private final CarriereRepository carriereRepository;

    private final CarriereMapper carriereMapper;

    public CarriereService(CarriereRepository carriereRepository, CarriereMapper carriereMapper) {
        this.carriereRepository = carriereRepository;
        this.carriereMapper = carriereMapper;
    }

    /**
     * Save a carriere.
     *
     * @param carriereDTO the entity to save.
     * @return the persisted entity.
     */
    public CarriereDTO save(CarriereDTO carriereDTO) {
        log.debug("Request to save Carriere : {}", carriereDTO);
        Carriere carriere = carriereMapper.toEntity(carriereDTO);
        carriere = carriereRepository.save(carriere);
        return carriereMapper.toDto(carriere);
    }

    /**
     * Get all the carrieres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CarriereDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Carrieres");
        return carriereRepository.findAll(pageable)
            .map(carriereMapper::toDto);
    }

    /**
     * Get one carriere by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarriereDTO> findOne(Long id) {
        log.debug("Request to get Carriere : {}", id);
        return carriereRepository.findById(id)
            .map(carriereMapper::toDto);
    }


    /**
     * Get one carriere by id.
     *
     * @param employeur .
     * @param debut .
     * @param fin .
     * @param salarie .
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarriereDTO> findSalarie(String employeur, LocalDate debut, LocalDate fin, Salarie salarie) {
        return carriereRepository.findByNumeroUniqueEmployeurAndDebutCotisationAndFinCotisationAndSalarie(employeur, debut, fin, salarie)
            .map(carriereMapper::toDto);
    }

    /**
     * Delete the carriere by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Carriere : {}", id);
        carriereRepository.deleteById(id);
    }
}
