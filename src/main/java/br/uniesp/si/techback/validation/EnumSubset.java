package br.uniesp.si.techback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumSubsetValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumSubset {
    String[] anyOf();
    String message() default "deve ser um dos valores permitidos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
