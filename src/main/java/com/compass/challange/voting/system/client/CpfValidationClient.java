package com.compass.challange.voting.system.client;

import com.compass.challange.voting.system.client.config.CpfValidationClientConfig;
import com.compass.challange.voting.system.util.CpfValidation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cpfClient",
        url = "${user.api.base-url}",
        configuration = CpfValidationClientConfig.class
)
public interface CpfValidationClient {

    @GetMapping("/users/{cpf}")
    CpfValidation validate(@PathVariable("cpf") String cpf);

}
