package com.moc.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.authentication.BadCredentialsException;
import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

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
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,ex));   
    }
      

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<InterfaceApi> NullPointerException(NullPointerException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,ex));   
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<InterfaceApi> DataIntegrityViolationException(DataIntegrityViolationException ex) { 
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex));
    }

    
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<InterfaceApi> BadCredentialsException(BadCredentialsException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, ex));
    }


    @ExceptionHandler(NoSuchFieldException.class)
    protected ResponseEntity<InterfaceApi> NoSuchFieldException(NoSuchFieldException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<InterfaceApi> NoSuchElementException(NoSuchFieldException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE, ex));
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<InterfaceApi> AuthenticationException(AuthenticationException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }


}