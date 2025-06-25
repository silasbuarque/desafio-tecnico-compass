package com.compass.challange.voting.system.api.assembler;

import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.domain.model.Pauta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PautaMapperTest {

    private ModelMapper modelMapper;
    private PautaMapper pautaMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        pautaMapper = new PautaMapper(modelMapper);
    }

    @Test
    void shouldConvertPautaToPautaDTO() {
        Pauta pauta = new Pauta();
        PautaDTO expectedDto = new PautaDTO();

        when(modelMapper.map(pauta, PautaDTO.class)).thenReturn(expectedDto);

        PautaDTO result = pautaMapper.toModel(pauta);

        assertEquals(expectedDto, result);
        verify(modelMapper).map(pauta, PautaDTO.class);
    }

    @Test
    void shouldConvertListOfPautaToListOfPautaDTO() {
        Pauta pauta1 = new Pauta();
        Pauta pauta2 = new Pauta();

        PautaDTO dto1 = new PautaDTO();
        PautaDTO dto2 = new PautaDTO();

        when(modelMapper.map(pauta1, PautaDTO.class)).thenReturn(dto1);
        when(modelMapper.map(pauta2, PautaDTO.class)).thenReturn(dto2);

        List<PautaDTO> result = pautaMapper.toCollectionModel(Arrays.asList(pauta1, pauta2));

        assertEquals(2, result.size());
        assertFalse(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void shouldConvertPautaInputToPautaEntity() {
        PautaInput pautaInput = new PautaInput();
        Pauta expectedEntity = new Pauta();

        when(modelMapper.map(pautaInput, Pauta.class)).thenReturn(expectedEntity);

        Pauta result = pautaMapper.toEntity(pautaInput);

        assertEquals(expectedEntity, result);
        verify(modelMapper).map(pautaInput, Pauta.class);
    }

    @Test
    void shouldCopyToDomainObject() {
        PautaInput pautaInput = new PautaInput();
        Pauta pauta = new Pauta();

        pautaMapper.copyToDomainObject(pautaInput, pauta);

        verify(modelMapper).map(pautaInput, pauta);
    }
}