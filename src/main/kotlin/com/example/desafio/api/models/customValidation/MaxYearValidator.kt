package com.example.desafio.api.models.customValidation


import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.*


val currentYear: Int
    get() = Calendar.getInstance()[Calendar.YEAR]

class MaxYearValidator : ConstraintValidator<MaxYear, Int> {
    override fun isValid(value: Int, context: ConstraintValidatorContext?): Boolean {
        return value <= currentYear
    }
}