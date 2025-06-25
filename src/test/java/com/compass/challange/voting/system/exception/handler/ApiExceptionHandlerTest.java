package com.compass.challange.voting.system.exception.handler;

import com.compass.challange.voting.system.exception.BusinessException;
import com.compass.challange.voting.system.exception.PautaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ApiExceptionHandlerTest {

    private ApiExceptionHandler handler;

    @Mock
    private MessageSource messageSource;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() throws Exception {
        openMocks(this);
        handler = new ApiExceptionHandler();

        Field field = ApiExceptionHandler.class.getDeclaredField("messageSource");
        field.setAccessible(true);
        field.set(handler, messageSource);
    }

    @Test
    void shouldHandleEntityNotFoundException() {
        PautaNotFoundException exception = new PautaNotFoundException(1L);

        ResponseEntity<?> response = handler.handlerEntidadeNaoEncontradaException(exception, webRequest);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        var body = (ProblemDTO) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getTitle()).isEqualTo("Resource not found");
        assertThat(body.getStatus()).isEqualTo(404);
        assertThat(body.getDetail()).contains("1");
    }

    @Test
    void shouldHandleBusinessException() {
        String message = "Erro de negócio";
        BusinessException exception = new BusinessException(message);

        ResponseEntity<?> response = handler.handlerNegocioException(exception, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ProblemDTO body = (ProblemDTO) response.getBody();
        assertThat(body.getTitle()).isEqualTo("Regra de negocio foi violada");
        assertThat(body.getDetail()).isEqualTo(message);
    }

    @Test
    void shouldHandleMethodArgumentNotValid() {
        FieldError fieldError = new FieldError("pautaInput", "title", "must not be blank");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));
        when(messageSource.getMessage(eq(fieldError), any(Locale.class)))
                .thenReturn("Título é obrigatório");

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(null, bindingResult);

        var response = handler.handleMethodArgumentNotValid(exception, null, HttpStatus.BAD_REQUEST, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ProblemDTO body = (ProblemDTO) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getObjects()).hasSize(1);
        assertThat(body.getObjects().getFirst().getName()).isEqualTo("title");
        assertThat(body.getObjects().getFirst().getUserMessage()).isEqualTo("Título é obrigatório");
    }
}