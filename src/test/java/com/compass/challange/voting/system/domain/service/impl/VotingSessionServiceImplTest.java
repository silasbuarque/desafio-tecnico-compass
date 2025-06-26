package com.compass.challange.voting.system.domain.service.impl;

import com.compass.challange.voting.system.api.assembler.PautaMapper;
import com.compass.challange.voting.system.api.dto.input.VotingSessionInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.api.dto.output.VotingSessionOutput;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.model.VotingSession;
import com.compass.challange.voting.system.domain.repository.VotingSessionRepository;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class VotingSessionServiceImplTest {

    @InjectMocks
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private PautaService pautaService;

    @Mock
    private PautaMapper pautaMapper;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void open_shouldCreateSessionWithDefaultDuration() {

        VotingSessionInput input = new VotingSessionInput();
        input.setPautaId(1L);

        PautaDTO pautaDto = new PautaDTO();
        pautaDto.setId(1L);
        pautaDto.setTitle("Título");
        pautaDto.setDescription("Descrição");

        Pauta pautaEntity = new Pauta();
        pautaEntity.setId(2L);
        pautaEntity.setTitle("Custom");
        pautaEntity.setDescription("Teste");

        when(pautaService.getPauta(1L)).thenReturn(pautaDto);
        when(pautaMapper.dtoToEntity(pautaDto)).thenReturn(pautaEntity);

        VotingSessionOutput output = votingSessionService.open(input);

        assertThat(output).isNotNull();
        assertThat(output.getPautaId()).isEqualTo(2L);
        assertThat(output.getStart()).isNotNull();
        assertThat(output.getEnd()).isAfter(output.getStart());
        verify(votingSessionRepository).save(any(VotingSession.class));
    }

    @Test
    void open_shouldCreateSessionWithCustomDuration() {
        VotingSessionInput input = new VotingSessionInput();
        input.setPautaId(2L);
        input.setDurationInMinutes(10);

        PautaDTO pauta = new PautaDTO();
        pauta.setId(2L);
        pauta.setTitle("Custom");
        pauta.setDescription("Teste");

        Pauta pautaEntity = new Pauta();
        pautaEntity.setId(2L);
        pautaEntity.setTitle("Custom");
        pautaEntity.setDescription("Teste");

        when(pautaService.getPauta(2L)).thenReturn(pauta);
        when(pautaMapper.dtoToEntity(pauta)).thenReturn(pautaEntity);

        VotingSessionOutput output = votingSessionService.open(input);

        assertThat(output.getEnd()).isEqualTo(output.getStart().plusMinutes(10));
        verify(votingSessionRepository).save(any());
    }

    @Test
    void findSessionForPauta_shouldReturnSessionIfExists() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        VotingSession session = new VotingSession();
        session.setPauta(pauta);
        session.setStart(OffsetDateTime.now());
        session.setEnd(OffsetDateTime.now().plusMinutes(1));

        when(votingSessionRepository.findByPauta(pauta)).thenReturn(Optional.of(session));

        VotingSession found = votingSessionService.findSessionForPauta(pauta);

        assertThat(found).isEqualTo(session);
    }

    @Test
    void findSessionForPauta_shouldThrowExceptionIfNotExists() {
        Pauta pauta = new Pauta();
        pauta.setId(99L);

        when(votingSessionRepository.findByPauta(pauta)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> votingSessionService.findSessionForPauta(pauta))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Voting session not started for this pauta.");
    }

    @Test
    void isSessaoOn_shouldReturnTrueIfNowIsBetweenStartAndEnd() {
        OffsetDateTime now = OffsetDateTime.now();

        VotingSession session = new VotingSession();
        session.setStart(now.minusMinutes(1));
        session.setEnd(now.plusMinutes(1));

        assertThat(votingSessionService.isSessaoOn(session)).isTrue();
    }

    @Test
    void isSessaoOn_shouldReturnFalseIfNowIsBeforeStart() {
        OffsetDateTime now = OffsetDateTime.now();

        VotingSession session = new VotingSession();
        session.setStart(now.plusMinutes(1));
        session.setEnd(now.plusMinutes(2));

        assertThat(votingSessionService.isSessaoOn(session)).isFalse();
    }

    @Test
    void isSessaoOn_shouldReturnFalseIfNowIsAfterEnd() {
        OffsetDateTime now = OffsetDateTime.now();

        VotingSession session = new VotingSession();
        session.setStart(now.minusMinutes(10));
        session.setEnd(now.minusMinutes(1));

        assertThat(votingSessionService.isSessaoOn(session)).isFalse();
    }
}