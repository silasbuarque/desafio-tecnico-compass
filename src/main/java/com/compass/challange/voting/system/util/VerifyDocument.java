package com.compass.challange.voting.system.util;

import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.domain.repository.VotingRepository;
import com.compass.challange.voting.system.domain.service.CpfValidationService;
import org.springframework.stereotype.Service;

@Service
public class VerifyDocument {

    public static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final VotingRepository votingRepository;
    private final CpfValidationService cpfValidationService;

    public VerifyDocument(VotingRepository votingRepository, CpfValidationService cpfValidationService) {
        this.votingRepository = votingRepository;
        this.cpfValidationService = cpfValidationService;
    }

    public boolean isAssociate(VotingInput input) {
        return votingRepository.existsByAssociateDocumentAndPautaId(input.getAssociateDocument(), input.getPautaId());
    }

    /**
     * Api de validação não está funcionando.
     * @param document
     * @return
     */
//    public boolean validate(String document) {
//        CpfValidation valiate = cpfValidationService.valiate(document);
//        return valiate.getStatus().equals(ABLE_TO_VOTE);
//    }

     public boolean validate() {
        return true;
     }

}
