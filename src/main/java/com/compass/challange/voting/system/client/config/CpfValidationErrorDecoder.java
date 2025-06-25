package com.compass.challange.voting.system.client.config;

import com.compass.challange.voting.system.exception.CpfValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CpfValidationErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 404) {
            return new CpfValidationException("CPF não encontrado.");
        } else if (response.status() == 500) {
            return new CpfValidationException("Erro interno no servidor externo.");
        }

        return new RuntimeException("Erro inesperado: " + response.status());
    }

}
