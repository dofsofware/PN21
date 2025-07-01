package com.secusociale.portail.validators;

import com.secusociale.portail.validators.classes.UniqueNineaConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNineaConstraint.class)
public @interface UniqueNinea {
    String message() default "une immatriculation est deja enregistrer avec ce numero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
