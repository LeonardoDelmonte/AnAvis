package com.moc.controllers.gestione_api_esterne;

import com.moc.utils.InterfaceApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface GestioneApiInterface {

    @GetMapping(value = "/public/ottieni-comuni")
    public ResponseEntity<InterfaceApi> ottieniComuni();

}
