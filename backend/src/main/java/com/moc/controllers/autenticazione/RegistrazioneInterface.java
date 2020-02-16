package com.moc.controllers.autenticazione;

import com.moc.dto.RegistrazioneDto;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * RegistrazioneInterface
 */
public interface RegistrazioneInterface {

    @PostMapping(value="/public/registrazione-utente")
    public ResponseEntity<InterfaceApi> registrazione(@RequestBody RegistrazioneDto registrazioneDto) throws NoSuchFieldException;
    
    @PostMapping(value="/sede/registrazione-utente")
    public ResponseEntity<InterfaceApi> registrazioneDonatore(@RequestBody RegistrazioneDto registrazioneDto) throws NoSuchFieldException;

}