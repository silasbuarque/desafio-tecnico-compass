package com.compass.challange.voting.system.util;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CpfValidationTest {

    @Test
    void shouldSetAndGetStatus() {
        CpfValidation cpfValidation = new CpfValidation();
        String expectedStatus = "VALID";

        cpfValidation.setStatus(expectedStatus);

        assertThat(cpfValidation.getStatus()).isEqualTo(expectedStatus);
    }
}