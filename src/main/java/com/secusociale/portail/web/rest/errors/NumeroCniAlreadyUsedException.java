package com.secusociale.portail.web.rest.errors;

public class NumeroCniAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public NumeroCniAlreadyUsedException() {
        super(ErrorConstants.CNI_ALREADY_USED_TYPE, "Cet numero CNI de salarié est déjà utilisé ! Veuillez en choisir un autre.", "userManagement", "emailexists");
    }
}
