package com.example.desafio.api.models.customvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxYearValidator.class)
public @interface MaxYear {

    String message() default "O ano não pode ser maior que o ano atual";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
