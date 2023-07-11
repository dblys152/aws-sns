package com.ys.secondbenefit.adapter.in;

import com.ys.secondbenefit.adapter.in.model.ApiErrorResponse;
import com.ys.secondbenefit.application.exception.OrderNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.webjars.NotFoundException;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleParameterException(Exception ex) {
        log.error(String.format("Request parameters exception message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(String.format("MethodArgumentNotValidException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity handleInvalidParameterException(InvalidParameterException ex) {
        log.error(String.format("InvalidParameterException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException ex) {
        log.error(String.format("NoSuchElementException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException ex) {
        log.error(String.format("NotFoundException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity handleOrderNotFoundException(OrderNotFoundException ex) {
        log.error(String.format("OrderNotFoundException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(String.format("ConstraintViolationException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException ex) {
        log.error(String.format("NullPointerException message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        log.error(String.format("Exception message : %s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiErrorResponse(ex.getMessage()));
    }
}
