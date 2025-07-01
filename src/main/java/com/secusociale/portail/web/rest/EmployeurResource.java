package com.secusociale.portail.web.rest;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Employeur;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.EmployeurService;
import com.secusociale.portail.service.dto.AgenceEmployeurLinkDTO;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing {@link com.secusociale.portail.domain.Employeur}.
 */
@RestController
@RequestMapping("/api")
public class EmployeurResource {

    private final Logger log = LoggerFactory.getLogger(EmployeurResource.class);

    private static final String ENTITY_NAME = "employeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeurService employeurService;

    public EmployeurResource(EmployeurService employeurService) {
        this.employeurService = employeurService;
    }

    /**
     * {@code POST  /employeurs} : Create a new employeur.
     *
     * @param employeur the employeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeur, or with status {@code 400 (Bad Request)} if the employeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employeurs")
    public ResponseEntity<Employeur> createEmployeur(@RequestBody Employeur employeur) throws URISyntaxException {
        log.debug("REST request to save Employeur : {}", employeur);
        if (employeur.getId() != null) {
            throw new BadRequestAlertException("A new employeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Employeur result = employeurService.save(employeur);
        return ResponseEntity.created(new URI("/api/employeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employeurs} : Updates an existing employeur.
     *
     * @param employeur the employeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeur,
     * or with status {@code 400 (Bad Request)} if the employeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employeurs")
    public ResponseEntity<Employeur> updateEmployeur(@RequestBody Employeur employeur) throws URISyntaxException {
        log.debug("REST request to update Employeur : {}", employeur);
        if (employeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Employeur result = employeurService.save(employeur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /employeurs} : get all the employeurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employeurs in body.
     */
    @GetMapping("/employeurs")
    public ResponseEntity<List<Employeur>> getAllEmployeurs(Pageable pageable) {
        log.debug("REST request to get a page of Employeurs");
        Page<Employeur> page = employeurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employeurs/:id} : get the "id" employeur.
     *
     * @param id the id of the employeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employeurs/{id}")
    public ResponseEntity<Employeur> getEmployeur(@PathVariable Long id) {
        log.debug("REST request to get Employeur : {}", id);
        Optional<Employeur> employeur = employeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeur);
    }


    /**
     * {@code GET  /employeursByLogin } : get employeur by Login.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employeursByLogin")
    public ResponseEntity<List<Employeur>> getEmployeurByLogin() {
        log.debug("REST request to get Employeur : {}");
        List<Employeur> employeurs = employeurService.findEmployeurByUser();
        return new ResponseEntity<List<Employeur>>(employeurs, HttpStatus.OK);
    }

    /**
     * {@code GET  /employeurByIdentifiant/{typeIdentifiant}/{numeroIdentifiant} : get employeur by TypeIdentifiant.
     *
     * @param typeIdentifiant   thetypeIdentifiant of employeur .
     * @param numeroIdentifiant the numeroIdentifiant of employeur
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeur, or with status {@code 404 (Not Found)}.
     */

    @GetMapping("/employeurByIdentifiant/{typeIdentifiant}/{numeroIdentifiant}")
    public ResponseEntity<Optional<Employeur>> getEmployeurByIdentifiantAndTypeIdentifiant(@PathVariable String typeIdentifiant, @PathVariable String numeroIdentifiant) {
        log.debug("REST request to get Employeur : {} {}", typeIdentifiant, numeroIdentifiant);
        Optional<Employeur> employeur = employeurService.findEmployeurByTypeIdentifiantAndNumeroIdentifiant(typeIdentifiant, numeroIdentifiant);
        return new ResponseEntity<Optional<Employeur>>(employeur, HttpStatus.OK);
    }

    @PostMapping("/employeurs/check-manager")
    public ResponseEntity<?> checkManager(@RequestBody Map<String, Object> requestBody) {
        String phoneNumber = requestBody.get("phoneNumber") != null ? requestBody.get("phoneNumber").toString() : null;
        String userName = requestBody.get("userName") != null ? requestBody.get("userName").toString() : null;

        ResponseEntity<Map<String, Object>> body = employeurService.checkRequiredParamsCheckMandataire(phoneNumber, userName);
        if (body != null) return body;

        String otp = RandomStringUtils.randomNumeric(6);
        employeurService.sendCode(phoneNumber, userName, otp);
        Map<String, Object> Response = new HashMap<>();
        Response.put("status", "OK");
        Response.put("code", "200");
        return ResponseEntity.ok().body(Response);
    }

    @PostMapping("/employeurs/update-manager")
    public ResponseEntity<?> updateManager(@RequestBody Map<String, Object> requestBody) {
        String codeOTP = requestBody.get("codeOtp") != null ? requestBody.get("codeOtp").toString() : null;
        String userNameEmployee = requestBody.get("userNameEmployee") != null ? requestBody.get("userNameEmployee").toString() : null;
        String userNameManager = requestBody.get("userNameManager") != null ? requestBody.get("userNameManager").toString() : null;

        ResponseEntity<?> body = employeurService.checkRequiredParamsVerifieCodeOTP(codeOTP,userNameEmployee,userNameManager);
        if (body != null) return body;

        ResponseEntity<Map<String, Object>> reponse = employeurService.validateCode(userNameManager, codeOTP);
        if (reponse != null) return reponse;

        ResponseEntity<Map<String, Object>> reponseService = employeurService.updateManager(userNameEmployee,userNameManager);
        if (reponseService != null) return reponseService;

        Optional<User> userOptional = employeurService.findByPhoneNumberOrUserName("phoneNumber", userNameManager, Constants.CABINET);

        Map<String, Object> Response = new HashMap<>();
        Response.put("message", "Manager modifié avec succès.");
        Response.put("status", "OK");
        Response.put("code", "200");
        userOptional.ifPresent(user -> {
            Response.put("manager", user);
        });
        return ResponseEntity.ok().body(Response);
    }

    /**
     * {@code DELETE  /employeurs/:id} : delete the "id" employeur.
     *
     * @param id the id of the employeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employeurs/{id}")
    public ResponseEntity<Void> deleteEmployeur(@PathVariable Long id) {
        log.debug("REST request to delete Employeur : {}", id);
        employeurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/employeur-agences/link")
    public ApiResponse<Object> linkAgencesToEmployeur(
        @RequestBody AgenceEmployeurLinkDTO dto) {
        log.debug("REST request to link agencies to employer : {}", dto);
        if (dto == null) {
            return ApiResponse.error400("Les données sont obligatoires");
        }
        if (dto.getEmployeurId() == null) {
            return ApiResponse.error400("L'ID de l'employeur est obligatoire");
        }
        if (dto.getAgenceCssCode() == null || dto.getAgenceCssCode().trim().isEmpty()) {
            return ApiResponse.error400("Le code de l'agence CSS est obligatoire");
        }
        if (dto.getAgenceIpresCode() == null || dto.getAgenceIpresCode().trim().isEmpty()) {
            return ApiResponse.error400("Le code de l'agence IPRES est obligatoire");
        }
        ApiResponse<Object> result = employeurService.linkAgencesToEmployeur(dto);
        return ApiResponse.success(result);
    }
}
