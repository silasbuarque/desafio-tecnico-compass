package com.compass.challange.voting.system.util;

import org.springframework.stereotype.Service;

/**
 * Classe criada com finalidade de substituir a integração
 * com o heroku, dado que ele encontra-se fora do ar no
 * momento da resolução do problema.
 */
@Service
public class DocumentValidationService {

    public boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int sum1 = 0;
            int sum2 = 0;
            int digit1, digit2;

            for (int i = 0; i < 9; i++) {
                int num = Character.getNumericValue(cpf.charAt(i));
                sum1 += num * (10 - i);
            }
            digit1 = 11 - (sum1 % 11);
            if (digit1 >= 10) digit1 = 0;

            for (int i = 0; i < 10; i++) {
                int num = Character.getNumericValue(cpf.charAt(i));
                sum2 += num * (11 - i);
            }
            digit2 = 11 - (sum2 % 11);
            if (digit2 >= 10) digit2 = 0;

            return digit1 == Character.getNumericValue(cpf.charAt(9)) &&
                    digit2 == Character.getNumericValue(cpf.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
