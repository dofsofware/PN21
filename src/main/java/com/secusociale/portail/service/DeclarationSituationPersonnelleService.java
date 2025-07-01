package com.secusociale.portail.service;

import com.secusociale.portail.domain.DeclarationSituationPersonnelle;
import com.secusociale.portail.domain.enumeration.StatutDeclarationSituationPersonnelle;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.domain.enumeration.TypeDeclarationSituationPersonnelle;
import com.secusociale.portail.repository.DeclarationSituationPersonnelleRepository;
import com.secusociale.portail.service.dto.DeclarationSituationPersonnelleDTO;
import com.secusociale.portail.service.mapper.DeclarationSituationPersonnelleMapper;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
public class DeclarationSituationPersonnelleService {

    private static final Logger LOG = LoggerFactory.getLogger(DeclarationSituationPersonnelleService.class);

    private final DeclarationSituationPersonnelleRepository declarationSituationPersonnelleRepository;

    private final DeclarationSituationPersonnelleMapper declarationSituationPersonnelleMapper;

    private final GrappeMemberService grappeMemberService;

    public DeclarationSituationPersonnelleService(GrappeMemberService grappeMemberService , DeclarationSituationPersonnelleRepository declarationSituationPersonnelleRepository, DeclarationSituationPersonnelleMapper declarationSituationPersonnelleMapper) {
        this.declarationSituationPersonnelleRepository = declarationSituationPersonnelleRepository;
        this.declarationSituationPersonnelleMapper = declarationSituationPersonnelleMapper;
        this.grappeMemberService = grappeMemberService ;
    }

    /**
     * Save a declaration.
     *
     * @param declarationSituationPersonnelleDTO the entity to save.
     * @return the persisted entity.
     */
    public DeclarationSituationPersonnelleDTO save(DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO) {
        LOG.debug("Request to save Declaration : {}", declarationSituationPersonnelleDTO);
        DeclarationSituationPersonnelle declarationSituationPersonnelle = declarationSituationPersonnelleMapper.toEntity(declarationSituationPersonnelleDTO);
        declarationSituationPersonnelle = declarationSituationPersonnelleRepository.save(declarationSituationPersonnelle);
        return declarationSituationPersonnelleMapper.toDto(declarationSituationPersonnelle);
    }

    /**
     * Update a declaration.
     *
     * @param declarationSituationPersonnelleDTO the entity to save.
     * @return the persisted entity.
     */
    public DeclarationSituationPersonnelleDTO update(DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO) {
        LOG.debug("Request to update Declaration : {}", declarationSituationPersonnelleDTO);
        DeclarationSituationPersonnelle declarationSituationPersonnelle = declarationSituationPersonnelleMapper.toEntity(declarationSituationPersonnelleDTO);
        declarationSituationPersonnelle = declarationSituationPersonnelleRepository.save(declarationSituationPersonnelle);
        return declarationSituationPersonnelleMapper.toDto(declarationSituationPersonnelle);
    }

    /**
     * Partially update a declaration.
     *
     * @param declarationSituationPersonnelleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DeclarationSituationPersonnelleDTO> partialUpdate(DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO) {
        LOG.debug("Request to partially update Declaration : {}", declarationSituationPersonnelleDTO);

        return declarationSituationPersonnelleRepository
            .findById(declarationSituationPersonnelleDTO.getId())
            .map(existingDeclarationSituationPersonnelle -> {
                declarationSituationPersonnelleMapper.partialUpdate(existingDeclarationSituationPersonnelle, declarationSituationPersonnelleDTO);
                return existingDeclarationSituationPersonnelle;
            })
            .map(declarationSituationPersonnelleRepository::save)
            .map(declarationSituationPersonnelleMapper::toDto);
    }

    /**
     * Get all the declarations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DeclarationSituationPersonnelleDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Declarations with pagination: {}", pageable);

        Page<DeclarationSituationPersonnelle> declarationsPage = declarationSituationPersonnelleRepository.findAll(pageable);

        return declarationsPage.map(declarationSituationPersonnelleMapper::toDto);
    }

    /**
     * Get one declaration by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeclarationSituationPersonnelleDTO> findOne(Long id) {
        LOG.debug("Request to get Declaration : {}", id);
        return declarationSituationPersonnelleRepository.findById(id).map(declarationSituationPersonnelleMapper::toDto);
    }

    /**
     * Delete the declaration by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Declaration : {}", id);
        declarationSituationPersonnelleRepository.deleteById(id);
    }

    public List<DeclarationSituationPersonnelleDTO> findByTypeDeclarationSituationPersonnelleAndUserId(TypeDeclarationSituationPersonnelle typeDeclaration, Long userId) {
        return declarationSituationPersonnelleRepository.findByTypeDeclarationSituationPersonnelleAndUserId(typeDeclaration, userId)
            .stream()
            .map(declarationSituationPersonnelleMapper::toDto)
            .collect(Collectors.toList());
    }

    public void updateStatusGrappeAndCheckMotif(DeclarationSituationPersonnelleDTO declarationSituationPersonnelleDTO) {
        Long grappeFamillialeId = declarationSituationPersonnelleDTO.getGrappeFamillialeId();
        StatutDeclarationSituationPersonnelle statut = declarationSituationPersonnelleDTO.getStatut();
        String motif = declarationSituationPersonnelleDTO.getMotif();
        if(grappeFamillialeId != null){
            if(statut == StatutDeclarationSituationPersonnelle.VALIDE &&
                    declarationSituationPersonnelleDTO.getTypeDeclaration() == TypeDeclarationSituationPersonnelle.DIVORCE){
                grappeMemberService.modifyStatus(StatutGrappeMembre.DIVORCE,grappeFamillialeId);
            }
            else if(statut == StatutDeclarationSituationPersonnelle.VALIDE &&
                    declarationSituationPersonnelleDTO.getTypeDeclaration() == TypeDeclarationSituationPersonnelle.DECES){
                grappeMemberService.modifyStatus(StatutGrappeMembre.DECES,grappeFamillialeId);
            }

            } else {
            throw new BadRequestAlertException("grappeFamillialeId n'est pas indiqué", ENTITY_NAME, "grappeFamillialeId idexists");
        }
        if ((statut == StatutDeclarationSituationPersonnelle.REJETE ||
                statut == StatutDeclarationSituationPersonnelle.RETOURNE)
                        && (motif == null || motif.isEmpty())) {
            throw new BadRequestAlertException("Le motif n'est pas indiqué", ENTITY_NAME, "Motif manquant");
        }
    }

}

