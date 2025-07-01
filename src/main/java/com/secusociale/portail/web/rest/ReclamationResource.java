package com.secusociale.portail.web.rest;


import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.secusociale.portail.domain.CarriereManquantes;
import com.secusociale.portail.domain.Reclamation;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import com.secusociale.portail.domain.enumeration.StatutReclamation;
import com.secusociale.portail.domain.enumeration.TypeOperation;
import com.secusociale.portail.domain.enumeration.TypeReclamation;
import com.secusociale.portail.repository.CarriereManquantesRepository;
import com.secusociale.portail.repository.ReclamationRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.ReclamationService;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.CarriereManquanteDTO;
import com.secusociale.portail.service.dto.ValidateRequest;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;


@RestController
@RequestMapping("/api/reclamations")
@Transactional
public class ReclamationResource {

    private static final Logger LOG = LoggerFactory.getLogger(ReclamationResource.class);

    private static final String ENTITY_NAME = "jhipsterReclamation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private final ReclamationRepository reclamationRepository;
    private final SalarieRepository salarieRepository;
    private final CarriereManquantesRepository carriereManquantesRepository;
    private final UserRepository userRepository;
    private final ReclamationService reclamationService;

    public ReclamationResource(UserRepository userRepository,ReclamationService reclamationService,CarriereManquantesRepository carriereManquantesRepository, ReclamationRepository reclamationRepository,SalarieRepository salarieRepository) {
        this.reclamationRepository = reclamationRepository;
        this.salarieRepository = salarieRepository;
        this.carriereManquantesRepository = carriereManquantesRepository;
        this.userRepository = userRepository;
        this.reclamationService = reclamationService;
    }

    @PostMapping("")
    public ApiResponse<Reclamation> createReclamation(@Valid @RequestBody Reclamation reclamation) throws URISyntaxException {
        LOG.debug("REST request to save Reclamation : {}", reclamation);
        if (reclamation.getId() != null) {
            throw new BadRequestAlertException("A new reclamation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        for(CarriereManquantes c : reclamation.getCarriereManquantes()){
            if( c != null){
                reclamation.addCarriereManquante(c);
            }
        }
        reclamation = reclamationRepository.save(reclamation);

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK,reclamation);
    }

