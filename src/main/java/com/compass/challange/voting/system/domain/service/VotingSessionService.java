package com.compass.challange.voting.system.domain.service;

import com.compass.challange.voting.system.api.dto.input.VotingSessionInput;
import com.compass.challange.voting.system.api.dto.output.VotingSessionOutput;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.model.VotingSession;

public interface VotingSessionService {

    VotingSessionOutput open(VotingSessionInput input);

    VotingSession findSessionForPauta(Pauta pauta);

    boolean isSessaoOn(VotingSession session);

}
