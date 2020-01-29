package com.avis.rest_controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.avis.models.Emergenza;
import com.avis.models.Utente;
import com.avis.services.EmergenzaService;
import com.avis.utils.ApiResponse;
import com.avis.utils.InterfaceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmergenzaController {

    @Autowired
    private EmergenzaService emergenzaService;

    @PostMapping("/emergenze/inserimento")
    public ResponseEntity<InterfaceApi> insertEmergenza(@RequestBody String gruppo) {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!emergenzaService.save(gruppo, utente.getId())) {
            return new ResponseEntity<>(new ApiResponse<>("Emergenza non inviata"), HttpStatus.BAD_REQUEST);
        }
        Logger.getGlobal().info("un emergenza è stata inviata");
        return new ResponseEntity<>(new ApiResponse<>("Emergenza inviata correttamente"), HttpStatus.CREATED);
    }

    @DeleteMapping("/emergenze/cancellazione")
    public ResponseEntity<InterfaceApi> deleteEmergenza(HttpServletRequest req) {

        if (!emergenzaService.delete(Long.valueOf(req.getHeader("data")))) {
            Logger.getGlobal().warning("fallimento cancellazione emergenza");
            new ResponseEntity<>(new ApiResponse<>("Emergenza non cancellata"), HttpStatus.BAD_REQUEST);
        }
        Logger.getGlobal().info("un emergenza è stata cancellata");
        return new ResponseEntity<>(new ApiResponse<>("Emergenza cancellata"), HttpStatus.OK);
    }

    @GetMapping("/emergenze")
    public @ResponseBody ResponseEntity<InterfaceApi> getEmergenze() {
        Utente utente = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(new ApiResponse<Emergenza>(emergenzaService.getEmergenze(utente.getId())), HttpStatus.OK);
    }

    @GetMapping("/countEmergenze")
    public @ResponseBody ResponseEntity<InterfaceApi> countGruppo() {
        return new ResponseEntity<>(new ApiResponse<>(emergenzaService.countGruppo()), HttpStatus.OK);
    }

}