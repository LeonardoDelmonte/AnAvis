package com.avis.rest_controller;

import com.avis.services.AuthenticationService;
import com.avis.services.ProfiloService;
import com.avis.utils.ApiResponse;
import com.avis.utils.InterfaceApi;
import com.avis.security.JwtTokenUtil;
import com.avis.dto.JwtRequest;
import com.avis.models.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private ProfiloService profilo;

    @RequestMapping(value = "public/login", method = RequestMethod.POST)
    public ResponseEntity<InterfaceApi> createAuthenticationToken(
        @RequestBody JwtRequest authenticationRequest) throws Exception {

        authService.authenticate(authenticationRequest);

        Utente utente = (Utente) authService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(utente);

        if(utente.getRuolo().compareTo("donatore")==0)
            utente = profilo.checkAbilitazione(utente.getEmail());

        return new ResponseEntity<>(new ApiResponse<>(utente, token), HttpStatus.OK);
    }
}


        
    


      //refresho un token che sta per scadere, solo un utente autenticato piò arrivare qui
   // @RequestMapping(value = "protected/refresh-token", method = RequestMethod.GET)
   // public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
   //     String token = request.getHeader(tokenHeader);
   //     UserDetails userDetails =
   //             (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
   //     if (jwtTokenUtil.canTokenBeRefreshed(token)) {
   //         String refreshedToken = jwtTokenUtil.refreshToken(token);
   //         response.setHeader(tokenHeader,refreshedToken);
//
   //         return ResponseEntity.ok(new JwtAuthenticationResponse(userDetails.getUsername(),userDetails.getAuthorities()));
   //     } else {
   //         return ResponseEntity.badRequest().body(null);
   //     }
   // }
