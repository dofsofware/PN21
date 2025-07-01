package com.secusociale.portail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.domain.DMT;
import com.secusociale.portail.repository.DMTRepository;
import com.secusociale.portail.service.dto.DMTDTO;
import com.secusociale.portail.service.immatriculation.DmtService;
import com.secusociale.portail.service.mapper.DMTMapper;
import com.secusociale.portail.service.soap.cmdmt.CmDmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DMT}.
 */
@Service
@Transactional
public class DMTService {

    private final Logger log = LoggerFactory.getLogger(DMTService.class);

    private final DMTRepository dMTRepository;

    private final DMTMapper dMTMapper;

    @Autowired
    private DmtService dmtService;

    public DMTService(DMTRepository dMTRepository, DMTMapper dMTMapper) {
        this.dMTRepository = dMTRepository;
        this.dMTMapper = dMTMapper;
    }

    /**
     * Save a dMT.
     *
     * @param dMTDTO the entity to save.
     * @return the persisted entity.
     */
    public DMTDTO save(DMTDTO dMTDTO) {
        log.debug("Request to save DMT : {}", dMTDTO);
        DMT dMT = dMTMapper.toEntity(dMTDTO);
        dMT = dMTRepository.save(dMT);
        return dMTMapper.toDto(dMT);
    }

    /**
     * Get all the dMTS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DMTDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DMTS");
        return dMTRepository.findAll(pageable)
            .map(dMTMapper::toDto);
    }

    /**
     * Get one dMT by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DMTDTO> findOne(Long id) {
        log.debug("Request to get DMT : {}", id);
        return dMTRepository.findById(id)
            .map(dMTMapper::toDto);
    }

    /**
     * Delete the dMT by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DMT : {}", id);
        dMTRepository.deleteById(id);
    }

    public List<DMTDTO> findAllPending() {
        String status = "PENDING";
        log.debug("find by status : {}", status);
        return dMTRepository.findAllByStatus(status).stream()
            .map(dMTMapper::toDto).collect(Collectors.toList());
    }

    public void sendDmtToPSRM() {
        findAllPending().forEach(dmtdto -> {
            try {
                CmDmt dmt = dmtService.dmt(dmtdto.toModel());
                if (dmt != null) {
                    dmtdto.reponsebrute((new ObjectMapper()).writeValueAsString(dmt))
                        .status("SUCCESS");

                    save(dmtdto);
                } else {
                    dmtdto.status("FAILED");
                    save(dmtdto);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    public DMTDTO sendOnDmtToPSRM(Long idDmt) {
        Optional<DMTDTO> optional = findOne(idDmt);
        if (optional.isPresent()) {
            DMTDTO dmtdto = optional.get();
            try {
                CmDmt dmt = dmtService.dmt(dmtdto.toModel());
                if (dmt != null) {
                    dmtdto.reponsebrute((new ObjectMapper()).writeValueAsString(dmt))
                        .status("SUCCESS");

                    save(dmtdto);
                } else {
                    dmtdto.status("FAILED");
                    save(dmtdto);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return dmtdto;
        }

        return null;

    }

    @Scheduled(cron = "#{@sendDeclarationJobExpression}")
    public void sendDmtToPSRMJob() {
        sendDmtToPSRM();
    }
}
