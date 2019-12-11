package com.avis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avis.models.Prenotazione;
import com.avis.models.Utente;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.EnteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService{

    //prenotazione ha un id sede e non una stringa;

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    
    @Autowired
    private EnteRepository utenteRepository;

    //fatto così solo per non dare errore, cambierà!
    public Optional<Prenotazione> findBySedeAvis(String sede){
        List<Prenotazione> listDateSede;
        List<Utente> listSedi;
        //listSedi = utenteRepository.findAll();
        listDateSede = prenotazioniRepository.findAll();
        //listSedi.stream().filter(e->e.getDenominazione().compareTo(sede)==0);
        //qualche if qua e la
        //listDateSede = prenotazioniRepository.findById(listSedi.get(0).getId());        
        return null;
    }

    public boolean prenotaData(int id){
        //controlla se la data esiste ancora
        //aggiorna la data mettendo il donatore
        //listDate.get(id).setDonatoreString("giovanni");
        return true;
    }

    public boolean saveNewDate(Prenotazione newDataLibera){
        //listDate.add(newDataLibera);      
        return true;
    }
    
}