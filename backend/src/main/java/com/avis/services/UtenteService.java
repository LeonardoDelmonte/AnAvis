package com.avis.services;
import com.avis.models.Utente;
import com.avis.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService{

    @Autowired
    private UtenteRepository utenteRepository;

    public void save(Utente utente){
        utenteRepository.save(utente);
    }


}





