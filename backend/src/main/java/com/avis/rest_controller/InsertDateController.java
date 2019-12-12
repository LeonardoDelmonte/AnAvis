package com.avis.rest_controller;

import com.avis.models.Prenotazione;
import com.avis.services.PrenotazioniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
public class InsertDateController{
    
    @Autowired
    private PrenotazioniService prenotazioniService;

    @PostMapping("/public/inserisciDate")
    public boolean inserisciDate(@RequestBody Prenotazione dataLibera){
        //ricevo il token e lo decapsulo
        //utenteRepository findByID(token)
        //Prenotazione new;
        prenotazioniService.save(dataLibera);
        return true;    
    }
    
    
}
