package com.kesmarki.task.controller.advice;

import com.kesmarki.task.exception.ErrorMessage;
import com.kesmarki.task.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class ControllerAdviceHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        log.error(entityNotFoundException.getLocalizedMessage());
        return ResponseEntity.status(NOT_FOUND).body(new ErrorMessage("The requested entity was not found"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        log.error(httpMessageNotReadableException.getLocalizedMessage());
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage("Invalid JSON payload"));
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleValidationException(ValidationException validationException) {
        log.error(validationException.getLocalizedMessage());
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage(validationException.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException badRequestException) {
        log.error(badRequestException.getLocalizedMessage());
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage("Bad request"));
    }
}
