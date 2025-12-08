package br.uniesp.si.techback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SenhaForteValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaForte {
    String message() default "Senha fraca: deve conter min 8 caracteres, letras maiúsculas, minúsculas, número e símbolo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
