package com.compass.challange.voting.system.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VotingEnumTest {

    @Test
    void shouldContainExpectedValues() {
        VotingEnum[] values = VotingEnum.values();

        assertThat(values).containsExactly(VotingEnum.YES, VotingEnum.NO);
    }

    @Test
    void valueOf_shouldReturnCorrectEnum() {
        assertThat(VotingEnum.valueOf("YES")).isEqualTo(VotingEnum.YES);
        assertThat(VotingEnum.valueOf("NO")).isEqualTo(VotingEnum.NO);
    }
}