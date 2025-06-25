package com.compass.challange.voting.system.config.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CpfValidationClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CpfValidationErrorDecoder();
    }

}
