package com.avis.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.avis.dto.DateDto;
import com.avis.dto.PrenotazioneDto;
import com.avis.models.Donatore;
import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService{

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    
    @Autowired
    private SedeAvisRepository sedeAvisRepository;

    @Autowired
    private DonatoreRepository donatoreRepository;


    public boolean prenotaData(PrenotazioneDto prenotazioneDto) {
        Donatore donatore = donatoreRepository.findByEmail(prenotazioneDto.getEmailDonatore());
        if(donatore==null)
            return false;
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(prenotazioneDto.getIdDataLibera());
        if(!prenotazione.isPresent() || prenotazione.get().getIdDonatore()!=null){
            return false;
        }
        prenotazione.get().setIdDonatore(donatore);
        prenotazioniRepository.save(prenotazione.get());
        return true;
	}


    public boolean save(DateDto dateLibere,Long idSede) {
        Timestamp data1 = dateLibere.getDataIniziale();
        Optional<SedeAvis> sedeAvis = sedeAvisRepository.findById(idSede);
        if (!sedeAvis.isPresent()){
            return false;
        }
        do {         
            prenotazioniRepository.save(new Prenotazione(sedeAvis.get(), data1));
            data1 = new Timestamp(data1.getTime() + TimeUnit.MINUTES.toMillis(15));
        } while (data1.compareTo(dateLibere.getDataFinale()) != 0);
        return true;
    }

    public boolean delete(long id) {
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(id);
        if (!prenotazione.isPresent()){
            return false;
        }  
        prenotazioniRepository.delete(prenotazione.get());
		return true;
	}

	public List<Prenotazione> getDateLibere(String comune) {
        SedeAvis sede = sedeAvisRepository.findByComune(comune);
        Optional<List<Prenotazione>> dateLibere = prenotazioniRepository.findByIdSedeAvis(sede);
        if (!dateLibere.isPresent()){
            return null;
        }
        return dateLibere.get().stream().filter(e->e.getIdDonatore()==null).collect(Collectors.toList());
	}

}