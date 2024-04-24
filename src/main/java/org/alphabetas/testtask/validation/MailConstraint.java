package org.alphabetas.testtask.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomMailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MailConstraint {
    String message() default "Email format is wrong!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

