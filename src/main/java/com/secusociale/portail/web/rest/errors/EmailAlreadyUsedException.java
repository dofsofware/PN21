package com.secusociale.portail.web.rest.errors;

public class EmailAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Cet email est déjà utilisé ! Veuillez en choisir un autre.", "userManagement", "emailexists");
    }
}
