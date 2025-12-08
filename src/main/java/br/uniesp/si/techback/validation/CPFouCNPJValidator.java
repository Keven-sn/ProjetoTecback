package br.uniesp.si.techback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFouCNPJValidator implements ConstraintValidator<CPFouCNPJ, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true;
        String digits = value.replaceAll("\\D", "");
        if (digits.length() == 11) return validarCPF(digits);
        if (digits.length() == 14) return validarCNPJ(digits);
        return false;
    }

    private boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) return false;
        if (cpf.chars().distinct().count() == 1) return false;
        try {
            int[] mult1 = {10,9,8,7,6,5,4,3,2};
            int[] mult2 = {11,10,9,8,7,6,5,4,3,2};
            String num = cpf.substring(0,9);
            int sum = 0;
            for (int i=0;i<9;i++) sum += Character.getNumericValue(num.charAt(i)) * mult1[i];
            int rev = 11 - (sum % 11);
            if (rev == 10 || rev == 11) rev = 0;
            if (rev != Character.getNumericValue(cpf.charAt(9))) return false;
            num += rev;
            sum = 0;
            for (int i=0;i<10;i++) sum += Character.getNumericValue(num.charAt(i)) * mult2[i];
            rev = 11 - (sum % 11);
            if (rev == 10 || rev == 11) rev = 0;
            return rev == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) return false;
        if (cnpj.chars().distinct().count() == 1) return false;
        try {
            int[] mult1 = {5,4,3,2,9,8,7,6,5,4,3,2};
            int[] mult2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
            String num = cnpj.substring(0,12);
            int sum = 0;
            for (int i=0;i<12;i++) sum += Character.getNumericValue(num.charAt(i)) * mult1[i];
            int rev = sum % 11;
            rev = rev < 2 ? 0 : 11 - rev;
            if (rev != Character.getNumericValue(cnpj.charAt(12))) return false;
            num += rev;
            sum = 0;
            for (int i=0;i<13;i++) sum += Character.getNumericValue(num.charAt(i)) * mult2[i];
            rev = sum % 11;
            rev = rev < 2 ? 0 : 11 - rev;
            return rev == Character.getNumericValue(cnpj.charAt(13));
        } catch (Exception e) {
            return false;
        }
    }
}
