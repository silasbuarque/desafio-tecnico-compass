package com.compass.challange.voting.system.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

class VotingSessionTest {

    @Test
    void gettersAndSetters_shouldWorkProperly() {
        VotingSession session = new VotingSession();

        Long id = 10L;
        Pauta pauta = new Pauta();
        OffsetDateTime start = OffsetDateTime.now();
        OffsetDateTime end = start.plusHours(1);

        session.setId(id);
        session.setPauta(pauta);
        session.setStart(start);
        session.setEnd(end);

        assertThat(session.getId()).isEqualTo(id);
        assertThat(session.getPauta()).isEqualTo(pauta);
        assertThat(session.getStart()).isEqualTo(start);
        assertThat(session.getEnd()).isEqualTo(end);
    }

    @Test
    void equalsAndHashCode_shouldUseOnlyId() {
        VotingSession session1 = new VotingSession();
        session1.setId(1L);

        VotingSession session2 = new VotingSession();
        session2.setId(1L);

        VotingSession session3 = new VotingSession();
        session3.setId(2L);

        VotingSession sessionNull2 = new VotingSession();

        VotingSession sessionNull = new VotingSession();
        assertThat(session1).isEqualTo(session2);
        assertThat(session1.hashCode()).isEqualTo(session2.hashCode());
        assertThat(session1).isNotEqualTo(session3);
        assertThat(session1).isNotEqualTo(sessionNull);
        assertThat(sessionNull).isNotEqualTo(session1);
        assertThat(sessionNull).isEqualTo(sessionNull2);
    }
}