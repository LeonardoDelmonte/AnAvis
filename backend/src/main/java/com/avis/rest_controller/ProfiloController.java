package com.avis.rest_controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.avis.dto.CredenzialiDto;
import com.avis.dto.ModuloDto;
import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.models.Utente;
import com.avis.services.ProfiloService;
import com.avis.utils.ApiResponse;
import com.avis.utils.InterfaceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfiloController {

    @Autowired
    private ProfiloService profiloService;

    @PutMapping("/profilo/modulo")
    public ResponseEntity<InterfaceApi> modificaModulo(@RequestBody ModuloDto moduloDto) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (utente.getRuolo().compareTo("donatore") == 0) {
            moduloDto.setEmail(utente.getEmail());
        }
        utente = profiloService.modificaModulo(moduloDto.getModulo(), moduloDto.getEmail());
        if (utente != null)
            return new ResponseEntity<>(new ApiResponse<>("Modulo modificato"), HttpStatus.OK);
        Logger.getGlobal().info("modulo non modificato");
        return new ResponseEntity<>(new ApiResponse<>("Modulo non modificato"), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/profilo/credenziali")
    public ResponseEntity<InterfaceApi> modificaCredenziali(@RequestBody CredenzialiDto credenziali) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utente = profiloService.modificaCredenziali(credenziali, utente);
        if (utente != null)
            return new ResponseEntity<>(new ApiResponse<>("Credenziali modificate", utente), HttpStatus.OK);
        Logger.getGlobal().info("credenziali non modificate");
        return new ResponseEntity<>(new ApiResponse<>("Credenziali non modificate"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/profilo/info")
    public ResponseEntity<InterfaceApi> showInfo() {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utente = profiloService.showInfo(utente);
        if (utente == null) {
            Logger.getGlobal().warning("sessione danneggiata");
            return new ResponseEntity<>(new ApiResponse<>("sessione danneggiata, riloggare"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse<>(utente, ""), HttpStatus.OK);
    }

    @GetMapping("/profilo/info-modulo/{email}")
    public @ResponseBody ResponseEntity<InterfaceApi> getModulo(@PathVariable String email) {
        Modulo modulo = profiloService.showModulo(email);
        if (modulo == null)
            return new ResponseEntity<>(new ApiResponse<>("Modulo non trovato"), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(new ApiResponse<>(modulo), HttpStatus.OK);
    }

}