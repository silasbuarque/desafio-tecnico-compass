package com.compass.challange.voting.system.api.controller;

import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PautaControllerTest {

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private PautaController pautaController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAllPautas_shouldReturnListOfPautas() {
        PautaDTO pauta1 = new PautaDTO();
        PautaDTO pauta2 = new PautaDTO();
        when(pautaService.getPautas()).thenReturn(Arrays.asList(pauta1, pauta2));

        List<PautaDTO> result = pautaController.getAllPautas();

        assertThat(result).hasSize(2);
        verify(pautaService).getPautas();
    }

    @Test
    void getPauta_shouldReturnPautaById() {
        Long id = 1L;
        PautaDTO expected = new PautaDTO();
        expected.setId(id);

        when(pautaService.getPauta(id)).thenReturn(expected);

        PautaDTO result = pautaController.getPauta(id);

        assertThat(result.getId()).isEqualTo(id);
        verify(pautaService).getPauta(id);
    }

    @Test
    void addPauta_shouldReturnCreatedPauta() {
        PautaInput input = new PautaInput();
        input.setTitle("Nova Pauta");

        PautaDTO created = new PautaDTO();
        created.setId(1L);
        created.setTitle("Nova Pauta");

        when(pautaService.addPauta(input)).thenReturn(created);

        PautaDTO result = pautaController.addPauta(input);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Nova Pauta");
        verify(pautaService).addPauta(input);
    }

    @Test
    void addPauta_shouldThrowBusinessExceptionOnError() {
        PautaInput input = new PautaInput();
        when(pautaService.addPauta(input)).thenThrow(new RuntimeException("Erro interno"));

        assertThatThrownBy(() -> pautaController.addPauta(input))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Erro interno");
    }

    @Test
    void updatePauta_shouldReturnUpdatedPauta() {
        Long id = 1L;
        PautaInput input = new PautaInput();
        input.setTitle("Atualizado");

        PautaDTO updated = new PautaDTO();
        updated.setId(id);
        updated.setTitle("Atualizado");

        when(pautaService.updatePauta(input, id)).thenReturn(updated);

        PautaDTO result = pautaController.updatePauta(input, id);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Atualizado");
        verify(pautaService).updatePauta(input, id);
    }

    @Test
    void updatePauta_shouldThrowBusinessExceptionOnError() {
        Long id = 1L;
        PautaInput input = new PautaInput();

        when(pautaService.updatePauta(input, id)).thenThrow(new RuntimeException("Falha na atualização"));

        assertThatThrownBy(() -> pautaController.updatePauta(input, id))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Falha na atualização");
    }

    @Test
    void deletePauta_shouldInvokeServiceDeleteMethod() {
        Long id = 1L;

        pautaController.deletePauta(id);

        verify(pautaService).deletePauta(id);
    }
}