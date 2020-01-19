package com.avis.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * JwtHandlerException
 */
@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private ResponseEntity<InterfaceApi> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError,apiError.getStatus()); 
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<InterfaceApi> IllegalArgumentException(IllegalArgumentException ex) {
        String error ="input null";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));   
    }
       
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<InterfaceApi> NullPointerException(NullPointerException ex) {
        String error ="email non valida!";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));   
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<InterfaceApi> DataIntegrityViolationException(DataIntegrityViolationException ex) { 
        String error ="email già registrata";
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, error, ex));
    }
    
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    protected ResponseEntity<InterfaceApi> InternalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        String error ="credenziali errate";
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, error, ex));
    }

    
    @ExceptionHandler(java.util.NoSuchElementException.class)
    protected ResponseEntity<InterfaceApi> NoSuchElementException(java.util.NoSuchElementException ex) {
        String error ="valore cercato inesistente";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }


    
    @ExceptionHandler(DisabledException.class)
    protected ResponseEntity<InterfaceApi> DisabledException(DisabledException ex) {
        String error ="utente disabilitato";
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, error, ex));
    }
}