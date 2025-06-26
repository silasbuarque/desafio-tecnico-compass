package com.compass.challange.voting.system.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentValidationServiceTest {

    private DocumentValidationService documentValidationService;

    @BeforeEach
    void setUp() {
        documentValidationService = new DocumentValidationService();
    }

    @Test
    @DisplayName("Deve retornar true para um CPF válido")
    void shouldReturnTrueForValidCpf() {
        String validCpf = "27846641042";
        boolean result = documentValidationService.isValid(validCpf);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false para CPF inválido")
    void shouldReturnFalseForInvalidCpf() {
        String invalidCpf = "12345678900";
        boolean result = documentValidationService.isValid(invalidCpf);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve retornar false para CPF com menos de 11 dígitos")
    void shouldReturnFalseForShortCpf() {
        String shortCpf = "123456789";
        boolean result = documentValidationService.isValid(shortCpf);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve retornar false para CPF com todos os dígitos iguais")
    void shouldReturnFalseForRepeatedDigitsCpf() {
        String repeatedCpf = "11111111111";
        boolean result = documentValidationService.isValid(repeatedCpf);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve retornar false para CPF nulo")
    void shouldReturnFalseForNullCpf() {
        String nullCpf = null;
        boolean result = documentValidationService.isValid(nullCpf);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve retornar false para CPF com caracteres não numéricos")
    void shouldReturnFalseForNonNumericCpf() {
        String cpfComLetra = "abc45678901";
        boolean result = documentValidationService.isValid(cpfComLetra);
        assertThat(result).isFalse();
    }
}