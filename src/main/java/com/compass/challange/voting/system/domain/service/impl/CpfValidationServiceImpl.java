package com.compass.challange.voting.system.domain.service.impl;

import com.compass.challange.voting.system.client.CpfValidationClient;
import com.compass.challange.voting.system.domain.service.CpfValidationService;
import com.compass.challange.voting.system.util.CpfValidationDTO;
import com.compass.challange.voting.system.util.DocumentValidationService;
import org.springframework.stereotype.Service;

@Service
public class CpfValidationServiceImpl implements CpfValidationService {

    private final CpfValidationClient cpfValidationClient;
    private final DocumentValidationService documentValidationService;

    public CpfValidationServiceImpl(CpfValidationClient cpfValidationClient, DocumentValidationService documentValidationService) {
        this.cpfValidationClient = cpfValidationClient;
        this.documentValidationService = documentValidationService;
    }

    /**
     * Desabilitando validação via client com o heroku
     * devido ao heroku está fora do ar no momento do teste
     *
     * @param document
     * @return
     */
    @Override
    public CpfValidationDTO valiate(String document) {
        return cpfValidationClient.validate(document);
    }

    @Override
    public boolean isValid(String document) {
        return documentValidationService.isValid(document);
    }


}
