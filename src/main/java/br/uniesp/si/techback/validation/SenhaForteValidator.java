package br.uniesp.si.techback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaForteValidator implements ConstraintValidator<SenhaForte, String> {

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {
        if (senha == null) return false;
        if (senha.length() < 8) return false;
        boolean hasUpper = senha.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = senha.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = senha.chars().anyMatch(Character::isDigit);
        boolean hasSymbol = senha.chars().anyMatch(ch -> "!@#$%^&*()-_+=[]{}|:;\"'<>,.?/`~".indexOf(ch) >= 0);
        return hasUpper && hasLower && hasDigit && hasSymbol;
    }
}
