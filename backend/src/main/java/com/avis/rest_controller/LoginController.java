package com.avis.rest_controller;
import com.avis.exception.AuthenticationException;
import com.avis.security.dto.JwtAuthenticationRequest;
import com.avis.security.dto.JwtAuthenticationResponse;
import com.avis.services.JwtAuthenticationService;
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
    private JwtAuthenticationService jwtAuthenticationService;

    @RequestMapping(value = "public/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
        final JwtUser jwtUser = jwtAuthenticationService.findUser(authenticationRequest.getEmail(),authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(jwtUser);       
        response.setHeader(tokenHeader,token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtUser.getUsername(),jwtUser.getAuthorities()));
    }
}

    //refresho un token che sta per scadere, solo un utente autenticato piò arrivare qui
   // @RequestMapping(value = "protected/refresh-token", method = RequestMethod.GET)

   //i metodi sono nella cartella backEnd_test

