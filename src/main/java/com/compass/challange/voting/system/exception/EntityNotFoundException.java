package com.compass.challange.voting.system.exception;

public abstract class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
