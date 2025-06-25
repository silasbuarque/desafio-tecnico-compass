package com.compass.challange.voting.system.api.controller;

import com.compass.challange.voting.system.api.dto.input.VotingInput;
import com.compass.challange.voting.system.api.dto.output.VotingResultDTO;
import com.compass.challange.voting.system.domain.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void voting(@RequestBody @Valid VotingInput input) {
        votingService.votar(input);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VotingResultDTO resultadoPorPauta(@PathVariable Long id) {
        return votingService.result(id);
    }

}