    @PutMapping("/update")
    public ApiResponse<Reclamation> updateReclamation(
        @Valid @RequestBody Reclamation reclamation
    ) throws URISyntaxException {
        Long id = reclamation.getId();
        if (reclamation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LOG.debug("REST request to update Reclamation : {}, {}", id, reclamation);
        if (!Objects.equals(id, reclamation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reclamationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        reclamation = reclamationRepository.save(reclamation);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, reclamation);
    }


    @PatchMapping(value = "/partial-update", consumes = { "application/json", "application/merge-patch+json" })
    public ApiResponse<Reclamation> partialUpdateReclamation(
        @NotNull @RequestBody Reclamation reclamation
    ) throws URISyntaxException {
        Long id = reclamation.getId();
        if (reclamation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LOG.debug("REST request to partial update Reclamation partially : {}, {}", id, reclamation);
        if (!Objects.equals(id, reclamation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reclamationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        reclamationService.partialSave(reclamationRepository,reclamation);

        List<CarriereManquantes> carriereManquantes = carriereManquantesRepository.findByReclamationId(id);
        for(CarriereManquantes c : carriereManquantes){
            if( c != null){
                reclamation.addCarriereManquante(c);
            }
        }
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, reclamation);
    }


    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllReclamations(
        Pageable pageable,
        @RequestParam(required = false) String globalSearch,
        @RequestParam(required = false) StatutReclamation statut,  // Changer String en StatutReclamation
        @RequestParam(required = false) String typeDemande,
        @RequestParam(required = false) Long salarieId) {

        LOG.debug("REST request to get all Reclamations with pagination: page={}, size={}, globalSearch={}, statut={}, typeDemande={}",
            pageable.getPageNumber(), pageable.getPageSize(), globalSearch, statut, typeDemande);

        // Ajouter un tri par date de soumission (du plus récent au plus ancien)
        Pageable sortedPageable = pageable.getSort().isUnsorted() ?
            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("dateSoumission"))) :
            pageable;

        // Récupérer une page de réclamations triées par dateSoumission
        Page<Reclamation> pageReclamation;
        if (globalSearch != null && !globalSearch.isEmpty() || salarieId != null) {
            pageReclamation = reclamationRepository.findAllByFilters(globalSearch, statut, typeDemande,salarieId, sortedPageable);
        } else {
            pageReclamation = reclamationRepository.findAll(sortedPageable);
        }
        // Liste qui contiendra les résultats transformés
        List<HashMap<String, Object>> resultList = new ArrayList<>();

        // Parcourir les réclamations de la page
        for (Reclamation rec : pageReclamation.getContent()) {
            HashMap<String, Object> result = new HashMap<>();

            // Ajouter les carrières manquantes si elles existent
            List<HashMap<String, Object>> carriereManquantesList = new ArrayList<>();
            rec.getCarriereManquantes().forEach(c -> {
                if (c != null) {
                    HashMap<String, Object> carriereManquanteDTO = new HashMap<>();
                    carriereManquanteDTO.put("id", c.getId());
                    carriereManquanteDTO.put("numeroUniqueEmployeur", c.getNumeroUniqueEmployeur());
                    carriereManquanteDTO.put("dateDebut", c.getDateDebut());
                    carriereManquanteDTO.put("dateFin", c.getDateFin());
                    carriereManquanteDTO.put("justificatifsCarriereManquantes", c.getJustificatifsCarriereManquantes());
                    carriereManquantesList.add(carriereManquanteDTO);
                }
            });

            // Transformation de la réclamation en DTO
            HashMap<String, Object> reclamationDTO = new HashMap<>();
            reclamationDTO.put("id", rec.getId());
            reclamationDTO.put("typeReclamation", rec.getTypeReclamation());
            reclamationDTO.put("userId", rec.getUserId());
            reclamationDTO.put("agenceId", rec.getAgenceId());
            reclamationDTO.put("numSalarie", rec.getNumSalarie());
            reclamationDTO.put("secondNumSalarie", rec.getSecondNumSalarie());
            reclamationDTO.put("libAutresRec", rec.getLibAutresRec());
            reclamationDTO.put("descriptionAutres", rec.getDescriptionAutres());
            reclamationDTO.put("statut", rec.getStatut());
            reclamationDTO.put("dateSoumission", rec.getDateSoumission() != null ? rec.getDateSoumission().toString() : null);
            reclamationDTO.put("dateValidateGC", rec.getDateValidateGC() != null ? rec.getDateValidateGC().toString() : null);
            reclamationDTO.put("userIdValidateGC", rec.getUserIdValidateGC());
            reclamationDTO.put("dateValidateCFCSS", rec.getDateValidateCFCSS() != null ? rec.getDateValidateCFCSS().toString() : null);
            reclamationDTO.put("userIdValidateCFCSS", rec.getUserIdValidateCFCSS());
            reclamationDTO.put("dateValidateCFIPRES", rec.getDateValidateCFIPRES() != null ? rec.getDateValidateCFIPRES().toString() : null);
            reclamationDTO.put("userIdValidateCFIPRES", rec.getUserIdValidateCFIPRES());
            reclamationDTO.put("motif", rec.getMotif());
            reclamationDTO.put("agenceIpresID", rec.getAgenceIpresID());
            reclamationDTO.put("agenceCssID", rec.getAgenceCssID());

            // Ajouter les carrières manquantes dans le DTO de la réclamation
            reclamationDTO.put("carriereManquantes", carriereManquantesList);

            // Ajouter les réclamations à la liste
            result.put("reclamation", reclamationDTO);

            // Ajouter le salarié si présent
            Optional<Salarie> salarie = salarieRepository.findByUserId(rec.getUserId());
            if (salarie.isPresent()) {
                result.put("salarieDTO", salarie.get());
            } else {
                result.put("salarieDTO", null);
            }

            result.put("gestionnaireDTO", null); // Ajoutez ici si vous avez des informations sur le gestionnaire
            resultList.add(result);
        }

        // Construire la pagination
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("size", pageReclamation.getSize());
        pagination.put("page", pageReclamation.getNumber());
        pagination.put("totalElements", pageReclamation.getTotalElements());
        pagination.put("totalPages", pageReclamation.getTotalPages());

        // Construire la réponse finale directement
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200");
        response.put("pagination", pagination);
        response.put("list", resultList);

        // Retourner la réponse sans la clé "body"
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ApiResponse<HashMap<String, Object>> getReclamation(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Reclamation : {}", id);
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);

        if (!optionalReclamation.isPresent()) {
            HashMap<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "Aucune réclamation trouvé pour l'ID : " + id);
            return ApiResponse.errorResponse(
                ApiResponse.ResponseCode.NOT_FOUND,
                errorResult
            );
        }

        HashMap<String, Object> result = new HashMap<>();
        Reclamation reclamation = optionalReclamation.get();

        for(CarriereManquantes c : reclamation.getCarriereManquantes()) {
            if(c != null) {
                reclamation.addCarriereManquante(c);
            }
        }

        Optional<Salarie> salarie = salarieRepository.findByUserId(reclamation.getUserId());
        if(salarie.isPresent()) {
            result.put("Salarie", salarie.get());
        } else {
            result.put("Salarie", null);
            LOG.warn("Aucun salarié trouvé pour l'userId: {}", reclamation.getUserId());
        }

        result.put("reclamation", reclamation);

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, result);
    }


