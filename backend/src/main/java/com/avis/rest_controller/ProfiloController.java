package com.avis.rest_controller;

import javax.servlet.http.HttpServletRequest;

import com.avis.dto.CredenzialiDto;
import com.avis.models.Modulo;
import com.avis.models.Utente;
import com.avis.services.ProfiloService;
import com.avis.utils.ApiResponse;

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
    public ResponseEntity<Object> modificaModulo(@RequestBody Modulo modulo, HttpServletRequest req) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utente = profiloService.modificaModulo(modulo, utente.getId());
        if(utente!=null)
            return new ResponseEntity<>(new ApiResponse("Modulo non modificato"), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(new ApiResponse("Modulo modificato",utente), HttpStatus.OK);
    }

    @PutMapping("/profilo/modificaCredenziali")
    public ResponseEntity<Object> modificaCredenziali(@RequestBody CredenzialiDto credenziali) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utente = profiloService.modificaCredenziali(credenziali,utente);
        if (utente!=null)
            return new ResponseEntity<>(new ApiResponse("Credenziali modificate",utente), HttpStatus.OK);
        return new ResponseEntity<>(new ApiResponse("Credenziali non modificate"), HttpStatus.NO_CONTENT);
    }
    

    @GetMapping("/profilo/showInfo")
    public ResponseEntity<Object> showInfo() {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utente = profiloService.showInfo(utente);
        if (utente == null) {
            return new ResponseEntity<>(new ApiResponse("sessione danneggiata, riloggare"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(utente,""), HttpStatus.OK);
    }
}