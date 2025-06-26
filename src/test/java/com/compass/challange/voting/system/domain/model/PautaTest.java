package com.compass.challange.voting.system.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PautaTest {

    @Test
    void gettersAndSetters_shouldWorkProperly() {
        Pauta pauta = new Pauta();

        Long id = 1L;
        String title = "Título da Pauta";
        String description = "Descrição da pauta";

        pauta.setId(id);
        pauta.setTitle(title);
        pauta.setDescription(description);

        assertThat(pauta.getId()).isEqualTo(id);
        assertThat(pauta.getTitle()).isEqualTo(title);
        assertThat(pauta.getDescription()).isEqualTo(description);
    }

    @Test
    void equalsAndHashCode_shouldUseOnlyId() {
        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);

        Pauta pauta2 = new Pauta();
        pauta2.setId(1L);

        Pauta pauta3 = new Pauta();
        pauta3.setId(2L);

        Pauta pautaNull = new Pauta();

        Pauta pautaNull2 = new Pauta();

        assertThat(pauta1).isEqualTo(pauta2);
        assertThat(pauta1.hashCode()).isEqualTo(pauta2.hashCode());
        assertThat(pauta1).isNotEqualTo(pauta3);
        assertThat(pauta1).isNotEqualTo(pautaNull);
        assertThat(pautaNull).isNotEqualTo(pauta1);
        assertThat(pautaNull).isEqualTo(pautaNull2);
    }
}