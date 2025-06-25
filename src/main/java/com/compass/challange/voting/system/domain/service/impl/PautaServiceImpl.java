package com.compass.challange.voting.system.domain.service.impl;

import com.compass.challange.voting.system.api.assembler.PautaMapper;
import com.compass.challange.voting.system.api.dto.input.PautaInput;
import com.compass.challange.voting.system.domain.repository.PautaRepository;
import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.service.PautaService;
import com.compass.challange.voting.system.api.dto.output.PautaDTO;
import com.compass.challange.voting.system.exception.EntityInUse;
import com.compass.challange.voting.system.exception.PautaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    private static final String MSG_ENTITY_IN_USE = "The agenda with id %d cannot be removed because it is in use.";

    @Autowired
    private PautaMapper pautaMapper;

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public List<PautaDTO> getPautas() {
        return pautaMapper.toCollectionModel(pautaRepository.findAll());
    }

    @Override
    public PautaDTO getPauta(Long id) {
        Pauta pauta = findPautaById(id);
        return pautaMapper.toModel(pauta);
    }

    @Override
    @Transactional
    public PautaDTO addPauta(PautaInput pauta) {
        Pauta pautaEntity = pautaMapper.inputToEntity(pauta);
        pautaEntity = pautaRepository.save(pautaEntity);
        return pautaMapper.toModel(pautaEntity);
    }

    @Override
    public PautaDTO updatePauta(PautaInput pautaDTO, Long id) {
        Pauta pautaEntity = findPautaById(id);
        pautaMapper.copyToDomainObject(pautaDTO, pautaEntity);
        return pautaMapper.toModel(pautaEntity);
    }

    @Override
    @Transactional
    public void deletePauta(Long id) {
        try{
            pautaRepository.deleteById(id);
            pautaRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new PautaNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUse(String.format(MSG_ENTITY_IN_USE, id));
        }
    }

    private Pauta findPautaById(Long id) {
        return pautaRepository.findById(id).orElseThrow(() -> new PautaNotFoundException(id));
    }
}
