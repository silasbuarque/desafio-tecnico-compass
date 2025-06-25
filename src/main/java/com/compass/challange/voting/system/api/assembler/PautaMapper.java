package com.compass.challange.voting.system.api.assembler;

import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.domain.model.Pauta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaMapper {

    private final ModelMapper modelMapper;

    public PautaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PautaDTO toModel(Pauta pauta) {
        return modelMapper.map(pauta, PautaDTO.class);
    }

    public List<PautaDTO> toCollectionModel(List<Pauta> pautaList) {
        return pautaList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Pauta toEntity(PautaInput pauta) {
        return modelMapper.map(pauta, Pauta.class);
    }

    public void copyToDomainObject(PautaInput pautaInput, Pauta pauta) {
        modelMapper.map(pautaInput, pauta);
    }

}
