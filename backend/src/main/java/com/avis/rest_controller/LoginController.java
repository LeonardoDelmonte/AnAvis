package com.avis.rest_controller;

import com.avis.services.AuthenticationService;
import javax.servlet.http.HttpServletResponse;
import com.avis.security.JwtTokenUtil;
import com.avis.dto.JwtRequest;
import com.avis.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public void createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response)
            throws Exception {
        // qui la pw è in chiaro...non credo vada bene
        authService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPw());
        final JwtUser userDetails = (JwtUser) authService.loadUserByUsername(authenticationRequest.getEmail());
        if (userDetails != null) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            response.setHeader(tokenHeader, token);
        } else {// meglio farlo con ResponseEntity
            response.setHeader(tokenHeader, null);
        }
    }
}
