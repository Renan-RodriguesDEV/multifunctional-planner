package com.desafio_fullstack.projedata.domain.exceptions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio_fullstack.presentation.controllers.EventScheduleController;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(EventScheduleController.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        logger.error("Resource not found: {}", e.getMessage());
        return ResponseEntity.status(404)
                .body(new ErrorResponse(e.getMessage(), "Resource not found", LocalDateTime.now()));
    }

    @ExceptionHandler(AlredyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlredyExistsException e) {
        logger.error("Resource already exists: {}", e.getMessage());
        // obs: eu nn lembrava o codigo de status, logo usei o HttpStatus
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage(), "Resource already exists", LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        logger.error("An unexpected error occurred: {}", e.getMessage());
        // obs: eu nn lembrava o codigo de status, logo usei o HttpStatus
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(e.getMessage(), "An unexpected error occurred", LocalDateTime.now()));
    }

    record ErrorResponse(String message, String details, LocalDateTime timestamp) {
    }
}
