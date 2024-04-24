package org.alphabetas.testtask.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateConstraint {
    String message() default "Your age is too small!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
