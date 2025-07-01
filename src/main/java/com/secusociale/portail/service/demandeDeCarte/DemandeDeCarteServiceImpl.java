package com.secusociale.portail.service.demandeDeCarte;

import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.demandeDeCarte.DemandeDeCarteEntity;
import com.secusociale.portail.domain.enumeration.StatutDemandeDeCarte;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.DemandeDeCarteRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.dto.extraction.DemandeCarteCollectiveRequest;
import com.secusociale.portail.service.dto.extraction.DemandeCarteCollectiveSalarieDTO;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteCollectiveResponse;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteDTO;
import com.secusociale.portail.service.mapper.DemandeDeCarteMapper;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DemandeDeCarteServiceImpl implements DemandeDeCarteService {

    private final DemandeDeCarteRepository demandeDeCarteRepository;
    private final DemandeDeCarteMapper demandeDeCarteMapper;
    private final SalarieRepository salarieRepository;
    private final UserRepository userRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public DemandeDeCarteDTO create(DemandeDeCarteDTO demandeDeCarteDTO,Boolean isCollective) {
        Long salarieId = demandeDeCarteDTO.getSalarieId();
        String nin = demandeDeCarteDTO.getNin();
        demandeDeCarteDTO.setDateDemande(LocalDate.now());
        if(isCollective){
//            Optional<Salarie> optionalSalarie = Optional.ofNullable(salarieRepository.findByNumeroCni(nin)
//                .orElseThrow(() -> new BadRequestAlertException("Salarié non trouvé avec le NIN : " +nin, "Salarie", "400")));
//            optionalSalarie.ifPresent(salarie ->{
//                demandeDeCarteDTO.setIdAgent(salarie.getAgentId());
//                demandeDeCarteDTO.setNin(salarie.getNumeroCni());
//                demandeDeCarteDTO.setSalarieId(salarie.getUserId());
//            });
        }
        else {
            Optional<Salarie> optionalSalarie = Optional.ofNullable(salarieRepository.findByUserId(salarieId)
                .orElseThrow(() -> new BadRequestAlertException("Salarié non trouvé avec l'userId : " +salarieId, "Salarie", "400")));
            optionalSalarie.ifPresent(salarie ->{
                demandeDeCarteDTO.setIdAgent(salarie.getAgentId());
                demandeDeCarteDTO.setNin(salarie.getNumeroCni());
            });
        }
       Optional<Agence> agence = Optional.ofNullable(agenceRepository.findById(demandeDeCarteDTO.getAgence()).orElseThrow(
            () -> new BadRequestAlertException("Agence non trouvée pour l'ID : " + demandeDeCarteDTO.getAgence(), "Agence", "400")
        ));

        DemandeDeCarteEntity demande = demandeDeCarteMapper.toEntity(demandeDeCarteDTO);
        demande.setNumeroDemande(generateNumber(LocalDate.now()));
        demande.setStatut(StatutDemandeDeCarte.EN_ATTENTE.toString());
        demande.setPhoto(demandeDeCarteDTO.getPhoto());
        demandeDeCarteRepository.save(demande);
        return demandeDeCarteMapper.toDto(demande);
    }

    @Override
    public DemandeDeCarteDTO readById(Long id) {
        return null;
    }

    @Override
    public List<DemandeDeCarteDTO> readAll(int page, int size) {
        //return List.of();
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());
        Page<DemandeDeCarteEntity> demandes = demandeDeCarteRepository.findAll(pageable);

        return demandes.stream().
            map(
                it ->{
                    DemandeDeCarteDTO d = demandeDeCarteMapper.toDto(it);

//                    Salarie salarie = salarieRepository.findByUserId(d.getSalarieId())
//                        .orElseThrow(() -> new RuntimeException("Salarié non trouvé avec l'userId : " + d.getSalarieId()));
//                    d.setSalarie(salarie);
//
//                    Agence agency = agenceRepository.findById(d.getAgence())
//                        .orElseThrow(() -> new RuntimeException("Agence non trouvée pour l'ID : " + d.getAgence()));
//                    d.setAgency(agency);
                    return d;
                }
            )
            .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDeCarteDTO> readDemandeForAgent(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());
        Page<DemandeDeCarteEntity> demandes = demandeDeCarteRepository.findByIdAgent(id,pageable);
        return getDemandeDeCarteDTOS(demandes);
    }

    @Override
    public List<DemandeDeCarteDTO> readDemandeForSalarie(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());
        Page<DemandeDeCarteEntity> demandes = demandeDeCarteRepository.findBySalarieId(id,pageable);
        return getDemandeDeCarteDTOS(demandes);
    }

    private List<DemandeDeCarteDTO> getDemandeDeCarteDTOS(Page<DemandeDeCarteEntity> demandes) {
        return demandes.stream().
            map(
                it ->{
                    DemandeDeCarteDTO d = demandeDeCarteMapper.toDto(it);

//                    if(d.getSalarieId() != null){
//                        Salarie salarie = salarieRepository.findByUserId(d.getSalarieId())
//                            .orElseThrow(() -> new RuntimeException("Salarié non trouvé avec l'userId : " + d.getSalarieId()));
//                        d.setSalarie(salarie);
//                    }
//
                    Agence agency = agenceRepository.findById(d.getAgence())
                        .orElseThrow(() -> new RuntimeException("Agence non trouvée pour l'ID : " + d.getAgence()));
                    d.setAgency(agency);
                    return d;
                }
            )
            .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDeCarteDTO> demandeByAgent() {
       //return List.of();
        return null;
    }

    public static String generateNumber(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyMMdd");
        String datePart = date.format(dateFormat);

        Random random = new Random();
        int randomNumber = random.nextInt(100);

        String randomPart = String.format("%02d", randomNumber);

        return datePart + randomPart;
    }

    public DemandeDeCarteCollectiveResponse createCollective(DemandeCarteCollectiveRequest request) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); // Format : année, mois, jour, heure, minute, seconde
        String dateTimeString = now.format(formatter);
        String lot = "LOT"+dateTimeString;
        validateRequest(request);

        List<Object> resultats = new ArrayList<>();

        for (DemandeCarteCollectiveSalarieDTO salarieDto : request.getSalaries()) {
            try {

                verifierDemandeExistante(salarieDto.getNin());

                DemandeDeCarteDTO demandeCree = creerDemandeCarte(request.getAgence(), salarieDto,lot);
                resultats.add(demandeCree);
            } catch (RuntimeException e) {

                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", e.getMessage());
                resultats.add(errorMap);
            }
        }

        return new DemandeDeCarteCollectiveResponse(resultats);
    }

    private void verifierDemandeExistante(String nin) {
        Optional<DemandeDeCarteEntity> demandeExistante =
            demandeDeCarteRepository.findFirstByNinAndStatut(nin, "EN_ATTENTE");

        if (demandeExistante.isPresent()) {
            throw new IllegalStateException("Salarié avec le NIN : " + nin + " a déjà une demande en attente");
        }
    }

    private void validateRequest(DemandeCarteCollectiveRequest request) {
        if (request.getAgence() == null ||
            request.getSalaries() == null ||
            request.getSalaries().isEmpty()) {
            throw new IllegalArgumentException("Paramètres invalides");
        }
    }

    private DemandeDeCarteDTO creerDemandeCarte(Long agenceId, DemandeCarteCollectiveSalarieDTO salarieDto,String lot) {
        DemandeDeCarteDTO dtoToCreate = new DemandeDeCarteDTO();
        dtoToCreate.setNin(salarieDto.getNin());
        dtoToCreate.setAgence(agenceId);
        dtoToCreate.setPhoto(salarieDto.getPhoto());
        dtoToCreate.setLot(lot);

        return create(dtoToCreate,true);
    }

    public List<Map<String, Object>> getDemandesGroupedByLot(int page, int size, String searchTerm) {
        Page<String> lotsPage = demandeDeCarteRepository.findDistinctLots(PageRequest.of(page, size));
        List<String> lots = lotsPage.getContent();
        List<Map<String, Object>> result = new ArrayList<>();

        for (String lot : lots) {
            Map<String, Object> groupedDemande = new HashMap<>();

            // Ajouter la logique de filtrage avec le searchTerm ici
            List<DemandeDeCarteEntity> demandes;
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                demandes = demandeDeCarteRepository.findByLotAndSearchTerm(lot, "%" + searchTerm.toLowerCase() + "%");
            } else {
                demandes = demandeDeCarteRepository.findByLot(lot);
            }

            if (!demandes.isEmpty()) {
                groupedDemande.put("lot", lot);
                groupedDemande.put("agency", agenceRepository.findById(demandes.get(0).getAgence()));
                groupedDemande.put("dateDemande", demandes.get(0).getDateDemande());

                // Liste pour stocker les demandes enrichies avec les informations du salarié
                List<Map<String, Object>> enrichedDemandes = new ArrayList<>();

                // Ajouter les informations du salarié pour chaque demande
                for (DemandeDeCarteEntity demande : demandes) {
                    Map<String, Object> demandeInfo = new HashMap<>();
                    demandeInfo.put("numeroDemande", demande.getNumeroDemande());
                    demandeInfo.put("statut", demande.getStatut());
                    demandeInfo.put("dateDemande", demande.getDateDemande());
                    demandeInfo.put("idAgent", demande.getIdAgent());
                    demandeInfo.put("salarieId", demande.getSalarieId());
                    demandeInfo.put("dateDeTraitement", demande.getDateDeTraitement());
                    demandeInfo.put("agence", demande.getAgence());
                    demandeInfo.put("photo", demande.getPhoto());
                    demandeInfo.put("nin", demande.getNin());
                    demandeInfo.put("lot", demande.getLot());

                    // Ajouter les informations du salarié
                    Salarie salarie = salarieRepository.findById(demande.getSalarieId()).orElse(null);
                    if (salarie != null) {
                        Map<String, Object> salarieInfo = new HashMap<>();
                        salarieInfo.put("nom", salarie.getNom());
                        salarieInfo.put("prenom", salarie.getPrenom());
                        salarieInfo.put("telephone", salarie.getTelephone());
                        salarieInfo.put("numeroCni", salarie.getNumeroCni());
                        salarieInfo.put("dateNais", salarie.getDateNais());
                        // Ajoutez d'autres informations du salarié si nécessaire
                        demandeInfo.put("salarie", salarieInfo);
                    }

                    // Ajouter la demande enrichie à la liste
                    enrichedDemandes.add(demandeInfo);
                }

                // Ajouter les demandes enrichies avec les informations du salarié à "demandes"
                groupedDemande.put("demandes", enrichedDemandes);

                result.add(groupedDemande);
            }
        }

        return result;
    }
}
