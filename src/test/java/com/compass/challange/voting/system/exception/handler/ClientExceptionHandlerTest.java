package com.compass.challange.voting.system.exception.handler;

import com.compass.challange.voting.system.exception.CpfValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClientExceptionHandlerTest {

    private ClientExceptionHandler exceptionHandler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        exceptionHandler = new ClientExceptionHandler();

        request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/v1/teste");
    }

    @Test
    void handleCpfValidation_shouldReturnBadRequest_withCorrectProblemException() {
        String errorMessage = "CPF inválido";
        CpfValidationException ex = new CpfValidationException(errorMessage);

        ResponseEntity<ProblemDTO> response = exceptionHandler.handleCpfValidation(ex, request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        ProblemDTO body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getStatus()).isEqualTo(400);
        assertThat(body.getTitle()).isEqualTo("Erro ao validar CPF");
        assertThat(body.getDetail()).isEqualTo(errorMessage);
        assertThat(body.getUserMessage()).isEqualTo("Não foi possível validar o CPF fornecido. Verifique e tente novamente.");
        assertThat(body.getTimestamp()).isBeforeOrEqualTo(OffsetDateTime.now());
        assertThat(body.getType()).isEqualTo("/api/v1/teste");
    }

    @Test
    void handleGenericException_shouldReturnInternalServerError_withCorrectProblemException() {
        String errorMessage = "Erro inesperado";
        Exception ex = new Exception(errorMessage);

        ResponseEntity<ProblemDTO> response = exceptionHandler.handleGenericException(ex, request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        ProblemDTO body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getStatus()).isEqualTo(500);
        assertThat(body.getTitle()).isEqualTo("Erro interno no servidor");
        assertThat(body.getDetail()).isEqualTo(errorMessage);
        assertThat(body.getUserMessage()).isEqualTo("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        assertThat(body.getTimestamp()).isBeforeOrEqualTo(OffsetDateTime.now());
        assertThat(body.getType()).isEqualTo("/api/v1/teste");
    }
}
