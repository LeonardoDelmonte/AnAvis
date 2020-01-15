package com.avis.rest_controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrenotazioneController {

    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private SedeAvisService sedeAvisService;

    @PostMapping("/prenotazione")
    public ResponseEntity<List<Prenotazione>> getDateLibere(@RequestBody DateDto dto) {
        List<Prenotazione> dateLibere = prenotazioniService.getDateLibere(dto);
        if (dateLibere == null || dateLibere.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Prenotazione>>(dateLibere, HttpStatus.OK);
    }

    // questo va bene per sede e donatore, basta che se è una sede lorenzo
    // aggiunge il campo email al dto
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
    

    @PostMapping("/handlerDate/insert")
    public ResponseEntity<Map<String,List<Timestamp>>> inserisciDate(@RequestBody DateDto dateLibere, HttpServletRequest req) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,List<Timestamp>> map = prenotazioniService.save(dateLibere, utente.getId());
        if (map==null) {
            //sedeAvis non esistente
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String,List<Timestamp>>>(map, HttpStatus.CREATED);
    }

    @DeleteMapping("/handlerDate/remove")
    public ResponseEntity<String> deleteDate(@RequestBody long prenotazione) {
        if (!prenotazioniService.delete(prenotazione)) {
            return new ResponseEntity<String>("Data non cancellata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Data rimossa correttamente", HttpStatus.OK);
    }

    @GetMapping("/prenotazione/getRegioni")
    public @ResponseBody ResponseEntity<Set<String>> searchRegioni() {
        System.out.println(sedeAvisService.getRegioni());
        return new ResponseEntity<Set<String>>(sedeAvisService.getRegioni(), HttpStatus.OK);
    }

    @GetMapping("/prenotazione/getProvince/{regione}")
    public @ResponseBody ResponseEntity<Set<String>> searchProvince(@PathVariable String regione) {
        return new ResponseEntity<Set<String>>(sedeAvisService.getProvince(regione), HttpStatus.OK);
    }

    @GetMapping("/prenotazione/getComuni/{provincia}")
    public @ResponseBody ResponseEntity<Set<String>> searchComuni(@PathVariable String provincia) {
        return new ResponseEntity<Set<String>>(sedeAvisService.getComuni(provincia), HttpStatus.OK);
    }

}
