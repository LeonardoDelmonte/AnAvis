package com.avis.services;

import java.util.Optional;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.DonatoreRepository;
import com.avis.repositories.ModuloRepository;
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

    public boolean modificaModulo(Modulo newModulo, Long idDonatore) {
        Optional<Donatore> donatore = donatoreRepository.findById(idDonatore);
        if (!donatore.isPresent()) {
            return false;
        }
        Optional<Modulo> oldModulo = moduloRepository.findById(idDonatore);
        if (!oldModulo.isPresent()) {
            newModulo.setId(idDonatore);
            moduloRepository.save(newModulo);
            donatore.get().setModulo(newModulo);
            donatoreRepository.save(donatore.get());
            return true;
        } else {
            moduloRepository.save(newModulo);
            return true;
        }
    }


    public Utente showInfo(Utente utente) {
        switch (utente.getRuolo()) {
        case "donatore":
            return donatoreRepository.findById(utente.getId()).get();
        case "sedeAvis":
            return sedeAvisRepository.findById(utente.getId()).get();
        case "centroTrasfusioni":
            return centroRepository.findById(utente.getId()).get();
        default:
            return null;
        }
    }
    

    public boolean modificaCredenziali(Donatore donatore) {
        donatoreRepository.save(donatore);
        return true;
    }
    public boolean modificaCredenziali(SedeAvis sede) {
        sedeAvisRepository.save(sede);
        return true;
    }
    public boolean modificaCredenziali(CentroTrasfusione centro) {
        centroRepository.save(centro);
        return true;
    }      
}
