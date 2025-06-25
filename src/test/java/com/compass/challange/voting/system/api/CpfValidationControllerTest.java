package com.compass.challange.voting.system.api;


import com.compass.challange.voting.system.api.controller.CpfValidationController;
import com.compass.challange.voting.system.exception.CpfValidationException;
import com.compass.challange.voting.system.domain.service.CpfValidationService;
import com.compass.challange.voting.system.util.CpfValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CpfValidationController.class)
class CpfValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CpfValidationService cpfValidationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 200 e os dados do CPF quando válido")
    @Disabled("O serviço de validação não está funcionando.")
    void deveRetornarCpfValidado() throws Exception {

        String cpf = "27846641042";
        CpfValidation mockResponse = new CpfValidation();
        mockResponse.setStatus("ABLE_TO_VOTE");

        when(cpfValidationService.valiate(cpf)).thenReturn(mockResponse);

        mockMvc.perform(get("/validationCpf/{cpf}", cpf)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("27846641042"))
                .andExpect(jsonPath("$.valid").value(true));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o CPF não for encontrado")
    void deveRetornarErroQuandoCpfNaoEncontrado() throws Exception {

        String cpf = "00000000000";

        when(cpfValidationService.valiate(cpf)).thenThrow(new CpfValidationException("CPF não encontrado"));


        mockMvc.perform(get("/validationCpf/{cpf}", cpf)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.title").value("404 NOT_FOUND"))
                .andExpect(jsonPath("$.userMessage").value("Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema."));
    }
}