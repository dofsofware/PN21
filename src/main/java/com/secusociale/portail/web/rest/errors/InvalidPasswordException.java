package com.secusociale.portail.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidPasswordException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super(ErrorConstants.INVALID_PASSWORD_TYPE, "Le nouveau mot de passe et sa confirmation ne sont pas égaux !", Status.BAD_REQUEST);
    }
}
