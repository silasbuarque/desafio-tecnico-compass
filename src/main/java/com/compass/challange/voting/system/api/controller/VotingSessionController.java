package com.compass.challange.voting.system.api.controller;

import com.compass.challange.voting.system.api.dto.input.VotingSessionInput;
import com.compass.challange.voting.system.api.dto.output.VotingSessionOutput;
import com.compass.challange.voting.system.domain.service.VotingSessionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting/session")
public class VotingSessionController {

    private final VotingSessionService service;

    public VotingSessionController(VotingSessionService service) {
        this.service = service;
    }

    @PostMapping("/open")
    public VotingSessionOutput abrirSessao(@RequestBody @Valid VotingSessionInput input) {
        return service.open(input);
    }
}


