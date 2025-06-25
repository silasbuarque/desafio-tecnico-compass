package com.compass.challange.voting.system.domain.service.impl;

import com.compass.challange.voting.system.api.assembler.PautaMapper;
import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.api.dto.output.VotingResultDTO;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.model.Voting;
import com.compass.challange.voting.system.domain.model.VotingEnum;
import com.compass.challange.voting.system.domain.repository.VotingRepository;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.exception.CpfValidationException;
import com.compass.challange.voting.system.exception.PautaNotFoundException;
import com.compass.challange.voting.system.util.VerifyDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class VotingServiceImplTest {

    @InjectMocks
    private VotingServiceImpl votingService;

    @Mock
    private VotingRepository votingRepository;

    @Mock
    private PautaService pautaService;

    @Mock
    private PautaMapper pautaMapper;

    @Mock
    private VerifyDocument verifyDocument;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void votar_shouldSaveVote_whenDocumentIsValid() {
        VotingInput input = new VotingInput();
        input.setAssociateDocument("12345678900");
        input.setVotingType(VotingEnum.YES);
        input.setPautaId(1L);

        PautaDTO pautaDto = new PautaDTO();
        pautaDto.setId(1L);
        pautaDto.setTitle("Título");
        pautaDto.setDescription("Descrição");

        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitle("Título");
        pauta.setDescription("Descrição");

        when(verifyDocument.validate()).thenReturn(true);
        when(verifyDocument.isAssociate(input)).thenReturn(true);
        when(pautaService.getPauta(1L)).thenReturn(pautaDto);
        when(pautaMapper.dtoToEntity(pautaDto)).thenReturn(pauta);

        votingService.votar(input);

        verify(votingRepository).save(any(Voting.class));
    }

    @Test
    void votar_shouldThrowException_whenDocumentInvalid() {
        VotingInput input = new VotingInput();
        input.setAssociateDocument("00000000000");
        input.setPautaId(1L);

        when(verifyDocument.validate()).thenReturn(false);
        when(verifyDocument.isAssociate(input)).thenReturn(true);

        assertThatThrownBy(() -> votingService.votar(input))
                .isInstanceOf(CpfValidationException.class)
                .hasMessageContaining("No possible voting with this document");

        verifyNoInteractions(votingRepository);
    }

    @Test
    void result_shouldReturnVotingResultDTO_whenExists() {
        Long pautaId = 1L;
        VotingResultDTO resultDTO = new VotingResultDTO(pautaId, "Título", 10L, 5L);

        when(votingRepository.getVotingResult(pautaId)).thenReturn(Optional.of(resultDTO));

        VotingResultDTO result = votingService.result(pautaId);

        assertThat(result).isNotNull();
        assertThat(result.getPautaId()).isEqualTo(pautaId);
        assertThat(result.getPautaTitulo()).isEqualTo("Título");
        assertThat(result.getTotalYes()).isEqualTo(10);
        assertThat(result.getTotalNo()).isEqualTo(5);
    }

    @Test
    void result_shouldThrowException_whenResultNotFound() {
        Long pautaId = 99L;
        when(votingRepository.getVotingResult(pautaId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> votingService.result(pautaId))
                .isInstanceOf(PautaNotFoundException.class)
                .hasMessageContaining("There is no agenda for the id " + pautaId);
    }
}