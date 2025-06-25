package com.compass.challange.voting.system.config.modelmapper;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ModelMapperConfigTest {

    private final ModelMapperConfig config = new ModelMapperConfig();

    @Test
    void modelMapper_shouldReturnModelMapperInstance() {
        ModelMapper modelMapper = config.modelMapper();

        assertThat(modelMapper).isNotNull();
        assertThat(modelMapper).isInstanceOf(ModelMapper.class);
    }
}