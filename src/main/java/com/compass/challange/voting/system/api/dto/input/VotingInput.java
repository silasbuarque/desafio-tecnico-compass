package com.compass.challange.voting.system.api.dto.input;

import com.compass.challange.voting.system.domain.model.VotingEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotingInput {

    @NotBlank
    private String associateDocument;

    @NotNull
    private VotingEnum votingType;

    @NotNull
    private Long pautaId;

}
