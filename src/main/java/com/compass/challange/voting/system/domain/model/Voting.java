package com.compass.challange.voting.system.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private VotingEnum votingType;

    @ManyToOne
    @JoinColumn(name = "voting_pauta_id")
    private Pauta pauta;
}
