package com.moc.controllers.gestione_api_esterne;

import java.util.List;

import com.moc.services.SedeAvisInterface;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseCustomEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestioneApi implements GestioneApiInterface {

    @Autowired
    SedeAvisInterface sedeAvisService;

    @Override
    public ResponseEntity<InterfaceApi> ottieniComuni() {
        List<String> list = sedeAvisService.getAllComuni();
        return new ResponseEntity<>(new ResponseCustomEntity<List<String>>(list), HttpStatus.OK);
    }

}