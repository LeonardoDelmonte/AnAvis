package com.avis.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.avis.models.Donatore;
import com.avis.dto.DateDto;
import com.avis.models.Prenotazione;
import com.avis.dto.PrenotazioneDto;
import com.avis.models.SedeAvis;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService {

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    @Autowired
    private SedeAvisRepository sedeAvisRepository;

    @Autowired
    private DonatoreRepository donatoreRepository;

    public boolean prenotaData(PrenotazioneDto prenotazioneDto) {
        // controlla se la data esiste ancora
        // if(idDonatore==null)return false;
        // il donatore verrà preso col token
        Optional<Donatore> donatore = donatoreRepository.findById(prenotazioneDto.getIdDonatore());
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(prenotazioneDto.getIdPrenotazione());
        prenotazione.get().setIdDonatore(donatore.get());
        prenotazioniRepository.saveAndFlush(prenotazione.get());
        return true;
    }

    public boolean save(DateDto dateLibere) {
        Timestamp data2 = dateLibere.getDataFinale();
        Timestamp data1 = dateLibere.getDataIniziale();
        Prenotazione prenotazione;
        // la sede verrà presa col token
        SedeAvis sedeAvis = sedeAvisRepository.findById(1).get();
        do {
            prenotazione = new Prenotazione(sedeAvis, data1);
            prenotazioniRepository.save(prenotazione);
            data1 = new Timestamp(data1.getTime() + TimeUnit.MINUTES.toMillis(15));
        } while (data1.compareTo(data2) != 0);
        return true;
    }

    public boolean delete(long id) {
        Prenotazione prenotazione = prenotazioniRepository.findById(id).get();
        prenotazioniRepository.delete(prenotazione);
		return true;
	}

    public List<Prenotazione> getDateLibere(String comune) {
        SedeAvis sede = sedeAvisRepository.findByComune(comune);
        Optional<List<Prenotazione>> pippo = prenotazioniRepository.findByIdSedeAvis(sede);
        if (!pippo.isPresent()) {
            return null;
        }
        return pippo.get().stream().filter(e -> e.getIdDonatore() == null).collect(Collectors.toList());
    }

}