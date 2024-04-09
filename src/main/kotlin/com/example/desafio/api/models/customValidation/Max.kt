package com.example.desafio.api.models.customValidation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MaxYearValidator::class])
@MustBeDocumented
annotation class MaxYear(
        val message: String = "O ano não pode ser maior que o ano atual",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)