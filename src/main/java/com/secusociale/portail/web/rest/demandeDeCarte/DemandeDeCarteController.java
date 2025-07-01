package com.secusociale.portail.web.rest.demandeDeCarte;

import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.domain.demandeDeCarte.DemandeDeCarteEntity;
import com.secusociale.portail.repository.DemandeDeCarteRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.demandeDeCarte.DemandeDeCarteService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.extraction.DemandeCarteCollectiveRequest;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteCollectiveResponse;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteDTO;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteRequest;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.config.JHipsterDefaults;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.*;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

@AllArgsConstructor
@RestController
@RequestMapping("/api/demande-cartes")
public class DemandeDeCarteController {

    private  final DemandeDeCarteService demandeDeCarteService;
    private  final DemandeDeCarteRepository demandeDeCarteRepository;
    private  final UserRepository userRepository;
    private final SalarieRepository salarieRepository;

    @PostMapping
    ResponseEntity<?> create(@RequestBody DemandeDeCarteDTO dto){
        Optional<DemandeDeCarteEntity> d = demandeDeCarteRepository.findFirstBySalarieIdAndStatut(dto.getSalarieId(),"EN_ATTENTE");
        if (d.isPresent()){
            throw new BadRequestAlertException("le salarié a déja une demande", "Salarie", "400");
        }

        DemandeDeCarteDTO demande = demandeDeCarteService.create(dto,false);
        Map<String,Object> reponse = new HashMap<>();
        reponse.put("code", HttpStatus.OK.value());
        reponse.put("data", demande);
        return ResponseEntity.ok().body(reponse);
    }

    @PostMapping("/collective")
    public ApiResponse<Object> createCollective(@RequestBody DemandeCarteCollectiveRequest request) {
        try {
            DemandeDeCarteCollectiveResponse response =
                demandeDeCarteService.createCollective(request);

            Map<String, Object> result = new HashMap<>();
            result.put("data", response.getResultats());

            return ApiResponse.success(result);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error400(e.getMessage());
        }
    }

