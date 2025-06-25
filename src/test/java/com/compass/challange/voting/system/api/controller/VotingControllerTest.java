package com.compass.challange.voting.system.api.controller;

import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.api.dto.output.VotingResultDTO;
import com.compass.challange.voting.system.domain.model.VotingEnum;
import com.compass.challange.voting.system.domain.service.VotingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class VotingControllerTest {

    @InjectMocks
    private VotingController votingController;

    @Mock
    private VotingService votingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void voting_shouldCallVotingServiceWithInput() {
        VotingInput input = new VotingInput();
        input.setAssociateDocument("12345678900");
        input.setVotingType(VotingEnum.YES);
        input.setPautaId(1L);

        votingController.voting(input);

        verify(votingService).votar(input);
    }

    @Test
    void resultadoPorPauta_shouldReturnVotingResultDTO() {
        Long pautaId = 1L;
        VotingResultDTO expectedResult = new VotingResultDTO(1L, "Pauta test", 10L, 5L);

        when(votingService.result(pautaId)).thenReturn(expectedResult);

        VotingResultDTO result = votingController.resultadoPorPauta(pautaId);

        assertThat(result).isNotNull();
        assertThat(result.getTotalYes()).isEqualTo(10);
        assertThat(result.getTotalNo()).isEqualTo(5);

        verify(votingService).result(pautaId);
    }
}