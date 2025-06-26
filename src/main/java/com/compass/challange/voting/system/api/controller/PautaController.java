package com.compass.challange.voting.system.api.controller;

import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.exception.BusinessException;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    private static final Logger logger = LogManager.getLogger(PautaController.class);
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PautaDTO> getAllPautas() {
        logger.info("Fetching all pautas");
        return pautaService.getPautas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PautaDTO getPauta(@PathVariable Long id) {
        logger.info("Fetching pauta by ID: {}", id);
        return pautaService.getPauta(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaDTO addPauta(@RequestBody @Valid PautaInput pautaInput) {
        try {
            PautaDTO pauta = pautaService.addPauta(pautaInput);
            logger.info("Pauta created successfully with ID: {}", pauta.getId());
            return pauta;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus
    public PautaDTO updatePauta(@RequestBody @Valid PautaInput pauta, @PathVariable Long id) {
        logger.info("Received request to update pauta with ID: {} and name: {}", id, pauta.getTitle());
        try {
            PautaDTO pautaUpdate = pautaService.updatePauta(pauta, id);
            logger.info("Pauta updated successfully with ID: {}", pautaUpdate.getId());
            return pautaUpdate;
        } catch (Exception e) {
            logger.error("Error updating pauta ID '{}': {}", id, e.getMessage(), e);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePauta(@PathVariable Long id) {
        logger.info("Deleting pauta with ID: {}", id);
        pautaService.deletePauta(id);
    }
}
