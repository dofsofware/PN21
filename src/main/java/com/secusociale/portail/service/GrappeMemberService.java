package com.secusociale.portail.service;

import com.secusociale.portail.domain.GrappeMember;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.repository.GrappeMemberRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.service.dto.GrappeMemberDTO;
import com.secusociale.portail.service.mapper.GrappeMemberMapper;
import com.secusociale.portail.service.mapper.SalarieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GrappeMember}.
 */
@Service
@Transactional
public class GrappeMemberService {

    private final Logger log = LoggerFactory.getLogger(GrappeMemberService.class);

    private final GrappeMemberRepository grappeMemberRepository;
    private final GrappeMemberMapper grappeMemberMapper;
    private final SalarieRepository salarieRepository;
    private final SalarieMapper salarieMapper;

    public GrappeMemberService(GrappeMemberRepository grappeMemberRepository, SalarieRepository salarieRepository, GrappeMemberMapper grappeMemberMapper, SalarieMapper salarieMapper) {
        this.grappeMemberRepository = grappeMemberRepository;
        this.salarieRepository = salarieRepository;
        this.grappeMemberMapper = grappeMemberMapper;
        this.salarieMapper = salarieMapper;
    }

    /**
     * Save a grappeMember.
     *
     * @param grappeMemberDTO the entity to save.
     * @return the persisted entity.
     */
    public GrappeMemberDTO save(GrappeMemberDTO grappeMemberDTO) {
        log.debug("Request to save GrappeMember : {}", grappeMemberDTO);
        GrappeMember grappeMember = grappeMemberMapper.toEntity(grappeMemberDTO);
        grappeMember = grappeMemberRepository.save(grappeMember);
        GrappeMemberDTO toReturn = grappeMemberMapper.toDto(grappeMember);
        Salarie salarie = salarieRepository.getOne(toReturn.getSalarieId());
        toReturn.setSalarieObj(salarieMapper.toDto(salarie));
        return toReturn;
    }

    /**
     * Get all the grappeMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GrappeMemberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrappeMembers");
        return grappeMemberRepository.findAll(pageable)
            .map(grappeMemberMapper::toDto).map(grappeMemberDTO -> {
                Salarie salarie = salarieRepository.getOne(grappeMemberDTO.getSalarieId());
                grappeMemberDTO.setSalarieObj(salarieMapper.toDto(salarie));
                return grappeMemberDTO;
            });
    }

    /**
     * Get one grappeMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GrappeMemberDTO> findOne(Long id) {
        log.debug("Request to get GrappeMember : {}", id);
        return grappeMemberRepository.findById(id)
            .map(grappeMemberMapper::toDto).map(grappeMemberDTO -> {
                Salarie salarie = salarieRepository.getOne(grappeMemberDTO.getSalarieId());
                grappeMemberDTO.setSalarieObj(salarieMapper.toDto(salarie));
                return grappeMemberDTO;
            });
    }

    /**
     * Delete the grappeMember by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GrappeMember : {}", id);
        grappeMemberRepository.deleteById(id);
    }

    /**
     * CountGrappeByType
     *
     * @param type,salarieId the type of the grappeMember
     */
    public Long countByType(String type, Long salarieId) {
        Salarie salarie = salarieRepository.getOne(salarieId);
        List<GrappeMember> list = grappeMemberRepository.findAllByTypeAndSalarie(type, salarie);
        int nb = list == null ? 0 : list.size();
        return Long.valueOf("" + nb);
    }

    public void modifyStatus(StatutGrappeMembre statutGrappeMembre, Long grappeFamillialeId) {
        grappeMemberRepository.modifyStatus(statutGrappeMembre, grappeFamillialeId);
    }

    @Transactional
    public void modifyStatusBySalarieId(StatutGrappeMembre statutGrappeMembre, Long salarieId) {
        grappeMemberRepository.modifyStatusBySalarieId(statutGrappeMembre, salarieId);
    }

    public List<GrappeMember> getGrappeMembersByStatus(String statutStr) {
        try {
            StatutGrappeMembre statut = StatutGrappeMembre.valueOf(statutStr.toUpperCase());
            return grappeMemberRepository.getGrappeMembersByStatus(statut);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Statut invalide. Les valeurs possibles sont: " + Arrays.toString(StatutGrappeMembre.values())
            );
        }
    }

    @Transactional
    public void updateStatusBySalarieId(String statutGrappeStr, Long salarieId) {
        try {
            if (!grappeMemberRepository.existsBySalarieId(salarieId)) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    ("Aucun membre de grappe trouvé pour le salarié avec l'ID: " + salarieId)
                );
            }
            StatutGrappeMembre newStatus = StatutGrappeMembre.valueOf(statutGrappeStr.toUpperCase());
            if (newStatus == StatutGrappeMembre.SAISIE || newStatus == StatutGrappeMembre.DIVORCE || newStatus == StatutGrappeMembre.DECES) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Statut "+newStatus+" n'est pas autorisé pour la mise à jour");
            }
            if(newStatus == StatutGrappeMembre.RETOURNE){
                newStatus = StatutGrappeMembre.SOUMIS;
            }
            StatutGrappeMembre[] validStatuses = {StatutGrappeMembre.SOUMIS, StatutGrappeMembre.REJETE, StatutGrappeMembre.VALIDE};
            grappeMemberRepository.updateStatusBySalarieId(newStatus, salarieId, validStatuses);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Statut invalide. Les valeurs possibles sont: " + Arrays.toString(StatutGrappeMembre.values())
            );
        }
    }

    @Transactional
    public void updateStatusById(Long grappeMemberId, StatutGrappeMembre newStatus) {
        try {
            int updatedRows = grappeMemberRepository.updateStatusById(newStatus, grappeMemberId);
            if (updatedRows == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le GrappeMember avec l'ID " + grappeMemberId + " n'existe pas.");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Statut invalide. Les valeurs possibles sont: " + Arrays.toString(StatutGrappeMembre.values())
            );
        }
    }

    @Transactional
    public void updateStatusAndMotifBySalarieId(StatutGrappeMembre statutGrappe, Long salarieId, String motif) {
        if (!grappeMemberRepository.existsBySalarieId(salarieId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                ("Aucun membre de grappe trouvé pour le salarié avec l'ID: " + salarieId)
            );
        }
        salarieRepository.updateStatusGrappeMembreAndMotifRejetRetourneGrappeById(statutGrappe.toString(),motif,salarieId);
        StatutGrappeMembre newStatus = StatutGrappeMembre.valueOf(statutGrappe.toString().toUpperCase());
        if(newStatus == StatutGrappeMembre.RETOURNE){
            newStatus = StatutGrappeMembre.SOUMIS;
        }
        grappeMemberRepository.updateStatusAndMotifBySalarieId(newStatus,motif,salarieId);
    }

    @Transactional
    public void updateStatusAndMotifBySId(StatutGrappeMembre statutGrappe, Long grappeId, String motif) {
        if (!grappeMemberRepository.existsById(grappeId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                ("Aucun membre de grappe trouvé avec l'ID: " + grappeId)
            );
        }
        StatutGrappeMembre newStatus = StatutGrappeMembre.valueOf(statutGrappe.toString().toUpperCase());
        grappeMemberRepository.updateStatusAndMotifBySId(newStatus,motif,grappeId);
    }

    public Page<GrappeMember> searchGrappeMembersBySalarieId(Long salarieId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return grappeMemberRepository.searchByStatusAndTerm(salarieId, pageable);
    }

}
