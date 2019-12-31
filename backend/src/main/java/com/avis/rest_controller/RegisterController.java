package com.avis.rest_controller;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
public class RegisterController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
	private PasswordEncoder bcryptEncoder;
    
    @RequestMapping(value = "public/register", method = RequestMethod.POST)
    public boolean register(@RequestBody Utente utente) {
        //la pw la devo ricevere già criptata dal frontend
        utente.setPw(bcryptEncoder.encode(utente.getPw()));
        return authService.save(createUtente(utente));    
    }

    private Utente createUtente(Utente utente){
        //ruolo required
        switch(utente.getRuolo()){
            case "donatore": 
                return new Donatore(utente.getEmail(), utente.getPw(), utente.getRuolo());
            case "sedeAvis": 
                return new SedeAvis(utente.getEmail(), utente.getPw(), utente.getRuolo());
            case "centroTrasfusioni": 
                return new CentroTrasfusione(utente.getEmail(), utente.getPw(), utente.getRuolo());
            default : return null;
        }
    }

}