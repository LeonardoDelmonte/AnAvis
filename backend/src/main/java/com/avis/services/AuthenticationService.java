package com.avis.services;

import com.avis.dto.JwtRequest;
import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.AuthenticationRepository;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.SedeAvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    private AuthenticationRepository authRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;
    @Autowired
    private SedeAvisRepository sedeAvisRepository;
    @Autowired
    private CentroTrasfusioneRepository centroRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
  
    public boolean save(Utente utente) {
        switch (utente.getRuolo()) {
            case "donatore":
                donatoreRepository.save(
                    new Donatore(utente.getEmail(), utente.getPassword(), utente.getRuolo()));
                    return true;
            case "sedeAvis":
                sedeAvisRepository.save(
                    new SedeAvis(utente.getEmail(), utente.getPassword(), utente.getRuolo()));
                    return true;
            case "centroTrasfusioni":
                centroRepository.save(     
                    new CentroTrasfusione(utente.getEmail(), utente.getPassword(), utente.getRuolo()));
                    return true;
            default:
                return false;
        }
    }

    
    public void authenticate(JwtRequest credentials) throws Exception {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPw()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.findByEmail(email);
    }

    
}
