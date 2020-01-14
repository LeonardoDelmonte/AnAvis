package com.avis.rest_controller;

import com.avis.models.Utente;
import com.avis.services.EmergenzaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmergenzaController {

    @Autowired
    private EmergenzaService emergenzaService;

    @PostMapping("/requestEmerg/insert")
    public ResponseEntity<String> insertEmergenza(@RequestBody String gruppo) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!emergenzaService.save(gruppo, utente.getId())) {
            return new ResponseEntity<String>("Emergenza non inviata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Emergenza inviata correttamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/requestEmerg/remove")
    public ResponseEntity<String> deleteEmergenza(@RequestBody long emergenza) {
        if (!emergenzaService.delete(emergenza)) {
            new ResponseEntity<String>("Emergenza non cancellata", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Emergenza cancellata", HttpStatus.OK);
    }

}