package com.avis.rest_controller;

import com.avis.dto.DateDto;
import com.avis.services.PrenotazioniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
public class DateController {

    @Autowired
    private PrenotazioniService prenotazioniService;

    @PostMapping("/public/inserisciDate")
    public boolean inserisciDate(@RequestBody DateDto dateLibere) {
        // controllo il token
        return prenotazioniService.save(dateLibere);
    }

    @PostMapping("/public/rimuoviData")
    public boolean rimuoviDate(@RequestBody long prenotazione) {
        return prenotazioniService.delete(prenotazione);
    }

}
