package com.compass.challange.voting.system.api.dto.output;


import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class VotingSessionOutput {
    private Long pautaId;
    private OffsetDateTime start;
    private OffsetDateTime end;
    private boolean open;
}
