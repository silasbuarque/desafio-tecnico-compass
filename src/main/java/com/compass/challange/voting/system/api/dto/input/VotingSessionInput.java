package com.compass.challange.voting.system.api.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotingSessionInput {

    @NotNull
    private Long pautaId;

    @Min(1)
    private Integer durationInMinutes;
}
