package com.avis.rest_controller;

import java.util.List;
import java.util.Set;

import com.avis.models.Prenotazione;
import com.avis.services.DateService;
import com.avis.services.SedeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class PrenotazioneController{

    @Autowired
    private DateService dateService;
    @Autowired
    private SedeService sedeService;

    @PostMapping("/prenota")
    public List<Prenotazione> searchSede(@RequestBody String sede){
        System.out.println(sede);
        return dateService.findBySedeAvis(sede);
    } 

    @PostMapping("/prenota/data")
    public boolean prenotaData(@RequestBody int id){
        dateService.prenotaData(id);
        return true;
    }

    @PostMapping("/getRegioni")
    public Set<String> searchRegioni(){
        return sedeService.retrieveAllRegions();
    }

    @PostMapping("/getProvincie")
    public Set<String> searchProvincie(@RequestBody String regione){
        return sedeService.retrieveAllProvincie(regione);
    }

    @PostMapping("/getComuni")
    public Set<String> searchComuni(@RequestBody String provincia){
        return sedeService.retrieveAllProvincie(provincia);
    }



}
