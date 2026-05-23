package com.desafio_fullstack.projedata.domain.exceptions;

public class AlredyExistsException extends RuntimeException {
    public AlredyExistsException(String message) {
        super(message);
    }

}
