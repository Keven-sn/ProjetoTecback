package br.uniesp.si.techback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumSubsetValidator implements ConstraintValidator<EnumSubset, String> {

    private Set<String> accepted;

    @Override
    public void initialize(EnumSubset annotation) {
        this.accepted = Arrays.stream(annotation.anyOf()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return accepted.contains(value);
    }
}
