package com.secusociale.portail.service;

import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.dto.SalarieDTO;
import com.secusociale.portail.service.dto.UserDTO;
import com.secusociale.portail.service.mapper.SalarieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Salarie}.
 */
@Service
@Transactional
public class SalarieService {

    private final Logger log = LoggerFactory.getLogger(SalarieService.class);

    private final SalarieRepository salarieRepository;
    private final UserRepository userRepository;

    private final SalarieMapper salarieMapper;

    public SalarieService(SalarieRepository salarieRepository, UserRepository userRepository, SalarieMapper salarieMapper) {
        this.salarieRepository = salarieRepository;
        this.userRepository = userRepository;
        this.salarieMapper = salarieMapper;
    }

    /**
     * Save a salarie.
     *
     * @param salarieDTO the entity to save.
     * @return the persisted entity.
     */
    public SalarieDTO save(SalarieDTO salarieDTO) {
        log.debug("Request to save Salarie : {}", salarieDTO);
        Salarie salarie = salarieMapper.toEntity(salarieDTO);
        salarie = salarieRepository.save(salarie);
        SalarieDTO sdto = salarieMapper.toDto(salarie);
        UserDTO userDTO = new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null)));
        sdto.setUser(userDTO);
        return sdto;
    }

    /**
     * Get all the salaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SalarieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Salaries");
        return salarieRepository.findAll(pageable)
            .map(salarieMapper::toDto).map(salarieDTO -> {
                salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
                return salarieDTO;
            });
    }

    /**
     * Get one salarie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SalarieDTO> findOne(Long id) {
        log.debug("Request to get Salarie : {}", id);
        return salarieRepository.findById(id)
            .map(salarieMapper::toDto).map(salarieDTO -> {
                salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
                return salarieDTO;
            });
    }

    /**
     * Get one salarie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Salarie> findById(Long id) {
        log.debug("Request to get Salarie : {}", id);
        return salarieRepository.findById(id);
    }

    /**
     * Get one salarie by userId.
     *
     * @param userId the userId of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SalarieDTO> findByUserId(Long userId) {
        log.debug("Request to get Salarie by userId : {}", userId);
        return salarieRepository.findByUserId(userId)
            .map(salarieMapper::toDto).map(salarieDTO -> {
                salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
                return salarieDTO;
            });
    }

    @Transactional(readOnly = true)
    public Optional<SalarieDTO> findSalarieDtoId(Long id) {
        log.debug("Request to get Salarie : {}", id);
        return salarieRepository.findById(id).map(salarieMapper::toDto).map(salarieDTO -> {
            salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
            return salarieDTO;
        });
    }

    /**
     * Get one salarie by numeroUnique.
     *
     * @param numeroUnique the userId of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SalarieDTO> findByNumeroUnique(String numeroUnique) {
        log.debug("Request to get Salarie by numeroUnique : {}", numeroUnique);
        return salarieRepository.findByNumeroUnique(numeroUnique)
            .map(salarieMapper::toDto).map(salarieDTO -> {
                salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
                return salarieDTO;
            });
    }

    /**
     * Get one salarie by numeroCni.
     *
     * @param numeroCni the userId of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SalarieDTO> findByNumeroCni(String numeroCni) {
        log.debug("Request to get Salarie by numeroCni : {}", numeroCni);
        return salarieRepository.findByNumeroCni(numeroCni)
            .map(salarieMapper::toDto).map(salarieDTO -> {
                salarieDTO.setUser(new UserDTO(Objects.requireNonNull(userRepository.findById(salarieDTO.getUserId()).orElse(null))));
                return salarieDTO;
            });
    }

    /**
     * Delete the salarie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Salarie : {}", id);
        salarieRepository.deleteById(id);
    }
}
