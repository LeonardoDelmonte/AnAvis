package com.avis.rest_controller;

import java.util.List;
import com.avis.dto.DateDto;
import com.avis.dto.PrenotazioneDto;
import com.avis.models.Prenotazione;
import com.avis.models.Utente;
import com.avis.services.PrenotazioniService;
import com.avis.services.SedeAvisService;
import com.avis.utils.ApiResponse;

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
    public ResponseEntity<Object> getDateLibere(@RequestBody DateDto dto) {
        List<Prenotazione> dateLibere = prenotazioniService.getDateLibere(dto);
        if (dateLibere == null || dateLibere.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse
                ("nessuna data disponibile con questi criteri di ricerca"),HttpStatus.OK);    
        }
        return new ResponseEntity<>(new ApiResponse(dateLibere),HttpStatus.OK);
    }


    @PutMapping("/prenotazione/donatore")
    public ResponseEntity<Object> prenotaData(@RequestBody PrenotazioneDto prenotazione) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (utente.getRuolo().equals("donatore")) {
            prenotazione.setEmailDonatore(utente.getEmail());
        }
        ApiResponse response = prenotazioniService.prenotaData(prenotazione);
            return new ResponseEntity<>(response, response.getStatus());
    }
    

    @PostMapping("/handlerDate/insert")
    public ResponseEntity<Object> inserisciDate(@RequestBody DateDto dateLibere) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse response = prenotazioniService.save(dateLibere, utente.getId());
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/handlerDate/remove")
    public ResponseEntity<Object> deleteDate(@RequestBody long prenotazione) {
        if (!prenotazioniService.delete(prenotazione)) {
            return new ResponseEntity<>(new ApiResponse("Data non cancellata"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("Data rimossa correttamente"), HttpStatus.OK);
    }


    @GetMapping("/prenotazione/getRegioni")
    public @ResponseBody ResponseEntity<Object> searchRegioni() {
        System.out.println(sedeAvisService.getRegioni());
        return new ResponseEntity<>(sedeAvisService.getRegioni(), HttpStatus.OK);
    }

    @GetMapping("/prenotazione/getProvince/{regione}")
    public @ResponseBody ResponseEntity<Object> searchProvince(@PathVariable String regione) {
        return new ResponseEntity<>(sedeAvisService.getProvince(regione), HttpStatus.OK);
    }

    @GetMapping("/prenotazione/getComuni/{provincia}")
    public @ResponseBody ResponseEntity<Object> searchComuni(@PathVariable String provincia) {
        return new ResponseEntity<>(sedeAvisService.getComuni(provincia), HttpStatus.OK);
    }

}
