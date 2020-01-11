package com.avis.rest_controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.avis.dto.DateDto;
import com.avis.dto.PrenotazioneDto;
import com.avis.models.Prenotazione;
import com.avis.models.Utente;
import com.avis.services.PrenotazioniService;
import com.avis.services.SedeAvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "*" })
public class PrenotazioneController {

    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private SedeAvisService sedeAvisService;

    @PostMapping("/prenotazione")
    public ResponseEntity<List<Prenotazione>> getDateLibere(@RequestBody String comune) {
        List<Prenotazione> dateLibere = prenotazioniService.getDateLibere(comune);
        if (dateLibere == null || dateLibere.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Prenotazione>>(dateLibere, HttpStatus.OK);
    }

    @PutMapping("/prenotazione/donatore")
    public ResponseEntity<String> prenotaData(@RequestBody PrenotazioneDto prenotazione) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (utente.getRuolo().equals("donatore")) {
            prenotazione.setEmailDonatore(utente.getEmail());
        }
        if (!prenotazioniService.prenotaData(prenotazione)) {
            return new ResponseEntity<String>("Prenotazione non effettuata", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Prenotazione effettuata", HttpStatus.OK);
    }

    @PostMapping("/prenotazione/getRegioni")
    public ResponseEntity<Set<String>> searchRegioni() {
        return new ResponseEntity<Set<String>>(sedeAvisService.getRegioni(), HttpStatus.OK);
    }

    @PostMapping("/prenotazione/getProvince")
    public ResponseEntity<Set<String>> searchProvince(@RequestBody String regione) {
        System.out.println(regione);
        return new ResponseEntity<Set<String>>(sedeAvisService.getProvince(regione), HttpStatus.OK);
    }

    @PostMapping("/prenotazione/getComuni")
    public ResponseEntity<Set<String>> searchComuni(@RequestBody String provincia) {
        return new ResponseEntity<Set<String>>(sedeAvisService.getComuni(provincia), HttpStatus.OK);
    }

    @PostMapping("/handlerDate/insert")
    public ResponseEntity<String> inserisciDate(@RequestBody DateDto dateLibere, HttpServletRequest req) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!prenotazioniService.save(dateLibere, utente.getId())) {
            return new ResponseEntity<String>("Data non inserita", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Data inserita correttamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/handlerDate/remove")
    public ResponseEntity<String> deleteDate(@RequestBody long prenotazione) {
        if (!prenotazioniService.delete(prenotazione)) {
            return new ResponseEntity<String>("Data non cancellata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Data rimossa correttamente", HttpStatus.OK);
    }

}
