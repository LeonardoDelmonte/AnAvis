package com.moc.utils;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ApiResponse
 * 
 * @param <T>
 */
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class ApiResponse implements InterfaceApi {

    private String message;
    private HttpStatus httpStatus;
    //la data mi da problemi con i test dei controller, quando ho finito la devo scommentare //TO DO//
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

}

