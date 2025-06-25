package com.compass.challange.voting.system.domain.service;

import com.compass.challange.voting.system.util.CpfValidation;

public interface CpfValidationService {

    CpfValidation valiate(String document);

}
