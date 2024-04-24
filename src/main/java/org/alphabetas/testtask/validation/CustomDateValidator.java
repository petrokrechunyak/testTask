package org.alphabetas.testtask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class CustomDateValidator implements ConstraintValidator<DateConstraint, LocalDate> {

    @Value("${validation.age}")
    private int age;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();

        return value.isBefore(now.minusYears(age));
    }
}
