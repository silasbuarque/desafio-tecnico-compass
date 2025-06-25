package com.compass.challange.voting.system.service.impl;

import com.compass.challange.voting.system.client.CpfValidationClient;
import com.compass.challange.voting.system.service.CpfValidationService;
import com.compass.challange.voting.system.util.CpfValidation;
import org.springframework.stereotype.Service;

@Service
public class CpfValidationServiceImpl implements CpfValidationService {

    private final CpfValidationClient cpfValidationClient;

    public CpfValidationServiceImpl(CpfValidationClient cpfValidationClient) {
        this.cpfValidationClient = cpfValidationClient;
    }

    @Override
    public CpfValidation valiate(String document) {
        return cpfValidationClient.validate(document);
    }
}
