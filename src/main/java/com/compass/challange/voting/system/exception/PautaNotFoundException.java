package com.compass.challange.voting.system.exception;

public class PautaNotFoundException extends EntityNotFoundException {

    public PautaNotFoundException(String message) {
        super(message);
    }

    public PautaNotFoundException(Long id) {
        super(String.format("Pauta %s not found", id));
    }

}
