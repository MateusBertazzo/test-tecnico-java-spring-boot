package com.example.desafio.api.models.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class MaxYearValidator implements ConstraintValidator<MaxYear, Integer> {

    private int currentYear;

    @Override
    public void initialize(MaxYear constraintAnnotation) {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value <= currentYear;
    }
}
