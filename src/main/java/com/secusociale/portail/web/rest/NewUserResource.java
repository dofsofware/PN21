package com.secusociale.portail.web.rest;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.repository.NewUserRepository;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.UserQueryService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.dto.UserCriteria;
import com.secusociale.portail.service.dto.UserDTO;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

@RestController
@RequestMapping("/api")
public class NewUserResource {

    private final Logger log = LoggerFactory.getLogger(NewUserResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NewUserRepository newUserRepository;

    private final UserQueryService newUserQueryService;

    private final UserService userService;

    private final MailService mailService;

    public NewUserResource(NewUserRepository newUserRepository, UserQueryService newUserQueryService, UserService userService, MailService mailService) {

        this.newUserRepository = newUserRepository;
        this.newUserQueryService = newUserQueryService;
        this.userService = userService;
        this.mailService = mailService;
    }


    /**
     * {@code GET  /users/employeurs} : get all the employeurs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carrieres in body.
     */
    @GetMapping("/users/employeurs")
    public ResponseEntity<HashMap<String, Object>> getAllEmployeurs(UserCriteria criteria, Pageable pageable) {
        if (!getCurrentUserLogin().isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";

        Optional<UserDTO> optionalUserDTO = userService.getUserWithAuthoritiesByLogin(username)
            .map(UserDTO::new);
        if (!optionalUserDTO.isPresent()) {
            throw Problem.builder().withDetail("Vous devez vous connecter pour acceder a cette ressource").withTitle("Erreur").build();
        }
        UserDTO user = optionalUserDTO.get();
        StringFilter typeCompteFilter = new StringFilter();
        List<String> types = new ArrayList<>();
        types.add(Constants.EMPLOYEUR);
        types.add(Constants.CABINET);
        typeCompteFilter.setIn(types);
        criteria.setTypeCompte(typeCompteFilter);
        log.debug("REST request to get Carrieres by criteria: {}", criteria);
        Page<UserDTO> page = newUserQueryService.findByCriteria(criteria, pageable);
        List<UserDTO> list = page.getContent();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page.getNumber());
        pagination.put("size", page.getSize());
        pagination.put("totalElements", page.getTotalElements());
        result.put("code", "200");
        result.put("list", list);
        result.put("pagination", pagination);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }


}
