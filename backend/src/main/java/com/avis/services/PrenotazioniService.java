package com.avis.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private ProfiloService profilo;

    public boolean prenotaData(PrenotazioneDto prenotazioneDto) {
        Donatore donatore = donatoreRepository.findByEmail(prenotazioneDto.getEmailDonatore());
        if (donatore == null)
            return false;
        profilo.checkAbilitazione(donatore.getId());
        if (donatore.getAbilitazioneDonazione()==0)
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

    public Map<String,List<Timestamp>> save(DateDto dateLibere, Long idSede) {
        Optional<SedeAvis> sedeAvis = sedeAvisRepository.findById(idSede);
        if (!sedeAvis.isPresent())
            return null;  
        Timestamp data1 = dateLibere.getDataIniziale();
        List<Timestamp> listError = new ArrayList<>();
        List<Timestamp> listOK = new ArrayList<>();
        while(data1.compareTo(dateLibere.getDataFinale()) != 0){
            if (!prenotazioniRepository.findByIdSedeAvisAndDate(sedeAvis.get(), data1).isPresent()) {
                    prenotazioniRepository.save(new Prenotazione(sedeAvis.get(), data1));
                    listOK.add(data1);
            }else{
                listError.add(data1);
            }
            data1 = new Timestamp(data1.getTime() + TimeUnit.MINUTES.toMillis(15));
        }
        Map<String,List<Timestamp>> map = new HashMap<>();
        map.put("listOK",listOK);
        map.put("listError",listError);
        return map;
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
        Optional<List<Prenotazione>> dateLibere = prenotazioniRepository.findByIdSedeAvisAndDateBetween(sede,
                dto.getDataIniziale(), dto.getDataFinale());
        if (!dateLibere.isPresent()) {
            return null;
        }
        return dateLibere.get().stream().filter(e -> e.getIdDonatore() == null).collect(Collectors.toList());
    }

}