    @GetMapping("/check")
    ResponseEntity<?> checkCarte(@RequestParam Long id,@RequestParam(required = false) String nin){
        Optional<DemandeDeCarteEntity> d = nin == null ? demandeDeCarteRepository.findFirstBySalarieIdAndStatut(id,"EN_ATTENTE") : demandeDeCarteRepository.findFirstByNinAndStatut(nin,"EN_ATTENTE");
        boolean hasCarte = d.isPresent();
        Map<String,Object> reponse = new HashMap<>();
        reponse.put("code", HttpStatus.OK.value());
        reponse.put("data", hasCarte);
        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping
    ResponseEntity<?> readAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String searchTerm) {
        Map<String, Object> reponse = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("size", size);
        pagination.put("page", page);

        // Comptez les éléments en fonction du searchTerm
        long totalElements = demandeDeCarteRepository.countByIdAgentAndSearchTerm(null, searchTerm);
        pagination.put("totalElements", totalElements);

        // Récupérez les éléments en fonction du searchTerm
        List<DemandeDeCarteEntity> demandes = demandeDeCarteRepository
            .findByIdAgentAndSearchTerm(null, searchTerm, PageRequest.of(page, size)).getContent();
        reponse.put("code", 200);
        reponse.put("list", demandes);
        reponse.put("pagination", pagination);
        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping("/agent")
    ResponseEntity<?> readDemandeForAgent(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String searchTerm) {
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findByLogin(username);
        Map<String, Object> reponse = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();
        long totalElements = demandeDeCarteRepository.countByIdAgentAndSearchTerm(user.getId(), searchTerm);
        pagination.put("size", size);
        pagination.put("page", page);
        pagination.put("totalElements", totalElements);
        List<DemandeDeCarteEntity> demandes = demandeDeCarteRepository
            .findByIdAgentAndSearchTerm(user.getId(), searchTerm, PageRequest.of(page, size)).getContent();
        reponse.put("code", 200);
        reponse.put("list", demandes);
        reponse.put("pagination", pagination);
        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping("/salaries")
    ResponseEntity<?> readDemandeForSalarie(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Map<String,Object> reponse = new HashMap<>();
        Map<String, Object>  pagination = new HashMap<>();
        pagination.put("size", size);
        pagination.put("page", page);
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findByLogin(username);
        salarieRepository.findByUserId(user.getId()).orElseThrow(() -> new BadRequestAlertException("l\'utilisateur n\'est pas un salarié", "Salarie", "idexists"));
        pagination.put("totalElements", demandeDeCarteRepository.countBySalarieId(user.getId()));
        reponse.put("code", 200);
        reponse.put("list",demandeDeCarteService.readDemandeForSalarie(user.getId(), page, size));
        reponse.put("pagination", pagination);
        return ResponseEntity.ok().body(reponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> traiterDemandeDeCarte(@RequestParam String statut,@PathVariable Long id){
        Optional<DemandeDeCarteEntity> demandeDeCarte = Optional.ofNullable(demandeDeCarteRepository.findById(id).orElseThrow(() -> new BadRequestAlertException("Aucune demande trouvée", "demande", "400")));
        if (!demandeDeCarte.get().getStatut().equals("EN_ATTENTE")){
            throw new BadRequestAlertException("Cette demnade est déja traitée", "demande", "400");
        }
        demandeDeCarte.get().setStatut(statut);
        demandeDeCarte.get().setDateDeTraitement(LocalDate.now());
        demandeDeCarteRepository.save(demandeDeCarte.get());
        Map<String,Object> reponse = new HashMap<>();
        reponse.put("code", HttpStatus.OK.value());
        reponse.put("message", "la demande numero "+demandeDeCarte.get().getNumeroDemande()+" change de status");
        return ResponseEntity.ok().body(reponse);
    }

    @PutMapping("/traitement-lot")
    public ApiResponse<Object> traiterParLotDemandeDeCarte(@RequestBody DemandeDeCarteRequest demandeDeCarteRequest) {
        Map<Long, String> traitesAvecSucces =  new HashMap<>();
        Map<Long, String> erreurs = new HashMap<>();
        List<DemandeDeCarteEntity> demandesTraitees = new ArrayList<>();

        if(demandeDeCarteRequest.getStatut()==null){
            return ApiResponse.error400("Statut non spécifié !");
        }
        for (Long demandeId : demandeDeCarteRequest.getDemandeIdList()) {
            Optional<DemandeDeCarteEntity> demandeOpt = demandeDeCarteRepository.findById(demandeId);
            if (!demandeOpt.isPresent()) {
                erreurs.put(demandeId, "Aucune demande trouvée avec l'id : " + demandeId);
                continue;
            }

            DemandeDeCarteEntity demande = demandeOpt.get();
            demande.setStatut(demandeDeCarteRequest.getStatut().toString());
            demandesTraitees.add(demande);
            traitesAvecSucces.put(demandeId,"Demande avec l'id : "+demandeId+" traitée avec succès");
        }

        if (!demandesTraitees.isEmpty()) {
            demandeDeCarteRepository.saveAll(demandesTraitees);
        }

        Map<String, Object> resultats = new HashMap<>();
        if(!traitesAvecSucces.isEmpty()){
            resultats.put("traitesAvecSucces", traitesAvecSucces);
        }
        if(!erreurs.isEmpty()){
            resultats.put("nonTraites", erreurs);
        }


        return ApiResponse.success(resultats);
    }

    @GetMapping("/grouped-by-lot")
    ResponseEntity<?> getDemandesGroupedByLot(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String searchTerm // Paramètre de recherche
    ) {
        // Passer le paramètre searchTerm au service
        List<Map<String, Object>> groupedDemandes = demandeDeCarteService.getDemandesGroupedByLot(page, size, searchTerm);

        // Préparer la réponse avec la pagination et les données
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("size", size);
        pagination.put("page", page);
        pagination.put("totalElements", groupedDemandes.size());

        response.put("code", 200);
        response.put("list", groupedDemandes);
        response.put("pagination", pagination);

        return ResponseEntity.ok().body(response);
    }



}
