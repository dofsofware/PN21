package com.secusociale.portail.web.rest.errors;

public class NumeroUniqueAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public NumeroUniqueAlreadyUsedException() {
        super(ErrorConstants.NUMERO_UNIQUE_ALREADY_USED_TYPE, "Cet numero unique de salarié est déjà utilisé ! Veuillez en choisir un autre.", "userManagement", "emailexists");
    }
}
