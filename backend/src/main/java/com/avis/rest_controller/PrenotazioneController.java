package com.avis.rest_controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.avis.models.Prenotazione;
import com.avis.services.PrenotazioniService;
import com.avis.services.SedeAvisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"*"})
@RestController
public class PrenotazioneController{

    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private SedeAvisService sedeAvisService;

    @PostMapping("/public/prenota")
    public Optional<Prenotazione> getDateLibere(@RequestBody String comune){
        //controlla token
        return null;//prenotazioniService.getDateLibere(comune);
    } 

    @PostMapping("/public/prenotazione")
    public ResponseEntity<List<Prenotazione>> getAlfredo(@RequestParam(name= "comune") String comune){
        List<Prenotazione> pippo = prenotazioniService.getAlfredo(comune);
        if(pippo==null || pippo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pippo, HttpStatus.OK);
    } 

    @PostMapping("/public/prenota/data")
    public boolean prenotaData(@RequestBody Long id){
        //con il token mi creo un donatore
        prenotazioniService.prenotaData(id,null);
        return true;
    }

    @PostMapping("/public/getRegioni")
    public Set<String> searchRegioni(){
        return sedeAvisService.getRegioni();
    }
    
    @PostMapping("/public/getProvincie")
    public Set<String> searchProvincie(@RequestBody String regione){
        return sedeAvisService.getProvincie(regione);
    }
    
    @PostMapping("/public/getComuni")
    public Set<String> searchComuni(@RequestBody String provincia){
        return sedeAvisService.getComuni(provincia);
    }



}
