package com.avis.rest_controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.avis.models.Prenotazione;
import com.avis.services.DateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class PrenotazioneController{

    @Autowired
    DateService dateService;
    

    //@GetMapping("/prenota")
    //public String searchSede(){
    //    return dateService.returnList().toString();
    //} 


    @GetMapping("/prenota")
    public List<Prenotazione> searchSede(){
        return dateService.returnList();
    } 

}
