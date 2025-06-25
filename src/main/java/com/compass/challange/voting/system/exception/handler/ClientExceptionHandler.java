package com.compass.challange.voting.system.exception.handler;

import com.compass.challange.voting.system.exception.CpfValidationException;
import com.compass.challange.voting.system.util.ProblemDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(CpfValidationException.class)
    public ResponseEntity<ProblemDTO> handleCpfValidation(CpfValidationException ex, HttpServletRequest request) {
        ProblemDTO problem = ProblemDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(OffsetDateTime.now())
                .type(request.getRequestURI())
                .title("Erro ao validar CPF")
                .detail(ex.getMessage())
                .userMessage("Não foi possível validar o CPF fornecido. Verifique e tente novamente.")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ProblemDTO problem = ProblemDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(OffsetDateTime.now())
                .type(request.getRequestURI())
                .title("Erro interno no servidor")
                .detail(ex.getMessage())
                .userMessage("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }
}
