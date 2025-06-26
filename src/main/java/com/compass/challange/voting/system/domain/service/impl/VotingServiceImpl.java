package com.compass.challange.voting.system.domain.service.impl;

import com.compass.challange.voting.system.api.assembler.PautaMapper;
import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.api.dto.output.VotingResultDTO;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.model.Voting;
import com.compass.challange.voting.system.domain.model.VotingSession;
import com.compass.challange.voting.system.domain.repository.VotingRepository;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.domain.service.VotingService;
import com.compass.challange.voting.system.domain.service.VotingSessionService;
import com.compass.challange.voting.system.exception.BusinessException;
import com.compass.challange.voting.system.exception.CpfValidationException;
import com.compass.challange.voting.system.exception.PautaNotFoundException;
import com.compass.challange.voting.system.util.VerifyDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;
    private final PautaService pautaService;
    private final PautaMapper pautaMapper;
    private final VerifyDocument verifyDocument;
    private final VotingSessionService votingSessionService;

    public VotingServiceImpl(VotingRepository votingRepository, PautaService pautaService, PautaMapper pautaMapper, VerifyDocument verifyDocument, VotingSessionService votingSessionService) {
        this.votingRepository = votingRepository;
        this.pautaService = pautaService;
        this.pautaMapper = pautaMapper;
        this.verifyDocument = verifyDocument;
        this.votingSessionService = votingSessionService;
    }


    @Override
    @Transactional
    public void votar(VotingInput input) {

        if (!verifyDocument.validate() && verifyDocument.isAssociate(input)) {
            throw new CpfValidationException("No possible voting with this document");
        }

        Pauta pauta = pautaMapper.dtoToEntity(pautaService.getPauta(input.getPautaId()));

        VotingSession session = votingSessionService.findSessionForPauta(pauta);

        if (session == null || !votingSessionService.isSessaoOn(session)) {
            throw new BusinessException("Voting session closed for this pauta.");
        }

        Voting voting = getVoting(input, pauta);

        votingRepository.save(voting);

    }

    @Override
    public VotingResultDTO result(Long id) {
        return votingRepository.getVotingResult(id)
                .orElseThrow(()-> new PautaNotFoundException(String.format("There is no agenda for the id %d", id)));
    }

    private Voting getVoting(VotingInput input, Pauta pauta) {
        Voting voting = new Voting();
        voting.setAssociateDocument(input.getAssociateDocument());
        voting.setVotingType(input.getVotingType());
        voting.setPauta(pauta);
        return voting;
    }


}
