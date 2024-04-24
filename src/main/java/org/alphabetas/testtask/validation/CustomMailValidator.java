package org.alphabetas.testtask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

public class CustomMailValidator implements ConstraintValidator<MailConstraint, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EmailValidator.getInstance().isValid(email);
    }
}

