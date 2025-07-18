package com.compass.challange.voting.system.client.config;

import com.compass.challange.voting.system.config.client.CpfValidationClientConfig;
import com.compass.challange.voting.system.config.client.CpfValidationErrorDecoder;
import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CpfValidationClientConfig.class)
class CpfValidationDTOClientConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldCreateErrorDecoderBean() {
        ErrorDecoder decoder = context.getBean(ErrorDecoder.class);

        assertThat(decoder).isInstanceOf(CpfValidationErrorDecoder.class);
    }
}