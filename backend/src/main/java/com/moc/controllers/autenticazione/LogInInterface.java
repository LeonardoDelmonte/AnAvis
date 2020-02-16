package com.moc.controllers.autenticazione;

import com.moc.dto.AutenticazioneDto;
import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * LogInInterface
 */
public interface LogInInterface {

    @PostMapping(value="/public/autenticazione-utente")
    public ResponseEntity<InterfaceApi> autenticazione(@RequestBody AutenticazioneDto authDto) throws NoSuchFieldException;
    
}