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
import com.avis.repositories.AuthenticationRepository;
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
    @Autowired
    private AuthenticationRepository authRepository;


    public Utente modificaModulo(Modulo newModulo, String email) {
        Donatore donatore = donatoreRepository.findByEmail(email);
        if(donatore.getModulo().getId()!=newModulo.getId())
            return null;
        newModulo.setModuloCompilato((byte) 1);
        moduloRepository.save(newModulo);
        return donatore;
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

    public Modulo showModulo(String email){
        Utente utente = authRepository.findByEmail(email);
        Modulo modulo = null;
        if(utente==null)
            return modulo; 
        if(utente.getRuolo().compareTo("donatore")==0){
            Donatore donatore = (Donatore) utente;
            modulo = donatore.getModulo();
        }
        return modulo;
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
        // check exception
        Donatore donatore = donatoreRepository.findByEmail(email);
        if (donatore.getModulo().getModuloCompilato()==0)
            return donatore;
        Long date = new Date().getTime();
        Long last = 0L;
        List<Prenotazione> list = prenotazioneRepository.findByIdDonatore(donatore);
        if (!list.isEmpty())
            last = list.get(list.size() - 1).getDate().getTime();
        if (date - last > 7884008640L) {
            donatore.setAbilitazioneDonazione((byte) 1);
            donatoreRepository.save(donatore);
        }
        return donatore;
    }
}
