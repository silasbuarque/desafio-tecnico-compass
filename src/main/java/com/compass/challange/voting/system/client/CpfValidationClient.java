package com.compass.challange.voting.system.client;

import com.compass.challange.voting.system.config.client.CpfValidationClientConfig;
import com.compass.challange.voting.system.util.CpfValidationDTO;
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
    CpfValidationDTO validate(@PathVariable("cpf") String cpf);

}
