package com.compass.challange.voting.system.client.config;

import com.compass.challange.voting.system.exception.CpfValidationException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidationErrorDecoderTest {

    private CpfValidationErrorDecoder decoder;

    @BeforeEach
    void setUp() {
        decoder = new CpfValidationErrorDecoder();
    }

    private Response mockResponse(int status) {
        return Response.builder()
                .status(status)
                .reason("")
                .request(Request.create(Request.HttpMethod.GET, "/", java.util.Collections.emptyMap(), null, null, null))
                .headers(java.util.Collections.emptyMap())
                .build();
    }

    @Test
    void shouldReturnCpfValidationExceptionFor404() {
        Response response = mockResponse(404);

        Exception exception = decoder.decode("methodKey", response);

        assertInstanceOf(CpfValidationException.class, exception);
        assertEquals("CPF não encontrado.", exception.getMessage());
    }

    @Test
    void shouldReturnCpfValidationExceptionFor500() {
        Response response = mockResponse(500);

        Exception exception = decoder.decode("methodKey", response);

        assertInstanceOf(CpfValidationException.class, exception);
        assertEquals("Erro interno no servidor externo.", exception.getMessage());
    }

    @Test
    void shouldReturnRuntimeExceptionForOtherStatus() {
        Response response = mockResponse(400);

        Exception exception = decoder.decode("methodKey", response);

        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Erro inesperado: 400", exception.getMessage());
    }
}