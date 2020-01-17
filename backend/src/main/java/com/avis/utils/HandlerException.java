package com.avis.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.authentication.DisabledException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * JwtHandlerException
 */
@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError,apiError.getStatus()); 
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> IllegalArgumentException(IllegalArgumentException ex) {
        String error ="input null";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));   
    }
       
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> DataIntegrityViolationException(DataIntegrityViolationException ex) { 
        String error ="email già registrata";
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, error, ex));
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> BadCredentialsException(BadCredentialsException ex) {
        String error ="credenziali errate";
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, error, ex));
    }

    
    @ExceptionHandler(DisabledException.class)
    protected ResponseEntity<Object> DisabledException(DisabledException ex) {
        String error ="utente disabilitato";
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, error, ex));
    }
}