package com.moc.utils;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ResponseLogin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ResponseOK extends ApiResponse{

    public ResponseOK(String message){
        this.setMessage(message);
        this.setHttpStatus(HttpStatus.OK);
    }
}