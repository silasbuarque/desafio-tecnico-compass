package com.compass.challange.voting.system.service.impl;

import com.compass.challange.voting.system.client.CpfValidationClient;
import com.compass.challange.voting.system.domain.service.impl.CpfValidationServiceImpl;
import com.compass.challange.voting.system.util.CpfValidationDTO;
import com.compass.challange.voting.system.util.DocumentValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CpfValidationDTOServiceImplTest {

    private CpfValidationClient cpfValidationClient;
    private DocumentValidationService documentValidationService;
    private CpfValidationServiceImpl cpfValidationService;

    @BeforeEach
    void setUp() {
        cpfValidationClient = Mockito.mock(CpfValidationClient.class);
        documentValidationService = Mockito.mock(DocumentValidationService.class);
        cpfValidationService = new CpfValidationServiceImpl(cpfValidationClient, documentValidationService);
    }

    @Test
    void valiate_shouldReturnCpfValidation_whenClientReturnsResult() {
        String cpf = "12345678901";
        CpfValidationDTO expected = new CpfValidationDTO();
        expected.setStatus("ABLE_TO_VOTE");

        when(cpfValidationClient.validate(cpf)).thenReturn(expected);

        CpfValidationDTO result = cpfValidationService.valiate(cpf);

        assertThat(result).isNotNull();

        verify(cpfValidationClient, times(1)).validate(cpf);
    }

    @Test
    void valiate_shouldThrowException_whenClientThrowsException() {
        String cpf = "invalidcpf";
        when(cpfValidationClient.validate(cpf)).thenThrow(new RuntimeException("Erro no client"));

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            cpfValidationService.valiate(cpf);
        });

        verify(cpfValidationClient, times(1)).validate(cpf);
    }
}