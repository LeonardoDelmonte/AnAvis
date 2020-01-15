package com.avis.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
public class PrenotazioniService {

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    @Autowired
    private SedeAvisRepository sedeAvisRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;

    public boolean prenotaData(PrenotazioneDto prenotazioneDto) {
        Donatore donatore = donatoreRepository.findByEmail(prenotazioneDto.getEmailDonatore());
        if (donatore == null)
            return false;
        if(!checkAbilitazione(donatore))
            return false;
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(prenotazioneDto.getIdDataLibera());
        if (!prenotazione.isPresent() || prenotazione.get().getIdDonatore() != null) {
            return false;
        }
        prenotazione.get().setIdDonatore(donatore);
        prenotazioniRepository.save(prenotazione.get());
        donatore.setAbilitazioneDonazione((byte) 0);
        donatoreRepository.save(donatore);
        return true;
    }

    // da provare
    private boolean checkAbilitazione(Donatore donatore) {
        Long date = new Date().getTime();Long last;       
        Optional<List<Prenotazione>> list = prenotazioniRepository.findByIdDonatore(donatore);
        if(!list.isPresent())
            return true;
        last = list.get().get(list.get().size()-1).getDate().getTime();
        if(date-last>7884008640L)
            return true;
        return false;
    }


    public List<Timestamp> save(DateDto dateLibere, Long idSede) {
        Optional<SedeAvis> sedeAvis = sedeAvisRepository.findById(idSede);
        if (!sedeAvis.isPresent())
            return null;  
        Timestamp data1 = dateLibere.getDataIniziale();
        List<Timestamp> listError = new ArrayList<>();
        while(data1.compareTo(dateLibere.getDataFinale()) != 0){
            if (!prenotazioniRepository.findByIdSedeAvisAndDate(sedeAvis.get(), data1).isPresent()) {
                    prenotazioniRepository.save(new Prenotazione(sedeAvis.get(), data1));
            }else{
                listError.add(data1);
            }
            data1 = new Timestamp(data1.getTime() + TimeUnit.MINUTES.toMillis(15));
        }
        return listError;     
    }


    public boolean delete(long id) {
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(id);
        if (!prenotazione.isPresent()) {
            return false;
        }
        prenotazioniRepository.delete(prenotazione.get());
        return true;
    }

    public List<Prenotazione> getDateLibere(DateDto dto) {
        SedeAvis sede = sedeAvisRepository.findByComune(dto.getComune());
        Optional<List<Prenotazione>> dateLibere = prenotazioniRepository.findByIdSedeAvisAndDateBetween(
            sede,dto.getDataIniziale(), dto.getDataFinale());
        if (!dateLibere.isPresent()) {
            return null;
        }
        return dateLibere.get().stream().filter(e -> e.getIdDonatore() == null).collect(Collectors.toList());
    }

}