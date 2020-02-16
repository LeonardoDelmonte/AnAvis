package com.moc.services;

import com.moc.dto.AutenticazioneDto;
import com.moc.models.Utente;
import com.moc.repositories.UtenteRepository;
import com.moc.security.UtenteCorrente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UtenteService
 */
@Service
public class UtenteService implements UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    private AuthenticationManager authenticationManager;


    public void registrazione(Utente utente) {
        if(utente==null)
            throw new IllegalArgumentException("argomento passato NULL");
        try{
            utenteRepository.save(utente);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("email alredy exists");
        }
    }

    public void autenticazione(AutenticazioneDto authDto){
        try{
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authDto.getEmail(), authDto.getPassword()));
        }catch(AuthenticationException e){
            throw new BadCredentialsException("credenziali errate");
        }
    }

    //metodo di UserDetailService , usato da authManager quindi per ora facciamo che serve perForza poi vediamo
    @Override
    public UtenteCorrente loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmail(email);
        return 
                UtenteCorrente.builder()
                      .id(utente.getId())
                      .email(utente.getEmail())
                      .password(utente.getPassword())
                      .ruolo(utente.getRuolo())
                      .autorizzazioni(utente.getAutorizzazioni())
                      .build();       
    }

    
}