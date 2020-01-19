package com.avis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avis.models.CentroTrasfusione;
import com.avis.models.Emergenza;
import com.avis.repositories.CentroTrasfusioneRepository;
import com.avis.repositories.EmergenzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergenzaService {

    @Autowired
    private EmergenzaRepository emergenzaRepository;
    @Autowired
    private CentroTrasfusioneRepository centroRepository;

    public boolean delete(long idEmergenza) {
        Optional<Emergenza> emergenza = emergenzaRepository.findById(idEmergenza);
        if (!emergenza.isPresent()) {
            return false;
        }
        emergenzaRepository.delete(emergenza.get());
        return true;
    }

    public boolean save(String gruppo, Long idCentro) {
        Optional<CentroTrasfusione> centro = centroRepository.findById(idCentro);
        if (!centro.isPresent()) {
            return false;
        }
        emergenzaRepository.save(new Emergenza(centro.get(), gruppo));
        return true;
    }

    public List<Emergenza> getEmergenze(Long id) {
        Optional<List<Emergenza>> emergenze = emergenzaRepository.findByIdCentroTrasfusione(id);
        if(!emergenze.isPresent())
            return new ArrayList<>();
        return emergenze.get();
	}

}