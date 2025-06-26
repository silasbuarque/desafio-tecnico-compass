package com.compass.challange.voting.system.util;

import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.domain.repository.VotingRepository;
import com.compass.challange.voting.system.domain.service.CpfValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VerifyDocumentTest {

    @Mock
    private VotingRepository votingRepository;

    @Mock
    private CpfValidationService cpfValidationService;

    @Mock
    private DocumentValidationService documentValidationService;

    private VerifyDocument verifyDocument;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        verifyDocument = new VerifyDocument(votingRepository, cpfValidationService, documentValidationService);
    }

    @Test
    void isAssociate_shouldReturnTrue_whenAssociateHasAlreadyVoted() {
        VotingInput input = new VotingInput();
        input.setAssociateDocument("12345678900");
        input.setPautaId(1L);

        when(votingRepository.existsByAssociateDocumentAndPautaId("12345678900", 1L)).thenReturn(true);

        boolean result = verifyDocument.isAssociate(input);

        assertThat(result).isTrue();
        verify(votingRepository).existsByAssociateDocumentAndPautaId("12345678900", 1L);
    }

    @Test
    void isAssociate_shouldReturnFalse_whenAssociateHasNotVoted() {
        VotingInput input = new VotingInput();
        input.setAssociateDocument("12345678900");
        input.setPautaId(2L);

        when(votingRepository.existsByAssociateDocumentAndPautaId("12345678900", 2L)).thenReturn(false);

        boolean result = verifyDocument.isAssociate(input);

        assertThat(result).isFalse();
        verify(votingRepository).existsByAssociateDocumentAndPautaId("12345678900", 2L);
    }

    @Test
    void validate_shouldAlwaysReturnTrue() {
        when(documentValidationService.isValid("12345678909")).thenReturn(true);

        boolean result = verifyDocument.isValid("12345678909");
        assertThat(result).isTrue();
    }
}
