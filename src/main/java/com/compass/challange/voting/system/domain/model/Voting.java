package com.compass.challange.voting.system.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SqlResultSetMapping(
        name = "VotingResultMapping",
        classes = @ConstructorResult(
                targetClass = com.compass.challange.voting.system.api.dto.output.VotingResultDTO.class,
                columns = {
                        @ColumnResult(name = "pautaId", type = Long.class),
                        @ColumnResult(name = "pautaTitulo", type = String.class),
                        @ColumnResult(name = "totalYes", type = Long.class),
                        @ColumnResult(name = "totalNo", type = Long.class)
                }
        )
)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "TBG_VOTING")
public class Voting {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @Column(nullable = false)
    private String associateDocument;

    @Enumerated(EnumType.STRING)
    @Column(name = "voting", nullable = false)
    private VotingEnum votingType;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;
}
