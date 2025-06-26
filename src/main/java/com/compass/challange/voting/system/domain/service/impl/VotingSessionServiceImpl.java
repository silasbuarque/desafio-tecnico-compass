package com.compass.challange.voting.system.domain.service.impl;
import com.compass.challange.voting.system.api.assembler.PautaMapper;
import com.compass.challange.voting.system.api.dto.input.VotingSessionInput;
import com.compass.challange.voting.system.api.dto.output.VotingSessionOutput;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.model.VotingSession;
import com.compass.challange.voting.system.domain.repository.VotingSessionRepository;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.domain.service.VotingSessionService;
import com.compass.challange.voting.system.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private static final Logger logger = LoggerFactory.getLogger(VotingSessionServiceImpl.class);

    private final PautaService pautaService;
    private final PautaMapper pautaMapper;
    private final VotingSessionRepository votingSessionRepository;

    public VotingSessionServiceImpl(PautaService pautaService, PautaMapper pautaMapper, VotingSessionRepository votingSessionRepository) {
        this.pautaService = pautaService;
        this.pautaMapper = pautaMapper;
        this.votingSessionRepository = votingSessionRepository;
    }

    @Transactional
    @Override
    public VotingSessionOutput open(VotingSessionInput votingSessionInput) {

        Pauta pauta = pautaMapper.dtoToEntity(pautaService.getPauta(votingSessionInput.getPautaId()));

        OffsetDateTime now = OffsetDateTime.now();
        int duracao = (votingSessionInput.getDurationInMinutes() != null) ? votingSessionInput.getDurationInMinutes() : 1;

        VotingSession session = new VotingSession();
        session.setPauta(pauta);
        session.setStart(now);
        session.setEnd(now.plusMinutes(duracao));

        votingSessionRepository.save(session);

        logger.info("Voting session opened: {}", session);

        return VotingSessionOutput.builder()
                .pautaId(pauta.getId())
                .start(session.getStart())
                .end(session.getEnd())
                .open(OffsetDateTime.now().isBefore(session.getEnd()))
                .build();
    }

    @Override
    public VotingSession findSessionForPauta(Pauta pauta) {
        return votingSessionRepository.findByPauta(pauta)
                .orElseThrow(() -> new BusinessException("Voting session not started for this pauta."));
    }

    @Override
    public boolean isSessaoOn(VotingSession session) {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime fim = session.getEnd() != null
                ? session.getEnd()
                : session.getStart().plusMinutes(1);
        return !agora.isBefore(session.getStart()) && agora.isBefore(fim);
    }
}
