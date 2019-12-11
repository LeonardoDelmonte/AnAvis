package com.avis.services;

import java.util.List;

import com.avis.models.Ente;
import com.avis.models.Utente;
import com.avis.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService{

    @Autowired
    private UtenteRepository utenteRepository;

    public void save(Ente utente){
        utenteRepository.save(utente);
    }

	public List<Ente> retriveAllSedi() {
		return utenteRepository.findAll();
	}


}





