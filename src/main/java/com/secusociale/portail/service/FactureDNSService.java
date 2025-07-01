package com.secusociale.portail.service;

import com.secusociale.portail.domain.FactureDNS;
import com.secusociale.portail.repository.FactureDNSRepository;
import com.secusociale.portail.service.dto.FactureDNSDTO;
import com.secusociale.portail.service.mapper.FactureDNSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FactureDNS}.
 */
@Service
@Transactional
public class FactureDNSService {

    private final Logger log = LoggerFactory.getLogger(FactureDNSService.class);

    private final FactureDNSRepository factureDNSRepository;

    private final FactureDNSMapper factureDNSMapper;

    public FactureDNSService(FactureDNSRepository factureDNSRepository, FactureDNSMapper factureDNSMapper) {
        this.factureDNSRepository = factureDNSRepository;
        this.factureDNSMapper = factureDNSMapper;
    }

    /**
     * Save a factureDNS.
     *
     * @param idDeclaration .
     * @return the if declaration has a bill.
     */
    public Boolean hasFacture(Long idDeclaration) {
        log.debug("Request to check if declaration has a bill : {}", idDeclaration);
        Optional<FactureDNS> optionalFactureDNS = factureDNSRepository.findByIdDeclaration(idDeclaration);
        return optionalFactureDNS.isPresent();
    }

    /**
     * Save a factureDNS.
     *
     * @param factureDNSDTO the entity to save.
     * @return the persisted entity.
     */
    public FactureDNSDTO save(FactureDNSDTO factureDNSDTO) {
        log.debug("Request to save FactureDNS : {}", factureDNSDTO);
        FactureDNS factureDNS = factureDNSMapper.toEntity(factureDNSDTO);
        factureDNS = factureDNSRepository.save(factureDNS);
        return factureDNSMapper.toDto(factureDNS);
    }

    /**
     * Get all the factureDNS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FactureDNSDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FactureDNS");
        return factureDNSRepository.findAll(pageable)
            .map(factureDNSMapper::toDto);
    }

    /**
     * Get one factureDNS by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FactureDNSDTO> findOne(Long id) {
        log.debug("Request to get FactureDNS : {}", id);
        return factureDNSRepository.findById(id)
            .map(factureDNSMapper::toDto);
    }


    /**
     * Get one factureDNS by idDeclaration.
     *
     * @param idDeclaration the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FactureDNSDTO> findFacture(Long idDeclaration) {
        log.debug("Request to get FactureDNS : {}", idDeclaration);
        return factureDNSRepository.findByIdDeclaration(idDeclaration)
            .map(factureDNSMapper::toDto);
    }

    /**
     * Delete the factureDNS by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FactureDNS : {}", id);
        factureDNSRepository.deleteById(id);
    }

    public List<FactureDNSDTO> findFacturesByDeclaration(Long idDeclaration) {
        List<FactureDNSDTO> resultList = new ArrayList<>();
        List<FactureDNS> factures = factureDNSRepository.findAllByIdDeclaration(idDeclaration);

        for (FactureDNS facture : factures) {
            resultList.add(factureDNSMapper.toDto(facture));
        }

        return resultList;
    }

    public FactureDNS recalculerTotaux(FactureDNS facture) {

        facture.setTauxRG(14.0f);
        facture.setTauxRC(6.0f);

        // Calculer mt_vieillesse_rg et mt_vieillesse_rc
        if (facture.getVieillesseRG() != null) {
            facture.setMtVieillesseRG(facture.getVieillesseRG() * 0.14);
        }

        if (facture.getVieillesseRC() != null) {
            facture.setMtVieillesseRC(facture.getVieillesseRC() * 0.06);
        }

        // Calculer mt_prestation_fam
        if (facture.getPrestationFam() != null && facture.getTauxPrestationFam() != null) {
            facture.setMtPrestationFam(facture.getPrestationFam() * (facture.getTauxPrestationFam() / 100));
        }

        // Calculer mt_atmp
        if (facture.getAtmp() != null && facture.getTauxATMP() != null) {
            facture.setMtAtmp(facture.getAtmp() * (facture.getTauxATMP() / 100));
        }

        // Calculer total_ipres
        Double totalIpres = 0.0;
        if (facture.getVieillesseRG() != null) {
            totalIpres += facture.getVieillesseRG() * 0.14;
        }
        if (facture.getVieillesseRC() != null) {
            totalIpres += facture.getVieillesseRC() * 0.06;
        }
        facture.setTotalIpres(totalIpres);

        // Calculer total_css
        Double totalCss = 0.0;
        if (facture.getPrestationFam() != null && facture.getTauxPrestationFam() != null) {
            totalCss += facture.getPrestationFam() * (facture.getTauxPrestationFam() / 100);
        }
        if (facture.getAtmp() != null && facture.getTauxATMP() != null) {
            totalCss += facture.getAtmp() * (facture.getTauxATMP() / 100);
        }
        facture.setTotalCss(totalCss);

        // Calculer total_a_payer
        facture.setTotalAPayer(totalIpres + totalCss);
        return facture;
    }
}
