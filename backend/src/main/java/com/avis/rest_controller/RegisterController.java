package com.avis.rest_controller;
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

    //Annotation @Valid
    @RequestMapping(value = "public/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Utente utente) {
        utente.setPw(bcryptEncoder.encode(utente.getPassword()));
        if (!authService.save(utente)) {
            return new ResponseEntity<String>("Utente non registrato", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Utente registrato correttamente", HttpStatus.OK);
    }

}