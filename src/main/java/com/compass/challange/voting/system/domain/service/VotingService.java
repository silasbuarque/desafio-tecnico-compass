package com.compass.challange.voting.system.domain.service;

import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.api.dto.output.VotingResultDTO;
import org.springframework.stereotype.Service;

@Service
public interface VotingService {

    void votar(VotingInput input);
    VotingResultDTO result(Long id);

}
