package com.secusociale.portail.web.rest.errors;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Cet login est déjà utilisé ! Veuillez en choisir un autre.", "userManagement", "userexists");
    }
}
