package com.avis.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.avis.dto.DateDto;
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

    public boolean prenotaData(Long idDataLibera, Long idDonatore){
        Optional<Donatore> donatore = donatoreRepository.findById(idDonatore);
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(idDataLibera);
        if(prenotazione.get().getIdDonatore()!=null){
            return false;
        }
        prenotazione.get().setIdDonatore(donatore.get());
        prenotazioniRepository.saveAndFlush(prenotazione.get());
        return true;
    }

    public boolean save(DateDto dateLibere,Long idSede) {
        Timestamp data2 = dateLibere.getDataFinale();
        Timestamp data1 = dateLibere.getDataIniziale();
        Prenotazione prenotazione;
        SedeAvis sedeAvis = sedeAvisRepository.findById(idSede).get();
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
        Optional<List<Prenotazione>> dateLibere = prenotazioniRepository.findByIdSedeAvis(sede);
        if (!dateLibere.isPresent()){
            return null;
        }
        return dateLibere.get().stream().filter(e->e.getIdDonatore()==null).collect(Collectors.toList());
	}
    
}