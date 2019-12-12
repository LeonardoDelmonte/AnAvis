package com.avis.rest_controller;

import com.avis.exception.AuthenticationException;
import com.avis.models.Utente;
import com.avis.security.dto.JwtAuthenticationResponse;
import com.avis.services.UtenteService;
import com.avis.security.JwtTokenUtil;
import com.avis.security.dto.JwtUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins={ "*" })
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UtenteService utenteService;


    @RequestMapping(value = "public/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Utente utente,HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
        final JwtUser jwtUser = utenteService.findUser(utente);
        final String token = jwtTokenUtil.generateToken(jwtUser);       
        response.setHeader(tokenHeader,token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtUser.getUsername(),jwtUser.getAuthorities()));
    }
}


