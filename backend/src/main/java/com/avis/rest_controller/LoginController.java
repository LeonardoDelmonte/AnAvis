package com.avis.rest_controller;

import com.avis.services.AuthenticationService;
import javax.servlet.http.HttpServletResponse;
import com.avis.security.JwtTokenUtil;
import com.avis.dto.JwtRequest;
import com.avis.models.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "*" })
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = "public/login", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response)
            throws Exception {
        // qui la pw è in chiaro...non credo vada bene
        try {
            authService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPw());
        } catch (DisabledException e) {
            return new ResponseEntity<>("Login fallito, utente disabilitato", HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Login fallito, credenziali errate", HttpStatus.UNAUTHORIZED);
        }
        Utente utente = (Utente) authService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(utente);
        response.setHeader(tokenHeader, token);
        return new ResponseEntity<>("Login effettuato con successo", HttpStatus.OK);

    }
}
