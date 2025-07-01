package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.DeclarationManquante;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.DeclarationManquanteRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;
import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.DeclarationManquante}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DeclarationManquanteResource {

    private static final String ENTITY_NAME = "portailCssIpresV2DeclarationManquante";
    private final Logger log = LoggerFactory.getLogger(DeclarationManquanteResource.class);
    private final DeclarationManquanteRepository declarationManquanteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentUrlService documentUrlService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public DeclarationManquanteResource(DeclarationManquanteRepository declarationManquanteRepository) {
        this.declarationManquanteRepository = declarationManquanteRepository;
    }

    /**
     * {@code POST  /declaration-manquantes} : Create a new declarationManquante.
     *
     * @param declarationManquante the declarationManquante to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new declarationManquante, or with status {@code 400 (Bad Request)} if the declarationManquante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/declaration-manquantes")
    public ResponseEntity<DeclarationManquante> createDeclarationManquante(@RequestBody DeclarationManquante declarationManquante) throws URISyntaxException {
        log.debug("REST request to save DeclarationManquante : {}", declarationManquante);
        if (declarationManquante.getId() != null) {
            throw new BadRequestAlertException("A new declarationManquante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        declarationManquante.dateUpload(LocalDate.now());
        declarationManquante.status(false);
        Optional<User> user = getCurrentUserLogin().flatMap(userRepository::findOneByLogin);
        user.map(User::getId).ifPresent(declarationManquante::userId);
        //file handling
        MultipartFile multipartFile = base64ToMultipart(declarationManquante.getFileJoin());
        try {
            String dest = declarationManquante.getPersonId() != null ?
                String.format("%s-%s", declarationManquante.getAncienNumeroIpres(), declarationManquante.getPersonId())
                : String.format("%s", declarationManquante.getAncienNumeroIpres());
            String fileName = String.format("%d.pdf", declarationManquante.getAnnee());
            String filePath = documentUrlService.uploadDocument(multipartFile, dest, fileName);
            declarationManquante.setFileJoin(filePath);
            log.info(String.format("Declaration Manquante enregistre avec succes, le lien du document est: %s", filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //file handling

        DeclarationManquante result = declarationManquanteRepository.save(declarationManquante);
        return ResponseEntity.created(new URI("/api/declaration-manquantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /declaration-manquantes} : Updates an existing declarationManquante.
     *
     * @param declarationManquante the declarationManquante to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated declarationManquante,
     * or with status {@code 400 (Bad Request)} if the declarationManquante is not valid,
     * or with status {@code 500 (Internal Server Error)} if the declarationManquante couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/declaration-manquantes")
    public ResponseEntity<DeclarationManquante> updateDeclarationManquante(@RequestBody DeclarationManquante declarationManquante) throws URISyntaxException {
        log.debug("REST request to update DeclarationManquante : {}", declarationManquante);
        if (declarationManquante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeclarationManquante result = declarationManquanteRepository.save(declarationManquante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, declarationManquante.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /declaration-manquantes} : get all the declarationManquantes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of declarationManquantes in body.
     */
    @GetMapping("/declaration-manquantes")
    public List<DeclarationManquante> getAllDeclarationManquantes() {
        log.debug("REST request to get all DeclarationManquantes");
        return declarationManquanteRepository.findAll();
    }

    @GetMapping("/declaration-manquantes/user/{userId}")
    public List<DeclarationManquante> getAllDeclarationManquantesByUser(@PathVariable Long userId) {
        log.debug("REST request to get all DeclarationManquantes by userId");
        return declarationManquanteRepository.findAllByUserId(userId);
    }

    /**
     * {@code GET  /declaration-manquantes/:id} : get the "id" declarationManquante.
     *
     * @param id the id of the declarationManquante to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the declarationManquante, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/declaration-manquantes/{id}")
    public ResponseEntity<DeclarationManquante> getDeclarationManquante(@PathVariable Long id) {
        log.debug("REST request to get DeclarationManquante : {}", id);
        Optional<DeclarationManquante> declarationManquante = declarationManquanteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(declarationManquante);
    }

    /**
     * {@code DELETE  /declaration-manquantes/:id} : delete the "id" declarationManquante.
     *
     * @param id the id of the declarationManquante to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/declaration-manquantes/{id}")
    public ResponseEntity<Void> deleteDeclarationManquante(@PathVariable Long id) {
        log.debug("REST request to delete DeclarationManquante : {}", id);
        declarationManquanteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
