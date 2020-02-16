package com.moc.controllers.autenticazione;

import com.moc.dto.AutenticazioneDto;

import com.moc.security.JwtTokenUtil;
import com.moc.security.UtenteCorrente;
import com.moc.services.UtenteService;
import com.moc.utils.InterfaceApi;
import com.moc.utils.ResponseLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Autenticazione
 * si occupa di mandare la richiesta post del form login al service autenticazione dedicato e se i dati sono corretti
 * chiede a JwtTokeUtil di creare un token per questo utente appena connesso
 * 
 * //TO DO//
 * si potrebbe tornare indietro l'utente o le sue caratteristiche , se serve ,
 * basta aggiungere qualche attributo in responseLogin() oltre al token.
 *   ***non tornare indietro ne PW ne ID;***
 * 
 */
@RestController
public class LogIn implements LogInInterface{

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<InterfaceApi> autenticazione(AutenticazioneDto authDto) throws NoSuchFieldException {
        
        utenteService.autenticazione(authDto);
      
        UtenteCorrente utenteCorrente = utenteService.loadUserByUsername(authDto.getEmail());

        final String token = jwtTokenUtil.generateToken(utenteCorrente);

        return new ResponseEntity<>(new ResponseLogin(token),HttpStatus.OK);
    }
   
}