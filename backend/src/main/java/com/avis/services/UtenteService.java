package com.avis.services;
import com.avis.models.Utente;
import com.avis.repositories.UtenteRepository;
import com.avis.security.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService{

    @Autowired
    private UtenteRepository utenteRepository;

    //solo per login, potrebbe anche sparire
    public JwtUser findUser(Utente utente){     
        JwtUser jwtUser = new JwtUser(utenteRepository.findByEmail(utente.getEmail()));
        //controlla pure la password e prendi il ruoolo
        return jwtUser;
    }

}





