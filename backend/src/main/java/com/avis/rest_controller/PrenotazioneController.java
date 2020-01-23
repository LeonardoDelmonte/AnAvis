package com.avis.rest_controller;

import java.sql.Timestamp;
import java.util.List;
import com.avis.dto.DateDto;
import com.avis.dto.PrenotazioneDto;
import com.avis.models.Prenotazione;
import com.avis.models.Utente;
import com.avis.services.PrenotazioniService;
import com.avis.services.SedeAvisService;
import com.avis.utils.ApiResponse;
import com.avis.utils.InterfaceApi;

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

    @PostMapping("/prenotazione/date-libere")
    public ResponseEntity<InterfaceApi> getDateLibere(@RequestBody DateDto dto) {
        List<Prenotazione> dateLibere = prenotazioniService.getDateLibere(dto);
        if (dateLibere == null || dateLibere.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("nessuna data disponibile con questi criteri di ricerca"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(dateLibere), HttpStatus.OK);
    }

    @PutMapping("/prenotazione")
    public ResponseEntity<InterfaceApi> prenotaData(@RequestBody PrenotazioneDto prenotazione) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (utente.getRuolo().equals("donatore")) {
            prenotazione.setEmailDonatore(utente.getEmail());
        }
        ApiResponse<String> response = prenotazioniService.prenotaData(prenotazione);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/donatore/cancella-prenotazione")
    public ResponseEntity<InterfaceApi> deletePrenotazione(@RequestBody long prenotazione) {
        if (!prenotazioniService.deletePrenotazione(prenotazione)) {
            return new ResponseEntity<>(new ApiResponse<>("Prenotazione non cancellata"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse<>("Prenotazione rimossa correttamente"), HttpStatus.OK);
    }

    @PostMapping("/gestione-date/inserimento")
    public ResponseEntity<InterfaceApi> insertDate(@RequestBody DateDto dateLibere) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse<Timestamp> response = prenotazioniService.save(dateLibere, utente.getId());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/gestione-date/cancellazione")
    public ResponseEntity<InterfaceApi> deleteDate(@RequestBody long prenotazione) {
        if (!prenotazioniService.deleteDate(prenotazione)) {
            return new ResponseEntity<>(new ApiResponse<>("Data non cancellata"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse<>("Data rimossa correttamente"), HttpStatus.OK);
    }

    @GetMapping("/gestione-date/prenotazioni")
    public ResponseEntity<InterfaceApi> getPrenotazioni() {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse<Prenotazione> response = prenotazioniService.getPrenotazioni(utente.getId());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/prenotazione/regioni")
    public @ResponseBody ResponseEntity<InterfaceApi> searchRegioni() {
        System.out.println(sedeAvisService.getRegioni());
        return new ResponseEntity<>(new ApiResponse<>(sedeAvisService.getRegioni()), HttpStatus.OK);
    }

    @GetMapping("/prenotazione/province/{regione}")
    public @ResponseBody ResponseEntity<InterfaceApi> searchProvince(@PathVariable String regione) {
        return new ResponseEntity<>(new ApiResponse<>(sedeAvisService.getProvince(regione)), HttpStatus.OK);
    }

    @GetMapping("/prenotazione/comuni/{provincia}")
    public @ResponseBody ResponseEntity<InterfaceApi> searchComuni(@PathVariable String provincia) {
        return new ResponseEntity<>(new ApiResponse<>(sedeAvisService.getComuni(provincia)), HttpStatus.OK);
    }

}
