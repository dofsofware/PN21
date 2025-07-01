package com.secusociale.portail.service;

import com.secusociale.portail.domain.DemandeServiceSalarie;
import com.secusociale.portail.repository.DemandeServiceSalarieRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.service.dto.DemandeServiceSalarieDTO;
import com.secusociale.portail.service.dto.SalarieDTO;
import com.secusociale.portail.service.mapper.DemandeServiceSalarieMapper;
import com.secusociale.portail.service.mapper.SalarieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DemandeServiceSalarie}.
 */
@Service
@Transactional
public class DemandeServiceSalarieService {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceSalarieService.class);

    private final DemandeServiceSalarieRepository demandeServiceSalarieRepository;
    private final SalarieRepository salarieRepository;
    private final SalarieMapper salarieMapper;

    private final DemandeServiceSalarieMapper demandeServiceSalarieMapper;

    public DemandeServiceSalarieService(DemandeServiceSalarieRepository demandeServiceSalarieRepository, SalarieRepository salarieRepository, SalarieMapper salarieMapper, DemandeServiceSalarieMapper demandeServiceSalarieMapper) {
        this.demandeServiceSalarieRepository = demandeServiceSalarieRepository;
        this.salarieRepository = salarieRepository;
        this.salarieMapper = salarieMapper;
        this.demandeServiceSalarieMapper = demandeServiceSalarieMapper;
    }

    /**
     * Save a demandeServiceSalarie.
     *
     * @param demandeServiceSalarieDTO the entity to save.
     * @return the persisted entity.
     */
    public DemandeServiceSalarieDTO save(DemandeServiceSalarieDTO demandeServiceSalarieDTO) {
        log.debug("Request to save DemandeServiceSalarie : {}", demandeServiceSalarieDTO);
        DemandeServiceSalarie demandeServiceSalarie = demandeServiceSalarieMapper.toEntity(demandeServiceSalarieDTO);
        demandeServiceSalarie = demandeServiceSalarieRepository.save(demandeServiceSalarie);
        return demandeServiceSalarieMapper.toDto(demandeServiceSalarie);
    }

    /**
     * Get all the demandeServiceSalaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandeServiceSalarieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DemandeServiceSalaries");
        if (pageable.getSort().isEmpty()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("date")));
        }
        return demandeServiceSalarieRepository.findAll(pageable)
            .map(demandeServiceSalarieMapper::toDto)
            .map(demandeServiceSalarieDTO -> {
                // Recherche de l'entité Salarie et transformation en SalarieDTO
                SalarieDTO salarieDTO = salarieRepository.findById(demandeServiceSalarieDTO.getSalarieId())
                    .map(salarieMapper::toDto) // Conversion via le mapper au lieu d'un constructeur direct
                    .orElseThrow(() -> new IllegalArgumentException("Salarie not found for ID: " + demandeServiceSalarieDTO.getSalarieId()));
                // Association du SalarieDTO au DemandeServiceSalarieDTO
                demandeServiceSalarieDTO.setSalarie(salarieDTO);
                return demandeServiceSalarieDTO;
            });
    }

    /**
     * Get one demandeServiceSalarie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DemandeServiceSalarieDTO> findOne(Long id) {
        log.debug("Request to get DemandeServiceSalarie : {}", id);
        return demandeServiceSalarieRepository.findById(id)
            .map(demandeServiceSalarieMapper::toDto);
    }

    /**
     * Delete the demandeServiceSalarie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DemandeServiceSalarie : {}", id);
        demandeServiceSalarieRepository.deleteById(id);
    }
}
