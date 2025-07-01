package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.Commentaire;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.CommentaireRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.CommentaireDTO;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commentaire")
@Transactional
public class CommentaireResource {

    private static final Logger LOG = LoggerFactory.getLogger(CommentaireResource.class);
    private static final String ENTITY_NAME = "jhipsterReclamation";
    private final UserRepository userRepository;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommentaireRepository commentaireRepository;

    @Autowired
    public CommentaireResource(UserRepository userRepository, CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ApiResponse<Commentaire> createCommentaire(@Valid @RequestBody Commentaire commentaire) throws URISyntaxException {
        LOG.debug("REST request to save Commentaire : {}", commentaire);

        // Vérification de l'ID
        if (commentaire.getId() != null) {
            throw new BadRequestAlertException("A new reclamation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();

        if (!loggedusername.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = loggedusername.get();
        Long userId = userRepository.findByLogin(username).getId();

        commentaire.setCreatedBy(userId);
        commentaire.setCreatedAt(Instant.now());
        // Sauvegarde du commentaire
        commentaire = commentaireRepository.save(commentaire);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, commentaire);
    }
    @GetMapping("")
    public ApiResponse<List<Commentaire>> getAllCommentaires() {
        LOG.debug("REST request to get all Commentaires");
        List<Commentaire> commentaires = commentaireRepository.findAll();
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, commentaires);
    }

    @GetMapping("/{nouvelleImmatriculationId}")
    public ResponseEntity<List<CommentaireDTO>> getCommentairesByNouvelleImmatriculationId(@PathVariable Long nouvelleImmatriculationId) {
        LOG.debug("REST request to get Commentaires for nouvelleImmatriculationId : {}", nouvelleImmatriculationId);

        // Récupérer les commentaires associés à l'ID de la nouvelle immatriculation
        List<Commentaire> commentaires = commentaireRepository.findByNouvelleImmatriculationId(nouvelleImmatriculationId);

        if (commentaires.isEmpty()) {
            return ResponseEntity.notFound().build(); // Renvoie 404 si aucun commentaire n'est trouvé
        }

        // Convertir les commentaires en DTO pour inclure les informations de l'utilisateur
        List<CommentaireDTO> commentaireDTOs = commentaires.stream()
            .map(commentaire -> {
                User user = commentaire.getUser(); // On récupère l'utilisateur du commentaire
                Instant createdAtInstant = commentaire.getCreatedAt();

                // Convertir Instant en LocalDateTime
                LocalDateTime createdAt = (createdAtInstant != null) ?
                    LocalDateTime.ofInstant(createdAtInstant, ZoneId.systemDefault()) : null;

                return new CommentaireDTO(
                    commentaire.getId(),
                    commentaire.getNouvelleImmatriculationId(),
                    commentaire.getDescription(),
                    commentaire.getTypeCommentaire(),
                    commentaire.getCreatedBy(),
                    createdAt, // On passe la version convertie en LocalDateTime
                    commentaire.getDestinataireId(),
                    (user != null) ? user.getFirstName() : null,
                    (user != null) ? user.getLastName() : null
                );
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(commentaireDTOs);
    }




    @PutMapping("/{id}")
    public ApiResponse<Commentaire> updateCommentaire(@PathVariable Long id, @Valid @RequestBody Commentaire commentaire) throws URISyntaxException {
        LOG.debug("REST request to update Commentaire : {}", commentaire);

        if (commentaire.getId() == null || !id.equals(commentaire.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        commentaire = commentaireRepository.save(commentaire);
        return ApiResponse.successResponse(ApiResponse.ResponseCode.OK, commentaire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Long id) {
        LOG.debug("REST request to delete Commentaire : {}", id);
        commentaireRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
