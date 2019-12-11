package com.avis.services;

import java.util.List;

import com.avis.models.Donatore;
import com.avis.models.Ente;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.EnteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService{

    @Autowired
    private EnteRepository enteRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;

    public void save(Ente utente){
        enteRepository.save(utente);
    }

    public void save(Donatore donatore){
        donatoreRepository.save(donatore);
    }

	public List<Ente> retriveAllSedi() {
		return enteRepository.findAll();
	}


}





