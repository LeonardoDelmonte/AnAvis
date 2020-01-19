package com.avis.services;

import com.avis.dto.CredenzialiDto;
import com.avis.dto.JwtRequest;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

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
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public boolean save(CredenzialiDto utente) {
        if (utente.getDonatore() != null) {
            encode(utente.getDonatore());
            donatoreRepository.save(utente.getDonatore());
            return true;
        }
        if (utente.getSedeAvis() != null) {
            encode(utente.getSedeAvis());
            sedeAvisRepository.save(utente.getSedeAvis());
            return true;
        }
        if (utente.getCentroTrasfusione() != null) {
            encode(utente.getCentroTrasfusione());
            centroRepository.save(utente.getCentroTrasfusione());
            return true;
        }
        return false;
    }

    private void encode(Utente utente) {
        utente.setPassword(bcryptEncoder.encode(utente.getPassword()));
    }

    public void authenticate(JwtRequest credentials) throws Exception {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPw()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.findByEmail(email);
    }

}
