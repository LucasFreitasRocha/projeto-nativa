package com.nativa.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidadorEmail.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "Email inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}