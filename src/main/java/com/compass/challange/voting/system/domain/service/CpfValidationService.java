package com.compass.challange.voting.system.domain.service;

import com.compass.challange.voting.system.util.CpfValidationDTO;

public interface CpfValidationService {

    CpfValidationDTO valiate(String document);

    boolean isValid(String document);

}
