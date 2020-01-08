package com.avis.services;

import java.util.Optional;

import com.avis.models.Donatore;
import com.avis.models.Modulo;
import com.avis.models.SedeAvis;
import com.avis.models.Utente;
import com.avis.repositories.AuthenticationRepository;
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
    private AuthenticationRepository utenteRepository;
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
            newModulo.setId(idDonatore);
            moduloRepository.save(newModulo);
            return true;
        }
    }

    /*
     * public boolean modificaCredenziali(Donatore newDonatore, Long id) {
     * Optional<Donatore> donatore = donatoreRepository.findById(id); if
     * (!donatore.isPresent()) { return false; }
     * donatore.get().setNome(newDonatore.getNome());
     * donatore.get().setCognome(newDonatore.getCognome());
     * donatoreRepository.save(donatore.get()); return true;
     * 
     * }
     */

    public Utente showInfo(Long id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (!utente.isPresent()) {
            return null;
        }
        switch (utente.get().getRuolo()) {
        case "donatore":
            return donatoreRepository.findById(id).get();
        case "sedeAvis":
            return sedeAvisRepository.findById(id).get();
        case "centroTrasfusioni":
            return centroRepository.findById(id).get();
        default:
            return null;
        }
    }

    public boolean modificaCredenziali(Utente utente) {
        switch (utente.getRuolo()) {
        case "donatore":
            donatoreRepository.save((Donatore) utente);
            return true;
        case "sedeAvis":
            sedeAvisRepository.save((SedeAvis) utente);
            return true;
        case "centroTrasfusioni":
        default:
            return false;
        }
        //donatoreRepository.save(utente);
        //return true;
    }
}
