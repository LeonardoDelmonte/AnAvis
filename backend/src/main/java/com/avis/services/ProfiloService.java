package com.avis.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.avis.dto.CredenzialiDto;
import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.models.Prenotazione;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.ModuloRepository;
import com.avis.repositories.PrenotazioniRepository;
import com.avis.repositories.SedeAvisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfiloService {

    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;
    @Autowired
    private SedeAvisRepository sedeAvisRepository;
    @Autowired
    private CentroTrasfusioneRepository centroRepository;
    @Autowired
    private PrenotazioniRepository prenotazioneRepository;

    public Utente modificaModulo(Modulo newModulo, Long idDonatore) {
        Optional<Donatore> donatore = donatoreRepository.findById(idDonatore);
        if (!donatore.isPresent()) {
            return null;
        }
        Optional<Modulo> oldModulo = moduloRepository.findById(idDonatore);
        if (!oldModulo.isPresent()) {
            newModulo.setId(idDonatore);
            moduloRepository.save(newModulo);
            donatore.get().setModulo(newModulo);
            donatore.get().setAbilitazioneDonazione((byte) 1);
            donatoreRepository.save(donatore.get());
            return donatore.get();
        } else {
            moduloRepository.save(newModulo);
            return donatore.get();
        }
    }

    public Utente showInfo(Utente utente) {
        switch (utente.getRuolo()) {
        case "donatore":
            return donatoreRepository.findById(utente.getId()).get();
        case "sedeAvis":
            return sedeAvisRepository.findById(utente.getId()).get();
        case "centroTrasfusione":
            return centroRepository.findById(utente.getId()).get();
        default:
            return null;
        }
    }

    public Utente modificaCredenziali(CredenzialiDto credenziali, Utente utente) {
        switch (utente.getRuolo()) {
        case "donatore":
            Donatore don = credenziali.getDonatore();
            if (don != null && utente.getId() == don.getId()) {
                donatoreRepository.save(don);
                return don;
            }
            return null;
        case "sedeAvis":
            SedeAvis sede = credenziali.getSedeAvis();
            if (sede != null && utente.getId() == sede.getId()) {
                sedeAvisRepository.save(sede);
                return sede;
            }
            return null;
        case "centroTrasfusione":
            CentroTrasfusione centro = credenziali.getCentroTrasfusione();
            if (centro != null && utente.getId() == centro.getId()) {
                centroRepository.save(centro);
                return centro;
            }
            return null;
        default:
            return null;
        }
    }

    public Donatore checkAbilitazione(String email) {
        //check exception
        Donatore donatore = donatoreRepository.findByEmail(email);
        if (donatore.getModulo() == null)
            return donatore;
        Long date = new Date().getTime();
        Long last = 0L;
        Optional<List<Prenotazione>> list = prenotazioneRepository.findByIdDonatore(donatore);
        if (list.isPresent())
            last = list.get().get(list.get().size() - 1).getDate().getTime();
        // 
        if (date - last > 7884008640L){
            donatore.setAbilitazioneDonazione((byte) 1);
            donatoreRepository.save(donatore);
        }
        return donatore;   
    }
}