    @DeleteMapping("/{id}")
    public ApiResponse <Void> deleteReclamation(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Reclamation : {}", id);
        reclamationRepository.deleteById(id);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK,null);
    }

    @GetMapping("/user-id/{userId}")
    public ApiResponse<List<HashMap<String, Object>>> getReclamationsByUserId(@PathVariable Long userId) {
        LOG.debug("REST request to get Reclamations by userId : {}", userId);
        List<Reclamation> list = reclamationRepository.findByUserIdOrderByIdDesc(userId);
        List<HashMap<String, Object>> resultList = new ArrayList<>();

        for(Reclamation rec : list) {
            HashMap<String, Object> result = new HashMap<>();

            for(CarriereManquantes c : rec.getCarriereManquantes()) {
                if(c != null) {
                    rec.addCarriereManquante(c);
                }
            }

            Optional<Salarie> salarie = salarieRepository.findByUserId(rec.getUserId());
            if(salarie.isPresent()) {
                result.put("Salarie", salarie.get());
            } else {
                result.put("Salarie", null);
                LOG.warn("Aucun salarié trouvé pour l'userId: {}", rec.getUserId());
            }

            result.put("reclamation", rec);
            resultList.add(result);
        }

        if(resultList.isEmpty()) {
            HashMap<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "Aucune réclamation trouvée pour l'userId : " + userId);
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.NOT_FOUND, Arrays.asList(errorResult));
        }

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, resultList);
    }

    @GetMapping("/user-id/{userId}/agence-id/{agenceId}")
    public ApiResponse<List<HashMap<String, Object>>> getReclamationsByUserIdAndAgenceId(@PathVariable Long userId, @PathVariable Long agenceId) {
        LOG.debug("REST request to get Reclamations by userId and agenceId : {} {}", userId, agenceId);
        List<Reclamation> list = reclamationRepository.findByUserIdAndAgenceIdOrderByIdDesc(userId, agenceId);
        List<HashMap<String, Object>> resultList = new ArrayList<>();

        for(Reclamation rec : list) {
            HashMap<String, Object> result = new HashMap<>();

            for(CarriereManquantes c : rec.getCarriereManquantes()) {
                if(c != null) {
                    rec.addCarriereManquante(c);
                }
            }

            Optional<Salarie> salarie = salarieRepository.findByUserId(rec.getUserId());
            if(salarie.isPresent()) {
                result.put("Salarie", salarie.get());
            } else {
                result.put("Salarie", null);
                LOG.warn("Aucun salarié trouvé pour l'userId: {}", rec.getUserId());
            }

            result.put("reclamation", rec);
            resultList.add(result);
        }

        if(resultList.isEmpty()) {
            HashMap<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "Aucune réclamation trouvée pour l'userId : " + userId + " et agenceId : " + agenceId);
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.NOT_FOUND, Arrays.asList(errorResult));
        }

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, resultList);
    }

    @GetMapping("/agence-id/{agenceId}")
    public ApiResponse<List<HashMap<String, Object>>> getReclamationsByAgenceId(@PathVariable Long agenceId) {
        LOG.debug("REST request to get Reclamations by agenceId : {} ", agenceId);
        List<Reclamation> list = reclamationRepository.findByAgenceIdOrderByIdDesc(agenceId);
        List<HashMap<String, Object>> resultList = new ArrayList<>();

        for(Reclamation rec : list) {
            HashMap<String, Object> result = new HashMap<>();

            for(CarriereManquantes c : rec.getCarriereManquantes()) {
                if(c != null) {
                    rec.addCarriereManquante(c);
                }
            }

            Optional<Salarie> salarie = salarieRepository.findByUserId(rec.getUserId());
            if(salarie.isPresent()) {
                result.put("Salarie", salarie.get());
            } else {
                result.put("Salarie", null);
                LOG.warn("Aucun salarié trouvé pour l'userId: {}", rec.getUserId());
            }

            result.put("reclamation", rec);
            resultList.add(result);
        }

        if(resultList.isEmpty()) {
            HashMap<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "Aucune réclamation trouvée pour l'agenceId : " + agenceId);
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.NOT_FOUND, Arrays.asList(errorResult));
        }

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, resultList);
    }

    @GetMapping("/soumis/agence-id/{agenceId}")
    public ApiResponse<List<HashMap<String, Object>>> getReclamationsByAgenceIdAndStatutSoumis(@PathVariable Long agenceId) {
        StatutReclamation statutSoumis = StatutReclamation.SOUMIS;
        LOG.debug("REST request to get Reclamations by agenceId and statut= soumis : {} {}", agenceId, statutSoumis);
        List<Reclamation> list = reclamationRepository.findByAgenceIdAndStatutOrderByIdDesc(agenceId, statutSoumis);
        List<HashMap<String, Object>> resultList = new ArrayList<>();

        for(Reclamation rec : list) {
            HashMap<String, Object> result = new HashMap<>();

            for(CarriereManquantes c : rec.getCarriereManquantes()) {
                if(c != null) {
                    rec.addCarriereManquante(c);
                }
            }

            Optional<Salarie> salarie = salarieRepository.findByUserId(rec.getUserId());
            if(salarie.isPresent()) {
                result.put("Salarie", salarie.get());
            } else {
                result.put("Salarie", null);
                LOG.warn("Aucun salarié trouvé pour l'userId: {}", rec.getUserId());
            }

            result.put("reclamation", rec);
            resultList.add(result);
        }

        if(resultList.isEmpty()) {
            HashMap<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "Aucune réclamation soumise trouvée pour l'agenceId : " + agenceId);
            return ApiResponse.errorResponse(ApiResponse.ResponseCode.NOT_FOUND, Arrays.asList(errorResult));
        }

        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, resultList);
    }

    @PutMapping("/validate")
    @Transactional
    public ApiResponse<Object> validate(
        @Valid @RequestBody ValidateRequest validateRequest
    ) {
        TransactionStatus status = null;
        try {

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setName("ReclamationTransaction");
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            status = transactionManager.getTransaction(def);
            Long id;
            if(validateRequest.getReclamation() != null &&
                validateRequest.getReclamation().getId() !=null){
                id = validateRequest.getReclamation().getId();
            }else {
                transactionManager.rollback(status);
                return ApiResponse.error400("La réclamation doit être renseignée.");
            }
            LOG.debug("REST request to validate Reclamation : {}", id);

            if (validateRequest.getTypeOperation() == null) {
                transactionManager.rollback(status);
                return ApiResponse.error400("Le typeOperation est obligatoire");
            }

            try {
                TypeOperation.valueOf(validateRequest.getTypeOperation());
            } catch (IllegalArgumentException e) {
                transactionManager.rollback(status);
                return ApiResponse.error400("Type d'opération invalide. Les valeurs acceptées sont: VALIDATION, REJETE, RETOURNE");
            }
            Optional<String> loggedUsername = getCurrentUserLogin();
            if (!loggedUsername.isPresent()) {
                transactionManager.rollback(status);
                return ApiResponse.error400("Utilisateur non connecté");
            }

            String username = loggedUsername.get();
            User user = userRepository.findByLogin(username);
            if (user == null) {
                transactionManager.rollback(status);
                return ApiResponse.error400("L'utilisateur avec le username : "+username+" est introuvable.");
            }

            Optional<Reclamation> reclamationOpt = reclamationRepository.findById(id);
            if (!reclamationOpt.isPresent()) {
                transactionManager.rollback(status);
                return ApiResponse.error404("Réclamation non trouvée");
            }

            if((validateRequest.getMotif() == null || validateRequest.getMotif().isEmpty()) &&
                (Objects.equals(validateRequest.getTypeOperation(), StatutReclamation.RETOURNE.toString()) || Objects.equals(validateRequest.getTypeOperation(), StatutReclamation.REJETE.toString()))
            ){
                transactionManager.rollback(status);
                return ApiResponse.error400("Le motif doit être renseigner pour les statuts REJETE ou RETOURNE.");
            }

            Reclamation reclamation = reclamationOpt.get();
            Object statusResult = reclamationService.determineNewStatus(user, TypeOperation.valueOf(validateRequest.getTypeOperation()), reclamation);
            if (!(statusResult instanceof StatutReclamation)) {
                transactionManager.rollback(status);
                return ApiResponse.error400(statusResult);
            }

            if(StatutReclamation.valueOf(statusResult.toString()) == StatutReclamation.VALIDATION_GESTIONNAIRE_DE_COMPTE &&
                reclamation.getTypeReclamation() == TypeReclamation.ABSENCE_DE_CARRIERE &&
                reclamation.getCarriereManquantes() != null){
                Integer requestCarriereManquantesSize = validateRequest.getReclamation().getCarriereManquantes().size();
                Integer reclamationCarriereManquantesSize = carriereManquantesRepository.findByReclamationId(reclamation.getId()).size();
                if(!Objects.equals(requestCarriereManquantesSize, reclamationCarriereManquantesSize)){
                    transactionManager.rollback(status);
                    return ApiResponse.error400("Toutes les carrières manquantes de cette réclamation doivent être renseignées");
                }
                if(requestCarriereManquantesSize<1){
                    transactionManager.rollback(status);
                    return ApiResponse.error400("La liste des carrières manquantes ne doit pas être vide");
                }
                for (CarriereManquanteDTO
                    carriere : validateRequest.getReclamation().getCarriereManquantes()) {
                    if (!reclamationService.isCarriereMontantsValid(carriere)) {
                        transactionManager.rollback(status);
                        return ApiResponse.error400(
                            String.format("Les montants sont obligatoires pour la carrière ID %d : Salaire Brut, Cotisation CSS et Cotisation IPRES doivent être renseignés",
                                carriere.getId())
                        );
                    }
                }

                for (CarriereManquanteDTO carriere : validateRequest.getReclamation().getCarriereManquantes()) {
                    Optional<CarriereManquantes> existingCarriere = carriereManquantesRepository.findById(carriere.getId());

                    if (existingCarriere.isPresent()) {
                        CarriereManquantes updateCarriere = existingCarriere.get();
                        if(!Objects.equals(updateCarriere.getReclamation().getId(), reclamation.getId())){
                            transactionManager.rollback(status);
                            return ApiResponse.error404("La carrière manquante avec l'id : "+updateCarriere.getId() +" n'appartient pas à la réclamation "+reclamation.getId());
                        }
                        updateCarriere.setSalaireBrut(carriere.getSalaireBrut());
                        updateCarriere.setCotisationCss(carriere.getCotisationCss());
                        updateCarriere.setCotisationIpres(carriere.getCotisationIpres());
                        carriereManquantesRepository.save(updateCarriere);
                    } else {
                        transactionManager.rollback(status);
                        return ApiResponse.error404(String.format("Carrière manquante non trouvée avec l'ID : %d", carriere.getId()));
                    }
                }
            }

            reclamation.setStatut(StatutReclamation.valueOf(statusResult.toString()));
            if(validateRequest.getReclamation().getAgenceCssID() != null){
                reclamation.setAgenceCssID(validateRequest.getReclamation().getAgenceCssID());
            }
            if(validateRequest.getReclamation().getNumSalarie() != null){
                reclamation.setNumSalarie(validateRequest.getReclamation().getNumSalarie());
            }
            if(validateRequest.getReclamation().getAgenceId() != null){
                reclamation.setAgenceId(validateRequest.getReclamation().getAgenceId());
            }
            if(validateRequest.getReclamation().getSecondNumSalarie() != null){
                reclamation.setSecondNumSalarie(validateRequest.getReclamation().getSecondNumSalarie());
            }
            if(validateRequest.getReclamation().getLibAutresRec() != null){
                reclamation.setLibAutresRec(validateRequest.getReclamation().getLibAutresRec());
            }
            if(validateRequest.getReclamation().getDescriptionAutres() != null){
                reclamation.setDescriptionAutres(validateRequest.getReclamation().getDescriptionAutres());
            }
            if(validateRequest.getReclamation().getAgenceIpresID() != null){
                reclamation.setAgenceIpresID(validateRequest.getReclamation().getAgenceIpresID());
            }

            switch(TypeOperation.valueOf(validateRequest.getTypeOperation())) {
                case VALIDATION:
                    reclamation.setDateValidateGC(LocalDate.now());
                    reclamation.setUserIdValidateGC(user.getId());
                    reclamation.setMotif("");
                    break;
                case REJETE:
                case RETOURNE:
                    reclamation.setMotif(validateRequest.getMotif());
                    break;
            }
            List<CarriereManquantes> carrieres = carriereManquantesRepository.findByReclamationId(reclamation.getId());
            reclamation.getCarriereManquantes().clear();
            for (CarriereManquantes carriere : carrieres) {
                carriere.setReclamation(reclamation);
                reclamation.getCarriereManquantes().add(carriere);
            }

            Reclamation reclamationSaved = reclamationRepository.save(reclamation);
            if (status.isNewTransaction()) {
                transactionManager.commit(status);
            }
            return ApiResponse.success(reclamationSaved);

        } catch (Exception e) {
            if (status != null && !status.isCompleted()) {
                transactionManager.rollback(status);
            }
        return ApiResponse.error400("Une erreur est survenue : " + e.getMessage());
    }
    }

}
