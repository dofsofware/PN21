package com.secusociale.portail.service.impl;

import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.PaiementService;
import com.secusociale.portail.domain.Paiement;
import com.secusociale.portail.repository.PaiementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Paiement}.
 */
@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {

    private final Logger log = LoggerFactory.getLogger(PaiementServiceImpl.class);

    private final PaiementRepository paiementRepository;

    private final UserRepository userRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository,UserRepository userRepository) {
        this.paiementRepository = paiementRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save a paiement.
     *
     * @param paiement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Paiement save(Paiement paiement) {
        log.debug("Request to save Paiement : {}", paiement);

        return paiementRepository.save(paiement);
    }

    /**
     * Get all the paiements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Paiement> findAll(Pageable pageable) {
        log.debug("Request to get all Paiements");
        return paiementRepository.findAll(pageable);
    }

    /**
     * Get one paiement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Paiement> findOne(Long id) {
        log.debug("Request to get Paiement : {}", id);
        return paiementRepository.findById(id);
    }

    /**
     * Delete the paiement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Paiement : {}", id);
        paiementRepository.deleteById(id);
    }


    @Override
    public List<Paiement> findAllByUserId(Long userId) {


            return this.paiementRepository.findByUserId(userId);


    }
}
