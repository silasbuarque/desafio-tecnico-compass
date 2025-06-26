package com.compass.challange.voting.system.domain.service;

import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;

import java.util.List;

public interface PautaService {

    List<PautaDTO> getPautas();

    PautaDTO getPauta(Long id);

    PautaDTO addPauta(PautaInput pauta);

    PautaDTO updatePauta(PautaInput pauta, Long id);

    void deletePauta(Long id);

}
