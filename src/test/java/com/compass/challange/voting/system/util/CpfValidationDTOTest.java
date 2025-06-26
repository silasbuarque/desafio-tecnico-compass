package com.compass.challange.voting.system.util;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CpfValidationDTOTest {

    @Test
    void shouldSetAndGetStatus() {
        CpfValidationDTO cpfValidationDTO = new CpfValidationDTO();
        String expectedStatus = "VALID";

        cpfValidationDTO.setStatus(expectedStatus);

        assertThat(cpfValidationDTO.getStatus()).isEqualTo(expectedStatus);
    }
}