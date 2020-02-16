package com.moc.utils;

import com.moc.security.UtenteCorrente;

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
public class ResponseLogin extends ApiResponse{

    private String token;
    private UtenteCorrente utenteCorrente;


    //this.setMessage("ok");
    //this.setHttpStatus(HttpStatus.OK);   

    public ResponseLogin(String token){
        this.token=token;
        this.setMessage("ok");
        this.setHttpStatus(HttpStatus.CREATED);   
 
    }
}