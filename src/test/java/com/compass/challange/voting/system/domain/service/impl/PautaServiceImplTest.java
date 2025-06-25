package com.compass.challange.voting.system.domain.service.impl;

import com.compass.challange.voting.system.api.assembler.PautaMapper;
import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.repository.PautaRepository;
import com.compass.challange.voting.system.exception.EntityInUse;
import com.compass.challange.voting.system.exception.PautaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PautaServiceImplTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPautas_shouldReturnMappedList() {
        List<Pauta> pautaList = Arrays.asList(new Pauta(), new Pauta());
        List<PautaDTO> pautaDTOList = Arrays.asList(new PautaDTO(), new PautaDTO());

        when(pautaRepository.findAll()).thenReturn(pautaList);
        when(pautaMapper.toCollectionModel(pautaList)).thenReturn(pautaDTOList);

        List<PautaDTO> result = pautaService.getPautas();

        assertThat(result).isEqualTo(pautaDTOList);
        verify(pautaRepository).findAll();
        verify(pautaMapper).toCollectionModel(pautaList);
    }

    @Test
    void getPauta_existingId_shouldReturnPautaDTO() {
        Long id = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(id);
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(id);

        when(pautaRepository.findById(id)).thenReturn(Optional.of(pauta));
        when(pautaMapper.toModel(pauta)).thenReturn(pautaDTO);

        PautaDTO result = pautaService.getPauta(id);

        assertThat(result).isEqualTo(pautaDTO);
        verify(pautaRepository).findById(id);
        verify(pautaMapper).toModel(pauta);
    }

    @Test
    void getPauta_nonExistingId_shouldThrowPautaNotFoundException() {
        Long id = 999L;
        when(pautaRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pautaService.getPauta(id))
                .isInstanceOf(PautaNotFoundException.class)
                .hasMessageContaining(id.toString());

        verify(pautaRepository).findById(id);
        verifyNoMoreInteractions(pautaMapper);
    }

    @Test
    void addPauta_shouldSaveAndReturnPautaDTO() {
        PautaInput input = new PautaInput();
        Pauta pautaEntity = new Pauta();
        Pauta pautaSaved = new Pauta();
        pautaSaved.setId(1L);
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(1L);

        when(pautaMapper.inputToEntity(input)).thenReturn(pautaEntity);
        when(pautaRepository.save(pautaEntity)).thenReturn(pautaSaved);
        when(pautaMapper.toModel(pautaSaved)).thenReturn(pautaDTO);

        PautaDTO result = pautaService.addPauta(input);

        assertThat(result).isEqualTo(pautaDTO);
        verify(pautaMapper).inputToEntity(input);
        verify(pautaRepository).save(pautaEntity);
        verify(pautaMapper).toModel(pautaSaved);
    }

    @Test
    void updatePauta_existingId_shouldCopyAndReturnPautaDTO() {
        Long id = 1L;
        PautaInput input = new PautaInput();
        Pauta pautaEntity = new Pauta();
        pautaEntity.setId(id);
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(id);

        when(pautaRepository.findById(id)).thenReturn(Optional.of(pautaEntity));
        doAnswer(invocation -> {
            return null;
        }).when(pautaMapper).copyToDomainObject(input, pautaEntity);

        when(pautaMapper.toModel(pautaEntity)).thenReturn(pautaDTO);

        PautaDTO result = pautaService.updatePauta(input, id);

        assertThat(result).isEqualTo(pautaDTO);
        verify(pautaRepository).findById(id);
        verify(pautaMapper).copyToDomainObject(input, pautaEntity);
        verify(pautaMapper).toModel(pautaEntity);
    }

    @Test
    void updatePauta_nonExistingId_shouldThrowPautaNotFoundException() {
        Long id = 999L;
        PautaInput input = new PautaInput();

        when(pautaRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pautaService.updatePauta(input, id))
                .isInstanceOf(PautaNotFoundException.class)
                .hasMessageContaining(id.toString());

        verify(pautaRepository).findById(id);
        verifyNoMoreInteractions(pautaMapper);
    }

    @Test
    void deletePauta_existingId_shouldCallRepositoryDelete() {
        Long id = 1L;

        doNothing().when(pautaRepository).deleteById(id);
        doNothing().when(pautaRepository).flush();

        pautaService.deletePauta(id);

        verify(pautaRepository).deleteById(id);
        verify(pautaRepository).flush();
    }

    @Test
    void deletePauta_nonExistingId_shouldThrowPautaNotFoundException() {
        Long id = 999L;

        doThrow(new EmptyResultDataAccessException(1)).when(pautaRepository).deleteById(id);

        assertThatThrownBy(() -> pautaService.deletePauta(id))
                .isInstanceOf(PautaNotFoundException.class)
                .hasMessageContaining(id.toString());

        verify(pautaRepository).deleteById(id);
        verify(pautaRepository, never()).flush();
    }

    @Test
    void deletePauta_dataIntegrityViolation_shouldThrowEntityInUse() {
        Long id = 1L;

        doThrow(new DataIntegrityViolationException("Integrity violation")).when(pautaRepository).deleteById(id);

        assertThatThrownBy(() -> pautaService.deletePauta(id))
                .isInstanceOf(EntityInUse.class)
                .hasMessageContaining("The agenda with id " + id + " cannot be removed");

        verify(pautaRepository).deleteById(id);
        verify(pautaRepository, never()).flush();
    }
}