package com.avis.rest_controller;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({ "*" })
public class RegisterController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    /*
     * @Value("#{'${list.of.auth.for.Donatore}'.split(',')}") private
     * ArrayList<SimpleGrantedAuthority> authoritiesDonatore;
     * 
     * @Value("#{'${list.of.auth.for.SedeAvis}'.split(',')}") private
     * ArrayList<SimpleGrantedAuthority> authoritiesSedeAvis;
     * 
     * @Value("#{'${list.of.auth.for.CentroTrasf}'.split(',')}") private
     * ArrayList<SimpleGrantedAuthority> authoritiesCentroTrasf;
     */

    @RequestMapping(value = "public/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Utente utente) {
        // la pw la devo ricevere già criptata dal frontend
        utente.setPw(bcryptEncoder.encode(utente.getPassword()));
        if (!authService.save(createUtente(utente))) {
            return new ResponseEntity<String>("Utente non registrato", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Utente registrato correttamente", HttpStatus.OK);
    }

    private Utente createUtente(Utente utente) {
        // ruolo required
        switch (utente.getRuolo()) {
        case "donatore":
            return new Donatore(utente.getEmail(), utente.getPassword(), utente.getRuolo());
        case "sedeAvis":
            return new SedeAvis(utente.getEmail(), utente.getPassword(), utente.getRuolo());
        case "centroTrasfusioni":
            return new CentroTrasfusione(utente.getEmail(), utente.getPassword(), utente.getRuolo());
        default:
            return null;
        }
    }

}