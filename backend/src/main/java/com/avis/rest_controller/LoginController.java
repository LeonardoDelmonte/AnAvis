package com.avis.rest_controller;
import com.avis.services.AuthenticationService;
import com.avis.security.JwtTokenUtil;
import com.avis.dto.JwtRequest;
import org.springframework.security.core.AuthenticationException;
import com.avis.models.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = "public/login", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(
        @RequestBody JwtRequest authenticationRequest)throws Exception { 
        try {
            authService.authenticate(authenticationRequest);
            Utente utente = (Utente) authService.loadUserByUsername(authenticationRequest.getEmail());
            final String token = jwtTokenUtil.generateToken(utente);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (DisabledException e) {
            return new ResponseEntity<>("Login fallito, utente disabilitato", HttpStatus.UNAUTHORIZED);
        } catch (AuthenticationException e){
            return new ResponseEntity<>("Login fallito, credenziali errate", HttpStatus.UNAUTHORIZED);
        }
        
    }


    //refreshed Token(){
        //
    //}
}
