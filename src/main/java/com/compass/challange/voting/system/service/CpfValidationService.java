package com.compass.challange.voting.system.service;

import com.compass.challange.voting.system.util.CpfValidation;

public interface CpfValidationService {

    CpfValidation valiate(String document);

}
