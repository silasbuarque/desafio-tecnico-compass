package com.compass.challange.voting.system.api.controller;

import com.compass.challange.voting.system.domain.service.CpfValidationService;
import com.compass.challange.voting.system.util.CpfValidationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation/document")
public class CpfValidationController {

    private static final Logger logger = LogManager.getLogger(CpfValidationController.class);
    private final CpfValidationService cpfValidationService;

    public CpfValidationController(CpfValidationService cpfValidationService) {
        this.cpfValidationService = cpfValidationService;
    }

    @GetMapping("/{document}")
    public ResponseEntity<CpfValidationDTO> validarCpf(@PathVariable String document) {
        logger.info("Start validation document: {}", document);
        CpfValidationDTO result = cpfValidationService.valiate(document);
        logger.info("Response: {}", result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/isValid/{document}")
    public ResponseEntity<Boolean> isValid(@PathVariable String document) {
        logger.info("Start simlpe validation document: {}", document);
        Boolean result = cpfValidationService.isValid(document);
        logger.info("isValid: {}", result);
        return ResponseEntity.ok(result);
    }

}
