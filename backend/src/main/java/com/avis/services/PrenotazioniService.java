package com.avis.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.avis.models.Donatore;
import com.avis.models.Prenotazione;
import com.avis.models.PrenotazioneDto;
import com.avis.models.SedeAvis;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService{

    //prenotazione ha un id sede e non una stringa;

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    
    @Autowired
    private SedeAvisRepository sedeAvisRepository;

    @Autowired
    private DonatoreRepository donatoreRepository;

    //fatto così solo per non dare errore, cambierà!
    /* public Optional<Prenotazione> getDateLibere(String comune){
        List<SedeAvis>
        listaDate = sedeAvisRepository.findByComune(comune);


        List<SedeAvis> list;

        list=sedeAvisRepository.findAll();
        list.stream().filter(e->e.getComune().compareTo(comune)==0);
        long id = list.get(0).getId();
        Optional<Prenotazione> freeDate;
        freeDate=prenotazioniRepository.findByIdSedeAvis(id);
        freeDate.filter(e->e.getIdDonatore()==null);
        return freeDate;     
    } */

    public boolean prenotaData(PrenotazioneDto prenotazioneDto){
        //controlla se la data esiste ancora
        //if(idDonatore==null)return false;
        Optional<Donatore> donatore = donatoreRepository.findById(prenotazioneDto.getIdDonatore());
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(prenotazioneDto.getIdPrenotazione());
        prenotazione.get().setIdDonatore(donatore.get());
        prenotazioniRepository.saveAndFlush(prenotazione.get());
        return true;
    }

    public boolean save(Prenotazione dataLibera){
        prenotazioniRepository.save(dataLibera);    
        return true;
    }

	public List<Prenotazione> getDateLibere(String comune) {
        SedeAvis sede = sedeAvisRepository.findByComune(comune);
        Optional<List<Prenotazione>> pippo = prenotazioniRepository.findByIdSedeAvis(sede);
        if (!pippo.isPresent()){
            return null;
        }
        /* List<Date> alfredo = new ArrayList<>();
        for (Prenotazione prenotazione : pippo.get()) {
            if(prenotazione.getIdDonatore()==null){
                alfredo.add(prenotazione.getDate());
            }
        } */
        return pippo.get().stream().filter(e->e.getIdDonatore()==null).collect(Collectors.toList());
	}
    
}