package com.avis.rest_controller;

import java.util.ArrayList;
import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    
    @Value("#{'${list.of.auth.for.Donatore}'.split(',')}") 
    private ArrayList<SimpleGrantedAuthority> authoritiesDonatore;
    @Value("#{'${list.of.auth.for.SedeAvis}'.split(',')}") 
    private ArrayList<SimpleGrantedAuthority> authoritiesSedeAvis; 
    @Value("#{'${list.of.auth.for.CentroTrasf}'.split(',')}") 
    private ArrayList<SimpleGrantedAuthority> authoritiesCentroTrasf;
    
    
    @RequestMapping(value = "public/register", method = RequestMethod.POST)
    public boolean register(@RequestBody Utente utente) {
        //la pw la devo ricevere già criptata dal frontend
        utente.setPw(bcryptEncoder.encode(utente.getPassword()));
        return authService.save(createUtente(utente));    
    }

    private Utente createUtente(Utente utente){
        //ruolo required
        switch(utente.getRuolo()){
            case "donatore": 
                return new Donatore(utente.getEmail(), utente.getPassword(), utente.getRuolo(),authoritiesDonatore);
            case "sedeAvis":                             
                return new SedeAvis(utente.getEmail(), utente.getPassword(), utente.getRuolo(),authoritiesSedeAvis);
            case "centroTrasfusioni":
                return new CentroTrasfusione(utente.getEmail(), utente.getPassword(), utente.getRuolo(),authoritiesCentroTrasf);
            default : return null;
        }
    }

}