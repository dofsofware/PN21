package com.secusociale.portail.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AgentDTO extends UserDTO {
    private Agence agenceObject;

    private String profil;

    private User utilisateur;

    public AgentDTO() {

    }

    public AgentDTO(User user, Agence agenceObject) {
        super(user);
        this.agenceObject = agenceObject;
    }
    @JsonIgnore
    public User getUser() {
        return this.utilisateur;
    }

    public AgentDTO(User user) {
        this.setId(user.getId());
        this.setLogin(user.getLogin());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhone());
        this.setActivated(user.getActivated());
        this.setImageUrl(user.getImageUrl());
        this.setTypeCompte(user.getTypeCompte());
        this.setAgence(user.getAgence());
        this.setInstitution(user.getInstitution());
        this.setLangKey(user.getLangKey());
        this.setCreatedBy(user.getCreatedBy());
        this.setCreatedDate(user.getCreatedDate());
        this.setLastModifiedBy(user.getLastModifiedBy());
        this.setLastModifiedDate(user.getLastModifiedDate());
        List<String> roles = new ArrayList<>();
        switch (user.getTypeCompte()) {
            case Constants.GESTIONNAIRE_EMPLOYEUR:
                roles.add(AuthoritiesConstants.GESTIONNAIRE_EMPLOYEUR);
                break;
            case Constants.GESTIONNAIRE_SALARIE:
                roles.add(AuthoritiesConstants.GESTIONNAIRE_SALARIE);
                break;
            case Constants.CHEF_AGENCE:
                roles.add(AuthoritiesConstants.CHEF_AGENCE);
                break;
        }
//        roles.add(AuthoritiesConstants.AGENT);
        this.setAuthorities(new HashSet<>(roles));
        this.setUtilisateur(user);
        this.setProfil(user.getTypeCompte());
    }

    public Agence getAgenceObject() {
        return agenceObject;
    }

    public void setAgenceObject(Agence agenceObject) {
        this.agenceObject = agenceObject;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
