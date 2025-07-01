package com.secusociale.portail.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.secusociale.portail.security.jwt.JWTFilter;
import com.secusociale.portail.security.jwt.TokenProvider;
import com.secusociale.portail.web.rest.vm.CustomResponse;
import com.secusociale.portail.web.rest.vm.LoginVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
@Transactional
@RequiredArgsConstructor
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        CustomResponse jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {
        private Long id;
        private String typeCompte;
        private String agence;
        private String numeroUnique;
        private String email;
        private String username;
        private String fullName;
        private String[] roles;
        private boolean firstConnexion;
        //        private Localimmatriculation[] immatriculations;
        private String accessToken;
        private Long expiresIn;
        private Integer nbLocalImmat;
        private Integer nbOldImmat;
        private Integer nbImmats;
        private String cachet;

        JWTToken(CustomResponse customResponse) {
            this.id = customResponse.id;
            this.typeCompte = customResponse.typeCompte;
            this.numeroUnique = customResponse.numeroUnique;
            this.agence = customResponse.agence;
            this.email = customResponse.email;
            this.username = customResponse.username;
            this.fullName = customResponse.fullName;
            this.roles = customResponse.roles;
            this.firstConnexion = customResponse.firstConnexion;
            this.accessToken = customResponse.accessToken;
            this.expiresIn = customResponse.expiresIn;
            this.nbLocalImmat = customResponse.nbLocalImmat;
            this.nbOldImmat = customResponse.nbOldImmat;
            this.nbImmats = customResponse.nbOldImmat + customResponse.nbLocalImmat;
            this.cachet = customResponse.cachet;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("typeCompte")
        public String getTypeCompte() {
            return typeCompte;
        }

        @JsonProperty("numeroUnique")
        public String getNumeroUnique() {
            return numeroUnique;
        }

        public void setNumeroUnique(String numeroUnique) {
            this.numeroUnique = numeroUnique;
        }

        public void setTypeCompte(String typeCompte) {
            this.typeCompte = typeCompte;
        }

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @JsonProperty("username")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @JsonProperty("authorities")
        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

        @JsonProperty("access_token")
        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        @JsonProperty("expires_in")
        public Long getExpiresIn() {
            return expiresIn;
        }

        @JsonProperty("first_connexion")
        public boolean getFirstConnexion() {
            return this.firstConnexion;
        }

        @JsonProperty("fullname")
        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("nboldimmats")
        public Integer getNbOldImmat() {
            return nbOldImmat;
        }

        public void setNbOldImmat(Integer nbOldImmat) {
            this.nbOldImmat = nbOldImmat;
        }

        @JsonProperty("nblocalimmats")
        public Integer getNbLocalImmat() {
            return nbLocalImmat;
        }

        @JsonProperty("nbimmats")
        public Integer getNbImmats() {
            return nbImmats;
        }

        public void setNbImmats(Integer nbImmats) {
            this.nbImmats = nbImmats;
        }

        public void setNbLocalImmat(Integer nbLocalImmat) {
            this.nbLocalImmat = nbLocalImmat;
        }

        @JsonProperty("cachet")
        public String getCachet() {
            return cachet;
        }

        public void setCachet(String cachet) {
            this.cachet = cachet;
        }

        //        @JsonProperty("immatriculations")
//        public Localimmatriculation[] getImmatriculations() {
//            return immatriculations;
//        }
//
//        public void setImmatriculations(Localimmatriculation[] immatriculations) {
//            this.immatriculations = immatriculations;
//        }

        public void setExpiresIn(Long expiresIn) {
            this.expiresIn = expiresIn;
        }

    }

}
