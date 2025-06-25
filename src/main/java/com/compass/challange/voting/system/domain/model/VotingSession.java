package com.compass.challange.voting.system.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "TBG_VOTING_SESSION")
public class VotingSession {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @OneToOne
    @NotNull
    @Valid
    private Pauta pauta;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime start;

    private OffsetDateTime end;

}
