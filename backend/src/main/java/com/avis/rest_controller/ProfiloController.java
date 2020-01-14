package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;

import com.avis.dto.CredenzialiDto;
import com.avis.models.Modulo;
import com.avis.models.Utente;
import com.avis.services.ProfiloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProfiloController {

    @Autowired
    private ProfiloService profiloService;

    @PutMapping("/profilo/modificaModulo")
    public ResponseEntity<String> modificaModulo(@RequestBody Modulo modulo, HttpServletRequest req) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!profiloService.modificaModulo(modulo, utente.getId())) {
            return new ResponseEntity<String>("Modulo non modificato", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Modulo modificato", HttpStatus.OK);
    }

    @PutMapping("/profilo/modificaCredenziali")
    public ResponseEntity<String> modificaCredenziali(@RequestBody CredenzialiDto credenziali) {
        Utente u = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean bool;
        switch(u.getRuolo()){
            case "donatore":
                bool = profiloService.modificaCredenziali(credenziali.getDonatore(),u.getId());
                break;
            case "sedeAvis":
                bool = profiloService.modificaCredenziali(credenziali.getSede(),u.getId());
                break;
            case "centroTrasfusione":
                bool = profiloService.modificaCredenziali(credenziali.getCentro(),u.getId());
                break;
            default:
                bool = false;
        }
        if (bool)
            return new ResponseEntity<String>("Credenziali modificate", HttpStatus.OK);
        return new ResponseEntity<String>("Credenziali non modificate", HttpStatus.NO_CONTENT);
    }
    

    @GetMapping("/profilo/showInfo")
    public ResponseEntity<Utente> showInfo() {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utente = profiloService.showInfo(utente);
        if (utente == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Utente>(utente, HttpStatus.OK);

    }
}