package com.compass.challange.voting.system.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VotingTest {

    @Test
    void gettersAndSetters_shouldWorkProperly() {
        Voting voting = new Voting();

        Long id = 123L;
        String document = "12345678900";
        VotingEnum votingEnum = VotingEnum.YES;
        Pauta pauta = new Pauta();

        voting.setId(id);
        voting.setAssociateDocument(document);
        voting.setVotingType(votingEnum);
        voting.setPauta(pauta);

        assertThat(voting.getId()).isEqualTo(id);
        assertThat(voting.getAssociateDocument()).isEqualTo(document);
        assertThat(voting.getVotingType()).isEqualTo(votingEnum);
        assertThat(voting.getPauta()).isEqualTo(pauta);
    }

    @Test
    void equalsAndHashCode_shouldUseOnlyId() {
        Voting voting1 = new Voting();
        voting1.setId(1L);

        Voting voting2 = new Voting();
        voting2.setId(1L);

        Voting voting3 = new Voting();
        voting3.setId(2L);

        Voting votingNull = new Voting();
        Voting votingNull2 = new Voting();

        assertThat(voting1).isEqualTo(voting2);
        assertThat(voting1.hashCode()).isEqualTo(voting2.hashCode());
        assertThat(voting1).isNotEqualTo(voting3);
        assertThat(voting1).isNotEqualTo(votingNull);
        assertThat(votingNull).isNotEqualTo(voting1);
        assertThat(votingNull).isEqualTo(votingNull2);
    }
}