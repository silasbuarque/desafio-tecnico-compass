package com.compass.challange.voting.system.util;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProblemDTOTest {

    @Test
    void shouldBuildProblemExceptionCorrectly() {
        OffsetDateTime now = OffsetDateTime.now();

        ProblemDTO.Object objectDetail = ProblemDTO.Object.builder()
                .name("cpf")
                .userMessage("CPF inválido")
                .build();

        ProblemDTO problem = ProblemDTO.builder()
                .status(400)
                .timestamp(now)
                .type("https://api.exemplo.com/errors/invalid-cpf")
                .title("Erro de Validação")
                .detail("CPF não encontrado")
                .userMessage("Por favor, verifique o CPF informado.")
                .objects(List.of(objectDetail))
                .build();

        assertThat(problem.getStatus()).isEqualTo(400);
        assertThat(problem.getTimestamp()).isEqualTo(now);
        assertThat(problem.getType()).isEqualTo("https://api.exemplo.com/errors/invalid-cpf");
        assertThat(problem.getTitle()).isEqualTo("Erro de Validação");
        assertThat(problem.getDetail()).isEqualTo("CPF não encontrado");
        assertThat(problem.getUserMessage()).isEqualTo("Por favor, verifique o CPF informado.");
        assertThat(problem.getObjects()).hasSize(1);
        assertThat(problem.getObjects().getFirst().getName()).isEqualTo("cpf");
        assertThat(problem.getObjects().getFirst().getUserMessage()).isEqualTo("CPF inválido");
    }
}