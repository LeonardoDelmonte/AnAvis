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
import com.avis.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ApiResponse<String> prenotaData(PrenotazioneDto prenotazioneDto) {
        Donatore donatore = profilo.checkAbilitazione(prenotazioneDto.getEmailDonatore());
        if (donatore.getAbilitazioneDonazione() == 0)
            return new ApiResponse<>("non sei abilitato a donare", HttpStatus.FORBIDDEN);
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(prenotazioneDto.getIdDataLibera());
        if (!prenotazione.isPresent() || prenotazione.get().getIdDonatore() != null) {
            return new ApiResponse<>("la data scelta non è più disponibile", HttpStatus.CONFLICT);
        }
        prenotazione.get().setIdDonatore(donatore);
        prenotazioniRepository.save(prenotazione.get());
        donatore.setAbilitazioneDonazione((byte) 0);
        donatoreRepository.save(donatore);
        return new ApiResponse<>("data prenotata con successo", prenotazione.get());
    }

    public ApiResponse<Timestamp> save(DateDto dateLibere, Long idSede) {
        Optional<SedeAvis> sedeAvis = sedeAvisRepository.findById(idSede);
        if (!sedeAvis.isPresent())
            return new ApiResponse<>("sessione danneggiata, riloggare", HttpStatus.BAD_REQUEST);
        Timestamp data1 = dateLibere.getDataIniziale();
        List<Timestamp> listError = new ArrayList<>();
        List<Timestamp> listOK = new ArrayList<>();
        while (data1.compareTo(dateLibere.getDataFinale()) != 0) {
            if (prenotazioniRepository.findByIdSedeAvisAndDate(sedeAvis.get(), data1).isEmpty()) {
                prenotazioniRepository.save(new Prenotazione(sedeAvis.get(), data1));
                listOK.add(data1);
            } else {
                listError.add(data1);
            }
            data1 = new Timestamp(data1.getTime() + TimeUnit.MINUTES.toMillis(15));
        }
        return new ApiResponse<>("listOK", listOK, "listError", listError);
    }

    public boolean deleteDate(long id) {
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(id);
        if (!prenotazione.isPresent()) {
            return false;
        }
        prenotazioniRepository.delete(prenotazione.get());
        return true;
    }

    public boolean deletePrenotazione(long id) {
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(id);
        if (!prenotazione.isPresent()) {
            return false;
        }
        prenotazione.get().setIdDonatore(null);
        prenotazioniRepository.save(prenotazione.get());
        return true;
    }

    public List<Prenotazione> getDateLibere(DateDto dto) {
        // check exception
        SedeAvis sede = sedeAvisRepository.findByComune(dto.getComune());
        List<Prenotazione> dateLibere = prenotazioniRepository.findByIdSedeAvisAndDateBetween(sede,
                dto.getDataIniziale(), dto.getDataFinale());
        return dateLibere.stream().filter(e -> e.getIdDonatore() == null).collect(Collectors.toList());
    }

    public ApiResponse<Prenotazione> getPrenotazioni(long id) {
        Optional<SedeAvis> sedeAvis = sedeAvisRepository.findById(id);
        if (!sedeAvis.isPresent())
            return new ApiResponse<>("sessione danneggiata, riloggare", HttpStatus.BAD_REQUEST);
        List<Prenotazione> listPrenotate = new ArrayList<>();
        List<Prenotazione> listLibere = new ArrayList<>();
        List<Prenotazione> listPrenotazioni = prenotazioniRepository.findByIdSedeAvisAndDateAfter(sedeAvis.get(),
                new Timestamp(new Date().getTime()));
        if (listPrenotazioni.isEmpty())
            return new ApiResponse<Prenotazione>("listaPrenotate", listPrenotate, "listaLibere", listLibere);
        for (Prenotazione prenotazione : listPrenotazioni) {
            if (prenotazione.getIdDonatore() == null)
                listLibere.add(prenotazione);
            else
                listPrenotate.add(prenotazione);
        }
        return new ApiResponse<Prenotazione>("listaPrenotate", listPrenotate, "listaLibere", listLibere);
    }

    public ApiResponse<Prenotazione> getPrenotazioniDonatore(long id){
        List<Prenotazione> listPrenotazioni;
        Optional<Donatore> donatore = donatoreRepository.findById(id);
        if (!donatore.isPresent())
            return new ApiResponse<>("sessione danneggiata, riloggare", HttpStatus.BAD_REQUEST);
        listPrenotazioni = prenotazioniRepository.findByIdDonatore(donatore.get());
        return new ApiResponse<Prenotazione>(listPrenotazioni);
    }

}