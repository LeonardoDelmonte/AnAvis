package com.moc.utils;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError implements InterfaceApi{

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
 
    private ApiError() {
        //timestamp = LocalDateTime.now();
    }
 
    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }
 
    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = ex.getLocalizedMessage();
    }

 }